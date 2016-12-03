<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加优惠券</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
	
	<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<link rel="stylesheet" href="${base}/web/location_tree/jquery.treeview.css" />
<link rel="stylesheet" href="${base}/web/location_tree/red-treeview.css" />
<link rel="stylesheet" href="${base}/web/location_tree/screen.css" />
<script src="${base}/web/location_tree/lib/jquery.js" type="text/javascript"></script>
<script src="${base}/web/location_tree/jquery.treeview.js" type="text/javascript"></script>


</head> 

<body> 

<div class="shop_main" id="shop_main"> 
    <form action="${base}/card/coupon!addCoupon.action" method="post" name="addInfo" id="addInfo">
    
    <input type="hidden" name="goodsid" class="i-text-i" id="goodsid" value="" maxlength="12"/>
    <input type="hidden" name="hcategory" class="i-text-i" id="hcategory" value="" maxlength="12"/>
    <input type="hidden" name="brandid" class="i-text-i" id="brandid" value="" maxlength="12"/>
        
        <font color="red">  红字为必填项，请仔细填写</font>
    	<div class="m-contents">
	    	<div class="labelnamese"><font color="red">优惠券名称：</font></div>
	    	<input type="text" name="coupon.name" class="i-text-i" id="name" value="" maxlength="12"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">前缀：</font></div>
	    	<input type="text" name="coupon.prefix" class="i-text-i" id="prefix" value="" maxlength="6"/>
    	</div>
 
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">位数：</font></div>
	    	<input type="text" name="coupon.codeLength" class="i-text-i" id="codeLength" value="" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">数字：</div>
	    	<input type="radio" name="coupon.rule" class="i-text-cb" id="" value="0"  checked="checked"/>
	    	<div class="labelnamese">数字加英文：</div>
	    	<input type="radio" name="coupon.rule" class="i-text-cb" id="" value="1"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">张数：</font></div>
	    	<input type="text" name="coupon.quantity" class="i-text-i" id="quantity" value="" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	 
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">开始时间：</font></div>
	    	<input type="text" name="coupon.startTime" class="i-text-i" id="startTime" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2050-10-01\'}'})"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">结束时间：</font></div>
	    	<input type="text" name="coupon.endTime" class="i-text-i" id="endTime" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2050-10-01'})"/>
    	</div>
  
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">最高使用金额：</font></div>
	    	<input type="text" name="coupon.endPrice" class="i-text-i" id="endPrice" value="" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">最低使用金额：</font></div>
	    	<input type="text" name="coupon.startPrice" class="i-text-i" id="startPrice" value="" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">满减金额：</font></div>
	    	<input type="text" name="coupon.disPrice" class="i-text-i" id="disPrice" value="" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">是否启用：</div>
	    	<input type="checkbox"  class="i-text-cb" id="status"  checked ='checked'/>
	    	<input type="hidden" name="coupon.status" class="i-text-i" id="qy" value="" maxlength="10"/>
	    	<div class="labelnamese">是否允许积分兑换：</div>
	    	<input type="checkbox" name="" class="i-text-cb" id="isExchange" />
	    	<input type="hidden" name="coupon.isExchange" class="i-text-i" id="jf" value="" maxlength="10"/>
	    	<div class="labelnamese">是否重复使用：</div>
	    	<input type="checkbox" name="" class="i-text-cb" id="isRepeat" />
	    	<input type="hidden" name="coupon.isRepeat" class="i-text-i" id="cf" value="" maxlength="10"/>
    	</div>
    	
        <div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">积分兑换数：</div>
	    	<input type="text" name="coupon.exchangeCount" class="i-text-i" id="" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">是否延期：</div>
	    	<input type="checkbox"  class="i-text-cb" id="isDelay"  />
	    	<input type="hidden" name="coupon.isDelay" class="i-text-i" id="yq"  maxlength="10" value="0"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">延期天数：</div>
	    	<input type="text" name="coupon.delayDay" class="i-text-i" id="delayDay" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="4"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">介绍：</div>
	    	<textarea class="taa-ui" name="coupon.instro"></textarea>
    	</div>
    	
       <div class="m-contents" style="margin-top:10px;">
            <div class="labelnamese">使用范围：</div>
	    	<div class="labelnamese" style="margin-left:0px;width:50px;">全场：</div>
	    	<input type="radio" style="margin-right:10px; margin-left:0px;" name="coupon.scope" class="i-text-cb" id="" value="0"  checked="checked" onclick="scope(0)"/>
	    	<div class="labelnamese" style="margin-left:10px;width:50px;">免运费：</div>
	    	<input type="radio" name="coupon.scope" class="i-text-cb" id="" value="1" style="margin-left:0px;" onclick="scope(1)"/>
	    	<div class="labelnamese"  style="margin-left:0px;width:50px;">品牌：</div>
	    	<input type="radio" name="coupon.scope" class="i-text-cb" id="" value="3" style="margin-left:0px;" onclick="scope(3)"/>
	    	<div class="labelnamese"  style="margin-left:0px;width:50px;">商品：</div>
	    	<input type="radio" name="coupon.scope" class="i-text-cb" id="" value="4" style="margin-left:0px;" onclick="scope(4)"/>
	    	<div class="labelnamese"  style="margin-left:0px;width:50px;">分类：</div>
	    	<input type="radio" name="coupon.scope" class="i-text-cb" id="" value="2" style="margin-left:0px;" onclick="scope(2)"/>
	    	
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;display:none" id="pp">
	    	<div class="labelnamese"><font color="red">关联品牌：</font></div>
	    	<input type="text" name="endPrice" class="i-text-i" id="glpp" value="" maxlength="10" autocomplete="off" />
    	</div> 
    	
    	<div class="m-contents" style="margin-top:10px;display:none" id="sp">
    		<div class="labelnamese"><font color="red">海典编号：</font></div>
	    	<input type="text" class="i-text-i" id="hdbh" value="" maxlength="20" autocomplete="off"/>
	    	<div class="labelnamese"><font color="red">关联商品：</font></div>
	    	<input type="text" name="endPrice" class="i-text-i" id="glsp" value="" readOnly="true" maxlength="10" autocomplete="off"/>
    	</div>   	
    	

		<div id="sidetree" style="float:left;width:100%;display:none;">
		<div class="labelnamese"><font color="red">关联商品分类：</font></div>
		<div  style="float:left;margin-left:10px;margin-top:10px;">
		<div id="sidetreecontrol"><a href="?#">合并全部</a> | <a href="?#">展开分部</a></div>
		<ul id="tree">
		<#list list1?if_exists as l1>
			<li><span><strong><input type="checkbox" value="${l1.id}" id="${l1.id}" name="${l1.id}" onclick="clickCity(${l1.id})"  />${l1.categoryName}</strong></span>
				<ul>
				  <#list list2?if_exists as l2><#if l1.id==l2.parentId>
				 <span><strong><li><input type="checkbox" name="${l1.id} ${l2.id}"  id="${l2.id}" onclick="clickCity(${l2.id})" value="${l2.id}"   />${l2.categoryName}
				    
				<ul>
				  <#list list3?if_exists as l3><#if l2.id==l3.parentId>
				  <li><input type="checkbox" name="${l1.id} ${l2.id} ${l3.id}" value="${l3.id}" id="${l1.id}_${l2.id}_${l3.id}" onclick="clickCity(${l3.id})"  />${l3.categoryName}</li>
				  </#if></#list>
				</ul>
				
				    </li></strong></span>
				  
				  </#if></#list>
				</ul>
			</li>
		</#list>
		</ul>
		</div>
		</div>
		</div>

    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_COUPON_TIANJIA_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="subForm();" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="刷新" onclick="javascript:history.go(0);" style="margin-left:10px;"/>
	    	<a href="${base}/card/coupon!list.action"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    		
    </form>
    </div>   
    
