<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<1 >
<h2 class="doc-recommend-title">${obj.cposition.title?if_exists}</h2>
<div class="doc-recommend-info doc-recommend-info2">
	<div class="w1210">
		<p class="pathogeny">${obj.cposition.titleAll?if_exists}</p>
        <div class="doc-n-recommend clearfix clear">
        	<div class="doc-info">
            	<img src="${obj.cposition.titleUrl?if_exists}"/>
                <#assign txt=(obj.cposition.txt?if_exists?split(";"))>
                <p>药师姓名：${txt[0]}</p>
                <p>从业年限：${txt[1]}</p>
            </div>
            <div class="recommend-info">
                <a class="recommend-link clearfix" href="${obj.cposition.imgLink?if_exists}" target="_blank">
                    <div class="recommend-img"><img src="${obj.cposition.imgUrl?if_exists}"/></div>
                    <div class="recommend-text">
                    	<p class="rcm-title">${obj.cposition.price?if_exists}</p>
                        <p class="rcm-brand">${obj.cposition.marketPrice?if_exists}</p>
                        <#assign txtLink=(obj.cposition.txtLink?if_exists?split(";"))>
                        <p class="rcm-price">市场价：¥${txtLink[0]}</p>
                        <p class="rcm-price">本站价：<span>¥${txtLink[1]}</span></p>
                    </div>
                </a>
                <a href="javascript:void(-1)" onclick="shortBug(${obj.cposition.priceUrl?if_exists})" class="rcm-buy-now">立即购买</a>
                <a href="javascript:void(0)" onclick="addCartAlert(${obj.cposition.priceUrl?if_exists})" class="rcm-into-cart">加入购物车</a>
            </div>
        </div>
	</div>
</div>
</#if>
</#list>