<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>公共列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>
<style>
tr{height:45px}
</style>
</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
            
	     	<form name="form1" id="subform"  action="${base}/shortbys/shortbuy!list.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
					
	        <div style="float:left;"> 
	            <div class="labelName">名称：</div>
	            <input name="shortBuyBean.name" type="text" class="lname-i" value="<#if shortBuyBean.name?exists>${shortBuyBean.name?default('--')}</#if>" style="border:#cccccc 1px solid;" />
	            
	            
	            <div class="labelName" style="margin-left:15px;">创建时间：</div>
	            <input name="stime" type="text" value="${stime?default('')}" class="lname-i" style="border:#cccccc 1px solid;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
	            <span style="float:left;font-size:20px">-</span><input name="etime" type="text" value="${etime?default('')}" class="lname-i" style="border:#cccccc 1px solid;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
	            
	            
	            <div class="labelName" style="margin-left:15px;">状态：</div>
	            <select name="shortBuyBean.status" class="select-ui">
	                 <option value="0" <#if shortBuyBean.status?exists><#if shortBuyBean.status==0>selected</#if></#if> >全部</option>
					 <option value="1" <#if shortBuyBean.status?exists><#if shortBuyBean.status==1>selected</#if></#if> >创建</option>
					 <option value="2" <#if shortBuyBean.status?exists><#if shortBuyBean.status==2>selected</#if></#if> >进行中 </option>
					 <option value="3" <#if shortBuyBean.status?exists><#if shortBuyBean.status==3>selected</#if></#if> >结束 </option>
				</select>
				
				 <input type="submit" class="btn01" value="查询" style="margin-left:15px;margin-top:2px;">
	            
	            <input type="button" class="btn01" onclick="add()" value="添加" style="margin-left:5px;margin-top:2px;">
	           
	            
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list" style="width:100%">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
        <td width="15%" align="center">名称</td>
        <td width="10%" align="center">创建时间</td>
        <td width="10%" align="center">开始时间</td>
        <td width="10%" align="center">结束时间</td>
        <td width="5%" align="center">状态</td>
        <td width="10%" align="center">广告语</td>
        <td width="5%" align="center">操作</td>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
				 
	  <#list pw.result?if_exists as l>
  
		      <tr class="content" style="text-align:center;" >		
		       
		        <td align="center" class="bc">
		         ${l.name?default('')}
		        </td>
		        <td align="center" class="bc">
		          ${l.create_time}
		        </td>
		        <td align="center" class="bc">
		         ${l.start_time}
		        </td>
		        <td align="center" class="bc">
		         ${l.end_time}
		        </td>
		        <td align="center" class="bc">
		         <#if l.status==1>创建</#if>
		         <#if l.status==2>进行中</#if>
		         <#if l.status==3>结束</#if>
		        </td>
		       
		        <td align="center" class="bc">
		         ${l.advertise?default('')}
		        </td>
		        <td align="center" class="bc">
		            <a href="${base}/shortbys/shortbuy!getRecord.action?id=${l.id?default(0)}">编辑</a>
		        	 <a href="${base}/shortbys/shortbuy!setGoods.action?id=${l.id?default(0)}">设置</a>
		        </td>	        
		      </tr>
      </#list>
      
      
	   		 <!-- /#list -->
			 </tbody>
			  
	     </table>   
     
     
     <!-- 分页 Start -->    
     <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
     <!-- 分页 End --> 
     
    </div>  
    
    <script>
    function add(){
    	window.location.href="${base}/shortbys/shortbuy!insert_page.action";
    }
    </script>      
</body>
</html>