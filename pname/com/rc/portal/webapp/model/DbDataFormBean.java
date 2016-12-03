package com.rc.portal.webapp.model;

public class DbDataFormBean
{
	/**商品ID*/
	private Long id;
	
	/**商品简称*/
	private String short_name;
	
	/**商品编号*/
	private String goodsno;
	
	/**规格*/
	private String spec;
	
	/**市场价格*/
	private String price;
	
	/**PC价格*/
	private String pc_price;
	
	/**WAP价格*/
	private String wap_price;
	
	/**APP价格*/
	private String app_price;
	
	/**PC上架状态*/
	private String pc_status;
	
	/**WAP上架状态*/
	private String wap_status;
	
	/**APP上架状态*/
	private String app_status;
	
	/**PC是否显示*/
	private String pc_isshow;
	
	/**WAP是否显示*/
	private String wap_isshow;
	
	/**APP是否显示*/
	private String app_isshow;
	
	/**PC是否置顶*/
	private String isPcTop;
	
	/**WAP是否置顶*/
	private String isWapTop;
	
	/**APP是否置顶*/
	private String isAppTop;
	
	/**缩略图*/
	private String abbreviation_picture;
	
	/**库存*/
	private String stock;
	
	/**是否发布*/
	private String is_release;
	
	/**条形码*/
	private String bar_code;
	
	/**批准文号*/
	private String approval_number;
	
	/**SKUID*/
	private String sku_id;
	
	/**海典编号*/
	private String goodscode;
	
	/**厂家ID*/
	private String manufacturer_id;
	
	/**商品全称*/
	private String goods_name;
	
	/**商品类型*/
	private String type;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getShort_name()
	{
		return short_name;
	}

	public void setShort_name(String short_name)
	{
		this.short_name = short_name;
	}

	public String getGoodsno()
	{
		return goodsno;
	}

	public void setGoodsno(String goodsno)
	{
		this.goodsno = goodsno;
	}

	public String getSpec()
	{
		return spec;
	}

	public void setSpec(String spec)
	{
		this.spec = spec;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getPc_price()
	{
		return pc_price;
	}

	public void setPc_price(String pc_price)
	{
		this.pc_price = pc_price;
	}

	public String getWap_price()
	{
		return wap_price;
	}

	public void setWap_price(String wap_price)
	{
		this.wap_price = wap_price;
	}

	public String getApp_price()
	{
		return app_price;
	}

	public void setApp_price(String app_price)
	{
		this.app_price = app_price;
	}

	public String getPc_status()
	{
		return pc_status;
	}

	public void setPc_status(String pc_status)
	{
		this.pc_status = pc_status;
	}

	public String getWap_status()
	{
		return wap_status;
	}

	public void setWap_status(String wap_status)
	{
		this.wap_status = wap_status;
	}

	public String getApp_status()
	{
		return app_status;
	}

	public void setApp_status(String app_status)
	{
		this.app_status = app_status;
	}

	public String getPc_isshow()
	{
		return pc_isshow;
	}

	public void setPc_isshow(String pc_isshow)
	{
		this.pc_isshow = pc_isshow;
	}

	public String getWap_isshow()
	{
		return wap_isshow;
	}

	public void setWap_isshow(String wap_isshow)
	{
		this.wap_isshow = wap_isshow;
	}

	public String getApp_isshow()
	{
		return app_isshow;
	}

	public void setApp_isshow(String app_isshow)
	{
		this.app_isshow = app_isshow;
	}

	public String getIsPcTop()
	{
		return isPcTop;
	}

	public void setIsPcTop(String isPcTop)
	{
		this.isPcTop = isPcTop;
	}

	public String getIsWapTop()
	{
		return isWapTop;
	}

	public void setIsWapTop(String isWapTop)
	{
		this.isWapTop = isWapTop;
	}

	public String getIsAppTop()
	{
		return isAppTop;
	}

	public void setIsAppTop(String isAppTop)
	{
		this.isAppTop = isAppTop;
	}

	public String getStock()
	{
		return stock;
	}

	public void setStock(String stock)
	{
		this.stock = stock;
	}

	public String getIs_release()
	{
		return is_release;
	}

	public void setIs_release(String is_release)
	{
		this.is_release = is_release;
	}

	public String getBar_code()
	{
		return bar_code;
	}

	public void setBar_code(String bar_code)
	{
		this.bar_code = bar_code;
	}

	public String getApproval_number()
	{
		return approval_number;
	}

	public void setApproval_number(String approval_number)
	{
		this.approval_number = approval_number;
	}

	public String getSku_id()
	{
		return sku_id;
	}

	public void setSku_id(String sku_id)
	{
		this.sku_id = sku_id;
	}

	public String getGoodscode()
	{
		return goodscode;
	}

	public void setGoodscode(String goodscode)
	{
		this.goodscode = goodscode;
	}

	public String getManufacturer_id()
	{
		return manufacturer_id;
	}

	public void setManufacturer_id(String manufacturer_id)
	{
		this.manufacturer_id = manufacturer_id;
	}

	public String getGoods_name()
	{
		return goods_name;
	}

	public void setGoods_name(String goods_name)
	{
		this.goods_name = goods_name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public DbDataFormBean()
	{
		super();
	}

	public DbDataFormBean(Long id, String short_name, String goodsno,
			String spec, String price, String pc_price, String wap_price,
			String app_price, String pc_status, String wap_status,
			String app_status, String pc_isshow, String wap_isshow,
			String app_isshow, String isPcTop, String isWapTop,
			String isAppTop, String abbreviation_picture, String stock,
			String is_release, String bar_code, String approval_number,
			String sku_id, String goodscode, String manufacturer_id,
			String goods_name, String type)
	{
		super();
		this.id = id;
		this.short_name = short_name;
		this.goodsno = goodsno;
		this.spec = spec;
		this.price = price;
		this.pc_price = pc_price;
		this.wap_price = wap_price;
		this.app_price = app_price;
		this.pc_status = pc_status;
		this.wap_status = wap_status;
		this.app_status = app_status;
		this.pc_isshow = pc_isshow;
		this.wap_isshow = wap_isshow;
		this.app_isshow = app_isshow;
		this.isPcTop = isPcTop;
		this.isWapTop = isWapTop;
		this.isAppTop = isAppTop;
		this.abbreviation_picture = abbreviation_picture;
		this.stock = stock;
		this.is_release = is_release;
		this.bar_code = bar_code;
		this.approval_number = approval_number;
		this.sku_id = sku_id;
		this.goodscode = goodscode;
		this.manufacturer_id = manufacturer_id;
		this.goods_name = goods_name;
		this.type = type;
	}
	
	
}
