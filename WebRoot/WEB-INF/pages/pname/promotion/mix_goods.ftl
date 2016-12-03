<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>多品</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
		<#include "/WEB-INF/pages/inc/taglibs.ftl">
	
<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />

<#--
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />
-->

<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>


<style>
input{height:35px;width:200px;padding-left:3px}
</style>
</head> 

<body> 
       
   <form id="formobj"  method="post"  >
   
        <input type="hidden" value="${id}" name="promid" />
        <input type="hidden" value="${prom.type}" name="ptype" />
    
    	<table border="1" id="myt" cellspacing="0" cellpadding="0" style="border-color:rgba(218, 228, 243, 0.44);padding-top:15px;width:1000px">			
		 
		   <tr class="crecord" >
		     <td >搜索类型：
		     <select id="searchtype" style="height:37px"><option value="1">促销商品</option><option value="2">赠品</option></select>
		     </td>
		     <td>
		       商品名称关键字：
		     <input type="text" maxlength="20" id="sub" >
		      
		     <input type="hidden" maxlength="20" value="${giftid?default(0)}" id="gift" name="gift" >
		     </td>
		     
		        <td  align="center" >
		           海典编号：<input type="text"  maxlength="20"   id="gsn" />
		           <input type="button" value="确定" style="width:50px;height:35px" onclick="getGoodsBySn()"　>
		      </td>
		      
		      
		   </tr>   
		   
		   
		   
		  <#list l?if_exists as l>
		   <tr class="crecord" id="$id" style="height:40px">
		     <td width="200"  align="left" colspan="2" style="padding-left:15px" >
			     <input type="hidden" value="${l.target_id?default('')}" name="goodsid"   />
			     ${l.goods_name?default('')}
		     </td>
			 <td  align="center" onclick="removeRecord(this)">移除 </td>
		   </tr>                                                                                 
		  </#list>
		  
		  <#if giftname?exists >
		   <tr class="crecord" id="$id" style="height:40px" name="gift-record">
		     <td width="200"  align="left" colspan="2" style="padding-left:15px" >
			     <input type="hidden" value="${giftid?default(0)}"   />
			    <font color="red"> [赠品]</font>${giftname?default('')}
		     </td>
			 <td  align="center" onclick="removeRecord(this)">移除 </td>
		   </tr>        
		  </#if>
		  
       </table>
       
       <p/>
   	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_SET_GOODS">
	    	<#if flag=='1'>
	    	<input type="button" class="btn01" value="提交" onclick="addGoods()" id="btn100"  style="margin-left:10px;" />
	    	</#if>
	    	</@security.authorize>
	    	
	    	<input type="button" class="btn01" value="返回" onclick="back()" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    
	<table id="ht" style="display:none"  >
	    <tr class="crecord" id="$id" style="height:40px"  name="recordflag">
	     <td width="200"  align="left" colspan="2"  style="padding-left:15px"  ><input type="hidden" value="$goodsid" name="goodsid"   />$name</td>
		 <td width="100"  align="center" onclick="removeRecord(this)">移除 </td>
	   </tr>
	</table>
	
    	
</body>


 <script type="text/javascript">
