   <div class="pagin fr" style="font-family:'Microsoft Yahei'"> 
    <span class="text">共${pw.pageInfo.count?if_exists}条记录</span> 
    <span class="text">共${pw.pageInfo.pages?if_exists}页</span> 
    <#if pw.pageInfo.page==1>
    <span class="prev-disabled">上一页</span> 
    <span class="prev-disabled">首页</span> 
    <#else>
	    <a class="next" href="#">上一页</a> 
	    <a class="next" href="#">首页</a>
    </#if>
    
    <#if (pw.pageInfo.getPage()>6)><#assign pfrom = pw.pageInfo.getPage()-5><#assign pto = pw.pageInfo.getPage() + 4>
		<#if (pto>pw.pageInfo.getPages())><#assign pto = pw.pageInfo.getPages()>
		</#if>
    <#else>	
    <#if (pw.pageInfo.getPages()>10)>
    	<#assign pfrom = 1><#assign pto = 10>
    <#else>
        <#assign pfrom = 1>
        <#assign pto = pw.pageInfo.getPages()>	
    </#if>	
   </#if>	
   <!--遍历页码条-->
   <#foreach p in pfrom..pto>	
   		<#if p==pw.pageInfo.page>
   			<a href="javascript:void(0)" class="current">${p}</a>
   		<#else>
   			<a href="javascript:void(0)">${p}</a> 
   		</#if>
	</#foreach>
	
	<#if pw.pageInfo.page==pw.pageInfo.pages>
		<span class="prev-disabled">末页</span> 
	    <span class="prev-disabled">下一页</span> 
	<#else>
	    <a class="next" href="javascript:void(0)">末页</a> 
	    <a class="next" href="javascript:void(0)">下一页</a> 
    </#if>
    <input name="totalPage" type="hidden" value="40" />
    <em>转向第 <input name="toPage" type="text" size="2" value="${pw.pageInfo.page}" class="na-text" /> 页 <input type="button" value="确定" onclick="" /></em> 
   </div> 
   <script>
   
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
				if(pageNum > ${pw.pageInfo.pages?if_exists} || pageNum < 1){
					alert("页码超出范围");
					document.getElementById('pageNext').focus();
					return;
				}else{
					document.getElementById('pageNext').value = pageNum;
					document.getElementById('p_curPage').value = pageNum;
					document.form1.submit();
				}
			}else{
				alert("请正确输入要跳转的页码！");
				document.getElementById('pageNext').value = "";
				document.getElementById('pageNext').focus();
			}
	}

   //判断传入的值是否为数字
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
