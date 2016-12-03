<#if (model.pageInfo.pages?if_exists >= 1)> 
<!-- 显示分页信息 start -->
<table width="100%" border=0>
	<tr>
		<td>
			<div id="page" align="right">
 				<#assign itemfrom = model.pageInfo.getPage()?if_exists-1*model.pageInfo.getPageSize()?if_exists + 1>     
              		<#if (model.pageInfo.getPage()>6) >
              			<#assign pfrom = model.pageInfo.getPage()-5>
              			<#assign pto = model.pageInfo.getPage() + 4>								
              			<#if (pto>model.pageInfo.getPages())>
              				<#assign pto = model.pageInfo.getPages()>								
              			</#if>							
              		<#else>							
              			<#if (model.pageInfo.getPages()>10)>
              				<#assign pfrom = 1><#assign pto = 10>								
              			<#else>
              				<#assign pfrom = 1>
              				<#assign pto = model.pageInfo.getPages()>								
              			</#if>							
              		</#if>	
              		<div align="right" >
						显示 <font color="red">${model.pageInfo.start?if_exists}-${model.pageInfo.end?if_exists}</font>条    
						共<font color="red">${model.pageInfo.pages?if_exists}</font>页<font color="red">${model.pageInfo.count?if_exists}</font>条							
						
						<#if model.pageInfo.page == 1>
							首页
						<#else>
							<a href="#" onclick="return goPage(1);">首页</a>
						</#if>


						<#foreach p in pfrom..pto>
							<#if p != model.pageInfo.getPage()>
								<a href="#" onclick="return goPage(${p});"><font color="red">${p?if_exists}</font></a>
							<#else>
								<font class="current" >${p?if_exists}</font>
							</#if>
						</#foreach>
	
						<#if model.pageInfo.page == model.pageInfo.pages>
							末页
						<#else>
							<a href="#" onclick="return goPage(${model.pageInfo.pages?if_exists});">末页</a>
						</#if>

						到第<input name="page" type="text" size="2"/>页<label><input type="button" name="Submit" value="GO" onclick="return goPage(document.logForm.page.value);"/></label>
					</div>
			</div>
		</td>
	</tr>
</table>
<#else> 
	<tr><td><font color="red">没有满足条件的记录,请尝试其他搜索条件。</font></td></tr>
</#if>
<input name="NextPage" type="hidden" value="${model.pageInfo.page?if_exists}"/>
<input name="PageEnd" type="hidden" value="${model.pageInfo.end?if_exists}"/>





<script>
	function goPage(pageNum){
		
		if(!isInts(pageNum) ){
			alert("请正确输入要跳转的页码！");
			document.logForm.page.value = "";
			document.logForm.page.focus();
			return false;
		}
		
		if( pageNum >0 ){
		
			if(pageNum > ${model.pageInfo.pages?if_exists} || pageNum < 1){
				alert("页码超出范围");
				document.logForm.page.focus();
				return false;
			}else{
				// alert("qq1="+pageNum);
				document.logForm.page.value = pageNum;
				document.logForm.submit();
				return false;
			}
		}else{
			alert("请正确输入要跳转的页码！");
			document.logForm.page.value = "";
			document.logForm.page.focus();
			return false;
		}
	}

	function isInts(str) {
    	if (str == ""){
      		return false;
    	}
   		var r = /^[0-9]+$/;
    return r.test(str);
}
</script>
<!-- 显示分页信息 end -->
