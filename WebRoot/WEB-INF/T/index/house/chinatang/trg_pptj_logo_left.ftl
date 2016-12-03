<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<1 >
	<div class="sp-logo-big"><a href="${obj.cposition.imgLink?if_exists}" target="_blank">
	<img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" height="166" width="300"/>
	</a></div>
</#if>
</#list>
