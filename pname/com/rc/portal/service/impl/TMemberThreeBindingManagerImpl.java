package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.commons.DataUtil;
import com.rc.portal.commons.ValidateUtil;
import com.rc.portal.dao.TMemberDAO;
import com.rc.portal.dao.TMemberThreeBindingDAO;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TMemberThreeBindingManager;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TCouponCard;
import com.rc.portal.vo.TCouponCardExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberThreeBinding;
import com.rc.portal.vo.TMemberThreeBindingExample;
import com.rc.portal.webapp.util.FileUtil;

public class TMemberThreeBindingManagerImpl  implements TMemberThreeBindingManager {

    private TMemberThreeBindingDAO tmemberthreebindingdao;
    
    private TMemberDAO tmemberdao;

    public TMemberThreeBindingManagerImpl() {
        super();
    }
    public void  setTmemberthreebindingdao(TMemberThreeBindingDAO tmemberthreebindingdao){
        this.tmemberthreebindingdao=tmemberthreebindingdao;
    }
    public TMemberThreeBindingDAO getTmemberthreebindingdao(){
        return this.tmemberthreebindingdao;
    }
    public     int countByExample(TMemberThreeBindingExample example) throws SQLException{
        return tmemberthreebindingdao. countByExample( example);
    }

    public     int deleteByExample(TMemberThreeBindingExample example) throws SQLException{
        return tmemberthreebindingdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tmemberthreebindingdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TMemberThreeBinding record) throws SQLException{
        return tmemberthreebindingdao. insert( record);
    }

    public     Long insertSelective(TMemberThreeBinding record) throws SQLException{
        return tmemberthreebindingdao. insertSelective( record);
    }

    public     List selectByExample(TMemberThreeBindingExample example) throws SQLException{
        return tmemberthreebindingdao. selectByExample( example);
    }

    public     TMemberThreeBinding selectByPrimaryKey(Long id) throws SQLException{
        return tmemberthreebindingdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TMemberThreeBinding record, TMemberThreeBindingExample example) throws SQLException{
        return tmemberthreebindingdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TMemberThreeBinding record, TMemberThreeBindingExample example) throws SQLException{
        return tmemberthreebindingdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TMemberThreeBinding record) throws SQLException{
        return tmemberthreebindingdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TMemberThreeBinding record) throws SQLException{
        return tmemberthreebindingdao. updateByPrimaryKey( record);
    }
    
    

public TMemberDAO getTmemberdao() {
		return tmemberdao;
	}
	public void setTmemberdao(TMemberDAO tmemberdao) {
		this.tmemberdao = tmemberdao;
	}
	//	1获取电话和手机号
//	
//		首先判断电话是否是正常的电话号码，
//	
//		如果是，电话号码优先使用电话
//	
//		如果不是，判断手机号是否符合正常的电话号码
//			如果是,使用手机号
//			如果不是,跳过该条
//		
//	2获取会员卡号
//		判断会员卡号是否为空，
//		如果为空，跳过该条
//	
//	
//	3:插入逻辑判断
//		判断 t_member表中是否存在该手机号(user_name mobile 和海典手机号相同 待确认)
//		如果存在，
//			判断是否已经绑定该会员号(binding_uuid 和 海典会员卡号相同 且 member_id和t_member的id相同 待确认)
//		如果不存在，
//			绑定该会员卡号,包括member_id
	
	//测试用例：
//	1：对于t_member表中已经存在海典mobile的记录不能再重复插入
//	1：对于t_member表中不存在海典mobile的记录需要插入,并且insert t_member_three_bingding 信息
//	2:对于t_member表中已经存在的,如果t_member_three_bingding表中不存在，则insert
//	3:对于t_member表中已经存在的,如果t_member_three_bingding表中存在，但uuid和memcardno不同，则update uuid为memcardno
//	4:对于t_member表中已经存在的,如果t_member_three_bingding表中存在，并且uuid和memcardno相同，则不做任何改动
   

