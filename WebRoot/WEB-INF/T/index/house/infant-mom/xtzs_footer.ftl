 <#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<4 >
		        <li class="li04">
		        <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
                <h3>${obj.cposition.title?if_exists}</h3>
                <p>${obj.cposition.txt?if_exists}</p>
                </a>
                </li>
		</#if>
	 </#list>