package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmOperationLogsDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmOperationLogs;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmOperationLogs entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmOperationLogs
 * @author MyEclipse Persistence Tools
 */

public class TAdmOperationLogsDAO extends HibernateDaoSupport implements TAdmOperationLogsDAOItf {
	private static final Log log = LogFactory.getLog(TAdmOperationLogsDAO.class);
	// property constants
	public static final String MODULE_ID = "moduleId";
	public static final String OPT_TYPE = "optType";
	public static final String OPT_ID = "optId";
	public static final String OBJ_TYPE = "objType";
	public static final String OPERATION = "operation";
	public static final String IP_ADDRESS = "ipAddress";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#save(com.rc.oss.vo.TAdmOperationLogs)
	 */
	public void save(TAdmOperationLogs transientInstance) {
		log.debug("saving TAdmOperationLogs instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#delete(com.rc.oss.vo.TAdmOperationLogs)
	 */
	public void delete(TAdmOperationLogs persistentInstance) {
		log.debug("deleting TAdmOperationLogs instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findById(java.lang.Long)
	 */
	public TAdmOperationLogs findById(java.lang.Long id) {
		log.debug("getting TAdmOperationLogs instance with id: " + id);
		try {
			TAdmOperationLogs instance = (TAdmOperationLogs) getHibernateTemplate().get("com.rc.oss.vo.TAdmOperationLogs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findByExample(com.rc.oss.vo.TAdmOperationLogs)
	 */
	public List findByExample(TAdmOperationLogs instance) {
		log.debug("finding TAdmOperationLogs instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmOperationLogs instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmOperationLogs as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findByModuleId(java.lang.Object)
	 */
	public List findByModuleId(Object moduleId) {
		return findByProperty(MODULE_ID, moduleId);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findByOptType(java.lang.Object)
	 */
	public List findByOptType(Object optType) {
		return findByProperty(OPT_TYPE, optType);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findByOptId(java.lang.Object)
	 */
	public List findByOptId(Object optId) {
		return findByProperty(OPT_ID, optId);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findByObjType(java.lang.Object)
	 */
	public List findByObjType(Object objType) {
		return findByProperty(OBJ_TYPE, objType);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findByOperation(java.lang.Object)
	 */
	public List findByOperation(Object operation) {
		return findByProperty(OPERATION, operation);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findByIpAddress(java.lang.Object)
	 */
	public List findByIpAddress(Object ipAddress) {
		return findByProperty(IP_ADDRESS, ipAddress);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmOperationLogs instances");
		try {
			String queryString = "from TAdmOperationLogs";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#merge(com.rc.oss.vo.TAdmOperationLogs)
	 */
	public TAdmOperationLogs merge(TAdmOperationLogs detachedInstance) {
		log.debug("merging TAdmOperationLogs instance");
		try {
			TAdmOperationLogs result = (TAdmOperationLogs) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#attachDirty(com.rc.oss.vo.TAdmOperationLogs)
	 */
	public void attachDirty(TAdmOperationLogs instance) {
		log.debug("attaching dirty TAdmOperationLogs instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmOperationLogsDAOItf#attachClean(com.rc.oss.vo.TAdmOperationLogs)
	 */
	public void attachClean(TAdmOperationLogs instance) {
		log.debug("attaching clean TAdmOperationLogs instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmOperationLogsDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmOperationLogsDAOItf) ctx.getBean("TAdmOperationLogsDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}
}