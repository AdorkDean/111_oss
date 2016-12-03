<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<15 >
            <div class="model" id="${obj.cposition.imgLink?if_exists}">
                <h2><i></i>${obj.cposition.title?if_exists}<span>${obj.cposition.titleAll?if_exists}</span></h2>
            </div>
            <!--#include virtual="/static/imgout/version2.2.0/index_stair_${obj.cposition.titleUrl?if_exists}.html" -->
</#if>
</#list>