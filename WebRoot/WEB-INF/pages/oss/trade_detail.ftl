<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>798管理系统工作平台</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />			
</head>

<body style="margin:0 3px">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#576d85"> 
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="middle" align="center"><img src="${base}/web/images/tb.gif" width="14" height="14" /></td>
		                <td width="94%" valign="bottom" class="l_tit">交易数据统计</td>
		              </tr>
		            </table></td>
		            <td align="right" class="l_tit" valign="middle">
		              &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</td>
		          </tr>
		        </table>        
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
     <td height="35px" class="pl15">
     	<form name="form1" id="subform"  action="${base}/boss/tradedetail!detailInit.action" method="post">
			        时间：<input name="condition.starttime" type="text" id="starttime" value="${condition.starttime?if_exists }" size="19"  class="input_time" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" /> &nbsp
			        至&nbsp;<input name="condition.endtime" type="text" id="endtime" value="${condition.endtime?if_exists}" size="19"  class="input_time" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
			        彩种类型：<select name="condition.type" class="select">
							<OPTION  value="" ></OPTION>
							<OPTION  value="1" <#if condition.type?if_exists=="1">selected</#if>>体彩</OPTION>
							<OPTION  value="2" <#if condition.type?if_exists=="2">selected</#if>>福彩</OPTION>
						</select>&nbsp;&nbsp;
			        彩种名称：<select name="condition.lotteryid" class="select">
							<OPTION  value="" ></OPTION>
							<#list condition.lotteryList?if_exists as lottery>
								<OPTION  value="${lottery.lotteryId?if_exists}" <#if condition.lotteryid?default('-1')==lottery.lotteryId?string>selected</#if>>${lottery.lotteryName?default("")}</OPTION>											
							</#list>
						</select>&nbsp;&nbsp;
				订单号码：<input type="text" name="condition.orderno" value="${condition.orderno?default('')}" class="input_time">&nbsp;&nbsp;												
			       投注状态：<select name="condition.status" class="select">
							<OPTION  value="" ></OPTION>													
							<OPTION  value="111" <#if condition.status?if_exists=="111">selected</#if>>等待提取</OPTION>											
							<OPTION  value="121" <#if condition.status?if_exists=="121">selected</#if>>重新调度等待提取</OPTION>
							<OPTION  value="122" <#if condition.status?if_exists=="122">selected</#if>>重新调度等待提取时撤消</OPTION>
							<OPTION  value="211" <#if condition.status?if_exists=="211">selected</#if>>已经提取正在出票</OPTION>
							<OPTION  value="212" <#if condition.status?if_exists=="212">selected</#if>>出票中要求撤消</OPTION>
							<OPTION  value="221" <#if condition.status?if_exists=="221">selected</#if>>接口调度后正在重新出票</OPTION>											
							<OPTION  value="222" <#if condition.status?if_exists=="222">selected</#if>>调度后重新出票中要求撤消</OPTION>
							<OPTION  value="311" <#if condition.status?if_exists=="311">selected</#if>>出票成功 </OPTION>
							<OPTION  value="312" <#if condition.status?if_exists=="312">selected</#if>>出票成功后撤消</OPTION>
							<OPTION  value="322" <#if condition.status?if_exists=="322">selected</#if>>出票失败不再尝试出票</OPTION>													
						</select>
						<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?if_exists}" id="p_curPage">
						<input type="hidden" name="rs.p_pageSize" value="5" id="pageSize">						
						<input type="button" name="buttonsub" id="buttonsub" value="查询" class="btn_sm" />
						<input type="button" name="buttontoexcel" id="buttontoexcel" value="导出" class="btn_sm" />						       
		</form>        
     </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#99c3db" class="w_td" >
      <tr class="t_b">        
        <td width="3%" align="center">序号</td>
        <td width="8%" align="center">订单号</td>
        <td width="5%" align="center">彩种</td>
        <td width="5%" align="center">期次</td>
        <td width="10%" align="center">投注时间</td>
        <td width="15%" align="center">投注号码</td>
        <td width="10%" align="center">玩法</td>
        <td width="3%" align="center">倍数</td>
        <td width="5%" align="center">投注金额</td>
        <td width="5%" align="center">投注状态</td>
      </tr>
      
      <#list pw.result?if_exists as Order>
			      <tr>			        
			        <td height="20" align="center" class="bc">${Order_index+1?default('')}</td>
			        <td align="center" class="bc">${Order.ticketlistId?if_exists}</td>
			        <td align="center" class="bc">${Order.lotteryname?if_exists}</td>
			        <td align="center" class="bc">${Order.issueNum?if_exists}</td>
			        <td align="center" class="bc">${Order.ticketlistAcctime?string('yyyy-MM-dd HH:mm:ss')}</td>
			        <td align="center" class="bc">${Order.ticketlistContent?if_exists}</td>
			        <td align="center" class="bc">${Order.playname?if_exists}</td>
			        <td align="center" class="bc">${Order.ticketlistMultiple?if_exists}</td>
			        <td align="center" class="bc">${Order.ticketlistAmount?if_exists}</td>
			        <td align="center" class="bc">
						<#if Order.ticketlistSendstat?if_exists==111>等待提取										
						<#elseif Order.ticketlistSendstat?if_exists==121>重新调度等待提取
						<#elseif Order.ticketlistSendstat?if_exists==122>重新调度等待提取时撤消
						<#elseif Order.ticketlistSendstat?if_exists==211>已经提取正在出票
						<#elseif Order.ticketlistSendstat?if_exists==212>出票中要求撤消
						<#elseif Order.ticketlistSendstat?if_exists==221>接口调度后正在重新出票										
						<#elseif Order.ticketlistSendstat?if_exists==222>调度后重新出票中要求撤消
						<#elseif Order.ticketlistSendstat?if_exists==311>出票成功 
						<#elseif Order.ticketlistSendstat?if_exists==312>出票成功后撤消
						<#elseif Order.ticketlistSendstat?if_exists==322>出票失败不再尝试出票
						<#else>
						</#if>			        
			        </td>       
			      </tr>
      </#list>
      
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>        
<#include "/WEB-INF/pages/inc/pagebox_condition.ftl">   
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script src="${base?if_exists}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
<script>	
	var p_curPage=$("#p_curPage");
	var buttonsub=$("#buttonsub");
	var buttontoexcel=$("#buttontoexcel");
	buttontoexcel.click(function(){
		p_curPage.attr("value","1");
		$("#subform").attr("action","${base}/boss/tradedetail!importExcel.action");
		$("#subform").submit();
	});	
	buttonsub.click(function(){
		p_curPage.attr("value","1");
		$("#subform").attr("action","${base}/boss/tradedetail!detailInit.action");
		$("#subform").submit();
	});
	
</script>