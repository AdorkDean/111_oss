<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>编辑龙卡</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 
<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/card/longCard!upLong.action" method="post" name="form1" id="form1">
        <font color="red">  红字为必填项，请仔细填写</font>
    	<div class="m-contents">
	    	<div class="labelnamese"><font color="red">龙卡名称：</font></div>
	    	<input type="text" name="tlong.name" class="i-text-i" id="name" value="${tlong.name?default('')}" maxlength="12"/>
	    	<input type="hidden" name="tlong.id" class="i-text-i"  value="${tlong.id?default(0)}" maxlength="12"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">数字：</div>
	    	<input type="radio" name="tlong.rule" class="i-text-cb" id="" value="0"  <#if tlong.rule?exists><#if tlong.rule?default('0')=='0'>checked="checked"</#if></#if>/>
	    	<div class="labelnamese">数字加英文：</div>
	    	<input type="radio" name="tlong.rule" class="i-text-cb" id="" value="1" <#if tlong.rule?exists><#if tlong.rule?default('0')=='1'>checked="checked"</#if></#if>/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">张数：</font></div>
	    	<input type="text" name="tlong.quantity" class="i-text-i" id="quantity" value="${tlong.quantity?default(0)}" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	 
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">开始时间：</font></div>
	    	<input type="text" name="tlong.startTime" class="i-text-i" id="startTime" value="${tlong.startTime?string('yyyy-MM-dd')}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2050-10-01\'}'})"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">结束时间：</font></div>
	    	<input type="text" name="tlong.endTime" class="i-text-i" id="endTime" value="${tlong.endTime?string('yyyy-MM-dd')}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2050-10-01'})"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">金额：</font></div>
	    	<input type="text" name="tlong.amount" class="i-text-i" id="disPrice" value="${tlong.amount?default(0)}" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">是否启用：</div>
	    	<input type="checkbox"  class="i-text-cb" id="status"   <#if tlong.status?exists><#if tlong.status?default(0)==1>checked ='checked'</#if></#if>/>
	    	<input type="hidden" name="tlong.status" class="i-text-i" id="qy" value="${tlong.status?default(0)}" maxlength="10"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">介绍：</div>
	    	<textarea class="taa-ui" name="tlong.instro">${tlong.instro?default('')}</textarea>
    	</div>
    	
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LONG_BIANJI_TIJIAO">
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

   var disPrice = $("#disPrice").val();
   if($.trim(disPrice)==""){
   $alert('warn','满减金额不能为空');
      return false;
   }



    if($("#status").is(":checked")){
        $("#qy").val("1");
    }else{
    	$("#qy").val("0");
    }
    
    $('#form1').submit();
}

function fh(){
window.location.href="${base}/card/longCard!list.action";
}
</script>
</html>