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
	function changePassword(){
	var password=$("#password").val();
	var passwordco=$("#passwordco").val();
	var userId=$("#userId").val();
		if(password==""){
			alert("密码不能为空");
			return;
		}else if(passwordco==""||password!=passwordco){
			alert("两次密码输入不一致，请重新输入");
			return;
		}else{
			$.get("${base}/user/useroperate!updatePassword.action",{userid:userId,password:password},
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
                <td width="94%" valign="bottom" class="l_tit"> 修改密码</td>
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
				      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab_input">
				  	<tr >
				    	<td width="10%">登录名称：</td>
				    	<td width="10%" align="left"><#if userinfo?exists><span>${userinfo.username?if_exists}</span></#if></td>
				    	<td width="40%" align="left"></td>
				    	<td width="40%" align="left"></td>
				    </tr>
				  	<tr>
					    <td>用户姓名：</td>
					    <td ><#if userinfo?exists><span>${userinfo.name?if_exists}</span></#if></td>
					    <td width="40%" align="left"></td>
				    	<td width="40%" align="left"></td>
				    </tr>
				  	<tr>
					    <td>输入新密码：</td>
					    <td ><input  type="password" name="password"  id="password" value=""/></td>
					    <td width="40%" align="left"></td>
				    	<td width="40%" align="left"></td>
				    </tr>
				  	<tr>
					    <td>重复输入密码：</td>
					    <td ><input  type="password" name="passwordco"  id="passwordco" value=""/></td>
					    <td width="40%" align="left"></td>
				    	<td width="40%" align="left"></td>
				    </tr>
				  	<tr>
					    <td colspan="2"><input type="button" onclick="changePassword();" value="提交"></td>
					    <td width="40%" align="left"></td>
				    	<td width="40%" align="left"></td>
				    </tr>
				</table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  
</table>
</body>
</html>
