<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>生成优惠券</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 
<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/card/coupon!createCard.action" method="post" name="form1" id="form1">
    	<div class="m-contents">
	    	<div class="labelnamese">优惠券名称：</div>
	    	<input type="text"  class="i-text-i"  value="${coupon.name?default('')}" readonly="readonly" style="border:none;"/>
	    	<input type="hidden" name="id" class="i-text-i"  value="${coupon.id?default(0)}" maxlength="12"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">位数：</div>
	    	<input type="text"  class="i-text-i"  value="${coupon.codeLength?default(0)}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">开始时间：</div>
	    	<input type="text"  class="i-text-i"  value="${coupon.startTime?string('yyyy-MM-dd HH:mm:ss')}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">结束时间：</div>
	    	<input type="text"  class="i-text-i"  value="${coupon.endTime?string('yyyy-MM-dd HH:mm:ss')}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">已生成优惠劵：</div>
	    	<input type="text"  class="i-text-i"  value="${ccount?default(0)}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">已使用优惠劵：</div>
	    	<input type="text"  class="i-text-i"  value="${ycount?default(0)}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">已绑定优惠劵：</div>
	    	<input type="text"  class="i-text-i"  value="${bcount?default(0)}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">张数：</font></div>
	    	<input type="text" name="quantity" class="i-text-i" id="quantity" value="${coupon.quantity?default(0)}" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_COUPON_SHENGCHENG_SHENGCHENG">
	    	<input type="button" class="btn01" value="生成" onclick="subForm();" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<a href="${base}/card/coupon!list.action"><input type="button" class="btn01" value="返回" style="margin-left:10px;"/></a>
    	</div>
    		
    </form>
    </div>   
</body>
<script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<script>
function subForm(){
   
   var quantity = $("#quantity").val();
   if($.trim(quantity)==""){
   $alert('warn','生成张数不能为空');
      return false;
   }
   
   $('#form1').submit();
}
</script>
</html>