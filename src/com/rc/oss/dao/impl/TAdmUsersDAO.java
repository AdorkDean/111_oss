package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmUsersDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmUsers;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmUsers entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmUsers
 * @author MyEclipse Persistence Tools
 */

public class TAdmUsersDAO extends HibernateDaoSupport implements TAdmUsersDAOItf {
	private static final Log log = LogFactory.getLog(TAdmUsersDAO.class);
	// property constants
	public static final String LOGIN_NAME = "loginName";
	public static final String NAME = "name";
	public static final String DEPARTMENT = "department";
	public static final String PASSWORD = "password";
	public static final String TELEPHONE = "telephone";
	public static final String EMAIL = "email";
	public static final String USER_LEVEL = "userLevel";
	public static final String STATUS = "status";
	public static final String ALLOW_DELETE = "allowDelete";
	public static final String IS_ADMIN_USER = "isAdminUser";
	public static final String WEBID = "webid";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#save(com.rc.oss.vo.TAdmUsers)
	 */
	public void save(TAdmUsers transientInstance) {
		log.debug("saving TAdmUsers instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#delete(com.rc.oss.vo.TAdmUsers)
	 */
	public void delete(TAdmUsers persistentInstance) {
		log.debug("deleting TAdmUsers instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findById(java.lang.Long)
	 */
	public TAdmUsers findById(java.lang.Long id) {
		log.debug("getting TAdmUsers instance with id: " + id);
		try {
			TAdmUsers instance = (TAdmUsers) getHibernateTemplate().get("com.rc.oss.vo.TAdmUsers", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByExample(com.rc.oss.vo.TAdmUsers)
	 */
	public List findByExample(TAdmUsers instance) {
		log.debug("finding TAdmUsers instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmUsers instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmUsers as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByLoginName(java.lang.Object)
	 */
	public List findByLoginName(Object loginName) {
		return findByProperty(LOGIN_NAME, loginName);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByName(java.lang.Object)
	 */
	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByDepartment(java.lang.Object)
	 */
	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByPassword(java.lang.Object)
	 */
	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByTelephone(java.lang.Object)
	 */
	public List findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByEmail(java.lang.Object)
	 */
	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByUserLevel(java.lang.Object)
	 */
	public List findByUserLevel(Object userLevel) {
		return findByProperty(USER_LEVEL, userLevel);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByAllowDelete(java.lang.Object)
	 */
	public List findByAllowDelete(Object allowDelete) {
		return findByProperty(ALLOW_DELETE, allowDelete);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByIsAdminUser(java.lang.Object)
	 */
	public List findByIsAdminUser(Object isAdminUser) {
		return findByProperty(IS_ADMIN_USER, isAdminUser);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findByWebid(java.lang.Object)
	 */
	public List findByWebid(Object webid) {
		return findByProperty(WEBID, webid);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmUsers instances");
		try {
			String queryString = "from TAdmUsers";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#merge(com.rc.oss.vo.TAdmUsers)
	 */
	public TAdmUsers merge(TAdmUsers detachedInstance) {
		log.debug("merging TAdmUsers instance");
		try {
			TAdmUsers result = (TAdmUsers) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#attachDirty(com.rc.oss.vo.TAdmUsers)
	 */
	public void attachDirty(TAdmUsers instance) {
		log.debug("attaching dirty TAdmUsers instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUsersDAOItf#attachClean(com.rc.oss.vo.TAdmUsers)
	 */
	public void attachClean(TAdmUsers instance) {
		log.debug("attaching clean TAdmUsers instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmUsersDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmUsersDAOItf) ctx.getBean("TAdmUsersDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}
}