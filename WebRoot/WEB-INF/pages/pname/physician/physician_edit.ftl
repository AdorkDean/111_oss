<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加药师</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/physician/physician!updatePhysicianInfo.action" method="post" name="addInfo" id="addInfo" enctype="multipart/form-data">
    <input type="hidden" name="physician.id" value="${physician.id}"/>
    	<div class="m-contents">
	    	<div class="labelnamese">姓名：</div>
	    	<input type="text" name="physician.realName" id="username" class="i-text-i" id="" value="${physician.realName?default('')}" maxlength="40"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">头像：</div>
	    	<input type="hidden" name="physician.headUrl" class="i-text-i" id="" value="${physician.headUrl?default('')}"/>
	    	<input type="file" name="image" class="i-text-i" id="imageFile" value="${physician.headUrl?default('')}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">从业年限：</div>
	    	<input type="text" name="physician.workYear" class="i-text-i" id="" value="${physician.workYear?default('')}"  maxlength="10"/>
    	</div>
    	
    	<div class="m-contents">
	    	<div class="labelnamese">备注：</div>
	    	<input type="text" name="physician.remark" class="i-text-i" id="" value="${physician.remark?default('')}"/>
    	</div>
    	<!--
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">审核状态：</div>
	    	<select name="tphysician.auditType" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option value="">请选择</option>
					 <option value="0">提交审核</option>
					 <option value="1">审核通过</option>
					 <option value="2">审核不通过</option>
			</select>
    	</div>
    	<div class="m-contents">
	    	<div class="labelnamese">审核备注：</div>
	    	<input type="text" name="tphysician.auditRemark" class="i-text-i" id="" value=""/>
    	</div>
    	-->
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="submit" class="btn01" value="提交" id="submitBtn" onclick="" style="margin-left:10px;"/>
	    	<input type="reset" class="btn01" value="重置" onclick="" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   
</body>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/jquery.validate.js"></script>
<script>	
$().ready(function() {
	var $inputForm = $("#addInfo");
	var $username =	$("#username");
	// 表单验证
	$inputForm.validate({
		rules: {
			"physician.realName":"required",
			"physician.headUrl":"required",
			"physician.workYear":"required",
		}
	});
	$("#submitBtn").click(function(){
	if($username.val()>20 || $username.val()<10){
			$alert("warn","请输入有效的用户名");
			return false;
		}
	});
});
</script>
</html>