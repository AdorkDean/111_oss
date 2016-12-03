 <div class="cp-shopping-bottom clearfix clear">
        <div class="cp-shopping-list">
            <ul>
			<#list attInfo?sort_by('weight')?reverse as obj>
			<#if obj_index<5 >
			      <li <#if obj_index ==4 >class="r-li"<#else></#if>>
                    <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
                        <p>
                            <span>${obj.cposition.title?if_exists}</span>
                            <b>${obj.cposition.titleAll?if_exists}</b>
                        </p>
                        <img src="${obj.cposition.imgUrl?if_exists}" alt="" width="<#if obj_index ==4 >356<#else>212</#if>" height="166"/>
                    </a>
                </li>
			</#if>
			</#list> 
		</ul>
	</div>
</div>            