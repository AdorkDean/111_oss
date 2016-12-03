<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>公共列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<link rel="stylesheet" type="text/css" href="${base}/web/css/default.include.b2676e.css" media="all" />
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
	     	<form name="form1" id="form1"  action="${base}/goods/goods!goodsList.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="1" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
			<input type="hidden" name="type" value="${type?default(1)}" id="type">	
	        <div style="float:left;"> 
				<div class="labelName">名称：</div>
	            <input name="goodsf.shortname" value="${goodsf.shortname?default('')}" type="text" class="lname-i" style="border:#cccccc 1px solid;" />
	            <div class="labelName" style="margin-left:5px;">海典编号：</div>
	            <input name="goodsf.goodsno" value="${goodsf.goodsno?default('')}" type="text" class="lname-i" style="border:#cccccc 1px solid;" />
	            <div class="labelName" style="margin-left:5px;">商品类型：
	            <select name="goodsType" style="height:30px;background:#f5f5f5;">
					 <option value="-1">全部</option>
					 <option <#if goodsType?default(-1)==1> selected</#if> value="1">非处方药</option>
					 <option <#if goodsType?default(-1)==2> selected</#if> value="2">处方药</option>
				</select>	 
				</div>
	            <div class="labelName" style="margin-left:5px;">上架状态：
	            <select name="status" style="height:30px;background:#f5f5f5;">
					 <option value="-1">全部</option>
					 <option <#if status?default('-1')=='1'> selected</#if> value="1">上架</option>
					 <option <#if status?default('-1')=='2'> selected</#if> value="2">下架</option>
				</select>
				</div>
	            <div class="labelName" style="margin-left:5px;">平台：</div>
	            <span style="position:relative;top:10px;">
	            <input name="price_type" type="radio" value="pc" <#if price_type?exists><#if price_type=='pc'>checked</#if><#else>checked</#if> />
	            PC
	            <input name="price_type" type="radio" value="wap" <#if price_type?exists><#if price_type=='wap'>checked</#if></#if>/>
	            WAP
	            <input name="price_type" type="radio" value="app" <#if price_type?exists><#if price_type=='app'>checked</#if></#if>/>
	            APP
	            </span>
	            
	            </div>
	            
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_SHANGPINGUANLI_SHANGPINGUANLI_LIEBIAO_CHAXUN">
				<input type="button" class="btn01" onclick="submitForm(1)" value="查询" style="margin-left:15px;margin-top:2px;" />
				</@security.authorize>
				 
				<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_SHANGPINGUANLI_SHANGPINGUANLI_LIEBIAO_TIANJIA">
	            <a href="${base}/goods/goods!goodsAddPage.action" style="margin-left:5px;margin-top:2px;" class="a-button">添加</a>
	            </@security.authorize>
	            <input type="button" class="btn01" onclick="submitForm(2)" value="导出" style="margin-left:15px;margin-top:2px;" />
	            </a>
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list" style="width:100%">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
			   	 	<td align="center">商品编号</td>
			        <td align="center">海典编号</td>
			        <td align="center">商品</td>
			        <td align="center">名称</td>
			        <td align="center">规格</td>
			        <td align="center">SKU_ID</td>
			        <td align="center">成本价</td>
			        <td align="center">PC销售价</td>
			        <td align="center">WAB销售价</td>
			        <td align="center">APP销售价</td>
			        <td align="center">库存</td>
			        <td align="center">是否上架</td>
			        <td align="center">是否发布</td>
			        <td align="center">处方药</td>
			        <td width="18%" align="center">操作</td>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
	   <#list pw.result?if_exists as resc>
		<tr id="tr_record_${resc.id}" style="cursor:hand" onmousemove="xztr(this);">
			<td class="bc" align="center" title="${resc.goodscode?default('')}">
			<#if resc.goodscode?exists>
					${resc.goodscode?default('')}
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.goodsno?default('')}">
			<#if resc.goodsno?exists>
				<#if resc.goodsno != 'null'>
				${resc.goodsno?default('')}
				</#if>
			<#else>
			</#if>
			</td>
			<td class="bc" align="center" id="sit-simple-demo" style="vertical-align:middle;">
			<#if resc.abbreviation_picture?exists>
					<img class="sit-preview" alt="" src="${ui1}${resc.abbreviation_picture?default('')}" data-preview-url="${ui1}${resc.abbreviation_picture?default('')}" style="width:50px;height:50px"/>
			</#if>
			</td>
			
			<td class="bc" align="center" title="${resc.short_name?default('')}">
			<#if resc.short_name?exists>
					${resc.short_name?default('')}
			</#if>
			</td>
			
			<td class="bc" align="center" title="${resc.spec?default('')}">
			<#if resc.spec?exists>
				${resc.spec?default('')}
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.sku_id?default('')}">
			<#if resc.sku_id?exists>
				${resc.sku_id?default('')}
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.price?default('')}">
			<#if resc.price?exists>
				${resc.price?default('')}
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.pc_price?default('')}">
			<#if resc.pc_price?exists>
				${resc.pc_price?default('')}
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.wap_price?default('')}">
			<#if resc.wap_price?exists>
				${resc.wap_price?default('')}
		   <#else>
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.app_price?default('')}">
			<#if resc.app_price?exists>
				${resc.app_price?default('')}
		   <#else>
			</#if>
			</td>
			<td class="bc" align="center" title="${resc.stock?default('')}">
			<#if resc.stock?exists>
				${resc.stock?default('')}
		   <#else>
			</#if>
			</td>
			<td class="bc" align="center">
				PC
				<#if resc.pc_status==2>
					否
				</#if>
				<#if resc.pc_status==1>
					是
				</#if>
				<br>
				WAP
				<#if resc.wap_status==2>
					否
				</#if>
				<#if resc.wap_status==1>
					是
				</#if>
				<br>
				APP
				<#if resc.app_status==2>
					否
				</#if>
				<#if resc.app_status==1>
					是
				</#if>
			</td>
			<td class="bc" align="center">
				<#if resc.is_release==0>
					否
				</#if>
				<#if resc.is_release==1>
					是
				</#if>
			</td>
			<td class="bc" align="center">
				<#if resc.type==2||resc.type==3>
					是
				<#else>
					否
				</#if>
			</td>
			<td class="bc" align="center" style="width:16%">
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_SHANGPINGUANLI_SHANGPINGUANLI_LIEBIAO_CHAKAN">
			<a href="${www1}${resc.id?default('')}.html" target="_blank">查看</a>&nbsp;
			</@security.authorize>
			
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_SHANGPINGUANLI_SHANGPINGUANLI_LIEBIAO_FABU">
			<a href="javascript:;" onclick="release('${resc.id?default('')}','${resc.goodsno?default('')}','${resc.bar_code?default('')}','${resc.approval_number?default('')}','${resc.is_release?default('')}','${resc.manufacturer_id?default('')}','${resc.type?default('1')}');">发布</a>&nbsp;
			</@security.authorize> 
			
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_SHANGPINGUANLI_SHANGPINGUANLI_LIEBIAO_XIUGAI">
			<a href="${base}/goods/goods!goodsGetView.action?id=${resc.id}&shortname=${goodsf.shortname?default('')}&goodsno=${goodsf.goodsno?default('')}">修改</a>&nbsp;
			</@security.authorize> 
			
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_SHANGPINGUANLI_SHANGPINGUANLI_LIEBIAO_TIANJIAGUIGE">
			<a href="javascript:;" onclick="addgg('${resc.id}','${resc.short_name?default('')}');">添加规格</a>&nbsp;
			</@security.authorize> 
			
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_SHANGPINGUANLI_SHANGPINGUANLI_LIEBIAO_DIANPING">
			<a href="javascript:;" onclick="pcomment(${resc.id},'${resc.short_name?default('')}')">点评</a>
			</@security.authorize>
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
    	function submitForm(type) {
		    if(type==2){
		 		$("#type").val(2);
		 	}else{
			    $('#p_curPage').val(1);
			    $("#type").val(1);
		 	}
		    $('#form1').submit();
		    $("#type").val(1);
		}
	function release(id,goodsno,bar_code,approval_number,is_release,manufacturer_id,type){
		if(0==is_release){
			if(goodsno!=''&&manufacturer_id!=''){
				jQuery.ajax({
			       type: "post",
			       url: " ${base}/goods/goods!goodsRelease.action",	  
			       data:{"id":id,"type":type},
			       success: function(data){
			       		if("1"==data){
			       			window.location.reload();
			       			$alert('success','发布成功!');
			       		}else{
			       			$alert('error','网络异常!');
			       		}
			       },error:function(){
			       		$alert('error','网络异常!');
			       }
			       
			   }); 
			}else{
				$alert('warn','请点击修改完善商品信息!');
			}
		}else{
			$alert('warn','商品已发布!');
		}
	}
   function updateStatus(id,flag){
	  jQuery.ajax
	   ({
	       type: "post",
	       url: " ${base}/healthes/health!updateStatus.action",	  
	       data:{"id":id,"flag":flag},
	       success: function(data)
	       {
	          if(data>0){
	          	window.location.reload();
	          }else{
	          	$alert('error','网络异常!');
	          }
	       },error:function(){
	       		$alert('error','网络异常!');
	       }
	       
	   }); 
	}
	</script>
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
	function xztr(obj){
	    var _table=obj.parentNode;
	    for (var i=0;i<_table.rows.length;i++){
	        _table.rows[i].style.backgroundColor="";
	    }    
	    obj.style.backgroundColor="#c1e8e2";
	}
	function addgg(id,name){
		/*显示遮罩层*/
			$("#addggid").fadeIn();
			$("#addggid1").fadeIn();
		$("#spangg").html(name);
		$("#guigeval").val(id);
		$("#ggvs").val("");
	}
	function guigeup(){
		var id = $("#guigeval").val();
		var data = $("#ggvs").val();
		if(data == '' || data == null)
			{
				$alert("warn","请输入规格名称！");
				return false;
			}
		 jQuery.ajax
	   ({
	       type: "post",
	       url: " ${base}/goods/goods!addSpec.action",	  
	       data:{"id":id,"specName":data},
	       success: function(data)
	       {
	          if(data>0)
	          {
	          	$alert('success','添加规格成功！');
				$(".mask-ui").fadeOut();
				$(".doctor-ui").fadeOut();
	          	window.setTimeout(function(){
	          		window.location.reload();
	          	},500);
	          }
	          else
	          {
	          	$alert('error','网络异常!');
	          }
	       },error:function(){
	       		$alert('error','网络异常!');
	       }
	       
	   }); 
	}
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
			});
			window.setTimeout(function(){
				$("#submit-btn").val('提交');
				$("#submit-btn").attr('disabled',false);
			},2000);
		}
		 jQuery(document).ready(function() {
	        jQuery("#sit-simple-demo .sit-preview").smartImageTooltip({previewTemplate: "simple", imageWidth: "480px"});
	        jQuery("#sit-caption-demo .sit-preview").smartImageTooltip({previewTemplate: "caption", scaleDesktop: 70, scaleMobile: 55});
	        jQuery("#sit-envato-demo .sit-preview").smartImageTooltip({previewTemplate: "envato", imageWidth: "500px"});
	        jQuery("#sit-envato-link-demo .sit-preview").smartImageTooltip({previewTemplate: "envato"});
	        jQuery("#sit-nolink-demo img.sit-thumb").smartImageTooltip({previewTemplate: "caption", imageWidth: "480px"});
	    });
	</script>
	<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
	<script type="text/javascript" src="${base}/web/js/enlarge/default.include.c78d4e.js"></script>
<script type="text/javascript" src="${base}/web/js/enlarge/default.include-footer.304291.js"></script>
	<!-- 添加规格 -->
	<div class="mask-ui" style="display:none;" id="addggid"></div>
	<div class="doctor-ui" style="height:180px;display:none;" id="addggid1">
		<div class="title-ui"><span class="title-con" id="spangg"></span></div>
		<div class="doc-line" style="height:auto;">
			<div class="doc-line-1" style="height:40px;line-height:40px;">规格名称：</div>
		    <input type="hidden" id="guigeval"/>
			<textarea class="tarea" style="height:40px;" id="ggvs" maxlength="30"></textarea>
		</div>
		<div class="btn-div">
			<input type="button" id="submit-btn" value="提交" class="btn01" onclick="guigeup();" style="margin-left:165px;margin-top:10px;"/>
			<input type="button" id="buttonsub" value="取消" class="btn01" onclick="javascript:cancelDiv()" style="margin-left:15px;margin-top:10px;"/>
		</div>
		<input type="hidden" id="pha_phy_good_id" value=""/>
		<input type="hidden" id="goodsid" value=""/>
	</div>
	       
</body>
</html>