<#list attInfo?sort_by('weight')?reverse as obj>
    <a href="${obj.cposition.price?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a>
</#list>