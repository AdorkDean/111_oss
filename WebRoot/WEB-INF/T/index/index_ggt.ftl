<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<1 >
	<img src="${obj.cposition.imgUrl?if_exists}" width="300" height="130">
	</#if>
</#list>
