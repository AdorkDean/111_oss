<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>退换货详细</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" href="${base}/web/plugins/jquery-ui-1.8.14.custom/css/ui-lightness/jquery-ui-1.8.14.custom.css" rel="stylesheet" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
		<style type="text/css">
#nav {
    height: 30px;
    width: 100%;
}
#nav ul {
    margin: 0 0 0 30px;
    padding: 0px;
    font-size: 12px;
    line-height: 30px;
    white-space: nowrap;
}
#nav li {
    background: #d0d0d0 none repeat scroll 0 0;
    color: #fff;
    cursor: pointer;
    display: inline;
    float: left;
    height: 26px;
    line-height: 26px;
    text-align: center;
    width: 80px;
}
#nav li a {
    text-decoration: none;
    font-family: Arial, Helvetica, sans-serif;
    padding: 7px 10px;
}
#nav li a:hover {
    color: #ff0;
    background-color: #f00;
}
#nav li .ll{
    background: #fff none repeat scroll 0 0;
    color: #666;
}
#optBtns input{
	cursor: pointer;
}








</style>
</head> 

<body> 
	<div id="nav">
		<ul> 
			<li class="ll" title="d1">退货信息</li> 
			<li title="d2">退货项</li> 
			<li title="d3">退货图片</li> 
		</ul> 
	</div>
	<div class="shop_main" id="shop_main"> 
		<div> 
			<!-- ------------------------退货信息开始---------------------------------------- -->
			<div id="d1">
		    	<input type="hidden" id="id" name="id" value="${tReturn.id}"/>
		    	<div class="m-contents">
			    	<div class="labelnamese">订单号：</div>
			    	<input type="text" name="orderSn" disabled="true" class="i-text-i" id="orderSn" value="${tReturn.orderSn?if_exists}"/>
			    	<div class="labelnamese">服务类型：</div>
			    	<input id="serviceType" type="hidden" value="${tReturn.serviceType?if_exists}"/>
		    		<#if tReturn?exists && tReturn.serviceType?exists && tReturn.serviceType==0>
						<input class="i-text-i" value="退货"  disabled="true"/>
					<#elseif  tReturn?exists && tReturn.serviceType?exists && tReturn.serviceType==1>
						<input class="i-text-i" disabled="true" value="换货"/>
					<#else>
						<input class="i-text-i" disabled="true" value="状态有误"/>
					</#if>
		    	</div>
		    	
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">快递单号：</div>
			    	<input type="text" name="expressDelivery" disabled="true" class="i-text-i" id="expressDelivery" value="${tReturn.expressDelivery?if_exists}"/>
			    	<div class="labelnamese">快递公司：</div>
			    	<input type="text" name="expressCompany" disabled="true" class="i-text-i" id="expressCompany" value="${tReturn.expressCompany?if_exists}"/>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">发货人电话：</div>
			    	<input type="text" name="shipperPhone" disabled="true" class="i-text-i" id="shipperPhone" value="${tReturn.shipperPhone?if_exists}"/>
			    	<div class="labelnamese">用户名：</div>
			    	<input type="text" name="userName" disabled="true" class="i-text-i" id="userName" value="<#if tMember?exists>${tMember.userName?if_exists}</#if>"/>
		    	</div>
		    	
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">退款金额：</div>
			    	<input class="i-text-i" disabled="true" value="${tReturn.refundAmount?default('')}"/></span>
			    	<div class="labelnamese">退款描述：</div>
			    	<input type="text" name="refundDescribe" disabled="true" class="i-text-i" id="refundDescribe" value="${tReturn.refundDescribe?if_exists}"/>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">订单状态：</div>
						<input type="hidden" name="orderStatus" disabled="true" class="i-text-i" id="orderStatus" value="${tReturn.orderStatus?if_exists}"/>
			    		
			    		<#if tReturn.orderStatus?exists && tReturn.orderStatus==0>
							<input type="text" disabled="true" class="i-text-i" value="未处理"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==1>
							<input type="text" disabled="true" class="i-text-i" value="已通过"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==2>
							<input type="text" disabled="true" class="i-text-i" value="未通过"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==3>
							<input type="text" disabled="true" class="i-text-i" value="验货通过"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==4>
							<input type="text" disabled="true" class="i-text-i" value="验货未通过"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==5>
							<input type="text" disabled="true" class="i-text-i" value="退款中"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==6>
							<input type="text" disabled="true" class="i-text-i" value="处理中"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==7>
							<input type="text" disabled="true" class="i-text-i" value="验货中"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==8>
							<input type="text" disabled="true" class="i-text-i" value="换货中"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==9>
							<input type="text" disabled="true" class="i-text-i" value="结束"/>
						<#else>
							<input type="text" disabled="true" class="i-text-i" value="状态有误"/>
						</#if>
			    	<div class="labelnamese">退款账号：</div>
			    	<input type="text" name="refundAccount" disabled="true" class="i-text-i" id="refundAccount" value="${tReturn.refundAccount?if_exists}"/>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">退款银行：</div>
			    	<input type="text" name="refundBank" disabled="true" class="i-text-i" id="refundBank" value="${tReturn.refundBank?if_exists}"/>
			    	<div class="labelnamese">原订单ID：</div>
			    	<input type="text" name="oldOrderId" disabled="true" class="i-text-i" id="oldOrderId" value="${tReturn.oldOrderId?if_exists}"/>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">创建时间：</div>
			    	<input type="text" name="createTime" disabled="true" class="i-text-i" id="createTime" value="<#if tReturn?exists && tReturn.createTime?exists>${tReturn.createTime?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if>"/>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">备注：</div>
			    	<textarea name="refundRemark" id="refundRemark" style="background-color:#fff;width:400px;height:40px;" class="i-text-i">${tReturn.refundRemark?if_exists}</textarea>
		    	</div>
		    </div>
		    <!-- ------------------------退货信息结束---------------------------------------- -->
		    
		    <!-- ------------------------退货项开始---------------------------------------- -->
    		<div id="d2" style="display:none">
    			<table class="table-list">
    				<thead style="background:#dbf1fc;"> 
						<tr style="height:40px;" class="content">
							<th style="width:4%;">序号</th>
							<th>商品编号</th>
							<th>商品名称</th>
							<th>数量</th>
						</tr>
					</thead>
					<tbody> 
						<#list itemList as item>
							<tr class="content" style="text-align:center;">
								<td>${item.goodsId?if_exists}</td>
								<td>${item.goodsId?if_exists}</td>
								<td>${item.quantity?if_exists}</td>
							</tr>
						</#list>
					</tbody>
    			</table>
    		</div>
    		<!-- ------------------------退货项结束---------------------------------------- -->
			
			
			<!-- ------------------------退货图片开始---------------------------------------- -->
			<div id="d3" style="display:none">
				<#list imageList as image>
					<image src="${image.url?if_exists}"/>
				</#list>
			</div> 
			<!-- ------------------------退货图片结束---------------------------------------- -->
    	</div> 
    	
    	<div id="optBtns" style="height:28px; overflow:hidden; width:700px;position:relative; margin:0 auto;padding-top: 30px;">
			<input type="button" disabled class="button statusbtn passed" id="passed" value="通过"  />
			<input type="button" disabled class="button statusbtn noPass" id="noPass" value="不通过" />
			<input type="button" disabled class="button statusbtn passInspection" id="passInspection" value="验货通过"  />
			<input type="button" disabled class="button validateNotPass" id="validateNotPass" value="验货未通过"  />
			<input type="button" disabled class="button statusbtn refund" id="refund" value="退款中"  />
			<input type="button" disabled class="button statusbtn replaceGoods" id="replaceGoods" value="换货中"  />
			<input type="button" class="button statusbtn end " id="end" value="结束"  />
			<input type="button" class="button" value="返回" onclick="goList()" style="margin-left:10px;"/>
		</div>
    </div>   
    
    <!-- ----------------弹出层开始---------------- -->
