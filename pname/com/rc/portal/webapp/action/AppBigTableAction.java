package com.rc.portal.webapp.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.NbigTableManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.vo.NbigTable;
import com.rc.portal.vo.TCategory;


public class AppBigTableAction extends BaseAction  {
	
	private TCategoryManager tcategorymanager;
	
	private NbigTableManager nbigtablemanager;
	
	private OpenSqlManage opensqlmanage;
	
	private List<TCategory> list ;
	
	
	public String page() throws SQLException{
		
		list = opensqlmanage.selectForListByMap(null, "nbig_table.search_category");
		
		List<NbigTable> lista = opensqlmanage.selectForListByMap(null, "nbig_table.get-hot-words");
		
		
		StringBuilder hotWords = new StringBuilder("");
		StringBuilder daoHang = new StringBuilder("");
		
		for(NbigTable m : lista){
			if(m.getType()==3){
				hotWords.append(m.getName()+",");
			}
			if(m.getType()==1){
				daoHang.append(m.getName()+",");
			}
		}
		
		
		this.getRequest().setAttribute("hotwords", hotWords);
		this.getRequest().setAttribute("daohang_words", daoHang);
		
		System.out.println(">>>>>>>>>>>>>>"+list.size());
		
		return "words_list";
	}
	
	
	public int deleteByType(int type) throws SQLException{
		Map m = new HashMap();
		m.put("t", type);
		int n = opensqlmanage.updateByMap(m, "nbig_table.del-type");
		return n;
	}

	public void add() throws IOException, SQLException{
		
		PrintWriter out = this.getResponse().getWriter();
		String type = this.getRequest().getParameter("type");
		String hotwords = this.getRequest().getParameter("hotwords");
		String ids = this.getRequest().getParameter("ids");
		
		String daohang_words = this.getRequest().getParameter("daohang_words");
		
		deleteByType(2);
		deleteByType(3);
		
		NbigTable entity = new NbigTable();
		entity.setModifyDate(new Date());
		
		Date d = new Date();
		
		if("1".equals(type)){
			// 侧边栏入库
			String[] ary = ids.split(",");
			for(String id : ary){
				entity = new NbigTable();
				entity.setModifyDate(new Date());
				entity.setType(1);
				entity.setCreateDate(d);
				entity.setCid(Long.parseLong(id));
				nbigtablemanager.insertSelective(entity);
			}
		}else{
			
			// 热词入库
			String[] wordsArray = hotwords.split(",");
			for(String words : wordsArray){
				if(!"".equals(words)){
					entity = new NbigTable();
					entity.setModifyDate(new Date());
					entity.setName(words);
					entity.setType(3);
					entity.setCreateDate(d);
					entity.setCid(-100L);
					nbigtablemanager.insertSelective(entity);
				}
			}
			
			// 搜索分类入库
			String[] ary = ids.split(",");
			for(String id : ary){
				entity = new NbigTable();
				entity.setModifyDate(new Date());
				entity.setType(2);
				entity.setCreateDate(d);
				entity.setCid(Long.parseLong(id));
				nbigtablemanager.insertSelective(entity);
			}
			
		}
		
		deleteByType(4);
		if(daohang_words!=null && !"".equals(daohang_words)){
			
			entity = new NbigTable();
			entity.setModifyDate(new Date());
			entity.setName(daohang_words);
			entity.setType(4);
			entity.setCreateDate(d);
			entity.setCid(-1L);
			nbigtablemanager.insertSelective(entity);
		}
		out.print(1);
		out.close();
		
	}

	

	/*public void add() throws IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		String type = this.getRequest().getParameter("type");
		String hotwords = this.getRequest().getParameter("hotwords");
		String ids = this.getRequest().getParameter("ids");
		
		String daohang_words = this.getRequest().getParameter("daohang_words");
		
		int del = bigServiceImpl.del(2);
		bigServiceImpl.del(3);
		
		BigTable entity = new BigTable();
		
		Date d = new Date();
		
		if("1".equals(type)){
			// 侧边栏入库
			String[] ary = ids.split(",");
			for(String id : ary){
				entity = new BigTable();
				entity.setType(1);
				entity.setCreateDate(d);
				entity.setCid(Long.parseLong(id));
				bigServiceImpl.save(entity);
			}
		}else{
			
			// 热词入库
			String[] wordsArray = hotwords.split(",");
			for(String words : wordsArray){
				entity = new BigTable();
				entity.setName(words);
				entity.setType(3);
				entity.setCreateDate(d);
				entity.setCid(-100L);
				bigServiceImpl.save(entity);
			}
			
			// 搜索分类入库
			String[] ary = ids.split(",");
			for(String id : ary){
				entity = new BigTable();
				entity.setType(2);
				entity.setCreateDate(d);
				entity.setCid(Long.parseLong(id));
				bigServiceImpl.save(entity);
			}
			
		}
		
		bigServiceImpl.del(4);
		if(daohang_words!=null && !"".equals(daohang_words)){
			
			entity = new BigTable();
			entity.setName(daohang_words);
			entity.setType(4);
			entity.setCreateDate(d);
			entity.setCid(-1L);
			bigServiceImpl.save(entity);
		}
		out.print(1);
		out.close();
		
	}*/

	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		
	}


	public TCategoryManager getTcategorymanager() {
		return tcategorymanager;
	}


	public void setTcategorymanager(TCategoryManager tcategorymanager) {
		this.tcategorymanager = tcategorymanager;
	}




	public NbigTableManager getNbigtablemanager() {
		return nbigtablemanager;
	}




	public void setNbigtablemanager(NbigTableManager nbigtablemanager) {
		this.nbigtablemanager = nbigtablemanager;
	}




	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}




	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}




	public List<TCategory> getList() {
		return list;
	}




	public void setList(List<TCategory> list) {
		this.list = list;
	}
	
	
	
	

}
