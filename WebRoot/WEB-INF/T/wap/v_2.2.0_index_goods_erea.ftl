<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj.cposition.titleAll?exists>
		<#if obj.cposition.titleAll != ''>
		<div class="new_index_tips" onclick="window.location.href='${obj.cposition.titleUrl?if_exists}';"><span>${obj.cposition.title?if_exists}</span>${obj.cposition.titleAll?if_exists}</div>
		</#if>     
	</#if>     
	<div class="new-index-adv" <#if obj_index != 0> style="margin-top:10px;"</#if>  >
		<img src="${obj.cposition.imgUrl?if_exists}" onclick="window.location.href='${obj.cposition.imgLink?if_exists}';">
	</div>
	<#if obj.cposition.price?exists>
		<#if obj.cposition.price != ''>
		  	<img src="http://img.zdfei.com/static/image/temp/20160715/ef3f3e8a3c2bd35c1d7d96346bfcb322.png" class="imgarrow"/>
		  	<div class="index-wrapper" id="index_wrapper_${obj_index}">
			    <div class="index-scroller">
			        <ul>
			        	<!--#include virtual="/static/imgout/wap/v_2.2.0_index_goods_erea_${obj.cposition.price?if_exists}.html" -->
			        </ul>
			    </div>
			 </div>
		</#if>
	</#if>
</#list>    	  