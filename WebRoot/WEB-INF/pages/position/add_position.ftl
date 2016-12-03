<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>模板参数增加-编辑</title> 
  <#include "/WEB-INF/pages/inc/common.ftl">
 </head> 
 <style>
 	select{width:167px}
 </style>
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';margin-left: 10px;margin-top: 10px;"> 
   <div class="sm_user"> 
     <!-- 模板基础参数开始-->
      <div class="tbl_form"> 
     <form action="" method="get" name="addInfo" id="addInfo">
      <#if cposition?exists><input type="hidden" name="cposition.id" value="${cposition.id?default(0)}"/></#if>
      <input type="hidden" name="cposition.issue" value="0"/>
	  <input type="hidden" name="channel" value="${channel?default(0)}"/>
	   
      <TABLE class="table-list">
       <colgroup> 
          <col style="width:8%" /> 
          <col style="width:22%" /> 
         </colgroup> 
         <tbody> 
	  	<#if rule.img.ishow=='1'>
		  <TR class="content">
			<TD >${rule.img.name?if_exists}：</TD>
			<TD ><input class="input"  style="width:245px;height: 30px;" type="text" name="cposition.imgUrl" <#if cposition?exists>value="${cposition.imgUrl?default('')}"  </#if>/></TD>
		  </TR>
		</#if>
		<#if rule.img_url.ishow=='1'>
		  <TR class="content">
			<TD >${rule.img_url.name?if_exists}：</TD>
			<TD><input  class="input " style="width:245px;height: 30px;" type="text" name="cposition.imgLink" <#if cposition?exists>value="${cposition.imgLink?default('')}"  </#if>/></TD>
		  </TR>
		 </#if>
		 <#if rule.title.ishow=='1'>
		  <TR class="content">
			<TD >${rule.title.name?if_exists}：</TD>
			<TD><input  class="input " style="width:245px;height: 30px;" type="text" name="cposition.title" <#if cposition?exists>value="${cposition.title?default('')}"  </#if>/></TD>
		  </TR>
		  </#if>
		  <#if rule.title_url.ishow=='1'>
		  <TR class="content">
			<TD >${rule.title_url.name?if_exists}：</TD>
			<TD><input  class="input " style="width:245px;height: 30px;" type="text" name="cposition.titleUrl" <#if cposition?exists>value="${cposition.titleUrl?default('')}"  </#if>/></TD>
		  </TR>
		  </#if>
		  <#if rule.txt.ishow=='1'>
		  <TR class="content">
			<TD >${rule.txt.name?if_exists}：</TD>
			<TD><TEXTAREA NAME="cposition.txt" ROWS="30" style="width:450px;overflow:auto;resize:none;"><#if cposition?exists>${cposition.txt?default('')}</#if></TEXTAREA></TD>
		  </TR>
		  </#if>
		  <#if rule.txt_url.ishow=='1'>
		  <TR class="content">
			<TD >${rule.txt_url.name?if_exists}：</TD>
			<TD><input  class="input " style="width:245px;height: 30px;" type="text" name="cposition.txtLink" <#if cposition?exists>value="${cposition.txtLink?default('')}"  </#if>/></TD>
		  </TR>
		  </#if>
		 <#if rule.title_all.ishow=='1'>
		  <TR class="content">
			<TD  >${rule.title_all.name?if_exists}：</TD>
			<TD><input class="input "  style="width:245px;height: 30px;" type="text" name="cposition.titleAll" <#if cposition?exists>value="${cposition.titleAll?default('')}"  </#if>/></TD>
		  </TR>
		  </#if>
		   <#if rule.price.ishow=='1'>
		  <TR class="content">
			<TD  >${rule.price.name?if_exists}：</TD>
			<TD><input class="input " style="width:245px;height: 30px;" type="text" name="cposition.price" <#if cposition?exists>value="${cposition.price?default('')}"  </#if>/></TD>
		  </TR>
		  </#if>
		  <#if rule.price_url.ishow=='1'>
		  <TR class="content">
			<TD  >${rule.price_url.name?if_exists}：</TD>
			<TD><input class="input " style="width:245px;height: 30px;" type="text" name="cposition.priceUrl" <#if cposition?exists>value="${cposition.priceUrl?default('')}"  </#if>/></TD>
		  </TR>
		  </#if>
		   <#if rule.market_price.ishow=='1'>
		  <TR class="content">
			<TD  >${rule.market_price.name?if_exists}：</TD>
			<TD><input class="input " style="width:245px;height: 30px;"  type="text" name="cposition.marketPrice" <#if cposition?exists>value="${cposition.marketPrice?default('')}"  </#if>/></TD>
		  </TR>
		  </#if>
		  	<TR class="content">
			<TD >权重：</TD>
			<TD><input  class="input " style="width:245px;height: 30px;" type="text" name="cposition.weight"  <#if cposition?exists>value="${cposition.weight?default('')}"  </#if>/></TD>
		  </TR>
		  <TR class="content">
			<TD ></TD>
			<TD ><input type="button" class="btn01" id="sub-btn" value="提交" onclick="addPositionInfo()"/> 
			<input type="button" class="btn01"  style="margin-right:0;" value="返回" onclick="location.href='${base}/position/managePosition!queryPostionInfo.action?id=${channel?default(0)}'" />
			</TD>
		  </TR>
		  </tbody>
  	</TABLE>
     </form>
     </div>
   </div> 
  </div>   
 </body>
 <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>	

//添加数据
	function addPositionInfo(){
		var formInfo=$("#addInfo").serialize();
		$("#sub-btn").val("..");
		$("#sub-btn").attr("disabled",true);
		$.ajax({
			url:"${base}/position/managePosition!addPositionInfo.action",
			type:"post",
			data:formInfo,
			success:function(data){
				if(data==1 || data==2){ //'1'更新,'2'修改
					$alert("success","恭喜，操作成功！");
					window.setTimeout(function(){
						location.href='${base}/position/managePosition!queryPostionInfo.action?id=${channel?default(0)}';
					},1000);
				}else{
					$alert("warn","操作失败，请稍后再试");
				}
				$("#sub-btn").val("提交");
				$("#sub-btn").attr("disabled",false);
			},
			error:function(){
				$alert("error","系统异常，请稍后再试");
				$("#sub-btn").val("提交");
				$("#sub-btn").attr("disabled",false);
			}
		});
		}

</script>
</html>