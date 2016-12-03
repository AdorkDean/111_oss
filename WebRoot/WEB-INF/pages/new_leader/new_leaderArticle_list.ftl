<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>健康领秀文章管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/leader/newLeaderArticle!geiArticleList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>领秀姓名： <input  name="leaderName" type="text" value="${leaderName?default('')}" class="input input_v1" /></td> 
           <td>领秀手机： <input  name="leaderMobile" type="text" value="${leaderMobile?default('')}" class="input input_v1" /></td> 
           <td>领秀编号： <input  name="leaderCode" type="text" value="${leaderCode?default('')}" class="input input_v1" /></td> 
           <td>审核状态： 
					 <select name="checkStatus" >
					 <option value="-1">全部</option>
					 <option <#if checkStatus?default(-1)==0> selected</#if> value="0">未提交审核</option>
					 <option <#if checkStatus?default(-1)==1> selected</#if> value="1">审核中</option>
					 <option <#if checkStatus?default(-1)==2> selected</#if> value="2">审核通过</option>
					 <option <#if checkStatus?default(-1)==3> selected</#if> value="3">审核不通过</option>
				 	</select>
		
			</td> 
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
           </td> 
          </tr> 
         </tbody> 
        </table> 
       </div> 
      </form> 
     </div> 
     <!--查询条件结束->
     
     <!--显示列表开始-->
     <div class="order_tbl"> 
      <table class="table-list"> 
       <thead style="background:#dbf1fc"> 
        <tr> 
         <th class="b-l"> 序号 </th> 
         <th> 领秀编号</th> 
         <th> 领秀姓名 </th> 
         <th> 领秀手机号</th> 
         <th> 审核状态</th> 
         <th> 文章明细</th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
         ${resc.code?default('')}		
         </td> 
         <td align="center"> 
         ${resc.real_name?default('')}		
         </td> 
         <td align="center"> 
         ${resc.phone?default('')}	
         </td> 
         <td align="center"> 
         <#if resc.audit_status?default(-1)==0>
         	未提交审核
         <#elseif resc.audit_status?default(-1)==1>
         	审核中
         <#elseif resc.audit_status?default(-1)==2>
         	已通过
         <#elseif resc.audit_status?default(-1)==3>
         	未通过
         	
         </#if>
         </td>          
         <td align="center"> 
         <a href="${base}/leader/newLeaderArticle!articleDetail.action?id=${resc.id?default(0)}" >查看</a>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="6" class="blank"></td> 
        </tr> 
       </tbody> 
      </table>
      <br /> 
      <!--显示列表开始-->
      <!--分页开始--> 
       	<#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
      <!--分页结束--> 
     </div> 
    </div> 
   </div> 
  </div>  
 </body>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script>	
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	
	//发布商品
	function publish_article(articleid)
	{
		$("#publish_leader_article_btn_"+articleid).val("...");
		$("#publish_leader_article_btn_"+articleid).attr("disabled",true);
		$.ajax(
		{
			url: "/leader/newLeaderArticle!publishArticle.action",
			type:"POST",
			data:{"articleId":articleid},
			async:false,
			cache:false,
			success: function(data) 
			{
				$alert("success","发布成功！");
				window.setTimeout(function()
				{
					window.location.reload(true);	
				},1000);
			},
			error: function(e)
			{
				$("#publish_leader_article_btn_"+articleid).val("发布");
				$("#publish_leader_article_btn_"+articleid).attr("disabled",false);
			}
	    });
	}
	
	
</script>
</html>
<style>
* {font-size:12px;}
.btn01 {font-size:12px;}
</style>