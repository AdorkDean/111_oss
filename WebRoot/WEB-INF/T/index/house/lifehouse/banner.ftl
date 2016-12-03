 <div class="imgbox">
        <ul id="banner-img">
        	<#list attInfo?sort_by('weight')?reverse as obj>
				<li><a href="${obj.cposition.imgLink?if_exists}" target="_blank" style="background:url(${obj.cposition.imgUrl?if_exists}) no-repeat center top"></a></li>
			</#list>  
        </ul>
        <div class="clear"></div>
        <div class="imgnum">
             <#list attInfo?sort_by('weight')?reverse as obj>
				<span <#if obj_index==0 >class="onselect"</#if>></span>
			</#list>
        </div>
	</div>
    