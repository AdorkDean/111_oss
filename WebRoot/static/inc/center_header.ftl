<#include "/static/inc/header_common.html">
<#assign contextPath = request.contextPath/>
<#assign www = "http://new.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://ui1.111yao.com"/>  
<script>
var jsCtx = "${base}";
var CtxPath = "${contextPath}";
</script>

<div class="logo-search-cart" style="border-bottom:#bc2d0d 1px solid;">
        <div class="w1210 clearfix clear">
            
            <!-- 我的购物车 -->
            <#include "/static/inc/header_shopcart.html">
            <!-- LOGO部分 -->
        	<div class="important" style="float:right;margin-top:12px;margin-right:10px;"><img src="/static/image/400.png"></div>
            <h1 class="logo fl">
            <#include "/static/imgout/header_logo.html">
            </h1>
            <div class="current-page" style="float:left;margin-top:10px;margin-left:20px;">
            	<h2 class="current-location" style="font-family:'Arial',Microsoft Yahei;color:#e74925;font-size:22px;font-weight:bold;letter-spacing:2px;">我的111</h2>
                <a href="/" style="border:#e74925 1px solid;border-radius:10px;display:block;color:#e74925;width:120px;text-align:center;margin-left:-2px;margin-top:2px;">返回医药馆首页</a>
            </div>
            
        </div>
    </div>
</div>

<#include "/static/inc/header_cartjs.html">
