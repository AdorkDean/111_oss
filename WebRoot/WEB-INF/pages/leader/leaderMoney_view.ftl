<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<#include "/WEB-INF/pages/inc/common.ftl">
<script type="text/javascript" src="${base}/web/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/web/js/input.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript">
</script>
<head> 
	<title>查看</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="" method="post" name="addInfo" id="addInfo" enctype="multipart/form-data">
    	<div class="m-contents">
	    	<div class="labelnamese">设置状态：</div>
	    	<select name="tleader.status" id="leaderStatus" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option value="1" id="op1" <#if tleader.status == 1>selected</#if> >正常</option>
					 <option value="2" id="op2" <#if tleader.status == 2>selected</#if>>暂停使用</option>
			</select>
    	</div>
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
    </form>
    </div>   
</body>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>	
	$("#leaderStatus").click(function(){
	var  leaderStatus=	$("#leaderStatus").val();
	var  optionText=$("#leaderStatus").find("option:selected").text();
	       $.ajax({
				url: "${base}/leader/leaderMoney!changeStatus.action",
				data:{
				leaderStatus:leaderStatus,
				id:${tleader.id?default('')},
				},
				type: "GET",
				cache: false,
				success: function(data) {
			         if(data==0){
			      	 $alert("warn","数据异常！");
			         }
				}
		   });
	});
</script>
</html>