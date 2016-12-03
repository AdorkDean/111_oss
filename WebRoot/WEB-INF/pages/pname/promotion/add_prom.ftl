<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加数据公共模板</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>
<style>
.cl1{height:35px;width:200px;padding-left:3px}
.mycheck{height:35px;padding-left:3px;width:40px}
tr{height:45px}
</style>

</head> 

<body> 
	<div class="shop_main" id="shop_main" > 
   <form id="formobj" action="" method="post"  >
            <input type="hidden" name="action" value="add"　/>
    		<table border="1" cellspacing="0" cellpadding="0" style="border-color:rgba(218, 228, 243, 0.44);padding-top:15px;" id="myt" width="1000px" >			
		    
		   <tr class="crecord" >
		    <td width="50"  align="center" >促销类型</td>
			 <td width="100"  align="center" >
			    <select id="sel" class="select-ui" style="margin-left:10px" onchange="selectType()" name="prom.type">
					<option value="1">单品</option><option value="2">商品组合</option>
					<#--
					<option value="3">分类促销</option>
					-->
				</select>
			 </td>
		     <td width="50"  align="center" >促销名称</td>
		     <td width="100"  align="center" ><input name="prom.name" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;"/></td>
		   </tr>
		   
		    <tr class="crecord" >
		     <td width="50"  align="center" >开始时间</td>
		     <td width="100"  align="center" ><input name="stime" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" /></td>
		     
		     <td width="50"  align="center" >结束时间</td>
			 <td width="100"  align="center" ><input name="etime" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" /></td>
		   </tr>
		   
		   <tr class="crecord" >
		    <td width="50"  align="center" >专题链接</td>
			 <td width="100"  align="center" >
			   <input name="prom.link" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;"/>
			 </td>
		     <td width="50"  align="center" >促销广告</td>
		     <td width="100"  align="center" ><input name="prom.advertise" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;"/></td>
		   </tr>
		   
		    <tr class="crecord" >
		    <td width="50"  align="center" >能否用券</td>
			 <td width="100"  align="left" >
			  <input type="checkbox" name="isticket" value="1" style="height: 18px;margin-left:-81px" class="cl1"/>
			 </td>
		     <td width="50"  align="center" >使用平台</td>
		     <td width="100"  align="center" >
		      PC: <input type="checkbox" name="platform" value="PC" style="height: 18px;" class="mycheck"/>
			  WAP: <input type="checkbox" name="platform" value="WAP" style="height: 18px;" class="mycheck" />
			  APP: <input type="checkbox" name="platform" value="APP" style="height: 18px;" class="mycheck" />
		     </td>
		   </tr>
		   
		   
		    <tr class="crecord" id="rec1" style="display:none" >
		    <td width="50"  align="center" >下限金额</td>
			 <td width="100"  align="center" >
			   <input name="prom.minAmount" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;"/>
			 </td>
		  	<td width="50"  align="center" >满减金额</td>
			 <td width="100"  align="center" >
			   <input name="prom.amount" type="text" value="" class="lname-i" style="border:#cccccc 1px solid;"/>
			 </td>
		   </tr>
		   
       </table>
    	
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;margin-left:250px">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_LIST_SET_GOODS">
	    	<input type="button" class="btn01" id="btn100" value="提交" onclick="insert()"  style="margin-left:10px;" />
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="返回" onclick="back()" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   
</body>


 <script type="text/javascript">


function back(){
	window.location.href="${base}/proms/prom!list.action";
}

function insert(){
 
  var platform_str = "";
  var platform = $("input[name='platform']:checked").each(function(){
  		platform_str = platform_str+","+this.value;
  });

  var name = $("input[name='prom.name']").val();
  if($.trim(name)==""){
  	alert("促销名称不能为空");
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
  
  platform_str = platform_str.substring(1,platform_str.length);
  if(platform_str==""){
 	 alert("请选择促销使用平台 ");
 	 return;
  }
  

  var sel = $("#sel").find("option:selected").val();
  if(sel!=1){
	 
  	  var min_amount = $("input[name='prom.minAmount']").val();
  	  var amount = $("input[name='prom.amount']").val();
  	  if($.trim(min_amount)==""){
  	     alert("下限金额不能为空");
  	     return;
  	  }
  	  
  	    var patrn = new RegExp("^[0-9]+(.[0-9]{1,3})?$");
	  if(!patrn.test(min_amount)){
			alert("下限金额必须为数字格式");
			return ;
	  }
	  
  	  if($.trim(amount)==""){
  	     alert("满减金额不能为空");
  	     return;
  	  }
	  
	  if(!patrn.test(amount)){
			alert("满减金额必须必须为数字格式");
			return ;
	  }
	  
  }
  
  $('#btn100').attr("value","提交中..");
  $('#btn100').attr({disabled:"disabled"});
  
  var vFrmObj = $("#formobj");
  jQuery.ajax
   ({
       type: "post",
       url: "${base}/proms/prom!insert.action?platfrom="+platform_str,	  
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


function selectType(n){
	var val = $("#sel").find("option:selected").val();
	if(val==2 || val==3){
	   $('#rec1').show();
	   $('#rec2').show();
	}else{
	   $('#rec1').hide();
	   $('#rec2').hide();
	}
}

</script>


</html>