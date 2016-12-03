package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmRescRolesDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmRescRoles;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmRescRoles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmRescRoles
 * @author MyEclipse Persistence Tools
 */

public class TAdmRescRolesDAO extends HibernateDaoSupport implements TAdmRescRolesDAOItf {
	private static final Log log = LogFactory.getLog(TAdmRescRolesDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#save(com.rc.oss.vo.TAdmRescRoles)
	 */
	public void save(TAdmRescRoles transientInstance) {
		log.debug("saving TAdmRescRoles instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#delete(com.rc.oss.vo.TAdmRescRoles)
	 */
	public void delete(TAdmRescRoles persistentInstance) {
		log.debug("deleting TAdmRescRoles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public void deleteAll(List<TAdmRescRoles> persistentInstances) {
		log.debug("deleting TAdmRescRoles instance");
		try {
			getHibernateTemplate().deleteAll(persistentInstances);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#findById(java.lang.Long)
	 */
	public TAdmRescRoles findById(java.lang.Long id) {
		log.debug("getting TAdmRescRoles instance with id: " + id);
		try {
			TAdmRescRoles instance = (TAdmRescRoles) getHibernateTemplate().get("com.rc.oss.vo.TAdmRescRoles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#findByExample(com.rc.oss.vo.TAdmRescRoles)
	 */
	public List findByExample(TAdmRescRoles instance) {
		log.debug("finding TAdmRescRoles instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmRescRoles instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmRescRoles as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmRescRoles instances");
		try {
			String queryString = "from TAdmRescRoles";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#merge(com.rc.oss.vo.TAdmRescRoles)
	 */
	public TAdmRescRoles merge(TAdmRescRoles detachedInstance) {
		log.debug("merging TAdmRescRoles instance");
		try {
			TAdmRescRoles result = (TAdmRescRoles) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#attachDirty(com.rc.oss.vo.TAdmRescRoles)
	 */
	public void attachDirty(TAdmRescRoles instance) {
		log.debug("attaching dirty TAdmRescRoles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescRolesDAOItf#attachClean(com.rc.oss.vo.TAdmRescRoles)
	 */
	public void attachClean(TAdmRescRoles instance) {
		log.debug("attaching clean TAdmRescRoles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmRescRolesDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmRescRolesDAOItf) ctx.getBean("TAdmRescRolesDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}
}