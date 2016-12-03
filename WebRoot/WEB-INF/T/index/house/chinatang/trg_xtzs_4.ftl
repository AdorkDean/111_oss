<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index <4 >
    <li class="li04">
        <h3 onclick="window.open('${obj.cposition.imgLink?if_exists}','_blank');">${obj.cposition.title?if_exists}</h3>
        <p>${obj.cposition.txt?if_exists}</p>
    </li>
</#if>
</#list>




         