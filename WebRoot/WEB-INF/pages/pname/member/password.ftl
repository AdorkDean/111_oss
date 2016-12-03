<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>重置密码</title> 
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
	    	<div class="labelnamese">输入新密码：</div>
	    	<input type="password" name="password" class="i-text-i" id="password" value=""/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">再次输入新密码：</div>
	    	<input type="password"  class="i-text-i" id="password1" value=""/>
    	</div>
    	
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUAN_CHONGZHIMIMA_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="submitForm(${id?default(0)});" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="返回" onclick="javascript:history.go(-1);" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   
</body>
<script>
function submitForm(id){
var password=$("#password").val();
var password1=$("#password1").val();

if($.trim(password)==""){
alert("密码不能为空");
return;
}
if($.trim(password1)==""){
alert("请重复输入密码");
return;
}
if(password!=password1){
alert("两次密码输入不一致");
return;
}
$.ajax({
url:"${base}/member/member!updatePassword.action",
			type:"post",
			data:{"id":id,"password":password},
			success:function(data){
                   if(data==0){
                   alert("密码修改失败");
                    }else if(data==-1){
                    alert("密码没有改动");
                    window.close();
                    }else{
                    alert("密码修改成功");
                    window.close();
                    }
                    }
  });
}

</script>
</html>