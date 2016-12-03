<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>红包查询</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/red/red!redList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
		<input type="hidden" name="type" value="${type?default(0)}" id="type">	
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>红包名称： <input  name="cardname" type="text" value="${cardname?default('')}" id="cardname" class="input input_v1" /></td> 
           <td>领秀名称： <input  name="realName" type="text" value="${realName?default('')}" id="realName" class="input input_v1" /></td> 
            <td  colspan="2">发送红包时间：
				<input  type="text" class="input input_v1"  value="${startDate?default('')}" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
			至
			 <input  type="text" class="input input_v1"  value="${endDate?default('')}" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
			</td>
          </tr> 
           <tr>
               <td colspan="4">
                 <input type="button"   id="buttonsub" value="查询" class="btn05" onclick="submitForm(1)" />
                 <input type="button"   id="buttonadd" value="导出excle" class="btn05" onclick="submitForm(2)" />
		       </td>
          </tr>
          <tr>
          <td colspan="4">
               	一共发放：${redCount?default(0)}次红包， ${lingquCount?default(0)}人次领取，领取红包总金额：${lingquMoney?default(0.00)}元，已使用人数： ${useCount?default(0)}人，使用红包金额：${useMoney?default(0.00)}元
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
         <th class="b-l">序号</th> 
         <th>秀粉名称</th> 
         <th>所属领秀</th> 
         <th>红包名称</th>
         <th>红包金额</th> 
         <th> 红包编码 </th> 
         <th> 领取时间</th>
         <th> 优惠券开始时间</th>
         <th> 优惠券结束时间</th>
         <th> 是否使用</th>
         <th> 使用时间 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="11" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
          ${resc_index+1?default('')}	
         </td> 
           <td align="center"> 
         ${resc.user_name?default('')}		
         </td> 
        <td align="center"> 
         ${resc.real_name?default('')}		
         </td> 
         <td align="center"> 
         ${resc.name?default('')}	
         </td> 
           <td align="center"> 
       ${resc.dis_price?default('0.00')}
         </td> 
          <td align="center"> 
       ${resc.card_no?default('')}	
         </td> 
          <td align="center"> 
         <#if resc.receive_dt?exists>${resc.receive_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>			
         </td> 
          <td align="center"> 
          <#if resc.start_time?exists>${resc.start_time?string('yyyy-MM-dd')}</#if>			
         </td> 
           <td align="center"> 
              <#if resc.end_time?exists>${resc.end_time?string('yyyy-MM-dd')}</#if>			
         </td> 
         <td align="center"> 
          <#if resc.is_use?exists>
         	<#if resc.is_use?default(0)==0>
         	否
         	<#else>
         	是
         	</#if>
         </#if>
         </td> 
         <td align="center"> 
        <#if resc.use_time?exists>${resc.use_time?string('yyyy-MM-dd HH:mm:ss')}</#if>	
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="11" class="blank"></td> 
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
function submitForm(type) {
    if(type==2){
	 	$("#type").val(2);
	}else{
	   $('#p_curPage').val(1);
	 	$("#type").val(0);
	}
   $('#form1').submit();
   $("#type").val(0);
}
$("#form1").keydown(function() {
    if (event.keyCode == 13) {
        $("#buttonsub").click();
    }
});

</script>
</html>