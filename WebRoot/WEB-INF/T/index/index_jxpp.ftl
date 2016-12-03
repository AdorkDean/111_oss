<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index < 3 >
<div class="brand-box brand-box1" id="rbox${obj_index+1}">


            	<div class="brand-adv">
                	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${obj.cposition.imgUrl?if_exists}" onclick="window.open('${obj.cposition.txt?if_exists}','_blank');" style="cursor:pointer;"/>
                </div>
                
                <ul class="brand-list clearfix">
                	<li class="first-brand">
						<#assign datas=(obj.cposition.imgLink?if_exists?split(","))>
                    	<a href="${datas[1]}" target="_blank">
                        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${datas[0]}"/>
                            <p>${datas[2]}</p>
                        </a>
                    </li>
                    
                    <li>
                    	<#assign datas=(obj.cposition.title?if_exists?split(","))>
                    	<a href="${datas[1]}" target="_blank">
                        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${datas[0]}"/>
                            <p>${datas[2]}</p>
                        </a>
                    </li>
                    
                    <li>
                    	<#assign datas=(obj.cposition.titleAll?if_exists?split(","))>
                    	<a href="${datas[1]}" target="_blank">
                        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${datas[0]}"/>
                            <p>${datas[2]}</p>
                        </a>
                    </li>
                    
                    <li class="long-brand">
                    	<#assign datas=(obj.cposition.titleUrl?if_exists?split(","))>
                    	<a href="${datas[1]}" target="_blank">
                        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${datas[0]}"/>
                            <p>${datas[2]}</p>
                        </a>
                    </li>
                    
                    
                    <li>
                    	<#assign datas=(obj.cposition.txtLink?if_exists?split(","))>
                    	<a href="${datas[1]}" target="_blank">
                        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${datas[0]}"/>
                            <p>${datas[2]}</p>
                        </a>
                    </li>
                    
                    <li>
                    	<#assign datas=(obj.cposition.price?if_exists?split(","))>
                    	<a href="${datas[1]}" target="_blank">
                        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${datas[0]}"/>
                            <p>${datas[2]}</p>
                        </a>
                    </li>
                    
                    <li>
                    	<#assign datas=(obj.cposition.marketPrice?if_exists?split(","))>
                    	<a href="${datas[1]}" target="_blank">
                        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${datas[0]}"/>
                            <p>${datas[2]}</p>
                        </a>
                    </li>
                    
                    <li>
                    	<#assign datas=(obj.cposition.priceUrl?if_exists?split(","))>
                    	<a href="${datas[1]}" target="_blank">
                        	<img src="http://www.111yao.com/upload/image/300.jpg" class="lazy" data-original="${datas[0]}"/>
                            <p>${datas[2]}</p>
                        </a>
                    </li>
                </ul>
            </div>

</#if>
</#list>































        


