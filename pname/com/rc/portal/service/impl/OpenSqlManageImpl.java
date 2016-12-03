package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.service.OpenSqlManage;

/**  
 * @Title: OpenSqlManageImple.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2012-6-27 下午10:43:58
 * @version V1.0  
 */

public class OpenSqlManageImpl implements OpenSqlManage {
	private OpenSqlDAO opensqldao;
	
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}

	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}

	@Override
	public List selectForListByMap(Map map, String querySql) {
		// TODO Auto-generated method stub
		return opensqldao.selectForListByMap(map, querySql);
	}

	@Override
	public Object selectForObjectByMap(Map map, String querySql) {
		// TODO Auto-generated method stub
		return opensqldao.selectForObjectByMap(map, querySql);
	}

	@Override
	public PageWraper selectForPageByMap(Map map, String sql_count, String sql_record, Integer page, Integer pagesize) {
		// TODO Auto-generated method stub
		return opensqldao.selectForPageByMap(map, sql_count, sql_record, page, pagesize);
	}

	
	@Override
	public int updateByMap(Map map, String updatesql) throws SQLException {
		// TODO Auto-generated method stub
		return opensqldao.updateByMap(map, updatesql);
	}

	@Override
	public String batchInsertByList(List<Object> list, String insertsql) throws SQLException {
		// TODO Auto-generated method stub
		return opensqldao.batchInsertByList(list, insertsql);
	}
	
	
	
	
	
	
	public List selectForListByMap_drug(Map map, String querySql) {
		// TODO Auto-generated method stub
		return opensqldao.selectForListByMap_drug(map, querySql);
	}

	@Override
	public Object selectForObjectByMap_drug(Map map, String querySql) {
		// TODO Auto-generated method stub
		return opensqldao.selectForObjectByMap_drug(map, querySql);
	}

	@Override
	public PageWraper selectForPageByMap_drug(Map map, String sql_count, String sql_record, Integer page, Integer pagesize) {
		// TODO Auto-generated method stub
		return opensqldao.selectForPageByMap_drug(map, sql_count, sql_record, page, pagesize);
	}

	
	@Override
	public int updateByMap_drug(Map map, String updatesql) throws SQLException {
		// TODO Auto-generated method stub
		return opensqldao.updateByMap_drug(map, updatesql);
	}

	@Override
	public String batchInsertByList_drug(List<Object> list, String insertsql) throws SQLException {
		// TODO Auto-generated method stub
		return opensqldao.batchInsertByList_drug(list, insertsql);
	}
	
	
	
	

	public List selectForListByMap_public(Map map, String querySql) {
		// TODO Auto-generated method stub
		return opensqldao.selectForListByMap_public(map, querySql);
	}

	@Override
	public Object selectForObjectByMap_public(Map map, String querySql) {
		// TODO Auto-generated method stub
		return opensqldao.selectForObjectByMap_public(map, querySql);
	}

	@Override
	public PageWraper selectForPageByMap_public(Map map, String sql_count, String sql_record, Integer page, Integer pagesize) {
		// TODO Auto-generated method stub
		return opensqldao.selectForPageByMap_public(map, sql_count, sql_record, page, pagesize);
	}

	
	@Override
	public int updateByMap_public(Map map, String updatesql) throws SQLException {
		// TODO Auto-generated method stub
		return opensqldao.updateByMap_public(map, updatesql);
	}

	@Override
	public String batchInsertByList_public(List<Object> list, String insertsql) throws SQLException {
		// TODO Auto-generated method stub
		return opensqldao.batchInsertByList_public(list, insertsql);
	}
	
	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param object 任意队形的参数
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap_drug(Object object,String sql_count,String sql_record,Integer page,Integer pagesize){
		return opensqldao.selectForPageByMap_drug(object, sql_count, sql_record, page, pagesize);
	}
	
	/**
	 * 传入對象 返回一个对象(返回可一个实体类对象或者List集合)
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectObjectByObject(Object object,String querySql){
		return opensqldao.selectObjectByObject(object, querySql);
	}
	
	
	/**
	 * 根据对象查找集合
	 * @param object 任意类型
	 * @param querySql 需要执行的sql_map ID
	 * @return 任意对象 依据sql_map定义类型集合
	 */
	public Object selectListByObject(Object object,String querySql){
		return opensqldao.selectListByObject(object, querySql);
	}
}
