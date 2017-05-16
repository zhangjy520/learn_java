<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<aside class="col-xs-3" style="padding-top: 20px">
    <ul class="document-grade-1">
        <li>
            <a href="#">平台管理</a>
            <ul class="document-grade-2" style="display:block;">
                <li><a href="${ctx}/platform/index" id="appManager"> 平台信息管理</a></li>
                <li><a href="${ctx}/platform/add/page" class="addApp" id="addPlatformPage">增加平台信息</a></li>
                <!--<li><a href="manageCenter5.html">申请记录</a></li>-->
            </ul>
        </li>
    </ul>
</aside>
<script>
    $(function () {
        if ("${platformIndex}" == "platformIndex") {
            $("#appManager").addClass('active');
        }
        if ("${addPlatformPage}" == "addPlatformPage") {
            $("#addPlatformPage").addClass('active');
        }
    })
</script>