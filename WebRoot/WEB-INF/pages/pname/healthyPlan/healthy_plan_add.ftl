<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加健康方案</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" href="${base}/web/plugins/jquery-ui-1.8.14.custom/css/ui-lightness/jquery-ui-1.8.14.custom.css" rel="stylesheet" /> 
	 <#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<script type="text/javascript" src="${base}/web/js/ajaxfileupload.js"></script>
		<style type="text/css">
body { 
	background: #f0f0f0; 
	margin: 0; 
	padding: 0; 
	font: 10px normal Verdana, Arial, Helvetica, sans-serif; 
	color: #444; 
} 
h1 {font-size: 3em; margin: 20px 0;} 
.container {width: 500px; margin: 10px auto;} 
	ul.tabs { 
	margin: 0; 
	padding: 0; 
	float: left; 
	list-style: none; 
	height: 32px; 
	border-bottom: 1px solid #999; 
	border-left: 1px solid #999; 
	width: 100%; 
} 
ul.tabs li { 
	float: left; 
	margin: 0; 
	padding: 0; 
	height: 31px; 
	line-height: 31px; 
	border: 1px solid #999; 
	border-left: none; 
	margin-bottom: -1px; 
	background: #e0e0e0; 
	overflow: hidden; 
	position: relative; 
} 
ul.tabs li a { 
	text-decoration: none; 
	color: #000; 
	display: block; 
	font-size: 1.2em; 
	padding: 0 20px; 
	border: 1px solid #fff; 
	outline: none; 
} 
ul.tabs li a:hover { 
	background: #ccc; 
} 
html ul.tabs li.active, html ul.tabs li.active a:hover { 
	background: #fff; 
	border-bottom: 1px solid #fff; 
} 
.tab_container { 
	border: 1px solid #999; 
	border-top: none; 
	clear: both; 
	float: left; 
	width: 100%; 
	background: #fff; 
	-moz-border-radius-bottomright: 5px; 
	-khtml-border-radius-bottomright: 5px; 
	-webkit-border-bottom-right-radius: 5px; 
	-moz-border-radius-bottomleft: 5px; 
	-khtml-border-radius-bottomleft: 5px; 
	-webkit-border-bottom-left-radius: 5px; 
	border-left-width: 1px; border-bottom-width: 1px; border-right-width: 1px;
} 
.tab_content { 
padding: 20px; 
font-size: 1.2em; 
} 
.tab_content h2 { 
font-weight: normal; 
padding-bottom: 10px; 
border-bottom: 1px dashed #ddd; 
font-size: 1.8em; 
} 
.tab_content h3 a{ 
color: #254588; 
} 
.tab_content img { 
float: left; 
margin: 0 20px 20px 0; 
border: 1px solid #ddd; 
padding: 5px; 
} 
input{height:27px;width:180px;padding-left:3px; border: 1px solid #cbcbcb;margin-left: 10px;}
</style>
</head> 

<body onload="ajaxOnload()"> 

<iframe name="bankmessage"  src="" height="0px" width="0px"></iframe>


<div>
   <input type="button"   id="buttonsub" value="返回" class="btn05" onclick="goback()" /> 
	<div id="nav">
		<ul class="tabs"> 
			<li><a href="#tab1">健康方案</a></li> 
			<li><a href="#tab2">伴随症</a></li> 
			<li><a href="#tab3">用药套餐</a></li> 
		</ul>  
	</div>
	
	<div class="tab_container" id="shop_main11"> 
		<div class="tab_container" style="border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px;"> 
				<div id="tab1" class="tab_content">
				<form action="${base}/healthyplan/healthyplan!healthyPlanSave.action" method="post" onsubmit="return checkHealthyForm()"  name="form1" id="form1"  target="bankmessage" enctype="multipart/form-data" >
					 <input name="fileflag" id="fileflag" value="" type="hidden" />
					 <input name="thealthyplan.id" id="thealthyplanId" value="${thealthyplan.id?default('')}" type="hidden" />
					 <table border="1" cellspacing="0" cellpadding="0" width="95%" style="border-color:rgba(218, 228, 243, 0.44);"> 
			          <tr style="height:40px"> 
			           <td align="center" style="width:15%"><span style="color:red">*</span>选择分类:</td> 
			           <td>
			                <select name="thealthyplan.classificationId" id="thealthyplan_classificationId" style="height:27px;width:200px;margin-left: 10px;">
								 <option value="">全部</option>
								 <#list classificationList?if_exists as classification>
								    <option value="${classification.id}" <#if thealthyplan.classificationId?exists><#if classification.id==thealthyplan.classificationId>selected</#if></#if>>${classification.classificationName}</option>
								 </#list>
							 </select>
			            </td> 
			           <td align="center" style="width:15%"><span style="color:red">*</span>方案名称:</td> 
			           <td>
			               <input name="thealthyplan.name" style="" id="thealthyplan_name" maxlength="15" value="${thealthyplan.name?default('')}"  />
			           </td> 
			          </tr> 
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%"><span style="color:red">*</span>缩略图:</td> 
			           <td colspan="3">
			                <img src="${thealthyplan.planImgUrl?default('')}" id="img0" style="<#if thealthyplan.planImgUrl?exists><#else>display: none;</#if> width:150px;height:150px">
							<img src="" id="img1" style="display: none; width:150px;height:150px">
							<input type="file" class="file" name="uploadify" id="file" style="width:150px;" />
							<span style="color:red">(支持jpg,png,jpeg,gif格式图片,大小控制在100K以内（必填）)</span>
							<span style="color:red" id="sidimg"></span>
			           </td> 
			           </tr> 
			          <tr style="height:40px">
			           <td align="center" style="width:15%">Banner图:</td> 
			           <td colspan="3">
			                <img src="${thealthyplan.bannerImgUrl?default('')}" id="img2" style="<#if thealthyplan.bannerImgUrl?exists><#else>display: none;</#if> width:150px;height:150px">
							<img src="" id="img3" style="display: none; width:150px;height:150px">
							<input type="file" class="file" name="uploadify" id="file1" style="width:150px;"/>
							<span style="color:red">(支持jpg,png,jpeg,gif格式图片,大小控制在100K以内，不上传则不显示Banner.)</span>
							<span style="color:red" id="sidimg1"></span>
			           </td> 
			          </tr> 
			          <tr style="height:40px">
			           <td align="center" style="width:15%"><span style="color:red">*</span>微信分享图:</td> 
			           <td colspan="3">
			                <img src="${thealthyplan.wxShareUrl?default('')}" id="img4" style="<#if thealthyplan.wxShareUrl?exists><#else>display: none;</#if> width:150px;height:150px">
							<img src="" id="img5" style="display: none; width:150px;height:150px">
							<input type="file" class="file" name="wxfile" id="wxfile" style="width:150px;"/>
							<span style="color:red">(支持jpg,png,jpeg,gif格式图片,必填.)</span>
							<span style="color:red" id="sidimg2"></span>
			           </td> 
			          </tr> 
			           <tr style="height:40px"> 
			          <td align="center" style="width:15%"><span style="color:red">*</span>权重:</td> 
			           <td>
			                <input name="thealthyplan.weight" style="" maxlength="9" id="thealthyplan_weight" value="${thealthyplan.weight?default(0)}"  />
			           </td> 
			           <td align="center" style="width:15%"></td> 
			           <td>
			           </td> 
			          </tr>
			           <tr style="height:40px"> 
			          <td align="center" style="width:15%"><span style="color:red">*</span>商品分类:</td> 
			           <td colspan="3">
			               <input type="hidden" id="categoryId" value="${thealthyplan.goodsCategoryId?default('')}" name="thealthyplan.goodsCategoryId"/>
			             <select onchange="getfl(1)" id="fl1">
						  <option value ="fl1">请选择</option>
						    ${fl1?default('')}
						</select>
						<select onchange="getfl(2)" id="fl2"  style="display:none">
						    <#if fl2?exists>${fl2?default('')}</#if>
						</select>
						<select onchange="getfl(3)" id="fl3" style="display:none">
						    <#if fl3?exists>${fl3?default('')}</#if>
						</select>
			           </td> 
			           </td> 
			          </tr>
			           <tr style="height:40px"> 
			          	<td align="center" style="width:15%"><span style="color:red">*</span>微信分享语:</td> 
			           <td colspan="3">
			               <textarea class="taa-ui"  name="thealthyplan.wxShareContent" id="thealthyplan_wxShareContent">${thealthyplan.wxShareContent?default('')}</textarea>
			           </td> 
			          </tr>
			           <tr style="height:40px"> 
			          	<td align="center" style="width:15%"><span style="color:red">*</span>症状介绍:</td> 
			           <td colspan="3">
			               <textarea class="taa-ui"  name="thealthyplan.planExplain" id="thealthyplan_planExplain">${thealthyplan.planExplain?default('')}</textarea>
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          	<td align="center" style="width:15%">温馨提示:</td> 
			           <td colspan="3">
			               <textarea class="taa-ui" name="thealthyplan.reminder" id="thealthyplan_reminder">${thealthyplan.reminder?default('')}</textarea>
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
	    		<div id="tab2" class="tab_content"> 
	    		 <table class="table-list"> 
			        <tr style="height:40px">
			             <td align="left" width="20%">
			             <input type="button" id="add_bansuizheng"  onclick="addSymptom('')"  class="btn02" style="width: 79px;" value="添加" />
			             </td>
			          </tr>
			     </table>
	    		 <table class="table-list"> 
			       <thead style="background:#dbf1fc"> 
			        <tr> 
			         <th style="width:150px;">伴随症名称 </th> 
			         <th>药品</th> 
			         <th style="width:100px;">权重</th>
			         <th style="width:100px;">操作</th>
			        </tr> 
			       </thead> 
			       <tr> <td colspan="4" class="blank"></td></tr> 
			       <tbody id="bansuizheng_tbody"> 
			       
			       
			       </tbody> 
		       	</table>
	    		</div>
    		<div id="tab3" class="tab_content"> 
    		 <table class="table-list"> 
			    <tr style="height:40px">
	             <td align="left" width="20%">
	                <input type="button" id="add_bansuizheng"  onclick="addCombination('')"  class="btn02" style="width: 79px;" value="添加" />
	             </td>
			    </tr>
			  </table>
    		   <table class="table-list"> 
		       <thead style="background:#dbf1fc"> 
		        <tr> 
		         <th style="width:150px;">套餐名称</th> 
		         <th>药品</th> 
		         <th style="width:100px;">权重</th> 
		         <th style="width:100px;">操作</th>
		        </tr> 
		       </thead> 
		       <tr> <td colspan="4" class="blank"></td></tr> 
		       <tbody id="taocan_tbody"> 
		        
		        
		       </tbody> 
		      </table>
    		</div>
    	</div> 
    </div>  
</div>
</body>
<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript">

  $('#refunding').dialog({
		autoOpen: false,
		width: 470,
		title:'发货',
		resizable: false,
		modal: true //遮蔽
	});
		//弹出出库隐藏div
	function shipmentOrderView(){
	     $('#refunding').dialog('open');
	}
		$(function(){
			//Default Action 
			$(".tab_content").hide(); //Hide all content 
			$("ul.tabs li:first").addClass("active").show(); //Activate first tab 
			$(".tab_content:first").show(); //Show first tab content 
			//On Click Event 
			$("ul.tabs li").click(function() { 
				$("ul.tabs li").removeClass("active"); //Remove any "active" class 
				$(this).addClass("active"); //Add "active" class to selected tab 
				$(".tab_content").hide(); //Hide all tab content 
				var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content 
				$(activeTab).fadeIn(); //Fade in the active content 
				return false; 
			}); 
			if($("#thealthyplanId").val()!=''){
			   if($("#flv2").val()!=''){
					$("#fl2").show();
				}
				if($("#flv3").val()!=''){
					$("#fl3").show();
				}
			}
			
		})
		 
function goback(){
	  window.location.href="${base}/healthyplan/healthyplan!healthyPlanList.action";
}	
	
	//缩略图
$("#file").change(function(){
	var objUrl = getObjectURL(this.files[0],0) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img0").attr("src", objUrl);
    	$("#img1").attr("src",objUrl);
		$("#img0").show("slow");
		$("#fileflag").val("1");
	}
}) ;	
	//Banner图
