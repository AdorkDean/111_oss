<div class="big-img im-big-img">
   <#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<2 >
	      <a href="${obj.cposition.imgLink?if_exists}" target="_blank" <#if obj_index==0 >class="a-t a-h" <#else>class="a-h"</#if>><img src="${obj.cposition.imgUrl?if_exists}" alt="" width="498" height="237"/></a>
	</#if>
</#list>
</div>
