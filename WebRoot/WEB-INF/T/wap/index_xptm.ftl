 <ul class="one-column-enter">
        	<#list attInfo?sort_by('weight')?reverse as obj>
        		<li>
        			<a href="${obj.cposition.imgLink?if_exists}">
        			<img src="${obj.cposition.imgUrl?if_exists}"/>
        			<p>
        			<#if obj.cposition.titleAll?exists>
        			<span>${obj.cposition.titleAll?if_exists}</span>
        			</#if>
        			${obj.cposition.title?if_exists}
        			</p>
        			</a>
        			
        		</li>
        	</#list>
  </ul>

       
