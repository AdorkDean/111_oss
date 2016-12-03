<#list attInfo?sort_by('weight')?reverse as obj>
<a href="${obj.cposition.imgLink?if_exists}" target="_blank">
	<dl class="clearfix clear">
		<dt><img src="${obj.cposition.imgUrl?if_exists}"/></dt>
	    <dd>
	    	<p class="subitem-article-title">${obj.cposition.title?if_exists}</p>
	        <p class="subitem-article-time">${obj.cposition.titleAll?if_exists}</p>
	        <p class="subitem-article-text">${obj.cposition.txt?if_exists}</p>
	    </dd>
	</dl>
</a>
</#list>