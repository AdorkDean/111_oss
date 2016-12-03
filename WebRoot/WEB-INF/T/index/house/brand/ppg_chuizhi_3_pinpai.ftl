<ul class="con-main tab-hd">
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<3 >
<li class="tab <#if obj_index == 0>active</#if>"><a href="${obj.cposition.imgLink?if_exists}"><img src="${obj.cposition.imgUrl?if_exists}" height="160" width="216"/></a></li>
</#if>
</#list>
</ul>
            
            
            
        
            
