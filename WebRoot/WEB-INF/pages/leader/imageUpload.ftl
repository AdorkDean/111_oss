<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>图片管理</title> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>
  <script type="text/javascript" src="${base}/web/js/ajaxfileupload.js"></script>
  <script type="text/javascript" src="${base}/web/js/zeroclipboard-1.0.7/ZeroClipboard.js"></script>
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order"> 
     <div id="order_form" class="order_form" style="width:95%;"> 
       <div class="tbl_form" style="width:99%;"> 
    <form action="${base}/leader/uploadImage!goPage.action" name="form1" id="form1" method="post">
	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
	<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td style="width:8%"><p>开 始 日 期：</p></td> 
           <td style="width:18%"><p><input type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="<#if startDt?exists>${startDt}</#if>" name="startDt" id="startDt"  class="input input_v1" /></p></td> 
           <td style="text-align:right;width:8%;"><p> 结 束 日 期：</p></td> 
           <td style="width:18%"><p><input type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="<#if endDt?exists>${endDt}</#if>" name="endDt" id="endDt" class="input input_v1" /></p></td>
           <td style="width:8%"><p>图 片名 称：</p></td> 
           <td style="width:26%"><input type="text" style="width:100px;" name="imageName" class="input input_v1" value="<#if imageName?exists>${imageName?default('')}</#if>"/>
           类型 :<select name="imgType" style="width:110px;"><option value="4" <#if imgType?default('')==4>selected="true"</#if>>领袖海报</option><option value="5" <#if imgType?default('')==5>selected="true"</#if>>领袖素材图片</option></select>
           </td> 
           <td>
           <input type="button" name="querybut" id="querybut" onclick="query()" class="btn01" value="查询" />
           </td> 
          </tr>
          <tr> 
           <td><p>选 择 图 片：</p></td> 
           <td> <p><input  TYPE="file" NAME="imgfile" id="imgfile" class="input input_v1" /></p> (支持jpg,png,jpeg格式图片,建议上传5M以内图片!)</td> 
           <td style="text-align:right;"><p>图 片 名 称：</p></td> 
           <td> <input type="text" id="imgName" class="input input_v1"/></td> 
           <td colspan="2">
           <select id="imgtype" style="width:110px;"><option value="4">领袖海报</option><option value="5">领袖素材图片</option></select>
           权重：<input type="text" id="qzs" style="width:149px;" value="0" onblur="vd(this);" title="权重" class="input input_v1" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'0')" onafterpaste="this.value=this.value.replace(/\D/g,'0')"/>
           </td>
           <td>
	           <input type="button" class="btn01" onclick="uploadImg()" value="上传" id="upload-btn" />
	           <input type="button" class="btn01" value="发布" onclick="fabu();" style="margin-left:5px;margin-top:2px;">
           </td> 
          </tr> 
         </tbody> 
        </table> 
        </table>
       </div> 
      </form> 
     </div> 
     <div class="order_tbl"> 
      <table class="table-list" style="width: 99%;"> 
       <colgroup> 
        <col width="8"> 
        <col width="17"> 
        <col width="15"> 
        <col width="15"> 
        <col width="40"> 
        <col width="15"> 
        <col width="20"> 
        <col width="20">
       </colgroup> 
       <thead style="background:#dbf1fc"> 
        <tr> 
         <th class="b-l"> 序号</th> 
         <th>图片详情</th> 
         <th>上传时间</th> 
         <th>图片名称</th> 
         <th>图片链接</th> 
         <th>所属类别</th> 
         <th>权重</th> 
         <th class="b-r">操作</th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> 
         <td colspan="5" class="blank"></td> 
        </tr>
 	<#list pw.result as obj>
         <tr class="content" id="tr_record_${obj.id}"> 
         <td colspan="1" style="text-align: center;"> 
           <input type='checkbox' name='ck' value=${obj.id?default('')}>${obj_index+1}
						</br>
						<#if obj.status?default('')==1>
                    		<b style="color:red">发布</b>
                    	<#else>
                    		未发布
		            	</#if>
          </td> 
         <td class="p-values"> 
         <a target="_blank" href="${_ui1}${obj.imgurl?default()}"> <img style="width:100px;height:100px" src="${_ui1}${obj.imgurl?default()}" ></a>
          </td> 
         <td class="t-c">${obj.createDt?string('yyyy-MM-dd HH:mm:ss')} </td> 
         <td>${obj.name?default('')}</td>
         <td class="t-c"> 
          <a target="_blank" href="${_ui1}${obj.imgurl?default()}">${_ui1}${obj.imgurl?default()}</a>
         </td> 
         <td class="t-c"> 
         	<#if obj.imgType?exists>
			<#if obj.imgType==1>
				PC
			</#if>
			<#if obj.imgType==2>
				WAP
			</#if>
			<#if obj.imgType==3>
				APP
			</#if>
			<#if obj.imgType==4>
				领秀海报
			</#if>
			<#if obj.imgType==5>
				领秀素材图片
			</#if>
		<#else>
		</#if>
         </td>
         <td class="t-c" title="${obj.weight?default('')}" id="img_${obj.id}" ondblclick="setSort(&apos;${obj.id?default('')}&apos;)" imgsort="${obj.weight?default('')}"> 
         <#if obj.weight?exists>
			${obj.weight?default('')}
		<#else>
		</#if>
         </td>
         <td class="t-c"> <input type="button" name="copy"  class="btn05" id="copy${obj_index+1}" data="${_ui1}${obj.imgurl?default('')}" value="复制链接" /><span style="color:#f1680c"></span>
        	 <a href="javascript:deleteRecord(${obj.id});" >删除</a>
         </td> 
        </tr> 
      </#list>
       </tbody> 
      </table> 
      <br /> 
      <!--分页开始-->
       	<#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
	  <!--分页结束-->
      </div> 
     </div> 
    </div>   
   </div>
  </div>
 </body>
 <script>
 function setSort(k){
	var i = $("#img_"+k).attr("imgsort"); 
	html ='<input type="text" value='+i+' id="sortv'+k+'" style="width:50px;" maxlength="2" onkeyup="on1(this);"/><input type="button" class="btn01" onclick="upimgsort('+i+',&apos;'+k+'&apos;)" value="修改" style="margin-left:15px;margin-top:2px;">';
	$("#img_"+k).html(html);
}
function on1(v){
	v.value=v.value.replace(/\D/g,0);
}
function upimgsort(i,v1){
	    if(!window.confirm("您确定要修改该记录吗?")){
		  	return;
		 }	
		 var sortv = $("#sortv"+v1).val()
		 if(sortv!=''){
			 jQuery.ajax({
			       type: "post",
			       url: " ${base}/leader/uploadImage!upLeaderImg.action",	  
			       data:{"id":v1,"sort":sortv},
			       success: function(data){
			          if(data>0){
			          $alert("success","操作成功！");
			          $("#img_"+v1).html(sortv);
			          $("#img_"+v1).attr("imgsort",sortv); 
			          }else{
			          	$alert('error','网络异常!');
			          }
			       },error:function(){
			       		$alert('error','网络异常!');
			       }
		  		}); 
		 }else{
		 	$alert('warn','请输入序号!');
		 }
    }
  function fabu(){
    	var str="";
		$('input[name="ck"]:checked').each(function(){
	        str+=$(this).val()+",";
        });
        if(str!=''){
			jQuery.ajax({
		       type: "post",
		       url: " ${base}/leader/uploadImage!ajaxfabu.action",	  
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
 function deleteRecord(id){
	 if(!window.confirm("您确定要删除该记录吗?删除的数据不能恢复！")){
	  	return;
	 }
			jQuery.ajax
	   ({
	       type: "post",
	       url: "${base}/leader/uploadImage!del.action",	  
	       data:{"id":id},
	       success: function(data)
	       {
	          if(data>0){
	          	alert("操作成功");
	          	$('#tr_record_'+id).remove();
	          }else{
	          	alert("网络异常");
	          }
	       },error:function(){
	       		alert("网络异常");
	       }
	       
	   }); 
	}
	
 $(function(){
 		$("input[name=copy]").each(function(){
			//复制按钮
		ZeroClipboard.setMoviePath("${base}/web/js/zeroclipboard-1.0.7/ZeroClipboard.swf");
		var clip = new ZeroClipboard.Client(); 	
		clip.setHandCursor(true); 
		var obj=$(this);
		var objId =$(this).attr("id");
		clip.glue(objId);
		clip.addEventListener("mouseDown", function(client) {  
			    clip.setText($(obj).attr("data")); 
			}); 
		clip.addEventListener("mouseOver", function(client) {  
		   $(obj).css("color","#f1680c");
		});   
		clip.addEventListener( "mouseOut", function(client) {         
		    $(obj).css("color","");   
		}); 
		clip.addEventListener( "complete", function(){  
		   // $(obj).parent().find("span").html("复制成功"); 
		   // window.setTimeout(function(){$(obj).parent().find("span").html("")},2000);
		   $alert("success","复制成功！");
		});
		})
 });
 
 
  function query(){
 	$("#p_curPage").val(1);
 	$("#form1").submit();
 }
  function uploadImg(){
	var filName =$("#imgfile").val();
 	filName=filName.replace(/\s/g,"");
 	if(null==filName || filName.length==0){
 		$alert("warn","请选择要上传的文件！");
 		return false;
 	}
 	var imageName=$("#imgName").val();
 	if(imageName.length==0){
 		$alert("warn","上传图片的名称不能为空！");
 		return false;
 	}
	var qzs=$("#qzs").val();
 	var imgtype=$("#imgtype").val();
   var url="${base}/leader/uploadImage!uploadImage.action?imageName="+imageName+"&weight="+qzs+"&imgType="+imgtype;
   $("#upload-btn").val("..");
   $("#upload-btn").attr("disabled",true);
      $.ajaxFileUpload({
        url: url,
        secureuri: false,
        fileElementId: "imgfile",
        dataType: "json",
        success: function(data,status)
        {
          var flag=data.flag;
          if(flag==1){
          	$alert("success","图片上传成功！");
          	window.location.reload();
          }else{
          	$alert("error",data.msg);
          }
            $("#upload-btn").val("上传");
   			$("#upload-btn").attr("disabled",false);
        },
        error: function (data, status, e)
        {	 
            $alert("error","图片上传失败！");
            $("#upload-btn").val("上传");
   			$("#upload-btn").attr("disabled",false);
        }
    });
  }
   $("#form1").keydown(function(){
	if(event.keyCode==13){
	$("#querybut").click();
	}
});
 </script>
</html>