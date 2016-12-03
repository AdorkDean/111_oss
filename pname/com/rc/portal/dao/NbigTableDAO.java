package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.NbigTable;
import com.rc.portal.vo.NbigTableExample;

public interface NbigTableDAO {
    int countByExample(NbigTableExample example) throws SQLException;

    int deleteByExample(NbigTableExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(NbigTable record) throws SQLException;

    Long insertSelective(NbigTable record) throws SQLException;

    List selectByExample(NbigTableExample example) throws SQLException;

    NbigTable selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(NbigTable record, NbigTableExample example) throws SQLException;

    int updateByExample(NbigTable record, NbigTableExample example) throws SQLException;

    int updateByPrimaryKeySelective(NbigTable record) throws SQLException;

    int updateByPrimaryKey(NbigTable record) throws SQLException;


}
