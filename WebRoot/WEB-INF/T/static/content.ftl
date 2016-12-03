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
<link href="http://router.111yao.com/web/app/css/common1.css" rel="stylesheet" type="text/css" />
<link href="http://router.111yao.com/web/app/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://router.111yao.com/web/app/js/jquery.min.js"></script>  
<script type="text/javascript" src="http://router.111yao.com/web/resources/app/js/common.js"></script>
</head>
<body>
<article class="content">
  <article class="product-details">
    <div class="details-img" id="imgdiv" oncontextmenu="return false" ondragstart="return false" onselectstart ="return false" onselect="document.selection.empty()"
 oncopy="document.selection.empty()" onbeforecopy="return false">
        <!--<p><img src="http://img.zdfei.com/static/image/temp/20160202/019ad5734e114eda9931bf86898477ee.jpg" alt="服务公告"/></p>-->
    	${goods.goodsDescribes?default('')}
    </div>
  </article>
</article>
</body>
</html>