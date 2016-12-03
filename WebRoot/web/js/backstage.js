$(function(){
   /*第一部分的tab切换*/
  /* $(".userppt_list ul").on("click","li",function(e){
      e.preventDefault();
      var index=$(this).index();
      $(this).addClass("user_on").find(".userppt_triangle").show();
      $(this).siblings().removeClass("user_on").find(".userppt_triangle").hide();
      $(".province_content>section").eq(index).show().siblings().hide();
   });*/
   /*获取长度的进度条*/
   $(".prbox_list ul li").each(function(){
      var progressnumber=$(this).find(".pr_provinces").last().html();
      var prolength=$(this).find(".pr_ratio").find("b");
      prolength.width(parseInt(progressnumber)+"px");
   });
   /*第二部分的tab切换*/
   $(".newadd_list").on("click","li",function(e){
      e.preventDefault();
      var index=$(this).index();
      $(this).addClass("add_cover").find(".newadd_triangle").show();
      $(this).siblings().removeClass("add_cover").find(".newadd_triangle").hide();
//      $(".numpk_content>section").eq(index).show().siblings().hide();
   });



   var clicknum=0;
   var allsouces=$(".allsouc_box");
   $(".allsouc_box").each(function(){
      $(this).on("click",function(){
         var firstli=$(this).find("ol");
         if(clicknum++%2==0){
            $(this).find("ol").slideDown();
            $(this).find(".allsouc_up").hide();
            $(this).find(".allsouc_down").show();
         }else{
            $(this).find("ol").slideUp();
            $(this).find(".allsouc_up").show();
            $(this).find(".allsouc_down").hide();
         }
      })

   });
   var allfirstli=$(this).find("ol").children().first();
   $(".allsouc_list").on("click","li",function(){

   })
});