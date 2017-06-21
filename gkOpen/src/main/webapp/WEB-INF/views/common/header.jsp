<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit"/>
    <script src="${ctx}/static/another-js/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/document.css"/>
    <link rel="icon" href="${ctx}/static/images/logo-icon.png"/>
    <link rel="stylesheet" href="${ctx}/static/css/alert.css"/>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
</head>

<body>
<div class="container-fluid">
    <nav class="container">
        <a href="${ctx}/index"><h3><img src="${ctx}/static/images/logo-logo.png" alt=""/></h3></a>
        <ul class="nav-menu">
            <li><a href="${ctx}/" id="home">首页</a></li>
            <li><a href="${ctx}/document/index" id="document">开发文档</a></li>
            <li><a href="${ctx}/tech/index" id="tech">支持中心</a></li>
        </ul>

        <c:if test="${sessionScope.openUser == null }">
            <div>
                <span class="login">登录</span>
                <span class="register" onclick="registerIndex()" id="register">开发者注册</span>
                    <%--"window.location.replace('${ctx}/register/index')"--%>
            </div>
        </c:if>
        <c:if test="${sessionScope.openUser != null}">
            <div>
                <span style="width:auto;border:none;"
                      onclick="window.location.href = '${ctx}/manager/index'">${sessionScope.openUser.username}</span>
                <span id="logOut" onclick="window.location.href='${ctx}/doLogout'"> 退出登录</span>
                <c:if test="${sessionScope.status != 0}">
                    <span onclick="window.location.href='${ctx}/manager/index'">管理中心</span>
                </c:if>
                <c:if test="${sessionScope.status == 0}">
                    <span onclick="dodeatil()">管理中心</span>
                </c:if>

            </div>
        </c:if>
        <c:if test="${sessionScope.openUser == null}">
            <%--<script>--%>
            <%--alert("您当前还没有登陆系统或已经登陆超时，请前往登陆");--%>
            <%--&lt;%&ndash;window.location.href="${ctx}/doLogout";&ndash;%&gt;--%>
            <%--</script>--%>
        </c:if>
    </nav>
    <div id="demo" style="display: none"></div>

</div>
<script>
    <%--var viewFlag = true;--%>
    <%--if (!viewFlag){--%>
        <%--if (${empty sessionScope.openUser}) {--%>
            <%--alert("登录超时，请重新登录！");--%>
            <%--&lt;%&ndash;window.location.href = "${ctx}/doLogout";&ndash;%&gt;--%>
        <%--}--%>
    <%--}--%>
    function dodeatil() {
        webToast("您的资料尚未填写完整，正在为您打开详细资料页面，请稍后...", "top", 3000);
        setTimeout(function () {
                    window.location.href = '${ctx}/register/detail?status=noWrite&&id=${sessionScope.openUser.id}';
                },
                2500)
    }
    function registerIndex() {
        $("#regist_email").val("");
        $("#regist_password").val("");
        window.location.href = '${ctx}/register/index';
    }

    $(function () {

        if ("${document}" == "document") {
            $("#document").addClass('active');
        }
        if ("${home}" == "home") {
            $("#home").addClass('active');
        }

        if ("${tech}" == "tech") {
            $("#tech").addClass('active');
        }
        if ("${register}" == "register") {
            $("#register1").addClass('active');
        }

        if ("${registerindex}" == "registerindex") {
            $("#tech").removeClass('active');
        }

        if ("${treaty}" == "treaty" || "${ilegal}" == "ilegal"
                || "${register}" == "register" || "${join}" == "join"
                || "${run}" == "run" || "${service}" == "service"
                || "${back}" == "back" || "${plate}" == "plate" || "${api}" == "api") {
            $("#document").addClass('active');
        }

        if ("${codeback}" == "codeback" || "${question}" == "question") {
            $("#document").addClass('active');
        }

        <%--if("${basic}" == "basic"||"${PWDPage}" == "PWDPage"--%>
        <%--||"${addApp}" == "addApp"||"${all}" == "all"--%>
        <%--||"${dynamic}" == "dynamic") {--%>
        <%--$("#index").removeClass('active');--%>
        <%--}--%>
    })
</script>
</body>
</html>