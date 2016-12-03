<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>秀粉列表</title> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/showfen!showlist.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
		<input type="hidden" name="type" value="${type?default(1)}" id="type">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>领秀姓名: <input style=" width:80px;"  name="realname" type="text" value="${realname?default('')}" class="input input_v1" /></td> 
           <td>秀粉手机号: <input style=" width:90px;"  name="phone" type="text" value="${phone?default('')}" class="input input_v1" /></td>
            <td  colspan="2">注册时间：
				<input style=" width:80px;"  type="text" class="input input_v1"  value="${startDate?default('')}" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
			至
			 <input style=" width:80px;"  type="text" class="input input_v1"  value="${endDate?default('')}" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
			</td>
           <td> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIU_XIUFENLIEBIAO_CHAXUN">
           <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm(1)" /> 
           </@security.authorize>
           </td> 
           <td>
            <input type="button" class="btn01" onclick="submitForm(2)" value="导出" style="margin-left: -70;" />
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
         <th>秀粉手机号</th>
         <th>注册时间</th>
         <th>秀粉消费金额</th>
         <th>领秀姓名 </th>
         <th>领秀手机号 </th>
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
       	  <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIU_XIUFENLIEBIAO_CHAKANXIANGQING">
         	<a href="${base}/order/order!getOrderList.action?memberName=${resc.user_name?default('')}">${resc.user_name?default('')}</a>
          </@security.authorize>
         </td>
        <td align="center"> 
         	${resc.mobile?default('')}
         </td> 
         <td align="center"> 
         	<#if resc.register_date?exists>${resc.register_date?string('yyyy-MM-dd HH:mm:ss')}</#if>
         </td>
         <td align="center"> 
         	${resc.totlprice?default(0)}
         </td> 
         <td align="center"> 
         	${resc.sfname?default('')}
         </td> 
          <td align="center"> 
         	${resc.sfphone?default('')}
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
	function submitForm(type) {
		    if(type==2){
		 		$("#type").val(2);
		 	}else{
			    $('#p_curPage').val(1);
			    $("#type").val(1);
		 	}
		    $('#form1').submit();
		    $("#type").val(1);
		}
	
	$("#form1").keydown(function(){
		if(event.keyCode==13){
			$("#buttonsub").click();
		}
	});
</script>
</html>