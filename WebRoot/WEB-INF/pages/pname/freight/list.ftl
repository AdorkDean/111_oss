<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>运费列表</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/goodsfreight/goodsfreight!freightList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
       <div style="height:20px; position:relative;" class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
              <td> 
              <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAXUN">	
                     <input style="position:absolute; left:-15px; top:-17px;" type="button"   id="buttonsub" value="添加" class="btn05" onclick="addfreight()" />
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
         <th>ID</th> 
         <th>运费</th> 
         <th>备注</th> 
         <th>创建时间</th>
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="5" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content">
         <td align="center"> 
            ${resc.id?default('')}
         </td>  
         <td align="center"> 
            ${resc.freight?default('')}
         </td> 
         <td align="center"> 
            ${resc.remark?default('')}
         </td> 
          <td align="center"> 
           <#if resc.create_dt?exists>${resc.create_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN">	
              <a href="javascript:void(0);" onclick="upfreight(${resc.id?default('')})">修改</a>
          </@security.authorize>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="5" class="blank"></td> 
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
function addfreight(){
  window.location.href="${base}/goodsfreight/goodsfreight!freight.action";
}	
function submitForm() {
  $('#form1').submit();
}

$("#form1").keydown(function() {
    if (event.keyCode == 13) {
        $("#buttonsub").click();
    }
});

function upfreight(id){
  window.location.href="${base}/goodsfreight/goodsfreight!upFreight.action?id="+id;
}
</script>
</html>