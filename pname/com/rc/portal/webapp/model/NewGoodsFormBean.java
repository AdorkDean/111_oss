package com.rc.portal.webapp.model;

import java.math.BigDecimal;
import java.util.Date;

import com.rc.portal.vo.TGoods;

public class NewGoodsFormBean
{
	/**商品*/
	private TGoods goods;
	
	/**商品价格实体ID*/
	Integer pc_priceId = null;
	Integer wap_priceId = null;
	Integer app_priceId = null;
	
	/**PC价格*/
	private BigDecimal pcPrice;
	/**WAP价格*/
	private BigDecimal wapPrice;
	/**APP价格*/
	private BigDecimal appPrice;
	
	/**PC是否置顶*/
	private Integer isPcTop;
	/**WAP是否置顶*/
	private Integer isWapTop;
	/**APP是否置顶*/
	private Integer isAppTop;
	
	/**PC上下架状态*/
	private Integer pcStatus;
	/**WAP上下架状态*/
	private Integer wapStatus;
	/**APP上下架状态*/
	private Integer appStatus;
	
	/**PC是否显示*/
	private Integer isPcShow;
	/**WAP是否显示*/
	private Integer isWapShow;
	/**APP是否显示*/
	private Integer isAppShow;
	
	/**PC自动上下架时间*/
	private Date pcUpdate;
	private Date pcDowndate;
	
	/**WAP自动上下架时间*/
	private Date wapUpdate;
	private Date wapDowndate;
	
	/**APP自动上下架时间*/
	private Date appUpdate;
	private Date appDowndate;
	
	public NewGoodsFormBean()
	{
		super();
	}
	
	
	public Integer getPc_priceId()
	{
		return pc_priceId;
	}

	public void setPc_priceId(Integer pc_priceId)
	{
		this.pc_priceId = pc_priceId;
	}

	public Integer getWap_priceId()
	{
		return wap_priceId;
	}

	public void setWap_priceId(Integer wap_priceId)
	{
		this.wap_priceId = wap_priceId;
	}

	public Integer getApp_priceId()
	{
		return app_priceId;
	}

	public void setApp_priceId(Integer app_priceId)
	{
		this.app_priceId = app_priceId;
	}

	public NewGoodsFormBean(TGoods goods, Integer pc_priceId,
			Integer wap_priceId, Integer app_priceId, BigDecimal pcPrice,
			BigDecimal wapPrice, BigDecimal appPrice, Integer isPcTop,
			Integer isWapTop, Integer isAppTop, Integer pcStatus,
			Integer wapStatus, Integer appStatus, Integer isPcShow,
			Integer isWapShow, Integer isAppShow, Date pcUpdate,
			Date pcDowndate, Date wapUpdate, Date wapDowndate, Date appUpdate,
			Date appDowndate)
	{
		super();
		this.goods = goods;
		this.pc_priceId = pc_priceId;
		this.wap_priceId = wap_priceId;
		this.app_priceId = app_priceId;
		this.pcPrice = pcPrice;
		this.wapPrice = wapPrice;
		this.appPrice = appPrice;
		this.isPcTop = isPcTop;
		this.isWapTop = isWapTop;
		this.isAppTop = isAppTop;
		this.pcStatus = pcStatus;
		this.wapStatus = wapStatus;
		this.appStatus = appStatus;
		this.isPcShow = isPcShow;
		this.isWapShow = isWapShow;
		this.isAppShow = isAppShow;
		this.pcUpdate = pcUpdate;
		this.pcDowndate = pcDowndate;
		this.wapUpdate = wapUpdate;
		this.wapDowndate = wapDowndate;
		this.appUpdate = appUpdate;
		this.appDowndate = appDowndate;
	}


	public TGoods getGoods()
	{
		return goods;
	}
	public void setGoods(TGoods goods)
	{
		this.goods = goods;
	}
	public BigDecimal getPcPrice()
	{
		return pcPrice;
	}
	public void setPcPrice(BigDecimal pcPrice)
	{
		this.pcPrice = pcPrice;
	}
	public BigDecimal getWapPrice()
	{
		return wapPrice;
	}
	public void setWapPrice(BigDecimal wapPrice)
	{
		this.wapPrice = wapPrice;
	}
	public BigDecimal getAppPrice()
	{
		return appPrice;
	}
	public void setAppPrice(BigDecimal appPrice)
	{
		this.appPrice = appPrice;
	}
	public Integer getIsPcTop()
	{
		return isPcTop;
	}
	public void setIsPcTop(Integer isPcTop)
	{
		this.isPcTop = isPcTop;
	}
	public Integer getIsWapTop()
	{
		return isWapTop;
	}
	public void setIsWapTop(Integer isWapTop)
	{
		this.isWapTop = isWapTop;
	}
	public Integer getIsAppTop()
	{
		return isAppTop;
	}
	public void setIsAppTop(Integer isAppTop)
	{
		this.isAppTop = isAppTop;
	}
	public Integer getPcStatus()
	{
		return pcStatus;
	}
	public void setPcStatus(Integer pcStatus)
	{
		this.pcStatus = pcStatus;
	}
	public Integer getWapStatus()
	{
		return wapStatus;
	}
	public void setWapStatus(Integer wapStatus)
	{
		this.wapStatus = wapStatus;
	}
	public Integer getAppStatus()
	{
		return appStatus;
	}
	public void setAppStatus(Integer appStatus)
	{
		this.appStatus = appStatus;
	}
	public Integer getIsPcShow()
	{
		return isPcShow;
	}
	public void setIsPcShow(Integer isPcShow)
	{
		this.isPcShow = isPcShow;
	}
	public Integer getIsWapShow()
	{
		return isWapShow;
	}
	public void setIsWapShow(Integer isWapShow)
	{
		this.isWapShow = isWapShow;
	}
	public Integer getIsAppShow()
	{
		return isAppShow;
	}
	public void setIsAppShow(Integer isAppShow)
	{
		this.isAppShow = isAppShow;
	}
	public Date getPcUpdate()
	{
		return pcUpdate;
	}
	public void setPcUpdate(Date pcUpdate)
	{
		this.pcUpdate = pcUpdate;
	}
	public Date getPcDowndate()
	{
		return pcDowndate;
	}
	public void setPcDowndate(Date pcDowndate)
	{
		this.pcDowndate = pcDowndate;
	}
	public Date getWapUpdate()
	{
		return wapUpdate;
	}
	public void setWapUpdate(Date wapUpdate)
	{
		this.wapUpdate = wapUpdate;
	}
	public Date getWapDowndate()
	{
		return wapDowndate;
	}
	public void setWapDowndate(Date wapDowndate)
	{
		this.wapDowndate = wapDowndate;
	}
	public Date getAppUpdate()
	{
		return appUpdate;
	}
	public void setAppUpdate(Date appUpdate)
	{
		this.appUpdate = appUpdate;
	}
	public Date getAppDowndate()
	{
		return appDowndate;
	}
	public void setAppDowndate(Date appDowndate)
	{
		this.appDowndate = appDowndate;
	}
	
	
}
