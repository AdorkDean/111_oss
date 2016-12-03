package com.rc.oss.service.impl;

import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.oss.dao.OpenSqlItf;
import com.rc.oss.service.OpenSqlManagerItf;

/**  
 * @Title: OpenSqlItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-16 下午04:05:04
 * @version V1.0  
 */

public class OpenSqlManager implements OpenSqlManagerItf{
	private OpenSqlItf openSqlDAO;
	public OpenSqlItf getOpenSqlDAO() {
		return openSqlDAO;
	}

	public void setOpenSqlDAO(OpenSqlItf openSqlDAO) {
		this.openSqlDAO = openSqlDAO;
	}

	/**
	 * 说明:公开查询方法，适合单表或多表联合查询。
	 * 注意：使用标准HQL，不要使用SQL，不要使用本地数据库的分页语句,建议使用hibernate分页，实现跨库
	 * 
	 * @param queryname
	 *            命名查询的名字
	 * @param map
	 *            传入的参数map
	 * @return
	 */
	public  List getListByNamed(String queryname, Map<String, String> map){
		return openSqlDAO.getListByNamed(queryname, map);
	}

	/**
	 * 说明:工具传入HQL查询
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public  List getListByStr(String queryString, String[] values){
		return openSqlDAO.getListByStr(queryString, values);
	}

	/**
	 * 说明:工具传入HQL查询,单表分页查询
	 * 
	 * @param hql
	 *            查询HQL
	 * @param hql_cnt
	 *            计算总数量HQL
	 * @param pageInfo
	 * @return
	 */
	public PageWraper getListForPage(final String hql, final String hql_cnt, final PageInfo pageInfo){
		return openSqlDAO.getListForPage(hql, hql_cnt, pageInfo);
	}

	/**
	 * 说明:工具传入命名查询的名字,进行分页查询，可以单表一多表联合查询，建议使用严格hql
	 * @param namedQuery
	 * @param hql_cnt
	 * @param pageInfo
	 * @return
	 */
	public PageWraper getPageByNamedQuery(final String namedQuery, final String hql_cnt, final PageInfo pageInfo){
		return openSqlDAO.getPageByNamedQuery(namedQuery, hql_cnt, pageInfo);
	}

	/**
	 * 说明:动态查询
	 * @param namespace Dynamics.dql里的命名空间名字
	 * @param root	传入的参数
	 * @return
	 */
	public List getListByDynamics(String namespace, Map root){
		return openSqlDAO.getListByDynamics(namespace, root);
	}

	/**
	 * 说明:动态分页查询
	 * @param namespace 查询的命名空间
	 * @param namespace_cnt 查询数量的命名空间
	 * @param root	传入的参数
	 * @param pageInfo 分页信息
	 * @return
	 */
	public PageWraper getListForDynamicsPage(String namespace, String namespace_cnt, Map root, final PageInfo pageInfo){
		return openSqlDAO.getListForDynamicsPage(namespace, namespace_cnt, root, pageInfo);
	}

	@Override
	public List getListByDynamicsHql(String namespace, Map root) {
		// TODO Auto-generated method stub
		return openSqlDAO.getListByDynamicsHql(namespace, root);
	}

	@Override
	public PageWraper getListForDynamicsPageHql(String namespace, String namespace_cnt, Map root, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return openSqlDAO.getListForDynamicsPageHql(namespace, namespace_cnt, root, pageInfo);
	}

	@Override
	public void ByDynamicsDeleteHql(String hql) {
		// TODO Auto-generated method stub
		openSqlDAO.ByDynamicsDeleteHql(hql);
	}
	@Override
	public void ByDynamicsDeleteHqlRoot(String namespace,Map root) {
		// TODO Auto-generated method stub
		openSqlDAO.ByDynamicsDeleteHqlRoot(namespace, root);
	}

}