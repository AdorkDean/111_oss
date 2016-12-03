<ul class="activity-list clearfix">
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 4 >
           <li>
           	<a href="${obj.cposition.imgLink?if_exists}" target="_blank">
			<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}"/>
			</a>
           </li>
</#if>
</#list>
</ul>















        


