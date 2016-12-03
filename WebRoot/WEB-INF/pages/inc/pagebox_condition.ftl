<!-- 显示分页信息 start -->
<div class="m clearfix">
<div class="pagin fr" style="font-family:&#39;Microsoft Yahei&#39;">
 <#assign itemfrom = pw.pageInfo.getPage()?if_exists-1*pw.pageInfo.getPageSize()?if_exists + 1>              
        <#if (pw.pageInfo.getPage()>6)>
        <#assign pfrom = pw.pageInfo.getPage()-5>
        <#assign pto = pw.pageInfo.getPage() + 4>
        <#if (pto>pw.pageInfo.getPages())>
        <#assign pto = pw.pageInfo.getPages()>
        </#if>
        <#else>
        <#if (pw.pageInfo.getPages()>10)>
        <#assign pfrom = 1>
        <#assign pto = 10>
        <#else>
        <#assign pfrom = 1>
        <#assign pto = pw.pageInfo.getPages()>
        </#if>
        </#if>	
        <span class="text">共${pw.pageInfo.count?if_exists}条记录</span>
         <span class="text">共${pw.pageInfo.pages?if_exists}页</span>
		
<#if pw.pageInfo.page == 1>
 <span class="prev-disabled">首页</span>
 <span class="prev-disabled">上一页<b></b></span>
<#else>
<a href="#" class="next" onclick="goPage(1)">首页</a>
<a href="#" class="next" onclick="goPage(${pw.pageInfo.page?default(1)-1})">上一页</a>
</#if>
<#foreach p in pfrom..pto>	
	<#if p != pw.pageInfo.getPage()>	
	<a onclick="goPage(${p})" href="#">${p?if_exists}</a>
	<#else>	  
	<a class="current" href="#">${p?if_exists}</a>
	</#if>
</#foreach>
<#if pw.pageInfo.page == pw.pageInfo.pages>
 <span class="prev-disabled">下一页<b></b></span>
  <span class="prev-disabled">末页</span>
<#else>
 <a class="next" href="#" onclick="goPage(${pw.pageInfo.page?default(1)+1})">下一页<b></b></a>
  <a class="next" href="#" onclick="goPage(${pw.pageInfo.pages?if_exists})">末页</a>
</#if>
<em>转向第
 <input id="pageNext" name="pageInfo.page" type="text" class="na-text" size="2" value="${rs.p_curPage?if_exists}"/>
  页
    <label>
    <input type="button" name="Submit" class="toPageSure" value="确定" onclick="goPage(document.getElementById('pageNext').value);" />
   </label>
</div>
</div> 
<style>
.toPageSure {float:right;width:40px;height:25px;border:#ccc 1px solid;margin-right:5px;border-radius:5px;margin-left:5px;background:#f5f5f5;margin-top:2px;cursor:pointer;}
</style>
<input name="pageInfo.pageSize" type="hidden" value="${pw.pageInfo.pageSize?if_exists}"/>
<#if pw.pageInfo.pages?if_exists <= 0>
<script>
document.getElementById('page').style.display="none";
</script>
</#if><script>

//pageInputObj,输入页码的文本框对象
//pages,总页数
function goPage(pageNum)
{ 
	if( !isInts(pageNum) )
	{
		$alert("warn","请正确输入要跳转的页码！");
		document.getElementById('pageNext').value = "";
		document.getElementById('pageNext').focus();
		return;
	}
	if( pageNum>0 )
	{
		if(pageNum > ${pw.pageInfo.pages?if_exists} || pageNum < 1){
			$alert("warn","页码超出范围");
			document.getElementById('pageNext').focus();
			return;
		}else{
			document.getElementById('pageNext').value = pageNum;
			document.getElementById('p_curPage').value = pageNum;
			document.form1.submit();
		}
	}else{
		$alert("warn","请正确输入要跳转的页码！");
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
