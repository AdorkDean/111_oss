package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.YktGoodsManager;
import com.rc.portal.vo.YktGoods;
import com.rc.portal.webapp.util.PageResult;



public class YiKaTongProduct extends BaseAction {
	
	private YktGoodsManager yktgoodsmanager;
	private OpenSqlManage opensqlmanage;
	
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	
	
	private String goodsno;

	

	public String list(){
		
		Map map = new HashMap();
		if(!"".equals(goodsno)){
			map.put("goodsno", goodsno);
		}
		
		pw = opensqlmanage.selectForPageByMap_drug(map,"ykt_goods.ykt-list-count", "ykt_goods.ykt-list",  rs.getP_curPage(), rs.getP_pageSize());
		return "ykt-list";
	}
	
	
	public void add() throws SQLException, IOException{
		
		PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();
			YktGoods g = new YktGoods();
			g.setGoodsNo(goodsno);
			g.setCreateTime(new Date());
			long id = yktgoodsmanager.insertSelective(g);
			out.print(id);
			out.close();
		}catch(Exception e){
			out.print(0);
			out.close();
			e.printStackTrace();
		}
	}
	
	
	public void delete() throws NumberFormatException, SQLException, IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		String id = this.getRequest().getParameter("id");
		int n  = yktgoodsmanager.deleteByPrimaryKey(Long.parseLong(id));
		out.print(n);
		out.close();
	}
	


	
	public static void main(String[] args) throws IOException {
		
		YiKaTongProduct o = new YiKaTongProduct();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		o.opensqlmanage = (OpenSqlManage) context.getBean("opensqlmanage");
		o.yktgoodsmanager = (YktGoodsManager) context.getBean("yktgoodsmanager");
		
		YktGoods record = new YktGoods();
	
		/*
		 List<String> list = new ArrayList<String>();
		 File file=new File("c://no.txt");
		 if(file.isFile() && file.exists()){ //判断文件是否存在
             InputStreamReader read = new InputStreamReader(
             new FileInputStream(file),"UTF-8");//考虑到编码格式
             BufferedReader bufferedReader = new BufferedReader(read);
             String lineTxt = null;
             while((lineTxt = bufferedReader.readLine()) != null){
                 System.out.println(lineTxt);
                 list.add(lineTxt);
             }
             read.close();
		 }
		 
		 System.out.println("--->"+list.size());*/
		 
	}

	
	

	public YktGoodsManager getYktgoodsmanager() {
		return yktgoodsmanager;
	}


	public void setYktgoodsmanager(YktGoodsManager yktgoodsmanager) {
		this.yktgoodsmanager = yktgoodsmanager;
	}


	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}


	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}


	public PageWraper getPw() {
		return pw;
	}


	public void setPw(PageWraper pw) {
		this.pw = pw;
	}


	public PageResult getRs() {
		return rs;
	}


	public void setRs(PageResult rs) {
		this.rs = rs;
	}


	public String getGoodsno() {
		return goodsno;
	}


	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}


	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		
	}

}
