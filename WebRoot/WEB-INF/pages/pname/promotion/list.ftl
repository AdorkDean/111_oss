<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>公共列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>
</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
            
	     	<form name="form1" id="subform"  action="${base}/proms/prom!list.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
					
	        <div style="float:left;"> 
	            <div class="labelName">名称：</div>
	            <input name="prom.name" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;" value="<#if prom.name?exists>${prom.name}</#if>"/>
	            
	            
	            <div class="labelName" style="margin-left:15px;">时间：</div>
	            <input name="stime" type="text" value="${stime?default('')}" class="lname-i" style="border:#cccccc 1px solid;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
	            <span style="float:left;font-size:20px">-</span><input name="etime" type="text" value="${etime?default('')}" class="lname-i" style="border:#cccccc 1px solid;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
	            
	            
	            <div class="labelName" style="margin-left:15px;">状态：</div>
	            <select name="prom.status" class="select-ui">
	                 <option value="0" <#if prom.status?exists><#if prom.status==0>selected</#if></#if> >全部</option>
					 <option value="1" <#if prom.status?exists><#if prom.status==1>selected</#if></#if> >创建</option>
					 <option value="2" <#if prom.status?exists><#if prom.status==2>selected</#if></#if> >进行中 </option>
					 <option value="3" <#if prom.status?exists><#if prom.status==43>selected</#if></#if> >结束 </option>
				</select>
				  <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_QUERY">
				 <input type="submit" class="btn01" onclick="" value="查询" style="margin-left:15px;margin-top:2px;">
				  </@security.authorize>
				  <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_ADD">
	              <input type="button" class="btn01" onclick="addpage()" value="添加" style="margin-left:5px;margin-top:2px;">
	             </@security.authorize>
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list" style="width:100%">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
				    <td width="10%" align="center">促销类型</td>
			        <td width="20%" align="center">名称</td>
			        <td width="10%" align="center">创建时间</td>
			        <td width="10%" align="center">开始时间</td>
			        <td width="10%" align="center">结束时间</td>
			        <td width="5%" align="center">状态</td>
			        <td width="6%" align="center">优惠券使用</td>
			        <td width="10%" align="center">广告语</td>
			        <td width="150px" align="center">操作</td>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
				 
	  <#list pw.result?if_exists as l>
  
		      <tr class="content" style="text-align:center;"  >		
		       
		        <td align="center" class="bc">
		          <#if l.type==1>单品促销</#if>
		          <#if l.type==2>组合促销</#if>
		          <#if l.type==3>分类促销</#if>
		        </td>
		        <td align="center" class="bc">
		         ${l.name?default('')}
		        </td>
		        <td align="center" class="bc">
		          ${l.create_time}
		        </td>
		        <td align="center" class="bc">
		         ${l.start_time}
		        </td>
		        <td align="center" class="bc">
		         ${l.end_time}
		        </td>
		        <td align="center" class="bc">
		         <#if l.status==1>创建</#if>
		         <#if l.status==2>进行中</#if>
		         <#if l.status==3>结束</#if>
		         <#if l.status==4>取消</#if>
		         
		        </td>
		        <td align="center" class="bc">
		        <#if l.is_tiket==1>是</#if>
		        <#if l.is_tiket==0>否</#if>
		        </td>
		        <td align="center" class="bc">
		         ${l.advertise?default('')}
		        </td>
		        <td align="center" class="bc">
		        
		        	<#if l.status==1>
		        	 <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_SET">
		        	<a href="${base}/proms/prom!setDetialPage.action?id=${l.id?default(0)}&type=${l.type}&flag=1">设置商品</a>
		        	</@security.authorize>
		        	 <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_EDIT">
		        	 <a href="${base}/proms/prom!updateRecord.action?id=${l.id?default(0)}">编辑</a>
		        	 </@security.authorize>
		        	
		        	</#if>
		        	 <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_DETAIL">
		        	<a href="${base}/proms/prom!setDetialPage.action?id=${l.id?default(0)}&type=${l.type}&flag=2">查看</a>
		        	</@security.authorize>
		        	<#if l.status!=4>
		        	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_CANCEL">
		        	 <a href="javascript:void(0)" onclick="cancel(${l.id?default(0)})">取消</a>
		        	</@security.authorize>
		        	</#if>
		        </td>	        
		      </tr>
      </#list>
			 </tbody>
			  
	     </table>   
     
     
     <!-- 分页 Start -->    
     <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
     <!-- 分页 End --> 
     
    </div>  
    
    <script>
      function cancel(id){
      
       if(!window.confirm("您确定要取消该促销吗?如果您对进行中状态的促销活动进行取消操作，不可恢复")){
		   return;
	   }
       jQuery.ajax
	   ({
	       type: "post",
	       url: "${base}/proms/prom!cancel.action",	  
	       data:{"id":id},
	       success: function(data)
	       {
	       	
	    	   if(data>0){
	    	   		alert("操作成功");
	    	   		window.location.reload();
	    	   }else{
	    	   		alert("系统忙,请稍候再试！");
	    	   }
	       }
	   }); 
      }
      
      function addpage(){
      	window.location.href="${base}/proms/prom!inser_page.action";
      }
    </script>      
</body>
</html>