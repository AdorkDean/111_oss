<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<#include "/WEB-INF/pages/inc/common.ftl">
<script type="text/javascript" src="${base}/web/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/web/js/input.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<head> 
	<title>修改商品运费</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
	    <input type="hidden" name="gf_id" value="${map.id?default(0)}" id="gf_id">
    	<div class="m-contents">
	    	<div class="labelnamese">商品名称：</div>
	    	<input type="text" class="i-text-i" id="" value="${map.short_name?default(0)}" readonly/>
    	</div>
    	<div class="m-contents" style="margin-top:15px;">
	    	<div class="labelnamese">运费：</div>
	    	<select name="f_id" id="f_id" style="height:30px;border:#ccc 1px solid;background:#f5f5f5;width:80px;margin-left:10px;">
	    	      <#list list?if_exists as resc>
					 <option <#if resc.id?default(0)==map.freight_id?default(0) > selected</#if> value="${resc.id?default(0)}">${resc.freight?default(0)}</option>
			      </#list>
			</select>
			元
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="修改" id="submitBtn"  onclick="upgoodfreight();" style="margin-left:10px;"/>
	    	<input type="reset" class="btn01" value="返回"  style="margin-left:10px;" onclick="goodsFreight();"/>
    	</div>
    </div>   
</body>
<script>
function upgoodfreight(){
    var gf_id = $('#gf_id').val();
    var f_id = $('#f_id').val();
    $.post("${base}/goodsfreight/goodsfreight!updateGoodFreight.action",{fid:f_id,gid:gf_id,random:Math.random()},function(data){
           if(data!=-1){
             alert("修改成功");
			 window.location.href="${base}/goodsfreight/goodsfreight!goodsFreight.action";
           }else{
             alert("修改失败");
           }
    });
}   
function goodsFreight(){
   window.location.href="${base}/goodsfreight/goodsfreight!goodsFreight.action";
}
</script>
</html>