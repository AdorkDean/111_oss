<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 1 >
<div class="product-adv">
    <a href="${obj.cposition.imgLink?if_exists}" target='_blank' class="adv-img">
    <img src="${obj.cposition.imgUrl?if_exists}"/>
    </a>
    <a href="javascript:void(0)" class="close-adv"></a>
</div>
            
</#if>
</#list>
<style>
.product-adv{width:1210px; padding-top:7px; margin:0 auto; position:relative;}
.adv-img,.adv-img img{width:1210px; display:block;}
.close-adv{width:8px; height:8px; overflow:hidden; display:block; background:url(/web/img/close_adv.png) no-repeat; background-size:8px; position:absolute; right:5px; top:12px; z-index:9; opacity:.5;}
</style>





















        


