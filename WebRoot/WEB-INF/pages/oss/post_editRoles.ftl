<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>798管理系统工作平台</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />			
</head>

<body style="margin:0 3px" height="100%" >
<form action="${base}/user/postEdit!toEditRole.action" method="post" name="form1" id="rolesub">
<!--form action="${base}/user/useroperate!editUserRoles.action" method="post" id=""-->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#576d85"> 
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		          <tr>
		                <td width="6%" height="19" valign="middle" align="center"><img src="${base}/web/images/tb.gif" width="14" height="14" /></td>
		                <td width="94%" valign="bottom" class="l_tit">岗位管理_用户角色修改</td>            
		          </tr>
		        </table>        
        </td>
      </tr>
    </table></td>
  </tr>
  		          <tr>
		            <td class="pl15">
						角色描述：<input type="text" name="roledesc" value="${roledesc?if_exists}">
						角色名称：<input type="text" name="rolename" value="${rolename?if_exists}">	
							平台：
						    	<select name="webid" style="height:23px;">
									<OPTION  value="" ></OPTION>	
									<OPTION  value="1" <#if webid?if_exists=='1'>selected</#if>>网站后台</OPTION>
									<OPTION  value="2" <#if webid?if_exists=='2'>selected</#if>>微信后台</OPTION>
								</select>													
								 <input type="hidden" name="id" value="${id?if_exists}">
								 <input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?if_exists}" id="p_curPage">
								 <input type="hidden" name="rs.p_pageSize" value="20" id="pageSize">		
								 <input type="submit" value="查询">	
								 <input type="button" id="roleupdate" value="提交修改">	
					</td>            
		          </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#99c3db" class="w_td" >
      <tr class="t_b">        
        <td width="3%" align="center">选择</td>
        <td width="5%" align="center">角色描述</td>
        <td width="13%" align="center">角色名字</td>
      </tr>
      
      <#list sysRoles?if_exists as sysrole>
		             		<#if sysrole.roleName=='ROLE_USER' || sysrole.roleName=='ROLE_LOGIN'>
		             			<#assign   disab="disabled='disabled'">style="color:red;"
		             			<#assign   col="color:red;">
		             		<#else>
		             			<#assign   disab="">
		             			<#assign   col="">
		             		</#if>      
			             	<#assign ckd="">	             	
			             	<#list postRole?if_exists as userrole>
			             		<#if userrole.id=sysrole.id >
			             			<#assign   ckd="checked='checked'">
			             		</#if>			             		
			             	</#list>      
			      <tr>			        
			        <td height="20" align="center" style="width:5%;" class="bc">
			        	<input name="showroles" type="checkbox" style="display:none" ${ckd?default('')} value="${sysrole.id?default(0)}" />
			        	<input name="rolesid" type="checkbox" ${ckd?default('')} ${disab?default('')}  value="${sysrole.id?default(0)}" id="${sysrole.id?default(0)}" )"/>
			        </td>
			        <td class="bc table_left" style="width:40%;${col?default('')}" >${sysrole.roleDesc?default('')}</td>
			        <td class="bc table_left" style="width:45%;${col?default('')}" >${sysrole.roleName?default('')}</td>
			      </tr>
      </#list>
      
    </table></td>
  </tr>
</form>  
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
<script>	
$("#roleupdate").click(function(){
		$("#rolesub").attr("action","${base}/user/postEdit!editPostRoles.action");
		$("#rolesub").submit();
	}
);

</script>