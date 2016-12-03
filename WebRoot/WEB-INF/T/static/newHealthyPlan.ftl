<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="renderer" content="webkit" />
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="yes" name="apple-touch-fullscreen" />
<meta content="fullscreen=yes,preventMove=no" name="ML-Config" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>
<#if obj.name?length gt 10 >
	${obj.name[0..10]?default('')}...
<#else>
	${obj.name?default('')}
</#if>
</title>
<#assign ui1 = "http://img.zdfei.com"/>
<link href="/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.care-adv-tit img{width:100%; display:block;}
.symptom{height:75px; overflow:hidden; position:relative; border-bottom:1px solid #d7d7d7; background: #FFF; }
.symptom a{height:75px; display:block;}
.symptom-tag{background:#0fa9a3; display:inline-block; font-size:12px; color:#fff; text-align:center; padding:0 2px; width:54px; border-radius:5px; -webkit-border-radius:5px; position:absolute; top:50%; margin:-15px 0 0 15px;}
.symptom-corner{width:9px; height:15px; display:inline-block; background:url(http://img.zdfei.com/static/image/temp/20160104/43407ec4530698d568b96af2c3bf0fa6.png) no-repeat; background-size:9px 15px; float:right; margin:20px 15px 0 15px;}
.symptom-text{ height:54px; padding:11px 0 10px 90px;}
.symptom-text p{ font-size:12px; line-height:18px; height:54px; overflow:hidden;}
.recomm-one{}
.recomm-one-img{float:left; width:50%;}
.recomm-one-img a{ display:block; margin:0 15px; position:relative;text-align:center;display:-webkit-box;display:-ms-flexbox;display:-webkit-flex;display:flex;-webkit-box-pack:center;-ms-flex-pack:center;-webkit-justify-content:center;justify-content:center;-webkit-box-align:center;-ms-flex-align:center;-webkit-align-items:center;align-items:center;flex:1; z-index:10;}
.recomm-one-img a img{width:100%; display:block;}
.recomm-one-info{ float:left; width:50%; padding-top:15px; position: relative;}
.recomm-one-info p{padding:0 15px;}
.recomm-one-title{color:#222; height:38px; overflow:hidden; line-height:1.4em;}
.recomm-one-title span{ display:block; font-size:12px;}
.recomm-one-price{color:#fc4044; font-size:15px;}
.recomm-one .recomm-into-cart a,.recomm-two .recomm-into-cart a{ font-size:15px; color:#fe4310; display:block; height:30px; line-height:30px; border-radius:5px; -webkit-border-radius:5px; border:1px solid #fe4310; margin:5px 0 0 0; text-align:center;}
.c-line{margin:0 0 0 0px; height:1px; background:#d7d7d7;}
.recomm-two{margin-top:1px;}
.recomm-two li{ float:left; width:50%; padding-bottom:15px;}
.recomm-two li a.recomm-a{margin:0 15px; display:block; position:relative;text-align:center;display:-webkit-box;display:-ms-flexbox;display:-webkit-flex;display:flex;-webkit-box-pack:center;-ms-flex-pack:center;-webkit-justify-content:center;justify-content:center;-webkit-box-align:center;-ms-flex-align:center;-webkit-align-items:center;align-items:center;flex:1; z-index:10;}
.recomm-two li a img{width:100%; display:block;}
.recomm-two-info{position: relative;}
.recomm-two-info p{ padding:0 15px;}
.recomm-two-title{color:#222; height:38px; overflow:hidden; line-height:1.4em;}
.recomm-two-title span{ display:block; font-size:12px;}
.recomm-two-price{color:#fc4044; font-size:15px;}
.care-tips{padding:15px 15px 0; color:#3e3e3e; border-top:1px solid #d7d7d7; background:#fff; margin-bottom:10px;}
.care-tips h2{ font-size:15px;}
.care-tips p{margin:5px 0 15px; font-size:12px; line-height:1.6em;}
.package-tj{ background:#fff; margin-bottom:10px;}
.package-tj-title{height:45px; line-height:45px; font-size:12px; color:#222;}
.package-tj-title span{background:#0fa9a3; display:inline-block; font-size:12px; line-height:1.4em; color:#fff; text-align:center; padding:0 6px; border-radius:5px; -webkit-border-radius:5px; margin:15px;}
.sy-comment{background:#efeff4; border-bottom:none;}
.sy-comment-tag{display:inline-block; font-size:12px; text-align:center; padding:0 6px; margin:30px 15px; float:left;}
.price-box{margin:0 15px; padding:10px 0;}
.total-price{ text-align:right; line-height:16px;}
.total-price span{ margin:0 5px 0 20px; color:#222; font-size:12px;}
.total-price b{ font-size:15px; color:#fc4044;}
.total-price p{color:#999; font-size:10px;}
.total-price p i{ font-style:normal; text-decoration:line-through; margin-left:5px;}
.sy-buy{ width:108px; height:32px; line-height:32px; color:#fff; display:inline-block; float:right; text-align:center; font-size:15px; border-radius:5px; -webkit-border-radius:5px; background:#fe4310; margin-top:5px;}
.b-cover{ position:fixed; background:rgba(0,0,0,.8); top:0; left:0; right:0; bottom:0; z-index:100; display:none;}
.describ{ position:absolute; height:70%; top:100%; left:0; right:0; background:#efeff4;}
.describ-tit{height:50px; line-height:50px; border-bottom:1px solid #d7d7d7; font-size:15px; padding:0 10px;}
.describ-tit a{ display:inline-block; float:right; width:16px; height:16px; background:url(http://img.zdfei.com/static/image/temp/20160104/ea130623bab7fd96336e9e9f3b0ba4f7.png) no-repeat; background-size:16px; margin:17px 0;}
.describ-details{padding:10px; color:#222; height:80%; line-height:1.6em; overflow-y:auto;}
.wrapper{position:relative;z-index:1;height:120px;width:100%;overflow:hidden;-ms-touch-action:none}
.scroller{position:absolute;z-index:1;-webkit-tap-highlight-color:transparent;width:580px;height:120px;-webkit-transform:translateZ(0);-moz-transform:translateZ(0);-ms-transform:translateZ(0);-o-transform:translateZ(0);transform:translateZ(0);-webkit-touch-callout:none;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;-webkit-text-size-adjust:none;-moz-text-size-adjust:none;-ms-text-size-adjust:none;-o-text-size-adjust:none;text-size-adjust:none}
.scroller ul{list-style:none;width:100%;padding:0;margin:0; background:#fff;}
.scroller li{width:95px;height:120px;background:#fff;float:left;overflow:hidden; position:relative;}
.recomm-pro-plus li a,.recomm-pro-plus li a img{width:80px; height:80px; display:block;}
.recomm-pro-plus li span{ width:15px; height:80px; line-height:80px; text-align:center; display:inline-block; position:absolute; right:0; top:0; font-size:18px;}
.recomm-pro-plus li:first-child{ margin-left:10px;}
.recomm-pro-plus li:last-child span{ display:none;}
.recomm-pro-tit{ padding:0 5px; font-size:12px; color:#222; line-height:1.4em; height:32px; overflow:hidden;}
.health-nav-box{position:fixed ;z-index:99;}
.health-nav-box{position:relative;z-index:99;height:40px;width:100%;background:#f3f5f7;overflow:hidden;-ms-touch-action:none}
.health-nav-box-list{position:absolute;z-index:1;-webkit-tap-highlight-color:transparent;width:450px;height:40px;-webkit-transform:translateZ(0);-moz-transform:translateZ(0);-ms-transform:translateZ(0);-o-transform:translateZ(0);transform:translateZ(0);-webkit-touch-callout:none;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;-webkit-text-size-adjust:none;-moz-text-size-adjust:none;-ms-text-size-adjust:none;-o-text-size-adjust:none;text-size-adjust:none}
.health-nav-box-list ul{list-style:none;width:100%;padding:0;margin:0}
.health-nav-box-list li{width:90px;height:40px;float:left;line-height:40px;background-color:#f3f5f7;font-size:14px;overflow:hidden;text-align:center; color:#fff;}
.health-nav-box-list li a{color:#999999; pointer-events: none; display: inline-block; height: 38px; line-height: 38px;}
.health-nav-box-list li.curr a{ color: #0fa9a3; border-bottom:2px solid #0fa9a3;}
.recomm-product{background: #FFF;}
.original-cost{font-size:12px; color:#999; text-decoration: line-through;}
.new-car-red{display:block; position: absolute; bottom:5px; right:15px; width:20px; height:20px; background: url("http://img.zdfei.com/static/image/temp/20151230/1e5447ed907c66e3505cb40fa65d4278.png") no-repeat center center; background-size:18px;}
.center-line{  border-top: 1px solid #dadada;  margin-top: 20px;  height: 10px;  position: relative;}
.center-line p{font-size: 12px;  width: 140px;  text-align: center;  display: inline-block; color: #383838;  height: 21px;  line-height: 21px;  position: absolute;  left: 50%;  top: -10px;  margin-left: -70px;  background: #f0f0f0;}
.product-list-all{padding: 0 0 10px;}
.product-list-box{padding-left:10px;}
.product-list-box li{width:50%; float: left; position: relative;}
.new-top-search{  width: 30px;  height: 30px;  line-height: 30px;  text-align: center;  position: absolute;  right: 7px;  top: 7px;  font-size: 28px;  color: #999;  z-index: 5;  font-weight: normal;}
.new-discount{width:40px; height:18px; line-height:18px; color:#fff; background:#ff3d3d; text-align:center; position:absolute; right:0; top:10px;}
.new-list-pro{ background: #fff; margin: 10px 10px 0 0; position: relative; padding: 15px 15px 10px; border-radius:5px; -webkit-border-radius:5px;}
.new-list-pro img{width:100%; display:block;}
.new-list-text{margin: 0 10px 0 0; position: relative; padding: 15px 15px 10px;}
.new-list-pro-title{color:#383838; line-height:16px; height:16px; overflow:hidden; margin-top:5px; padding:0 3px; text-overflow: ellipsis; white-space: nowrap;}
.new-list-pro-price{color:#ff3d3d; font-size:18px; margin-top:5px; padding:0 3px;}
.new-list-pro-original{color:#a1a1a1; margin-top:0; text-decoration:line-through; padding:0 3px;}
.new-list-pro-into-cart{width:30px; height:30px; display:inline-block; background:url("http://img.zdfei.com/static/image/temp/20160104/433f91b420e7eb1daa40cc7d1a843f26.png") no-repeat center center; background-size:18px; position:absolute; right:10px; bottom:0; z-index:9;}
.mask-ui {position:fixed;z-index:99;background: rgba(22,22,22,.9);top:0;left:0;width:100%;height:100%;display:none;}
.share-img{position:fixed;z-index:999;top:0;left:0;width:100%;display:none;}
.loading-img {display:none;position:fixed;height:auto;z-index:100000;top:45%;left:45%;background:gray;padding:10px;border-radius:10px;opacity:.6;filter:alpha(opacity=60);}
.loading-img img {widht:30px;height:30px;}
</style>
</head>
<body>

<header class="header">
    <a id="backListPage" class="iconfont top-left-btn">B</a>
    <h2 class="header-title">${obj.categoryName}</h2>
    <a href="javascript:;" class="iconfont top-right-btn"><span id="shareBtnByhealthy">分享</span></a>
</header>

<div class="care-main pt45">
    <input type="hidden" id="csum" value="${obj.csum}"/>
    <input type="hidden" id="hid" value="${obj.id?c}"/>
    <input type="hidden"  id="p_curPage"  value="0" >
	<input type="hidden"  id="categoryid" value="${obj.goodsCategoryId?c}"/>
	<input type="hidden" id="shareImageUrl" value="${ui1}${obj.shareImageUrl}"/>
	<input type="hidden" id="shareWords" value="${obj.shareWords}"/>
	<input type="hidden" id="bannerImgUrl" value="${ui1}${obj.bannerImgUrl}"/>
    
	<input type="hidden" value="${obj.lisths?size}" id="priceLooperNumber"/>
<#list obj.lisths?if_exists as lisths>
 	<#if lisths_index == 0>
	<div class="care-part clearfix">
	
	<!--Banner图-开始-->
	<#if obj.bannerImgUrl?default('')!=''>
    	<div class="care-adv-tit"><img src="${ui1}${obj.bannerImgUrl}"></div>
	</#if>
	<!--Banner图-结束-->
	
	<!--滑动菜单-开始-->
	<nav class="health-nav-box wrapper3">
            <div class="health-nav-box-list">
                <ul id="newscroller">
                </ul>
            </div>
    </nav>
	<!--滑动菜单-结束-->
	
	<!--症状介绍-开始-->
    <div class="symptom clearfix">
        <a href="javascript:void(0)" class="out-details" onclick="zhankai(1,0);">
        <input type="hidden" id="exp0" value="${obj.explain}"/>
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
	<!--症状介绍-结束-->
        
    <!--推荐套餐-START-->
    <input type="hidden" id="bigSize" value="${obj.listhc?size}"/>
    <#list obj.listhc?if_exists as listhc>
    <article class="package-tj">
    	
    	<h2 class="package-tj-title clearfix">
        	<span>药师推荐</span>${listhc.cname}
        </h2>
        <div class="wrapper wrapper${listhc_index}">
                <div class="scroller" style="width:${listhc.gsum*100}px">
                    <ul class="recomm-pro-plus clearfix">
                	<input type="hidden" id="smallSize_${listhc_index}" value="${listhc.listc?size}"/>
                    <#list listhc.listc?if_exists as listc>
                        <li>
                            <a class="recomm-a" onclick="product(${listc.gid?c},&quot;${listc.sname}&quot;);">
                                <img src="${ui1}${listc.gImgUrl}"/>
                                <p class="recomm-pro-tit">${listc.sname}</p>
                                <input type="hidden" id="wap_price_${listhc_index}_${listc_index}" value="${listc.wapPrice?c}" />
                                <input type="hidden" id="old_price_${listhc_index}_${listc_index}" value="${listc.price?c}" />
                                <input type="hidden" id="goods_id_${listhc_index}_${listc_index}" value="${listc.gid?c}" />
                                <input type="hidden" id="goods_name_${listhc_index}_${listc_index}" value="${listc.sname}" />
                            </a>
                            <span>+</span>
                        </li>
                    </#list>
                    </ul>
                </div>
			</div>
			
			<div class="symptom sy-comment clearfix">
            <a class="out-details" onclick="zhankai(2,${listhc_index});">
            <input type="hidden" id="ccexp${listhc_index}" value="${listhc.commentContent}"/>
                <span class="sy-comment-tag">药师点评</span>
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
			
        <div class="price-box clearfix">
        	<div class="total-price">
            	<span>共节省</span><b id="totalSave_${listhc_index}">¥0</b><span>组合套餐</span><b id="combinePrce_${listhc_index}">¥0</b>
                <p>原价<i id="oldPrice_${listhc_index}">¥0</i></p>
            </div>
            <a href="javascript:;" onclick="healthysAddCart(${listhc_index})" class="sy-buy">立即购买</a>
        </div>
    </article>
    </#list>
    <!--推荐套餐-END-->
	</#if>
	

    <div class="recomm-product">
	<#if lisths_index == 0>
    	<h2 class="package-tj-title clearfix">
            <span>单品用药</span>主用药
        </h2>
    	<#if obj.bannerImgUrl?default('')!=''>
	    	<div class="care-adv-tit"><img src="${ui1}${obj.bannerImgUrl}"></div>
		</#if>
	<#else>
		<div class="symptom clearfix">
            <a class="out-details" onclick="zhankai(1,${lisths_index});">
            <input type="hidden" id="exp${lisths_index}" value="${lisths.sexplain?default('')}">
                <span class="symptom-tag">${lisths.sname?default('')}</span>
                <div class="symptom-text clearfix">
                    <span class="symptom-corner fr"></span>
		           	<p>${lisths.sexplain?default('')}</p>
                </div>
            </a>
        </div>
	</#if>
    
	<#if (lisths.gsum==1)>
        <div class="recomm-one clearfix">
            <div class="recomm-one-img"><a onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
            <div class="recomm-one-info">
                <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
            	<p class="recomm-two-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}" id="goods_price_one_in_${lisths_index}">¥${lisths.lists[0].wapPrice}</p>
            	<input type="hidden" value="${lisths.lists[0].gid?c}" id="goods_id_one_in_${lisths_index}"/>
                <p class="original-cost">¥${lisths.lists[0].price}</p>
                <a class="new-car-red" onclick="healthyAddCart(1,${lisths.lists[0].gid?c})" style="margin-right:15px;"></a>
            </div>
        </div>
	</#if>
        	
	<#if (lisths.gsum==2)>
		<input type="hidden" value="${lisths.gsum}" id="obj_two_${lisths_index}"/>
        <div class="recomm-two clearfix">
        <div class="c-line"></div>
        	<ul class="clearfix">
    		<input type="hidden" value="${lisths.lists?size}" id="obj_two_size_${lisths_index}"/>
        	<#list lisths.lists?if_exists as objs>
            	<li>
                	<a  class="recomm-a" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);"><img src="${ui1}${objs.gImgUrl}"></a>
                    <div class="recomm-two-info">
                        <p class="recomm-two-title" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);">${objs.gname}<span>${objs.mainTitle}</span></p>
                        <p class="recomm-two-price bszjg${lisths.sid?c}_${objs.gid?c}" id="goods_price_two_in_${lisths_index}_${objs_index}">¥${objs.wapPrice}</p>
                		<input type="hidden" value="${objs.gid?c}" id="goods_id_two_in_${lisths_index}_${objs_index}"/>
                		<p class="original-cost">¥${objs.price}</p>
                		<a class="new-car-red" onclick="healthyAddCart(1,${objs.gid?c})" style="margin-right:15px;"></a>
                    </div>
                </li>
             </#list>
             </ul>
         </div>
	 </#if>
        	
	<#if (lisths.gsum==3)>
		<input type="hidden" value="${lisths.gsum}" id="obj_three_${lisths_index}"/>
		<div class="recomm-one clearfix">
            <div class="recomm-one-img"><a onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
            <div class="recomm-one-info">
                <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
                <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}" id="goods_price_three_in_1_${lisths_index}">¥${lisths.lists[0].wapPrice}</p>
                <input type="hidden" value="${lisths.lists[0].gid?c}" id="goods_id_three_in_1_${lisths_index}"/>
                <p class="original-cost">¥${lisths.lists[0].price}</p>
                <a class="new-car-red" onclick="healthyAddCart(1,${lisths.lists[0].gid?c})" style="margin-right:15px;"></a>
            </div>
        </div>
    	<div class="c-line" style="height:0px;"></div>
        <div class="recomm-two clearfix">
        	<ul class="clearfix">
            	<li>
                	<a class="recomm-a" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);"><img src="${ui1}${lisths.lists[1].gImgUrl}"></a>
                    <div class="recomm-two-info">
                        <p class="recomm-one-title" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);">${lisths.lists[1].gname}<span>${lisths.lists[1].mainTitle}</span></p>
                    	<p class="recomm-two-price bszjg${lisths.sid?c}_${lisths.lists[1].gid?c}" id="goods_price_three_in_2_${lisths_index}">¥${lisths.lists[1].wapPrice}</p>
                       	<input type="hidden" value="${lisths.lists[1].gid?c}" id="goods_id_three_in_2_${lisths_index}"/>
                        <p class="original-cost">¥${lisths.lists[1].price}</p>
                        <a class="new-car-red" onclick="healthyAddCart(1,${lisths.lists[1].gid?c})" style="margin-top:-20px;"></a>
                    </div>
                </li>
                <li>
                	<a class="recomm-a" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);"><img src="${ui1}${lisths.lists[2].gImgUrl}"></a>
                    <div class="recomm-two-info">
                        <p class="recomm-one-title" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);">${lisths.lists[2].gname}<span>${lisths.lists[2].mainTitle}</span></p>
                   		<p class="recomm-two-price bszjg${lisths.sid?c}_${lisths.lists[2].gid?c}" id="goods_price_three_in_3_${lisths_index}">¥${lisths.lists[2].wapPrice}</p>
                   		<input type="hidden" value="${lisths.lists[2].gid?c}" id="goods_id_three_in_3_${lisths_index}"/>
                        <p class="original-cost">¥${lisths.lists[2].price}</p>
                        <a class="new-car-red" onclick="healthyAddCart(1,${lisths.lists[2].gid?c})" style="margin-top:-20px;"></a>
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
	        <p>${obj.reminder}</p>
	    </div>
	</#if>

	<div class="center-line"><p>继续拖动,查看更多商品</p></div>
	<div class="product-list-all">
	    <ul class="product-list-box clearfix" id="goodsList">
	    </ul>
	</div>
	    
</div>

<div class="b-cover">
	<div class="describ">
    	<div class="describ-tit"><a onclick="closeDocument()" class="describ-x"></a>详细描述</div>
        <div class="describ-details"></div>
    </div>
</div>

<script type="text/javascript" src="/web/js/jquery.min.js"></script>
<div style="position:relative;top:45px;">
<!--#include virtual="/static/inc/wap/footer.html" -->
</div>
<div class="mask-ui"></div>
<img class="share-img" src="/web/images/share.png"/>
<div class="loading-img" id="loadimg"><img src="/web/images/loading_img.jpg"/></div>
</body>
</html>

<script type="text/javascript" src="/web/js/iscroll.js"></script>
<script type="text/javascript" src="/web/js/alert.main.js"></script>
<script type="text/javascript" src="/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="/web/js/common.new.js"></script>
<script type="text/javascript" src="/web/js/goodList.js"></script>
<script type="text/javascript" src="/web/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
//导航初始化
var myScroll;
function loaded() 
{
	var sum = $("#csum").val();
	for(var i=0;i<sum;i++)
	{
		myScroll = new IScroll('.wrapper'+i, { eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
	}
	$("#p_curPage").val('0');
	appendGoodList();
}
//微信初始化认证
function initWechat()
{	
    var surl = window.location.href;
	$.ajax({
	    url: "/wx/wechat!getSignInfoFromRemote.action?signUrl="+encodeURIComponent(surl),
		type: "GET",
		cache: false,
		async: false,
		success: function(data){
			eval("var datas = " + data+";");
			wx.config({
			    debug: false, 
			    appId: datas.appId, 
			    timestamp: datas.timestamp, 
			    nonceStr: datas.nonceStr, 
			    signature: datas.signature,
			    jsApiList: datas.jsApiList
			});
	     }
     });
}
//初始化分享参数
function initParam()
{
	//分享图片
	var shareImageUrl = $.trim($("#shareImageUrl").val());
	//分享语
	var shareWords = $.trim($("#shareWords").val());
	//Banner图
	var bannerImgUrl = $.trim($("#bannerImgUrl").val());
	var imgUrl;
	if(shareImageUrl == null || shareImageUrl == '' || shareImageUrl == undefined)
	{
		imgUrl = bannerImgUrl;
	}
	else
    {
    	imgUrl = shareImageUrl;
    }
	var title = document.title;
	var desc = shareWords;
	var url = window.location.href;
	wx.ready(function()
	{
		//分享给朋友
		wx.onMenuShareAppMessage({
		    title: title, 
		    desc: desc,
		    link: url,
		    imgUrl: imgUrl,
		    type: '',
		    dataUrl: '',
		    success: function (){ 
		    },
		    cancel: function (){ 
		    }
		});
		//分享到朋友圈
		wx.onMenuShareTimeline({
		    title: title,
		    link: url,
		    imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
		//分享到QQ
		wx.onMenuShareQQ({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
		    success: function () { 
		    },
		    cancel: function () { 
		    }
		});
	});	
}
//菜单点击事件
function toHealty(id)
{
	window.location.href = "/static/healthypla/new/"+id+".html";
}
//关闭阅读层
function closeDocument()
{
	$(".b-cover").hide();
}

$(function()
{
 	//回健康方案列表页
 	$("#backListPage").click(function()
 	{
 		window.location.href = "/web/health_plan/health_plan.html";
 	});
 	
 	try
 	{
	 	//组合套装计算价格
	 	getDynamicPrice();
	 	
	 	//动态获取关联商品的价格
	 	getPriceInGoods();
	 	
 		//获取头部导航分类名称
		getNavication();
		
		//导航初始化
		loaded();
		
		//微信分享初始化
		initWechat();
 	}
 	catch(e)
 	{
 		console.log(e);
 	}

	//分享按钮点击事件
	$("#shareBtnByhealthy").click(function()
	{
		$(".mask-ui").show();
		$(".share-img").show();
		window.setTimeout(function()
		{
			$(".mask-ui").hide();
			$(".share-img").hide();
		},1000);
		initParam();
	});
});

//组合套装计算价格
function combinationPrice()
{
	var bigSize = $.trim($("#bigSize").val());
	for(var i=0; i<bigSize; i++)
	{
		var totalWapPrice = 0;
		var totalOldPrice = 0;
		var savePrice = 0;
		var smallSize = $.trim($("#smallSize_"+i).val());
		for(var j=0; j<smallSize; j++)
		{
			//WAP站价格
			var wap_price = parseFloat($.trim($("#wap_price_"+i+"_"+j).val()));
			//原价格
			var old_price = parseFloat($.trim($("#old_price_"+i+"_"+j).val()));
			totalWapPrice += wap_price;
			totalOldPrice += old_price;
		}
		savePrice = parseFloat(totalOldPrice - totalWapPrice);
		savePrice = savePrice.toFixed(2);
		//共节省
		$("#totalSave_"+i).html("¥"+savePrice);
		//组合价
		$("#combinePrce_"+i).html("¥"+totalWapPrice.toFixed(2));
		//原价
		$("#oldPrice_"+i).html("¥"+totalOldPrice.toFixed(2));
	}
}

//动态获取商品价格
function getDynamicPrice()
{
	var bigSize = $.trim($("#bigSize").val());
	var ps = "";
	for(var i=0; i<bigSize; i++)
	{
		var smallSize = $.trim($("#smallSize_"+i).val());
		for(var j=0; j<smallSize; j++)
		{
			var goods_id = $.trim($("#goods_id_"+i+"_"+j).val());
	  		var hj = "wap_price_"+i+"_"+j;
	  		var p1 = "{'goodsId':"+goods_id+",'nowPrice':'00.00','platform':'2','htmlObjId':'"+hj+"'}"; 
	  		ps += p1 + ",";
		}
	}
	ps = ps.substring(0,ps.length-1);
  	var p_start = "[" ;
	var p_end = "]" ;
	var params = p_start + ps + p_end ;
    $.ajax
    ({
        type: "post",
        url: "/index!countpricefrompc.action",
        data: {"params":params},
        async: false,
        success: function(data)
        {
            if(data != null && data != '' && data != undefined)
            {
                for(var i=0; i<data.length; i++)
                {
                    var nowPrice = data[i].nowPrice;
                    //HTML对象ID
                    var htmlObjId = data[i].htmlObjId;
                    $("#"+htmlObjId).val(nowPrice);
                }
                combinationPrice();
            }
            else
            {
                console.log("Datas null !");
                combinationPrice();
            }
        },
        error: function()
        {
            console.log("Server error !");
            combinationPrice();
        }
    });
}

//动态获取关联商品的价格
function getPriceInGoods()
{
	var pln = $.trim($("#priceLooperNumber").val());
	var ps = "";
	for(var i=0;i<pln;i++)
	{
		//关联一个商品
		var oneObj = document.getElementById("goods_price_one_in_"+i);
		if(oneObj != null)
		{
			var hj = "goods_price_one_in_"+i;
			var goods_id = $.trim($("#goods_id_one_in_"+i).val());
			var p1 = "{'goodsId':"+goods_id+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj+"'}";
			ps += p1 + ",";
		}
		//关联两个商品
		var twoObj = document.getElementById("obj_two_"+i);
		if(twoObj != null)
		{
			var twoSize = parseInt($.trim($("#obj_two_size_"+i).val()));
			for(var j=0; j<twoSize; j++)
			{
				var hj = "goods_price_two_in_"+i+"_"+j;
				var goods_id = $.trim($("#goods_id_two_in_"+i+"_"+j).val());
				var p2 = "{'goodsId':"+goods_id+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj+"'}";
				ps += p2 + ",";
			}
		}
		//关联三个商品
		var threeObj = document.getElementById("obj_three_"+i);
		if(threeObj != null)
	    {
	    	var hj1 = "goods_price_three_in_1_"+i;
			var goods_id_1 = $.trim($("#goods_id_three_in_1_"+i).val());
			var p3_1 = "{'goodsId':"+goods_id_1+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj1+"'}";
			
	    	var hj2 = "goods_price_three_in_2_"+i;
			var goods_id_2 = $.trim($("#goods_id_three_in_2_"+i).val());
			var p3_2 = "{'goodsId':"+goods_id_2+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj2+"'}";
			
	    	var hj3 = "goods_price_three_in_3_"+i;
			var goods_id_3 = $.trim($("#goods_id_three_in_3_"+i).val());
			var p3_3 = "{'goodsId':"+goods_id_3+",'nowPrice':'0','platform':'2','htmlObjId':'"+hj3+"'}";
			
			var $p3 = p3_1 + ","+p3_2+","+p3_3;
			ps += $p3 + ",";
	    }
	}
	ps = ps.substring(0,ps.length-1);
	var p_start = "[" ;
	var p_end = "]" ;
	var params = p_start + ps + p_end ;
    $.ajax
    ({
        type: "post",
        url: "/index!countpricefrompc.action",
        data: {"params":params},
        async: false,
        success: function(data)
        {
            if(data != null && data != '' && data != undefined)
            {
                for(var i=0; i<data.length; i++)
                {
                    var nowPrice = data[i].nowPrice;
                    var htmlObjId = data[i].htmlObjId;
                    $("#"+htmlObjId).html("¥"+nowPrice);
                }
            }
            else
            {
                console.log("Datas null !");
            }
        },
        error: function()
        {
            console.log("Server error !");
        }
    });
	
}

//获取头部导航分类名称
function getNavication()
{
	var hid = $.trim($("#hid").val());
	var url = "/healthyplan/healthyplan!getPlanCategorys.action?hid="+hid;
	$.post(url,null,function(data)
	{
		var liobj = "";
		for(var i=0; i<data.length; i++)
		{
			var cname = data[i].name;
			var id = data[i].id;
			var c = "";
			if(id == hid)
			{
				c = "curr";
			}
			liobj += "<li class='"+c+"' onclick='toHealty("+id+")'><a>"+cname+"</a></li>";
		}
		$(".health-nav-box-list ul").append(liobj);
		//动态控制滚动条长度
		var lg = document.getElementById("newscroller").getElementsByTagName("li").length;
		$(".health-nav-box-list").css("width",lg*90+"px");
		var isoll = new IScroll('.wrapper3', { eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
		var clientWidth = $(window).width();
	    var showLiNum = Math.floor(clientWidth / 90);
	    var lg = $("#newscroller li").length-1;
	    var currIndex = $("#newscroller li.curr").index();
	    if(lg == currIndex)
	    {
		    if (currIndex > (showLiNum - 1)) 
		    {
		    	var u = navigator.userAgent, app = navigator.appVersion;
		    	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
		    	if(isiOS)
		    	{
		    		isoll.scrollTo((-(currIndex - showLiNum + 1) * 90)+45, 0);
		    	}
		    	else
		    	{
			        isoll.scrollTo(-(currIndex - showLiNum + 1) * 90, 0);
		    	}
		    }
	    }
	    else
	    {
		    if (currIndex > (showLiNum - 1)) 
		    {
		        isoll.scrollTo(-(currIndex - showLiNum + 1) * 90, 0);
		    }
	    }
	    //为导航重新排序
		reSortNavicator();
	});
}

//商品详情
function product(id,name)
{
	window.location.href="/m/"+id+".html"
}

//展开详情
function zhankai(t,i)
{
	var v = "";
	if(1==t)
	{
		v=$("#exp"+i+"").val();
	}
	else
	{
		v=$("#ccexp"+i+"").val();
	}
	$('.describ-details').text(v);
	$('.b-cover').show();
	$('.describ').animate({top:"30%"});
}

//组合套装加入购物车
function healthysAddCart(i)
{
	//显示加载图标
	$(".mask-ui").show();
	$("#loadimg").show();
	
	window.setTimeout(function()
	{
		var smallSize = $.trim($("#smallSize_"+i).val());
		var message = "";
		for(var j=0; j<smallSize; j++)
		{
			//商品ID
			var goodsId = $.trim($("#goods_id_"+i+"_"+j).val());
			//商品名称
			var goodsName = $.trim($("#goods_name_"+i+"_"+j).val());
			var rs = add_cart(goodsId,1);
			message += "【"+goodsName+"】"+ getMessage(rs) + "\n";
		}
		//更新购物车
		getCartSum(1);
		//隐藏加载图标
		$(".mask-ui").fadeOut();
		$("#loadimg").fadeOut();
		window.setTimeout(function()
		{
			alert(message);
		},400);
	},1000);
}

//单品加入购物车
function healthyAddCart(num, gid)
{
	var rs = add_cart(gid,num);
    if(rs>0)
    {
		$alert("success","已成功加入购物车！");
		getCartSum(1);
		return false;
	}
	else if(rs==-2 || rs==-1)
	{
		$alert("warn","库存不足！");
		return false;
	}
	else if(rs==-3)
	{
		$alert("warn","该商品正促销中，超出了购买数量！");
		return false;
	}
	else if(rs==-100)
	{
		$alert("warn","该商品为处方药，请咨询购买！");
		return false;
	}
	else if(rs==-200)
	{
		$alert("warn","该商品为下架商品！");
		return false;
	}
	else if(rs==-300)
	{
		$alert("warn","您已经购买过该促销商品了！");
		return false;
	}
	else if(rs==0)
	{
		$alert("warn","操作失败！");
		return false;
    }
}

//购物车加入成功后信息返馈
function getMessage(rs)
{
	if(rs>0)
    {
		return "已成功加入购物车！";
	}
	else if(rs==-2 || rs==-1)
	{
		return "库存不足！";
	}
	else if(rs==-3)
	{
		return "正促销中，超出了购买数量！";
	}
	else if(rs==-100)
	{
		return "为处方药，请咨询购买！";
	}
	else if(rs==-200)
	{
		return "为下架商品！";
	}
	else if(rs==-300)
	{
		return "您已经购买过该促销商品了！";
	}
	else if(rs==0)
	{
		return "操作失败！";
    }
}

//为导航重新排序
function reSortNavicator()
{
	var o = $("#newscroller li").eq(0);
	var n = null;
	$("#newscroller li").each(function(i)
	{
		var m = $(this);
		if(m.hasClass("curr"))
		{
			n = m;
		}
	});
	n.insertBefore(o);
}

</script>

