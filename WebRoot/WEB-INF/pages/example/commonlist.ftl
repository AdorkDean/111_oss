<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>公共列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
            
	     	<form name="form1" id="subform"  action="" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
					
	        <div style="float:left;"> 
	            <div class="labelName">名称1：</div>
	            <input name="" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;"/>
	            
	            <div class="labelName" style="margin-left:15px;">名称2：</div>
	            <input name="" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;"/>
	            
	            <div class="labelName" style="margin-left:15px;">名称3：</div>
	            <select name="" class="select-ui">
					 <option value="0">请选择</option>
					 <option value="1">项目1</option>
				</select>
				
				<input name="" type="checkbox" value="" class="lname-c" style="border:#cccccc 1px solid;margin-left:15px;"/>
	            <div class="labelName" style="margin-left:5px;">选项1</div>
				
				<input name="" type="radio" value="" class="lname-c" style="border:#cccccc 1px solid;margin-left:15px;"/>
	            <div class="labelName" style="margin-left:5px;">选项1</div>
	            
	            <input type="button" class="btn01" onclick="" value="添加" style="margin-left:15px;margin-top:2px;">
	            <input type="button" class="btn01" onclick="" value="返回" style="margin-left:15px;margin-top:2px;">
	            
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
			   	 	<th style="width:10%;">序号</th>
			   	 	<th>名称1</th>
			   	 	<th>名称2</th>
			   	 	<th>操作</th>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
	   	     <!-- #list pw.result as abc -->
				 <tr class="content" style="text-align:center;">
				 	<td>1</td>
				 	<td>2</td>
				 	<td>3</td>
				 	<td>
				 		<input type="button" class="btn01" onclick="" value="编辑">
				 		<input type="button" class="btn01" onclick="" value="删除">
				 	</td>
				 </tr>
	   		 <!-- /#list -->
			 </tbody>
			  
	     </table>   
     
     
     <!-- 分页 Start -->    
     <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
     <!-- 分页 End --> 
     
    </div>        
</body>
</html>