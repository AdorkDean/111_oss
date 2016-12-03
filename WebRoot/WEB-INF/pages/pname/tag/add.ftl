<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>添加标签</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl"> 
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 

<body>
	<div class="shop_main" id="shop_main" style="margin-top:0px;margin-left:0px;width:99%;"> 
    <form method="post" id="form_id" enctype="multipart/form-data" action="${base}/label/tag!save.action">
    	
    	<div class="m-contents" style="margin:0px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">标签名称：</div>
		    <input type="text" name="tag.name" id="name" style="float:left;width:200px;height:30px;line-height:30px;border:#ccc 1px solid;" maxlength="45"/>
    		<div id="cname" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;">请填写标题名称</div>
    	</div>
    	
    	<div class="m-contents" style="margin:10px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">标签类型：</div>
    		<select id="type" name="tag.type" style="float:left;width:60px;height:30px;border:#ccc 1px solid;">
    			<option value="">请选择类型</option>
    			<option value="0">会员</option>
    		</select>
    		<div id="ctype" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;margin-left:152px;">请选择标签类型</div>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:10px;">
    		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_BIAOQIAN_TIANJIA_BAOCUN">
	    	<input type="button" class="btn01" value="保存" id="submit_btn" onclick="validateData()" style="float:left;margin-left:85px;"/>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="返回" id="preview_btn" onclick="javascript:history.go(-1)" style="float:left;margin-left:10px;"/>
    	</div>
    </form>
    </div>   
</body>
<script>
/**表单校验*/
function validateData()
{
	//标签名称
	var name = $.trim($("#name").val());
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
	
	//标签名称
	var type = $.trim($("#type").val());
	console.log(type);
	if(type == '' || type == null)
	{
		$("#type").focus();
		$("#ctype").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#type").blur();
			$("#ctype").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
		},2000);
		return false;
	}
	
	
	$("#submit_btn").val("...").attr("disabled",true);
	
	$("#form_id").submit();
}
</script>
</html>