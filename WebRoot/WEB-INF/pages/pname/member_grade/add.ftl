<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>编辑会员等级</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<script type="text/javascript" src="${base}/web/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/web/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#addInfo");
	// 表单验证
	$inputForm.validate({
		rules: {
			"memberGrade.name":"required",
			scale: {
				required: true,
				min: 0,
				decimal: {
					integer: 3,
					fraction: 3
				}
			},
			"memberGrade.enterpriseDiscount": {
				required: true,
				min: 0,
				max: 1
			},
			"memberGrade.consumePrice": {
				required: true,
				digits: true
			}
			
		}
	});
});
</script>
</head> 
<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/member/grade!save.action" method="post" name="addInfo" id="addInfo" enctype="multipart/form-data">
    
    	<div class="m-contents">
	    	<div class="labelnamese">等级名称：</div>
	    	<input type="text" name="memberGrade.name" class="i-text-i" id="" value=""/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">优惠比例：</div>
	    	<input type="text" name="memberGrade.enterpriseDiscount" class="i-text-i" id="" value=""/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">消费金额：</div>
	    	<input type="text" name="memberGrade.consumePrice" class="i-text-i" id="" value=""/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">默认等级：</div>
	    	<input type="checkbox" name="memberGrade.isDefault" class="i-text-cb" value="true"/>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUANDENGJI_TIANJI_TIJIAO">
	    	<input type="submit" class="btn01" value="提交" onclick="" />
	    	</@security.authorize>
	    	<input type="reset" class="btn01" value="重置" onclick="" />
	    	<input type="button" class="btn01" value="返回" onclick="javascript:history.go(-1);" />
    	</div>
    		
    </form>
    </div>   
</body>

</html>