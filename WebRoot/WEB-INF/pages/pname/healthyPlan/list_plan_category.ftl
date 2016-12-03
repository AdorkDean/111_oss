<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <title>健康方案分类</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
</head> 

<body> 
<div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
       <form name="form1" id="form1"  action="${base}/healthyplan/plan_category!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">	
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>分类名称： <input  name="classificationName" type="text" value="${classificationName?default('')}" class="input input_v1" /></td> 
            <td>
            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HEALTHY_PLAN_CLASSIFICATION_CHAXUN">
             <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" />
             </@security.authorize>
             <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HEALTHY_PLAN_CLASSIFICATION_ADD">
             <input type="button"   id="button_add" value="添加" class="btn01" onclick="categoryAdd('')" />
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
         <th style="width:50px;"> 序号</th> 
         <th style="width:100px;"> 名称 </th> 
         <th style="width:200px;"> 描述 </th>
         <th style="width:50px;">权重</th> 
         <th style="width:100px;"> 类型 </th> 
         <th class="b-r" style="width:100px;"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="6" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         
          <td align="center"> 
            ${resc_index+1?default('')}	
         </td>
           <td align="center"> 
         ${resc.classification_name?default('')}		
         </td> 
         <td align="center"> 
         ${resc.content?default('')}		
         </td> 
        <td align="center"> 
        ${resc.weight?default('')}		
         </td> 
         <td align="center"> 
          <#if resc.type==1>普通分类</#if><#if resc.type==2>推荐分类</#if>
         </td> 
         <td align="center"> 
             <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HEALTHY_PLAN_CLASSIFICATION_UPDATE">
            	<a href="javascript:void(0);" onclick="categoryAdd(${resc.id?default('')})">修改</a>
             </@security.authorize>
             <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HEALTHY_PLAN_CLASSIFICATION_DELETE">
                <#if resc.type==1>
           		 |<a href="javascript:void(0);" onclick="categoryDelete(${resc.id?default('')})">删除</a>
           		</#if>
             </@security.authorize>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="6" class="blank"></td> 
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
 <script type="text/javascript">
 function submitForm() {
   $('#form1').submit();
 }

function categoryAdd(classId){
   window.location.href="${base}/healthyplan/plan_category!toAddOrUpdate.action?classId="+classId;
}


function categoryDelete(classId){
    if(confirm("确定要删除该方案分类吗？")){
     $.ajax({
			url:"${base}/healthyplan/plan_category!deleteClassification.action",
			type:"post",
			data:{"classId":classId},
			success:function(data){
			   if(data=='1'){
			     $alert("success","删除成功！");
			     submitForm();
			   }else if(data=='0'){
			     $alert("error","删除失败！");
			   }else if(data=='2'){
			     $alert("error","该分类下面有方案不允许删除！");
			   }
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
 }
}
</script>


</html>