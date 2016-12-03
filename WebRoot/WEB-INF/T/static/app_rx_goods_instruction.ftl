<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="renderer" content="webkit" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="yes" name="apple-touch-fullscreen" />
<meta content="fullscreen=yes,preventMove=no" name="ML-Config" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>处方药_商品说明书</title>
<link href="http://router.111yao.com/web/app/css/common1.css" rel="stylesheet" type="text/css" />
<link href="http://router.111yao.com/web/app/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://router.111yao.com/web/app/js/jquery.min.js"></script>  
<script type="text/javascript" src="http://router.111yao.com/web/resources/app/js/common.js"></script>
</head>
<body>
<article class="content">
  <article class="product-details">
    <div class="details-img" id="imgdiv" oncontextmenu="return false" ondragstart="return false" onselectstart ="return false" onselect="document.selection.empty()" oncopy="document.selection.empty()" onbeforecopy="return false">
    	<#if goods.instructions?exists>
    		<#if goods.instructions?if_exists?has_content>
    			${goods.instructions?default('')}
    			<#else>
    			<p><img src="http://img.zdfei.com/static/image/temp/20160628/a34c91030e0c635210b9363b4fede585.jpg" alt="普通商品说明书"/></p>
    		</#if>
    	<#else>
    		<p><img src="http://img.zdfei.com/static/image/temp/20160628/a34c91030e0c635210b9363b4fede585.jpg" alt="普通商品说明书"/></p>
    	</#if>
    </div>
  </article>
</article>
</body>
</html>