<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>门店地址管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
   <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/store/store!getDispatchList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form">  
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>门店名称： <input  name="storeName" type="text" value="${storeName?default('')}" class="input input_v1" /></td> 
            <td>门店海典ID： <input  name="storeHD" type="text" value="${storeHD?default('')}" class="input input_v1" /></td>
           <td> 
           <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
          	<input type="button" class="btn01" onclick="location.href='${base}/store/store!addOrEditStore.action';" value="添加" />
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
         <th> 门店名称 </th> 
         <th> 门店地址 </th> 
         <th> 门店城市</th> 
         <th> 门店海典ID </th> 
         <th> 门店经营时间 </th> 
         <th> 创建时间 </th> 
          <th> 排序</th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="9" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
           <td align="center"> 
         ${resc.name?default('')}		
         </td> 
        <td align="center"> 
         ${resc.address?default('')}	
         </td>
        <td align="center"> 
         ${resc.city?default('')}	
         </td>
          <td align="center"> 
         ${resc.store_hd_id?default('')}	
         </td>
          <td align="center"> 
        <#if resc.start_dt?exists>${resc.start_dt?string('yyyy-MM-dd')}</#if><br>
        	——<br>	
        <#if resc.end_dt?exists>${resc.end_dt?string('yyyy-MM-dd')}</#if>	
         </td>
         <td align="center"> 
         <#if resc.create_dt?exists>${resc.create_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
        ${resc.sort?default('')}	
         </td> 
         <td align="center"> 
         <a  href="${base}/store/store!addOrEditStore.action?storeid=${resc.id?default(-1)}" >编辑</a>
         <a  href="javascript:void(0);" onclick="deleteStore(${resc.id?default(-1)});">删除</a>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="9" class="blank"></td> 
        </tr> 
       </tbody> 
      </table>
      <br /> 
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

function deleteStore(storeid){
		if(confirm("确定要删除该门店信息吗?")){
			$.ajax({
				url:'${base}/store/store!deleteStore.action',
				data:{"storeid":storeid},
				type:'post',
				success:function(data){
					if(data!=0){
					$alert("success","删除成功！");
						window.location.href="${base}/store/store!getDispatchList.action";
					}else{
						$alert("error","删除失败");
					}
				}
			});
		};
	}

</script>
</html>