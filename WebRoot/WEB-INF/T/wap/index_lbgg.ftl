<div class="slider">
  <ul>
        	<#list attInfo?sort_by('weight')?reverse as obj>
        		<li><a href="${obj.cposition.imgLink?if_exists}"><img src="${obj.cposition.imgUrl?if_exists}"/></a></li>
        	</#list>
  </ul>
</div>


