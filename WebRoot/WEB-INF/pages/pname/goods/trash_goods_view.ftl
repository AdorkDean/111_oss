<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/WEB-INF/pages/inc/taglibs.ftl">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品管理</title>
<link href="${base}/web/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${base}/web/location_tree/jquery.treeview.css" />
<link href="${base}/web/plugins/autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.yygdivcss{background-color:#d0d0d0; position:absolute; z-index:99; left: 37%; bottom:0; width:300px;display:none; 
height:200px;filter: alpha(opacity=50);-moz-opacity: 0.5;}


.BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:1000px;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
.DialogDiv{
position:absolute;
left:20%;
margin-left:-100px; height:auto; 
z-index:100;background-color:#fff;
}

body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;}
body,td,th {font-family: 微软雅黑;font-size: 13px;}
.tac{position: absolute;width: 950px;background: #d0d0d0;}
.tac td{width: 118px;background-color:#FFFFFF;}
.act{width: 600px;}
.colordiv{position: absolute;height: 23px;background: #DDD9D9;}
.fct{font-family: 微软雅黑;font-size: 13px;font-weight: normal;font-style: normal;text-decoration: none;color: #CC6633;}
.la{padding: 5px;}
.tda{width: 118px;background-color:#FFFFFF;}
.tdb{width: 250px;}
.frm{font-size: 12px;color: red;}
.lia{text-decoration: none!important ;color: #706D6D!important;}
.hideTr{display: none;}


 
.tab 
{ 
border-bottom: #d0d0d0 1px solid; 
border-left: #d0d0d0 1px solid; 
border-top: #d0d0d0 1px solid; 
border-right: #d0d0d0 1px solid; 
} 
.tab UL 
{ 
zoom: 1; 
clear: both; 
} 
.tab UL:after 
{ 
display: block; 
height: 0px; 
visibility: hidden; 
clear: both; 
content: ""; 
} 
.tab UL LI 
{ 
text-align: center; 
line-height: 26px; 
width: 80px; 
display: inline; 
background: #d0d0d0; 
float: left; 
height: 26px; 
color: #fff; 
cursor:pointer;
} 
.tab UL LI.on 
{ 
background: #fff; 
color: #666; 
} 
.tabList 
{ 
border-bottom: #d0d0d0 1px solid; 
border-left: #d0d0d0 1px solid; 
height: 1200px; 
border-top: #d0d0d0 1px; 
border-right: #d0d0d0 1px solid; 
} 
.tabList .one 
{ 
padding-bottom: 10px; 
padding-left: 10px; 
padding-right: 10px; 
display: none; 
color: #666; 
padding-top: 10px; 
} 
.tabList .block 
{ 
display: block; 
} 
</style>
<style>
.com_bg{background:#e6e6e6;}
#attending {width:166px;resize:none;}
#seoDescribe {width:166px;resize:none;}
#searchWord {width:166px;resize:none;}
select {height:20px;}
</style>
</head>
<body>
<div> 
<div class="tab"> 
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="24" bgcolor="#576d85">
		  <tr>
			 <td width="2%" height="19" valign="middle" align="center"><img src="../web/images/tb.gif" width="14" height="14" /></td>
			 <td width="96%" valign="bottom" class="l_tit">商品修改</td>
		  </tr>
    	</table>
	</td>
  </tr>
  </table>
	<ul> 
	<li id="tow1" class="on" onclick='setTab("tow",1,7)'>基础信息 </li> 
	<li id="tow2" onclick='setTab("tow",2,7)'>详细信息 </li> 
	<li id="tow3" onclick='setTab("tow",3,7)'>商品描述 </li> 
	<li id="tow4" onclick='setTab("tow",4,7)'>药品说明 </li> 
	<li id="tow5" onclick='setTab("tow",5,7)'>编辑图片  </li> 
	<li id="tow6" onclick='setTab("tow",6,7)'>赠送赠品 </li>
	<li id="tow7" onclick='setTab("tow",7,7)'>推荐组合 </li>
	</ul> 
</div> 



<form action="${base}/goods/goods!goodsUpdate.action" method="post" name="form1" id="form1" enctype="multipart/form-data">
<div class="tabList"> 
<div id="cont_tow_1" class="one block"> 
<!------------------------------------------------------------------基础信息----------------------------------------------------------------------------->
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
   <tr><td colspan="100%"><div class="com_bg">>>标识信息<div></td></tr>
   <tr>
    <td>药监码:</td>
    <td><input type="text" id="drugCode" name="goods.drugCode" value="${goods.drugCode?default('')}" onblur="vd(this);" title="药监码" maxlength="30" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
    <td>条形码:</td>
    <td><input type="text" id="barCode" name="goods.barCode" value="${goods.barCode?default('')}" onblur="vd(this);" title="条形码" maxlength="30" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
   </tr>
   <tr>
    <td><span style="color:red">*海典编号:</span></td>
    <td colspan="100%">
    <input type="text" id="goodsno" name="goods.goodsno" title="海典编号" value="${goods.goodsno?default('')}"  maxlength="10"/>
    	<span id="sidgoodsno" style="color:red"></span>
    	<span style="color:red">（海典编号 请务必完善,且海典编号请保证唯一！）</span>
    </td>
  </tr>
  
   <tr><td colspan="100%"><div class="com_bg">>>商品属性<div></td></tr>
   
   <tr>
	    <td style="width: 12%;"><span style="color:red">*</span>简称:</td>
	    <td>
		<input type="hidden" id="goodsid" name="goods.id" value="${goods.id?default('')}"/>
		<input type="hidden" name="goodsf.shortname" value="${goodsf.shortname?default('')}"/>
		<input type="hidden" name="goodsf.goodsno" value="${goodsf.goodsno?default('')}"/>
	    <input type="text" id="shortName" name="goods.shortName" onblur="vd(this);" title="简称" value="${goods.shortName?default('')}" maxlength="50"/>
	    <span id="sidshortName" style="color:red"></span>
	    </td>
	    <td style="width: 12%;"><span style="color:red">*</span>全称:</td>
	    <td>
	    <input type="text" id="goodsName" name="goods.goodsName" onblur="vd(this);" title="全称" value="${goods.goodsName?default('')}" maxlength="100"/>
	    <span id="sidgoodsName" style="color:red"></span>
	    </td>
   </tr>
   
   <tr>
	    <td><span style="color:red">*</span>主标题:</td>
	    <td>
	    <input type="text" id="mainTitle" name="goods.mainTitle" onblur="vd(this);" title="主标题" value="${goods.mainTitle?default('')}" maxlength="80"/>
	    <span id="sidmainTitle" style="color:red"></span>
	    </td>
	    <td>副标题:</td>
	    <td>
	    <input type="text" id="subTitle" name="goods.subTitle" onblur="vd(this);" title="副标题" value="${goods.subTitle?default('')}" maxlength="100"/>
	    <span id="sidsubTitle" style="color:red"></span>
	    </td>
   </tr>
   
   <tr>
	    <td><span style="color:red">*</span>通用名称:</td>
	    <td>
	    <input type="text" id="genericName" name="goods.genericName" onblur="vd(this);" title="通用名称" value="${goods.genericName?default('')}" maxlength="80"/>
	    <span id="sidgenericName" style="color:red"></span>
	    </td>
	    <td>赠送积分:</td>
	    <td>
	    <input type="text" id="integral" name="goods.integral" onblur="vd(this);" title="赠送积分" value="${goods.integral?default('')}" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
	    <span id="sidintegral" style="color:red"></span>
	    </td>
   </tr>
   
   <tr>
	    <td>别名:</td>
	    <td>
	    <input type="text" id="goodAlias" name="goods.goodAlias" value="${goods.goodAlias?default('')}" title="别名" maxlength="50" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
	    </td>
	    <td>批准文号:</td>
	    <td>
	    <input type="text" id="approvalNumber" name="goods.approvalNumber" value="${goods.approvalNumber?default('')}" title="批准文号" maxlength="50"/>
	    </td>
   </tr>
   
   <tr>
	    <td><span style="color:red">*</span>市场价格:</td>
	    <td>
	    <input type="text" id="price" name="goods.price" onblur="vd(this);" title="市场价格" value="${goods.price?default('')}" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
	    <span id="sidprice" style="color:red"></span>
	    </td>
	    <td>成本价格:</td>
	    <td>
	    <input type="text" id="costPrice" name="goods.costPrice" onblur="vd(this);" title="成本价格" value="${goods.costPrice?default('')}"  maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
	    <span id="sidcostPrice" style="color:red"></span>
	    </td>
   </tr>
   
   
   <tr>
	    <td><span style="color:red">*</span>重量:</td>
	    <td>
	    <input type="text" id="weight" name="goods.weight" onblur="vd(this);" title="重量" value="${goods.weight?default('')}" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
	    <span id="sidweight" style="color:red"></span>
	    </td>
	    <td><span style="color:red">*</span>生产厂家:</td>
	    <td>
	    <input type="text" id="manufacturerId" onblur="vd(this);" title="生产厂家" maxlength="50" style="width:168px;" value="${goodsf.manufacturer?default('')}" autocomplete="off"/>
		<input type="hidden" id="manufacturerId1" name="goods.manufacturerId" value="${goodsf.manufacturerId?default('')}"/>
		<span id="sidmanufacturerId" style="color:red"></span> 
	    </td>
   </tr>
   
   <tr>
	    <td>产品规格:</td>
	    <td>
	    <input type="text" id="spec1" name="goods.spec" title="产品规格" value="${goods.spec?default('')}" maxlength="20"/>
	    </td>
	    <td><span style="color:red">*</span>库存:</td>
	    <td>
	    <input type="text" id="stock" name="goods.stock" onblur="vd(this);" title="库存" value="${goods.stock?default('')}"  maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
	    <span id="sidstock" style="color:red"></span>
	    </td>
   </tr>
   
   <tr>
	    <td><span style="color:red">*</span>缩略图:</td>
	    <td colspan="100%">
		<img src="${ui1}${goods.abbreviationPicture?default('')}" id="img0" style="width:100px;height:100px">
		<input type="hidden" id="imgids" value="${goods.abbreviationPicture?default('')}"/>
		<#--<input type="file" class="file" name="file" id="file" style="width:150px;"/>-->
		<img src="" id="img1" style="display: none;">
		<span style="color:red">（支持jpg,png,jpeg,gif格式图片,且图片尺寸为150x150！）</span>
		<span style="color:red" id="sidimg"></span>
	    </td>
   </tr>
   
   <tr>
	    <td><span style="color:red">*</span>商品分类:</td>
	    <td colspan="3">
	    <input type="hidden" id="categoryId" name="goodsf.categoryId" value="${goodsf.categoryId?default('')}"/>
	    <input type="hidden" id="categoryId1" name="goodsf.categoryId1"/>
	    <input type="hidden" id="flv1" value="${goodsf.fl1?default('')}"/>
	    <input type="hidden" id="flv2" value="${goodsf.fl2?default('')}"/>
	    <input type="hidden" id="flv3" value="${goodsf.fl3?default('')}"/>
		<select onchange="getfl(1)" id="fl1">
		  <option value ="fl1">请选择</option>
		  ${goodsf.fl1?default('')}
		</select>
		<select onchange="getfl(2)" id="fl2"  style="display:none">
		 ${goodsf.fl2?default('')}
		</select>
		<select onchange="getfl(3)" id="fl3" style="display:none">
		${goodsf.fl3?default('')}
		</select>
	    </td>
   </tr>
   
   <tr>
   		<td><span style="color:red">*</span>药品分类 :</td>
	    <td colspan="100%">
	    <input type="hidden" id="types" value="${goods.type?default('')}"/>
		  <input type="radio" id="typesv1" name="goods.type" value="1"/>OTC
		    <input type="radio" id="typesv2" name="goods.type" value="2"/>处方药A
		    <input type="radio" id="typesv3" name="goods.type" value="3"/>处方药B
		    <input type="radio" id="typesv4" name="goods.type" value="4"/>非药品
	    </td>
   </tr>
   
   <tr>
	    <td>医药馆:</td>
	    <td colspan="100%">
	    <div style="position: relative;">
	    <input type="text" id="yiyaoguanId" onMouseOver="showyyg()" onMouseOut="hideyyg()" readonly="readonly"  value="${goodsf.yiyaoguanName?default('')}" />
	    <div id="yygdiv" class="yygdivcss" onMouseOver="showyyg()" onMouseOut="hideyyg()">
	 	<div id="sidetree" style="background-color:#d0d0d0;" onMouseOver="showyyg()" onMouseOut="hideyyg()">
		<div id="sidetreecontrol"><a href="?#">合并全部</a> | <a href="?#">展开分部</a></div>
		<ul id="tree">
		<#list goodsf.list1?if_exists as l1>
		<li><span><strong>${l1.categoryName}</strong></span>
			<ul style="background:#d0d0d0">
			  <#list goodsf.list2?if_exists as l2><#if l1.id==l2.parentId>
			 <span><strong><li><input type="checkbox" cname="${l2.categoryName}" <#list goodsf.list4?if_exists as lit><#if lit==l2.id>checked</#if></#list> name="${l1.id} ${l2.id}"  id="${l2.id}" value="${l2.id}"   />${l2.categoryName}
			<ul style="background:#d0d0d0">
			  <#list goodsf.list3?if_exists as l3><#if l2.id==l3.parentId>
			  <li><input type="checkbox" cname="${l3.categoryName}" <#list goodsf.list4?if_exists as lit><#if lit==l3.id>checked</#if></#list> name="${l1.id} ${l2.id} ${l3.id}" value="${l3.id}" id="${l1.id}_${l2.id}_${l3.id}"   />${l3.categoryName}
			  <ul style="background:#d0d0d0">
			  <#list goodsf.list4s?if_exists as l4><#if l3.id==l4.parentId>
			  <li><input type="checkbox" cname="${l4.categoryName}" <#list goodsf.list4?if_exists as lit><#if lit==l4.id>checked</#if></#list> name="${l1.id} ${l2.id} ${l3.id} ${l4.id}" value="${l4.id}" id="${l1.id}_${l2.id}_${l3.id}_${l4.id}"   />${l4.categoryName}
			  </li>
			  </#if></#list>
			  </ul>
			  </li>
			  </#if></#list>
			</ul>
			    </li></strong></span>
			  
			  </#if></#list>
			</ul>
			</li>
		</#list>
		</ul>
		</div>
	    </div>
	    </div>
	    <input type="hidden" id="yiyaoguanIdval" name="goodsf.yiyaoguanId1" value="${goodsf.yiyaoguanId?default('')}"/>
	    <input type="hidden" name="goodsf.yiyaoguanId" value="${goodsf.yiyaoguanId?default('')}"/>
	    <span style="color:red" id="sidyyg"></span>
	    </td>
   </tr>
   
   <tr>
	    <td>剂量:</td>
	    <td>
	    <select id="doseId" name="goods.doseId" style="width:173px;">
		  <option value ="">请选择</option>
		  ${goodsf.dose?default('')}
		</select>
	    </td>
	    <td>品牌:</td>
	    <td>
	    <select id="brandId" name="goods.brandId" style="width:173px;">
		  <option value ="">请选择</option>
		  ${goodsf.brand?default('')}
		</select>
	    </td>
   </tr>
   
   <tr>
	    <td>搜索词:</td>
	    <td colspan="100%"><textarea id="searchWord" name="goods.searchWord" title="搜索词" maxlength="100">${goods.searchWord?default('')}</textarea>
	    <span style="color:red">（多个关键词请用逗号分开！）</span>
	    </td>
   </tr>
   
  <tr><td colspan="100%"><div class="com_bg">>>价格设置<div></td></tr>
  <input type="hidden" value="${newGoods.pc_priceId?default(0)}" name="priceidformbean.pc_priceId"/>
  <input type="hidden" value="${newGoods.wap_priceId?default(0)}" name="priceidformbean.wap_priceId"/>
  <input type="hidden" value="${newGoods.app_priceId?default(0)}" name="priceidformbean.app_priceId"/>
  
  <tr>
    <td><span style="color:red">*</span>销售价格(pc):</td>
    <td>
    <input type="text" id="pcPrice" name="priceformbeanmodel.pcPrice" value="${newGoods.pcPrice?default(0)}" onblur="vd(this);" title="销售价格(pc)" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
    <span id="sidpcPrice" style="color:red"></span>
    </td>
    <td><span style="color:red">*</span>销售价格(wap):</td>
    <td>
    <input type="text" id="wapPrice" name="priceformbeanmodel.wapPrice" value="${newGoods.wapPrice?default(0)}" title="销售价格(wap)" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
    <span id="sidwapPrice" style="color:red"></span>
  </tr>
   
  <tr>
  	</td>
        <td><span style="color:red">*</span>销售价格(app):</td>
    <td>
    <input type="text" id="appPrice" name="priceformbeanmodel.appPrice" value="${newGoods.appPrice?default(0)}" title="销售价格(app)" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
    <span id="sidappPrice" style="color:red"></span>
    </td>
        <td>是否置顶:</td>
    <td>
	    <input type="checkbox" name="priceformbeanmodel.isPcTop" value="1" id="itop1" <#if newGoods.isPcTop?exists><#if newGoods.isPcTop==1>checked</#if></#if>/>pc
	    <input type="checkbox" name="priceformbeanmodel.isWapTop" value="1" id="itop2" <#if newGoods.isWapTop?exists><#if newGoods.isWapTop==1>checked</#if></#if>/>wap
	    <input type="checkbox" name="priceformbeanmodel.isAppTop" value="1" id="itop3" <#if newGoods.isAppTop?exists><#if newGoods.isAppTop==1>checked</#if></#if>/>app
	    <input type="checkbox" onclick="quanxuan('itop');" id="itop"/>全选
    </td>
  </tr>
  
  <tr>
  	<td>上下架状态 </td>
    <td>
    <input type="checkbox" name="priceformbeanmodel.pcStatus" value="1" id="sta1" <#if newGoods.pcStatus?exists><#if newGoods.pcStatus==1>checked</#if></#if>/>pc
    <input type="checkbox" name="priceformbeanmodel.wapStatus" value="1" id="sta3" <#if newGoods.wapStatus?exists><#if newGoods.wapStatus==1>checked</#if></#if>/>wap
    <input type="checkbox" name="priceformbeanmodel.appStatus" value="1" id="sta2" <#if newGoods.appStatus?exists><#if newGoods.appStatus==1>checked</#if></#if>/>app
    <input type="checkbox" onclick="quanxuan('sta');" id="sta" checked="true"/>全选
    </td>
    <td>是否显示:</td>
    <td>
    <input type="checkbox" name="priceformbeanmodel.isPcShow" value="1" id="isx1" <#if newGoods.isPcShow?exists><#if newGoods.isPcShow==1>checked</#if></#if>/>pc
    <input type="checkbox" name="priceformbeanmodel.isWapShow" value="1" id="isx2" <#if newGoods.isWapShow?exists><#if newGoods.isWapShow==1>checked</#if></#if>/>wap
    <input type="checkbox" name="priceformbeanmodel.isAppShow" value="1" id="isx3" <#if newGoods.isAppShow?exists><#if newGoods.isAppShow==1>checked</#if></#if>/>app
    <input type="checkbox" onclick="quanxuan('isx');" id="isx" checked="true"/>全选
    </td>
  </tr>
   
  <tr><td colspan="100%"><div class="com_bg">>>自动化设置<div></td></tr>
  <tr>
    <td>PC自动上架时间:</td>
    <td>
    <input type="text" name="priceformbeanmodel.pcUpdate" class="i-text-i" id="beginDate" value="<#if newGoods.pcUpdate?exists>${newGoods.pcUpdate?datetime}</#if>" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',beginDate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
    </td>
    <td>PC自动下架时间:</td>
    <td>
    <input type="text" name="priceformbeanmodel.pcDowndate" class="i-text-i" id="endDate" value="<#if newGoods.pcDowndate?exists>${newGoods.pcDowndate?datetime}</#if>" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}',endDate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
    </td>
  </tr>
  <tr>
    <td style="font-size:12px;">WAP自动上架时间:</td>
    <td>
    <input type="text" name="priceformbeanmodel.wapUpdate" class="i-text-i" id="wapUpdate" value="<#if newGoods.wapUpdate?exists>${newGoods.wapUpdate?datetime}</#if>" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'wapDowndate\')}',wapUpdate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
    </td>
    <td style="font-size:12px;">WAP自动下架时间:</td>
    <td>
    <input type="text" name="priceformbeanmodel.wapDowndate" class="i-text-i" id="wapDowndate" value="<#if newGoods.wapDowndate?exists>${newGoods.wapDowndate?datetime}</#if>" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'wapUpdate\')}',wapDowndate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
    </td>
  </tr>
  <tr>
    <td style="font-size:12px;">APP自动上架时间:</td>
    <td>
    <input type="text" name="priceformbeanmodel.appUpdate" class="i-text-i" id="appUpdate" value="<#if newGoods.appUpdate?exists>${newGoods.appUpdate?datetime}</#if>" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'appDowndate\')}',appUpdate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
    </td>
    <td style="font-size:12px;">APP自动下架时间:</td>
    <td>
    <input type="text" name="priceformbeanmodel.appDowndate" class="i-text-i" id="appDowndate" value="<#if newGoods.appDowndate?exists>${newGoods.appDowndate?datetime}</#if>" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'appUpdate\')}',appDowndate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
    </td>
  </tr>
   
   
  <tr>
    <td colspan="4" align="center">
     <input type="button" name="cancel" id="cancel" value="返回" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 



<div id="cont_tow_2" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
   <tr>
    <td style="width: 20%;">拼音全码:</td>
    <td>
    <input type="text" id="pinyinCode" name="goodse.pinyinCode" value="${goodse.pinyinCode?default('')}" maxlength="100"/>
    <input type="hidden" name="goodse.goodsid" value="${goodse.goodsid?default('')}"/>
    <span id="sid" style="color:red"></span>
    </td>
    <td>主要成份:</td>
    <td>
    <input type="text" id="bases" name="goodse.bases" value="${goodse.bases?default('')}" maxlength="100"/>
    </td>
  </tr>
   <tr>
    <td>性 状:</td>
    <td>
    <input type="text" id="characterd" name="goodse.characterd" value="${goodse.characterd?default('')}" maxlength="100"/>
    </td>
    <td>适应症/功能主治:</td>
    <td>
    <input type="text" id="indication" name="goodse.indication" value="${goodse.indication?default('')}" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>用法用量:</td>
    <td>
    <input type="text" id="usaged" name="goodse.usaged" value="${goodse.usaged?default('')}" maxlength="100"/>
    </td>
    <td>不良反应:</td>
    <td>
    <input type="text" id="untowardEffect" name="goodse.untowardEffect" value="${goodse.untowardEffect?default('')}" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>禁 忌:</td>
    <td>
    <input type="text" id="taboo" name="goodse.taboo" value="${goodse.taboo?default('')}" maxlength="100"/>
    </td>
    <td>注意事项:</td>
    <td>
    <input type="text" id="note" name="goodse.note" value="${goodse.note?default('')}" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>药理毒理:</td>
    <td>
    <input type="text" id="pharmacology" name="goodse.pharmacology" value="${goodse.pharmacology?default('')}" maxlength="100"/>
    </td>
    <td>贮 藏:</td>
    <td>
    <input type="text" id="storaged" name="goodse.storaged" value="${goodse.storaged?default('')}" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>包 装:</td>
    <td>
    <input type="text" id="packing" name="goodse.packing" value="${goodse.packing?default('')}" maxlength="100"/>
    </td>
    <td>有 效 期:</td>
    <td>
    <input type="text" id="lasts" name="goodse.lasts" value="${goodse.lasts?default('')}" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>主要作用:</td>
    <td>
    <input type="text" id="attending" name="goodse.attending" value="${goodse.attending?default('')}" maxlength="100"/>
    </td>
    <td>seo标题:</td>
    <td>
    <input type="text" id="seoTitle" name="goodse.seoTitle" value="${goodse.seoTitle?default('')}" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>seo关键字:</td>
    <td>
    <input type="text" id="seoKeyword" name="goodse.seoKeyword" value="${goodse.seoKeyword?default('')}" maxlength="100"/>
    </td>
    <td>seo描述:</td>
    <td>
    <input type="text" id="seoDescribe" name="goodse.seoDescribe" value="${goodse.seoDescribe?default('')}" maxlength="100"/>
    </td>
  </tr>
  <tr>
    <td colspan="4" align="center">
     <input type="button" name="cancel" id="cancel" value="返回" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 



<div id="cont_tow_3" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
   <tr>
    <td>
    <input type="hidden" name="goodse.goodsDescribes" id="goodsDescribe"/>
    <script type="text/plain" id="myEditor" style="width:100%;height:100%;">${goodse.goodsDescribes?default('')}</script>	
    </td>
    </tr>
  <tr>
    <td colspan="2" align="center">
     <input type="button" name="cancel" id="cancel" value="返回" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 

<div id="cont_tow_4" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
    <tr>
    <td>
    <input type="hidden" name="goodse.instructions" id="instruction"/>
    <script type="text/plain" id="myEditor1" style="width:100%;height:100%;">${goodse.instructions?default('')}</script>	
    </td>
    </tr>
  <tr>
    <td colspan="2" align="center">
     <input type="button" name="cancel" id="cancel" value="返回" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div>


<div id="cont_tow_5" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
	<tr>
	<td colspan="6">
     <#--<a href="#"><input type="button" name="cancel" id="btnShow" value="上传图片" /></a>-->
    </td>
	<tr>
	<tr id="imagev">
		<td>图片</td>
		<td>标题</td>
		<td>所属系统</td>
		<td>是否默认</td>
		<td>排序</td>
	</tr>
  <tr>
  <#list listi?if_exists as resc>
	<tr height="35px" id="trsl_${resc.id}">
	<td class="bc" title="${resc.artworkUrl?default('')}">
	<#if resc.artworkUrl?exists>
			<img src="${ui1}${resc.artworkUrl?default('')}" style="width:100px;height:100px">
	<#else>
	</#if>
	</td>
	<td class="bc" title="${resc.titel?default('')}">
	<#if resc.titel?exists>
		<#assign siz=resc.titel.length()>
        <#if siz gt 50 >
       		${resc.titel[0..50]?default('')}...
	  	<#else>
			${resc.titel?default('')}
	   </#if>
	<#else>
	</#if>
	</td>
	<td class="bc" title="${resc.userType?default('')}">
	<#if resc.userType?exists>
		<#if resc.userType==1>
			PC
		</#if>
		<#if resc.userType==2>
			WAP
		</#if>
		<#if resc.userType==3>
			APP
		</#if>
	<#else>
	</#if>
	</td>
	<td class="bc" title="${resc.isdefault?default('')}">
		<#if resc.isdefault?exists>
			<#if resc.isdefault==0>
				否
			</#if>
			<#if resc.isdefault==1>
				是
			</#if>
		<#else>
		</#if>
	</td>
	<td class="bc" title="${resc.sort?default('')}" id="img_${resc.id}" ondblclick="setSort(&apos;${resc.id?default('')}&apos;)" imgsort="${resc.sort?default('')}">
		<#if resc.sort?exists>
			${resc.sort?default('')}
		<#else>
		</#if>
	</td>
	</tr>
  </#list>
    <td colspan="6" align="center">
     <input type="button" name="cancel" id="cancel" value="返回" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div>

<div id="cont_tow_6" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
	<tr>
	<td colspan="6">
     <#--<a href="#"><input type="button" name="cancel" id="btnShow2" value="添加赠品" /></a>-->
      <input type="hidden" id="goodspids" name="goodsf.goodspids"/>  
    </td>
	<tr>
	<tr id="ptrv">
		<td>名称</td>
		<td>规格</td>
		<td>数量</td>
		<td>操作</td>
	</tr>
	
	<#list listp?if_exists as resc>
	<tr height="35px" id="trsl_${resc.id}">
	<input type="hidden" name="preids" value="${resc.premiums_id}" num="${resc.goodsum}" >
	<td class="bc" title="${resc.short_name?default('')}">
	<#if resc.short_name?exists>
		${resc.short_name?default('')}
	<#else>
	</#if>
	</td>
	<td class="bc" title="${resc.spec?default('')}">
	<#if resc.spec?exists>
			${resc.spec?default('')}
	<#else>
	</#if>
	</td>
	<td class="bc" title="${resc.goodsum?default('')}">
		<#if resc.goodsum?exists>
			${resc.goodsum?default('')}
		<#else>
		</#if>
	</td>
	<td class="bc" style="width:15%">
	<a href="javascript:;" onclick="delpre('trsl_${resc.id}','${resc.id}','${resc.short_name?default('')}',1);">删除</a>
	</td>
	</tr>
  </#list>
	
	
	
  <tr>
    <td colspan="4" align="center">
     <input type="button" name="cancel" id="cancel" value="返回" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 



<div id="cont_tow_7" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
	<tr>
	<td colspan="6">
     <#--<a href="#"><input type="button" name="cancel" id="btnShow1" value="添加组合" /></a>-->
      <input type="hidden" id="goodsgroid" name="goodsf.goodsgids"/>     
      <input type="hidden" id="goodsgidsm" name="goodsf.goodsgidsm" value="${goodsf.goodsgidsm?default('')}"/>     
    </td>
	<tr>
	<tr id="grouptrv">
		<td>名称</td>
		<td>规格</td>
		<td>是否主商品</td>
		<td>数量</td>
		<td>操作</td>
	</tr>
	
	
	<#list listg?if_exists as resc>
	<tr height="35px" id="trsg_${resc.id}">
	<input type="hidden" id="hid${resc.id}"name="groupids" value="${resc.goodsid}" num="${resc.goods_num}" ism="${resc.main_goods?default('')}">
	<td class="bc" title="${resc.short_name?default('')}">
	<#if resc.short_name?exists>
		${resc.short_name?default('')}
	<#else>
	</#if>
	</td>
	<td class="bc" title="${resc.spec?default('')}">
		<#if resc.spec?exists>
				${resc.spec?default('')}
		<#else>
		</#if>
	</td>
	<td class="bc" title="${resc.main_goods?default('')}">
	<span name="spismv">
		<#if resc.main_goods?exists>
			<#if resc.main_goods==0>
				否
			</#if>
			<#if resc.main_goods==1>
				是
			</#if>
		<#else>
		</#if>
		</span>
	</td>
	<td class="bc" title="${resc.goods_num?default('')}">
		<#if resc.goods_num?exists>
			${resc.goods_num?default('')}
		<#else>
		</#if>
	</td>
	<td class="bc" style="width:15%">
	<a href="javascript:;" onclick="delGroup('trsg_${resc.id}','${resc.id}','${resc.short_name?default('')}',1);">删除</a>
	</td>
	</tr>
  </#list>
	
	
  <tr>
    <td colspan="5" align="center">
     <input type="button" name="cancel" id="cancel1" value="返回" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 
</div> 
</div> 
</form>
<#include "/WEB-INF/pages/pname/goods/goods_update_img.ftl">
<#include "/WEB-INF/pages/pname/goods/goods_update_group.ftl">
<#include "/WEB-INF/pages/pname/goods/goods_update_premiums.ftl">
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/web/js/ajaxfileupload.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/web/plugins/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/web/plugins/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<script src="${base}/web/location_tree/jquery.treeview.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>
<script src="${base}/web/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
function setSort(k){
	var i = $("#img_"+k).attr("imgsort"); 
	html ='<input type="text" value='+i+' id="sortv'+k+'" style="width:50px;" maxlength="2" onkeyup="on1(this);"/><input type="button" class="btn01" onclick="upimgsort('+i+',&apos;'+k+'&apos;)" value="修改" style="margin-left:15px;margin-top:2px;">';
	$("#img_"+k).html(html);
}
function on1(v){
	v.value=v.value.replace(/\D/g,0);
}
function upimgsort(i,v1){
	    if(!window.confirm("您确定要修改该记录吗?")){
		  	return;
		 }	
		 var sortv = $("#sortv"+v1).val()
		 if(sortv!=''){
			 jQuery.ajax({
			       type: "post",
			       url: " ${base}/goods/goods!upImg.action",	  
			       data:{"id":v1,"sort":sortv},
			       success: function(data){
			          if(data>0){
			          $alert("success","操作成功！");
			          $("#img_"+v1).html(sortv);
			          $("#img_"+v1).attr("imgsort",sortv); 
			          }else{
			          	$alert('error','网络异常!');
			          }
			       },error:function(){
			       		$alert('error','网络异常!');
			       }
		  		}); 
		 }else{
		 	$alert('warn','请输入序号!');
		 }
    }
$("#manufacturerId").autocomplete({	
		url: '${base}/goods/goods!likeManufacturer.action',
		showResult: function(value, data) {
			return '<span style="color:blue">'+value+'</span>';
		},
		onItemSelect: function(item) {
		   $("#manufacturerId").attr("value",item.value);
		   $("#manufacturerId1").val(item.data);
		},
		maxItemsToShow: 20,
		cacheLength :1,
		matchSubset :true,
		useCache: false,
		mustMatch:true,
		minChars: 1
});
function quanxuan(v){
	if($("#"+v).attr("checked")){
		$("#"+v+"1").attr("checked",true);
		$("#"+v+"2").attr("checked",true);
		$("#"+v+"3").attr("checked",true);
	}else{
		$("#"+v+"1").attr("checked",false);
		$("#"+v+"2").attr("checked",false);
		$("#"+v+"3").attr("checked",false);
	}
}
function showyyg(){
	 $("#yygdiv").css({"display":"block","left":"150px"});
}
function hideyyg(){
	$("#yiyaoguanId").val("");
	var str="";
	var ids="";
        $("#sidetree input[type='checkbox']:checkbox").each(function(){ 
            if($(this).attr("checked")){
                str += $(this).attr("cname")+","
           		ids +=$(this).val()+",";
            }
        })
        $("#yiyaoguanIdval").val(ids);
		$("#yiyaoguanId").val(str);
		$("#yygdiv").css("display","none");
}
//分类
function getfl(v){
	if("fl1"==$("#fl"+v).val()){
		$("#fl2").hide();
		$("#fl3").hide();
	}
	if("fl2"==$("#fl"+v).val()){
		$("#fl3").hide();
	}
	if("fl1"!=$("#fl"+v).val()&&"fl2"!=$("#fl"+v).val()&&"fl3"!=$("#fl"+v).val()){
		jQuery.ajax({
		       type: "post",
		       url: " ${base}/goods/goods!getCategory.action",	  
		       data:{"id":$("#fl"+v).val()},
		       success: function(data){
			       if(1==v){
				       $("#fl2").hide();
				       $("#fl3").hide();
				       $("#fl2").empty();
				       $("#fl3").empty();
			       }
			       if(2==v){
				       $("#fl3").hide();
				       $("#fl3").empty();
			       }
			       if(""!=data){
			       		v++;
			       		$("#fl"+v).empty();
			       		$("#fl"+v).show();
			       		$("#fl"+v).append("<option value ='fl"+v+"'>请选择</option>");
				       $("#fl"+v).append(data);
			       }
		       },error:function(){
		       		$alert('error','网络异常!');
		       }
		   });
	}
}
//焦点失去验证
function vd(v){
	var flag = true;
	if(v.id=="shortName"){
		flag = isnotnull(v);
	}else if(v.id=="goodsName"){
		flag = isnotnull(v);
	}else if(v.id=="mainTitle"){
		flag = isnotnull(v);
	}else if(v.id=="subTitle"){
		flag = isnotnull(v);
	}else if(v.id=="genericName"){
		flag = isnotnull(v);
	}else if(v.id=="price"){
		flag = isnotnull(v);
	}else if(v.id=="pcPrice"){
		flag = isnotnull(v);
	}else if(v.id=="costPrice"){
		flag = isnotnull(v);
	}else if(v.id=="unit"){
		flag = isnotnull(v);
	}else if(v.id=="weight"){
		flag = isnotnull(v);
	}else if(v.id=="stock"){
		flag = isnotnull(v);
	}else if(v.id=="integral"){
		flag = isnotnull(v);
	}else if(v.id=="goodsno"){
		flag = isnotnull(v);
		if(flag){
			flag = onlyVer(v,1)
		}
	}else if(v.id=="manufacturerId"){
		flag = isnotnull(v);
	}
	return flag;
}
//自定义验证方法
function isnotnull(v){ 
	var flag = true;
	if (v.value.replace(/\s+/g, "").length ==0){
		$("#sid"+v.id).html($("#"+v.id).attr("title")+"不能为空!");
		flag = false;
　　}else{
		$("#sid"+v.id).html("");
	}
	 return flag;
}
	function onlyVer(v,t){ 
		var flag = false;
		 jQuery.ajax({
	       type: "post",
	       async:false,
	       url: " ${base}/goods/goods!onlyVerification.action",	  
	       data:{"vals":v.value,"type":t,"id":$("#goodsid").val()},
	       success: function(data){
	          if(data>0){
	          	$("#sid"+v.id).html($("#"+v.id).attr("title")+"已经存在!");
			    flag = false;
	          }else{
	          	flag = true;
	          }
	       },error:function(){
	       		$alert('error','网络异常!');
	       }
	   });
		 return flag;
	}
	
	
//商品图片
var imgindex = 0;
function imgxs(datas,ids,path){
	var arr = new Array(); 
	arr = datas.split(",");
	var userType = "";
	if("1"==arr[1]){
		userType = "PC";
	}else if("2"==arr[1]){
		userType = "WAP";
	}else if("3"==arr[1]){
		userType = "APP";
	}
	var isdefault = "";
	if("0"==arr[2]){
		isdefault = "否";
	}else if("1"==arr[2]){
		isdefault = "是";
	}
	
	var trHtml = 
		'<tr id="trs'+imgindex+'"><td><input type="hidden" value="'+ ids+'" name="goodsf.goodsimgid" /><img src="${base}' + path + '" style="width:100px;height:100px"></td><td>'+ arr[0]+'</td><td>'+userType+'</td><td>'+isdefault+'</td><td id="img_'+ids+'" imgsort="'+arr[3]+'" ondblclick="setSort(&apos;'+ids+'&apos;)">'+arr[3]+'</td></tr>';
	$("#imagev").after(trHtml);
	imgindex ++;
}
var productImageIndex = 0;
function addImg()
{
		var trHtml = 
		"<tr id='tr" + productImageIndex + "'>"
			+"<td><input type='file' name='file' id='imgfile" + productImageIndex + "' onchange='show_image_small(this,"+productImageIndex+")'/></td>"
			+"<td><img src='' id='goods_imgs_"+productImageIndex+"' style='display:none;width:70px;height:70px'></td>"
			+"<td><input type='text' id='titel" + productImageIndex + "' maxlength='200' value='1'/></td>"
			+"<td><select id='userType" + productImageIndex + "'><option value='1'>全平台</option></select></td>"
			+"<td><select id='isdefault" + productImageIndex + "'><option value='0'>否</option><option value='1'>是</option></select></td>"
			+"<td><input type='text' id='sort" + productImageIndex + "' maxlength='9' style='width: 50px;' value='0'/></td>"
			+'<td id="adel"' + productImageIndex + '"><a href="javascript:void(0)" onclick="delImgtr(&quot;tr' + productImageIndex + '&quot;,0);">删除</a></td>'
			+"</tr>";
	$("#imageTable").after(trHtml);
	productImageIndex ++;
}
function show_image_small(obj, index)
{
	var objUrl = getObjectURL(obj.files[0]) ;
	if(objUrl)
	{
		$("#goods_imgs_"+index).attr("src", objUrl);
		$("#goods_imgs_"+index).show("slow");
	}
}
//文本输入插件
 var um = UE.getEditor('myEditor',{
   'initialFrameWidth':800,
   'initialFrameHeight':600
   });
 var um1 = UE.getEditor('myEditor1',{
   'initialFrameWidth':800,
   'initialFrameHeight':600
   });
//添加图片弹出层
$(function(){
	$("#tree").treeview({
		collapsed: true,
		animated: "medium",
		control:"#sidetreecontrol",
		persist: "location"
	});
	if($("#pcs").val()==1){
	 	$("#sta1").attr("checked", true);
	}
	if($("#apps").val()==1){
	 	$("#sta2").attr("checked", true);
	}
	if($("#waps").val()==1){
		$("#sta3").attr("checked", true);
	}
	$("#typesv"+$("#types").val()).attr("checked", true);
	
	if($("#pci").val()==1){
	 	$("#isx1").attr("checked", true);
	}
	if($("#appi").val()==1){
	 	$("#isx2").attr("checked", true);
	}
	if($("#wapi").val()==1){
		$("#isx3").attr("checked", true);
	}

	if($("#isr").val()==1){
	 	$("#isrv").attr("checked", true);
	}
	if($("#ist").val()==1){
	 	$("#istv").attr("checked", true);
	}
	if($("#flv2").val()!=''){
		$("#fl2").show();
	}
	if($("#flv3").val()!=''){
		$("#fl3").show();
	}
   $("#btnShow").click(function(){
	for (var i=productImageIndex;i>=0;i--){
		$("#tr"+productImageIndex).remove();
		if(0!=productImageIndex){
			productImageIndex--;
		}
	}
      $("#BgDiv").css({ display:"block",height:$(document).height()});
      var yscroll=document.documentElement.scrollTop;
      $("#DialogDiv").css("top","100px");
      $("#DialogDiv").css("display","block");
      document.documentElement.scrollTop=0;
   });
   $("#btnClose").click(function(){
   $("#uploadImgbt").removeAttr("disabled");
      $("#BgDiv").css("display","none");
      $("#DialogDiv").css("display","none");
   });
   
    $("#btnShow1").click(function(){
    jQuery.ajax({
	       type: "post",
	       async:false,
	       url: " ${base}/goods/goods!ifGroup.action",	  
	       data:{"id":$("#goodsid").val()},
	       success: function(data){
	          if(data>0){
	          	$alert('warn','该商品已成为套装!');
			    return false;
	          }else{
		      $("#BgDiv1").css({ display:"block",height:$(document).height()});
		      var yscroll=document.documentElement.scrollTop;
		      $("#DialogDiv1").css("top","100px");
		      $("#DialogDiv1").css("display","block");
		      document.documentElement.scrollTop=0;
	          }
	       },error:function(){
	       		$alert('error','网络异常!');
	       }
	   }); 
   });
   $("#btnClose1").click(function(){
   $("#uploadImgbt1").removeAttr("disabled");
      $("#BgDiv1").css("display","none");
      $("#DialogDiv1").css("display","none");
   });
   
    $("#btnShow2").click(function(){
      $("#BgDiv2").css({ display:"block",height:$(document).height()});
      var yscroll=document.documentElement.scrollTop;
      $("#DialogDiv2").css("top","100px");
      $("#DialogDiv2").css("display","block");
      document.documentElement.scrollTop=0;
   });
   $("#btnClose2").click(function(){
   $("#uploadImgbt2").removeAttr("disabled");
      $("#BgDiv2").css("display","none");
      $("#DialogDiv2").css("display","none");
   });
});
function delImgtr(v,id){
	if(v.indexOf("trs")>=0){
		 if(!window.confirm("您确定要删除该记录吗?删除的数据不能恢复！")){
			return;
		}
	jQuery.ajax({
	       type: "post",
	       url: " ${base}/goods/goods!delImgs.action",	  
	       data:{"id":id},
	       success: function(data){
	          if(data>0){
	          	$alert('success','操作成功!');
	          }else{
	         	$alert('error','网络异常!');
	          }
	       },error:function(){
	       	$alert('error','网络异常!');
	       }
	       
	   }); 
	}
	$("#"+v).remove();
}

//缩略图
$("#file").change(function(){
	var objUrl = getObjectURL(this.files[0]) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img0").attr("src", objUrl);
    	$("#img1").attr("src",objUrl);
		$("#img0").show("slow");
	}
}) ;
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}

//tab标签
	function setTab(name,m,n){ 
		for( var i=1;i<=n;i++){ 
			var menu = document.getElementById(name+i); 
			var showDiv = document.getElementById("cont_"+name+"_"+i); 
			menu.className = i==m ?"on":""; 
			showDiv.style.display = i==m?"block":"none"; 
		} 
	} 
	 var sum = 0;
	var sum1 = 0;
	  function uploadImg(){
		    var iffile = true;
			var iffilename = false;
			var idarr = new Array();
			var namearr = new Array();
			var ids = new Array();
			var ai = 0;
			$("#DialogDiv input[name=file]").each(function(){
				var fid = $(this).attr("id");
				var id = fid.substring(7,fid.length);
				if(''!=$(this).val())
				{
					iffile = false;
			  		if(!iffilename)
			  		{
						var titel = $("#titel"+id).val();
						var userType = $("#userType"+id).val();
						var isdefault = $("#isdefault"+id).val();
						var sort = $("#sort"+id).val();
			  			namearr[ai]=titel+","+userType+","+isdefault+","+sort+",";
			  			idarr[ai] = fid;
			  			ids[ai]=id;
			  			ai++;
			  		}
				}
			});
			if(iffile){
				$alert('warn','请选择上传文件!');
				return false;
			}
			if(iffilename){
				return false;
			}
			for(var j=0;j<idarr.length;j++) {
				upAction(idarr[j],namearr[j],ids[j]);
				sum++;
				$("#goods_imgs_"+j).hide();
			} 
		  //$("#uploadImgbt").attr("disabled","true");
	    }
   	function sx(v){
    	if(sum==v){
    		$alert('warn','上传完毕!');
		}
    }
    function upAction(file,v,id){
     	var goodsid = $("#goodsid").val();
    	var url="${base}/goods/goods!upImgs.action?fileDatas="+v+"&goodsid="+goodsid;
	     $.ajaxFileUpload({
	        url: url, //用于文件上传的服务器端请求地址
	        secureuri: false, //是否需要安全协议，一般设置为false
	        fileElementId: file, //文件上传域的ID
	        dataType: 'json', //返回值类型 一般设置为json
	        success: function (data, status){  //服务器成功响应处理函数
	       		imgxs(v,data.ids,data.path);
				$("#adel"+id).html(data.msg);       		
	        },
	        error: function (data, status, e){//服务器响应失败处理函数
	            $("#s"+id).text("上传异常!.....");
	        }
	    });
    }
    
    $(function(){
		$("input[type='text']").attr("disabled","true");
		$("select").attr("disabled","true");
		$("textarea").focus(function(){
			$(this).blur();
		}); 
    	
    })
</script>
</body>
</html>