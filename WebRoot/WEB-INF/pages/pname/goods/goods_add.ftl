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
.yygdivcss{background-color:#d0d0d0; position:absolute; z-index:99; left: 20%; bottom:0; width:300px;display:none; 
height:200px;filter: alpha(opacity=50);-moz-opacity: 0.5;}

.BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; 
display:none; width:100%; height:1000px;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
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
			 <td width="96%" valign="bottom" class="l_tit">商品添加</td>
		  </tr>
    	</table>
	</td>
  </tr>
  </table>
	<ul> 
	<li id="tow1" class="on" onclick='setTab("tow",1,8)'>基础信息 </li> 
	<li id="tow2" onclick='setTab("tow",2,8)'>详细信息 </li> 
	<li id="tow3" onclick='setTab("tow",3,8)'>商品描述 </li> 
	<li id="tow4" onclick='setTab("tow",4,8)'>药品说明 </li> 
	<li id="tow5" onclick='setTab("tow",5,8)'>编辑图片  </li> 
	<li id="tow6" onclick='setTab("tow",6,8)'>赠送赠品 </li>
	<li id="tow7" onclick='setTab("tow",7,8)'>推荐组合 </li>
	<li id="tow8" onclick='setTab("tow",8,8)'>上架设置 </li>
	</ul> 
</div> 



