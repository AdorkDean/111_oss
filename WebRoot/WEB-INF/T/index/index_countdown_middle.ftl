<ul class="panic-buying-pro clearfix">
	<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index < 5 >
	<li>
    	<a href="${obj.cposition.imgLink?if_exists}" target="_blank">
        	<img src="${obj.cposition.imgUrl?if_exists}">
            <p>${obj.cposition.title?if_exists}</p>
            <div class="panic-buying-price">
            	${obj.cposition.marketPrice?if_exists}<i>${obj.cposition.price?if_exists}</i>
            </div>
            <b class="grabgo"></b>
        </a>
    </li>
    </#if>
    </#list>
</ul>
        


