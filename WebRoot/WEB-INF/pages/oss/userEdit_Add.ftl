<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>798管理系统工作平台</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script src="${base?if_exists}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
 <style type="text/css">
.input{width: 100%;border:1px solid #aecae2; height:18px; line-height:18px; padding-left:5px; color:#333;}
</style>
</head>
<script>
function submitInfo(){
	var loginName = document.getElementById("loginName").value;
	var password = document.getElementById("password").value;	
	var expireTime = document.getElementById("expireTime").value;
	var name = document.getElementById("name").value;
		
	if(loginName==""||loginName==null){alert("登陆名不能为空");return false;}
	if(name==""||name==null){alert("用户名不能为空");return false;}
	if(password==""||password==null){alert("密码不能为空");return false;}
	if(expireTime==""||expireTime==null){alert("有效不能为空");return false;}
	
	 document.getElementById("form1").submit();

}


function chgpwd(){
	var pwd=$("#password").val();
	if(pwd==""){
		alert("请输入新密码!");
		return;
	}else{
		var userinfoid=$("#userinfoid").val();
		$.get("${base}/user/useroperate!chgpwd.action",{pwd:pwd,userid:userinfoid},
			   function(data){
					alert(data);
		});
	}
}
</script>
<body style="margin:0 3px">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#576d85"> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="middle" align="center"><img src="${base}/web/images/tb.gif" width="14" height="14" /></td>
                <td width="94%" valign="bottom" class="l_tit"> 管理人员基本信息</td>
              </tr>
            </table></td>
            <td align="right" class="l_tit" valign="middle">
               &nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
  	
    <td valign="top">
    <form action="${base}/user/useroperate!addUser.action" id="form1">
				      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab_input">
				  	<tr >
				  		<input name="userinfo.id" type="hidden"  id="userinfoid" value="<#if userinfo?exists>${userinfo.id?if_exists}</#if>"/>
				    	<td width="10%">登陆名称：</td>
				    	<td width="15%"><input name="userinfo.loginName" type="text"  class="input" id="loginName" value="<#if userinfo?exists>${userinfo.loginName?if_exists}</#if>"/></td>
				    	<td>&nbsp;</td>
				    	<td>&nbsp;</td>
				    </tr>
				  	<tr>
					    <td>用户姓名：</td>
					    <td><input  type="text" name="userinfo.name" class="input" id="name" value="<#if userinfo?exists>${userinfo.name?if_exists}</#if>"/></td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>
				  	<tr>
					    <td>登陆密码：</td>
					    <td><input  type="password" name="userinfo.password" class="input" id="password" value=""/></td>
					    <#if userinfo?exists>
					    	<td><input type="button" onclick="chgpwd();" value="更改密码"></td>
				    	<#else>
				    		<td>&nbsp;</td>
				    	</#if>					    
					    <td>&nbsp;</td>
				    </tr>				    
				    <tr>
					    <td>部门：</td>
					    <td><input  type="text" name="userinfo.department" class="input" value="<#if userinfo?exists>${userinfo.department?if_exists}</#if>"/></td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>
				    <tr>
					    <td>有效期：</td>
					    <td><input  type="text" name="userinfo.expireTime" class="input" size="19"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" readonly id="expireTime" value="<#if userinfo?exists><#if userinfo.expireTime?exists>${userinfo.expireTime?string('yyyy-MM-dd')}</#if></#if>"/></td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>
				    <tr>
					    <td>电话：</td>
					    <td><input  type="text" name="userinfo.telephone" class="input" value="<#if userinfo?exists>${userinfo.telephone?if_exists}</#if>"/></td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>
				    <tr>
					    <td>电子邮件：</td>
					    <td><input  type="text" name="userinfo.email" class="input" value="<#if userinfo?exists>${userinfo.email?if_exists}</#if>"/></td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>	
				    
				    <tr>
					    <td>状态：</td>
					    <td><SELECT name="userinfo.status">
					    <#if userinfo?exists>
						    <#if userinfo.status?default(0)==2>
	                           	<option value="1">有效</option>
	                           	<option value="2" selected>禁用</option>
	                        <#elseif userinfo.status?default(0)==1>
	                           	<option value="1" selected>有效</option>
	                           	<option value="2" >禁用</option>	                                         
	                        </#if>
                        <#else>
	                        	<option value="1" >有效</option>
	                           	<option value="2" >禁用</option>     
					        </SELECT>	
					    </#if>  
					    </td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>					    
				    				    				    
				  	<tr>
					    <td>&nbsp;</td>
					    <td colspan="3"><input type="button" class="btn_sm" value="保 存" onclick="submitInfo()"/></td>
				    </tr>
				</table>
	</form>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  
</table>
</body>
</html>
