<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<1 >
	<div class="yktzx-ui"><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"></a></div>
	</#if>
</#list>
