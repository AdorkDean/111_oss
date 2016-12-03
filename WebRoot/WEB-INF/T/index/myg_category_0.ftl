<p><#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index &lt; 3 >
	 <a href="${obj.cposition.imgLink?if_exists}" target="_blank" class="all-column">${obj.cposition.title?if_exists}</a>
	</#if>
</#list>
</p>