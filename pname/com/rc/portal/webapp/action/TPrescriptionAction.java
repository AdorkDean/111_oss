package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.InfoUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TPrescriptionImageManager;
import com.rc.portal.service.TPrescriptionManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.vo.TPrescription;
import com.rc.portal.vo.TPrescriptionExample;
import com.rc.portal.vo.TPrescriptionImage;
import com.rc.portal.vo.TPrescriptionImageExample;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;

/**
 * 病例(处方药)Action
 * 
 * @author WWF
 * @createTime 2015-9-16 上午10:26:30
 */
public class TPrescriptionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private TPrescriptionManager tprescriptionmanager;

	private TPrescriptionImageManager tprescriptionimagemanager;

	private OpenSqlManage opensqlmanage;

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();

	private String prescriptionAppUrl = InfoUtil.getInstance().getInfo("images", "images.public.prescription.path");

	//
	private String imageUrl;

	private TPrescription tPrescription;

	private List<TPrescriptionImage> imgList;

	/**
	 * 列表页
	 */
	public String prescriptionList() {
		TPrescriptionExample tPrescriptionExample = new TPrescriptionExample();
		String phone = this.getRequest().getParameter("phone");
		String status = this.getRequest().getParameter("status");
		String ifHelp = this.getRequest().getParameter("ifHelp");
		TPrescriptionExample.Criteria criteria = null;
		if (phone != null && !"".equals(phone)) {
			criteria = tPrescriptionExample.createCriteria();
			criteria.andPhoneEqualTo(phone);
			this.getRequest().setAttribute("phone", phone);
		}
		if (status != null && !"".equals(status)) {
			if (criteria == null) {
				criteria = tPrescriptionExample.createCriteria();
			}
			criteria.andStatusEqualTo(Integer.valueOf(status));
			this.getRequest().setAttribute("status", status);
		}
		if (ifHelp != null && !"".equals(ifHelp)) {
			if (criteria == null) {
				criteria = tPrescriptionExample.createCriteria();
			}
			criteria.andIfhelpEqualTo(Integer.valueOf(ifHelp));
			this.getRequest().setAttribute("ifHelp", ifHelp);
		}
		// 时间倒序
		tPrescriptionExample.setOrderByClause("create_date desc");
		pw = opensqlmanage.selectForPageByMap_drug(tPrescriptionExample,
				"t_prescription.ibatorgenerated_countByExample", "t_prescription.selectByExampleByPage",
				rs.getP_curPage(), rs.getP_pageSize());
		// 图片路径取值配置
		imageUrl = prescriptionAppUrl;

		return "list_page";
	}

	/**
	 * 详情页
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	@SuppressWarnings("unchecked")
	public String detailPrescription() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		tPrescription = tprescriptionmanager.selectByPrimaryKey(Long.valueOf(id));
		TPrescriptionImageExample tPrescriptionImageExample = new TPrescriptionImageExample();
		tPrescriptionImageExample.createCriteria().andPrescriptionIdEqualTo(tPrescription.getId());
		imgList = tprescriptionimagemanager.selectByExample(tPrescriptionImageExample);
		return "detail_page";
	}

	/**
	 * 回复页面
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	@SuppressWarnings("unchecked")
	public String replyPrescription() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		tPrescription = tprescriptionmanager.selectByPrimaryKey(Long.valueOf(id));
		TPrescriptionImageExample tPrescriptionImageExample = new TPrescriptionImageExample();
		tPrescriptionImageExample.createCriteria().andPrescriptionIdEqualTo(tPrescription.getId());
		imgList = tprescriptionimagemanager.selectByExample(tPrescriptionImageExample);
		return "reply_page";
	}

	/**
	 * 更新数据
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void update() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		String returning = this.getRequest().getParameter("returning");
		ResultData resultData = new ResultData();
		if (returning == null || "".equals(returning)) {
			resultData.setStatus(0);
			resultData.setMessage("回复内容不能为空!");
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
		if (id == null || "".equals(id)) {
			resultData.setStatus(0);
			resultData.setMessage("id不能为空!");
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
		TPrescription tPrescription = tprescriptionmanager.selectByPrimaryKey(Long.valueOf(id));
		if (tPrescription == null) {
			resultData.setStatus(0);
			resultData.setMessage("病例信息有误,请确认!");
			this.writeObjectToResponse(resultData, ContentType.application_json);
			return;
		}
		tPrescription.setReturning(returning);
		tPrescription.setStatus(1);
		UserinfoModel user = (UserinfoModel) this.getSession().getAttribute(ConstantUtil.logincookiename);
		tPrescription.setCallUsername(user.getUsername());
		tPrescription.setCallTime(new Date());
		tprescriptionmanager.updateByPrimaryKeySelective(tPrescription);
		resultData.setStatus(1);
		resultData.setMessage("回复成功");
		this.writeObjectToResponse(resultData, ContentType.application_json);
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {

	}

	public TPrescriptionManager getTprescriptionmanager() {
		return tprescriptionmanager;
	}

	public void setTprescriptionmanager(TPrescriptionManager tprescriptionmanager) {
		this.tprescriptionmanager = tprescriptionmanager;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public TPrescription gettPrescription() {
		return tPrescription;
	}

	public void settPrescription(TPrescription tPrescription) {
		this.tPrescription = tPrescription;
	}

	public List<TPrescriptionImage> getImgList() {
		return imgList;
	}

	public void setImgList(List<TPrescriptionImage> imgList) {
		this.imgList = imgList;
	}

	public TPrescriptionImageManager getTprescriptionimagemanager() {
		return tprescriptionimagemanager;
	}

	public void setTprescriptionimagemanager(TPrescriptionImageManager tprescriptionimagemanager) {
		this.tprescriptionimagemanager = tprescriptionimagemanager;
	}

	/**
	 * ajax更新(删除)返回对象
	 * 
	 * @author user00
	 * @createTime 2015-9-16 下午2:52:35
	 */
	class ResultData {
		private int status;
		private String message;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

}
