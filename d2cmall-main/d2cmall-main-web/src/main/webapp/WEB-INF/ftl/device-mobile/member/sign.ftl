<#import "templates/public_mobile.ftl" as m>
<@m.page_header title='每日签到'  service='false'/>
<#assign LOGINMEMBER=loginMember()/>
<style>body{background-color:#fff;}
#popup-modal-outer{background:transparent}
.sign-succuss-content{width:85vw;min-width:250px;height:260px;border-radius:13px;background:#fff;text-align:center;}
.success-title{color:grba(0,0,0,.85);font-weight:bold;font-size:20px;margin-top:-50px;}
.list-item a{display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-align: center;-ms-flex-align: center;align-items: center;overflow:hidden}
</style>
<div class="section">
	<div class="sign-head">
		<div class="sign-top ">
			<div><a href="/page/dbdetail" style="color:rgba(0,0,0,.5)">收支明细&nbsp;></a></div>	
			<div>签到提醒<div class="re-button"></div></div> 	
		</div>
		
		<div class="sign-db">
			<p class="total" id="total"></p>
			<p style="line-height:20px;font-size:14px;color:#9b6919"><a href="/page/mudb">我的D币</a></p>
		</div>
		<div class="sign-tips">连续签到<span>${signRecords.total}天</span>，明日签到+<span>${tomorrowReward}</span>D币</div>
		<div class="sign-bg"></div>
	</div>
	<div class="sign-content">
		<div class="sign-jf">
		<#if signRecords.total && signRecords.total lt 7 && signRecords.total gt 0>
		<#list signRecords.list?sort_by(["id"]) as list>
			<div class="sign-it text-center"><div class="old">+${list.point}</div><p class="ite-day">${list.totalDay}天</p></div>
		</#list>
		<#list signRewards as list>
		<#if list_index gte signRecords.total>
			<div class="sign-it text-center"><div>+${list}</div><p class="ite-day">${list_index+1}天</p></div>
		</#if>
		</#list>
		<#else>
			<#list signRewards as list>
				<div class="sign-it text-center"><div>+${list}</div><p class="ite-day">${list_index+1}天</p></div>
			</#list>
		</#if>
			
			
		</div>
		<div class="sign-button">
			签到
		</div>
		<p class="sign-tips">
		  连续签到可以获得更多D币>
		</p>
	</div>
	<div class="sign-product">
		<p class="product-title text-center">D币兑换<a href="/point/shop"><span class="more">查看更多></span></a></p>
		<div class="sign-list clearfix">
		</div>
		<div class="text-center" style="margin:15px 0;color:rgba(0,0,0,.3);font-size:12px;">
			活动由D2C提供，与设备生产商Apple Inc无关
		</div>
		<button style="line-height:44px;height:44px;width:92%;margin:15px auto;background-color:#000;color:#fff;text-align:center;border:none;display:block;font-size:16px;" onclick="location.href='/point/shop'">D币商城</button>
	</div>

</div>
<script id="list-template" type="text/html">
	<div class="lazyload">
    {{each pager.list as value i}}
		<div class="list-item">
			<a href="/dcion/shop/productpoint/{{value.id}}">
				<img src="https:${picture_base}{{value.detailPic}}!600">
			</a>
			<p class="list-title">{{value.name}}</p>
			<p class="need-db" style=""><span style="color:#c39b5c">{{value.point}}</span>D币</p>
		</div>
    {{/each}}
    </div>
</script>

<script>
$(function(){
	$('.sign-button').on('click',function(){
		$.post("/member/sign.json",function(res){
	       if(res.result.status==1){
	       	var day=res.result.datas.dailySign.totalDay;
	       	var point=res.result.datas.dailySign.point;
	       	sendSignStatus();
	       	 signsuccess(day,point);
	       }else{
	       	$.flashTip({position:'center',type:'success',message:res.result.message});
	       }
	 	 });
	})
	function signsuccess(day,point){
	  var html='<div class="popup-content"><div class="sign-succuss-content">\
	  <img src="http://static.d2c.cn/img/other/pc_lihua.png" style="width:100%"><p class="success-title">连续签到'+day+'天</p><p style="margin:25px 0 15px;color:#e83333;font-size:14px;">获得+'+point+'D币</p><p style="color:rgba(0,0,0,.5);font-size:12px;">明日签到可获得+${tomorrowReward}D币</p>\
	  <button class="button" style="background:#000;height:44px;width:80%;color:#fff;line-height:44px;margin-top:40px;" onclick="location.reload();">知道了</button>\
	  </div><div>';
	  popupModal({content:html});
	}
	 $.get("/pointproduct/list.json",function(res){
		var list_html=template('list-template',res);
		$('.sign-list').append(list_html)
		 var height=$('.list-item a').width()+'px'
		 $('.list-item a').css('height',height)
	 });
	 
	 $.get("/member/info.json",function(res){
		var point=res.member.memberDetail.integration;
		$('#total').html('<a href="/page/mudb">'+point+'</a>')
	 });
	 
	//延时询问开关状态
	 setTimeout(function(){
		 noticeFunc()
		 makeClass('no')
	 }, 210)
	
	 $('.sign-tips').on("click",function(){
	 	var html='<div class="popup-content"><div class="sign-succuss-content" style="height:220px;width:78vw;">\
	  	<p class="success-title"  style="margin-top:0;padding-top:25px;">连续签到规则</p><p style="color: rgba(0,0,0,.5);font-size: 12px;line-height: 20px; width: 80%;margin: 20px auto 0;">一个签到周期为7天，连续签到可获得更多的D币，如果中途中断签到则从第一天累计计算</p>\
	  <button class="button" style="background:#000;height:44px;width:80%;color:#fff;line-height:44px;margin-top:20px;" onclick="location.reload();">知道了</button>\
	  </div><div>';
	  popupModal({content:html});
	 }) 
	  
	 $('.re-button').on('click',function(){
	   	openNotice();
		makeClass()

	 }) 
		function makeClass(a){
			if(isAndroid){
				setTimeout(function(){
					if(window.respons=='open'){
						$('.re-button').addClass('on')
						if(a!='no'){
							$.flashTip({position:'center',type:'success',message:'签到通知开启'});
						}
			   			
					}else{
						$('.re-button').removeClass('on')
						if(a!='no'){
							$.flashTip({position:'center',type:'success',message:'签到通知关闭'});
						}
					}
				},100);
			}
			
		}
		
	
})	 
</script>


<@m.page_footer />