<div class="s_wp" style="display:none" id="audit">
	<div class="s_con">
		<div class="s_int">
			<form id="f" method="post">
				<input type="hidden" name="flag" id="flag" />
				<table width="100%" height="130px" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="23%" align="right" valign="top">收货人：</td>
						<td width="77%"><input type="text" name="receiver" value="<#if tOrder?exists>${tOrder.receiver?if_exists}</#if>" style="height:26px;width:200px" /></td>
					</tr>
					<tr>
						<td width="23%" align="right" valign="top">手机号：</td>
						<td width="77%"><input type="text" name="phone" value="<#if tOrder?exists>${tOrder.phone?if_exists}</#if>" style="height:26px;width:200px;margin-top: 2px;" /></td>
					</tr>
					<tr>
						<td width="23%" align="right" valign="top">收货地址：</td>
						<td width="77%"><input type="text" name="address" value="<#if tOrder?exists>${tOrder.areaName?if_exists}${tOrder.detailedAddress?if_exists}</#if>" style="height:26px;width:200px;margin-top: 2px;" /></td>
					</tr>
					<!-- 收货人,手机号,收货地址自动带出 -->
					
					<tr>
						<td width="23%" align="right" valign="top">物流公司：</td>
						<td width="77%"><input type="text" name="expressCompany" style="height:26px;width:200px;margin-top: 2px;" /></td>
					</tr>
					<tr>
						<td width="23%" align="right" valign="top">快递单号：</td>
						<td width="77%"><input type="text" name="expressDelivery" style="height:26px;width:200px;margin-top: 2px;" /></td>
					</tr>
					<tr>
						<td width="23%" align="right" valign="top" style="height:26px;width:200px;margin-top: 2px;">配送方式：</td>
						<td width="77%">普通快递</td>
					</tr>
					<tr>
						<td width="23%" align="right" valign="top" style="height:26px;width:200px;margin-top: 2px;">备注：</td>
						<td width="77%"><textarea id="remark" name="remark">${tReturn.refundRemark?if_exists}</textarea></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" onclick="mySumbit()" value="添加" class="tex_1" style="height:26px;width:60px" />
							<input type="button" value="关闭" class="tex_1" onclick="gb()" style="height:26px;width:60px" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
    <!-- ----------------弹出层结束---------------- -->
