<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
   <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
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
     <form name="form1" id="form1"  action="${base}/leader/newLeaderGoods!getLeaderGoodsList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>名称： <input  name="goodsName" type="text" value="${goodsName?default('')}" class="input input_v1" /></td> 
           <td>海典编码： <input  name="goodsno" type="text" value="${goodsno?default('')}" class="input input_v1" /></td> 
           <td>审核状态：
            <select name="status" >
					 <option value="-1">全部</option>
					 <option <#if status?default(-1)==0> selected</#if> value="0">未提交审核</option>
					 <option <#if status?default(-1)==1> selected</#if> value="1">审核中</option>
					 <option <#if status?default(-1)==2> selected</#if> value="2">审核通过</option>
					 <option <#if status?default(-1)==3> selected</#if> value="3">审核不通过</option>
				 </select>
           </td> 
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
          		<a href="${base}/leader/newLeaderGoods!addOrEdit.action" ><input type="button" class="btn01" value="添加" /></a>
           </td> 
          </tr> 
          <tr>
           <td  colspan="2">创建时间：
         	<input  type="text" class="input input_v1"  value="${start_date?default('')}" name="start_date" id="start_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
						至
			<input  type="text" class="input input_v1"  value="${end_date?default('')}" name="end_date" id="end_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
           </td> 
          <td colspan="2" ><input type="button"    value="批量提交审核" class="btn05" onclick="plcheck()" />
          <input type="button"    value="批量删除商品" class="btn05" onclick="pldeleteGoods()" />
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
          <th class="b-l"> <input type="checkbox" class="check_all"/> </th>
         <th class="b-l"> 序号 </th> 
         <th> 商品名称 </th> 
         <th> 海典编码 </th> 
         <th> 商品价格(wap)</th> 
         <th> 返佣金额</th> 
         <th> 返佣比例</th> 
         <th> 审核状态</th> 
         <th> 创建时间</th> 
         <th> 操作人</th> 
         <th> 备注</th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
          <td align="center"> 
         	<input type="checkbox" class="check_item" value="${resc.id?default('')}"/>	
         </td>
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
         <#if resc.create_dt?exists>
         ${resc.create_dt?string('YYYY-MM-dd HH:mm:ss')}	
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
         <#if resc.audit_status?default(0)!=1>
         	<#if resc.audit_status?default(0)!=2>
	         <a href="javascript:void(0)" onclick="tijiaoCheck(${resc.id?default()});" >提交审核</a><br>
         	</#if>
         <a href="${base}/leader/newLeaderGoods!addOrEdit.action?id=${resc.id?default(0)}" >编辑</a><br>
         <a href="javascript:void(0)" onclick="deleteBrokerageGoods(${resc.id?default(0)})" >删除</a>
         </#if>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="12" class="blank"></td> 
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


$().ready(function(){
	$(".check_all").click(function(){
		
		if($(this).is(':checked')){
			$(".check_item").attr("checked", true);
		}else{
			$(".check_item").attr("checked", false);
		}
	});
});


function plcheck(){
		if($(".check_item:checked").size() > 0){
				var ids = "";
			    $(".check_item:checked").each(function(){
			        ids += $(this).val() + ","; 
			    });
		$.ajax({
 		url:"${base}/leader/newLeaderGoods!tijiaoCheck.action",
 		type:"post",
 		data:{"ids":ids,"type":2},
 		success:function(data){
 		$alert('success',data)
 		window.location.reload();
 		}
 	});
		}else{
			alert("请选择相关记录");
		}
}
function pldeleteGoods(){
if(confirm("删除后不可恢复，确定要删除这批佣金商品吗？")){
		if($(".check_item:checked").size() > 0){
				var ids = "";
			    $(".check_item:checked").each(function(){
			        ids += $(this).val() + ","; 
			    });
		$.ajax({
 		url:"${base}/leader/newLeaderGoods!plDeleteGoods.action",
 		type:"post",
 		data:{"ids":ids},
 		success:function(data){
 		$alert('success',data)
 		window.location.reload();
 		}
 	});
		}else{
			alert("请选择相关记录");
		}
		}
}

	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	
	
	
	 function tijiaoCheck(id){
 	$.ajax({
 		url:"${base}/leader/newLeaderGoods!tijiaoCheck.action",
 		type:"post",
 		data:{"id":id,"type":1},
 		success:function(data){
 		$alert('success',data)
 		window.location.reload();
 		}
 	});
 	}
	 function deleteBrokerageGoods(id){
	 if(confirm("删除后不可恢复，确定要删除该佣金商品吗？")){
 	$.ajax({
 		url:"${base}/leader/newLeaderGoods!deleteGoods.action",
 		type:"post",
 		data:{"id":id},
 		success:function(data){
 		if(data>0){
 		$alert('success','删除成功')
 		window.location.reload();
 		}else{
 		$alert('error','删除失败')
 		
 		}
 		}
 	});
 	}
 	}
</script>
</html>
<style>
* {font-size:12px;}
.btn01 {font-size:12px;}
</style>