<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>添加位置类别</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 

<body>
	<div class="shop_main" id="shop_main" style="margin-top:0px;margin-left:0px;width:99.3%;"> 
    <form action="" method="get" name="addInfo" id="addInfo">
    
    	<div class="m-contents" style="margin-top:5px;">
	    	<div class="labelnamese">类别名称：</div>
	    	<input type="text" name="cPositionType.name" class="i-text-i" id="ctypeName" value=""/>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:30px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" id="submit-btn" onclick="addPositionType()" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" onclick="location.href='${base}/position/managePositionType!selectPostionTypeList.action';" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   

</body>

<script>	
//添加数据
function addPositionType()
{
	var $name = $.trim($("#ctypeName").val());
	if($name == null || $name == '')
	{
		$alert("warn","位置类别名称不能为空！");
		return false;
	}
	$("#submit-btn").val("..").attr("disabled",true);
	var formInfo = $("#addInfo").serialize();
	$.ajax(
	{
		url:"${base}/position/managePositionType!addPositionType.action",
		type:"post",
		data:formInfo,
		success:function(data)
		{
			if(data == 1)
			{
				$alert("error","该位置类别已经存在，数据添加失败！");
			}
			if(data == 0)
			{
				$alert("success","恭喜您，数据添加成功！");
				window.setTimeout(function(){
					location.href='${base}/position/managePositionType!selectPostionTypeList.action';				
				},1000);
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