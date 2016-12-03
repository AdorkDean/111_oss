<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>编辑优惠券</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 
<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/card/coupon!upcoupon.action" method="post" name="form1" id="form1">
        <font color="red">  红字为必填项，请仔细填写</font>
    	<div class="m-contents">
	    	<div class="labelnamese"><font color="red">优惠券名称：</font></div>
	    	<input type="text" name="coupon.name" class="i-text-i" id="name" value="${coupon.name?default('')}" maxlength="12"/>
	    	<input type="hidden" name="coupon.id" class="i-text-i"  value="${coupon.id?default(0)}" maxlength="12"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">前缀：</font></div>
	    	<input type="text" name="coupon.prefix" class="i-text-i" id="prefix" value="${coupon.prefix?default('')}" maxlength="6"/>
    	</div>
 
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">位数：</font></div>
	    	<input type="text" name="coupon.codeLength" class="i-text-i" id="codeLength" value="${coupon.codeLength?default(0)}" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">数字：</div>
	    	<input type="radio" name="coupon.rule" class="i-text-cb" id="" value="0"  <#if coupon.rule?exists><#if coupon.rule?default(0)==0>checked="checked"</#if></#if>/>
	    	<div class="labelnamese">数字加英文：</div>
	    	<input type="radio" name="coupon.rule" class="i-text-cb" id="" value="1" <#if coupon.rule?exists><#if coupon.rule?default(0)==1>checked="checked"</#if></#if>/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">张数：</font></div>
	    	<input type="text" name="coupon.quantity" class="i-text-i" id="quantity" value="${coupon.quantity?default(0)}" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	 
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">开始时间：</font></div>
	    	<input type="text" name="coupon.startTime" class="i-text-i" id="startTime" value="${coupon.startTime?string('yyyy-MM-dd')}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2050-10-01\'}'})"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">结束时间：</font></div>
	    	<input type="text" name="coupon.endTime" class="i-text-i" id="endTime" value="${coupon.endTime?string('yyyy-MM-dd')}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2050-10-01'})"/>
    	</div>
  
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">最高使用金额：</font></div>
	    	<input type="text" name="coupon.endPrice" class="i-text-i" id="endPrice" value="${coupon.endPrice?default(0)}" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">最低使用金额：</font></div>
	    	<input type="text" name="coupon.startPrice" class="i-text-i" id="startPrice" value="${coupon.startPrice?default(0)}" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">满减金额：</font></div>
	    	<input type="text" name="coupon.disPrice" class="i-text-i" id="disPrice" value="${coupon.disPrice?default(0)}" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">是否启用：</div>
	    	<input type="checkbox"  class="i-text-cb" id="status"   <#if coupon.status?exists><#if coupon.status?default(0)==1>checked ='checked'</#if></#if>/>
	    	<input type="hidden" name="coupon.status" class="i-text-i" id="qy" value="${coupon.status?default(0)}" maxlength="10"/>
	    	<div class="labelnamese">是否允许积分兑换：</div>
	    	<input type="checkbox" name="" class="i-text-cb" id="isExchange" <#if coupon.isExchange?exists><#if coupon.isExchange?default(0)==1>checked ='checked'</#if></#if> />
	    	<input type="hidden" name="coupon.isExchange" class="i-text-i" id="jf" value="${coupon.isExchange?default(0)}" maxlength="10"/>
    	    <div class="labelnamese">是否重复使用：</div>
	    	<input type="checkbox" name="" class="i-text-cb" id="isRepeat" <#if coupon.isRepeat?exists><#if coupon.isRepeat?default(0)==1>checked ='checked'</#if></#if>/>
	    	<input type="hidden" name="coupon.isRepeat" class="i-text-i" id="cf" value="${coupon.isRepeat?default(0)}" maxlength="10"/>
    	</div>
    	
        <div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">积分兑换数：</div>
	    	<input type="text" name="coupon.exchangeCount" class="i-text-i" id="" value="<#if coupon.exchangeCount?exists>${coupon.exchangeCount?default(0)}</#if>" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">是否延期：</div>
	    	<input type="checkbox"  class="i-text-cb" id="isDelay"  <#if coupon.isDelay?exists><#if coupon.isDelay?default(0)==1>checked ='checked'</#if></#if>/>
	    	<input type="hidden" name="coupon.isDelay" class="i-text-i" id="yq"  maxlength="10" value="${coupon.isDelay?default(0)}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">延期天数：</div>
	    	<input type="text" name="coupon.delayDay" class="i-text-i" id="delayDay" value="${coupon.delayDay?default(0)}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="4"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">介绍：</div>
	    	<textarea class="taa-ui" name="coupon.instro">${coupon.instro?default('')}</textarea>
    	</div>
    	
        <div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">使用范围：</div>
	    	<input type="text"  class="i-text-i"  value="<#if coupon.scope?exists><#if coupon.scope?default(0)==0>全场<#elseif coupon.scope?default(0)==1>免运费<#elseif coupon.scope?default(0)==2>分类 &nbsp;&nbsp;&nbsp;${rname?default('')}<#elseif coupon.scope?default(0)==3>品牌&nbsp;&nbsp;&nbsp;${rname?default('')}<#elseif coupon.scope?default(0)==4>商品&nbsp;&nbsp;&nbsp;${rname?default('')}</#if></#if>" readonly="readonly" style="border:none;"/>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	        <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_COUPON_BIANJI_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="subForm();" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="返回"  style="margin-left:10px;" onclick="fh();"/>
    	</div>
    		
    </form>
    </div>   
</body>
<script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<script>
function subForm(){
   
   var name = $("#name").val();
   if($.trim(name)==""){
       $alert('warn','优惠券名称不能为空');
      return false;
   }
   
   var prefix = $("#prefix").val();
   if($.trim(prefix)==""){
   $alert('warn','前缀不能为空');
      return false;
   }
   var codeLength = $("#codeLength").val();
   if($.trim(codeLength)==""){
   $alert('warn','位数不能为空');
      return false;
   }
   var quantity = $("#quantity").val();
   if($.trim(quantity)==""){
   $alert('warn','张数不能为空');
      return false;
   }
   var startTime = $("#startTime").val();
   if($.trim(startTime)==""){
   $alert('warn','开始时间不能为空');
      return false;
   }
   var endTime = $("#endTime").val();
   if($.trim(endTime)==""){
   $alert('warn','结束时间不能为空');
      return false;
   }
   var endPrice = $("#endPrice").val();
   if($.trim(endPrice)==""){
   $alert('warn','最高使用金额不能为空');
      return false;
   }
   var startPrice = $("#startPrice").val();
   if($.trim(startPrice)==""){
   $alert('warn','最低使用金额不能为空');
      return false;
   }
   var disPrice = $("#disPrice").val();
   if($.trim(disPrice)==""){
   $alert('warn','满减金额不能为空');
      return false;
   }

    if($("#status").attr("checked")){
        $("#qy").val("1");
    }else{
    	$("#qy").val("0");
    }
    
    if($("#isExchange").attr("checked")){
    	$("#jf").val("1");
    }else{
    	$("#jf").val("0");
    }
    
    if($("#isDelay").attr("checked")){
    	$("#yq").val("1");
    	if($.trim($('#delayDay').val())==""){
    	   $alert('warn','请填写延期天数');
      	   return false;
    	}
    }else{
    	$("#yq").val("0");
    }
    
    
    if($("#isRepeat").attr("checked")){
        $("#cf").val("1");
    }else{
    	$("#cf").val("0");
    }
    $('#form1').submit();
}

function fh(){
   window.location.href="${base}/card/coupon!list.action";
}
</script>
</html>