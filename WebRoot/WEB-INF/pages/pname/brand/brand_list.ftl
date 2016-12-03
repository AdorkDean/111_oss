<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>品牌管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/brand/brand!getBrandList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form">  
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>品牌名称： <input  name="brandName" type="text" value="${brandName?default('')}" class="input input_v1" /></td> 
            <td>拼音： <input  name="pinyin" type="text" value="${pinyin?default('')}" class="input input_v1" /></td>
            <td>网址： <input  name="url" type="text" value="${url?default('')}" class="input input_v1" /></td>
           <td> 
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_PINPAI_LIEBIAO_CHAXUN">
           <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
           </@security.authorize>
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_PINPAI_LIEBIAO_TIANJIA">
          		<input type="button" class="btn01" onclick="location.href='${base}/brand/brand!addOrUpdateBrand.action';" value="添加" />
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
         <th> 品牌ID </th> 
         <th> 品牌名称 </th> 
         <th> logo </th> 
         <th> 网址 </th> 
          <th> 拼音 </th> 
         <th> 显示方式</th> 
         <th> 创建时间 </th> 
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
         ${resc.brand_name?default('')}	
         </td>
           <td align="center"> 
         <img height="50" width="50"  alt="${resc.brand_name?default('')}" src="${ui1}${resc.logo?default('')}"/>	
         </td>
           <td align="center"> 
         <a href="${resc.brand_url?default('')}" target="_blank">${resc.brand_url?default('')}</a>	
         </td>
           <td align="center"> 
         ${resc.pinyin?default('')}	
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
         <#if resc.create_time?exists>${resc.create_time?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
        ${resc.remark?default('')}	
         </td> 
         <td align="center"> 
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_PINPAI_LIEBIAO_FANGWENLIANJIE">
         <a target="_blank" href="${www2}/${resc.id?default('')}/blist.html" >访问链接</a>
         </@security.authorize>
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_PINPAI_LIEBIAO_XIUGAI">
         <a href="${base}/brand/brand!addOrUpdateBrand.action?id=${resc.id?default('')}" >修改</a>
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
	
	   $("#form1").keydown(function(){
	if(event.keyCode==13){
	$("#buttonsub").click();
	}
});


</script>
</html>