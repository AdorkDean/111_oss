package com.rc.oss.service;

import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageWraper;

/**  
 * @Title: OpenSqlItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-16 下午04:05:04
 * @version V1.0  
 */

public interface OpenSqlManagerItf {

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
	public abstract List getListByNamed(String queryname, Map<String, String> map);

	/**
	 * 说明:工具传入HQL查询
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public abstract List getListByStr(String queryString, String[] values);

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
	public abstract PageWraper getListForPage(final String hql, final String hql_cnt, final PageInfo pageInfo);

	/**
	 * 说明:工具传入命名查询的名字,进行分页查询，可以单表一多表联合查询，建议使用严格hql
	 * @param namedQuery
	 * @param hql_cnt
	 * @param pageInfo
	 * @return
	 */
	public abstract PageWraper getPageByNamedQuery(final String namedQuery, final String hql_cnt, final PageInfo pageInfo);

	/**
	 * 说明:动态查询
	 * @param namespace Dynamics.dql里的命名空间名字
	 * @param root	传入的参数
	 * @return
	 */
	public abstract List getListByDynamics(String namespace, Map root);

	/**
	 * 说明:动态分页查询
	 * @param namespace 查询的命名空间
	 * @param namespace_cnt 查询数量的命名空间
	 * @param root	传入的参数
	 * @param pageInfo 分页信息
	 * @return
	 */
	public abstract PageWraper getListForDynamicsPage(String namespace, String namespace_cnt, Map root, final PageInfo pageInfo);
	
	
	public List getListByDynamicsHql(String namespace,Map root);
	
	public PageWraper getListForDynamicsPageHql(String namespace,String namespace_cnt,Map root, final PageInfo pageInfo);
	
	/**
	 * 说明:动态删除
	 * @param hql
	 */
	public void ByDynamicsDeleteHql(String hql);
	public void ByDynamicsDeleteHqlRoot(String namespace,Map root) ;

}