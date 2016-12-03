<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>积分管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/integral/integral!show.action?id=${tm.id?default(0)}" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
	         	积分明细
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
         <th>用户名</th>
         <th>积分</th>
         <th>积分明细</th>
         <th>积分来源</th>
         <th>创建日期 </th>
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
		       	  ${resc.integral?default('')}
		         </td>
		         <td align="center"> 
		          ${resc.remark?default('')}
		         </td>
		         <td align="center"> 
			      <#if resc.source?exists && resc.source == 1>
			                     注册
			        <#elseif resc.source?exists && resc.source == 2>
			      	 完善用户名
			      	<#elseif resc.source?exists && resc.source == 3>
			      	完善真实姓名
			      	<#elseif resc.source?exists && resc.source == 4>
			      	完善性别
			      	<#elseif resc.source?exists && resc.source == 5>
			      	完善血型
			      	<#elseif resc.source?exists && resc.source == 6>
			      	完善会员病历(常用药、病史、过敏史)
			      	<#elseif resc.source?exists && resc.source == 7>
			      	 评论
			      	<#elseif resc.source?exists && resc.source == 8>
			      	 签到
			      	<#elseif resc.source?exists && resc.source == 9>
			      	 订单完成 
			      	<#else>
			      	 兑换优惠券
			      </#if>
		         </td>
		         <td align="center"> 
		         <#if resc.create_date?exists>${resc.create_date?string('yyyy-mm-dd HH:mm:ss')}</#if>
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