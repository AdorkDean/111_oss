<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index <1 >
    <li class="li01"><img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" width="294" height="230"/></li>
</#if>
</#list>
