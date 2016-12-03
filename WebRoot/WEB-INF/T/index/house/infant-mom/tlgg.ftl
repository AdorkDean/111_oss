<#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<1 >
			<div class="adv-bar mt20" style="background:${obj.cposition.title?if_exists};">
			    <div class="w1210">
				  <a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}" /></a>
			    </div>
			</div>
	</#if>
</#list>