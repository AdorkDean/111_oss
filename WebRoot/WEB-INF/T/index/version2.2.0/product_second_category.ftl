<div class="children">
    <div class="w1090 clearfix clear">
	<#list attInfo?sort_by('weight')?reverse as obj>
    	<div class="nav-column <#if obj_index == 3>last</#if><#if obj_index == 7>last</#if><#if obj_index == 11>last</#if><#if obj_index == 15>last</#if><#if obj_index == 19>last</#if> ">
        	<h3><a href="${obj.cposition.titleAll?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a></h3>
            <div class="nav-column-info">
            	<dl class="clearfix clear">
                	<!--#include virtual="/static/imgout/version2.2.0/product_third_category_${obj.cposition.titleUrl?if_exists}_${obj.cposition.txtLink?if_exists}.html" -->
                </dl>
            </div>
        </div>
		</#list>
      </div>
</div>