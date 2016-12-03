package com.rc.portal.webapp.model;

/**
 * 药师点评封装Bean
 * @author LGP
 * @date 2015-08-30
 */
public class TPharmacistBean 
{
    private String id;
    private String phyheaderurl;
    private String realname;
    private String content;
    private String ctime;
    private String isview;
    private String goodsid;
    private String shortname;
    private String sn;
    private String is_pc;
    private String is_wap;
    private String is_app;
    private String goodsno;
    
    public TPharmacistBean()
    {
    }
    
	public TPharmacistBean(String id, String phyheaderurl, String realname,
			String content, String ctime, String isview, String goodsid,
			String shortname, String sn, String is_pc, String is_wap,
			String is_app, String goodsno) {
		super();
		this.id = id;
		this.phyheaderurl = phyheaderurl;
		this.realname = realname;
		this.content = content;
		this.ctime = ctime;
		this.isview = isview;
		this.goodsid = goodsid;
		this.shortname = shortname;
		this.sn = sn;
		this.is_pc = is_pc;
		this.is_wap = is_wap;
		this.is_app = is_app;
		this.goodsno = goodsno;
	}

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPhyheaderurl()
	{
		return phyheaderurl;
	}
	public void setPhyheaderurl(String phyheaderurl)
	{
		this.phyheaderurl = phyheaderurl;
	}
	public String getRealname()
	{
		return realname;
	}
	public void setRealname(String realname)
	{
		this.realname = realname;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getCtime()
	{
		return ctime;
	}
	public void setCtime(String ctime)
	{
		this.ctime = ctime;
	}
	public String getIsview()
	{
		return isview;
	}
	public void setIsview(String isview)
	{
		this.isview = isview;
	}
	public String getGoodsid()
	{
		return goodsid;
	}
	public void setGoodsid(String goodsid)
	{
		this.goodsid = goodsid;
	}
	public String getShortname()
	{
		return shortname;
	}
	public void setShortname(String shortname)
	{
		this.shortname = shortname;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getIs_pc()
	{
		return is_pc;
	}

	public void setIs_pc(String is_pc)
	{
		this.is_pc = is_pc;
	}

	public String getIs_wap()
	{
		return is_wap;
	}

	public void setIs_wap(String is_wap)
	{
		this.is_wap = is_wap;
	}

	public String getIs_app()
	{
		return is_app;
	}

	public void setIs_app(String is_app)
	{
		this.is_app = is_app;
	}

	public String getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

}