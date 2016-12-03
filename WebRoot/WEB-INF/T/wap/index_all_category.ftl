<ul class="index-column clearfix">
	<#list attInfo?sort_by('weight')?reverse as obj>
	<li onclick="window.location.href='${obj.cposition.imgLink?if_exists}';"><a style="background:${obj.cposition.titleAll?if_exists};"><b style="background: ${obj.cposition.titleAll?if_exists} url(${obj.cposition.imgUrl?if_exists}) no-repeat 2px 0; background-size: 44px"></b><span>${obj.cposition.title?if_exists}</span></a></li>
	</#list>
</ul>

