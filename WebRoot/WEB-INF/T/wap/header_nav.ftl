<nav class="nav-box" id="wrapper">
     <div class="nav-box-list" id="scroller">
        <ul>
	    <#list attInfo?sort_by('weight')?reverse as obj>
	        <li id="header-nav-${obj.cposition.weight?if_exists}" onclick="window.location.href='${obj.cposition.imgLink?if_exists}';"><a>${obj.cposition.title?if_exists}</a></li>
		</#list>
        </ul>
     </div>
</nav>
