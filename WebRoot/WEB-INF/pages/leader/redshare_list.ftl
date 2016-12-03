<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>分享语列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
	     	<form name="form1" id="subform"  action="${base}/leader/leaderRedShare!list.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="1" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
	        <div style="float:left;"> 
	       		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIU_HONGBAOFENXIANG_TIANJIA">
	            <a href="${base}/leader/leaderRedShare!add.action"  style="margin-left:5px;margin-top:2px;" class="a-button">添加</a>
	            </@security.authorize>
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIU_HONGBAOFENXIANG_FABU">
	            <input type="button" class="btn01" value="发布" onclick="release();" style="margin-left:5px;margin-top:2px;">
	            </@security.authorize>
	            <a href="javascript:history.go(-1);"  style="margin-left:5px;margin-top:2px;" class="a-button">返回</a>
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list" style="width:100%">
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			  <tr style="height:40px;" class="content">
			        <td width="5%" align="center">序号</td>
			        <td width="10%" align="center">分享语</td>
			        <td width="5%" align="center">分享缩略图</td>
			        <td width="5%" align="center">权重</td>
			        <td width="10%" align="center">类型</td>
			        <td width="5%" align="center">操作</td>
			   </tr>
			 </thead>
			 <!-- 查询内容  -->
		     <tbody> 
				 <#list pw.result as redshare>
				 <tr class="content" style="text-align:center;" id="tr_record_${redshare_index+1}">
					<td>
						<input type='checkbox' name='ck' value=${redshare.id?default('')}>${redshare_index+1}
						</br>
						<#if redshare.status?default('')==1>
                    		<b style="color:red">已发布</b>
                    	<#else>
                    		未发布
		            	</#if>
				 	</td>
				 	<td>
				 		${redshare.share_title?default('')}
				 	</td>
				 	<td>
				 		<img src='${ui1}${redshare.share_image_url?default('')}' width="50px" height ="50px"/>
					</td>
				 	<td>
				 		${redshare.weight?default('')}
				 	</td>
				 	<td>
				 	<#if redshare.share_type?exists>
			            <#if redshare.share_type==1 >
			           		 领秀红包分享
					  	<#else>
							购物红包分享
					   </#if>
					<#else>
					</#if>
				 	</td>
				 	<td align="center">
				 	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIU_HONGBAOFENXIANG_BIANJI">
				 		<input type="button" class="btn01" onclick="window.location='${base}/leader/leaderRedShare!view.action?id=${redshare.id?default('')}';" value="编辑" />
				 	</@security.authorize>
				 	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIU_HONGBAOFENXIANG_SHANCHU">
				 		<input type="button" class="btn01" id="del-btn-${redshare.keyword?default('')}" onclick="del('${redshare.id?default('')}','${redshare_index+1}')" value="删除" style="margin-left:5px;"/>
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
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
    <script type="text/javascript">
    function release(){
    	var str="";
		$('input[name="ck"]:checked').each(function(){
	        str+=$(this).val()+",";
        });
        if(str!=''){
			jQuery.ajax({
		       type: "post",
		       url: " ${base}/leader/leaderRedShare!release.action",	  
		       data:{"id":str},
		       success: function(data){
		          if(data>0){
		          	$alert("success","操作成功！");
		          	window.location.reload();
		          }else{
		          	$alert('error','网络异常!');
		          }
		       },error:function(){
		       		$alert('error','网络异常!');
		       }
	  		}); 
        }else{
        	$alert("warn","请选择内容！");
        	return;
        }
	}
   
	function del(id,indx){
	 if(!window.confirm("您确定要删除该记录吗?删除的数据不能恢复！")){
	  	return false;
	 }
			jQuery.ajax({
		       type: "post",
		       url: " ${base}/leader/leaderRedShare!delete.action",	  
		       data:{"id":id},
		       success: function(data){
		          if(data>0){
		          	$alert("success","操作成功！");
		          	$('#tr_record_'+indx).remove();
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