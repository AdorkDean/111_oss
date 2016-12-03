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
            
	     	<form name="form1" id="subform"  action="${base}/systemmanage/sysc!syscList.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="1" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
	        <div style="float:left;"> 
	            <div class="labelName">KEY或备注：</div>
	            <input  type="text" name="syskey" value="${syskey?default('')}" class="lname-i" style="border:#cccccc 1px solid;" />
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XTGUANLI_PEIZHIWENJIANGUANLI_LIEBIAO_CHAXUN">
				<input type="submit" class="btn01" onclick="" value="查询" style="margin-left:15px;margin-top:2px;">
				</@security.authorize>
	            
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XTGUANLI_PEIZHIWENJIANGUANLI_LIEBIAO_TIANJIA">
	            <input type="button" class="btn01" onclick="window.location.href='${base}/systemmanage/sysc!syscAddPage.action';" value="添加" style="margin-left:5px;margin-top:2px;">
				</@security.authorize>
	            
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
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
			
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XTGUANLI_PEIZHIWENJIANGUANLI_LIEBIAO_CHAKAN">
			<a href="${base}/systemmanage/sysc!syscGetView.action?id=${resc.id}&flag=0" >查看</a>
			</@security.authorize>
			
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XTGUANLI_PEIZHIWENJIANGUANLI_LIEBIAO_XIUGAI">
			<a href="${base}/systemmanage/sysc!syscGetView.action?id=${resc.id}&flag=1"  >修改</a>
			</@security.authorize>
			
			<#--<a href="javascript:deleteRecord(${resc.id});" >删除</a>-->
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