<html>
<head>
<title>111医药馆管理系统工作平台</title>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>

<base target="main">
<link href="${base}/frame/images/skin.css" rel="stylesheet" type="text/css">


</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="61%" height="64">
    	<a href="http://www.111yao.com"><img src="${base}/frame/images/logo1.jpg" width="93" height="64"></a>
    	<a href="/"><img src="${base}/frame/images/logo2.jpg" width="170" height="64"></a>
    </td>
    <td width="39%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="width:30%" height="38" class="admin_txt"> 您好：${user.username}， 感谢登陆使用！</td>
        <td style="width:30%" height="38" class="admin_txt" id="showtime">${nowDate?string("yyyy-MM-dd HH:mm")}</td>
        <td style="width:10%" ><a href="${base}/j_spring_security_logout" target="_parent"><img src="${base}/frame/images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
<script src="http://libs.baidu.com/jquery/1.7.2/jquery.js"></script>
<script>
function getTime(){
	$.get("${base}/login/login!showTime.action",function(data){
		$("#showtime").text(data);
	});
}
function show(){
   var mydate = new Date();
   var str = "" + mydate.getFullYear() + "年";
   str += (mydate.getMonth()+1) + "月";
   str += mydate.getDate() + "日";
   $("#showtime").text(str);
  }

self.setInterval("show()",60000);

</script>