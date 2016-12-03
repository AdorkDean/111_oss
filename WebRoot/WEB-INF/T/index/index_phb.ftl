<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<6 >
		<li>
			<a  href="${obj.cposition.titleUrl?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a>
		</li>
	</#if>
</#list>
