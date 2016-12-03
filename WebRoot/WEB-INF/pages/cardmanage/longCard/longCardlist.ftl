<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>龙卡管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/card/longCard!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>名称： <input  name="longName" type="text" value="${longName?default('')}" class="input input_v1" /></td> 
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" style="position:absolute; left:240px; top:8px;"/> 
          		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LONG_LIST_ADD">
          		<input type="button" class="btn01" value="添加" onclick="add();" style="position:absolute; left:310px; top:8px;"/>、
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
         <th> 名称 </th> 
         <th> 金额</th> 
         <th> 创建时间</th> 
         <th> 开始时间 </th> 
         <th> 结束时间 </th> 
         <th> 是否启动 </th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="8" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
         ${resc.name?default('')}		
         </td> 
         <td align="center"> 
         ${resc.amount?default('')}	
         </td> 
         <td align="center"> 
         <#if resc.create_time?exists>${resc.create_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td>  
         <td align="center"> 
         <#if resc.start_time?exists>${resc.start_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td>                 
          <td align="center"> 
         <#if resc.end_time?exists>${resc.end_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td>         
         <td align="center"> 
         <#if resc.status?exists>
         	<#if resc.status?default(0)==0>
         	未启动
         	<#elseif resc.status?default(0)==1>
         	启动
         	</#if>
         </#if>
         </td> 
         <td align="center">
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LONG_LIST_BIANJI"> 
         <a href="${base}/card/longCard!longUpEdit.action?id=${resc.id?default(0)}" >编辑</a>&nbsp;&nbsp;|&nbsp;
         </@security.authorize>
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LONG_LIST_SHENGCHENG">
         <a href="${base}/card/longCard!createCardEdit.action?id=${resc.id?default(0)}">生成龙卡</a>&nbsp;&nbsp;|&nbsp;
         </@security.authorize>
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LONG_LIST_XIANGQING">
         <a href="${base}/card/longCard!getLongCard.action?longId=${resc.id?default(0)}">龙卡详情</a>
         </@security.authorize>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="8" class="blank"></td> 
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
		${flash_message()}
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


function add(){
 window.location.href="${base}/card/longCard!longCardEdit.action";
}

</script>
</html>