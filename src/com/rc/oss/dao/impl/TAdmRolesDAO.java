package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmRolesDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmRoles;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmRoles entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmRoles
 * @author MyEclipse Persistence Tools
 */

public class TAdmRolesDAO extends HibernateDaoSupport implements TAdmRolesDAOItf {
	private static final Log log = LogFactory.getLog(TAdmRolesDAO.class);
	// property constants
	public static final String ROLE_NAME = "roleName";
	public static final String ROLE_DESC = "roleDesc";
	public static final String STATUS = "status";
	public static final String LAST_UPDATE_BY = "lastUpdateBy";
	public static final String EDITABLE = "editable";
	public static final String WEBID = "webid";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#save(com.rc.oss.vo.TAdmRoles)
	 */
	public void save(TAdmRoles transientInstance) {
		log.debug("saving TAdmRoles instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#delete(com.rc.oss.vo.TAdmRoles)
	 */
	public void delete(TAdmRoles persistentInstance) {
		log.debug("deleting TAdmRoles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findById(java.lang.Long)
	 */
	public TAdmRoles findById(java.lang.Long id) {
		log.debug("getting TAdmRoles instance with id: " + id);
		try {
			TAdmRoles instance = (TAdmRoles) getHibernateTemplate().get("com.rc.oss.vo.TAdmRoles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findByExample(com.rc.oss.vo.TAdmRoles)
	 */
	public List findByExample(TAdmRoles instance) {
		log.debug("finding TAdmRoles instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmRoles instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmRoles as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findByRoleName(java.lang.Object)
	 */
	public List findByRoleName(Object roleName) {
		return findByProperty(ROLE_NAME, roleName);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findByRoleDesc(java.lang.Object)
	 */
	public List findByRoleDesc(Object roleDesc) {
		return findByProperty(ROLE_DESC, roleDesc);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findByLastUpdateBy(java.lang.Object)
	 */
	public List findByLastUpdateBy(Object lastUpdateBy) {
		return findByProperty(LAST_UPDATE_BY, lastUpdateBy);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findByEditable(java.lang.Object)
	 */
	public List findByEditable(Object editable) {
		return findByProperty(EDITABLE, editable);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findByWebid(java.lang.Object)
	 */
	public List findByWebid(Object webid) {
		return findByProperty(WEBID, webid);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmRoles instances");
		try {
			String queryString = "from TAdmRoles";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#merge(com.rc.oss.vo.TAdmRoles)
	 */
	public TAdmRoles merge(TAdmRoles detachedInstance) {
		log.debug("merging TAdmRoles instance");
		try {
			TAdmRoles result = (TAdmRoles) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#attachDirty(com.rc.oss.vo.TAdmRoles)
	 */
	public void attachDirty(TAdmRoles instance) {
		log.debug("attaching dirty TAdmRoles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmRolesDAOItf#attachClean(com.rc.oss.vo.TAdmRoles)
	 */
	public void attachClean(TAdmRoles instance) {
		log.debug("attaching clean TAdmRoles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmRolesDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmRolesDAOItf) ctx.getBean("TAdmRolesDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}
}