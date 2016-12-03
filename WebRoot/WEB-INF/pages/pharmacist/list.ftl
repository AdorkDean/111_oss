<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>医师点评列表</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<style>
	.table-list tr th {border:#ccc 1px solid;}
	.head-btn-area {float:left;width:99.8%;height:auto;border:#ccc 1px solid;border-bottom:none;}
	#addBut {float:left;margin:10px 0px 10px 10px;}
	.search-btn {float:left;width:200px;height:25px;border:#ccc 1px solid;margin:9px 0px 0px 10px;background-color:#f5f5f5;border-radius:5px;}
	.search-btn:focus {outline:none;}
	#searchType {float:left;width:80px;height:26px;border:#ccc 1px solid;margin-top:10px;margin-left:5px;background:#f5f5f5;}
	.btn01 {font-size:12px;}
	</style>
</head> 
<body> 
	
	<div class="head-btn-area">
		<form name="form1" id="subform" action="" method="post">
		 	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
			<input type="hidden" name="channel" value="${channel?if_exists}" id="channel">
			<input type="text" id="goodsno" name="goodsno" value="${goodsno?default('')}" placeholder="请输入海典编码" class="search-btn" autocomplete="off"/>
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YAOSHIGUANLI_YAOSHIDIANPING_LIEBIAO_CHAXUN">
			<input type="submit" value="查询" class="btn01" name="addBut" onclick="posTypAdd()" id="addBut"/>
			</@security.authorize>
		</form>
	</div>
	
    <div style="font-family:'Microsoft Yahei';float:left;margin-top:-10px;"> 
	    <table class="table-list">
		   	<tr style="background:#dbf1fc;height:40px;" class="content">
		   	 	<th style="width:5%;">序号</th>
		   	 	<th>医师头像</th>
		   	 	<th>医师姓名</th>
		   	 	<th>商品</th>
		   	 	<th>海典编号</th>
		   	 	<th>点评内容</th>
		   	 	<th>是否显示</th>
		   	 	<th>点评时间</th>
		   	 	<th>操作</th>
		   	</tr>
		    <tbody> 
		    <#list pw.result as pharmacist>
				 <tr class="content" style="text-align:center;">
				 	<td>${pharmacist.id}</td>
				 	<#if pharmacist.phyheaderurl != ''>
				 		<td><img src="${ui1}${pharmacist.phyheaderurl}" style='width:50px;height:50px;'/></td>
				 	</#if>
				 	<#if pharmacist.phyheaderurl == ''>
				 		<td><img src="http://www.111yao.com/upload/image/300.jpg" style='width:50px;height:50px;'/></td>
				 	</#if>
				 	<td>${pharmacist.realname}</td>
				 	<td>
				 		<a href="http://www.111yao.com/p/${pharmacist.goodsid}.html" target="_blank">${pharmacist.shortname}</a>
				 	</td>
				 	<td>${pharmacist.goodsno}</td>
				 	<td>${pharmacist.content}</td>
				 	<td><#if pharmacist.isview == '0'>是<#else>否</#if></td>
				 	<td>${pharmacist.ctime?datetime}</td>
				 	<td>
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YAOSHIGUANLI_YAOSHIDIANPING_LIEBIAO_BIANJI">
				 		<input type="button" class="btn01" onclick="pcomment(${pharmacist.goodsid},'${pharmacist.shortname}')" value="编辑" style="float:left;margin-left:0px;"/>
				 		</@security.authorize>
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YAOSHIGUANLI_YAOSHIDIANPING_LIEBIAO_SHANCHU">
				 		<input type="button" class="btn01" id="del-btn-${pharmacist.id}" onclick="deleteData(${pharmacist.id})" value="删除" style="float:left;margin-left:0px;"/>
				 		</@security.authorize>
				 	</td>
				  </tr>
			 </#list>
			 </tbody> 
	     </table> 
       
     	 <!-- this is paging -->    
         <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
    </div>
    
    	<style>
	.mask-ui {position:fixed;_position:absolute;width:100%;height:100%;left:0;top:0;right:0;bottom:0;background-color:#000000;opacity:.5;filter(opacity=50);}
	.title-ui {float:left;width:100%;height:40px;line-height:40px;background:#f5f5f5;border-top-left-radius:5px;border-top-right-radius:5px;}
	.title-con {float:left;margin-left:15px;font-size:15px;font-family:'microsoft yahei';font-weight:bold;width:410px;overflow: hidden;white-space:nowrap;text-overflow:ellipsis;-o-text-overflow:ellipsis;}
	.doctor-ui {position:fixed;_position:absolute;background-color:#ffffff;width:450px;height:auto;top:5%;left:30.5%;border-radius:5px;font-style:normal;}
	.doc-line {float:left;width:100%;height:120px;margin-top:15px;}
	.doc-line-1 {float:left;width:120px;height:120px;line-height:100px;text-align:center;font-size:12px;font-family:'microsoft yahei';}
	.tarea {float:left;width:295px;height:116px;border:#ccc 1px solid;resize:none;border-radius:5px;}
	.isdisplay {float:left;width:100%;height:30px;margin-top:10px;}
	.isdisplay-c {float:left;width:120px;height:30px;line-height:30px;text-align:center;font-size:12px;font-family:'microsoft yahei';}
	.cb1 {float:left;height:30px;margin-left:0px;}
	.cb1-v {float:left;widht:30px;height:20px;margin-left:5px;margin-top:5px;}
	.select-docr {float:left;width:100%;height:auto;margin-top:10px;}
	.select-docr-c {float:left;width:120px;height:auto;line-height:30px;text-align:center;font-size:12px;font-family:'microsoft yahei';}
	.docr-area {float:left;width:315px;height:auto;line-height:35px;}
	.btn-div {float:left;width:100%;height:60px;margin-top:10px;border-top:#f5f5f5 1px solid;}
	.btn01 {width: 50px;height: 25px;background: url(${base}/web/images/16.jpg);color: #fff;cursor: pointer;letter-spacing: 5px;float: left;margin-left: 5px;margin-right: 5px;border:none;}
	</style>
	<!-- 医师点评输入框 -->
	<div class="mask-ui" style="display:none;" id="pcommentid1"></div>
	<div class="doctor-ui" style="display:none;" id="pcommentid2">
		<div class="title-ui"><span class="title-con"></span></div>
		<div class="doc-line">
			<div class="doc-line-1">点评内容：</div>
			<textarea class="tarea"></textarea>
		</div>
		<div class="isdisplay">
			<div class="isdisplay-c">是否显示：</div>
			<input type="radio" value="0" name="ro" class="cb1" id="ro0"/><span class="cb1-v">是</span>
			<input type="radio" value="1" name="ro" class="cb1" id="ro1" style="margin-left:15px;"/><span class="cb1-v">否</span>
		</div>
		<div class="select-docr">
			<div class="select-docr-c">医师选择：</div>
			<div class="docr-area">
			</div>
		</div>
		<div class="btn-div">
			<input type="button" id="submit-btn" value="提交" class="btn01" onclick="javascript:submitAction()" style="margin-left:165px;margin-top:10px;"/>
			<input type="button" id="buttonsub" value="取消" class="btn01" onclick="javascript:cancelDiv()" style="margin-left:15px;margin-top:10px;"/>
		</div>
		<input type="hidden" id="pha_phy_good_id" value=""/>
		<input type="hidden" id="goodsid" value=""/>
	</div>
	<script>
	function pcomment(goodsid,short_name)
	{
			/*显示遮罩层*/
			$("#pcommentid1").fadeIn();
			$("#pcommentid2").fadeIn();
			/*初始化数据*/
			$(".tarea").val('');
			$(".docr-area").html('');
			$(".title-con").html(short_name);
			$("#pha_phy_good_id").val('');
			$("#goodsid").val('');
			$("#goodsid").val(goodsid);
			$("#ro0").attr('checked', '');
			$("#ro1").attr('checked', '');
			/*点评信息*/
			var url = "${base}/phcm/phcmn!phamnpro.action?goodsid="+goodsid;
			var $pctid;
			$.post(url,null,function(data)
			{
			   if(data != '')
			   {
				   var jsonArry = eval(data);
				   for(var i=0;i<jsonArry.length;i++)
				   {
					   $(".tarea").val(jsonArry[i].content);
					   if($("#ro0").val() == jsonArry[i].is_view)
					   {
					      $("#ro0").attr('checked', 'checked');
					   }
					   else
					   {
					   	  $("#ro1").attr('checked', 'checked');
					   }
					   $pctid = jsonArry[i].pctid;
					   $("#pha_phy_good_id").val(jsonArry[i].id);
				   }
			   }
			   /*医师列表*/
				var $url = "${base}/phcm/phcmn!phclist.action";
				$.post($url,null,function(data)
				{
				   if(data != '')
				   {
					   var jsonArry = eval(data);
					   var dataObj = "";
					   for(var i=0;i<jsonArry.length;i++)
					   {
					       var $id = jsonArry[i].id; 
					       var $real_name = jsonArry[i].real_name;
					       var $checked = "";
					       if($pctid == $id)
					       {
					       	   $checked = "checked";	
					       }
					       var br = "";
					       if(i%4 == 0 && i != 0){
					       	   br = "<br/>";
					       }
					       dataObj += br +
					       "<div style='float:left;width:75px;height:30px;overflow:hidden;'><input type='radio' value='"+$id+"' name='doctor' "+$checked+" class='cb1'/><span class='cb1-v' style='float:left;height:30px;width:35px;margin-top:-3px;'>"+$real_name+"</span></div>";
					   }
				   }
				   $(".docr-area").append(dataObj);
				});
			});
		}
		/*点击取消按钮*/
		function cancelDiv()
		{
			$(".mask-ui").fadeOut();
			$(".doctor-ui").fadeOut();
		}
		/*点击提交按钮*/
		function submitAction()
		{
			var $pha_phy_good_id = $.trim($("#pha_phy_good_id").val());
			var $content = $.trim($(".tarea").val());
			var $isView = $.trim($("input[name='ro']:checked").val());
			var $pharmacistId = $("input[name='doctor']:checked").val();
			var $goodsid = $.trim($("#goodsid").val());
			if($content == '' || $content == null)
			{
				$alert("warn","请输入点评内容！");
				return false;
			}
			if($isView == undefined || $isView == '')
			{
				$alert("warn","请选择是否显示！");
				return false;
			}
			if($pharmacistId == undefined || $pharmacistId == '')
			{
				$alert("warn","请选择医师！");
				return false;
			}
			$("#submit-btn").val('..');
			$("#submit-btn").attr('disabled',true);
			var $url = "${base}/phcm/phcmn!savepharmacistcomment.action?goodsid="+$goodsid+"&pharmacistId="+$pharmacistId+"&content="+encodeURI(encodeURI($content))+"&isView="+$isView+"&pha_phy_good_id="+$pha_phy_good_id;
			$.post($url,null,function(data)
			{
				if(data == 0)
				{
					$alert("success","恭喜，修改数据成功！");
				}
				if(data == 1)
				{
					$alert("success","恭喜，保存数据成功！");
				}
				window.setTimeout(function(){
					cancelDiv();
				},1000);
				window.location.reload();
			});
			window.setTimeout(function(){
				$("#submit-btn").val('提交');
				$("#submit-btn").attr('disabled',false);
			},2000);
		}
	</script>
            
</body>
<script>
    //删除
    function deleteData(id)
    {
    	if(confirm("您确定要删除该项数据吗?"))
    	{
    		$("#del-btn-"+id).val("删除");
    		$("#del-btn-"+id).attr("disabled",true);
			$.ajax(
			{
				url:"${base}/phcm/phcmn!delete.action",
				type:"post",
				data:{id:id},
				success:function(data)
				{
					if(data == 0)
					{
					    $alert("success","恭喜，数据删除成功！");
						window.setTimeout(function(){
							window.location.reload();
						},1000);
					}
					if(data == 1)
					{
						$alert("warn","数据库异常，数据删除失败！");
					}
					if(data == 2)
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













