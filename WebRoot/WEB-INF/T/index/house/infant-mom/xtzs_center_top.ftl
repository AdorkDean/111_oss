<li class="li02">
      <#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<1 >
		        <h3><a href="${obj.cposition.imgLink?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a></h3>
                <p>${obj.cposition.txt?if_exists}</p>
		</#if>
	 </#list>
    <!--#include virtual="/static/imgout/house/infant-mom/infantmom_xtzs_center_top_list.html" -->
</li>
	