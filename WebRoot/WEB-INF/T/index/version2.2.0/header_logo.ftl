<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<2 >
<h1 class="logo">
   <a href="${obj.cposition.imgLink?if_exists}"><img src="${obj.cposition.imgUrl?if_exists}" /></a>
   <span>111医药馆</span>
</h1>
</#if>
</#list>
