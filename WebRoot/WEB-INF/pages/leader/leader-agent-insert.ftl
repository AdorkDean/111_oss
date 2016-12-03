<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>公共列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<link rel="stylesheet" type="text/css" href="${base}/web/css/default.include.b2676e.css" media="all" />
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body> 
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
    
    <form id="f">
      <input type="hidden" value="${flag}" name="flag" >
	 <table class="table-list" style="width:900px;margin-left:15px">
	        
		 <tbody> 
		 
			 <tr id="tr_record_" style="cursor:hand" >
		
				<td class="bc" align="center" >
				 用户名：
				</td>
				<td class="bc" align="center" >
					 <input type="text" name="username" value="${uname?default('')}" class="lname-i" style="border:#cccccc 1px solid;" />
				</td>
				<td class="bc" align="center" >
					 用户真是姓名：
				</td>
				<td class="bc" align="center" >
					 <input name="bean.realName" value="<#if record?exists> ${record.realName?default('')}</#if>" type="text" class="lname-i" style="border:#cccccc 1px solid;" />
				</td>
			
			</tr>
			
			 <tr id="tr_record_" style="cursor:hand" >
		
				<td class="bc" align="center" >
				 推广链接：
				</td>
				<td class="bc" align="center" >
					 <input name="bean.pushLink" value="<#if record?exists> ${record.pushLink?default('')}</#if>" type="text" class="lname-i" style="border:#cccccc 1px solid;" />
				</td>
				
				<td class="bc" align="center" >
				 编号：
				</td>
				<td class="bc" align="center" >
					 <input  value="" type="text" class="lname-i" style="border:#cccccc 1px solid;" />
				</td>
			
			</tr>
			
			 <tr >
		
				<td class="bc" align="center" colspan="4"　>
				 <input type="button" class="btn01" onclick="add()" value="添加" style="margin-left:15px;margin-top:2px;">
				 <a href="${base}/leader/leaderagent!list.action">
				 <input type="button" class="btn01" onclick="" value="返回" style="margin-left:15px;margin-top:2px;">
				 </a>
				</td>
			</tr>
		</tbody>
	</table>   
     </form>
     
    </div> 
    
    	<script type="text/javascript">
	
   function add(){
   
   
      var username = $("input[name='username']").val();
      if($.trim(username)==""){
      	alert("用户名称不能为空");
      	return;
      }
      
	  var f =  $("#f"); 		
	  jQuery.ajax
	   ({
	       type: "post",
	       url: " ${base}/leader/leaderagent!add.action",	  
	       data:f.serialize(),
	       success: function(data)
	       {
	          if(data>0){
	            alert("操作成功");
	          	window.location.href="${base}/leader/leaderagent!list.action";
	          	
	          }else{
	          	 if(data==0){
	          	 	alert("用户不存在");
	          	 	$("input[name='username']").val("");
	          	 }else if(data==-1){
	          	 	alert("存在一个以上该用户");
	          	 }else if(data==-2){
	          	 	alert("已添加过");
	          	 }
	          }
	       },error:function(){
	       		$alert('error','网络异常!');
	       }
	       
	   }); 
	}
	</script>
   
</body>
</html>