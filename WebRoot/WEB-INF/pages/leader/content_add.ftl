<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>添加领袖内容</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
	<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>
	<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
	<style>
	.doc-ui {float:left;width:220px;height:23px;line-height:23px;margin-left:10px;margin-top:3px;border:#ccc 1px solid;border-left:none;border-radius:10px;color:#ccc;text-align:left;padding-left:10px;}
	.i-text-i td{background-color:#FFFFFF;}
	</style>
</head> 

<body>
	<div class="shop_main" id="shop_main" style="margin-top:0px;margin-left:0px;width:99.3%;">
    <form action="${base}/leader/leaderContent!save.action" method="post" name="form1" id="form1">
    	<div class="m-contents" style="margin-top:5px;">
	    	<div class="labelnamese">标题：</div>
	    	<input type="text" name="obj.tile" class="i-text-i" id="tile"  maxlength="20"/>
	    	<span style="color:red" id="sidkeyword"></span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">内容：</div>
	    	<textarea class="i-text-i" style="width:250px;height:300px;" id="context" name="obj.context" maxlength="250"></textarea>
    	</div>
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">权重：</div>
	    	<input type="text" class="i-text-i" name="obj.weight" maxlength="3" style="width:250px;" id="weight" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:30px;">
	    	<div class="labelnamese"></div>
	    	<input type="submit" class="btn01" value="提交" id="submit-btn" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" onclick="location.href='${base}/leader/leaderContent!list.action';" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   

</body>

<script>	
//保存数据
$("#form1").submit(function tijiao() {
	var $tile = $.trim($("#tile").val());
	if($tile == null || $tile == ''){
		$alert("warn","标题不能为空！");
		return false;
	}
	var $context = $.trim($("#context").val());
	if($context == null || $context == ''){
		$alert("warn","内容不能为空！");
		return false;
	}
	var $weight = $.trim($("#weight").val());
	if($weight == null || $weight == ''){
		$alert("warn","权重不能为空！");
		return false;
	}
	$alert('success',"保存成功!");
});
function removeRecord(obj){
 $(obj).parent().remove();
}
</script>
</html>