</div>    
<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
 <script type="text/javascript">
		var noPass_huanHuo_status=4;
		function goList(){
			window.location.href="${base}/returns/returns!returnPage.action";
		}
		$(function() { 
			$("ul > li").click(tab); 
			
			function tab() { 
				$(this).addClass("ll").siblings().removeClass("ll"); 
				var tab = $(this).attr("title"); 
				$("#" + tab).show().siblings().hide(); 
			}; 
		}); 
		
		$(function(){
			initBtn();
		});
		
		function initBtn(){
			var orderStatus = $("#orderStatus").val();
			if(orderStatus == '0'){//未处理
				$("#passed").attr('disabled',false);
				$("#noPass").attr('disabled',false);
			}else if(orderStatus == '1' || orderStatus =='7'){
				$("#passInspection").attr('disabled',false);
				$("#validateNotPass").attr('disabled',false);
			}else if(orderStatus == '3'){
				if($("#serviceType").val() == '0'){
					$("#refund").attr('disabled',false);
				}else if($("#serviceType").val() == '1'){
					$("#replaceGoods").attr('disabled',false);
				}
			}else if(orderStatus == '9'){
				$("#end").attr('disabled',true);
			}
		}
		$(function(){
			//1通过
			$("#passed").click(function(){
				updateOrderStatus(1);//审核通过
			});
			//不通过
			$("#noPass").click(function(){
				updateOrderStatus(2);//审核不通过
			});
			//验货通过
			$("#passInspection").click(function(){
				updateOrderStatus(3);//验货通过
			});
			//验货未通过
			$("#validateNotPass").click(function(){
				noPass_huanHuo_status = 4;
				$('#audit').dialog('open');
				
				//updateOrderStatus(4);//验货未通过,弹层,输入物流信息
			});
			//退款中
			$("#refund").click(function(){
				updateOrderStatus(5);//退款中
			});
			//处理中
			$("#statusbtn").click(function(){
				updateOrderStatus(6);//处理中
			});
			//验货中
			$("#statusbtn").click(function(){
				updateOrderStatus(7);//验货中
			});
			//换货中
			$("#replaceGoods").click(function(){
				noPass_huanHuo_status = 8;
				$('#audit').dialog('open');
			
				//updateOrderStatus(8);//换货中
			});
			//结束
			$("#end").click(function(){
				updateOrderStatus(9);//结束
			});
		});
		
		function updateOrderStatus(status){
			var conf = confirm("您确定要修改?");
			if(!conf){
				return false;
			}
			var id = $("#id").val();
			var refundRemark = $("#refundRemark").val();
			$.ajax({//returns/returns!returnPage.action
				url: "${base}/returns/updateStatus!updateStatus.action",
				type: "POST",
				data: {'id':id,'status':status,'refundRemark':refundRemark} ,
				success: function(data){
					if(data.resultStatus=='1'){
						$alert('success',data.message);
					}else{
						$alert('warn',data.message);
					}
					window.location.href="${base}/returns/returns!returnPage.action";
				},error:function(){
					alert("内部错误,请稍后重试!");
					window.location.href="${base}/returns/returns!returnPage.action";
				}
			});
		}
		
		$('#audit').dialog({
			autoOpen: false,
			width: 470,
			title:'验货不通过',
			resizable: false,
			modal: true //遮蔽
		});
		function shenhe(){
			$('#flag').val("add");
			$('#audit').dialog('open');
		}
		function gb(){
			$('#audit').dialog('close');
		}
		
		/**
		 * 验货不通过/换货中,提交
		 */
		function mySumbit(){
			var id = $("#id").val();
			var receiver = $("#receiver").val();//收货人
			var phone = $("#phone").val();//手机号
			var addresss = $("#address").val();//收货地址
			var expressDelivery = $("#expressDelivery").val();//快递单号
			var expressCompany = $("#expressCompany").val();//物流公司
			var remark = $("#remark").val();//备注
			if(expressDelivery==null || expressDelivery==""){
				alert("快递单号不能为空!");
				return false;
			}
			if(expressCompany==null || expressCompany==""){
				alert("物流公司不能为空!");
				return false;
			}
			
			var status = noPass_huanHuo_status;
			$.ajax({
				url: "${base}/returns/validateNotPass!validateNotPass.action",
				type: "POST",
				data: {'id':id,'status':status,'expressDelivery':expressDelivery,'expressCompany':expressCompany,'refundRemark':remark} ,
				success: function(data){
					if(data.resultStatus=='1'){
						$alert('success',data.message);
					}else{
						$alert('warn',data.message);
					}
					window.location.href="${base}/returns/returns!returnPage.action";
				},error:function(){
					alert("内部错误,请稍后重试!");
					window.location.href="${base}/returns/returns!returnPage.action";
				}
			});
		}
</script>
</body>

</html>