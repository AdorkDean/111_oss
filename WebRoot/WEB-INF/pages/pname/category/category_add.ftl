<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加数据公共模板</title> 
	
	<style>
	.file1{
		height: 38px;
		width: 38px;
		
		
		position:absolute; 
		left:0; 
		//top:-12px; 
		border:1px solid #ccc; 
		display:inline-block; 
		width:38px; 
		height:38px; 
		line-height:38px; 
		text-align:center;
		opacity: 0;
	}
	.fileA{
		height: 38px;
		width: 38px;
		position:relative;
		margin-left: 10px;
	}
	.fileSpan1{
		height: 38px;
		width: 38px;
		background: #eee none repeat scroll 0 0;
		
		left:0; 
		//top:-12px; 
		border:1px solid #ccc; 
		display:inline-block; 
		width:38px; 
		height:38px; 
		line-height:38px; 
		text-align:center;
	}
	</style>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
	<link href="${base}/web/css/jquery.treeview.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/treeview/jquery.cookie.js"></script>
	<script type="text/javascript" src="${base}/web/js/treeview/jquery.treeview.js"></script>
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
	
    <form action="" method="get" name="addInfo" id="addInfo">
    <input type="hidden" name="category.id" id="categoryid" value="<#if category?exists>${category.id?default(0)}</#if>"/>
    <input type="hidden" name="category.allParentId" value="<#if category?exists>${category.allParentId?default('')}</#if>"/>
    <input type="hidden" name="category.parentId" id="parentid" value="<#if category?exists>${category.parentId?default('')}</#if>"/>
    	<div class="m-contents">
	    	<div class="labelnamese">类别名称：</div>
	    	<input type="text" name="category.categoryName" class="i-text-i" id="categoryName" value="<#if category?exists>${category.categoryName?default('')}</#if>"/>
    	</div>
    	<#if category?exists><#else>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">分类类别：</div>
	    	<select  id="type" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option selected value="1">商品分类</option>
					 <option value="2">医药馆</option>
			</select>
    	</div>
    	</#if>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">分类层级：</div>
    	<#if category?exists>
    	<input type="hidden" name="category.categoryLevel" id="level" value="${category.categoryLevel?default(1)}"/>
    	<#if category.categoryLevel?default(1)==1>
    	根级
    	<#elseif category.categoryLevel?default(1)==2>
    	一级
    	<#elseif category.categoryLevel?default(1)==3>
    	二级
    	<#elseif category.categoryLevel?default(1)==4>
    	三级
    	</#if>
    	<#else>
	    	<select name="category.categoryLevel" id="level" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option value="2">一级</option>
					  <option value="3">二级</option>
					   <option value="4">三级</option>
			</select>
    	
    	</#if>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">排序：</div>
	    	<input type="text" name="category.weight" class="i-text-i" id="weight" value="<#if category?exists>${category.weight?default('')}</#if>"/>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">pc是否显示：</div>
	    	<select name="category.isPc" id="ispc" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option <#if category?exists><#if category.isPc?default(1)==1>selected</#if><#else>selected</#if> value="1">显示</option>
					 <option <#if category?exists><#if category.isPc?default(1)==0>selected</#if></#if> value="0">不显示</option>
			</select>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">wap是否显示：</div>
	    	<select name="category.isWap" id="iswap" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option <#if category?exists><#if category.isWap?default(1)==1>selected</#if><#else>selected</#if> value="1">显示</option>
					 <option <#if category?exists><#if category.isWap?default(1)==0>selected</#if></#if> value="0">不显示</option>
			</select>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">app是否显示：</div>
	    	<select name="category.isApp" id="isapp" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option <#if category?exists><#if category.isApp?default(1)==1>selected</#if><#else>selected</#if> value="1">显示</option>
					 <option <#if category?exists><#if category.isApp?default(1)==0>selected</#if></#if> value="0">不显示</option>
			</select>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">seo标题：</div>
	    	<input type="text" name="category.seoTitle" class="i-text-i" id="seoTitle" value="<#if category?exists>${category.seoTitle?default('')}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">seo关键字：</div>
	    	<input type="text" name="category.seoKeyword" class="i-text-i" id="seoKeyword" value="<#if category?exists>${category.seoKeyword?default('')}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">seo描述：</div>
	    	<input type="text" name="category.seoDescribe" class="i-text-i" id="seoDescribe" value="<#if category?exists>${category.seoDescribe?default('')}</#if>"/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">分类描述：</div>
	    	<textarea class="taa-ui" id="categoryDescribe" name="category.categoryDescribe"><#if category?exists>${category.categoryDescribe?default('')}</#if></textarea>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">备注：</div>
	    	<textarea class="taa-ui" id="remarks" name="category.remarks"><#if category?exists>${category.remarks?default('')}</#if></textarea>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">分类图片：</div>
	    	<a style="" href="javascript:void(0)" class="fileA">
	    		<input unselectable="on" type="file" name="image1" id="file1" class="file1" value="file1" style=""/>
	    		<span style="" id="fileSpan1" class="fileSpan1">
	    		<#if imgUrl?exists>
	    			<img name="imgName" style="height:38px;width:38px;" src="${ui1?if_exists}${imgUrl?if_exists}" alt="" id=""/>
	    		<#else>
	    		上传</#if>
	    		</span>
	    	</a>
    	</div>
    	<#if category?exists>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">父类名称：</div>
	    	<input type="text" readOnly="readOnly" class="i-text-i"  value="${prantName?default('')}"/>
    	</div>
    	<#else>
    	<div class="tree" id="typeCate1" style="position:fixed;_position:absolute;">
					<!--开始树形结构-->
					
					<div id="cate1" style="display:none">
							<ul id="navigation">
								<#list cateList as cate>
									<#if cate.categoryLevel?default(1)==1 &&cate.id?default(-1)==1>
										<#assign fid=cate.id?default(0)>
										<#list cateidList?if_exists as cateid>
											<#if cateid==cate.id>
												<#assign showStr="checked">
												<#break>
											</#if>
										</#list>

											<li><input type="radio" style="display:none" ${showStr?default('')} id="parent" onclick="setPar(${cate.id?default()});" name="parent" value="${cate.id?default()}">${cate.categoryName?default('')}
											<#assign showStr="">
											
											
											<div id="cate2" style="display:none">
												<ul>
													<#list cateList as cate3>
														<#if cate3.categoryLevel?default(1)==2 && cate3.parentId?default(0)==fid>
															<#assign sid=cate3.id?default(0)>
																		<#list cateidList?if_exists as cateid>
																			<#if cateid==cate3.id>
																				<#assign showStr2="checked">
																			<#break>
																			</#if>
																		</#list>
																<li> <input type="radio" style="display:none" ${showStr2?default('')} onclick="setPar(${cate3.id?default()});" id="parent" name="parent" value="${cate3.id?default()}">${cate3.categoryName?default('')}
																<#assign showStr2="">
																
																<div id="cate3" style="display:none">
																	<ul>
													<#list cateList as cate5>
														<#if cate5.categoryLevel?default(1)==3 && cate5.parentId?default(0)==sid>
															<#assign did=cate5.id?default(0)>
																		<#list cateidList?if_exists as cateid>
																			<#if cateid==cate5.id>
																				<#assign showStr3="checked">
																			<#break>
																			</#if>
																		</#list>
																<li><input type="radio" style="display:none" ${showStr3?default('')} id="parent" onclick="setPar(${cate5.id?default()});" name="parent" value="${cate5.id?default()}">${cate5.categoryName?default('')}
																<#assign showStr3="">
																<div id="cate4" style="display:none">
																	<ul>
																		<#list cateList as cate7>
																			<#if cate7.categoryLevel?default(1)==4 && cate7.parentId?default(0)==did>
																				<#list cateidList?if_exists as cateid>
																					<#if cateid==cate7.id>
																						<#assign showStr4="checked">
																						
																					</#if>
																				</#list>
																																			
																				<li><input type="radio"  ${showStr4?default('')} id="parent" name="parent" onclick="setPar(${cate7.id?default()});" value="${cate7.id?default()}">${cate7.categoryName?default('')}</li>
																				<#assign showStr4="">
																			</#if>
																		</#list>
																	</ul>
																	</div>
																</li>
														</#if>
													</#list>
												</ul>
												</div>
											</li>
														</#if>
													</#list>
												</ul>
												</div>
											</li>
									</#if>
								</#list>
							</ul>
							</div>
					<!--结束树形结构-->
					</div>
					
					<div class="tree" id="typeCate2" style="position:fixed;_position:absolute; ">
					<!--开始树形结构-->
					
					<div id="cate1" style="display:none">
							<ul id="navigation">
								<#list cateList as cate>
									<#if cate.categoryLevel?default(1)==1 &&cate.id?default(-1)!=1>
										<#assign fid=cate.id?default(0)>
										<#list cateidList?if_exists as cateid>
											<#if cateid==cate.id>
												<#assign showStr="checked">
												<#break>
											</#if>
										</#list>

											<li><input type="radio" style="display:none" ${showStr?default('')} id="parent" onclick="setPar(${cate.id?default()});" name="parent" value="${cate.id?default()}">${cate.categoryName?default('')}
											<#assign showStr="">
											
											
											<div id="cate2" style="display:none">
												<ul>
													<#list cateList as cate3>
														<#if cate3.categoryLevel?default(1)==2 && cate3.parentId?default(0)==fid>
															<#assign sid=cate3.id?default(0)>
																		<#list cateidList?if_exists as cateid>
																			<#if cateid==cate3.id>
																				<#assign showStr2="checked">
																			<#break>
																			</#if>
																		</#list>
																<li> <input type="radio" style="display:none" ${showStr2?default('')} onclick="setPar(${cate3.id?default()});" id="parent" name="parent" value="${cate3.id?default()}">${cate3.categoryName?default('')}
																<#assign showStr2="">
																
																<div id="cate3" style="display:none">
																	<ul>
													<#list cateList as cate5>
														<#if cate5.categoryLevel?default(1)==3 && cate5.parentId?default(0)==sid>
															<#assign did=cate5.id?default(0)>
																		<#list cateidList?if_exists as cateid>
																			<#if cateid==cate5.id>
																				<#assign showStr3="checked">
																			<#break>
																			</#if>
																		</#list>
																<li><input type="radio" style="display:none" ${showStr3?default('')} id="parent" onclick="setPar(${cate5.id?default()});" name="parent" value="${cate5.id?default()}">${cate5.categoryName?default('')}
																<#assign showStr3="">
																<div id="cate4" style="display:none">
																	<ul>
																		<#list cateList as cate7>
																			<#if cate7.categoryLevel?default(1)==4 && cate7.parentId?default(0)==did>
																				<#list cateidList?if_exists as cateid>
																					<#if cateid==cate7.id>
																						<#assign showStr4="checked">
																						
																					</#if>
																				</#list>
																																			
																				<li><input type="radio"  ${showStr4?default('')} id="parent" name="parent" onclick="setPar(${cate7.id?default()});" value="${cate7.id?default()}">${cate7.categoryName?default('')}</li>
																				<#assign showStr4="">
																			</#if>
																		</#list>
																	</ul>
																	</div>
																</li>
														</#if>
													</#list>
												</ul>
												</div>
											</li>
														</#if>
													</#list>
												</ul>
												</div>
											</li>
									</#if>
								</#list>
							</ul>
							</div>
					<!--结束树形结构-->
					</div>
    	</#if>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_FENLEI_TIANJIA_TIJIAO">
	    	<input type="button" class="btn01" value="提交" onclick="submitForm();" style="margin-left:10px;"/>
	    	</@security.authorize>
	    	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JICHUXINXI_FENLEI_TIANJIA_FANHUI">
	    	<input type="button" class="btn01" value="返回" onclick="javascript:history.go(-1);" style="margin-left:10px;"/>
	    	</@security.authorize>
    	</div>
    		
    </form>
    </div>   
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#navigation").treeview({
		persist: "location",
		collapsed: true,
		unique: true
	});
	//定位类别区域
	$(".tree").css({"top":$("#categoryName").offset().top,"left":$("#remarks").offset().left+370});
	

