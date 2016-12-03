

<div class="header">
	<#include "/static/inc/header_common.html">
    <div class="logo-search-cart">
        <div class="w1210 clearfix clear">
            <h1 class="logo fl">
            <#include "/static/imgout/header_logo.html">
            </h1>
			<#include "/static/inc/header_shopcart.html">
            <div class="header-search fl">
                <#include "/static/inc/header_search.html">
                <#include "/static/imgout/header_keyword.html">
            </div>
        </div>
    </div>
    <!--menu开始-->
    <div class="nav-box">
        <div class="menu">
            <div class="all-sort"><h2><a href="javascript:void(0)">全部商品分类</a></h2>
			<#include "/static/imgout/index_category.html">
            </div>
            <div class="important"><img src="/static/image/400.png"></div>
			<#include "/static/imgout/header_nav.html">
        </div>
    </div>
    <!--menu结束-->
</div>

<#include "/static/inc/header_cartjs.html">