<div class="wrap" id="healthcareProductCty" style="display:block">
    <div class="all-sort-list cp-all-sort-list">

<#list rss as cate>
		<#if cate.category_level == 2>
		<#assign sid = cate.id>
	    <div class="item">
		        <h3>
		            <a href="/${parentid}/${cate.id}/ylist.html" class="all-column"><strong>${cate.category_name?default('')}<i style="width:9px;height:8px;background:url('/web/images/brand-right-arrow.png') no-repeat;"></i></strong></a> 
		            <p <#if cate_index == 5>style='border-bottom:none;'</#if>>
		            <#assign level2_index = 0>
		            <#list allcates as cate2c>
		            <#if cate2c.category_level == 3 && cate2c.parent_id == sid && level2_index <3 >
		                <#assign level2_index = level2_index+1>
			            <a href="/${parentid}/${cate2c.id}/ylist.html">${cate2c.category_name?default('')}</a>&nbsp;&nbsp;
		            </#if>
		            </#list>
		            </p>
		        </h3>
		        
		        <div class="item-list clearfix">
		                <div class="subitem">
		        		<#list allcates as cate3>
		        		<#if cate3.category_level == 3 && cate3.parent_id == sid>
		        		<#assign did = cate3.id>
			                	<h2><span onclick="window.location.href='/${parentid}/${cate3.id}/ylist.html';">${cate3.category_name?default('')}</span></h2>
			                	<p>
			                	<#list allcates as cate4>
			                	<#if cate4.category_level == 4 && cate4.parent_id == did>
			                	<a href="/${parentid}/${cate4.id}/ylist.html" target="_blank">${cate4.category_name?default('')}</a><em>|</em>
			                	</#if>
			                	</#list>
			                	</p>
		            	</#if>
		            	</#list>
		            	</div>
		            	<div class="cat-right">
                        	<a href="/" class="header-big-adv" target="_blank"><img src="/static/image/temp/category/bjg_a_${cate_index+1}.jpg"/></a>
							<span>
								<img class="img-ui-1" src="/static/image/temp/category/bjg_b_${cate_index+1}.jpg" onclick="window.open('/','_blank');"/>
								<img class="img-ui-2" src="/static/image/temp/category/bjg_c_${cate_index+1}.jpg" onclick="window.open('/','_blank');"/>
							</span>
                    	</div>
		        </div>
	    </div>	
		</#if>
</#list>
</div>
</div>

