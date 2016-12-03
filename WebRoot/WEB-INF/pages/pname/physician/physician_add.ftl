<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<#include "/WEB-INF/pages/inc/common.ftl">
<script type="text/javascript" src="${base}/web/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/web/js/input.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript">
$().ready(function() {
	var $inputForm = $("#addInfo");
	var $username =	$("#username");
	var $imageFile =	$("#imageFile");
	// 表单验证
	$inputForm.validate({
		rules: {
			"physician.realName":"required",
			"physician.headUrl":"required",
			"physician.workYear":"required",
		}
	});
	$("#submitBtn").click(function(){
		if($username.val().length>20 || $username.val().length<0){
			$alert("warn","请输入有效的用户名!");
			return false;
		}
		if($imageFile.val()==""|| $imageFile.val()==null){
			$alert("warn","头像不能为空!");
			return false;
		}
		$.ajax({
		url:"${base}/physician/physician!getPhysicianCount.action",
		type:"post",
		success:function(data){
		          if(data==0){
		         $alert("warn","最多添加十条药师信息！");
		         return false;
		          }        
		      }
		  });
	});
});
</script>
<head> 
	<title>添加药师</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/physician/physician!savePhysicianInfo.action" method="post" name="addInfo" id="addInfo" enctype="multipart/form-data">
    	<div class="m-contents">
	    	<div class="labelnamese">姓名：</div>
	    	<input type="text" id="username" name="physician.realName" class="i-text-i" id="" value="" maxlength="40"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">头像：</div>
	    	<input type="file" name="image" class="i-text-i" id="imageFile" value="选择头像"/>
	    	&nbsp;&nbsp;(支持jpg,png,jpeg格式图片)
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">从业年限：</div>
	    	<input type="text" name="physician.workYear" class="i-text-i" id="workyear" value="" maxlength="10"/>
    	</div>
    	
    	<div class="m-contents">
	    	<div class="labelnamese">备注：</div>
	    	<input type="text" name="physician.remark" class="i-text-i" id="remark" value=""/>
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
	    	<input type="submit" class="btn01" value="提交" id="submitBtn"  style="margin-left:10px;"/>
	    	<input type="reset" class="btn01" value="重置" onclick="" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   
</body>

</html>