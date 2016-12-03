
$(document).ready(function (){
var count=$("#count").val();
var status=$("#status").val();
if(status==1){
if(count!=0){
	 	$(".fonund_content").show();
}else{
	$alert("warn","未查询到符合条件的数据");
}
}
	
});



function initProvinces(provinceId,memberid){
	$.ajax({
		url:'/member/newMember!ajaxGetFirstAreas.action',
		data:{},
		type:'post',
		success:function(data){
		    if(data.length > 0){
		      var province_html="<option value=\"0\">请选择省</option>";
		      for(var i=0;i<data.length;i++){
		    	  if(provinceId == data[i].id){
		    		  province_html+="<option selected value=\""+data[i].name+"_"+data[i].id+"\">"+data[i].name+"</option>";
		    	  }else{
		    		  province_html+="<option value=\""+data[i].name+"_"+data[i].id+"\">"+data[i].name+"</option>";
		    	  }
		      }
		      $("#province_"+memberid).html(province_html);
		    }
		 }
	});
}




function showcity(memberid){
	var provinceid = $("#province_"+memberid).val();
	if(provinceid != null && provinceid != 0){
		provinceid = provinceid.split('_')[1];
		initCity(provinceid,'',memberid);
	}
}

function initCity(provinceid,cityid,memberid){
	if(provinceid != null && provinceid != 0){
    	var con ="";
    	
    	$.ajax({
			url:"/member/newMember!ajaxGetTwoAreaByAreaId.action?areaId="+provinceid,
			data:{"areaId":provinceid},
			type:'post',
			success:function(data){
			    if(data.length > 0){
			    	var html = '<option value="0">请选择城市</option>';
			    	for(var i=0 ; i<data.length ; i++){
			        	var city = data[i];
			        	if(cityid > 0 && cityid == city.id){
			        		con = "<option selected value='"+city.name+"_"+city.id+"'>"+city.name+"</option>";
			        	}else{
			        		con = "<option value='"+city.name+"_"+city.id+"'>"+city.name+"</option>";
			        	}
			       		html += con;
			    	}
			     	$("#city_"+memberid).html(html);
			    }
			  }
	   });
	}
}

function showcounty(memberid){
	var cityid = $("#city_"+memberid).val();
	if(cityid != null && cityid != 0){
		cityid = cityid.split('_')[1];
		initcounty(cityid,'',memberid);
  	}
}

function initcounty(cityid,countyid,memberid){
	if(cityid != null && cityid != 0){
		var con="";
    	$.ajax({
			url: "/member/newMember!ajaxGetTwoAreaByAreaId.action?cityid="+cityid,
			data:{"areaId":cityid},
			type:'post',
			success:function(data){
			    if(data.length > 0){
			    	var html = '<option value="0">请选择区县</option>';
			    	for(var i=0 ; i<data.length ; i++){
			        	var county = data[i];
			        	if(countyid > 0 && countyid == county.id){
			        		 con = "<option selected value='"+county.name+"_"+county.id+"'>"+county.name+"</option>";
			        	}else{
			        		con = "<option value='"+county.name+"_"+county.id+"'>"+county.name+"</option>";
			        	}
			       		html += con;
			    	}
			     	$("#county_"+memberid).html(html);
			    }
			  }
	   });
  	}
}
function checkEmail(memberid){
	var email = $("#email_"+memberid).val();
	if(email != undefined && email.length >=1&&email!=""){
		var re = /^[0-9a-z\.\-_]+@([0-9a-z\-_]*\.*)([0-9a-z\-_]+)\.[a-z\.]+$/i;
		if(!re.test(email)){
			 alert("邮箱格式不正确");
			return false;
		}
	}
	return true;
}
function checkTelephone(memberid){
	var mobile=$("#mobile_"+memberid).val();
	if(mobile != undefined && mobile.length >=1&&mobile!=""){
		var re = /^((\d{3,4})|(\d{3,4}-))?\d{7,8}$/;
		if(!re.test($("#mobile_"+memberid).val())){
			alert("请输入正确的手机号");
			$("#mobile_"+memberid).focus();
			return false;
		}
	}
	return true;
}
function checkFormData(memberid){
		var $nickName = $("#nickName_"+memberid);
		if($.trim($nickName.val()) == ''){
			alert("请填写邮箱");
			$nickName.focus();
			return false;
		}
		var $realName = $("#realName_"+memberid);
		if($.trim($realName.val()) == ''){
			alert("请填写真是姓名");
			$realName.focus();
			return false;
		}
		
		
		if(!checkEmail(memberid)){
			return false;
		}
		
		if(!checkTelephone()){
			return false;
		}
		
		return true;
	}
	
		




function saveMemberCenter(memberid){
	
	if(!checkFormData(memberid)){
		return;
	}
	var formInfo = $("#form1_"+memberid).serialize();
	$.ajax({
		url : "/member/newMember!saveMember.action",
		type : "post",
		data : formInfo,
		dataType:"json",
		success : function(data) {
			if(data == 1){
				alert("保存成功");
				$(".found_mack").hide();
                $("#"+memberid).hide();
                submitForm();
			}else{
				alert("保存失败");
			}
			//$alertCanDisplay("success","保存成功",null,null);
		},
		error : function() {
		alert("操作失败，稍后再试");
		}
	});
	
}

