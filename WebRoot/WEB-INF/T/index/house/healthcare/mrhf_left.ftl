<#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<1 >
		 <div class="skincare-left"><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}" alt="" width="214" height="404"/></a></div>
		</#if>
	</#list> 