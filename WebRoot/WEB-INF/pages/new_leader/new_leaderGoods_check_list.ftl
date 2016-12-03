<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
   <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>健康领秀佣金商品审核列表</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/leaderGoodsCheck!getLeaderGoodsCheckList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>名称： <input  name="goodsName" type="text" value="${goodsName?default('')}" class="input input_v1" /></td> 
           <td>海典编码： <input  name="goodsno" type="text" value="${goodsno?default('')}" class="input input_v1" /></td> 
           <td colspan="2" >申请时间：<input class="input input_v1"  type="text"  value="${startDate?default('')}" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"   >
                                                                            至
           			<input class="input input_v1"  type="text" value="${endDate?default('')}" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"   >
           </td> 
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
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
         <th> 商品名称 </th> 
         <th> 海典编码 </th> 
         <th> 商品价格(wap)</th> 
         <th> 返佣金额</th> 
         <th> 返佣比例</th> 
         <th> 审核状态</th> 
         <th> 申请时间</th> 
         <th> 申请人</th> 
         <th> 备注</th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list pw.result?if_exists as resc>
      <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
         ${resc.short_name?default('')}		
         </td> 
         <td align="center"> 
         ${resc.goodsno?default('')}		
         </td> 
         <td align="center"> 
         ${resc.wap_price?default('')}	
         </td> 
         <td align="center"> 
         ${resc.rebate_amount?default('')}	
         </td>          
         <td align="center"> 
          <#if resc.wap_price?default()!=0.00>
         <#assign tem=resc.rebate_amount?default('')/resc.wap_price?default('')*100>
         ${tem?string('0.0')}%	
          <#else>
 			0%        
         </#if>
         </td>          
         <td align="center">
         <#if resc.audit_status?default(0)==0>
         	未提交审核
         <#elseif resc.audit_status?default(0)==1>
          	审核中
         <#elseif resc.audit_status?default(0)==2>
          	审核通过
         <#elseif resc.audit_status?default(0)==3>
          	审核不通过
         </#if> 
         </td> 
         <td align="center"> 
         <#if resc.apply_dt?exists>
         ${resc.apply_dt?string('YYYY-MM-dd HH:mm:ss')}	
         </#if>
         </td>         
         <td align="center"> 
         ${resc.apply_user?default('')}	
         </td>         
         <td align="center">
         <#if resc.audit_remark?exists&&resc.audit_remark?default('')!=''>
         ${resc.audit_remark?default('')}	
         <#else>
			暂无备注         
         </#if>
         </td>         
         <td align="center"> 
        <a href="javascript:void(0)" onclick="checkGoods(${resc.id?default()});" >审核通过</a><br>
        <a href="javascript:void(0)" onclick="openNoCheck(${resc.id?default()});" >驳回</a>
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
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script>	
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	
	
	function checkGoods(id){
	$.ajax({
 		url:"${base}/leader/leaderGoodsCheck!checkGoods.action",
 		type:"post",
 		data:{"id":id},
 		success:function(data){
 		if(data>0){
 		$alert('success','审核成功')
 		window.location.reload();
 		}else{
 		$alert('error','审核成功')
 		
 		}
 		}
 	});
	}
	
	
	function openNoCheck(id){
window.open ("${base}/leader/leaderGoodsCheck!goNoCheckGoods.action?id="+id, 'newwindow', "width=375px,top=100, left=500,height=300px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");

}	
</script>
</html>
<style>
* {font-size:12px;}
.btn01 {font-size:12px;}
</style>