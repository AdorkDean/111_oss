<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 1 >
<li>
    <div class="usual-list usual-adv">
        <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
            <img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}"/>
        </a>
        <div class="cornerTR">
        	<b>推荐<br/>商品</b>
            <p class="companyInfo">
                <a href="${obj.cposition.titleAll?if_exists}" target="_blank">
                    <img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.title?if_exists}"/>
                    <span class="recommend-tit">${obj.cposition.txtLink?if_exists}</span>
                    <span class="panic-buying-price">
                        ${obj.cposition.marketPrice?if_exists}<i>${obj.cposition.price?if_exists}</i>
                    </span>
                </a>
            </p> 
        </div>
    </div>
</li>
</#if>
</#list>



























        


