<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <title>医卡通商品列表</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/ykt/ykt!yktGoodsList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>海典编号： <input  name="goodsno" type="text" value="${goodsno?default('')}" class="input input_v1" /></td> 
           <td>商品名称： <input  name="goodsname" type="text" value="${goodsname?default('')}" class="input input_v1" /></td> 
           <td>
           		<input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
          	  <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YKT_LIEBIAO_TIANJIA">
          		<input type="button" class="btn01" value="添加" onclick="location.href='${base}/ykt/ykt!addOrEditYktGoods.action';"/>
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
         <th> 商品编号 </th> 
         <th> 商品名称 </th> 
         <th> 创建时间 </th> 
         <th class="b-r"> 操作 </th> 
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
         ${resc.goods_no?default('')}		
         </td> 
        <td align="center"> 
         ${resc.goods_name?default('')}	
         </td> 
         <td align="center"> 
         <#if resc.create_time?exists>${resc.create_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YKT_LIEBIAO_XIUGAI">
         <a href="${base}/ykt/ykt!addOrEditYktGoods.action?id=${resc.id?default('')}" >修改</a>
         </@security.authorize>
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_YKT_LIEBIAO_SHANCHU">
         <a href="javascript:void(0);" onclick="deleteYktGoods(${resc.id?default('')})">删除</a>	
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
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	function deleteYktGoods(id){
		if(confirm("您确定要删除该医卡通商品吗？")){
			$.ajax({
				url:'${base}/ykt/ykt!deleteYktGoods.action',
				data:{"id":id},
				type:'post',
				success:function(data){
					if(data>0){
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
</script>
</html>