<ul class="con-main tab-bd">
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<9 >
<li><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"/></a></li>
</#if>
</#list>
</ul>
            
            
            
