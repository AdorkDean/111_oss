<ul class="sp-logo-small">
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<11 >
	<li <#if obj_index+1 != 10>class="li0${obj_index+1}"</#if><#if obj_index+1 == 10>class="li10"</#if> >
	<a href="${obj.cposition.imgLink?if_exists}" target="_blank">
	<img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" width="178" height="79"/>
	</a>
	</li>
</#if>
</#list>
</ul>

        
