<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 8 >
<li>
    <div class="usual-list <#if obj_index != 6>first-0${obj_index+1}</#if><#if obj_index == 6>last-usual</#if>" <#if obj_index == 6>style="margin-top:-1px;"</#if>>
        <a href="${obj.cposition.imgLink?if_exists}" target="_blank">
            <img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}"/>
        </a>
    </div>
</li>
</#if>
</#list>






































































        


