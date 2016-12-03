<div class="w1090">
    <ul class="recommend-list column4-list clearfix clear" style="margin-left:-10px;">
	<#list attInfo?sort_by('weight')?reverse as obj>
        <li <#if obj_index == 0>class="key-product"</#if>>
        	<a href="<#if obj_index == 0>${obj.cposition.imgLink?if_exists}<#else>/p/${obj.cposition.imgLink?if_exists}.html</#if>" target="_blank">
            	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="<#if obj_index == 0>${obj.cposition.imgUrl?if_exists}<#else>http://img.zdfei.com${obj.cposition.imgUrl?if_exists}</#if>" />
                <#if obj_index != 0>
                <div class="index-product-info">
                	<p class="index-product-name">${obj.cposition.txtLink?if_exists}</p>
                    <p class="index-product-express">${obj.cposition.priceUrl?if_exists}</p>
                    <p class="index-product-price"><span class="red"><b>¥</b>${obj.cposition.price?if_exists}</span><i>¥${obj.cposition.marketPrice?if_exists}</i></p>
                </div>
                </#if>
            </a>
        </li>
        </#list>
    </ul>
</div>




                

