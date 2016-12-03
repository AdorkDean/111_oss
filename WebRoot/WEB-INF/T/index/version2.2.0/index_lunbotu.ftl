<div class="fullSlide clearfix clear">
    <div class="bd">
        <ul>
            <#list attInfo?sort_by('weight')?reverse as obj>
			<#if obj_index<7 >
			<li _src="url(${obj.cposition.imgUrl?if_exists})" style="background:#fff center center no-repeat;"><a target="_blank" href="${obj.cposition.imgLink?if_exists}"></a></li>
			</#if>
			</#list>
        </ul>
    </div>
    <div class="hd">
        <ul></ul>
    </div>
    <div class="gd"><span class="prev"></span><span class="next"></span></div>
</div>
<script type="text/javascript">
jQuery(".fullSlide").hover(function() 
{
    jQuery(this).find(".prev,.next").stop(true, true).fadeTo("show", 0.5)
},
function() 
{
    jQuery(this).find(".prev,.next").fadeOut()
});
jQuery(".fullSlide").slide(
{
    titCell: ".hd ul",
    mainCell: ".bd ul",
    effect: "fold",
    autoPlay: true,
    autoPage: true,
    trigger: "click",
    startFun: function(i)
    {
        var curLi = jQuery(".fullSlide .bd li").eq(i);
        if ( !! curLi.attr("_src")) 
        {
            curLi.css("background-image", curLi.attr("_src")).removeAttr("_src")
        }
    }
});
</script>
