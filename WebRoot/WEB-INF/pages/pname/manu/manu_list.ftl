<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>生产厂家管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/manu/manu!getManufacturerList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form">  
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>厂家名称： <input  name="manuName" type="text" value="${manuName?default('')}" class="input input_v1" /></td> 
            <td>厂家状态：
               <select name="status" >
					 <option value="-1">全部</option>
					 <option <#if status?default(-1)==0> selected</#if> value="0">启用</option>
					 <option <#if status?default(-1)==1> selected</#if> value="1">禁用</option>
				 </select>
            </td> 
           <td> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_CHANGJIA_LIEBIAO_CHAXUN">
           <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
            </@security.authorize>
            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_CHANGJIA_LIEBIAO_TIANJIA">
          		<input type="button" onclick="location.href='${base}/manu/manu!addOrUpdateManu.action';" class="btn01" value="添加"/>
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
         <th> 厂家名称 </th> 
         <th> 厂家logo </th> 
         <th> 广告语</th> 
         <th> 厂家状态</th> 
         <th> 创建时间 </th> 
          <th> 备注</th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="8" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
        <td align="center"> 
         ${resc.manu_name?default('')}	
         </td>
           <td align="center"> 
         <img height="50" width="50"  alt="${resc.manu_name?default('')}" src="${ui1}${resc.manu_logo?default('')}"/>	
         </td>
           <td align="center"> 
       ${resc.slogan?default('')}
         </td>
         <td align="center"> 
         <#if resc.status?exists>
         	<#if resc.status?default(1)==0>
         	启用
         	<#else>
         	禁用
         	</#if>
         <#else>
        	 禁用	
         </#if>
         </td> 
         <td align="center"> 
         <#if resc.create_time?exists>${resc.create_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
        ${resc.remarks?default('')}	
         </td> 
         <td align="center"> 
          <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_CHANGJIA_LIEBIAO_XIUGAI">
        	 <a href="${base}/manu/manu!addOrUpdateManu.action?id=${resc.id?default('')}" >修改</a>
         </@security.authorize>
         <!-- <a href="javascript:void(0);" onclick="deleteCategory(${resc.id?default('')})">删除</a>	-->
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="8" class="blank"></td> 
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
	
	   $("#form1").keydown(function(){
	if(event.keyCode==13){
	$("#buttonsub").click();
	}
});
</script>
</html>