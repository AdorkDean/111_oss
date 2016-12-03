<!-- 显示分页信息 start -->
<div id="page"  class="pageNum">
 <#assign itemfrom = sysRescPw.pageInfo.getPage()?if_exists-1*sysRescPw.pageInfo.getPageSize()?if_exists + 1>              
        <#if (sysRescPw.pageInfo.getPage()>6)><#assign pfrom = sysRescPw.pageInfo.getPage()-5><#assign pto = sysRescPw.pageInfo.getPage() + 4>								<#if (pto>sysRescPw.pageInfo.getPages())><#assign pto = sysRescPw.pageInfo.getPages()>								</#if>							<#else>								<#if (sysRescPw.pageInfo.getPages()>10)><#assign pfrom = 1><#assign pto = 10>								<#else><#assign pfrom = 1><#assign pto = sysRescPw.pageInfo.getPages()>								</#if>							</#if>	
		显示 <font color="red">${sysRescPw.pageInfo.start?if_exists} - ${sysRescPw.pageInfo.end?if_exists}</font> 条    
		共 <font color="red">${sysRescPw.pageInfo.pages?if_exists}</font> 页 
		<font color="red">${sysRescPw.pageInfo.count?if_exists}</font> 条
<#if sysRescPw.pageInfo.page == 1>首页
<#else><a href="#" onclick="goPage(1)">首页</a>
</#if>
<#foreach p in pfrom..pto>	
	<#if p != sysRescPw.pageInfo.getPage()>	
		<a href="#" onclick="goPage(${p})"><font color="red">${p?if_exists}</font></a>
	<#else>	  
		<span class="current"><font color="red">${p?if_exists}</font></span>
	</#if>
</#foreach>
<#if sysRescPw.pageInfo.page == sysRescPw.pageInfo.pages> 末页	
<#else><a href="#" onclick="goPage(${sysRescPw.pageInfo.pages?if_exists})">末页</a>
</#if>
到第
  <input id="pageNext" name="pageInfo.page" type="text" size="2" value="${rs.p_curPage?if_exists}"/>
  页
    <label>
    <input type="button" name="Submit" value="GO" onclick="goPage(document.getElementById('pageNext').value);">
   </label>
</div>  

<input name="pageInfo.pageSize" type="hidden" value="${sysRescPw.pageInfo.pageSize?if_exists}"/>
<#if sysRescPw.pageInfo.pages?if_exists <= 0><script>
document.getElementById('page').style.display="none";
</script>
</#if><script>

//pageInputObj,输入页码的文本框对象
//pages,总页数
function goPage(pageNum)
{ 
	if( !isInts(pageNum) )
	{
		alert("请正确输入要跳转的页码！");
		document.getElementById('pageNext').value = "";
		document.getElementById('pageNext').focus();
		return;
	}
	if( pageNum>0 )
	{
		if(pageNum > ${sysRescPw.pageInfo.pages?if_exists} || pageNum < 1){
			alert("页码超出范围");
			document.getElementById('pageNext').focus();
			return;
		}else{
			document.getElementById('pageNext').value = pageNum;
			document.getElementById('p_curPage').value = pageNum;
			document.form1.action="${base}/user/roleEdit!toEditResc.action";			
			document.form1.submit();
		}
	}else{
		alert("请正确输入要跳转的页码！");
		document.getElementById('pageNext').value = "";
		document.getElementById('pageNext').focus();
	}
}
function isInts(str) 
{
    if (str == "")
    {
      	return false;
    }
    var r = /^[0-9]+$/;
    return r.test(str);
}
</script>
<!-- 显示分页信息 end -->
