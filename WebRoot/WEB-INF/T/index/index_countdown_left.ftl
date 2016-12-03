<#list attInfo?sort_by('weight')?reverse as obj>
<#if obj_index<1 >
	   <div class="countdown">
        	<div class="time-ico">
            	<span></span>
            </div>
            <div class="countdown-info"><span class="t-d-a-day">00</span><i>天</i><span class="t-d-a-hour">00</span><i>时</i><span class="t-d-a-min">00</span><i>分</i><span class="t-d-a-secon">00</span><i>秒</i></div>
            <p class="countdown-title">限时抢购</p>
        </div>
	    <input type="hidden" value="${obj.cposition.title?if_exists}" id="endTime"/>
	    <input type="hidden" value="${obj.cposition.imgLink?if_exists}" id="startTime"/>
</#if>
</#list>

