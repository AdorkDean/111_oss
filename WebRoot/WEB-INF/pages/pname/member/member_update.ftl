<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>修改用户信息</title> 
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/ajaxfileupload.js"></script>
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="" method="post" name="addInfo" id="addInfo">
     <input type="hidden" name="brand.id" value=""/>
    <div >
    
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">手机号码：</div>
	    	<input type="text"  name="mobile" class="i-text-i" id="mobile" value="${member.mobile?default('')}"/>
    	</div>
    
         <div class="m-contents" style="margin-top:10px;">
	    		<div class="labelnamese">会员等级：</div>
	    		<select onchange="getfl(1)" id="gradeName" class="i-text-i" >
	    		  <#list gradeList?if_exists as resc>
	    		      <option <#if member.memberGradeId?default(-1)==resc.id?default(0)>selected</#if> value ="${resc.id?default(0)}">${resc.name?default('')}</option>
	    		  </#list>
	    		</select>
    		<div class="labelnamese">真实姓名：</div>
	    	<input type="text"  name="realName" class="i-text-i" id="realName" value="${member.realName?default('')}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">邮箱：</div>
	    	<input type="text"  name="email" class="i-text-i" id="email" value="${member.email?default('')}"/>
	    	<div class="labelnamese">昵称：</div>
	    	<input type="text" name="nickName" class="i-text-i" id="nickName" value="${member.nickName?default('')}"/>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="submitForm();" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" onclick="javascript:history.go(-1);" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   
</body>
<script>
function submitForm(){
var userName=$("#userName").val();
var password=$("#password").val();
var realName=$("#realName").val();
var email=$("#email").val();
var nickName=$("#nickName").val();
var gradeName=$("#gradeName").val();
if($.trim(userName)==""){
alert("手机号码不能为空");
return;
}else{
   var pattern = new RegExp(/^[1][3,4,7,5,8][0-9]{9}$/);
   if(!pattern.test($.trim(userName))){
     alert("手机号码格式不正确");
     return;
   }
}

if($.trim(password)==""||password.length<6 || password.length>17){
	alert("请输入6-17位密码");
	return;
}

$.post("${base}/member/member!addMember.action",{gradeName:gradeName,userName:userName,password:password,realName:realName,email:email,nickName:nickName,random:Math.random()},function(data){
   if(data==2){
     alert('用户已存在！');
   }else if(data==0){
     alert('系统错误！');
   }else{
     alert('添加成功！');
      window.location.href="${base}/member/member!getMemberList.action";
   }
});

}


</script>
</html>