<form  action="${base}/goods/goods!goodsAdd.action" method="post" name="form1" id="form1" enctype="multipart/form-data">
<div class="tabList"> 
<div id="cont_tow_1" class="one block"> 
<!------------------------------------------------------------------基础信息----------------------------------------------------------------------------->
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
   <tr><td colspan="100%"><div class="com_bg">>>标识信息<div></td></tr>
   <tr>
    <td>药监码:</td>
    <td><input type="text" id="drugCode" name="goods.drugCode" onblur="vd(this);" title="药监码" maxlength="30" /></td>
    <td>条形码:</td>
    <td><input type="text" id="barCode" name="goods.barCode" onblur="vd(this);" title="条形码" maxlength="30" /></td>
   </tr>
   <tr><td colspan="100%"><div class="com_bg">>>商品属性<div></td></tr> 
  
   <tr>
    <td style="width: 12%;"><span style="color:red">*</span>简称:</td>
    <td>
    <input type="text" id="shortName" name="goods.shortName" onblur="vd(this);" title="简称" maxlength="50"/>
    <span id="sidshortName" style="color:red"></span>
    </td>
    <td style="width: 12%;"><span style="color:red">*</span>全称:</td>
    <td>
    <input type="text" id="goodsName" name="goods.goodsName" onblur="vd(this);" title="全称" maxlength="100"/>
    <span id="sidgoodsName" style="color:red"></span>
    </td>
  </tr>
   <tr>
    <td><span style="color:red">*</span>主标题:</td>
    <td>
    <input type="text" id="mainTitle" name="goods.mainTitle" onblur="vd(this);" title="主标题" maxlength="80"/>
    <span id="sidmainTitle" style="color:red"></span>
    </td>
    <td>副标题:</td>
    <td>
    <input type="text" id="subTitle" name="goods.subTitle" title="副标题" maxlength="100"/>
    <span id="sidsubTitle" style="color:red"></span>
    </td>
  </tr>
  <tr>
    <td><span style="color:red">*</span>通用名称:</td>
    <td>
    <input type="text" id="genericName" name="goods.genericName" onblur="vd(this);" title="通用名称" maxlength="80"/>
    <span id="sidgenericName" style="color:red"></span>
    </td>
    <td>赠送积分:</td>
    <td>
    <input type="text" id="integral" name="goods.integral" onblur="vd(this);" title="赠送积分" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    <span id="sidintegral" style="color:red"></span>
    </td>
  </tr>
  
  <tr>
    <td>别名:</td>
    <td>
    <input type="text" id="goodAlias" name="goods.goodAlias" title="别名" maxlength="50" t_value="" o_value="" />
    </td>
    <td>批准文号:</td>
    <td>
    <input type="text" id="approvalNumber" name="goods.approvalNumber" title="批准文号" maxlength="50" t_value="" o_value="" />
    </td>
  </tr>
  
  <tr>
    <td><span style="color:red">*</span>市场价格:</td>
    <td>
    <input type="text" id="price" name="goods.price" value="0" onblur="vd(this);" title="市场价格" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
    <span id="sidprice" style="color:red"></span>
    </td>
     <td>成本价格:</td>
    <td>
    <input type="text" id="costPrice" name="goods.costPrice" title="成本价格" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
    <input type="hidden" name="goods.unit" value="g"/>
    <span id="sidcostPrice" style="color:red"></span>
    </td>
  </tr>

  <tr>
    <td><span style="color:red">*</span>重量:</td>
    <td>
    <input type="text" id="weight" name="goods.weight" value="0" onblur="vd(this);" title="重量" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    <span id="sidweight" style="color:red"></span>
    </td>
    <td><span style="color:red">*</span>生产厂家:</td>
    <td>
	<input type="text" id="manufacturerId" onblur="vd(this);" title="生产厂家" maxlength="50" style="width: 170px;" autocomplete="off"/>
	<input type="hidden" id="manufacturerId1" name="goods.manufacturerId"/>
	<span id="sidmanufacturerId" style="color:red"></span> 
    </td>
  </tr>
  
  <tr>
    <td>产品规格1:</td>
    <td>
    <input type="text" id="spec1" name="goods.spec" title="产品规格1" maxlength="20"/>
    <a href="javascript:void(0);" onclick="addgg();" style="float:right" title="点击添加可添加多个规格!">添加</a>
    </td>
    <td><span style="color:red">*</span>库存:</td>
    <td>
    <input type="text" id="stock" name="goods.stock" value="0" onblur="vd(this);" title="库存" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    <span id="sidstock" style="color:red"></span>
    </td>
  </tr>
    <tr style="display: none" name="trs">
	    <td style="color:#0190ff">产品规格2:</td>
	    <td>
	    	<input type="text" id="spec2" name="goods.spec" onblur="vd(this);" title="产品规格2" maxlength="20"/>
    <span id="sidspec2" style="color:red"></span>
    	</td>
	    <td style="color:#0190ff">产品规格3:</td>
	    <td>
	    	<input type="text" id="spec3" name="goods.spec" onblur="vd(this);" title="产品规格3" maxlength="20"/>
    <span id="sidspec3" style="color:red"></span>
    	</td>
    </tr>
    <tr style="display: none" name="trs">
	    <td style="color:#0190ff">产品规格4:</td>
	    <td>
	    	<input type="text" id="spec4" name="goods.spec" onblur="vd(this);" title="产品规格4" maxlength="20"/>
    <span id="sidspec4" style="color:red"></span>
    	</td>
	    <td style="color:#0190ff">产品规格5:</td>
	    <td>
	    	<input type="text" id="spec5" name="goods.spec" onblur="vd(this);" title="产品规格5" maxlength="20"/>
    <span id="sidspec5" style="color:red"></span>
    	</td>
    </tr>
    <tr style="display: none" name="trs">
     <td style="color:#0190ff">产品规格6:</td>
	    <td>
	    	<input type="text" id="spec6" name="goods.spec" onblur="vd(this);" title="产品规格6" maxlength="20"/>
    <span id="sidspec6" style="color:red"></span>
    	</td>
	    <td style="color:#0190ff">产品规格7:</td>
	    <td>
	    	<input type="text" id="spec7" name="goods.spec" onblur="vd(this);" title="产品规格7" maxlength="20"/>
    <span id="sidspec7" style="color:red"></span>
    	</td>
    </tr>
    <tr style="display: none" name="trs">
    <td style="color:#0190ff">产品规格8:</td>
	    <td>
	    	<input type="text" id="spec8" name="goods.spec" onblur="vd(this);" title="产品规格8" maxlength="20"/>
    		<span id="sidspec8" style="color:red"></span>
    	</td>
	    <td style="color:#0190ff">产品规格9:</td>
	    <td>
	    	<input type="text" id="spec9" name="goods.spec" onblur="vd(this);" title="产品规格9" maxlength="20"/>
    		<span id="sidspec9" style="color:red"></span>
    	</td>
    </tr>
  <tr>
    <td><span style="color:red">*</span>缩略图:</td>
    <td colspan="100%">
	<img src="" id="img0" style="display:none;width:150px;height:150px">
	<img src="" id="img1" style="display: none;">
	<input type="file" class="file" name="file" id="file" style="width:150px;"/>
	<span style="color:red">（支持jpg,png,jpeg,gif格式图片,且图片尺寸为150x150！）</span>
	<span style="color:red" id="sidimg"></span>
    </td>
  </tr>
  <tr>
    <td><span style="color:red">*</span>商品分类:</td>
    <td colspan="100%">
    <input type="hidden" id="categoryId" name="goodsf.categoryId"/>
	<select onchange="getfl(1)" id="fl1" style="width:172px;">
	  <option value ="fl1">请选择</option>
	  ${goodsf.fl?default('')}
	</select>
	<select onchange="getfl(2)" id="fl2"  style="display:none">
	</select>
	<select onchange="getfl(3)" id="fl3" style="display:none">
	</select>
    </td>
  </tr>
  <tr>
    <td><span style="color:red">*</span>药品分类 :</td>
    <td colspan="100%">
	  <input type="radio" name="goods.type" value="1"/>OTC
	  <input type="radio" name="goods.type" value="2"/>处方药A
	  <input type="radio" name="goods.type" value="3"/>处方药B
	  <input type="radio" name="goods.type" value="4"/>非药品
    </td>
  </tr>
  
  
  <tr>
  </td>

	


    <td>医药馆:</td>
    <td colspan="100%">
    <div style="position: relative;">
    <input type="text" id="yiyaoguanId" onMouseOver="showyyg()"" readonly="readonly" />

    <div id="yygdiv" class="yygdivcss" onMouseOver="showyyg()" onMouseOut="hideyyg()">
