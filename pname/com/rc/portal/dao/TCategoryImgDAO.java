package com.rc.portal.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TCategoryImg;
import com.rc.portal.vo.TCategoryImgExample;

public interface TCategoryImgDAO {
    int countByExample(TCategoryImgExample example) throws SQLException;

    int deleteByExample(TCategoryImgExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TCategoryImg record) throws SQLException;

    Long insertSelective(TCategoryImg record) throws SQLException;

    List selectByExample(TCategoryImgExample example) throws SQLException;

    TCategoryImg selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TCategoryImg record, TCategoryImgExample example) throws SQLException;

    int updateByExample(TCategoryImg record, TCategoryImgExample example) throws SQLException;

    int updateByPrimaryKeySelective(TCategoryImg record) throws SQLException;

    int updateByPrimaryKey(TCategoryImg record) throws SQLException;


}
