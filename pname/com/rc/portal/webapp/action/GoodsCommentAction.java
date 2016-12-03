package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsCommentManager;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TOrderManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsComment;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TOrder;
import com.rc.portal.webapp.util.PageResult;

/**
 * 商品评论action
 * GoodsCommentAction<BR>
 * 创建人:Marlon <BR>
 * 时间：2016-8-8-下午6:02:55 <BR>
 * @version 1.0.0
 */
public class GoodsCommentAction extends BaseAction {

	private static final long serialVersionUID = 5294821245374431258L;
	private OpenSqlManage opensqlmanage;

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();

	private TGoodsCommentManager tgoodscommentmanager;
	private TGoodsManager tgoodsmanager;
	private TMemberManager tmembermanager;
	private TOrderManager tordermanager;

	private TGoodsComment tGoodsComment;

	private TMember tMember;
	private TGoods tGoods;
	private TOrder tOrder;

	private String userName;
	private String goodsName;
	
	/**
	 * 列表页面
	 * 方法名：goodsCommentPage<BR>
	 * 创建人：Marlon <BR>
	 * @return String<BR>
	 * @exception <BR>
	 */
	public String goodsCommentPage() {
		goodsName = this.getRequest().getParameter("goodsName");
		userName = this.getRequest().getParameter("userName");
		Map<String, Object> map = new HashMap<String, Object>();
		if (goodsName != null && !"".equals(goodsName.trim())) {
			map.put("goodsName", goodsName);
		}
		if (userName != null && !"".equals(userName.trim())) {
			map.put("userName", userName);
		}

		pw = opensqlmanage.selectForPageByMap_drug(map, "t_goods_comment.selectCountByMap",
				"t_goods_comment.selectListByMap", rs.getP_curPage(), rs.getP_pageSize());
		return "goods_comment_page";
	}

	/**
	 * 更新/详情
	 * 方法名：update<BR>
	 * 创建人：Marlon <BR>
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException String<BR>
	 * @exception <BR>
	 */
	public String update() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		String mark = this.getRequest().getParameter("mark");
		if (id == null || "".equals(id.trim())) {
			return ERROR;
		} else {
			tGoodsComment = tgoodscommentmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tGoodsComment != null && tGoodsComment.getMemberId() != null) {
				tMember = tmembermanager.selectByPrimaryKey(tGoodsComment.getMemberId());
			}
			if (tGoodsComment != null && tGoodsComment.getGoodsId() != null) {
				tGoods = tgoodsmanager.selectByPrimaryKey(tGoodsComment.getGoodsId());
			}
			if (tGoodsComment != null && tGoodsComment.getGoodsId() != null) {
				tOrder = tordermanager.selectByPrimaryKey(tGoodsComment.getOrderId());
			}
			this.getRequest().setAttribute("mark", mark);
		}
		return "update_page";
	}


	/**
	 * 更新评论数据(只更新是否显示字段)
	 * 
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public String updateGoodsComment() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		String isShow = this.getRequest().getParameter("isShow");
		if (id == null || "".equals(id.trim())) {
			return ERROR;
		}
		if (isShow == null || "".equals(isShow.trim())) {
			return ERROR;
		}
		tGoodsComment = tgoodscommentmanager.selectByPrimaryKey(Long.valueOf(id));
		if (tGoodsComment == null) {
			return ERROR;
		}
		tGoodsComment.setIsShow(Integer.valueOf(isShow));
		tgoodscommentmanager.updateByPrimaryKeySelective(tGoodsComment);
		return "goods_comment_page";
	}

	/**
	 * 设置显示不显示
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void setShowGoodsComment() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		String isShow = this.getRequest().getParameter("isShow");
		ReturnData returnData = new ReturnData();
		if (id == null || "".equals(id.trim())) {
			returnData.setStatus(0);
			returnData.setMessage("请选择正确的咨询数据!");
			this.getResponse().setCharacterEncoding("UTF-8");
		} else {
			tGoodsComment = tgoodscommentmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tGoodsComment == null) {
				returnData.setStatus(0);
				returnData.setMessage("您选择的咨询数据不存在,请确认!");
			} else {
				tGoodsComment.setIsShow(Integer.valueOf(isShow));
				tgoodscommentmanager.updateByPrimaryKeySelective(tGoodsComment);
				returnData.setStatus(1);
				returnData.setMessage("设置成功!");
			}
		}
		this.writeObjectToResponse(returnData, ContentType.application_json);
	}

	/**
	 * 删除
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void delGoodsComment() throws NumberFormatException, SQLException {
		ReturnData returnData = new ReturnData();
		String id = this.getRequest().getParameter("id");
		if (id == null || "".equals(id.trim())) {
			returnData.setStatus(0);
			returnData.setMessage("ID不能为空!");
		} else {
			TGoodsComment tGoodsComment = tgoodscommentmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tGoodsComment == null) {
				returnData.setStatus(0);
				returnData.setMessage("未找到该评论信息!");
			} else {
				tgoodscommentmanager.deleteByPrimaryKey(Long.valueOf(id));
				returnData.setStatus(1);
				returnData.setMessage("删除成功!");
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

	public TGoodsCommentManager getTgoodscommentmanager() {
		return tgoodscommentmanager;
	}

	public void setTgoodscommentmanager(TGoodsCommentManager tgoodscommentmanager) {
		this.tgoodscommentmanager = tgoodscommentmanager;
	}

	public TGoodsComment gettGoodsComment() {
		return tGoodsComment;
	}

	public void settGoodsComment(TGoodsComment tGoodsComment) {
		this.tGoodsComment = tGoodsComment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public TOrderManager getTordermanager() {
		return tordermanager;
	}

	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
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

	public TOrder gettOrder() {
		return tOrder;
	}

	public void settOrder(TOrder tOrder) {
		this.tOrder = tOrder;
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
