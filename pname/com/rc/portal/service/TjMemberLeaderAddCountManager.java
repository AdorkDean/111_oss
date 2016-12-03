package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TjMemberLeaderAddCount;
import com.rc.portal.vo.TjMemberLeaderAddCountExample;

public interface TjMemberLeaderAddCountManager {
    int countByExample(TjMemberLeaderAddCountExample example) throws SQLException;

    int deleteByExample(TjMemberLeaderAddCountExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TjMemberLeaderAddCount record) throws SQLException;

    Long insertSelective(TjMemberLeaderAddCount record) throws SQLException;

    List selectByExample(TjMemberLeaderAddCountExample example) throws SQLException;

    TjMemberLeaderAddCount selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TjMemberLeaderAddCount record, TjMemberLeaderAddCountExample example) throws SQLException;

    int updateByExample(TjMemberLeaderAddCount record, TjMemberLeaderAddCountExample example) throws SQLException;

    int updateByPrimaryKeySelective(TjMemberLeaderAddCount record) throws SQLException;

    int updateByPrimaryKey(TjMemberLeaderAddCount record) throws SQLException;



}
