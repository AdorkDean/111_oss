package com.rc.oss.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

/**  
 * @Title: HbnSqlFactory.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-6-25 下午05:34:59
 * @version V1.0  
 */

public class HbnSqlFactory {
	private Logger log=Logger.getLogger(this.getClass());
	private Map<String,Document> sourceMap;
	private String sourceUrl;
	/**
	 * 说明:
	 * @param args
	 */
	

	public HbnSqlFactory() {
		super();
		sourceMap=new SourceXml().getSqlMap();
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public Map<String, Document> getSourceMap() {
		return sourceMap;
	}
	public void setSourceMap(Map<String, Document> sourceMap) {
		this.sourceMap = sourceMap;
	}

	/**
	 * 说明:更具传入的命名空间、id、参数，去hql
	 * @param spaceid
	 * @param root
	 * @return
	 */
	public String getHql(String spaceid,Map root){
		String hql="";
		String[] ni=spaceid.split("\\.");
		String namespace=ni[0];
		String id=ni[1];
		
		Document document=sourceMap.get(namespace);		
        //获取文档的根元素 
        Element doc_root = document.getRootElement(); 
        String doc_namespace=doc_root.attributeValue("namespace");
        if(!namespace.equals(doc_namespace)){
        	log.info("sourceMap 对应的Document里没有此namespace");
        	return hql;
        }
        
        //遍历当前元素(在此是根元素)的子元素 
        for (Iterator i_pe = doc_root.elementIterator(); i_pe.hasNext();) { 
            Element e_pe = (Element) i_pe.next(); 
            String doc_id = e_pe.attributeValue("id"); 
            if(doc_id.equals(id)){
            	hql=getSqlText(e_pe,root);
            	break;
            }
            
        }
        return hql;
	}
	
	/**
	 * 说明:根据sql节点和参数map，返回hql语句
	 * @param e
	 * @param map
	 */
	public String getSqlText(Element e,Map map){
		StringBuffer sb=new StringBuffer("");
    	String sql = e.getTextTrim(); 
    	String sqlTxt=getText(sql,map);
    	sb.append(sqlTxt);
    	//找到isNotNull的节点list
        List<Element> list=e.elements("isNotNull");
        for(Element e1:list){
        	String pro=e1.attributeValue("property");        
            Set set = map.keySet();
    		for (Object c : set) {
    			Object val=map.get(c.toString());
    			if(pro.equals(c)&& val!=null){
    				String null_txt=e1.getTextTrim();
    				String txt=getText(null_txt,map);
    				sb.append(txt);
    			}
    		}
        }
        //取order节点内的sql
        List<Element> list_order=e.elements("order");
        for(Element e1:list_order){
        	String pro=e1.attributeValue("property"); 
        	String txt=e1.getTextTrim();
        	sb.append(txt);
        }
        
		return sb.toString();
	}
	
	/**
	 * 说明:更具sql文本和参数的map，替换后得到sql
	 * @param txt
	 * @param map
	 * @return
	 */
	public String getText(String txt,Map<String,Object> map){		
		Set set = map.keySet();
		String sql=txt;
		for (Object c : set) {
			String val=map.get(c.toString()).toString();
			sql=sql.replace("#"+c+"#", val)+" ";
		}
		return sql;
	}
	
	public static void main(String[] args) {
		Map root=new HashMap();
		root.put("name", "yinbin");
		root.put("age", "32");
		root.put("address", "beijing");
		String hql=new HbnSqlFactory().getHql("test.1", root);
		System.out.println(hql);
	}
	
}
