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
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <title>${tla.title?default('')}</title>
    <style rel="stylesheet" type="text/css">
        html,body,div,span,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,address,cite,code,del,dfn,em,img,ins,kbd,q,samp,small,strong,sub,sup,var,b,i,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,textarea,input,span,figure,aside,time,figcaption{margin:0;padding:0;border:0;font-size:100%;vertical-align:baseline}
        article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}
        audio,canvas,video{display:inline-block;*display:inline;*zoom:1}
        audio:not([controls]){display:none}
        html,body{height:100%; position:relative}
        html{font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}
        body{width:100%;font-family:"SimHei",Helvetica,Arial,sans-serif;font-size:14px;color:#000;background:#fff; line-height:1.6em;}
        input,textarea{font-family:"SimHei",Helvetica,Arial,sans-serif;color:#000;background: none;}
        input:-moz-placeholder,textarea:-moz-placeholder{color:#c7c7cd}
        input:-ms-input-placeholder,textarea:-ms-input-placeholder{color:#c7c7cd}
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{color:#c7c7cd}
        a{color:#0d0d0d;text-decoration:none;/*-webkit-tap-highlight-color:rgba(0,0,0,0.2);*/-webkit-tap-highlight-color:transparent}
        a:hover{text-decoration:none}
        a:focus{outline:none;blr:expression(this.onFocus=this.blur());}
        a:hover,a:active{outline:0}
        textarea,input{resize:none;outline:0;font-size:100%;-webkit-tap-highlight-color:rgba(255,0,0,0)}
        textarea{resize:none;-webkit-appearance:none}
        ul,ol{list-style:none}
        em{font-style:normal}
        h1,h2,h3,h4,h5,h6,b{font-weight:normal;}
        .clearfix {margin-bottom:10px;position:relative}
        .clearfix:after{visibility:hidden;display:block;font-size:0;content:".";clear:both;height:0;overflow:hidden}
        .fl{float:left}
        .fr{float:right}
        .health-article-share{background:#FFF;font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;}
        .article-share{padding:10px; overflow: hidden; background: #f7f7f7;}
        .share-title{padding:0 23px 0 11px;font-size:18px; font-weight:bold; color:#000; margin-bottom:6px;}
        .subhead{font-size:12px; color:#808080; margin-bottom:21px;padding:0 23px 0 11px;}
        .subhead a{font-size:12px; color:#00a6a0;}
        .main-text{overflow: hidden;}
        .main-text p{font-size:15px; line-height:25px; margin-bottom:20px; padding:0 23px 0 11px;}
        .main-img{padding:0 11px; margin-bottom:20px;}
        .main-img img{width:100%;}
        .recommend-shopping{overflow: hidden;}
        .rcm-header{background: #EEE; height:32px; position: relative;}
        .rcm-title{width:100%; text-align: center; font-size:15px; color:#00a6a0; line-height:32px; font-weight:bold;}
        .rcm-line{display:block; border-top:1px solid #b9b9b9; height:0; position: absolute; top:16px; width:38%;}
        .b-left{left:0;}
        .b-right{right:0;}
        .rcm-list{overflow: hidden;}
        .rcm-list dl{margin-bottom:4px; overflow: hidden;}
        .rcm-img{width:125px; height:125px; display: block; float:left;}
        .rcm-img a,.rcm-img a img{width:125px; height:125px; display: block;}
        .rcm-text{margin-left:138px;border-top:1px solid #e4e4e4;}
        .rcm-list dl:first-child .rcm-text{border-top:none;}
        .shopping-name{font-size:13px; color:#000; font-weight: bold; line-height:18px;height:20px; overflow: hidden;margin-top:5px;}
        .sales-promotion{color:#e60116;font-size:13px; margin-bottom:15px;line-height:18px;height:40px;}
        .shopping-norms{font-size:12px; color:#808080;}
        .shopping-price span{float:left; font-size:13px;color:#e60116;}
        .shopping-price span i{font-style: normal;font-size:18px; }
        .shopping-price a{float:right; display:block; height:20px; line-height:20px; border-radius: 4px; padding:0 6px; margin-right:5px; text-align: center; color:#FFF; font-size:13px; background: #e60116;}
        .health-more{border-top:1px solid #e4e4e4; border-bottom:1px solid #e4e4e4; padding:0 15px 0 12px;}
        .health-more a{display:block;width:100%; height:54px;line-height:54px;}
        .health-more a span{float:left; padding-left:32px; font-size:16px; color:#000; background: url("images/health_article_icon.png") no-repeat center left; background-size: 23px;}
        .health-more a p{float:right; font-size:15px; color:#808080; line-height:54px; background: url("images/health_arrow_right.png") no-repeat center right; background-size: 8px; padding-right:18px;}
        .wx-img{padding:20px 0 35px;}
        .wx-top{overflow:hidden;}
        .wx-top li{width:50%; float:left; list-style:none;}
        .wx-top li img{width:80%; display:block; margin:0 auto;}
        .wx-img p{text-align: center; font-size:13px; margin-top:13px;}
        .price-ui{position: absolute;top: 52%;width: 22%;right: 2%;height: auto;padding-top: 15px;padding-bottom: 5px;text-align: center;font-size: 6vw;color: #d10000;background: #fcffef;font-family: 'Arial';font-weight: bold;letter-spacing: -2px;}
		.get-box{height:45px; padding-left:5px; background: #FFF; position: relative;}
        .get-box label{display: block; height:45px; line-height:45px; background: url("http://img.zdfei.com/static/image/temp/20151229/cdf243262446233c557a91daddac4c5d.png") no-repeat left center; background-size: 18px;  padding-left:25px; font-size:12px;color:#999999;}
        .getClick{display:block; position: absolute; top:7px; right:6px; height:30px; width:75px; line-height:30px; text-align: center; font-size:14px; border:1px solid #e74925; color:#e74925; border-radius:5px;font-size:12px;}
   		.read-wrap{height:30px; padding-left:20px; border-top:1px solid #d7d7d7;}
   		.read-wrap li{float:left; line-height:30px; margin-right:30px; color:#00b8c9;}
   		.read-wrap li span{float:left;}
   		.read-wrap li b{display:inline-block; float:left; margin-top:3px;height:20px; width:20px; background:url("http://img.zdfei.com/static/image/temp/20160107/fb0bd9f2efc969aed2fd601882219228.png") no-repeat center center; background-size: 16px;}
    	.read-wrap li b.click-b{background:url("http://img.zdfei.com/static/image/temp/20160107/fff1569bb7bc35104e7f9c75020c931c.png") no-repeat center center; background-size: 16px;}
    	.readnum {margin-left:3px;}
    </style>
</head>
<body class="health-article-share">
<!--文章开始-->
<article class="article-share" style="background:#fff;">
	${acontent?default('')}
</article>
<input type="hidden" value="${tla.shareTitle?default('')}" id="shareTitle"/>
<!--文章结束-->

<div class="read-wrap">
	<ul>
		<li><span>阅读</span><span class="readnum" id="readnum">1000</span></li>
		<li><span>分享</span><span class="readnum" id="sharenum">1000</span></li>
		<li><b onclick="dianZan('${tla.id?default(0)}')" id="dianZan"></b><span class="showZan">100</span></li>
	</ul>
</div>


<!-- 新用户注册图片 -->
<div>
  <a style="display:block;">
    <img src="http://img.zdfei.com/static/image/temp/20151125/945a8d3a2088e1fcf685f41a77864254.jpg" style="width:100%;" onclick="toHome()" />
  </a>
</div>

<!-- 领券  -->
<div class="get-box">
   <label>领取优惠券，享更多优惠！</label>
   <a href="/coupon/yjcoupon!getCoupon.action" class="getClick">领取</a>
</div>

<!--值得推荐开始-->
<article class="recommend-shopping">
    <div class="rcm-list" id="tuijianContent">
    	<#list datas?if_exists as resc>
        <dl class="clearfix" id="goods_${resc_index+1}">
            <dt class="rcm-img clearfix"><a><img src="http://img.zdfei.com/${resc.imgurl?default('')}" alt="" height="125" width="125"/></a></dt>
            <dd class="rcm-text">
                <p class="shopping-name">${resc.shortname?default('')}</p>
                <p class="sales-promotion">${resc.maintitle?default('')}</p>
                <p class="shopping-norms">商品规格：${resc.spec?default('')}</p>
                <p class="shopping-price"><span>¥<i>${resc.wap_price?default(0)}</i></span><a>查看详情</a></p>
            </dd>
            <input type="hidden" value="${resc.goodsid?c}" id="goodsId_${resc_index+1}"/>
        </dl>
        </#list>
    </div>
</article>

<!-- 药师到家 -->
<div>
  <a style="display:block;">
    <img src="http://img.zdfei.com/static/image/temp/20151113/7eb9e42a0e527c8d3c98ae4aa55926a0.jpg" style="width:100%;" onclick="toHome()" />
  </a>
</div>

<!--微信开始-->
<div class="wx-img">
    <ul class="wx-top clearfix">
		<li><img src="/web/images/300.jpg" id="wx-img"/></li>
        <li><img src="http://img.zdfei.com/static/image/temp/20151119/2497b1cf6f525885c8fde3adbcc5b531.jpg" alt=""/></li>
    </ul>
    <p>更多健康知识，更多用药常识，长按进行关注</p>
</div>
<!--微信结束-->
</body>
<script type="text/javascript" src="/web/js/jquery.min.js"></script>
<script type="text/javascript" src="/web/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="/web/js/alert.main.js"></script>
<script type="text/javascript" src="/web/js/cookieUtil.js"></script>
<script>
var baseurl = "http://img.zdfei.com";
$(function()
{
	//文章点击数加1
	try{clickNumCount('${tla.id?default(0)}');}catch(e){console.log(e)}
	//文章点击数、点赞数、分享数初始化
	try{paramInit('${tla.id?default(0)}');}catch(e){console.log(e)}
	//微信认证初始化	
	try{initWechat();}catch(e){console.log(e)}	
	//商品点击事件
	try{goodsClickEvent();}catch(e){console.log(e)}
});

	//生成二维码图片
	var code = getParameters().code;
	var id = getParameters().id;
	var imgurl = "http://img.zdfei.com/static/image/haibao/"+id+"_"+code+".jpg";
	document.getElementById("wx-img").src = imgurl;

//商品点击事件
function goodsClickEvent()
{
  	$("#tuijianContent").find("dl.clearfix").each(function(index)
  	{
  		$(this).click(function()
  		{
  			var i = index + 1;
			var goodsId = document.getElementById("goodsId_"+i).value;
		  	var goodsurl = "/m/"+goodsId+".html";
		  	var redirect = "/leader/leader!shareLeader.action?hurl="+encodeURIComponent(goodsurl)+"&code="+code;
		  	window.location.href = redirect;
  		});
  	});
}
  
//跳转首页
function toHome()
{
	var redirect = "/leader/leader!shareLeader.action?hurl="+encodeURIComponent("/")+"&code="+code;
	window.location.href = redirect;
}
  
//初始化微信 
function initWX()
{
	var title = document.title;
	var desc = $.trim($("#shareTitle").val());
	var aimUrl = document.location.href;
	var imgUrl = baseurl+'${tla.shareImageUrl?default('')}';
	wx.ready(function()
	{
		//分享给朋友
		wx.onMenuShareAppMessage(
		{
		    title: title, 
		    desc: desc,
		    link: aimUrl,
		    imgUrl: imgUrl,
		    type: '',
		    dataUrl: '',
		    success:function()
		    { 
		    	try{shareNum('${tla.id?default(0)}');}catch(e){console.log(e)}
		    },
		    cancel:function(){}
		});
		//分享到朋友圈
		wx.onMenuShareTimeline(
		{
		    title: title,
		    link: aimUrl,
		    imgUrl: imgUrl,
		    success:function() 
		    { 
		    	try{shareNum('${tla.id?default(0)}');}catch(e){console.log(e)}
		    },
		    cancel:function(){}
		});
		//分享到QQ
		wx.onMenuShareQQ(
		{
			title: title,
			desc: desc,
			link: aimUrl,
			imgUrl: imgUrl,
		    success:function()
		    { 
		    	try{shareNum('${tla.id?default(0)}');}catch(e){console.log(e)}
		    },
		    cancel:function(){}
		});
	});	
}

//文章点击数统计
function clickNumCount(articleId)
{
	$.ajax
	({
		type: "POST",
		url: "/artic/artic!addLookSum.action",
		async: false,
		data:{"articleId":articleId},
		dataType: "json",
		success: function(data) 
		{  
			$("#readnum").html(data);
	    },  
	    error: function(e) 
	    {  
	      	console.log(e);
	    }  
	 });
}

//文章点赞数统计
function dianZan(articleId)
{
	$.ajax
	({
		type: "POST",
		url: "/artic/artic!ajaxClickLikeLeaderArticle.action",
		async: false,
		data:{"articleId":articleId},
		dataType: "json",
		success: function(data) 
		{  
			$(".showZan").html(data);
	    },  
	    error: function(e) 
	    {  
	      	console.log(e);
	    }  
	 });
}

//文章分享数统计
function shareNum(articleId)
{
	$.ajax
	({
		type: "POST",
		url: "/artic/artic!clickLeaderArticleShareSum.action",
		async: false,
		data:{"articleId":articleId},
		dataType: "json",
		success: function(data) 
		{  
			$("#sharenum").html(data);
	    },  
	    error: function(e) 
	    {  
	      	console.log(e);
	    }  
	 });
}

//文章点击数、点赞数、分享数初始化
function paramInit(articleId)
{
	$.ajax
	({
		type: "POST",
		url: "/artic/artic!ajaxGetLeaderArticleInfo.action",
		async: false,
		data:{"articleId":articleId},
		dataType: "json",
		success: function(data) 
		{  
			var datas = data;
			//阅读数
			$("#readnum").html(datas.look_sum);
			//分享数
			$("#sharenum").html(datas.comment_sum);
			//点赞
			$(".showZan").html(datas.click_like_sum);
	    },  
	    error: function(e) 
	    {  
	      	console.log(e);
	    }  
	 });
}

//微信认证初始化	
function initWechat()
{
	var url = document.location.href;
	$.ajax(
	{
	    url: "/wx/wechat!getSignInfoFromRemote.action?signUrl="+encodeURIComponent(url),
		type: "GET",
		cache: false,
		success: function(data)
		{
			eval("var datas = " + data+";");
			wx.config(
			{
			    debug: false, 
			    appId: datas.appId, 
			    timestamp: datas.timestamp, 
			    nonceStr: datas.nonceStr, 
			    signature: datas.signature,
			    jsApiList: datas.jsApiList
			});
			initWX();
	     }
     });
}

//解析地址栏参数
function getParameters()
{
	var pars = window.location.search;
    if(pars.charAt(0) == '?')
    {
        pars = pars.substring(1,pars.length);
    }
    var arr = pars.split("&");
    var objs = [];
    var eles;
    for(var i=0;i<arr.length;i++)
    {
        eles = arr[i].split("=");
        objs[eles[0]] = eles[1];
    }
    return objs;
}
</script>
</html>


