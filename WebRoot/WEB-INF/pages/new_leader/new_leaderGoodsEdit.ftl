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
    <form action="" method="post" name="addInfo" id="addInfo">
    	<font color="red"> 每个商品只能添加一次佣金</font>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">商品名称：</font></div>
	    	<input type="text"  class="i-text-i"  value="${goodsMap.short_name?default('')}" maxlength="12"  readonly="readonly" style="border:none;"/>
    	    <input type="hidden" name="brokerage.id" class="i-text-i" id="goodsId" value="${goodsMap.id?default('')}" maxlength="20"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">海典编码：</font></div>
	    	<input type="text"  class="i-text-i"  value="${goodsMap.goodsno?default('')}" maxlength="12"  readonly="readonly" style="border:none;"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">商品价格：</font></div>
	    	<input type="text"  class="i-text-i"  id="wap_price" value="${goodsMap.wap_price?default('')}" readonly="readonly" style="border:none;"/>元
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">佣金比例：</font></div>
	    	<input type="text"  class="i-text-i" id="brokerage" value="" maxlength="5" onkeyup="this.value=this.value.replace(/^((\d*[1-9])|(0?\.\d{2}))$g,'')" onafterpaste="this.value=this.value.replace(/^((\d*[1-9])|(0?\.\d{2}))$g,'')"/>%
    	</div>
    	
		<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">返佣金额：</font></div>
	    	<input type="text"  class="i-text-i" name="brokerage.rebateAmount" id="rebateAmount" readonly="readonly" value="${goodsMap.rebate_amount?default(0)}"/>元
	    	
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="subForm();" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="刷新" onclick="javascript:history.go(0);" style="margin-left:10px;"/>
	    	<a href="${base}/leader/newLeaderGoods!getLeaderGoodsList.action"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    		
    </form>
    </div>   
</body>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>


<script>

$().ready(function() {
	${flash_message()}
});	
function subForm(){
   var rebateAmount = $("#rebateAmount").val();
   if($.trim(rebateAmount)==""){
   $alert('warn','请填写返佣金额');
      return false;
   }
  $.ajax({
	url:"${base}/leader/newLeaderGoods!updateGoods.action",
			type:"post",
			data:$("#addInfo").serialize(),
			success:function(data){
                   if(data>0){
                   $alert('success','数据保存成功');
                    window.location.href="${base}/leader/newLeaderGoods!getLeaderGoodsList.action"; 
                    }else{
                   $alert('warn','数据保存失败');
                    }
                    }
 	 });
}
$("#brokerage").keyup(function(){
	var wap=$("#wap_price").val();
		var brokerage=$("#brokerage").val();
		var str=(wap*(brokerage/100)).toFixed(2);
		$("#rebateAmount").val(str);
});
</script>
</html>