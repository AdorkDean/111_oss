package com.rc.portal.webapp.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.commons.DataUtil;
import com.rc.portal.commons.ValidateUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponCardManager;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.service.TMemberGradeManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TMemberThreeBindingManager;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TCouponCard;
import com.rc.portal.vo.TCouponCardExample;
import com.rc.portal.vo.TCouponExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.vo.TMemberGradeExample;
import com.rc.portal.webapp.util.FileUtil;
public class InitMenDianMemberCouponInfoAction extends BaseAction{

	//测试用例
//	1:导入数据库中mobile不存在的会员,看是否成功,以及表中的关键数据 是否正确 status=0,mobile,user_name,real_name,platform=1,source=5
//			1.1mobile不存在,user_name也不存在的，应该能正常insert
//			1.2mobile不存在，user_name存在的，导入失败，并且记录该日志userNameEqualMobilelogFile_XXXXXX.log
//	2:导入数据库中mobile存在的会员,不应该insert任何数据，查看addLogFile_XXXXXX.log
//	3:查看1.1及2中的情况下是否成功绑定了优惠券 t_coupon_card 表中member_id = memberId and ticket_id = coupon.getId()
//	4:重复导入，看是否重新insert了会员及绑定了新的优惠券
	
	
	private static final long serialVersionUID = 1L;
	
	private OpenSqlManage opensqlmanage;
	
	private TMemberManager  tmembermanager ;
	
	private TMemberThreeBindingManager tmemberthreebindingmanager;
	
