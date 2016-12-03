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
<!--缩放比例-->
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
<#assign wap = "http://testwap.111yao.com/"/>
<#assign ui1 = "http://img.zdfei.com/"/>
<#assign app = "http://testrouter.111yao.com/"/>
<script type="text/javascript" src="${wap}/web/js/jquery.min.js"></script>
<script type="text/javascript" src="${wap}/web/js/iscroll.js"></script>
<script type="text/javascript" src="${wap}/web/js/wap-cart.js"></script>
<script type="text/javascript" src="http://m.111yao.com//web/js/alert.main.js"></script>
<script type="text/javascript">
var myScroll;
function loaded () {
	var sum = $("#csum").val();
	for(var i=0;i<sum;i++){
		myScroll = new IScroll('.wrapper'+i, { eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
	}
}
</script>
<link href="${wap}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${wap}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
</head>
<body onload="loaded()">
<div class="care-main">
    <input type="hidden" id="csum" value="${obj.csum}"/>
    <input type="hidden" id="hid" value="${obj.id?c}"/>
<#list obj.lisths?if_exists as lisths>
 	<#if lisths_index == 0>
	<div class="care-part clearfix">
	<#if obj.bannerImgUrl?default('')!=''>
    	<div class="care-adv-tit"><img src="${ui1}${obj.bannerImgUrl}"></div>
	<#else>
	</#if>
	<#if obj.explain?default('')!=''>
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
	<#else>
	</#if>
        <div class="recomm-product">
        	<#if (lisths.gsum==1)>
	            <div class="recomm-one clearfix">
	                <div class="recomm-one-img"><a href="javascript:;" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
	                <div class="recomm-one-info">
	                    <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
	                    <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}">￥${lisths.lists[0].price}</p>
	                    <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[0].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[0].gid?c})">加入购物车</a></p>
	                </div>
	            </div>
	        	<div class="c-line"></div>
        	</#if>
        	<#if (lisths.gsum==2)>
	            <div class="recomm-two clearfix">
	            	<ul class="clearfix">
	            	<#list lisths.lists?if_exists as objs>
	                	<li>
	                    	<a href="javascript:;" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);"><img src="${ui1}${objs.gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-two-title" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);">${objs.gname}<span>${objs.mainTitle}</span></p>
	                            <p class="recomm-two-price bszjg${lisths.sid?c}_${objs.gid?c}">￥${objs.price}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${objs.gid?c}"><a href="javascript:;" onclick="addTao(1,${objs.gid?c})">加入购物车</a></p>
	                        </div>
	                    </li>
                    </#list>
	                </ul>
	            </div>
        	</#if>
        	<#if (lisths.gsum==3)>
        		<div class="recomm-one clearfix">
	                <div class="recomm-one-img"><a href="javascript:;" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
	                <div class="recomm-one-info">
	                    <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
	                    <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}">￥${lisths.lists[0].price}</p>
	                    <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[0].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[0].gid?c})">加入购物车</a></p>
	                </div>
	            </div>
	        	<div class="c-line"></div>
	            <div class="recomm-two clearfix">
	            	<ul class="clearfix">
	                	<li>
	                    	<a href="#"><img src="${ui1}${lisths.lists[1].gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-one-title" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);">${lisths.lists[1].gname}<span>${lisths.lists[1].mainTitle}</span></p>
	                    		<p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[1].gid?c}">￥${lisths.lists[1].price}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[1].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[1].gid?c})">加入购物车</a></p>
	                        </div>
	                    </li>
	                    <li>
	                    	<a href="#"><img src="${ui1}${lisths.lists[2].gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-one-title" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);">${lisths.lists[2].gname}<span>${lisths.lists[2].mainTitle}</span></p>
	                    		<p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[2].gid?c}">￥${lisths.lists[2].price}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[2].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[2].gid?c})">加入购物车</a></p>
	                        </div>
	                    </li>
	                </ul>
	            </div>
        	</#if>
        </div>
    </div>
    <#else>
    
    <div class="care-part clearfix" style="margin-top:10px">
    <#if lisths.sbannerImgUrl?default('')!=''>
    	<div class="care-adv-tit"><img src="${ui1}${lisths.sbannerImgUrl}"></div>
	<#else>
	</#if>
	<#if lisths.sexplain?default('')!=''>
        <div class="symptom clearfix">
            <a href="javascript:void(0)" class="out-details" onclick="zhankai(1,${lisths_index});">
            <input type="hidden" id="exp${lisths_index}" value="${lisths.sexplain}"/>
                <span class="symptom-tag">${lisths.sname}</span>
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
    <#else>
	</#if>
                <div class="recomm-product">
        	<#if (lisths.gsum==1)>
	            <div class="recomm-one clearfix">
	                <div class="recomm-one-img"><a href="javascript:;" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
	                <div class="recomm-one-info">
	                    <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
	                    <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}">￥${lisths.lists[0].price}</p>
	                    <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[0].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[0].gid?c})">加入购物车</a></p>
	                </div>
	            </div>
	        	<div class="c-line"></div>
        	</#if>
        	<#if (lisths.gsum==2)>
	            <div class="recomm-two clearfix">
	            	<ul class="clearfix">
	            	<#list lisths.lists?if_exists as objs>
	                	<li>
	                    	<a href="#" href="javascript:;" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);"><img src="${ui1}${objs.gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-two-title" onclick="product(${objs.gid?c},&quot;${objs.gname}&quot;);">${objs.gname}<span>${objs.mainTitle}</span></p>
	                            <p class="recomm-two-price bszjg${lisths.sid?c}_${objs.gid?c}">￥${objs.price}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${objs.gid?c}"><a href="javascript:;" onclick="addTao(1,${objs.gid?c})">加入购物车</a></p>
	                        </div>
	                    </li>
                    </#list>
	                </ul>
	            </div>
        	</#if>
        	<#if (lisths.gsum==3)>
        		<div class="recomm-one clearfix">
	                <div class="recomm-one-img"><a href="javascript:;" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);"><img src="${ui1}${lisths.lists[0].gImgUrl}"></a></div>
	                <div class="recomm-one-info">
	                    <p class="recomm-one-title" onclick="product(${lisths.lists[0].gid?c},&quot;${lisths.lists[0].gname}&quot;);">${lisths.lists[0].gname}<span>${lisths.lists[0].mainTitle}</span></p>
	                    <p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[0].gid?c}">￥${lisths.lists[0].price}</p>
	                    <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[0].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[0].gid?c})">加入购物车</a></p>
	                </div>
	            </div>
	        	<div class="c-line"></div>
	            <div class="recomm-two clearfix">
	            	<ul class="clearfix">
	                	<li>
	                    	<a href="javascript:;" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);"><img src="${ui1}${lisths.lists[1].gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-one-title" onclick="product(${lisths.lists[1].gid?c},&quot;${lisths.lists[1].gname}&quot;);">${lisths.lists[1].gname}<span>${lisths.lists[1].mainTitle}</span></p>
	                    		<p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[1].gid?c}">￥${lisths.lists[1].price}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[1].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[1].gid?c})">加入购物车</a></p>
	                        </div>
	                    </li>
	                    <li>
	                    	<a href="javascript:;" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);"><img src="${ui1}${lisths.lists[2].gImgUrl}"></a>
	                        <div class="recomm-two-info">
	                            <p class="recomm-one-title" onclick="product(${lisths.lists[2].gid?c},&quot;${lisths.lists[2].gname}&quot;);">${lisths.lists[2].gname}<span>${lisths.lists[2].mainTitle}</span></p>
	                    		<p class="recomm-one-price bszjg${lisths.sid?c}_${lisths.lists[2].gid?c}">￥${lisths.lists[2].price}</p>
	                            <p class="recomm-into-cart gwc${lisths.sid?c}_${lisths.lists[2].gid?c}"><a href="javascript:;" onclick="addTao(1,${lisths.lists[2].gid?c})">加入购物车</a></p>
	                        </div>
	                    </li>
	                </ul>
	            </div>
        	</#if>
        </div>
    </div>
    </#if>
    </#list>
    <#if obj.reminder?default('')!=''>
	    <div class="care-tips clearfix">
	    	<h2>温馨提示：</h2>
	        <p>${obj.reminder}</p>
	    </div>
	<#else>
	</#if>
    
    <!--推荐套餐-->
    <#list obj.listhc?if_exists as listhc>
    <article class="package-tj">
    	<h2 class="package-tj-title clearfix">
        	<span>药师推荐</span>${listhc.cname}
        </h2>
        <div class="symptom sy-comment clearfix">
            <a href="javascript:void(0)" class="out-details" onclick="zhankai(2,${listhc_index});">
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
        <div class="wrapper wrapper${listhc_index}">
                <div class="scroller" style="width:${listhc.gsum*100}px">
                    <ul class="recomm-pro-plus clearfix">
                    
                    <#list listhc.listc?if_exists as listc>
                        <li>
                            <a href="javascript:;" onclick="product(${listc.gid?c},&quot;${listc.sname}&quot;);">
                                <img src="${ui1}${listc.gImgUrl}"/>
                                <p class="recomm-pro-tit">${listc.sname}</p>
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
                	<span>共节省</span><b id="spid${listhc.cid?c}">￥${listhc.savePrice}</b><span>组合套餐</span><b id="ccpid${listhc.cid?c}">￥${listhc.ccPrice}</b>
                    <p>原价<i id="cpid${listhc.cid?c}">￥${listhc.cPrice}</i></p>
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
   <div id="footer"> <!--#include virtual="/static/inc/wap/footer.html" --></div>
