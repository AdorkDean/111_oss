<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>编辑专题</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl"> 
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 

<body>
	<div class="shop_main" id="shop_main" style="margin-top:0px;margin-left:0px;width:99%;"> 
    <form method="post" id="form_id" enctype="multipart/form-data">
    	<input type="hidden" value="${tsubject.id}" name="tsubject.id"/>
    	<div class="m-contents" style="margin:0px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">选择平台：</div>
    		<select name="tsubject.ptype" style="float:left;width:60px;height:30px;border:#ccc 1px solid;">
    			<option value="1" <#if tsubject.ptype == 1>selected="selected"</#if> >PC</option>
    			<option value="2" <#if tsubject.ptype == 2>selected="selected"</#if> >WAP</option>
    			<option value="3" <#if tsubject.ptype == 3>selected="selected"</#if> >APP</option>
    		</select>
    		<div style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;margin-left:152px;">请选择平台（如：PC）</div>
    	</div>
    	
    	<div class="m-contents" style="margin:10px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">专题名称：</div>
		    <input type="text" name="tsubject.name" id="name" value="${tsubject.name}" style="float:left;width:200px;height:30px;line-height:30px;border:#ccc 1px solid;"/>
    		<div id="cname" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;">请填写专题名称（如：新版上线领取优惠券）</div>
    	</div>
    	
    	<div class="m-contents" style="margin:10px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">文件名称：</div>
		    <input type="text" name="tsubject.nameFile" id="nameFile" value="${tsubject.nameFile}" style="float:left;width:200px;height:30px;line-height:30px;border:#ccc 1px solid;"/>
    		<div id="cnameFile" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;">请填写文件英文名称（如：subject.html）</div>
    	</div>
    	
    	<div class="m-contents" style="margin:10px 0px 0px 10px;">
    		<div style="float:left;height:435px;width:75px;line-height:435px;text-align:center;">专题代码：</div>
	    	<div style="float:left;width:435px;">
	    		<textarea name="ztcode" id="ztContext" style="float:left;width:650px;height:435px;resize:none;font-size:13px;padding:5px;">加载中...</textarea>
	    		<input type="hidden" id="hzContent" value="${ztcode}"/>
	    	</div>
    	</div>
    	
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:10px;">
	    	<input type="button" class="btn01" value="更新" id="submit_btn" onclick="validateData()" style="float:left;margin-left:85px;"/>
	    	<input type="button" class="btn01" value="预览" id="preview_btn" onclick="previewSubject()" style="float:left;margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" id="preview_btn" onclick="window.history.back()" style="float:left;margin-left:10px;"/>
	    	<div id="ccet" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:95px;margin-top:-5px;border-radius:5px;color:#ccc;text-align:center;">请复制专题代码到指定区域</div>
    	</div>
    		
    </form>
    </div>   

</body>
<script>
$(function()
{
	var hzContent = strDec($.trim($("#hzContent").val()),"1", "2", "3");
	window.setTimeout(function()
	{
		$("#ztContext").text(hzContent);
	},1000);
});
/**表单校验*/
function validateData()
{
	//专题名称
	var name = $.trim($("#name").val());
	//文件名称
	var nameFile = $.trim($("#nameFile").val());
	//代码内容
	var ztContext = $.trim($("#ztContext").val());
	if(name == '' || name == null)
	{
		$("#name").focus();
		$("#cname").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#name").blur();
			$("#cname").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
		},2000);
		return false;
	}
	if(nameFile == '' || nameFile == null)
	{
		$("#nameFile").focus();
		$("#cnameFile").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#nameFile").blur();
			$("#cnameFile").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
		},2000);
		return false;
	}
	else
	{
		if(nameFile.indexOf(".html") == -1 || /^[\u4e00-\u9fa5]+$/i.test(nameFile))
		{
			$("#nameFile").focus();
			$("#cnameFile").css({"border":"red 1px solid","color":"red"}).html("文件格式不正确！").show();
			window.setTimeout(function(){
				$("#nameFile").blur();
				$("#cnameFile").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).html("请填写文件英文名称（如：subject.html）").hide();
			},2000);
			return false;
		}
	}
	if(ztContext == '' || ztContext == null)
	{
		$("#ztContext").focus();
		$("#ccet").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#ccet").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
			$("#ztContext").blur();
		},2000);
		return false;
	}
	$("#submit_btn").val("...").attr("disabled",true);
	jQuery.ajax(
	{
		url:'/template/subject!update.action',
		data:$('#form_id').serialize(),
		type:"POST",
		success:function(data)
		{
			$("#ccet").css({"border":"blue 1px solid","color":"blue"}).html("恭喜更新成功，页面正在跳转...").show();
			window.setTimeout(function(){
				$("#submit_btn").val("更新").attr("disabled",false);
				$("#ccet").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).html("请复制专题代码到指定区域").hide();
				window.location.href = "${base}/template/subject!list.action";
			},2000);
		}
	});
}

function previewSubject()
{
	var ztContext = $.trim($("#ztContext").val());
	if(ztContext == '' || ztContext == null)
	{
		$("#ztContext").focus();
		$("#ccet").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#ccet").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
			$("#ztContext").blur();
		},2000);
		return false;
	}
	$("#preview_btn").val("...").attr("disabled",true);
	jQuery.ajax(
	{
		url:'/template/subject!preview.action',
		data:$('#form_id').serialize(),
		type:"POST",
		success:function(data)
		{
			$("#ccet").css({"border":"blue 1px solid","color":"blue"}).html("正在打开预览页...").show();
			window.setTimeout(function(){
				$("#preview_btn").val("预览").attr("disabled",false);
				$("#ccet").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).html("请复制专题代码到指定区域").hide();
				window.top.open(data);
			},2000);
		}
	});
}

</script>
<script type="text/javascript" src="${base}/web/js/des.js"></script>
</html>