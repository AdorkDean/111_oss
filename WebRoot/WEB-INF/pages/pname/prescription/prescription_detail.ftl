<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>病例详细</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
<style type="text/css">
textarea{line-height: 20px;}
</style>
</head> 

<body> 
<div></div>
	<div class="shop_main" id="shop_main"> 
    <form method="post" name="addInfo" id="addInfo">
    	<input type="hidden" id="id" name="id" value="${tPrescription.id?if_exists}"/>
    	<div class="m-contents">
	    	<div class="labelnamese">创建时间：</div>
	    	<input type="text" name="createDate" disabled="true" class="i-text-i" id="createDate" style="" value="<#if tPrescription.createDate?exists>${tPrescription.createDate?string("yyyy-MM-dd HH:mm:ss")}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">修改时间：</div>
        		<input type="text" disabled="true" class="i-text-i" value="<#if tPrescription.modifyDate?exists>${tPrescription.modifyDate?string("yyyy-MM-dd HH:mm:ss")}</#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">手机号：</div>
	    	<input type="text" name="phone" disabled="true" class="i-text-i" id="phone"  value="${tPrescription.phone?if_exists}"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">病情描述：</div>
	    	<textarea type="text" name="diseaseDescrip" style="line-height: 20px;height:120px;" disabled="true" class="i-text-i" id="diseaseDescrip" >${tPrescription.diseaseDescrip?if_exists}</textarea>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">图片：</div>
	    	
	    	<div style="margin-left: 10px;">
		    	<#list imgList as img>
					<image width="500px;" src="${ui1?if_exists}${img.path?if_exists}" style="<#if img_index==0>padding-left:10px;</#if>"/>
				</#list>
			</div>
    	</div>
    	
    	
    	
    	<#if tPrescription.status?exists && tPrescription.status==1>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">回访人：</div>
	    	<input type="text" name="callUsername" disabled="true" class="i-text-i" id="callUsername"  value="${tPrescription.callUsername?if_exists}"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">回访时间：</div>
	    	<input type="text" name="callTime" disabled="true" class="i-text-i" id="callTime"  value=<#if tPrescription.callTime?exists>"${tPrescription.callTime?string("yyyy-MM-dd HH:mm:ss")}</#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">回访记录：</div>
	    	<textarea type="text" name="returning" style="height:120px;line-height: 20px;" disabled="true" class="i-text-i" id="returning" >${tPrescription.returning?if_exists}</textarea>
    	</div>
    	</#if>
    	
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="返回" onclick="goList()" style="margin-left:10px;"/>
    	</div>
    </form>
    </div>   
    
    
 <script type="text/javascript">
		function update(){
			var id = $("#id").val();
			var isShow = $('input[name=isShow]:checked').val();
			if(id==null || id==""){
				$alert("warn","id不能为空!");
				return false;
			}
			if(isShow==null || isShow==""){
				$alert("warn","请选择是否显示!");
				return false;
			}
			$("#_isShow").val(isShow);
			$("form").submit();
		}
		
		function goList(){
			window.location.href="../prescription/prescription!prescriptionList.action";
		}
		
		$(function(){
			function BodyOnLoad() { 
				var textarea= document.getElementById("comment"); 
				//textarea.style.posHeight=textarea.scrollHeight;
				textarea.css("height",$(this).attr("scrollHeight")); 
			} 
		})
</script>
</body>

</html>