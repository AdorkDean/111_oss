package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TTagManager;
import com.rc.portal.service.TTagRelationManager;
import com.rc.portal.vo.TCouponCard;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TTagExample;
import com.rc.portal.webapp.util.CustomDigestUtils;
import com.rc.portal.webapp.util.PageResult;

public class MemberAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private TMemberManager tmembermanager;
	private TCouponCardManager tcouponcardmanager;
	private TMember member;
	private Map resc;
	private TTagManager ttagmanager;
	private TTagRelationManager ttagrelationmanager;
	
	
	public String getMemberList() throws SQLException{
		
		Map map=new HashMap();
		if(model.getUsername()!=null&&!"".equals(model.getUsername().trim())){
			map.put("username", model.getUsername().trim());
		}
		
		if(model.getRealName()!=null&&!"".equals(model.getRealName().trim())){
			map.put("realName", model.getRealName().trim());
		}
		if(model.getMobile()!=null&&!"".equals(model.getMobile().trim())){
			map.put("mobile", model.getMobile().trim());
		}
		if(model.getLeader()!=null&&model.getLeader()!=-1){
			map.put("leader", model.getLeader());
		}
		if(model.getEnterprise()!=null&&model.getEnterprise()!=-1){
			map.put("enterprise", model.getEnterprise());
		}
		
		String tag = this.getRequest().getParameter("tag");
		
		if(StringUtils.isNotEmpty(tag)){
			map.put("tag", tag);
		}
		
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_member.selectMemberCountByMap", "t_member.selectMemberListByMap", rs.getP_curPage(), rs.getP_pageSize());
		
		TTagExample example = new TTagExample();
		
		example.createCriteria().andTypeEqualTo(0);
		
		this.getRequest().setAttribute("tags", this.ttagmanager.selectByExample(example));
		
		return "getMemberList";
	}
	/**
	 * 添加会员标记
	 */
	public void addTag() throws SQLException{
		
		String tagId = this.getRequest().getParameter("tagId");
		String relationIds = this.getRequest().getParameter("relationIds");
		
		if(StringUtils.isNotEmpty(tagId) && StringUtils.isNotEmpty(relationIds)){
			
			Map<String,Object> param = new HashMap<String,Object>();
			
			param.put("tagId", tagId);
			param.put("relationIds", relationIds.split(","));
			
			this.ttagrelationmanager.insertList(param);
			opensqlmanage.updateByMap(null, "t_tag_relation.delete_repeat");
			
			this.writeObjectToResponse(1, ContentType.application_json);
		}else{
			this.writeObjectToResponse(0, ContentType.application_json);
		}
		
	}
	
	//查看用户详情和修改信息
	public String toMemberInfo() throws Exception{
		Map map=new HashMap();
		List list = opensqlmanage.selectForListByMap_drug(map, "t_member_grade.selectObjectMember");
        this.getRequest().setAttribute("gradeList", list);
		return "toMemberInfo";
	}
	//锁定用户
	public void sockUser() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(model.getId()!=null&&model.getId()!=0){
			TMember member = tmembermanager.selectByPrimaryKey(model.getId());
			if(member!=null){
				if(model.getStatus()==1){
					member.setStatus(1);//停用
				}else if(model.getStatus()==2){
					member.setStatus(0);//启用
				}
				flag=tmembermanager.updateByPrimaryKeySelective(member);
			}
			
		}
		write.write(flag.toString());
		write.close();
	}
	//弹出重置密码
	public String toUpdatePassword(){
		if(model.getId()!=null&&model.getId()!=0){
			String password = (String)opensqlmanage.selectObjectByObject(model.getId(), "t_member.selectPassWordById");
			model.setPassword(password);
		}
		return "toUpdatePassword";
	}
	//重置密码
	public void updatePassword() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(model.getId()!=null&&model.getId()!=0){
			TMember member = tmembermanager.selectByPrimaryKey(model.getId());
			if(member!=null){
				if(model.getPassword()!=null&&!"".equals(model.getPassword().trim())){
					if(member.getPassword().equals(model.getPassword().trim())){
						flag=-1;
					}else{
						member.setPassword(CustomDigestUtils.md5Hex(model.getPassword().trim(), member));
						flag=tmembermanager.updateByPrimaryKeySelective(member);
					}
				}
			}
		}
		write.write(flag.toString());
		write.close();
	}
	
	//添加用户
	public void addMember() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Long flag=0L;
		
		String userName = this.getRequest().getParameter("userName");
		TMemberExample tme = new TMemberExample();
		tme.createCriteria().andUserNameEqualTo(userName);
		List list = tmembermanager.selectByExample(tme);
		if(null != list && list.size()>0){
			flag=2L; //用户已经存在
		}else{
			String password = this.getRequest().getParameter("password");
			String realName = this.getRequest().getParameter("realName");
			String email = this.getRequest().getParameter("email");
			String nickName = this.getRequest().getParameter("nickName");
			String gradeName = this.getRequest().getParameter("gradeName"); 
			TMember member = new TMember();
			member.setUserName(userName); //用户名
			member.setMobile(userName); //手机号码
			member.setPassword(CustomDigestUtils.md5Hex(password, member));
			member.setRealName(realName);
			member.setNickName(nickName);
			member.setEmail(email);
			member.setIsMobileCheck(1);
			member.setRegisterDate(new Date());
			member.setStatus(0);
			member.setMemberGradeId(new Long(gradeName));
			member.setAreaId(0l);
			member.setEnterpriseDiscount(new BigDecimal(0));
			member.setIsLeader(0);
			member.setIntegral(0);
			member.setSource(12);//12 管理员注册
			Long id = tmembermanager.insertMember(member);
			flag = id;
		}
		write.write(flag.toString());
		write.close();
	}
	
	
	//分配优惠券卡号
	public String toMemberCoupon(){
		return "toMemberCoupon";
	}
	//分配优惠劵
	public void addMemberCoupon() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(model.getCardNo()!=null&&!"".equals(model.getCardNo().trim())){
			Map map=new HashMap();
			map.put("cardNo", model.getCardNo().trim());
			TCouponCard card = (TCouponCard)opensqlmanage.selectForObjectByMap_drug(map, "t_coupon_card.selectCouponCardByCardNo");
			if(card!=null){
				card.setMemberId(model.getId());
				flag = tcouponcardmanager.updateByPrimaryKeySelective(card);
			}else{
				flag=-1;
			}
		}else{
			flag=-1;
		}
		write.write(flag.toString());
		write.close();
	}
	
	public class Condition {
		private String username;//用户名
		private String mobile;//手机号码
		private Integer leader;//是否是健康领袖
		private Integer enterprise;//是否是企业用户
		private String realName;//真实姓名
		private Long id;//ID 
		private Integer status;//1 停用  2 启用
		private String password;
		private String cardNo;
		private Integer type;
		
		
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public String getCardNo() {
			return cardNo;
		}
		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public Integer getLeader() {
			return leader;
		}
		public void setLeader(Integer leader) {
			this.leader = leader;
		}
		public Integer getEnterprise() {
			return enterprise;
		}
		public void setEnterprise(Integer enterprise) {
			this.enterprise = enterprise;
		}
		public String getRealName() {
			return realName;
		}
		public void setRealName(String realName) {
			this.realName = realName;
		}
		
		
		
		
		
	}
	@Override
	public Object getModel() {
		return this.model;
	}

	@Override
	public void setModel(Object o) {
		this.model = (Condition) o;
	}

	public PageWraper getPw() {
		return pw;
	}

	public void setPw(PageWraper pw) {
		this.pw = pw;
	}

	public PageResult getRs() {
		return rs;
	}

	public void setRs(PageResult rs) {
		this.rs = rs;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}

	public TCouponCardManager getTcouponcardmanager() {
		return tcouponcardmanager;
	}

	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
		this.tcouponcardmanager = tcouponcardmanager;
	}
	public TMember getMember() {
		return member;
	}
	public void setMember(TMember member) {
		this.member = member;
	}
	public Map getResc() {
		return resc;
	}
	public void setResc(Map resc) {
		this.resc = resc;
	}
	public TTagManager getTtagmanager() {
		return ttagmanager;
	}
	public void setTtagmanager(TTagManager ttagmanager) {
		this.ttagmanager = ttagmanager;
	}
	
	public TTagRelationManager getTtagrelationmanager() {
		return ttagrelationmanager;
	}
	
	public void setTtagrelationmanager(TTagRelationManager ttagrelationmanager) {
		this.ttagrelationmanager = ttagrelationmanager;
	}
}
