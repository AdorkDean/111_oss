<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加领袖佣金商品</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 
<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/leader/leaderGoods!leaderGoodsUpdate.action" method="post" name="addInfo" id="addInfo">
    	<font color="red"> 每个商品只能添加一次佣金</font>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">商品名称：</font></div>
	    	<input type="text"  class="i-text-i"  value="${lmap.short_name?default('')}" maxlength="12"  readonly="readonly" style="border:none;"/>
    	    <input type="hidden" name="brokerage.id" class="i-text-i" id="goodsId" value="${lmap.id?default('')}" maxlength="20"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">海典编码：</font></div>
	    	<input type="text"  class="i-text-i"  value="${lmap.goodsno?default('')}" maxlength="12"  readonly="readonly" style="border:none;"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">佣金比例：</font></div>
	    	<input type="text" name="brokerage.brokerage" class="i-text-i" id="brokerage" value="${lmap.brokerage?default('')}" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
		<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">佣金：</font></div>
	    	<div  style="margin-top:8px;"><font color="red" id="yongjin"></font></div>
	    	<input type="hidden"   id="pc_price" value="${lmap.pc_price?default('')}" />
	    	<input type="hidden"   id="wap_price" value="${lmap.wap_price?default('')}"/>
	    	<input type="hidden"   id="app_price" value="${lmap.app_price?default('')}" />
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LX_BIANJI_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="subForm();" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="刷新" onclick="javascript:history.go(0);" style="margin-left:10px;"/>
	    	<a href="${base}/leader/leaderGoods!list.action"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    		
    </form>
    </div>   
</body>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>


<script>

$().ready(function() {
	${flash_message()}
});	

	$(function(){
		var pc=$("#pc_price").val();
		var wap=$("#wap_price").val();
		var app=$("#app_price").val();
		var brokerage=$("#brokerage").val();
		var str="PC:"+(pc*(brokerage/100)).toFixed(2)+"元, WAP:"+(wap*(brokerage/100)).toFixed(2)+"元, APP:"+(app*(brokerage/100)).toFixed(2)+"元";
		$("#yongjin").text(str);
		});
function subForm(){
   
   var brokerage = $("#brokerage").val();
   if($.trim(brokerage)==""){
   $alert('warn','请填写佣金比例');
      return false;
   }
    
    $('#addInfo').submit();
}
$("#brokerage").keyup(function(){
	var pc=$("#pc_price").val();
	var wap=$("#wap_price").val();
	var app=$("#app_price").val();
	var brokerage=$("#brokerage").val();
	var str="PC:"+(pc*(brokerage/100)).toFixed(2)+"元, WAP:"+(wap*(brokerage/100)).toFixed(2)+"元, APP:"+(app*(brokerage/100)).toFixed(2)+"元";
	$("#yongjin").text(str);
});
</script>
</html>