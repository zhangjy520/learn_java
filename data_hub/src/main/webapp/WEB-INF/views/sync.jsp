<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<html>
<head>
    <title>同步管理</title>
    <%@ include file="common/resouces.jsp" %>
<body>
<style>
    .label{
        font-weight: normal;
    }
/*    span{
        background: #fff !important;
    }*/
    .label-primary {
        color: #00b4ef;
    }
    .label-success {
        color: #5cb85c;
    }
    .label-danger {
        color: #d9534f;
    }
    .label-default {
        color: #777;
    }
    table{
        font-size: 14px !important;
    }
    td{
        line-height: 35px !important;
        padding: 0 !important;
        vertical-align: middle !important;
    }
    .form-control{
        height: auto !important;
    }
    .spnbtn {
        width: 90px;
        text-align: center;
        border: none;
        color: #fff;
        border-radius: 5px;
        background: rgba(0, 189, 239, .5);
        outline: none;
    }
    .succ{
        background: rgba(92, 184, 92, 0.51) !important;
    }
    .succbefore{
        background: rgba(0, 189, 239, .5) !important;
    }
    .on{
        background: rgba(0, 189, 239, .5) !important;
    }
    .off{
        background: rgba(172, 41, 37, .55)!important;
    }
</style>
<%@ include file="common/layout/menu.jsp" %>
<div class="container">
<main id="main-tab">
        <table id="table" class="table" style="margin-top: 30px;" cellpadding="0" cellspacing="0" border='1' borderColor="#e5e5e5">
            <thead>
            <tr>
                <%--<th width="5%">序号</th>--%>
                <%--<th width="10%">是否有异常</th>--%>
                <th width="8%">应用名称</th>
                <th width="8%">平台名称</th>
                <th width="12%">队列名</th>
                <th width="10%">密钥</th>
                <th width="8%">初始化</th>
                <th width="15%">实时同步</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${syncViews.list}" var="syncView" varStatus="status">
                <tr>
                    <td>${syncView.appName}</td>
                    <td>${syncView.platformName}</td>
                    <c:if test="${!empty syncView.refPlatformApp.queues}">
                        <td>${syncView.refPlatformApp.queues}</td>
                    </c:if>
                    <c:if test="${empty syncView.refPlatformApp.queues}">
                        <td>
                            <div class="input-group" style="width: 100%" >
                                <input type="text" id="${syncView.refPlatformApp.id}" class="form-control" placeholder="add queue name..." >
                                <span class="input-group-btn">
                                    <button  class="btn btn-default" onclick="add(this)" type="button" name="${syncView.refPlatformApp.id}">添加</button>
                                </span>
                            </div>
                        </td>
                    </c:if>
                    <td>${syncView.password}</td>
                    <c:if test="${syncView.refPlatformApp.dataStatus==0}">
                        <td><a href="${ctx}/admin/sync/init?id=${syncView.refPlatformApp.id}"><span class="label label-primary">初始化</span></a></td>
                    </c:if>
                    <c:if test="${syncView.refPlatformApp.dataStatus==1}">
                        <td><span class="label label-success spnbtn succ">初始化完成</span></td>
                    </c:if>
                    <c:if test="${syncView.refPlatformApp.dataStatus==1}">
                        <c:if test="${syncView.refPlatformApp.syncStatus==0}">
                            <td><a href="${ctx}/admin/sync/open?id=${syncView.refPlatformApp.id}"><span class="label label-primary spnbtn on">开启实时推送</span></a></td>
                        </c:if>
                        <c:if test="${syncView.refPlatformApp.syncStatus==1}">
                            <td><a href="${ctx}/admin/sync/close?id=${syncView.refPlatformApp.id}"><span class="label label-danger spnbtn off">关闭实时推送</span></a></td>
                        </c:if>
                    </c:if>
                    <c:if test="${syncView.refPlatformApp.dataStatus==0}">
                        <td><span class="label label-default spnbtn succbefore">请先初始化</span></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    <div>
        <p>每条显示${syncViews.pageSize}条，共${syncViews.pages}页，共${syncViews.total}条记录</p>
        <div class="fenY" id="fenYDiv"></div>
    </div>
        <%--<div>
            <p style="font-size: 12px; float: left;color: #666;">每条显示${syncViews.pageSize}条，共${syncViews.pages}页，共${syncViews.total}条记录</p>
            <div class="fenY" id="fenYDiv" style="display:inline-block;float: right" ></div>
        </div>--%>
    <script>
        $("#menu_3").addClass("active");
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
        });

        function myRefresh () {
            location.reload().setTime(1000);
        }

    </script>
</main>
</div>
<footer></footer>
</body>
</html>
