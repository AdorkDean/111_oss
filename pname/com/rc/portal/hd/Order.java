package com.rc.portal.hd;

import java.util.List;

/**
 * 海典主订单实体
 * 
 * @author WWF
 * @createTime 2016-3-25 下午4:41:20
 */
public class Order {
	private String olorderno; // ;//varchar 订单号
	private String olshopid;// varchar 网店编码
	private int status; // Int 状态
	private String olcusid;// varchar 网店客户ID
	private String createtime;// varchar 生成日期
	private String discount_fee;// varchar 折扣金额
	private String total_fee;// varchar 订单总额
	private String adjust_fee;// varchar 调整金额
	private String pay_time;// varchar 付款时间
	private String modified;// varchar 修改时间
	private String alipay_id;// varchar 支付宝用户ID
	private String alipay_no;// varchar 支付宝交易号
	private String buyer_nick;// varchar 买家昵称
	private String receiver_name;// varchar 买家姓名
	private String receiver_state;// varchar 买家省
	private String receiver_district;// varchar 买家区
	private String receiver_address;// varchar 买家街道
	private String receiver_zip;// varchar 买家邮编
	private String receiver_mobile;// varchar 买家电话1
	private String receiver_phone;// varchar 买家电话2
	private String buyer_message;// varchar 买家备注
	private String notes;// varchar 备注
	private String post_fee;// varchar 邮费
	private String buyer_email;// varchar 买家邮箱
	private String trade_from;// varchar 订单来源
	private String expcmpname;// varchar 快递名称
	private String paytype;// varchar 付款类型
	private String Receiver_city;// varchar 买家市
	private String ol_status;// varchar 线上状态
	private String eccode;// varchar 平台编码
	private String seller_code_fee;// varchar 卖家货到付款服务费
	private String discount_fee_sum;// varchar 整单折扣金额
	private String payment;// varchar 实付金额
	private String invoice_name;// varchar 发票抬头
	private String invoice_type;// varchar 发票内容类型
	private String buyer_cod_fee;// varchar 买家货到付款服务费
	private String discount_fee_dtl;// varchar 明细折扣合计
	private String buyer_obtain_point_fee;// varchar 买家获得积分
	private String payway;// varchar 付款方式
	private String yfx_fee;// varchar 运费险金额
	private String yfx_id;// varchar 运费险交易ID
	private String yfx_type;// varchar 运费险类型
	private String delivery_type;// varchar 送货类型
	private String available_confirm_fee;// varchar 剩余确认收货金额
	private String real_point_fee;// varchar 买家实际使用积分
	private String commission;// varchar 佣金比例
	private String commission_fee;// varchar 交易佣金
	private String buyer_cod;// varchar 货到付款服务费比率
	private String confirm_time;// varchar 确认时间
	private String order_start_time;// varchar 订单开始时间
	private String seller_cod;// varchar 卖家货到付款服务费比例
	private String credit_card_fee;// varchar 信用卡支付金额
	private String end_time;// varchar 结束时间
	private String seller_memo;// varchar 卖家留言
	private String invoice_kind;// varchar 发票类型
	private String olbusno;// varchar 作业点ID
	private String invoice_contect;// varchar 发票内容
	private String discount_fee_eccode;// varchar 平台优惠金额
	private String bill_type;// varchar 单据类型
	private String rx_audit_status;// varchar 处方药状态
	private String havecfy;// varchar 是否是处方药
	private String groupid;// 1001

	// OrderDetail Array 订单明细
	private List<OrderDetail> orderDetail;

	public String getOlorderno() {
		return olorderno;
	}

	public void setOlorderno(String olorderno) {
		this.olorderno = olorderno;
	}

	public String getOlshopid() {
		return olshopid;
	}

	public void setOlshopid(String olshopid) {
		this.olshopid = olshopid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOlcusid() {
		return olcusid;
	}

	public void setOlcusid(String olcusid) {
		this.olcusid = olcusid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDiscount_fee() {
		return discount_fee;
	}

	public void setDiscount_fee(String discount_fee) {
		this.discount_fee = discount_fee;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getAdjust_fee() {
		return adjust_fee;
	}

	public void setAdjust_fee(String adjust_fee) {
		this.adjust_fee = adjust_fee;
	}

	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getAlipay_id() {
		return alipay_id;
	}

	public void setAlipay_id(String alipay_id) {
		this.alipay_id = alipay_id;
	}

	public String getAlipay_no() {
		return alipay_no;
	}

	public void setAlipay_no(String alipay_no) {
		this.alipay_no = alipay_no;
	}

	public String getBuyer_nick() {
		return buyer_nick;
	}

	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}


	public String getReceiver_state() {
		return receiver_state;
	}

	public void setReceiver_state(String receiver_state) {
		this.receiver_state = receiver_state;
	}

	public String getReceiver_district() {
		return receiver_district;
	}

	public void setReceiver_district(String receiver_district) {
		this.receiver_district = receiver_district;
	}

	public String getReceiver_address() {
		return receiver_address;
	}

	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}

	public String getReceiver_zip() {
		return receiver_zip;
	}

	public void setReceiver_zip(String receiver_zip) {
		this.receiver_zip = receiver_zip;
	}

	public String getReceiver_mobile() {
		return receiver_mobile;
	}

	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}

