package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.CPositionListManager;
import com.rc.portal.service.CPositionTypeManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.vo.CPositionType;
import com.rc.portal.webapp.util.PageResult;

/**
 * 位置类别管理
 * @author LGP
 * @date 2015年8月12日
 */
public class PositionTypeManageAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private CPositionType cPositionType;
	private CPositionTypeManager cpositiontypemanager;
	private CPositionListManager cpositionlistmanager;
	
	 public static void main(String[] args) throws SQLException
	 {
		 PositionTypeManageAction o = new PositionTypeManageAction();
		 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		 o.cpositiontypemanager = (CPositionTypeManager) context.getBean("cpositiontypemanager");
		 CPositionType c = new CPositionType();
		 c.setCreateDate(new Date());
		 long a =	o.cpositiontypemanager.insertSelective(c);
		 System.out.println("=="+a);
		 o.cpositiontypemanager.selectByPrimaryKey(1L);
	 }
	 
	/**
	 * 查询位置类别列表
	 */
	public String selectPostionTypeList()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		pw = opensqlmanage.selectForPageByMap_drug(map, "c_position_type.ibatorgenerated_countByExample", "c_position_type.selectByExampleByPage", rs.getP_curPage(), rs.getP_pageSize());
		return "postypelist";
	}
	
	/**
	 * 显示添加位置类别页面
	 */
	public String showAddPage()
	{
		return "addpage";
	}

	/**
	 * 保存位置类型信息数据
	 */
	@SuppressWarnings("unchecked")
	public void addPositionType() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		cPositionType.setCreateDate(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", cPositionType.getName());
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "c_position_type.selectByname");
		if(datas != null && datas.size() > 0)
		{
			out.print("1");
		}
		else
		{
			cpositiontypemanager.insert(cPositionType);
			out.print("0");
		}
		out.close();
	}
	
	/**
	 * 删除位置类型信息数据
	 */
	public void deletePosType() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		String strId = this.getRequest().getParameter("id");
		if(strId != null && !strId.trim().equals(""))
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("genre", strId);
			int cres = Integer.parseInt(String.valueOf(opensqlmanage.selectForObjectByMap(map, "c_position_list.count_selectGenre")));
			if(cres > 0)
			{
				out.print(0);
			}
			else
			{
				int dbc = cpositiontypemanager.deleteByPrimaryKey(Long.parseLong(strId));
				if(dbc > 0)
				{
					out.print(1);
				}
				else
				{
					out.print(2);
				}
			}
		}
		else
		{
			out.print(3);
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 弹出编辑位置类型信息页面
	 */
	public String showEditPage() throws NumberFormatException, SQLException
	{
		String strId = this.getRequest().getParameter("id");
		if(strId != null && !strId.trim().equals(""))
		{
			cPositionType = cpositiontypemanager.selectByPrimaryKey(Long.valueOf(strId));
		}
		return "editpage";
	}
	
	/**
	 * 编辑位置类型信息
	 */
	public void editPositionType() throws NumberFormatException, SQLException, IOException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		String strId = this.getRequest().getParameter("id");
		if(strId != null && !strId.trim().equals(""))
		{
			CPositionType ct = cpositiontypemanager.selectByPrimaryKey(Long.valueOf(strId));
			int flag = 0;
			if(!ct.getName().trim().equals(cPositionType.getName()))
			{
				ct.setName(cPositionType.getName());
				flag = cpositiontypemanager.updateByPrimaryKey(ct);
				if(flag > 0)
				{
					out.print(0);
				}
				else
				{
					out.print(1);
				}
			}
			else
			{
				out.print(1);
			}
		}
		else
		{
			out.print(1);
		}
		out.flush();
		out.close();
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

	public CPositionType getcPositionType()
	{
		return cPositionType;
	}

	public void setcPositionType(CPositionType cPositionType)
	{
		this.cPositionType = cPositionType;
	}

	@Override
	public Object getModel() 
	{
		return null;
	}

	@Override
	public void setModel(Object o) 
	{
	}

	public CPositionTypeManager getCpositiontypemanager()
	{
		return cpositiontypemanager;
	}

	public void setCpositiontypemanager(CPositionTypeManager cpositiontypemanager)
	{
		this.cpositiontypemanager = cpositiontypemanager;
	}

	public CPositionListManager getCpositionlistmanager()
	{
		return cpositionlistmanager;
	}

	public void setCpositionlistmanager(CPositionListManager cpositionlistmanager)
	{
		this.cpositionlistmanager = cpositionlistmanager;
	}
	
}
