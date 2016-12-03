<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加伴随症</title> 
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
				<form action="${base}/healthyplan/healthyplan!planSymptomSave.action" method="post" onsubmit="return checkHealthyForm()"  name="form1" id="form1"  target="bankmessage" enctype="multipart/form-data" >
					 <input name="thealthyplansymptom.id" id="thealthyplansymptomId" value="${thealthyplansymptom.id?default('')}" type="hidden" />
					 <input name="thealthyplansymptom.planId" id="thealthyplansymptom_planId" value="${thealthyplansymptom.planId?default('')}" type="hidden" />
					 <table border="1" cellspacing="0" cellpadding="0" width="95%" style="border-color:rgba(218, 228, 243, 0.44);"> 
			          <tr style="height:40px"> 
			           <td align="center" style="width:15%"><span style="color:red">*</span>伴随症名称:</td> 
			           <td>
			                <input name="thealthyplansymptom.name" style="" maxlength="6" id="thealthyplansymptom_name" value="${thealthyplansymptom.name?default('')}"  />
			           </td> 
			           <td align="center" style="width:15%"><span style="color:red">*</span>权重:</td> 
			           <td>
			                <input name="thealthyplansymptom.weight" style="" maxlength="9" id="thealthyplansymptom_weight" value="${thealthyplansymptom.weight?default(0)}"  />
			           </td> 
			          </tr> 
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">banner图:</td> 
			           <td colspan="3">
			                <img src="${thealthyplansymptom.bannerImgUrl?default('')}" id="img0" style="<#if thealthyplansymptom.bannerImgUrl?exists><#else>display: none;</#if>width:150px;height:150px">
							<img src="" id="img1" style="display: none; width:150px;height:150px">
							<input type="file" class="file" name="uploadify" id="file" style="width:150px;"/>
							<span style="color:red">(支持jpg,png,jpeg,gif格式图片)</span>
							<span style="color:red" id="sidimg"></span>
			           </td> 
			           </tr> 
			           <tr style="height:40px"> 
			          	<td align="center" style="width:15%">说明:</td> 
			           <td colspan="3">
			               <textarea class="taa-ui" name="thealthyplansymptom.symptomExplain" id="thealthyplansymptom_symptomExplain">${thealthyplansymptom.symptomExplain?default('')}</textarea>
			           </td> 
			          </tr>
			        </table>
			        <table class="table-list"> 
			        <tr style="height:40px">
			             <td align="center" width="20%">海典编号:</td>
			             <td colspan="5" id="sbtd" width="80%">
							<input type="text" id="healthy_goods_goodsno" name="healthy_goods_goodsno"  />
							<input type="button" id="addOrderItem" onclick="getGoodsByGoodsno('healthy_goods_goodsno','healthy_goods_tbody',3)"  class="btn02" style="width: 79px;" value="添加商品" />
						    <span style="color:red">最多添加三个主药(必填)</span>
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

	
	//缩略图
$("#file").change(function(){
	var objUrl = getObjectURL(this.files[0],0) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img0").attr("src", objUrl);
    	$("#img1").attr("src",objUrl);
		$("#img0").show("slow");
	}
}) ;	
function getObjectURL(file,type) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	if(url!=null){
	    if(type==1){
	      $("#sidimg1").html("");
	    }else{
	    $("#sidimg").html("");
	    }
		
	}
	return url ;
}	
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
			       var trhtml="<tr id=\"goodsTr"+data.goods.id+"\" style=\"height:40px\"><td align=\"center\"><input name=\"goodsids\"  value=\""+data.goods.id+"\" type=\"hidden\"/>"+data.goods.goodsName+"</td><td align=\"center\">"+data.goods.goodsno+"</td><td align=\"right\">"+data.goods.spec+"</td><td>"+data.goods.price+"</td><td>"+data.pcPrice+"</td><td>"+data.wapPrice+"</td><td>"+data.appPrice+"</td><td><input name=\"goodsWeights\" maxlength=\"9\" value=\"0\" class=\"input input_v1\" style=\"width:50px;\"/></td><td align=\"center\"><a href=\"javascript:deleteTr("+data.goods.id+")\">删除</a></td></tr>";
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
   //方案名称
   var thealthyplansymptom_name =$.trim($("#thealthyplansymptom_name").val());
   if(thealthyplansymptom_name==''){
       $alert("warn","请填写伴随症名称！");
       return false;
   }
   
   
   //权重
   var thealthyplansymptom_weight =$.trim($("#thealthyplansymptom_weight").val());
   if(thealthyplansymptom_weight==''){
       $alert("warn","请填写权重！");
       return false;
   }else{
      var re = /^[0-9]*$/;
		if (!re.test(thealthyplansymptom_weight)) {
			 $alert("warn","权重只能输入数字！");
			return false;
	    } 
   }
   
   
   //说明
   var thealthyplansymptom_symptomExplain =$.trim($("#thealthyplansymptom_symptomExplain").val());
   if(thealthyplansymptom_symptomExplain!=''){
      if(thealthyplansymptom_symptomExplain.length >600){
         $alert("warn","说明最多可输入600个字符！");
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
	    $("#thealthyplansymptomId").val(status);
	    $alert("success","保存成功！");
	    window.opener.ajaxSelectBansuizheng();
	    window.close();
	 }
 }
</script>
</html>