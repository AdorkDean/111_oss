<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="fullscreen=yes,preventMove=no" name="ML-Config">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<title><#if obj.name?length gt 10 >${obj.name[0..10]?default('')}...<#else>${obj.name?default('')}</#if></title>
<#assign wap = "http://m.111yao.com/"/>
<#assign ui1 = "http://img.zdfei.com/"/>
<link href="${wap}/web/css/??common.css,iconfont.css,healthyPlanContentAPP.css" rel="stylesheet" type="text/css"/>
</head>
<body onload="loaded()">
<div class="care-main">
    <input type="hidden" id="csum" value="${obj.csum}"/>
    <input type="hidden" id="hid" value="${obj.id?c}"/>
    <input type="hidden" value="${obj.lisths?size}" id="priceLooperNumber"/>
<#list obj.lisths?if_exists as lisths>
 	<#if lisths_index == 0>
		<div class="care-part clearfix">
		<#if obj.bannerImgUrl?default('')!=''>
	    	<div class="care-adv-tit"><img src="${ui1}${obj.bannerImgUrl?default('')}"></div>
		</#if>
		
		<!--滑动菜单-开始-->
		<nav class="health-nav-box wrapper3">
	            <div class="health-nav-box-list">
	                <ul id="newscroller">
	                </ul>
	            </div>
	    </nav>
		<!--滑动菜单-结束-->
		
		
		<!-- 症状介绍 -->
		<#if obj.explain?default('')!=''>
	        <div class="symptom clearfix">
	            <a href="javascript:void(0)" class="out-details" onclick="zhankai(1,0);">
	            <input type="hidden" id="exp0" value="${obj.explain?default('')}"/>
	                <span class="symptom-tag">症状介绍</span>
	                <div class="symptom-text clearfix">
	                    <span class="symptom-corner fr"></span>
			            <#if obj.explain?length gt 50 >
			           		<p>${obj.explain[0..50]?default('')}...</p>
					  	<#else>
							<p>${obj.explain?default('')}</p>
					   </#if>
	                </div>
	            </a>
	        </div>
		</#if>
		</div>
        </#if>
    
    
    <div class="care-part clearfix" <#if lisths_index !=0>style="margin-top:10px;"</#if> >
    <#if lisths.sbannerImgUrl?default('')!=''>
    	<div class="care-adv-tit"><img src="${ui1}${lisths.sbannerImgUrl?default('')}"></div>
	</#if>
	
	<#if lisths.sexplain?default('')!=''>
        <div class="symptom clearfix">
            <a href="javascript:void(0)" class="out-details" onclick="zhankai(1,${lisths_index});">
            <input type="hidden" id="exp${lisths_index}" value="${lisths.sexplain?default('')}"/>
                <span class="symptom-tag">${lisths.sname?default('')}</span>
                <div class="symptom-text clearfix">
                    <span class="symptom-corner fr"></span>
		            <#if lisths.sexplain?length gt 50 >
		           		<p>${lisths.sexplain[0..50]?default('')}...</p>
				  	<#else>
						<p>${lisths.sexplain?default('')}</p>
				   </#if>
                </div>
            </a>
        </div>
	 </#if>
            
            <div class="recomm-product">
        	<#if (lisths.gsum==1)>
	            <div class="recomm-one clearfix">
	                <div class="recomm-one-img"><a href="javascript:;" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
	                <div class="recomm-one-info">
	                    <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
	                    <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}" id="goods_price_one_in_${lisths_index}">¥${lisths.lists[0].appPrice}</p>
	                    <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[0].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[0].gid?c})">加入购物车</a></p>
		            	<input type="hidden" value="${lisths.lists[0].gid?c}" id="goods_id_one_in_${lisths_index}"/>
	                </div>
	            </div>
	        	<div class="c-line"></div>
        	</#if>
        	
        	<#if (lisths.gsum==2)>
        	    <input type="hidden" value="${lisths.gsum}" id="obj_two_${lisths_index}"/>
	            <div class="recomm-two clearfix">
	            	<ul class="clearfix">
	            	<input type="hidden" value="${lisths.lists?size}" id="obj_two_size_${lisths_index}"/>
	            	<#list lisths.lists?if_exists as objs>
	                	<li>
	                    	<a href="#" href="javascript:;" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);"><img src="${ui1}${objs.gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-two-title" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);">${objs.gname}<span>${objs.mainTitle}</span></p>
	                            <p class="recomm-two-price bszjg${lisths.sid?c}_${objs.gid?c}" id="goods_price_two_in_${lisths_index}_${objs_index}">¥${objs.appPrice}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${objs.gid?c}"><a href="javascript:;" onclick="addTao(1,${objs.gid?c})">加入购物车</a></p>
		                		<input type="hidden" value="${objs.gid?c}" id="goods_id_two_in_${lisths_index}_${objs_index}"/>
	                        </div>
	                    </li>
                    </#list>
	                </ul>
	            </div>
        	</#if>
        	
        	<#if (lisths.gsum==3)>
        	    <input type="hidden" value="${lisths.gsum}" id="obj_three_${lisths_index}"/>
        		<div class="recomm-one clearfix">
	                <div class="recomm-one-img"><a href="javascript:;" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
	                <div class="recomm-one-info">
	                    <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
	                    <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}" id="goods_price_three_in_1_${lisths_index}">¥${lisths.lists[0].appPrice}</p>
	                    <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[0].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[0].gid?c})">加入购物车</a></p>
		                <input type="hidden" value="${lisths.lists[0].gid?c}" id="goods_id_three_in_1_${lisths_index}"/>
	                </div>
	            </div>
	        	<div class="c-line"></div>
	            <div class="recomm-two clearfix">
	            	<ul class="clearfix">
	                	<li>
	                    	<a href="javascript:;" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);"><img src="${ui1}${lisths.lists[1].gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-one-title" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);">${lisths.lists[1].gname}<span>${lisths.lists[1].mainTitle}</span></p>
	                    		<p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[1].gid?c}" id="goods_price_three_in_2_${lisths_index}">¥${lisths.lists[1].appPrice}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[1].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[1].gid?c})">加入购物车</a></p>
                       			<input type="hidden" value="${lisths.lists[1].gid?c}" id="goods_id_three_in_2_${lisths_index}"/>
	                        </div>
	                    </li>
	                    <li>
	                    	<a href="javascript:;" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);"><img src="${ui1}${lisths.lists[2].gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-one-title" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);">${lisths.lists[2].gname}<span>${lisths.lists[2].mainTitle}</span></p>
	                    		<p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[2].gid?c}" id="goods_price_three_in_3_${lisths_index}">¥${lisths.lists[2].appPrice}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[2].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[2].gid?c})">加入购物车</a></p>
                   				<input type="hidden" value="${lisths.lists[2].gid?c}" id="goods_id_three_in_3_${lisths_index}"/>
	                        </div>
	                    </li>
	                </ul>
	            </div>
        	</#if>
        </div>
    </div>
    </#list>
    
    <#if obj.reminder?default('')!=''>
	    <div class="care-tips clearfix">
	    	<h2>温馨提示：</h2>
	        <p>${obj.reminder?default('')}</p>
	    </div>
	</#if>
    
    <!-- 推荐套餐 -->
    <input type="hidden" id="bigSize" value="${obj.listhc?size}"/>
    <#list obj.listhc?if_exists as listhc>
    <article class="package-tj">
    	<h2 class="package-tj-title clearfix">
        	<span>药师推荐</span>${listhc.cname?default('')}
        </h2>
        <div class="symptom sy-comment clearfix" style="border-bottom:#efefef 1px solid;">
            <a href="javascript:void(0)" class="out-details" onclick="zhankai(2,${listhc_index});">
            <input type="hidden" id="ccexp${listhc_index}" value="${listhc.commentContent?default('')}"/>
                <span class="sy-comment-tag">
                	<img class="ys_head_url" src="${ui1}${listhc.headUrl?default('')}"/>
                	<span class="ys_real_name">${listhc.realName?default('')}</span>
                	<span class="ys_work_year">从业${listhc.workYear?default('10')}年</span>
                </span>
                <span class="arrow_right_h"></span>
                <div class="symptom-text clearfix">
                    <span class="symptom-corner fr"></span>
		            <#if listhc.commentContent?length gt 50 >
		           		<p>${listhc.commentContent[0..50]?default('')}...</p>
				  	<#else>
						<p>${listhc.commentContent?default('')}</p>
				   </#if>
                </div>
            </a>
        </div>
        <div class="wrapper wrapper${listhc_index}">
                <div class="scroller" style="width:${listhc.gsum*100}px">
                    <ul class="recomm-pro-plus clearfix">
                    <input type="hidden" id="smallSize_${listhc_index}" value="${listhc.listc?size}"/>
                    <#list listhc.listc?if_exists as listc>
                        <li>
                            <a href="javascript:;" onclick="product(${listc.gid?c},&quot;${listc.sname}&quot;);">
                                <img src="${ui1}${listc.gImgUrl}"/>
                                <p class="recomm-pro-tit">${listc.sname}</p>
                                <input type="hidden" id="app_price_${listhc_index}_${listc_index}" value="${listc.appPrice?c}"/>
                                <input type="hidden" id="old_price_${listhc_index}_${listc_index}" value="${listc.price?c}"/>
                                <input type="hidden" id="goods_id_${listhc_index}_${listc_index}" value="${listc.gid?c}"/>
                                <input type="hidden" id="goods_name_${listhc_index}_${listc_index}" value="${listc.sname}"/>
                            </a>
                            <span>+</span>
                        </li>
                    </#list>
                    </ul>
                </div>
			</div>
            <!--立即购买-->
            <div class="price-box clearfix">
            	<div class="total-price">
                	<span>共节省</span><b id="totalSave_${listhc_index}">¥0</b><span>组合套餐</span><b id="combinePrce_${listhc_index}">¥0</b>
                    <p>原价<i id="oldPrice_${listhc_index}">¥0</i></p>
                </div>
                <a href="javascript:;" onclick="addTao(2,${listhc.cid?c})" class="sy-buy">立即购买</a>
            </div>
    </article>
    </#list>
    <!--推荐套餐-->
</div>
<!---->
<div class="b-cover">
	<div class="describ">
    	<div class="describ-tit">
        	<a href="javascript:void(0)" class="describ-x"></a>
                        详细描述
        </div>
        <div class="describ-details">
        </div>
    </div>
</div>
<script type="text/javascript" src="${wap}/web/js/??jquery.min.js,iscroll.js,wap-cart.js,alert.main.js,healthyPlanContentAPP.js"></script>
</body>
</html>
