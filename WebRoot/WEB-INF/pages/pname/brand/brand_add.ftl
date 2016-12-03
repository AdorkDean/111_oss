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
     <input type="hidden" name="brand.id" value="<#if brand?exists>${brand.id?default(0)}</#if>"/>
    <div >
	    	<div class="labelnamese">品牌logo：</div>
	    	 <input type="file" name="file" id="file"/><input type="button" class="btn01"  style="margin-left:10px; float:none" onclick="doajaxFileUpload()" value="上传" />
    	     <input type="hidden" name="brand.logo" id="imgpath" value="<#if brand?exists>${brand.logo?default('')}</#if>">
			<br/>
			<img style="width:100px;height:100px;" id="logoid" src="<#if brand?exists><#if brand.logo?exists>${base}${brand.logo?default('')}</#if></#if>" />
    	</div>
    
    	<div class="m-contents" style="margin-top:10px;">
    	
	    	<div class="labelnamese">品牌名称：</div>
	    	
	    	<input type="text" name="brand.brandName" class="i-text-i" id="brandName" value="<#if brand?exists>${brand.brandName?default('')}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">品牌拼音：</div>
	    	<input type="text" name="brand.pinyin" class="i-text-i" id="pinyin" value="<#if brand?exists>${brand.pinyin?default('')}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">品牌网址：</div>
	    	<input type="text" name="brand.brandUrl" class="i-text-i" id="brandUrl" value="<#if brand?exists>${brand.brandUrl?default('')}</#if>"/>
    	</div>
    	
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">pc是否显示：</div>
	    	<select name="brand.isPc" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option <#if brand?exists><#if brand.isPc?default(1)==1>selected</#if><#else>selected</#if>  value="1">显示</option>
					 <option <#if brand?exists><#if brand.isPc?default(1)==0>selected</#if></#if> value="0">不显示</option>
			</select>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">wap是否显示：</div>
	    	<select name="brand.isWap" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option <#if brand?exists><#if brand.isWap?default(1)==1>selected</#if><#else>selected</#if>  value="1">显示</option>
					 <option <#if brand?exists><#if brand.isWap?default(1)==0>selected</#if></#if> value="0">不显示</option>
			</select>
    	</div>
    	
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">app是否显示：</div>
	    	<select name="brand.isApp" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option <#if brand?exists><#if brand.isApp?default(1)==1>selected</#if><#else>selected</#if>  value="1">显示</option>
					 <option <#if brand?exists><#if brand.isApp?default(1)==0>selected</#if></#if> value="0">不显示</option>
			</select>
    	</div>
    	
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">seo标题：</div>
	    	<input type="text" name="brand.seoTitle" class="i-text-i" id="seoTitle" value="<#if brand?exists>${brand.seoTitle?default('')}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">seo关键字：</div>
	    	<input type="text" name="brand.seoKeyword" class="i-text-i" id="seoKeyword" value="<#if brand?exists>${brand.seoKeyword?default('')}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">seo描述：</div>
	    	<textarea class="taa-ui" id="seoDescribe" name="brand.seoDescribe"><#if brand?exists>${brand.seoDescribe?default('')}</#if></textarea>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">品牌介绍：</div>
	    	<textarea class="taa-ui" id="brandInfo" name="brand.brandInfo"><#if brand?exists>${brand.brandInfo?default('')}</#if></textarea>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">品牌备注：</div>
	    	<textarea class="taa-ui" id="remark" name="brand.remark"><#if brand?exists>${brand.remark?default('')}</#if></textarea>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_PINPAI_TIANJIA_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="submitForm();" style="margin-left:10px;"/>
	    	 </@security.authorize>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_PINPAI_TIANJIA_FANHUI">
	    	<input type="button" class="btn01" value="返回" onclick="javascript:history.go(-1);" style="margin-left:10px;"/>
	    	 </@security.authorize>
    	</div>
    		
    </form>
    </div>   
</body>
<script>
function submitForm(){
var brandName=$("#brandName").val();
var pinyin=$("#pinyin").val();
var brandUrl=$("#brandUrl").val();
var seoTitle=$("#seoTitle").val();
var seoKeyword=$("#seoKeyword").val();
var seoDescribe=$("#seoDescribe").val();
var brandInfo=$("#brandInfo").val();
var remark=$("#remark").val();
var imgpath=$("#imgpath").val();

if($.trim(brandName)==""||brandName.length>50){
alert("品牌名称不能为空，且不大于50个字！");
return;
}

if($.trim(pinyin)==""||pinyin.length>50){
alert("拼音不能为空，且大于50个字！");
return;
}

if(brandUrl.length>100){
alert("品牌网址不能大于100个字！");
return;
}

if(seoTitle.length>200){
alert("seo标题不能大于200个字！");
return;
}

if(seoKeyword.length>200){
alert("seo关键字不能大于200个字！");
return;
}

if(seoDescribe.length>200){
alert("seo描述不能大于200个字！");
return;
}

if(brandInfo.length>300){
alert("品牌介绍不能大于300个字！");
return;
}


if(remark.length>100){
alert("备注不能大于100个字符！");
return;
}

if($.trim(imgpath)==""){
alert("品牌logo不能为空");
return;
}
$.ajax({
url:"${base}/brand/brand!saveOrEditBrand.action",
			type:"post",
			data:$("#addInfo").serialize(),
			success:function(data){
                   if(data==0){
                   alert("数据保存失败");
                    }else{
                    alert("数据保存成功");
                    window.location.href="${base}/brand/brand!getBrandList.action"; 
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
    alert("请上传jpg或png格式的图片");
    return;
    }
              $.ajaxFileUpload({
                  url: "${base}/brand/brand!saveImage.action",
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