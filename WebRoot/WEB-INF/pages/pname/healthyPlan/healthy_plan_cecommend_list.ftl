<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <title>推荐分类方案</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
    <!-- <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/healthyplan/healthyplan!healthyPlanRecommendList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
            <tr>
	            <td>方案分类：
	                 <select name="classificationId" >
						 <option value="">全部</option>
						 <#list classificationList?if_exists as classification>
						    <option <#if classificationId?default(-1)==classification.id> selected</#if> value="${classification.id}">${classification.classificationName}</option>
						 </#list>
					 </select>
	            </td>
             <td >
                 <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" />
			 </td>
          </tr> 
         </tbody> 
        </table> 
       </div> 
      </form> 
     </div> -->
     <!--查询条件结束->
     
     <!--显示列表开始-->
     <div class="order_tbl"> 
      <table class="table-list"> 
       <thead style="background:#dbf1fc"> 
        <tr> 
         <th style="width:50px;"> 序号</th>
         <th style="width:100px;"> 分类 </th> 
         <th class="b-r" style="width:100px;"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="3" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
          <td align="center"> 
            ${resc_index+1?default('')}	
         </td> 
           <td align="center"> 
         ${resc.classification_name?default('')}		
         </td> 
         <td align="center"> 
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HEALTHY_PLAN_RECOMMEND_UPDATE">
            <a href="javascript:void(0);" onclick="healthyplanAdd(${resc.id?default('')})">完善方案</a>
         </@security.authorize>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="3" class="blank"></td> 
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
   $('#form1').submit();
}

$("#form1").keydown(function() {
    if (event.keyCode == 13) {
        $("#buttonsub").click();
    }
});
function healthyplanAdd(classId){
   window.location.href="${base}/healthyplan/healthyplan!healthyPlanRecommendAdd.action?classId="+classId;
}


</script>
</html>