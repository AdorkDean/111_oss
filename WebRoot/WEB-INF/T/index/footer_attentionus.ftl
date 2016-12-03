<dd>
	<ul>
	<#list attInfo?sort_by('weight')?reverse as obj>
    <li><a href="${obj.cposition.imgLink?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a></li>
    </#list>
	</ul>
</dd>