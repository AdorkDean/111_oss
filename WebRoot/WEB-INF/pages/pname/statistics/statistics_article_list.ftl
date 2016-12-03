<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
    <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>文章分享统计</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/statistics!statisticArticleList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
		<input type="hidden" name="sort" value="${sort?default(-1)}" id="sort">	
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>文章标题： <input  name="title" type="text" value="${title?default('')}" class="input input_v1" /></td> 
             <td colspan="2" >统计时间：
							<input  type="text" class="input input_v1"  value="${start_date?default('')}" name="start_date" id="start_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  >
						至
						 <input  type="text" class="input input_v1"  value="${end_date?default('')}" name="end_date" id="end_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  >
						</td>
           <td>
           		<input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm(-1)" /> 
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
         <th> 文章标题 </th>
         <th> <a href="javascript:void(0);" onclick="submitForm(1)">分享数量</a></th> 
         <th> <a href="javascript:void(0);" onclick="submitForm(2)">文章点击数量</a> </th> 
         <th> <a href="javascript:void(0);" onclick="submitForm(3)">文章商品点击数量</a> </th> 
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
         ${resc.title?default('')}		
         </td> 
        <td align="center"> 
         ${resc.fenxiangnum?default('')}	
         </td> 
        <td align="center"> 
         ${resc.worknum?default('')}	
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
	function submitForm(sort){
	 	$("#p_curPage").val("1");
	 	if(sort==-1){
	 		$("#sort").val(-1);
	 	}else if(sort==1){
	 		$("#sort").val(1);
	 	}else if(sort==2){
	 		$("#sort").val(2);
	 	}else if(sort==3){
	 		$("#sort").val(3);
	 	}else{
	 	$("#sort").val(-1);
	 	}
	 	$("#form1").submit();
	}
	
	   $("#form1").keydown(function(){
	if(event.keyCode==13){
	$("#buttonsub").click();
	}
});
</script>
</html>