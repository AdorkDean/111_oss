<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index <1 >
    <li class="li03">
        <h3 onclick='open('${obj.cposition.imgLink?if_exists}','_blank');'>${obj.cposition.title?if_exists}</h3>
        <img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" height="150" width="150"/>
    </li>
</#if>
</#list>




         