<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>分配优惠劵</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="" method="get" name="addInfo" id="addInfo">
     <input type="hidden" name="id" value="${id?default(0)}"/>
    
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">输入优惠劵卡号：</div>
	    	<input type="text" name="cardNo" class="i-text-i" id="cardNo" value=""/>
    	</div>
    	
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUAN_FAFANGYOUHUIJUAN_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="submitForm(${id?default(0)});" style="margin-left:10px;"/>
	    	 </@security.authorize>
	    	<input type="button" class="btn01" value="返回" onclick="javascript:history.go(-1);" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   
</body>
<script>
function submitForm(id){
var cardNo=$("#cardNo").val();

if($.trim(cardNo)==""){
alert("卡号不能为空");
return;
}
$.ajax({
url:"${base}/member/member!addMemberCoupon.action",
			type:"post",
			data:{"id":id,"cardNo":cardNo},
			success:function(data){
                   if(data==0){
                   alert("分配优惠劵失败");
                    }else if(data==-1){
                    alert("优惠劵不存在或者已经失效，请检查优惠劵卡号");
                    }else{
                    alert("分配成功");
                    window.close();
                    }
                    }
  });
}

</script>
</html>