	public boolean initHDMemberInfo(List<Map> hdMemberList,long memberGradeId,String logFile) throws SQLException{
    	if(hdMemberList == null || hdMemberList.size() <= 0){
    		return false;
    	}
    	for(int i=0; i<hdMemberList.size(); i++){
//    		if(i%100 == 0){
//    			System.out.println("已导入会员数量："+i);
//    		}
    		
    		Map<String,String> hdMemberMap = hdMemberList.get(i);
    		String mobile;//phone和handset中选择的正确的电话
    		
    		String memcardno = DataUtil.getTrimValue(hdMemberMap.get("memcardno"));//会员卡号   必填
    		String cardholder = DataUtil.getTrimValue(hdMemberMap.get("cardholder"));//持卡人
    		String handset = DataUtil.getTrimValue(hdMemberMap.get("handset"));//移动电话	 必填
    		String phone = DataUtil.getTrimValue(hdMemberMap.get("phone"));//电话
    		
    		FileUtil.appendFile(logFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ "----------memcardno:"+memcardno+",handset:"+handset+",phone:"+phone);
    		
    		//过滤手机号码
    		if(phone != null && ValidateUtil.validateMobile(phone)){
    			mobile = phone;
    		}else if(handset != null && ValidateUtil.validateMobile(handset)){
    			mobile = handset;
    		}else{
    			continue;
    		}
    		//过滤会员卡号
    		if(memcardno == null || memcardno.trim().length() == 0){
    			continue;
    		}
    		Date currentDate = new Date();
    		
    		//判断是否存在该手机号对应的会员卡号
    		TMemberExample tMemberExample = new TMemberExample();
    		tMemberExample.createCriteria().andMobileEqualTo(mobile);
    		int existMemberFlag = tmemberdao.countByExample(tMemberExample);
    		if(existMemberFlag > 0){//存在
    			//判断是否已经绑定了
    			List<TMember> tMemberList = tmemberdao.selectByExample(tMemberExample);
    			if(tMemberList == null || tMemberList.size() <= 0){
    				continue;
    			}
    			
    			TMember tMember = tMemberList.get(0);
    			
    			TMemberThreeBindingExample tMemberThreeBindingExample = new TMemberThreeBindingExample();
    			tMemberThreeBindingExample.createCriteria().andMemberIdEqualTo(tMember.getId()).andSourceEqualTo(8);
    			
    			List tmemberthreebindinglist = tmemberthreebindingdao.selectByExample(tMemberThreeBindingExample);
    			if(tmemberthreebindinglist == null || tmemberthreebindinglist.size() <= 0){//没绑定过
    				//绑定
    				TMemberThreeBinding tMemberThreeBinding = new TMemberThreeBinding();
    				tMemberThreeBinding.setBindingUuid(memcardno);
    				tMemberThreeBinding.setMemberId(tMember.getId());
    				tMemberThreeBinding.setCreateDate(currentDate);
    				tMemberThreeBinding.setSource(8);
    				
    				Long insertThreeBindId = tmemberthreebindingdao.insert(tMemberThreeBinding);
    				FileUtil.appendFile(logFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ "insertThreeBindId:"+insertThreeBindId);
    			}else{//绑定过
    				//判断之前绑定的会员卡号和当前会员卡号是否一致,不一致需要更新
    				TMemberThreeBinding tMemberThreeBinding = (TMemberThreeBinding) tmemberthreebindinglist.get(0);
    				if(!memcardno.equals(tMemberThreeBinding.getBindingUuid())){//不一致，更新
    					
    					tMemberThreeBinding.setBindingUuid(memcardno);
    					tmemberthreebindingdao.updateByPrimaryKeySelective(tMemberThreeBinding);
    					FileUtil.appendFile(logFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ "updateThreeBindId:"+tMemberThreeBinding.getId()+" updateBindingUuid:"+memcardno);
    				}
    			}
    			
    			
    		}else{//不存在，添加
    			
    			TMember tMember = new TMember();
    			tMember.setPassword("");
    			tMember.setUserName("hd_"+memcardno+"_"+System.currentTimeMillis());
    			tMember.setRealName(cardholder);
    			tMember.setMobile(mobile);
    			tMember.setPlatform(5);
    			tMember.setSource(8);
    			tMember.setStatus(0);
    			tMember.setIsMobileCheck(1);
    			tMember.setRegisterDate(currentDate);
    			tMember.setMemberGradeId(memberGradeId);
    			Long memberId = tmemberdao.insert(tMember);
    			
    			//绑定
    			TMemberThreeBinding tMemberThreeBinding = new TMemberThreeBinding();
				tMemberThreeBinding.setBindingUuid(memcardno);
				tMemberThreeBinding.setMemberId(memberId);
				tMemberThreeBinding.setCreateDate(currentDate);
				tMemberThreeBinding.setSource(8);
    			
				long threeId = tmemberthreebindingdao.insert(tMemberThreeBinding);
				
				FileUtil.appendFile(logFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ "insertMemberId:"+memberId+",insertThreeBindId:"+threeId);
    		}
    		
    		
    	}
    	
    	
    	return true;
    }
    
    
	/**
	 * 
	 * @param hdMemberList	从excel中读取的导入会员数据
	 * @param tMemberGrade	查询的最低会员等级
	 * @param userNameEqualMobilelogFile mobilenotexistbutusernameexist_currenttime.log	用于保存excel表中的mobile 在 t_member表中的 mobile 不存在 但是 user_name存在的数据 
	 * @param addLogFile	addmobileandcoupon_current.log新增会员及绑定优惠券情况的日志
	 */
	public void addMemberAndBindCoupon(List<Map> hdMemberList,Long tMemberGradeId
										,String userNameEqualMobilelogFile,String addLogFile
										,TCouponCardManager tcouponcardmanager,OpenSqlManage opensqlmanage,Long couponId){
//		判断（是否有 t_member表中的mobile字段 = excel表中的手机号）
//				如果没有
//					判断是否有 user_name = excel表中的手机号
//						如果有
//							记录日志中，不做其他处理
//						如果没有
//							新增会员信息
//							绑定优惠券
//				如果有
//					绑定优惠券
		
		if(hdMemberList == null || hdMemberList.size() <= 0){
			FileUtil.appendFile(addLogFile,"hdMemberList size is null,import error!");
    		return;
    	}else{
    		for(int i=0; i<hdMemberList.size(); i++){
    			
    			Map<String,String> memberMap = hdMemberList.get(i);
    			String receiver = DataUtil.getTrimValue(memberMap.get("receiver"));//收货人
    			String mobile = DataUtil.getTrimValue(memberMap.get("mobile"))==null?"":DataUtil.getTrimValue(memberMap.get("mobile"));//电话
    			
    			FileUtil.appendFile(addLogFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ "--mobile:"+mobile+",receiver:"+receiver);
    			try {
	        		//过滤手机号码
	        		if(mobile == null || mobile.trim().length() == 0 || !ValidateUtil.validateMobile(mobile)){
	        			continue;
	        		}
	        		
	        		TMemberExample tMemberExample = new TMemberExample();
	        		tMemberExample.createCriteria().andMobileEqualTo(mobile).andIsMobileCheckEqualTo(1);
					List<TMember> memberList = tmemberdao.selectByExample(tMemberExample);
					if(memberList == null || memberList.size() <= 0){
						tMemberExample = new TMemberExample();
		        		tMemberExample.createCriteria().andUserNameEqualTo(mobile);
		        		int existMemberFlag = tmemberdao.countByExample(tMemberExample);
		        		if(existMemberFlag > 0){
		        			//记录日志中，不做其他处理
		        			List<TMember> memberListTemp = tmemberdao.selectByExample(tMemberExample);
		        			FileUtil.appendFile(addLogFile,"--addMemberState:existUsername--addMemberId:"+memberListTemp.get(0).getId());
		        			if(memberListTemp != null && memberListTemp.size() > 0){
		        				FileUtil.appendFile(userNameEqualMobilelogFile,"\r\n"+"--exist_user_name:"+mobile+"--id:"+memberListTemp.get(0).getId());
		        			}
		        		}else{
		        			TMember tMember = new TMember();
		    				tMember.setPassword("");
		        			tMember.setUserName(mobile);					//手机号
		        			tMember.setRealName(receiver);				//持卡人，如果为空则为空
		        			tMember.setMobile(mobile);
		        			tMember.setPlatform(1);
		        			tMember.setSource(5);
		        			tMember.setStatus(0);
		        			tMember.setIsMobileCheck(1);
		        			tMember.setRegisterDate(new Date());
		        			tMember.setMemberGradeId(tMemberGradeId);
		        			Long memberId = tmemberdao.insertSelective(tMember);
		        			if(memberId > 0){
		        				FileUtil.appendFile(addLogFile,"--addMemberState:success--addMemberId:"+memberId);
		        				//绑定优惠券
		        				bingCoupon(memberId,addLogFile,tcouponcardmanager,opensqlmanage,couponId);
		        			}else{
		        				FileUtil.appendFile(addLogFile,"--addMemberState:fail--addMemberId:"+memberId);
		        			}
		        		}
					}else{
						FileUtil.appendFile(addLogFile,"--addMemberState:exist--addMemberId:"+memberList.get(0).getId());
						//绑定优惠券
						bingCoupon(memberList.get(0).getId(),addLogFile,tcouponcardmanager,opensqlmanage,couponId);
					}
				} catch (SQLException e) {
					FileUtil.appendFile(addLogFile,"--addState:error");
					FileUtil.appendFile(addLogFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ "--mobile:"+mobile+e.getMessage());
					e.printStackTrace();
				}
    		}
    	}
		
	}
	public void bingCoupon(Long memberId,String addLogFile,TCouponCardManager tcouponcardmanager,OpenSqlManage opensqlmanage,Long couponId) throws SQLException{
		//查看该会员是否已经绑定过指定的优惠券了 t_coupon_card 表中member_id = memberId and ticket_id = coupon.getId()
		//绑定过
			//return
		//没绑定过
			//绑定
		long ticketId = couponId;
		
		TCouponCardExample tCouponCardExample = new TCouponCardExample();
		tCouponCardExample.createCriteria().andMemberIdEqualTo(memberId).andTicketIdEqualTo(ticketId);
		List<TCouponCard> tCouponCardList =  tcouponcardmanager.selectByExample(tCouponCardExample);
		
		if(tCouponCardList != null && tCouponCardList.size() > 0){
			FileUtil.appendFile(addLogFile,"--addCouponState:exists--addCouponId:"+tCouponCardList.get(0).getId());
			return;
		}else{
			Map couponMap=new HashMap();
			couponMap.put("regCouponId", ticketId);
			TCoupon coupon = (TCoupon) opensqlmanage.selectObjectByObject(couponMap, "t_coupon.selectCouponByid");
			Long couponCardId = tcouponcardmanager.bindingCoupon(coupon,memberId, 1);
			if(couponCardId != null && couponCardId > 0){
				FileUtil.appendFile(addLogFile,"--addCouponState:success--addCouponId:"+couponCardId);
			}else{
				FileUtil.appendFile(addLogFile,"--addCouponState:fail--addCouponId:"+couponCardId);
			}
		}
		
	}
}
