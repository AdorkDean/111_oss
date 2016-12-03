<div class="notice-box-wrap">
    <div class="notice-box" id="marquee6">
        <ul>
        	<#list attInfo?sort_by('weight')?reverse as obj>
            <li><a href="${obj.cposition.imgLink?if_exists}" target="_blank"><span></span>${obj.cposition.title?if_exists}</a></li>
            </#list>
        </ul>
    </div>
</div>




















        


