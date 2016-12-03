<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>驳回领秀商品佣金设置</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
		<style> 
		.labelnamese1 {float:left;height:30px;line-height:30px;text-align:right;}
		.labelnamese2 {float:left;text-align:right;}
		.m-contents1 {float:fight;}
		</style>
</head> 
<body> 
	<div class="shop_main" > 
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese1"><font >驳回意见：</font></div></br>
    	    <input type="hidden" name="id"  id="brokerage_id" value="${id?default('0')}"/>
    	    <textarea id="check_idear" cols="50" rows="6"></textarea>
    	</div>
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="subForm();" style="margin-left:10px;"/>
	    	<input type="button" class="btn01" value="关闭" onclick="window.close()";  style="margin-left:10px;"/>
    	</div>
    		
    </div>   
</body>


<script>
function subForm(){
   var check_idear = $("#check_idear").val();
   var brokerage_id = $("#brokerage_id").val();
    if($.trim(check_idear)==""){
  		alert("意见不能为空");
        return false;
   }
  if(check_idear.length>30){
  		 alert("意见不能大于30字");
       return false;
   }
  $.ajax({
	url:"${base}/leader/leaderGoodsCheck!noCheckGoods.action",
			type:"post",
			data:{"check_idear":check_idear,"id":brokerage_id},
			success:function(data){
                   if(data>0){
                   alert("操作成功");
                    window.opener.location.reload();   
                    window.close();
                    }else{
                    alert("操作失败");
                    }
                    }
 	 });
}
</script>
</html>