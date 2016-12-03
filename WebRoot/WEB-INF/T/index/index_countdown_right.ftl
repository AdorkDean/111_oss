<div class="next-panic-buying">

	<h2 class="next-panic-title">下期限时抢购</h2>
	<div class="hdwrap" style="position: relative;">
		<div class="hdwrap-shade" style=" position:absolute; top:0; left:0; height:140px; width:180px; border-radius:4px; background:rgba(0,0,0,.4) url(http://img.zdfei.com/static/image/temp/20160119/fa8a8b307f152c3433079aab2114180f.png) no-repeat center center;"></div>
		<div class="hdflash f_list">
		<div class="flashlist">
	    <#list attInfo?sort_by('weight')?reverse as obj>
	    <#if obj_index < 3 >
	    <div class="f_out" style="position: relative;">
	      <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
	      	<div class="hdwrap-shade" style=" position:absolute; top:0; left:0; height:140px; width:180px; border-radius:4px; background:rgba(0,0,0,.4) url(http://img.zdfei.com/static/image/temp/20160119/fa8a8b307f152c3433079aab2114180f.png) no-repeat center center;"></div>
	        <img src="${obj.cposition.imgUrl?if_exists}" width="180" height="140" title="${obj.cposition.title?if_exists}"/>
	      </a>
	      <div class="picintro">
	        <p>${obj.cposition.title?if_exists}</p>
	      </div>
	    </div>
	    </#if>
	    </#list>
	    </div>
      
<div class="flash_tab" style="display:none">
	<div class="tabs f_tabs" style="width:180px;">
          <ul>
          	<#list attInfo?sort_by('weight')?reverse as obj>
	    	<#if obj_index < 3 >
            <li class="f_tab opdiv">
              <a href="${obj.cposition.imgLink?if_exists}" title="${obj.cposition.title?if_exists}">
              </a>
            </li>
            </#if>
	    	</#list>
          </ul>
     </div>
</div>

	</div>
	</div>
</div>


