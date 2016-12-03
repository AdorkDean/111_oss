package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TCategoryExample;

public class CategoryTreeAction extends BaseAction {
	
	
	
	private TCategoryManager tcategorymanager;
	
	public String firstGrade() throws SQLException{
		
		System.out.println("*************************");
		
		TCategoryExample example = new TCategoryExample();
		example.createCriteria().andCategoryLevelEqualTo(1);
		List<TCategory> list1 = tcategorymanager.selectByExample(example);
		
		example.clear();
		example.createCriteria().andCategoryLevelEqualTo(2);
		List<TCategory> list2 = tcategorymanager.selectByExample(example);
		
		example.clear();
		example.createCriteria().andCategoryLevelEqualTo(3);
		List<TCategory> list3 = tcategorymanager.selectByExample(example);
		
		
		this.getRequest().setAttribute("list1",list1);
		this.getRequest().setAttribute("list2",list2);
		this.getRequest().setAttribute("list3",list3);
		
		return "first-grade";
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
	
	

	public TCategoryManager getTcategorymanager() {
		return tcategorymanager;
	}



	public void setTcategorymanager(TCategoryManager tcategorymanager) {
		this.tcategorymanager = tcategorymanager;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
