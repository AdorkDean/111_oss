package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmPostDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmPost;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmPost entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmPost
 * @author MyEclipse Persistence Tools
 */

public class TAdmPostDAO extends HibernateDaoSupport implements TAdmPostDAOItf {
	private static final Log log = LogFactory.getLog(TAdmPostDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String REMARK = "remark";
	public static final String ENABLE = "enable";
	public static final String WEBID = "webid";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#save(com.rc.oss.vo.TAdmPost)
	 */
	public void save(TAdmPost transientInstance) {
		log.debug("saving TAdmPost instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#delete(com.rc.oss.vo.TAdmPost)
	 */
	public void delete(TAdmPost persistentInstance) {
		log.debug("deleting TAdmPost instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#findById(java.lang.Long)
	 */
	public TAdmPost findById(java.lang.Long id) {
		log.debug("getting TAdmPost instance with id: " + id);
		try {
			TAdmPost instance = (TAdmPost) getHibernateTemplate().get("com.rc.oss.vo.TAdmPost", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#findByExample(com.rc.oss.vo.TAdmPost)
	 */
	public List findByExample(TAdmPost instance) {
		log.debug("finding TAdmPost instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmPost instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmPost as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#findByName(java.lang.Object)
	 */
	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#findByRemark(java.lang.Object)
	 */
	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#findByEnable(java.lang.Object)
	 */
	public List findByEnable(Object enable) {
		return findByProperty(ENABLE, enable);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#findByWebid(java.lang.Object)
	 */
	public List findByWebid(Object webid) {
		return findByProperty(WEBID, webid);
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmPost instances");
		try {
			String queryString = "from TAdmPost";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#merge(com.rc.oss.vo.TAdmPost)
	 */
	public TAdmPost merge(TAdmPost detachedInstance) {
		log.debug("merging TAdmPost instance");
		try {
			TAdmPost result = (TAdmPost) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#attachDirty(com.rc.oss.vo.TAdmPost)
	 */
	public void attachDirty(TAdmPost instance) {
		log.debug("attaching dirty TAdmPost instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmPostDAOItf#attachClean(com.rc.oss.vo.TAdmPost)
	 */
	public void attachClean(TAdmPost instance) {
		log.debug("attaching clean TAdmPost instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmPostDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmPostDAOItf) ctx.getBean("TAdmPostDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}
}