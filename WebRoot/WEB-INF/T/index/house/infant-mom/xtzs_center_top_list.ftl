 <span>
<#list attInfo?sort_by('weight')?reverse as obj>
<a href="${obj.cposition.titleUrl?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a>
</#list>
</span>