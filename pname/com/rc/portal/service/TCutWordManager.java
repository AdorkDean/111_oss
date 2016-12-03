package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TCutWord;
import com.rc.portal.vo.TCutWordExample;

public interface TCutWordManager {
    int countByExample(TCutWordExample example) throws SQLException;

    int deleteByExample(TCutWordExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TCutWord record) throws SQLException;

    Long insertSelective(TCutWord record) throws SQLException;

    List selectByExample(TCutWordExample example) throws SQLException;

    TCutWord selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TCutWord record, TCutWordExample example) throws SQLException;

    int updateByExample(TCutWord record, TCutWordExample example) throws SQLException;

    int updateByPrimaryKeySelective(TCutWord record) throws SQLException;

    int updateByPrimaryKey(TCutWord record) throws SQLException;



}