<#--------------------------------------->
	
</body>
<script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>

<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>
<script>

function clickCity(v){
     var ys = $('#'+v).is(':checked');
 	// $('input[name~='+v+']').attr("checked",false);
 	
 	
 	$('#hcategory').val(v);
 	
	jQuery("input[type=checkbox]:checked").each(function(){
	      if($(this).val()!=v){
	        $(this).attr("checked",false);
	      }
	 });
	  
  
}
	$(function() {
		$("#tree").treeview({
			collapsed: true,
			animated: "medium",
			control:"#sidetreecontrol",
			persist: "location"
		});
	})
	
$("#hdbh").autocomplete({	
		url: '${base}/shortbys/shortbuy!getGoodsByHDNO.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
		},
		onItemSelect: function(item) {
			$("#glsp").attr("value",item.value);
			var ary = String(item.data).split("_");
		    var goodsno=ary[2];
		    var price = ary[1];
		    var id = ary[0];
		   $("#goodsid").attr("value",id);
		   $("#hdbh").attr("value",goodsno)
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:false,
		minChars: 1
});

$("#glpp").autocomplete({	
		url: '${base}/brand/brand!like.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
		},
		onItemSelect: function(item) {
		   $("#brandid").attr("value",item.data);
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:true,
		minChars: 1
});

