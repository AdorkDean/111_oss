<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<1 >
	<div class="product-sider-adv">
	   <img src="${obj.cposition.imgUrl?if_exists}" onclick="window.open('${obj.cposition.imgLink?if_exists}','_blank')" style="cursor:pointer;"/>
	</div>
</#if>
</#list>
