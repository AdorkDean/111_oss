<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>会员等级管理</title> 
  <#include "/WEB-INF/pages/inc/common.ftl">
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/member/grade!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>等级名称: <input  name="memberGrade.name" type="text" value="${memberGrade.name?default('')}" class="input input_v1" /></td> 
           <td> 
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUANDENGJI_LIEBIAO_CHAXUN">
           		<input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" />
           		</@security.authorize>
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUANDENGJI_LIEBIAO_TIANJIA"> 
          		<a href="${base}/member/grade!add.action" class="a-button">添加</a>
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
         <th>等级名称</th>
         <th>创建时间</th>
         <th>优惠比例</th>
         <th>消费金额</th> 
         <th>默认等级</th>
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
         	${resc.name?default('')}	
         </td>
         <td align="center"> 
         	<#if resc.createDate?exists>${resc.createDate?string('yyyy-MM-dd HH:mm:ss')}</#if>			
         </td> 
        <td align="center"> 
         	${resc.enterpriseDiscount}		
         </td> 
         <td align="center"> 
         	${resc.consumePrice}	
         </td> 
         <td align="center">       		
       		<#if resc.isDefault>
         	是
         	<#else>
         	否	
         	</#if>
         </td>	
         <td align="center">
         	<a href = "${base}/member/grade!edit.action?memberGrade.id=${resc.id}">修改</a>
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
<script>
	$().ready(function() {
		${flash_message()}
	});	
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	function edit(id){
		var remark = prompt("请输入相关备注信息");
		if(remark != null){
			$.ajax({
				url:'${base}/customer/call!edit.action',
				data:{"tCustomerCall.id":id,"tCustomerCall.remark":remark},
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