<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>代理商管理</title>		

<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<#include "/WEB-INF/pages/inc/common.ftl">

<style>

</style>

</head>

<html>

<body style="margin:0 3px;" >

<form id="formobj" >


<table border="1" class="table-list" style="width:1100px;margin-left:50px;margin-top:20px;font-size:13px" >

<tr><td style="width:90px">热词：   </td>
<td><input type="text" id="hotwords" style="width:90%;height:35px" value=${hotwords?default('')} ><p style="color:red">多个热文词以逗号分隔（eg:感冒发烧,呼吸肠道）</p></td>
</tr>
<#--
<tr><td>导航词：   </td><td><input type="text" id="daohang_words" style="width:150px;height:35px" value=${daohang_words?default('')} ></td></tr>
-->
<tr><td colspan="2" align="center" >搜索分类</td></tr>
<tr><td colspan="2">
<#--
<table>
<#assign n=1 > 

<tr>
<#list list?if_exists as l>

<td><input type="checkbox" value="${l.id}"  <#if l.isexist==1>checked</#if>/>&nbsp;${l.category_name?default('')}</td>
<#if n%5==0>
</tr>
</#if>
 <#assign n=n+1 > 
</#list>
</tr>

</table>
-->
<table>


<#list list?if_exists as l>
<#if l.id!=799>
<tr>
<td style="width:20px">${l.category_name?default('')}<input type="checkbox" id="parent_${l.id}" value="${l.id}" <#if l.isexist==1>checked</#if> style="display:none" ></td><td>

<#assign tvalue="${l.childs?default('')}"> 
<#assign n=1 > 
<#assign tvalue="${l.childs}"> 
	<#list tvalue?split(",") as tv> 
		<#assign vlaue="${tv}" > 
		<#if n%10==0>
			<br/>
		</#if>
		<input value="${vlaue?split("-")[0]}" type="checkbox" name="${l.id}"  <#if vlaue?split("-")[2]='1' >checked</#if> onclick="mychk(this)" > ${vlaue?split("-")[1]}&nbsp;&nbsp;&nbsp;
		 <#assign n=n+1 > 
	</#list>
</td>
</tr>
</#if>
</#list>
</table>



</td>
</tr>
<tr><td colspan="2" align="center">
<input type="button" class="btn01" value="提交" id="btn"  >
</td></tr>

<table>

</form>


</body>

<script type="text/javascript">


function mychk(obj){
	var ys = $(obj).is(':checked');
	var parentid = obj.name;
	if(ys){
		$('#parent_'+parentid).attr('checked','true');
	}else{
		
		var len = $("input[name="+parentid+"]:checked").length;
		if(len==0){
			$('#parent_'+parentid).attr('checked',false);
		}
	}
}
	

 $(document).ready(function (){
 $('#btn').bind('click', function() {
  var str = "";
     jQuery("input[type=checkbox]:checked").each(function(){
     	str = str + $(this).val()+",";
     });
     
     var finstr = str.substring(0,str.length-1);
     if(finstr==""){
     	alert("没有选择");
     	return;
     }
     
     var type = $("#mysel").val();
     var hotwords = $('#hotwords').val();
      $("#btn").attr("disabled",true);
      
      var daohang_words = $('#daohang_words').val();
       $('#btn').attr("disabled",true);
       $('#btn').attr({disabled:"disabled"});
       $('#btn').attr("value","处理..");
    jQuery.ajax
   ({
       type: "post",
       url: "${base}/versions/big_table!add.action",	  
       data:{'ids':finstr,'type':type,'hotwords':hotwords,'daohang_words':daohang_words},
       success: function(data)
       {
           if(data>0){
           	alert("操作成功");
           	window.location.reload();
           }else{
           	alert("操作失败");
           }
       }
   }); 
     
     
 });
 
 
});


</script>
	

</html>
