<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>优惠劵设置</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body> 
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list" style="width:100%">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
        <td width="15%" align="center">Key</td>
        <td width="10%" align="center">Value</td>
        <td width="10%" align="center">备注</td>
        <td width="5%" align="center">操作</td>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
				 
      <#list pw.result?if_exists as resc>
		<tr height="35px" id="tr_record_${resc.id}">
			<td class="bc" align="center" title="${resc.sys_key?default('')}">
			<#if resc.sys_key?exists>
				<#assign siz=resc.sys_key.length()>
	            <#if siz gt 50 >
	           		${resc.sys_key[0..50]?default('')}...
			  	<#else>
					${resc.sys_key?default('')}
			   </#if>
			<#else>
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.sys_value?default('')}" style="width:20%">
			<#if resc.sys_value?exists>
				<#assign siz=resc.sys_value.length()>
	            <#if siz gt 50 >
	           		${resc.sys_value[0..50]?default('')}...
			  	<#else>
					${resc.sys_value?default('')}
			   </#if>
		  	<#else>
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.remark?default('')}" style="width:20%">
			<#if resc.remark?exists>
				<#assign siz=resc.remark.length()>
	            <#if siz gt 50 >
	           		${resc.remark[0..50]?default('')}...
			  	<#else>
					${resc.remark?default('')}
			   </#if>
			<#else>
			</#if>
			</td>
			<td class="bc" align="center" style="width:15%">
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YHJ_XTSZ">
			<a href="${base}/systemmanage/sysc!syscCouponGetView.action?id=${resc.id}&flag=1"  >修改</a>
			</@security.authorize>
			</td>
			</tr>
			</#list>
      
	   		 <!-- /#list -->
			 </tbody>
			  
	     </table>   
     
     
    </div>        
    <script type="text/javascript">
	function deleteRecord(id){
	 if(!window.confirm("您确定要删除该记录吗?删除的数据不能恢复！")){
	  	return;
	 }
			jQuery.ajax
	   ({
	       type: "post",
	       url: " ${base}/systemmanage/sysc!syscDelete.action",	  
	       data:{"id":id},
	       success: function(data)
	       {
	          if(data>0){
	          	alert("操作成功");
	          	$('#tr_record_'+id).remove();
	          }else{
	          	alert("网络异常");
	          }
	       },error:function(){
	       		alert("网络异常");
	       }
	       
	   }); 
	}
	</script>
</body>
</html>