$(function(){
    /*合并账号按钮*/
    $(".merge_list").on("click","label",function(){
        if(!$(this).hasClass("check_box")){
            $(this).addClass("check_box");
            $(".merge_list").find("span").css({"color":"#00aaa0"});
            $(".result_list").find("label").addClass("check_selected");
            /*合并删除*/
            $(".merge_clear").on("click",function(){
                $(".result_content").hide();
                return false;
            });
        }else{
            $(this).removeClass("check_box");
            $(".merge_list").find("span").css({"color":"#d7d7d7"});
            $(".result_list").find("label").removeClass("check_selected");
        }
        return false;
    });

    /*tap切换*/
    /*初始情况下的滚动条的宽*/
    var w1=$(".result_list1").eq(0).find(".results_box").length;
    $(".result_content").width(w1*320);
    $(".fnav_list").on("click","li",function(){
        var i=$(this).index();
        $(this).find("a").css({"color":"#00aaa0"}).parent().siblings().find("a").css({"color":"#999999"});
        $(".result_list").eq(i).show().siblings(".result_list").hide();
        var widths=$(".result_list").eq(i).find(".results_box").length;
        $(".result_content").width(widths*320);
        return false;
    });
    
    
    $(".results_box").each(function(){
        /*主账号设置和编辑按钮显现*/
        $(this).on("mouseover",function(){
            $(this).find(".handle_box").show();
            /*$(this).find("b").show();*/
        });
        $(this).on("mouseout",function(){
            $(this).find(".handle_box").hide();
            /*$(this).find("b").hide();*/
        });
        /*信息栏的复选框背景变换*/
        $(this).on("click","label",function(){
            if(!$(this).hasClass("check_selected")){
                $(this).addClass("check_selected");
            }else{
                $(this).removeClass("check_selected");
            }
            return false;
        });
        /*信息栏主键的背景变换*/
        $(this).on("click","b",function(){
            if(!$(this).hasClass("set_account")){
                $(this).addClass("set_account");
                $(this).parent().next().css({"border-color":"#00aaa0"});
               /* $(this).parent().parent().siblings().find(".results_name b").css({"display":"none"});*/
            }else{
                $(this).removeClass("set_account");
                $(this).parent().next().css({"border-color":"#d7d7d7"});
               /* $(this).parent().parent().siblings().find(".results_name b").css({"display":"block"});*/
            }
            return false;
        });
        /*锁定按钮的变换*/
        $(this).on("click",".hand_over1",function(){
            var box_cover=$("<a></a>");
            box_cover.css({"background":"rgba(0,0,0,0.3)","z-index":"99","border-color":"rgba(0,0,0,0.3)","position":"absolute","top":"0","left":"0","width":"100%","height":"100%","display":"block"});
            if(!$(this).hasClass("forbidden_btn1")){
                $(this).addClass("forbidden_btn1");
                $(this).parent().parent().css({"position":"relative"});
                $(this).parent().parent().append(box_cover);
                $(this).siblings().css({"display":"none"});
                $(this).parent().parent().prev().find("b").addClass("no_use");
            }else{
                $(this).parent().parent().find("a").hide();
                $(this).removeClass("forbidden_btn1");
                $(this).siblings().css({"display":"block"});
                $(this).parent().parent().prev().find("b").removeClass("no_use");
                $(this).parent().parent().remove(box_cover);
            }
            return false;
        });
        /*删除按钮的变换*/
        $(this).on("click",".hand_over3",function(){
            $(this).parent().parent().parent().css({"display":"none"});
        });
        $(this).on("click",".msg_personal",function(){
            $(this).next().show().parent().siblings().find("div").hide();
            if($(this).find("s").hasClass("open_btn")){
                $(this).find("s").removeClass("open_btn").parent().parent().parent().siblings().find("s").addClass("open_btn");
            }
            return false;
        });
        
    })
});

/*点击查找按钮，出来查找结果*/
$(".found_nav").on("click","span",function(){
		 submitForm();
})

 function openAdd(id){
            $(".found_mack").show();
            $("#"+id).show();
            var province = $("#province-"+id).val();
			var city = $("#city-"+id).val();
			var county = $("#county-"+id).val();

if(province != ''){
initProvinces(province,id);
}
if(city != ''){
initCity(province,city,id);
}
if(county != ''){
initcounty(city,county,id);
}
            
            $(".found_mack").on("click",function(){
                $(".found_mack").hide();
                $("#"+id).hide();
            })
        
        }
function submitForm(){
var flag=false;
var mobile=$("#mobile").val();
var email=$("#email").val();
var memberid=$("#memberid").val();
var orderno=$("#orderno").val();
if(mobile!=null&&""!=mobile){
flag=true;
}
 if(email!=null&&""!=email){
flag=true;
}
 if(memberid!=null&&""!=memberid){
flag=true;
}
 if(orderno!=null&&""!=orderno){
flag=true;
}
$("#status").val(1);
if(!flag){
 alert("请填写至少一项查询条件");
 return;
}
 	$("#form1").submit();
}

   $("#form1").keydown(function(){
if(event.keyCode==13){
submitForm();
}
});
   
   
   function isuse(memberid){
		
		$.ajax({
			url : "/member/newMember!isuse.action",
			type : "post",
			data : {memberid:memberid},
			dataType:"json",
			success : function(data) {
			}
		});
		
	}
