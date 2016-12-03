package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.CPositionDAO;
import com.rc.portal.dao.CPositionListDAO;
import com.rc.portal.service.CPositionManager;
import com.rc.portal.vo.CPosition;
import com.rc.portal.vo.CPositionExample;

public class CPositionManagerImpl  implements CPositionManager {

	 private CPositionListDAO cpositionlistdao;
	    private CPositionDAO cpositiondao;
	    
    public CPositionListDAO getCpositionlistdao() {
			return cpositionlistdao;
		}
		public void setCpositionlistdao(CPositionListDAO cpositionlistdao) {
			this.cpositionlistdao = cpositionlistdao;
		}
	public CPositionManagerImpl() {
        super();
    }
    public void  setCpositiondao(CPositionDAO cpositiondao){
        this.cpositiondao=cpositiondao;
    }
    public CPositionDAO getCpositiondao(){
        return this.cpositiondao;
    }
    public     int countByExample(CPositionExample example) throws SQLException{
        return cpositiondao. countByExample( example);
    }

    public     int deleteByExample(CPositionExample example) throws SQLException{
        return cpositiondao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Integer id) throws SQLException{
        return cpositiondao. deleteByPrimaryKey( id);
    }

    public     Integer insert(CPosition record) throws SQLException{
        return cpositiondao. insert( record);
    }

    public     Integer insertSelective(CPosition record) throws SQLException{
        return cpositiondao. insertSelective( record);
    }

    public     List selectByExample(CPositionExample example) throws SQLException{
        return cpositiondao. selectByExample( example);
    }

    public     CPosition selectByPrimaryKey(Integer id) throws SQLException{
        return cpositiondao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(CPosition record, CPositionExample example) throws SQLException{
        return cpositiondao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(CPosition record, CPositionExample example) throws SQLException{
        return cpositiondao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(CPosition record) throws SQLException{
        return cpositiondao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(CPosition record) throws SQLException{
        return cpositiondao. updateByPrimaryKey( record);
    }



}
