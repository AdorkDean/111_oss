<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>生成静态化页面</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<script src="${base}/web/js/datePicker/WdatePicker.js"></script>
	<style>
	* {margin:0px;padding:0px;}
	.body-ui {float:left;width:99.3%;height:auto;border:#cccccc 1px solid;padding-bottom:20px;}
	.m-contents select {float:left;width:190px;height:25px;background-color:#f5f5f5;border:#ccc 1px solid;}
	.m-contents select:focus {outline:none;}
	.labelnamese {height:25px;line-height:25px;}
	.doc-ui {display:none;float:left;width:150px;height:23px;line-height:23px;border:#ccc 1px solid;border-left:none;border-radius:10px;color:#ccc;text-align:center;}
	.builtsuccess {position:fixed;_position:absolute;border:#ccc 1px solid;background-color:#fff;width:385px;height:50px;box-shadow: 1px 1px 6px #999999;top:15%;left:35%;border-radius:5px;}
	.builtsuccess img {float:left;margin-top:10px;margin-left:15px;}
	.builtsuccess span.r {float:left;height:50px;line-height:50px;margin-left:4px;}
	#builtnum {font-family:'Arial';font-weight:normal;}
	#btime {font-family:'Arial';font-weight:normal;}
	.loading-ui {display:none;position:fixed;_position:absolute;width:100%;height:auto;text-align:center;z-index:99;}
	.loading-ui img {width:35px;height:35px;margin:auto;margin-top:180px;}
	#beginDate {float:left;width:188px;height:25px;margin-left:0px;}
	#endDate {float:left;width:188px;height:25px;margin-left:0px;}
	.mask-ui {display:none;position:fixed;_position:absolute;width:100%;height:100%;top:0;left:0px;background-color:#000;opacity:.1;filter:alpha(opacity=10);z-index:5;}
	.progressbar {display:none;float:left;width:212px;height:15px;margin:auto;border:green 1px solid;margin-left:140px;margin-top:20px;}
	.percet {display:none;float:left;width:50px;height:25px;line-height:25px;margin:auto;margin-top:15px;margin-left:5px;}
	#pbr {float:left;width:auto;height:15px;margin:auto;background:green;}
	</style>
</head> 
<body style="overflow:hidden;"> 
	<div class="body-ui">
		<div class="m-contents" style="margin-top:15px;">
	    	<div class="labelnamese">选择生成类型：</div>
	    	<select id="staticType">
	    		<option value="0">请选择</option>
	    		<#list pw.result as sta>
		    		<option value="${sta.id}-${sta.staticMehtod}">${sta.staticName}</option>
	    		</#list>
	    	</select>
	    	<span class="doc-ui" style="display:block;width:180px;margin-left:5px;border:#dcdcdc 1px solid;color:#ccc;text-align:left;padding-left:10px;">*请选择生成类型</span>
    	</div>
    	
		<div class="m-contents" style="margin-top:15px;">
	    	<div class="labelnamese">选择商品分类：</div>
	    	<select id="categoryType">
	    		<option value="0">全部商品分类</option>
			<#list allcates as cate>
					<#if cate.category_level == 2>
					<#assign sid = cate.id>
									<option value="${cate.id}">
				                    ${cate.category_name}
					        		<#list allcates as cate3>
					        		<#if cate3.category_level == 3 && cate3.parent_id == sid>
					        		<#assign did = cate3.id>
										<option value="${cate3.id}">
				                    	&nbsp;&nbsp;&nbsp;${cate3.category_name}
						                	<#list allcates as cate4>
						                	<#if cate4.category_level == 4 && cate4.parent_id == did>
						                		<option value="${cate4.id}">
				                    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cate4.category_name}
						                	</#if>
						                	</#list>
					            	</#if>
					            	</#list>
					            	</option>
					</#if>
			</#list>
	    	</select>
	    	<span class="doc-ui" style="display:block;width:180px;margin-left:5px;border:#dcdcdc 1px solid;color:#ccc;text-align:left;padding-left:10px;">请选择商品分类</span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">起时日期：</div>
	    	<input type="text" name="" class="i-text-i" id="beginDate" value="" onfocus="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});"/>
	    	<span class="doc-ui" style="display:block;width:180px;margin-left:5px;border:#dcdcdc 1px solid;color:#ccc;text-align:left;padding-left:10px;">留空则从最早的内容开始生成</span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">结束日期：</div>
	    	<input type="text" name="" class="i-text-i" id="endDate" value="" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
	    	<span class="doc-ui" style="display:block;width:180px;margin-left:5px;border:#dcdcdc 1px solid;color:#ccc;text-align:left;padding-left:10px;">留空则生成至最后的内容</span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:30px;margin-left:128px;">
    	    <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XITONGGUANLI_SHENGCHENGJINGTAIHUA_QUEDING">
	    	<input type="button" class="btn01" id="sureStatic" value="确定" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	
	    	<!--input type="button" class="btn01" value="重置" style="margin-left:5px;" onclick="window.location.href='${base}/static/static!showstaticp.action'"/-->
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XITONGGUANLI_SHENGCHENGJINGTAIHUA_JIANCE">
	    	<input type="button" class="btn01" id="checkfile" value="检测" style="margin-left:5px;"/>
	    	</@security.authorize>
	    	<span class="doc-ui" id="doc-ui">检测模板文件是否存在？</span>
    	</div>
		<div class="loading-ui"><img src="../web/images/loading_img.jpg"/></div>
		<!-- this is progress bar -->
    	<div class="progressbar">
    		<span id="pbr"></span>
    	</div>
    	<div class="percet">0%</div>
	</div>
	
	<!-- this is tip box -->
	<div class="builtsuccess" id="successId" style="display:none;width:360px;">
		<img src="../web/images/success.gif" style="margin-top:10px;"/>
		<span class="r" style="margin-left:0px;">生成静态成功 （生成数量：<b id='builtnum'>1</b>）（耗时：<b id='btime'>1</b>）</span>
	</div>
	<div class="builtsuccess" id="failId" style="display:none;">
		<img src="../web/images/error.png" style="margin-top:8px;"/>
		<span class="r"><span class='errorH'>生成静态失败</span> （生成数量：<b id='builtnum' class="builtnum">0</b>&nbsp;&nbsp;耗时：<b id='btime' class="btime">1</b>）</span>
	</div>
    <div class="mask-ui"></div>
    
    <script>
    $(function(){
    	$("#checkfile").hover(function()
    	{
    		$("#doc-ui").css(
    		{
	    		"position":"fixed",
	    		"top":$("#checkfile").offset().top,
	    		"left":$("#checkfile").offset().left+50
    		}
    		).fadeIn();
    	},
    	function()
    	{
    		$("#doc-ui").fadeOut();
    	});
    	$("#sureStatic").click(function()
        {
	    	build();
        });
    });
    
    /**检查模板文件是否存在*/
    document.getElementById("checkfile").onclick = function()
    {
    	var $staticType = $.trim($("#staticType").val());
    	var $array = $staticType.split("-");
    	if($staticType == 0)
    	{
    		$alert("warn","请选择生成类型！");
    		return false;
    	}
    	$("#checkfile").val("检测").attr("disabled",true);
    	$.ajax(
		{
			url:"${base}/static/static!isfilexist.action?id="+$array[0],
			type:"post",
			success:function(data)
			{
				if(data == 0)
				{
					$alert("success","模板文件存在！");
				}
				if(data == 1)
				{
					$alert("error","模板文件不存在！");
				}
				if(data == 2)
				{
					$alert("warn","参数传递异常！");
				}
				$("#checkfile").val("检测").attr("disabled",false);
			},
			error:function()
			{
				$alert("error","网络异常！");
				$("#checkfile").val("检测").attr("disabled",false);
			}
		});
		$("#doc-ui").fadeOut();
    };
    
    //商品总数
    var $total = 0;
    //分页参数
	var $offSet = 0;
	//控制程序临时变量
	var $tParam = 0;
	//所需总时间
	var $spendTime = 0;
	//生成数量
	var $buildnum = 0;
	
    //确定按钮点击事件
	function build()
	{
		//参数判断及分割成数组
		var $staticType = $.trim($("#staticType").val());
		var $array = $staticType.split("-");
    	if($staticType == 0)
    	{
    		$alert("warn","请选择生成类型！");
    		return false;
    	}
    	//条件查询参数
    	var $categoryType = $.trim($("#categoryType").val());
    	var $beginDate = $.trim($("#beginDate").val());
    	var $endDate = $.trim($("#endDate").val());
    	//显示加载图标
    	if($tParam == 0)
    	{
			init();
    	}
    	var newUrl = "${base}"+$array[1]+"?id="+$array[0]+"&categoryType="+$categoryType+"&beginDate="+$beginDate+"&endDate="+$endDate+"&offSet="+$offSet+"&tParam="+$tParam+"&total="+$total+"&buildnum="+$buildnum;
    	if($array[1].indexOf("?") > 0)
    	{
    		newUrl = "${base}"+$array[1]+"&id="+$array[0]+"&categoryType="+$categoryType+"&beginDate="+$beginDate+"&endDate="+$endDate+"&offSet="+$offSet+"&tParam="+$tParam+"&total="+$total+"&buildnum="+$buildnum;
    	}
    	//与服务器交互
    	$.ajax(
		{
			url:newUrl,
			type:"post",
			success:function(data)
			{
			    var jsons = eval(data);
		    	$spendTime += jsons[0].buildTime;
		    	$tParam = 1;
		    	$buildnum = jsons[0].count;
		    	$offSet += 5;
		    	$total = jsons[0].total;
		    	//计算滚动条
		    	var plg = (212*$buildnum)/$total;
		    	$("#pbr").css({"width":plg});
		    	$(".percet").html(((plg/212)*100).toFixed(3)+"%");
				if($("#pbr").width() >= 212)
				{
					$("#pbr").css({"width":212});
				}
			    //生成静态化未完成
			    if(jsons[0].iscompleted == false)
			    {
			    	build();
			    }
			    //已完全生成静态化
			    else
			    {
				    //耗时
				    if($spendTime < 60000) 
				    {
						$spendTime = ($spendTime / 1000).toFixed(3) + "秒";
					} 
					else 
					{
						$spendTime = ($spendTime / 60000).toFixed(3) + "分";
					}
					//生成静态化成功				
				    if(jsons[0].result == 0)
				    {
						$("#successId").fadeIn();
						$("#builtnum").html($buildnum);
						$("#btime").html($spendTime);
				    	window.setTimeout(function()
				    	{
				    		$("#successId").fadeOut();
				    	},
				    	3000);
				    	initParam();
				    }
				    //此类别下暂时没有添加商品
				    if(jsons[0].result == 1)
				    {
				    	$("#failId").fadeIn();
						$(".errorH").html('此类别下暂时没有添加商品');
						$(".builtnum").html($buildnum);
						$(".btime").html($spendTime);
				    	window.setTimeout(function()
				    	{
				    		$("#failId").fadeOut();
				    	},
				    	3000);
				    	initParam();
				    }
				    //此日期之间没有任何商品
				    if(jsons[0].result == 2)
				    {
				    	$("#failId").fadeIn();
						$(".errorH").html('此日期之间没有任何商品');
						$(".builtnum").html($buildnum);
						$(".btime").html($spendTime);
				    	window.setTimeout(function()
				    	{
				    		$("#failId").fadeOut();
				    	},
				    	3000);
				    	initParam();
				    }
				    //传递参数为空操作错误
				    if(jsons[0].result == 3)
				    {
				    	$("#failId").fadeIn();
						$(".errorH").html('传递参数为空操作错误');
						$(".builtnum").html($buildnum);
						$(".btime").html($spendTime);
				    	window.setTimeout(function()
				    	{
				    		$("#failId").fadeOut();
				    	},
				    	3000);
				    	initParam();
				    }
				    //网络异常
				    if(jsons[0].result == 4)
				    {
				    	$("#failId").fadeIn();
						$(".errorH").html('网络异常');
						$(".builtnum").html($buildnum);
						$(".btime").html($spendTime);
				    	window.setTimeout(function()
				    	{
				    		$("#failId").fadeOut();
				    	},
				    	3000);
				    	initParam();
				    }
				    rec();
			    }
			},
			error:function()
			{
				$alert("error","网络异常！");
				rec();
			}
		});
	}
	//初始化UI
	function init()
	{
		$("#sureStatic").val("确定").attr("disabled",true);
    	$(".loading-ui").fadeIn();
    	$(".mask-ui").fadeIn();
    	$(".progressbar").show();
    	$(".percet").show();
	}
	//恢复UI
	function rec()
	{
		$("#sureStatic").val("确定").attr("disabled",false);
		$(".loading-ui").fadeOut();
    	$(".mask-ui").fadeOut();
    	$(".progressbar").hide();
    	$(".percet").hide();
	}
	//滚动条函数
	function progress()
	{   
		var h = 5;
		var ct = window.setInterval(function(){
		    h += 5;
			$("#pbr").css({"width":h});
			if($("#pbr").width() >= 212){
				window.clearInterval(ct);
				$("#pbr").css({"width":212});
			}
		},1000);
	}
	//初始化变量
	function initParam()
	{
		$spendTime = 0;
		$offSet = 0;
		$buildnum = 0;
		$tParam = 0;
	}
    </script>
    
</body>	
</html>