$("#file1").change(function(){
	var objUrl = getObjectURL(this.files[0],1) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img2").attr("src", objUrl);
    	$("#img3").attr("src",objUrl);
		$("#img2").show("slow");
		$("#fileflag").val("2");
	}
}) ;

//微信分享图
$("#wxfile").change(function(){
	var objUrl = getObjectURL(this.files[0],1) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img4").attr("src", objUrl);
    	$("#img5").attr("src",objUrl);
		$("#img4").show("slow");
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
 var planId = $("#thealthyplanId").val();
  //选择分类
   var thealthyplan_classificationId =$("#thealthyplan_classificationId").val();
   if(thealthyplan_classificationId==''){
       $alert("warn","请选择分类！");
       return false;
   }
   
   //方案名称
   var thealthyplan_name =$.trim($("#thealthyplan_name").val());
   if(thealthyplan_name==''){
       $alert("warn","请填写方案名称！");
       return false;
   }
   
   //缩略图
   var file =$.trim($("#file").val());
   if(file==''&&planId==''){
       $alert("warn","请添加缩略图！");
       return false;
   }
    //微信分享图
   var wxfile =$.trim($("#wxfile").val());
   if(wxfile==''&&planId==''){
       $alert("warn","请添加微信分享图！");
       return false;
   }
   
   //权重
   var thealthyplan_weight =$.trim($("#thealthyplan_weight").val());
   if(thealthyplan_weight==''){
       $alert("warn","请填写权重！");
       return false;
   }else{
      var re = /^[0-9]*$/;
		if (!re.test(thealthyplan_weight)) {
			 $alert("warn","权重只能输入数字！");
			return false;
	    } 
   }
   
    //微信分享语
   var thealthyplan_wxShareContent =$.trim($("#thealthyplan_wxShareContent").val());
   if(thealthyplan_wxShareContent==''){
       $alert("warn","请填微信分享语！");
       return false;
   }else{
      if(thealthyplan_wxShareContent.length >26){
         $alert("warn","微信分享语最多可输入26个字符！");
         return false;
      }
   }
   //症状介绍
   var thealthyplan_planExplain =$.trim($("#thealthyplan_planExplain").val());
   if(thealthyplan_planExplain==''){
       $alert("warn","请填写症状介绍！");
       return false;
   }else{
      if(thealthyplan_planExplain.length >600){
         $alert("warn","症状介绍最多可输入600个字符！");
         return false;
      }
   }
    //温馨提示
   var thealthyplan_reminder =$.trim($("#thealthyplan_reminder").val());
   if(thealthyplan_reminder!=''){
      if(thealthyplan_reminder.length >600){
         $alert("warn","温馨提示最多可输入600个字符！");
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
    var fl1 = $("#fl1").val();
	var fl2 = $("#fl2").val();
	var fl3 = $("#fl3").val();
	if("fl1"==fl1){
		$alert('warn',"请选择商品分类一级分类!");
		return false;
	}else{
		$("#categoryId").val(fl1);
	}
	if("fl2"==fl2){
		//$alert('warn',"请选择商品分类二级分类!");
		//return false;
	}else{
		if(""!=fl2&&fl2!=null){
			$("#categoryId").val(fl2);
		}
	}
	if("fl3"==fl3){
		//$alert('warn',"请选择商品分类三级分类!");
		//return false;
	}else{
		if(""!=fl3&&fl3!=null){
			$("#categoryId").val(fl3);
		}
	}
   
   
   return true;
}

function ReturnMsg(status){
	 if(status==0){
	     $alert("error","保存失败！");
	 }else if(status>0){
	    $("#thealthyplanId").val(status);
	    $alert("success","保存成功！");
	 }
 }


//添加套餐
function addCombination(combinationId){
  var thealthyplanId = $("#thealthyplanId").val();
  if(thealthyplanId!=''){
     window.open ("${base}/healthyplan/healthyplan!planCombinationAdd.action?planId="+thealthyplanId+"&combinationId="+combinationId, 'combinationIdnewwindow', "width=1000px,height=800px,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
  }else{
     $alert("warn","请先保存方案主信息！");
  }
}
function ajaxOnload(){
	ajaxSelectCombination();
	ajaxSelectBansuizheng();
}

//动态查询套餐
function ajaxSelectCombination(){
   var planId = $("#thealthyplanId").val();
   if(planId!=''){
        $("#taocan_tbody").html("");
        $.ajax({
			url:"${base}/healthyplan/healthyplan!ajaxPlanCombination.action",
			type:"post",
			data:{"planId":planId},
			success:function(data){
			   if(data.flag=='1'){
			        var trhtml="";
					    for(var i=0;i<data.list.length;i++){
						  trhtml+="<tr><td>"+data.list[i].package_name+"</td><td align=\"center\">"+data.list[i].goodsName+"</td><td align=\"center\">"+data.list[i].weight+"</td><td align=\"center\"><a href=\"javascript:addCombination("+data.list[i].id+")\">修改</a>|<a href=\"javascript:deleteCombination("+data.list[i].id+")\">删除</a></td></tr>";
						}
			        $("#taocan_tbody").append(trhtml);
			   }
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
   }
}	
//删除套餐
function deleteCombination(combinationId){
 if(confirm("确定要删除该套餐吗？")){
     $.ajax({
			url:"${base}/healthyplan/healthyplan!ajaxDeletePlanCombination.action",
			type:"post",
			data:{"combinationId":combinationId},
			success:function(data){
			   if(data=='1'){
			     $alert("success","删除成功！");
			     ajaxSelectCombination();
			   }
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
 }

}
	
	
//添加伴随证
function addSymptom(symptomId){
  var thealthyplanId = $("#thealthyplanId").val();
  if(thealthyplanId!=''){
     window.open ("${base}/healthyplan/healthyplan!planSymptomAdd.action?planId="+thealthyplanId+"&symptomId="+symptomId, 'newwindow', "width=900px,height=600px,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
  }else{
     $alert("warn","请先保存方案主信息！");
  }
}	

//动态查询伴随症状
function ajaxSelectBansuizheng(){
   var planId = $("#thealthyplanId").val();
   if(planId!=''){
        $("#bansuizheng_tbody").html("");
        $.ajax({
			url:"${base}/healthyplan/healthyplan!ajaxPlanSymptom.action",
			type:"post",
			data:{"planId":planId},
			success:function(data){
			   if(data.flag=='1'){
			        var trhtml="";
						for(var i=0;i<data.list.length;i++){
						  trhtml+="<tr id=\"bansuizhengTr"+data.list[i].id+"\"><td>"+data.list[i].name+"</td><td align=\"center\">"+data.list[i].goodsName+"</td><td align=\"center\">"+data.list[i].weight+"</td><td align=\"center\"><a href=\"javascript:addSymptom("+data.list[i].id+")\">修改</a>|<a href=\"javascript:deleteBansuizheng("+data.list[i].id+")\">删除</a></td></tr>";
						}
			        $("#bansuizheng_tbody").append(trhtml);
			   }
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
   }
}	
//删除伴随症状
function deleteBansuizheng(symptomId){
 if(confirm("确定要删除该伴随症吗？")){
 $.ajax({
			url:"${base}/healthyplan/healthyplan!ajaxDeletePlanSymptom.action",
			type:"post",
			data:{"symptomId":symptomId},
			success:function(data){
			   if(data=='1'){
			     $alert("success","删除成功！");
			     ajaxSelectBansuizheng();
			   }
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
		}
}
	
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