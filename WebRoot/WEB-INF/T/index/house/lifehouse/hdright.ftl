<ul class="activity-list clearfix">
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<4 >
        <li><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"/></a></li>
</#if>
</#list>
</ul>