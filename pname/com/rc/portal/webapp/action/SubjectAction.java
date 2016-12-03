package com.rc.portal.webapp.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.FileUtil;
import com.rc.commons.freemarker.InfoUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TSubjectManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.vo.TSubject;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.Des;
import com.rc.portal.webapp.util.PageResult;

/**
 * 专题页管理
 * @author LTL&LGP
 * @date 2015年10月29日
 */
public class SubjectAction extends BaseAction
{

	private static final long serialVersionUID = 45345345345341L;

	private OpenSqlManage opensqlmanage;
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	private TSubject tsubject = new TSubject();
	
	private TSubjectManager tsubjectmanager;
	
	private String ztcode;
	
	private Integer platform = 0;
	
	public Integer getPlatform()
	{
		return platform;
	}

	public void setPlatform(Integer platform)
	{
		this.platform = platform;
	}

	public String getZtcode()
	{
		return ztcode;
	}

	public void setZtcode(String ztcode)
	{
		this.ztcode = ztcode;
	}

	public Object getModel() 
	{
		return tsubject;
	}

	public void setModel(Object o) 
	{
		this.tsubject = (TSubject) o;
	}
	
	public OpenSqlManage getOpensqlmanage()
	{
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) 
	{
		this.opensqlmanage = opensqlmanage;
	}

	public PageWraper getPw() 
	{
		return pw;
	}

	public void setPw(PageWraper pw)
	{
		this.pw = pw;
	}

	public PageResult getRs()
	{
		return rs;
	}

	public void setRs(PageResult rs)
	{
		this.rs = rs;
	}

	public TSubjectManager getTsubjectmanager()
	{
		return tsubjectmanager;
	}

	public void setTsubjectmanager(TSubjectManager tsubjectmanager) 
	{
		this.tsubjectmanager = tsubjectmanager;
	}

	public TSubject getTsubject() 
	{
		return tsubject;
	}

	public void setTsubject(TSubject tsubject) 
	{
		this.tsubject = tsubject;
	}

	/**
	 * 专题列表
	 * @return
	 * @throws Exception
	 */
	public String list()
	{
		Map<String,Object> param = new HashMap<String,Object>();
		if(tsubject != null)
		{
			if(!com.rc.commons.util.StringUtil.isEmpty(tsubject.getName()))
			{
				param.put("name", "%"+tsubject.getName()+"%");
			}
			if(platform != 0)
			{
				param.put("ptype", platform);
			}
		}
		getRequest().setAttribute("platform", platform);
		pw = opensqlmanage.selectForPageByMap_drug(param, "t_subject.ibatorgenerated_countByExample", "t_subject.selectByExampleByPage", rs.getP_curPage(), 5);
		return "list";
	}
	
	/**
	 * 弹出添加页面
	 */
	public String add()
	{
		return "add";
	}
	
	/**
	 * 删除专题
	 * @throws SQLException 
	 */
	public void delete() throws SQLException
	{
		int id = Integer.parseInt(String.valueOf(getRequest().getParameter("id")));
		tsubject = tsubjectmanager.selectByPrimaryKey(id);
		int resultDB = tsubjectmanager.deleteByPrimaryKey(id);
		File file = new File(tsubject.getTempletePath());
		boolean resultFile = false;
		if(file.exists())
		{
			resultFile = file.delete();
		}
		if(resultDB > 0 && resultFile)
		{
			writeObjectToResponse(1, ContentType.application_json);
		}
		else
		{
			writeObjectToResponse(2, ContentType.application_json);
		}
	}
	
	/**
	 * 弹出编辑页面
	 * @throws SQLException 
	 */
	public String edit() throws SQLException
	{
		int id = Integer.parseInt(String.valueOf(getRequest().getParameter("id")));
		tsubject = tsubjectmanager.selectByPrimaryKey(id);
		ztcode = new String(tsubject.getZtContext());
		return "edit";
	}
	
