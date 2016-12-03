package com.rc.portal.webapp.action;

import java.util.Date;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.StringUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberGradeManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.vo.TMemberGradeExample;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;
/**
 * 会员等级
 * @author liutianling
 *
 */
public class MemberGradeAction extends BaseAction {

	private static final long serialVersionUID = 121232143214231L;
	
	private OpenSqlManage opensqlmanage;
	
	private TMemberGradeManager tmembergrademanager ;
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	private TMemberGrade memberGrade = new TMemberGrade();
	
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {

	}
	
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TMemberGradeManager getTmembergrademanager() {
		return tmembergrademanager;
	}

	public void setTmembergrademanager(TMemberGradeManager tmembergrademanager) {
		this.tmembergrademanager = tmembergrademanager;
	}

	public TMemberGrade getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(TMemberGrade memberGrade) {
		this.memberGrade = memberGrade;
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
	
	/**
	 * 等级列表
	 * @return
	 */
	public String list() throws Exception{
		
		TMemberGradeExample example = new TMemberGradeExample();
		if(memberGrade != null){
			if(!StringUtil.isEmpty(memberGrade.getName()))
			example.createCriteria().andNameLike("%"+memberGrade.getName()+"%");
		}
		
		pw = opensqlmanage.selectForPageByMap_drug(example, "t_member_grade.ibatorgenerated_countByExample", "t_member_grade.selectByExampleByPage", rs.getP_curPage(), rs.getP_pageSize());
		
		return "list";
	}
	
	/**
	 * 编辑
	 * @return
	 */
	public String edit() throws Exception{
		
		if(this.memberGrade!=null && memberGrade.getId().longValue() >0){
			Object selectObjectByObject = this.opensqlmanage.selectObjectByObject(memberGrade, "t_member_grade.ibatorgenerated_selectByPrimaryKey");
			if(selectObjectByObject!=null){
				memberGrade = (TMemberGrade) this.opensqlmanage.selectObjectByObject(memberGrade, "t_member_grade.ibatorgenerated_selectByPrimaryKey");
			}			
		}
		return "edit";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String update() throws Exception{
		
		String [] fileNames = {"id","name","enterpriseDiscount","consumePrice"};
		if(isValid(memberGrade,fileNames)){
			this.memberGrade.setUpdateDate(new Date());
			UserinfoModel user = (UserinfoModel)this.getSession().getAttribute(ConstantUtil.logincookiename);
			this.memberGrade.setUpdatePeople(user.getUsername());
			
			checkGradeDefault();
			
			this.tmembergrademanager.updateByPrimaryKeySelective(this.memberGrade);
			this.addFlashMessage(true);
		}
		
		return "redirect";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String add() throws Exception{
		return "add";
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String save() throws Exception{	
		
		String [] fileNames = {"name","enterpriseDiscount","consumePrice"};
		if(isValid(memberGrade,fileNames)){
			this.memberGrade.setCreateDate(new Date());			
			UserinfoModel user = (UserinfoModel)this.getSession().getAttribute(ConstantUtil.logincookiename);
			this.memberGrade.setCreatePeople(user.getUsername());
			
			checkGradeDefault();
			
			this.tmembergrademanager.insertSelective(memberGrade);
			this.addFlashMessage(true);
		}
		return "redirect";
	}
	
	/**
	 * 保证时刻只有一个默认
	 * @throws Exception
	 */
	private void checkGradeDefault()throws Exception{
		//检测当前是否存在默认级别
		TMemberGradeExample tMemberGradeExample = new TMemberGradeExample();
		tMemberGradeExample.createCriteria().andIsDefaultEqualTo(true);
		
		Object countDefault = this.opensqlmanage.selectObjectByObject(tMemberGradeExample, "t_member_grade.ibatorgenerated_countByExample");
		
		if(countDefault==null){
			this.memberGrade.setIsDefault(true);
		}else{
			int count = (Integer)countDefault;
			
			if(count > 0){
				Boolean isDefault = this.memberGrade.getIsDefault();
				
				if(isDefault !=null && isDefault.booleanValue() == true){
					this.opensqlmanage.updateByMap(null, "t_member_grade.updateAllDefaultToNo");
				}
			}else{
				this.memberGrade.setIsDefault(true);
			}
		}
	}
	
}
