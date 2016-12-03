<#list attInfo?sort_by('weight')?reverse as obj>
    <li>
        <a href="/p/${obj.cposition.imgLink?if_exists}.html" target="_blank">
        	<img src="${obj.cposition.marketPrice?if_exists}" class="imgtags" />
            <img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="http://img.zdfei.com${obj.cposition.imgUrl?if_exists}" width="190" height="190" />
            <p class="new-add-pro-info">${obj.cposition.txtLink?if_exists}</p>
            <p class="new-add-pro-price">¥${obj.cposition.price?if_exists}</p>
        </a>
    </li>
</#list>





























        


