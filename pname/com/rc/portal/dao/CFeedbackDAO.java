package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.CFeedback;
import com.rc.portal.vo.CFeedbackExample;

public interface CFeedbackDAO {
    int countByExample(CFeedbackExample example) throws SQLException;

    int deleteByExample(CFeedbackExample example) throws SQLException;

    int deleteByPrimaryKey(Integer id) throws SQLException;

    Integer insert(CFeedback record) throws SQLException;

    Integer insertSelective(CFeedback record) throws SQLException;

    List selectByExample(CFeedbackExample example) throws SQLException;

    CFeedback selectByPrimaryKey(Integer id) throws SQLException;

    int updateByExampleSelective(CFeedback record, CFeedbackExample example) throws SQLException;

    int updateByExample(CFeedback record, CFeedbackExample example) throws SQLException;

    int updateByPrimaryKeySelective(CFeedback record) throws SQLException;

    int updateByPrimaryKey(CFeedback record) throws SQLException;


}
