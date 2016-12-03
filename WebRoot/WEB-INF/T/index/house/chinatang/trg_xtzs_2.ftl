<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index <1 >
    <li class="li02">
        <h3 onclick="window.open('${obj.cposition.imgUrl?if_exists}','_blank');">${obj.cposition.imgLink?if_exists}</h3>
        <p>${obj.cposition.title?if_exists}</p>
        <span>
        <a href="${obj.cposition.titleAll?if_exists}" target="_blank">${obj.cposition.titleUrl?if_exists}</a>
        <a href="${obj.cposition.price?if_exists}" target="_blank">${obj.cposition.marketPrice?if_exists}</a>
        </span>
    </li>
</#if>
</#list>




         