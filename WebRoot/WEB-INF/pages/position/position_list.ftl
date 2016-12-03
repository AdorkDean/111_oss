<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <title>位置详情</title> 
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <#include "/WEB-INF/pages/inc/common.ftl">
    <style>
    #searchType {height:25px;background-color:#f5f5f5;border:#cccccc 1px solid;}
    #searchValue {width:250px;height:25px;border:#cccccc 1px solid;margin-left:10px;background-color:#f5f5f5;}
    </style>
</head> 
<body> 
   	<form name="form1" id="subform" action=""  method="POST" enctype="multipart/form-data">
	 	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage" />
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(100)}" id="pageSize" />
		<input type="hidden" name="channel" value="${channel?if_exists}" id="channel" />
		<input type="hidden" name="searchType_hidden" value="${searchType_hidden?default('')}" id="searchType_hidden" />
		<input type="hidden" name="searchValue_hidden" value="${searchValue_hidden?default('')}" id="searchValue_hidden" />
	</form>
	<input type="button" value="添加" class="btn01" name="addBut" onclick="positionAdd(${channel?if_exists})"/>&nbsp;&nbsp;&nbsp;
	<#if isrelease=='1'><input type="button" class="btn01" id="publish-btn" value="发布" name="releaseBut" onclick="positionFabu()"/>&nbsp;&nbsp;&nbsp;</#if>
	<input type="button" name="button" class="btn01" id="goback" onclick="fanhui()" value="返回" />
	
	
	<input type="text" id="searchValue" value="${searchValue_hidden?default('')}"/>
	<select id="searchType" onchange="initPage()">
		
		<option value="all">全部</option>
		
		<#if rule.img.ishow=='1'>
			<option value="img_url" <#if searchType_hidden?exists> <#if searchType_hidden == 'img_url'>selected</#if> </#if>>${rule.img.name?if_exists}</option>
		</#if>
		
		<#if rule.img_url.ishow=='1'>
			<option value="img_link" <#if searchType_hidden?exists> <#if searchType_hidden == 'img_link'>selected</#if> </#if>>${rule.img_url.name?if_exists}</option>
		</#if>
		
		<#if rule.txt.ishow=='1'>
			<option value="txt" <#if searchType_hidden?exists> <#if searchType_hidden == 'txt'>selected</#if> </#if>>${rule.txt.name?if_exists}</option>
		</#if>
		
		<#if rule.txt_url.ishow=='1'>
			<option value="txt_link" <#if searchType_hidden?exists> <#if searchType_hidden == 'txt_link'>selected</#if> </#if>>${rule.txt_url.name?if_exists}</option>
		</#if>
		
		<#if rule.title.ishow=='1'>
			<option value="title" <#if searchType_hidden?exists> <#if searchType_hidden == 'title'>selected</#if> </#if>>${rule.title.name?if_exists}</option>
		</#if>
		
		<#if rule.title_url.ishow=='1'>
			<option value="title_url" <#if searchType_hidden?exists> <#if searchType_hidden == 'title_url'>selected</#if> </#if>>${rule.title_url.name?if_exists}</option>	
		</#if>
		
		<#if rule.title_all.ishow=='1'>
			<option value="title_all" <#if searchType_hidden?exists> <#if searchType_hidden == 'title_all'>selected</#if> </#if>>${rule.title_all.name?if_exists}</option>	
		</#if>
		
		<#if rule.price.ishow=='1'>
			<option value="price" <#if searchType_hidden?exists> <#if searchType_hidden == 'price'>selected</#if> </#if>>${rule.price.name?if_exists}</option>	
		</#if>
		
		<#if rule.price_url.ishow=='1'>
			<option value="price_url" <#if searchType_hidden?exists> <#if searchType_hidden == 'price_url'>selected</#if> </#if>>${rule.price_url.name?if_exists}</option>	
		</#if>
		
		<#if rule.market_price.ishow=='1'>
			<option value="market_price" <#if searchType_hidden?exists> <#if searchType_hidden == 'market_price'>selected</#if> </#if>>${rule.market_price.name?if_exists}</option>	
		</#if>	
	</select>
	<input type="button" name="button" class="btn01" id="goback" value="检索" onclick="searchEvents()"/>
	
    <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
    <div class="sm_user"> 
    <div> 
    <table class="table-list" style="text-align:center;">
    <thead style="background:#dbf1fc"> 
	   	 <tr style="height:30px;" class="content">
	   	 	<th style="width:6%;"><input type="checkbox" name="checkAll" id="selectAll" onclick="selectAll()" value="" title="全选/反选"/> &nbsp;序号</th>
	   	 	<th>ID</th>
	   	 	<#if rule.img.ishow=='1'>
			<th>${rule.img.name?if_exists}</th>
			</#if>
			<#if rule.img_url.ishow=='1'>
			<th>${rule.img_url.name?if_exists}</th>
			</#if>
			<#if rule.txt.ishow=='1'>
			<th >${rule.txt.name?if_exists}</th>
			</#if>
			<#if rule.txt_url.ishow=='1'>
			<th>${rule.txt_url.name?if_exists}</th>
			</#if>
			<#if rule.title.ishow=='1'>
			<th>${rule.title.name?if_exists}</th>
			</#if>
			<#if rule.title_url.ishow=='1'>
			<th>${rule.title_url.name?if_exists}</th>	
			</#if>
			<#if rule.title_all.ishow=='1'>
			<th>${rule.title_all.name?if_exists}</th>	
			</#if>
			<#if rule.price.ishow=='1'>
			<th>${rule.price.name?if_exists}</th>	
			</#if>
			<#if rule.price_url.ishow=='1'>
			<th >${rule.price_url.name?if_exists}</th>	
			</#if>
			<#if rule.market_price.ishow=='1'>
			<th >${rule.market_price.name?if_exists}</th>	
			</#if>	
	   	 	<th >权重</th>
	   	 	<th style="width:100px;">操作</th>
	   	 </tr>
	   </thead> 
          <tbody> 
	   	  <#list pw.result as position>
			 <tr class="content">
			 	<td ><input type="checkbox" name="psid"  value="${position.id}"/> &nbsp;&nbsp;&nbsp;${position_index+1}
			 	<#if position.issue==0><br/><span style="color:gray;">未发布</span><#elseif position.issue==1><br/><span style="color:red;">已发布</span></#if>
			 	</td>
			 	<td>
			 	  ${position.id?if_exists}
			 	</td>
			 	<#if rule.img.ishow=='1'>
			 		<td ><#if (position.imgUrl)?default("")!=""><a href="${position.imgUrl?if_exists}" target="_blank"><img  style="width: 80px;height:80px;" src="${position.imgUrl?if_exists}" alt="暂无图片"/></a></#if></td>
			 	</#if>
			 	<#if rule.img_url.ishow=='1'>
					<td style="width:8%;">${position.imgLink?if_exists} </td>
				</#if>
				<#if rule.txt.ishow=='1'>
					<td>${position.txt?if_exists}</td>
				</#if>
				<#if rule.txt_url.ishow=='1'>
					<td>${position.txtLink?if_exists}</td>
				</#if>
				<#if rule.title.ishow=='1'>
					<td>${position.title?if_exists}</td>
				</#if>
				<#if rule.title_url.ishow=='1'>
					<td style="width:10%;">${position.titleUrl?if_exists}</td>
				</#if>	
				<#if rule.title_all.ishow=='1'>
			         <td style="width:100px;">${position.titleAll?if_exists}</td>	
			     </#if>
			     <#if rule.price.ishow=='1'>
					<td>${position.price?if_exists}</td>	
				 </#if>
				 <#if rule.price_url.ishow=='1'>
					<td>${position.priceUrl?if_exists}</td>	
				 </#if>
			     <#if rule.market_price.ishow=='1'>
					<td>${position.marketPrice?if_exists}</td>	
			     </#if>
				<td>${position.weight?if_exists}</td>
				<td>
					<input type="button" class="btn01" id="del-btn-${position.id}" onclick="javascript:positionDel(${position.id})" value="删除" /> 
					<input type="button" class="btn01" onclick="window.location='${base}/position/managePosition!addPosition.action?channel=${position.channel}&id=${position.id}'" value="编辑" style="margin-top:7px;"/>
					<input type="button" class="btn01" id="copy_btn_${position.id}" onclick="javascript:positionCopy(${position.id})" value="复制" style="margin-top:5px;"/> 
			 	</td>
			 </tr>
			 </#list>
			 </tbody> 
     </table>   
     <br/> 
     
    <!-- this is paging -->
    <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
    </div> 
    </div> 
    </div>        
