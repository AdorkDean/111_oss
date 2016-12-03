package com.rc.portal.webapp.action;

import com.rc.app.framework.webapp.action.BaseAction;

public class DrugAction extends BaseAction {
	
	private User model;
  
	public String addDrugPage(){
		return "add_drug_page";
	}
	
    /**
	 * 检查文件类型
	 * @param type
	 * @return
	 */
	public boolean checkFileType(String type){
		boolean flag=false;
		type = type.toLowerCase();
		String[] arrType={"jpg","png","jpeg"};
		for(String s:arrType){
			if(type.equals(s)){
				return true;
			}
		}
		return flag;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return (User)model;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		this.model = (User)o;
	}
	
	class User{
		private String name;
		private String pwd;
		
	}
	
	
}
