<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加方案分类</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" href="${base}/web/plugins/jquery-ui-1.8.14.custom/css/ui-lightness/jquery-ui-1.8.14.custom.css" rel="stylesheet" /> 
	 <#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	<script type="text/javascript" src="${base}/web/js/ajaxfileupload.js"></script>
	<style type="text/css">
     </style>
</head> 
<body> 
<div>
	<div class="tab_content">
				<form action="${base}/healthyplan/plan_category!saveOrUpdateClassification.action" method="post"   name="form1" id="form1"  >
					 <input name="planClassification.id" id="planClassificationId" value="${planClassification.id?default('')}" type="hidden" />
					 <input name="planClassification.classificationLevel" id="planClassification_classificationLevel" value="1" type="hidden" />
					 <input name="planClassification.status" id="planClassification_status" value="1" type="hidden" />
					 <input name="planClassification.type" id="planClassification_type" value="1" type="hidden" />
					 <table border="1" cellspacing="0" cellpadding="0" width="95%" style="border-color:rgba(218, 228, 243, 0.44);"> 
			          <tr style="height:40px"> 
			           <td align="center" style="width:15%"><span style="color:red">*</span>分类名称:</td> 
			           <td>
			                <input name="planClassification.classificationName" style="height:27px;width:180px;padding-left:3px; border: 1px solid #cbcbcb;margin-left: 10px;" maxlength="6" id="planClassification_classificationName" value="${planClassification.classificationName?default('')}"  />
			           </td> 
			           <td align="center" style="width:15%"><span style="color:red">*</span>权重:</td> 
			           <td>
			                <input name="planClassification.weight" style="height:27px;width:180px;padding-left:3px; border: 1px solid #cbcbcb;margin-left: 10px;" maxlength="9" id="planClassification_weight" value="${planClassification.weight?default(0)}"  />
			           </td> 
			          </tr> 
			            <#--<tr style="height:40px"> 
			          	<td align="center" style="width:15%"><span style="color:red">*</span>类型:</td> 
				           <td colspan="3">
				                <select name="planClassification.type" id="planClassification_type" style="height:27px;width:200px;margin-left: 10px;">
								    <option value="1" <#if planClassification.type?exists><#if planClassification.type==1>selected</#if></#if>>普通分类</option>
								    <option value="2" <#if planClassification.type?exists><#if planClassification.type==2>selected</#if></#if>>推荐分类</option>
							    </select>
				           </td> 
			          </tr>-->
			           <tr style="height:40px"> 
			          	<td align="center" style="width:15%">描述:</td> 
			           <td colspan="3">
			               <textarea class="taa-ui" name="planClassification.content" id="planClassification_content">${planClassification.content?default('')}</textarea>
			           </td> 
			          </tr>
			        </table>
			      <table class="table-list"> 
			      <tr style="height:40px" >
		             <td colspan="4" width="80%" align="center">
						<input name="receiverAdd" type="button"  onclick="saveClassification()" class="btn01" value="提交 "/>
						<input name="receiverAdd" type="button"  onclick="fanhui()" class="btn01" value="返回 "/>
					</td>
		          </tr>
		        </table>
			      </form> 
		        </div>
</div>
</body>
<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript">

function fanhui(){
window.location.href="${base}/healthyplan/plan_category!list.action";
}

//检查提交的form表单
function checkHealthyForm(){
   
   //分类名称
   var planClassification_classificationName =$.trim($("#planClassification_classificationName").val());
   if(planClassification_classificationName==''){
       $alert("warn","请填写分类名称！");
       return false;
   }
   
   
   //权重
   var planClassification_weight =$.trim($("#planClassification_weight").val());
   if(planClassification_weight==''){
       $alert("warn","请填写权重！");
       return false;
   }else{
      var re = /^[0-9]*$/;
		if (!re.test(planClassification_weight)) {
			 $alert("warn","权重只能输入数字！");
			return false;
	    } 
   }
    //描述
   var planClassification_content =$.trim($("#planClassification_content").val());
   if(planClassification_content!=''){
      if(planClassification_content.length >50){
         $alert("warn","说明最多可输入50个字符！");
         return false;
      }
   }
   return true;
}
function saveClassification(){
    if(checkHealthyForm()){
       var formInfo=$("#form1").serialize();
		$.ajax({
			url:"${base}/healthyplan/plan_category!saveOrUpdateClassification.action",
			type:"post",
			data:formInfo,
			success:function(data){
				if(data!=0){ //'1'更新,'2'修改
					  $alert("success","提交成功");
					fanhui();
				}else{
					 $alert("error","提交失败！");
				}
			},
			error:function(){
				  $alert("error","提交失败！");
			}
		});
    }
	
}



</script>
</html>