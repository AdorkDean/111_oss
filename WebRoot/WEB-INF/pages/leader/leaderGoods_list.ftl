<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>健康领秀佣金商品管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/leaderGoods!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>名称： <input  name="name" type="text" value="${name?default('')}" class="input input_v1" /></td> 
           <td>海典编码： <input  name="goodsno" type="text" value="${goodsno?default('')}" class="input input_v1" /></td> 
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LX_SPYONGJIN_ADD">
          		<a href="${base}/leader/leaderGoods!leaderGoodsEdit.action" ><input type="button" class="btn01" value="添加" /></a>
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
         <th> 商品名称 </th> 
         <th> 海典编码 </th> 
         <th> 商品PC价格</th>
         <th> 商品WAP价格</th> 
         <th> 商品APP价格</th>  
         <th> 返佣比例</th> 
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
         ${resc.pc_price?default('')}	
         </td> 
         <td align="center"> 
         ${resc.wap_price?default('')}	
         </td> 
         <td align="center"> 
         ${resc.app_price?default('')}	
         </td>                   
         <td align="center"> 
         ${resc.brokerage?default('')}	
         </td>          
         <td align="center"> 
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LX_SPYONGJIN_BIANJI">
         <a href="${base}/leader/leaderGoods!leaderGoodsUpEdit.action?id=${resc.id?default(0)}" >编辑</a>
          </@security.authorize>
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LX_SPYONGJIN_SHANCHU">
         <a href="${base}/leader/leaderGoods!leaderGoodsDelete.action?id=${resc.id?default(0)}&name=${name?default('')}" onClick="return confirm('确认要删除？')">删除</a>
          </@security.authorize>
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
	$().ready(function() {
		${flash_message()}
	});	
	
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
</script>
</html>
<style>
* {font-size:12px;}
.btn01 {font-size:12px;}
</style>