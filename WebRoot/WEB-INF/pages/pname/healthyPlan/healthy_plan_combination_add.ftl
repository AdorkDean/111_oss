<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加套餐</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" href="${base}/web/plugins/jquery-ui-1.8.14.custom/css/ui-lightness/jquery-ui-1.8.14.custom.css" rel="stylesheet" /> 
	 <#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<script type="text/javascript" src="${base}/web/js/ajaxfileupload.js"></script>
	<style type="text/css">
       input{height:27px;width:180px;padding-left:3px; border: 1px solid #cbcbcb;margin-left: 10px;}
     </style>
</head> 
<body> 
<iframe name="bankmessage"  src="" height="0px" width="0px"></iframe>
<div>
	<div class="tab_content">
				<form action="${base}/healthyplan/healthyplan!planCombinationSave.action" method="post" onsubmit="return checkHealthyForm()"  name="form1" id="form1"  target="bankmessage" enctype="multipart/form-data" >
					 <input name="thealthyplancombination.id" id="thealthyplancombinationId" value="${thealthyplancombination.id?default('')}" type="hidden" />
					 <input name="thealthyplancombination.planId" id="thealthyplancombination_planId" value="${thealthyplancombination.planId?default('')}" type="hidden" />
					 <table border="1" cellspacing="0" cellpadding="0" width="95%" style="border-color:rgba(218, 228, 243, 0.44);"> 
			          <tr style="height:40px"> 
			           <td align="center" style="width:15%"><span style="color:red">*</span>套餐名称:</td> 
			           <td>
			                <input name="thealthyplancombination.packageName" style="" maxlength="6" id="thealthyplansymptom_package_name" value="${thealthyplancombination.packageName?default('')}"  />
			           </td> 
			           <td align="center" style="width:15%"><span style="color:red">*</span>权重:</td> 
			           <td>
			                <input name="thealthyplancombination.weight" style="" maxlength="9" id="thealthyplancombination_weight" value="${thealthyplancombination.weight?default(0)}"  />
			           </td> 
			          </tr> 
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%"><span style="color:red">*</span>药师:</td> 
			           <td colspan="3">
			                <select name="thealthyplancombination.pharmacistId" id="thealthyplancombination_pharmacistId" style="height:27px;width:200px;margin-left: 10px;">
								 <option value="">全部</option>
								 <#list phyList?if_exists as classification>
								    <option value="${classification.id}"  <#if thealthyplancombination.pharmacistId?exists><#if classification.id==thealthyplancombination.pharmacistId>selected</#if></#if>>${classification.realName}</option>
								 </#list>
							 </select>
			           </td> 
			           </tr> 
			           <tr style="height:40px"> 
			          	<td align="center" style="width:15%"><span style="color:red">*</span>药师点评:</td> 
			           <td colspan="3">
			               <textarea class="taa-ui" name="thealthyplancombination.commentContent" id="thealthyplancombination_comment_content">${thealthyplancombination.commentContent?default('')}</textarea>
			           </td> 
			          </tr>
			        </table>
			        <table class="table-list"> 
			        <tr style="height:40px">
			             <td align="center" width="20%">海典编号:</td>
			             <td colspan="5" id="sbtd" width="80%">
							<input type="text" id="healthy_goods_goodsno" name="healthy_goods_goodsno"  />
							<input type="button" id="addOrderItem" onclick="getGoodsByGoodsno('healthy_goods_goodsno','healthy_goods_tbody',6)"  class="btn02" style="width: 79px;" value="添加商品" />
						    <span style="color:red">最多添加六个主药(必填)</span>
						</td>
			          </tr>
			          </table>
			        <table class="table-list"> 
			       <thead style="background:#dbf1fc"> 
			        <tr> 
			         <th> 商品名称 </th> 
			         <th> 海典编号</th> 
			         <th> 规格 </th>
			         <th>市场价格</th> 
			         <th> pc价格 </th> 
			         <th> wap价格 </th>
			         <th> app价格 </th> 
			         <th> 权重</th>
			         <th>操作</th>
			        </tr> 
			       </thead> 
			       <tbody id="healthy_goods_tbody"> 
			       <tr> <td colspan="9" class="blank"></td></tr> 
			            <#list list?if_exists as resc>
			              <tr id="goodsTr${resc.id}" style="height:40px">
			              <td align="center"><input name="goodsids"  value="${resc.id}" type="hidden"/>${resc.goods_name?default('')}</td>
			              <td align="center">${resc.goodsno?default('')}</td>
			              <td align="right">${resc.spec?default(0)}</td>
			              <td>${resc.price?default(0)}</td>
			              <td>${resc.pc_price?default(0)}</td>
			              <td>${resc.wap_price?default(0)}</td>
			              <td>${resc.app_price?default(0)}</td>
			              <td><input name="goodsWeights" maxlength="9" value="${resc.weight?default(0)}" class="input input_v1" style="width:50px;"/></td>
			              <td align="center"><a href="javascript:deleteTr(${resc.id})">删除</a></td></tr> 
			            </#list>
			       </tbody> 
			      </table>
			      <table class="table-list"> 
			      <tr style="height:40px" >
		             <td colspan="4" width="80%" align="center">
						<input name="receiverAdd" type="submit"  class="btn01" style="width: 50px;" value="提交 "/>
					</td>
		          </tr>
		        </table>
			      </form> 
		        </div>
</div>

</body>
<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript">

//根据海典编号添加商品
function getGoodsByGoodsno(goods_no_id,table_id,goodsSize){
     var  goodsno = $("#"+goods_no_id).val();
     var goodsids = document.getElementsByName("goodsids");
     if(goodsno!=''&&goodsids.length<goodsSize){
         $.ajax({
			url:"${base}/healthyplan/healthyplan!getGoodsByGoodsno.action",
			type:"post",
			data:{"goodsno":goodsno},
			success:function(data){
			   if(data.flag=='1'){
			       if(checkGoodsId(data.goods.id)){
			       var trhtml="<tr id=\"goodsTr"+data.goods.id+"\" style=\"height:40px\"><td align=\"center\"><input name=\"goodsids\"  value=\""+data.goods.id+"\" type=\"hidden\"/>"+data.goods.goodsName+"</td><td align=\"center\">"+data.goods.goodsno+"</td><td align=\"right\">"+data.goods.spec+"</td><td>"+data.goods.price+"</td><td>"+data.pcPrice+"</td><td>"+data.wapPrice+"</td><td>"+data.appPrice+"</td><td><input name=\"goodsWeights\"  value=\"0\" class=\"input input_v1\" maxlength=\"9\" style=\"width:50px;\"/></td><td align=\"center\"><a href=\"javascript:deleteTr("+data.goods.id+")\">删除</a></td></tr>";
			       $("#"+table_id).append(trhtml);
			       }
			   }
			   if(data.flag == "2"){
			   	   $alert("warn","该药品为处方药，不允许添加！");
			   }
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
     }
}
//判断商品id
function checkGoodsId(goodsid){
   var goodsids = document.getElementsByName("goodsids");
   if(goodsids.length>0){
      for(var i=0;i<goodsids.length;i++){
         if(goodsids[i].value==goodsid){
            return false;
         }
      }
   }
   return true;
}
//删除商品行
function deleteTr(goodsid){
   $("#goodsTr"+goodsid).remove(); 
}
//检查提交的form表单
function checkHealthyForm(){
   
   //套餐名称
   var thealthyplansymptom_package_name =$.trim($("#thealthyplansymptom_package_name").val());
   if(thealthyplansymptom_package_name==''){
       $alert("warn","请填写套餐名称！");
       return false;
   }
   
   
   //权重
   var thealthyplancombination_weight =$.trim($("#thealthyplancombination_weight").val());
   if(thealthyplancombination_weight==''){
       $alert("warn","请填写权重！");
       return false;
   }else{
      var re = /^[0-9]*$/;
		if (!re.test(thealthyplancombination_weight)) {
			 $alert("warn","权重只能输入数字！");
			return false;
	    } 
   }
   //药师
   var thealthyplancombination_pharmacistId =$.trim($("#thealthyplancombination_pharmacistId").val());
   if(thealthyplancombination_pharmacistId==''){
       $alert("warn","请选择药师！");
       return false;
   }
   //药师点评
   var thealthyplancombination_comment_content =$.trim($("#thealthyplancombination_comment_content").val());
   if(thealthyplancombination_comment_content==''){
       $alert("warn","请填写药师点评！");
       return false;
   }else{
       if(thealthyplancombination_comment_content.length >600){
         $alert("warn","药师点评最多可输入600个字符！");
         return false;
      }
   }
   
   var goodsids = document.getElementsByName("goodsids");
   if(goodsids.length==0){
       $alert("warn","请添加商品！");
       return false;
   }else{
      var goodsWeights = document.getElementsByName("goodsWeights");
       var re = /^[0-9]*$/;
      for(var i=0;i<goodsWeights.length;i++){
         if (!re.test($.trim(goodsWeights[i].value))) {
			$alert("warn","商品权重只能输入数字！");
			return false;
	    } 
      }
   }
   
   return true;
}

function ReturnMsg(status){
	 if(status==0){
	     $alert("error","保存失败！");
	 }else if(status>0){
	    $("#thealthyplancombinationId").val(status);
	    $alert("success","保存成功！");
	    window.opener.ajaxSelectCombination();
	    window.close();
	 }
 }
	
	
	
	
</script>
</html>