<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>代理商管理</title>		
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">


<link rel="stylesheet" href="${base}/web/location_tree/jquery.treeview.css" />
<link rel="stylesheet" href="${base}/web/location_tree/red-treeview.css" />
<link rel="stylesheet" href="${base}/web/location_tree/screen.css" />
<script src="${base}/web/location_tree/lib/jquery.js" type="text/javascript"></script>
<script src="${base}/web/location_tree/jquery.treeview.js" type="text/javascript"></script>

<style>

</style>

</head>

<html>

<body style="margin:0 3px;" >

<form id="formobj" >
 <input type="hidden" value="${id}" name="promid" />

<div class="m-contents" style="margin-top:20px;">
	<div class="labelnamese"></div>
	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROM_DETAIL_SURE">
	<#if flag=='1'>
	<input type="button" class="btn01" value="提交" onclick="addGoods()" id="btn100" style="margin-left:10px;" />
	</#if>
	</@security.authorize>
	<input type="button" class="btn01" value="返回" onclick="back()" style="margin-left:10px;"/>
</div>
    		
<#--  		
<table border="1" cellspacing="0" cellpadding="0" style="border-color:rgba(218, 228, 243, 0.44);padding-top:15px" id="myt" >			
  
    <tr>
	    <td width="100"  class="tit_new" align="center" >下限金额</td>
		 <td width="100"  class="tit_new" align="center" ><input type="text" class="i-text-i" name="min_price" style="height:30px" maxlength="5" value="<#if prom.minAmount?exists>${prom.minAmount}</#if>" /></td>
		 <td width="100"  class="tit_new" align="center"  >满减金额</td>
		 <td width="100"  class="tit_new" align="center" ><input type="text" class="i-text-i" value="<#if prom.amount?exists>${prom.amount}</#if>" name="price"  style="height:30px" maxlength="5" /></td>
    </tr>
    
     <tr>
		 <td width="100"  class="tit_new" align="center" >赠品ID</td>
		 <td width="100"  class="tit_new" align="left" colspan="3" ><input type="text" onblur="goodsIsExist()" name="gift"  style="height:30px" maxlength="10"/></td>
    </tr>
    
</table>
-->
   
   
   <div id="sidetree" style="padding-left:80px">
<div id="sidetreecontrol"><a href="?#">合并全部</a> | <a href="?#">展开分部</a></div>
<ul id="tree">
<#list list1?if_exists as l1>
	<li><span><strong><input type="checkbox" value="${l1.id}" id="${l1.id}" name="${l1.id}" onclick="clickCity(${l1.id})" <#list idlist?if_exists as lit><#if lit.targetId==l1.id>checked</#if></#list> />${l1.categoryName}</strong></span>
		<ul>
		  <#list list2?if_exists as l2><#if l1.id==l2.parentId>
		 <span><strong><li><input type="checkbox" <#list idlist?if_exists as lit><#if lit.targetId==l2.id>checked</#if></#list> name="${l1.id} ${l2.id}"  id="${l2.id}" onclick="clickCity(${l2.id})" value="${l2.id}"   />${l2.categoryName}
		    </li></strong></span>
		  
		  </#if></#list>
		</ul>
	</li>
</#list>
</ul>
</div>
</div>    

<#--
<div id="sidetree" style="padding-left:80px">
<div id="sidetreecontrol"><a href="?#">合并全部</a> | <a href="?#">展开分部</a></div>
<ul id="tree">
<#list list1?if_exists as l1>
	<li><span><strong><input type="checkbox" value="${l1.id}" id="${l1.id}" name="${l1.id}" onclick="clickCity(${l1.id})" <#list idlist?if_exists as lit><#if lit.targetId==l1.id>checked</#if></#list> />${l1.categoryName}</strong></span>
		<ul>
		  <#list list2?if_exists as l2><#if l1.id==l2.parentId>
		 <span><strong><li><input type="checkbox" <#list idlist?if_exists as lit><#if lit.targetId==l2.id>checked</#if></#list> name="${l1.id} ${l2.id}"  id="${l2.id}" onclick="clickCity(${l2.id})" value="${l2.id}"   />${l2.categoryName}
			
			<ul>
			  <#list list3?if_exists as l3><#if l2.id==l3.parentId>
			  <li><input type="checkbox" <#list idlist?if_exists as lit><#if lit.targetId==l3.id>checked</#if></#list> name="${l1.id} ${l2.id} ${l3.id}" value="${l3.id}" id="${l1.id}_${l2.id}_${l3.id}"   />${l3.categoryName}</li>
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
-->
</form>


</body>

<script type="text/javascript">
function back(){
	window.location.href="${base}/proms/prom!list.action";
}

function goodsIsExist(){
    var gift = $("input[name='gift']").val();
    if($.trim(gift)==""){
    	return;
    }
   
    var patrn=/^[0-9]{1,20}$/; 
	if(!patrn.exec(gift)){
		alert("请输入数字ID");
		return ;
	}
    
	jQuery.ajax
   ({
       type: "post",
       url: "${base}/proms/prom!giftIsExist.action",	  
       data:{"id":gift},
       success: function(data)
       {
    	  if(data>0){
    	  	alert("您所要赠送的商品不存在，请核实ID");
    	  	$("input[name='gift']").val("");
    	  }
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
	
function clickCity(v){
     var ys = $('#'+v).is(':checked');
	if(ys){
		$('input[name~='+v+']').attr("checked",true);
	}else{
		$('input[name~='+v+']').attr("checked",false);
	}
}

function addGoods(){

     var str = "";
	 jQuery("input[type=checkbox]:checked").each(function(){
	    str = str + $(this).val() + ",";
	 });
	 
	 
	$('#btn100').attr("value","提交中..");
	$('#btn100').attr({disabled:"disabled"});


   var vFrmObj = $("#formobj");
   var parentid = $('#promid').val();
    jQuery.ajax
   ({
       type: "post",
       url: "${base}/proms/prom!setMixGoods.action?category="+str.substring(0,str.length-1)+"&ptype="+3,	  
       data:vFrmObj.serialize(),
       success: function(data)
       {
    	   if(data>0){
    	   		alert("操作成功");
    	   }else{
    	   		alert("系统忙,请稍候再试！");
    	   }
       }
   }); 
}


</script>
</html>
