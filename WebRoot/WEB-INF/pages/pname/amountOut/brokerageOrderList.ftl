<html>
 <head> 
 	<#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>健康领秀订单佣金统计</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/member/amountOut!brokerageOrderList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
        <input type="hidden" name="type" value="${type?default(0)}" id="type">
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr style="position:relative;"> 
           <td style="width:280px;">领秀会员名称： <input  name="userName" type="text" value="${userName?default('')}" class="input input_v1" /></td> 
           <td style="width:480px;" >订单时间：
			<input  type="text" class="input input_v1"  value="${start_date?default('')}" name="start_date" id="start_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
						至
						 <input  type="text" class="input input_v1"  value="${end_date?default('')}" name="end_date" id="end_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
						</td>
           <td style="50px;"><@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TIXIANGUANLI_YONGJIN_CHAXUN"><input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /></@security.authorize> </td>
           <td style="50px;"><@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TIXIANGUANLI_YONGJIN_DAOCHU"><input type="button"   id="buttonsub" value="导出excle" class="btn05" style="width:100;top:18px;" onclick="fy(2)" /></@security.authorize></td>
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
         <th> 订单编号 </th> 
         <th> 订单会员名称</th> 
         <th> 支付方式</th> 
         <th> 支付金额</th> 
         <th> 下单时间</th> 
         <th> 订单状态 </th>
         <th> 领秀会员名称 </th> 
         <th> 订单佣金 </th>  
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="8" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
         ${resc.order_sn?default('')}		
         </td> 
         <td align="center"> 
         ${resc.ouser_name?default('')}
         </td> 
         <td align="center"> 
         ${resc.pay_method?default(0)}
         </td>  
         <td align="center"> 
         ${resc.payable_amount?default(0)}		
         </td>                 
          <td align="center"> 
          <#if resc.create_date?exists>
               ${resc.create_date?string('yyyy-MM-dd HH:mm:ss')}	
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
         ${resc.luser_name?default('')}
         </td>  
         <td align="center"> 
         ${resc.amount?default(0)}		
         </td>                    
        </tr> 
        </#list>
        <tr> 
         <td colspan="8" class="blank"></td> 
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
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
<script>	
	$().ready(function() {
		${flash_message()}
	});	
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	function deleteCategory(id){
		if(confirm("该分类可能有下级分类，将下级分类全部删除，你确定要删除该模板吗?")){
			$.ajax({
				url:'${base}/category/category!deleteCategory.action',
				data:{"id":id},
				type:'post',
				success:function(data){
					if(data==1){
					alert("数据删除成功");
						window.location.reload();
					}else{
						alert("数据删除失败");
					}
				}
			});
		};
	}
	
	   $("#form1").keydown(function(){
	if(event.keyCode==13){
	$("#buttonsub").click();
	}
});




	function openAddInfo(){
	window.open ("${base}/card/coupon!couponEdit.action", 'newwindow', "width=800px, height=600px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
	
	}
	
	function openUpInfo(id){
	window.open ("${base}/card/coupon!couponUpEdit.action?id="+id, 'newwindow', "width=800px,height=600px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
	
	}
		
	 function fy(type){
		 if(type==2){
		 	$("#type").val(2);
		 }
		  $('#form1').submit();
		  $("#type").val(0);
	 }
</script>
</html>