<div class="cp-shopping-img">
  <ul>
<#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<2 >
		      <li class="<#if obj_index ==0 >l-li<#else>r-li</#if>">
                    <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
                        <p>
                            <span>${obj.cposition.title?if_exists}</span>
                            <b>${obj.cposition.titleAll?if_exists}</b>
                        </p>
                        <#if obj_index ==0 >
                          <img src="${obj.cposition.imgUrl?if_exists}" alt="" width="425" height="235"/>
                          <#else>
                           <img src="${obj.cposition.imgUrl?if_exists}" alt="" width="356" height="235"/>
                        </#if>
                        
                     </a>
                </li>
		</#if>
	</#list> 
 </ul>
</div>