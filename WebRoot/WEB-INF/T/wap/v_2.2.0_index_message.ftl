<div class="index-notice-box-wrap">
	<span class="index-notice-ico"></span>
	<div class="index-notice-box" id="marquee6" >
	    <ul>
	    	<#list attInfo?sort_by('weight')?reverse as obj>
	        <li><a href="${obj.cposition.imgLink?if_exists}">${obj.cposition.title?if_exists}</a></li>
	        </#list>
	    </ul>
	</div>
</div>