<ul class="hot-today-list column3-list clearfix clear">
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<3 >
    <li <#if obj_index == 2>class="distance-none"</#if> ><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"/></a></li>
</#if>
</#list>
</ul>