function closeceng(){
	window.close();
}

function scope(type){
    if(type==0 || type==1){
       $('#sidetree').hide();
       $('#pp').hide();
       $('#sp').hide(); 
       
       $('#goodsid').val('');
       $('#hcategory').val('');
       $('#brandid').val('');
       $('#glpp').val('');
       $('#glsp').val('');   
       
       	jQuery("input[type=checkbox]").each(function(){
	        $(this).attr("checked",false);
	    });    
            
    }else if(type==2){
       $('#sidetree').show();
       $('#pp').hide();
       $('#sp').hide();
    }else if(type==3){
       $('#sidetree').hide();
       $('#pp').show();
       $('#sp').hide();   
    }else if(type==4){
       $('#sidetree').hide();
       $('#pp').hide();
       $('#sp').show();   
    }
}

function subForm(){
   var name = $("#name").val();
   if($.trim(name)==""){
       $alert('warn','优惠券名称不能为空');
      return false;
   }
   
   var prefix = $("#prefix").val();
   if($.trim(prefix)==""){
   $alert('warn','前缀不能为空');
      return false;
   }
   var codeLength = $("#codeLength").val();
   if($.trim(codeLength)==""){
   $alert('warn','位数不能为空');
      return false;
   }
   var quantity = $("#quantity").val();
   if($.trim(quantity)==""){
   $alert('warn','张数不能为空');
      return false;
   }
   var startTime = $("#startTime").val();
   if($.trim(startTime)==""){
   $alert('warn','开始时间不能为空');
      return false;
   }
   var endTime = $("#endTime").val();
   if($.trim(endTime)==""){
   $alert('warn','结束时间不能为空');
      return false;
   }
   var endPrice = $("#endPrice").val();
   if($.trim(endPrice)==""){
   $alert('warn','最高使用金额不能为空');
      return false;
   }
   var startPrice = $("#startPrice").val();
   if($.trim(startPrice)==""){
   $alert('warn','最低使用金额不能为空');
      return false;
   }
   var disPrice = $("#disPrice").val();
   if($.trim(disPrice)==""){
   $alert('warn','满减金额不能为空');
      return false;
   }

    if($("#status").attr("checked")){
        $("#qy").val("1");
    }else{
    	$("#qy").val("0");
    }
    
    if($("#isExchange").attr("checked")){
    	$("#jf").val("1");
    }else{
    	$("#jf").val("0");
    }
    
   if($("#isDelay").attr("checked")){
    	$("#yq").val("1");
    	if($.trim($('#delayDay').val())==""){
    	   $alert('warn','请填写延期天数');
      	   return false;
    	}
    }else{
    	$("#yq").val("0");
    }
    
   if($("#isRepeat").attr("checked")){
        $("#cf").val("1");
    }else{
    	$("#cf").val("0");
    }
    
    $('#addInfo').submit();
}
</script>
</html>