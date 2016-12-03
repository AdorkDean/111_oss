<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>798管理系统工作平台</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />			
</head>

<body style="margin:0 3px" height="100%" >
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
                <td width="94%" valign="bottom" class="l_tit">权限所属资源</td>
              </tr>
            </table></td>
            <td align="right" class="l_tit" valign="middle">
              </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>

  <form action="${base}/user/roleEdit!editRoleResc.action" method="post" name="form1" id="subform">
  <tr>
     <td height="35px" class="pl15">
     	资源名称：<input type="text" name="rescname" id="rescname" value="${rescname?default('')}" class="input_time" />
     	资源URL：<input type="text" name="rescurl" id="rescurl" value="${rescurl?default('')}" class="input_time" />
			平台：
		    	<select name="webid" style="height:23px;">
					<OPTION  value="" ></OPTION>	
					<OPTION  value="1" <#if webid?if_exists=='1'>selected</#if>>网站后台</OPTION>
					<OPTION  value="2" <#if webid?if_exists=='2'>selected</#if>>微信后台</OPTION>
				</select>		
		<input type="button" name="buttonquery" id="buttonquery" value="查询" class="btn_sm" />&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="buttonsub" id="buttonsub" value="保存" class="btn_sm" />		
		<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">			               
		<input type="hidden" name="id" value="${id?default(-1)}" >
     </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#99c3db" class="w_td" >
      <tr class="t_b">        
	  	<td width="3%" align="center">&nbsp;</td>
        <td width="3%" align="center">序号</td>
        <td width="20%" align="center">URL</td>
        <td width="20%" align="center">名字</td>
        <td width="20%" align="center">描述</td>
      </tr>
      
	          	<#list sysRescPw.result?if_exists as rescsys>
	          		
	             	
		             	<#assign ckd="">	             	
		             	<#list userResc?if_exists as rescuser>
		             		<#if rescsys.id=rescuser.id >
		             			<#assign   ckd="checked='checked'">
		             		</#if>
		             	</#list>
				      <tr>			        
					  	<td height="20" align="center" class="bc">
				  			<input name="rescid" type="checkbox" ${ckd?default('')} value="${rescsys.id?default(0)}" id="${rescsys.id?default(0)}" )"/>
				  			<input name="resciddels" style="display:none;" type="checkbox" ${ckd?default('')} value="${rescsys.id?default(0)}" id="${rescsys.id?default(0)}" )"/>
					  	</td>
				        <td height="20" align="center" class="bc">${rescsys_index+1?default('')}</td>
				        <td align="left" class="bc">&nbsp;&nbsp;${rescsys.resString?default('')}</td>
				        <td align="left" class="bc">&nbsp;&nbsp;${rescsys.name?default('')}</td>
				        <td align="left" class="bc">&nbsp;&nbsp;${rescsys.descn?default('')}</td>
				      </tr>
      			</#list>
      
    </table></td>
  </tr>
</form>

</table>
	<table width="100%">
	  <tr>
	  <#include "/WEB-INF/pages/inc/pagebox_condition_role_resc_edit.ftl">
	  </tr>
	</table>  
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>	
	$("input[name='moduleId']").click(function(){	
		var p_id=$(this).attr("id");
		var f=$(this).attr("checked");
		var id=p_id+"_ul";		
		var child=$("#"+id).children();		
		$.each(child,function(){
			var ipt=$(this).children();
			$.each(ipt,function(){
				$(this).attr("checked",f)
				$(this).attr("disabled",!f)
			});			
		});
	});

	var buttonsub=$("#buttonsub");
	var buttonquery=$("#buttonquery");
	var p_curPage=$("#p_curPage");
	var subform=$("#subform");

	buttonsub.click(function(){
		subform.attr("action","${base}/user/roleEdit!editRoleResc.action");
		subform.submit();
	});
	buttonquery.click(function(){
		p_curPage.attr("value",1);
		subform.attr("action","${base}/user/roleEdit!toEditResc.action");
		subform.submit();
	});
</script>