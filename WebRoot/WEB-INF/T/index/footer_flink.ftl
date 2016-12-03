	<#list attInfo?sort_by('weight')?reverse as obj>
    <li><a><img src="${obj.cposition.imgUrl?if_exists}" alt="暂无图片"/></a></li>
    </#list>
