<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<3 >
<li>
	<a href="${obj.cposition.imgLink?if_exists}" class="rcm-list-link" target="_blank">
    	<div class="rcm-list-img">
        	<img src="${obj.cposition.imgUrl?if_exists}"/>
            <p class="rcm-list-cover"><span>${obj.cposition.txt?if_exists}</span></p>
        </div>
        <div class="rcm-list-text">
            <p class="rcm-list-title">${obj.cposition.titleAll?if_exists}</p>
            <p class="rcm-list-brand">${obj.cposition.title?if_exists}</p>
            <p class="rcm-list-price">市场价：¥${obj.cposition.marketPrice?if_exists}</p>
            <p class="rcm-list-price">本站价：<span>¥${obj.cposition.price?if_exists}</span></p>
        </div>
    </a>
    <a href="javascript:void(-1)" onclick="shortBug(${obj.cposition.priceUrl?if_exists})" class="rcm-list-buy-now">立即购买</a>
    <a href="javascript:void(0)" onclick="addCartAlert(${obj.cposition.priceUrl?if_exists})" class="rcm-list-into-cart">加入购物车</a>
</li>
</#if>
</#list>