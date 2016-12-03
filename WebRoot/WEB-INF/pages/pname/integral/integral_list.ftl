<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>积分管理</title> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/integral/integral!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>用户名: <input  name="tm.userName" type="text" value="${tm.userName?default('')}" class="input input_v1" /></td> 
           <td>手机号: <input  name="tm.mobile" type="text" value="${tm.mobile?default('')}" class="input input_v1" /></td> 
           <td> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIFENGUANLI_JIFENCHAXUN">
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
         <th>会员名称 </th>
         <th>当前积分</th>
         <th>累计积分</th>
         <th>手机号码 </th>
         <th>邮箱 </th>
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
         	${resc.userName?default('')}
         </td>	
        <td align="center"> 
         	${resc.integral?default('')}
         </td> 
         <td align="center"> 
         	${resc.accumulateIntegral?default('')}
         </td>
          <td align="center"> 
         	${resc.mobile?default('')}
         </td>	
         <td align="center"> 
         	${resc.email?default('')}
         </td>	
        <td class="t-c">
        <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIFENGUANLI_JIFENCHAKAN">
         <a href="${base}/integral/integral!show.action?id=${resc.id?default('')}" >查看</a>
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
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	function edit(id){
		if(confirm("确定标记为已回拨?")){
			//window.location='${base}/customer/call!edit.action?id='+id;
			$.ajax({
				url:'${base}/customer/call!edit.action',
				data:{"tCustomerCall.id":id},
				type:'post',
				success:function(data){
					if(data==1){
						window.location.reload();
					}else if(data<1){
						alert("已标记成已回拨状态");
					}else{
						alert("系统异常，请稍后再试");
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
</script>
</html>