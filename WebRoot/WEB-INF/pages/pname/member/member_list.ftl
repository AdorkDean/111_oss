<html>
 <head> 
 <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>会员管理</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/member/member!getMemberList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">		
       <div class="tbl_form">  
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>用户名： <input  name="username" type="text" value="${username?default('')}" class="input input_v1" /></td>
           <td>手机号： <input  name="mobile" type="text" value="${mobile?default('')}" class="input input_v1" /></td>
           <td>真实姓名： <input  name="realName" type="text" value="${realName?default('')}" class="input input_v1" /></td> 
           <td>
           		标记：<select id="tag" name="tag" style="width:100px;">
           				<option value="">请选择</option>
           				<#list tags?if_exists as tag>
           				<option value="${tag.id?default('')}">${tag.name?default('')}</option>
           				</#list>
           			</select>
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUAN_LIEBIAO_ADDTAG">	
           		<input type="button"   id="add_tag" value="加标记" style="letter-spacing:0px;" class="btn01"/>
           		</@security.authorize>
           </td>
            </tr>
            <tr>
            <td>健康领袖：
               <select name="leader" >
					 <option value="-1">全部</option>
					 <option <#if leader?default(-1)==0> selected</#if> value="0">否</option>
					 <option <#if leader?default(-1)==1> selected</#if> value="1">是</option>
				 </select>
            </td> 
             <td>企业用户：
               <select name="enterprise" >
					 <option value="-1">全部</option>
					 <option <#if enterprise?default(-1)==0> selected</#if> value="0">否</option>
					 <option <#if enterprise?default(-1)==1> selected</#if> value="1">是</option>
				 </select>
            </td>
            <td></td>
           <td> <input type="button"   id="buttonsub" value="查询" class="btn01" onclick="submitForm()" /> 
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUAN_LIEBIAO_ADD">
                <input type="button"   id="buttonsub" value="添加" class="btn01" onclick="addMember()" />
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
     <div class="order_tbl"> 
      <table class="table-list"> 
       <thead style="background:#dbf1fc"> 
        <tr>
         <th class="b-l"> <input type="checkbox" class="check_all"/> </th>	 
         <th> 序号 </th> 
         <th> 会员ID </th> 
         <th> 用户名</th> 
         <th> 昵称 </th> 
         <th> 真实姓名</th> 
         <th> 用户等级/积分</th> 
         <th> 电话</th> 
         <th> 认证状态</th>
         <th> 相关标签</th>  
         <th> 是否健康领袖</th> 
         <th> 是否企业用户</th>
         <th> 用户状态</th>  
         <th> 注册时间 </th> 
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="12" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         	<input type="checkbox" class="check_item" value="${resc.id?default('')}"/>	
         </td>
        
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         <td align="center"> 
         ${resc.id?default('')}		
         </td> 
        <td align="center"> 
         ${resc.user_name?default('')}	
         </td>
         <td align="center"> 
         ${resc.nick_name?default('')}	
         </td>
         <td align="center"> 
         ${resc.real_name?default('')}	
         </td>
         <td align="center"> 
         ${resc.gradeName?default('')}<br>
                               会员积分:${resc.integral?default(0)}
         </td>
           <td align="center"> 
         ${resc.mobile?default('')}	
         </td>
         <td align="center"> 
         邮箱：
         <#if resc.is_email_check?exists>
         	<#if resc.is_email_check?default(0)==1>
         	已认证
         	<#else>
         	未认证
         	</#if>
         <#else>
        	 未认证	
         </#if>
         </br>
   手机：
    <#if resc.is_mobile_check?exists>
         	<#if resc.is_mobile_check?default(0)==1>
         	已认证
         	<#else>
         	未认证
         	</#if>
         <#else>
        	 未认证	
         </#if>
         </td>
         <td align="center" class="tag_text">
         	${resc.tag_name?default('')}
         </td> 
         <td align="center"> 
         <#if resc.is_leader?exists>
         	<#if resc.is_leader?default(0)==1>
         	是
         	<#else>
         	否
         	</#if>
         <#else>
        	 否	
         </#if>
         </td>
         <td align="center"> 
         <#if resc.is_enterprise?exists>
         	<#if resc.is_enterprise?default(0)==1>
         	是
         	<#else>
         	否
         	</#if>
         <#else>
        	 否	
         </#if>
         </td>         
		 <td align="center"> 
         <#if resc.status?exists>
         	<#if resc.status?default(0)==1>
         	停用
         	<#else>
         	启用
         	</#if>
         <#else>
        	 启用	
         </#if>
         </td>
         
         <td align="center"> 
         <#if resc.register_date?exists>${resc.register_date?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
          <!--<a href="${base}/member/member!toMemberInfo.action?id=${resc.id?default('')}">查看详情</a></br>-->
          <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUAN_LIEBIAO_CHONGZHIMIMA">
         <a href="${base}/member/member!toUpdatePassword.action?id=${resc.id?default('')}">重置密码</a></br>
         </@security.authorize>
         <#if resc.status?default(0)==1>
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUAN_LIEBIAO_QIDONGYONGHU">
         <a href="javascript:void(0);" onclick="sockUser(${resc.id?default('')},2);">启用用户</a></br>
         </@security.authorize>
         <#else>
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUAN_LIEBIAO_TINGYONGYONGHU">
         <a href="javascript:void(0);" onclick="sockUser(${resc.id?default('')},1);">停用用户</a></br>
         </@security.authorize>
         </#if>
         <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_HUIYUAN_LIEBIAO_FAFANGYOUHUIJUAN">
         <a href="${base}/member/member!toMemberCoupon.action?id=${resc.id?default('')}">发放优惠券</a>
         </@security.authorize>
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="12" class="blank"></td> 
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
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>	

$().ready(function(){
	$(".check_all").click(function(){
		
		if($(this).is(':checked')){
			$(".check_item").attr("checked", true);
		}else{
			$(".check_item").attr("checked", false);
		}
	});
	
	
	$("#add_tag").click(function(){
	
		if($(".check_item:checked").size() > 0){
			var tag = $("#tag").val();
			if(tag != null && tag != ""){
				var ids = "";
			    $(".check_item:checked").each(function(){
			        ids += $(this).val() + ","; 
			    });
				
				$.ajax({
					url:"${base}/member/member!addTag.action",
					type:"post",
					data:{"relationIds":ids,"tagId":tag},
					success:function(data){
	                   if(data==0){
	                      alert("数据保存失败");
	                   }else{
	                   	  var tag_text = $("#tag").find("option:selected").text();
						  $(".check_item:checked").each(function(){
						  	$(this).parent().parent().find(".tag_text").append("<br/>"+tag_text);
						  	
						  });	
	                      alert("数据保存成功");
	                   }
                    }
			  });
			}else{
				alert("请选择标记");	
			}
		}else{
			alert("请选择相关记录");
		}
	});
});
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	
	   $("#form1").keydown(function(){
	if(event.keyCode==13){
	$("#buttonsub").click();
	}
});

   function addMember(){
      window.location.href="${base}/member/member!toMemberInfo.action";
   }


function sockUser(id,status){
$.ajax({
url:"${base}/member/member!sockUser.action",
			type:"post",
			data:{"id":id,"status":status},
			success:function(data){
                   if(data==0){
                   alert("数据保存失败");
                    }else{
                    alert("数据保存成功");
                  submitForm();
                    }
                    }
  });
}
</script>
</html>