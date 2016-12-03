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
   
    	<div class="m-contents">
	    	<div class="labelnamese">名称：</div>
	    	<input type="text" name="shortBuyBean.name" value="${shortBuyBean.name?default('')}" class="i-text-i" maxlength="50" />
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">广告语：</div>
	    	<input type="text" name="shortBuyBean.advertise" maxlength="50" id="title" maxlength="20" value="${shortBuyBean.advertise?default('')}" class="i-text-i" /> 
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">开始时间：</div>
	    	
			<input type="text" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;" name="stime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  />
			
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">结束时间：</div>
	    	<input type="text" class="select-ui"  style="border:#ccc 1px solid;margin-left:10px;" name="etime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  />
    	</div>
    	<#--
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">期号：</div>
	    	<input type="text"  name="shortBuyBean.issue"  maxlength="20" class="i-text-i" />
	    	 
    	</div>
    	-->
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;margin-left:230px">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" id="btn100"  value="提交" onclick="insert()"  style="margin-left:10px;" />
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


function insert(){


  
   var name = $("input[name='shortBuyBean.name']").val();
  if($.trim(name)==""){
  	alert("名称不能为空");
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
  
  $('#btn100').attr("value","提交中..");
  $('#btn100').attr({disabled:"disabled"});
  
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
    	   }else if(data==-1){
    	   		alert("创建失败: 存在创建状态的记录");
    	   		return;
    	   }else if(data==-2){
    	   		alert("创建失败: 存在正在进行的秒杀活动");
    	   		return;
    	   }else{
    	   		alert("系统忙,请稍候再试！");
    	   }
       }
   }); 
}


</script>


</html>