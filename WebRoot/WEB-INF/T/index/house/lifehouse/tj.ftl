<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<18 >
         <#if obj_index==0 || obj_index==10>
         <li class="tj-m" >
         <#elseif obj_index==5 || obj_index==13 ||obj_index==14 || obj_index==17>
         <li class="tj-l">
         <#else>
         <li>
         </#if>
      	 <div class=""></div>
        	<a href="${obj.cposition.imgLink?if_exists}" target="_blank"><img src="${obj.cposition.imgUrl?if_exists}"></a>
        </li>
</#if>
</#list>