<div id="sidetree" style="background-color:#d0d0d0;" onMouseOver="showyyg()" onMouseOut="hideyyg()">
<div id="sidetreecontrol"><a href="?#">合并全部</a> | <a href="?#">展开分部</a></div>
<ul id="tree">
<#list list1?if_exists as l1>
	<li>
	<span>
	<strong>
	${l1.categoryName}
	</strong>
	</span>
		<ul style="background:#d0d0d0">
		  <#list list2?if_exists as l2><#if l1.id==l2.parentId>
		 <span><strong>
		 <li><input type="checkbox" cname="${l2.categoryName}" <#list idlist?if_exists as lit><#if lit.targetId==l2.id>checked</#if></#list> name="${l1.id} ${l2.id}"  id="${l2.id}" value="${l2.id}"/>${l2.categoryName}
		<ul style="background:#d0d0d0">
		  <#list list3?if_exists as l3><#if l2.id==l3.parentId>
		  <span><strong>
		  <li><input type="checkbox" cname="${l3.categoryName}" <#list idlist?if_exists as lit><#if lit.targetId==l3.id>checked</#if></#list> name="${l1.id} ${l2.id} ${l3.id}" value="${l3.id}" id="${l1.id}_${l2.id}_${l3.id}"   />${l3.categoryName}
		  <ul style="background:#d0d0d0">
		  <#list list4?if_exists as l4>
		  <#if l3.id==l4.parentId>
		  <li><input type="checkbox" cname="${l4.categoryName}" <#list idlist?if_exists as lit><#if lit.targetId==l4.id>checked</#if></#list> name="${l1.id} ${l2.id} ${l3.id} ${l4.id}" value="${l4.id}" id="${l1.id}_${l2.id}_${l3.id}_${l4.id}"   />${l4.categoryName}
		  </li>
		  </#if>
		  </#list>
		</ul>
		  </li></strong></span>
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
        <input type="hidden" id="yiyaoguanIdval" name="goodsf.yiyaoguanId"/>
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
    <select id="brandId" name="goods.brandId" style="width:172px;">
	  <option value ="">请选择</option>
	  ${goodsf.brand?default('')}
	</select>
    </td>
  </tr>
  
    <tr>
    <td>搜索词:</td>
    <td colspan="100%"><textarea id="searchWord" name="goods.searchWord" title="搜索词" maxlength="100"></textarea>
    <span style="color:red">（多个关键词请用逗号分开！）</span>
    </td>
  </tr>
  
  
  
  <tr>
    <td colspan="4" align="center">
     <input type="button" name="save" id="save" value="保 存" onclick="tijiao();" />&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" name="cancel" id="cancel1" value="取消" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 

