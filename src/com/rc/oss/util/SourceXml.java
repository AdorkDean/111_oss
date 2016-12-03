package com.rc.oss.util;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**  
 * @Title: TestXml.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-6-25 下午03:16:52
 * @version V1.0  
 */

public class SourceXml {
	private Logger log=Logger.getLogger(this.getClass());
	 /** 
     * 获取指定xml文档的Document对象
     * 
     * @param xmlFilePath xml文件路径 
     * @return Document对象 
     */ 
    public Document parse2Document(String xmlFilePath) { 
        SAXReader reader = new SAXReader(); 
        Document document = null; 
        try { 
            InputStream in = this.getClass().getResourceAsStream(xmlFilePath);
            document = reader.read(in); 
        } catch (DocumentException e) { 
            System.out.println("读取映射文件，请检查映射文件名是否存在！"); 
            e.printStackTrace(); 
        } 
        return document; 
    } 

    /**
     * 说明:根据文件路径，把命名空间和Document放入到map里
     * @param xmlFileName
     * @return
     */
    public Map<String,Document> ParseXML2Data(String xmlFileName) { 

        //将xml文档转换为Document的对象 
        Document document = parse2Document(xmlFileName); 
        //获取文档的根元素 
        Element root = document.getRootElement(); 
        String namespace=root.attributeValue("namespace");
        
//        List list=new ArrayList();
//        //遍历当前元素(在此是根元素)的子元素 
//        for (Iterator i_pe = root.elementIterator(); i_pe.hasNext();) { 
//            Element e_pe = (Element) i_pe.next(); 
//            String id = e_pe.attributeValue("id"); 
//            String sql = e_pe.getText(); 
//            HsqlBean bean=new HsqlBean(id,sql.trim());
//            list.add(bean);
//            //log.info("namespace:"+namespace+" id:"+id);
//            Element  e=e_pe.element("isnotnull");
//            String t=e.getText();
//            System.out.println(t);
//        }
        Map<String,Document> map=new HashMap<String,Document>();
        map.put(namespace, document);
        return map;
    } 

    /**
     * 说明：取资源映射文件的地址，用于读取内容
     * @return
     */
    public  Map<String,Document> getSqlMap(){
    	SAXReader reader = new SAXReader(); 
        Document document = null; 
        
        InputStream in = this.getClass().getResourceAsStream("/hbnMap.xml"); 
        try {
			document = reader.read(in);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Map<String,Document> mapSource=new HashMap();
       
        //获取文档的根元素 
        Element root = document.getRootElement(); 
        List<String> list=new ArrayList<String>();
        for (Iterator i_pe = root.elementIterator(); i_pe.hasNext();) { 
            Element e_pe = (Element) i_pe.next(); 
            String resource = e_pe.attributeValue("resource");
            log.info("hbnMap.xml "+resource);
            Map map=ParseXML2Data(resource);
            
            mapSource.putAll(map);
        }
    	return mapSource;
    }
    

    public static void main(String args[]) { 

    } 

}
