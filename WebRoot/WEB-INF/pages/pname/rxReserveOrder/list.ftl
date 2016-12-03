<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>待处理处方药预订单列表</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
     
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"  action="${base}/reserveorder/reserveorder!reserverOrderList.action" method="post">
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
		<input type="hidden" name="type" value="${type?default(0)}" id="type">	
		<input type="hidden" name="title" value="" id="title">
		<input type="hidden" name="colum" value="" id="colum">
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
           <td>用户名： <input  name="user_name" type="text" value="${user_name?default('')}" class="input input_v1"/></td> 
           <td>审核状态：
        		  <select name="audit_status" >
					 <option <#if audit_status?default('-1')=='-1'> selected</#if> value="-1">全部</option>
					 <option <#if audit_status?default('-1')=='0'> selected</#if> value="0">待审核</option>
					 <option <#if audit_status?default('-1')=='1'> selected</#if> value="1">审核通过</option>
					 <option <#if audit_status?default('-1')=='2'> selected</#if> value="2">审核不通过</option>
				  </select>
           </td> 
           <td>处理状态：
        		  <select name="order_status" >
					 <option <#if order_status?default('-1')=='-1'> selected</#if> value="-1">全部</option>
					 <option <#if order_status?default('-1')=='0'> selected</#if> value="0">待处理</option>
					 <option <#if order_status?default('-1')=='8'> selected</#if> value="8">已下单</option>
					 <option <#if order_status?default('-1')=='9'> selected</#if> value="9">已取消</option>
				  </select>
            </td>
              <td style="position:relative;"> 
              <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAXUN">	
                     <input style="position:absolute; left:-33px; top:-22px;" type="button" id="buttonsub" value="查询" class="btn05" onclick="submitForm(0)" />
              </@security.authorize> 
            </td>
            </tr>
            <tr>
             <td style="padding-top:10px;">收货人： <input  name="receiver" type="text" value="${receiver?default('')}" class="input input_v1" /></td> 
             <td style="padding-top:10px; position:relative;">收货人手机：<input style="position:absolute; right:0; top:32px;" name="mobile" type="text" value="${mobile?default('')}" class="input input_v1" /></td> 
             <td style="padding-top:10px;"><input type="button" id="buttonadd" value="导出Excel" class="btn05" onclick="submitForm(2)"></td> 
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
        <tr id="exceltitle"> 
         <th>用户名</th> 
         <th>收货人/手机号</th> 
         <th>药品名称</th>
         <th>价格</th> 
         <th>数量</th> 
         <th>合计</th>
         <th>取消原因</th>
         <th>审核状态</th>
         <th>处理状态</th>
         <th>完结状态</th>
         <th>预定时间</th>
         <th class="b-r"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> <td colspan="7" class="blank"></td></tr> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center"> 
         	${resc.user_name?default('')}
         </td> 
        <td align="center"> 
        	${resc.receiver?default('')}</br>
        	${resc.mobile?default('')}
         </td> 
         <td align="center"> 
            ${resc.short_name?default('')}
         </td> 
         <td align="center">
            ${resc.good_price?default('')} 
         </td> 
         <td align="center"> 
            ${resc.num?default('')}
         </td> 
         <td align="center"> 
          	${resc.order_amount?default('')}
         </td> 
         <td align="center"> 
          	${resc.cancel_remark?default('')}
         </td> 
         <td align="center"> 
          <#if resc.audit_status?default(-1)==0>
        	 待审核
         <#elseif resc.audit_status?default(-1)==1>
         	审核通过	
         <#elseif resc.audit_status?default(-1)==2>
           	 审核不通过
         </#if> 
         </td> 
         <td align="center"> 
           <#if resc.order_status?default(-1)==0>
          	 待处理
           <#elseif resc.order_status?default(-1)==8>
           	已下单
           <#elseif resc.order_status?default(-1)==9>
          	 已取消
           </#if> 
         </td> 
         <td align="center">
           <#if resc.is_end?default(-1)==0>
          	 未完结
           <#elseif resc.is_end?default(-1)==1>
           	已完结
           </#if>         
         </td>
          <td align="center"> 
           <#if resc.create_dt?exists>${resc.create_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td> 
         <td align="center"> 
              <a href="${base}/reserveorder/reserveorder!detail.action?id=${resc.rid?default(0)}"  >查看</a>&nbsp;&nbsp;
          	<#if resc.is_end?default(-1)==0>
              <a href="${base}/reserveorder/reserveorder!reserverOrderByUser.action?memberId=${resc.id?default('')}&source=2" >操作</a>
             </#if> 
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="7" class="blank"></td> 
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
function submitForm(type){
	if(type==2){
		 $("#type").val(2);
		 var title = $("#exceltitle").text();
		 $("#title").val(title);
		 $("#colum").val("user_name#receiver#mobile#short_name#good_price#num#order_amount#cancel_remark#audit_status#order_status#is_end#create_dt");
	 }else{
	     $('#p_curPage').val(1);
	     $("#type").val(0);
	 }
   $('#form1').submit();
   $("#type").val(0);
}

$("#form1").keydown(function() {
    if (event.keyCode == 13) {
        $("#buttonsub").click();
    }
});

</script>
</html>