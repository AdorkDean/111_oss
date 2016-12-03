<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>分词编辑</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl"> 
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 

<body>
	<div class="shop_main" id="shop_main" style="margin-top:0px;margin-left:0px;width:99%;"> 
    <form method="post" id="form_id" enctype="multipart/form-data" action="${base}/cut/word!update.action">
    	<input type="hidden" name="cutWord.id" id="id" value="${cutWord.id?default('')}"/>
    	<div class="m-contents" style="margin:0px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">分词名称：</div>
		    <input type="text" name="cutWord.cutword" id="cutword" style="float:left;width:200px;height:30px;line-height:30px;border:#ccc 1px solid;" value="${cutWord.cutword?default('')}" maxlength="45"/>
    		<div id="ccutword" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;">请填写分词名称</div>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:10px;">
	    	<input type="button" class="btn01" value="保存" id="submit_btn" onclick="validateData()" style="float:left;margin-left:85px;"/>
	    	<input type="button" class="btn01" value="返回" id="preview_btn" onclick="javascript:history.go(-1)" style="float:left;margin-left:10px;"/>
    	</div>
    </form>
    </div>   
</body>
<script>
/**表单校验*/
function validateData()
{
	//分词名称
	var cutword = $.trim($("#cutword").val());
	if(cutword == '' || cutword == null)
	{
		$("#cutword").focus();
		$("#ccutword").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#cutword").blur();
			$("#ccutword").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
		},2000);
		return false;
	}
	
	$("#submit_btn").val("...").attr("disabled",true);
	
	$("#form_id").submit();
}
</script>
</html>