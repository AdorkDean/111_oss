<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>手动下单</title> 
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>
<style>
input{height:28px;width:180px;padding-left:3px; border: 1px solid #cbcbcb;}
.btn02 {
    background: rgba(0, 0, 0, 0) url("../frame/images/16.jpg") repeat scroll 0 0;
    color: #fff;
    cursor: pointer;
    height: 25px;
    letter-spacing: 5px;
    margin-left: 5px;
    margin-right: 5px;
}
</style>
</head> 
 <body> 
     <form action="" method="post" name="form1" id="form1">
      <input name="order.memberId" readonly="readonly" id="memberid" type="hidden"  />
      <input name="receiverId" readonly="readonly" id="receiverId"   type="hidden"/>
       <input name="goodsidsS" readonly="readonly" id="goodsidsS"    type="hidden" />
      <input name="goodsSumsS" readonly="readonly" id="goodsSumsS"  type="hidden" />
        <table border="1" cellspacing="0" cellpadding="0" width="80%" style="border-color:rgba(218, 228, 243, 0.44);"> 
          <tr style="height:40px"> 
            <td align="center" style="width:20%"><span style="color: #2b2b2b;font-weight: bold;"><font color="red">*</font>会员信息</span></td>
			<td colspan="3" style="width:80%"></td>
          </tr>
          <tr style="height:40px"> 
           <td align="center" ><font color="red">*</font>用户名:</td> 
           <td><input name="username" readonly="readonly" id="username"   />
               <input name="usernameSelect" type="Button"  onclick="addmember()" class="btn02" style="width: 79px;" value="选择会员"/>
            </td> 
           <td align="center" >姓名:</td> 
           <td><input  name="realName" readonly="readonly" redon id="realName"    /></td> 
          </tr> 
          <tr style="height:40px"> 
           <td align="center" >电话:</td> 
           <td><input name="mobile" readonly="readonly" id="mobile"  /></td> 
           <td></td> 
           <td></td> 
          </tr> 
          <tr style="height:40px"> 
            <td align="center" ><span style="color: #2b2b2b;font-weight: bold;"><font color="red">*</font>收货地址</span></td>
			<td colspan="4"><input name="receiverAdd" type="Button"  onclick="addreceiver()" class="btn02" style="width: 79px;" value="添加地址 "/></td>
          </tr>
           <tr style="height:40px"> 
            <td align="center">收货人</td>
			<td align="center">联系电话</td>
			<td align="center" colspan="2">地址</td>
          </tr>
         <tbody id="receiverTbody">
           
         </tbody> 
          <tr style="height:40px"> 
            <td align="center" ><font color="red">*</font><span style="color: #2b2b2b;font-weight: bold;">支付方式</span></td>
			<td colspan="3"></td>
          </tr>
          <tr style="height:40px"> 
           <td align="center" ><font color="red">*</font>支付方式:</td> 
           <td>
             <select name="order.paymentId" id="paymentId" style="height:35px;width:200px;">
		      	<option value="">请选择</option>
		      	<#list paymentWayList as payment>
		      	  <option value="${payment.id?default('')}">${payment.name?default('')}</option>
		      	</#list>
		      </select>
           </td> 
           <td align="center" ><font color="red">*</font>配送方式:</td> 
           <td>
             <select name="order.deliveryId" id="deliveryId" onchange="orderPriceJisuan()" style="height:35px;width:200px;">
		      	<option value="">请选择</option>
		      	<#list deliveryWayList as delivery>
		      	  <option value="${delivery.id?default('')}">${delivery.name?default('')}</option>
		      	</#list>
		      </select>
           </td> 
          </tr> 
           <tr style="height:40px"> 
            <td align="center" ><span style="color: #2b2b2b;font-weight: bold;">发票信息</span></td>
			<td colspan="3">
			</td>
			</tr>
			<td align="center" >发票内容</td>
			<td>
			  <select name="order.invoiceType" id="invoiceType" style="height:35px;width:200px;">
		      	<option value="0">不开发票</option>
		      	<option value="1">明细</option>
		      	<option value="2">药品</option>
		      	<option value="3">保健品</option>
		      	<option value="4">医疗器械</option>
		      </select>
			</td>
			<td align="center" >发票抬头</td>
			<td>
			  <input type="text" id="invoiceTitle" name="order.invoiceTitle"  value="" maxlength="200"/>
			</td>
			</tr>
          <tr style="height:40px"> 
            <td align="center" ><span style="color: #2b2b2b;font-weight: bold;"><font color="red">*</font>订单结算信息</span></td>
			<td colspan="3">
			</td>
          </tr>
          <tr style="height:40px"> 
            <td align="center" ><font color="red">*</font>运费</td>
			<td><input name="order.shippingFee"  id="shippingFee" onchange="updatePriceJisuan()"   value="0.00" />
			</td>
			<td align="center" ><font color="red">*</font>优惠折扣金额</td>
			<td><input name="order.promotionalDiscount" readonly="readonly" id="promotionalDiscount"  value="0.00" />
			</td>
          </tr>
            <tr style="height:40px"> 
            <td align="center" ><font color="red">*</font>订单金额</td>
			<td><input name="order.orderAmount" readonly="readonly" id="orderAmount"   value="0.00" />
			</td>
			<td align="center" ><font color="red">*</font>应付金额</td>
			<td><input name="order.payableAmount" readonly="readonly" id="payableAmount"   value="0.00" />
			</td>
          </tr>
        </table>
        <table  border="1" cellspacing="0" width="80%" cellpadding="0" style="border-color:rgba(218, 228, 243, 0.44);"> 
          <tr style="height:40px"> 
            <td align="center" width="20%"><span style="color: #2b2b2b;font-weight: bold;"><font color="red">*</font>商品信息</span></td>
			<td colspan="5" width="80%"></td>
          </tr>
          <tr style="height:40px">
             <td align="center" width="20%">商品名称:</span></td>
             <td colspan="5" id="sbtd" width="80%">
				<input type="text" id="goodsName" name="goodsName"  />
				<input type="hidden" id="goodsid" name="goodsid"  />
				<input type="button" id="addOrderItem" onclick="getGoods()"  class="btn02" style="width: 79px;" value="添加商品" />
			</td>
          </tr>
           <tr style="height:40px"> 
            <td align="center" width="15%">商品编号</td>
			<td align="center" width="20%">商品名称</td>
			<td align="center" width="15%">商品价格</td>
			<td align="center" width="15%">购买数量</td>
			<td align="center" width="20%">小计</td>
			<td align="center" width="15%">操作</td>
          </tr>
         <tbody id="goodsbody">
         
         </tbody> 
         <tr style="height:40px" >
             <td colspan="6" width="80%" align="center">
				<input name="receiverAdd" type="Button"  onclick="saveOrder()" class="btn02" style="width: 50px;" value="提交 "/>
			</td>
          </tr>
        </table>
         </form>
     <!--模板基础参数结束-->
 </body>
<script>	
    //选择用户
	function addmember(){
	  window.open ("${base}/order/order!orderMemberList.action", 'newwindow', "width=900px,height=600px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
	}
	//根据用户id 查询收获地址
	function memberReceiver(memberId){
		$.ajax({
			url:'${base}/order/order!getMemberReceiver.action',
			data:{"memberId":memberId},
			type:'post',
			success:function(data){
			    var trhtml="";
				for(var i=0;i<data.length;i++){
				  trhtml+="<tr><td><input type=\"radio\" name=\"receiverid\" style=\"width:30px;height:15px;\" value=\""+data[i].id+"\" onclick=\"xuanzeReceiver()\"/>&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].receiver+"</td><td align=\"center\">"+data[i].mobile+"</td><td colspan=\"2\">"+data[i].area+data[i].location_address+data[i].address+" </td></tr>";
				}
				$("#receiverTbody").html(trhtml);
			}
		});
	}
	
	//添加收货地址
	function addreceiver(){
	 var memberid = $("#memberid").val();
	 if(memberid!=''){
	  window.open ("${base}/order/order!addReceiver.action?memberId="+memberid, 'newwindow_addReceiver', "width=800px,top=300, left=400,height=400px,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
	 }else{
	    $alert("warn","请选择会员！");
	 }
	}
	//判断是否开具发票
	function ifInvoiceCheckbox(){
	   if($("#ifInvoice").attr("checked")){
	        $('#invoiceTitle').attr("disabled",false);
	   }else{
	         $('#invoiceTitle').attr("disabled",true);
	   }
	}
	
	//联想商品名称
	$("#goodsName").autocomplete({	
		url: '${base}/proms/prom!like.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
		},
		onItemSelect: function(item) {
		  $("#goodsid").val(item.data);
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:true,
		minChars: 1
     });
	//获取商品信息
	function getGoods(){
	var goodsid =$("#goodsid").val();
	   if(goodsid!=''){
	     if(checkGoodsId(goodsid)){
	      $.ajax({
			url:'${base}/order/order!getGoodsById.action',
			data:{"goodsid":goodsid},
			type:'post',
			success:function(data){
			    data = eval('(' + data + ')');
			    var trhtml="<tr id=\"goodsTr"+data.good.id+"\" style=\"height:40px\"><td align=\"center\"><input name=\"goodsids\"  value=\""+data.good.id+"\" type=\"hidden\"/>"+data.good.goodsno+"</td><td align=\"center\">"+data.good.goodsName+"</td><td align=\"right\">"+data.goodprice.price+"</td><td><input name=\"goodsSums\"  value=\"1\" class=\"input input_v1\" style=\"width:50px;\" onchange=\"orderPriceJisuan()\"/></td><td align=\"right\"><span id=\"goodsSumPricepan"+data.good.id+"\">"+data.goodprice.price+"<\span></td><td align=\"center\"><a href=\"javascript:deleteTr("+data.good.id+")\">删除</a></td></tr>";
				$("#goodsbody").append(trhtml);
				orderPriceJisuan();
			}
		});
		}
	   }
	}
	//判断商品id
	function checkGoodsId(goodsid){
	   var goodsids = document.getElementsByName("goodsids");
	   if(goodsids.length>0){
	      for(var i=0;i<goodsids.length;i++){
	         if(goodsids[i].value==goodsid){
	            return false;
	         }
	      }
	   }
	   return true;
	}
	//删除商品行
	function deleteTr(goodsid){
	   $("#goodsTr"+goodsid).remove(); 
	   orderPriceJisuan();
	}
	//验证商品数量
	function validateGoodSum(){
	    var re =/^\+?[1-9][0-9]*$/;
       	var goodsSumsobj = document.getElementsByName("goodsSums");   
        for(var i=0;i<goodsSumsobj.length;i++){
	       if(goodsSumsobj[i].value==''){
	          $alert("warn","商品数量不能为空！");
	           return false;
	       }else{
	         if(!re.test(goodsSumsobj[i].value)){
	           $alert("warn","商品数量只能为大于0的整数！");
	           return false;
	         }
	       }
	    }
	    return true;
	}
	
	
	
	//校验运费金额
	function checkShippingFee(){
		var re = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
	    var shippingFee =$.trim($("#shippingFee").val());
	    if(shippingFee==''){
	       $alert("warn","运费不能为空！");
	       return false;
	    }else{
	       if(!re.test(shippingFee)){
	         $alert("warn","运费格式不正确！");
	         return false;
	       }
	    }
	    return true;
	}
	
	
	//计算订单相关金额
	function updatePriceJisuan(){
	    if(!validateGoodSum()){
	       return ;
	    }
	    var goodsSumsobj = document.getElementsByName("goodsSums");
	    var goodsidsobj = document.getElementsByName("goodsids");
	    var deliveryId = $("#deliveryId").val();
	    var goodsSums="";
	    var goodsids="";
	    if(goodsidsobj.length>0){
		    for(var i=0;i<goodsidsobj.length;i++){
		       goodsSums =goodsSums+goodsSumsobj[i].value+",";
		       goodsids =goodsids +goodsidsobj[i].value+",";
		    }
		    $("#goodsidsS").val(goodsids);
		    $("#goodsSumsS").val(goodsSums);
	        $.ajax({
				url:'${base}/order/order!orderGoodsPrice.action',
				data:{"goodsids":goodsids,"goodsSums":goodsSums,"deliveryId":deliveryId},
				type:'post',
				success:function(data){
				    data = eval('(' + data + ')');
				    if(checkShippingFee()){
				        var shippingFee =$("#shippingFee").val();
		       			$("#promotionalDiscount").val(parseFloat(data.promotionalDiscount).toFixed(2));
		       			$("#orderAmount").val(parseFloat(parseFloat(data.orderAmount)+parseFloat(shippingFee)-parseFloat(data.shippingFee)).toFixed(2));
		       			$("#payableAmount").val(parseFloat(parseFloat(data.orderAmount)+parseFloat(shippingFee)-parseFloat(data.shippingFee)).toFixed(2));
		       			if(data.goodMapList.length>0){
		       			 for(var i=0;i<data.goodMapList.length;i++){
						  $("#goodsSumPricepan"+data.goodMapList[i].goodid).html(data.goodMapList[i].goodSumPrice);
						 }
	       			   }
				    }
				}
		   });
	    }else{
	       $("#shippingFee").val("0.00");
	       $("#promotionalDiscount").val("0.00");
	       $("#orderAmount").val("0.00");
	       $("#payableAmount").val("0.00");
	    }
	}
	
	
	//计算订单相关金额
	function orderPriceJisuan(){
	    if(!validateGoodSum()){
	       return ;
	    }
	    var goodsSumsobj = document.getElementsByName("goodsSums");
	    var goodsidsobj = document.getElementsByName("goodsids");
	    var deliveryId = $("#deliveryId").val();
	    var goodsSums="";
	    var goodsids="";
	    if(goodsidsobj.length>0){
		    for(var i=0;i<goodsidsobj.length;i++){
		       goodsSums =goodsSums+goodsSumsobj[i].value+",";
		       goodsids =goodsids +goodsidsobj[i].value+",";
		    }
		    $("#goodsidsS").val(goodsids);
		    $("#goodsSumsS").val(goodsSums);
	        $.ajax({
				url:'${base}/order/order!orderGoodsPrice.action',
				data:{"goodsids":goodsids,"goodsSums":goodsSums,"deliveryId":deliveryId},
				type:'post',
				success:function(data){
				    data = eval('(' + data + ')');
				    $("#shippingFee").val(parseFloat(data.shippingFee).toFixed(2));
	       			$("#promotionalDiscount").val(parseFloat(data.promotionalDiscount).toFixed(2));
	       			$("#orderAmount").val(parseFloat(data.orderAmount).toFixed(2));
	       			$("#payableAmount").val(parseFloat(data.payableAmount).toFixed(2));
	       			if(data.goodMapList.length>0){
	       			 for(var i=0;i<data.goodMapList.length;i++){
					  $("#goodsSumPricepan"+data.goodMapList[i].goodid).html(data.goodMapList[i].goodSumPrice);
					 }
	       			}
				}
		   });
	    }else{
	       $("#shippingFee").val("0.00");
	       $("#promotionalDiscount").val("0.00");
	       $("#orderAmount").val("0.00");
	       $("#payableAmount").val("0.00");
	    }
	}
	//选择收货地址	
	function xuanzeReceiver(){
	   var val_payPlatform = $('input[name="receiverid"]:checked ').val(); 
	   $("#receiverId").val(val_payPlatform);
	}
	//表单验证
	function validateForm(){
	   //验证用户
	   var memberid = $("#memberid").val();
	   if(memberid==''){
	       $alert("warn","请添加会员信息！");
	       return false;
	   }
	   //验证收获地址信息
	   var receiverId =$("#receiverId").val();
	   if(receiverId==''){
	       $alert("warn","请添加收获地址信息！");
	       return false;
	   }
	   //验证支付方式
	   var paymentId =$("#paymentId").val();
	   if(paymentId==''){
	       $alert("warn","请选支付方式！");
	       return false;
	   }
	   //验证配送方式
	   var deliveryId =$("#deliveryId").val();
	   if(deliveryId==''){
	       $alert("warn","请选择配送方式！");
	       return false;
	   }
	   //验证添加商品
	   var goodsidsS= $("#goodsidsS").val();
	   if(goodsidsS==''){
	       $alert("warn","请添加商品信息！");
	       return false;
	   }
	   var re =/^\+?[1-9][0-9]*$/;
       	var goodsSumsobj = document.getElementsByName("goodsSums");   
        for(var i=0;i<goodsSumsobj.length;i++){
	       if(goodsSumsobj[i].value==''){
	          $alert("warn","商品数量不能为空！");
	           return false;
	       }else{
	         if(!re.test(goodsSumsobj[i].value)){
	           $alert("warn","商品数量只能为大于0的整数！");
	           return false;
	         }
	       }
	    }
	  return  true;
	}
	//提交订单
	function saveOrder(){
	   var formInfo=$("#form1").serialize();
	   if(!validateForm()){
	      return ;
	   }
	   if(!checkShippingFee()){
	      return ;
	   }
		$.ajax({
			url:"${base}/order/order!saveOrder.action",
			type:"post",
			data:formInfo,
			success:function(data){
				if(data!='0'){ //'1'更新,'2'修改
					$alert("success","订单提交成功！");
					window.location.href="${base}/order/order!getOrderList.action";
				}else if(data=='0'){
					$alert("error","操作失败，请稍后再试！");
				}else{
				    $alert("error","订单提交成功！");
				}
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
	}
</script>
</html>