<!------------------------------------------------------------------详细信息----------------------------------------------------------------------------->
<style>
.com_bg{background:#e6e6e6;}
#attending {width:166px;resize:none;}
#seoDescribe {width:166px;resize:none;}
#searchWord {width:166px;resize:none;}
select {height:20px;}
</style>

<div id="cont_tow_2" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
   <tr><td colspan="100%"><div class="com_bg">>>详细属性<div></td></tr>
   <tr>
    <td style="width: 20%;">拼音全码:</td>
    <td>
    <input type="text" id="pinyinCode" name="goodse.pinyinCode" maxlength="100"/>
    <span id="sid" style="color:red"></span>
    </td>
    <td>主要成份:</td>
    <td>
    <input type="text" id="bases" name="goodse.bases" maxlength="100"/>
    </td>
  </tr>
   <tr>
    <td>性 状:</td>
    <td>
    <input type="text" id="characterd" name="goodse.characterd" maxlength="100"/>
    </td>
    <td>有 效 期:</td>
    <td>
    <input type="text" id="lasts" name="goodse.lasts" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>用法用量:</td>
    <td>
    <input type="text" id="usaged" name="goodse.usaged" maxlength="100"/>
    </td>
    <td>不良反应:</td>
    <td>
    <input type="text" id="untowardEffect" name="goodse.untowardEffect" maxlength="100"/>
    </td>
  </tr>
  
     <tr>
    <td>禁 忌:</td>
    <td>
    <input type="text" id="taboo" name="goodse.taboo" maxlength="100"/>
    </td>
    <td>注意事项:</td>
    <td>
    <input type="text" id="note" name="goodse.note" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>药理毒理:</td>
    <td>
    <input type="text" id="pharmacology" name="goodse.pharmacology" maxlength="100"/>
    </td>
    <td>贮 藏:</td>
    <td>
    <input type="text" id="storaged" name="goodse.storaged" maxlength="100"/>
    </td>
  </tr>
     <tr>
    <td>包 装:</td>
    <td>
    <input type="text" id="packing" name="goodse.packing" maxlength="100"/>
    </td>
        <td>适应症/功能主治:</td>
    <td>
    <input type="text" id="indication" name="goodse.indication" maxlength="100"/>
    </td>
  </tr>
  <tr>
  	<td>作用描述:</td>
    <td colspan="100%">
    <textarea id="attending" name="goodse.attending" maxlength="100"></textarea>
    </td>
  </tr>

  <tr><td colspan="100%"><div class="com_bg">>>SEO设置<div></td></tr>
  
  <tr>
    <td>seo标题:</td>
    <td colspan="100%">
    <input type="text" id="seoTitle" name="goodse.seoTitle" maxlength="100"/>
    </td>
  </tr>
  <tr>
    <td>seo关键字:</td>
    <td colspan="100%">
    <input type="text" id="seoKeyword" name="goodse.seoKeyword" maxlength="100"/>
    </td>
  </tr>
    <tr>
    <td>seo描述:</td>
    <td colspan="100%">
    <textarea id="seoDescribe" name="goodse.seoDescribe" maxlength="100"></textarea>
    </td>
  </tr>
  
  <tr>
    <td colspan="4" align="center">
     <input type="submit" name="save" id="save" value="保 存"  />&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" name="cancel" id="cancel1" value="取消" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 



<div id="cont_tow_3" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
   <tr>
    <td>
    <input type="hidden" name="goodse.goodsDescribes" id="goodsDescribe"/>
    <script type="text/plain" id="myEditor" style="width:100%;height:100%;"></script>	
    </td>
    </tr>
  <tr>
    <td colspan="2" align="center">
     <input type="submit" name="save" id="save" value="保 存"  />&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" name="cancel" id="cancel1" value="取消" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 

<div id="cont_tow_4" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
    <tr>
    <td>
    <input type="hidden" name="goodse.instructions" id="instruction"/>
    <script type="text/plain" id="myEditor1" style="width:100%;height:100%;"></script>	
    </td>
    </tr>
  <tr>
    <td colspan="2" align="center">
     <input type="submit" name="save" id="save" value="保 存"  />&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" name="cancel" id="cancel1" value="取消" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div>



<div id="cont_tow_5" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
	<tr>
	<td colspan="6">
     <a href="#"><input type="button" name="cancel" id="btnShow" value="上传图片" /></a>
    </td>
	<tr>
	<tr id="imagev">
		<td>图片</td>
		<td>标题</td>
		<td>所属系统</td>
		<td>是否默认</td>
		<td>排序</td>
		<td>操作</td>
	</tr>
  <tr>
    <td colspan="6" align="center">
     <input type="submit" name="save" id="save" value="保 存"  />&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" name="cancel" id="cancel1" value="取消" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div>




<div id="cont_tow_6" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
	<tr>
	<td colspan="6">
     <a href="#"><input type="button" name="cancel" id="btnShow2" value="添加赠品" /></a>
      <input type="hidden" id="goodspids" name="goodsf.goodspids"/>  
    </td>
	<tr>
	<tr id="ptrv">
		<td>名称</td>
		<td>规格</td>
		<td>数量</td>
		<td>操作</td>
	</tr>
  <tr>
    <td colspan="5" align="center">
     <input type="submit" name="save" id="save" value="保 存"  />&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" name="cancel" id="cancel1" value="取消" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>
</div> 



<div id="cont_tow_7" class="one"> 
<table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac">
	<tr>
	<td colspan="6">
     <a href="#"><input type="button" name="cancel" id="btnShow1" value="添加组合" /></a>
      <input type="hidden" id="goodsgroid" name="goodsf.goodsgids"/>     
      <input type="hidden" id="goodsgidsm" name="goodsf.goodsgidsm"/>     
    </td>
	<tr>
	<tr id="grouptrv">
		<td>名称</td>
		<td>规格</td>
		<td>是否主商品</td>
		<td>数量</td>
		<td>操作</td>
	</tr>
  <tr>
    <td colspan="5" align="center">
     <input type="submit" name="save" id="save" value="保 存"  />&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" name="cancel" id="cancel1" value="取消" onclick="history.go(-1)"/>
    </td>
  </tr>
</table>

</div> 

<div id="cont_tow_8" class="one"> 
<!------------------------------------------------------------------上架设置----------------------------------------------------------------------------->
    <style>
        .clearfix:after{clear:both;display:block;visibility:hidden;overflow:hidden;height:0;content:".";font-size:0}
        .fl{float:left}
        .fr{float:right}
        .clear{clear:both;zoom:1}
        html,body{width:100%; height:100%;}
        ._publish_wraper{width:950px; margin-left:20px;}
        ._publish_line{height:32px; overflow: hidden;}
        ._pheader_left{float:right;}
        ._publish_now{display:inline-block; width:100px; height:25px; line-height: 25px; margin-right:10px; text-align:center; color:#ffffff; cursor: pointer; margin-top:3px;}
        ._publish_save{display:inline-block; width:50px; height:25px; line-height: 25px; margin-right:10px; text-align:center; color:#ffffff; cursor: pointer; margin-top:3px;}
        .btn05{background:url("${base}/web/images/18btn05.jpg") no-repeat center;}
        .btn01{background:url("${base}/web/images/btn01.jpg") no-repeat center;}
        ._publish_content{border:1px solid #d2d2d2; margin-bottom:10px;}
        ._p_t_name{display:inline-block; width:70px; height:32px; line-height:32px; text-align:center; background:url("${base}/web/images/leftsign.jpg") no-repeat left center; margin:0 15px; float:left;}
        ._p_c_box{float:left; margin-top:6px;}
        ._p_checkbox{float:left; margin-top:4px; margin-right:5px;}
        ._top_line{border-top:1px solid #d2d2d2;}
        ._p_l_left{width:128px; height:32px; line-height:32px; text-indent: 23px; float:left; border-right:1px solid #d2d2d2;}
        ._p_l_right{float:left; width:819px; height:32px; line-height:32px; text-indent:10px;}
        ._p_l_checkbox{display:block; float:left; width:160px; height:20px; border:1px solid #d2d2d2; margin:5px 0 0 10px;}
        ._p_l_checkbox:hover{border-color:#268b7d;}
        ._pad_left{padding-left:30px;}
        ._p_t_box{float:right; margin-right:8px; margin-top:4px;}
        ._p_time_box{width:204px; height:22px; border:1px solid #d2d2d2;}
        ._p_r_price{width:345px; height:32px; line-height:32px; text-indent: 23px; float:left;}
        ._p_r_point{width:126px; height:32px; line-height:32px; float:left; border-right:1px solid #d2d2d2; border-left:1px solid #d2d2d2; text-align:center;}
        ._colred:hover{color:red;}
    </style>
 <div class="_publish_wraper">
        <div class="_publish_header _publish_line clearfix">
            <span class="_pheader_left">
                <input class="_publish_save btn01" value="保存" type="submit"/>
                <input class="_publish_save btn01" value="返回" type="button" onclick="history.go(-1)"/>
            </span>
        </div>
        <div class="_publish_content clearfix">
            <div class="_publish_title _publish_line clearfix">
                <span class="_p_t_name">综合设置</span>
                <label class="_p_c_box">
                    <input class="_p_checkbox" id="check_all" type="checkbox"/>
                    <span>三端一致</span>
                </label>
            </div>
        </div>
        <div class="_publish_content clearfix">
            <div class="_publish_title _publish_line clearfix">
                <span class="_p_t_name">Web</span>
                  <div class="_p_l_right">
                </div>
            </div>
            <div class="_publish_line _top_line clearfix">
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox"  name="priceformbeanmodel.isPcShow" value="1" id="isx1" checked="true" />
                    <span>显示商品</span>
                </label>
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox" name="priceformbeanmodel.pcStatus" value="1" id="sta1" checked="true" />
                    <span>上架</span>
                </label>
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox" name="priceformbeanmodel.isPcTop" value="1" id="itop1" />
                    <span>置顶</span>
                </label>
                <label class="_p_t_box">
                    <span>下架时间:</span>
                    <input type="text" name="priceformbeanmodel.pcDowndate" disabled="true" onblur="setDate('endDate');" class="_p_time_box" id="endDate" value="" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}',endDate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                </label>
                <label class="_p_t_box">
                    <span>上架时间:</span>
                    <input type="text" name="priceformbeanmodel.pcUpdate" disabled="true" class="_p_time_box" onblur="setDate('beginDate');" id="beginDate" value="" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',beginDate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                </label>
            </div>
            <div class="_publish_line _top_line clearfix">
                <div class="_p_l_left">销售价格</div>
                <div class="_p_r_price">
               <input type="text" id="pcPrice" name="priceformbeanmodel.pcPrice" value="0" onblur="vd(this);" title="销售价格(pc)" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
                 <span id="sidpcPrice" style="color:red"></span>
                </div>
            </div>
        </div>
        <div class="_publish_content clearfix">
            <div class="_publish_title _publish_line clearfix">
                <span class="_p_t_name">WAP</span>
                 <div class="_p_l_right">
                </div>
            </div>
            <div class="_publish_line _top_line clearfix">
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox"  name="priceformbeanmodel.isWapShow" value="1" id="isx2" checked="true" />
                    <span>显示商品</span>
                </label>
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox" name="priceformbeanmodel.wapStatus" value="1" id="sta2" checked="true" />
                    <span>上架</span>
                </label>
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox" name="priceformbeanmodel.isWapTop" value="1" id="itop2" />
                    <span>置顶</span>
                </label>
                <label class="_p_t_box">
                    <span>下架时间:</span>
                    <input type="text" name="priceformbeanmodel.wapDowndate" disabled="true" class="_p_time_box" id="wapDowndate" value="" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'wapUpdate\')}',wapDowndate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                </label>
                <label class="_p_t_box">
                    <span>上架时间:</span>
                   <input type="text" name="priceformbeanmodel.wapUpdate" disabled="true" class="_p_time_box" id="wapUpdate" value="" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'wapDowndate\')}',wapUpdate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                </label>
            </div>
            <div class="_publish_line _top_line clearfix">
                <div class="_p_l_left">销售价格</div>
                <div class="_p_r_price">
                 <input type="text" id="wapPrice" name="priceformbeanmodel.wapPrice" value="0" title="销售价格(wap)" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
                <span id="sidwapPrice" style="color:red"></span>
                </div>
            </div>
        </div>
        <div class="_publish_content clearfix">
            <div class="_publish_title _publish_line clearfix">
                <span class="_p_t_name">App</span>
                 <div class="_p_l_right">
                </div>
            </div>
            <div class="_publish_line _top_line clearfix">
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox" name="priceformbeanmodel.isAppShow" value="1" id="isx3" checked="true" />
                    <span>显示商品</span>
                </label>
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox" name="priceformbeanmodel.appStatus" value="1" id="sta3" checked="true" />
                    <span>上架</span>
                </label>
                <label class="_p_c_box _pad_left">
                    <input class="_p_checkbox" type="checkbox" name="priceformbeanmodel.isAppTop" value="1" id="itop3" />
                    <span>置顶</span>
                </label>
                <label class="_p_t_box">
                    <span>下架时间:</span>
                     <input type="text" name="priceformbeanmodel.appDowndate" disabled="true" class="_p_time_box" id="appDowndate" value="" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'appUpdate\')}',appDowndate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                </label>
                <label class="_p_t_box">
                    <span>上架时间:</span>
                     <input type="text" name="priceformbeanmodel.appUpdate" disabled="true" class="_p_time_box" id="appUpdate" value="" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'appDowndate\')}',appUpdate:'%y-%M-%d %H:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                </label>
            </div>
            <div class="_publish_line _top_line clearfix">
                <div class="_p_l_left">销售价格</div>
                <div class="_p_r_price">
                 <input type="text" id="appPrice" name="priceformbeanmodel.appPrice" value="0" title="销售价格(app)" maxlength="10" t_value="" o_value="" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
                 <span id="sidappPrice" style="color:red"></span>
                </div>
            </div>
        </div>
    </div>
</div> 
</form>

</div> 
</div> 














<#include "/WEB-INF/pages/pname/goods/goods_add_img.ftl">
<#include "/WEB-INF/pages/pname/goods/goods_add_group.ftl">
<#include "/WEB-INF/pages/pname/goods/goods_add_premiums.ftl">
<script type="text/javascript" src="${base}/web/js/jquery-1.8.3.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/web/plugins/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/web/plugins/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/web/js/ajaxfileupload.js"></script>
<script src="${base}/web/location_tree/jquery.treeview.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/plugins/autocomplete/jquery.autocomplete.js" charset="utf-8" ></script>
<script src="${base}/web/js/datePicker/WdatePicker.js"></script>
<script>
    $().ready(function(){
    $("#check_all").click(function(){
	    	if($("#isx1").is(':checked')){
	    		$("#isx2").attr("checked",true);
	    		$("#isx3").attr("checked",true);
	    	}else{
	    		$("#isx2").attr("checked",false);
	    		$("#isx3").attr("checked",false);
	    	}
	    	if($("#sta1").is(':checked')){
	    		$("#sta2").attr("checked",true);
	    		$("#sta3").attr("checked",true);
	    	}else{
	    	   $("#sta2").attr("checked",false);
	    	   $("#sta3").attr("checked",false);
	    	}
	    	if($("#itop1").is(':checked')){
	    		$("#itop2").attr("checked",true);
	    		$("#itop3").attr("checked",true);
	    	}else{
	    		$("#itop2").attr("checked",false);
	    		$("#itop3").attr("checked",false);
	    	}
	
	    	var beginDate=$("#beginDate").val();
	    	var endDate=$("#endDate").val();
	    	$("#wapUpdate").val(beginDate);
	    	$("#appUpdate").val(beginDate);
	    	$("#wapDowndate").val(endDate);
	    	$("#appDowndate").val(endDate);
	    	var pcPrice=$("#pcPrice").val();
	    	$("#wapPrice").val(pcPrice);
	    	$("#appPrice").val(pcPrice);
	    }else{
	    
	    }
    });
    
    
    
        	$("#isx1").click(function(){
	    	 if($("#check_all").is(':checked')){
	    		if($("#isx1").is(':checked')){
		    		$("#isx2").attr("checked",true);
		    		$("#isx3").attr("checked",true);
	    		}else{
	    			$("#isx2").attr("checked",false);
	    			$("#isx3").attr("checked",false);
	    		}
	    		}
	    	});
	    	$("#sta1").click(function(){
	    	 if($("#check_all").is(':checked')){
	    		if($("#sta1").is(':checked')){
		    		$("#sta2").attr("checked",true);
		    		$("#sta3").attr("checked",true);
	    		}else{
	    			$("#sta2").attr("checked",false);
	    			$("#sta3").attr("checked",false);
	    		}
	    		}
	    	});
	    	$("#itop1").click(function(){
	    	 if($("#check_all").is(':checked')){
	    		if($("#itop1").is(':checked')){
		    		$("#itop2").attr("checked",true);
		    		$("#itop3").attr("checked",true);
	    		}else{
	    			$("#itop2").attr("checked",false);
	    			$("#itop3").attr("checked",false);
	    		}
	    		}
	    	});
	    	$("#pcPrice").change(function(){
	    	 if($("#check_all").is(':checked')){
		    	$("#wapPrice").val($("#pcPrice").val());
	    		$("#appPrice").val($("#pcPrice").val());
	    		}
	    	});
    });
    
    
    function setDate(dateType){
	     if($("#check_all").is(':checked')){
	     	if(dateType=="beginDate"){
	     		$("#wapUpdate").val($("#beginDate").val());
	     		$("#appUpdate").val($("#beginDate").val());
	     	}else if(dateType=="endDate"){
	     		$("#wapDowndate").val($("#endDate").val());
	     		$("#appDowndate").val($("#endDate").val());
	     	}
	     }
    }
    
   function setStatus(type,goodsid){
	    jQuery.ajax({
		       type: "post",
		       url: " ${base}/goods/newGoods!setGoodsStatus.action",	  
		       data:{"type":type,"goodsid":goodsid},
		       success: function(data){
		          if(data>0){
			          if(type==1){
			          $("#sta1").attr("checked",false);
			          	$alert('success','PC下架成功!');
			          }else if(type==2){
			          $("#sta2").attr("checked",false);
			          	$alert('success','WAP下架成功!');
			          }else{
			          $("#sta3").attr("checked",false); 
			          	$alert('success','APP下架成功!');
			          }
		          }else{
		          	$alert('error','操作失败!');
		          }
		       },error:function(){
		       		$alert('error','操作失败!');
		       }
		       
		   }); 
    
    }
</script>
<script type="text/javascript">
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
function showyyg(){
	 $("#yygdiv").css("display","block");
}
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
		'<tr id="trs'+imgindex+'"><td><input type="hidden" value="'+ ids+'" name="goodsf.goodsimgid" /><img src="${base}' + path + '" style="width:100px;height:100px"></td><td>'+ arr[0]+'</td><td>'+userType+'</td><td>'+isdefault+'</td><td>'+arr[3]+'</td><td><a href="javascript:;" onclick="delImgtr(&quot;trs' + imgindex + '&quot;,'+ ids+');">删除</a></td></tr>';
	$("#imagev").after(trHtml);
	imgindex ++;
}


var productImageIndex = 0;
function addImg(){
		var trHtml = 
		'<tr id="tr' + productImageIndex + '"><td><input type="file" name="file" id="imgfile' + productImageIndex + '"/></td><td><input type="text" id="titel' + productImageIndex + '" maxlength="200" value="1"/></td><td><select id="userType' + productImageIndex + '"><option value="1">全平台</option></select> </td><td><select id="isdefault' + productImageIndex + '"><option value="0">否</option><option value="1">是</option></select></td><td><input type="text" id="sort' + productImageIndex + '" maxlength="9" style="width: 50px;" value="0"/></td><td id="adel' + productImageIndex + '"><a href="javascript:;"  onclick="delImgtr(&quot;tr' + productImageIndex + '&quot;,0);">删除</a></td></tr>';
	$("#imageTable").after(trHtml);
	productImageIndex ++;
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
      $("#BgDiv1").css({ display:"block",height:$(document).height()});
      var yscroll=document.documentElement.scrollTop;
      $("#DialogDiv1").css("top","100px");
      $("#DialogDiv1").css("display","block");
      document.documentElement.scrollTop=0;
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
	if(url!=null){
		$("#sidimg").html("");
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
	 var i = 0;
    function addgg(){
		var trs = $("tr[name='trs']");
		if(i>3){
			$alert('warn','一个商品最多九种规格!');
		}else{
			trs[i].style.display='';
			i++;
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
				if(''!=$(this).val()){
					iffile = false;
			  		if(!iffilename){
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
			} 
		  $("#uploadImgbt").attr("disabled","true");
	    }
   	function sx(v){
    	if(sum==v){
    		$alert('warn','上传完毕!');
		}
    }
    function upAction(file,v,id){
    	var url="${base}/goods/goods!upImgs.action?fileDatas="+v;
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
	 
	 
 //焦点失去验证
function vd(v){
	var flag = true;
	if(v.id=="shortName"){
		flag = isnotnull(v);
	}else if(v.id=="goodsName"){
		flag = isnotnull(v);
	}else if(v.id=="mainTitle"){
		flag = isnotnull(v);
	}else if(v.id=="genericName"){
		flag = isnotnull(v);
	}else if(v.id=="price"){
		flag = isnotnull(v);
	}
	else if(v.id=="pcPrice"){
		flag = isnotnull(v);
	}
	else if(v.id=="wapPrice"){
		flag = isnotnull(v);
	}
	else if(v.id=="skuId"){
		flag = isnotnull(v);
	}
	else if(v.id=="appPrice"){
		flag = isnotnull(v);
	}
	else if(v.id=="weight"){
		flag = isnotnull(v);
	}else if(v.id=="stock"){
		flag = isnotnull(v);
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
	 
	 
//提交验证
function tijiao() 
{
		 //简称
		 var shortName = document.getElementById("shortName");
		 if(isnotnull(shortName))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+shortName.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //全称
		 var goodsName = document.getElementById("goodsName");
		 if(isnotnull(goodsName))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+goodsName.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //主标题
		 var mainTitle = document.getElementById("mainTitle");
		 if(isnotnull(mainTitle))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+mainTitle.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //通用名称
	  	 var genericName = document.getElementById("genericName");	
		 if(isnotnull(genericName))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+genericName.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //市场价格
		 var price = document.getElementById("price");
		 if(isnotnull(price))
		 {
		 } 
		 else
		 {
		 	$alert('warn',$("#"+price.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //重量
		 var weight = document.getElementById("weight");
		 if(isnotnull(weight))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+weight.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //生产厂家
		 var manufacturerId1 = document.getElementById("manufacturerId1");
		 var manufacturerId = document.getElementById("manufacturerId");
		 if(isnotnull(manufacturerId1)&&isnotnull(manufacturerId))
		 {
		 } 
		 else
		 {
		 	$("#manufacturerId").val("");
		 	$("#manufacturerId1").val("");
		 	$alert('warn',$("#"+manufacturerId.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //库存
		 var stock = document.getElementById("stock");
		 if(isnotnull(stock))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+stock.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //缩略图
	     var imgUrl = $('#imgUrl').val();
		 var file = $('#file').val();
		 if($.trim(file)=="")
		 {
		 	$alert('warn',"药品缩略图不能为空");
		 	$("#sidimg").html("药品缩略图不能为空!");
		 	return false;
		 }
		 else
		 {
		 	var f = document.getElementById("file");
	        if(!/\.(jpg|jpeg|png|gif|JPG|PNG|GIF)$/.test(f.value))
	        {
	          $alert('warn',"请上传指定图片格式!");
	          $("#sidimg").html("请上传指定图片格式!");
	          return false;
	        }
	        if(5000<(f.files[0].size / 1024).toFixed(2))
	        {
	        	$alert('warn',"上传文件不能大于5M!");
	        	$("#sidimg").html("上传文件不能大于5M!");
	          	return false;
	        }
		 }
		 //商品PC价格
		 var pcPrice = document.getElementById("pcPrice");
		 if(isnotnull(pcPrice))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+pcPrice.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //商品WAP价格
		 var wapPrice = document.getElementById("wapPrice");
		 if(isnotnull(wapPrice))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+wapPrice.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 //商品APP价格
		 var appPrice = document.getElementById("appPrice");
		 if(isnotnull(appPrice))
		 {
		 }
		 else
		 {
		 	$alert('warn',$("#"+appPrice.id).attr("title")+"不能为空!");
		 	return false;
		 }
		 
	     var num = '';
	     $("#cont_tow_7 input[name=groupids]").each(function()
	     {
		    num = num+$(this).val()+":"+$(this).attr("num")+",";
	     });
	     $("#goodsgroid").val(num);
	     var goodsgidsm = document.getElementById("goodsgidsm");
	     if(''!=num)
	     {
		     if (goodsgidsm.value.replace(/\s+/g, "").length ==0)
		     {
			 	$alert('warn',"组合商品请选择主商品!")
			 	return false;
		     }
		  }
	
		var pnum = '';
		$("#cont_tow_6 input[name=preids]").each(function(){
			pnum = pnum+$(this).val()+":"+$(this).attr("num")+",";
		});
		$("#goodspids").val(pnum);
		
		var fl1 = $("#fl1").val();
		var fl2 = $("#fl2").val();
		var fl3 = $("#fl3").val();
		if("fl1"==fl1){
			$alert('warn',"请选择一级分类!");
			return false;
		}else{
			$("#categoryId").val(fl1);
		}
		if("fl2"==fl2){
			$alert('warn',"请选择二级分类!");
			return false;
		}else{
			if(""!=fl2&&fl2!=null){
				$("#categoryId").val(fl2);
			}
		}
		if("fl3"==fl3){
			$alert('warn',"请选择三级分类!");
			return false;
		}else{
			if(""!=fl3&&fl3!=null){
				$("#categoryId").val(fl3);
			}
		}

		 if($('input[name="goods.type"]:checked').val()==undefined){
		 	$alert('warn',"请选择药品分类!");
		 	return false;
		 }
		 
		var goodsDescribe =um.getContent();
		var instruction =um1.getContent();
		if($.trim(goodsDescribe)!=""){
			$("#goodsDescribe").val(goodsDescribe);
		}
		if($.trim(instruction)!=""){
			$("#instruction").val(instruction);
		}
		$("#form1").submit();
		$alert('success',"保存成功!");
	}
	 
</script>
</body>
</html>