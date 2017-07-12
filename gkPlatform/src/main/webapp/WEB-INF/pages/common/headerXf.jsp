<%@ page import="cn.gukeer.platform.common.LogUtils" %>
<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <%--以下css的加载顺序不可调换--%>
    <link rel="icon" href="${ctxStaticNew}/images/logo.png"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/style.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/common.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/pageDevide.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/Notice.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/zTreeStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/oldCss.css"/>

    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/action.js"></script>
    <script src="${ctxStaticNew}/js/html5shiv.min.js"></script>
    <script src="${ctxStaticNew}/js/respond.min.js"></script>
    <script src="${ctxStaticNew}/js/jquery.ztree.core.js"></script>
    <script src="${ctxStaticNew}/js/pageDevide.js"></script>
    <!--end 分页插件-->
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/laydate.js"></script>

    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>


    <script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/util.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/interface/NotifyJs.js"></script>
    <script>
        if (${empty loginUser}) {
            layer.msg("登录超时，请重新登录！");
            window.location.href = "${ctx}/doLogout";
        }

        $(function () {
            NotifyJs.init();
            $.get("${ctx}/head/getlogo", {}, function (retJson) {
                if (retJson.msg != "") {
                    document.getElementById("logo").src = "${ctx}/file/pic/show?picPath=" + retJson.msg;
                } else {
                    document.getElementById("logo").src = "${ctxStaticNew}/images/logo-logo.png";
                }
            });
        })
        //这个方法用来启动该页面的ReverseAjax功能
        dwr.engine.setActiveReverseAjax(true);
        //设置在页面关闭时，通知服务端销毁会话
        dwr.engine.setNotifyServerOnPageUnload(true);

        dwr.engine.setErrorHandler(function (message, ex) {
            dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);
        }
        );

        function show(msg) {
            alert(msg);
        }
    </script>
</head>

<body>
<header id="header" class="container-fluid header-scroll">
    <div class="container">
        <div class="logo">
            <%--<c:if test="${gukeer:notEmptyString(sessionScope.logoSession)}">--%>
            <%--<img src="${ctx}/file/pic/show?picPath=${sessionScope.logoSession}" style="height: 45px; vertical-align: middle" alt=""/>--%>
            <%--</c:if>--%>
            <%--<c:if test="${gukeer:emptyString(sessionScope.logoSession)}">--%>
            <%--<img src="${ctxStaticNew}/images/logo-logo.png" alt=""/>--%>
            <%--</c:if>--%>
            <img
            <%--src="${ctxStaticNew}/images/loading.gif" --%>
                    style="height: 45px; vertical-align: middle" alt="" id="logo"/>
        </div>
        <div class="nav-menu">
            <span class="home active" onclick="window.location.href='${ctx}/home/index'"></span>
            <div class="news-center">
                <c:if test="${publicCount > 0}">
                    <i>${publicCount}</i>
                </c:if>
                <ul class="news-center-menu">
                    <c:choose>
                        <c:when test="${publicCount > 0}">
                            <li>
                                <span onclick="window.open('${ctx}/home/messagecenter')">
                                    通知公告
                                        <i>${publicCount}</i>
                                </span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <span>
                                    暂无消息
                                </span>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <ul>
                <li class="account">
                    <a href="#">
                        <c:choose>
                            <%--<c:when test="${gukeer:notEmptyString(loginUser)}">
                                 <c:if test="${userType == 3}">
                                     ${bottomName}
                                 </c:if>
                            </c:when>
                            <c:otherwise>
                                账号
                            </c:otherwise>--%>
                            <c:when test="${userType == 3}">
                                ${bottomName}
                            </c:when>
                            <c:otherwise>
                                ${loginUser.name}
                            </c:otherwise>
                        </c:choose>

                        <i></i></a>
                    <ul class="account-box">
                        <li><a href="${ctx}/user/editInfo"><i></i>设置</a></li>
                        <li><a href="${ctx}/doLogout"><i></i>退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

</header>
<div style="width:100%;height: 95px"></div>

</body>

</html>