<div id="index-pro-category" style="display:none;">
<div class="wrap wrap2" id="productCategorys">
<div class="dividing-line"></div>
<div class="all-sort-list">
<#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index &lt; 16 >
	     <div class="item">
		        <h3>
		            <b class="menu_ico${obj_index+1}"></b>
		            <a href="${obj.cposition.price?if_exists}" class="all-column" target="_blank">${obj.cposition.titleAll?if_exists}</a>
		            <em>/</em><a href="${obj.cposition.marketPrice?if_exists}" target="_blank">${obj.cposition.title?if_exists}</a>
		        </h3>
		        <div class="item-list clearfix">
		                <div class="subitem">
                                    <h2><span>${obj.cposition.titleAll?if_exists}</span></h2>
                                    <p>
                                      <!--#include virtual="/static/imgout/index_${obj.cposition.priceUrl?if_exists}_1.html" -->
                                    </p>
                                    <h2><span>推荐商品</span></h2>
                                    <p>
                                      <!--#include virtual="/static/imgout/index_${obj.cposition.priceUrl?if_exists}_2.html" -->
                                     </p>
                                     <#if obj.cposition.titleUrl=='1'>
                                        <h2><span>推荐文章</span></h2>
	                                    <div class="subitem-article">
	                                      <!--#include virtual="/static/imgout/index_${obj.cposition.priceUrl?if_exists}_3.html" --> 
	                                    </div>
                                     </#if>
                                </div>
		            	<div class="cat-right">
                        	<!--#include virtual="/static/imgout/index_${obj.cposition.priceUrl?if_exists}_4.html" --> 
                    	</div>
		        </div>
	    </div>	
	</#if>
</#list>
</div>
</div>
</div>
