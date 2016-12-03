<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加数据公共模板</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	
<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>


<style>

.myinput{
  width: 90px;
  height: 30px;
  line-height: 30px;
  border: #ccc 1px solid;
  background-color: #f5f5f5;
  margin-left: 10px;
  padding-left:5px
  }
</style>
</head> 

<body> 
   <form id="formobj" action="${base}/shortbys/shortbuy!set.action" method="post"  >
        <input type="hidden" value="${id}" name="parentid" />
    	<div class="m-contents">
	    	<div class="labelnamese">商品名称：</div>
	    	<input type="text" readOnly="true"  maxlength="20" value="${shortBuyBean.name?default('')}" class="i-text-i" id="sub" />
	    	<div class="labelnamese">海典编号：</div>
	    	<input type="text"  maxlength="20" value="" class="i-text-i" id="haidian" />
    	</div>
    	
    	
    	<table border="1" cellspacing="0" cellpadding="0" style="border-color:rgba(218, 228, 243, 0.44);padding-top:15px" id="myt" >			
		    <tr>
		     <th width="200"  class="tit_new" align="center"　>名称</th>
			 <th width="100" class="tit_new" align="center" >价格</th>
			  <th width="100"  class="tit_new" align="center" >购买数量</th>
			 <th width="100"  class="tit_new" align="center" >总数量</th>
			 <th width="100"  class="tit_new" align="center" >操作</th>
		    </tr>
		  <#list list?if_exists as l>
		   <tr class="crecord" id="${l.goodsId}" >
		     <td width="200"  align="center" >
		     <input type="hidden" value="${l.goodsId}" name="goodsid"   />
		     ${l.gname?default('')}</td>
			 <td width="100"  align="center" ><input type="text" name="price" maxlength="10" value="${l.price}" class="myinput" /></td>
			 <td width="100"  align="center" ><input type="text" name="limit_count" maxlength="10" value="${l.limitCount}" class="myinput" /></td>
			 <td width="100"  align="center" ><input type="text" name="total_count" maxlength="10" value="${l.totalCount}" class="myinput" /></td>
			 <td width="100"  align="center" onclick="removeRecord(this)">移除 </td>
		   </tr>
		  </#list>
       </table>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" id="btn100" onclick="addGoods()"  style="margin-left:10px;" />
	    	<input type="button" class="btn01" value="返回" onclick="back()" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    
    	<table id="ht" style="display:none">
    	<tr class="crecord" id="$id" >
		     <td width="200"  align="center" >
		      <input type="hidden" value="$goodsid" name="goodsid"   />
		     $name</td>
			 <td width="100"  align="center" ><input type="text" name="price" maxlength="20" value="$price" class="myinput"/></td>
			 <td width="100"  align="center" ><input type="text" name="limit_count" maxlength="20" value="1" class="myinput"/></td>
			 <td width="100"  align="center" ><input type="text" name="total_count" maxlength="20" value="10" class="myinput"/></td>
			 <td width="100"  align="center" onclick="removeRecord(this)">移除 </td>
		</tr>
    	</table>
    	
    	
</body>


 <script type="text/javascript">
 
 function back(){
 	window.location.href="${base}/shortbys/shortbuy!list.action";
 }
/*
$("#sub").autocomplete({	
		url: '${base}/shortbys/shortbuy!like.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
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
		minChars: 1
});
*/
$("#haidian").autocomplete({
		url: '${base}/shortbys/shortbuy!getGoodsByHDNO.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
		},
		onItemSelect: function(item) {
		   $("#sub").attr("value",item.value);
		    var ary = String(item.data).split("_");
		    var goodsno=ary[2];
		    var price = ary[1];
		    var id = ary[0];
		    $("#haidian").val(goodsno);
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
   $('#btn100').attr("value","提交中..");
$('#btn100').attr({disabled:"disabled"});

    jQuery.ajax
   ({
       type: "post",
       url: "${base}/shortbys/shortbuy!set.action",	  
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


</script>


</html>