<!DOCTYPE html>
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>添加专题</title> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl"> 
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 

<body>
	<div class="shop_main" id="shop_main" style="margin-top:0px;margin-left:0px;width:99%;"> 
    <form method="post" id="form_id" enctype="multipart/form-data">
    	
    	<div class="m-contents" style="margin:0px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">选择平台：</div>
    		<select name="tsubject.ptype" style="float:left;width:60px;height:30px;border:#ccc 1px solid;">
    			<option value="1">PC</option>
    			<option value="2">WAP</option>
    			<option value="3">APP</option>
    		</select>
    		<div style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;margin-left:152px;">请选择平台（如：PC）</div>
    	</div>
    	
    	<div class="m-contents" style="margin:10px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">专题名称：</div>
		    <input type="text" name="tsubject.name" id="name" style="float:left;width:200px;height:30px;line-height:30px;border:#ccc 1px solid;"/>
    		<div id="cname" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;">请填写专题名称（如：新版上线领取优惠券）</div>
    	</div>
    	
    	<div class="m-contents" style="margin:10px 0px 0px 10px;">
    		<div style="float:left;height:30px;width:75px;line-height:30px;text-align:center;">文件名称：</div>
		    <input type="text" name="tsubject.nameFile" id="nameFile" style="float:left;width:200px;height:30px;line-height:30px;border:#ccc 1px solid;"/>
    		<div id="cnameFile" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:10px;border-radius:5px;color:#ccc;text-align:center;">请填写文件英文名称（如：subject.html）</div>
    	</div>
    	
    	<div class="m-contents" style="margin:10px 0px 0px 10px;">
    		<div style="float:left;height:360px;width:75px;line-height:360px;text-align:center;">专题代码：</div>
	    	<div style="float:left;width:900px;">
	    		<textarea name="ztcode" id="ztContext" style="width:900px;height:360px;resize:none;font-size:13px;padding:5px;"></textarea>
	    	</div>
    	</div>
    	
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:10px;">
	    	<input type="button" class="btn01" value="保存" id="submit_btn" onclick="validateData()" style="float:left;margin-left:85px;"/>
	    	<input type="button" class="btn01" value="预览" id="preview_btn" onclick="previewSubject()" style="float:left;margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" id="preview_btn" onclick="window.history.back()" style="float:left;margin-left:10px;"/>
	    	<input type="button" class="btn01" value="JS" id="preview_btn" onclick="openJS()" style="float:left;margin-left:10px;"/>
	    	<div id="ccet" style="display:none;float:left;width:260px;height:30px;line-height:30px;border:#dcdcdc 1px solid;margin-left:95px;margin-top:-5px;border-radius:5px;color:#ccc;text-align:center;">请复制专题代码到指定区域</div>
    	</div>
    		
    </form>
    </div>   
    
    
    <div style="display:none;position:fixed;top:0;left:17%;width:auto;height:auto;resize:none;z-index:100;" id="jscode">
	<div style="float:left;width:100%;heigth:40px;">
		<img src='${base}/web/images/close.png' style="float:right;width:30px;height:30px;background-color:#fff;cursor:pointer;" onclick="closeJS()"/>
	</div>
	<textarea style="float:left;width:650px;height:435px;background:#fff;border:#ccc 1px solid;resize:none;padding:20px;">
	#######【PC平台用下面代码】########
	$.ajax
	({
		type: "post",
		url: "/index!countpricefrompc.action",
        data: {"params":params},
		async: false,
		success: function(data) 
		{  
			if(data != null && data != '' && data != undefined)
			{
				for(var i=0; i<data.length; i++)
			    {
					//商品ID
					var goodsId = data[i].goodsId;
					//现在价格
					var nowPrice = data[i].nowPrice;
					//平台
					var platform = data[i].platform;
					//HTML对象ID
					var htmlObjId = data[i].htmlObjId;
					//把价格渲染到元素中
					var objs = nowPrice.toString().split(".");
					var obj_l = "<b class='price_b_l' id='p_l_"+i+"'>"+objs[0]+"</b>";
					var obj_m = "<b class='price_b_m' id='p_m_"+i+"'>.</b>";
					var obj_r = "<span class='price_s_r' id='p_r_"+i+"'>"+objs[1]+"</span>";
					$("#"+htmlObjId).html(obj_l+obj_m+obj_r);
			    }
			}
			else
			{
				console.log("Datas null !"); 
			}
        },  
        error: function() 
        {  
            console.log("Server error !"); 
        }  
	});
	
	
	###########【其他平台用下面代码】#########
	
	var p_start = "[" ;
	var p_end = "]" ;
	var p1 = "{'goodsId':'1','nowPrice':'10.00','platform':'1','htmlObjId':'test1'}," ;
	var p2 = "{'goodsId':'2','nowPrice':'44.00','platform':'1','htmlObjId':'test2'}" ;
	var params = p_start + p1 + p2 + p_end ;
	$.ajax
	({
		type: "get",
		url: "http://www.111yao.com/index!countpricebyplatform.action",
		async: false,
		data: "params="+params,
		dataType: "jsonp",
		jsonp: "jsoncallback",
		success: function(data) 
		{  
			if(data != null && data != '' && data != undefined)
			{
				for(var i=0; i<data.length; i++)
			    {
					//商品ID
					var goodsId = data[i].goodsId;
					//现在价格
					var nowPrice = data[i].nowPrice;
					//平台
					var platform = data[i].platform;
					//HTML对象ID
					var htmlObjId = data[i].htmlObjId;
					//把价格渲染到元素中
					var objs = nowPrice.toString().split(".");
					var obj_l = "<b class='price_b_l' id='p_l_"+i+"'>"+objs[0]+"</b>";
					var obj_m = "<b class='price_b_m' id='p_m_"+i+"'>.</b>";
					var obj_r = "<span class='price_s_r' id='p_r_"+i+"'>"+objs[1]+"</span>";
					$("#"+htmlObjId).html(obj_l+obj_m+obj_r);
			    }
			}
			else
			{
				console.log("Datas null !"); 
			}
        },  
        error: function() 
        {  
            console.log("Server error !"); 
        }  
	});
	</textarea>
    </div>
    
    <div id="maskDIV" style="display:none;position:fixed;width:100%;height:100%;top:0;left:0;background:#000;opacity:.1;"></div>
    