	/**
	 * 保存专题对象并生成对应文件
	 * @throws SQLException
	 * @throws IOException
	 */
	public void save() throws SQLException, IOException
	{
		//项目绝对路径
		String root = getRequest().getSession().getServletContext().getRealPath("/");
		//用户
		UserinfoModel userinfo = (UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		//平台类型
		int ptype = tsubject.getPtype();
		String platformurl = null;
		//公网地址
		String visiturl = null;
		String common = "/static/product/nzt";
		//文件名称
		String filename = tsubject.getNameFile();
		//专题代码
		String zcode = new Des().strEnc(ztcode, "1", "2", "3");
        byte[] code = zcode.getBytes();
		if(ptype == 1)
		{
			platformurl = common+"/p/";
			visiturl = InfoUtil.getInstance().getInfo("config", "pc_zt_url")+"/nzt/p/"+filename;
		}
		if(ptype == 2)
		{
			platformurl = common+"/m/";
			visiturl = InfoUtil.getInstance().getInfo("config", "wap_zt_url")+"/nzt/m/"+filename;
		}
		if(ptype == 3)
		{
			platformurl = common+"/a/";
			visiturl = InfoUtil.getInstance().getInfo("config", "app_zt_url")+"/nzt/a/"+filename;
		}
		//保存专题对象
		TSubject ts = new TSubject();
		ts.setName(tsubject.getName());
		ts.setNameFile(tsubject.getNameFile());
		ts.setZtContext(code);
		ts.setTempletePath(root+platformurl+filename);
		ts.setLastEdit(userinfo.getUsername());
		ts.setCreateDt(new Date());
		ts.setZtPath(visiturl);
		ts.setPtype(ptype);
		int result = tsubjectmanager.insert(ts);
		//生成专题文件
		if(result > 0)
		{
			FileUtil.createFile(ztcode, root+platformurl+filename);
		}
	}
	
	/**
	 * 更新专题对象并更新对应文件
	 * @throws SQLException
	 * @throws IOException
	 */
	public void update() throws SQLException, IOException
	{
		//查找当前专题对象
		TSubject ts = tsubjectmanager.selectByPrimaryKey(tsubject.getId());
		//项目绝对路径
		String root = getRequest().getSession().getServletContext().getRealPath("/");
		//用户
		UserinfoModel userinfo = (UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		//平台类型
		int ptype = tsubject.getPtype();
		String platformurl = null;
		//公网地址
		String visiturl = null;
		String common = "/static/product/nzt";
		//文件名称
		String filename = tsubject.getNameFile();
		//专题代码
		String zcode = new Des().strEnc(ztcode, "1", "2", "3");
		byte[] code = zcode.getBytes();
		if(ptype == 1)
		{
			platformurl = common+"/p/";
			visiturl = InfoUtil.getInstance().getInfo("config", "pc_zt_url")+"/nzt/p/"+filename;
		}
		if(ptype == 2)
		{
			platformurl = common+"/m/";
			visiturl = InfoUtil.getInstance().getInfo("config", "wap_zt_url")+"/nzt/m/"+filename;
		}
		if(ptype == 3)
		{
			platformurl = common+"/a/";
			visiturl = InfoUtil.getInstance().getInfo("config", "app_zt_url")+"/nzt/a/"+filename;
		}
		ts.setName(tsubject.getName());
		ts.setNameFile(tsubject.getNameFile());
		ts.setZtContext(code);
		ts.setLastEdit(userinfo.getUsername());
		ts.setLastDt(new Date());
		ts.setTempletePath(root+platformurl+filename);
		ts.setZtPath(visiturl);
		ts.setPtype(ptype);
		int result = tsubjectmanager.updateByPrimaryKeySelective(ts);
		//生成专题文件
		if(result > 0)
		{
			FileUtil.createFile(ztcode, root+platformurl+filename);
		}
	}
	
	/**
	 * 专题预览
	 */
	public void preview()
	{
		int ptype = tsubject.getPtype();
		String common = "/static/product/nzt";
		String domainurl = null;
		String filename = "/preview.html";
		switch (ptype)
		{
			case 1:
				domainurl = InfoUtil.getInstance().getInfo("config", "pc_zt_url");
				break;
			case 2:
				domainurl = InfoUtil.getInstance().getInfo("config", "wap_zt_url");
				break;
			default:
				domainurl = InfoUtil.getInstance().getInfo("config", "app_zt_url");
				break;
		}
		String fullname = getRequest().getSession().getServletContext().getRealPath("/")+common+filename;
		String previewurl = domainurl + common+filename;
		FileUtil.createFile(ztcode, fullname);
		writeObjectToResponse(previewurl, ContentType.application_json);
	}

}
