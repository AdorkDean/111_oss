<div class="sp-small-banner">
    <ul>
		<#list attInfo?sort_by('weight')?reverse as obj>
		<#if obj_index < 4>
        <li>
            <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
                <p>
                    <span>${obj.cposition.title?if_exists}</span>
                    <b>${obj.cposition.titleUrl?if_exists}</b>
                </p>
                <img src="${obj.cposition.imgUrl?if_exists}" width="301" height="105"/>
            </a>
        </li>
		</#if>
		</#list>
    </ul>
</div>