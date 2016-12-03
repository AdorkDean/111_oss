package com.rc.portal.webapp.util;
/**
 * 
 * 版权所有：2013-北京健喜玖玖电子商务有限公司
 * 项目名称：drug_portal   
 *
 * 类描述：商品最终页商品评价所用的商品分页信息类
 * 类名称：com.rc.portal.webapp.util.PageInfoUtil     
 * 创建人：梁彦强 
 * 创建时间：2013-2-4 下午05:35:06   
 * 修改人：
 * 修改时间：2013-2-4 下午05:35:06   
 * 修改备注：   
 * @version   V1.0
 */
public class PageInfoUtil {
	private Integer currentpage;//当前页
	private Integer pageSize =5;//每页个数
	private Integer count;//总记录数
	private Integer pages;//总页数
	
	private Integer prePage;//上一页
	private Integer nextPage;//下一页
	private Integer startPage; //首页
	private Integer[] pagebar; //显示的页码条
	
	
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}
	public void setPagebar(Integer[] pagebar) {
		this.pagebar = pagebar;
	}
	//根据显示条数和总记录数计算总页数
	public Integer getPages() {
		if( this.count%this.pageSize>0){
			this.pages =count/pageSize+1;
		}else if(this.count%pageSize==0){
			this.pages=this.count/this.pageSize;
		}
		return pages;
	}
	//计算上一页
	public Integer getPrePage() {
		if(this.currentpage>1){
			this.prePage = this.currentpage-1;
		}else{
			this.prePage= this.currentpage;
		}
		return prePage;
	}
	//计算下一页
	public Integer getNextPage() {
		if(this.currentpage>this.pages-1){
			this.nextPage =pages;
		}else{
			this.nextPage =this.currentpage+1;
		}
		return nextPage;
	}
	
	public Integer getStartPage() {
		startPage =1;
		return startPage;
	}
	
	public Integer[] getPagebar() {
		int startpage;
		int endpage;
		if(this.pages<=this.pageSize){
			startpage=1;
			endpage=this.pages;
		}else {
			int t=0;//显示当前页的前t页
			int m=0;//显示当前页的后m页
			if(this.pageSize%2==0){
				t=this.pageSize/2-1;
				m=this.pageSize/2;
			}else{
				t=m=this.pageSize/2;
			}
			startpage=this.currentpage-t;
			endpage=this.currentpage+m;	
			if(startpage<1){
				startpage=1;
				endpage=this.pageSize;
			}
			if(endpage>this.pages){
				startpage=this.pages-this.pageSize+1;
				endpage=this.pages;
			}
		}
		this.pagebar = new Integer[endpage-startpage+1];
		 int j=0;
		  for(int i=startpage;i<=endpage;i++){
			  this.pagebar[j++]=i;
		  }
		return pagebar;
	}

/*	public static void main(String[] args) {
		PageInfoUtil p = new PageInfoUtil();
		p.setCount(1);
		p.setCurrentpage(1);
		p.setPageSize(5);
		
		
		System.out.println();
		System.out.println("总页数："+p.getPages());
		System.out.println("每页显示条数"+p.pageSize);
		System.out.println("当前页："+p.getCurrentpage());
		System.out.println("上一页："+p.getPrePage());
		System.out.println("下一页："+p.getNextPage());
		int a[] =p.getPagebar();
		for(int i=0;i<a.length;i++){
			System.out.print(" "+a[i]+",");
		}
	}*/
}
