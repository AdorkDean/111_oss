
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<1 >	
    <div class="column-img">
    	 <img class="lazy"  src="http://www.111yao.com/upload/image/300.jpg" data-original="${obj.cposition.imgUrl?if_exists}" style="cursor:pointer;" onclick="window.open('${obj.cposition.imgLink?if_exists}','_blank');"/>
        <p>${obj.cposition.title?if_exists}</p>
    </div>
</#if>
</#list>




		
		



















