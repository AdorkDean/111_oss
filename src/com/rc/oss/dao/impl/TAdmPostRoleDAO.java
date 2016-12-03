package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmPostRoleDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmPostRole;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmPostRole entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmPostRole
 * @author MyEclipse Persistence Tools
 */

public class TAdmPostRoleDAO extends HibernateDaoSupport implements TAdmPostRoleDAOItf {
	private static final Log log = LogFactory.getLog(TAdmPostRoleDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#save(com.rc.oss.vo.TAdmPostRole)
	 */
	public void save(TAdmPostRole transientInstance) {
		log.debug("saving TAdmPostRole instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#delete(com.rc.oss.vo.TAdmPostRole)
	 */
	public void delete(TAdmPostRole persistentInstance) {
		log.debug("deleting TAdmPostRole instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#findById(java.lang.Long)
	 */
	public TAdmPostRole findById(java.lang.Long id) {
		log.debug("getting TAdmPostRole instance with id: " + id);
		try {
			TAdmPostRole instance = (TAdmPostRole) getHibernateTemplate().get("com.rc.oss.vo.TAdmPostRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#findByExample(com.rc.oss.vo.TAdmPostRole)
	 */
	public List findByExample(TAdmPostRole instance) {
		log.debug("finding TAdmPostRole instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmPostRole instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmPostRole as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmPostRole instances");
		try {
			String queryString = "from TAdmPostRole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#merge(com.rc.oss.vo.TAdmPostRole)
	 */
	public TAdmPostRole merge(TAdmPostRole detachedInstance) {
		log.debug("merging TAdmPostRole instance");
		try {
			TAdmPostRole result = (TAdmPostRole) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#attachDirty(com.rc.oss.vo.TAdmPostRole)
	 */
	public void attachDirty(TAdmPostRole instance) {
		log.debug("attaching dirty TAdmPostRole instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostRoleDAOItf#attachClean(com.rc.oss.vo.TAdmPostRole)
	 */
	public void attachClean(TAdmPostRole instance) {
		log.debug("attaching clean TAdmPostRole instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmPostRoleDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmPostRoleDAOItf) ctx.getBean("TAdmPostRoleDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}

	public void deleteAll(List<TAdmPostRole> persistentInstances) {
		log.debug("deleting TAdmPostRole instance");
		try {
			getHibernateTemplate().deleteAll(persistentInstances);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
}