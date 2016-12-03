<#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index<3 >
		      <li <#if obj_index==0 >class="current"</#if>>
                    <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
                        <dl>
                            <dt><img src="${obj.cposition.imgUrl?if_exists}" alt="" width="100" height="100"/></dt>
                            <dd>
                                <p>${obj.cposition.title?if_exists}</p>
                                <b>¥${obj.cposition.price?if_exists}</b>
                            </dd>
                        </dl>
                    </a>
                </li>
		</#if>
	</#list> 
