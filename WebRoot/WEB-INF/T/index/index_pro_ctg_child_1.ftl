<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<6 >	
	<li>
    	<a class="collect-list <#if obj_index == 0>collect-curr</#if>" href="${obj.cposition.imgLink?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a>
        <div class="column-text" <#if obj_index == 0>style="display:block"</#if>>
            <div class="column-list-text" style="margin-top:0px;overflow:hidden;font-size:12px;font-family:'宋体';">
                    <#assign rows=(obj.cposition.txt?if_exists?split(","))>
               		<#list rows as oj>
               		<#assign datas=(oj?split("["))>
               			<a href="/p/${datas[1]?if_exists}" target="_blank">${datas[0]}</a>
               			<#if oj_has_next>
               			<em>|</em>
               			</#if>
               		</#list>
            </div>
        </div>
    </li>
</#if>
</#list>
		



















