 <#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<4 >
	    <li>
			<a href="${obj.cposition.imgLink?if_exists}"  target="_blank"
			<#if obj_index==0 >class="bt-pink" <#elseif obj_index==1> class="bt-green"<#elseif obj_index==2> class="bt-blue"<#else> class="bt-orange"</#if>>
		    	<img src="${obj.cposition.imgUrl?if_exists}"/>
		        <div class="hot-product-info">
		            <p class="hot-product-title">${obj.cposition.title?if_exists}</p>
		            <p class="hot-product-price"><span>¥${obj.cposition.price?if_exists}</span><i>¥${obj.cposition.marketPrice?if_exists}</i></p>
		        </div>
		    </a>
		</li>
	</#if>
</#list>