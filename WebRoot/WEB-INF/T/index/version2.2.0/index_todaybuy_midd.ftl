<ul class="hot-today-list column4-list clearfix clear" style="margin-left:-10px;">
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<4 >
    <li <#if obj_index == 3>class="distance-none"</#if> ><a <#if obj_index == 3>onclick="showOrHideCouponList(1)"<#else> href="${obj.cposition.imgLink?if_exists}"</#if> target="_blank" <#if obj_index == 3>style="cursor:pointer;"</#if>><img src="${obj.cposition.imgUrl?if_exists}"/></a></li>
</#if>
</#list>
</ul>
