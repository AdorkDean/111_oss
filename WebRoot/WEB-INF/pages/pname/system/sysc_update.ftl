<!DOCTYPE html">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>配置文件管理</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;}
body,td,th {font-family: 微软雅黑;font-size: 13px;}
.tac{position: absolute;width: 950px;background: #d0d0d0;}
.act{width: 600px;}
.colordiv{position: absolute;height: 23px;background: #DDD9D9;}
.fct{font-family: 微软雅黑;font-size: 13px;font-weight: normal;font-style: normal;text-decoration: none;color: #CC6633;}
.la{padding: 5px;}
.tda{width: 118px;}
.tdb{width: 250px;}
.frm{font-size: 12px;color: red;}
.lia{text-decoration: none!important ;color: #706D6D!important;}
.hideTr{display: none;}
</style>
</head>
<body>
<div>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="24" bgcolor="#576d85">
		  <tr>
			 <td width="2%" height="19" valign="middle" align="center"><img src="../web/images/tb.gif" width="14" height="14" /></td>
			 <td width="98%" valign="bottom" class="l_tit"><#if flag=='1'>配置文件修改</#if><#if flag=='0'>配置文件查看</#if></td>
		  </tr>
    	</table>
	</td>
  </tr>
  
<tr> <td>
<form action="${base}/systemmanage/sysc!syscUpdate.action" method="post" name="form1" id="form1">
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
<input type="hidden" value="${sysp.id?default(0)}" name="sysp.id" id="id" />
   <tr>
    <td class="tda" align="left" bgcolor="#FFFFFF" style="width: 10%;">Key:</td>
    <td class="tda" align="left" bgcolor="#FFFFFF">
    <input type="text" id="sysKey" name="sysp.sysKey" onblur="getkey(this.value)" value="${sysp.sysKey?default('')}"/>
    <span id="sid" style="color:red"></span>
    </td>
  </tr>
   <tr>
    <td class="tda" align="left" bgcolor="#FFFFFF" style="width: 10%;">Value:</td>
    <td class="tda" align="left" bgcolor="#FFFFFF">
    	<input type="text" id="sysValue" name="sysp.sysValue" value="${sysp.sysValue?default('')}"/>
    </td>
  </tr>
   <tr>
    <td class="tda" align="left" bgcolor="#FFFFFF" style="width: 10%;">备注:</td>
    <td class="tda" align="left" bgcolor="#FFFFFF">
    <input type="text" id="remark" name="sysp.remark" value="${sysp.remark?default('')}"/>
    </td>
  </tr>
  <tr>
    <td colspan="6" align="center" bgcolor="#FFFFFF">
     <#if flag=='1'>
     	<input type="submit" name="save" id="save" value="修 改"/>
     </#if>
     &nbsp;&nbsp;&nbsp;&nbsp;
     <a href="${base}/systemmanage/sysc!syscList.action"　><input type="button" name="cancel" id="cancel" value="取消" /></a>
    </td>
  </tr>
</table>
</form>
	</td>
  </tr>
</table>
</div>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>
function getkey(v) {
	var rt = 0;
	if($.trim(v)!=""){
		jQuery.ajax
		   ({
		       type: "post",
		       async: false,
		       url: " ${base}/systemmanage/sysc!syscGetKey.action",	  
		       data:{"keys":v,"id":$("#id").val()},
		       success: function(data)
		       {
		          if(data>0){
		          	$("#sid").html("Key已经存在!")
				    rt=1
		          }else{
		          	$("#sid").html("");
		          }
		       },error:function(){
		       		alert("网络异常");
		       }
		       
		   }); 
	}
    return rt;
}

	$("#form1").submit(function tijiao() {
	     var sysKey = $('#sysKey').val();
		 var sysValue = $('#sysValue').val();
		 var remark = $('#remark').val();
		 if($.trim(sysKey)==""){
		 	alert("Key不能为空");
		 	return false;
		 }else{
		 	if(1==getkey(sysKey)){
		 		alert("Key已经存在!");
		 		return false;
		 	}
		 	if(50<sysKey.length){
		 		alert("Key不能超过50字符!");
		 		return false;
		 	}
		 }
		 if($.trim(sysValue)==""){
		 	alert("Value不能为空");
		 	return false;
		 }else{
		 	if(200<sysValue.length){
		 		alert("Value不能超过200字符!");
		 		return false;
		 	}
		 }
		 if($.trim(remark)!=""){
		 	if(200<remark.length){
		 		alert("备注不能超过200字符!");
		 		return false;
		 	}
		 }
		alert("保存成功!");
	});
</script>
</body>
</html>