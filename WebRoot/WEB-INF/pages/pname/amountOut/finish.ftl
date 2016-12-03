<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>提现完成</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 
<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/member/amountOut!finish.action" method="post" name="form1" id="form1">
	<input type="hidden" name="id" class="i-text-i"  value="${outMap.id?default(0)}" maxlength="12"/>
    	<div class="m-contents">
	    	<div class="labelnamese">会员名称：</div>
	    	<input type="text"  class="i-text-i"  value="${outMap.user_name?default('')}" readonly="readonly" style="border:none;"/>
    	</div>
    	
    	<div class="m-contents">
	    	<div class="labelnamese">提现申请人：</div>
	    	<input type="text"  class="i-text-i"  value="${outMap.real_name?default('')}" readonly="readonly" style="border:none;"/>
    	</div>
   
       	<div class="m-contents">
	    	<div class="labelnamese">提现金额：</div>
	    	<input type="text"  class="i-text-i"  value="${outMap.amount?default(0)}" readonly="readonly" style="border:none;"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">申请时间：</div>
	    	<input type="text"  class="i-text-i"  value="${outMap.out_time?string('yyyy-MM-dd HH:mm:ss')}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">提现方式：</div>
	    	<input type="text"  class="i-text-i"  value="<#if outMap.out_type?exists><#if outMap.out_type?default(0)==1>支付宝<#elseif outMap.out_type?default(0)==2>银行卡</#if></#if>" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">支付宝账号：</div>
	    	<input type="text"  class="i-text-i"  value="${outMap.alipay_no?default('')}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">提现银行：</div>
	    	<input type="text"  class="i-text-i"  value="${outMap.bank_name?default('')}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">提现银行账号：</div>
	    	<input type="text"  class="i-text-i"  value="${outMap.bank_no?default('')}" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
     	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">手续费：</div>
	    	<input type="text"  class="i-text-i"  value="${outMap.out_fee?default('')}" readonly="readonly" style="border:none;"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">提现状态：</div>
	    	<input type="text"  class="i-text-i"  value="<#if outMap.status?exists><#if outMap.status?default(0)==1>申请提现<#elseif outMap.status?default(0)==2>提现中<#elseif outMap.status?default(0)==3>提现完成</#if></#if>" readonly="readonly" style="border:none;"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">提现备注：</div>
	    	<textarea class="taa-ui" readonly="readonly" style="border:none;">${outMap.remark?default('')}</textarea>
    	</div> 	

    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TIXIAN_WANCHENG_WANCHENG">	
	    	<input type="button" class="btn01" value="完成" style="margin-left:10px;" onclick="subForm();"/>
	    	</@security.authorize>
	    	<a href="${base}/member/amountOut!list.action"><input type="button" class="btn01" value="返回" style="margin-left:10px;"/></a>
    	</div>
    		
    </form>
    </div>   
</body>
<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<script>
function subForm(){
   
 //  $("#status").val(type);
   $('#form1').submit();
   
}
</script>
</html>