package com.rc.portal.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.commons.util.InfoUtil;
import com.rc.portal.commons.DataUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TManufacturerManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExample;
import com.rc.portal.vo.TManufacturer;
import com.rc.portal.vo.TManufacturerExample;
import com.rc.portal.webapp.util.FileUtil;

public class GoodsSearchWordImportAction  extends BaseAction{
	private final Long MAXSIZE = 1024*1024*10L;
	private final String diskPath = InfoUtil.getInstance().getInfo("files", "files.excel.path");
	private SqlMapClient sqlMapClient_nurse;
	private OpenSqlManage opensqlmanage;
	private TGoodsManager tgoodsmanager;
	TManufacturerManager tmanufacturermanager;
	
	
	int importTotalCount = 0;//导入总条数
	int importSuccessCount = 0;//导入成功条数
	int importFailCount = 0;//导入失败条数
	
	List<Map> importFailProductList = new ArrayList();;//导入失败商品列表
	Condition model = new Condition();


	public TManufacturerManager getTmanufacturermanager() {
		return tmanufacturermanager;
	}


	public void setTmanufacturermanager(TManufacturerManager tmanufacturermanager) {
		this.tmanufacturermanager = tmanufacturermanager;
	}


	public Condition getModel() {
		return model;
	}


	public SqlMapClient getSqlMapClient_nurse() {
		return sqlMapClient_nurse;
	}


