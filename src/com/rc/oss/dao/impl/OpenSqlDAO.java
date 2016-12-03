package com.rc.oss.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.FreeMarkerUtil;
import com.rc.oss.dao.OpenSqlItf;
import com.rc.oss.util.HbnSqlFactory;

/**
 * @Title: OpenSqlDAO.java
 * @Description:
 * @author yinbinhome@163.com
 * @date 2013-5-16 下午03:45:50
 * @version V1.0
 */

public class OpenSqlDAO extends HibernateDaoSupport implements OpenSqlItf {
	private HbnSqlFactory hbnSqlFactory;
	public HbnSqlFactory getHbnSqlFactory() {
		return hbnSqlFactory;
	}

	public void setHbnSqlFactory(HbnSqlFactory hbnSqlFactory) {
		this.hbnSqlFactory = hbnSqlFactory;
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
	public List getListByNamed(String queryname, Map<String, String> map) {
		int size = map.size();
		String[] names = new String[size];
		String[] values = new String[size];
		Set set = map.keySet();
		Iterator it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			String name = (String) it.next();
			String value = map.get(name);
			names[i] = name;
			values[i] = value;
		}
		List<Object> list = getHibernateTemplate().findByNamedQueryAndNamedParam(queryname, names, values);
		return list;
	}

	/**
	 * 说明:工具传入HQL查询
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public List getListByStr(String queryString, String[] values) {
		List list = getHibernateTemplate().find(queryString, values);
		return list;
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
	public PageWraper getListForPage(final String hql, final String hql_cnt, final PageInfo pageInfo) {
		PageWraper pw = null;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((pageInfo.getPage()-1) * pageInfo.getPageSize());
				query.setMaxResults(pageInfo.getPageSize());
				List list = query.list();
				return list;
			}
		});
		Integer count = (Integer) getHibernateTemplate().find(hql_cnt).listIterator().next();
		pw = PageManager.getPageWraper(pageInfo, list, count.intValue());
		return pw;
	}
	
	
	/**
	 * 说明:工具传入命名查询的名字,进行分页查询，可以单表一多表联合查询，建议使用严格hql
	 * @param namedQuery
	 * @param hql_cnt
	 * @param pageInfo
	 * @return
	 */
	public PageWraper getPageByNamedQuery(final String namedQuery, final String namedQuery_cnt, final PageInfo pageInfo) {
		PageWraper pw = null;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.getNamedQuery(namedQuery);
				query.setFirstResult((pageInfo.getPage()-1) * pageInfo.getPageSize());
				query.setMaxResults(pageInfo.getPageSize());
				List list = query.list();
				return list;
			}
		});
		
		Integer count = (Integer) getHibernateTemplate().findByNamedQuery(namedQuery_cnt).listIterator().next();
		pw = PageManager.getPageWraper(pageInfo, list, count.intValue());
		return pw;
	}

	/**
	 * 使用criterion进行操作
	 * 
	 * @param arg
	 * @param criterions
	 * @param offset
	 * @param length
	 * @return List
	 */
	protected List getListForPage(final Class arg, final Criterion[] criterions, final PageInfo pageInfo) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(arg);
				// 循环遍历添加约束条件
				for (int i = 0; i < criterions.length; i++) {
					criteria.add(criterions[i]);
				}

				criteria.setFirstResult((pageInfo.getPage()-1) * pageInfo.getPageSize());
				criteria.setMaxResults(pageInfo.getPageSize());
				return criteria.list();
			}
		});
		return list;
	}
	
	/**
	 * 说明:动态查询
	 * @param namespace Dynamics.dql里的命名空间名字
	 * @param root	传入的参数
	 * @return
	 */
	public List getListByDynamics(String namespace,Map root){
		root.put("namespace", namespace);
		String path=getClass().getResource("/").getPath();
		final String hql = FreeMarkerUtil.createTemplate(root, "Dynamics.dql", path);
		PageWraper pw = null;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				List list = query.list();
				return list;
			}
		});
		return list;		
	}
	
	/**
	 * 说明:动态分页查询
	 * @param namespace 查询的命名空间
	 * @param namespace_cnt 查询数量的命名空间
	 * @param root	传入的参数
	 * @param pageInfo 分页信息
	 * @return
	 */
	public PageWraper getListForDynamicsPage(String namespace,String namespace_cnt,Map root, final PageInfo pageInfo) {
		root.put("namespace", namespace);
		String path=getClass().getResource("/").getPath();
		final String hql = FreeMarkerUtil.createTemplate(root, "Dynamics.dql", path);
		root.put("namespace", namespace_cnt);
		final String hql_cnt = FreeMarkerUtil.createTemplate(root, "Dynamics.dql", path);
		
		PageWraper pw = null;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((pageInfo.getPage()-1) * pageInfo.getPageSize());
				query.setMaxResults(pageInfo.getPageSize());
				List list = query.list();
				return list;
			}
		});
		Integer count = (Integer) getHibernateTemplate().find(hql_cnt).listIterator().next();
		pw = PageManager.getPageWraper(pageInfo, list, count.intValue());
		return pw;
	}
	
	
	//==============================================================================================
	//==============================================================================================	
	/**
	 * 说明:动态查询
	 * @param namespace Dynamics.dql里的命名空间名字
	 * @param root	传入的参数
	 * @return
	 */
	public List getListByDynamicsHql(String namespace,Map root){
		final String hql = hbnSqlFactory.getHql(namespace, root);
		System.out.println(hql);
		PageWraper pw = null;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				List list = query.list();
				return list;
			}
		});
		return list;		
	}
	
	/**
	 * 说明:动态分页查询
	 * @param namespace 查询的命名空间
	 * @param namespace_cnt 查询数量的命名空间
	 * @param root	传入的参数
	 * @param pageInfo 分页信息
	 * @return
	 */
	public PageWraper getListForDynamicsPageHql(String namespace,String namespace_cnt,Map root, final PageInfo pageInfo) {
		final String hql = hbnSqlFactory.getHql(namespace, root);
		final String hql_cnt = hbnSqlFactory.getHql(namespace_cnt, root);
		
		PageWraper pw = null;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((pageInfo.getPage()-1) * pageInfo.getPageSize());
				query.setMaxResults(pageInfo.getPageSize());
				List list = query.list();
				return list;
			}
		});
		Long count = (Long) getHibernateTemplate().find(hql_cnt).listIterator().next();
		pw = PageManager.getPageWraper(pageInfo, list, count.intValue());
		return pw;
	}
	
	/**
	 * 说明:动态删除
	 * @param hql
	 */
	public void ByDynamicsDeleteHql(String hql){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery(hql).executeUpdate();
		
	}
	/**
	 * 说明:根据sql动态删除
	 * @param hql
	 */
	public void ByDynamicsDeleteHqlRoot(String namespace,Map root){
		final String hql = hbnSqlFactory.getHql(namespace, root);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery(hql).executeUpdate();
		
	}
}
