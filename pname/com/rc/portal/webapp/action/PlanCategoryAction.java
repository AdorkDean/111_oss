package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.THealthyPlanClassificationManager;
import com.rc.portal.service.THealthyPlanManager;
import com.rc.portal.service.THealthyPlanRecommendManager;
import com.rc.portal.vo.THealthyPlanClassification;
import com.rc.portal.vo.THealthyPlanExample;
import com.rc.portal.vo.THealthyPlanRecommendExample;
import com.rc.portal.webapp.util.PageResult;

public class PlanCategoryAction extends BaseAction  {
	
	private static final long serialVersionUID = 8108280843844897453L;
	private THealthyPlanClassificationManager thealthyplanclassificationmanager;
	
	private THealthyPlanRecommendManager thealthyplanrecommendmanager;
	
	private THealthyPlanManager  thealthyplanmanager;
	private THealthyPlanClassification planClassification ;
	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlanCategoryAction o = new PlanCategoryAction();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		o.opensqlmanage = (OpenSqlManage) context.getBean("opensqlmanage");
		o.list();
	}
	/**
	 * 分类列表
	 * @return
	 */
	public String list(){
		Map map = new HashMap();
		String classificationName = this.getRequest().getParameter("classificationName");
		if(StringUtils.hasText(classificationName)){
			map.put("classificationName", classificationName.trim());
		}
		this.getRequest().setAttribute("classificationName", classificationName);
		pw = opensqlmanage.selectForPageByMap_drug(map,"t_healthy_plan_classification.plan_category_count", "t_healthy_plan_classification.plan_category",  rs.getP_curPage(), rs.getP_pageSize());
		return "list";
	}
	/**
	 * 跳转到方案分类添加 修改页面
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public String toAddOrUpdate() throws NumberFormatException, SQLException {
		String classId = this.getRequest().getParameter("classId");
		if(StringUtils.hasText(classId)){
			planClassification = this.thealthyplanclassificationmanager.selectByPrimaryKey(Long.valueOf(classId.trim()));
		}else{
			planClassification = new THealthyPlanClassification();
		}
		return "add_plan_category";
	}
	/**
	 * 保存修改方案分类
	 * @throws SQLException
	 */
	public void saveOrUpdateClassification() throws SQLException {
		long classId = 0;
		if(planClassification!=null&&planClassification.getId()!=null){//修改
			classId = planClassification.getId();
			planClassification.setType(null);
			this.thealthyplanclassificationmanager.updateByPrimaryKeySelective(planClassification);
		}else{//新增
			planClassification.setCreateDt(new Date());
			classId=this.thealthyplanclassificationmanager.insertSelective(planClassification);
		}
		this.writeObjectToResponse(classId, ContentType.application_json);
	}
	/**
	 * 删除方案分类
	 * @throws Exception 
	 */
	public void deleteClassification() throws Exception {
		String flag ="0";//0 表示 失败  1 表示成功 2 表示分类下面有方案 不能删除
		String classId = this.getRequest().getParameter("classId");
		if(StringUtils.hasText(classId)){
			planClassification = this.thealthyplanclassificationmanager.selectByPrimaryKey(Long.valueOf(classId.trim()));
			if(planClassification.getType()==2){// 推荐分类
				THealthyPlanRecommendExample example = new THealthyPlanRecommendExample();
				example.createCriteria().andClassificationIdEqualTo(Long.valueOf(classId.trim()));
				List list = thealthyplanrecommendmanager.selectByExample(example);
				if(list!=null&&list.size()>0){
					flag="2";
				}else{
					thealthyplanclassificationmanager.deleteByPrimaryKey(Long.valueOf(classId.trim()));
					flag="1";
				}
			}else{
				THealthyPlanExample example = new THealthyPlanExample();
				example.createCriteria().andClassificationIdEqualTo(Long.valueOf(classId.trim())).andDeleteStatusEqualTo(0);
				List list =this.thealthyplanmanager.selectByExample(example);
				if(list!=null&&list.size()>0){
					flag="2";
				}else{
					thealthyplanmanager.deleteTHealthyPlanClassification(Long.valueOf(classId.trim()));
					flag="1";
				}
				
			}
		}
	    this.writeObjectToResponse(flag, ContentType.application_json);
	}


	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}


	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}


	public Object getModel() {
		return null;
	}
	public void setModel(Object o) {
		
	}
	public THealthyPlanClassification getPlanClassification() {
		return planClassification;
	}
	public void setPlanClassification(THealthyPlanClassification planClassification) {
		this.planClassification = planClassification;
	}
	public THealthyPlanClassificationManager getThealthyplanclassificationmanager() {
		return thealthyplanclassificationmanager;
	}
	public void setThealthyplanclassificationmanager(
			THealthyPlanClassificationManager thealthyplanclassificationmanager) {
		this.thealthyplanclassificationmanager = thealthyplanclassificationmanager;
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
	public THealthyPlanRecommendManager getThealthyplanrecommendmanager() {
		return thealthyplanrecommendmanager;
	}
	public void setThealthyplanrecommendmanager(
			THealthyPlanRecommendManager thealthyplanrecommendmanager) {
		this.thealthyplanrecommendmanager = thealthyplanrecommendmanager;
	}
	public THealthyPlanManager getThealthyplanmanager() {
		return thealthyplanmanager;
	}
	public void setThealthyplanmanager(THealthyPlanManager thealthyplanmanager) {
		this.thealthyplanmanager = thealthyplanmanager;
	}
	

}
