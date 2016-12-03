<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加领秀注册赠送或是推荐商品</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 
<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>
<body> 
	<div class="shop_main" id="product_main" style="display:block;">
    <form action="" method="post" name="addInfo" id="addInfo">
        <input type="hidden" name="type" value="${type}" id="type">
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">海典编码：</div>
	    	<input type="text" class="i-text-i" id="goodsno" value="" maxlength="12" autocomplete="off"/>
	    	<font color="red">输入完整的海典编码,鼠标点击输入框以为任何位置,自动加载商品佣金里面的商品</font>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">商品名称：</div>
	    	<input type="hidden" name="goodsId" class="i-text-i" id="goodsId" value="" maxlength="20"/>
	    	<span id="goodsName"><span>
    	</div>
		<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">返佣金额：</div>
	    	<span id="rebateAmount"><span>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="subProductForm();" style="margin-left:10px;"/>
	    	<a href="javascript:backlist()"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    </form>
    </div>   
</body>
<script>


function backlist(){
   var type =$("#type").val();
   if(type=='1'){
    window.location.href="${base}/leader/leaderGoodsRecommend!registerLeaderGoodsList.action";
   }else{
    window.location.href="${base}/leader/leaderGoodsRecommend!leaderRecommendGoodsList.action";
   }
}

function subProductForm(){
   var goodsId = $("#goodsId").val();
   if($.trim(goodsId)==""){
   $alert('warn','请添加商品');
      return false;
   }
  $.ajax({
	url:"${base}/leader/leaderGoodsRecommend!saveLeaderRecommendGoods.action",
			type:"post",
			data:$("#addInfo").serialize(),
			success:function(data){
			 if(data.flag=="1"){
	           $alert('success','商品添加成功');
	           var type =$("#type").val();
	           	   if(type=='1'){
		            window.location.href="${base}/leader/leaderGoodsRecommend!registerLeaderGoodsList.action";
		           }else{
		            window.location.href="${base}/leader/leaderGoodsRecommend!leaderRecommendGoodsList.action";
		           }
	          }else if(data.flag=="0"){
	            $alert('warn','商品添加失败');
	          }else if(data.flag=="2"){
	            $alert('warn','商品已经存在');
	          }else{
	          	$alert('warn','商品添加失败');
	          }
       }
 	 });
}


$(function(){
	$("#goodsno").focus(function(){
		var goodsno = $("#goodsno").val();
		if(goodsno==""){
			$("#glsp").val("");
			$("#goodsId").val("");
		}
	});
	$("#goodsno").blur(function(){
		var goodsno = $("#goodsno").val();
		if(goodsno!=""){
			$.ajax({type: 'POST',
				url: "${base}/leader/leaderGoodsRecommend!queryGoodsBrokerage.action" ,
				data: {"goodno":goodsno} ,
				dataType: "json",
				success: function(data){
					if(data.flag=='0'){//不存在，删掉输入的
						$alert("warn","未查询到海典编码["+goodsno+"]商品,或是商品佣金中没有该商品");
						$("#goodsId").val("");
						$("#goodsno").val("");
						$("#rebateAmount").html("");
						$("#goodsName").html("");
					}else if(data.flag=='1'){//存在，拼input值
						$("#goodsId").val(data.result.goods_id);
						$("#rebateAmount").html(data.result.rebate_amount+"元");
						$("#goodsName").html(data.result.goods_name);
					}else if(data.flag=='2'){
						$alert("warn","未查询到海典编码["+goodsno+"]商品,或是商品佣金中没有该商品");
						$("#goodsId").val("");
						$("#goodsno").val("");
						$("#rebateAmount").html("");
						$("#goodsName").html("");
					}
				},error:function(data){
					//不存在，删掉输入的
					$alert("warn","未查询到海典编码["+goodsno+"]商品,或是商品佣金中没有该商品");
					$("#goodsId").val("");
					$("#goodsno").val("");
					$("#rebateAmount").html("");
					$("#goodsName").html("");
				}
			});
		}else{
			$("#goodsId").val("");
			$("#goodsno").val("");
			$("#rebateAmount").html("");
		    $("#goodsName").html("");
		}
	});
})

</script>
</html>