<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>798管理系统工作平台</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />			
<style type="text/css">

</style>
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
                <td width="94%" valign="bottom" class="l_tit">用户模块修改</td>
              </tr>
            </table></td>
            <td align="right" class="l_tit" valign="middle">
              </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab_input">
  <form action="${base}/user/useroperate!toEditModule.action" method="post" id="subform">
  <!--form action="${base}/user/useroperate!editUserModules.action" method="post" id=""-->
  	<tr>
       <td width="5%">
       	<input type="hidden" name="userid" value="${userid?if_exists}">       	
       	平台：
		<select name="webid" style="width:100px;" >	     							
			<option value="1" <#if webid='1'>selected</#if>>网站后台</option>
			<option value="2" <#if webid='2'>selected</#if>>微信后台</option>
		</select>
		父模块名字：
		<input type="text" name="modulename" value="${modulename?if_exists}">
       	<input type="submit" value="查询">
       	<input type="button" onclick="save();" value="提交">
       </td>  	
  	</tr>
  	
  	<!--=================================================================================-->
  	<!--=================================================================================-->
  	<!--=================================================================================-->
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#99c3db" class="w_td" >
		      <tr class="t_b">        
		        <td width="3%" align="center">序号</td>
		        <td width="8%" align="center">父模块</td>
		        <td width="8%" align="center">子模块</td>
		        <td width="50%" align="center">&nbsp;</td>

		      </tr>
		        <#assign No=1>
	          	<#list listParent?if_exists as module>
	          		<#if module.parentid?default(0)=0>
			             	<tr style="height:20px">
			             	<#assign ckd="">
			             	<#assign da="disabled">
					<#list listUM?if_exists as userModule>
			             		<#if userModule.TAdmModules.id?default(0)=module.id >
			             			<#assign   ckd="checked='checked'">	   
			             			<#assign da="">          			
			             		</#if>
					 </#list>
			             	<td class="bc" align="center" height=20>${No?default(0)}</td><#assign No=No+1>
			             	<td class="bc" style="color:red">	             			
			             			<input name="moduleId" type="checkbox" ${ckd?default('')} value="${module.id?default(0)}" id="${module.id?default(0)}" onclick="show('pm_${module_index?if_exists}')"/>
			             			<input name="moduleall" type="hidden" value="${module.id?default(0)}"/>
			             			&nbsp;${module.moduleName?default('')}
				            </td> 		
				            <td class="bc" colspan=2> 
		             			<#list listM?if_exists as child>
		             				<#assign childckd="">
		             					<#if child.parentid?default(0)=module.id>
								<#list listUM?if_exists as userModule>
			                    					<#if userModule.TAdmModules.id==child.id>
			                    						<#assign childckd="checked='checked'">
			                    					</#if> 
								 </#list>
						                    	<input id="${module.id?default(0)}=0" name="moduleId" ${da?default('')} type="checkbox" ${childckd?default('')} value="${child.id?default('')}" />
						                    	<input name="moduleall" type="hidden" value="${child.id?default(0)}"/>
						                    	${child.moduleName?default('')}
						                    	&nbsp;
			                    		</#if>
			                    </#list>
							</td> 
							</tr>            			
		 
	             </#if>  
		      </#list>
		    </table></td>
		  </tr>

  	<!--=================================================================================-->
  	<!--=================================================================================-->
  	<!--=================================================================================-->  
  
</table>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>	
	$("input[name='moduleId']").click(function(){	
		var p_id=$(this).attr("id");
		var f=$(this).attr("checked");
		var id=p_id+"=0";		
		var child=$("input[id="+id+"]");
		$.each(child,function(){
				$(this).attr("checked",f)
				$(this).attr("disabled",!f)	
		});
	});
	function save(){
		$("#subform").attr("action","${base}/user/useroperate!editUserModules.action");
		$("#subform").submit();
	}
</script>