$("#sub").autocomplete({	
		url: '${base}/proms/prom!like2.action',
		showResult: function(value, data) {
			return '<span style="color:blue;font-size:2px">'+value+'</span><hr>';
		},
		onItemSelect: function(item) {
		    var searchtype = $('#searchtype').find("option:selected").val();
		    $("#sub").attr("value",item.value);
			var ary = String(item.data).split("_");
			var price = ary[1];
			var id = ary[0];
			var flag = true;
		    if(searchtype==1)	// 设置促销商品
		    {
			    $(".crecord").each(function(){
			    	if(this.id==id){
			    		alert("["+item.value+"]已经添加过");
			    		flag = false;
			    	}
			    });
			   if(flag)
			   {
			   	 	var htm = $('#ht').html();
	   				htm = htm.replace("$name",item.value);
	   				htm = htm.replace("$price",price);
	   				htm = htm.replace("$id",id);
	   				htm = htm.replace("$goodsid",id);
					$('#myt').append(htm);
			   }
		    }else if(searchtype==2){
		    
		    
		    	// $('#gift_span').text("赠品："+item.value);
		    	$('#gift').val(id);
		    	
		    	$("tr[name='gift-record']").remove();
		    	var htm = $('#ht').html();
   				htm = htm.replace("$name","<font color='red'>[赠品]</font>"+item.value);
   				htm = htm.replace("$price",price);
   				htm = htm.replace("recordflag","gift-record");
   				htm = htm.replace("$id",id);
   				htm = htm.replace("$goodsid",id);
				$('#myt').append(htm);
					
					
		    }
		    
		    
		    
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:true,
		minChars: 3
});

function addRecord(){
	var htm = $('#ht').html();
    $('#myt').append(htm);
}

function removeRecord(obj){
 $(obj).parent().remove();
}

function addGoods(){

     var child = $('#myt').find("tr");
     if(child.size()==1){
     	alert("请添加要促销的商品");
     	return;
     }
     
     $('#btn100').attr("value","提交中..");
     $('#btn100').attr({disabled:"disabled"});
  
   var vFrmObj = $("#formobj");
   var parentid = $('#promid').val();
    jQuery.ajax
   ({
       type: "post",
       url: "${base}/proms/prom!setMixGoods.action",	  
       data:vFrmObj.serialize(),
       success: function(data)
       {
    	   if(data>0){
    	   		alert("操作成功");
    	   		back();
    	   }else{
    	   		alert("系统忙,请稍候再试！");
    	   }
       }
   }); 
}

function goodsIsExist(){
	var bool = false;
    var gift = $("input[name='gift']").val();
    if($.trim(gift)==""){
    	return false;
    }
   
    var patrn=/^[0-9]{1,20}$/; 
	if(!patrn.exec(gift)){
		alert("请输入数字ID");
		return true;
	}
    
	jQuery.ajax
   ({
       type: "post",
       url: "${base}/proms/prom!giftIsExist.action",	  
       data:{"id":gift},
       async:true,
       success: function(data)
       {
    	  if(data>0){
    	  	alert("您所要赠送的商品不存在，请核实ID");
    	  	$("input[name='gift']").val("");
    	  	bool = true;
    	  }
       }
   }); 
   
   return bool;
}

function back(){
	window.location.href="${base}/proms/prom!list.action";
}


function getGoodsBySn(){
	
	var gsn = $('#gsn').val();
	if($.trim(gsn)==""){
		alert("商品编号不存在");
		return;
	}
	var flag = true;
	   var searchtype = $('#searchtype').find("option:selected").val();
	
	 jQuery.ajax
   ({
       type: "post",
       url: "${base}/proms/prom!getGoodsBySN.action",	  
       data:{'gsn':gsn},
       success: function(data)
       {
    	   var s=jQuery.parseJSON(data);
    	   if(s.key==0){
    	   		alert("商品"+gsn+"不存在");
    	   		return;
    	   }else{
    	   	$(".crecord").each(function(){
		    	if(s.id==this.id){
		    		alert("["+gsn+"]已经添加过");
		    		flag = false;
		    	}
		    });
		   if(flag)
		   {
		    	
		    	if(searchtype==2){
			    	$("tr[name='gift-record']").remove();
			    	$('#gift').val(s.id);
			    	var htm = $('#ht').html();
	   				htm = htm.replace("$name","<font color='red'>[赠品]</font>"+s.goodsName);
	   				htm = htm.replace("$price",s.pcPrice);
	   				htm = htm.replace("recordflag","gift-record");
	   				htm = htm.replace("$id",s.id);
	   				htm = htm.replace("$goodsid",s.id);
					$('#myt').append(htm);
				
		    	}else if(searchtype==1){
		    		var htm = $('#ht').html();
	   				htm = htm.replace("$name",s.goodsName);
	   				htm = htm.replace("$price",s.pcPrice);
	   				htm = htm.replace("$id",s.id);
	   				htm = htm.replace("$goodsid",s.id);
					$('#myt').append(htm);
		    	}
		    	
				
		   }
		   
    	  }
       }
   }); 
   
}


</script>


</html>