package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmUserRolesDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmUserRoles;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmUserRoles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmUserRoles
 * @author MyEclipse Persistence Tools
 */

public class TAdmUserRolesDAO extends HibernateDaoSupport implements TAdmUserRolesDAOItf {
	private static final Log log = LogFactory.getLog(TAdmUserRolesDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#save(com.rc.oss.vo.TAdmUserRoles)
	 */
	public void save(TAdmUserRoles transientInstance) {
		log.debug("saving TAdmUserRoles instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#delete(com.rc.oss.vo.TAdmUserRoles)
	 */
	public void delete(TAdmUserRoles persistentInstance) {
		log.debug("deleting TAdmUserRoles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#findById(java.lang.Long)
	 */
	public TAdmUserRoles findById(java.lang.Long id) {
		log.debug("getting TAdmUserRoles instance with id: " + id);
		try {
			TAdmUserRoles instance = (TAdmUserRoles) getHibernateTemplate().get("com.rc.oss.vo.TAdmUserRoles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#findByExample(com.rc.oss.vo.TAdmUserRoles)
	 */
	public List findByExample(TAdmUserRoles instance) {
		log.debug("finding TAdmUserRoles instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmUserRoles instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmUserRoles as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmUserRoles instances");
		try {
			String queryString = "from TAdmUserRoles";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#merge(com.rc.oss.vo.TAdmUserRoles)
	 */
	public TAdmUserRoles merge(TAdmUserRoles detachedInstance) {
		log.debug("merging TAdmUserRoles instance");
		try {
			TAdmUserRoles result = (TAdmUserRoles) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#attachDirty(com.rc.oss.vo.TAdmUserRoles)
	 */
	public void attachDirty(TAdmUserRoles instance) {
		log.debug("attaching dirty TAdmUserRoles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserRolesDAOItf#attachClean(com.rc.oss.vo.TAdmUserRoles)
	 */
	public void attachClean(TAdmUserRoles instance) {
		log.debug("attaching clean TAdmUserRoles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmUserRolesDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmUserRolesDAOItf) ctx.getBean("TAdmUserRolesDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}
}