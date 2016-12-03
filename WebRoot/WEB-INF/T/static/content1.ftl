<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>



<!--允许全屏-->
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
<title>商品详情</title>
<link href="http://router.111yao.com/web/app/css/common.css" rel="stylesheet" type="text/css" />
<link href="http://router.111yao.com/web/app/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://router.111yao.com/web/app/js/jquery.min.js"></script>  
<script type="text/javascript" src="http://router.111yao.com/web/app/js/common.js"></script>
<script type="text/javascript">
function product(id){
   document.location = window.location.href+"?id*"+id;
}
</script>
</head>

<body onLoad="loaded()">
<article class="content" style="display:none">
  <!--效果html开始-->
  <div class="slider">
    <div class="slider-page"><span id="cur-num">1</span>/<span>${listi?size}</span></div>
    	<ul>
    	<#list listi?if_exists as resc>
		    <li class="clone"><a href="javascript:;"><img src="http://img.zdfei.com/${resc.artworkUrl?default('')}"></a></li>
	    </#list>
        </ul>
  </div>
  <script type="text/javascript" src="http://router.111yao.com/web/app/js/yxMobileSlider.js"></script>
  <script>
      $(".slider").yxMobileSlider({width:640,height:640,during:3000})
  </script>  
  <!--效果html结束-->
  <article class="product-box">
    <header class="product-info">
      <h1 class="product-title">${goods.goodsName?default('')}</h1>
      <p class="product-price">
        <i></i><b id="pri">￥${goods.app_price?default('')}</b>
        <span>原价:${goods.price?default('')}</span>
      </p>
      <p class="priduct-spec">
        规格:${goods.spec?default('')}
      </p>
    </header>
  </article>
  <ul class="explain clearfix" id="promotiondividval">
  	<li><span class="iconfont">M</span>30天无理由退换货</li>
    <li><span class="iconfont">M</span>真品假一赔十</li>
    <li><span class="iconfont">M</span>全程冷藏运输</li>
    <li><span class="iconfont">M</span>全国规模化采购</li>
    <li><span class="iconfont">M</span>购物满99元包邮，不满99元收取邮费8元</li>
  </ul>
  <article class="product-comment">
    <h1 class="module-title">商品评论</h1>
    <a href="javascript:product(${goods.id?default('')});" class="more-comment">查看更多评论</a>
  </article>
  <article class="product-details">
    <h1 class="module-title">商品详情</h1>
    <div class="details-img" id="imgdiv">
    	<!--<p><img src="http://img.zdfei.com/static/image/temp/20160202/019ad5734e114eda9931bf86898477ee.jpg" alt="服务公告"/></p>-->
    	${goods.goodsDescribes?default('')}
    </div>
  </article>
</article>
<div class="spinner">
  <div class="bounce1"></div>
  <div class="bounce2"></div>
  <div class="bounce3"></div>
</div>
</body>
</html>
<script>
function loaded(){$(".spinner").hide();$(".content").show();}
$("#imgdiv a").click(function(){
$(this).attr("href","javascript:;");
});
</script>