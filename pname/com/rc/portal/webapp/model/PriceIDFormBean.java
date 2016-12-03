package com.rc.portal.webapp.model;

public class PriceIDFormBean
{
	/**商品价格实体ID*/
	Long pc_priceId = null;
	Long wap_priceId = null;
	Long app_priceId = null;

	public PriceIDFormBean()
	{
		super();
	}
	
	public PriceIDFormBean(Long pc_priceId, Long wap_priceId,
			Long app_priceId)
	{
		super();
		this.pc_priceId = pc_priceId;
		this.wap_priceId = wap_priceId;
		this.app_priceId = app_priceId;
	}
	
	public Long getPc_priceId()
	{
		return pc_priceId;
	}
	public void setPc_priceId(Long pc_priceId)
	{
		this.pc_priceId = pc_priceId;
	}
	public Long getWap_priceId()
	{
		return wap_priceId;
	}
	public void setWap_priceId(Long wap_priceId)
	{
		this.wap_priceId = wap_priceId;
	}
	public Long getApp_priceId()
	{
		return app_priceId;
	}
	public void setApp_priceId(Long app_priceId)
	{
		this.app_priceId = app_priceId;
	}
	
}
