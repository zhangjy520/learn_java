<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
    <style>
        .offline {
            color: #ff0000;
            background: url(${ctx}/static/img/icon5.png) no-repeat left center;
        }

        .pushing {
            color: #4bab54;
            background: url(${ctx}/static/img/icon8.png) no-repeat left center;
        }

        .online {
            color: #54ab37;
            background: url(${ctx}/static/img/ico3.png) no-repeat left center;
        }
        .updated{
            color: #54ab37;
            background: url(${ctx}/static/img/duihao.png) no-repeat left center;
        }

    </style>
</head>
<body>
<main id="main-tab" style="width:100%;padding:0 25px;">

    <div id="check1" class="app-manage">
        <table>
            <thead>
            <input type="hidden" id="appId" value="${appId}">
            <tr>
                <th width="3%">序号</th>
                <th width="3%">名称</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <c:forEach items="${platformExtentionList}" var="platformExtention" varStatus="status">
            <tbody>
            <tr>
                <td>${status.count}</td>
                <td>${platformExtention.platform.name}</td>
                <td>
                    <c:if test="${platformExtention.isExist == 0 }">
                        <span class="popPush pushing" data-url="${ctx}/app/push/ref"
                              value="${platformExtention.platform.id}">推送至应用平台</span>
                    </c:if>

                    <%--<c:if test="${platformExtention.appStatus == 1 && platformExtention.appOptStatus==4}">--%>
                        <%--<span class="popPush online" &lt;%&ndash;data-url="${ctx}/app/off/line"&ndash;%&gt;--%>
                              <%--value="${platformExtention.platform.id}">上线</span>--%>
                    <%--</c:if>--%>
                    <c:if test="${platformExtention.appStatus == 2 && platformExtention.appOptStatus==0}">
                        <span <%--data-url="${ctx}/app/off/line"--%>
                              value="${platformExtention.platform.id}">已经在任务队列</span>
                    </c:if>
                    <c:if test="${platformExtention.appStatus == 2 && platformExtention.appOptStatus==1}">
                        <span class="popPush updated" <%--data-url="${ctx}/app/off/line"--%>
                              value="${platformExtention.platform.id}">已更新</span>
                    </c:if>
                    <c:if test="${platformExtention.appStatus == 4 && platformExtention.appOptStatus==0}">
                        <span <%--data-url="${ctx}/app/off/line"--%>
                              value="${platformExtention.platform.id}">已经在任务队列</span>
                    </c:if>
                    <c:if test="${platformExtention.appStatus == 1 && platformExtention.appOptStatus==0}">
                        <span <%--data-url="${ctx}/app/off/line"--%>
                                value="${platformExtention.platform.id}">已经在任务队列</span>
                    </c:if>
                    <c:if test="${platformExtention.appStatus == 4 && platformExtention.appOptStatus==1}">
                        <span class="popPush online" data-url="${ctx}/app/push/ref?appStatus=online"
                              value="${platformExtention.platform.id}">上线</span>
                </c:if>
                    <c:if test="${platformExtention.appStatus == 1 && platformExtention.appOptStatus==1}">
                        <span class="popPush updated" data-url="${ctx}/app/push/ref?appStatus=online"
                              value="${platformExtention.platform.id}">已上线</span>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
<script>
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    //上线操作即推送
    parent.$(document).on("click", ".popPush", function () {
        debugger;
        var url = $(this).attr("data-url");
        var appId = $("#appId").val();
        var platformId = $(this).attr('value');
        $.post(url, {
            appId: appId,
            platformId: platformId
        }, function (data) {
            window.location.reload();
        })
    })
    //下线
    parent.$(document).on("click", ".popUnPush", function () {
        var url = $(this).attr("data-url");
        var appId = $("#appId").val();
        var platformId = $("#platformId").val();
        $.post(url, {
            appId: appId,
            platformId: platformId
        }, function (data) {
            window.location.reload();
        })
    })
</script>
</html>
