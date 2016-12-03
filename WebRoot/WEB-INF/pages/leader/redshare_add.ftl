<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>添加红包分享语</title> 
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
    <form action="${base}/leader/leaderRedShare!save.action" method="post" name="form1" id="form1" enctype="multipart/form-data">
    	<div class="m-contents" style="margin-top:5px;">
	    	<div class="labelnamese">领秀红包分享语：</div>
	    	<textarea class="i-text-i" style="width:250px;height:100px;" id="redsharetitle" name="lxredshare.shareTitle" maxlength="100"></textarea>
	    	<span style="color:red" id="sidkeyword"></span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:5px;">
	    	<div class="labelnamese">领秀红包分享图：</div>
	    	<input type="file" name="image" class="i-text-i" id="imageFile" value="领秀红包分享图"/>
	    	&nbsp;&nbsp;(支持jpg,png,jpeg格式图片,图片大小在5M以内)
    	</div>
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">权重：</div>
	    	<input type="text" class="i-text-i" name="lxredshare.weight" maxlength="3"  id="weight" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	<div style="position:relative;" class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">类型：</div>
	    	<input type="radio" style="width:20px; height:20px; margin-top:5px;" class="i-text-i" name="lxredshare.shareType" value="1" checked="checked"/><span style="position:absolute; left:198px; top:8px;">领秀红包</span>
	    	<input type="radio" style="width:20px; height:20px; position:absolute; left:250px; top:5px;" class="i-text-i" name="lxredshare.shareType" value="2"/><span style="position:absolute; left:286px; top:8px;">购物红包</span>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:30px;">
	    	<div class="labelnamese"></div>
	    	<input type="submit" class="btn01" value="提交" id="submitbtn" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" onclick="location.href='${base}/leader/leaderRedShare!list.action';" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   

</body>

<script type="text/javascript">	
//保存数据
$().ready(function() {
	$("#submitbtn").click(function(){
	var $redsharetitle = $.trim($("#redsharetitle").val());
	if($redsharetitle == null || $redsharetitle == ''){
		$alert("warn","领秀红包分享语不能为空！");
		return false;
	}
	var $imageFile = $.trim($("#imageFile").val());
	if($imageFile == null || $imageFile == ''){
		$alert("warn","领秀红包分享图不能为空！");
		return false;
	}
	var $weight = $.trim($("#weight").val());
	if($weight == null || $weight == ''){
		$alert("warn","权重不能为空！");
		return false;
	}
	});
});
</script>
<script type="text/javascript">	
$("#imageFile").change(function(){
		var file = this.files[0]
		if(file == undefined){
			return false;
		}
    	var size = file.size;
	    if(size > 5 *1024*1024){
	    	$alert("warn","图片尺寸太大！");
	    	return false;
	    }
	});
</script>
</html>