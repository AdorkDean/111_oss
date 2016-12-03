<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>编辑静态化资源</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 

<body>
	<div class="shop_main" id="shop_main" style="margin-top:0px;margin-left:0px;width:99.3%;">
	<input type="hidden" name="tstatic.id" id="staid" value="${tstatic.id}"/>
    <form action="" method="post" name="addInfo" id="addInfo">
    	<div class="m-contents" style="margin-top:5px;">
	    	<div class="labelnamese">资源名称：</div>
	    	<input type="text" name="tstatic.staticName" class="i-text-i" id="staticName" value="${tstatic.staticName}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">静态化方法：</div>
	    	<input type="text" name="tstatic.staticMehtod" class="i-text-i" id="staticMehtod" value="${tstatic.staticMehtod}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">模板路径：</div>
	    	<input type="text" name="tstatic.templatePath" class="i-text-i" id="templatePath" value="${tstatic.templatePath}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">模板名称：</div>
	    	<input type="text" name="tstatic.templateName" class="i-text-i" id="templateName" value="${tstatic.templateName}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">输出全路径：</div>
	    	<input type="text" name="tstatic.outPath" class="i-text-i" id="outPath" value="${tstatic.outPath}"/>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:30px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" id="submit-btn" onclick="save()" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" onclick="location.href='${base}/static/static!list.action';" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   

</body>

<script>	
//更新数据
function save()
{
	//静态化资源名称
	var $staticName = $.trim($("#staticName").val());
	if($staticName == null || $staticName == '')
	{
		$alert("warn","资源名称不能为空！");
		return false;
	}
	//静态化方法
	var $staticMehtod = $.trim($("#staticMehtod").val());
	if($staticMehtod == null || $staticMehtod == '')
	{
		$alert("warn","静态化方法不能为空！");
		return false;
	}
	//模板路径
	var $templatePath = $.trim($("#templatePath").val());
	if($templatePath == null || $templatePath == '')
	{
		$alert("warn","模板路径不能为空！");
		return false;
	}
	//模板名称
	var $templateName = $.trim($("#templateName").val());
	if($templateName == null || $templateName == '')
	{
		$alert("warn","模板名称不能为空！");
		return false;
	}
	//输出全路径
	var $outPath = $.trim($("#outPath").val());
	if($outPath == null || $outPath == '')
	{
		$alert("warn","输出全路径不能为空！");
		return false;
	}
	var $id= $.trim($("#staid").val());
	$("#submit-btn").val("..").attr("disabled",true);
	var formInfo = $("#addInfo").serialize();
	$.ajax(
	{
		url:"${base}/static/static!update.action?id="+$id,
		type:"post",
		data:formInfo,
		success:function(data)
		{
			if(data == 1)
			{
				$alert("success","数据修改成功！");
				window.setTimeout(function(){
					location.href='${base}/static/static!list.action';				
				},1000);
			}
			if(data == 2)
			{
				$alert("error","数据库异常！");
			}
			if(data == 3)
			{
				$alert("warn","对象为空！");
			}
			if(data == 4)
			{
				$alert("warn","参数为空！");
			}
			$("#submit-btn").val("提交").attr("disabled",false);
		},
		error:function()
		{
			$alert("error","网络异常！");
			$("#submit-btn").val("提交").attr("disabled",false);
		}
	});
}
</script>
</html>