	private TMemberGradeManager tmembergrademanager;
	private TCouponManager tcouponmanager;	
	private TCouponCardManager tcouponcardmanager;
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}


	public TMemberGradeManager getTmembergrademanager() {
		return tmembergrademanager;
	}

	public void setTmembergrademanager(TMemberGradeManager tmembergrademanager) {
		this.tmembergrademanager = tmembergrademanager;
	}

	
	public TCouponCardManager getTcouponcardmanager() {
		return tcouponcardmanager;
	}

	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
		this.tcouponcardmanager = tcouponcardmanager;
	}

	public TCouponManager getTcouponmanager() {
		return tcouponmanager;
	}

	public void setTcouponmanager(TCouponManager tcouponmanager) {
		this.tcouponmanager = tcouponmanager;
	}

	/**
	 * 列表
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws SQLException 
	 */
	public void initMembersFromHDExcel() throws FileNotFoundException, IOException, SQLException{
		
		String couponIdStr = getRequest().getParameter("couponId");
		System.out.println("couponId:"+couponIdStr);
		Long couponId;
		if(couponIdStr == null || couponIdStr.trim().length() == 0){
			System.out.println("请传入绑定的优惠券id!");
			return;
		}else{
			try{
				couponId = Long.valueOf(couponIdStr.trim());
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
		}
		//查询是否存在对应的优惠券
		TCouponExample tCouponExample = new TCouponExample();
		tCouponExample.createCriteria().andIdEqualTo(couponId);
		int hasCoupon = tcouponmanager.countByExample(tCouponExample);
		if(hasCoupon <= 0){
			System.out.println("绑定的优惠券id不存在!");
			return;
		}
		
		String addLogFile = "/var/myimport/logs/mendian/importmember/addLogFile_"+System.currentTimeMillis()+".log";
		String userNameEqualMobilelogFile = "/var/myimport/logs/mendian/importmember/userNameEqualMobilelogFile_"+System.currentTimeMillis()+".log";
		
		String excelFile = getRequest().getSession().getServletContext().getRealPath("/")+"/excel_temp/门店会员客户信息（短信）2.24.xlsx";
		
		List<Map> hdMemberList = getMembersSceneXSSFMap(excelFile,addLogFile);
		List<Map> hdMemberList_tmp=null;
		
		TMemberGradeExample tMemberGradeExample = new TMemberGradeExample(); 
		tMemberGradeExample.setOrderByClause(" id asc limit 1 ");
		List<TMemberGrade> tMemberGradeList= tmembergrademanager.selectByExample(tMemberGradeExample);
		if(tMemberGradeList == null || tMemberGradeList.size() <= 0){
			System.out.println("获取TMemberGrade 失败");
			return;
		}
		
		for(int i=1;i<1000;i++){
			System.out.println("md---第 "+i*100+" 条----");
			hdMemberList_tmp=new ArrayList<Map>();
			for(int j=(i-1)*100;j<i*100;j++){
				if(j>=hdMemberList.size()){
					break;
				}
				hdMemberList_tmp.add(hdMemberList.get(j));
				
			}
			
			FileUtil.appendFile(addLogFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ " *************封装导入数据列表 完成       hdMemberList.size()="+hdMemberList.size()+"***************");
			
			tmemberthreebindingmanager.addMemberAndBindCoupon(hdMemberList_tmp,tMemberGradeList.get(0).getId(),userNameEqualMobilelogFile,addLogFile,tcouponcardmanager,opensqlmanage,couponId);
			
			if(i * 100 >= hdMemberList.size()){
				break;
			}
		}

		getResponse().getWriter().write("success");
		return ;
	}

	/**
	 * 
	 * @param hdMemberList	从excel中读取的导入会员数据
	 * @param tMemberGrade	查询的最低会员等级
	 * @param userNameEqualMobilelogFile mobilenotexistbutusernameexist_currenttime.log	用于保存excel表中的mobile 在 t_member表中的 mobile 不存在 但是 user_name存在的数据 
	 * @param addLogFile	addmobileandcoupon_current.log新增会员及绑定优惠券情况的日志
	 */
	public void addMemberAndBindCoupon(List<Map> hdMemberList,Long tMemberGradeId
										,String userNameEqualMobilelogFile,String addLogFile){
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
	        		tMemberExample.createCriteria().andMobileEqualTo(mobile);
	        		System.out.println("-------------start1:"+System.currentTimeMillis());
					List<TMember> memberList = tmembermanager.selectByExample(tMemberExample);
					System.out.println("-------------end1:"+System.currentTimeMillis());
					if(memberList == null || memberList.size() <= 0){
						tMemberExample = new TMemberExample();
		        		tMemberExample.createCriteria().andUserNameEqualTo(mobile);
		        		System.out.println("-------------start2:"+System.currentTimeMillis());
		        		int existMemberFlag = tmembermanager.countByExample(tMemberExample);
		        		System.out.println("-------------end2:"+System.currentTimeMillis());
		        		if(existMemberFlag > 0){
		        			//记录日志中，不做其他处理
		        			System.out.println("-------------start3:"+System.currentTimeMillis());
		        			List<TMember> memberListTemp = tmembermanager.selectByExample(tMemberExample);
		        			System.out.println("-------------end3:"+System.currentTimeMillis());
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
		        			System.out.println("-------------start4:"+System.currentTimeMillis());
		        			Long memberId = tmembermanager.insertSelective(tMember);
		        			System.out.println("-------------end4:"+System.currentTimeMillis());
		        			if(memberId > 0){
		        				FileUtil.appendFile(addLogFile,"--addMemberState:success--addMemberId:"+memberId);
		        				//绑定优惠券
		        				System.out.println("-------------start5:"+System.currentTimeMillis());
		        				bingCoupon(memberId,addLogFile);
		        				System.out.println("-------------end5:"+System.currentTimeMillis());
		        			}else{
		        				FileUtil.appendFile(addLogFile,"--addMemberState:fail--addMemberId:"+memberId);
		        			}
		        		}
					}else{
						FileUtil.appendFile(addLogFile,"--addMemberState:exist--addMemberId:"+memberList.get(0).getId());
						//绑定优惠券
						System.out.println("-------------start6:"+System.currentTimeMillis());
						bingCoupon(memberList.get(0).getId(),addLogFile);
						System.out.println("-------------end6:"+System.currentTimeMillis());
					}
				} catch (SQLException e) {
					FileUtil.appendFile(addLogFile,"--addState:error");
					FileUtil.appendFile(addLogFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ "--mobile:"+mobile+e.getMessage());
					e.printStackTrace();
				}
    			System.out.println("-------------end:"+new Date());
    		}
    	}
		
	}
	
	public void bingCoupon(Long memberId,String addLogFile) throws SQLException{
		//查看该会员是否已经绑定过指定的优惠券了 t_coupon_card 表中member_id = memberId and ticket_id = coupon.getId()
		//绑定过
			//return
		//没绑定过
			//绑定
		long ticketId = 46;
		
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
	
	
	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {
		
	}

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}

	public TMemberThreeBindingManager getTmemberthreebindingmanager() {
		return tmemberthreebindingmanager;
	}

	public void setTmemberthreebindingmanager(
			TMemberThreeBindingManager tmemberthreebindingmanager) {
		this.tmemberthreebindingmanager = tmemberthreebindingmanager;
	}
	
	
	public static String getCellValue(XSSFCell cell) { 
		if(cell == null){
			return null;
		}
        String ret;  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_BLANK:  
            ret = "";  
            break;  
        case HSSFCell.CELL_TYPE_BOOLEAN:  
            ret = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case HSSFCell.CELL_TYPE_ERROR:  
            ret = null;  
            break;  
        case HSSFCell.CELL_TYPE_NUMERIC:  
            if (DateUtil.isCellDateFormatted(cell)) {   
                Date theDate = cell.getDateCellValue();  
                ret = new SimpleDateFormat().format(theDate);  
            } else {   
                ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
            }  
            break;  
        case HSSFCell.CELL_TYPE_STRING:  
            ret = cell.getRichStringCellValue().getString();  
            break;  
        default:  
            ret = null;  
        }  
          
        return ret; //有必要自行trim  
    }  
	public  List<Map> getMembersSceneXSSFMap(String fileToBeRead,String logFile) throws FileNotFoundException, IOException{
		List importList = new ArrayList();
		
		File file = new File(fileToBeRead);
		FileInputStream fi = new FileInputStream(fileToBeRead);
		
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileToBeRead));
		int sheetNum = workbook.getNumberOfSheets();
		for(int i=0; i<sheetNum; i++){
			XSSFSheet sheet = workbook.getSheetAt(i);
			
			int rowNums = sheet.getLastRowNum();
			
			System.out.println("rowNums:"+rowNums);
			loop:for(int rowNum=1; rowNum<=rowNums; rowNum++){
				XSSFRow row = sheet.getRow(rowNum);
				if(row != null){
					
					Map<String,String> member = new HashMap();
					int cellNums = row.getLastCellNum();
					
					String receiver = null;//收货人
					String mobile = null;//电话
					
					for(int cellNum=0; cellNum<cellNums; cellNum++){
						XSSFCell cell = row.getCell(cellNum);
						if(cell == null && cellNum == 1){
							continue loop;
						}else{
							String cellVal = getCellValue(cell);
							
							switch (cellNum) {
							case 0://"收货人姓名";
								receiver = DataUtil.getTrimValue(cellVal);
								break;
							case 1://"联系手机";
								mobile = DataUtil.getTrimValue(cellVal);
								break;
							default:
								break;
							}
							
						}
					}
					member.put("receiver", receiver);
					member.put("mobile", mobile);
					
					
					//过滤手机号码
					mobile = mobile==null?"":mobile;
					
		    		if(!ValidateUtil.validateMobile(mobile)){
		    			continue;
		    		}
		    		FileUtil.appendFile(logFile,member.toString());
					System.out.println(member);
					importList.add(member);
				}
			}
		}
		return importList;
	}
	
	
	public static String getCellValue(HSSFCell cell) { 
		if(cell == null){
			return null;
		}
        String ret;  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_BLANK:  
            ret = "";  
            break;  
        case HSSFCell.CELL_TYPE_BOOLEAN:  
            ret = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case HSSFCell.CELL_TYPE_ERROR:  
            ret = null;  
            break;  
        case HSSFCell.CELL_TYPE_NUMERIC:  
            if (DateUtil.isCellDateFormatted(cell)) {   
                Date theDate = cell.getDateCellValue();  
                ret = new SimpleDateFormat().format(theDate);  
            } else {   
                ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
            }  
            break;  
        case HSSFCell.CELL_TYPE_STRING:  
            ret = cell.getRichStringCellValue().getString();  
            break;  
        default:  
            ret = null;  
        }  
          
        return ret; //有必要自行trim  
    }  
	
	
	public class Member {
		String memcardno;//会员卡号   必填
		String cardholder;//持卡人
		String birthday ;//出生日期
		String sex ;//性别
		String handset;//移动电话	 必填
		public String getMemcardno() {
			return memcardno;
		}
		public void setMemcardno(String memcardno) {
			this.memcardno = memcardno;
		}
		public String getCardholder() {
			return cardholder;
		}
		public void setCardholder(String cardholder) {
			this.cardholder = cardholder;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getHandset() {
			return handset;
		}
		public void setHandset(String handset) {
			this.handset = handset;
		}
		@Override
		public String toString() {
			return "Member [memcardno=" + memcardno + ", cardholder=" + cardholder
					+ ", birthday=" + birthday + ", sex=" + sex + ", handset="
					+ handset + "]";
		}
		
		
		
	}
}
