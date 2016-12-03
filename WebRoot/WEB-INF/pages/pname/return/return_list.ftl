<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>退换货管理</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:96%;"> 
            
     	<form name="form1" id="subform" action="${base}/returns/returns!returnPage.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
					
	        <div style="float:left;"> 
	            <div class="labelName">用户名：</div>
	            <input name="userName" type="text" value="${userName?if_exists}" class="lname-i" style="border:#cccccc 1px solid;"/>
	            
	            <div class="labelName" style="margin-left:15px;">订单号：</div>
	            <input name="orderSn" type="text" value="${orderSn?if_exists}" class="lname-i" style="border:#cccccc 1px solid;"/>
	            <div class="labelName" style="margin-left:15px;">退款描述：</div>
	            <input name="refundDescribe" type="text" value="${refundDescribe?if_exists}" class="lname-i" style="border:#cccccc 1px solid;"/>
	            	<div class="labelName" style="margin-left:15px;">订单状态：</div>
		            <select name="orderStatus" class="select-ui" value="${orderStatus?if_exists}">
						<option value="" >全部状态</option>
						<option value="0" <#if orderStatus?exists && orderStatus=='0'>selected = "selected"</#if>>未处理</option>
						<option value="1" <#if orderStatus?exists && orderStatus=='1'>selected = "selected"</#if>>已通过</option>
						<option value="2" <#if orderStatus?exists && orderStatus=='2'>selected = "selected"</#if>>未通过</option>
						<option value="3" <#if orderStatus?exists && orderStatus=='3'>selected = "selected"</#if>>验货通过</option>
						<option value="4" <#if orderStatus?exists && orderStatus=='4'>selected = "selected"</#if>>验货未通过</option>
						<option value="5" <#if orderStatus?exists && orderStatus=='5'>selected = "selected"</#if>>退款中</option>
						<option value="6" <#if orderStatus?exists && orderStatus=='6'>selected = "selected"</#if>>处理中</option>
						<option value="7" <#if orderStatus?exists && orderStatus=='7'>selected = "selected"</#if>>验货中</option>
						<option value="8" <#if orderStatus?exists && orderStatus=='8'>selected = "selected"</#if>>换货中</option>
						<option value="9" <#if orderStatus?exists && orderStatus=='9'>selected = "selected"</#if>>结束</option>
					</select>
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_LIEBIAO_CHAXUN">
	            	<input type="button" class="btn01" onclick="search()" value="搜索" style="margin-left:15px;margin-top:2px;">
	            </@security.authorize>
	            <!--<div class="labelName" style="margin-left:15px;">名称3：</div>
	            <select name="" class="select-ui">
					 <option value="0">请选择</option>
					 <option value="1">项目1</option>
				</select>
				
				<input name="" type="checkbox" value="" class="lname-c" style="border:#cccccc 1px solid;margin-left:15px;"/>
	            <div class="labelName" style="margin-left:5px;">选项1</div>
				
				<input name="" type="radio" value="" class="lname-c" style="border:#cccccc 1px solid;margin-left:15px;"/>
	            <div class="labelName" style="margin-left:5px;">选项1</div>
	            
	            <input type="button" class="btn01" onclick="" value="添加" style="margin-left:15px;margin-top:2px;">
	            <input type="button" class="btn01" onclick="" value="返回" style="margin-left:15px;margin-top:2px;">
	            -->
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
			   	 	<th style="width:4%;">序号</th>
					<th>订单号</th>
					<th>服务类型</th>
					<th>快递单号</th>
					<th>快递公司</th>
					<th>发货人电话</th>
					<th>用户名</th>
					<th>退款金额</th>
					<th>退换描述</th>
					<th>订单状态</th>
					<!--<th>退款账号</th>
					<th>退款银行</th>-->
					<th>退款备注</th>
					<th>创建时间</th>
					<th>原订单编号</th>
					<th>管理</th>
			   	 </tr>
			 </thead>
			 <!-- 查询内容  -->
		     <tbody> 
	   	     <!-- #list pw.result as abc -->
	   	     	<#list pw.result as return>
				 <tr class="content" style="text-align:center;">
					<td>${return_index+1}</td>
					<td title="${return.orderSn?default('')}">${return.orderSn?default('')}</td>
					<td>
						<#if return?exists && return.serviceType?exists && return.serviceType==0>
							退货
						<#elseif return?exists && return.serviceType?exists && return.serviceType==1>
							换货
						<#elseif return?exists && return.serviceType?exists && return.serviceType==2>
							退款(无需退货)
						<#else>
							状态有误
						</#if>
					</td>
					<td title="${return.expressDelivery?default('')}">${return.expressDelivery?default('')}</td>
					<td title="${return.expressCompany?default('')}">${return.expressCompany?default('')}</td>
					<td title="${return.shipperPhone?default('')}">${return.shipperPhone?default('')}</td>
					<td title="${return.userName?default('')}">${return.userName?default('')}</td>
					<td title="${return.refundAmount?if_exists}">
						<#if return.refundAmount?exists>
							${currency(return.refundAmount?if_exists,"true")}
						</#if>
					</td>
					<td title="${return.refundDescribe?default('')}">
					<#if return?exists && return.refundDescribe?exists && return.refundDescribe?length gt 15>
							${return.refundDescribe[0..14]}...
						<#else>
							${return.refundDescribe?default('')}
						</#if>
					</td>
					<td title="<#if return.orderStatus?exists && return.orderStatus==0>未处理<#elseif return.orderStatus?exists && return.orderStatus==1>已通过<#elseif return.orderStatus?exists && return.orderStatus==2>未通过<#elseif return.orderStatus?exists && return.orderStatus==3>验货通过<#elseif return.orderStatus?exists && return.orderStatus==4>验货未通过<#elseif return.orderStatus?exists && return.orderStatus==5>退款中<#elseif return.orderStatus?exists && return.orderStatus==6>处理中<#elseif return.orderStatus?exists && return.orderStatus==7>验货中<#elseif return.orderStatus?exists && return.orderStatus==8>换货中<#elseif return.orderStatus?exists && return.orderStatus==9>结束<#else>状态有误	</#if>">
						<#if return.orderStatus?exists && return.orderStatus==0>
							未处理
						<#elseif return.orderStatus?exists && return.orderStatus==1>
							已通过
						<#elseif return.orderStatus?exists && return.orderStatus==2>
							未通过
						<#elseif return.orderStatus?exists && return.orderStatus==3>
							验货通过
						<#elseif return.orderStatus?exists && return.orderStatus==4>
							验货未通过
						<#elseif return.orderStatus?exists && return.orderStatus==5>
							退款中
						<#elseif return.orderStatus?exists && return.orderStatus==6>
							处理中
						<#elseif return.orderStatus?exists && return.orderStatus==7>
							验货中
						<#elseif return.orderStatus?exists && return.orderStatus==8>
							换货中
						<#elseif return.orderStatus?exists && return.orderStatus==9>
							结束
						<#else>
							状态有误
						</#if>
					</td>
					<!--<td>${return.refundAccount?default('')}</td>
					<td>${return.refundBank?default('')}</td>-->
					<td title="${return.refundRemark?default('')}">
						<#if return?exists && return.refundRemark?exists && return.refundRemark?length gt 15>
							${return.refundRemark[0..14]}...
						<#else>
							${return.refundRemark?default('')}
						</#if>
					</td>
					<td title="<#if return.createTime?exists>${return.createTime?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if>">
						<#if return.createTime?exists>
			            	${return.createTime?if_exists?string("yyyy-MM-dd HH:mm:ss")}
						</#if>
					</td>
					<td>${return.oldOrderSn?default('')}</td>
				 	<td>
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_LIEBIAO_CAOZUO">
	            			<a href="javascript:void(0)" onclick="operate(${return.id?if_exists})">操作</a>&nbsp;|&nbsp;
				 		</@security.authorize>
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_LIEBIAO_CHAKAN">
	            			<a href="javascript:void(0)" onclick="detailReturn(${return.id?default('')})">查看</a>
				 		</@security.authorize>
				 	</td>
				 </tr>
				 </#list>
	   		 <!-- /#list -->
			 </tbody>
			  
	     </table>   
     
     
     <!-- 分页 Start -->    
     <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
     <!-- 分页 End --> 
     
    </div>        
    <script type="text/javascript">
    	/**
    	 * 查看详细
    	 */
    	function detailReturn(id){
    		window.location.href="${base}/returns/detailReturns!detailReturnPage.action?id="+id;
    	}
    	
    	function search(){
    		$("#subform").submit();
    	}
    	
    	/**
    	  * 操作
    	  */
    	function operate(id){
    		if(id==null || id==""){
		    	$alert('请选择一条数据');
		    	return false;
    		}
    		window.location.href="${base}/returns/operateReturnPage!operateReturnPage.action?id="+id;
    	}
    
    </script>
</body>
</html>