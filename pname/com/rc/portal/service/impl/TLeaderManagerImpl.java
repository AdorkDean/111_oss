package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.TLeaderDAO;
import com.rc.portal.dao.TMemberDAO;
import com.rc.portal.dao.TMemberLeaderDAO;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberLeader;

public class TLeaderManagerImpl  implements TLeaderManager {

    private TLeaderDAO tleaderdao;
    private TMemberDAO tmemberdao;
    private OpenSqlDAO opensqldao;
    private TMemberLeaderDAO tmemberleaderdao;
	public TMemberLeaderDAO getTmemberleaderdao() {
		return tmemberleaderdao;
	}
	public void setTmemberleaderdao(TMemberLeaderDAO tmemberleaderdao) {
		this.tmemberleaderdao = tmemberleaderdao;
	}
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}
	public TMemberDAO getTmemberdao() {
		return tmemberdao;
	}
	public void setTmemberdao(TMemberDAO tmemberdao) {
		this.tmemberdao = tmemberdao;
	}
	public TLeaderManagerImpl() {
        super();
    }
    public void  setTleaderdao(TLeaderDAO tleaderdao){
        this.tleaderdao=tleaderdao;
    }
    public TLeaderDAO getTleaderdao(){
        return this.tleaderdao;
    }
    public     int countByExample(TLeaderExample example) throws SQLException{
        return tleaderdao. countByExample( example);
    }

    public     int deleteByExample(TLeaderExample example) throws SQLException{
        return tleaderdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tleaderdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TLeader record) throws SQLException{
        return tleaderdao. insert( record);
    }

    public     Long insertSelective(TLeader record) throws SQLException{
        return tleaderdao. insertSelective( record);
    }

    public     List selectByExample(TLeaderExample example) throws SQLException{
        return tleaderdao. selectByExample( example);
    }

    public     TLeader selectByPrimaryKey(Long id) throws SQLException{
        return tleaderdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TLeader record, TLeaderExample example) throws SQLException{
        return tleaderdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TLeader record, TLeaderExample example) throws SQLException{
        return tleaderdao. updateByExample( record, example);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public     int updateMemberAndLeaderSelective(TLeader record) throws SQLException{
					Map map=new HashMap();
    				map.put("id", record.getId());
    				TMember member = (TMember)opensqldao.selectObjectByObject(map, "t_member.selectMemberByleaderId");
    				member.setIsLeader(1);
    		tmemberdao.updateByPrimaryKeySelective(member);
    		Map memberId=new HashMap();
    		memberId.put("memberId", member.getId());
    		TMemberLeader memberLerder = (TMemberLeader) opensqldao.selectObjectByObject(memberId, "t_member_leader.selectMemberLeaderByMemberId");
    		if(memberLerder!=null){
    			tmemberleaderdao.deleteByPrimaryKey(memberLerder.getId());
    		}
        return tleaderdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TLeader record) throws SQLException{
        return tleaderdao. updateByPrimaryKey( record);
    }
    public     int updateByPrimaryKeySelective(TLeader record) throws SQLException{
        return tleaderdao. updateByPrimaryKeySelective( record);
    }

}
