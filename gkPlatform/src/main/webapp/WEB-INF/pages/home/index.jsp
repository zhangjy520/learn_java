<%@ page import="java.util.HashMap" %>
<%@ include file="../common/headerXf.jsp" %>
<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <style>
        .layui-layer.layui-layer-page.yourclass.layer-anim {
            top: 30% !important;
        }
    </style>
</head>
<body style="background-color: #f2f2f2 !important;">
<div class="container">
    <div class="row">
        <!--TODO 左边一列-->
        <aside id="left-side" class="col-sm-3">
            <!--TODO 用户信息-->
            <div id="user">
                <%-- <p>上次登录时间:2016年7月5日</p>--%>
                <div class="row">
                        <span style="display:block;margin:0 auto;width:102px;height:102px;border:2px solid #fff;overflow:hidden;border-radius: 50%;">
                            <%--<img src="${ctxStaticNew}/images/user-icon.png" alt=""/>--%>
                            <c:if test="${gukeer:emptyString(loginUser.photoUrl)}"> <img
                                    style="width:102px;height:102px"
                                    src="${ctx}/file/pic/show?picPath=${defaultHead}" alt="">
                            </c:if>
                                <c:if test="${gukeer:notEmptyString(loginUser.photoUrl)}">
                                    <img style="width:102px;height:102px"
                                         src="${ctx}/file/pic/show?picPath=${loginUser.photoUrl}" alt=""/>
                                </c:if>
                            <%--<img style="width:102px;height:102px" src="${ctx}/file/pic/show?picPath=${loginUser.photoUrl}" alt=""/>--%>
                        </span>
                </div>
                <h2>${userName}</h2>

                <c:choose>
                    <c:when test="${userType == 3}">
                        <h2>${bottomName}</h2>
                    </c:when>
                    <c:otherwise>
                        <h3>${bottomName}</h3>
                    </c:otherwise>
                </c:choose>

                <div class="row personal-msg">
                    <div class="col-sm-4">
                        <h3 id="guanzhu"></h3>
                        <span>关注</span>
                    </div>
                    <div class="col-sm-4">
                        <h3 id="fensi"></h3>
                        <span>粉丝</span>
                    </div>
                    <div class="col-sm-4">
                        <h3 id="dongtai"></h3>
                        <span>动态</span>
                    </div>
                </div>
            </div>
            <!--TODO 通知公告、消息、邮件-->
            <c:if test="${userType == 1}">
                <div id="news-block">
                    <h1>
                        <span></span>
                        通知公告
                    </h1>
                    <div class="news-box">
                        <ul>
                            <c:choose>
                                <c:when test="${empty notifyList}">
                                    暂无通知
                                </c:when>
                                <c:when test="${fn:length(notifyList) > 6}">
                                    <c:set var="endIndex" value="6"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="endIndex" value="${fn:length(notifyList)}"></c:set>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach begin="0" end="${endIndex}" items="${notifyList}" var="notify">
                                <li onclick="window.open('${ctx}/notify/details/${notify.notifyId}')">
                                    <p
                                            <c:choose>
                                                <c:when test="${notify.readFlag==1}">
                                                    class="read"
                                                </c:when>
                                                <c:otherwise>
                                                    class="not-read"
                                                </c:otherwise>
                                            </c:choose>
                                    >

                                        <span>
                                            【${notify.columName}】
                                            ${fn:substring(notify.title,0, 15)}
                                        <c:if test="${notify.title.length()>15-notify.columName.length()}">
                                            .....
                                        </c:if>
                                        </span>


                                        <span>${gukeer:intervalNowTimeToView(notify.publishTime)}</span>
                                    </p>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="get-more">
                            <a onclick="window.open('${ctx}/notify/index')">进入通知公告 ></a>
                        </div>
                    </div>
                </div>
            </c:if>
            <!--TODO 板块-->
            <div id="plate">
                <h1>
                    <span></span>
                    板块
                </h1>
                <div class="plate-items">
                    <ul id="plate_ajax">
                        暂无板块
                    </ul>
                    <div class="get-more">
                        <a id="plate_a">进入全部板块></a>
                    </div>
                </div>
            </div>
        </aside>
        <main id="main-part" class="col-sm-6">
            <div id="app-frequent">
                <h3 class="get-more">
                    <span></span>
                    系统应用
                    <a onclick="window.open('${ctx}/app/appstore/index')">进入应用商店 ></a>
                </h3>

                <div class="app-box">
                    <ul class="row">
                        <c:if test="${empty defaultAppList}">
                            暂无应用
                        </c:if>
                        <c:forEach items="${defaultAppList}" var="defaultApp" varStatus="status">
                            <li style="padding-top: 12px" onclick="
                            <c:choose>
                                <%--<c:when test="${gukeer:isContainsString(defaultApp.webUrl,'http')}">window.open('${defaultApp.webUrl}?${loginUser.username}=${snsMac}&picUrl=${fullPath}/file/pic/show?picPath=${loginUser.photoUrl}${moocUrl}')</c:when>--%>
                            <c:when test="${gukeer:isContainsString(defaultApp.webUrl,'http')}">window.open('${ctx}/third/party/page?appId=${defaultApp.id}&backUrl='+'${fullPath}')</c:when>
                            <c:when test="${gukeer:emptyString(defaultApp.webUrl)}">javascript:hintYou(400,202,'温馨提示','您没有权限使用该应用，如有需要请联系管理员。')</c:when>
                            <c:otherwise>window.open('${ctx}/${defaultApp.webUrl}?appId=${defaultApp.id}')</c:otherwise>
                                    </c:choose>">
                                <span>
                                     <c:if test="${defaultApp.sfczxmz==0}"><img class="img-responsive"
                                                                                src="${ctx}/file/pic/show?picPath=${defaultApp.iconUrl}"/></c:if>
                                     <c:if test="${defaultApp.sfczxmz==1}"><img class="img-responsive"
                                                                                src="${ctxStatic}/image/appDetails/${defaultApp.iconUrl}"/></c:if>
                                     <c:if test="${defaultApp.sfczxmz==2}"><img class="img-responsive"
                                                                                src="${defaultApp.iconUrl}"/></c:if>
                                </span>
                                <p>${defaultApp.name}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div id="weibo">
                <h3 class="get-more">
                    <span></span>
                    微博
                    <a id="weibo_a">进入班级圈
                        ></a>
                </h3>
                <div class="weibo-content">
                    <ul id="weibo_ajax">
                        暂无微博
                    </ul>
                </div>
            </div>
        </main>

        <aside id="right-side" class="col-sm-3">
            <div id="calendar">
                <div class="calendar-view">
                    <aside>
                        <span>${gukeer:getYmdw(2)}月</span>
                        <p>${gukeer:getYmdw(3)}</p>
                    </aside>
                    <main>
                        <p>${gukeer:getYmdw(1)}</p>
                        <p id="weather"></p>
                    </main>
                </div>
            </div>
            <div id="topic">
                <h1>
                    <span></span>
                    话题
                </h1>
                <div class="topic-content">
                    <ul id="topic_ajax">
                        暂无话题
                    </ul>
                    <div class="get-more">
                        <a id="topic_a">进入热门话题></a>
                    </div>
                </div>

            </div>
            <div id="tiezi">
                <h1>
                    <span></span>
                    帖子
                </h1>
                <div class="tiezi-content">
                    <ul id="tiezi_ajax">
                        暂无帖子
                    </ul>
                    <div class="get-more">
                        <a id="tiezi_a">进入全部帖子></a>
                    </div>
                </div>
            </div>
        </aside>
    </div>
