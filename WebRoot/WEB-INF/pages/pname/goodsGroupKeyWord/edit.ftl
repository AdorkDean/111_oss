<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>编辑套装关键字</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
	<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>
	<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
	<style>
	.doc-ui {float:left;width:220px;height:23px;line-height:23px;margin-left:10px;margin-top:3px;border:#ccc 1px solid;border-left:none;border-radius:10px;color:#ccc;text-align:left;padding-left:10px;}
	.i-text-i td{background-color:#FFFFFF;}
	</style>
</head> 

<body>
	<div class="shop_main" id="shop_main" style="margin-top:0px;margin-left:0px;width:99.3%;">
    <form action="#" method="post" name="form1" id="form1">
    	<div class="m-contents" style="margin-top:5px;">
	    	<div class="labelnamese">关键字：</div>
	    	<input type="text" class="i-text-i" value="${keyword}" style="width:250px;" disabled="disabled"/>
	    	<input type="hidden" name="keyword" id="keyword1" value="${keyword}"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:7px;">
	    	<div class="labelnamese">套装商品：</div>
	    	<input type="text" class="i-text-i" id="sub" maxlength="20" style="width:250px;"/>
    	</div>
    	<div class="m-contents" style="margin-top:7px;">
    	<div class="labelnamese"></div>
    		<table class="i-text-i" cellspacing="1" style="background: #d0d0d0;" id="myt">
    			<tr>
    				<td align="center">商品名称</td>
    				<td align="center">操作</td>
    			<tr>
    			<#list list as list>
    			<tr class="crecord" id="${list.id}" >
				 		<td align="center" ><input type="hidden" value="${list.id}" id="gids1"/>${list.short_name}</td>
					 	<td align="center" onclick="removeRecord(this,'${list.id}');">
					 	<input type="button" class="btn01" value="删除" style="margin-left:10px;"/>
					 </td>
				 </tr>
			 	</#list>
    		</table>
    		<table id="ht" style="display:none">
		    	<tr class="crecord" id="$id" >
				     <td align="center" ><input type="hidden" value="$goodsid" name="goodsids" id="gids"/>$name</td>
					 <td align="center" onclick="removeRecord(this,$goodsid1);">
					 	<input type="button" class="btn01" value="删除" style="margin-left:10px;"/>
					 </td>
				</tr>
    		</table>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:30px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="返回" onclick="location.href='${base}/goods/goodsgk!list.action';" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   

</body>

<script>	
$("#sub").autocomplete({	
		url: '${base}/goods/goodsgk!like.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
		},
		onItemSelect: function(item) {
		    $("#sub").attr("value",item.value);
		    var ary = String(item.data).split("_");
		    var id = ary[0];
		    var flag = true;
		    $(".crecord").each(function(){
		    	if(this.id==id){
		    		$alert("warn","["+item.value+"]已经添加过");
		    		flag = false;
		    	}
		    });
		   if(flag)
		   {
		   	 	var htm = $('#ht').html();
   				htm = htm.replace("$name",item.value);
   				htm = htm.replace("$id",id);
   				htm = htm.replace("$goodsid",id);
   				htm = htm.replace("$goodsid1",id);
				$('#myt').append(htm);
				jQuery.ajax({
			       type: "post",
			       url: " ${base}/goods/goodsgk!ajaxsave.action",	  
			       data:{"keyword":$("#keyword1").val(),"goodsids":id},
			       success: function(data){
			          if(data>0){
			          	$alert('success','添加成功!');
			          }else{
			          	$alert('error','网络异常!');
			          }
			       },error:function(){
			       		$alert('error','网络异常!');
			       }
	  			});
				
				
		   }
		    
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:true,
		minChars: 1
});
function onlyVer(){ 
$("#sidkeyword").html("");
		var flag = true;
if(''!=$("#keyword").val()&&$("#keyword1").val()!=$("#keyword").val()){
		 jQuery.ajax({
	       type: "post",
	       async:false,
	       url: " ${base}/goods/goodsgk!onlyVerification.action",	  
	       data:{"keyword":$("#keyword").val()},
	       success: function(data){
	          if(data>0){
	          	$("#sidkeyword").html("关键字已经存在!");
			    flag = false;
	          }else{
	          	flag = true;
	          }
	       },error:function(){
	       		$alert('error','网络异常!');
	       }
	   });
}
		 return flag;
	}
function removeRecord(obj,id){
	if(!window.confirm("您确定要删除该记录吗?删除的数据不能恢复！")){
		  	return;
	}
	jQuery.ajax({
		       type: "post",
		       url: " ${base}/goods/goodsgk!ajaxdel.action",	  
		       data:{"keyword":$("#keyword1").val(),"goodsids":id},
		       success: function(data){
		          if(data>0){
		          	$alert('success','删除成功!');
					$(obj).parent().remove();
		          }else{
		          	$alert('error','网络异常!');
		          }
		       },error:function(){
		       		$alert('error','网络异常!');
		       }
	  		});
}
</script>
</html>