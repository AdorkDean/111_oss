package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.TReturnItemDAO;
import com.rc.portal.service.TReturnItemManager;
import com.rc.portal.vo.TReturnItem;
import com.rc.portal.vo.TReturnItemExample;

/**
 * 退换货项service实现
 * 
 * @author user00
 * @createTime 2015-8-27 下午8:44:05
 */
public class TReturnItemManagerImpl implements TReturnItemManager {

	private TReturnItemDAO treturnitemdao;

	public TReturnItemManagerImpl() {
		super();
	}

	public void setTreturnitemdao(TReturnItemDAO treturnitemdao) {
		this.treturnitemdao = treturnitemdao;
	}

	public TReturnItemDAO getTreturnitemdao() {
		return this.treturnitemdao;
	}

	public int countByExample(TReturnItemExample example) throws SQLException {
		return treturnitemdao.countByExample(example);
	}

	public int deleteByExample(TReturnItemExample example) throws SQLException {
		return treturnitemdao.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Long id) throws SQLException {
		return treturnitemdao.deleteByPrimaryKey(id);
	}

	public Long insert(TReturnItem record) throws SQLException {
		return treturnitemdao.insert(record);
	}

	public Long insertSelective(TReturnItem record) throws SQLException {
		return treturnitemdao.insertSelective(record);
	}

	public List selectByExample(TReturnItemExample example) throws SQLException {
		return treturnitemdao.selectByExample(example);
	}

	public TReturnItem selectByPrimaryKey(Long id) throws SQLException {
		return treturnitemdao.selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(TReturnItem record, TReturnItemExample example) throws SQLException {
		return treturnitemdao.updateByExampleSelective(record, example);
	}

	public int updateByExample(TReturnItem record, TReturnItemExample example) throws SQLException {
		return treturnitemdao.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(TReturnItem record) throws SQLException {
		return treturnitemdao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TReturnItem record) throws SQLException {
		return treturnitemdao.updateByPrimaryKey(record);
	}

	/**
	 * 根据退换货id查退换货项
	 * @throws SQLException 
	 */
	@Override
	public List<TReturnItem> selectListByReturnId(Long returnId) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("returnId", returnId);
		List<TReturnItem> list  = treturnitemdao.selectListByReturnId(map);
		return list;
	}

}
