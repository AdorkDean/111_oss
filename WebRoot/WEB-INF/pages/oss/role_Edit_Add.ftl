<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>798管理系统工作平台</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />		
<script type="text/javascript" language="javascript" src="${base}/web/js/calendar4_7.js"></script>	
</head>
<script>

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
                <td width="94%" valign="bottom" class="l_tit">权限修改/增加</td>
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
    <form action="${base}/user/roleEdit!saveRole.action" id="form1">
				      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab_input">
				  	<tr >
				  		<input name="role.id" type="hidden"  value="<#if role?exists>${role.id?if_exists}</#if>"/>
				  		<input name="role.createTime" type="hidden"  value="<#if role?exists>${role.createTime?if_exists}</#if>"/>
				    	<td width="10%">角色名称：</td>
				    	<td width="20%"><input name="role.roleName" type="text"  value="<#if role?exists>${role.roleName?if_exists}</#if>" class="input" id="loginName"/></td>
				    	<td>&nbsp;</td>
				    	<td>&nbsp;</td>
				    </tr>
				  	<tr>
					    <td>描述：</td>
					    <td><input  type="text" name="role.roleDesc" class="input" id="name" value="<#if role?exists>${role.roleDesc?if_exists}</#if>"/></td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>
				    <tr>
					    <td>平台：</td>
					    <td>
					    	<select name="role.webid" class="select" style="height:25px;">	
								<OPTION  value="1" <#if role?exists && role.webid?default(0)==1>selected</#if>>网站后台</OPTION>
								<OPTION  value="2" <#if role?exists && role.webid?default(0)==2>selected</#if>>微信后台</OPTION>
							</select>	
					    </td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>
				  	<tr>
					    <td>状态：</td>
					    <td>
					    	<select name="role.status" class="select" style="height:25px;">
								<OPTION  value="1" <#if role?exists && role.status?default(0)==1>selected</#if>>有效</OPTION>
								<OPTION  value="2" <#if role?exists && role.status?default(0)==2>selected</#if>>禁用</OPTION>
							</select>	
					    </td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
				    </tr>				    
				    			    				   
				  	<tr>
					    <td>&nbsp;</td>
					    <td colspan="3"><input type="submit" class="btn_sm" value="保 存" /></td>
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
