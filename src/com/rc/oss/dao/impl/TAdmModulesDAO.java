package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmModulesDAOItf;
import com.rc.oss.vo.TAdmModules;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmModules entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmModules
 * @author MyEclipse Persistence Tools
 */

public class TAdmModulesDAO extends HibernateDaoSupport implements TAdmModulesDAOItf{
	private static final Log log = LogFactory.getLog(TAdmModulesDAO.class);
	// property constants
	public static final String MODULE_NAME = "moduleName";
	public static final String MEMO = "memo";
	public static final String MODULE_TYPE = "moduleType";
	public static final String FOR_LOG = "forLog";
	public static final String URL = "url";
	public static final String MODULE_ID = "moduleId";
	public static final String DISPNUM = "dispnum";
	public static final String MODULE_KEY = "moduleKey";
	public static final String EDITABLE = "editable";
	public static final String WEBID = "webid";

	protected void initDao() {
		// do nothing
	}

	public void save(TAdmModules transientInstance) {
		log.debug("saving TAdmModules instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TAdmModules persistentInstance) {
		log.debug("deleting TAdmModules instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TAdmModules findById(java.lang.Long id) {
		log.debug("getting TAdmModules instance with id: " + id);
		try {
			TAdmModules instance = (TAdmModules) getHibernateTemplate().get("com.rc.oss.vo.TAdmModules", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TAdmModules instance) {
		log.debug("finding TAdmModules instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmModules instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmModules as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByModuleName(Object moduleName) {
		return findByProperty(MODULE_NAME, moduleName);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findByModuleType(Object moduleType) {
		return findByProperty(MODULE_TYPE, moduleType);
	}

	public List findByForLog(Object forLog) {
		return findByProperty(FOR_LOG, forLog);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByModuleId(Object moduleId) {
		return findByProperty(MODULE_ID, moduleId);
	}

	public List findByDispnum(Object dispnum) {
		return findByProperty(DISPNUM, dispnum);
	}

	public List findByModuleKey(Object moduleKey) {
		return findByProperty(MODULE_KEY, moduleKey);
	}

	public List findByEditable(Object editable) {
		return findByProperty(EDITABLE, editable);
	}

	public List findByWebid(Object webid) {
		return findByProperty(WEBID, webid);
	}

	public List findAll() {
		log.debug("finding all TAdmModules instances");
		try {
			String queryString = "from TAdmModules";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TAdmModules merge(TAdmModules detachedInstance) {
		log.debug("merging TAdmModules instance");
		try {
			TAdmModules result = (TAdmModules) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TAdmModules instance) {
		log.debug("attaching dirty TAdmModules instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TAdmModules instance) {
		log.debug("attaching clean TAdmModules instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getSession().createCriteria(TAdmModules.class);
		Criterion cron  = Restrictions.like("", "");
		return criteria;
	}
}