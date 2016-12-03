package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmUsersModulesDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmUsersModules;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmUsersModules entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmUsersModules
 * @author MyEclipse Persistence Tools
 */

public class TAdmUsersModulesDAO extends HibernateDaoSupport implements TAdmUsersModulesDAOItf {
	private static final Log log = LogFactory.getLog(TAdmUsersModulesDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#save(com.rc.oss.vo.TAdmUsersModules)
	 */
	public void save(TAdmUsersModules transientInstance) {
		log.debug("saving TAdmUsersModules instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#delete(com.rc.oss.vo.TAdmUsersModules)
	 */
	public void delete(TAdmUsersModules persistentInstance) {
		log.debug("deleting TAdmUsersModules instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#findById(java.lang.Long)
	 */
	public TAdmUsersModules findById(java.lang.Long id) {
		log.debug("getting TAdmUsersModules instance with id: " + id);
		try {
			TAdmUsersModules instance = (TAdmUsersModules) getHibernateTemplate().get("com.rc.oss.vo.TAdmUsersModules", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#findByExample(com.rc.oss.vo.TAdmUsersModules)
	 */
	public List findByExample(TAdmUsersModules instance) {
		log.debug("finding TAdmUsersModules instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmUsersModules instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmUsersModules as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmUsersModules instances");
		try {
			String queryString = "from TAdmUsersModules";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#merge(com.rc.oss.vo.TAdmUsersModules)
	 */
	public TAdmUsersModules merge(TAdmUsersModules detachedInstance) {
		log.debug("merging TAdmUsersModules instance");
		try {
			TAdmUsersModules result = (TAdmUsersModules) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#attachDirty(com.rc.oss.vo.TAdmUsersModules)
	 */
	public void attachDirty(TAdmUsersModules instance) {
		log.debug("attaching dirty TAdmUsersModules instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersModulesDAOItf#attachClean(com.rc.oss.vo.TAdmUsersModules)
	 */
	public void attachClean(TAdmUsersModules instance) {
		log.debug("attaching clean TAdmUsersModules instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmUsersModulesDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmUsersModulesDAOItf) ctx.getBean("TAdmUsersModulesDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}

	public void deleteAll(List<TAdmUsersModules> persistentInstances) {
		// TODO Auto-generated method stub
		log.debug("deleting TAdmUsersModules instance");
		try {
			getHibernateTemplate().deleteAll(persistentInstances);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
}