<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<1 >
<div class="brand-banner ban01" style="background:${obj.cposition.title?if_exists}">
        <div class="brand-banner-img">
        <a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}" alt="广告图"/></a>
        </div>
</div>
</#if>
</#list>
