<!DOCTYPE html>
<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>位置管理模板列表</title> 
  <#include "/WEB-INF/pages/inc/common.ftl">
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
  <div class="sm_user"> 
  <div class="order">
     <!--查询条件开始-->
     <div id="order_form" class="order_form" style="float:left;width:95.5%;border-bottom:none;padding-bottom:20px;"> 
     <form name="form1" id="subform" action="${base}/position/position!selectPostionList.action" method="get">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage" />
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize" />
		<div style="float:left;"> 
	            <div class="labelName">位置名称：</div>
	            <input name="positionName" type="text" <#if positionName?exists>value="${positionName?default()}"</#if> class="lname-i" style="border:#cccccc 1px solid;" />
	            
	            <div class="labelName" style="margin-left:15px;">检索：</div>
	            <select name="genre" class="select-ui">
	            	<option value="0">请选择</option>
					<#list pwPosType.result as pt>
						<option value="${pt.id}" <#if gid == pt.id> selected="selected"</#if> >
					    	${pt.name}
					    </option>
					</#list>
				</select>
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_MOBAN_CHAXUN">	
	            <input type="button" id="buttonsub" value="查询" class="btn01" onclick="javascript:resetCurPage()" style="margin-left:15px;margin-top:2px;"/>
	            </@security.authorize>
	            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_MOBAN_ADD">	
           		<input type="button" class="btn01" value="添加" onclick="javascript:window.location = '${base}/position/position!gotoPage.action'" style="margin-left:5px;margin-top:2px;"/> 
           		</@security.authorize>
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_MOBAN_CHONGZHI">	
           		<input type="button" class="btn01" value="重置" onclick="javascript:window.location = '${base}/position/position!selectPostionList.action'" style="margin-left:5px;margin-top:2px;"/> 
	            </@security.authorize>
	     </div> 
      </form> 
     </div> 
     <!--查询条件结束->
  
     <!--显示列表开始-->
     <div class="order_tbl" style="width:99.5%;"> 
       <table class="table-list"> 
       <colgroup> 
        <col width="20"> 
        <col width="90"> 
        <col width="90"> 
        <col width="100"> 
        <col width="100"> 
        <col width="70"> 
        <col width="75"> 
        <col width="80"> 
       </colgroup>
       <thead style="background:#dbf1fc"> 
        <tr> 
         <th class="b-l"> 序号 </th> 
         <th> 位置名称 </th> 
         <th> 模板路径 </th> 
         <th> 模板名称 </th> 
         <th> 输出全入境 </th> 
         <th> 所属位置 </th> 
         <th> 创建时间 </th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list pw.result as position>
        <tr class="content"> 
         <td colspan=" 1"> 
         	<div class="p-img">
           <p>${position_index+1?default()}</p> 
          </div> 
         </td> 
         <td> 
          <div class="p-value1" style="text-align:center;"> 
           <a href="${base}/position/managePosition!queryPostionInfo.action?id=${position.id?default('')}">${position.name?default('')}</a> 
          </div> 
         </td> 
         <td class="t-c"> 
          <div class="discount">
            ${position.mPath?default('')}
          </div>
         </td> 
         <td class="t-c"> 
          <div class="discount">
            ${position.mName?default('')}
          </div> </td> 
         <td class="t-c"> 
          <div class="discount">
            ${position.outPath?default('')} 
          </div> </td> 
         <td class="t-c"> 
          <div class="discount">
          	  ${position.genre?default('')}
          </div>
         </td> 
         <td class="t-c">${position.createDt?string('yyyy-MM-dd HH:mm:ss')}</td> 
         <td class="t-c">
          <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_MOBAN_XIUGAI">	
          <input type="button" class="btn01" onclick="window.location='${base}/position/position!gotoPage.action?id=${position.id?default('')}'" value="编辑" style="float:left;"/>
          </@security.authorize>
          <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_WEIZHI_MOBAN_SHANCHU">	
          <input type="button" class="btn01" id="del-btn-${position.id}" onclick="deleteTemplate(${position.id?default('')})" value="删除" style="float:left;margin-left:1px;" /> 
          </@security.authorize>
          <a href="javascript:;" style="float:left;margin-left:5px;" onclick="copyTemplate(${position.id?default('')})">复制</a>
          </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="8" class="blank"></td> 
        </tr> 
       </tbody> 
      </table>
      <br /> 
      <!--显示列表开始-->
      
      <div style="display:none;"> 
      </div>
      <!--分页开始--> 
       	<#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
      <!--分页结束--> 
     </div> 
    </div> 
   </div> 
  </div>  
 </body>
<script>	
	function resetCurPage()
	{
		var posTypeName = $.trim($(".lname-i").val());
		if(posTypeName == '')
		{
			$alert("warn","位置名称不能为空！");
			return;
		}
		if($(".select-ui").val() == '0')
		{
			$alert("warn","请选择位置类别！");
			return;
		}
	 	$("#p_curPage").val("1");
	 	$("#subform").submit();
	}
	
	//删除模板
	function deleteTemplate(id)
	{
		if(confirm("你确定要删除该模板吗?"))
		{
			$("#del-btn-"+id).val("..");
    		$("#del-btn-"+id).attr("disabled",true);
			$.ajax(
			{
				url:'${base}/position/position!deleteTemplate.action',
				data:{"id":id},
				type:'post',
				success:function(data)
				{
					if(data == 0)
					{
						$alert("warn","该位置下已存在内容，不允许删除！");
					}
					if(data == 1)
					{
					    $alert("success","恭喜，删除数据成功！");
					    window.setTimeout(function(){
							window.location.reload();
					    },1000);
					}
					if(data == 2)
					{
						$alert("error","系统异常，删除数据失败！");
					}
					if(data == 3)
					{
						$alert("error","参数错误，删除数据失败！");
					}
					$("#del-btn-"+id).val("删除");
    				$("#del-btn-"+id).attr("disabled",false);
				}
			});
		};
	}
	
	//复制模板
	function copyTemplate(id)
	{
		if(confirm("你确定要复制该模板吗?"))
		{
			$.ajax(
			{
				url:'${base}/position/position!copyTemplates.action',
				data:{"id":id},
				type:'post',
				success:function(data)
				{
					if(data > 0)
					{
						$alert("success","数据复制成功！");
						window.setTimeout(function(){
							window.location.reload();
						},500);
					}
				}
			});
		};
	}
	
	
	
	//位置分类检索
	$(".select-ui").change(function()
	{
		if($(".select-ui").val() == '0')
		{
			window.location.href = "${base}/position/position!selectPostionList.action";
		}
		else
		{
			$("#subform").submit();
		}
	});
	
	//按回车键查询
	$("#subform").keydown(function()
	{
		if(event.keyCode==13)
		{
			$("#buttonsub").click();
		}
	});
</script>
</html>