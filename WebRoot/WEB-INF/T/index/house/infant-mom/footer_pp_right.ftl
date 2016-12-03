 <#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<10 >
		      <#if obj_index &gt; 8 ><li class="li${obj_index+1}">
		      <#else>
		      <li class="li0${obj_index+1}">
		      </#if>
		         <a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}" alt="" width="178" height="79"/></a>
		       </li>
		</#if>
	</#list>