</body>
<script>
/**表单校验*/
function validateData()
{
	//专题名称
	var name = $.trim($("#name").val());
	//文件名称
	var nameFile = $.trim($("#nameFile").val());
	//代码内容
	var ztContext = $.trim($("#ztContext").val());
	if(name == '' || name == null)
	{
		$("#name").focus();
		$("#cname").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#name").blur();
			$("#cname").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
		},2000);
		return false;
	}
	if(nameFile == '' || nameFile == null)
	{
		$("#nameFile").focus();
		$("#cnameFile").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#nameFile").blur();
			$("#cnameFile").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
		},2000);
		return false;
	}
	else
	{
		if(nameFile.indexOf(".html") == -1 || /^[\u4e00-\u9fa5]+$/i.test(nameFile))
		{
			$("#nameFile").focus();
			$("#cnameFile").css({"border":"red 1px solid","color":"red"}).html("文件格式不正确！").show();
			window.setTimeout(function(){
				$("#nameFile").blur();
				$("#cnameFile").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).html("请填写文件英文名称（如：subject.html）").hide();
			},2000);
			return false;
		}
	}
	if(ztContext == '' || ztContext == null)
	{
		$("#ztContext").focus();
		$("#ccet").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#ccet").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
			$("#ztContext").blur();
		},2000);
		return false;
	}
	$("#submit_btn").val("...").attr("disabled",true);
	jQuery.ajax(
	{
		url:'/template/subject!save.action',
		data:$('#form_id').serialize(),
		type:"POST",
		success:function(data)
		{
			$("#ccet").css({"border":"blue 1px solid","color":"blue"}).html("恭喜保存成功，页面正在跳转...").show();
			window.setTimeout(function(){
				$("#submit_btn").val("保存").attr("disabled",false);
				$("#ccet").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).html("请复制专题代码到指定区域").hide();
				window.location.href = "${base}/template/subject!list.action";
			},2000);
		}
	});
}

function previewSubject()
{
	var ztContext = $.trim($("#ztContext").val());
	if(ztContext == '' || ztContext == null)
	{
		$("#ztContext").focus();
		$("#ccet").css({"border":"red 1px solid","color":"red"}).show();
		window.setTimeout(function(){
			$("#ccet").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).hide();
			$("#ztContext").blur();
		},2000);
		return false;
	}
	$("#preview_btn").val("...").attr("disabled",true);
	jQuery.ajax(
	{
		url:'/template/subject!preview.action',
		data:$('#form_id').serialize(),
		type:"POST",
		success:function(data)
		{
			$("#ccet").css({"border":"blue 1px solid","color":"blue"}).html("正在打开预览页...").show();
			window.setTimeout(function(){
				$("#preview_btn").val("预览").attr("disabled",false);
				$("#ccet").css({"border":"#dcdcdc 1px solid","color":"#ccc"}).html("请复制专题代码到指定区域").hide();
				window.top.open(data,"_blank")
			},2000);
		}
	});
}

function openJS()
{
	$('#maskDIV').fadeIn();
	$('#jscode').fadeIn();
}
function closeJS()
{
	$('#maskDIV').fadeOut();
	$('#jscode').fadeOut();
}
</script>
</html>