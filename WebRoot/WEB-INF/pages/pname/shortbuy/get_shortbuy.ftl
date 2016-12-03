<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加数据公共模板</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>


</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
   <form id="formobj" action="${base}/shortbys/shortbuy!insertShortBuy.action" method="post"  >
   
   <input type="hidden" value="update" name="action" >
  	    <input type="hidden" value="<#if shortBuyBean.id?exists >${shortBuyBean.id}</#if>" name="shortBuyBean.id" >
    
    	<div class="m-contents">
	    	<div class="labelnamese">名称：</div>
	    	<input type="text" name="shortBuyBean.name" maxlength="50" value="${shortBuyBean.name?default('')}" class="i-text-i" />
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">广告语：</div>
	    	<input type="text" name="shortBuyBean.advertise" id="title" maxlength="50" value="${shortBuyBean.advertise?default('')}" class="i-text-i" /> 
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">开始时间：</div>
	    	
			<input type="text" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;" name="stime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${shortBuyBean.startTime?string("yyyy-MM-dd HH:mm:ss")}" />
			
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">结束时间：</div>
	    	<input type="text" class="select-ui"  style="border:#ccc 1px solid;margin-left:10px;" name="etime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${shortBuyBean.endTime?string("yyyy-MM-dd HH:mm:ss")}" />
    	</div>
    	<#--
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">期号：</div>
	    	<input type="text" value="${shortBuyBean.issue?default('')}" name="shortBuyBean.issue"  maxlength="20" class="i-text-i" />
	    	 
    	</div>
    	-->
    	
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;margin-left:230px">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="update()"  style="margin-left:10px;" />
	    	<input type="button" class="btn01" value="返回" onclick="back()" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   
</body>


 <script type="text/javascript">
 
  function back(){
    window.location.href="${base}/shortbys/shortbuy!list.action";
 }
 
$("#sub").autocomplete({	
		url: '${base}/shortbys/shortbuy!like.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
		},
		onItemSelect: function(item) {
		   $("#sub").attr("value",item.value);
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:true,
		minChars: 1
});


function update(){
   var flag=true;
  var o;
  $("input[type=text]").each(function(index){
  if( $(this).val()=="" ){
   $(this).focus();
    $(this).css("background-color","yellow");
   flag = false;
   o = $(this);
   return flag; //为了防止提示最后一个文本框
  }
  });
 
  if(!flag){
   alert("不能为空");
   o.css("background-color","");
   return;
  }
  
  var stime = $("input[name='stime']").val();
  if($.trim(stime)==""){
  	alert("开始时间不能为空");
  	return;
  }
  
  var etime = $("input[name='etime']").val();
  if($.trim(etime)==""){
  	alert("结束时间不能为空");
  	return;
  }
  
  if(stime>etime){
  	 alert("开始时间不能大于结束时间");
  	 return;
  }
  
    var now = new Date();
  if(new Date(stime)<now){
  	alert("开始时间不能小于当前时间");
  	return;
  }
  
  
  var vFrmObj = $("#formobj");
  jQuery.ajax
   ({
       type: "post",
       url: "${base}/shortbys/shortbuy!insertShortBuy.action",	  
       data:vFrmObj.serialize(),
       success: function(data)
       {
    	   if(data>0){
    	   		alert("操作成功");
    	   		window.location.href="${base}/shortbys/shortbuy!list.action";
    	   }else{
    	   		alert("系统忙,请稍候再试！");
    	   }
       }
   }); 
}


</script>


</html>