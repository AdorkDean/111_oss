<div class="w1210">
        <div class="top-adv fr">
            <ul class="top-adv-img fr">
                <#list attInfo?sort_by('weight')?reverse as obj>
					<#if obj_index<3 >
					     <li><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"/></a></li>
					</#if>
				</#list>
            </ul>
        </div>
    </div>