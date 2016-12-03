<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>模板管理</title> 
  <#include "/WEB-INF/pages/inc/common.ftl">
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form" style="width:96.1%;"> 
     <form name="form1" id="form1"  action="${base}/template/subject!list.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(5)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td style="width:240px;">专题名称：<input  name="tsubject.name" type="text" value="${tsubject.name?default('')}" class="input input_v1" style="height:30px;border:#ccc 1px solid;"/></td> 
           <td style='width:100px;'>
           		<select name="platform" onchange="searchBySort()" style="float:left;width:100px;height:30px;border:#ccc 1px solid;">
	    			<option value="0" <#if platform == 0>selected="selected"</#if>>请选择平台</option>
	    			<option value="1" <#if platform == 1>selected="selected"</#if>>PC</option>
	    			<option value="2" <#if platform == 2>selected="selected"</#if>>WAP</option>
	    			<option value="3" <#if platform == 3>selected="selected"</#if>>APP</option>
	    		</select>
           </td>
           <td> 
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUODONGGUANLI_ZHUANTILIEBIAOGUANLI_LIEBIAO_CHAXUN">
           		<input type="button"  id="buttonsub" value="查询" class="btn01" onclick="submitForm()" style="margin-left:10px;"/>
           		</@security.authorize>
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUODONGGUANLI_ZHUANTILIEBIAOGUANLI_LIEBIAO_TIANJIA">
           		<input type="button"  id="buttonsub" value="添加" class="btn01" onclick="addSubject()"/>
           		</@security.authorize>
           </td> 
          </tr> 
         </tbody> 
        </table> 
       </div> 
      </form> 
     </div> 
     <!--查询条件结束->
     
     <!--显示列表开始-->
     <div class="order_tbl" style="width:99.5%;margin-top:-23px;"> 
      <table class="table-list"> 
       <thead style="background:#dbf1fc"> 
        <tr> 
         <th class="b-l">序号</th>
         <th>平台</th>
         <th>专题名称</th>
         <th>文件名称</th>
         <th>绝对地址 </th> 
         <th>专题访问地址</th>
         <th>创建时间</th> 
         <th>修改时间</th> 
         <th>修改人</th> 
         <th class="b-r">操作</th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         	${resc_index+1?default('')}	
         </td> 
         <td align="center">
         	<#if resc.ptype == 1>PC</#if>
         	<#if resc.ptype == 2>WAP</#if>
         	<#if resc.ptype == 3>APP</#if>
         </td>
         <td align="center"> 
         	${resc.name?default('')}
         </td> 
         <td align="center"> 
        	 ${resc.nameFile?default('')}		
         </td>         
          <td align="center"> 
         	${resc.templetePath?default('')}		
         </td>
         
         <td align="center"> 
         	<a href='${resc.ztPath}' target='_blank'>${resc.ztPath?default('')}</a>		
         </td>
         <td align="center"> 
         	<#if resc.createDt?exists>${resc.createDt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
         	<#if resc.lastDt?exists>${resc.lastDt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
         	${resc.lastEdit?default('')}		
         </td>
         
         <td align="center">
            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUODONGGUANLI_ZHUANTILIEBIAOGUANLI_LIEBIAO_BIANJI">
         	<input type="button" class="btn01" value="编辑" onclick="window.location.href='${base}/template/subject!edit.action?id=${resc.id}'"/>
         	</@security.authorize>
         	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUODONGGUANLI_ZHUANTILIEBIAOGUANLI_LIEBIAO_CHAKAN">
         	<input type="button" class="btn01" value="查看" id="del_${resc.id}" onclick="window.open('${resc.ztPath}')" style="margin-top:5px;"/>
         	</@security.authorize>
         	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUODONGGUANLI_ZHUANTILIEBIAOGUANLI_LIEBIAO_SHANCHU">
         	<!--input type="button" class="btn01" value="删除" id="del_${resc.id}" onclick="delectData(${resc.id})" style="margin-top:5px;"/-->
         	</@security.authorize>
         </td>	
        </tr> 
        </#list>
        <tr> 
         <td class="blank"></td> 
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

<script type="text/javascript">	
	//表单提交	
	function submitForm()
	{
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	//键盘按下时间
	$("#form1").keydown(function(event)
	{
		if(event.keyCode==13)
		{
			$("#buttonsub").click();
		}
	});
	//去添加专题页
	function addSubject()
	{
		window.location.href = "${base}/template/subject!add.action";
	}
	//删除专题
	function delectData(id)
	{
		if(window.confirm("删除该条记录，对应的专题文件也将会被删除，确定吗？"))
		{
			$("#del_"+id).val("...").attr("disabled",true);
			var url = "/template/subject!delete.action?id="+id;
			$.post(url,null,function(data)
			{
				if(data == 1)
				{
					$alert("success","恭喜，数据和文件删除成功！");
					window.setTimeout(function()
					{
						window.location.reload();
					},2000);
				}
				else
				{
					$alert("error","数据和文件删除失败！");
				}
				$("#del_"+id).val("删除").attr("disabled",false);
			});
		}
	}
	//平台检索事件
	function searchBySort()
	{
		$("#p_curPage").val("1");
		$("#form1").submit();
	}
	
</script>
</html>