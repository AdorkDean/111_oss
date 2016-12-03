<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 15 >
    <li class="${obj.cposition.imgLink?if_exists}" onclick="anchorPage('${obj.cposition.imgLink?if_exists}')">
    	<i></i>
    	<span class="default-s">${obj.cposition.title?if_exists}</span>
        <span class="current-s">${obj.cposition.title?if_exists}</span>
    </li>
</#if>
</#list>                   
                    
                    
                    
                    