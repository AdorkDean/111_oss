<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <title>分类管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/category/category!categoryList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>分类名称： <input  name="categoryName" type="text" value="${categoryName?default('')}" class="input input_v1" /></td> 
           <td>父类名称： <input  name="prantName" type="text" value="${prantName?default('')}" class="input input_v1" /></td> 
           <td>分类等级：
               <select name="level" >
					 <option value="0">全部</option>
					 <option <#if level?default('0')=='1'> selected</#if> value="1">根级</option>
					 <option <#if level?default('0')=='2'> selected</#if> value="2">一级</option>
					 <option <#if level?default('0')=='3'> selected</#if> value="3">二级</option>
					 <option <#if level?default('0')=='4'> selected</#if> value="4">三级</option>
				 </select>
            </td> 
             <td>排序方式：
               <select name="weight" >
					 <option <#if weight?default(-1)==-1> selected</#if> value="-1">请选择</option>
					 <option <#if weight?default(-1)==1> selected</#if> value="1">正序</option>
					 <option <#if weight?default(-1)==2> selected</#if> value="2">倒序</option>
				 </select>
            </td> 
           <td>
             <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_FENLEI_LIEBIAO_CAOXUN">
           		<input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
          	</@security.authorize>
          	 <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_FENLEI_LIEBIAO_TIANJIA">
          		<input type="button" class="btn01" value="添加" onclick="location.href='${base}/category/category!addOrUpdateCategory.action';"/>
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
         <th> 分类ID </th> 
         <th> 分类名称 </th> 
         <th> 等级 </th> 
         <th> 父类名称 </th> 
         <th> 创建时间 </th> 
         <th> 显示方式</th> 
         <th> 排序</th> 
          <th> 备注</th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="10" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
           <td align="center"> 
         ${resc.id?default('')}		
         </td> 
        <td align="center"> 
         ${resc.category_name?default('')}	
         </td> 
         <td align="center"> 
         <#if resc.category_level?exists>
         	<#if resc.category_level?default(0)==1>
         	根级
         	<#elseif resc.category_level?default(0)==2>
         	一级
         	<#elseif resc.category_level?default(0)==3>
         	二级
         	<#elseif resc.category_level?default(0)==4>
         	三级
         	</#if>
         </#if>
         </td> 
         <td align="center"> 
       ${resc.parentname?default('')}	
         </td> 
         <td align="center"> 
         <#if resc.create_time?exists>${resc.create_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
          <#if resc.is_pc?exists>
         	<#if resc.is_pc?default(0)==1>
         	pc显示
         	<#else>
         	pc不显示
         	</#if>
         </#if>
         <#if resc.is_wap?exists>
         	<#if resc.is_wap?default(0)==1>
         	<br>wap显示
         	<#else>
         	<br>wap不显示
         	</#if>
         </#if>
         <#if resc.is_app?exists>
         	<#if resc.is_app?default(0)==1>
         	<br>app显示
         	<#else>
         	<br>app不显示
         	</#if>
         </#if>
          
         
         </td> 
          <td align="center"> 
        ${resc.weight?default(0)}	
         </td> 
         <td align="center"> 
        ${resc.remarks?default('')}	
         </td> 
         <td align="center"> 
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_FENLEI_LIEBIAO_XIUGAI">
         <a href="${base}/category/category!addOrUpdateCategory.action?id=${resc.id?default('')}&prantName=${resc.parentname?default('')}" >修改</a>
         </@security.authorize>
         <!-- <a href="javascript:void(0);" onclick="deleteCategory(${resc.id?default('')})">删除</a>	-->
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
	function deleteCategory(id){
		if(confirm("该分类可能有下级分类，将下级分类全部删除，你确定要删除该模板吗?")){
			$.ajax({
				url:'${base}/category/category!deleteCategory.action',
				data:{"id":id},
				type:'post',
				success:function(data){
					if(data==1){
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
function openAddInfo(id,prantName){
window.open ("${base}/category/category!addOrUpdateCategory.action?id="+id+"&prantName="+prantName, 'newwindow', "width=800px,top=300, left=400,height=600px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");

}
</script>
</html>