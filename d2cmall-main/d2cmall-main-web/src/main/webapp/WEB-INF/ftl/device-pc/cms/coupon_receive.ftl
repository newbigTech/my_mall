<#assign LOGINMEMBER=loginMember()/>
<html>
<head>
<title>${result.datas.coupon.name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no,minimal-ui" />
<meta http-equiv="cleartype" content="on" />
<#if profile=='development'>
<link type="text/css" href="${static_base}/nm/css/com.base.css" rel="stylesheet" media="screen" />
<link type="text/css" href="${static_base}/nm/css/com.element.css" rel="stylesheet" media="screen" />
<link type="text/css" href="${static_base}/nm/css/com.component.css" rel="stylesheet" media="screen" />
<link type="text/css" href="${static_base}/nm/css/com.layout.css" rel="stylesheet" media="screen" />
<#else>
<link type="text/css" href="${static_base}/nm/css/common.css?t=${refreshTimeStamp}" rel="stylesheet" media="screen" />
</#if>
<#if css?length gt 0>
    <#list css?split("|") as s>
<link rel="stylesheet" href="${static_base}/nm/css/${s}.css?t=${refreshTimeStamp}" />
    </#list>
</#if>
</head>
<body>
<div class="section">
    <div class="text-center padding" style="line-height:250%;padding-top:30%;">
      <#if result.datas.coupon.over>
        <div style="padding-top:15px;">
	            <a href="/" class="button button-l button-red">优惠券领用时间已经过了，下次早点来</a>
	    </div>
      <#else>
      <div>
         		  ${result.datas.coupon.wapCode} 
      <div>
      	       	<#if LOGINMEMBER.id!=null>
	     	  <div class="coupon-main clearfix">
	            <div class="coupon-left">
	             <#if result.datas.coupon.type=="DISCOUNT">
	                   <p>折扣 ${result.datas.coupon.amount/10}</strong></p> 
	                    <#else><p>&yen;<strong>${result.datas.coupon.amount}</strong></p></#if>
	                <p>满${result.datas.coupon.needAmount}元使用</p>
	            </div>
	            <div class="coupon-right">
	                <p>${result.datas.coupon.name}</p>
	                <p>有效期：${(result.datas.coupon.enableDate?string("yy/MM/dd"))!} - ${(result.datas.coupon.expireDate?string("yy/MM/dd"))!}</p>
	            </div>
	        </div>
		    <#else>
	            <div class="form-item">
	            	 <input type="text" name="mobile" id="mobile" value="" class="input validate" title="手机号码" placeholder="请输入手机号码"  />
	                <input type="text" name="code" id="code" value="" class="input validate" title="校验码" placeholder="请输入校验码" />
	                <button type="button" data-source="" data-type="RETRIEVEPASSWORD" class="button button-white validate-send validate-button">获取校验码</button>
	            </div>
	   	   </#if>
	        <div class="coupon-button">
	            <a href="javascript:" data-url="${result.datas.receiveUrl}"   success-tip="恭喜您！领取成功!" fail-tip="优惠券领取不成功！" class="button button-red ajax-request active-coupon">立即领取</a>
	        </div>
        </#if>
       
    </div>
</div>
</body>
</html>