$("#level").change(function(){
var $v = $.trim($("#type").val());
var leveltype = "#typeCate1";
if($v!=1){
leveltype = "#typeCate2"
}
$("input:radio[name='parent']").attr("checked",false);
var level=$("#level").val();
if(level==1){
	$(leveltype).find("#cate1").hide();
	$(leveltype).find("#cate2").hide();
	$(leveltype).find("#cate3").hide();
	$(leveltype).find("#cate4").hide();
}else if(level==2){
	$(leveltype).find("#cate1").show();
	$(leveltype).find("#cate2").hide();
	$(leveltype).find("#cate3").hide();
	$(leveltype).find("#cate4").hide();
	$(leveltype).find("#cate1").find('input').show();
}else if(level==3){
	$(leveltype).find("#cate1").show();
	$(leveltype).find("#cate1").find('input').hide();
	$(leveltype).find("#cate2").show();
	$(leveltype).find("#cate2").find('input').show();
	$(leveltype).find("#cate3").hide();
	$(leveltype).find("#cate4").hide();
}else if(level==4){
	$(leveltype).find("#cate1").show();
	$(leveltype).find("#cate2").show();
	$(leveltype).find("#cate3").show();
	$(leveltype).find("#cate1").find('input').hide();
	$(leveltype).find("#cate2").find('input').hide();
	$(leveltype).find("#cate3").find('input').show();
	$(leveltype).find("#cate4").hide();
}
});

