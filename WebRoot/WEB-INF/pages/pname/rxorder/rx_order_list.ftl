<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>处方药订单管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/order/rxorder!getReOrderList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
		<input type="hidden" name="type" value="${type?default(0)}" id="type">	
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0"  border="0"> 
         <tbody> 
          <tr> 
           <td>订单编号： <input  name="orderNo" type="text" value="${orderNo?default('')}" class="input input_v1" /></td> 
           <td>会员名称： <input  name="uname" type="text" value="${uname?default('')}" class="input input_v1" /></td> 
           <td>收货人： <input  name="receiver" type="text" value="${receiver?default('')}" class="input input_v1" /></td> 
           <td style="position:relative;">收货人手机号： <input style="position:absolute; right:-15px; top:0;"  name="mobile" type="text" value="${mobile?default('')}" class="input input_v1" /></td>
          </tr>
           <tr style="height:30px; line-height:30px;">
            <td>订单状态：
               <select name="status" >
					 <option value="-1">全部</option>
					 <option <#if status?default(-1)==0> selected</#if> value="0">已下单</option>
					 <option <#if status?default(-1)==1> selected</#if> value="1">待收货</option>
					 <option <#if status?default(-1)==2> selected</#if> value="2">已取消</option>
					 <option <#if status?default(-1)==3> selected</#if> value="3">拒收</option>
					 <option <#if status?default(-1)==4> selected</#if> value="4">已完成</option>
				 </select>
            </td> 
            <td colspan="3" >订单时间：
							<input  type="text" readonly="readonly" class="input input_v1"  value="${startDate?default('')}" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')}'})"  >
						至
						 <input  type="text" readonly="readonly" class="input input_v1"  value="${endDate?default('')}" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'%y-%M-%d'})"  >
						</td>
            </tr>
            <tr><td colspan="4">
                         <input type="button"   id="buttonsub" value="查询" class="btn05" onclick="submitForm()" />
          				 <input type="button"   id="buttonadd" value="推送海典" class="btn05" onclick="pushHaidian()" />
          				 <input type="button" id="buttonadd" value="导出Excel" class="btn05" onclick="submitForm(2)">
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
         <th style="width:4%"></th> 
         <th> 订单编号 </th> 
         <th> 配送方式 </th> 
         <th> 应付金额</th> 
         <th> 会员名称</th>
         <th> 订单状态</th>
         <th> 收货人/手机号</th>
         <th> 下单时间 </th> 
         <th>推送海典 </th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="10" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content">
          <td align="center"> 
             <input type="checkbox" name="orderidCheckbox" value="${resc.orderid?default('')}"/>
         </td>
         <td align="center"> 
         	${resc.order_sn?default('')}		
         </td> 
         
         <td align="center"> 
         	${resc.delivery?default('')}		
         </td> 
         
         <td align="center"> 
       		${resc.order_amount?default('0.00')}
         </td> 
         
         <td align="center"> 
       		${resc.user_name?default('')}
         </td> 
         
         <td align="center"> 
            <#if resc.order_status?exists>
	         	<#if resc.order_status?default(0)==0>
	         		已下单
	         	<#elseif resc.order_status?default(0)==1>
	         		待收货
	         	<#elseif resc.order_status?default(0)==2>
	         		已取消
	         	<#elseif resc.order_status?default(0)==3>
	         		拒收
	         	<#elseif resc.order_status?default(0)==4>
	         		已完成
         	</#if>
         </#if>
         </td> 
         <td align="center"> 
             ${resc.receiver?default('')}<br/>
             ${resc.mobile?default('')}
         </td> 
         <td align="center"> 
         <#if resc.create_dt?exists>${resc.create_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
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
         <a target="_blank" href="${base}/order/rxorder!getOrderDetail.action?orderid=${resc.orderid?default(0)}">查看</a>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="10" class="blank"></td> 
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
$("#form1").keydown(function() {
    if (event.keyCode == 13) {
        $("#buttonsub").click();
    }
});

function orderDetail(id) {
     window.location.href = "${base}/order/rxorder!getOrderDetail.action?orderid=" + id;
}

//推送海典
function pushHaidian(){  
  var chk_value =[];    
  $('input[name="orderidCheckbox"]:checked').each(function(){    
    chk_value.push($(this).val());    
  });
  if(chk_value.length==0){
    $alert("warn","请选择推送海典的订单！");
  }else if(chk_value.length>1){
    $alert("warn","只能选择一个订单！");
  }else{
     var orderId = chk_value;
	$.ajax({
		url:"${base}/order/rxorder!pushOrder.action?orderid="+orderId,
		type:"post",
		success:function(data){
			if(data=='1'){ 
				 $alert("success","推送成功！");
				 submitForm();
			}else if(data=='2'){
				$alert("error","推送失败，订单状态异常！");
			}else if(data=='0'){
				$alert("error","推送失败，订单不存在！");
			}else if(data=='3'){
				$alert("error","推送失败，该订单已经发货！");
			}else if(data=='4'){
				$alert("error","推送失败，该订单已经取消！");
			}else if(data=='5'){
				$alert("error","推送失败，该订单已经拒收！");
			}else if(data=='6'){
				$alert("error","推送失败，该订单已经完成！");
			}else{
			  $alert("error","推送失败！");
			}
		},
		error:function(){
		      $alert("error","系统异常，请稍后再试！");
		}
	});
     
  
  }
} 

</script>
</html>