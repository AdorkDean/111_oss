<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>添加领秀公告</title> 
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
    <form action="${base}/leader/leaderAnnouncement!save.action" method="post" name="form1" id="form1" enctype="multipart/form-data">
    	<div class="m-contents" style="margin-top:5px;">
	    	<div class="labelnamese">领秀通告内容：</div>
	    	<textarea class="i-text-i" style="width:250px;height:100px;" id="acCentent" name="tlxAnnouncement.centent" maxlength="100"></textarea>
	    	<span style="color:red" id="sidkeyword"></span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">权重：</div>
	    	<input type="text" class="i-text-i" name="tlxAnnouncement.weight" maxlength="3"  id="weight" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	<div style="position:relative;" class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">类型：</div>
	    	<input type="radio" style="width:20px; height:20px; margin-top:5px;" class="i-text-i" name="tlxAnnouncement.announcementType" value="1" checked="checked"/><span style="position:absolute; left:198px; top:8px;">中心通告</span>
	    	<input type="radio" style="width:20px; height:20px; position:absolute; left:250px; top:5px;" class="i-text-i" name="tlxAnnouncement.announcementType" value="2"/><span style="position:absolute; left:286px; top:8px;">排行通告</span>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:30px;">
	    	<div class="labelnamese"></div>
	    	<input type="submit" class="btn01" value="提交" id="submitbtn" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" onclick="location.href='${base}/leader/leaderAnnouncement!list.action';" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   

</body>

<script type="text/javascript">	
//保存数据
$().ready(function() {
	$("#submitbtn").click(function(){
	var $acCentent = $.trim($("#acCentent").val());
	if($acCentent == null || $acCentent == ''){
		$alert("warn","领秀通告内容不能为空！");
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
</html>