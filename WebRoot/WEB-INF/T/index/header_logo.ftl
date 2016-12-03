<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<1 >
    <a href="${obj.cposition.imgLink?if_exists}" target="_self"><img src="${obj.cposition.imgUrl?if_exists}"/></a>
</#if>
</#list>
