<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<#include "/WEB-INF/pages/inc/common.ftl">
<script type="text/javascript" src="${base}/web/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/web/js/input.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<head> 
	<title>添加运费</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    	<div class="m-contents">
	    	<div class="labelnamese">运费：</div>
	    	<input type="text" id="freight" name="freight" class="i-text-i" id="" value="" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	<div class="m-contents">
	    	<div class="labelnamese">备注：</div>
	    	<input type="text" name="remark" class="i-text-i" id="remark" value=""/>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" id="submitBtn"  onclick="add();" style="margin-left:10px;"/>
	    	<input type="reset" class="btn01" value="返回"  style="margin-left:10px;" onclick="freightList();"/>
    	</div>
    </div>   
</body>
<script>
function add(){
   var freight = $('#freight').val();
   if($.trim(freight)==""){
      alert("运费不能为空");
      return false;
   }
    var remark = $('#remark').val();
    $.post("${base}/goodsfreight/goodsfreight!addFreight.action",{freight:freight,remark:remark,random:Math.random()},function(data){
           if(data!=-1){
             alert("添加成功");
             $('#freight').val('');
             $('#remark').val('');
           }else{
             alert("添加失败");
           }
    });
}   
function freightList(){
   window.location.href="${base}/goodsfreight/goodsfreight!freightList.action";
}
</script>
</html>