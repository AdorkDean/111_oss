 <#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<1 >
		        <li class="li01"><img src="${obj.cposition.imgUrl?if_exists}" alt="" width="294" height="230"/></li>
		</#if>
	</#list>