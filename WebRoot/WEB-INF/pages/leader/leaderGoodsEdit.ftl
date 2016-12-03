<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加领袖佣金商品</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 
<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="${base}/leader/leaderGoods!leaderGoodsAdd.action" method="post" name="addInfo" id="addInfo">
    	<font color="red"> 每个商品只能添加一次佣金</font>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">商品名称：</font></div>
	    	<input type="text" class="i-text-i" id="glsp" value="" maxlength="12" autocomplete="off"/>
	    	<input type="hidden" name="brokerage.goodsId" class="i-text-i" id="goodsId" value="" maxlength="20"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">海典编码：</font></div>
	    	<input type="text" class="i-text-i" id="goodsno" value="" maxlength="12" autocomplete="off"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">佣金比例：</font></div>
	    	<input type="text" name="brokerage.brokerage" class="i-text-i" id="brokerage" value="" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    	</div>
		<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red" >佣金：</font></div>
	    	<div  style="margin-top:8px;"><font color="red" id="yongjin"></font></div>
	    	<input type="hidden"   id="pc_price" value="" />
	    	<input type="hidden"   id="wap_price" value=""/>
	    	<input type="hidden"   id="app_price" value="" />
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_LX_ADD_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="subForm();" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="刷新" onclick="javascript:history.go(0);" style="margin-left:10px;"/>
	    	<a href="${base}/leader/leaderGoods!list.action"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    		
    </form>
    </div>   
</body>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>


<script>
$(function(){
	$("#goodsno").focus(function(){
		var goodsno = $("#goodsno").val();
		if(goodsno==""){
			$("#glsp").val("");
			$("#goodsId").val("");
		}
	});
	$("#goodsno").blur(function(){
		var goodsno = $("#goodsno").val();
		if(goodsno!=""){
			$.ajax({type: 'POST',
				url: "${base}/leader/leaderGoods!selectGoodsByHaidianCode.action" ,
				data: {"goodsno":goodsno} ,
				dataType: "json",
				success: function(data){
					if(data.status==0){//不存在，删掉输入的
						$alert("warn","未查询到编码["+goodsno+"]商品");
						$("#glsp").val("");
						$("#goodsId").val("");
						$("#goodsno").val("");
						$("#pc_price").val("");
						$("#wap_price").val("");
						$("#app_price").val("");
						$("#yongjin").text("");
					}
					if(data.status==1){//存在，拼input值
						$("#glsp").attr("value",data.goodsName);
						$("#goodsId").val(data.id);
						$("#pc_price").val(data.pc_price);
						$("#wap_price").val(data.wap_price);
						$("#app_price").val(data.app_price);
					}
				},error:function(data){
					//不存在，删掉输入的
					$alert("warn","未查询到编码["+goodsno+"]商品");
					$("#glsp").val("");
					$("#goodsId").val("");
					$("#goodsno").val("");
					$("#pc_price").val("");
					$("#wap_price").val("");
					$("#app_price").val("");
					$("#yongjin").text("");
				}
			});
		}else{
			$("#glsp").val("");
			$("#goodsId").val("");
			$("#goodsno").val("");
			$("#pc_price").val("");
			$("#wap_price").val("");
			$("#app_price").val("");
			$("#yongjin").text("");
		}
	});
})



$().ready(function() {
	${flash_message()}
});	


function subForm(){
   var goodsId = $("#goodsId").val();
   if($.trim(goodsId)==""){
       $alert('warn','请填写佣金商品');
      return false;
   }
   
   var brokerage = $("#brokerage").val();
   if($.trim(brokerage)==""){
   $alert('warn','请填写佣金比例');
      return false;
   }
    $.post("${base}/leader/leaderGoods!leaderGoodsAdd.action",{goodsId:goodsId,bro:brokerage,random:Math.random()},function(data){
          if(data=="1"){
            $alert('success','添加成功');
            $("#goodsId").val('');
            $("#brokerage").val('');
            $("#glsp").val('');
          }else if(data=="-1"){
            $alert('warn','该商品已添加过');
          }else if(data=="-2"){
            $alert('warn','添加失败');
          }else if(data=="-3"){
          	$alert('warn','系统异常');
          }else{
          	$alert('warn','参数有误');
          }
    }); 
    
   
}


$("#glsp").autocomplete({	
		url: '${base}/proms/prom!likeWithHaidianCode.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
		},
		onItemSelect: function(item) {
			if(item!=null && item !="undefined" && item.data!=null && item.data!="undefined" && item.data!=""){
				$("#goodsId").attr("value",item.data[0]);
				if(item.data[1]!="undefined" && item.data[1]!=null && item.data[1]!="null"){
					$("#goodsno").attr("value",item.data[1]);
				}
				
			}
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:false,
		minChars: 0
});

$("#brokerage").keyup(function(){
	var pc=$("#pc_price").val();
	var wap=$("#wap_price").val();
	var app=$("#app_price").val();
	var brokerage=$("#brokerage").val();
	var str="PC:"+(pc*(brokerage/100)).toFixed(2)+"元, WAP:"+(wap*(brokerage/100)).toFixed(2)+"元, APP:"+(app*(brokerage/100)).toFixed(2)+"元";
	$("#yongjin").text(str);
});
</script>
</html>