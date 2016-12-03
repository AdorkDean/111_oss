<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<6 >
<li class="stmenu <#if obj_index == 0>first-stmenu</#if> <#if obj_index == 5>last</#if>">
	<h3><a href="${obj.cposition.titleAll?if_exists}" class="nav-dropdown" target="_blank"><span>${obj.cposition.title?if_exists}</span></a></h3> 
	<!--#include virtual="/static/imgout/version2.2.0/product_second_category_${obj.cposition.titleUrl?if_exists}.html" -->
</li>
</#if>
</#list>
