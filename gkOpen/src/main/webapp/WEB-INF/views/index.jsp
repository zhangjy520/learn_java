<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jsp" %>
<%@ include file="login/login.jsp" %>
<%@ include file="/base.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="${ctx}/static/css/index.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/bannerShow.min.css"/>

    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/js/bannerShow.js"></script>
    <script src="${ctx}/static/open/register.js"></script>
    <%--<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>--%>
    <%--<script type='text/javascript' src='${ctx}/dwr/util.js'></script>--%>
    <%--<script type='text/javascript' src='${ctx}/dwr/interface/SendPush.js'></script>--%>
</head>
<body>

<style>
    ul {
        margin: 0 !important;
    }
    .web_toast{
        display: block !important;
        top: 130px !important;
    }
</style>
<div style="width:100%;">
    <section>
        <div class="bs-content" <%--style="height:685px;"--%>>
            <%--<ul class="bs-box">--%>
            <%--<li style=" opacity: 1;filter:alpha(opacity=100);">--%>
            <%--<a href="" style="background:url(${ctx}/static/images/slider1.jpg) center top no-repeat">--%>
            <img style="width:100%;max-height:685px;" src="${ctx}/static/images/slider1.jpg" alt="">
            <%--</a>--%>
            <%--</li>--%>
            <%--</ul>--%>
        </div>
    </section>
    <div id="platformStatus" class="clearfix">
        <div>
            <div class="highlight lf"><span>平台动态</span></div>
            <div class="statusDetail lf">
                <ul>
                    <c:forEach items="${list}" var="dynamic" varStatus="status">
                        <li>
                            <span>[${status.index+1}]  </span>
                            <a href="${ctx}/dynamic/detail?id=${dynamic.id}">${dynamic.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="statusDetail rl">
                <a href="${ctx}/dynamic/more" style="color: #54ab37">查看更多></a>
            </div>
        </div>

    </div>
    <!--产品模块-->
    <div id="production1">
        <h1>产品服务</h1>
        <section class="clearfix">
            <a href="${ctx}/document/index">
                <%--<img src="${ctx}/static/images/index-icon1.png" alt="">--%>
                <div></div>
                <span>概述</span>
            </a>
            <a href="${ctx}/document/join/register">
                <%--<img src="${ctx}/static/images/index-icon2.png" alt="">--%>
                <div></div>
                <span>接入指南</span>
            </a>
            <a href="${ctx}/document/treaty">
                <%--<img src="${ctx}/static/images/index-icon3.png" alt="">--%>
                <div></div>
                <span>标准规范</span>
            </a>
            <a href="${ctx}/document/api">
                <%--<img src="${ctx}/static/images/index-icon4.png" alt="">--%>
                <div></div>
                <span>API索引</span>
            </a>
        </section>
    </div>
    <div id="production2">
        <h1>接入开放平台</h1>
        <div>
            <div>
                <img src="${ctx}/static/images/steps2.png" alt=""/>
            </div>
            <button onclick="window.location.replace('${ctx}/register/index')">立即成为开发者</button>
        </div>

    </div>
    <div id="login">
    </div>
</div>


<!--网页信息-->
<%@ include file="common/footer.jsp" %>

<!--登录界面-->
<script src="${ctx}/static/js/action.js"></script>
<script>
    var exception = "${exception}";
    if (exception.length>0){
        webToast(data.msg, "top", 3000);
    }


</script>
</body>
</html>