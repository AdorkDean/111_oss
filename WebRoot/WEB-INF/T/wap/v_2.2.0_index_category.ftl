<ul class="new-column-list clearfix">
	<#list attInfo?sort_by('weight')?reverse as obj>
	<li><a href="${obj.cposition.imgLink?if_exists}"><b class="column${obj_index+1}"></b><p>${obj.cposition.title?if_exists}</p></a></li>
	</#list>
</ul>