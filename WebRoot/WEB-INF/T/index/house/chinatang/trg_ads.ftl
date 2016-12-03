<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<1 >
	<div class="sp-banner"><a href="${obj.cposition.imgLink?if_exists}" target="_blank" style="height:417px;background:url('${obj.cposition.imgUrl?if_exists}') no-repeat center top;display:block;"></a></div>
</#if>
</#list>

