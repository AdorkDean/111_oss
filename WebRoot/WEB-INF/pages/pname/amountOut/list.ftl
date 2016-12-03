<html>
 <head> 
 	<#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>提现管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/member/amountOut!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr style="position:relative;"> 
           <td>名称： <input  name="userName" type="text" value="${userName?default('')}" class="input input_v1" /></td> 
           <td><input style="position:absolute; left:230px; top:8px;" type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /></td>
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
         <th> 会员名称 </th> 
         <th> 提现类型</th> 
         <th> 提现金额</th> 
         <th> 申请时间 </th> 
         <th> 申请人姓名</th> 
         <th> 提现状态 </th> 
         <th class="b-r"> 操作 </th> 
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
         ${resc.user_name?default('')}		
         </td> 
         <td align="center"> 
         <#if resc.out_type?exists><#if resc.out_type?default(0)==1>支付宝<#elseif resc.out_type?default(0)==2>银行卡</#if></#if>	
         </td> 
         <td align="center"> 
         ${resc.amount?default(0)}
         </td>  
         <td align="center"> 
         <#if resc.out_time?exists>${resc.out_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td>                 
          <td align="center"> 
         ${resc.real_name?default('')}		
         </td>         
         <td align="center"> 
         <#if resc.status?exists>
         	<#if resc.status?default(0)==1>
         	提交提现
         	<#elseif resc.status?default(0)==2>
         	提现中
         	<#elseif resc.status?default(0)==3>
         	提现完成
         	<#elseif resc.status?default(0)==4>
         	审核失败
         	</#if>
         </#if>
         </td> 
         <td align="center"> 
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TIXIAN_XIANGQING">	
         	<a href="${base}/member/amountOut!details.action?id=${resc.id?default(0)}">详情</a>
         </@security.authorize>
          <#if resc.status?exists>
         	<#if resc.status?default(0)==1>
         	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TIXIAN_TIXIANSHENHE">	
         	<a href="${base}/member/amountOut!auditEdit.action?id=${resc.id?default(0)}">提现审核</a>
         	</@security.authorize>
         	<#elseif resc.status?default(0)==2>
         	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TIXIAN_TIXIANWANCHENG">
         	<a href="${base}/member/amountOut!outFinish.action?id=${resc.id?default(0)}">提现完成</a>
         	</@security.authorize>
         	</#if>
         </#if>
         
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
</script>
</html>