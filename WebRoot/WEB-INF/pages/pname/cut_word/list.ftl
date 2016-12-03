<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>分词管理</title> 
  <#include "/WEB-INF/pages/inc/common.ftl">
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form" style="width:96.1%;"> 
     <form name="form1" id="form1"  action="${base}/cut/word.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(5)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td style="width:240px;">分词名称：<input  name="cutWord.cutword" type="text" value="${cutWord.cutword?default('')}" class="input input_v1" style="height:30px;border:#ccc 1px solid;"/></td> 
           <td> 
           		<input type="button"  id="buttonsub" value="查询" class="btn01" onclick="submitForm()" style="margin-left:10px;"/>
           		<input type="button"  id="buttonsub" value="添加" class="btn01" onclick="add()"/>
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
         <th>名称</th>        
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
         	${resc.cutword?default('')}
         </td>  
         <td align="center">
         	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_BIAOQIAN_LIEBIAO_BIANJI">
         	<input type="button" class="btn01" value="编辑" onclick="window.location.href='${base}/cut/word!edit.action?cutWord.id=${resc.id}'"/>
         	</@security.authorize>
         	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_BIAOQIAN_LIEBIAO_SHANCHU">
         	<input type="button" class="btn01 delete_bnt" value="删除" val="${resc.id}"/>
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
	//添加
	function add()
	{
		window.location.href = "${base}/cut/word!add.action";
	}
	//平台检索事件
	function searchBySort()
	{
		$("#form1").submit();
	}
	
	
	$().ready(function() {
		${flash_message()}
		
		$(".delete_bnt").click(function(){
			
			if(window.confirm("确认删除该分词?"))
			{
				var obj = $(this);
				$(this).val("...").attr("disabled",true);
				
				$.post("${base}/cut/word!delete.action",{"cutWord.id":$(this).attr("val")},function(data)
				{
					if(data == true){
						$alert("success","恭喜，数据删除成功！");	
						obj.parent().parent().remove();					
					}else{
						$alert("error","数据删除失败！");
						$(this).val("刪除");
					}
				});
			}
		});
	});
</script>
</html>