<#assign www = "http://m.111yao.com"/>
<#assign ui = "http://ui.111yao.com"/>  
<#assign ui1 = "http://ui1.111yao.com"/> 
<link href="${base}/web/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}/web/css/new-health.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${base}/web/js/cookieUtil.js"></script>
<script type="text/javascript" src="${base}/web/js/common.new.js"></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script type="text/javascript" src="${base}/web/js/loadingbefore.js"></script>

<header class="health-header">
	<form id="inputForm" name="inputForm" method="post" enctype="multipart/form-data">
    <header class="health-header-title">
        <a href="${base}/member/profile.action" class="iconfont top-left-btn">B</a>
        <h2 class="header-title"></h2>
        <a onclick="window.location.href='${base}/';" class="iconfont top-right-btn">h</a>
    </header>
    <div class="health-header-img">
        <a href="#" class="header-img"><img id="img148"/></a>
        <a href="#" class="edit-info"><input id="head" name="head"  type="file"></a>
    </div>
    </form>
    <div class="health-header-info">
        <p id="nickname"></p>
        <p><span>秀粉</span><span id="xfcount"></span></p>
    </div>
</header>
<nav class="health-nav">
    <ul class="clearfix clear" id="tabs">
        <li><a onclick="window.location.href='${base}/leader/leader!leader.action';"><span><b class="health-center"></b>中心</span></a></li>
        <li><a onclick="window.location.href='${base}/leader/leader!list.action';"><span><b class="health-pharmacy"></b>药房</span></a></li>
        <li><a onclick="window.location.href='${base}/leader/leader!ranking.action';"><span><b class="health-ranking"></b>排行</span></a></li>
        <li><a onclick="window.location.href='${base}/leader/leader!leaderTool.action';"><span><b class="health-tools"></b>工具</span></a></li>
    </ul>
</nav>
<script type="text/javascript">
$().ready(function(){
	$("#head").change(function(){
	
		var file = this.files[0]
		
		if(file == undefined){
			return false;
		}
		var name = file.name;
    	var size = file.size;
    	var type = file.type;
    	
	    if(size > 4 *1024*1024){
	    	alert("文件不能大于4M!");
	    	return false;
	    }
	    
	    //JPG、GIF、PNG、JPEG、BMP
	    if(!(type.toLocaleUpperCase().toString().indexOf('JPG')>0
	    	||type.toLocaleUpperCase().toString().indexOf('GIF')>0
	    	||type.toLocaleUpperCase().toString().indexOf('PNG')>0
	    	||type.toLocaleUpperCase().toString().indexOf('JPEG')>0
	    	||type.toLocaleUpperCase().toString().indexOf('BMP')>0)){
	    	alert("文件格式只能是JPG、GIF、PNG、JPEG、BMP!");
	    	return false;
	    }
		
		if(typeof FileReader == 'undefined'){ 
			var imagePath=input.value;
			
			$("#img148").src= imagePath;
			
		}else{ 
			var reader = new FileReader(); 
			reader.readAsDataURL(file); 
			reader.onload = function(e){
				$("#img148").attr("src",this.result);
			}
		}
		
		var formData = new FormData($('form')[0]);
	    $.ajax({
	        url: '${base}/leader/leader!uploadHeadPortrait.action',  //server script to process data
	        type: 'POST',
	        //Ajax事件
	        beforeSend: function(){},
	        success: function(data){
	        	if(data==1 || data=="1"){
	        		location.reload();
	        	}
	        },
	        error: function(){},
	        data: formData,
	        cache: false,
	        contentType: false,
	        processData: false
	    }); 
	});
	
	//获取秀粉信息
    $.ajax(
    {
        url: "${base}/leader/leader!getinfo.action",
        type: 'POST',
        cache: false,
        success: function(data)
        {
        	if(data != null)
        	{
	        	var headurl = data.head_url;
	        	if(headurl == null)
	        	{
	        		headurl = "${ui1}/static/image/temp/20151014/b09e2b114b6779b8fe47bcd8d38fe48a.png";
	        	}
	        	var nickname = data.nick_name;
	        	if(nickname == null)
	        	{
	        		nickname = data.real_name;
	        	}
	        	var count = data.count;
	        	$("#img148").attr("src","${ui1}"+headurl);
	        	$("#nickname").html(nickname);
	        	$("#xfcount").html(count);
        	}
        }
    }); 
	
	
});
</script>