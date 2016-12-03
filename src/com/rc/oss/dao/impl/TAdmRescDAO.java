package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmRescDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmResc;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmResc entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmResc
 * @author MyEclipse Persistence Tools
 */

public class TAdmRescDAO extends HibernateDaoSupport implements TAdmRescDAOItf {
	private static final Log log = LogFactory.getLog(TAdmRescDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String RES_TYPE = "resType";
	public static final String RES_STRING = "resString";
	public static final String PRIORITY = "priority";
	public static final String DESCN = "descn";
	public static final String EDITABLE = "editable";
	public static final String WEBID = "webid";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#save(com.rc.oss.vo.TAdmResc)
	 */
	public void save(TAdmResc transientInstance) {
		log.debug("saving TAdmResc instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#delete(com.rc.oss.vo.TAdmResc)
	 */
	public void delete(TAdmResc persistentInstance) {
		log.debug("deleting TAdmResc instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findById(java.lang.Long)
	 */
	public TAdmResc findById(java.lang.Long id) {
		log.debug("getting TAdmResc instance with id: " + id);
		try {
			TAdmResc instance = (TAdmResc) getHibernateTemplate().get("com.rc.oss.vo.TAdmResc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByExample(com.rc.oss.vo.TAdmResc)
	 */
	public List findByExample(TAdmResc instance) {
		log.debug("finding TAdmResc instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmResc instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmResc as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByName(java.lang.Object)
	 */
	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByResType(java.lang.Object)
	 */
	public List findByResType(Object resType) {
		return findByProperty(RES_TYPE, resType);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByResString(java.lang.Object)
	 */
	public List findByResString(Object resString) {
		return findByProperty(RES_STRING, resString);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByPriority(java.lang.Object)
	 */
	public List findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByDescn(java.lang.Object)
	 */
	public List findByDescn(Object descn) {
		return findByProperty(DESCN, descn);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByEditable(java.lang.Object)
	 */
	public List findByEditable(Object editable) {
		return findByProperty(EDITABLE, editable);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findByWebid(java.lang.Object)
	 */
	public List findByWebid(Object webid) {
		return findByProperty(WEBID, webid);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmResc instances");
		try {
			String queryString = "from TAdmResc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#merge(com.rc.oss.vo.TAdmResc)
	 */
	public TAdmResc merge(TAdmResc detachedInstance) {
		log.debug("merging TAdmResc instance");
		try {
			TAdmResc result = (TAdmResc) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#attachDirty(com.rc.oss.vo.TAdmResc)
	 */
	public void attachDirty(TAdmResc instance) {
		log.debug("attaching dirty TAdmResc instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRescDAOItf#attachClean(com.rc.oss.vo.TAdmResc)
	 */
	public void attachClean(TAdmResc instance) {
		log.debug("attaching clean TAdmResc instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmRescDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmRescDAOItf) ctx.getBean("TAdmRescDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}
}