<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加数据公共模板</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="" method="get" name="addInfo" id="addInfo">
    
    	<div class="m-contents">
	    	<div class="labelnamese">项目名称一：</div>
	    	<input type="text" name="" class="i-text-i" id="" value=""/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">项目名称二：</div>
	    	<input type="text" name="" class="i-text-i" id="" value=""/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">项目名称三：</div>
	    	<select name="" class="select-ui" style="border:#ccc 1px solid;margin-left:10px;">
					 <option value="0">请选择</option>
					 <option value="1">项目1</option>
			</select>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">项目名称四：</div>
	    	<textarea class="taa-ui"></textarea>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">项目名称五：</div>
	    	<input type="checkbox" name="" class="i-text-cb" id="" value=""/>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">项目名称六：</div>
	    	<input type="radio" name="" class="i-text-cb" id="" value=""/>
    	</div>
    	
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="重置" onclick="" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="返回" onclick="" style="margin-left:10px;"/>
    	</div>
    		
    </form>
    </div>   
</body>

</html>