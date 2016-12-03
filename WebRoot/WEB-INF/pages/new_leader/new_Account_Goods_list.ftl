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
    <form name="form1" id="form1" action="${base}/leader/newLeaderAccount!getGoodsList.action?id=${id}&type=2" method="post">
	  <input type="hidden" name="id" value="${id?default()}" id="id">
       <table cellpadding="0" cellspacing="0" border="0"> 
	     <tbody> 
	      <tr> 
	       <!--<td>所属领秀： <input  name="leaderName" type="text" value="${leaderName?default('')}" class="input input_v1" /></td>--> 
	       <td> <input type="button" id="buttonsub" value="导出列表" class="btn05" onclick="submitForm()" /></td> 
	       </tr>
	      </tbody> 
	    </table> 
	    </form>
     <!--查询条件结束->
     
     <!--显示列表开始-->
     <div class="order_tbl"> 
      <table class="table-list"> 
       <thead style="background:#dbf1fc"> 
        <tr> 
         <th style="width:4%"> 序号 </th> 
         <th> 海典编码</th> 
         <th style="width:22%"> 商品名称</th> 
         <th style="width:7%"> 商品数量 </th> 
         <th> 返佣比例(百分比)</th> 
         <th> 商品金额</th>
         <th> 商品总额</th>
         <th> 商品佣金</th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list accGoodsList?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
          ${resc.goodsno?default('')}	
         </td> 
         <td align="center"> 
         ${resc.goods_name?default('')}		
         </td> 
         <td align="center"> 
         ${resc.quantity?default('0')}		
         </td> 
         <!--<td align="center"><#if resc.am?exists>${(resc.am/resc.price)*100}%</#if></td> -->
         <td align="center"><#if resc.percent?exists>${resc.percent?default('0.00%')}</#if></td>
         <td align="center"> 
        <#if resc.price?exists>${resc.price?string('0.00')}</#if>	
         </td> 
         <td align="center"> 
        <#if resc.total?exists>${resc.total?string('0.00')}</#if>	
         </td> 
         <td align="center"> 
        <#if resc.am?exists> ${resc.am?string('0.00')}</#if>	
         </td> 
        </tr> 
        </#list>
        <tr>
        	<td colspan="8" align="center"><input type="button" class="btn04" onclick="closeOpen();" value="关闭">&nbsp;&nbsp;<input type="button" class="btn04" onclick="javascript:history.go(-1);" value="返回"></td> 
        </tr>
       </tbody> 
      </table>
     </div> 
    </div> 
   </div> 
  </div>  
 </body>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script>
	function closeOpen(){
		window.close();
	}
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
</script>
</html>
<style>
* {font-size:12px;}
.btn01 {font-size:12px;}
</style>