<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>待处理处方药预订单按用户</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/reserveorder/reserveorder!reserverOrderListByUser.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>用户名： <input  name="username" type="text" value="${username?default('')}" class="input input_v1" /></td> 
           <td>姓名： <input  name="realname" type="text" value="${realname?default('')}" class="input input_v1" /></td> 
           <td>电话：<input  name="mobile" type="text" value="${mobile?default('')}" class="input input_v1" />
            </td>
              <td> 
              <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_RXRESERVERORDER_CHAXUN">	
                     <input type="button"   id="buttonsub" value="查询" class="btn05" onclick="submitForm(1)" />
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
         <th>用户名</th> 
         <th>姓名</th> 
         <th>电话</th>
         <th>待处理预定单</th> 
         <th>已取消预定单</th> 
         <th>最新预定时间</th>
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="7" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc.user_name?default('')}		
         </td> 
        <td align="center"> 
         ${resc.real_name?default('')}		
         </td> 
         <td align="center"> 
       ${resc.mobile?default('')}	
         </td> 
           <td align="center"> 
            ${resc.shenhe?default('')}/${resc.noend?default('')}	
         </td> 
          <td align="center"> 
           ${resc.quxiao?default('')}	
         </td> 
          <td align="center"> 
           <#if resc.createdt?exists>${resc.createdt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_RXRESERVERORDER_CAOZUO">	
              <a href="${base}/reserveorder/reserveorder!reserverOrderByUser.action?memberId=${resc.member_id?default('')}&source=1">操作</a>
          </@security.authorize>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="7" class="blank"></td> 
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
<script>	
function submitForm() {
  $("#p_curPage").val("1");
  $("#pageSize").val("20");
  $('#form1').submit();
}

$("#form1").keydown(function() {
    if (event.keyCode == 13) {
        $("#buttonsub").click();
    }
});

</script>
</html>