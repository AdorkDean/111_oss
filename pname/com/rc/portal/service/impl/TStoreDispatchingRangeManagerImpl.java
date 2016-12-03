package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.rc.portal.dao.TStoreDispatchingRangeDAO;
import com.rc.portal.service.TStoreDispatchingRangeManager;
import com.rc.portal.vo.TStoreDispatchingRange;
import com.rc.portal.vo.TStoreDispatchingRangeExample;

public class TStoreDispatchingRangeManagerImpl  implements TStoreDispatchingRangeManager {

    private TStoreDispatchingRangeDAO tstoredispatchingrangedao;

    public TStoreDispatchingRangeManagerImpl() {
        super();
    }
    public void  setTstoredispatchingrangedao(TStoreDispatchingRangeDAO tstoredispatchingrangedao){
        this.tstoredispatchingrangedao=tstoredispatchingrangedao;
    }
    public TStoreDispatchingRangeDAO getTstoredispatchingrangedao(){
        return this.tstoredispatchingrangedao;
    }
    public     int countByExample(TStoreDispatchingRangeExample example) throws SQLException{
        return tstoredispatchingrangedao. countByExample( example);
    }

    public     int deleteByExample(TStoreDispatchingRangeExample example) throws SQLException{
        return tstoredispatchingrangedao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tstoredispatchingrangedao. deleteByPrimaryKey( id);
    }

    public     Long insert(TStoreDispatchingRange record) throws SQLException{
        return tstoredispatchingrangedao. insert( record);
    }

    public     Long insertSelective(TStoreDispatchingRange record) throws SQLException{
        return tstoredispatchingrangedao. insertSelective( record);
    }

    public     List selectByExample(TStoreDispatchingRangeExample example) throws SQLException{
        return tstoredispatchingrangedao. selectByExample( example);
    }

    public     TStoreDispatchingRange selectByPrimaryKey(Long id) throws SQLException{
        return tstoredispatchingrangedao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TStoreDispatchingRange record, TStoreDispatchingRangeExample example) throws SQLException{
        return tstoredispatchingrangedao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TStoreDispatchingRange record, TStoreDispatchingRangeExample example) throws SQLException{
        return tstoredispatchingrangedao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TStoreDispatchingRange record) throws SQLException{
        return tstoredispatchingrangedao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TStoreDispatchingRange record) throws SQLException{
        return tstoredispatchingrangedao. updateByPrimaryKey( record);
    }
    
	public int saveStore(TStoreDispatchingRange store) throws Exception {
		Integer flag=0;
		if(store!=null){
			if(store.getId()!=null&&store.getId()!=-1){
				flag=tstoredispatchingrangedao.updateByPrimaryKeySelective(store);
			}else{
				store.setCreateDt(new Date());
				flag=tstoredispatchingrangedao.insertSelective(store).intValue();
			}
		}
		return flag;
	}


}
