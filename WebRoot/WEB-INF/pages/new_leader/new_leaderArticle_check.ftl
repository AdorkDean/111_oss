<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>领秀文章审核</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	  <link rel="stylesheet" type="text/css" href="${base}/web/css/wx/editor-min.css" />
	  <script type="text/javascript" charset="utf-8" src="${base}/web/plugins/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script> 
  <script type="text/javascript" charset="utf-8" src="${base}/web/plugins/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script> 
  <script charset="utf-8" src="${base}/web/plugins/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
		<style> 
		.labelnamese1 {float:left;height:30px;line-height:30px;text-align:right;}
		.labelnamese2 {float:left;text-align:right;}
		.m-contents1 {float:fight;}
		</style>
		 <script type="text/javascript">
		var ues = UE.getEditor('editor', 
		{
			initialFrameWidth: 500,
   			initialFrameHeight: 500,
			autoHeightEnabled: false,
			enableAutoSave: false,
			wordCount: false,
			elementPathEnabled: false,
			saveInterval: 50000,
			autoFloatEnabled:false,
			zIndex: 0,
			toolbars: [[
	            'fullscreen', 'source', '|', 'undo', 'redo', '|',
	            'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch',  'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
	            'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
	            'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
	            'directionalityltr', 'directionalityrtl', 'indent', '|',
	            'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
	            'link', 'unlink',  '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter','insertimage'
	        ]]
		})
	</script> 
</head> 
<script src="${base}/web/js/jquery-1.7.2.min.js" type="text/javascript" language="javascript" ></script>
<body> 
	 
	<div class="shop_main"  >
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese1"><font >文章标题：</font></div>
	    	<div class="labelnamese1"><font >${article.title?default('')}</font></div>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese1"><font >分享标语：</font></div>
	    	<div class="labelnamese1"><font >${article.shareTitle?default('')}</font></div>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese1"><font >分享图片：</font></div>
	    	<div class="labelnamese2"><img  src="${ui1}${article.shareImageUrl?default('')}" style="width:150px;height:150px" /></div>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese1"><font >商品图片：</font></div><br/>
	    	 <#list goodsList?if_exists as goods>
	    	<div class="labelnamese2"><a target="_blank" href="${www1}${goods.goodsId}.html"><img  src="${ui1}${goods.goodImageUrl?default('')}"  style="width:150px;height:150px"/></a></div>
	    	</#list>
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese1"><font >文章内容：</font></div><br/>
			     <div id="bdeditor"> 
			      <script id="editor" type="text/plain">${content?default('')}</script> 
			     </div> 
    	</div>
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese1"><font color="red">审核意见：</font></div><br/>
	    	<textarea id="check_idear" cols="100" rows="3"></textarea>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents1" style="margin-top:20px;" align="center">
    		<#if article.auditStatus?default(0)==1>
		    	<input type="button" class="btn01" value="驳回" onclick="checkArticle(2,${article.id?default(0)});" style="margin-left:10px;"/>
		    	<input type="button" class="btn05" value="审核通过" onclick="checkArticle(1,${article.id?default(0)});" style="margin-left:10px;"/>
	    	</#if>
	    	<a href="${base}/leader/newLeaderArticle!geiArticleList.action"><input type="button" class="btn01" value="返回"  style="margin-left:10px;"/></a>
    	</div>
    </div>   
</body>
<script>
function checkArticle(status,id){
var idear=$("#check_idear").val();
if(status==2){
	 if($.trim(idear)==""){
  		 $alert('warn','请填写审核意见');
        return false;
   }
}
 if(idear.length>300){
  		 $alert('warn','审核意见不超过300个字');
        return false;
   }
  $.ajax({
	url:"${base}/leader/newLeaderArticle!checkAricle.action",
			type:"post",
			data:{"checkStatus":status,"id":id,"check_idear":idear},
			success:function(data){
			if(data>0){
               $alert('success','操作成功');
                window.location.href="${base}/leader/newLeaderArticle!geiArticleList.action"; 
				}else{
				 $alert('warn','操作失败');
				}
                    }
 	 });

}

</script>
</html>