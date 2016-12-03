<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>领秀订单统计</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/order/order!leaderOrderList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
		<input type="hidden" name="type" value="${type?default(0)}" id="type">	
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>订单编号： <input  name="orderNo" type="text" value="${orderNo?default('')}" class="input input_v1" /></td> 
           <td>领秀名称： <input  name="memberName" type="text" value="${memberName?default('')}" class="input input_v1" /></td> 
           <td>订单来源：
               <select name="source" >
					 <option value="-1">全部</option>
					 <option <#if source?default(-1)==1> selected</#if> value="1">pc</option>
					 <option <#if source?default(-1)==2> selected</#if> value="2">wap</option>
					 <option <#if source?default(-1)==3> selected</#if> value="3">安卓</option>
					 <option <#if source?default(-1)==4> selected</#if> value="4">ios</option>
					  <option <#if source?default(-1)==5> selected</#if> value="5">手动订单</option>
				 </select>
            </td> 
            <td>订单状态：
               <select name="orderStatus" >
					 <option value="-1">全部</option>
					 <option <#if orderStatus?default(-1)==0> selected</#if> value="0">待支付</option>
					 <option <#if orderStatus?default(-1)==1> selected</#if> value="1">待发货</option>
					 <option <#if orderStatus?default(-1)==2> selected</#if> value="2">待收货</option>
					 <option <#if orderStatus?default(-1)==3> selected</#if> value="3">已完成</option>
					 <option <#if orderStatus?default(-1)==4> selected</#if> value="4">已取消 </option>
					 <option <#if orderStatus?default(-1)==5> selected</#if> value="5">已过期</option>
				 </select>
            </td> 
            </tr>
            <tr>
            <td colspan="2" >订单时间：
							<input  type="text" class="input input_v1"  value="${start_date?default('')}" name="start_date" id="start_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
						至
						 <input  type="text" class="input input_v1"  value="${end_date?default('')}" name="end_date" id="end_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
						</td>
			 <td>配送方式：
               <select name="deliveryId" >
					 <option value="-1">全部</option>
					 <#list deliveryWayList?if_exists as deliveryWay>
					    <option <#if deliveryId?default(-1)==deliveryWay.id> selected</#if> value="${deliveryWay.id}">${deliveryWay.name}</option>
					 </#list>
			   </select>
            </td>
             <td>确认状态：
               <select name="verifyStatus" >
					 <option value="-1">全部</option>
					 <option <#if verifyStatus?default(-1)==0> selected</#if> value="0">未确认</option>
					 <option <#if verifyStatus?default(-1)==1> selected</#if> value="1">已确认</option>
			   </select>
            </td>
          </tr> 
          <tr> 
           <td>收&nbsp;&nbsp;货&nbsp;&nbsp;人： <input  name="receiver" type="text" value="${receiver?default('')}" class="input input_v1" /></td> 
           <td colspan="2">收货人手机号： <input  name="phone" type="text" value="${phone?default('')}" class="input input_v1" /></td>
          </tr> 
          <tr><td colspan="4">
                          <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_LEADER_CHAXUN">	
                         <input type="button"   id="buttonsub" value="查询" class="btn05" onclick="submitForm(1)" />
                         </@security.authorize>
          				 <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_LEADER_DAOCHU">	
          				 <input type="button"   id="buttonadd" value="导出excle" class="btn05" onclick="submitForm(2)" />
          				 </@security.authorize>
          				 </td></tr>
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
         <th> 订单编号 </th> 
         <th> 订单来源</th> 
         <th> 支付方式 </th>
          <th> 应付金额</th> 
         <th> 配送方式 </th> 
           <th> 确认状态</th>
           <th> 订单状态</th>
            <th> 收货人</th>
            <th> 收货人手机号</th>
         <th> 下单时间 </th> 
         <th>推送海典 </th> 
         <th>秀粉名称 </th>
         <th>领秀名称 </th>
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="13" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
           <td align="center"> 
         ${resc.order_sn?default('')}		
         </td> 
        <td align="center"> 
         <#if resc.order_source?exists>
         	<#if resc.order_source?default(-1)==1>
         	PC
         	<#elseif resc.order_source?default(-1)==2>
         	wap
         	<#elseif resc.order_source?default(-1)==3>
         	安卓
         	<#elseif resc.order_source?default(-1)==4>
         	ios
         	<#elseif resc.order_source?default(-1)==5>
         	手动下单
         	</#if>
         </#if>
         </td> 
         <td align="center"> 
       ${resc.payName?default('')}	
         </td> 
           <td align="center"> 
       ${resc.payable_amount?default('0.00')}
         </td> 
          <td align="center"> 
       ${resc.deliName?default('')}	
         </td> 
          <td align="center"> 
         <#if resc.verifyStatus?exists>
         	<#if resc.verifyStatus?default(-1)==0>
         	未确认
         	<#elseif resc.verifyStatus?default(-1)==1>
         	已确认
         	</#if>
         </#if>
         </td> 
           <td align="center"> 
              <#if resc.order_status?exists>
         	<#if resc.order_status?default(0)==0>
         	待支付
         	<#elseif resc.order_status?default(0)==1>
         	待发货
         	<#elseif resc.order_status?default(0)==2>
         	 待收货
         	<#elseif resc.order_status?default(0)==3>
         	已完成
         	<#elseif resc.order_status?default(0)==4>
         	已取消
         	<#elseif resc.order_status?default(0)==5>
         	已过期
         	</#if>
         </#if>
         </td> 
         <td align="center"> 
             ${resc.receiver?default('')}
         </td> 
         <td align="center"> 
             ${resc.phone?default('')}
         </td> 
         <td align="center"> 
         <#if resc.create_date?exists>${resc.create_date?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
         <#if resc.is_push?exists>
         	<#if resc.is_push?default(0)==0>
         	否
         	<#else>
         	是
         	</#if>
         </#if>
         </td> 
         <td align="center"> 
       ${resc.xiufenname?default('')}	
         </td> 
         <td align="center"> 
       ${resc.lingxiuname?default('')}	
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="13" class="blank"></td> 
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