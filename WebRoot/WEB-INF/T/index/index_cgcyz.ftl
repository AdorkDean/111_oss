<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<1 >
	</#if>
	<#if obj_index<3 >
	   <dl>
        	<dt><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}" width="59" height="58" /></a></dt>
        	<dd><a href="${obj.cposition.txtLink?if_exists}" target="_blank"><strong>${obj.cposition.title?if_exists}</strong>${obj.cposition.txt?if_exists}</a></dd>
		</dl>
	</#if>
</#list>
