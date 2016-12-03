<#list attInfo?sort_by('weight')?reverse as obj>
<li>
	<a href="/m/${obj.cposition.imgLink?if_exists}.html">
    	<img src="http://img.zdfei.com${obj.cposition.imgUrl?if_exists}">
        <p class="scroll-pro-name">${obj.cposition.txtLink?if_exists}</p>
        <p class="scroll-pro-price">¥${obj.cposition.price?if_exists}</p>
        <input type="hidden" value="${obj.cposition.imgLink?if_exists}"/>
    </a>
</li>
</#list>