	public void setSqlMapClient_nurse(SqlMapClient sqlMapClient_nurse) {
		this.sqlMapClient_nurse = sqlMapClient_nurse;
	}


	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}


	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}


	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}


	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}
	public String goodsSearchWordImport(){
		return "goodsSearchWordImport";
	}
	

	public void importExcel() throws IOException{
		
		int flag =-1;
		String msg="EXCEL上传出现异常";
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out = this.getResponse().getWriter();
		String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
		String filename = model.getMemberExcelFileName();
		String fileType= FilenameUtils.getExtension(filename);
		String fullName= "";
		String imgUrl = "";
		File file = model.getMemberExcel();
		if(checkFileType(fileType)){
			if(file.length()<MAXSIZE){
				String fullPath=diskPath+getDiskName()+"/"+DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()));
				imgUrl=fullPath+"."+fileType;
				fullName=basePath+imgUrl;
				File uploadFile = new File(fullName);
				FileUtils.copyFile(file, uploadFile);//上传文件
				flag=1;
				msg="文件上传成功";
			}else{
				flag =2;
				msg="上传文件大小超过10M";
			}
		}else{
			flag=0;
			msg="上传文件类型错误,暂支持xls,xlsx格式的图片";
		}
		Map map = new HashMap();
		map.put("fileType", fileType);
		map.put("flag", flag);
		map.put("msg", msg);
		map.put("imgUrl", imgUrl);
		out.write(JSONObject.fromObject(map).toString());
		out.close();
	}
	
	
	public void importData() throws IOException{
		String filePath = getRequest().getParameter("filePath");
		String fileType = getRequest().getParameter("fileType");
		System.out.println("filePath:"+filePath);
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
		String addLogFile = "/var/myimport/logs/product/importproduct/addLogFile_"+System.currentTimeMillis()+".log";
		String excelFileFullPath  =  getRequest().getSession().getServletContext().getRealPath("/")+"/"+filePath;
		
		//PrintWriter out = this.getResponse().getWriter();
		
//		String validateMsg = validateExcel(excelFileFullPath,fileType);
//		if(validateMsg != null && validateMsg.trim().length() > 0){
//			Map map = new HashMap();
//			map.put("flag", 0);
//			map.put("msg", validateMsg);
//			out.write(JSONObject.fromObject(map).toString());
//			out.close();
//			return;
//		}
		
		List<Map> productList = getProductsSceneXSSFMap(excelFileFullPath,addLogFile,fileType);
		importTotalCount = productList.size();
		
		FileUtil.appendFile(addLogFile,"\r\n"+com.rc.commons.util.DateUtil.getFormattedDateUtil(new Date(), "yyyy-MM-dd HH:mm:ss")+ " *************封装导入数据列表 完成       productList.size()="+productList.size()+"***************");
		List<Map> productList_tmp=null;
		
		
		for(int i=1;i<1000;i++){
			System.out.println("gw---第 "+i*100+" 条----");
			productList_tmp=new ArrayList<Map>();
			for(int j=(i-1)*100;j<i*100;j++){
				if(j>=productList.size()){
					break;
				}
				productList_tmp.add(productList.get(j));
				
			}
			
			
			addProductSearchWord(productList_tmp,addLogFile);
			if(i * 100 >= productList.size()){
				break;
			}
		}

		
		
//		Map map = new HashMap();
//		map.put("flag", 1);
//		map.put("msg", "success");
//		out.write(JSONObject.fromObject(map).toString());
//		out.close();
		printResultExcel(this.getResponse());
	}
	
	
	public boolean addProductSearchWord(List<Map> productList_tmp,String addLogFile){
		if(productList_tmp == null || productList_tmp.size() <= 0){
    		return false;
    	}
    	for(int i=0; i<productList_tmp.size(); i++){
    		
    		Map<String,String> productMap = productList_tmp.get(i);
    		
    		String goodsno = DataUtil.getTrimValue(productMap.get("goodsno"));//商家编码
    		String goodsName = DataUtil.getTrimValue(productMap.get("goodsName"));//商品名称
    		String searchWord = DataUtil.getTrimValue(productMap.get("searchWord"));//搜索词
    		
    		//查询t_goods表中是否存在 goodsno = goodsno的数据
    		//如果没有
    			//打印日志
    		TGoodsExample tGoodsExample = new TGoodsExample();
    		tGoodsExample.createCriteria().andGoodsnoEqualTo(goodsno);
    		try {
    			System.out.println("start:"+System.currentTimeMillis());
    			List<TGoods> goodsList = tgoodsmanager.selectByExample(tGoodsExample);
    			System.out.println("end:"+System.currentTimeMillis());
				if(goodsList == null || goodsList.size() == 0){//商品不存在
					Map<String,String> failProductMap = new HashMap();
					failProductMap.put("goodsno", goodsno);
					failProductMap.put("failReason", "商品编号不存在");
					importFailProductList.add(failProductMap);
					continue;
				}else{
					if(searchWord == null || searchWord.trim().length() == 0){
						Map<String,String> failProductMap = new HashMap();
						failProductMap.put("goodsno", goodsno);
						failProductMap.put("failReason", "搜索词为空");
						importFailProductList.add(failProductMap);
						continue;
					}else{
						searchWord = dealSearchWord(searchWord);
						TGoods goods = goodsList.get(0);
						goods.setSearchWord(searchWord);
						long updateFlag = tgoodsmanager.updateByPrimaryKeySelective(goods);
						if(updateFlag >= 0){
							importSuccessCount ++;
							//成功
						}else{
							Map<String,String> failProductMap = new HashMap();
							failProductMap.put("goodsno", goodsno);
							failProductMap.put("failReason", "系统保存异常");
							importFailProductList.add(failProductMap);
							//失败
						}
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				FileUtil.appendFile(addLogFile,"\r\n error:"+e.getMessage());
				
			}
    	}
    	return true;
	}
	
	public String dealSearchWord(String searchWord){
		return searchWord.replace("，", ",").replace("。",",");
	}
	public boolean addProduct(List<Map> productList_tmp,String addLogFile){
		if(productList_tmp == null || productList_tmp.size() <= 0){
    		return false;
    	}
    	for(int i=0; i<productList_tmp.size(); i++){
    		
    		Map<String,String> productMap = productList_tmp.get(i);
    		
    		String goodsno = DataUtil.getTrimValue(productMap.get("goodsno"));
    		String goodstitle = DataUtil.getTrimValue(productMap.get("goodstitle"));
    		String price = DataUtil.getTrimValue(productMap.get("price"));
    		
    		//查询t_goods表中是否存在 goodsno = goodsno的数据
    		//如果没有
    			//打印日志
    		TGoodsExample tGoodsExample = new TGoodsExample();
    		tGoodsExample.createCriteria().andGoodsnoEqualTo(goodsno);
    		try {
				int tgoodsCnt = tgoodsmanager.countByExample(tGoodsExample);
				TManufacturer t;
				if(tgoodsCnt <= 0){
					FileUtil.appendFile(addLogFile,"\r\n"+"--no-->"+productMap);
					System.out.println("--no-->"+productMap);
					//查询dc_t_good表中的信息，然后insert t_good
					Map goodCodeMap = new HashMap();
					goodCodeMap.put("goodsno", goodsno);
					Map dcTGoodMap = selectByGoodCode(goodCodeMap);
					
					if(dcTGoodMap != null){
						FileUtil.appendFile(addLogFile,"--dc_t_good:id:-->"+dcTGoodMap.get("id"));
						TGoods tGoods = new TGoods();
						tGoods.setGoodsno(dcTGoodMap.get("goodcode").toString());
						tGoods.setShortName(dcTGoodMap.get("short_name")==null?"":dcTGoodMap.get("short_name").toString());
						tGoods.setGoodsName(dcTGoodMap.get("goods_name")==null?"":dcTGoodMap.get("goods_name").toString());
						tGoods.setGenericName(dcTGoodMap.get("generic_name")==null?"":dcTGoodMap.get("generic_name").toString());
						tGoods.setUnit(dcTGoodMap.get("unit")==null?null:dcTGoodMap.get("unit").toString());
						tGoods.setWeight(dcTGoodMap.get("weight")==null?new BigDecimal(0L):new BigDecimal(dcTGoodMap.get("weight").toString()));
						tGoods.setApprovalNumber(dcTGoodMap.get("approval_number")==null?null:dcTGoodMap.get("approval_number").toString());
						tGoods.setBarCode(dcTGoodMap.get("bar_code")==null?null:dcTGoodMap.get("bar_code").toString());
						tGoods.setSpec(dcTGoodMap.get("spec")==null?null:dcTGoodMap.get("spec").toString());
						tGoods.setCreateDt(new Date());
						
						
						String manufacturer = dcTGoodMap.get("manufacturer") == null?null:dcTGoodMap.get("manufacturer").toString();
						if (manufacturer != null && !"".equals(manufacturer)) {
							TManufacturerExample tManufacturerExample = new TManufacturerExample();
							tManufacturerExample.createCriteria().andManuNameEqualTo(manufacturer.trim());
							List<TManufacturer> mList = tmanufacturermanager.selectByExample(tManufacturerExample);
							if (mList != null && mList.size() > 0) {
								tGoods.setManufacturerId(mList.get(0).getId());
							} else {
							}
						}
						
						tGoods.setIsRelease(0);// 未发布
						
						Map paramMap = new HashMap<String, Object>();
						paramMap.put("len", 8);
						String returnSn = (String) opensqlmanage.selectForObjectByMap(paramMap,
								"t_goods.p_t_goods_goodscode");
						while (returnSn == null || "".equals(returnSn) || "-1".equals(returnSn)) {
							returnSn = (String) opensqlmanage.selectForObjectByMap(paramMap,
									"t_goods.p_t_goods_goodscode");
						}
						tGoods.setGoodscode(returnSn);
						tGoods.setSkuId(String.valueOf(System.currentTimeMillis()));
						tGoods.setBrandId(0L);
						tGoods.setDoseId(0L);
						tGoods.setIsSuit(0);
						tGoods.setType(4);
						tGoods.setStock(0L);
						Long goodsId = tgoodsmanager.insertSelectiveByGoods(tGoods);
						FileUtil.appendFile(addLogFile,"--insert status: success , goodsId:-->"+goodsId);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				FileUtil.appendFile(addLogFile,"\r\n error:"+e.getMessage());
				
			}
    	}
    	return true;
	}
	
	public static String getDiskName(){
		String diskname="";
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		diskname =df.format(new Date());
		return diskname;
		
	}
	
	public String validateExcel(String fileToBeRead,String fileType) throws IOException{
		String msg = "";
		File file = new File(fileToBeRead);
		FileInputStream fi = new FileInputStream(fileToBeRead);
		Workbook workbook = null;
		if(checkFileTypeFlag(fileType) == -1){
			return null;
		}else if(checkFileTypeFlag(fileType) == 1){//xls
			workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
		}else if(checkFileTypeFlag(fileType) == 2){//xlsx
			workbook = new XSSFWorkbook(new FileInputStream(fileToBeRead));
		}
		int sheetNum = workbook.getNumberOfSheets();
		for(int i=0; i<=0; i++){
			Sheet sheet = workbook.getSheetAt(i);
			
			int rowNums = sheet.getLastRowNum();
			
			System.out.println("rowNums:"+rowNums);
			loop:for(int rowNum=0; rowNum<=0; rowNum++){
				Row row = sheet.getRow(rowNum);
				if(row != null){
					
					Map<String,String> product = new HashMap();
					int cellNums = row.getLastCellNum();
					
					String goodsno = null;//商家编码
					String goodsName = null;//商品名称
					String searchWord = null;//搜索词
					
					for(int cellNum=0; cellNum<cellNums; cellNum++){
						Cell cell = row.getCell(cellNum);
						if(cell == null && cellNum == 1){
							continue loop;
						}else{
							String cellVal = getCellValue(cell);
							
							switch (cellNum) {
							case 0://"海典编码";
								goodsno = DataUtil.getTrimValue(cellVal);
								break;
							case 1://"商品名称";
								goodsName = DataUtil.getTrimValue(cellVal);
								break;
							case 2://"搜索词";
								searchWord = DataUtil.getTrimValue(cellVal);
								break;
							default:
								break;
							}
							
						}
					}
					//System.out.println(goodsno+","+goodsName+","+searchWord);
					
//					if(goodsno == null || goodsno.indexOf("商家编码") < 0 ){
//						return "第2列必须为:商家编码";
//					}else if(price == null || price.indexOf("售价") < 0){
//						return "第7列必须为:售价";
//					}
					
				}
			}
		}
		
		
		
		return msg;
	}
	
	public void printResultExcel(HttpServletResponse response){
		this.getResponse().setContentType("application/x-download");
		try {
			this.getResponse().addHeader("Content-Disposition", "attachment;filename="+ "test"+com.rc.commons.util.DateUtil.getNowLongTime()+".xlsx");
			OutputStream out = null;
			out = this.getResponse().getOutputStream();
		
		
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = null;
			sheet = workbook.createSheet("商品搜索词导入结果_"+com.rc.commons.util.DateUtil.getNowLongTime());
			sheet.setColumnWidth(0, 50*160);
			sheet.setColumnWidth(1, 50*160);
			Row rowTotal = sheet.createRow(0);
			rowTotal.createCell(0).setCellValue("导入总条数：");
			rowTotal.createCell(1).setCellValue(importTotalCount);
			
			Row rowSuccess = sheet.createRow(1);
			rowSuccess.createCell(0).setCellValue("导入成功条数：");
			rowSuccess.createCell(1).setCellValue(importSuccessCount);
			
			Row rowFail = sheet.createRow(2);
			rowFail.createCell(0).setCellValue("导入失败条数：");
			rowFail.createCell(1).setCellValue(importTotalCount - importSuccessCount);
			
			if(importFailProductList.size() > 0){
				Row rowThree = sheet.createRow(3);
				rowThree.createCell(0).setCellValue("导入失败商品编号：");
				rowThree.createCell(1).setCellValue("导入失败原因");
			}
			for(int i=0; i<importFailProductList.size(); i++){
				Map importFailProduct = importFailProductList.get(i);
				
				Row rowThree = sheet.createRow(4+i);
				rowThree.createCell(0).setCellValue(importFailProduct.get("goodsno").toString());
				rowThree.createCell(1).setCellValue(importFailProduct.get("failReason").toString());
			}
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public  List<Map> getProductsSceneXSSFMap(String fileToBeRead,String logFile,String fileType) throws FileNotFoundException, IOException{
		List importList = new ArrayList();
		
		File file = new File(fileToBeRead);
		FileInputStream fi = new FileInputStream(fileToBeRead);
		
		Workbook workbook = null;
		
		if(checkFileTypeFlag(fileType) == -1){
			return null;
		}else if(checkFileTypeFlag(fileType) == 1){//xls
			workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
		}else if(checkFileTypeFlag(fileType) == 2){//xlsx
			workbook = new XSSFWorkbook(new FileInputStream(fileToBeRead));
		}
		int sheetNum = workbook.getNumberOfSheets();
		for(int i=0; i<=0; i++){
			Sheet sheet = workbook.getSheetAt(i);
			
			int rowNums = sheet.getLastRowNum();
			
			System.out.println("rowNums:"+rowNums);
			loop:for(int rowNum=0; rowNum<=rowNums; rowNum++){
				Row row = sheet.getRow(rowNum);
				if(row != null){
					
					Map<String,String> product = new HashMap();
					int cellNums = row.getLastCellNum();
					
					String goodsno = null;//商家编码
					String goodsName = null;//商品名称
					String searchWord = null;//搜索词
					
					for(int cellNum=0; cellNum<cellNums; cellNum++){
						Cell cell = row.getCell(cellNum);
						if(cell == null && cellNum == 1){
							continue loop;
						}else{
							String cellVal = getCellValue(cell);
							
							switch (cellNum) {
							case 0://"商家编码";
								goodsno = DataUtil.getTrimValue(cellVal);
								break;
							case 1://"宝贝标题";
								goodsName = DataUtil.getTrimValue(cellVal);
								break;
							case 2://"售价";
								searchWord = DataUtil.getTrimValue(cellVal);
								break;
							default:
								break;
							}
							
						}
					}
					product.put("goodsno", goodsno);
					product.put("goodsName", goodsName);
					product.put("searchWord", searchWord);
					
					
					//过滤商家编码
					goodsno = goodsno==null?"":goodsno;
					if(goodsno.equals("")){
						continue;
					}
		    		FileUtil.appendFile(logFile,product.toString());
					System.out.println(product);
					System.out.println(goodsno+","+goodsName+","+searchWord);
					importList.add(product);
				}
			}
		}
		return importList;
	}
	
	public static String getCellValue(Cell cell) { 
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
	/**
	 * 检查文件类型
	 * @param type
	 * @return
	 */
	public boolean checkFileType(String type){
		boolean flag=false;
		type = type.toLowerCase();
		String[] arrType={"xls","xlsx"};
		for(String s:arrType){
			if(type.equals(s)){
				return true;
			}
		}
		return flag;
	}
	
	/**
	 * 检查文件类型
	 * @param type
	 * @return
	 */
	public int checkFileTypeFlag(String type){
		type = type.toLowerCase();
		
		if("xls".equals(type)){
			return 1;
		}else if("xlsx".equals(type)){
			return 2;
		}else{
			return -1;
		}
	}
	
	public class Condition{
		private File memberExcel;
		private String memberExcelFileName;//文件名
		public File getMemberExcel() {
			return memberExcel;
		}
		public void setMemberExcel(File memberExcel) {
			this.memberExcel = memberExcel;
		}
		public String getMemberExcelFileName() {
			return memberExcelFileName;
		}
		public void setMemberExcelFileName(String memberExcelFileName) {
			this.memberExcelFileName = memberExcelFileName;
		}
		
		
	}

	@Override
	public void setModel(Object o) {
		this.model =(Condition) o;
		
	}

	public Map selectByGoodCode(Map goodCodeMap) throws SQLException {
        Map dcTGoodMap = (Map) sqlMapClient_nurse.queryForObject("t_goods.selectDcTGoodsByGoodCode", goodCodeMap);
        System.out.println(dcTGoodMap);
        return dcTGoodMap;
    }
	
	public static BigDecimal getBigDecimal(Object priceObj){
		if(priceObj == null){
			return new BigDecimal(0);
		}
		try{
			return new BigDecimal(priceObj.toString().trim());
		}catch(Exception e){
			return new BigDecimal(0); 
		}
	}
	
	public String testCookie2(){
		return "test";
	}
	
	public String testCookie() throws UnsupportedEncodingException{
		System.out.println("************");
		System.out.println("************");
		System.out.println("************");
		
		Cookie cookie = new Cookie("zjtestCookie", URLEncoder.encode("jackytest测试cookievalue", "UTF-8"));
		
		cookie.setMaxAge(60);
		
		cookie.setDomain("/111_oss/member/test");
		
		getResponse().addCookie(cookie);
		
		return "test";
	}
}
