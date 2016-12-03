<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>移动端图片管理</title> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js" charset="utf-8" ></script>
  <script type="text/javascript" src="${base}/web/js/ajaxfileupload.js"></script>
  <script type="text/javascript" src="${base}/web/js/zeroclipboard-1.0.7/ZeroClipboard.js"></script>
  <script type="text/javascript" src="${base}/web/js/limitFileSize.js"></script>
  <script type="text/javascript" src="${base}/web/js/imageUpload.js"></script>
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order"> 
     <div id="order_form" class="order_form" style="width:95%;"> 
       <div class="tbl_form"> 
    <form action="${base}/position/uploadImage!goPage2.action" name="form1" id="form1" method="post">
	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
	<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(10)}" id="pageSize">
        <table cellpadding="0" cellspacing="0" border="0"> 
         <colgroup> 
          <col style="width:10%" /> 
          <col style="width:22%" /> 
          <col style="width:9%" /> 
          <col style="width:23%" /> 
          <col style="width:8%" /> 
          <col style="width:30%" /> 
         </colgroup> 
         <tbody> 
          <tr> 
           <td><p>开 始 日 期：</p></td> 
           <td><p><input type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="<#if startDt?exists>${startDt}</#if>" name="startDt" id="startDt"  class="input input_v1" /></p></td> 
           <td style="text-align:right;"><p> 结 束 日 期：</p></td> 
           <td><p><input type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="<#if endDt?exists>${endDt}</#if>" name="endDt" id="endDt" class="input input_v1" /></p></td>
           <td><p>图 片名 称：</p></td> 
           <td><input type="text" style="width:100px;" name="imageName" class="input input_v1" value="<#if imageName?exists>${imageName?default('')}</#if>"/>
           <!--span style="margin-left:22px;">类型 ：<span><select name="imgType" style="width:80px;"><option value="0" <#if imgType?default('')==0>selected="true"</#if>>全部</option><option value="1" <#if imgType?default('')==1>selected="true"</#if>>PC</option><option value="2" <#if imgType?default('')==2>selected="true"</#if>>WAP</option><option value="3" <#if imgType?default('')==3>selected="true"</#if>>APP</option></select-->
           <span style="margin-left:22px;">类型 ：<span><select name="imgType" style="width:80px;"><option value="0">移动端</option></select>
           </td> 
           <td>
           <input type="button" name="querybut" id="querybut" onclick="query()" class="btn01" value="查询" />
           </td> 
          </tr>
          <tr> 
           <td><p>选 择 图 片：</p></td> 
           <td> <p><input type="file" name="imgfile" id="imgfile" class="input input_v1" onchange="$_checkfile(this.id,'0.2')" style="width:100px;"/>（小于200KB）</p></td> 
           <td style="text-align:right;"><p>图 片 名 称：</p></td> 
           <td> <input type="text" id="imgName" class="input input_v1"/></td> 
           <td colspan="2">
           <select id="imgtype" style="width:110px;"><option value="0">移动端</option><!--option value="2">WAP</option><option value="3">APP</option--></select>
           权重：<input type="text" id="qzs" style="width:149px;" value="0" onblur="vd(this);" title="权重" class="input input_v1" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'0')" onafterpaste="this.value=this.value.replace(/\D/g,'0')"/>
           </td>
           <td>
           <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_TUPIAN_SHANGCHUAN">	
           <input type="button" class="btn01" onclick="uploadImg('imgfile')" value="上传" id="upload-btn" />
           </@security.authorize>
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
        <col width="5"> 
        <col width="17"> 
        <col width="15"> 
        <col width="15"> 
        <col width="40"> 
        <col width="15"> 
        <col width="15"> 
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
 	<#list pw.result as obj>
         <tr class="content" id="tr_record_${obj.id}"> 
         <td colspan="1" style="text-align: center;"> 
           ${obj_index+1}
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
         	移动端
         </td>
         <td class="t-c"> 
         ${obj.weight?default('')}
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
</html>