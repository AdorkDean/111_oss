<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>注册领秀添加药房商品</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/leaderGoodsRecommend!registerLeaderGoodsList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
		<input type="hidden" name="type" value="1" id="type">
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>名称： <input  name="goodsName" type="text" value="${goodsName?default('')}" class="input input_v1" /></td> 
           <td>海典编码： <input  name="goodno" type="text" value="${goodno?default('')}" class="input input_v1" /></td> 
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
          		<a href="${base}/leader/leaderGoodsRecommend!addLeaderRecommendGoods.action?type=1" ><input type="button" class="btn01" value="添加" /></a>
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
         <th> 商品名称 </th> 
         <th> 海典编码 </th> 
         <th> 返佣金额</th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc.goods_name?default('')}		
         </td> 
         <td align="center"> 
         ${resc.goodsno?default('')}		
         </td> 
         <td align="center"> 
         ${resc.rebate_amount?default('')}	
         </td>          
         <td align="center"> 
              <a href="javascript:deleteGoods(${resc.id?default(0)})" >删除</a>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="4" class="blank"></td> 
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
	//删除
	function deleteGoods(id){
	    if(confirm("确定要删除该商品吗？"))
		 {
		     $.ajax({
			url:"${base}/leader/leaderGoodsRecommend!deleteLeaderRecommendGoods.action",
					type:"post",
				    data: {"id":id} ,
					success:function(data){
					 if(data.flag=="1"){
			           $alert('success','删除成功');
			           	window.location.href="${base}/leader/leaderGoodsRecommend!registerLeaderGoodsList.action";
			          }else if(data.flag=="0"){
			            $alert('warn','删除失败');
			          }else{
			          	$alert('warn','删除失败');
			          }
		       }
		 	 });
		 }
	}
</script>
</html>
