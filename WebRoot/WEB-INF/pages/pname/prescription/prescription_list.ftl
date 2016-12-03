<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>病例管理</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:96%;"> 
            
     	<form name="form1" id="subform" action="${base}/prescription/prescription!prescriptionList.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
					
	        <div style="float:left;"> 
	            <div class="labelName">手机号：</div>
	            <input name="phone" type="text" value="${phone?if_exists}" class="lname-i" style="border:#cccccc 1px solid;"/>
            	<div class="labelName" style="margin-left:15px;">是否需要医师帮助：</div>
	            <select name="ifHelp" class="select-ui" value="${orderStatus?if_exists}">
					<option value="" >请选择</option>
					<option value="1" <#if ifHelp?exists && ifHelp=='1'>selected = "selected"</#if>>是</option>
					<option value="0" <#if ifHelp?exists && ifHelp=='0'>selected = "selected"</#if>>否</option>
				</select>
            	<div class="labelName" style="margin-left:15px;">是否已回访：</div>
	            <select name="status" class="select-ui" value="${orderStatus?if_exists}">
					<option value="" >请选择</option>
					<option value="1" <#if status?exists && status=='1'>selected = "selected"</#if>>是</option>
					<option value="0" <#if status?exists && status=='0'>selected = "selected"</#if>>否</option>
				</select>
	            
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_BINGLI_LIEBIAO_SEARCH">
					<input type="button" class="btn01" onclick="search()" value="搜索" style="margin-left:15px;margin-top:2px;">
		    	</@security.authorize>
	            <!--<div class="labelName" style="margin-left:15px;">名称3：</div>
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
	            -->
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
			   	 	<th style="width:4%;">序号</th>
					<th>病情描述</th>
					<th>手机号</th>
					<th>用户名</th>
					<th>状态</th>
					<th>图片</th>
					<th>上传时间</th>
					<th>是否需要回访</th>
					<th>回访人</th>
					<th>回访时间</th>
					<th>管理</th>
			   	 </tr>
			 </thead>
			 <!-- 查询内容  -->
		     <tbody> 
	   	     <!-- #list pw.result as abc -->
	   	     	<#list pw.result as prescription>
				 <tr class="content" style="text-align:center;">
					<td>${prescription_index+1}</td>
					<td title="${prescription.diseaseDescrip?default('')}">
						<#if prescription?exists && prescription.diseaseDescrip?exists && prescription.diseaseDescrip?length gt 15>
							${prescription.diseaseDescrip[0..14]}...
						<#else>
							${prescription.diseaseDescrip?default('')}
						</#if>
					</td>
					<td title="${prescription.phone?default('')}">
						${prescription.phone?default('')}
					</td>
					<td title="${prescription.userName?default('')}">${prescription.userName?default('')}</td>
					<td>
						<#if prescription.status?exists && prescription.status==1>
							<span title="已回访">已回访</span>
							<#else>
							<span title="已上传">已上传</span>
						</#if>
					</td>
					<td title="${ui1?if_exists}${prescription.imageUrl?default('')}">
						<#if prescription.imageUrl?exists><img src="${ui1?if_exists}${prescription.imageUrl?default('')}" style="width:100px;height:90px;"/></#if>
					</td>
					<td title="<#if prescription.uploadTime?exists>${prescription.uploadTime?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if>"><#if prescription.uploadTime?exists>${prescription.uploadTime?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
					<td>
						<#if prescription.ifhelp?exists && prescription.ifhelp==1>
							<span title="是">是</span>
							<#else>
							<span title="否">否</span>
						</#if>
					</td>
					<td title="${prescription.callUsername?default('')}">
							${prescription.callUsername?default('')}
					</td>
					<td title="<#if prescription.callTime?exists>${prescription.callTime?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if>"><#if prescription.callTime?exists>${prescription.callTime?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
				 	<td>
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_BINGLI_LIEBIAO_DETAIL">
			    			<a href="prescription!detailPrescription.action?id=${prescription.id?if_exists}">[查看]</a>
				    	</@security.authorize>
						<#if prescription.status?exists && prescription.status==0 && prescription.ifhelp?exists && prescription.ifhelp==1>
				 			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_BINGLI_LIEBIAO_REPLY">
								<a href="prescription!replyPrescription.action?id=${prescription.id}">[回访]</a>
					    	</@security.authorize>
						</#if>
						<#if prescription.status?exists && prescription.status==1>
							已回访
						</#if>
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
    <script type="text/javascript">
    	/**
    	 * 查看详细
    	 */
    	function detailReturn(id){
    		window.location.href="${base}/prescription/prescription!detailPrescription.action?id="+id;
    	}
    	
    	function search(){
    		$("#subform").submit();
    	}
    	
    	/**
    	  * 操作
    	  */
    	function operate(id){
    		if(id==null || id==""){
		    	$alert('请选择一条数据');
		    	return false;
    		}
    		window.location.href="${base}/prescription/prescription!replyPrescription.action?id="+id;
    	}
    
    </script>
</body>
</html>