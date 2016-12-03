<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<#include "/WEB-INF/pages/inc/common.ftl">
<script type="text/javascript" src="${base}/web/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/web/js/input.js"></script>
<script type="text/javascript">
</script>
<head> 
	<title>查看</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/physician/physician!savePhysicianInfo.action" method="post" name="addInfo" id="addInfo" enctype="multipart/form-data">
    	<div class="m-contents">
	    	<div class="labelnamese">昵称：</div>
	    	${tleader.nickName?default('')}
    	</div>
    	<div class="m-contents">
	    	<div class="labelnamese">用户姓名：</div>
	    	 ${tleader.realName?default('')}
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">用户头像：</div>
	    	 <a target="_blank" href="${ui1}${tleader.headUrl?default('')}"><img src='${ui1}${tleader.headUrl?default('')}' width="50px" height ="50px"/></a>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">药师资质：</div>
	    	 <#list result.get(0)?if_exists as leaderimage>
	    		<a target="_blank" href="${ui1}${leaderimage.image_url?default('')}"><img src='${ui1}${leaderimage.image_url?default('')}' width="50px" height ="50px"/></a>
	    	 </#list>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">身份证正面：</div>
	    	<a target="_blank" href="${ui1}${tleader.cardFirstUrl?default('')}"><img src='${ui1}${tleader.cardFirstUrl?default('')}' width="50px" height ="50px"/></a>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">身份证反面：</div>
	    	<a target="_blank" href="${ui1}${tleader.cardTwoUrl?default('')}"><img src='${ui1}${tleader.cardTwoUrl?default('')}' width="50px" height ="50px"/></a>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">手机号：</div>
	    		${tleader.phone?default('')}
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">联系地址：</div>
	    		${tleader.address?default('')}
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">身份证号：</div>
	    		${tleader.cardCode?default('')}
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">审核备注：</div>
	    	<input type="text" name="auditRemark" class="i-text-i" id="remark" value="" maxlength="300"/>(最多输入300个字符)
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<a  id="noPassAction">不通过</a>
	    	<a href="${base}/leader/authstrLeader!pass.action?id=${tleader.id?default('')}">通过并发送通知</a>
    	</div>
    		
    </form>
    </div>   
</body>
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	  $("#noPassAction").click(function(){
			 $.ajax({
				    url: "${base}/leader/authstrLeader!noPass.action",
				    type: "GET",
				 	data: { 
				 		id: ${tleader.id?default('')},
				 		auditRemark: $("#remark").val(),
				 	},
				  	cache: false,
			      	success: function(data) {
					if(data==0){
						location.href="${base}/leader/authstrLeader!list.action";
					}else{
						alert("数据异常！");
					}		
					return true;
				}
			 });
	  });
});
</script>
</html>