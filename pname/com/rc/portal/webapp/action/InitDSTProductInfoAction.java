//package com.rc.portal.webapp.action;
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.NumberToTextConverter;
//
//
//import com.ibatis.sqlmap.client.SqlMapClient;
//import com.rc.app.framework.webapp.action.BaseAction;
//import com.rc.portal.commons.DataUtil;
//import com.rc.portal.service.OpenSqlManage;
//import com.rc.portal.service.TCouponCardManager;
//import com.rc.portal.service.TCouponManager;
//import com.rc.portal.service.TGoodsManager;
//import com.rc.portal.service.TManufacturerManager;
//import com.rc.portal.service.TMemberGradeManager;
//import com.rc.portal.service.TMemberManager;
//import com.rc.portal.service.TMemberThreeBindingManager;
//import com.rc.portal.vo.TGoods;
//import com.rc.portal.vo.TGoodsExample;
//import com.rc.portal.vo.TManufacturer;
//import com.rc.portal.vo.TManufacturerExample;
//import com.rc.portal.webapp.util.FileUtil;
//public class InitDSTProductInfoAction extends BaseAction{
//
//	private static final long serialVersionUID = 1L;
//	
//	private OpenSqlManage opensqlmanage;
//	
//	private TMemberManager  tmembermanager ;
//	
//	private TMemberThreeBindingManager tmemberthreebindingmanager;
//	
//	private TMemberGradeManager tmembergrademanager;
//	
//	private TCouponCardManager tcouponcardmanager;
//	
//	private TCouponManager tcouponmanager;
//	
//	private TGoodsManager tgoodsmanager;
//	
//	private SqlMapClient sqlMapClient_nurse;
//	
//	TManufacturerManager tmanufacturermanager;
//	
//	
//	
//	
//	
//	
//
//	public TManufacturerManager getTmanufacturermanager() {
//		return tmanufacturermanager;
//	}
//
//	public void setTmanufacturermanager(TManufacturerManager tmanufacturermanager) {
//		this.tmanufacturermanager = tmanufacturermanager;
//	}
//
//	public SqlMapClient getSqlMapClient_nurse() {
//		return sqlMapClient_nurse;
//	}
//
//	public void setSqlMapClient_nurse(SqlMapClient sqlMapClient_nurse) {
//		this.sqlMapClient_nurse = sqlMapClient_nurse;
//	}
//
//	public TGoodsManager getTgoodsmanager() {
//		return tgoodsmanager;
//	}
//
//	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
//		this.tgoodsmanager = tgoodsmanager;
//	}
//
//	public OpenSqlManage getOpensqlmanage() {
//		return opensqlmanage;
//	}
//
//	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
//		this.opensqlmanage = opensqlmanage;
//	}
//
//
//	public TMemberGradeManager getTmembergrademanager() {
//		return tmembergrademanager;
//	}
//
//	public void setTmembergrademanager(TMemberGradeManager tmembergrademanager) {
//		this.tmembergrademanager = tmembergrademanager;
//	}
//
//	
//	public TCouponCardManager getTcouponcardmanager() {
//		return tcouponcardmanager;
//	}
//
//	public void setTcouponcardmanager(TCouponCardManager tcouponcardmanager) {
//		this.tcouponcardmanager = tcouponcardmanager;
//	}
//
//	public TCouponManager getTcouponmanager() {
//		return tcouponmanager;
//	}
//
//	public void setTcouponmanager(TCouponManager tcouponmanager) {
//		this.tcouponmanager = tcouponmanager;
//	}
//
//	/**
//	 * 列表
//	 * @throws IOException 
//	 * @throws FileNotFoundException 
//	 * @throws SQLException 
//	 */
//	public void initProductsFromExcel() throws FileNotFoundException, IOException, SQLException{
//		
//		 
//		String addLogFile = "/var/myimport/logs/product/importproduct/addLogFile_"+System.currentTimeMillis()+".log";
//		
//		String excelFile = getRequest().getSession().getServletContext().getRealPath("/")+"/excel_temp/德生堂处方药在售最新20160224.xls";
//		
//		List<Map> productList = getProductsSceneXSSFMap(excelFile,addLogFile);
//		FileUtil.appendFile(addLogFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ " *************封装导入数据列表 完成       productList.size()="+productList.size()+"***************");
//		List<Map> productList_tmp=null;
//		
//		
//		for(int i=1;i<1000;i++){
//			System.out.println("gw---第 "+i*100+" 条----");
//			productList_tmp=new ArrayList<Map>();
//			for(int j=(i-1)*100;j<i*100;j++){
//				if(j>=productList.size()){
//					break;
//				}
//				productList_tmp.add(productList.get(j));
//				
//			}
//			
//			
//			addProduct(productList_tmp,addLogFile);
//			if(i * 100 >= productList.size()){
//				break;
//			}
//		}
//
//		getResponse().getWriter().write("success");
//		return ;
//	}
//
//	
//	public boolean addProduct(List<Map> productList_tmp,String addLogFile){
//		if(productList_tmp == null || productList_tmp.size() <= 0){
//    		return false;
//    	}
//    	for(int i=0; i<productList_tmp.size(); i++){
//    		
//    		Map<String,String> productMap = productList_tmp.get(i);
//    		
//    		String goodsno = DataUtil.getTrimValue(productMap.get("goodsno"));
//    		String goodstitle = DataUtil.getTrimValue(productMap.get("goodstitle"));
//    		String price = DataUtil.getTrimValue(productMap.get("price"));
//    		
//    		//查询t_goods表中是否存在 goodsno = goodsno的数据
//    		//如果没有
//    			//打印日志
//    		TGoodsExample tGoodsExample = new TGoodsExample();
//    		tGoodsExample.createCriteria().andGoodsnoEqualTo(goodsno);
//    		try {
//				int tgoodsCnt = tgoodsmanager.countByExample(tGoodsExample);
//				TManufacturer t;
//				if(tgoodsCnt <= 0){
//					FileUtil.appendFile(addLogFile,"\r\n"+"--no-->"+productMap);
//					System.out.println("--no-->"+productMap);
//					//查询dc_t_good表中的信息，然后insert t_good
//					Map goodCodeMap = new HashMap();
//					goodCodeMap.put("goodsno", goodsno);
//					Map dcTGoodMap = selectByGoodCode(goodCodeMap);
//					
//					if(dcTGoodMap != null){
//						FileUtil.appendFile(addLogFile,"--dc_t_good:id:-->"+dcTGoodMap.get("id"));
//						TGoods tGoods = new TGoods();
//						tGoods.setGoodsno(dcTGoodMap.get("goodcode").toString());
//						tGoods.setShortName(dcTGoodMap.get("short_name")==null?"":dcTGoodMap.get("short_name").toString());
//						tGoods.setGoodsName(dcTGoodMap.get("goods_name")==null?"":dcTGoodMap.get("goods_name").toString());
//						tGoods.setGenericName(dcTGoodMap.get("generic_name")==null?"":dcTGoodMap.get("generic_name").toString());
//						tGoods.setUnit(dcTGoodMap.get("unit")==null?null:dcTGoodMap.get("unit").toString());
//						tGoods.setWeight(dcTGoodMap.get("weight")==null?new BigDecimal(0L):new BigDecimal(dcTGoodMap.get("weight").toString()));
//						tGoods.setApprovalNumber(dcTGoodMap.get("approval_number")==null?null:dcTGoodMap.get("approval_number").toString());
//						tGoods.setBarCode(dcTGoodMap.get("bar_code")==null?null:dcTGoodMap.get("bar_code").toString());
//						tGoods.setSpec(dcTGoodMap.get("spec")==null?null:dcTGoodMap.get("spec").toString());
//						tGoods.setCreateDt(new Date());
//						
//						tGoods.setPcPrice(getBigDecimal(price));
//						tGoods.setWapPrice(getBigDecimal(price));
//						tGoods.setAppPrice(getBigDecimal(price));
//						
//						String manufacturer = dcTGoodMap.get("manufacturer") == null?null:dcTGoodMap.get("manufacturer").toString();
//						if (manufacturer != null && !"".equals(manufacturer)) {
//							TManufacturerExample tManufacturerExample = new TManufacturerExample();
//							tManufacturerExample.createCriteria().andManuNameEqualTo(manufacturer.trim());
//							List<TManufacturer> mList = tmanufacturermanager.selectByExample(tManufacturerExample);
//							if (mList != null && mList.size() > 0) {
//								tGoods.setManufacturerId(mList.get(0).getId());
//							} else {
//							}
//						}
//						
//						tGoods.setPcStatus(2);// 下架状态
//						tGoods.setAppStatus(2);
//						tGoods.setWapStatus(2);
//						tGoods.setIsRelease(0);// 未发布
//						tGoods.setIsTop(2);
//						tGoods.setIsWap(0);
//						tGoods.setIsApp(0);
//						tGoods.setIsWap(0);
//						
//						Map paramMap = new HashMap<String, Object>();
//						paramMap.put("len", 8);
//						String returnSn = (String) opensqlmanage.selectForObjectByMap(paramMap,
//								"t_goods.p_t_goods_goodscode");
//						while (returnSn == null || "".equals(returnSn) || "-1".equals(returnSn)) {
//							returnSn = (String) opensqlmanage.selectForObjectByMap(paramMap,
//									"t_goods.p_t_goods_goodscode");
//						}
//						tGoods.setGoodscode(returnSn);
//						tGoods.setSkuId(String.valueOf(System.currentTimeMillis()));
//						tGoods.setBrandId(0L);
//						tGoods.setDoseId(0L);
//						tGoods.setIsSuit(0);
//						tGoods.setType(4);
//						tGoods.setStock(0L);
//						Long goodsId = tgoodsmanager.insertSelectiveByGoods(tGoods);
//						FileUtil.appendFile(addLogFile,"--insert goodsId:-->"+goodsId);
//					}
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				FileUtil.appendFile(addLogFile,"\r\n error:"+e.getMessage());
//				
//			}
//    	}
//    	return true;
//	}
//	
//	@Override
//	public Object getModel() {
//		return null;
//	}
//
//	@Override
//	public void setModel(Object o) {
//		
//	}
//
//	public TMemberManager getTmembermanager() {
//		return tmembermanager;
//	}
//
//	public void setTmembermanager(TMemberManager tmembermanager) {
//		this.tmembermanager = tmembermanager;
//	}
//
//	public TMemberThreeBindingManager getTmemberthreebindingmanager() {
//		return tmemberthreebindingmanager;
//	}
//
//	public void setTmemberthreebindingmanager(
//			TMemberThreeBindingManager tmemberthreebindingmanager) {
//		this.tmemberthreebindingmanager = tmemberthreebindingmanager;
//	}
//	
//	
//	public static String getCellValue(Cell cell) { 
//		if(cell == null){
//			return null;
//		}
//        String ret;  
//        switch (cell.getCellType()) {  
//        case HSSFCell.CELL_TYPE_BLANK:  
//            ret = "";  
//            break;  
//        case HSSFCell.CELL_TYPE_BOOLEAN:  
//            ret = String.valueOf(cell.getBooleanCellValue());  
//            break;  
//        case HSSFCell.CELL_TYPE_ERROR:  
//            ret = null;  
//            break;  
//        case HSSFCell.CELL_TYPE_NUMERIC:  
//            if (DateUtil.isCellDateFormatted(cell)) {   
//                Date theDate = cell.getDateCellValue();  
//                ret = new SimpleDateFormat().format(theDate);  
//            } else {   
//                ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
//            }  
//            break;  
//        case HSSFCell.CELL_TYPE_STRING:  
//            ret = cell.getRichStringCellValue().getString();  
//            break;  
//        default:  
//            ret = null;  
//        }  
//          
//        return ret; //有必要自行trim  
//    }  
//	public  List<Map> getProductsSceneXSSFMap(String fileToBeRead,String logFile) throws FileNotFoundException, IOException{
//		List importList = new ArrayList();
//		
//		File file = new File(fileToBeRead);
//		FileInputStream fi = new FileInputStream(fileToBeRead);
//		
//		Workbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
//		int sheetNum = workbook.getNumberOfSheets();
//		for(int i=0; i<=0; i++){
//			Sheet sheet = workbook.getSheetAt(i);
//			
//			int rowNums = sheet.getLastRowNum();
//			
//			System.out.println("rowNums:"+rowNums);
//			loop:for(int rowNum=1; rowNum<=rowNums; rowNum++){
//				Row row = sheet.getRow(rowNum);
//				if(row != null){
//					
//					Map<String,String> product = new HashMap();
//					int cellNums = row.getLastCellNum();
//					
//					String goodsno = null;//商家编码
//					String goodstitle = null;//宝贝标题
//					String price = null;//售价
//					
//					for(int cellNum=0; cellNum<cellNums; cellNum++){
//						Cell cell = row.getCell(cellNum);
//						if(cell == null && cellNum == 1){
//							continue loop;
//						}else{
//							String cellVal = getCellValue(cell);
//							
//							switch (cellNum) {
//							case 1://"商家编码";
//								goodsno = DataUtil.getTrimValue(cellVal);
//								break;
//							case 2://"宝贝标题";
//								goodstitle = DataUtil.getTrimValue(cellVal);
//								break;
//							case 6://"售价";
//								price = DataUtil.getTrimValue(cellVal);
//								break;
//							default:
//								break;
//							}
//							
//						}
//					}
//					product.put("goodsno", goodsno);
//					product.put("goodstitle", goodstitle);
//					product.put("price", price);
//					
//					
//					//过滤商家编码
//					goodsno = goodsno==null?"":goodsno;
//					if(goodsno.equals("")){
//						continue;
//					}
//		    		FileUtil.appendFile(logFile,product.toString());
//					System.out.println(product);
//					importList.add(product);
//				}
//			}
//		}
//		return importList;
//	}
//	
//	
//	public static String getCellValue(HSSFCell cell) { 
//		if(cell == null){
//			return null;
//		}
//        String ret;  
//        switch (cell.getCellType()) {  
//        case HSSFCell.CELL_TYPE_BLANK:  
//            ret = "";  
//            break;  
//        case HSSFCell.CELL_TYPE_BOOLEAN:  
//            ret = String.valueOf(cell.getBooleanCellValue());  
//            break;  
//        case HSSFCell.CELL_TYPE_ERROR:  
//            ret = null;  
//            break;  
//        case HSSFCell.CELL_TYPE_NUMERIC:  
//            if (DateUtil.isCellDateFormatted(cell)) {   
//                Date theDate = cell.getDateCellValue();  
//                ret = new SimpleDateFormat().format(theDate);  
//            } else {   
//                ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
//            }  
//            break;  
//        case HSSFCell.CELL_TYPE_STRING:  
//            ret = cell.getRichStringCellValue().getString();  
//            break;  
//        default:  
//            ret = null;  
//        }  
//          
//        return ret; //有必要自行trim  
//    }  
//	
//	
//	public Map selectByGoodCode(Map goodCodeMap) throws SQLException {
//        Map dcTGoodMap = (Map) sqlMapClient_nurse.queryForObject("t_goods.selectDcTGoodsByGoodCode", goodCodeMap);
//        System.out.println(dcTGoodMap);
//        return dcTGoodMap;
//    }
//
//	
//	public class Member {
//		String memcardno;//会员卡号   必填
//		String cardholder;//持卡人
//		String birthday ;//出生日期
//		String sex ;//性别
//		String handset;//移动电话	 必填
//		public String getMemcardno() {
//			return memcardno;
//		}
//		public void setMemcardno(String memcardno) {
//			this.memcardno = memcardno;
//		}
//		public String getCardholder() {
//			return cardholder;
//		}
//		public void setCardholder(String cardholder) {
//			this.cardholder = cardholder;
//		}
//		public String getBirthday() {
//			return birthday;
//		}
//		public void setBirthday(String birthday) {
//			this.birthday = birthday;
//		}
//		public String getSex() {
//			return sex;
//		}
//		public void setSex(String sex) {
//			this.sex = sex;
//		}
//		public String getHandset() {
//			return handset;
//		}
//		public void setHandset(String handset) {
//			this.handset = handset;
//		}
//		@Override
//		public String toString() {
//			return "Member [memcardno=" + memcardno + ", cardholder=" + cardholder
//					+ ", birthday=" + birthday + ", sex=" + sex + ", handset="
//					+ handset + "]";
//		}
//		
//		
//		
//	}
//	
//	public static BigDecimal getBigDecimal(Object priceObj){
//		if(priceObj == null){
//			return new BigDecimal(0);
//		}
//		try{
//			return new BigDecimal(priceObj.toString().trim());
//		}catch(Exception e){
//			return new BigDecimal(0); 
//		}
//	}
//	public static void main(String[] args) {
//		System.out.println(getBigDecimal("1f23.123"));
//	}
//}
