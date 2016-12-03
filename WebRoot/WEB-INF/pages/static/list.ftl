<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>静态化资源列表</title> 
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
		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XITONGGUANLI_JINGTAIHUAZIYUAN_LIEBIAO_TIANJIA">
		<input type="button" value="添加" class="btn01" name="addBut" onclick="add()" id="addBut"/>
		</@security.authorize>
		
	</div>
	
    <div style="font-family:'Microsoft Yahei';float:left;margin-top:-10px;"> 
	    <table class="table-list">
		   	<tr style="background:#dbf1fc;height:40px;" class="content">
		   	 	<th style="width:4%;">序号</th>
		   	 	<th style="width:8%;">静态化名称</th>
		   	 	<th>静态化方法</th>
		   	 	<th>模板名称</th>
		   	 	<th style="width:10%;">模板路径</th>
		   	 	<th style="width:20%;">输出全路径</th>
		   	 	<th style="width:8%;">创建时间</th>
		   	 	<th style="width:12%;">操作</th>
		   	</tr>
		    <tbody> 
		    <#list pw.result as sta>
				 <tr class="content" style="text-align:center;">
				 	<td>${sta.id}</td>
				 	<td>${sta.staticName}</td>
				 	<td>${sta.staticMehtod}</td>
				 	<td>${sta.templateName}</td>
				 	<td>${sta.templatePath}</td>
				 	<td>${sta.outPath}</td>
				 	<td>${sta.createDate?date}</td>
				 	<td align="center">
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XITONGGUANLI_JINGTAIHUAZIYUAN_LIEBIAO_BIANJI">
				 		<input type="button" class="btn01" onclick="window.location='${base}/static/static!edit.action?id=${sta.id}';" value="编辑" />
				 		</@security.authorize>
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XITONGGUANLI_JINGTAIHUAZIYUAN_LIEBIAO_SHANCHU">
				 		<input type="button" class="btn01" id="del-btn-${sta.id}" onclick="del(${sta.id})" value="删除" style="margin-left:5px;"/>
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
	function add()
	{
		window.location.href = "/static/static!add.action";
	}
    //删除
    function del(id)
    {
    	if(confirm("您确定要删除该项数据吗?"))
    	{
    		$("#del-btn-"+id).val("..").attr("disabled",true);
			$.ajax(
			{
				url:"${base}/static/static!delete.action",
				type:"post",
				data:{id:id},
				success:function(data)
				{
					if(data == 0)
					{
					    $alert("success","数据删除成功！");
					    window.setTimeout(function()
					    {
							window.location.reload();
						},1000);
					}
					if(data == 1)
					{
						$alert("error","数据库异常！");
						
					}
					if(data == 2)
					{
						$alert("error","参数传递异常！");
					}
					$("#del-btn-"+id).val("删除").attr("disabled",false);
				 }
			 });
		 }
    }
</script>
</html>













