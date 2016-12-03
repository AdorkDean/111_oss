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

import org.apache.log4j.Logger;
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
import com.rc.portal.service.TMemberGradeManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TMemberThreeBindingManager;
import com.rc.portal.vo.TMemberGrade;
import com.rc.portal.vo.TMemberGradeExample;
import com.rc.portal.webapp.util.FileUtil;
public class InitHDTableInfoAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private OpenSqlManage opensqlmanage;
	
	private TMemberManager  tmembermanager ;
	
	private TMemberThreeBindingManager tmemberthreebindingmanager;
	
	private TMemberGradeManager tmembergrademanager;
	
	private final Logger log = Logger.getLogger(getClass());
	
	
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

	/**
	 * 列表
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws SQLException 
	 */
	public void initMembersFromHDExcel() throws FileNotFoundException, IOException, SQLException{
		System.out.println(getRequest().getSession().getServletContext().getRealPath("/"));;
		String logFile = "/var/myimport/logs/haidian/importhdmember/"+System.currentTimeMillis()+".log";
		FileUtil.appendFile(logFile,"hello linux!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//WebRoot/excel_temp/海典会员信息2.xlsx
		String excelFile = getRequest().getSession().getServletContext().getRealPath("/")+"/excel_temp/海典会员资料.xlsx";
		
		List<Map> hdMemberList = getMembersSceneXSSFMap(excelFile,logFile);
		
		List<Map> hdMemberList_tmp=null;
		
		TMemberGradeExample tMemberGradeExample = new TMemberGradeExample(); 
		tMemberGradeExample.setOrderByClause(" id asc limit 1 ");
		List<TMemberGrade> tMemberGradeList= tmembergrademanager.selectByExample(tMemberGradeExample);
		if(tMemberGradeList == null || tMemberGradeList.size() <= 0){
			System.out.println("获取TMemberGrade 失败");
			return;
		}
		
		for(int i=1;i<1000;i++){
			System.out.println("-------第 "+i*100+" 条----");
			hdMemberList_tmp=new ArrayList<Map>();
			for(int j=(i-1)*100;j<i*100;j++){
				if(j>=hdMemberList.size()){
					break;
				}
				hdMemberList_tmp.add(hdMemberList.get(j));
				
			}
			//1:读取excel表信息
			//2:循环excel表信息，经过逻辑判断初始化t_member表，及t_member_three_binding表
			
			//注意：member表中的platform=5  source=8  t_member_three_bingding中的source = 8
			
			FileUtil.appendFile(logFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ " *************封装导入数据列表 完成       hdMemberList.size()="+hdMemberList.size()+"***************");
			log.info("*************封装导入数据列表 完成       hdMemberList.size()="+hdMemberList.size()+"***************");
			
			
			tmemberthreebindingmanager.initHDMemberInfo(hdMemberList_tmp,tMemberGradeList.get(0).getId(),logFile);	
		}

		
		getResponse().getWriter().write("success");
		return ;
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
		
//		String fileToBeRead="D:\\myeclipseworkspace08\\initDbTable\\海典会员信息2.xlsx";
		
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
//					Member member = new Member();
					Map<String,String> member = new HashMap();
					int cellNums = row.getLastCellNum();
					
					String memcardno = null;//会员卡号   必填
					String cardholder= null;//持卡人
					String birthday = null;//出生日期
					String sex = null;//性别
					String handset= null;//手机	 必填
					String phone = null;//电话
					
					for(int cellNum=0; cellNum<cellNums; cellNum++){
						XSSFCell cell = row.getCell(cellNum);
						if(cell == null && cellNum == 0){
							continue loop;
						}else{
							String cellVal = getCellValue(cell);
							String name = "";
							switch (cellNum) {
							case 17:
								name = "会员卡号";
								memcardno = DataUtil.getTrimValue(cellVal);
								break;
							case 10:
								name = "持卡人";
								cardholder = DataUtil.getTrimValue(cellVal);
								break;
							case 12:
								name = "出生日期";
								birthday = cellVal;
								break;
							case 14:
								name = "性别";
								sex = cellVal;
								break;
							case 15:
								name = "电话";
								phone = DataUtil.getTrimValue(cellVal);
								break;
							case 16:
								name = "手机";
								handset = DataUtil.getTrimValue(cellVal);
								break;
							default:
								name = null;
								break;
							}
//							if(name != null){
//								System.out.print(name+":"+ cellVal +"  ");
//							}
							
						}
					}
					member.put("memcardno", memcardno);
					member.put("birthday", birthday);
					member.put("sex", sex);
					member.put("cardholder", cardholder);
					member.put("handset", handset);
					member.put("phone", phone);
					
					
					//过滤手机号码
					phone = phone==null?"":phone;
					handset = handset==null?"":handset;
					
		    		if(!ValidateUtil.validateMobile(phone) && !ValidateUtil.validateMobile(handset)){
		    			continue;
		    		}
		    		if(memcardno == null ||memcardno.trim().length() == 0){
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
	
	public  List getMembersSceneXSSFObject(String fileToBeRead) throws FileNotFoundException, IOException{
		List importList = new ArrayList();
		
//		String fileToBeRead="D:\\myeclipseworkspace08\\initDbTable\\海典会员信息2.xlsx";
		
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
					Member member = new Member();
					int cellNums = row.getLastCellNum();
					
					String memcardno = null;//会员卡号   必填
					String cardholder= null;//持卡人
					String birthday = null;//出生日期
					String sex = null;//性别
					String handset= null;//移动电话	 必填
					
					for(int cellNum=0; cellNum<cellNums; cellNum++){
						XSSFCell cell = row.getCell(cellNum);
						if(cell == null && cellNum == 0){
							continue loop;
						}else{
							String cellVal = getCellValue(cell);
							String name = "";
							switch (cellNum) {
							case 17:
								name = "会员卡号";
								memcardno = cellVal;
								break;
							case 10:
								name = "持卡人";
								cardholder = cellVal;
								break;
							case 12:
								name = "出生日期";
								birthday = cellVal;
								break;
							case 14:
								name = "性别";
								sex = cellVal;
								break;
							case 15:
								name = "移动电话";
								handset = cellVal;
								break;
							default:
								name = null;
								break;
							}
							if(name != null){
								System.out.print(name+":"+ cellVal +"  ");
							}
							
						}
					}
					member.setMemcardno(memcardno);
					member.setBirthday(birthday);
					member.setSex(sex);
					member.setCardholder(cardholder);
					member.setHandset(handset);
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
	
	public static void main(String[] args) {
//		String logFile = "/var/myimport/logs/haidian/importhdmember/"+System.currentTimeMillis()+".log";
//		FileUtil.appendFile(logFile,"hello linux!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		FileUtil.appendFile("/temp/logs/haidian/importhdmember/"+System.currentTimeMillis()+".log", "aaaaaaaaaaaaa");
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		list.add("11");
		list.add("12");
		list.add("13");
		list.add("14");
		list.add("15");
		list.add("16");
		list.add("17");
		list.add("18");
		list.add("19");
		list.add("20");
		for(int i=1;i<8;i++){
			
			for(int j=(i-1)*4;j<i*4;j++){
				if(j>=list.size()){
					System.out.println("越界");
					break;
				}
				System.out.println("out:"+list.get(j));
				
			}
		}
	}
}
