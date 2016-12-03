<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>会员管理</title> 
<#include "/WEB-INF/pages/inc/taglibs.ftl">
    <style>
        @charset"utf-8";
        a,abbr,address,article,aside,b,blockquote,body,caption,cite,code,dd,del,dfn,div,dl,dt,em,fieldset,figure,form,h1,h2,h3,h4,h5,h6,html,i,iframe,img,input,ins,kbd,label,legend,li,object,ol,p,pre,q,samp,small,span,strong,sub,sup,table,tbody,td,textarea,tfoot,th,thead,time,tr,ul,var{margin:0;padding:0;border:0;vertical-align:baseline;font-weight:400;font-size:100%}
        article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}
        audio,canvas,video{display:inline-block}
        audio:not([controls]){display:none}
        html{font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}
        body{width:100%;background:#fff;color:#000;font-size:14px;font-family:"Microsoft YaHei",Arial,Helvetica,verdana,sans-serif}
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
        input,textarea:focus{border-color:#00aaa0;}
        textarea{resize:none;-webkit-appearance:none}
        ol,ul{list-style:none}
        em{font-style:normal}
        .clearfix:after{clear:both;display:block;visibility:hidden;overflow:hidden;height:0;content:".";font-size:0}
        .fl{float:left}
        .fr{float:right}
        .clear{clear:both;zoom:1}
        html,body{width:100%; height:100%;}
        /*found_wraper*/
        .found_wraper{position:relative;}
        .found_wrap{ padding:40px auto;}
        .fond_title{padding:10px;}
        .fond_title p{height:30px; line-height:30px; background-color: #f7f7f7; border:1px solid #d7d7d7; padding-left:32px;}
        .p_btm{padding-bottom:0;}
        .fond_title1{padding:4px 10px 4px 10px;}
        .fond_title1 p{height:40px; line-height:40px; background-color: #ffece5; padding-left:32px; color:#74706f;}
        /*found_header*/
        .found_header{height:30px; line-height:30px; margin-top:16px; padding:0 10px 0 44px;}
        .found_header span{color:#000; font-size:12px; float:left;}
        .found_header input{display:inline-block; width:148px; height:30px; float:left; border:1px solid #d7d7d7; line-height:30px; text-indent: 8px; margin-right:32px; font-size:12px;}
        .found_header input:focus{border-color:#00aaa0;}
        /*found_nav*/
        .found_nav{height:25px; line-height:25px; padding:16px 10px 26px 10px;}
        .found_nav ul li{float:left; width:100px; height:25px; margin-right:10px; text-align:center; color:#ffffff; letter-spacing:6px;}
        .btn05{background:url("${base}/web/images/18btn05.jpg") no-repeat center;}
        .exclamation_sign{width:14px; height:14px; float:left; margin:13px 6px 0 0;}
        .merge_list{height:14px; line-height:14px; padding:12px 10px; cursor:pointer;}
        .no_box{display:none;}
        .merge_list label{display:block; width:14px; height:14px; float:left; margin-right:9px; background:url("${base}/web/images/wxz.png") no-repeat center;}
        .merge_list label input{display:none;}
        .merge_list label.check_box{background:url("${base}/web/images/xz.png") no-repeat center;}
        .merge_list span{margin-right:47px; color:#d7d7d7;}
        .fnav_list{height:14px; line-height:14px; padding-left:16px;}
        .fnav_list ol li{float:left;}
        .fnav_list ol li a{padding:0 17px; color:#999999;}
        .fnav_list ol li a.fnav_color{color:#00aaa0;}
        .fnav_list ol li a img{float:right; margin-top:2px;}
        .fond_result{padding:10px 10px; width:920px; height:600px; border:1px solid #f3f5f7; overflow-x: scroll; margin:15px 0 20px 10px;}
        .result_content{width:100%; height:100%;}
        .results_box{width:320px; height:314px; float:left;}
        .results_name{height:16px; line-height:16px; margin:7px 0 9px 0;}
        .results_name label{display:block; width:14px; height:14px; float:left; margin-right:10px; background:url("${base}/web/images/wxz.png") no-repeat center center;}
        .results_name label input{display:none;}
        .results_name label.check_selected{background:url("${base}/web/images/xz.png") no-repeat center center;}
        .results_name span{float:left; padding-right:10px;}
        .results_name b{display:inline-block; width:67px; height:16px; background:url("${base}/web/images/zhu.jpg") no-repeat left center; float:left;}
        .results_name b.set_account{ background:url("${base}/web/images/sw.jpg") no-repeat left center;}
        .results_name b.no_use{ background:url("${base}/web/images/yjy.jpg") no-repeat left center;}
        .results_name b{display:none;}
        .mesg_box{width:298px; border:1px solid #d7d7d7; border-top-width:2px; margin-bottom: 20px; padding-top:8px; position:relative; padding-bottom:20px;}
        .mesg_box:hover{border-color:#00aaa0;}
        .mesg_box ol li{padding:0 10px 0 10px; border-bottom:1px dashed #d7d7d7;}
        .mesg_box ol li p{height:14px; line-height: 14px;}
        .mesg_box ol li p span{display:block; width:90px; float:left; text-align:right; font-size: 12px;}
        .msg_personal{color:#222222; margin-bottom:36px; margin-top:10px; cursor:pointer;}
        .msg_personal span{font-weight:bold;}
        .msg_personal span s{display:block; width:14px; height:14px; background: url("${base}/web/images/sq.png") no-repeat center; float:right; margin-left:10px; z-index: 9; position:relative;}
        .msg_personal span s.open_btn{display:block; width:14px; height:14px; background: url("${base}/web/images/zk.png") no-repeat center; float:right; margin-left:10px;}
        .msg_name{color:#666666; margin-bottom:36px;}
        .msg_nickname{display:block; width:144px; height:14px; float:left; text-indent:25px; text-align: left;}
        .true_name{color:#666666; margin-bottom:36px;}
        .true_text{display:block; width:184px; height:14px; float:left; text-align: left; text-indent:25px;}
        .m_btm{margin-bottom: 0;}
        .true_address{padding-left:25px; display:block; width:159px; height:72px; float:left; line-height:1.2;}
        .address_btm{margin-bottom:0; padding-bottom:80px;}
        .handle_box{width:52px; height:76px; position:absolute; top:16px; right:10px; display:none; z-index: 999;}
        .handle_sign{width:50px; height:18px; background:url("${base}/web/images/jy.png") no-repeat right center; position:absolute; right:0;}
        .hand_over1{background:url("${base}/web/images/jy.png") no-repeat 35px 1px; top:0;}
        .hand_over2{background:url("${base}/web/images/bj.png") no-repeat 35px 1.5px; top:27px;}
        .hand_over3{background:url("${base}/web/images/sc.png") no-repeat 36px 1px;bottom:5px;}
        .hand_over1:hover{background:url("${base}/web/images/handle_08.jpg") no-repeat right center; top:0;}
        .hand_over2:hover{background:url("${base}/web/images/handle_03.jpg") no-repeat right center; top:27px;}
        .hand_over3:hover{background:url("${base}/web/images/handle_06.jpg") no-repeat right center; bottom:5px;}
        .hand_over1.forbidden_btn1{background:url("${base}/web/images/qy.png") no-repeat 35px 1px; top:0;}
        .hand_over1.forbidden_btn1:hover{background:url("${base}/web/images/handle.jpg") no-repeat 0 -1px; top:0;}
        /*edit*/
        .found_mack{position:fixed; top:0; left:0; width:100%; height:100%;background-color:rgba(0,0,0,0.4); z-index: 999; display:none;}
        .cover_layer{position:absolute; left:50%; margin-left:-450px; top:50px; width:900px; height:920px; background-color:#f8f8f8; z-index: 9999; display:none;}
        .found_top{padding:10px 30px; border-bottom:1px dashed #d8d8d8; overflow: hidden;}
        .person_msg{height:14px; line-height: 14px; margin-bottom:16px;}
        .person_line{height:30px; line-height:30px; margin-bottom:16px;}
        .person_line span{display:inline-block; width:80px; height:30px; text-align:right; color:#828282;}
        .person_line input{display:inline-block; width:98px; height:28px; line-height:28px;
        border:1px solid #d1d1d1; padding-left:10px; margin-left:15px;}
        .person_line input:focus{border-color:#00aaa0;}
        .person_sel{display:inline-block; width:68px; height:28px; border:1px solid #d1d1d1; margin-left:11px;}
        .ftop_left{float:left; width:266px; }
        .ftop_center{float:left; width:320px;}
        .ftop_right{float:left; width:254px;}
        .person_address{display:inline-block; width:50px; height:28px; border:1px solid #d1d1d1;}
        .p_left{margin-left:11px;}
        .txt_area{display:inline-block; width:182px; height:65px; padding:5px; border:1px solid #d1d1d1; float:left; margin-left:15px;}
        .person_add{float:left;}
        .w66{width:68px;}
        .live_conditions{display:inline-block; width:114px; height:28px; padding:5px; border:1px solid #d1d1d1; margin-left:11px;}
        .found_center{height:258px;}
        .person_area{height:65px; margin-bottom:32px;}
        .w190{width:190px;}
        .w114{width:114px;}
        .w160{width:160px;}
        .gradute_school{width:160px; float:left; padding-left:15px;}
        .found_btm{height:300px; padding:10px 30px; overflow: hidden;}
        .like_box{margin-bottom:16px;}
        .like_left{width:420px; height:72px; float:left;}
        .found_like{display:block; float:left; width:280px; height:72px; border:1px solid #d8d8d8; margin-left:5px; padding:10px;}
        .person_like{width:90px; float:left; color:#717171;}
        .like_right{width:420px; float:left;}
        .found_footer{padding-left:125px;}
        .save_btn{float:left; width:100px; height:25px; margin-right:10px; text-align:center; color:#ffffff; letter-spacing:6px; cursor: pointer;}
        .btn05{background:url("${base}/web/images/18btn05.jpg") no-repeat center;}
        .fonund_content{display:none;}
        .sel_box{padding-left:10px; margin-bottom:16px;}
        .sel_left{width:31%; float:left; color:#828282;}
        .selects_box{width:69%; float:left;}
        .selects_box label{float:left; padding:0 10px; line-height:1.5;}
        .selects_box label input{display:inline-block; float:left; margin-top:5px; padding-right:5px;}
        .selects_box label b{color:#828282;}
    </style>
    <script type="text/javascript" src="${base}/web/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
      <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
</head>
<body>
<div class="found_wraper">
    <div class="found_wrap">
     <form name="form1" id="form1"  action="${base}/member/newMember!getNewMemberList.action" method="post">
        <div class="fond_title">
            <p>查找条件</p>
        </div>
            <input type="hidden" id="count"  value="${count?default(0)}" />
            <input type="hidden" id="status" name="status" value="${status?default(0)}" />
        <div class="found_header clearfix">
            <span>手机：</span>
            <input type="text" id="mobile" name="mobile" value="${mobile?default('')}" />
            <span>邮箱：</span>
            <input type="text" id="email" name="email" value="${email?default('')}" />
            <span>用户ID：</span>
            <input type="text" id="memberid" name="memberid" value="${memberid?default('')}" />
            <span>订单号：</span>
            <input type="text" id="orderno" name="orderno" value="${orderno?default('')}" />
        </div>
        <div class="found_nav clearfix">
            <span class="save_btn btn05">查找</span>
        </div>
       </form> 
        <div class="fonund_content">
            <div class="fond_title p_btm">
                <p>查找结果</p>
            </div>
            <div class="fontd_cont">
                <div class="fond_title1">
                    <p><img class="exclamation_sign" src="${base}/web/images/gantanhao.png"/>您好，根据查找条件${message?default('')}，共查找到${count?default(0)} 个符合条件的帐号</p>
                </div>
                <div class="merge_list">
                    <label for="ch_select"><input class="ch_select" id="ch_select" name="merge_select" type="checkbox"/></label><span>合并账号</span><span class="merge_clear">清除</span>
                </div>
                <div class="fnav_list">
                    <ol>
                        <li><a class="fnav_color" href="">基本资料<img width="1" height="10" src="${base}/web/images/linebg.jpg"/></a></li>
                        <li><a href="">兴趣爱好<img width="1" height="10" src="${base}/web/images/linebg.jpg"/></a></li>
                        <li><a href="">个人信息</a></li>
                    </ol>
                </div>
                <div class="fond_result">
                    <div class="result_content">
                        <div class="result_list result_list1">
                        
                        <#list memberList?if_exists as resc>
                            <div class="results_box">
                                <div class="results_name">
                                    <label for="check_result1"><input id="check_result1" class="ch_select" type="checkbox"/></label>
                                    <span>结果${resc_index+1}</span><b></b>
                                </div>
                                <div class="mesg_box" <#if resc.status?default(0)==1>style="position:relative"</#if> >
                                <#if resc.status?default(0)==1><a style="background:rgba(0,0,0,0.3);z-index:99;border-color:rgba(0,0,0,0.3);position:absolute;top:0;left:0;width:100%;height:100%;display:block;"></a></#if>
                                    <div class="handle_box">
                                        <div class="handle_sign hand_over1 <#if resc.status?default(0)==1>forbidden_btn1</#if>" onclick="isuse(${resc.id?default()});"></div>
                                        <div class="handle_sign hand_over2" onclick="openAdd(${resc.id?default()});" <#if resc.status?default(0)==1>style="display:none;"</#if>></div>
                                        <div class="handle_sign hand_over3" <#if resc.status?default(0)==1>style="display:none;"</#if>></div>
                                    </div>
                                    <ol>
                                        <li>
                                            <p class="msg_personal"><span>个人信息<s></s></span></p>
                                            <div class="select_list">
                                                <p class="msg_name"><span>昵称：</span><strong class="msg_nickname">${resc.nick_name?default('')}</strong></p>
                                                <p class="true_name"><span>真实姓名：</span><strong class="true_text">${resc.real_name?default('')}</strong></p>
                                                <p class="true_name"><span>性别：</span><strong class="true_text"><#if resc.sex?exists><#if resc.sex?default(1)==2>女<#else>男</#if></#if></strong></p>
                                                <p class="true_name"><span>生日：</span><strong class="true_text"><#if resc.birthday?exists>${resc.birthday?string('yyyy-MM-dd')} </#if></strong></p>
                                                <p class="true_name"><span>身份：</span><strong class="true_text"><#if resc.identity?exists>
                                                <#if resc.identity?default(0)==0>
                                                	学生
                                                <#elseif resc.identity?default(0)==1>
                                                	在职
                                                <#elseif resc.identity?default(0)==2>
                                                	自由职业
                                                <#elseif resc.identity?default(0)==3>
                                                	家庭主妇
                                                <#elseif resc.identity?default(0)==4>
                                                	退休
                                                </#if>
                                                </#if></strong></p>
                                            </div>
                                        </li>
                                        <li>
                                            <p class="msg_personal"><span>通讯方式<s class="open_btn"></s></span></p>
                                            <div class="select_list no_box">
                                                <p class="true_name"><span>手机：</span><strong class="msg_nickname">${resc.mobile?default('')}</strong></p>
                                                <p class="true_name"><span>邮箱：</span><strong class="msg_nickname">${resc.email?default('')}</strong></p>
                                                <p class="true_name"><span>居住地：</span><strong class="true_text">${resc.area?default('')}</strong></p>
                                                <p class="true_name address_btm"><span>地址：</span><strong class="true_address">${resc.address?default('')}</strong></p>
                                            </div>
                                        </li>
                                        <li>
                                            <p class="msg_personal"><span>家庭情况<s class="open_btn"></s></span></p>
                                            <div class="select_list no_box">
                                                <p class="true_name"><span>婚姻状况：</span><strong class="msg_nickname">
                                                <#if resc.is_marry?exists>
                                                <#if resc.is_marry?default(2)==0>
                                               		未婚
                                                <#elseif resc.is_marry?default(2)==1>
                                                	已婚
                                                <#else>
                                                	保密
                                                </#if>
                                                </#if></strong></p>
                                                <p class="true_name address_btm"><span>居住状况：</span><strong class="true_address">
                                                <#list resc.liveStatus?if_exists as live>
                                                <#if live.live?default('0')=='0'>
                                               		保密
                                                <#elseif live.live?default('0')=='1'>
                                                	和伴侣
                                                <#elseif live.live?default('0')=='2'>
                                                	和室友
                                                <#elseif live.live?default('0')=='3'>
                                                	和父母
                                                <#else>
                                                	保密
                                                </#if>
                                                </#list>
												</strong></p>
                                                <p class="true_name"><span>家庭成员：</span><strong class="true_text">
                                                 <#if resc.family_member?exists>
                                                <#if resc.family_member?default(1)==1>
                                               		1个人
                                                <#elseif resc.family_member?default(1)==2>
                                                	2个人
                                                <#elseif resc.family_member?default(1)==3>
                                                	3个人
                                                <#elseif resc.family_member?default(1)==4>
                                                	4个人以上	
                                                <#else>
                                                	保密
                                                </#if>
                                                </#if>
                                                </strong></p>
                                                <p class="true_name address_btm"><span>小孩年龄：</span><strong class="true_address">
                                                
                                                   <#list resc.childAge?if_exists as age>
                                                <#if age.child?default('0')=='0'>
                                               		无
                                                <#elseif age.child?default('0')=='1'>
                                                	（0-3岁）
                                                <#elseif age.child?default('0')=='2'>
                                                	（4-6岁）
                                                <#elseif age.child?default('0')=='3'>
                                                	（7-12岁）
                                                <#elseif age.child?default('0')=='4'>
                                                	（13-18岁）
                                                <#elseif age.child?default('0')=='5'>
                                                	（18岁以上）
                                                <#else>
                                                	保密
                                                </#if>
                                                </#list>
                                                </strong></p>
                                            </div>
                                        </li>
                                        <li>
                                            <p class="msg_personal"><span>家庭资产<s class="open_btn"></s></span></p>
                                            <div class="select_list no_box">
                                                <p class="true_name"><span>个人收入状况：</span><strong class="msg_nickname">
                                                <#if resc.personal_income?exists>
                                                <#if resc.personal_income?default(1)==1>
                                               		2000元及以下
                                                <#elseif resc.personal_income?default(1)==2>
                                                	2000-5000元
                                                <#elseif resc.personal_income?default(1)==3>
                                                	5000-10000元
                                                <#elseif resc.personal_income?default(1)==4>
                                                	10000-20000元
                                                <#elseif resc.personal_income?default(1)==5>
                                                	20000-40000元
                                                <#elseif resc.personal_income?default(1)==6>
                                                	40000元以上
                                                <#else>
                                                	保密
                                                </#if>
                                                </#if>
                                                </strong></p>
                                                <p class="true_name"><span>是否有车：</span><strong class="msg_nickname">
                                                 <#if resc.is_cart?exists>
                                                <#if resc.is_cart?default(0)==1>
                                               		是
                                                <#else>
                                                	否
                                                </#if>
                                                </#if>
                                                </strong></p>
                                            </div>
                                        </li>
                                    </ol>
                                </div>
                            </div>
                           </#list> 
                        </div>
                        
                        
                        
                        <div class="result_list no_box">
                        <#list memberList?if_exists as resc>
                            <div class="results_box">
                                <div class="results_name">
                                    <label for="check_result1"><input id="check_result1" class="ch_select" type="checkbox"/></label>
                                    <span>结果${resc_index+1}</span><b></b>
                                </div>
                                  <div class="mesg_box" <#if resc.status?default(0)==1>style="position:relative"</#if> >
                                <#if resc.status?default(0)==1><a style="background:rgba(0,0,0,0.3);z-index:99;border-color:rgba(0,0,0,0.3);position:absolute;top:0;left:0;width:100%;height:100%;display:block;"></a></#if>
                                    <div class="handle_box">
                                        <div class="handle_sign hand_over1 <#if resc.status?default(0)==1>forbidden_btn1</#if>" onclick="isuse(${resc.id?default()});"></div>
                                        <div class="handle_sign hand_over2" onclick="openAdd(${resc.id?default()});" <#if resc.status?default(0)==1>style="display:none;"</#if>></div>
                                        <div class="handle_sign hand_over3" <#if resc.status?default(0)==1>style="display:none;"</#if>></div>
                                    </div>
                                    <ol>
                                        <li>
                                            <p class="msg_personal"><span>兴趣爱好<s></s></span></p>
                                            <div class="select_list">
                                                <p class="msg_name"><span>购物喜好：</span><strong class="msg_nickname">
	 											 <#if resc.shopping_like?exists>
                                                <#if resc.shopping_like?default('-1')=='0'>
                                               		食品/饮料
                                                <#elseif resc.shopping_like?default('-1')=='1'>
                                                	美容/护理
                                                <#elseif resc.shopping_like?default('-1')=='2'>
                                                	厨卫/清洁
                                                <#elseif resc.shopping_like?default('-1')=='3'>
                                                	母婴/玩具 
                                                <#elseif resc.shopping_like?default('-1')=='4'>
                                                	家居/家具
                                                <#elseif resc.shopping_like?default('-1')=='5'>
                                                	家电/数码
                                                <#elseif resc.shopping_like?default('-1')=='6'>
                                                	服装/箱包
                                                <#elseif resc.shopping_like?default('-1')=='7'>
                                                	运动/户外
                                                <#elseif resc.shopping_like?default('-1')=='8'>
                                                	文具/影音
                                                <#elseif resc.shopping_like?default('-1')=='9'>
                                                	建材/装修
                                                <#elseif resc.shopping_like?default('-1')=='10'>
                                                	汽车/护养
                                                <#elseif resc.shopping_like?default('-1')=='11'>
                                                	进口/食品
                                                <#elseif resc.shopping_like?default('-1')=='12'>
                                                	营养保健/健身器械
                                                <#elseif resc.shopping_like?default('-1')=='13'>
                                                	机票/手机充值/健康服务
                                                <#else>
                                                	保密
                                                </#if>
                                                </#if>

												</strong></p>
                                                <p class="true_name address_btm"><span>购物类型：</span><strong class="true_address">
                                                      <#list resc.shop?if_exists as shop>
                                                <#if shop.shop?default('-1')=='0'>
                                               		购新奇
                                                <#elseif shop.shop?default('-1')=='1'>
                                                	购快捷
                                                <#elseif shop.shop?default('-1')=='2'>
                                                	购安全
                                                <#elseif shop.shop?default('-1')=='3'>
                                                	购低价
                                                <#else>
                                                	保密
                                                </#if>
                                                </#list>
                                                
                                                </strong></p>
                                                <p class="true_name address_btm"><span>喜爱的品牌：</span><strong class="true_address">${resc.like_brand?default('')}</strong></p>
                                                <p class="true_name address_btm"><span>喜爱的明星：</span><strong class="true_address">${resc.like_star?default('')}</strong></p>
                                                <p class="true_name address_btm"><span>喜爱的电影：</span><strong class="true_address">${resc.like_film?default('')}</strong></p>
                                                <p class="true_name address_btm"><span>喜爱的人物：</span><strong class="true_address">${resc.like_character?default('')}</strong></p>
                                            </div>
                                        </li>
                                    </ol>
                                </div>
                            </div>
                         </#list>   
                        </div>
                        <div class="result_list no_box">
                         <#list memberList?if_exists as resc>
                            <div class="results_box">
                                <div class="results_name">
                                    <label for="check_result1"><input id="check_result1" class="ch_select" type="checkbox"/></label>
                                    <span>结果${resc_index+1}</span><b></b>
                                </div>
                                  <div class="mesg_box" <#if resc.status?default(0)==1>style="position:relative"</#if> >
                                <#if resc.status?default(0)==1><a style="background:rgba(0,0,0,0.3);z-index:99;border-color:rgba(0,0,0,0.3);position:absolute;top:0;left:0;width:100%;height:100%;display:block;"></a></#if>
                                    <div class="handle_box">
                                        <div class="handle_sign hand_over1 <#if resc.status?default(0)==1>forbidden_btn1</#if>" onclick="isuse(${resc.id?default()});"></div>
                                        <div class="handle_sign hand_over2" onclick="openAdd(${resc.id?default()});" <#if resc.status?default(0)==1>style="display:none;"</#if>></div>
                                        <div class="handle_sign hand_over3" <#if resc.status?default(0)==1>style="display:none;"</#if>></div>
                                    </div>
                                    <ol>
                                        <li>
                                            <p class="msg_personal"><span>工作情况<s></s></span></p>
                                            <div class="select_list">
                                                <p class="true_name address_btm"><span>工作单位：</span><strong class="true_address">${resc.company_name?default('')}</strong></p>
                                                <p class="true_name"><span>行业：</span><strong class="true_text">
													<#if resc.industry?exists>
                                                <#if resc.industry?default(-1)==0>
                                               		计算机/互联网/通讯/电子
                                                <#elseif resc.industry?default(-1)==1>
                                                	会计/金融/银行/保险
                                                <#elseif resc.industry?default(-1)==2>
                                                	贸易/消费/制造/运营
                                                <#elseif resc.industry?default(-1)==3>
                                                	制药/医疗
                                                <#elseif resc.industry?default(-1)==4>
                                                	广告/媒体
                                                <#elseif resc.industry?default(-1)==5>
                                                	房地产/建筑
                                                <#elseif resc.industry?default(-1)==6>
                                                	专业服务/教育/培训
                                                <#elseif resc.industry?default(-1)==7>
                                                	服务业
                                                <#elseif resc.industry?default(-1)==8>
                                                	物流/运输
                                                <#elseif resc.industry?default(-1)==9>
                                                	能源/原材料
                                                <#elseif resc.industry?default(-1)==10>
                                                	政府/非盈利机构/其他
                                                <#elseif resc.industry?default(-1)==11>
                                                	退休
                                                <#else>
                                                	保密
                                                </#if>
                                                </#if>
                                                </strong></p>
                                                <p class="true_name"><span>职位：</span><strong class="true_text">${resc.position?default('')}</strong></p>
                                                <p class="true_name"><span>工作年限：</span><strong class="true_text">
                                                			<#if resc.work_year?exists>
                                                <#if resc.work_year?default(-1)==1>
                                               		1年
                                                <#elseif resc.work_year?default(-1)==2>
                                                	2年
                                                <#elseif resc.work_year?default(-1)==3>
                                                	3年
                                                <#elseif resc.work_year?default(-1)==4>
                                                	4年以上
                                                <#else>
                                                	保密
                                                </#if>
                                                </#if>
                                                </strong></p>
                                            </div>
                                        </li>
                                        <li>
                                            <p class="msg_personal"><span>教育情况<s class="open_btn"></s></span></p>
                                            <div class="select_list no_box">
                                                <p class="true_name"><span>教育程度：</span><strong class="msg_nickname">
                                                			<#if resc.diploma?exists>
                                                <#if resc.diploma?default(-1)==0>
                                               		高中以下
                                                <#elseif resc.diploma?default(-1)==1>
                                               		中专
                                                <#elseif resc.diploma?default(-1)==2>
                                                	大专
                                                <#elseif resc.diploma?default(-1)==3>
                                                	本科
                                                <#elseif resc.diploma?default(-1)==4>
                                                	硕士
                                                <#elseif resc.diploma?default(-1)==5>
                                                	博士
                                                <#else>
                                                	保密
                                                </#if>
                                                </#if>
                                                </strong></p>
                                                <p class="true_name address_btm"><span>毕业学校：</span><strong class="true_address">${resc.graduation_school?default('')}</strong></p>
                                                <p class="true_name"><span>毕业时间：</span><strong class="true_text">${resc.graduation_dt?default('')}</strong></p>
                                            </div>
                                        </li>
                                    </ol>
                                </div>
                            </div>
                          </#list>  
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="found_mack"></div>
<#list memberList?if_exists as resc>
<div class="cover_layer" id="${resc.id?default()}">
<form name="form1_${resc.id?default()}" id="form1_${resc.id?default()}"  action="${base}/member/newMember!getNewMemberList.action" method="post">
    <div class="found_top clearfix">
        <div class="ftop_left">
            <div class="person_msg">个人信息</div>
            <div class="person_line">
                <span>昵称：</span><input class="person_name" id="nickName_${resc.id?default()}" name="base.nickName" type="input" value="${resc.nick_name?default('')}"/>
            </div>
            <div class="person_line">
                <span>真实姓名：</span><input class="person_name" id="realName_${resc.id?default()}" name="base.realName" type="input" value="${resc.real_name?default('')}"/>
            </div>
            <div class="person_line">
                <span>性别：</span>
                <select class="person_sel" name="base.sex">
                    <option <#if resc.sex?exists><#if resc.sex?default(1)!=2>selected</#if><#else>selected</#if> value="1">男</option>
                    <option <#if resc.sex?exists><#if resc.sex?default(1)==2>selected</#if></#if> value="2">女</option>
                </select>
            </div>
            <div class="person_line">
                <span>生日：</span><input  type="text" class="person_name"  value="<#if resc.birthday?exists>${resc.birthday?string('yyyy-MM-dd')} </#if>" id="birthday_${resc.id?default()}" name="base.birthday" readOnly="readOnly"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
            </div>
            <div class="person_line">
                <span>身份：</span>
                <select class="person_sel" name="base.identity">
                    <option  <#if resc.identity?default(0)==0> selected</#if> value="0">学生</option>
                    <option  <#if resc.identity?default(0)==1> selected</#if> value="1">在职</option>
                    <option  <#if resc.identity?default(0)==2> selected</#if> value="2">自由职业</option>
                    <option  <#if resc.identity?default(0)==3> selected</#if> value="3">家庭主妇</option>
                    <option  <#if resc.identity?default(0)==4> selected</#if> value="4">退休</option>
                </select>
            </div>
        </div>
        <div class="ftop_center">
            <div class="person_msg">通讯方式</div>
            <input type="hidden" id="province-${resc.id?default()}" value="${resc.province?default(0)}"/>
            <input type="hidden" id="city-${resc.id?default()}" value="${resc.city?default(0)}"/>
            <input type="hidden" id="county-${resc.id?default()}" value="${resc.county?default(0)}"/>
            <input type="hidden" name="base.memberId" value="${resc.id?default()}"/>
            <input type="hidden" name="tmember.id" value="${resc.id?default()}"/>
            <input type="hidden" name="hobby.memberId" value="${resc.id?default()}"/>
            <div class="person_line">
                <span>手机：</span><input class="person_name" type="input" id="mobile_${resc.id?default()}" name="tmember.mobile" value="${resc.mobile?default('')}"/>
            </div>
            <div class="person_line">
                <span>邮箱：</span><input class="person_name" type="input" id="email_${resc.id?default()}" name="tmember.email" value="${resc.email?default('')}"/>
            </div>
            <div class="person_line">
                <span>居住地：</span>
                <select class="person_address p_left" id="province_${resc.id?default()}" name="province"  onchange="showcity(${resc.id?default()});">
                    <option value="0">
							                           请选择省
							    </option>
                </select>
                <b>省</b>
                <select class="person_address" id="city_${resc.id?default()}" name="city" onchange="showcounty(${resc.id?default()})">
                  <option value="0">
									请选择城市
								</option>
                </select>
                <b>市</b>
                <select class="person_address"  id="county_${resc.id?default()}" name="county">
                    <option value="0">
									请选择区县
								</option>
                </select>
                <b>区</b>
            </div>
            <div class="person_line">
                <span class="person_add">地址：</span><textarea name="base.address" id="address_${resc.id?default()}" class="txt_area">${resc.address?default('')}</textarea>
            </div>
        </div>
        <div class="ftop_right">
            <div class="person_msg">家庭情况</div>
            <div class="person_line">
                <span>婚姻状况：</span>
                <select class="live_conditions" name="base.isMarry">
                    <option <#if resc.is_marry?default(0)==0> selected</#if> value="0">未婚</option>
                    <option <#if resc.is_marry?default(0)==1> selected</#if> value="1">已婚</option>
                    <option <#if resc.is_marry?default(0)==2> selected</#if> value="2">保密</option>
                </select>
            </div>
            <div class="sel_box clearfix">
                <div class="sel_left">居住状况：</div>
                <div class="selects_box">
                	<label><input  <#if resc.live_status?exists><#if resc.live_status?index_of(',0,') gte 0 > checked</#if></#if> type="checkbox" name="liveStatus" value="0"/><b>保密</b></label>  
                	<label><input  <#if resc.live_status?exists><#if resc.live_status?index_of(',1,') gte 0 > checked</#if></#if> type="checkbox" name="liveStatus" value="1"/><b>和伴侣</b></label> 
                	<label><input  <#if resc.live_status?exists><#if resc.live_status?index_of(',2,') gte 0 > checked</#if></#if> type="checkbox" name="liveStatus" value="2"/><b>室友 </b></label> 
                	<label><input  <#if resc.live_status?exists><#if resc.live_status?index_of(',3,') gte 0 > checked</#if></#if> type="checkbox" name="liveStatus" value="3"/><b>父母 </b></label>  
                </div> 
            </div>
            <div class="person_line">
                <span>家庭成员：</span>
                <select class="person_sel w66" name="base.familyMember">
                       <option <#if resc.family_member?default(1)==1> selected</#if> value="1">1个人</option>
                       <option <#if resc.family_member?default(1)==2> selected</#if> value="2">2个人</option>
                       <option <#if resc.family_member?default(1)==3> selected</#if> value="3">3个人</option>
                       <option <#if resc.family_member?default(1)==4> selected</#if> value="4">4个人以上</option>
                </select>
            </div>
           <div class="sel_box clearfix">
                <div class="sel_left">小孩年龄：</div>
                <div class="selects_box">
                	<label><input <#if resc.child_age?exists><#if resc.child_age?index_of(',0,') gte 0 > checked</#if></#if> type="checkbox" name="childAge" value="0"/><b>无</b></label>  
                	<label><input <#if resc.child_age?exists><#if resc.child_age?index_of(',1,') gte 0 > checked</#if></#if> type="checkbox" name="childAge" value="1"/><b>0-3岁</b></label> 
                	<label><input <#if resc.child_age?exists><#if resc.child_age?index_of(',2,') gte 0 > checked</#if></#if> type="checkbox" name="childAge" value="2"/><b>4-6岁 </b></label> 
                	<label><input <#if resc.child_age?exists><#if resc.child_age?index_of(',3,') gte 0 > checked</#if></#if> type="checkbox" name="childAge" value="3"/><b>7-12岁 </b></label>  
                	<label><input <#if resc.child_age?exists><#if resc.child_age?index_of(',4,') gte 0 > checked</#if></#if> type="checkbox" name="childAge" value="4"/><b>13-18岁 </b></label>  
                	<label><input <#if resc.child_age?exists> <#if resc.child_age?index_of(',5,') gte 0 > checked</#if></#if> type="checkbox" name="childAge" value="5"/><b>18岁以上 </b></label>  
                </div> 
            </div>
        </div>
    </div>
    <div class="found_top found_center clearfix">
        <div class="ftop_left">
            <div class="person_msg">家庭资产</div>
            <div class="person_line">
            <span>个人收入：</span>
                <select class="live_conditions" name="base.personalIncome">
                    <option <#if resc.personal_income?default(1)==1> selected</#if> value="1">2000元及以下</option>
                    <option <#if resc.personal_income?default(1)==2> selected</#if> value="2">2000-5000元包含5000元</option>
                    <option <#if resc.personal_income?default(1)==3> selected</#if> value="3">5000-10000元包含10000元</option>
                    <option <#if resc.personal_income?default(1)==4> selected</#if> value="4">10000-20000元包含20000元</option>
                    <option <#if resc.personal_income?default(1)==5> selected</#if> value="5">20000-40000元 包含40000元</option>
                    <option <#if resc.personal_income?default(1)==6> selected</#if> value="6">40000元以上</option>
                </select>
            </div>
            <div class="person_line">
                <span>是否有车：</span>
                <select class="person_sel" name="base.isCart">
                    <option <#if resc.is_cart?default(0)==0> selected</#if> value="0">否</option>
                    <option <#if resc.is_cart?default(0)==1> selected</#if> value="1">是</option>
                </select>
            </div>
        </div>
        <div class="ftop_center">
            <div class="person_msg">工作情况</div>
            <div class="person_line person_area clearfix">
                <span class="person_add">单位名称：</span>
                <textarea class="txt_area w190" id="companyName_${resc.id?default()}" name="base.companyName">${resc.company_name?default('')}</textarea>
            </div>
            <div class="person_line clearfix">
                <span>行业：</span>
                <select class="live_conditions w190" name="base.industry">
                   <option <#if resc.industry?default(0)==0> selected</#if> value="0">计算机/互联网/通讯/电子</option>
                   <option <#if resc.industry?default(0)==1> selected</#if> value="1">会计/金融/银行/保险</option>
                   <option <#if resc.industry?default(0)==2> selected</#if> value="2">贸易/消费/制造/运营</option>
                   <option <#if resc.industry?default(0)==3> selected</#if> value="3">制药/医疗</option>
                   <option <#if resc.industry?default(0)==4> selected</#if> value="4">广告/媒体</option>
                   <option <#if resc.industry?default(0)==5> selected</#if> value="5">房地产/建筑</option>
                   <option <#if resc.industry?default(0)==6> selected</#if> value="6">专业服务/教育/培训</option>
                   <option <#if resc.industry?default(0)==7> selected</#if> value="7">服务业</option>
                   <option <#if resc.industry?default(0)==8> selected</#if> value="8">物流/运输</option>
                   <option <#if resc.industry?default(0)==9> selected</#if> value="9">能源/原材料</option>
                   <option <#if resc.industry?default(0)==10> selected</#if> value="10">政府/非盈利机构/其他</option>
                   <option <#if resc.industry?default(0)==11> selected</#if> value="11">退休</option>
                </select>
            </div>
            <div class="person_line clearfix">
                <span>职位：</span>
                <input class="person_name" name="base.position" id="position_${resc.id?default()}" type="input" value="${resc.position?default('')}"/>
            </div>
            <div class="person_line clearfix">
                <span>工作年限：</span>
				<select class="person_sel" name="base.workYear">
                    <option <#if resc.work_year?default(1)==1> selected</#if> value="1">1年</option>
                    <option <#if resc.work_year?default(1)==2> selected</#if> value="2">2年</option>
                    <option <#if resc.work_year?default(1)==3> selected</#if> value="3">3年</option>
                    <option <#if resc.work_year?default(1)==4> selected</#if> value="4">4年以上</option>
                </select>
				
            </div>
        </div>
        <div class="ftop_right">
            <div class="person_msg">教育情况</div>
            <div class="person_line">
                <span>教育程度：</span>
                <select class="live_conditions" name="base.diploma">
                     <option <#if resc.diploma?default(0)==0> selected</#if> value="0">高中以下</option>
                     <option <#if resc.diploma?default(0)==1> selected</#if> value="1">中专</option>
                     <option <#if resc.diploma?default(0)==2> selected</#if> value="2">大专</option>
                     <option <#if resc.diploma?default(0)==3> selected</#if> value="3">本科</option>
                     <option <#if resc.diploma?default(0)==4> selected</#if> value="4">硕士</option>
                     <option <#if resc.diploma?default(0)==5> selected</#if> value="5">博士</option>
                </select>
            </div>
            <div class="person_line">
                <span class="person_add">毕业学校：</span>
                <input class="gradute_school w160" name="base.graduationSchool" id="graduationSchool_${resc.id?default()}" type="text" value="${resc.graduation_school?default('')}"/>
            </div>
            <div class="person_line">
                <span>毕业时间：</span><input  type="text" class="person_name"  value="${resc.graduation_dt?default('')}" id="graduationDt_${resc.id?default()}" name="base.graduationDt" readOnly="readOnly"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
            </div>
        </div>
    </div>
    <div class="found_btm">
        <div>
            <div class="person_line">
                <span>购物喜好：</span>
                  <select class="live_conditions" name="hobby.shoppingLike">
                     <option <#if resc.shopping_like?default('0')=='0'> selected</#if> value="0">食品/饮料</option>
                     <option <#if resc.shopping_like?default('0')=='1'> selected</#if> value="1">美容/护理</option>
                     <option <#if resc.shopping_like?default('0')=='2'> selected</#if> value="2">厨卫/清洁</option>
                     <option <#if resc.shopping_like?default('0')=='3'> selected</#if> value="3">母婴/玩具</option>
                     <option <#if resc.shopping_like?default('0')=='4'> selected</#if> value="4">家居/家具</option>
                     <option <#if resc.shopping_like?default('0')=='5'> selected</#if> value="5">家电/数码</option>
                     <option <#if resc.shopping_like?default('0')=='6'> selected</#if> value="6">服装/箱包</option>
                     <option <#if resc.shopping_like?default('0')=='7'> selected</#if> value="7">运动/户外</option>
                     <option <#if resc.shopping_like?default('0')=='8'> selected</#if> value="8">文具/影音</option>
                     <option <#if resc.shopping_like?default('0')=='9'> selected</#if> value="9">建材/装修</option>
                     <option <#if resc.shopping_like?default('0')=='10'> selected</#if> value="10">汽车/护养</option>
                     <option <#if resc.shopping_like?default('0')=='11'> selected</#if> value="11">进口/食品</option>
                     <option <#if resc.shopping_like?default('0')=='12'> selected</#if> value="12">营养保健/健身器械</option>
                     <option <#if resc.shopping_like?default('0')=='13'> selected</#if> value="13">机票/手机充值/健康服务</option>
                </select>
                
            </div>
              <div class="sel_box clearfix">
                <div class="sel_left">购物偏好：</div>
                <div class="selects_box">
                	<label><input  <#if resc.shopping_preference?exists><#if resc.shopping_preference?index_of(',0,') gte 0 > checked</#if></#if> type="checkbox" name="shoppingPreference" value="0"/><b>购新奇</b></label>  
                	<label><input  <#if resc.shopping_preference?exists><#if resc.shopping_preference?index_of(',1,') gte 0 > checked</#if></#if> type="checkbox" name="shoppingPreference" value="1"/><b>购快捷</b></label> 
                	<label><input  <#if resc.shopping_preference?exists><#if resc.shopping_preference?index_of(',2,') gte 0 > checked</#if></#if> type="checkbox" name="shoppingPreference" value="2"/><b>购安全 </b></label> 
                	<label><input  <#if resc.shopping_preference?exists><#if resc.shopping_preference?index_of(',3,') gte 0 > checked</#if></#if> type="checkbox" name="shoppingPreference" value="3"/><b>购低价 </b></label>  
                </div> 
            </div>
            <div class="like_box clearfix">
             <div class="like_left">
                    <span class="person_like">喜爱的电影：</span>
                    <textarea class="found_like" name="hobby.likeFilm" id="likeFilm_${resc.id?default()}">${resc.like_film?default('')}</textarea>
                </div>
                <div class="like_right">
                    <span class="person_like">喜爱的品牌：</span>
                    <textarea class="found_like" name="hobby.likeBrand" id="likeBrand_${resc.id?default()}" >${resc.like_brand?default('')}</textarea>
                </div>
            </div>
            <div class="like_box clearfix">
                <div class="like_left">
                    <span class="person_like">喜爱的明星：</span>
                    <textarea class="found_like" name="hobby.likeStar" id="likeStar_${resc.id?default()}">${resc.like_star?default('')}</textarea>
                </div>
                <div class="like_right">
                    <span class="person_like">喜爱的人物：</span>
                    <textarea class="found_like" name="hobby.likeCharacter" id="likeCharacter_${resc.id?default()}">${resc.like_character?default('')}</textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="found_footer clearfix">
        <input type="button" class="save_btn btn05" onclick="saveMemberCenter(${resc.id?default()});" value="保存">
    </div>
    </form>
</div>
</#list>
</body>
<script type="text/javascript" src="${base}/web/js/memberList.js"></script>
</html>
