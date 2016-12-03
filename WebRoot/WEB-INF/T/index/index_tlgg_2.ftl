<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 1 >
    <div class="adv-bar mt20" style="background:${obj.cposition.title?if_exists};">
    	<div class="w1210">
        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" style="cursor:pointer;" onclick="window.open('${obj.cposition.imgLink?if_exists}');"/>
        </div>
    </div>	
</#if>
</#list>

