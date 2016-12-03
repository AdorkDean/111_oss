package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TSubject;
import com.rc.portal.vo.TSubjectExample;

public interface TSubjectDAO {
    int countByExample(TSubjectExample example) throws SQLException;

    int deleteByExample(TSubjectExample example) throws SQLException;

    int deleteByPrimaryKey(Integer id) throws SQLException;

    int insert(TSubject record) throws SQLException;

    int insertSelective(TSubject record) throws SQLException;

    List selectByExampleWithBLOBs(TSubjectExample example) throws SQLException;

    List selectByExampleWithoutBLOBs(TSubjectExample example) throws SQLException;

    TSubject selectByPrimaryKey(Integer id) throws SQLException;

    int updateByExampleSelective(TSubject record, TSubjectExample example) throws SQLException;

    int updateByExampleWithBLOBs(TSubject record, TSubjectExample example) throws SQLException;

    int updateByExampleWithoutBLOBs(TSubject record, TSubjectExample example) throws SQLException;

    int updateByPrimaryKeySelective(TSubject record) throws SQLException;

    int updateByPrimaryKeyWithBLOBs(TSubject record) throws SQLException;

    int updateByPrimaryKeyWithoutBLOBs(TSubject record) throws SQLException;


}
