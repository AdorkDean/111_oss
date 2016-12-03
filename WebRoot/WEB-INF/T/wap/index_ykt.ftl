<#list attInfo?sort_by('weight')?reverse as obj>
 <li class="clearfix" >
                <a href="/p/${obj.cposition.title?if_exists}.html">
                    <dl>
                        <dt><img src="${obj.cposition.imgUrl?if_exists}"><dt>
                        <dd>
                            <p class="free-shipping-title">${obj.cposition.titleAll?if_exists}</p>
                            <p class="free-shipping-price">￥<span id="span_${obj.cposition.title?if_exists}">${obj.cposition.price?if_exists}</span><i>￥${obj.cposition.marketPrice?if_exists}</i></p>
                        </dd>
                        </dt>
                </a>
                <a href="javascript:void(0);" onclick="staticadd(${obj.cposition.title?if_exists},1);" class="iconfont list-pro-into-cart">C</a>
                <#assign str="{'goodsId':${obj.cposition.title?if_exists},'nowPrice':${obj.cposition.price?if_exists},'platform':'2','htmlObjId':'span_${obj.cposition.title?if_exists}'}">
            	<input type="hidden" id="str_${obj_index+1}" value="${str?if_exists}">
            </li>
</#list>
