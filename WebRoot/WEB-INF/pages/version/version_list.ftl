<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>公共列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<link rel="stylesheet" type="text/css" href="${base}/web/css/default.include.b2676e.css" media="all" />
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
	
	<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<link type="text/css" href="${base}/web/css/jquery-ui-1.8.14.custom.css" rel="stylesheet" />

</head> 
<body> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
	     	<form name="form1" id="subform"  action="${base}/versions/version!verionList.action" method="post">
	     	<input type="hidden" name="rs.p_curPage" value="1" id="p_curPage">
			<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
	        <div style="float:left;"> 
	            <div class="labelName">搜索 ：</div>
	           <select name="phone" style="height:26px;width:75px"><option value="-1">全  部</option><option value="1">IOS</option><option value="0">Android</option></select>
	             <input type="submit" class="btn01" onclick="" value="查询" style="margin-left:15px;margin-top:2px;">
	             <input type="button" class="btn01" onclick="shenhe()" value="添加" style="margin-left:15px;margin-top:2px;">
	             
	            
	         </div> 
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
     
     
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list" style="width:1100px">
	        
	        <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
			   	   
			         <td align="center" style="width:60px">序号</td><td align="center" style="width:100px;">版本号</td>
			         <td align="center" style="width:150px" >手机类型</td><td align="center" style="width:175px" >创建时间</td>
			         <td align="center" >下载地址</td>
			         <td align="center" >备注</td>
			         <td align="center" style="width:80px" >操作</td>
			   	 </tr>
			 </thead>
			 
			 <!-- 查询内容  -->
		     <tbody> 
	   <#list pw.result?if_exists as resc>
		<tr id="tr_record_" style="cursor:hand" >
		
		
			
			<td class="bc" align="center" >${resc_index+1}</td>
			<td class="bc" align="center" >
				${resc.version_info?default('')}
			</td>
			
			<td class="bc" align="center" >
			  <#if resc.type==1>IOS</#if>
			  <#if resc.type==0>Android</#if>
			</td>
			
			<td class="bc" align="center" style="width:20%;padding-left:3px">
			   ${resc.create_date}
			</td>
			<td class="bc" align="center" style="width:20%;padding-left:3px">
			   ${resc.url?default('')}
			</td>		
			
			<td class="bc" align="center" style="width:20%;padding-left:3px">
			   ${resc.comment?default('')}
			</td>
			
		
			<td class="bc" align="center" >
			<a href="javascript:getRecord(${resc.id?default('')});"  >修改</a>
			<a href="javascript:delRecord(${resc.id?default('')});"  >删除</a>
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
    
    
      <div class="s_wp" style="display:none" id="audit">
       <div class="s_con">
          <div class="s_int"> 
          <form id="f" method="post">
          <input type="hidden" name="flag" id="flag" />
          <input type="hidden" name="cversion.id" value="${cversion.id?default(0)}"　/>
              <table width="100%" height="130px" border="0" cellspacing="0" cellpadding="0">
                    <tr style="height:30px" >
                      <td width="23%" align="right">手机：</td>
                      <td width="77%"><select name="cversion.type" style="height:26px;width:75px" id="phone"><option value="1">IOS</option><option value="0">Android</option></select></td>
                    </tr>
                    
                    <tr style="height:30px">
                      <td width="23%" align="right" >版本：</td>
                      <td width="77%"> <input type="text" name="cversion.versionInfo" style="height:26px;width:200px;" /> </td>
                    </tr>
                    <tr style="height:30px">
                      <td width="23%" align="right" >下载地址：</td>
                      <td width="77%"> <input type="text" name="cversion.url" style="height:26px;width:200px;" /> </td>
                    </tr>                 
                     <tr >
                      <td width="23%" align="right">备注：</td>
                      <td width="77%"> 
                      <#--
                      <input type="text" name="cversion.comment" style="height:26px;width:200px" />
                      -->
                      <textarea  name="cversion.comment" style="height:150px;width:280px;margin-top:20px"  ></textarea>
                       </td>
                    </tr>
                    
                    
                    <tr>
                      <td colspan="2" align="center"> 
                      <input  type="button" onclick="mySumbit()" value="确定" class="tex_1" style="height:26px;width:60px" / > 
                      <input  type="button" value="关闭" class="tex_1" onclick="gb()" style="height:26px;width:60px" /></td>
                    </tr>
              </table> 
              </form>             
           </div>
       </div>
    </div>
    
    


<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript" src="${base}/web/js/init.js"></script>
<script>	
$('#audit').dialog({
	autoOpen: false,
	width: 470,
	title:'版本发布',
	resizable: false,
	modal: true //遮蔽
});
function shenhe(){
 $("input[name='cversion.versionInfo']").val("");
 $("textarea[name='cversion.comment']").val("");
 $('#flag').val("add");
 $('#audit').dialog('open');
}


function gb(){
 $('#audit').dialog('close');
}

function mySumbit(){


	var v = $("input[name='cversion.versionInfo']").val();
	if($.trim(v)==""){
		alert("版本不能为空");
		return;
	}
	var phone = $("#phone").val();
	if(phone=="0"){
	    var url = $("input[name='cversion.url']").val();
		if($.trim(url)==""){
			alert("下载地址不能为空");
			return;
		}
	}
	var info =  $("textarea[name='cversion.comment']").val();
	
	if($.trim(info).length>400){
		alert("备注不能超过400个字");
		return;
	}
	
	var f = $('#f');
		jQuery.ajax
	   ({
	       type: "post",
	       url: "${base}/versions/version!addNewVersion.action",	  
	       data:f.serialize(),
	       success: function(data)
	       {
	          if(data>0){
	          	alert("操作成功");
	            window.location.reload();
	            gb();
	          }else{
	            if(data=-2){
	            	alert("版本号重复");
	            }else{
	           	 alert("操作失败");
	            }
	          	
	          }
	       },error:function(){
	       		alert("网络异常");
	       }
	       
	   }); 
}

function getRecord(id){
	   $('#flag').val("update");
		jQuery.ajax
	   ({
	       type: "post",
	       url: "${base}/versions/version!getVersionRecord.action",	  
	       data:{'id':id},
	       success: function(data)
	       {
	         var s=jQuery.parseJSON(data);     
	         $("input[name='cversion.versionInfo']").val(s.versionInfo);
	        $("textarea[name='cversion.comment']").val(s.comment);
	       $("select[name='cversion.type']").val(s.type);
	        $("input[name='cversion.id']").val(s.id);
	        $("input[name='cversion.url']").val(s.url);
	        $('#audit').dialog('open');
	       },error:function(){
	       		alert("网络异常");
	       }
	       
	   }); 
}


function delRecord(id){
	  
	  
if(!window.confirm("您确定要删除该版本记录吗?删除之后不能恢复")){
 // alert("no");
  return;
 }
 
		jQuery.ajax
	   ({
	       type: "post",
	       url: "${base}/versions/version!deleteRecord.action",	  
	       data:{'id':id},
	       success: function(data)
	       {
	         if(data>0){
	         	alert("操作成功");
	          	window.location.reload();
	          	//$('#tr_record_'+id).remove();
	         }else{
	         	alert("操作失败");
	         }  
	        	
	       },error:function(){
	       		alert("网络异常");
	       }
	       
	   }); 
}


	
	</script>
	
   
</body>
</html>