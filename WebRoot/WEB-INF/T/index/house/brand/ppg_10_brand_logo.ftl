<div class="brand-list-wrap">
<div class="brand-main-list">
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<11 >
<div class="brand-main-img <#if obj_index+1 != 10>img0${obj_index+1}</#if><#if obj_index+1 == 10>img10</#if>">
<a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}"/></a>
</div>
</#if>
</#list>
</div>
</div>
            
            
            
