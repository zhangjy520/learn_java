<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>审核管理</title>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/zoom.css">
</head>
<style>
    img{
        border: 1px solid #ddd;
    }
</style>
<body>
<main id="check-detail">
    <div id="aside">
        <ul>
            <li data="content1" class="active">应用信息</li>
            <li data="content2">开发者信息</li>
            <li data="content3">应用概况</li>
        </ul>
    </div>
    <!--<h3>应用信息</h3>-->
    <div id="check-detail-content">
        <section id="content1">
            <ul class="massages massages1">
                <li>
                    <aside>
                        <span>应用名称:</span>
                    </aside>
                    <div>
                        <span>${appInfo.app.name}</span>
                        <span id="appId" hidden>${appInfo.app.id}</span>
                    </div>
                </li>
                <li>
                    <aside><span>版本号:</span></aside>
                    <div>
                        <span>${appInfo.app.version}</span>
                    </div>
                </li>
                <%--<li>--%>
                    <%--<aside><span>应用简介:</span></aside>--%>
                    <%--<div>--%>
                        <%--<span>${appInfo.app.appAbbreviation}</span>--%>
                    <%--</div>--%>
                <%--</li>--%>
                <li>
                    <aside><span>应用类别:</span></aside>
                    <c:if test="${appInfo.app.category==0}">
                    <div><span>教学教务</span></div>
                    </c:if>
                    <c:if test="${appInfo.app.category==1}">
                        <div><span>互动空间</span></div>
                    </c:if>
                </li>
                <li>
                    <aside><span>目标用户:</span></aside>
                    <div>
                        <span>教师</span>
                    </div>
                </li>
                <li>
                    <aside><span>是否免费:</span></aside>
                    <div>
                        <c:if test="${appInfo.app.isFree==1}">
                            <span>否</span>
                        </c:if>
                        <c:if test="${appInfo.app.isFree==0}">
                            <span>是</span>
                        </c:if>
                    </div>
                </li>

                <li>
                    <aside><span>应用级别:</span></aside>
                    <div>
                        <c:if test="${appInfo.app.appRank==0}">
                            <span>区级</span>
                        </c:if>
                        <c:if test="${appInfo.app.appRank==1}">
                            <span>校级</span>
                        </c:if>
                    </div>
                </li>
            </ul>
            <ul class="massages">
                <li>
                    <aside><span>应用图标:</span></aside>
                    <div>
                        <p style="width: 100px; height: 100px"><a onclick="window.open('${appInfo.app.logo}')"><img style="width:100%; height: 100%" src="${appInfo.app.logo}" alt="aaa"></a></p>
                    </div>
                </li>
                <li>
                    <aside><span>应用截图:</span></aside>
                    <div>
                        <ul class="gallery"style="width: 350px;">
                            <c:forEach items="${screenshotList}" var="screenshot">
                                <li><a onclick="window.open('${screenshot}')"><img src="${screenshot}" alt="应用截图"></a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <%--<div class="wrap af1">--%>
                    <%--<ul class="slidebox">--%>
                    <%--<c:forEach items="${screenshotList}" var="screenshot">--%>
                    <%--<li><a href="${screenshot}"><img src="${screenshot}" alt="应用截图"></a></li>--%>
                    <%--</c:forEach>--%>
                    <%--</ul>--%>
                    <%--</div>--%>
                </li>
                <li>
                    <aside><span>应用介绍:</span></aside>
                    <div>
                        <span style="width:700px; word-wrap: break-word;">${appInfo.app.appAbstruct}</span>
                    </div>
                </li>

                <li>
                    <aside><span>演示URL:</span></aside>
                    <div>
                        <a href="">${appInfo.app.appUrl}</a>
                    </div>
                </li>
                <li>
                    <aside><span>演示账号:</span></aside>
                    <div>
                        <span>${appInfo.app.demoAccount}</span>
                    </div>
                </li>
            </ul>
        </section>
        <!--<h3>开发者信息</h3>-->
        <section id="content2">
            <c:if test="${empty appInfo.personal}">
                <ul class="massages">
                    <li>
                        <aside>
                            <span>开发者:</span>
                        </aside>
                        <div>
                            <span>${appInfo.company.businessName}</span>
                        </div>
                    </li>
                    <li>
                        <aside><span>负责人:</span></aside>
                        <div>
                            <span>${appInfo.company.developerName}</span>
                        </div>
                    </li>
                    <li>
                        <aside><span>联系电话:</span></aside>
                        <div><span>${appInfo.company.developerPhone}</span></div>
                    </li>
                </ul>
            </c:if>
            <c:if test="${empty appInfo.company}">
                <ul class="massages">
                    <li>
                        <aside>
                            <span>开发者:</span>
                        </aside>
                        <div>
                            <span>${appInfo.personal.companyName}</span>
                        </div>
                    </li>
                    <li>
                        <aside><span>负责人:</span></aside>
                        <div>
                            <span>${appInfo.personal.name}</span>
                        </div>
                    </li>
                    <li>
                        <aside><span>联系电话:</span></aside>
                        <div><span>${appInfo.personal.contactsPhone}</span></div>
                    </li>
                </ul>
            </c:if>
        </section>
        <section id="content3">
            <ul class="massages">
                <%--<li>--%>
                    <%--<aside style="width:170px;">--%>
                        <%--<span>应用标识(AppKey):</span>--%>
                    <%--</aside>--%>
                    <%--<div>--%>
                        <%--<span>${appInfo.app.appSecret}</span>--%>
                    <%--</div>--%>
                <%--</li>--%>
                <li>
                    <aside style="width:170px;"><span>Client Id:</span></aside>
                    <div>
                        <span>${appInfo.app.clientId}</span>
                    </div>
                </li>
                <li>
                    <aside style="width:170px;"><span>Client Secret:</span></aside>
                    <div><span>${appInfo.app.clientSecret}</span></div>
                </li>
            </ul>
        </section>
    </div>
