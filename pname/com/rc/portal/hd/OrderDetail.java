package com.rc.portal.hd;

/**
 * 海典订单项实体
 * 
 * @author WWF
 * @createTime 2016-3-25 下午4:40:47
 */
public class OrderDetail {

	private String olorderno;// varchar 订单号
	private String oid;// varchar 子订单编号
	private String needinvoice;// varchar 是否需要发票
	private String price;// varchar 单价
	private String num_iid;// varchar 商品数字ID
	private String item_meal_id;// varchar 套餐ID
	private String num;// varchar 数量
	private String total_fee;// varchar 总金额
	private String payment;// varchar 实付金额
	private String discount_fee;// varchar 折扣金额
	private String adjust_fee;// varchar 调整金额
	private String modified;// varchar 修改时间
	private String notes;// varchar 备注
	private String twarename;// varchar 商品名称
	private String eccode;// varchar 平台编码
	private String olshopid;// varchar 作业点
	private String sku_id;// varchar SKUID
	private String outer_iid;// varchar 外部编码

	public String getOlorderno() {
		return olorderno;
	}

	public void setOlorderno(String olorderno) {
		this.olorderno = olorderno;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getNeedinvoice() {
		return needinvoice;
	}

	public void setNeedinvoice(String needinvoice) {
		this.needinvoice = needinvoice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum_iid() {
		return num_iid;
	}

	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}

	public String getItem_meal_id() {
		return item_meal_id;
	}

	public void setItem_meal_id(String item_meal_id) {
		this.item_meal_id = item_meal_id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDiscount_fee() {
		return discount_fee;
	}

	public void setDiscount_fee(String discount_fee) {
		this.discount_fee = discount_fee;
	}

	public String getAdjust_fee() {
		return adjust_fee;
	}

	public void setAdjust_fee(String adjust_fee) {
		this.adjust_fee = adjust_fee;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTwarename() {
		return twarename;
	}

	public void setTwarename(String twarename) {
		this.twarename = twarename;
	}

	public String getEccode() {
		return eccode;
	}

	public void setEccode(String eccode) {
		this.eccode = eccode;
	}

	public String getOlshopid() {
		return olshopid;
	}

	public void setOlshopid(String olshopid) {
		this.olshopid = olshopid;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public String getOuter_iid() {
		return outer_iid;
	}

	public void setOuter_iid(String outer_iid) {
		this.outer_iid = outer_iid;
	}

}
