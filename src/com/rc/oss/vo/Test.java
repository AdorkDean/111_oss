package com.rc.oss.vo;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.oss.service.OpenSqlManagerItf;

/**  
 * @Title: Test.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-9 下午02:28:23
 * @version V1.0  
 */

public class Test {

	/**
	 * 说明:
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext act = new ClassPathXmlApplicationContext(new String[]{"applicationContext-oss.xml"});
		//TAdmModule1sDAO TAdmModulesDAO=(TAdmModule1sDAO) act.getBean("TAdmModulesDAO");
//		TAdmUserRolesDAO tAdmUserRolesDAO=(TAdmUserRolesDAO) act.getBean("TAdmUserRolesDAO");
//		TAdmUsersModulesDAO tAdmUsersModulesDAO=(TAdmUsersModulesDAO) act.getBean("TAdmUsersModulesDAO");
//		TAdmModulesManager tAdmModulesManager=(TAdmModulesManager) act.getBean("tAdmModulesManager");
		OpenSqlManagerItf openSqlDAO=(OpenSqlManagerItf) act.getBean("openSqlManager");
//		List<TAdmModules> list = TAdmModulesDAO.findAll();
//		TAdmModules instance=new TAdmModules();
//		instance.setId(1);
//		List<TAdmModules> list=TAdmModulesDAO.findByExample(instance);
//		for(TAdmModules t:list){
//			System.out.println(t.getMemo());
//			t.setMemo("测试成功1111-"+t.getId());
//			TAdmModulesDAO.merge(t);
//		}
//		tAdmUserRolesDAO.findAll();
//		TAdmUsersModules tAdmUsersModules=tAdmUsersModulesDAO.findById(567L);
//		System.out.println(tAdmUsersModules.getTAdmUsers());
//		List list1 = TAdmModulesDAO.findByPage();
//		TAdmPost tAdmPost=new TAdmPost();
//		tAdmPost.setCreateDt(new Date());
//		tAdmPost.setEnable(true);
//		tAdmPost.setName("123");
//		tAdmPost.setRemark("12345");
//		tAdmPost.setWebid(true);
//		List<TAdmPost> list=tAdmPostManager.findAll();
//		for(TAdmPost t:list){
//			System.out.println(t.getName());
//		}
//		List<Mytest> list=openSqlDAO.getListByDynamics("test", new HashMap());
		PageInfo p=new PageInfo();
		p.setPage(1);
		p.setPageSize(20);
		PageWraper pw=openSqlDAO.getListForDynamicsPage("test", "test_cnt", new HashMap(), p);
		List<Mytest> list=pw.getResult();
		for(Mytest t:list){
			System.out.println(t.getName());
		}
	}

}
