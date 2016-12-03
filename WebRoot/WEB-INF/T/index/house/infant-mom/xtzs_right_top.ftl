 <li class="li03">
     <#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<1 >
				<a href="${obj.cposition.imgLink?if_exists}" target="_blank">
		        <h3>${obj.cposition.title?if_exists}</h3>
                <img src="${obj.cposition.imgUrl?if_exists}" alt="" height="150" width="150"/>
				</a>
		</#if>
	 </#list>
</li>