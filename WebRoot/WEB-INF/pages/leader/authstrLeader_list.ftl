<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>佣金历史</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
   <#include "/WEB-INF/pages/inc/common.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/authstrLeader!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>姓名: <input  name="realname" type="text" value="${realname?default('')}" class="input input_v1" /></td> 
           <td>手机号: <input  name="phone" type="text" value="${phone?default('')}" class="input input_v1" /></td>
           <td> 
       		 <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIU_LINGXIUHUIYUANSHENHE_CHAXUN">
           <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
             </@security.authorize>
           </td> 
          </tr> 
         </tbody> 
        </table> 
       </div> 
      </form> 
     </div> 
     <!--查询条件结束->
     
     <!--显示列表开始-->
     <div class="order_tbl"> 
      <table class="table-list"> 
       <thead style="background:#dbf1fc"> 
        <tr> 
         <th class="b-l"> 序号 </th>
         <th>用户名 </th>
         <th>领秀昵称 </th>
         <th>姓名</th>
         <th>手机号</th>
         <th>联系地址</th>
         <th>申请时间</th>
         <th>状态</th>
         <th>操作</th>
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="6" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
        	 ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
         	${resc.user_name?default('')}
         </td>
         <td align="center"> 
         	${resc.nick_name?default('')}
         </td>	
         <td align="center"> 
         ${resc.real_name?default('')}
         </td>	
        <td align="center"> 
         	${resc.phone?default('')}
         </td> 
         <td align="center"> 
         	${resc.address?default('')}
         </td> 
         <td align="center"> 
         	<#if resc.create_dt?exists>${resc.create_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>
         </td>
         <td align="center"> 
         	<#if resc.audit_type?exists>
	         	<#if resc.audit_type?default(0)==0>
	         	待审核
	         	<#elseif resc.audit_type?default(0)==1>
	         	审核通过
	         	<#elseif resc.audit_type?default(0)==2>
	         	 审核不通过
         	</#if>
         </#if>
         </td>
         <td align="center"> 
          <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIU_LINGXIUHUIYUANSHENHE_CHAKAN">
         	<a href="${base}/leader/authstrLeader!view.action?id=${resc.id?default('')}">查看</a>
          </@security.authorize>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="6" class="blank"></td> 
        </tr> 
       </tbody> 
      </table>
      <br /> 
      <!--显示列表开始-->
      
      <div style="display:none;"> 
      </div>
      <!--分页开始--> 
       	<#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
      <!--分页结束--> 
     </div> 
    </div> 
   </div> 
  </div>  
 </body>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>
  $().ready(function() {
	});		
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	
	$("#form1").keydown(function(){
		if(event.keyCode==13){
			$("#buttonsub").click();
		}
	});
</script>
</html>