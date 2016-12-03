package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.portal.vo.THealthyPlan;
import com.rc.portal.vo.THealthyPlanCombination;
import com.rc.portal.vo.THealthyPlanExample;
import com.rc.portal.vo.THealthyPlanSymptom;
import com.rc.portal.webapp.model.HealthyPlanContentModel;

public interface THealthyPlanManager {
    int countByExample(THealthyPlanExample example) throws SQLException;

    int deleteByExample(THealthyPlanExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(THealthyPlan record) throws SQLException;

    Long insertSelective(THealthyPlan record) throws SQLException;

    List selectByExample(THealthyPlanExample example) throws SQLException;

    THealthyPlan selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(THealthyPlan record, THealthyPlanExample example) throws SQLException;

    int updateByExample(THealthyPlan record, THealthyPlanExample example) throws SQLException;

    int updateByPrimaryKeySelective(THealthyPlan record) throws SQLException;

    int updateByPrimaryKey(THealthyPlan record) throws SQLException;
    
    List<HealthyPlanContentModel> getHealthyPlanContent(Map<String, Object> map);
    
    Map<String, Object> getHealthyPlaContentInfo(Map<String, Object> map);
    
    /**
     * 删除健康方案
     * @throws Exception
     */
    void  deleteTHealthyPlan(long planId) throws Exception;
    
    /**
     * 删除健康方案分类
     * @param classId
     * @throws Exception
     */
    void deleteTHealthyPlanClassification(long classId) throws Exception;
    
    Long saveTHealthyPlan(THealthyPlan plan,String[] goodsWeights,String[] goodsids) throws SQLException;
    
    /**
     * 保存伴随症状
     * @param symptom
     * @param goodsWeights
     * @param goodsids
     * @return
     * @throws SQLException
     */
    Long saveTHealthyPlanSymptom(THealthyPlanSymptom symptom,String[] goodsWeights,String[] goodsids) throws SQLException;
    
    /**
     * 删除伴随症状
     * @param symptomId
     * @throws SQLException
     */
    void deletePlanSymptom(long symptomId) throws SQLException;
    
    /**
     * 删除 用药套餐
     * @param combinationId
     * @throws SQLException
     */
    void deletePlanCombination(long combinationId) throws SQLException;
    
    
    /**
     * 保存套餐
     * @param combination
     * @param goodsWeights
     * @param goodsids
     * @return
     * @throws SQLException
     */
    Long saveTHealthyPlanCombination(THealthyPlanCombination combination,String[] goodsWeights,String[] goodsids) throws SQLException;
    
    

}
