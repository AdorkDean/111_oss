package com.rc.portal.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.page.PageWraper;


/**  
 * @Title: OpenSqlDAO.java
 * @Description: 针对底层数据库的公共dao接口，主要是通过Map携带参数，返回包含Map的list或者一个Object，
 * 				 省去每次新建实体对象的过程，在分页查询时开发人员只需写好sql语句，在前台显示即可。
 * @author yinbinhome@163.com  
 * @date 2012-6-27 下午10:01:41
 * @version V1.0  
 */

public interface OpenSqlDAO {
	/**
	 * 传入Map 返回包含Map的List
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public List selectForListByMap(Map map,String querySql);
	
	/**
	 * 传入Map 返回一个对象，可以使一个Bean也可以是一个Integer，Long
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectForObjectByMap(Map map,String querySql);

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param map
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap(Map map,String sql_count,String sql_record,Integer page,Integer pagesize);
	
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * @param map
	 * @param updatesql sqlmap的id
	 * @return
	 * @throws SQLException 
	 */
	public int updateByMap(Map map, String updatesql) throws SQLException;
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param List含有Map的list 
	 * @param insertsql 的id
	 * @return
	 * @throws SQLException
	 */
	public String batchInsertByList(List<Object> list, String insertsql) throws SQLException;
	
	
	//====================================================================================================================
	//====================================================================================================================
	//====================================================================================================================	
	/**
	 * 传入Map 返回包含Map的List
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public List selectForListByMap_drug(Map map,String querySql);
	
	/**
	 * 传入Map 返回一个对象，可以使一个Bean也可以是一个Integer，Long
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectForObjectByMap_drug(Map map,String querySql);

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param map
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap_drug(Map map,String sql_count,String sql_record,Integer page,Integer pagesize);
	
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * @param map
	 * @param updatesql sqlmap的id
	 * @return
	 * @throws SQLException 
	 */
	public int updateByMap_drug(Map map, String updatesql) throws SQLException;
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param List含有Map的list 
	 * @param insertsql 的id
	 * @return
	 * @throws SQLException
	 */
	public String batchInsertByList_drug(List<Object> list, String insertsql) throws SQLException;
	
	
	//====================================================================================================================
	//====================================================================================================================
	//====================================================================================================================	
	/**
	 * 传入Map 返回包含Map的List
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public List selectForListByMap_public(Map map,String querySql);
	
	/**
	 * 传入Map 返回一个对象，可以使一个Bean也可以是一个Integer，Long
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectForObjectByMap_public(Map map,String querySql);

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param map
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap_public(Map map,String sql_count,String sql_record,Integer page,Integer pagesize);
	
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * @param map
	 * @param updatesql sqlmap的id
	 * @return
	 * @throws SQLException 
	 */
	public int updateByMap_public(Map map, String updatesql) throws SQLException;
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param List含有Map的list 
	 * @param insertsql 的id
	 * @return
	 * @throws SQLException
	 */
	public String batchInsertByList_public(List<Object> list, String insertsql) throws SQLException;
	public String newBatchInsertByList_drug(List<Object> list, String insertsql) throws SQLException;
	
	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param object 任意队形的参数
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap_drug(Object object,String sql_count,String sql_record,Integer page,Integer pagesize);
	
	/**
	 * 传入對象 返回一个对象(返回可一个实体类对象或者List集合)
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectObjectByObject(Object object,String querySql);
	

	/**
	 * 根据对象查找集合
	 * @param object 任意类型
	 * @param querySql 需要执行的sql_map ID
	 * @return 任意对象 依据sql_map定义类型集合
	 */
	public Object selectListByObject(Object object,String querySql);
}