</div>
<script>
    getWeather();
    bankuai();
    weibo();
    huati();
    tiezi();
    dynamic();

    function dynamic() {
        $.ajax({
            type: "post",
            url: "${ctx}/home/sns/5",
            data: {},
            dataType: "json",
            success: function (res) {
                var list = res.data.list;
                if (list.length > 0) {
                    var obj = list[0];
                    $("#guanzhu").html(obj.fans);
                    $("#fensi").html(obj.follow);
                    $("#dongtai").html(obj.wb_num);
                }
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
    function tiezi() {
        $.ajax({
            type: "post",
            url: "${ctx}/home/sns/4",
            data: {},
            dataType: "json",
            success: function (res) {
                var list = res.data.list;
                var snsUrl = res.data.snsUrl;
                var snsMac = res.data.snsMac;
                var param = res.data.loginUser.username + "=" + snsMac;
                var fullPath = "${fullPath}";

                $("#tiezi_a").click(function () {
                    window.open(snsUrl + '/testsns/index.php/forum/index/index?' + param + "&picUrl=" + fullPath + "/file/pic/show?picPath=" + res.data.loginUser.photoUrl);
                });

                var html = "";
                for (var i = 0; i < list.length; i++) {
                    var url = list[i].url + '&' + param + "&picUrl=" + fullPath + "/file/pic/show?picPath=" + res.data.loginUser.photoUrl;
                    var create_time = getLocalTime(list[i].create_time);

                    html += '<li class="row" onclick="window.open(\'' + url + '\')">' +
                            '<p class="col-sm-3"><span><img src="' + list[i].path + '"/></span></p>' +
                            '<div class="col-sm-9">' +
                            '<h3><span>【' + list[i].big_title + '】' + list[i].title + '</span></h3>' +
                            '<p><span>' + list[i].nickname + '&nbsp;发表：' + create_time + '</span></p>' +
                            '<div>' +
                            '<span>阅读（' + list[i].view_count + '）</span>' +
                            '<span>回复（' + list[i].reply_count + '）</span>' +
                            '</div></div></li>';
                }
                $("#tiezi_ajax").html(html);
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
    function huati() {
        $.ajax({
            type: "post",
            url: "${ctx}/home/sns/1",
            data: {},
            dataType: "json",
            success: function (res) {
                var list = res.data.list;
                var snsUrl = res.data.snsUrl;
                var snsMac = res.data.snsMac;
                var param = res.data.loginUser.username + "=" + snsMac;
                var fullPath = "${fullPath}";

                $("#topic_a").click(function () {
                    window.open(snsUrl + '/testsns/index.php/weibo/topic/topic?' + param + "&picUrl=" + fullPath + "/file/pic/show?picPath=" + res.data.loginUser.photoUrl);
                });

                var html = "";
                for (var i = 0; i < list.length; i++) {
                    var class_name = "";
                    if (i < 3) {
                        class_name = "hot-topic";
                    }
                    var url = list[i].url + '&' + param;
                    html += '<li class="row" onclick="window.open(\'' + url + '\')">' +
                            '<p class="col-sm-1"><span class="' + class_name + '">' + (i + 1) + '</span></p>' +
                            '<div class="col-sm-11">' +
                            '<span>#' + list[i].name + '#</span>' +
                            '<span>' + list[i].weibo_num + '</span>' +
                            '</div></li>';

                }
                $("#topic_ajax").html(html);
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
    function weibo() {
        $.ajax({
            type: "post",
            url: "${ctx}/home/sns/2",
            data: {},
            dataType: "json",
            success: function (res) {
                var list = res.data.list;
                var snsUrl = res.data.snsUrl;
                var snsMac = res.data.snsMac;
                var loginUser = res.data.loginUser;
                var fullPath = "${fullPath}";
                var param = loginUser.username + "=" + snsMac;

                $("#weibo_a").click(function () {
                    window.open(snsUrl + '/testsns/index.php/weibo/index/index?' + param);
                });

                var html = "";
                for (var i = 0; i < list.length; i++) {
                    var type = list[i].type;
                    var url = list[i].url + "&" + param + "&picUrl=" + fullPath + "/file/pic/show?picPath=" + loginUser.photoUrl;
                    if (type == "feed") {

                        var fragment = list[i].topic_name;
                        var pian = "";
                        var pian2 = "";

                        if (fragment != null && fragment != undefined && fragment != '') {
                            pian = '<span class="topic">#' + fragment + '#</span>'
                        }

                        if (type == 'image') {
                            pian2 = '<span class="img"></span>';
                        }
                        var create_time = getLocalTime(list[i].create_time);
                        html += '<li class="row" onclick="window.open(\'' + url + '\')">' +
                                '<div class="col-sm-2"><span><img class="img-responsive" src="' + list[i].path + '"/>' +
                                '</span></div>' +
                                '<div class="col-sm-10"><h3>' + list[i].nickname + '<i>Lv1实习</i></h3>' +
                                '<div class="comment-time"><span>' + create_time + '</span></div>' +
                                '<div class="comment-floor"><p>' + pian + list[i].content + '</p></div>' +
                                '<div class="comment-operation">' + pian2 +
                                '<div>' +
                                '<span><i></i>' + list[i].likes + '</span>' +
                                '<span><i></i>' + list[i].comment_count + '</span>' +
                                '<span><i></i>' + list[i].repost_count + '</span>' +
                                '</div></div></div></li>';

                    } else if (type == "image" || (list[i].old_content != null && list[i].old_content != undefined && list[i].old_content != '')) {
                        var pian2 = "";
                        if (type == 'image') {
                            pian2 = '<span class="img"></span>';
                        }
                        var pian3 = "";
                        if (list[i].old_content != null && list[i].old_content != undefined && list[i].old_content != '') {
                            pian3 = '<div class="floor-box"><h5>@' + list[i].old_nickname + '</h5><p>' + list[i].old_content + '</p><div><span>' + list[i].orignal_time + '</span></div></div>';
                        }

                        var create_time = getLocalTime(list[i].create_time)
                        html += '<li class="row" onclick="window.open(\'' + url + '\')">' +
                                '<div class="col-sm-2"><span><img class="img-responsive" src="' + list[i].path + '"/>' +
                                '</span></div>' +
                                '<div class="col-sm-10"><h3>' + list[i].nickname + '<i>Lv1实习</i></h3>' +
                                '<div class="comment-time"><span>' + create_time + '</span></div>' +
                                '<div class="comment-floor"><p>' + list[i].content + '</p>' + pian3 +
                                '</div>' +
                                '<div class="comment-operation">' + pian2 +
                                '<div>' +
                                '<span><i></i>' + list[i].likes + '</span>' +
                                '<span><i></i>' + list[i].comment_count + '</span>' +
                                '<span><i></i>' + list[i].repost_count + '</span>' +
                                '</div></div></div></li>';
                    }

                }
                $("#weibo_ajax").html(html);
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
    function bankuai() {
        $.ajax({
            type: "post",
            url: "${ctx}/home/sns/3",
            data: {},
            dataType: "json",
            success: function (res) {
                var list = res.data.list;
                var param = res.data.loginUser.username + "=" + res.data.snsMac;
                $("#plate_a").click(function () {
                    window.open(res.data.snsUrl + '/testsns/index.php/forum/index/lists?' + param);
                });

                var html = "";
                for (var i = 0; i < list.length; i++) {
                    var url = list[i].url + '&' + param;
                    html += '<li onclick="window.open(\'' + url + '\')">' +
                            '<span><img class="img-responsice" src="' + list[i].path + '"/></span>' +
                            '<div>' +
                            '<p>' + list[i].title + '</p>' +
                            '<span>帖子：' + list[i].post_count + '</span>' +
                            '</div>' +
                            '</li>';

                }
                $("#plate_ajax").html(html);
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
    function getWeather() {
        $.ajax({
            type: "post",
            url: "${ctx}/home/weather",
            data: {},
            dataType: "json",
            success: function (res) {
                //$("#weather").html(res.data.status1 + "  " + res.data.temperature2 + "℃/" + res.data.temperature1 + "℃");
                if (res.data.tmp != null)
                    $("#weather").html(res.data.cond.txt_d + "  " + res.data.tmp.min + "℃/" + res.data.tmp.max + "℃");
            },
            error: function (e) {
                console.log(e);
            }
        });
    }

    function getLocalTime(nS) {
        return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
    }
</script>
</body>
</html>