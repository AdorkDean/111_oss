package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.portal.vo.TMemberThreeBinding;
import com.rc.portal.vo.TMemberThreeBindingExample;

public interface TMemberThreeBindingManager {
    int countByExample(TMemberThreeBindingExample example) throws SQLException;

    int deleteByExample(TMemberThreeBindingExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TMemberThreeBinding record) throws SQLException;

    Long insertSelective(TMemberThreeBinding record) throws SQLException;

    List selectByExample(TMemberThreeBindingExample example) throws SQLException;

    TMemberThreeBinding selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TMemberThreeBinding record, TMemberThreeBindingExample example) throws SQLException;

    int updateByExample(TMemberThreeBinding record, TMemberThreeBindingExample example) throws SQLException;

    int updateByPrimaryKeySelective(TMemberThreeBinding record) throws SQLException;

    int updateByPrimaryKey(TMemberThreeBinding record) throws SQLException;

    public boolean initHDMemberInfo(List<Map> hdMemberList,long memberGradeId,String logFile) throws SQLException;
    
    public void addMemberAndBindCoupon(List<Map> hdMemberList,Long tMemberGradeId
			,String userNameEqualMobilelogFile,String addLogFile
			,TCouponCardManager tcouponcardmanager,OpenSqlManage opensqlmanage,Long couponId);

}