</body>
<script>
	//初始化页面
	function initPage()
	{
		var searchType = $.trim($("#searchType").val());
		if(searchType == 'all')
		{
			$("#searchType_hidden").val('');
			$("#searchValue_hidden").val('');
			$("#subform").submit();
		}
	}
	//搜索事件
	function searchEvents()
	{
		var searchValue = $.trim($("#searchValue").val());
		if(searchValue == '' || searchValue == null || searchValue == undefined)
		{
			$alert("warn","请输入搜索关键字！");
			return;
		}
		var searchType = $.trim($("#searchType").val());
		$("#searchType_hidden").val(searchType);
		$("#searchValue_hidden").val(searchValue);
		$("#subform").submit();
	}
    function  fanhui()
    {
      window.location="${base}/position/position!selectPostionList.action"
    }
    //跳转到模板数据添加页面
    function positionAdd(channel)
    {
       window.location="${base}/position/managePosition!addPosition.action?channel="+channel;
    }
    //删除
    function positionDel(psid)
    {
	    if(confirm("您确定要删除以上数据吗?"))
	    {
	    	$("#del-btn-"+psid).val("..");
    		$("#del-btn-"+psid).attr("disabled",true);
			$.ajax(
			{
				url:"${base}/position/managePosition!deletePositionInfo.action",
				type:"post",
				data:{pids:psid},
				success:function(data)
				{
					if(data==1)
					{
						$alert("success","恭喜，数据删除成功！");
						window.setTimeout(function(){
							window.location.reload();
						},1000);
					}
					else
					{
						$alert("error","数据删除失败！");
					}
					$("#del-btn-"+psid).val("删除");
    				$("#del-btn-"+psid).attr("disabled",false);
				}
			});
		 }
    }
    //复制数据
    function positionCopy(psid)
    {
	    if(confirm("您确定要复制该数据吗?"))
	    {
	    	$("#copy_btn_"+psid).val("..");
    		$("#copy_btn_"+psid).attr("disabled",true);
			$.ajax(
			{
				url:"${base}/position/managePosition!copyPositionInfo.action",
				type:"post",
				data:{pids:psid},
				success:function(data)
				{
					if(data == 1)
					{
						$alert("success","恭喜，数据复制成功！");
						window.setTimeout(function(){
							window.location.reload();
						},1000);
					}
					else
					{
						$alert("error","数据复制失败！");
					}
					$("#copy_btn_"+psid).val("复制");
    				$("#copy_btn_"+psid).attr("disabled",false);
				}
			});
		 }
    }
    //发布
    function positionFabu()
    {
		var psids = "";
		var conut = 0;
		$("input[name=psid]:checked").each(function()
		{
			if($.trim($(this).val()).length>0)
			{
				conut++;
			}
			psids+=$(this).val()+',';
		});
		var psid=psids.substring(0,psids.length-1);
		if(conut<=0)
		{
			$alert("warn","请至少选择一条数据进行发布！");
			return false;
		}
		else
		{
			if(confirm("您确定要发布新页面吗?"))
	    	{
				var channel=$("#channel").val();
				$("#publish-btn").val("..");
	    		$("#publish-btn").attr("disabled",true);
				$.ajax(
				{
					url:"${base}/position/managePosition!releasePage.action",
					type:"post",
					data:{"pids":psid,"channel":channel},
					success:function(data)
					{
						if(data==1)
						{
							$alert("success","恭喜，页面发布成功！");
							window.setTimeout(function(){
								window.location.reload();
							},1000);
						}
						else
						{
							$alert("error","页面发布失败！");
						}
						$("#publish-btn").val("发布");
	    				$("#publish-btn").attr("disabled",false);
					}
				});
			}
		 }
     }
//全选、取消全选的事件
function selectAll()
{
	if($("#selectAll").attr("checked")) 
	{
		$(":checkbox").attr("checked", true);
	} 
	else 
	{
		$(":checkbox").attr("checked", false);
	}
}
</script>
</html>