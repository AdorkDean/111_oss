<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>798管理系统工作平台</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />			
</head>

<body style="margin:0 3px" height="100%" >
<form action="${base}/user/useroperate!toEditPost.action" method="post" name="form1" id="rolesub">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#576d85"> 
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		          <tr>
		                <td width="6%" height="19" valign="middle" align="center"><img src="${base}/web/images/tb.gif" width="14" height="14" /></td>
		                <td width="94%" valign="bottom" class="l_tit">用户岗位修改</td>            
		          </tr>
		        </table>        
        </td>
      </tr>
    </table></td>
  </tr>
  		          <tr>
		            <td class="pl15">
						岗位备注：<input type="text" name="postremark" value="${postremark?if_exists}">
						岗位名称：<input type="text" name="postname" value="${postname?if_exists}">	
						平台：
					    	<select name="webid" style="height:23px;">
								<OPTION  value="" ></OPTION>	
								<OPTION  value="1" <#if webid?if_exists=='1'>selected</#if>>网站后台</OPTION>
								<OPTION  value="2" <#if webid?if_exists=='2'>selected</#if>>微信后台</OPTION>
							</select>											
								 <input type="hidden" name="userid" value="${userid?if_exists}">
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
        <td width="10%" align="center">岗位名字</td>
        <td width="20%" align="center">岗位备注</td>
        <td width="5%" align="center">状态</td>
        <td width="10%" align="center">创建时间</td>
      </tr>
      
      <#list sysPost?if_exists as syspost>
			             	<#assign ckd="">	             	
			             	<#list userPost?if_exists as userpost>
			             		<#if userpost.id=syspost.id >
			             			<#assign   ckd="checked='checked'">
			             		</#if>
			             	</#list>      
			      <tr>			        
			        <td height="20" align="center" style="width:5%;" class="bc">
			        	<input name="showpost" type="checkbox" style="display:none" checked="checked" value="${syspost.id?default(0)}" />
			        	<input name="postid" type="checkbox" ${ckd?default('')} value="${syspost.id?default(0)}" id="${syspost.id?default(0)}" )"/>
			        </td>
			        <td class="bc table_left" >${syspost.name?default('')}</td>
			        <td class="bc table_left" >${syspost.remark?default('')}</td>
			        <td class="bc table_left" ><#if syspost.enable?default(-1)=1>可用<#elseif syspost.enable?default(-1)=0>停用</#if></td>
			        <td class="bc table_left" >${syspost.createDt?string('yyyy-MM-dd HH:mm:ss')}</td>
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
		$("#rolesub").attr("action","${base}/user/useroperate!editUserPost.action");
		$("#rolesub").submit();
	}
);

</script>