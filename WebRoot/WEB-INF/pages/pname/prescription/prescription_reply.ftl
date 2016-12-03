<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>病例回访</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
</head> 

<body> 
<div></div>
	<div class="shop_main" id="shop_main"> 
    <form action="prescription/prescription!replyPrescription.action" method="post" name="addInfo" id="addInfo">
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
	    	<textarea type="text" name="diseaseDescrip" disabled="true" style="height:120px;line-height: 20px;" class="i-text-i" id="diseaseDescrip" >${tPrescription.diseaseDescrip?if_exists}</textarea>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">图片：</div>
	    	<#list imgList as img>
				<image width="500px" src="${ui1?if_exists}${img.path?if_exists}" style="<#if img_index==0>padding-left:10px;</#if>"/>
			</#list>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">回访记录：</div>
	    	<textarea name="returning" style="height:120px;background-color: #ffffff;line-height: 20px;" class="i-text-i" id="returning" >${tPrescription.returning?if_exists}</textarea>	
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_BINGLI_REPLY_SUBMIT">
				<input type="button" class="btn01" value="提交" onclick="reply()" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<input type="button" class="btn01" value="返回" onclick="goList()" style="margin-left:10px;"/>
    	</div>
    </form>
    </div>   
    
    
 <script type="text/javascript">
		function reply(){
			var id = $("#id").val();
			var returning = $("#returning").val();
			if(id==null || id==""){
				$alert("warn","id不能为空!");
				return false;
			}
			if(returning==null || returning==""){
				$alert("warn","请输入回复内容!");
				return false;
			}
			$.ajax({//setShowGoodsComment
	    		url: "../prescription/prescription!update.action",
	    		type: "POST",
	    		data: {'id':id,'returning':returning} ,
	    		async:false,
	    		success: function(data){
	    			if(data.status==1){
	    				$alert("success",data.message);
	    				window.location.href="../prescription/prescription!prescriptionList.action";
	    			}else{
	    				$alert("warn",data.message);
	    				window.location.href="../prescription/prescription!prescriptionList.action";
	    			}
	    		},error:function(data){
	    			$alert("warn","内部错误,请稍后重试!");
	    			window.location.reload(true);
	    		}
	    	});
			
		}
		
		function goList(){
			window.location.href="../prescription/prescription!prescriptionList.action";
		}
		
</script>
</body>

</html>