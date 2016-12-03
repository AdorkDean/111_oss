<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加数据公共模板</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
		<#include "/WEB-INF/pages/inc/taglibs.ftl">
	
<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>


<style>
input{height:35px;width:200px;padding-left:3px}
</style>
</head> 

<body> 
   <form id="formobj" action="" method="post"  >
   
   
        <input type="hidden" value="${id}" name="promid" />
    	
    	
    	<table >			
		   <tr >
		     <td width=""  align="center" >
		           商品名称：<input type="text"  maxlength="20" readOnly="true"  id="sub" />
		      </td>
		      
		        <td width=""  align="center" >
		           海典编号：<input type="text"  maxlength="20"   id="gsn" />
		      </td>
		      
		   </tr>
       </table>
       
    	<table border="1" cellspacing="0" cellpadding="0" style="border-color:rgba(218, 228, 243, 0.44);padding-top:15px" id="myt" >			
		    
		    <tr>
		     <th width="200"  class="tit_new" align="center"　>名称</th>
			 <th width="100" class="tit_new" align="center" >促销价格</th>
			  <th width="100"  class="tit_new" align="center" >限购数量</th>
			 <th width="100"  class="tit_new" align="center" >促销数量</th>
			 <th width="100"  class="tit_new" align="center" >操作</th>
		    </tr>
		   <tr>
		   
		  <#list l?if_exists as l>
		   <tr class="crecord" id="${l.target_id}" >
		     <td width="200"  align="center" >
		      <input type="hidden" value="${l.target_id}" name="goodsid"   />
		     ${l.goods_name?default('')}</td>
			 <td width="100"  align="center" ><input type="text" name="price" maxlength="20" value="${l.price}" /></td>
			 <td width="100"  align="center" ><input type="text" name="limit_count" maxlength="20" value="${l.limit_count}"  /></td>
			 <td width="100"  align="center" ><input type="text" name="sale_count" maxlength="20" value="${l.sale_count}"  /></td>
			 <td width="100"  align="center" onclick="removeRecord(this)">移除 </td>
		   </tr>
		  </#list>
       </table>
       
    
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;margin-left:200px">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_SET_GOODS">
	    	<#if flag=='1'>
	    	<input type="button" class="btn01" value="提交" id="btn100" onclick="addGoods()"  style="margin-left:10px;" />
	    	</#if>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="返回" onclick="back()" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    
    	<table id="ht" style="display:none">
    	<tr class="crecord" id="$id" >
		     <td width="200"  align="center" >
		      <input type="hidden" value="$goodsid" name="goodsid"   />
		     $name</td>
			 <td width="100"  align="center" ><input type="text" name="price" maxlength="20" value="$price" /></td>
			 <td width="100"  align="center" ><input type="text" name="limit_count" maxlength="20" value="1"  /></td>
			 <td width="100"  align="center" ><input type="text" name="sale_count" maxlength="20" value="10"  /></td>
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
		    $("#sub").attr("value",item.value);
		    var ary = String(item.data).split("_");
		    var price = ary[1];
		    var id = ary[0];
		    var flag = true;
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
		    
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:true,
		minChars: 3
});

$("#gsn").autocomplete({	
		url: '${base}/shortbys/shortbuy!getGoodsByHDNO.action',
		showResult: function(value, data) {
			return '<span style="color:blue;font-size:2px">'+value+'</span><hr>';
		},
		onItemSelect: function(item) {
		  $("#sub").attr("value",item.value);
		    var ary = String(item.data).split("_");
		    var goodsno=ary[2];
		    var price = ary[1];
		    var id = ary[0];
		    $("#gsn").val(goodsno);
		    var flag = true;
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
		    
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:false,
		minChars: 0
});
function addRecord(){
	var htm = $('#ht').html();
    $('#myt').append(htm);
}

function removeRecord(obj){
 $(obj).parent().remove();
}

function addGoods(id){

   var vFrmObj = $("#formobj");
   var parentid = $('#parentid').val();
   
   $('#btn100').attr("value","等待");
   
   $('#btn100').attr({disabled:"disabled"}); 
   
   
    jQuery.ajax
   ({
       type: "post",
       url: "${base}/proms/prom!setSingleGoods.action",	  
       data:vFrmObj.serialize(),
       success: function(data)
       {
    	   if(data>0){
    	   		alert("操作成功");
    	   		window.location.href="${base}/proms/prom!list.action";
    	   }else{
    	   		alert("系统忙,请稍候再试！");
    	   }
       }
   }); 
}

function back(){
	window.location.href="${base}/proms/prom!list.action";
}

function getGoodsBySn(){
	
	var gsn = $('#gsn').val();
	var flag = true;
	
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
		   	 	var htm = $('#ht').html();
   				htm = htm.replace("$name",s.goodsName);
   				htm = htm.replace("$price",s.pcPrice);
   				htm = htm.replace("$id",s.id);
   				htm = htm.replace("$goodsid",s.id);
				$('#myt').append(htm);
		   }
    	   }
       }
   }); 
   
}
</script>


</html>