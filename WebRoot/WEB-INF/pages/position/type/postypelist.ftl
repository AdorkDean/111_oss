<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>位置类别列表</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<style>
	.table-list tr th {border:#ccc 1px solid;}
	.head-btn-area {float:left;width:99.8%;height:auto;border:#ccc 1px solid;border-bottom:none;}
	#addBut {float:left;margin:10px 0px 10px 10px;}
	</style>
</head> 
<body> 
	
	<div class="head-btn-area">
		<form name="form1" id="subform" action="" method="post">
		 	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
			<input type="hidden" name="channel" value="${channel?if_exists}" id="channel">
		</form>
		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_MOBANLEIXING_ADD">	
		<input type="button" value="添加" class="btn01" name="addBut" onclick="posTypAdd()" id="addBut"/>
		</@security.authorize>
	</div>
	
    <div style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list">
		   	<tr style="background:#dbf1fc;height:40px;" class="content">
		   	 	<th style="width:10%;">序号</th>
		   	 	<th>类别名称</th>
		   	 	<th>创建时间</th>
		   	 	<th>操作</th>
		   	</tr>
		    <tbody> 
		    <#list pw.result as posType>
				 <tr class="content" style="text-align:center;">
				 	<td>${posType.id}</td>
				 	<td>${posType.name}</td>
				 	<td>${posType.createDate?date}</td>
				 	<td align="center">
				 	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_MOBANLEIXING_XIUGAI">	
				 		<input type="button" class="btn01" onclick="window.location='${base}/position/managePositionType!showEditPage.action?id=${posType.id}';" value="编辑" />
				 	</@security.authorize>
				 	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_MOBANLEIXING_SHANCHU">	
				 		<input type="button" class="btn01" id="del-btn-${posType.id}" onclick="deleteData(${posType.id})" value="删除" style="margin-left:5px;"/>
				    </@security.authorize>
				 	</td>
				  </tr>
			 </#list>
			 </tbody> 
	     </table> 
       
     	 <!-- this is paging -->    
         <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
    </div>        
</body>
<script>
    function posTypAdd()
    {
    	window.location.href = "${base}/position/managePositionType!showAddPage.action";
    }
    //删除
    function deleteData(id)
    {
    	if(confirm("您确定要删除该项数据吗?"))
    	{
    		$("#del-btn-"+id).val("..");
    		$("#del-btn-"+id).attr("disabled",true);
			$.ajax(
			{
				url:"${base}/position/managePositionType!deletePosType.action",
				type:"post",
				data:{id:id},
				success:function(data)
				{
					if(data == 0)
					{
					    $alert("warn","已分配有内容，不允许删除！");
					}
					if(data == 1)
					{
						$alert("success","恭喜，数据删除成功！");
						window.setTimeout(function(){
							window.location.reload();
						},1000);
					}
					if(data == 2)
					{
						$alert("warn","数据库异常，数据删除失败！");
					}
					if(data == 3)
					{
						$alert("error","参数传递异常，数据删除失败！");
					}
					$("#del-btn-"+id).val("删除");
    				$("#del-btn-"+id).attr("disabled",false);
				 }
			 });
		 }
    }
</script>
</html>













