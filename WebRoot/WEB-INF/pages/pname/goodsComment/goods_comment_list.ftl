<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
<title>商品评论管理</title>

</head>
<body style="margin:0 3px" >
<!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:96%;"> 
            
	     	<form name="form1" id="subform"  action="${base}/goodsComment/goodsComment!goodsCommentPage.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
					
	        <div style="float:left;"> 
	            <div class="labelName">商品名称：</div>
	            <input name="goodsName" type="text" value="${goodsName?if_exists}" class="lname-i" style="border:#cccccc 1px solid;"/>
	            
	            <div class="labelName" style="margin-left:15px;">用户名：</div>
	            <input name="userName" type="text" value="${userName?if_exists}" class="lname-i" style="border:#cccccc 1px solid;"/>
	            
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PINGLUN_LIEBIAO_CHAXUN">
					<input type="button" class="btn01" onclick="search()" value="搜索" style="margin-left:15px;margin-top:2px;">
	            </@security.authorize>
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PINGLUN_LIEBIAO_RESET">
					<input type="button" class="btn01" onclick="reset()" value="重置" style="margin-left:15px;margin-top:2px;">
	            </@security.authorize>
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
		            <th style="width:12%;">总评分</th>
		            <th style="width:6%;">类型</th>
		            <th style="width:7%;">卖家好评</th>
		            <th style="width:7%;">快递好评</th>
		            <th style="width:7%;">商品好评</th>
		            <th style="width:7%;">快递员好评</th>
		            <th style="width:6%;">是否显示</th>
		            <th style="width:12%;">评论内容</th>
		            <th style="width:8%;">会员用户名称</th>
		            <th style="width:8%;">商品名称</th>
		            <th style="width:8%;">订单编号</th>
		            <th style="width:12%;">管理</th>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
	   	     <#list pw.result as tGoodsComment>
				 <tr class="content" style="text-align:center;">
		            <td>${tGoodsComment_index+1}</td>
		            <td>
			            ${tGoodsComment.sumFraction?if_exists+'星'}
		            </td>
		            <td>
		            	<#if tGoodsComment.type?exists && tGoodsComment.type==1>
		            		好评
		            	<#elseif tGoodsComment.type?exists && tGoodsComment.type==2>
		            		中评
		            	<#elseif tGoodsComment.type?exists && tGoodsComment.type==3>
		            		差评
		            	<#else>
		            		差评
		            	</#if>
		            </td>
		            <td>
		            	<#if tGoodsComment?exists && tGoodsComment.seller?exists>
			            	<#list 1..tGoodsComment.seller as x1>
			            		<span style="color:#FFB200;">★</span>
			            	</#list>
			            </#if>
		            </td>
		            <td>
		            	<#if tGoodsComment?exists && tGoodsComment.fastMail?exists>
			            	<#list 1..tGoodsComment.fastMail as x2>
			            		<span style="color:#FFB200;">★</span>
			            	</#list>
		            	</#if>
		            </td>
		            <td>
		            	<#if tGoodsComment?exists && tGoodsComment.goods?exists>
			            	<#list 1..tGoodsComment.goods as x2>
			            		<span style="color:#FFB200;">★</span>
			            	</#list>
		            	</#if>
		            </td>
		            <td>
		            	<#if tGoodsComment?exists && tGoodsComment.fastMailPeople?exists>
			            	<#list 1..tGoodsComment.fastMailPeople as x2>
			            		<span style="color:#FFB200;">★</span>
			            	</#list>
		            	</#if>
		            <td>
		            	<#if tGoodsComment.isShow?exists && tGoodsComment.isShow==1>
		            		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PINGLUN_LIEBIAO_SETSHOW">
		            			<a href="javascript:void(0)" style="color:green;" title="点击设置不显示" onclick="setCommentShow(${tGoodsComment.id?if_exists},0)" id="setShow_${tGoodsComment.id?if_exists}">显示</a>
			            	</@security.authorize>
		            	<#else>
		            		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PINGLUN_LIEBIAO_SETNOTSHOW">
			            		<a href="javascript:void(0)" style="color:red;" title="点击设置显示" onclick="setCommentShow(${tGoodsComment.id?if_exists},1)" id="setShow_${tGoodsComment.id?if_exists}">不显示</a>
			            	</@security.authorize>
		            	</#if>
	            	</td>
		            <td>
		            	<#if tGoodsComment?exists && tGoodsComment.comment?exists && tGoodsComment.comment?length gt 25>
			            	${tGoodsComment.comment[0..24]}...
			            <#else>
			            	${tGoodsComment.comment?default('')}
		            	</#if>
		            </td>
		            <td>${tGoodsComment.userName?default('')}</td>
		            <td>
	            		<a target="_blank" href="http://www.111yao.com/p/${tGoodsComment.goodsId?if_exists}.html">${tGoodsComment.goodsName?default('')}</a>
		            	
		            </td>
		            <td>${tGoodsComment.orderSn?default('')}</td>
				 	<td>
				 		<!--<input type="button" class="btn01" onclick="" value="回复">-->
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PINGLUN_LIEBIAO_EDIT">
					 		<a href="#" onclick="editComment(${tGoodsComment.id})">编辑</a> &nbsp;|&nbsp;
			            </@security.authorize>
			            
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PINGLUN_LIEBIAO_DETAIL">
					 		<a onclick="detailComment(${tGoodsComment.id})" href="#">查看</a>&nbsp;|&nbsp;
			            </@security.authorize>
				 		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_PINGLUN_LIEBIAO_DELETE">
				 			<a onclick="delComment(${tGoodsComment.id})" href="#">删除</a>
			            </@security.authorize>
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
    	function editComment(id){
    		window.location.href="${base}/goodsComment/goodsComment!update.action?id="+id+"&mark=1";
    	}
    	function detailComment(id){
    		window.location.href="${base}/goodsComment/goodsComment!update.action?id="+id+"&mark=0";
    	}
    	
    	function search(){
    		$("#subform").submit();
    	}
    	function reset(){
    		$("input[name='goodsName']").val("");
    		$("input[name='userName']").val("");
    	}
    	/**
    	  * 设置显示不显示
    	  */
    	function setCommentShow(id,isShow){
    		$.ajax({//setShowGoodsComment
				url: "${base}/goodsComment/setShowGoodsComment!setShowGoodsComment.action",
				type: "POST",
				data: {'id':id,'isShow':isShow} ,
				success: function(data){
					if(data.status=='1'){
						$alert("success",data.message);
						if(isShow==0){
							$("#setShow_"+id).attr("title","点击设置显示");
							$("#setShow_"+id).text("不显示");
							$("#setShow_"+id).css("color","red");
							$("#setShow_"+id).attr("onclick","setCommentShow('"+id+"','1')");
						}else if(isShow==1){
							$("#setShow_"+id).attr("title","点击设置不显示");
							$("#setShow_"+id).text("显示");
							$("#setShow_"+id).css("color","green");
							$("#setShow_"+id).attr("onclick","setCommentShow('"+id+"','0')");
						}
					}else{
						$alert("warn",data.message);
					}
				},error:function(data){
					$alert("warn","内部错误,请稍后重试");
				}
			});
    	}
    	
    	function delComment(id){
    		var con = confirm("您确定要删除?");
    		if(!con){
    			return;
    		}
    		$.ajax({//setShowGoodsComment
				url: "${base}/goodsComment/delGoodsComment!delGoodsComment.action",
				type: "POST",
				data: {'id':id} ,
				success: function(data){
					if(data.status=='1'){
						$alert("success",data.message);
					}else{
						$alert("warn",data.message);
					}
					window.location.reload();
				},error:function(data){
					alert(data);
					$alert("warn","内部错误,请稍后重试");
				}
			});
			//$("#subform").submit();
    	}
    
    </script>
</body>
</html>
