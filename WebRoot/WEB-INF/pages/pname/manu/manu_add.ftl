<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>品牌添加修改</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/ajaxfileupload.js"></script>
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="" method="get" name="addInfo" id="addInfo">
     <input type="hidden" name="manu.id" value="<#if manu?exists>${manu.id?default(0)}</#if>"/>
    <div >
	    	<div class="labelnamese">生产厂家logo：</div>
	    	 <input type="file" name="file" id="file"/><input type="button" class="btn01"  style="margin-left:10px; float:none" onclick="doajaxFileUpload()" value="上传" />
    	     <input type="hidden" name="manu.manuLogo" id="imgpath" value="<#if manu?exists>${manu.manuLogo?default('')}</#if>">
			<br/>
			<img style="width:100px;height:100px;" id="logoid" src="<#if manu?exists><#if manu.manuLogo?exists>${base}${manu.manuLogo?default('')}</#if></#if>" />
    	</div>
    
    	<div class="m-contents" style="margin-top:10px;">
    	
	    	<div class="labelnamese">生产厂家名称：</div>
	    	
	    	<input type="text" name="manu.manuName" class="i-text-i" id="manuName" value="<#if manu?exists>${manu.manuName?default('')}</#if>"/>
    	</div>
    	
    	
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">是否启用：</div>
	    	<select name="manu.status" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option <#if manu?exists><#if manu.status?default(0)==0>selected</#if><#else>selected</#if>  value="0">启用</option>
					 <option <#if manu?exists><#if manu.status?default(0)==1>selected</#if></#if> value="1">禁用</option>
			</select>
    	</div>
    	
    	
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">广告语：</div>
	    	<textarea class="taa-ui" id="slogan" name="manu.slogan"><#if manu?exists>${manu.slogan?default('')}</#if></textarea>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">生产厂家描述：</div>
	    	<textarea class="taa-ui" id="manuDescribe" name="manu.manuDescribe"><#if manu?exists>${manu.manuDescribe?default('')}</#if></textarea>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">备注：</div>
	    	<textarea class="taa-ui" id="remarks" name="manu.remarks"><#if manu?exists>${manu.remarks?default('')}</#if></textarea>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_CHANGJIA_TIANJIA_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="submitForm();" style="margin-left:10px;"/>
	    	 </@security.authorize>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_CHANGJIA_TIANJIA_FANHUI">
	    	<input type="button" class="btn01" value="返回" onclick="javascript:history.go(-1);" style="margin-left:10px;"/>
	    	 </@security.authorize>
    	</div>
    		
    </form>
    </div>   
</body>
<script>
function submitForm(){
var manuName=$("#manuName").val();
var slogan=$("#slogan").val();
var remarks=$("#remarks").val();
var manuDescribe=$("#manuDescribe").val();
var imgpath=$("#imgpath").val();

if($.trim(manuName)==""||manuName.length>200){
alert("生产厂家名称不能为空，且大于200个字！");
return;
}

if(slogan.length>400){
alert("广告语不能大于400个字！");
return;
}

if(remarks.length>400){
alert("备注不能大于400个字！");
return;
}

if(manuDescribe.length>400){
alert("生产厂家描述不能大于400个字！");
return;
}

$.ajax({
url:"${base}/manu/manu!saveOrEditManu.action",
			type:"post",
			data:$("#addInfo").serialize(),
			success:function(data){
                   if(data==0){
                   alert("数据保存失败");
                    }else{
                    alert("数据保存成功");
                window.location.href="${base}/manu/manu!getManufacturerList.action"; 
                    }
                    }
  });
}


    function doajaxFileUpload(){
    var filet=$("#file").val()
    if(filet==""){
    alert("请选择文件");
    return;
    }
     if(!/\.(jpg|jpeg|JPG|JPEG|png|PNG)$/.test(filet)){
    alert("请上传jpg或者png格式的图片");
    return;
    }
              $.ajaxFileUpload({
                  url: "${base}/manu/manu!saveImage.action",
                  secureuri: false,
                  fileElementId: "file",
                  dataType: "text",
                  success: function(data,status){
                  		var logo=$("#logoid");
                  		var len=data.length;
                  		var path=jsCtx+data;
                  		logo.attr("src",path);
                  		var imgpath=$("#imgpath");
                  		var file=$("#file");
                  		file.attr("value","");
                  		imgpath.attr("value",data);
                  		alert("成功！");                      
                  },
                  error: function (data, status, e)
                  {
                      alert("失败！");
                  }
              });
     }

</script>
</html>