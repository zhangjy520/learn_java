<%--
  Created by IntelliJ IDEA.
  User: lx
  Date: 2017/4/12
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="common/resouces.jsp" %>
    <%--<link rel="stylesheet" href="${ctxStatic}/css/bootstrap-table.css"/>--%>
    <script src="${ctxStatic}/js/bootstrap-table.js"></script>
    <script src="${ctxStatic}/js/bootstrap-table-zh-CN.js"></script>
    <script src="${ctxStatic}/js/echarts.simple.min.js"></script>

</head>
<body>
<%@ include file="common/layout/menu.jsp" %>
<div class="container" style="padding-top: 30px; padding-bottom:60px;">

        <table id="table" class="table" cellpadding="0" cellspacing="0" border='1' borderColor="#e5e5e5" ></table>

</div>

<footer>

</footer>
</body>

<script type="text/javascript">
    $(function () {
        $("#menu_1").addClass("active");
        $.getJSON("http://114.215.29.139:11060/queue/json?callback=?",
                function (data) {
                    $('#table').bootstrapTable({
                        columns: [{
                            field: '$.name',
                            title: '队列名'
                        }, {
                            field: 'stats.$.size',
                            title: '待处理队列数'
                        }, {
                            field: 'stats.$.consumerCount',
                            title: '消费者数量'
                        }, {
                            field: 'stats.$.enqueueCount',
                            title: '消息入队数'
                        }, {
                            field: 'stats.$.dequeueCount',
                            title: '消息出对数'
                        },
                        ],
                        data: data
                    })
                });

        $('.nav-left li').eq(0).addClass('active').siblings().removeClass('active');
    });

</script>
</html>