</body>
</html>
<style type="text/css">
.care-part{ background:#fff;}
.care-adv-tit img{width:100%; display:block;}
.symptom{height:75px; overflow:hidden; position:relative; border-bottom:1px solid #d7d7d7;}
.symptom a{height:75px; display:block;}
.symptom-tag{width:48px;overflow:hidden;background:#0fa9a3; display:inline-block; font-size:12px; color:#fff; text-align:center; padding:0 6px; border-radius:5px; -webkit-border-radius:5px; position:absolute; top:50%; margin:-15px 0 0 15px;}
.symptom-corner{width:9px; height:15px; display:inline-block; background:url(${wap}/web/images/symptom_corner.png) no-repeat; background-size:9px 15px; float:right; margin:20px 15px 0 15px;}
.symptom-text{ height:54px; padding:11px 0 10px 90px;}
.symptom-text p{ font-size:12px; line-height:18px; height:54px; overflow:hidden;}
.recomm-one{}
.recomm-one-img{float:left; width:50%;}
.recomm-one-img a{ display:block; margin:0 15px;}
.recomm-one-img a img{width:100%; display:block;}
.recomm-one-info{ float:left; width:50%; padding-top:15px;}
.recomm-one-info p{padding:0 15px;}
.recomm-one-title{color:#222; height:38px; overflow:hidden; line-height:1.4em;}
.recomm-one-title span{ display:block; font-size:12px;}
.recomm-one-price{color:#fc4044; font-size:15px;}
.recomm-one .recomm-into-cart a,.recomm-two .recomm-into-cart a{ font-size:15px; color:#fe4310; display:block; height:30px; line-height:30px; border-radius:5px; -webkit-border-radius:5px; border:1px solid #fe4310; margin:5px 0 0 0; text-align:center;}
.c-line{margin:0 0 0 15px; height:1px; background:#d7d7d7;}
.recomm-two{margin-top:1px;}
.recomm-two li{ float:left; width:50%; padding-bottom:15px;}
.recomm-two li a{margin:0 15px; display:block;}
.recomm-two li a img{width:100%; display:block;}
.recomm-two-info{}
.recomm-two-info p{ padding:0 15px;}
.recomm-two-title{color:#222; height:38px; overflow:hidden; line-height:1.4em;}
.recomm-two-title span{ display:block; font-size:12px;}
.recomm-two-price{color:#fc4044; font-size:15px;}
.care-tips{padding:15px 15px 0; color:#3e3e3e; border-top:1px solid #d7d7d7; background:#fff;}
.care-tips h2{ font-size:15px;}
.care-tips p{margin:5px 0 15px; font-size:12px; line-height:1.6em;}
.package-tj{ background:#fff; margin-top:10px;}
.package-tj-title{height:45px; line-height:45px; font-size:12px; color:#222;}
.package-tj-title span{background:#0fa9a3; display:inline-block; font-size:12px; line-height:1.4em; color:#fff; text-align:center; padding:0 6px; border-radius:5px; -webkit-border-radius:5px; margin:15px;}
.sy-comment{background:#efeff4; border-bottom:none;}
.sy-comment-tag{display:inline-block; font-size:12px; text-align:center; padding:0 6px; margin:30px 15px; float:left;}
.price-box{margin:0 15px; border-top:1px solid #d7d7d7; padding:10px 0;}
.total-price{ text-align:right; line-height:16px;}
.total-price span{ margin:0 5px 0 20px; color:#222; font-size:12px;}
.total-price b{ font-size:15px; color:#fc4044;}
.total-price p{color:#999; font-size:10px;}
.total-price p i{ font-style:normal; text-decoration:line-through; margin-left:5px;}
.sy-buy{ width:108px; height:32px; line-height:32px; color:#fff; display:inline-block; float:right; text-align:center; font-size:15px; border-radius:5px; -webkit-border-radius:5px; background:#fe4310; margin-top:5px;}
/**/
.b-cover{ position:fixed; background:rgba(0,0,0,.8); top:0; left:0; right:0; bottom:0; z-index:100; display:none;}
.describ{ position:absolute; height:70%; top:100%; left:0; right:0; background:#efeff4;}
.describ-tit{height:50px; line-height:50px; border-bottom:1px solid #d7d7d7; font-size:15px; padding:0 10px;}
.describ-tit a{ display:inline-block; float:right; width:16px; height:16px; background:url(${wap}/web/images/describ_x.png) no-repeat; background-size:16px; margin:17px 0;}
.describ-details{padding:10px; color:#222; height:80%; line-height:1.6em; overflow-y:auto;}
/*iscroll*/
.wrapper{position:relative;z-index:1;height:120px;width:100%;background:#fff;overflow:hidden;-ms-touch-action:none}
.scroller{position:absolute;z-index:1;-webkit-tap-highlight-color:transparent;width:580px;height:120px;-webkit-transform:translateZ(0);-moz-transform:translateZ(0);-ms-transform:translateZ(0);-o-transform:translateZ(0);transform:translateZ(0);-webkit-touch-callout:none;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;-webkit-text-size-adjust:none;-moz-text-size-adjust:none;-ms-text-size-adjust:none;-o-text-size-adjust:none;text-size-adjust:none}
.scroller ul{list-style:none;width:100%;padding:0;margin:0; background:#fff;}
.scroller li{width:95px;height:120px;background:#fff;float:left;overflow:hidden; position:relative;}
.recomm-pro-plus li a,.recomm-pro-plus li a img{width:80px; height:80px; display:block;}
.recomm-pro-plus li span{ width:15px; height:80px; line-height:80px; text-align:center; display:inline-block; position:absolute; right:0; top:0; font-size:18px;}
.recomm-pro-plus li:first-child{ margin-left:10px;}
.recomm-pro-plus li:last-child span{ display:none;}
.recomm-pro-tit{ padding:0 5px; font-size:12px; color:#222; line-height:1.4em; height:32px; overflow:hidden;}
</style>
<script type="text/javascript">
$(function(){
	$('.describ-x').click(function(){
		$('.b-cover').hide();	
	})
	var type = GetQueryString();
	var url = "";
	if('w'!=type){
		$("#footer").remove();
        url="${app}/sltRouter?method=gethealthyplaninfo-V2";
	}else{
		url="${wap}/healthyplan/healthyplan!getHealthyPlanInfo.action";
	}
	jQuery.ajax({
       url: url,	  
       type: "post",
       data:{"id":$("#hid").val()},
       success: function(data){
       		data = jQuery.parseJSON(data);
       		for(var i=0; i<data.lists.length; i++){
           		if('w'==type){
           			$(".bszjg"+data.lists[i].tid+"_"+data.lists[i].gid).text("￥"+data.lists[i].wap_price);
	           		if(data.lists[i].wap_status!=1||data.lists[i].stock<=0||data.lists[i].type==3||data.lists[i].type==2){
	           			$(".gwc"+data.lists[i].tid+"_"+data.lists[i].gid).remove();
	           		}
           		}else{
           			$(".bszjg"+data.lists[i].tid+"_"+data.lists[i].gid).text("￥"+data.lists[i].app_price);
           			if(data.lists[i].app_status!=1||data.lists[i].stock<=0||data.lists[i].type==3||data.lists[i].type==2){
           				$(".gwc"+data.lists[i].tid+"_"+data.lists[i].gid).remove();
	           		}
           		}
           }   
           for(var i=0; i<data.listc.length; i++){
           			$("#cpid"+data.listc[i].tid).text("￥"+parseFloat(parseInt(data.listc[i].p)/100).toFixed(2));
	           	if('w'==type){
	           		$("#ccpid"+data.listc[i].tid).text(parseFloat(parseInt(data.listc[i].wp)/100).toFixed(2));
			   		$("#spid"+data.listc[i].tid).text(parseFloat((parseInt(data.listc[i].p)-parseInt(data.listc[i].wp))/100).toFixed(2));
           		}else{
           			$("#ccpid"+data.listc[i].tid).text(parseFloat(parseInt(data.listc[i].ap)/100).toFixed(2));
			   		$("#spid"+data.listc[i].tid).text(parseFloat((parseInt(data.listc[i].p)-parseInt(data.listc[i].ap))/100).toFixed(2));;
           		}
           }
       },error:function(){
    	   $alert("error","网络异常",null,null);
       }
   }); 
})

//去产品详情页
function product(id,name)
{
	var type = GetQueryString();
	if('w'!=type)
	{
		document.location = "id*"+id+"*"+name;
	}
	else
	{
		window.location.href="http://m.111yao.com/m/"+id+".html"
	}
}

//展开详细内容
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
</script>