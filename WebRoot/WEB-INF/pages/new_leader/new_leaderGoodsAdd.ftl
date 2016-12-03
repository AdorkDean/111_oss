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
	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font >请选择添加模式：</font></div>
	    	<select class="i-text-i" name="type" id="type" onchange="showPOrC(this.value);">
					 <option value="1">按照商品</option>
					 <option value="2">按照分类</option>
				 </select>
    	</div>
	
	
	
	 
	<div class="shop_main" id="product_main" style="display:block;">
    <form action="" method="post" name="addInfo" id="addInfo">
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">商品名称：</font></div>
	    	<input type="text" class="i-text-i" id="glsp" value="" maxlength="12" autocomplete="off"/>
	    	<input type="hidden" name="goodsId" class="i-text-i" id="goodsId" value="" maxlength="20"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">海典编码：</font></div>
	    	<input type="text" class="i-text-i" id="goodsno" value="" maxlength="12" autocomplete="off"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">商品价格：</font></div>
	    	<input type="text"  class="i-text-i"  id="wap_price" value="" readonly="readonly" style="border:none;"/>元
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font >佣金计算器：</font></div>
	    	<input type="text"  class="i-text-i" id="brokerage" value="" maxlength="5" onkeyup="this.value=this.value.replace(/^((\d*[1-9])|(0?\.\d{2}))$g,'')" onafterpaste="this.value=this.value.replace(/^((\d*[1-9])|(0?\.\d{2}))$g,'')"/>%
    	</div>
    	
		<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">返佣金额：</font></div>
	    	<input type="text"  class="i-text-i" name="rebateAmount" id="rebateAmount" readonly="readonly" value=""/>元
	    	
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="subProductForm();" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="刷新" onclick="javascript:history.go(0);" style="margin-left:10px;"/>
	    	<a href="${base}/leader/newLeaderGoods!getLeaderGoodsList.action"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    		
    </form>
    </div>   
    
    
    
    	<div class="shop_main" id="category_main" style="display:none;">
    <form action="" method="post" name="addCategory" id="addCategory">
    	<input type="hidden" id="categoryId" name="categoryId" value=""/>
		<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">商品分类：</font></div>
	    	<select onchange="getfl(1)" id="fl1" class="i-text-i">
	 		 <option value ="fl1">请选择</option>
	 			 ${fl?default('')}
			</select>
		<select onchange="getfl(2)" id="fl2" class="i-text-i" style="display:none">
		</select>
		<select onchange="getfl(3)" id="fl3" class="i-text-i" style="display:none">
		</select>
    	</div>
		<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">返佣金额：</font></div>
	    	<input type="text"  class="i-text-i" name="rebateAmount" id="rebateAmount1" value=""/>元
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="subCategoryForm();" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="刷新" onclick="javascript:history.go(0);" style="margin-left:10px;"/>
	    	<a href="${base}/leader/newLeaderGoods!getLeaderGoodsList.action"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    		
    </form>
    </div> 
</body>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>


<script>

$().ready(function() {
	${flash_message()}
});	
function subProductForm(){
   var goodsId = $("#goodsId").val();
   if($.trim(goodsId)==""){
   $alert('warn','请选择商品');
      return false;
   }
   var rebateAmount = $("#rebateAmount").val();
   if($.trim(rebateAmount)==""){
   $alert('warn','请填写返佣金额');
      return false;
   }
  $.ajax({
	url:"${base}/leader/newLeaderGoods!addGoods.action",
			type:"post",
			data:$("#addInfo").serialize(),
			success:function(data){
			 if(data=="1"){
           $alert('success','数据保存成功');
           window.location.href="${base}/leader/newLeaderGoods!getLeaderGoodsList.action"; 
          }else if(data=="-1"){
            $alert('warn','该商品已添加过');
          }else if(data=="-2"){
            $alert('warn','添加失败');
          }else if(data=="-3"){
          	$alert('warn','系统异常');
          }else{
          	$alert('warn','参数有误');
          }
       }
 	 });
}
function subCategoryForm(){
var fl1 = $("#fl1").val();
		var fl2 = $("#fl2").val();
		var fl3 = $("#fl3").val();
		if("fl1"==fl1){
			$alert('warn',"请选择一级分类!");
			return false;
		}else{
			$("#categoryId").val(fl1);
		}
		if("fl2"==fl2){
			$alert('warn',"请选择二级分类!");
			return false;
		}
		if("fl3"==fl3){
			$alert('warn',"请选择三级分类!");
			return false;
		}else{
			if(""!=fl3&&fl3!=null){
				$("#categoryId").val(fl3);
			}
		}

   var rebateAmount = $("#rebateAmount1").val();
   if($.trim(rebateAmount)==""){
   $alert('warn','请填写返佣金额');
      return false;
   }
  $.ajax({
	url:"${base}/leader/newLeaderGoods!addCategory.action",
			type:"post",
			data:$("#addCategory").serialize(),
			success:function(data){
                   $alert('success',data);
                    window.location.href="${base}/leader/newLeaderGoods!getLeaderGoodsList.action"; 
                    }
 	 });
}
$("#brokerage").keyup(function(){
	var wap=$("#wap_price").val();
		var brokerage=$("#brokerage").val();
		var str=(wap*(brokerage/100)).toFixed(2);
		$("#rebateAmount").val(str);
});


function showPOrC(divId){
if(divId==1){
$("#product_main").show();
$("#category_main").hide();
}else{
$("#category_main").show();
$("#product_main").hide();
}
}




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
						$("#wap_price").val("");
					}
					if(data.status==1){//存在，拼input值
						$("#glsp").attr("value",data.goodsName);
						$("#goodsId").val(data.id);
						$("#wap_price").val(data.wap_price);
					}
				},error:function(data){
					//不存在，删掉输入的
					$alert("warn","未查询到编码["+goodsno+"]商品");
					$("#glsp").val("");
					$("#goodsId").val("");
					$("#goodsno").val("");
					$("#wap_price").val("");
				}
			});
		}else{
			$("#glsp").val("");
			$("#goodsId").val("");
			$("#goodsno").val("");
			$("#wap_price").val("");
		}
	});
})




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
				if(item.data[2]!="undefined" && item.data[2]!=null && item.data[2]!="null"){
					$("#wap_price").attr("value",item.data[2]);
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



//分类
function getfl(v){
	if("fl1"==$("#fl"+v).val()){
		$("#fl2").hide();
		$("#fl3").hide();
		
	}
	if("fl2"==$("#fl"+v).val()){
		$("#fl3").hide();
	}
	if("fl1"!=$("#fl"+v).val()&&"fl2"!=$("#fl"+v).val()&&"fl3"!=$("#fl"+v).val()){
		jQuery.ajax({
		       type: "post",
		       url: " ${base}/goods/goods!getCategory.action",	  
		       data:{"id":$("#fl"+v).val()},
		       success: function(data){
			       if(1==v){
				       $("#fl2").hide();
				       $("#fl3").hide();
				       $("#fl2").empty();
				       $("#fl3").empty();
			       }
			       if(2==v){
				       $("#fl3").hide();
				       $("#fl3").empty();
			       }
			       if(""!=data){
			       		v++;
			       		$("#fl"+v).empty();
			       		$("#fl"+v).show();
			       		$("#fl"+v).append("<option value ='fl"+v+"'>请选择</option>");
				       $("#fl"+v).append(data);
			       }
		       },error:function(){
		       		$alert('error','网络异常!');
		       }
		   });
	}
}
</script>
</html>