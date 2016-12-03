<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>公共列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
	     	<form name="form1" id="subform"  action="${base}/leader/leaderContent!list.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="1" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
	        <div style="float:left;"> 
	            <div class="labelName">标题：</div>
	            <input  type="text" name="tile" value="${tile?default('')}" class="lname-i" style="border:#cccccc 1px solid;" />
				<input type="submit" class="btn01" onclick="" value="查询" style="margin-left:15px;margin-top:2px;">
	            <a href="${base}/leader/leaderContent!add.action"  style="margin-left:5px;margin-top:2px;" class="a-button">添加
	            </a>
	            <input type="button" class="btn01" value="发布" onclick="fabu();" style="margin-left:5px;margin-top:2px;">
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
        <td width="10%" align="center">标题</td>
        <td width="15%" align="center">文字内容</td>
        <td width="5%" align="center">权重</td>
        <td width="5%" align="center">操作</td>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
				 <#list pw.result as sta>
				 <tr class="content" style="text-align:center;" id="tr_record_${sta_index+1}">
					<td>
						<input type='checkbox' name='ck' value=${sta.id?default('')}>${sta_index+1}
						</br>
						<#if sta.status?default('')==1>
                    		<b style="color:red">发布</b>
                    	<#else>
                    		未发布
		            	</#if>
						
						
				 	</td>
				 	<td>
				 		${sta.tile?default('')}
				 	</td>
				 	<td title="${sta.context?default('')}">
					<#if sta.context?exists>
						<#assign siz=sta.context.length()>
			            <#if siz gt 30 >
			           		${sta.context[0..30]?default('')}...
					  	<#else>
							${sta.context?default('')}
					   </#if>
					<#else>
					</#if>
					</td>
				 	<td>
				 		${sta.weight?default('')}
				 	</td>
				 	<td align="center">
				 		<input type="button" class="btn01" onclick="window.location='${base}/leader/leaderContent!view.action?id=${sta.id?default('')}';" value="编辑" />
				 		<input type="button" class="btn01" id="del-btn-${sta.keyword?default('')}" onclick="del('${sta.id?default('')}','${sta_index+1}')" value="删除" style="margin-left:5px;"/>
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
    function fabu(){
    	var str="";
		$('input[name="ck"]:checked').each(function(){
	        str+=$(this).val()+",";
        });
        if(str!=''){
			jQuery.ajax({
		       type: "post",
		       url: " ${base}/leader/leaderContent!ajaxfabu.action",	  
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
    function setkey(k,i){
    	html ='<input type="text" value='+k+' class="lname-i" id="key'+i+'" style="border:#cccccc 1px solid;" /><input type="button" class="btn01" onclick="updata('+i+',&apos;'+k+'&apos;)" value="修改" style="margin-left:15px;margin-top:2px;">'
    	$("#td_"+i).html(html);
    }
    function updata(i,v1){
	    if(!window.confirm("您确定要修改该记录吗?")){
		  	return;
		 }	
    	jQuery.ajax({
		       type: "post",
		       url: " ${base}/goods/goodsgk!ajaxupdata.action",	  
		       data:{"keyword":v1,"newkeyword":$("#key"+i).val()},
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
    }
	function del(id,indx){
	 if(!window.confirm("您确定要删除该记录吗?删除的数据不能恢复！")){
	  	return;
	 }
			jQuery.ajax({
		       type: "post",
		       url: " ${base}/leader/leaderContent!ajaxdel.action",	  
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