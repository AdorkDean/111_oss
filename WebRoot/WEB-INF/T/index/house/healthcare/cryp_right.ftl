<div class="skincare-right adult-right">

            <ul>
<#list attInfo?sort_by('weight')?reverse as obj>
			<#if obj_index<6 >
                <li class="li0${obj_index+1}"> 
                    <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
                        <p>
                            <span>${obj.cposition.title?if_exists}</span>
                            <b>${obj.cposition.titleAll?if_exists}</b>
                        </p>
                        <img src="${obj.cposition.imgUrl?if_exists}" alt="" 
                        <#if obj_index ==0>
                        width="213" height="236"
                        <#elseif obj_index == 1>
                       width="213" height="236"
                        <#elseif obj_index == 2>
                        width="355" height="236"
                        <#elseif obj_index == 3>
                        width="213" height="165"
                        <#elseif obj_index == 4>
                        width="213" height="165"
                        <#elseif obj_index == 5>
                        width="355" height="165"
                        </#if>
                        />
                    </a>
                </li>
</#if>
</#list> 
            </ul>

        </div>
