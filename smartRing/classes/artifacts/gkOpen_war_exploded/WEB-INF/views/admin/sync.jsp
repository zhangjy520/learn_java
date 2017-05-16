<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<%@ include file="../common/admin/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/alert.css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
    <style>
        /*  [id*=check] table span {
              display: none;
          }*/
    </style>
</head>
<body>
<%@ include file="../common/admin/menu.jsp" %>
<main id="main-tab">
    <div id="check1">
        <table style="margin-top: 30px;">
            <thead>
            <tr>
                <th width="5%">序号</th>
                <th width="10%">是否有异常</th>
                <th width="8%">应用名称</th>
                <th width="8%">平台名称</th>
                <th width="18%">队列名</th>
                <th width="10%">密钥</th>
                <th width="8%">初始化</th>
                <th width="15%">实时同步</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${syncViews.list}" var="syncView" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <c:if test="${syncView.refPlatformApp.optStatus==0}">
                        <td style="color: #ff0000">存在操作过程异常</td>
                    </c:if>
                    <c:if test="${syncView.refPlatformApp.optStatus==1}">
                        <td>操作过程无异常</td>
                    </c:if>
                    <td>${syncView.appName}</td>
                    <td>${syncView.platformName}</td>
                    <c:if test="${!empty syncView.refPlatformApp.queues}">
                        <td>${syncView.refPlatformApp.queues}</td>
                    </c:if>
                    <c:if test="${empty syncView.refPlatformApp.queues}">
                        <td>
                            <input type="text" id="${syncView.refPlatformApp.id}">
                            <button onclick="add(this)" name="${syncView.refPlatformApp.id}">添加</button>
                        </td>
                    </c:if>
                        <td>${syncView.password}</td>
                    <c:if test="${syncView.refPlatformApp.dataStatus==0}">
                        <td><a style="color: #54ab37" href="${ctx}/admin/sync/init?id=${syncView.refPlatformApp.id}">初始化</a></td>
                    </c:if>
                    <c:if test="${syncView.refPlatformApp.dataStatus==1}">
                        <td>已初始化完成</td>
                    </c:if>
                    <c:if test="${syncView.refPlatformApp.dataStatus==1}">
                    <c:if test="${syncView.refPlatformApp.syncStatus==0}">
                        <td><a style="color: #54ab37" href="${ctx}/admin/sync/open?id=${syncView.refPlatformApp.id}">开启数据实时推送</a></td>
                    </c:if>
                    <c:if test="${syncView.refPlatformApp.syncStatus==1}">
                        <td><a style="color: #54ab37" href="${ctx}/admin/sync/close?id=${syncView.refPlatformApp.id}">关闭数据实时推送</a></td>
                    </c:if>
                    </c:if>
                    <c:if test="${syncView.refPlatformApp.dataStatus==0}">
                        <td>请先初始化数据</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <footer class="clear">
            <p>每条显示${syncViews.pageSize}条，共${syncViews.pages}页，共${syncViews.total}条记录</p>
            <div class="fenY" id="fenYDiv"></div>
        </footer>
    </div>
</main>
</body>

<script>
    function add(a){
        var id = a.name;
        var id_text = '#'+id;
        var val = $(id_text).val();
        $.post("${ctx}/admin/sync/setName", {
            id: id,
            content: val
        }, function (data) {
            location.reload();
            alert("修改成功");
        })
    }
    $(function () {
        $(".fenY").createPage({
            pageCount: ${syncViews.pages},
            current: ${syncViews.pageNum},
            backFn: function (p) {
                window.location.href = "${ctx}/admin/sync?pageNum="+p+"&pageSize=10";
            }
        });
    })

</script>

</html>