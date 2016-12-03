package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TjMember;
import com.rc.portal.vo.TjMemberExample;

public interface TjMemberManager {
    int countByExample(TjMemberExample example) throws SQLException;

    int deleteByExample(TjMemberExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TjMember record) throws SQLException;

    Long insertSelective(TjMember record) throws SQLException;

    List selectByExample(TjMemberExample example) throws SQLException;

    TjMember selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TjMember record, TjMemberExample example) throws SQLException;

    int updateByExample(TjMember record, TjMemberExample example) throws SQLException;

    int updateByPrimaryKeySelective(TjMember record) throws SQLException;

    int updateByPrimaryKey(TjMember record) throws SQLException;



}
