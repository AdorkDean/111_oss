package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.BaseModel;
import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.OpenSqlDAO;

/**
 * @Title: OpenSqlDAO.java
 * @Description: 针对底层数据库的公共dao接口，主要是通过Map携带参数，返回包含Map的list或者一个Object，
 *               省去每次新建实体对象的过程，在分页查询时开发人员只需写好sql语句，在前台显示即可。
 * @author yinbinhome@163.com
 * @date 2012-6-27 下午10:01:41
 * @version V1.0
 */

public class OpenSqlDAOImpl implements OpenSqlDAO {
	private SqlMapClient sqlMapClient_drug;
	private SqlMapClient sqlMapClient_public;
	private SqlMapClient sqlMapClient;
	private SqlMapClient sqlMapClient_art;
	private Logger log=Logger.getLogger(getClass());
	

	public SqlMapClient getSqlMapClient_drug() {
		return sqlMapClient_drug;
	}

	public void setSqlMapClient_drug(SqlMapClient sqlMapClient_drug) {
		this.sqlMapClient_drug = sqlMapClient_drug;
	}

	public SqlMapClient getSqlMapClient_public() {
		return sqlMapClient_public;
	}

	public void setSqlMapClient_public(SqlMapClient sqlMapClient_public) {
		this.sqlMapClient_public = sqlMapClient_public;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public OpenSqlDAOImpl() {
		super();
	}

	public OpenSqlDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * 传入Map 返回包含Map的List
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public List selectForListByMap(Map map, String querySql) {
		log.info("[OpenSqlDAOImpl] 查找返回List :"+querySql);
		List list = new ArrayList();
		try {
			list = (List) sqlMapClient_drug.queryForList(querySql, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回List异常");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 传入Map 返回一个对象，可以使一个Bean也可以是一个Integer，Long
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectForObjectByMap(Map map, String querySql) {
		log.info("[OpenSqlDAOImpl] 查找返回Object :"+querySql);
		Object object = null;
		try {
			object = sqlMapClient_drug.queryForObject(querySql, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回Object异常");
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param map
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap(Map map, String sql_count, String sql_record,Integer page,Integer pagesize) {
		log.info("[OpenSqlDAOImpl] 查找返回List :"+sql_count+" | "+sql_record+" | "+page +" | "+pagesize);
    	PageWraper pw=null;
    	Integer count=0;
        PageInfo pageInfo=new PageInfo();
        pageInfo.setPage(page);
        pageInfo.setPageSize(pagesize);
        map.put("pageInfo", pageInfo);
		try {
			count = (Integer)sqlMapClient_drug.queryForObject(sql_count, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回总记录数异常");
			e.printStackTrace();
		}
        List list=new ArrayList();;
		try {
			list = (List)sqlMapClient_drug.queryForList(sql_record, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回List异常");
			e.printStackTrace();
		}

		pw=PageManager.getPageWraper(pageInfo, list, count);
        return pw;
	}

	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param map
	 * @param updatesql
	 *            sqlmap的id
	 * @return
	 * @throws SQLException
	 */
	public int updateByMap(Map map, String updatesql) throws SQLException {

		int rows = sqlMapClient_drug.update(updatesql, map);
		return rows;
	}

	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param List含有Map的list 
	 * @param insertsql 的id
	 * @return
	 * @throws SQLException
	 */
	public String batchInsertByList(List<Object> list, String insertsql) throws SQLException {
		String reStr="";
		if (list == null || list.size() == 0 || list.size() > 500) {
			log.info("[OpenSql]批量提交数据错误");
			reStr="检查List，是否为空或者大小超过500";
			return reStr;
		}
		log.info("[OpenSql]此次批量提交数据 " + list.size() + " 条");
		try {
			sqlMapClient_drug.startTransaction();
			sqlMapClient_drug.startBatch();

			for (Object obj : list) {
				sqlMapClient_drug.insert(insertsql, obj);
			}
			sqlMapClient_drug.executeBatch();
			sqlMapClient_drug.commitTransaction();

		} catch (Exception e) {
			sqlMapClient_drug.getCurrentConnection().rollback();
			log.info("[OpenSql]批量提交发生异常回滚");
			reStr="[OpenSql]批量提交发生异常回滚";
		} finally {
			sqlMapClient_drug.endTransaction();
		}
		reStr="成功提交 "+list.size()+" 条记录";
		return reStr;
	}

	
	//===========================================================================================
	//===========================================================================================
	//===========================================================================================	
	
	/**
	 * 传入Map 返回包含Map的List
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public List selectForListByMap_drug(Map map, String querySql) {
		log.info("[OpenSqlDAOImpl] 查找返回List :"+querySql);
		List list = new ArrayList();
		try {
			list = (List) sqlMapClient_drug.queryForList(querySql, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回List异常");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 传入Map 返回一个对象，可以使一个Bean也可以是一个Integer，Long
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectForObjectByMap_drug(Map map, String querySql) {
		log.info("[OpenSqlDAOImpl] 查找返回Object :"+querySql);
		Object object = null;
		try {
			object = sqlMapClient_drug.queryForObject(querySql, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回Object异常");
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param map
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap_drug(Map map, String sql_count, String sql_record,Integer page,Integer pagesize) {
		log.info("[OpenSqlDAOImpl] 查找返回List :"+sql_count+" | "+sql_record+" | "+page +" | "+pagesize);
    	PageWraper pw=null;
    	Integer count=0;
        PageInfo pageInfo=new PageInfo();
        pageInfo.setPage(page);
        pageInfo.setPageSize(pagesize);
        pageInfo.setStart(page, pagesize);
        pageInfo.setEnd(page, pagesize);
        map.put("pageInfo", pageInfo);
		try {
			count = (Integer)sqlMapClient_drug.queryForObject(sql_count, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回总记录数异常");
			e.printStackTrace();
		}
        List list=new ArrayList();;
		try {
			list = (List)sqlMapClient_drug.queryForList(sql_record, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回List异常");
			e.printStackTrace();
		}

		pw=PageManager.getPageWraper(pageInfo, list, count);
        return pw;
	}

	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param map
	 * @param updatesql
	 *            sqlmap的id
	 * @return
	 * @throws SQLException
	 */
	public int updateByMap_drug(Map map, String updatesql) throws SQLException {

		int rows = sqlMapClient.update(updatesql, map);
		return rows;
	}

	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param List含有Map的list 
	 * @param insertsql 的id
	 * @return
	 * @throws SQLException
	 */
	public String batchInsertByList_drug(List<Object> list, String insertsql) throws SQLException {
		String reStr="";
		if (list == null || list.size() == 0 || list.size() > 500) {
			log.info("[OpenSql]批量提交数据错误");
			reStr="检查List，是否为空或者大小超过500";
			return reStr;
		}
		log.info("[OpenSql]此次批量提交数据 " + list.size() + " 条");
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();

			for (Object obj : list) {
				sqlMapClient.insert(insertsql, obj);
			}
			sqlMapClient.executeBatch();
			sqlMapClient.commitTransaction();

		} catch (Exception e) {
			sqlMapClient.getCurrentConnection().rollback();
			log.info("[OpenSql]批量提交发生异常回滚");
			reStr="[OpenSql]批量提交发生异常回滚";
		} finally {
			sqlMapClient.endTransaction();
		}
		reStr="成功提交 "+list.size()+" 条记录";
		return reStr;
	}
	//===========================================================================================
	//===========================================================================================
	//===========================================================================================	
	
	/**
	 * 传入Map 返回包含Map的List
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public List selectForListByMap_public(Map map, String querySql) {
		log.info("[OpenSqlDAOImpl] 查找返回List :"+querySql);
		List list = new ArrayList();
		try {
			list = (List) sqlMapClient_public.queryForList(querySql, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回List异常");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 传入Map 返回一个对象，可以使一个Bean也可以是一个Integer，Long
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectForObjectByMap_public(Map map, String querySql) {
		log.info("[OpenSqlDAOImpl] 查找返回Object :"+querySql);
		Object object = null;
		try {
			object = sqlMapClient_public.queryForObject(querySql, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回Object异常");
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param map
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap_public(Map map, String sql_count, String sql_record,Integer page,Integer pagesize) {
		log.info("[OpenSqlDAOImpl] 查找返回List :"+sql_count+" | "+sql_record+" | "+page +" | "+pagesize);
		PageWraper pw=null;
		Integer count=0;
		PageInfo pageInfo=new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setPageSize(pagesize);
		pageInfo.setStart(page, pagesize);
		pageInfo.setEnd(page, pagesize);
		map.put("pageInfo", pageInfo);
		try {
			count = (Integer)sqlMapClient_public.queryForObject(sql_count, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回总记录数异常");
			e.printStackTrace();
		}
		List list=new ArrayList();;
		try {
			list = (List)sqlMapClient_public.queryForList(sql_record, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回List异常");
			e.printStackTrace();
		}
		
		pw=PageManager.getPageWraper(pageInfo, list, count);
		return pw;
	}

	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param map
	 * @param updatesql
	 *            sqlmap的id
	 * @return
	 * @throws SQLException
	 */
	public int updateByMap_public(Map map, String updatesql) throws SQLException {

		int rows = sqlMapClient_public.update(updatesql, map);
		return rows;
	}

	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param List含有Map的list 
	 * @param insertsql 的id
	 * @return
	 * @throws SQLException
	 */
	public String batchInsertByList_public(List<Object> list, String insertsql) throws SQLException {
		String reStr="";
		if (list == null || list.size() == 0 || list.size() > 500) {
			log.info("[OpenSql]批量提交数据错误");
			reStr="检查List，是否为空或者大小超过500";
			return reStr;
		}
		log.info("[OpenSql]此次批量提交数据 " + list.size() + " 条");
		try {
			sqlMapClient_public.startTransaction();
			sqlMapClient_public.startBatch();

			for (Object obj : list) {
				sqlMapClient_public.insert(insertsql, obj);
			}
			sqlMapClient_public.executeBatch();
			sqlMapClient_public.commitTransaction();

		} catch (Exception e) {
			sqlMapClient_public.getCurrentConnection().rollback();
			log.info("[OpenSql]批量提交发生异常回滚");
			reStr="[OpenSql]批量提交发生异常回滚";
		} finally {
			sqlMapClient_public.endTransaction();
		}
		reStr="成功提交 "+list.size()+" 条记录";
		return reStr;
	}
	public String newBatchInsertByList_drug(List<Object> list, String insertsql) throws SQLException {
		String reStr="";
		if (list == null || list.size() == 0) {
			log.info("[OpenSql]批量提交数据错误");
			reStr="检查List，是否为空";
			return reStr;
		}
		log.info("[OpenSql]此次批量提交数据 " + list.size() + " 条");
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();
			sqlMapClient.insert(insertsql, list);
			sqlMapClient.executeBatch();
			sqlMapClient.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			sqlMapClient.getCurrentConnection().rollback();
			log.info("[OpenSql]批量提交发生异常回滚");
			reStr="[OpenSql]批量提交发生异常回滚";
		} finally {
			sqlMapClient.endTransaction();
		}
		reStr="成功提交 "+list.size()+" 条记录";
		return reStr;
	}
	public SqlMapClient getSqlMapClient_art() {
		return sqlMapClient_art;
	}

	public void setSqlMapClient_art(SqlMapClient sqlMapClientArt) {
		sqlMapClient_art = sqlMapClientArt;
	}

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param object 任意队形的参数
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap_drug(Object object,String sql_count,String sql_record,Integer page,Integer pagesize){
		
		log.info("[OpenSqlDAOImpl] 查找返回List :"+sql_count+" | "+sql_record+" | "+page +" | "+pagesize);
    	PageWraper pw=null;
    	Integer count=0;
        PageInfo pageInfo=new PageInfo();
        pageInfo.setPage(page);
        pageInfo.setPageSize(pagesize);
        pageInfo.setStart(page, pagesize);
        pageInfo.setEnd(page, pagesize);
        BaseModel BaseModel = (BaseModel)object;
        BaseModel.setPageInfo(pageInfo);
		try {
			count = (Integer)sqlMapClient_drug.queryForObject(sql_count, object);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回总记录数异常");
			e.printStackTrace();
		}
        List list=new ArrayList();;
		try {
			list = (List)sqlMapClient_drug.queryForList(sql_record, object);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回List异常");
			e.printStackTrace();
		}

		pw=PageManager.getPageWraper(pageInfo, list, count);
        return pw;
	}
	
	/**
	 * 传入對象 返回一个对象(返回可一个实体类对象或者List集合)
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectObjectByObject(Object object,String querySql){
		log.info("[selectObjectByObject] 查找返回Object :"+querySql);
		Object result = null;
		try {
			result = sqlMapClient_public.queryForObject(querySql, object);
		} catch (SQLException e) {
			log.info("[selectObjectByObject] 查找返回Object异常");
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 根据对象查找集合
	 * @param object 任意类型
	 * @param querySql 需要执行的sql_map ID
	 * @return 任意对象 依据sql_map定义类型集合
	 */
	public Object selectListByObject(Object object,String querySql){
		log.info("[selectListByObject] 查找返回Object :"+querySql);
		Object result = null;
		try {
			result = sqlMapClient_public.queryForList(querySql, object);
		} catch (SQLException e) {
			log.info("[selectListByObject] 查找返回Object异常");
			e.printStackTrace();
		}
		return result;
	}
}
