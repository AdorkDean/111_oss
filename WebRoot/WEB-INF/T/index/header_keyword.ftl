<ul class="hot-search clearfix clear">
    <#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<6 >
		<li><em>|</em></li>
        <li><a href="${obj.cposition.imgLink?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a></li>
    </#if>
	</#list>
    </ul>


                    
                    
