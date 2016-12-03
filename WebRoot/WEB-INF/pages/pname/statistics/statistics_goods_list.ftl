<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
    <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>商品点击统计</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/statistics!statisticGoodsList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>海典编号： <input  name="goodsno" type="text" value="${goodsno?default('')}" class="input input_v1" /></td> 
               <td colspan="2" >统计时间：
							<input  type="text" class="input input_v1"  value="${start_date?default('')}" name="start_date" id="start_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  >
						至
						 <input  type="text" class="input input_v1"  value="${end_date?default('')}" name="end_date" id="end_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  >
						</td>
           <td>
           		<input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
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
         <th> 海典编号 </th>
         <th> 商品名称</th>
         <th> 商品价格</th>
         <th> 商品点击统计 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="5" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
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
         PC价格:${resc.pc_price?default('')}<br/>
         WAP价格:	${resc.wap_price?default('')}<br/>
         APP价格:	${resc.app_price?default('')}
         </td> 
        <td align="center"> 
         ${resc.goodsnum?default('')}	
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
	
	   $("#form1").keydown(function(){
	if(event.keyCode==13){
	$("#buttonsub").click();
	}
});
</script>
</html>