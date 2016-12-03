<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
    <title>待处理处方药预订单详情 </title>
    <style>
        @charset"utf-8";
        a,abbr,address,article,aside,b,blockquote,body,caption,cite,code,dd,del,dfn,div,dl,dt,em,fieldset,figure,form,h1,h2,h3,h4,h5,h6,html,i,iframe,img,input,ins,kbd,label,legend,li,object,ol,p,pre,q,samp,small,span,strong,sub,sup,table,tbody,td,textarea,tfoot,th,thead,time,tr,ul,var{margin:0;padding:0;border:0;vertical-align:baseline;font-weight:400;font-size:100%}
        article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}
        audio,canvas,video{display:inline-block}
        audio:not([controls]){display:none}
        html{font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}
        body{width:100%;background:#fff;color:#000;font-size:12px;font-family:"Microsoft YaHei",Arial,Helvetica,verdana,sans-serif}
        input,textarea{color:#000;font-family:"Microsoft YaHei",Arial,Helvetica,verdana,sans-serif}
        input:-moz-placeholder,textarea:-moz-placeholder{color:#999}
        input:-ms-input-placeholder,textarea:-ms-input-placeholder{color:#999}
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{color:#999}
        a{color:#000;text-decoration:none;-webkit-tap-highlight-color:transparent;}
        a:hover{text-decoration:none}
        a:focus{outline:none;blr:expression(this.onFocus=this.blur());}
        a:active,a:hover{outline:0}
        img{display:block}
        input,textarea{outline:0;font-size:100%;resize:none;-webkit-tap-highlight-color:rgba(255,0,0,0)}
        textarea{resize:none;-webkit-appearance:none}
        ol,ul{list-style:none}
        em{font-style:normal}
        .clearfix:after{clear:both;display:block;visibility:hidden;overflow:hidden;height:0;content:".";font-size:0}
        .fl{float:left}
        .fr{float:right}
        .clear{clear:both;zoom:1}
        .text_line{height:30px; line-height: 30px; margin:10px 0 0 10px; position:relative;}
        .line_left{width:100px; height:30px; text-align: right; overflow: hidden; position:absolute; left:0; top:0;}
        .line-right{width:400px; height:30px; position:absolute; left:110px; top:0;}
        .text_cont{ border:1px solid #ccc; overflow:hidden; width:240px; height:18px; line-height: 18px; padding: 5px 10px; color:#999999;}
        .text_box{height:202px; position:relative; margin:10px 0 0 10px;}
        .pic_box{position:absolute; left:110px; top:0; width:200px; height:200px; border:1px solid #CCCCCC;}
        .pic_box img{width:200px; height:200px;}
        .input_box{width:100px; position:absolute; left:0; top:0; text-align: right;}
        .text_foot{position:relative; margin:10px 0 0 10px;}
        .product_describe{width:100px; height:1520px; text-align: right; position:absolute; left:0; top:0;}
        .product_pic{width:150px; height:150px; position:absolute; left:110px; top:0; border:1px solid #CCCCCC;}
        .product_pic img{width:150px; height:150px;}
        .product_name{width:800px; height:30px; line-height:30px; position:absolute; left:272px; top:0; }
        .product_number{width:800px; height:30px; line-height:30px; position:absolute; left:272px; top:0; }
        .product_price{width:800px; height:30px; line-height:30px; position:absolute; left:272px; top:30px;}
        .product_total{width:800px; height:30px; line-height:30px; position:absolute; left:272px; top:60px;}
        .pro_num{margin-top:61px;}
        .area_cont{position:absolute; left:110px; top:0; width:250px; height:100px; border:1px solid #ccc; line-height:2; overflow:hidden; padding:5px;}
    </style>
</head>
<body>
    <div class="text_wrapper">
        <div class="text_line clearfix">
            <div class="line_left">用户名：</div>
            <div class="line-right">
                <textarea style="word-break:keep-all; white-space:nowrap;" class="text_cont">${orderMap.user_name?default('')}</textarea >
            </div>
        </div>
        <div class="text_line clearfix">
            <div class="line_left">回拨电话：</div>
            <div class="line-right">
                <div class="text_cont">${orderMap.reply_mobile?default('')}</div>
            </div>
        </div>          
        <div class="text_line clearfix">
            <div class="line_left">预订单号：</div>
            <div class="line-right">
                <div class="text_cont">${orderMap.order_sn?default('')}</div>
            </div>
        </div>
        <div class="text_line clearfix">
            <div class="line_left">收货人：</div>
            <div class="line-right">
                <div class="text_cont">${orderMap.receiver?default('')}</div>
            </div>
        </div>
        <div class="text_line clearfix">
            <div class="line_left">收货人电话：</div>
            <div class="line-right">
               <div class="text_cont">${orderMap.mobile?default('')}</div>
            </div>
        </div>
        <div style="height:110px;" class="text_line clearfix">
            <div class="line_left">收货人地址：</div>
            <div>
                <textarea style="padding:5px; overflow-y:scroll;" class="area_cont">${orderMap.area_name?default('')}&nbsp;&nbsp;${orderMap.detailed_address?default('')}</textarea>
            </div>
        </div>
        <div class="text_line clearfix">
            <div class="line_left">创建时间：</div>
            <div class="line-right">
                <div class="text_cont"><#if orderMap.create_dt?exists>${orderMap.create_dt?string('yyyy-MM-dd HH:mm:ss')}</#if></div>
            </div>
        </div>  
        <div class="text_line clearfix">
            <div class="line_left">审核状态：</div>
            <div class="line-right">
                <div class="text_cont">          
                <#if orderMap.audit_status?default(-1)==0>
        	 		待审核
         		<#elseif orderMap.audit_status?default(-1)==1>
         			审核通过	
         		<#elseif orderMap.audit_status?default(-1)==2>
           	 		审核不通过
         		</#if> 
            </div>
            </div>
        </div>
        <div class="text_line clearfix">
            <div class="line_left">处理状态：</div>
            <div class="line-right">
                <div class="text_cont">          
	           <#if orderMap.order_status?default(-1)==0>
	          	 待处理
	           <#elseif orderMap.order_status?default(-1)==8>
	           	已下单
	           <#elseif orderMap.order_status?default(-1)==9>
	          	 已取消
	           </#if> 
            </div>
            </div>
        </div>      
        <div class="text_line clearfix">
            <div class="line_left">取消原因：</div>
            <div class="line-right">
               <div class="text_cont">${orderMap.cancel_remark?default('')}</div>
            </div>
        </div>
        <div class="text_line clearfix">
            <div class="line_left">完结状态：</div>
            <div class="line-right">
                <div class="text_cont">          
		       <#if orderMap.is_end?default(-1)==0>
		      	 未完结
		       <#elseif orderMap.is_end?default(-1)==1>
		       	已完结
		       </#if> 
            </div>
            </div>
        </div>         
        <div style="height:110px;" class="text_line clearfix">
            <div class="line_left">备注：</div>
            <div>
                <textarea style="padding:5px; overflow-y:scroll;" class="area_cont">${orderMap.remark?default('')}</textarea>
            </div>
        </div>
		<div class="text_line clearfix">
            <div class="line_left">订单来源：</div>
            <div class="line-right">
                <div class="text_cont">          
                <#if orderMap.source?default(-1)==1>
        	 		PC
         		<#elseif orderMap.source?default(-1)==2>
         			WAP
         		<#elseif orderMap.source?default(-1)==3>
           	 		审核不通过
         		</#if> 
            </div>
            </div>
         </div>
        <div class="text_line clearfix">
            <div class="line_left">运费：</div>
            <div class="line-right">
                <div class="text_cont">${orderMap.freight?default(0)}</div>
            </div>
        </div>    
        <div class="text_box clearfix">
            <div class="input_box">用户处方：</div>
            <div class="pic_box"><img src="${ui1}${orderMap.rx_img_url?default('')}" /></div>
        </div>
        <div class="text_foot clearfix">
            <div class="product_describe">处方药：</div>
            <div class="product_pic"><img src="${_ui1}${orderMap.abbreviation_picture?default('')}"/></div>
            <div class="product_name">
                <p class="line_height pro_name">名称：${orderMap.short_name?default('')}</p>
                <p class="line_height">规格：${orderMap.spec?default('')}</p>
            </div>
            <div class="product_number">
                <p class="line_height pro_num">个数：${orderMap.num?default('')}</p>
            </div>
            <div class="product_price">
                <p class="line_height pro_num">价格：${orderMap.good_price?default('')}</p>
            </div>
            <div class="product_number product_total">
                <p class="line_height pro_num">合计：${orderMap.order_amount?default('')}</p>
            </div>
            <input style="position:absolute; left:90px; bottom:-220px;" type="button" id="buttonsub" value="返回" class="btn05" onclick="history.go(-1)" />
        </div>
    </div>
</body>
</html>