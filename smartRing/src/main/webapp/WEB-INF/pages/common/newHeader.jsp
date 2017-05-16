<!DOCTYPE html>
<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctxStatic}/css/common.css"/>
    <%--<script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>--%>
    <script src="${ctxStatic}/js/jquery.min.js"></script>
    <script src="${ctxStatic}/js/plugins/pageDevide.js"></script>
    <script src="${ctxStatic}/js/global.js"></script>
</head>

<body>
<nav id="top-menu">
    <div class="container clear">
        <p class="lf">
            <img src="${ctxStatic}/images/logo.png" alt=""/>
        </p>
        <ul class="lf clear">
            <li id="indexPage"><a href="${ctx}/index">首页</a></li>
            <li id="dailyHealth">
                <shiro:hasPermission name="daily:health:index">
                    <a href="${ctx}/dailyhealth/sport/index" class="sh-menu">日常健康</a>
                    <ul>
                        <li><a href="${ctx}/dailyhealth/sport/index">运动统计</a></li>
                        <li><a href="${ctx}/dailyhealth/sleep/index">睡眠统计</a></li>
                        <li><a href="${ctx}/dailyhealth/manage/index">日常管理</a></li>
                    </ul>
                </shiro:hasPermission>
            </li>
            <li id="peTest">
                <shiro:hasPermission name="pe:test:index">
                    <a href="${ctx}/sport/score/index" class="sh-menu">体育教学</a>
                    <ul>
                        <li><a href="${ctx}/sport/score/index">成绩统计</a></li>
                        <li><a href="${ctx}/sport/teach/index">教学建议</a></li>
                        <li><a href="${ctx}/sport/scoremange/index">成绩管理</a></li>
                        <%--<li><a href="${ctx}/sport/class/index">班级管理</a></li>--%>
                    </ul>
                </shiro:hasPermission>
            </li>
            <li id="physicalHealth">
                <shiro:hasPermission name="body:health:index">
                    <a href="${ctx}/physical/analysis/index" class="sh-menu">体质健康</a>
                    <ul>
                        <li><a href="${ctx}/physical/analysis/index">体质分析</a></li>
                        <li><a href="${ctx}/physical/score/index">成绩管理</a></li>
                        <li><a href="${ctx}/physical/archives/index">学生体质档案</a></li>
                    </ul>
                </shiro:hasPermission>
            </li>
            <li id="positionControl">
                <shiro:hasPermission name="position:control:index">
                    <a href="${ctx}/position/realtime/index" class="sh-menu">位置监控</a>
                    <ul>
                        <li><a href="${ctx}/position/realtime/index">实时监控</a></li>
                        <li><a href="${ctx}/position/statistics/index">位置统计</a></li>
                        <li><a href="${ctx}/manage/area/index/1">区域信息</a></li><%--1代表位置监控-区域信息--%>
                    </ul>
                </shiro:hasPermission>
            </li>
            <li id="basicManage">
                <a href="${ctx}/manage/ring/index" class="sh-menu">基础管理</a>
                <ul>
                    <%--<li><a href="${ctx}/manage/ring/index">手环挂失和绑定</a></li>--%>
                    <%--<li><a href="${ctx}/manage/tempring/index">临时手环</a></li>--%>
                    <shiro:hasPermission name="ring:manage:index">
                        <li><a href="${ctx}/manage/ring/index">手环管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="station:manage:index">
                        <%--<li><a href="${ctx}/manage/station/index">网关管理</a></li>--%>
                        <li><a href="${ctx}/manage/station/index">网关管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="role:manage:index">
                        <li><a href="${ctx}/manage/role/index">角色管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:manage:index">
                        <li><a href="${ctx}/manage/user/index">用户管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="area:manage:index">
                        <li><a href="${ctx}/manage/area/index/0">区域管理</a></li><%--0代表基础管理-区域管理--%>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="sportClass:manage:index">
                        <li><a href="${ctx}/sport/class/index">班级管理</a></li><%--0代表基础管理-区域管理--%>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="device:manage:index">
                        <li><a href="${ctx}/device/index">签到演示</a></li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </ul>
        <div class="rl">
            <ul>
                <li>
                    <span></span>
                    <ul>
                        <li>
                            <a href="${ctx}/user/info/index" class="i-user-msg">用户信息</a>
                        </li>
                        <%--<li>--%>
                            <%--<a href="#" class="i-news">我的消息</a>--%>
                        <%--</li>--%>
                        <li>
                            <a href="${ctx}/doLogout" class="i-quit">退出</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div id="support"></div>
<script>
    /*var navOffset=$("#top-menu").offset().top;
    $(window).scroll(function(){
        var scrollPos=$(window).scrollTop();
        if(scrollPos>navOffset){
            $("#top-menu").addClass("header-scroll");
        }else{
            $("#top-menu").removeClass("header-scroll");
        }
    });*/
</script>

</body>

</html>