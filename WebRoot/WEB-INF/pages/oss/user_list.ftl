<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>798管理系统工作平台</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />			
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
		                <td width="6%" height="19" valign="middle" align="center"><img src="${base}/web/images/tb.gif" width="14" height="14" /></td>
		                <td width="94%" valign="bottom" class="l_tit">用户列表</td>
		              </tr>
		            </table></td>
		            <td align="right" class="l_tit" valign="middle">
		              &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</td>
		          </tr>
		        </table>        
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
     <td height="35px" class="pl15">
     	<form name="form1" id="subform"  action="${base}/user/useroperate!listUsers.action" method="post">
     					登录名：<input type="text" name="loginName" value="${loginName?default('')}" class="input_time">&nbsp;&nbsp;
     					部门：<input type="text" name="department" value="${department?default('')}" class="input_time">&nbsp;&nbsp;
     					电话：<input type="text" name="mobile" value="${mobile?default('')}" class="input_time">&nbsp;&nbsp;
     					E-mail：<input type="text" name="email" value="${email?default('')}" class="input_time">&nbsp;&nbsp;
						<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?if_exists}" id="p_curPage">
						<input type="hidden" name="rs.p_pageSize" value="20" id="pageSize">						
						<input type="button" name="buttonsub" id="buttonsub" value="查询" class="btn_sm" />
						<input type="button" name="buttonsub" id="buttonsadd" value="增加" class="btn_sm" />
		</form>        
     </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#99c3db" class="w_td" >
      <tr class="t_b">        
        <td width="3%" align="center">序号</td>
        <td width="6%" align="center">登录名称</td>
        <td width="6%" align="center">用户姓名</td>
        <td width="5%" align="center">部门</td>
        <td width="8%" align="center">电话</td>
        <td width="8%" align="center">电子邮件</td>
        <td width="8%" align="center">创建时间</td>
        <td width="3%" align="center">状态</td>
        <td width="15%" align="center">操作</td>

      </tr>
      
      <#list pw.result?if_exists as user>
			      <tr>			        
			        <td height="20" align="center" class="bc">${user_index+1?default('')}</td>
			        <td align="center" class="bc table_left">${user.loginName?default('')}</td>
			        <td align="center" class="bc table_left">${user.name?default('')}</td>
			        <td align="center" class="bc">${user.department?if_exists}</td>
			        <td align="center" class="bc">${user.telephone?default('')}</td>
			        <td align="center" class="bc">${user.email?default('')}</td>
			        <td align="center" class="bc">${user.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			        <td align="center" class="bc"><#if user.status?default(0)==1>有效<#elseif user.status?default(0)==2>禁用<#elseif user.status?default(-1)==0>删除</#if></td>
			        <td align="center" class="bc"><a href="${base}/user/useroperate!toedituser.action?userinfo.id=${user.id?default('')}">修改</a>|<a href="${base}/user/useroperate!toEditModule.action?userid=${user.id?default('')}">修改模块</a>|<a href="${base}/user/useroperate!toEditPost.action?userid=${user.id?default('')}">修改岗位</a>|<a href="${base}/user/useroperate!deleteUser.action?userinfo.id=${user.id?default('')}" onClick="javascript:return confirm('该删除操作将无法恢复！是否继续？')">删除用户</a></td>			        
			      </tr>
      </#list>
      
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>        
<#include "/WEB-INF/pages/inc/pagebox_condition.ftl">   
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script src="${base?if_exists}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
<script>	
	var p_curPage=$("#p_curPage");
	var buttonsub=$("#buttonsub");
	var buttonadd=$("#buttonsadd");
	var buttontoexcel=$("#buttontoexcel");
	buttonsub.click(function(){
		p_curPage.attr("value","1");
		$("#subform").attr("action","${base}/user/useroperate!listUsers.action");
		$("#subform").submit();
	});
	
	buttonadd.click(function(){
		window.location="${base}/user/useroperate!toedituser.action";
	});
</script>