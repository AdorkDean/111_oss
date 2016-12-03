 <#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<5 >
		<li <#if obj_index==0>class="show-ranking-details"</#if>>
		        <p class="ranking-list-title">
		        	<span class="ranking-list-price fr">¥${obj.cposition.price?if_exists}</span>
		            ${obj.cposition.title?if_exists}
		        </p>
		        <dl class="ranking-list-info">
		        	<dt><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"/></a></dt>
		            <dd>
		            	<p>${obj.cposition.titleAll?if_exists}</p>
		                <b><a href="javascript:void(-1)" onclick="shortBug(${obj.cposition.titleUrl?if_exists})">立即购买</a></b>
		            </dd>
		        </dl>
		</li>
	</#if>
</#list>
