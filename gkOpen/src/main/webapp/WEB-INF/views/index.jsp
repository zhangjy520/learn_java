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
</head>
<body>

<style>
    .web_toast{
        display: block !important;
        top: 130px !important;
    }
</style>
<div style="width:100%;">
    <section>
        <div class="bs-content">
            <img style="width:100%;max-height:685px;" src="${ctx}/static/images/slider1.jpg" alt="">
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
                <div></div>
                <span>概述</span>
            </a>
            <a href="${ctx}/document/join/register">
                <div></div>
                <span>接入指南</span>
            </a>
            <a href="${ctx}/document/treaty">
                <div></div>
                <span>标准规范</span>
            </a>
            <a href="${ctx}/document/api">
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

    $(function () {
//        $('.mask-bg').fadeIn(300);
//        $('.mask-content input[type=text]').focus();

            var remember  = 0;
            if (document.getElementById("rememberme").checked) {
                remember = 1;
            } else {
                remember = 0;
            }

            var username = $("#username").val();
            var password = $("#password").val();
            $.post(postPath + "/doLogin", {
                username: username,
                password: password,
                remember:remember
            }, function (data) {
                $("#loginButton").attr("disabled", false);
                if (data.code == 0) {
                    window.location.replace(data.data);
                } else {
                    $("#alert").text(data.msg);
                }
            });
    })


</script>
</body>
</html>