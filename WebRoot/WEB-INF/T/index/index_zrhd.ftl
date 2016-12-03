<div class="modules w1210 activity mt20">
	<div class="module-title">
    	<b></b>
        <h2>最热活动</h2>
    </div>
    <div class="modules-info hot-activity" id="a-big-c">
        <input type="hidden" value="1" id="identity"/>
    	<ul class="activity-num" id="signnum" style="background:none; width:80px; left:698px;">
        	<li><a href="javascript:;" class="current" id="act1">1</a></li>
            <li><a href="javascript:;" id="act2">2</a></li>
            <li><a href="javascript:;" id="act3">3</a></li>
        </ul>
        <#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index < 3 >
    	<div class="activity-box activity-box1" id="atycon${obj_index+1}">
        	<div class="activity-adv">
            	<img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" style="cursor:pointer;" onclick="window.open('${obj.cposition.imgLink?if_exists}','_blank');"/>
            </div>
        	<#if obj_index == 0>
        		<!--#include virtual="/static/imgout/index_zrhd_product_1.html" -->
        	</#if>
        	<#if obj_index == 1>
        		<!--#include virtual="/static/imgout/index_zrhd_product_2.html" -->
        	</#if>
        	<#if obj_index == 2>
        		<!--#include virtual="/static/imgout/index_zrhd_product_3.html" -->
        	</#if>
        	
        	
        </div>
        </#if>
        </#list>
        
    </div>
</div>






























        


