package com.rc.portal.webapp.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.StringUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCustomerCallManager;
import com.rc.portal.vo.TCustomerCall;
import com.rc.portal.webapp.util.PageResult;
/**
 * 客户电话回访
 * @author liutianling
 *
 */
public class CustomerCallAction extends BaseAction {

	private static final long serialVersionUID = 121232143214231L;
	
	private OpenSqlManage opensqlmanage;
	
	private TCustomerCallManager tcustomercallmanager ;
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	private TCustomerCall tCustomerCall = new TCustomerCall();
	
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {

	}
	
	public TCustomerCall gettCustomerCall() {
		return tCustomerCall;
	}

	public void settCustomerCall(TCustomerCall tCustomerCall) {
		this.tCustomerCall = tCustomerCall;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}
	
	public TCustomerCallManager getTcustomercallmanager() {
		return tcustomercallmanager;
	}

	public void setTcustomercallmanager(TCustomerCallManager tcustomercallmanager) {
		this.tcustomercallmanager = tcustomercallmanager;
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
	 * 回拨列表
	 * @return
	 */
	public String list() throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		if(tCustomerCall != null){
			if(!StringUtil.isEmpty(tCustomerCall.getPhoneNumber()))
				param.put("phoneNumber", "%"+tCustomerCall.getPhoneNumber()+"%");
		}
		
		pw = opensqlmanage.selectForPageByMap_drug(param, "t_customer_call.selectPageCount", "t_customer_call.selectPage", rs.getP_curPage(), rs.getP_pageSize());
		
		return "list";
	}
	
	/**
	 * 异步修改回拨状态
	 * @return
	 */
	public void edit() throws Exception{
		if(tCustomerCall != null && tCustomerCall.getId() > 0){
			tCustomerCall.setCallStatus(1);
			tCustomerCall.setCallTime(new Date());
			tcustomercallmanager.updateByPrimaryKeySelective(tCustomerCall);
			this.writeObjectToResponse("1", ContentType.application_json);
		}else{
			this.writeObjectToResponse("0", ContentType.application_json);
		}
	}
}
