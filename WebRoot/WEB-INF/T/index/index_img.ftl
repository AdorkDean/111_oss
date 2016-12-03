<ul id="picshow">
<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<4 >
		<li><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"></a></li>
	</#if>
</#list>
</ul>

<ul id="select_btn">
	<#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<4 >
	       <li class="<#if obj_index==0>blue<#elseif obj_index==1>fen<#elseif obj_index==2>green<#elseif obj_index==3>yellow</#if>">
	       <a href="${obj.cposition.imgLink?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a>
	       </li>
	      </#if>
	  </#list>
</ul>

    