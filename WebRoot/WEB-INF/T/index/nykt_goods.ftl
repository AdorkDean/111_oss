<#list attInfo?sort_by('weight')?reverse as obj>
    <li>
        <#if obj.cposition.titleUrl?exists && obj.cposition.titleUrl != ''>
    	<i class="discount-corner">${obj.cposition.titleUrl?if_exists}折</i>
    	</#if>
    	
    	<a class="product">
        	<img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}"/>
            <p class="pro-tit">${obj.cposition.title?if_exists}</p>
            <p class="pro-price">￥<span id="price_${obj.cposition.txtLink?if_exists}_obj_${obj_index}">${obj.cposition.price?if_exists}</span></p>
            <input type="hidden" value="${obj.cposition.imgLink?if_exists}" id="nykt_goodsid_${obj_index}"/>
        </a>
        <a class="pay-now" href="http://www.111yao.com/p/${obj.cposition.imgLink?if_exists}.html" target="_blank">立即购买<span>&gt;</span></a>
    </li>
</#list>

