<div class="nav">
    <ul class="clearfix clear">
    <#list attInfo?sort_by('weight')?reverse as obj>
	<#if obj_index<10 >
        <li>
        	<a id="header-nav-${obj_index+1}" <#if obj_index ==7>style="position:relative;"</#if> href="${obj.cposition.imgLink?if_exists}" <#if obj_index ==0> class="current" </#if>  <#if obj_index !=0>target="_blank"</#if> >${obj.cposition.title?if_exists}
        	
	        <#if obj_index ==7>
	        	<span style="position:absolute; top:0; right:6px;display:block; height:14px; width:26px; text-align:center; line-height:14px; color:#fff; font-size:10px; border-radius:3px; background:#FF4141;">hot<i style=" display:block; width:7px; height:4px; position:absolute; bottom:-4px; left:5px; background: url(http://img.zdfei.com/static/image/temp/20160115/ea860c9a292abcdc996c017a2d9f648b.png) no-repeat;"></i></span>
	        </#if>
            
        	</a>
        </li>
    </#if>
	</#list>
    </ul>
</div>