var type=$("#type").val();
if(type==1){
	$("#typeCate1").show();
	$("#typeCate2").hide();
}

$("#type").change(function(){
var t=$("#type").val();
var tt=$("#level");
$("input:radio[name='parent']").attr("checked",false);
$("#parentid").val('');
if(t==1){
$("#level option[value='1']").remove();
	$("#typeCate1").css("display","block");
	$("#typeCate2").css("display","none");
}else if(t==2){
	tt.prepend("<option selected value='1'>根级</option>")
	$("#typeCate2").css("display","block");
	$("#typeCate1").css("display","none");
}
});

});

$(document).ready(function(){
var level=$("#level").val();
var type=$("#type").val();
if(type==1){
if(level==2){
$("#typeCate1").find("#cate1").show();
$("#typeCate1").find("#cate1").find('input').show();
}

}
});

function setPar(parid){
$("#parentid").val(parid);
}
function submitForm(){
var categoryName=$("#categoryName").val();
var seoTitle=$("#seoTitle").val();
var seoKeyword=$("#seoKeyword").val();
var seoDescribe=$("#seoDescribe").val();
var categoryDescribe=$("#categoryDescribe").val();
var remarks=$("#remarks").val();
var parent=$("#parent").val();
var level=$("#level").val();
var categoryid=$("#categoryid").val();
var weight=$("#weight").val();
var r = /^[0-9]+$/;
if(categoryid==null||categoryid==""){
if(level!=1){
var flag=0;
var radio=document.getElementsByName("parent");
for(var i=0;i<radio.length;i++)
   {
         if(radio.item(i).checked==true)
             {
     flag=1;
                  break;
       }
   }
  if(flag==0){
  alert("分类不为根级时，请选择父类");
  return;
  } 
}
}
if($.trim(categoryName)==""||categoryName.length>20){
alert("分类名称不能为空，且不大于20个字符！");
return;
}

if($.trim(weight)==""||weight.length>3){
alert("排序不能为空，且不能超过3位数字！");
return;
}
if(!r.test(weight)){
alert("排序只能为正整数！");
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
alert("seo描述不能大于200个字符！");
return;
}

if(categoryDescribe.length>100){
alert("分类描述不能大于100个字符！");
return;
}


if(remarks.length>100){
alert("备注不能大于100个字符！");
return;
}

var _imgN = encodeURI($("img[name='imgName']").attr("src")).replace(/\+/g,'%2B');
$.ajax({
url:"${base}/category/category!addOrEditCategory.action",
			type:"post",
			data:$("#addInfo").serialize()+"&imgName="+_imgN,
			success:function(data){
                   if(data==0){
                   alert("数据保存失败");
                   }else if(data==-1){
                   alert("根级目录名称不能重复")
                    }else{
                    alert("数据保存成功");
                    window.location.href="${base}/category/category!categoryList.action"; 
                    }
                    }
  });
}








$(function(){
	$(document).ready(function(){
		$("input[type=file]").live('click',function(){
				if(typeof FileReader==='undefined'){ 
				$(this).change(function(){
						loadImgForIE(this);
					})
				}else{ 
					$(this).change(function(){
						readFile(this)
					})
				} 
		})
	});

	function readFile(obj){
		var file = obj.files[0]; 
		if(typeof(file) == "undefined"){
			return false;
		}
		if(!/image\/jpeg/.test(file.type)){ 
			alert("文件必须为JPG图片！"); 
			$(obj).val("");
			return false; 
		}
		if(file.size>102400){ 
			alert("文件必须小于等于100K"); 
			$(obj).val("");
			return false; 
		}
		var reader = new FileReader(); 
		reader.readAsDataURL(file); 
		reader.onload = function(e){
			$(obj).siblings("span")[0].innerHTML = '<img name="imgName" style="height:38px;width:38px;" src="'+this.result+'" alt="" id=""/>' ;
		} 
	}
	function loadImgForIE(obj){
		var imgPath=$(obj).val();
		$(obj).siblings("span")[0].innerHTML = '<img name="imgName" style="height:38px;width:38px;" src="'+this.result+'" alt="" id=""/>' ;
	}
})
</script>

</html>
