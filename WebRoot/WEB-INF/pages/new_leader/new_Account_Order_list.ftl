<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>健康领秀返佣管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/newLeaderAccount!getAccountOrderList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
		<input type="hidden" name="leaderId" type="text" value="${leaderId?default('')}" />
				<input type="hidden" name="orderStatus" type="text" value="${orderStatus?default('')}" />
		<input type="hidden" name="type" value="${type?default(0)}" id="type">	
       <div class="tbl_form"> 
        <table border="0"> 
         <tbody> 
          <tr> 
           <td>收益流水号： <input  name="serialNumber" type="text" value="${serialNumber?default('')}" class="input input_v1" /></td> 
           <td>订单编号： <input  name="orderNo" type="text" value="${orderNo?default('')}" class="input input_v1" /></td>
           <td> <input type="button" value="查询" class="btn01" onclick="submitForm()" style="margin-left:-40px;"/>
           		<input type="button" value="导出列表" class="btn05" onclick="submitForm(2)"/>
           </td>
           </tr>
           <#if flag>
	           <#list tm?if_exists as tm>
		          <tr>
		           <td>待入账金额：<b><#if tm.waitmoney?exists>${tm.waitmoney?string.currency}<#else>￥0.00</#if></b></td>
		           <td>可提现金额：<b><#if tm.freemoney?exists>${tm.freemoney?string.currency}<#else>￥0.00</#if></b></td>
		           <td>已提现金额：<b><#if tm.total?exists>${tm.total?string.currency}<#else>￥0.00</#if></b></td>
		          </tr> 
	          </#list>
          </#if>
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
         <th class="b-l" style="width:4%"> 序号 </th> 
         <th style="width:12%"> 收益流水号</th> 
         <th style="width:13%"> 订单编号</th> 
         <th> 支付时间 </th> 
         <th> 订单状态</th> 
         <th> 订单金额</th> 
         <th> 返佣时间</th> 
         <th> 返佣金额</th> 
         <th> 用户昵称</th> 
         <th> 领秀姓名</th> 
         <th> 操作</th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
         ${resc.serial_number?default('')}		
         </td> 
         <td align="center"> 
         ${resc.order_sn?default('')}		
         </td> 
         <td align="center">
         <#if resc.pay_date?exists>
         ${resc.pay_date?string('yyyy-MM-dd HH:mm:ss')}	
         </#if> 	
         </td> 
         <td align="center"> 
         <#if resc.order_status?default(0)==1>
         	待发货
         <#elseif resc.order_status?default(0)==2>
         	待收货
         <#elseif resc.order_status?default(0)==3>
         	已完成
         <#elseif resc.order_status?default(0)==4>
         	已取消
         <#elseif resc.order_status?default(0)==5>
         	已过期
         <#else>
         	待支付
         </#if>	
         </td> 
         <td align="center"> 
        <#if resc.order_amount?exists> ${resc.order_amount?string('0.00')}</#if>	
         </td> 
         <td align="center"> 
         <#if resc.create_dt?exists>
         ${resc.create_dt?string('yyyy-MM-dd HH:mm:ss')}	
         </#if>
         </td> 
         <td align="center"> 
         <#if resc.amount?exists>${resc.amount?string('0.00')}</#if>	
         </td> 
          <td align="center"> 
         ${resc.nick_name?default('')}		
         </td>
          <td align="center"> 
         ${resc.real_name?default('')}		
         </td>
         <td align="center"> 
         <a href="javascript:void(0);" onclick="openGoodsList(${resc.id?default(0)})">查看</a><br/>
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
      <!--分页开始--> 
       	<#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
      <!--分页结束--> 
     </div> 
    </div> 
   </div> 
  </div>  
 </body>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script>	
	function submitForm(type){
		if(type==2){
			 $("#type").val(2);
		 }else{
		     $('#p_curPage').val(1);
		     $("#type").val(0);
		 }
	   $('#form1').submit();
	   $("#type").val(0);
	}
	
	function checkOrder(id){
	if(confirm("确定审核通过返佣记录？")){
		$.ajax({
		url:"${base}/leader/newLeaderAccount!checkOrder.action",
			type:"post",
			data:{"id":id},
			success:function(data){
			if(data>0){
                   $alert('success','操作成功');
                   submitForm(); 
			}else{
                   $alert('warn','操作失败');
			
			}
                    }
 	 });
 	 }
	}	
function openGoodsList(id){
window.open ("${base}/leader/newLeaderAccount!getGoodsList.action?id="+id, 'newwindow', "width=860px,top=200, left=250,height=400px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
}	
</script>
</html>
<style>
* {font-size:12px;}
.btn01 {font-size:12px;}
</style>