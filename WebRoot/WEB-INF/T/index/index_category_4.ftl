<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index &lt; 1 >
	<a href="${obj.cposition.imgLink?if_exists}" class="header-big-adv" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"/></a>
	<span>
		<img class="img-ui-1" src="${obj.cposition.title?if_exists}" onclick="window.open('${obj.cposition.titleAll?if_exists}','_blank');"/>
		<img class="img-ui-2" src="${obj.cposition.price?if_exists}" onclick="window.open('${obj.cposition.marketPrice?if_exists}','_blank');"/>
	</span>
</#if>
</#list>