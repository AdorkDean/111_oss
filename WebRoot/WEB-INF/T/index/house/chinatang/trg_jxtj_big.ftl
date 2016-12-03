<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index <1 >
    <div class="big-img">
    <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
    <img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" width="498" height="475"/>
    </a>
    </div>
</#if>
</#list>
