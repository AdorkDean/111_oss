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
		                <td width="94%" valign="bottom" class="l_tit">权限列表</td>
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
     	<form name="form1" id="subform"  action="${base}/user/roleEdit!listRoles.action" method="post">
     					角色名称：<input type="text" name="name" value="${name?default('')}" class="input_time">&nbsp;&nbsp;
     					角色描述：<input type="text" name="desc" value="${desc?default('')}" class="input_time">&nbsp;&nbsp;		
							平台：
						    	<select name="webid" style="height:23px;">
									<OPTION  value="" ></OPTION>	
									<OPTION  value="1" <#if webid?if_exists=='1'>selected</#if>>网站后台</OPTION>
									<OPTION  value="2" <#if webid?if_exists=='2'>selected</#if>>微信后台</OPTION>
								</select>        						   			        
						<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?if_exists}" id="p_curPage">
						<input type="hidden" name="rs.p_pageSize" value="20" id="pageSize">						
						<input type="button" name="buttonsub" id="buttonsub" value="查询" class="btn_sm" />
						<input type="button" name="buttonsub" id="buttonadd" value="增加" class="btn_sm" />				       
		</form>        
     </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#99c3db" class="w_td" >
      <tr class="t_b">        
        <td width="3%" align="center">序号</td>
        <td width="15%" align="center">角色名称</td>
        <td width="20%" align="center">角色描述</td>
        <td width="5%" align="center">状态</td>
        <td width="5%" align="center">平台</td>
        <td width="10%" align="center">创建时间</td>
        <td width="10%" align="center">操作</td>

      </tr>
      
      <#list pw.result?if_exists as role>
			      <tr>			        
			        <td height="20" align="center" class="bc">${role_index+1?default('')}</td>
			        <td align="center" class="bc table_left">${role.roleName?default('')}</td>
			        <td align="left" class="bc table_left">&nbsp;&nbsp;${role.roleDesc?default('')}</td>
			        <td align="center" class="bc"><#if role.status?if_exists=1>有效<#elseif role.status?if_exists=2>禁用</#if></td>
			        <td align="center" class="bc"><#if role.webid?default(0)=1>网站后台<#elseif role.webid?default(0)=2>微信后台</#if></td>
			        <td align="center" class="bc">${role.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			        <td align="center" class="bc"><#if role.editable?default(-1)==1><a href="${base}/user/roleEdit!toEditAddRole.action?role.id=${role.id?default('')}">修改</a>|<a href="${base}/user/roleEdit!toEditResc.action?id=${role.id?default('')}">修改资源</a>|<a href="${base}/user/roleEdit!deleteRoles.action?role.id=${role.id?default('')}"  onClick="javascript:return confirm('该删除操作将无法恢复！是否继续？')" >删除</a><#else><span style="color:red;">不可修改</span></#if></td>			        
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
	var buttonadd=$("#buttonadd");
	buttonadd.click(function(){
		window.location="${base}/user/roleEdit!toEditAddRole.action";
	});
	buttonsub.click(function(){
		p_curPage.attr("value","1");
		$("#subform").attr("action","${base}/user/roleEdit!listRoles.action");
		$("#subform").submit();
	});
	
</script>




