<!DOCTYPE html>
<html lang="en">
<head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <title>预订单处理页面</title> 
   <script type="text/javascript" src="${base}/web/js/jquery-1.8.3.js"></script>
   <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
  <link type="text/css" rel="stylesheet" href="${base}/web/css/reserve_order.css" /> 
 </head>
 
 <body>
    <div class="submit_wraper">
    	<div class="submit_wrap">
    		<div class="submit_header clearfix">
    			<span>当前用户：</span>
    			<input type="text" value="${tmember.userName?default('')}" readonly="readonly"/>
    			<span>姓名：</span>
    			<input type="text" value="<#if tmemberbasemessageext?exists>${tmemberbasemessageext.realName?default('')}</#if>" readonly="readonly"/>
    			<span>电话：</span>
    			<input type="text" value="${tmember.mobile?default('')}" readonly="readonly"/>
    		</div>
    		<div class="submint_nav clearfix">
    			<ul style="position:relative;">
    				<li style="position:absolute; right:-10px; top:0;" class="btn05" ><a style="display:block; color:#fff;" href="${base}/reserveorder/reserveorder!reserverOrderList.action?user_name=${tmember.userName?default('')}" target="_blank" >历史记录</a></li>
    				<li class="btn05" onclick="shuaxin()"><a style="display:block; color:#fff;" href="${base}/reserveorder/reserveorder!reserverOrderByUser.action?memberId=${tmember.id?default('')}&source=${source?default('')}">刷新</a></li>
    				<li class="btn05" onclick="goBack()"><a style="display:block; color:#fff;" href="javascript:goBack(${source?default('')})">返回</a></li>
    			</ul>
    		</div>
    		<div class="submit_box submit_box1">
    			<div class="submit_content clearfix">
    			<#list weichulilist?if_exists as resc>
					<div class="sub_cont clearfix">
						<div class="sub_title clearfix"><b><#if resc.order_status == 9>已取消订单(取消原因：${resc.cancel_remark?default('')})</#if></b><span class="sub_time">(<#if resc.source == 1>PC</#if><#if resc.source == 2>WAP</#if><#if resc.source == 3>APP</#if>)${resc.create_dt?string("yyyy-MM-dd HH:mm:ss")}</span></div>
						<div class="sub_list">
							<div class="sublist_left">
							    <#if resc.audit_status == 1 && resc.order_status == 0>
								  <input class="check_btn" type="checkbox" value="${resc.id?default('')}" name="order_checkbox" onclick="getFreight()"/>
								</#if>
								<#if resc.audit_status == 1>
								   <span>审核通过</span>
								 <#else>
								    <span style="background: #EEEE00;">等待审核</span>   
								</#if>
							</div>
							<div class="sublist_center">
								<div class="subc_top">
									<h1>${resc.receiver?default('')}/${resc.mobile?default('')}<span class="tel_box"></span>回拨电话: <strong class="tel_box">${resc.reply_mobile?default('')}</strong></h1>
									<p>收货地址：${resc.area_name?default('')}${resc.detailed_address?default('')}</p>
								</div>
								<div class="subc_bottom clearfix">
									<dl>
										<dt><span><a href="${www1}${resc.goodid?default('')}.html" target="_blank"><img class="sit-preview" alt="" src="${ui1}${resc.abbreviation_picture?default('')}" data-preview-url="${ui1}${resc.abbreviation_picture?default('')}" style="width:87px;height:78px"/></a></span></dt>
										<dd class="proprice_box clearfix">
											<p class="pro_lineone"><a href="${www1}${resc.goodid?default('')}.html" target="_blank" style="color:blue;">${resc.goods_name?default('')}</a>(${resc.goodsno?default('')})</p>
											<p>${resc.spec?default('')}</p>
											<p class="pro_linelast"><u class="product_price">${resc.good_price?string.currency}</u><u class="number">${resc.num?default(0)}</u><u class="all_money">${(resc.good_price*resc.num)?string.currency}</u></p>
										</dd>
									</dl>
								</div>
							</div>
							<div class="sublist_right">
							<#if resc.rx_img_url?if_exists != ''>
							    <div class="prescription_pic"><a href="${ui1}${resc.rx_img_url?default('')}" target="_blank"><img class="sit-preview" alt="" src="${ui1}${resc.rx_img_url?default('')}" data-preview-url="${ui1}${resc.rx_img_url?default('')}" style="width:87px;height:78px"/></a></div>
							</#if>
								<div class="down_box" style="cursor:pointer;">
									<span>操作<img src="${base}/web/images/triangle.jpg"/></span>
									<div class="down_list">
										<ul>
										   <#if resc.audit_status == 0 && resc.order_status == 0>
											 <li onclick="auditOrder(${resc.id?default('')})">审核通过</li>
										   </#if>
										   <#if resc.order_status == 9> 
											 <li onclick="revokeReserverOrder(${resc.id?default('')})">撤销</li>
										   </#if>
										   <#if resc.order_status == 0> 
											 <li onclick="cancelShow(${resc.id?default('')})">取消预定</li>
										   </#if>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
    			</#list>
    		</div>
			<div class="all_freight clearfix">
				<b>
				    <span>商品金额：</span>
					<span class="total_box"><strong id="order_heji">¥0.00</strong></span>
					<input id ="order_heji_value" type="hidden" name ="order_heji_value" value="0.00" >
					<span>运费：</span>
					<input class="freight_box" value="0.00" maxlength="9" id="freight" type="text" onchange="jisuanYfPrice()" placeholder=""/>
					<span>应付金额：</span>
					<span class="total_box"><strong id="order_heji_yf">¥0.00</strong></span>
				</b>
			</div>
			<div class="get_order clearfix">
				<input class="btn05" type="button" value="生成订单" id="create_order_id" onclick="createOrder()"/>
			</div>
    		</div>
    	</div>
    </div>
    <div class="cancel_mask">
    	<div class="cancel_order">
	    	<span class="cancel_btn" onclick="cancleHide()">X</span>
	    	<h3 class="cancel_title">取消预定备注</h3>
	    	<div class="clist_box clearfix">
	    		<ul class="cancel_list">
		    		<li><label style="display:block; width:100%; height:100%;" for="radio1"><input id="radio1" type="radio" name="cancleradio" onclick="clearRemark()" value="1"/>审核不通过</label></li>
		    		<li><label style="display:block; width:100%; height:100%;" for="radio2"><input id="radio2" type="radio" name="cancleradio" onclick="clearRemark()" value="客户买错了"/>客户买错了</label></li>
		    		<li><label style="display:block; width:100%; height:100%;" for="radio3"><input id="radio3" type="radio" name="cancleradio" onclick="clearRemark()" value="无法联系上用户"/>无法联系上用户</label></li>
		    		<li><label style="display:block; width:100%; height:100%;" for="radio4"><input id="radio4" type="radio" name="cancleradio" onclick="clearRemark()" value="库存不足"/>库存不足</label></li>
		    		<li class="other_reason"><input class="reason_input" type="text" placeholder="其他取消原因" maxlength="30" name="cancelRemark" id="cancelRemark"/></li>
	    		</ul>
	    	</div>
	    	<p class="cancel_btm clearfix">
	    		<span><input style="color:#000;" class="remove_btn"  style="" type="button" value="取消" onclick="cancleHide()"/><input class="select_ok" type="button" onclick="cancelOrder()" value="确定"/></span>
	    	</p>	    
    	</div>
    </div>
    <input  id="orderIds" type="hidden" name="orderIds"/>
    <input  id="orderId" type="hidden" name="orderId"/>
