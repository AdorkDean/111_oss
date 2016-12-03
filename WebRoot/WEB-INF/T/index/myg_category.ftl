<div id="myg-pro-category" style="display:none;">
<div class="wrap" id="infantmon-pro-c" style="display:block;">
<div class="all-sort-list cp-all-sort-list im-all-sort-list">
<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index &lt; 16 >
	     <div class="item">
		        <h3>
		            <a href="${obj.cposition.price?if_exists}" target="_blank" class="all-column"><strong>${obj.cposition.titleAll?if_exists}<i style="width:9px;height:8px;background:url('/web/images/brand-right-arrow.png') no-repeat;"></i></strong></a> 
		        	<!--#include virtual="/static/imgout/house/infant-mom/myg_${obj.cposition.priceUrl?if_exists}_0.html" -->
		        </h3>
		        <div class="item-list clearfix">
		                <div class="subitem">
                                    <h2><span>${obj.cposition.titleAll?if_exists}</span></h2>
                                    <p>
                                      <!--#include virtual="/static/imgout/house/infant-mom/myg_${obj.cposition.priceUrl?if_exists}_1.html" -->
                                    </p>
                                    <h2><span>推荐商品</span></h2>
                                    <p>
                                      <!--#include virtual="/static/imgout/house/infant-mom/myg_${obj.cposition.priceUrl?if_exists}_2.html" -->
                                     </p>
                                     <#if obj.cposition.titleUrl=='1'>
                                        <h2><span>推荐文章</span></h2>
	                                    <div class="subitem-article">
	                                      <!--#include virtual="/static/imgout/house/infant-mom/myg_${obj.cposition.priceUrl?if_exists}_3.html" --> 
	                                    </div>
                                     </#if>
                                </div>
		            	<div class="cat-right">
                        	<!--#include virtual="/static/imgout/house/infant-mom/myg_${obj.cposition.priceUrl?if_exists}_4.html" --> 
                    	</div>
		        </div>
	    </div>	
	</#if>
</#list>
</div>
</div>
</div>
