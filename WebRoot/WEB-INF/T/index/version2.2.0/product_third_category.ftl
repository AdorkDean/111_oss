<#list attInfo?sort_by('weight')?reverse as obj>
<dt>${obj.cposition.imgLink?if_exists}</dt>
<dd class="clearfix">
	<a <#if obj.cposition.titleAll?exists && obj.cposition.titleAll != ''>href="${obj.cposition.title?if_exists}"</#if> target="_blank">${obj.cposition.titleAll?if_exists}</a>
    <a <#if obj.cposition.titleUrl?exists && obj.cposition.titleUrl != ''>href="${obj.cposition.titleUrl?if_exists}"</#if> target="_blank"><span class="red">${obj.cposition.txt?if_exists}</span></a>
    <a <#if obj.cposition.txtLink?exists && obj.cposition.txtLink != ''>href="${obj.cposition.txtLink?if_exists}"</#if> target="_blank">${obj.cposition.price?if_exists}</a>
    <a <#if obj.cposition.marketPrice?exists && obj.cposition.marketPrice != ''>href="${obj.cposition.marketPrice?if_exists}"</#if> target="_blank">${obj.cposition.priceUrl?if_exists}</a>
</dd>
</#list>


