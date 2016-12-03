<div class="con-sub-right">
<ul>
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<4 >
<li>
    <div class="pic"><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}"/></a></div>
    <div class="text">
        <p><a href="${obj.cposition.imgLink?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a></p>
        <p><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><strong>￥${obj.cposition.marketPrice?if_exists}</strong><em>￥${obj.cposition.price?if_exists}</em></a></p>
    </div>
</li>
</#if>
</#list>
</ul>
</div>
            
            
         
                                
