<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 5 >
<div class="new-add-floor">
        <div class="w1210">
            <h2 class="new-add-title clearfix clear">
                <div class="new-add-title-right fr">
                	<b><img src="${obj.cposition.txtLink?if_exists}" width="12" height="16"></b>
                	<a <#if obj.cposition.titleUrl?exists>href="${obj.cposition.titleUrl?if_exists}" target="_blank"</#if> >${obj.cposition.titleAll?if_exists}</a>
                </div>
                <i><img src="${obj.cposition.imgUrl?if_exists}" width="29" height="27"></i>
                <a <#if obj.cposition.imgLink?exists> href="${obj.cposition.imgLink?if_exists}" target="_blank"</#if> >${obj.cposition.title?if_exists}</a>
            </h2>
            <ul class="new-add-list clearfix clear">
                <li class="new-add-list-first"><a href="${obj.cposition.priceUrl?if_exists}" target="_blank"><img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${obj.cposition.price?if_exists}" /></a></li>
                <!--#include virtual="/static/imgout/index_HD_${obj_index + 1}.html" -->
            </ul>
            <#if obj.cposition.marketPrice?exists>
            	<#if obj.cposition.marketPrice == 'Y'>
            	<!--#include virtual="/static/imgout/index_CFY.html" -->
            	</#if>
            </#if>
        </div>
</div>
</#if>
</#list>





























        


