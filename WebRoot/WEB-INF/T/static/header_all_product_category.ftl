<div class="wrap wrap2" id="productCategorys">
<div class="dividing-line"></div>
<div class="all-sort-list">

<#list rss as cate>
		<#if cate.category_level == 2 && cate_index <16>
		<#assign sid = cate.id>
	    <div class="item">
		        <h3>
		            <b class="menu_ico${cate_index+1}"></b>
		            <a href="/${cate.id}/list.html" class="all-column">${cate.category_name?default('')}</a>
		            <#list allcates as cate2c>
		            <#if cate2c.category_level == 3 && cate2c.parent_id == sid>
		            <em>/</em><a href="/${cate2c.id}/list.html">${cate2c.category_name?default('')}</a>
		            <#break>
		            </#if>
		            </#list>
		        </h3>
		        <div class="item-list clearfix">
		                <div class="subitem">
		        		<#list allcates as cate3>
		        		<#if cate3.category_level == 3 && cate3.parent_id == sid>
		        		<#assign did = cate3.id>
			                	<h2><span onclick="window.location.href='/${cate3.id}/list.html';">${cate3.category_name?default('')}</span></h2>
			                	<p>
			                	<#list allcates as cate4>
			                	<#if cate4.category_level == 4 && cate4.parent_id == did>
			                	<a href="/${cate4.id}/list.html" target="_blank">${cate4.category_name?default('')}</a><em>|</em>
			                	</#if>
			                	</#list>
			                	</p>
		            	</#if>
		            	</#list>
		            	</div>
		            	<div class="cat-right">
                        	<a href="/" class="header-big-adv" target="_blank"><img src="/static/image/temp/category/a_${cate_index+1}.jpg"/></a>
							<span>
								<img class="img-ui-1" src="/static/image/temp/category/b_${cate_index+1}.jpg" onclick="window.open('/','_blank');"/>
								<img class="img-ui-2" src="/static/image/temp/category/c_${cate_index+1}.jpg" onclick="window.open('/','_blank');"/>
							</span>
                    	</div>
		        </div>
	    </div>	
		</#if>
</#list>
</div>
</div>
