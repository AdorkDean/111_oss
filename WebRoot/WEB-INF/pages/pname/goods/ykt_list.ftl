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
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
	     	<form name="form1" id="subform"  action="${base}/goods/yktgoods!list.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="1" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
	        <div style="float:left;"> 
	            <div class="labelName">海典编号：</div>
	            <input name="goodsno" value="" type="text" class="lname-i" style="border:#cccccc 1px solid;" />
	             <input type="submit" class="btn01" onclick="" value="查询" style="margin-left:15px;margin-top:2px;">
	             <input type="button" class="btn01" onclick="add()" value="添加" style="margin-left:15px;margin-top:2px;">
	            </a>
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list" style="width:100%">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
			   	    <td align="center" style="width:55px">序号</td>
			        <td align="center" style="width:150px" >海典编号</td>
			        <td align="center">商品名称</td>
			        <td align="center">创建时间</td>
			        <#--
			         <td align="center" style="width:150px"  >操作</td>
			         -->
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
	   <#list pw.result?if_exists as resc>
		<tr id="tr_record_" style="cursor:hand" >
		
			<td class="bc" align="center" >
				${resc_index+1}
			</td>
			<td class="bc" align="center" >
				${resc.goods_no?default('')}
			</td>
			<td class="bc" align="left" style="padding-left:20px;" >
				${resc.goods_name?default('')}
			</td>
			<td class="bc" align="center" style="width:100px">
				${resc.create_time?default('')}
			</td>
			<#--
			<td class="bc" align="center" >
				删除
			</td>
			-->
			</tr>
			</#list>
	   		 <!-- /#list -->
			 </tbody>
	     </table>   
     <!-- 分页 Start -->    
     <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
     <!-- 分页 End --> 
     
    </div> 
    
    	<script type="text/javascript">
	
   function add(){
   	  var goodsno = $("input[name='goodsno']").val();
   	  if($.trim(goodsno)==""){
   	  	return;
   	  }
	  jQuery.ajax
	   ({
	       type: "post",
	       url: " ${base}/goods/yktgoods!add.action",	  
	       data:{"goodsno":goodsno},
	       success: function(data)
	       {
	          if(data>0){
	            alert("操作成功");
	          	//window.location.reload();
	          	$("input[name='goodsno']").val("");
	          }else{
	          	$alert('error','网络异常!');
	          }
	       },error:function(){
	       		$alert('error','网络异常!');
	       }
	       
	   }); 
	}
	</script>
   
</body>
</html>