</body>

<script>
function clearRemark(){
   $("#cancelRemark").val("");
   $("#cancelRemark").attr("readonly","readonly");
}

//返回
function goBack(source){
   if(source=='1'){
     window.location.href="${base}/reserveorder/reserveorder!reserverOrderListByUser.action";
   }else{
     window.location.href="${base}/reserveorder/reserveorder!reserverOrderList.action";
   }
   
}
//计算应付金额
function jisuanYfPrice(){
    var order_heji_value = $("#order_heji_value").val();
    var freight = $("#freight").val();
     $("#order_heji_yf").html(parseFloat(parseFloat(order_heji_value)+parseFloat(freight)).toFixed(2));
}


function getFreight() {
      var checkboxId=""; 
      $('input[name="order_checkbox"]:checked').each(function(){
      	 checkboxId+=$(this).val()+",";  
      });
      $("#orderIds").val(checkboxId);
      if(checkboxId==''){
         $("#freight").val("0.00");
         $("#order_heji").html("¥0.00");
      }else{
        $.ajax({
				url:'${base}/reserveorder/reserveorder!getFreight.action',
				data:{"orderIds":checkboxId},
				type:'post',
				success:function(data){
				   $("#freight").val(data.freight.toFixed(2));
                   $("#order_heji").html("¥"+data.price.toFixed(2));
                   $("#order_heji_yf").html("¥"+data.yf_price.toFixed(2));
                   $("#order_heji_value").val(data.price.toFixed(2));
				}
		   });
      }
}
//撤销订单
function revokeReserverOrder(orderId){
   $.ajax({
		url:'${base}/reserveorder/reserveorder!revokeReserverOrder.action',
		data:{"id":orderId},
		type:'post',
		success:function(data){
		   if(data.flag=='1'){
		      $alert("success","操作成功！");
		      window.location.href="${base}/reserveorder/reserveorder!reserverOrderByUser.action?memberId=${tmember.id?default('')}&source=${source?default('')}";
		   }else{
		      $alert("error",data.message);
		   }
		}
    });
}

