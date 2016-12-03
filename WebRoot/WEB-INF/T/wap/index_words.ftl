<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 1 >
<h2>${obj.cposition.imgLink?if_exists}</h2>
</#if>
</#list>