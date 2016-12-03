<div class="small-img">
<ul>
<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index <6 >
    <li class="li0${obj_index+1}">
          <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
             <p>${obj.cposition.title?if_exists}</p>
             <img src="/web/images/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" width="236" height="236"/>
         </a>
     </li>
</#if>
</#list>
</ul>
</div>




         
