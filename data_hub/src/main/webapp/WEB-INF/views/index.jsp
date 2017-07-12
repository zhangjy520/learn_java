<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<html>
<head>
    <title>队列信息</title>
    <%@ include file="common/resouces.jsp" %>
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
        $.getJSON("http://121.42.173.162:11060/queue/json?callback=?",
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
