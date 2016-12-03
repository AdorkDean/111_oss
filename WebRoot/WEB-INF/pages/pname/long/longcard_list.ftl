<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <title>龙卡列表</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/card/longCard!getLongCard.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
		<input type="hidden" name="type" value="${type?default(0)}" id="type">
		<input type="hidden" name="longId" value="${longId?default(0)}" id="longId">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LONG_XIANGQING_DAOCHU">
           <input type="button"   id="buttonsub" value="导出excle" class="btn01" style="width:100;" onclick="fy(2)" /> 
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
         <th> 龙卡号 </th> 
         <th> 使用状态</th> 
         <th> 使用时间</th> 
         <th> 记录时间 </th> 
         <th> 用户名 </th> 
         <th> 龙卡名称</th> 
          <th> 龙卡开始时间</th> 
           <th> 龙卡结束时间</th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="9" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
           <td align="center"> 
         ${resc.card?default('')}		
         </td> 
        <td align="center"> 
        <#if resc.is_use?default(0)==1>
        	已使用
        <#else>
        	未使用
        </#if>
         </td> 
         <td align="center"> 
        <#if resc.used_time?exists> ${resc.used_time?string('yyyy-MM-dd HH:mm:ss')}</#if>
         </td> 
           <td align="center"> 
        <#if resc.create_time?exists> ${resc.create_time?string('yyyy-MM-dd HH:mm:ss')}</#if>
         </td> 
          <td align="center"> 
       ${resc.user_name?default('')}	
         </td> 
         <td align="center"> 
       ${resc.name?default('')}	
         </td> 
         <td align="center"> 
         <#if resc.start_time?exists>${resc.start_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
         <#if resc.end_time?exists>${resc.end_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="9" class="blank"></td> 
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
 function fy(type){
 if(type==2){
 $("#type").val(2);
 }else{
   $('#p_curPage').val(1);
 $("#type").val(0);
 }
   $('#form1').submit();
   $("#type").val(0);
 }
</script>
</html>