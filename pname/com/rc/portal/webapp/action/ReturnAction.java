package com.rc.portal.webapp.action;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TOrderManager;
import com.rc.portal.service.TReturnImageManager;
import com.rc.portal.service.TReturnItemManager;
import com.rc.portal.service.TReturnManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TReturn;
import com.rc.portal.vo.TReturn.ReturnStatus;
import com.rc.portal.vo.TReturnImage;
import com.rc.portal.vo.TReturnImageExample;
import com.rc.portal.vo.TReturnItem;
import com.rc.portal.webapp.util.PageResult;

/**
 * 退换货action
 * 
 * @author user00
 * @createTime 2015-8-11 下午7:00:30
 */
public class ReturnAction extends BaseAction {

	private static final long serialVersionUID = 4824005742869172775L;

	private OpenSqlManage opensqlmanage;

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();

	private TReturnManager treturnmanager;
	private TMemberManager tmembermanager;
	private TReturnItemManager treturnitemmanager;
	private TReturnImageManager treturnimagemanager;
	private TOrderManager tordermanager;
	
	private TSysParameterManager tsysparametermanager;

	private TReturn tReturn;
	private TMember tMember;
	private TOrder tOrder;
	private List<TReturnItem> itemList;
	private List<TReturnImage> imageList;

	private String userName;
	private String orderSn;
	private String refundDescribe;

