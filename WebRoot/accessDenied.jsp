<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>web/css/style.css" rel="stylesheet" type="text/css" />			
</head>

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
                <td width="6%" height="19" valign="middle" align="center"><img src="<%=basePath%>web/images/tb.gif" width="14" height="14" /></td>
                <td width="94%" valign="bottom" class="l_tit">无操作权限</td>
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
     <td >
       <div class="err_box">
       <img src="<%=basePath%>web/images/logo_404.jpg" width="145" height="59" /><br />
       <p style="color:red;">您的访问权限已被外星人劫持，请联系管理员，帮您夺回权限！
一场星球大战即将上演！</p>
       <p>&nbsp;</p></div>
    </td>
  </tr>

</table>
</body>
</html>
<script>
	alert("访问受限，请联系管理员！");
</script>
