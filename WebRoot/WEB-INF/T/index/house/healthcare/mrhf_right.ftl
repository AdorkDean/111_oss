<div class="skincare-right">
            <ul>
<#list attInfo?sort_by('weight')?reverse as obj>
			<#if obj_index<7 >
                <li class="li0${obj_index+1}">
                    <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
                        <p>
                            <span>${obj.cposition.title?if_exists}</span>
                            <b>${obj.cposition.titleAll?if_exists}</b>
                        </p>
                        <img src="${obj.cposition.imgUrl?if_exists}" alt="" 
                         <#if obj_index ==0 >
                           width="425" height="236"
                        <#elseif obj_index ==1>
                           width="212" height="236"
                        <#elseif obj_index ==2>
                           width="355" height="236"
                        <#elseif obj_index ==6>   
                           width="355" height="165"
                        <#else>
                           width="212" height="165"
                        </#if>
                        />
                    </a>
                </li>
</#if>
</#list> 
      </ul>
</div>