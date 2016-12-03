package com.rc.portal.webapp.model;

import java.math.BigDecimal;

public class PriceFormBeanModel
{
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
	private String pcUpdate;
	private String pcDowndate;
	
	/**WAP自动上下架时间*/
	private String wapUpdate;
	private String wapDowndate;
	
	/**APP自动上下架时间*/
	private String appUpdate;
	private String appDowndate;
	
	public String getPcUpdate()
	{
		return pcUpdate;
	}
	public void setPcUpdate(String pcUpdate)
	{
		this.pcUpdate = pcUpdate;
	}
	public String getPcDowndate()
	{
		return pcDowndate;
	}
	public void setPcDowndate(String pcDowndate)
	{
		this.pcDowndate = pcDowndate;
	}
	public String getWapUpdate()
	{
		return wapUpdate;
	}
	public void setWapUpdate(String wapUpdate)
	{
		this.wapUpdate = wapUpdate;
	}
	public String getWapDowndate()
	{
		return wapDowndate;
	}
	public void setWapDowndate(String wapDowndate)
	{
		this.wapDowndate = wapDowndate;
	}
	public String getAppUpdate()
	{
		return appUpdate;
	}
	public void setAppUpdate(String appUpdate)
	{
		this.appUpdate = appUpdate;
	}
	public String getAppDowndate()
	{
		return appDowndate;
	}
	public void setAppDowndate(String appDowndate)
	{
		this.appDowndate = appDowndate;
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
	public PriceFormBeanModel()
	{
		super();
	}
	public PriceFormBeanModel(BigDecimal pcPrice, BigDecimal wapPrice,
			BigDecimal appPrice, Integer isPcTop, Integer isWapTop,
			Integer isAppTop, Integer pcStatus, Integer wapStatus,
			Integer appStatus, Integer isPcShow, Integer isWapShow,
			Integer isAppShow)
	{
		super();
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
	
}
