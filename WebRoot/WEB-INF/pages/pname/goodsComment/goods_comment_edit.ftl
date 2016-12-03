<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>评论编辑</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 

<body> 
<div></div>
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/goodsComment/updateGoodsComment!updateGoodsComment.action" method="post" name="addInfo" id="addInfo">
    	<input type="hidden" id="id" name="id" value="${tGoodsComment.id}"/>
    	<div class="m-contents">
	    	<div class="labelnamese">总评分：</div>
	    	<input type="text" name="sumFraction" disabled="true" class="i-text-i" id="sumFraction" style="" value="${tGoodsComment.sumFraction?if_exists+'星'}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">类型：</div>
	    	<#if tGoodsComment.type?exists && tGoodsComment.type==1>
        		<input type="text" disabled="true" class="i-text-i" value="好评"/>
        	<#elseif tGoodsComment.type?exists && tGoodsComment.type==2>
        		<input type="text" disabled="true" class="i-text-i" value="中评"/>
        	<#elseif tGoodsComment.type?exists && tGoodsComment.type==3>
        		<input type="text" disabled="true" class="i-text-i" value="差评"/>
        	<#else>
        		<input type="text" disabled="true" class="i-text-i" value="${tGoodsComment.type?if_exists}"/>
        	</#if>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">卖家好评：</div>
	    	<input type="text" name="seller" disabled="true" class="i-text-i" id="seller" style="color:#FFB200;" 
	    		value="<#if tGoodsComment.seller?exists><#list 1..tGoodsComment.seller as x2>★</#list></#if>"/>
	    		
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">快递好评：</div>
	    	<input type="text" name="fastMail" disabled="true" class="i-text-i" id="fastMail" style="color:#FFB200;" value="<#if tGoodsComment.fastMail?exists><#list 1..tGoodsComment.fastMail as x1>★</#list></#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">商品好评：</div>
	    	<input type="text" name="goods" disabled="true" class="i-text-i" id="goods" style="color:#FFB200;" value="<#if tGoodsComment.goods?exists><#list 1..tGoodsComment.goods as x3>★</#list></#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">快递员好评：</div>
	    	<input type="text" name="fastMailPeople" disabled="true" class="i-text-i" id="fastMailPeople" style="color:#FFB200;" value="<#if tGoodsComment.fastMailPeople?exists><#list 1..tGoodsComment.fastMailPeople as x4>★</#list></#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">评论内容：</div>
	    	<textarea class="i-text-i" id="comment" disabled="true" style="resize: none;height:120px;line-height: 20px;">${tGoodsComment.comment?if_exists}</textarea>
	    	<!--<span name="comment" style="margin-left: 10px;">${tGoodsComment.comment?if_exists}</span>-->
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">会员名：</div>
	    	<input type="text" name="userName" disabled="true" class="i-text-i" id="userName" value="<#if tMember?exists>${tMember.userName?if_exists}</#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">商品名称：</div>
	    	<input type="text" name="goodsName" disabled="true" class="i-text-i" id="goodsName" value="<#if tGoods?exists>${tGoods.goodsName?if_exists}</#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">订单编号：</div>
	    	<input type="text" name="orderSn" disabled="true" class="i-text-i" id="orderSn" value="<#if tOrder?exists>${tOrder.orderSn?if_exists}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">是否显示：</div> 
	    	<div style="float:left;margin-left: 10px;">
		    	<#if tGoodsComment.isShow?exists && tGoodsComment.isShow?if_exists==1>
			    	<input type="radio" style="width: 14px;" name="isShow" class="i-text-cb" id="isShow1" checked="true" value="1"/><span style="border:0px; height: 15px; margin-top: 7px;" class="i-text-cb">显示</span>
			    	<input type="radio" style="width: 14px;" name="isShow" class="i-text-cb" id="isShow2" value="0"/><span style="border:0px;width:45px; height: 15px; margin-top: 7px;" class="i-text-cb">不显示</span>
				<#elseif tGoodsComment.isShow?exists && tGoodsComment.isShow?if_exists==0>
			    	<input type="radio" style="width: 14px;" name="isShow" class="i-text-cb" id="isShow1" value="1"/><span style="border:0px; height: 15px; margin-top: 7px;" class="i-text-cb">显示</span>
			    	<input type="radio" style="width: 14px;" name="isShow" class="i-text-cb" id="isShow2" checked="true" value="0"/><span style="border:0px;width:45px; height: 15px; margin-top: 7px;" class="i-text-cb">不显示</span>
				</#if>
    	</div>
    	<input name="_isShow" id="_isShow" type="hidden" value="${tGoodsComment.isShow?if_exists}"/>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<#if mark=='1'><!-- 判断是否是更新如果是更新就显示提交 -->
        		<input type="button" class="btn01" value="提交" onclick="update()" style="margin-left:10px;"/>
        	</#if>
	    	<input type="button" class="btn01" value="返回" onclick="goList()" style="margin-left:10px;"/>
    	</div>
    </form>
    </div>   
    
    
 <script type="text/javascript">
		function update(){
			var id = $("#id").val();
			var isShow = $('input[name=isShow]:checked').val();
			if(id==null || id==""){
				$alert("warn","id不能为空!");
				return false;
			}
			if(isShow==null || isShow==""){
				$alert("warn","请选择是否显示!");
				return false;
			}
			$("#_isShow").val(isShow);
			$("form").submit();
		}
		
		function goList(){
			window.location.href="${base}/goodsComment/goodsComment!goodsCommentPage.action";
		}
</script>
</body>

</html>