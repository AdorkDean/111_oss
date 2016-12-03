<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>客戶回拨管理</title> 
  <#include "/WEB-INF/pages/inc/common.ftl">
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/customer/call!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td style="width:240px;">手机号码: <input  name="tCustomerCall.phoneNumber" type="text" value="${tCustomerCall.phoneNumber?default('')}" class="input input_v1" /></td> 
           <td> 
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_KEHUHUIBO_LIEBIAO_CHAXUN">	
           		<input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" />
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
         <th>手机号码</th>
         <th>相关商品</th>
         <th>创建时间 </th> 
         <th>回拨状态</th>
         <th>回拨备注</th>
         <th>回拨时间 </th> 
         <th>操作人</th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td class="blank"></td></tr>
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
         ${resc.phone_number?default('')}
         </td> 
         <td align="center"> 
         <a href="http://www.111yao.com/p/${resc.goods_id?default('')}.html" target="_blank">${resc.goods_name?default('')}</a>		
         </td>
        <td align="center"> 
         <#if resc.create_time?exists>${resc.create_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
         	<#if resc.call_status?default(0)==0>
         	否
         	<#elseif resc.call_status?default(0)==1>
         	是	
         	</#if>
         </td> 
         <td align="center" id="${resc.id?default('')}_remark">       		
       		${resc.remark?default('')}
         </td>	
         <td align="center"> 
       	<#if resc.call_time?exists>${resc.call_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td>
        
         <td align="center">
         	${resc.operator?default('')} 
         </td>	
         <td align="center">
         	<#if resc.call_status?default(0)==1>已处理</#if>
         	<#if resc.call_status?default(0)==0><a href="#" class="remark" val="${resc.id?default('')}">标记</a></#if>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td class="blank"></td> 
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

<script type="text/javascript">		
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	
	$(".remark").click(function (){
		var obj = $(this);
		var remark = prompt("请输入相关备注信息");
		if(remark != null && remark != ""){
			$.ajax({
				url:'${base}/customer/call!edit.action',
				data:{"tCustomerCall.id":obj.attr("val"),"tCustomerCall.remark":remark},
				type:'post',
				success:function(data){
					if(data==1){
						$alert("success","已标记成已回拨状态");
						obj.parent().html("已标记");
						obj.remove();
						$("#"+obj.attr("val")+"_remark").html(remark);
						
					}else if(data<1){
						$alert("success","状态标记失败");
					}else{
						alert("系统异常，请稍后再试");
					}
				}
			});
		};
	});
	
	
	
	$("#form1").keydown(function(){
		if(event.keyCode==13){
			$("#buttonsub").click();
		}
	});
</script>
</html>