<div class="sp-logo-big">
 <#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<1 >
		       <a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}" alt="" height="166" width="300"/></a>
		</#if>
	</#list>
</div>