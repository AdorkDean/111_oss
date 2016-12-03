package com.rc.portal.webapp.model;

/**  
 * @Title: Rule.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-12-16 下午04:04:49
 * @version V1.0  
 */

public class Rule {
	private Show txt;
	private Show txt_url;
	private Show img;
	private Show img_url;
	private Show title;
	private Show title_url;
	private Show title_all;
	private Show price;
	private Show price_url;
	private Show market_price;
	private Show type;
	
	public Rule() {
		super();
	}
	public Rule(Show txt, Show txt_url, Show img, Show img_url, Show title,
			Show title_url, Show title_all, Show price, Show price_url,
			Show market_price, Show type) {
		super();
		this.txt = txt;
		this.txt_url = txt_url;
		this.img = img;
		this.img_url = img_url;
		this.title = title;
		this.title_url = title_url;
		this.title_all = title_all;
		this.price = price;
		this.price_url = price_url;
		this.market_price = market_price;
		this.type = type;
	}
	public Show getTxt() {
		return txt;
	}
	public void setTxt(Show txt) {
		this.txt = txt;
	}
	public Show getTxt_url() {
		return txt_url;
	}
	public void setTxt_url(Show txt_url) {
		this.txt_url = txt_url;
	}
	public Show getImg() {
		return img;
	}
	public void setImg(Show img) {
		this.img = img;
	}
	public Show getImg_url() {
		return img_url;
	}
	public void setImg_url(Show img_url) {
		this.img_url = img_url;
	}
	public Show getTitle() {
		return title;
	}
	public void setTitle(Show title) {
		this.title = title;
	}
	public Show getTitle_url() {
		return title_url;
	}
	public void setTitle_url(Show title_url) {
		this.title_url = title_url;
	}
	public Show getTitle_all() {
		return title_all;
	}
	public void setTitle_all(Show title_all) {
		this.title_all = title_all;
	}
	public Show getPrice() {
		return price;
	}
	public void setPrice(Show price) {
		this.price = price;
	}
	public Show getPrice_url() {
		return price_url;
	}
	public void setPrice_url(Show price_url) {
		this.price_url = price_url;
	}
	public Show getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Show market_price) {
		this.market_price = market_price;
	}
	public Show getType() {
		return type;
	}
	public void setType(Show type) {
		this.type = type;
	}
	
	
	
	
}
