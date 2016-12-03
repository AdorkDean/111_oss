<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>选择用户</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/order/order!orderMemberList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0" style="width:800px"> 
         <tbody> 
          <tr> 
           <td>用户名： <input  name="username" type="text" value="${username?default('')}" class="input input_v1" /></td> 
           <td>手机号：<input  name="mobile" type="text" value="${mobile?default('')}" class="input input_v1" />
            </td> 
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
           <input type="button"   id="buttonsub" value="确定" class="btn01" onclick="memberXz()" /> 
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
      <table class="table-list" style="width:800px"> 
       <thead style="background:#dbf1fc"> 
        <tr> 
         <td align="center" style="width:50px"></td> 
         <td align="center">用户名</td> 
         <td align="center">真实姓名 </td> 
         <td align="center">手机</td> 
         <td align="center">邮箱 </td> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="5" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
            <input type="radio" name="identity"  value="${resc.id?default('')},${resc.user_name?default('')},${resc.real_name?default('')},${resc.mobile?default('')}"/>
         </td> 
        <td align="center"> 
         ${resc.user_name?default('')}	
         </td> 
         <td align="center"> 
        ${resc.real_name?default('')}	
         </td> 
         <td align="center"> 
       ${resc.mobile?default('')}	
         </td> 
         <td align="center"> 
        ${resc.email?default('')}		
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="5" class="blank"></td> 
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
    function memberXz(){
       var radiovalue =$("input[type='radio']:checked").val();
       if(radiovalue){
          window.opener.document.getElementById("memberid").value=radiovalue.split(",")[0]; 
          window.opener.document.getElementById("username").value=radiovalue.split(",")[1]; 
          window.opener.document.getElementById("realName").value=radiovalue.split(",")[2]; 
          window.opener.document.getElementById("mobile").value=radiovalue.split(",")[3]; 
          window.opener.memberReceiver(radiovalue.split(",")[0]);
          window.close();
       }
    }
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