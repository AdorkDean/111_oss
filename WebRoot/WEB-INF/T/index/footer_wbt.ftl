<div class="footer-icon">
<div class="w1210">
         	<ul class="footer-icon-list">
         	<#list attInfo?sort_by('weight')?reverse as obj>
         	<#if obj_index <5 >
             	  <li>
                 	<b class="footer-ico${obj_index+1}" style="background:url(${obj.cposition.imgUrl?if_exists}) no-repeat;"></b>
                     <p>${obj.cposition.title?if_exists}</p>
                 </li>
            </#if>
         	</#list>
             </ul>
         </div>
     </div>