	public String getReceiver_phone() {
		return receiver_phone;
	}

	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}

	public String getBuyer_message() {
		return buyer_message;
	}

	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPost_fee() {
		return post_fee;
	}

	public void setPost_fee(String post_fee) {
		this.post_fee = post_fee;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getTrade_from() {
		return trade_from;
	}

	public void setTrade_from(String trade_from) {
		this.trade_from = trade_from;
	}

	public String getExpcmpname() {
		return expcmpname;
	}

	public void setExpcmpname(String expcmpname) {
		this.expcmpname = expcmpname;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getReceiver_city() {
		return Receiver_city;
	}

	public void setReceiver_city(String receiver_city) {
		Receiver_city = receiver_city;
	}

	public String getOl_status() {
		return ol_status;
	}

	public void setOl_status(String ol_status) {
		this.ol_status = ol_status;
	}

	public String getEccode() {
		return eccode;
	}

	public void setEccode(String eccode) {
		this.eccode = eccode;
	}

	public String getSeller_code_fee() {
		return seller_code_fee;
	}

	public void setSeller_code_fee(String seller_code_fee) {
		this.seller_code_fee = seller_code_fee;
	}

	public String getDiscount_fee_sum() {
		return discount_fee_sum;
	}

	public void setDiscount_fee_sum(String discount_fee_sum) {
		this.discount_fee_sum = discount_fee_sum;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getInvoice_name() {
		return invoice_name;
	}

	public void setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	public String getBuyer_cod_fee() {
		return buyer_cod_fee;
	}

	public void setBuyer_cod_fee(String buyer_cod_fee) {
		this.buyer_cod_fee = buyer_cod_fee;
	}

	public String getDiscount_fee_dtl() {
		return discount_fee_dtl;
	}

	public void setDiscount_fee_dtl(String discount_fee_dtl) {
		this.discount_fee_dtl = discount_fee_dtl;
	}

	public String getBuyer_obtain_point_fee() {
		return buyer_obtain_point_fee;
	}

	public void setBuyer_obtain_point_fee(String buyer_obtain_point_fee) {
		this.buyer_obtain_point_fee = buyer_obtain_point_fee;
	}

	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	public String getYfx_fee() {
		return yfx_fee;
	}

	public void setYfx_fee(String yfx_fee) {
		this.yfx_fee = yfx_fee;
	}

	public String getYfx_id() {
		return yfx_id;
	}

	public void setYfx_id(String yfx_id) {
		this.yfx_id = yfx_id;
	}

	public String getYfx_type() {
		return yfx_type;
	}

	public void setYfx_type(String yfx_type) {
		this.yfx_type = yfx_type;
	}

	public String getDelivery_type() {
		return delivery_type;
	}

	public void setDelivery_type(String delivery_type) {
		this.delivery_type = delivery_type;
	}

	public String getAvailable_confirm_fee() {
		return available_confirm_fee;
	}

	public void setAvailable_confirm_fee(String available_confirm_fee) {
		this.available_confirm_fee = available_confirm_fee;
	}

	public String getReal_point_fee() {
		return real_point_fee;
	}

	public void setReal_point_fee(String real_point_fee) {
		this.real_point_fee = real_point_fee;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCommission_fee() {
		return commission_fee;
	}

	public void setCommission_fee(String commission_fee) {
		this.commission_fee = commission_fee;
	}

	public String getBuyer_cod() {
		return buyer_cod;
	}

	public void setBuyer_cod(String buyer_cod) {
		this.buyer_cod = buyer_cod;
	}

	public String getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getOrder_start_time() {
		return order_start_time;
	}

	public void setOrder_start_time(String order_start_time) {
		this.order_start_time = order_start_time;
	}

	public String getSeller_cod() {
		return seller_cod;
	}

	public void setSeller_cod(String seller_cod) {
		this.seller_cod = seller_cod;
	}

	public String getCredit_card_fee() {
		return credit_card_fee;
	}

	public void setCredit_card_fee(String credit_card_fee) {
		this.credit_card_fee = credit_card_fee;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getSeller_memo() {
		return seller_memo;
	}

	public void setSeller_memo(String seller_memo) {
		this.seller_memo = seller_memo;
	}

	public String getInvoice_kind() {
		return invoice_kind;
	}

	public void setInvoice_kind(String invoice_kind) {
		this.invoice_kind = invoice_kind;
	}

	public String getOlbusno() {
		return olbusno;
	}

	public void setOlbusno(String olbusno) {
		this.olbusno = olbusno;
	}

	public String getInvoice_contect() {
		return invoice_contect;
	}

	public void setInvoice_contect(String invoice_contect) {
		this.invoice_contect = invoice_contect;
	}

	public String getDiscount_fee_eccode() {
		return discount_fee_eccode;
	}

	public void setDiscount_fee_eccode(String discount_fee_eccode) {
		this.discount_fee_eccode = discount_fee_eccode;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

	public String getRx_audit_status() {
		return rx_audit_status;
	}

	public void setRx_audit_status(String rx_audit_status) {
		this.rx_audit_status = rx_audit_status;
	}

	public String getHavecfy() {
		return havecfy;
	}

	public void setHavecfy(String havecfy) {
		this.havecfy = havecfy;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

}
