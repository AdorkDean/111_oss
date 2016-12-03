package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsConsultManager;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsConsult;
import com.rc.portal.vo.TMember;
import com.rc.portal.webapp.util.PageResult;

/**
 * 商品评论action
 * 
 * @author user00
 * @createTime 2015-8-13 下午8:03:40
 */
public class GoodsConsultAction extends BaseAction {

	private static final long serialVersionUID = 5294821245374431258L;
	private OpenSqlManage opensqlmanage;

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();

	private TGoodsConsultManager tgoodsconsultmanager;
	private TGoodsManager tgoodsmanager;
	private TMemberManager tmembermanager;

	private TGoodsConsult tGoodsConsult;
	private TMember tMember;
	private TGoods tGoods;

	private String userName;
	private String consultContent;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	public String goodsConsultPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		consultContent = this.getRequest().getParameter("consultContent");
		userName = this.getRequest().getParameter("userName");
		if (consultContent != null && !"".equals(consultContent.trim())) {
			map.put("consultContent", consultContent);
		}
		if (userName != null && !"".equals(userName.trim())) {
			map.put("userName", userName);
		}
		pw = opensqlmanage.selectForPageByMap_drug(map, "t_goods_consult.selectCountByMap",
				"t_goods_consult.selectListByMap", rs.getP_curPage(), rs.getP_pageSize());
		return "goods_consult_page";
	}

	/**
	 * 回复页面
	 * 
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public String replyPage() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		if (id == null || "".equals(id.trim())) {
			return ERROR;
		} else {
			tGoodsConsult = tgoodsconsultmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tGoodsConsult != null && tGoodsConsult.getMemberId() != null) {
				tMember = tmembermanager.selectByPrimaryKey(tGoodsConsult.getMemberId());
			}
			if (tGoodsConsult != null && tGoodsConsult.getGoodsId() != null) {
				tGoods = tgoodsmanager.selectByPrimaryKey(tGoodsConsult.getGoodsId());
			}
		}
		return "reply_page";
	}

	/**
	 * 详细页面
	 * 
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public String detailGoodsConsult() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		if (id == null || "".equals(id.trim())) {
			return ERROR;
		} else {
			tGoodsConsult = tgoodsconsultmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tGoodsConsult != null && tGoodsConsult.getMemberId() != null) {
				tMember = tmembermanager.selectByPrimaryKey(tGoodsConsult.getMemberId());
			}
			if (tGoodsConsult != null && tGoodsConsult.getGoodsId() != null) {
				tGoods = tgoodsmanager.selectByPrimaryKey(tGoodsConsult.getGoodsId());
			}
		}
		return "detail_page";
	}

	/**
	 * 保存回复信息
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public String saveReplyGoodsConsult() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		String isShow = this.getRequest().getParameter("isShow");
		String answer = this.getRequest().getParameter("answer");

		if (id == null || "".equals(id.trim())) {
			return ERROR;
		}
		if (isShow == null || "".equals(isShow.trim())) {
			return ERROR;
		}
		if (answer == null || "".equals(answer.trim())) {
			return ERROR;
		}
		tGoodsConsult = tgoodsconsultmanager.selectByPrimaryKey(Long.valueOf(id));
		if (tGoodsConsult == null) {
			return ERROR;
		}

		tGoodsConsult.setAnswer(answer);
		tGoodsConsult.setAnswerDate(new Date());
		tGoodsConsult.setIsShow(Integer.valueOf(isShow));

		tgoodsconsultmanager.updateByPrimaryKeySelective(tGoodsConsult);
		return "goods_consult_page";
	}

	/**
	 * 更新
	 * 
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public String updateGoodsConsult() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		String isShow = this.getRequest().getParameter("isShow");
		if (id == null || "".equals(id.trim())) {
			return ERROR;
		}
		if (isShow == null || "".equals(isShow.trim())) {
			return ERROR;
		}
		tGoodsConsult = tgoodsconsultmanager.selectByPrimaryKey(Long.valueOf(id));
		if (tGoodsConsult == null) {
			return ERROR;
		}
		tGoodsConsult.setIsShow(Integer.valueOf(isShow));
		tgoodsconsultmanager.updateByPrimaryKeySelective(tGoodsConsult);
		return "goods_consult_page";
	}

	/**
	 * 删除
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void delGoodsConsult() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		ReturnData returnData = new ReturnData();
		if (id == null || "".equals(id.trim())) {
			returnData.setStatus(0);
			returnData.setMessage("请选择正确的咨询数据!");
			this.getResponse().setCharacterEncoding("UTF-8");
		} else {
			tGoodsConsult = tgoodsconsultmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tGoodsConsult == null) {
				returnData.setStatus(0);
				returnData.setMessage("您选择的咨询数据不存在,请确认!");
			} else {
				tgoodsconsultmanager.deleteByPrimaryKey(Long.valueOf(id));
				returnData.setStatus(1);
				returnData.setMessage("删除成功!");
			}
		}
		this.writeObjectToResponse(returnData, ContentType.application_json);
	}

	/**
	 * 设置显示不显示
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void setShowGoodsConsult() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		String isShow = this.getRequest().getParameter("isShow");
		ReturnData returnData = new ReturnData();
		if (id == null || "".equals(id.trim())) {
			returnData.setStatus(0);
			returnData.setMessage("请选择正确的咨询数据!");
			this.getResponse().setCharacterEncoding("UTF-8");
		} else {
			tGoodsConsult = tgoodsconsultmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tGoodsConsult == null) {
				returnData.setStatus(0);
				returnData.setMessage("您选择的咨询数据不存在,请确认!");
			} else {
				tGoodsConsult.setIsShow(Integer.valueOf(isShow));
				tgoodsconsultmanager.updateByPrimaryKeySelective(tGoodsConsult);
				returnData.setStatus(1);
				returnData.setMessage("设置成功!");
			}
		}
		this.writeObjectToResponse(returnData, ContentType.application_json);
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

	public TGoodsConsultManager getTgoodsconsultmanager() {
		return tgoodsconsultmanager;
	}

	public void setTgoodsconsultmanager(TGoodsConsultManager tgoodsconsultmanager) {
		this.tgoodsconsultmanager = tgoodsconsultmanager;
	}

	public TGoodsConsult gettGoodsConsult() {
		return tGoodsConsult;
	}

	public void settGoodsConsult(TGoodsConsult tGoodsConsult) {
		this.tGoodsConsult = tGoodsConsult;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConsultContent() {
		return consultContent;
	}

	public void setConsultContent(String consultContent) {
		this.consultContent = consultContent;
	}

	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
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

	public TGoods gettGoods() {
		return tGoods;
	}

	public void settGoods(TGoods tGoods) {
		this.tGoods = tGoods;
	}

	class ReturnData {
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
