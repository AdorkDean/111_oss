<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
<title>商品咨询管理</title>

</head>

<html>
<body style="margin:0 3px" >
<!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:96%;"> 
            
	     	<form name="form1" id="subform"  action="${base}/goodsConsult/goodsConsult!goodsConsultPage.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
					
	        <div style="float:left;"> 
	            <div class="labelName">咨询人：</div>
	            <input name="userName" type="text" value="${userName?if_exists}" class="lname-i" style="border:#cccccc 1px solid;"/>
	            
	            <div class="labelName" style="margin-left:15px;">咨询内容：</div>
	            <input name="consultContent" type="text" value="${consultContent?if_exists}" class="lname-i" style="border:#cccccc 1px solid;"/>
	            
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ZIXUN_LIEBIAO_SEARCH">
		            <input type="button" class="btn01" onclick="search()" value="搜索" style="margin-left:15px;margin-top:2px;">
		    	</@security.authorize>
		    	
	            <input type="button" class="btn01" onclick="reset()" value="重置" style="margin-left:15px;margin-top:2px;">
	            <!--<div class="labelName" style="margin-left:15px;">名称3：</div>
	            <select name="" class="select-ui">
					 <option value="0">请选择</option>
					 <option value="1">项目1</option>
				</select>
				
				<input name="" type="checkbox" value="" class="lname-c" style="border:#cccccc 1px solid;margin-left:15px;"/>
	            <div class="labelName" style="margin-left:5px;">选项1</div>
				
				<input name="" type="radio" value="" class="lname-c" style="border:#cccccc 1px solid;margin-left:15px;"/>
	            <div class="labelName" style="margin-left:5px;">选项1</div>
	            
	            <input type="button" class="btn01" onclick="" value="添加" style="margin-left:15px;margin-top:2px;">
	            <input type="button" class="btn01" onclick="" value="返回" style="margin-left:15px;margin-top:2px;">
	            -->
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
			   	 	<th style="width:4%;">序号</th>
		            <th style="width:10%;">商品名称</th>
		            <th style="width:10%;">会员名称</th>
		            <th style="width:14%;">咨询内容</th>
		            <th style="width:14%;">回复内容</th>
		            <th style="width:7%;">是否显示</th>
		            <!--<th>父id</th>-->
		            <th style="width:12%;">创建时间</th>
		            <th style="width:12%;">回复时间</th>
		            <th style="width:17%;">管理</th>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
	   	     <!-- #list pw.result as abc -->
	   	     
	   	     <#list pw.result as tGoodsConsult>
				 <tr class="content" style="text-align:center;">
		            <td>${tGoodsConsult_index+1}</td>
		            <td>
	            		<a target="_blank" href="http://www.111yao.com/p/${tGoodsConsult.goodsId?if_exists}.html">${tGoodsConsult.goodsName?if_exists}
		            </td>
		            <td>${tGoodsConsult.userName?if_exists}</td>
		            <td>
		            	<#if tGoodsConsult?exists && tGoodsConsult.consultContent?exists && tGoodsConsult.consultContent?length gt 25>
		            		${tGoodsConsult.consultContent[0..24]}...
		            	<#else>
		            		${tGoodsConsult.consultContent?if_exists}
		            	</#if>
		            </td>
		            <td>
		            	<#if tGoodsConsult?exists && tGoodsConsult.answer?exists && tGoodsConsult.answer?length gt 25>
		            		${tGoodsConsult.answer[0..24]}...
		            	<#else>
		            		${tGoodsConsult.answer?if_exists}
		            	</#if>
		           </td>
		            <td>
		            	<#if tGoodsConsult.isShow?exists && tGoodsConsult.isShow==1>
		            		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ZIXUN_LIEBIAO_SETNOTSHOW">
						    	<a href="javascript:void(0)" title="点击设置不显示" onclick="setConsultShow(${tGoodsConsult.id?if_exists},0)" id="setShow_${tGoodsConsult.id?if_exists}">显示</a>
					    	</@security.authorize>
		            	<#else>
		            		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ZIXUN_LIEBIAO_SETSHOW">
		            			<a href="javascript:void(0)" title="点击设置显示" onclick="setConsultShow(${tGoodsConsult.id?if_exists},1)" id="setShow_${tGoodsConsult.id?if_exists}">不显示</a>
					    	</@security.authorize>
		            	</#if>
		            </td>
		            <!--<td>${tGoodsConsult.parentId?if_exists}</td>-->
		            <td>${tGoodsConsult.createDate?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
		            <td>
				 		<#if tGoodsConsult.answerDate?exists>
			            	${tGoodsConsult.answerDate?if_exists?string("yyyy-MM-dd HH:mm:ss")}
						</#if>
		            </td>
				 	<td>
			 		<!--<input type="button" class="btn01" onclick="" value="回复">-->
			 			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ZIXUN_LIEBIAO_DELETE">
					    	<a href="#" onclick="delConsult(${tGoodsConsult.id})">删除</a>&nbsp;|&nbsp;
				    	</@security.authorize>
			 			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ZIXUN_LIEBIAO_DETAIL">
				 			<a href="#" onclick="detailConsult(${tGoodsConsult.id})">详细</a>&nbsp;|&nbsp;
				    	</@security.authorize>
				 		<#if tGoodsConsult?exists && tGoodsConsult.answer?exists && tGoodsConsult.answer!=''>
				 			<span>已回复</span>
				 		<#else>
				 			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ZIXUN_LIEBIAO_REPLY">
				 				<a href="#" onclick="replyConsult(${tGoodsConsult.id})">回复</a>
				    	</@security.authorize>
				 		</#if>
				 	</td>
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
    	function replyConsult(id){
    		window.location.href="${base}/goodsConsult/replyGoodsConsult!replyPage.action?id="+id;
    	}
    	
    	/**
    	 * 搜索按钮
    	 */
    	function search(){
    		$("#subform").submit();
    	}
    	function reset(){
    		alert(11);
    		$("input[name='consultContent']").val("");
    		$("input[name='userName']").val("");
    	}
    	/**
    	  * 删除
    	  */
    	function delConsult(id){
    		if(id==null || id==''){
    			alert("请选择相关咨询数据");
    			return false;
    		}
    		var con = confirm("您确定要删除?删除后数据不能恢复");
    		if(!con){
    			return false;
    		}
    		$.ajax({
				url: "${base}/goodsConsult/delGoodsConsult!delGoodsConsult.action",
				type: "POST",
				data: {'id':id} ,
				success: function(data){
					if(data.status=='1'){
						$alert("success",data.message);
					}else{
						$alert("warn",data.message);
					}
					//window.location.reload();
					$("#subform").submit();
				},error:function(data){
					$alert("warn","内部错误,请稍后重试");
					//window.location.reload();
					$("#subform").submit();
				}
			});
    	}
    	
    	/**
    	  * 详细
    	  */
    	function detailConsult(id){
    		window.location.href="${base}/goodsConsult/detailGoodsConsult!detailGoodsConsult.action?id="+id;
    	}
    	
    	/**
    	  * 设置显示不显示
    	  */
    	function setConsultShow(id,isShow){
    		$.ajax({
				url: "${base}/goodsConsult/setShowGoodsConsult!setShowGoodsConsult.action",
				type: "POST",
				data: {'id':id,'isShow':isShow} ,
				success: function(data){
					if(data.status=='1'){
						$alert("success",data.message);
						if(isShow==0){
							$("#setShow_"+id).attr("title","点击设置显示");
							$("#setShow_"+id).attr("id","setShow_"+id);
							$("#setShow_"+id).text("不显示");
							$("#setShow_"+id).attr("onclick","setConsultShow('"+id+"','1')");
						}else if(isShow==1){
							$("#setShow_"+id).attr("title","点击设置不显示");
							$("#setShow_"+id).attr("id","setShow_"+id);
							$("#setShow_"+id).text("显示");
							$("#setShow_"+id).attr("onclick","setConsultShow('"+id+"','0')");
						}
					}else{
						$alert("warn",data.message);
					}
				},error:function(data){
					$alert("warn","内部错误,请稍后重试");
				}
			});
    	}
    	
    
    </script>
</body>
</html>
