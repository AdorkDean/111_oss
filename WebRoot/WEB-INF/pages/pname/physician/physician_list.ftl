<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>药师管理</title> 
   <#include "/WEB-INF/pages/inc/taglibs.ftl">
   <#include "/WEB-INF/pages/inc/common.ftl">

 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/physician/physician!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>药师名称: <input  name="physician.realName" type="text" value="${physician.realName?default('')}" class="input input_v1" /></td> 
           <td> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YAOSHIGUANLI_YAOSHICHAXUN">
           <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
           </@security.authorize>
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YAOSHIGUANLI_YAOSHITIANJIA">
           <a href="${base}/physician/physician!add.action" class="a-button">添加</a>
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
         <th>药师名称 </th>
         <th>药师头像</th>
         <th>从业年限</th>
         <th>备注</th>
         <th class="b-r"> 操作 </th> 
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
         	${resc.realName?default('')}
         </td>	
         <td align="center"> 
         	<img src='${ui1}${resc.headUrl?default('')}' width="50px" height ="50px"/>
         </td>	
        <td align="center"> 
         	${resc.workYear?default('')}
         </td> 
         <td align="center"> 
         	${resc.remark?default('')}
         </td>
        <td class="t-c">
        <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YAOSHIGUANLI_YAOSHIXIUGAI">
         <a href="${base}/physician/physician!edit.action?id=${resc.id?default('')}" >修改</a>
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