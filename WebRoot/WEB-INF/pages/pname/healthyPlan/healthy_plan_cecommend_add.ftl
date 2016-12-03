<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>完善推荐方案</title> 
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
				<form action="${base}/healthyplan/healthyplan!healthyPlanRecommendSave.action" method="post" onsubmit="return checkHealthyForm()"  name="form1" id="form1"  target="bankmessage" enctype="multipart/form-data" >
					 <input name="thealthyplanclassification.id" id="classId" value="${thealthyplanclassification.id?default('')}" type="hidden" />
					 <table class="table-list"> 
			          <tr style="height:40px"> 
			           <td align="center" style="width:15%">分类名称:</td> 
			           <td >
			                ${thealthyplanclassification.classificationName?default('')}
			           </td> 
			           <td colspan="3">
							<input type="button" id="addOrderItem" onclick="recommendAdd()"  class="btn02" style="width: 79px;" value="添加方案" />
						  </td>
			          </tr> 
			         </table>
			        <table class="table-list"> 
			       <thead style="background:#dbf1fc"> 
			        <tr> 
			         <th> 方案名称 </th> 
			         <th>发布状态</th>
			         <th> 权重</th>
			         <th>操作</th>
			        </tr> 
			       </thead> 
			       <tbody id="healthy_goods_tbody"> 
			            <#list list?if_exists as resc>
			              <tr id="goodsTr${resc.recommendId}" style="height:40px">
				              <td align="center"><input name="goodsids"  value="${resc.recommendId}" type="hidden"/>${resc.name?default('')}</td>
				              <td align="center">
				                  <#if resc.status?exists>
						         	<#if resc.status?default(0)==0>
						         	未发布
						         	<#else>
						         	已发布
						         	</#if>
						         </#if>
				                </td>
				              <td><input name="goodsWeights" maxlength="9" value="${resc.recommendWeight?default(0)}" class="input input_v1" style="width:200px;"/></td>
				              <td align="center"><a href="javascript:deleteTr(${resc.recommendId})">删除</a></td>
			              </tr> 
			            </#list>
			       </tbody> 
			      </table>
			      <table class="table-list"> 
			      <tr style="height:40px" >
		             <td colspan="4" width="80%" align="center">
						<input name="receiverAdd" type="submit"  class="btn01"  value="提交 "/>
						<input name="receiverAdd" type="button" onclick="goback()"  class="btn01"  value="返回 "/>
					</td>
		          </tr>
		        </table>
			      </form> 
		        </div>
</div>

</body>
<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript">

function goback(){
	  window.location.href="${base}/healthyplan/healthyplan!healthyPlanRecommendList.action";
}	

//跳转到推荐方案添加页面
function recommendAdd(){
  window.open ("${base}/healthyplan/healthyplan!healthyPlanListOpen.action", 'recommend_open', "width=900px,height=600px,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
}
//保存多个推荐方案
function saveRecommend(planId){
  var classId = $("#classId").val();
  $.ajax({
		url:"${base}/healthyplan/healthyplan!ajaxSaveRecommend.action?planId="+planId,
		type:"post",
		data:{"classId":classId},
		success:function(data){
		   if(data=='1'){
		      $alert("success","添加成功！");
		      ajaxRecommend();
		   }
		},
		error:function(){
		      $alert("error","系统异常，请稍后再试！");
		}
	});
}



//刷新推荐方案
function ajaxRecommend(){
     var  classId = $("#classId").val();
     $.ajax({
		url:"${base}/healthyplan/healthyplan!ajaxRecommendList.action",
		type:"post",
		data:{"classId":classId},
		success:function(data){
		   if(data.flag=='1'){
		   var trhtml="";
		       for(var i=0;i<data.list.length;i++){
				  trhtml+="<tr id=\"goodsTr"+data.list[i].recommendId+"\" style=\"height:40px\"><td align=\"center\"><input name=\"goodsids\"  value=\""+data.list[i].recommendId+"\" type=\"hidden\"/>"+data.list[i].name+"</td>";
				  if(data.list[i].status==0){
				   trhtml+="<td align=\"center\">未发布</td>";
				  }else{
				   trhtml+="<td align=\"center\">已发布</td>";
				  }
				  trhtml+="<td><input name=\"goodsWeights\" maxlength=\"9\" value=\""+data.list[i].recommendWeight+"\" class=\"input input_v1\" style=\"width:200px;\"/></td>"+
				              "<td align=\"center\"><a href=\"javascript:deleteTr("+data.list[i].recommendId+")\">删除</a></td>"+
			              "</tr>";
			   }
			  $("#healthy_goods_tbody").html(trhtml);
		   }
		},
		error:function(){
		      $alert("error","系统异常，请稍后再试！");
		}
	});
}


//删除推荐方案
function deleteTr(recommendId){
  if(confirm("确定要删除吗？")){
    $.ajax({
			url:"${base}/healthyplan/healthyplan!ajaxDeleteRecommend.action",
			type:"post",
			data:{"recommendId":recommendId},
			success:function(data){
			   if(data=='1'){
			      $alert("success","删除成功！");
			      // $("#goodsTr"+recommendId).remove(); 
			      ajaxRecommend();
			   }
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
		}
}
//检查提交的form表单
function checkHealthyForm(){
   var goodsids = document.getElementsByName("goodsids");
   if(goodsids.length==0){
       $alert("warn","请添加健康方案！");
       return false;
   }else{
      var goodsWeights = document.getElementsByName("goodsWeights");
       var re = /^[0-9]*$/;
      for(var i=0;i<goodsWeights.length;i++){
         if($.trim(goodsWeights[i].value)==''){
            $alert("warn","请填写权重值！");
			return false;
         }
         if (!re.test($.trim(goodsWeights[i].value))) {
			$alert("warn","权重只能输入数字！");
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
	     $alert("success","保存成功！");
	 }
 }
	
</script>
</html>