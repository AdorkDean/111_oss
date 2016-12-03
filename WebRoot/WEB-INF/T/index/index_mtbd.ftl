<div class="new-info-box" style="font-size:12px;font-family:'宋体';">
	<a class="top-media-link" href="#">
        <dl class="top-media clearfix clear">
        <#list attInfo?sort_by('weight')?reverse as obj>
        <#if obj_index == 0>
            <dt><img onclick="window.open('${obj.cposition.imgLink?if_exists}','_blank');" src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}"/></dt>
            <dd><a href="${obj.cposition.imgLink?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a></dd>
        </#if>
        </#list>
        </dl>
    </a>
    <ul class="media-list">
        <#list attInfo?sort_by('weight')?reverse as obj>
        <#if obj_index <8 >
        <#if obj_index != 0 >
    	<li><a href="${obj.cposition.imgLink?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a></li>
        </#if>
        </#if>
        </#list>
    </ul>
</div>

