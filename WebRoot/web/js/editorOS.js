function showloading()
{
  	$(".loading-ui").show();
  	$(".mask-ui").show();
}

function hideloading()
{
	$(".loading-ui").hide();
	$(".mask-ui").hide();
}

function launchFullscreen(a) {
        if (a.requestFullscreen) {
            a.requestFullscreen()
        } else {
            if (a.mozRequestFullScreen) {
                a.mozRequestFullScreen()
            } else {
                if (a.msRequestFullscreen) {
                    a.msRequestFullscreen()
                } else {
                    if (a.webkitRequestFullscreen) {
                        a.webkitRequestFullScreen()
                    }
                }
            }
        }
    };
    function exitFullscreen() {
        if (document.exitFullscreen) {
            document.exitFullscreen()
        } else {
            if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen()
            } else {
                if (document.webkitExitFullscreen) {
                    document.webkitExitFullscreen()
                }
            }
        }
    };
    function fullscreenElement() {
        return document.fullscreenElement || document.webkitCurrentFullScreenElement || document.mozFullScreenElement || null
    };
    $(function() {
        $("#phoneclose").live('click',
        function() {
            $("#previewbox").hide()
        });
        $("#phone").live('click',
        function() {
            if ($("#previewbox").css("display") == "block") {
                $("#previewbox").hide();
            } else {
                $("#previewbox").show();
            }
        });
        $(window).live('fullscreenchange webkitfullscreenchange mozfullscreenchange',
        function() {
            if (!fullscreenElement()) {
                $('.wxeditor').css({
                    margin: '0'
                });
            }
        });
        $('.fullshowbox').live('click',
        function() {
            $('.wxeditor').css({
                margin: '50px 0'
            });
            launchFullscreen(document.documentElement)
        });
        $('.fullhidebox').live('click',
        function() {
            $('#wxeditortip,#header').show();
            exitFullscreen()
        });
        var b = ["borderTopColor", "borderRightColor", "borderBottomColor", "borderLeftColor"],
        d = [];
        $.each(b,
        function(a) {
            d.push(".itembox .wxqq-" + b[a])
        });
        $("#colorpickerbox").ColorPicker({
            flat: true,
            color: "#00bbec",
            onChange: function(a, e, f) {
                $(".itembox .wxqq-bg").css({
                    backgroundColor: "#" + e
                });
                $(".itembox .wxqq-color").css({
                    color: "#" + e
                });
                $.each(d,
                function(g) {
                    $(d[g]).css(b[g], "#" + e)
                })
            }
        });
        var c = UE.getEditor("editor", {
            topOffset: 0,
            autoFloatEnabled: false,
            autoHeightEnabled: false,
            autotypeset: {
                removeEmptyline: true
            },
        });
        c.ready(function() {
            c.addListener('contentChange',
            function() {
                $("#preview").html(c.getContent() + '<div><a style="font-size:12px;color:#607fa6" href="javascript:void(0);" id="post-user">阅读原文</a> <em style="color:#8c8c8c;font-style:normal;font-size:12px;">阅读 100000+</em><span class="fr"><a style="font-size:12px;color:#607fa6" href="javascript:void(0);">举报</a></span></div>');
            });
            $(".itembox").live("click",
            function(a)
             {
  			    var content = ues.getContent();
  			    var f = $(this).html();
				if(content.indexOf("goods_img_p_1") >= 0 && f.indexOf("goods_img_p_1") >= 0)
				{
					$alert("warn","编辑器中已存在商品一！")
					return false;
				}
				if(content.indexOf("goods_img_p_2") >= 0 && f.indexOf("goods_img_p_2") >= 0)
				{
					$alert("warn","编辑器中已存在商品二！")
					return false;
				}
				if(content.indexOf("goods_img_p_3") >= 0 && f.indexOf("goods_img_p_3") >= 0)
				{
					$alert("warn","编辑器中已存在商品三！")
					return false;
				}
				if(content.indexOf("goods_img_p_4") >= 0 && f.indexOf("goods_img_p_4") >= 0)
				{
					$alert("warn","编辑器中已存在商品四！")
					return false;
				}
				if(content.indexOf("goods_img_p_5") >= 0 && f.indexOf("goods_img_p_5") >= 0)
				{
					$alert("warn","编辑器中已存在商品五！")
					return false;
				}
	            c.execCommand("insertHtml", $(this).html())
            })
        });
        $(".tabs li a").live("click",
        function() {
            $(this).addClass("current").parent().siblings().each(function() {
                $(this).find("a").removeClass("current")
            });
            $("#" + $(this).attr("tab")).show().siblings().hide()
        })
    });
    
    
    
    
    
    
    
  /*小图标选择器*/
  function shifuMouseDownMark(id) {
  	var con   = $('#'+id).find("span").html();
  	var range = UE.getEditor('editor').selection.getRange();

  	range.select();
  	
  	UE.getEditor('editor').selection.getText();
  	
  	UE.getEditor('editor').execCommand('insertHtml',con);
  }
  $(function () 
  {
      $(window).resize(function(){
  		var win_height = $(window).height();
  		$('#bdeditor').height(win_height);
  		var area_height = win_height -6;
  		if(area_height > 800){
  			area_height = 800;
  		}
  		
  		$('#editor').height(area_height-40);
  		$('#styleselect').height(area_height);
  		$('.content').height(area_height-165);
  	}).trigger('resize');

  	$('.clear-editor').click(function(){
  		if(confirm('是否确认清空内容，清空后内容将无法恢复')){
  			c.setContent('');
  		}		
  	});
      
   	//window.onbeforeunload = function(event) { return confirm("确定离开此页面吗？"); }
  	var client = new ZeroClipboard( $('.copy-editor-html') );
  	ZeroClipboard.config({
  				swfPath: "ueditor/third-party/zeroclipboard/ZeroClipboard.swf"
  			});
  	client.on( 'ready', function(event) {
  		client.on( 'copy', function(event) {
  	  		event.clipboardData.setData('text/html', c.getContent());
  	  		event.clipboardData.setData('text/plain',c.getContent()); 
  		});
          client.on('aftercopy',function(event) {
  			alert('正文内容已经复制到剪切板，可粘贴（ctrl+v）到微信公众平台编辑器中使用！');
  		 });
  	});  
      $("#phone").live('click', function () {
           $("#myModal").modal(options)
      });
       $("#wx").live('click', function () {
           $("#wxModal").modal(options)
      });
      $("#kefu").live('click', function () {
           $("#kefu1").modal(options)
      });
  	$("#savebox").live('click', function () {
           $("#myModal").modal(options)
      });
  	$('#savewx').live('click', function () {
         
     });
     	$('#reguser').live('click', function () {
  		
  	    $('#loginModal').modal('hide');
  		$('#userregModal').modal('show')
         
     });
    
      var b = ["borderTopColor", "borderRightColor", "borderBottomColor", "borderLeftColor"],
          d = [];
      $.each(b, function (a) {
          d.push(".itembox .wxqq-" + b[a])
      });
      $("#colorpickerbox").ColorPicker({
          flat: true,
          color: "#00bbec",
          onChange: function (a, e, f) {
              $(".itembox .wxqq-bg").css({
                  backgroundColor: "#" + e
              });
              $(".itembox .wxqq-color").css({
                  color: "#" + e
              });
              $.each(d, function (g) {
                  $(d[g]).css(b[g], "#" + e)
              })
          }
      });

      var c = UE.getEditor("editor", {
          topOffset: 0,
          autotypeset: {
              removeEmptyline: true
          },
          toolbars: [
              ['fullscreen', 'source', 'undo', 'redo', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'removeformat', 'autotypeset', 'blockquote', 'pasteplain', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', 'rowspacingtop', 'rowspacingbottom', 'lineheight', 'indent', 'justifyleft', 'justifycenter', 'justifyright', 'fontfamily', 'fontsize', 'justifyjustify', 'touppercase', 'tolowercase', 'insertimage', 'emotion', 'insertvideo', 'map', 'date', 'time', 'spechars', 'preview', 'searchreplace'],
              ['con', 'title', 'fork', 'guide', 'division', 'other', 'mystyle']
          ],
          autoHeightEnabled: false,
          allowDivTransToP: false,
          autoFloatEnabled: true,
          enableAutoSave: false
      });
      c.ready(function () {
          c.addListener('contentChange', function () {
              $("#preview").html(c.getContent());
              $("#wxpreview").html(c.getContent());
          });

      });
      $(".tabs li a").live("click", function () 
      {
          $(this).addClass("current").parent().siblings().each(function () 
          {
              $(this).find("a").removeClass("current")
          });
          var $index = $(this).attr("tab");
          $("#" + $index).show().siblings().hide();
      });
  	

  	window.onload = function() 
  	{
  		var LSContent = localStorage.getItem('ueditor_preference');
  		if(LSContent) {
  			var JContent;
  			try{
  				JContent = JSON.parse(LSContent);
  			} catch(ex) {
  				throw new Error("JSON格式的数据有误，转化失败!");
  			}
  			if(JContent.localContent) {
  				setTimeout(function() {
  					UE.getEditor('editor').setContent(JContent.localContent);
  					
  				},350);
  				
  			}
  		}
  	}
  });