<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>健康领秀提现管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/newLeaderAccount!getAccountList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>领秀姓名： <input  name="leaderName" type="text" value="${leaderName?default('')}" class="input input_v1" /></td> 
           <td>领秀手机： <input  name="leaderMobile" type="text" value="${leaderMobile?default('')}" maxlength="14" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d]/g,'')" class="input input_v1" /></td> 
           <td>提现流水号： <input name="serialNumber" type="text" value="${serialNumber?default('')}" class="input input_v1" /></td> 
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /></td>
           <input type="hidden" name="status" value="${status?default(-1)}" id="status"/>
          </tr>
          <tr>
	          <td><input href="javascript:void(0)" readonly unselectable="on" value="全部记录<#if status?default(-1)==-1>(${pw.pageInfo.count})</#if>" onclick="setStatus(-1)" class="btn05" style="text-align:center;"/></td>
	          <td><input href="javascript:void(0)" readonly unselectable="on" value="待审核<#if status?default(-1)==1>(${pw.pageInfo.count})</#if>" onclick="setStatus(1)" class="btn05" style="text-align:center;margin-left:-50px;"/></td>
	          <td><input href="javascript:void(0)" readonly unselectable="on" value="提现中<#if status?default(-1)==2>(${pw.pageInfo.count})</#if>" onclick="setStatus(2)" class="btn05" style="text-align:center;margin-left:-120px;"/></td>
	          <td><input href="javascript:void(0)" readonly unselectable="on" value="提现完成<#if status?default(-1)==3>(${pw.pageInfo.count})</#if>" onclick="setStatus(3)" class="btn05" style="text-align:center;margin-left:-190px;"/></td>
	          <td><input href="javascript:void(0)" readonly unselectable="on" value="审核失败<#if status?default(-1)==4>(${pw.pageInfo.count})</#if>" onclick="setStatus(4)" class="btn05" style="text-align:center;margin-left:-260px;"/></td>
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
         <th class="b-l" style="width:3%"> 序号 </th> 
         <th style="width:8%"> 提现流水号</th> 
        <!-- <th style="width:10%"> 领秀编号</th> -->
         <th style="width:8%"> 领秀姓名 </th> 
         <th style="width:8%"> 领秀手机</th> 
         <th style="width:5%"> 提现金额</th> 
         <th style="width:5%"> 提现方式</th> 
         <th style="width:25%"> 账号</th> 
         <th style="width:5%"> 提现状态</th> 
         <th style="width:5%"> 明细</th> 
         <th style="width:8%"> 操作</th> 
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
         ${resc.real_name?default('')}	
         </td> 
         <td align="center"> 
         ${resc.phone?default('')}	
         </td> 
         <td align="center"> 
         <#if resc.amount?exists>${resc.amount?string('0.00')}	</#if>
         </td> 
         <td align="center"> 
        <#if resc.out_type?default(1)==1>
        	支付宝
        <#else>
        	银行卡
        </#if>
         </td> 
         <td align="left"> 
        <#if resc.out_type?default(1)==1>
        	支付宝账号：${resc.alipay_no?default('')}<br>
        	收款人：${resc.real_name?default('')}
        <#else>
        	银行名称：${resc.bank_name?default('')}<br>
        	银行账号：${resc.bank_no?default('')}<br>
        	收款人：${resc.rname?default('')}
        </#if>
         </td> 
         <td align="center"> 
        <#if resc.status?default(1)==2>
        	提现中
        <#elseif resc.status?default(1)==3>
        	提现完成
        <#elseif resc.status?default(1)==4>
        	审核失败
        <#else>
        	待审核
        </#if>
         </td> 
         <td align="center"> 
         <a href="javascript:void(0);" onclick="openOrderList(${resc.leaderId?default(0)});" >查看</a>
         </td>          
         <td align="center"> 
         <#if resc.status?default(1)==1>
         	 <a href="javascript:void(0);" onclick="checkAccount(${resc.id?default(0)},${resc.phone?default(0)})">审核通过</a><br/>
        	 <a href="javascript:void(0);" onclick="openNoCheck(${resc.id?default(0)},${resc.phone?default(0)})">驳回</a><br/>
         	</#if>
         	<#if resc.status?default(1)==2>
         	<a href="javascript:void(0);" onclick="overAccount(${resc.id?default(0)})">提现完成</a><br/>
         	</#if>
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
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script>	
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	
	function checkAccount(id,phone){
	if(confirm("确定审核通过该提现申请？")){
		$.ajax({
		url:"${base}/leader/newLeaderAccount!checkAccount.action",
			type:"post",
			data:{"id":id,"phone":phone},
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
	function overAccount(id){
	if(confirm("确定已汇款至领秀账号？")){
		$.ajax({
		url:"${base}/leader/newLeaderAccount!overAccount.action",
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
	
	
function openOrderList(leaderId){
window.open ("${base}/leader/newLeaderAccount!getAccountOrderList.action?leaderId="+leaderId+"&orderStatus=3", 'newwindow', "width=1000px,top=100, left=200,height=500px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");

}	
function openNoCheck(id,phone){
window.open ("${base}/leader/newLeaderAccount!goNoCheckAccount.action?id="+id+"&phone="+phone, 'newwindow', "width=375px,top=100, left=500,height=300px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");

}	


function setStatus(status){
$("#status").val(status);
submitForm();
}
</script>
</html>
<style>
* {font-size:12px;}
.btn01 {font-size:12px;}
</style>