	/**
	 * 列表页
	 * 
	 * @return
	 */
	public String returnPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		refundDescribe = this.getRequest().getParameter("refundDescribe");
		userName = this.getRequest().getParameter("userName");
		orderSn = this.getRequest().getParameter("orderSn");
		String orderStatus = this.getRequest().getParameter("orderStatus");
		if (refundDescribe != null && !"".equals(refundDescribe)) {
			map.put("refundDescribe", refundDescribe);
		}
		if (userName != null && !"".equals(userName)) {
			map.put("userName", userName);
		}
		if (orderSn != null && !"".equals(orderSn)) {
			map.put("orderSn", orderSn);
		}
		if (orderStatus != null && !"".equals(orderStatus)) {
			map.put("orderStatus", Integer.valueOf(orderStatus));
			this.getRequest().setAttribute("orderStatus", orderStatus);
		}
		pw = opensqlmanage.selectForPageByMap_drug(map, "t_return.selectCountByMap", "t_return.selectListByMap",
				rs.getP_curPage(), rs.getP_pageSize());
		return "return_page";
	}

	/**
	 * 查看详细
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public String detailReturnPage() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		if (id == null || "".equals(id)) {
			return ERROR;
		}
		tReturn = treturnmanager.selectByPrimaryKey(Long.valueOf(id));
		if (tReturn == null) {
			return ERROR;
		}
		itemList = treturnitemmanager.selectListByReturnId(tReturn.getId());
		TReturnImageExample tReturnImageExample = new TReturnImageExample();
		tReturnImageExample.createCriteria().andReturnIdEqualTo(tReturn.getId());
		imageList = treturnimagemanager.selectByExample(tReturnImageExample);

		if (tReturn.getOldOrderId() != null) {
			tOrder = tordermanager.selectByPrimaryKey(tReturn.getOldOrderId());
		}
		if (tReturn.getMemberId() != null) {
			tMember = tmembermanager.selectByPrimaryKey(tReturn.getMemberId());
		}
		return "detail_return_page";
	}

	/**
	 * 操作页
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	@SuppressWarnings("unchecked")
	public String operateReturnPage() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		if (id == null || "".equals(id)) {
			return ERROR;
		}
		tReturn = treturnmanager.selectByPrimaryKey(Long.valueOf(id));
		if (tReturn == null) {
			return ERROR;
		}
		itemList = treturnitemmanager.selectListByReturnId(tReturn.getId());
		TReturnImageExample tReturnImageExample = new TReturnImageExample();
		tReturnImageExample.createCriteria().andReturnIdEqualTo(tReturn.getId());
		imageList = treturnimagemanager.selectByExample(tReturnImageExample);
		if (tReturn.getOldOrderId() != null) {
			tOrder = tordermanager.selectByPrimaryKey(tReturn.getOldOrderId());
		}
		if (tReturn.getMemberId() != null) {
			tMember = tmembermanager.selectByPrimaryKey(tReturn.getMemberId());
		}

		return "operate_return_page";
	}

	/**
	 * 状态审核修改
	 * @throws Exception 
	 */
	public void updateStatus() throws Exception {
		ResultData resultData = new ResultData();
		String status = this.getRequest().getParameter("status");
		String refundRemark = this.getRequest().getParameter("refundRemark");
		String id = this.getRequest().getParameter("id");

		// 退款相关字段
		String refundBank = this.getRequest().getParameter("refundBank");
		String refundAmount = this.getRequest().getParameter("refundAmount");
		String refundAccount = this.getRequest().getParameter("refundAccount");

		if (id == null || "".equals(id)) {
			resultData.setResultStatus(0);
			resultData.setMessage("id不能为空");
			this.writeObjectToResponse(resultData, ContentType.application_json);
		} else if (status == null || "".equals(status)) {
			resultData.setResultStatus(0);
			resultData.setMessage("状态不能为空");
			this.writeObjectToResponse(resultData, ContentType.application_json);
		} else {
			tReturn = treturnmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tReturn == null) {
				resultData.setResultStatus(0);
				resultData.setMessage("您选择的退换货数据不存在,请确认");
				this.writeObjectToResponse(resultData, ContentType.application_json);
			} else {
				TReturn.ReturnStatus orderStatus = TReturn.ReturnStatus.getReturnStatusByOrdinal(Integer
						.valueOf(status));
				tReturn.setOrderStatus(orderStatus.ordinal());
				tReturn.setRefundRemark(refundRemark);
				if (refundAmount != null && !"".equals(refundAmount)) {
					BigDecimal _refundAmount = new BigDecimal(refundAmount);
					tReturn.setRefundAmount(_refundAmount);
				}
				if (refundAccount != null && !"".equals(refundAccount)) {
					tReturn.setRefundAccount(refundAccount);
				}
				if (refundBank != null && !"".equals(refundBank)) {
					tReturn.setRefundBank(refundBank);
				}
				if (orderStatus == ReturnStatus.end) {
					tReturn.setFinishDt(new Date());
				}
				String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
				treturnmanager.updateReturnYongjin(tReturn,publicServiceUrl,Integer.valueOf(status).intValue());
				resultData.setResultStatus(1);
				resultData.setMessage("修改成功");
				this.writeObjectToResponse(resultData, ContentType.application_json);
			}
		}
	}

	/**
	 * 验货不通过,填写相关物流信息
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void validateNotPass() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		String status = this.getRequest().getParameter("status");
		String expressDelivery = this.getRequest().getParameter("expressDelivery");// 快递单号
		String expressCompany = this.getRequest().getParameter("expressCompany");// 物流公司
		String refundRemark = this.getRequest().getParameter("refundRemark");// 备注

		ResultData resultData = new ResultData();
		if (id == null || "".equals(id.trim())) {
			resultData.setMessage("id不能为空!");
			resultData.setResultStatus(0);
			return;
		}
		if (status == null || "".equals(status.trim())) {
			resultData.setMessage("状态标识不能为空!");
			resultData.setResultStatus(0);
			return;
		}
		if (expressDelivery == null || "".equals(expressDelivery.trim())) {
			resultData.setMessage("快递单号不能为空!");
			resultData.setResultStatus(0);
			return;
		}
		if (expressCompany == null || "".equals(expressCompany.trim())) {
			resultData.setMessage("物流公司不能为空!");
			resultData.setResultStatus(0);
			return;
		}
		tReturn = treturnmanager.selectByPrimaryKey(Long.valueOf(id));
		if (tReturn == null) {
			resultData.setMessage("您选择的退换货信息有误,请确认");
			resultData.setResultStatus(0);
			return;
		}
		tReturn.setOrderStatus(Integer.valueOf(status));
		tReturn.setExpressCompany(expressCompany);
		tReturn.setExpressDelivery(expressDelivery);
		tReturn.setRefundRemark(refundRemark);
		treturnmanager.updateByPrimaryKeySelective(tReturn);
		resultData.setMessage("保存成功!");
		resultData.setResultStatus(1);
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {

	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TReturnManager getTreturnmanager() {
		return treturnmanager;
	}

	public void setTreturnmanager(TReturnManager treturnmanager) {
		this.treturnmanager = treturnmanager;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getRefundDescribe() {
		return refundDescribe;
	}

	public void setRefundDescribe(String refundDescribe) {
		this.refundDescribe = refundDescribe;
	}

	public TReturn gettReturn() {
		return tReturn;
	}

	public void settReturn(TReturn tReturn) {
		this.tReturn = tReturn;
	}

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}

	public TMember gettMember() {
		return tMember;
	}

	public void settMember(TMember tMember) {
		this.tMember = tMember;
	}

	public TReturnItemManager getTreturnitemmanager() {
		return treturnitemmanager;
	}

	public void setTreturnitemmanager(TReturnItemManager treturnitemmanager) {
		this.treturnitemmanager = treturnitemmanager;
	}

	public TReturnImageManager getTreturnimagemanager() {
		return treturnimagemanager;
	}

	public void setTreturnimagemanager(TReturnImageManager treturnimagemanager) {
		this.treturnimagemanager = treturnimagemanager;
	}

	public List<TReturnItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<TReturnItem> itemList) {
		this.itemList = itemList;
	}

	public List<TReturnImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<TReturnImage> imageList) {
		this.imageList = imageList;
	}

	public TOrderManager getTordermanager() {
		return tordermanager;
	}

	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
	}

	public TOrder gettOrder() {
		return tOrder;
	}

	public void settOrder(TOrder tOrder) {
		this.tOrder = tOrder;
	}

	// ajax返回结果对象
	class ResultData {
		private int resultStatus;
		private String message;

		public int getResultStatus() {
			return resultStatus;
		}

		public void setResultStatus(int resultStatus) {
			this.resultStatus = resultStatus;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}
	
	
	
}
