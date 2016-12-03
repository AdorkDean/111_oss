<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>咨询详细</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 

<body> 
<div></div>
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/goodsConsult/saveReplyGoodsConsult!saveReplyGoodsConsult.action" method="post" name="addInfo" id="addInfo">
    	<input type="hidden" id="id" name="id" value="${tGoodsConsult.id}"/>
    	<div class="m-contents">
	    	<div class="labelnamese">商品名称：</div>
	    	<input type="text" name="goodsName" disabled="true" class="i-text-i" id="goodsName" value="<#if tGoods?exists>${tGoods.goodsName?if_exists}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">会员名：</div>
	    	<input type="text" name="userName" disabled="true" class="i-text-i" id="userName" value="<#if tMember?exists>${tMember.userName?if_exists}</#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">咨询内容：</div>
	    	<!--<input type="text" name="consultContent" disabled="true" class="i-text-i" id="consultContent" value="${tGoodsConsult.consultContent?if_exists}"/>-->
	    	
	    	<textarea disabled="true" style="background-color: #f5f5f5;width: 250px;" name="consultContent" class="taa-ui" id="consultContent">${tGoodsConsult.consultContent?if_exists}</textarea>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">咨询时间：</div>
	    	<input type="text" name="createDate" disabled="true" class="i-text-i" id="createDate" value="${tGoodsConsult.createDate?if_exists?string("yyyy-MM-dd HH:mm:ss")}"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">是否显示：</div> 
		    	<#if tGoodsConsult.isShow?exists && tGoodsConsult.isShow?if_exists==1>
			    	<input type="radio" style="width: 14px;" name="isShow" class="i-text-cb" id="isShow1" checked="true" value="1"/><span style="border:0px; height: 15px; margin-top: 7px;width:60px;" class="i-text-cb">显示</span>
			    	<input type="radio" style="width: 14px;" name="isShow" class="i-text-cb" id="isShow2" value="0"/><span style="border:0px;width:45px; height: 15px; margin-top: 7px;" class="i-text-cb">不显示</span>
				<#elseif tGoodsConsult.isShow?exists && tGoodsConsult.isShow?if_exists==0>
			    	<input type="radio" style="width: 14px;" name="isShow" class="i-text-cb" id="isShow1" value="1"/><span style="border:0px; height: 15px; margin-top: 7px;" class="i-text-cb">显示</span>
			    	<input type="radio" style="width: 14px;" name="isShow" class="i-text-cb" id="isShow2" checked="true" value="0"/><span style="border:0px;width:45px; height: 15px; margin-top: 7px;" class="i-text-cb">不显示</span>
				</#if>
    	</div>
    	<hr/>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">回复时间：</div>
	    	<input type="text" name="answerDate" disabled="true" class="i-text-i" id="answerDate" value="<#if tGoodsConsult?exists && tGoodsConsult.answerDate?exists>${tGoodsConsult.answerDate?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">回复内容：</div>
	    	<textarea style="background-color:#fff;" name="answer" class="taa-ui" id="answer">${tGoodsConsult.answer?if_exists}</textarea>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<!--<input type="button" class="btn01" value="提交" onclick="reply()" style="margin-left:10px;"/>-->
	    	<input type="button" class="btn01" value="返回" onclick="goList()" style="margin-left:10px;"/>
    	</div>
    </form>
    </div>   
    
    
 <script type="text/javascript">
		function reply(){
		
			var id = $("#id").val();
			var isShow = $('input[name=isShow]:checked').val();
			var answer = $('input[name=answer]').val();
			if(id==null || id==""){
				$alert("warn","id不能为空!");
				return false;
			}
			if(isShow==null || isShow==""){
				$alert("warn","请选择是否显示!");
				return false;
			}
			if(answer==null || answer==""){
				$alert("warn","请输入回复内容!");
				return false;
			}
			var con = confirm("确定提交?");
			if(!con){
				return;
			}
			$("#_isShow").val(isShow);
			$("form").submit();
		}
		
		function goList(){
			window.location.href="${base}/goodsConsult/goodsConsult!goodsConsultPage.action";
		}
</script>
</body>

</html>