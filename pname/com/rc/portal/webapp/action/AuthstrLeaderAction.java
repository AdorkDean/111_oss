package com.rc.portal.webapp.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.InfoUtil;
import com.rc.commons.util.StringUtil;
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.jms.MessageSender;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TLeader;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.QRCodeUtil;
import com.rc.portal.webapp.util.zxing.QRCodeZXingUtil;

public class AuthstrLeaderAction  extends BaseAction{

	private static final long serialVersionUID = -2714746065920506242L;
	private TLeader tleader=new TLeader();
	private TLeaderManager tleadermanager;
	private OpenSqlManage opensqlmanage;
	private TSysParameterManager tsysparametermanager;
	
	private MessageSender topicMessageSender;
	// 海报存储地址
	private final String savePath = InfoUtil.getInstance().getInfo("healthleader", "healthleader.haibao.save.path");;
	// 二维码logo路径
	private final String qrLogoPath = InfoUtil.getInstance().getInfo("healthleader", "qr.image.log.path");
	private final String secoundQR = InfoUtil.getInstance().getInfo("healthleader", "healthleader.secound.qr");

	// 最新二维码图片
	private final String codeQR = InfoUtil.getInstance().getInfo("healthleader", "healthleader.qr.code");

	// 去海报页面
	private final String goHaibaoUrl = InfoUtil.getInstance().getInfo("healthleader", "healthleader.qr.code.gohaibao");

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private List result=new ArrayList();

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public TLeader getTleader() {
		return tleader;
	}

