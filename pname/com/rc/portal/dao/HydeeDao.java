package com.rc.portal.dao;

import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TOrder;

/**
 * 海典dao
 * 
 * @author user00
 * @createTime 2015-9-6 上午10:41:48
 */
public interface HydeeDao {

	/**
	 * 插入海典库
	 * 
	 * @param order
	 * @return
	 */
	boolean insertDstOrderToHD(TOrder order,TMember tMember);

}
