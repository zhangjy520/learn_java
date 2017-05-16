<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--<meta charset="UTF-8">--%>
<%--<title>平台信息</title>--%>
<%--<style>--%>
<%--div {--%>
<%--margin: 0 auto;--%>
<%--width: 400px;--%>
<%--height: 235px;--%>
<%--padding-top: 10px;--%>
<%--}--%>

<%--span {--%>
<%--display: inline-block;--%>
<%--width: 24%;--%>
<%--margin: 10px 0;--%>
<%--}--%>

<%--input {--%>
<%--margin-right: 8px;--%>
<%--}--%>

<%--label {--%>
<%--font-size: 13px;--%>
<%--color: #525252;--%>
<%--cursor: pointer--%>
<%--}--%>
<%--</style>--%>
<%--<script src="${ctx}/static/another-js/jquery-1.9.1.min.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div>--%>
<%--<c:forEach items="${platformList}" var="platform" varStatus="status">--%>
<%--<span>--%>
<%--<input type="checkbox" id="p${status.index+1}" value="${platform.id}" name="platformCheckBox">--%>
<%--<input type="hidden"  value="${platform.id}" class="platformId${status.index}">--%>
<%--<label for="p${status.index+1}">${platform.name}</label>--%>
<%--</span>--%>
<%--</c:forEach>--%>
<%--<input type="hidden" id="appIds" value="${appIds}">--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
<%--$(".layui-layer-btn0").click(function () {--%>
<%--var platformIds = null;--%>
<%--var i =0;--%>
<%--$("input[name='platformCheckBox']:checked").each(function () {--%>
<%--i++;--%>
<%--var singlePlatformId = $(".platformIdInput"+i).val();--%>
<%--platformIds += ','+singlePlatformId;--%>
<%--});--%>
<%--var appId = $("#appId").val();--%>
<%--})--%>
<%--</script>--%>

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
                    <c:if test="${platformExtention.appStatus == 5 &&  platformExtention.appOptStatus ==5}">
                        <span class="app5 popPush popPlatformId" data-url="${ctx}/app/push/ref" value="${platformExtention.platform.id}">推送至云平台</span>
                    </c:if>
                    <c:if test="${platformExtention.appStatus == 0 }">
                        <span class="popUnPush" data-url="${ctx}/app/unpush" >正在推送</span>
                    </c:if>
                    <c:if test="${platformExtention.appStatus == 1 && platformExtention.appOptStatus ==0}">
                        <span class="popUnPush" data-url="${ctx}/app/unpush" style="color: red">推送失败</span>
                    </c:if>
                    <c:if test="${platformExtention.appStatus == 1 && platformExtention.appOptStatus ==1}">
                        <span class="c5 popUnPush" data-url="${ctx}/app/unpush">下线</span>
                    </c:if>
                    <c:if test="${platformExtention.appStatus == 2 && platformExtention.appOptStatus ==0}">
                        <span class="popUnPush" data-url="${ctx}/app/unpush">修改等待推送</span>
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
        var url = $(this).attr("data-url");
        var appId = $("#appId").val();
        var platformId = $(".popPlatformId").attr('value');
        $.post(url, {
            appId: appId,
            platformId: platformId
        }, function (data) {
            window.location.href = postPath + "/" + data.data+"?appId="+appId;
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
            window.location.href = postPath + "/" + data.data;
        })
    })
</script>
</html>