</main>
<c:if test="${mark!=1}">
    <footer>
        <button class="check-btn1" onclick="window.location.replace('${ctx}/admin/index')">返回</button>
        <button class="check-btn2">不通过</button>
        <button class="check-btn3">通过</button>
    </footer>
</c:if>
<c:if test="${mark==1}">
    <footer>
        <footer>
            <button class="check-btn1" onclick="javascript:history.go(-1)" style="border: none">返回</button>
        </footer>
    </footer>
</c:if>
<script src="${ctx}/static/another-js/jquery-1.9.1.min.js"></script>
<script src="${ctx}/static/another-js/zoom.min.js"></script>
<script src="${ctx}/static/another-js/layer.js"></script>
<script>
    $('.check-btn3').click(function () {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定通过该应用的审核？</p>',
            move: false,
            btn1: function () {
                var appId = $("#appId").text();
                $.post("${ctx}/check/app/Pass", {
                    appId: appId
                }, function (data) {
                    webToast(data.data, "top", 1300);
                    setTimeout(function () {
                                window.opener.location.reload();
                                window.close();
                            },
                            1300)
                });
            }
        })
    })
    $('.check-btn2').click(function () {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '310px'],
            content: '<textarea id="textRemark" style="border-radius:5px;padding:5px;outline: none;" cols="45" rows="11" placeholder="请输入不通过的原因"></textarea>',
            move: false,
            btn1: function () {
                var appId = $("#appId").text();
                var textarea = $("#textRemark").val();
                $.post("${ctx}/check/app/noPass", {
                    appId: appId,
                    text: textarea
                }, function (data) {
                    webToast(data.data, "top", 1300);
                    setTimeout(function () {
                                window.opener.location.reload();
                                window.close();
                            },
                            1300)
                });
            }
        })
    })

    $('#aside li').click(function () {
        var data = $(this).attr('data');
        var content = $('#check-detail-content section');
        content.map(function (index, key) {
            console.log(key)
            if (data == key.id) {
                $(key).show();
                $(key).siblings().hide();
            }
        })
        $(this).addClass('active').siblings().removeClass('active')
    })
</script>
</body>
</html>