	public void setTleader(TLeader tleader) {
		this.tleader = tleader;
	}

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
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
	/*
	 * 待审核领袖列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list(){
		Map map = new HashMap();
		String realname = this.getRequest().getParameter("realname");
		String phone = this.getRequest().getParameter("phone");
		if(!StringUtil.isEmpty(realname)){
			map.put("realname", realname);
		}
		if(!StringUtil.isEmpty(phone)){
			map.put("phone", phone);
		}
		pw = opensqlmanage.selectForPageByMap_drug(map,
				"t_leader.selectLeaderAuditTypeCount",
				"t_leader.selectleaderByAuditType", rs.getP_curPage(),
				rs.getP_pageSize());
		return "list";
	}
	/*
	 * 未通过审核领袖列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String noPassList(){
		Map map = new HashMap();
		String realname = this.getRequest().getParameter("realname");
		String phone = this.getRequest().getParameter("phone");
		if(!StringUtil.isEmpty(realname)){
			map.put("realname", realname);
		}
		if(!StringUtil.isEmpty(phone)){
			map.put("phone", phone);
		}
		pw = opensqlmanage.selectForPageByMap_drug(map,
				"t_leader.selectLeaderAuditTypeCountNoPass",
				"t_leader.selectleaderByAuditTypeNoPass", rs.getP_curPage(),
				rs.getP_pageSize());
		return "nopasslist";
	}
	/*
	 * 查看领袖
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String view() throws Exception{
		String leaderId = this.getRequest().getParameter("id");
		Map map = new HashMap();
		map.put("id",leaderId );
	    Object imageList = opensqlmanage.selectForListByMap_drug(map, "t_leader.selectleaderImage");
	    result.add(imageList);
		System.out.println(result);
		tleader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
		return "view";
	}
	/*
	 * 领袖通过审核
	 */
	public String pass() throws Exception{
		String leaderId = this.getRequest().getParameter("id");
		String auditRemark = this.getRequest().getParameter("auditRemark");
		if(leaderId!=null){
			TLeader leader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
			leader.setAuditRemark(auditRemark);
			leader.setAuditType(1);//通过
			leader.setStatus(1);//可用
			tleadermanager.updateMemberAndLeaderSelective(leader);
			
			// 生成第二个二维码
			if (leader.getCode() != null && !"".equals(leader.getCode())) {
				// savePath存海报的路径下
				File _savePath = new File(savePath);
				String realPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();// System.getProperty("user.dir");
				if (realPath.endsWith("/WEB-INF/classes/")) {
					realPath = realPath.substring(0, realPath.length() - 17);
				}
				File secoundQRFile = new File(realPath + savePath + leader.getId() + "_" + leader.getCode() + ".jpg");
				System.out.println("文章页二维码图片路径:\t\t"
						+ (realPath + savePath + leader.getId() + "_" + leader.getCode() + ".jpg"));
				if (!_savePath.exists()) {
					_savePath.mkdirs();
				}
				File realPathFile = new File(realPath + savePath);
				if (!realPathFile.exists()) {
					realPathFile.mkdirs();
				}
				if (!secoundQRFile.exists()) {
					secoundQRFile.createNewFile();
				}
				FileOutputStream fileOutputStream = new FileOutputStream(secoundQRFile);
				String content = secoundQR + leader.getCode() + "&hurl=" + java.net.URLEncoder.encode("/", "utf-8");
				try {
					URL url = new URL(qrLogoPath);
					URLConnection uc = url.openConnection();
					uc.getInputStream();
					QRCodeZXingUtil.encoderQRCoder_logoUrl(content, fileOutputStream, qrLogoPath);
				} catch (IOException e) {
					// log.error("生成健康领袖logo二维码不存在,配置链接为:[" + qrLogoPath + "]");
					QRCodeUtil.encoderQRCoder(content, fileOutputStream, 10);
				}
				fileOutputStream.flush();
				fileOutputStream.close();
			}

			// code对应的二维码,跳转海报的页面

			if (leader.getCode() != null && !"".equals(leader.getCode())) {
				String realPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();// System.getProperty("user.dir");
				if (realPath.endsWith("/WEB-INF/classes/")) {
					realPath = realPath.substring(0, realPath.length() - 17);
				}
				File codeQrDir = new File(realPath + codeQR);
				if (!codeQrDir.exists()) {
					codeQrDir.mkdirs();
				}
				File codeQRFile = new File(codeQrDir + "/" + leader.getCode() + ".jpg");
				if (!codeQRFile.exists()) {
					codeQRFile.createNewFile();
				}
				FileOutputStream fileOutputStream = new FileOutputStream(codeQRFile);
				System.out.println("最新二维码路径:\t\t" + codeQRFile.getAbsolutePath());

				String content = goHaibaoUrl + "?code=" + leader.getCode() + "&id=" + leader.getId();
				try {
					URL url = new URL(qrLogoPath);
					URLConnection uc = url.openConnection();
					uc.getInputStream();
					QRCodeZXingUtil.encoderQRCoder_logoUrl(content, fileOutputStream, qrLogoPath);
				} catch (IOException e) {
					// log.error("生成健康领袖logo二维码不存在,配置链接为:[" + qrLogoPath + "]");
					QRCodeUtil.encoderQRCoder(content, fileOutputStream, 10);
				}
				fileOutputStream.flush();
				fileOutputStream.close();
			}
			
			
			if(leader.getPhone()!=null){
				//通过审核并发送短信通知
				Map<String, String> map = new HashMap<String, String>();
				map.put("mobiles", leader.getPhone());
				map.put("smsContent", "尊敬的会员，您的领秀申请已被审核人员通过，如有疑问请联系我们的客服4006063111。健康领秀，成就健康巨人。");
				String YAO_GATEWAY_URL =tsysparametermanager.getKeys("sms");
				String buildRequestBySMS = ClientSubmit.buildRequestBySMS(map,YAO_GATEWAY_URL);
				System.out.println(buildRequestBySMS);
			}
		}
		return "redirect";
	}
	/*
	 * 领袖未通过审核
	 */
	public void noPass() throws Exception{
		int flag=-1;
		PrintWriter out =  this.getResponse().getWriter();
		String leaderId = this.getRequest().getParameter("id");
		String auditRemark = this.getRequest().getParameter("auditRemark");
		if(leaderId!=null){
			TLeader leader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
			leader.setAuditRemark(auditRemark);
			leader.setAuditType(2);//未通过
			leader.setStatus(2);//不可用
			tleadermanager.updateByPrimaryKeySelective(leader);
			flag=0;
			/*if(leader.getPhone()!=null){
				//通过审核并发送短信通知
				Map<String, String> map = new HashMap<String, String>();
				map.put("mobiles", leader.getPhone());
				map.put("smsContent", "尊敬的会员，"+auditRemark+"如有疑问请联系我们的客服4006063111。");
				String YAO_GATEWAY_URL =tsysparametermanager.getKeys("sms");
				String buildRequestBySMS = ClientSubmit.buildRequestBySMS(map,YAO_GATEWAY_URL);
				System.out.println(buildRequestBySMS);
			}*/
		}
		out.print(flag);
		out.close();
	}
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		
	}

	public MessageSender getTopicMessageSender() {
		return topicMessageSender;
	}

	public void setTopicMessageSender(MessageSender topicMessageSender) {
		this.topicMessageSender = topicMessageSender;
	}

}
