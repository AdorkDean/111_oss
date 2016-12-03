package com.rc.oss.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rc.oss.dao.TAdmUserPostDAOItf;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmUserPost;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAdmUserPost entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rc.oss.vo.TAdmUserPost
 * @author MyEclipse Persistence Tools
 */

public class TAdmUserPostDAO extends HibernateDaoSupport implements TAdmUserPostDAOItf {
	private static final Log log = LogFactory.getLog(TAdmUserPostDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#save(com.rc.oss.vo.TAdmUserPost)
	 */
	public void save(TAdmUserPost transientInstance) {
		log.debug("saving TAdmUserPost instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#delete(com.rc.oss.vo.TAdmUserPost)
	 */
	public void delete(TAdmUserPost persistentInstance) {
		log.debug("deleting TAdmUserPost instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#findById(java.lang.Long)
	 */
	public TAdmUserPost findById(java.lang.Long id) {
		log.debug("getting TAdmUserPost instance with id: " + id);
		try {
			TAdmUserPost instance = (TAdmUserPost) getHibernateTemplate().get("com.rc.oss.vo.TAdmUserPost", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#findByExample(com.rc.oss.vo.TAdmUserPost)
	 */
	public List findByExample(TAdmUserPost instance) {
		log.debug("finding TAdmUserPost instance by example");
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
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmUserPost instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAdmUserPost as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#findAll()
	 */
	public List findAll() {
		log.debug("finding all TAdmUserPost instances");
		try {
			String queryString = "from TAdmUserPost";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#merge(com.rc.oss.vo.TAdmUserPost)
	 */
	public TAdmUserPost merge(TAdmUserPost detachedInstance) {
		log.debug("merging TAdmUserPost instance");
		try {
			TAdmUserPost result = (TAdmUserPost) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#attachDirty(com.rc.oss.vo.TAdmUserPost)
	 */
	public void attachDirty(TAdmUserPost instance) {
		log.debug("attaching dirty TAdmUserPost instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rc.oss.dao.impl.TAdmUserPostDAOItf#attachClean(com.rc.oss.vo.TAdmUserPost)
	 */
	public void attachClean(TAdmUserPost instance) {
		log.debug("attaching clean TAdmUserPost instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAdmUserPostDAOItf getFromApplicationContext(ApplicationContext ctx) {
		return (TAdmUserPostDAOItf) ctx.getBean("TAdmUserPostDAO");
	}
	
	public Criteria getCriteria(){
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TAdmModules.class);
		return criteria;
	}

	public void deleteAll(List<TAdmUserPost> persistentInstances) {
		// TODO Auto-generated method stub
		log.debug("deleting TAdmUserPost instance");
		try {
			getHibernateTemplate().deleteAll(persistentInstances);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
}