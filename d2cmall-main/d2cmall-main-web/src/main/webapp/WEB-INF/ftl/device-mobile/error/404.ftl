<#import "templates/public_mobile.ftl" as m>
<@m.page_header title='出错啦！' service="no"/>
<div class="section">
    <div class="text-center padding" style="line-height:250%;padding-top:3em;">
        <div class="icon icon-excam"></div>
        <div class="red"><strong>对不起，你请求的页面不存在。</strong></div>
        <div>请检查是否地址输入错误。</div>
        <div>&nbsp;</div>
        <#if !m.FROMAPP>
        <div><button type="button" name="return" onclick="location.href='/';" style="width:100%;" class="button button-l button-red">返回首页</button></div>
        </#if>
    </div>
</div>
<hr />
<@m.page_footer menu=true />