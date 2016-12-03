<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>医卡通商品</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 
<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<body> 
	<div class="shop_main" id="shop_main"> 
    	<font color="red"> 每个商品只能添加一次医卡通</font>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">海典编码：</font></div>
	    	<input type="text" class="i-text-i" id="goodsno" value="${goodsMap.goods_no?default('')}" maxlength="12" autocomplete="off"/>
	    	<input type="hidden" class="i-text-i" name="yktid" id="yktid" value="${goodsMap.id?default('')}" />
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">商品名称：</font></div>
	    	<input type="text" readonly="readonly" class="i-text-i" id="glsp" value="${goodsMap.goods_name?default('')}"/>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LX_ADD_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="subForm();" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="刷新" onclick="javascript:history.go(0);" style="margin-left:10px;"/>
	    	<a href="${base}/ykt/ykt!yktGoodsList.action"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    		
    </div>   
</body>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>


<script>
$(function(){
	$("#goodsno").focus(function(){
		var goodsno = $("#goodsno").val();
		if(goodsno==""){
			$("#glsp").val("");
		}
	});
	$("#goodsno").blur(function(){
		var goodsno = $("#goodsno").val();
		if(goodsno!=""){
			$.ajax({type: 'POST',
				url: "${base}/ykt/ykt!selectGoodsByGoodsno.action" ,
				data: {"goodsno":goodsno} ,
				dataType: "json",
				success: function(data){
					if(data.status==0){//不存在，删掉输入的
						$alert("warn","未查询到编码["+goodsno+"]商品");
						$("#glsp").val("");
						$("#goodsno").val("");
					}
					if(data.status==1){//存在，拼input值
						$("#glsp").attr("value",data.goodsName);
					}
				},error:function(data){
					//不存在，删掉输入的
					$alert("warn","未查询到编码["+goodsno+"]商品");
					$("#glsp").val("");
					$("#goodsno").val("");
				}
			});
		}else{
			$("#glsp").val("");
			$("#goodsno").val("");
		}
	});
})





function subForm(){
   var glsp = $("#glsp").val();
   var goodsno = $("#goodsno").val();
   var yktid = $("#yktid").val();
   if($.trim(glsp)==""){
       $alert('warn','请填写医卡通商品');
      return false;
   }
   if($.trim(goodsno)==""){
       $alert('warn','请填写医卡通商品');
      return false;
   }
   
    $.post("${base}/ykt/ykt!saveYkrGoods.action",{yktid:yktid,goodsno:goodsno,random:Math.random()},function(data){
          if(data>0){
            $alert('success','数据保存成功');
           window.location.href="${base}/ykt/ykt!yktGoodsList.action";
          }else if(data=="-1"){
            $alert('warn','该商品已添加过');
          }else if(data=="-2"){
            $alert('warn','添加失败');
          }else if(data=="-3"){
            $alert('warn','商品编号有误');
          }
    }); 
    
   
}

</script>
</html>