<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>意见反馈</title> 
<link rel="stylesheet" href="${base}/web/css/zdfcss/style.css" type="text/css">
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
</head>
<body>
<div class="main">
     <form name="form1" id="subform"  action="${base}/feedback/feedback!feedbackList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(5)}" id="pageSize">		
	    <input type="hidden" name="collect" value="${collect?if_exists}" id="collect">		
	  </form>	
	  

		<table id="showtable" border="1" cellpadding="0" cellspacing="1" width="100%" class="w_td" >
		<tr align="center" style="height:45px;font-weight:bold" >
		<td style="width:5%">序号</td><td style="width:40%">内容</td>
		<td style="width:10%">联系方式</td>
		<td style="width:400px">客户端类型</td>
		<#--
		<td>分辨率</td>
		-->
		<td style="width:100px" >版本号</td>
		<td style="width:300px" >时间</td>
		<td style="width:200px">操作</td>
		</tr>
		<#list pw.result as feedback>
		<tr align="center" style="height:40px" >
		<td >${feedback_index+1}</td>
		<td >${feedback.message?default('--')}</td>
		<td>${feedback.fromPhone?default('--')}</td>
		<td>${feedback.clientType?default('--')}</td>
		<#--
		<td>${feedback.fenbielv?default('--')}</td>
		-->
		<td>${feedback.clientVersion?default('--')}</td>
	     <td>${feedback.createDate?string('yyyy-MM-dd HH:mm:ss')}</td>
		<td align="center">
		<span id="collect_${feedback.id?default(0)}">
		<#if feedback.collect==0>
		<a   onclick="collect('${feedback.id?default(0)}','1')">
		收藏
		</a><#else>
		<a  onclick="collect('${feedback.id?default(0)}','0')" >
		取消收藏
		</a></#if></span></td>
		</tr>
		</#list>
		<tr>
		<td colspan="7">
		 <div class="page mr3">
        <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
    </div>
		</td>
		</tr>
		
		<table>

    

</div>

</body>
<script>
function collect(id,type){
    $.post("${base}/feedback/feedback!collect.action",{id:id,type:type,random:Math.random()},function(data){
          if(data==1){
	            window.location.reload();
          }
    });
}

</script>
</html>