//审核 订单
function auditOrder(orderId){
   $.ajax({
		url:'${base}/reserveorder/reserveorder!changeOrderStatus.action',
		data:{"orderId":orderId,"auditStatus":1},
		type:'post',
		success:function(data){
		   if(data.flag=='1'){
		      $alert("success","操作成功！");
		      window.location.href="${base}/reserveorder/reserveorder!reserverOrderByUser.action?memberId=${tmember.id?default('')}&source=${source?default('')}";
		   }else{
		      $alert("error","操作失败！");
		   }
		}
    });
}

$(function(){
   $(".down_box").each(function() {
		var downshow = $(this).find(".down_list");
		var clicknum=0;
		$(this).on("click",function(){
			downshow.css({"display":"block"});
			return false;
		});
		$("body").on("click",function(){
			if(downshow.css({"display":"block"})){
				downshow.css({"display":"none"});
			}
		})
	});
});
  //取消弹出层 显示
 function cancelShow(orderId){
    $("#orderId").val(orderId);
    $("#cancelRemark").val("");
    $("input:radio[name='cancleradio']").attr("checked",false);
    $("#cancelRemark").removeAttr("readonly");
    $(".cancel_mask").show();
 }
 //取消弹出层 关闭
 function cancleHide(){
    $(".cancel_mask").hide();
    $("#orderId").val("");
 }
 //取消预定
function cancelOrder(){
   var cancelValue = $('input:radio:checked').val();
   var cancelRemark="";
   var auditStatus ="";
   if(cancelValue){
      if(cancelValue=='1'){
        auditStatus="2";
        cancelRemark="审核不通过";
      }else{
        cancelRemark=cancelValue;
      }
   }else{
      cancelRemark = $("#cancelRemark").val();
      if($.trim(cancelRemark)==''){
           $alert("warn","请选择或是填写取消原因！");
           return ; 
      }
   }
  var orderId =$("#orderId").val();
  $.ajax({
		url:'${base}/reserveorder/reserveorder!changeOrderStatus.action',
		data:{"orderId":orderId,"auditStatus":auditStatus,"cancelRemark":cancelRemark,"orderStatus":9},
		type:'post',
		success:function(data){
		   if(data.flag=='1'){
		      cancleHide();
		      $alert("success","操作成功！");
		      window.location.href="${base}/reserveorder/reserveorder!reserverOrderByUser.action?memberId=${tmember.id?default('')}&source=${source?default('')}";
		   }else{
		      $alert("error","操作失败！");
		   }
		}
    });
}
//生成订单 
function createOrder(){
  if($("#orderIds").val()==''){
    $alert("warn","请选择预订单!");
    return ;
  }
  var freight =$("#freight").val();
  if($.trim(freight)==''){
     $alert("warn","请填写运费!");
     return ;
  }else{
      var patrn=/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
      if(!patrn.test($.trim(freight))){
        $alert("warn","您填写的运费格式不正确!");
        return ;
      }
  }
 if(confirm("确定要生成订单吗？"))
 {
  $("#create_order_id").val("提交中...");
  $("#create_order_id").removeAttr("onclick");
  $.ajax({
		url:'${base}/reserveorder/reserveorder!createReserveOrder.action',
		data:{"freight":freight,"orderIds":$("#orderIds").val(),"memberId":${tmember.id?default('')}},
		type:'post',
		success:function(data){
		   if(data.resultFlag=='1'){
		      $alert("success","操作成功！");
		      if(data.isover=='1'){
		         window.location.href="${base}/reserveorder/reserveorder!reserverOrderByUser.action?memberId=${tmember.id?default('')}&source=${source?default('')}";
		      }else{
		         goBack(${source?default('')});
		      }
		   }else{
		      $alert("error",data.message);
		      $("#create_order_id").val("生成订单");
		      $("#create_order_id").attr("onclick","createOrder();");
		   }
		},
		error : function() {
		   $alert("error","服务器异常操作失败");
		   $("#create_order_id").val("生成订单");
		   $("#create_order_id").attr("onclick","createOrder();");
		}
    });
 }

} 
 

</script>
</html>
