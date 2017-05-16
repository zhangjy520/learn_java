<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看详情</title>
    <link rel="stylesheet" href="${ctxStatic}/css/common.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/position.css"/>
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/pageDevide.js"></script>
    <script src="${ctxStatic}/js/global.js"></script>
</head>
<body style="padding-bottom: 80px">
<header id="breadcrumb-nav" class="clear">
                    <span class="lf current-position">
                        当前位置：
                    </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">位置监控</a></li>
        <li class="child-nav"><a href="#">实时监控</a></li>
        <li class="child-nav active"><a href="#">查看详情</a></li>
    </ul>
</header>
<div id="bodyContainer">
    <main class="fix-width module-bg position2">
        <section>
            <p>
                <span>${name}</span>
                <%--<span>初二1班</span>--%>
                <span>${xh}</span>
                <span>当前所在区域 : ${areaName}</span>
            </p>
            <%--<div>--%>
            <%--<span class="checking-in-1">8:00<br>进入学校</span>--%>
            <%--<span class="checking-in-2">11:45<br>离开学校</span>--%>
            <%--<span class="checking-in-3">13:40<br>进入学校</span>--%>
            <%--<span class="checking-in-4">18:00<br>离开学校</span>--%>
            <%--</div>--%>
            <table class="table" style="margin-top: 50px;">
                <thead>
                <tr>
                    <th>区域</th>
                    <th>进入时间</th>
                    <th>离开时间</th>
                    <th>逗留时长(min)</th>
                </tr>
                </thead>
                <thead>
                <c:forEach items="${page}" var="page">
                    <tr>
                        <td>${page.areaName}</td>
                        <td>${gukeer:formatTime(page.lastUpdate)}</td>
                        <td>${gukeer:formatTime(page.leavingTime)}</td>
                        <td>${gukeer:timeDiffer(page.leavingTime,page.lastUpdate)}</td>
                    </tr>
                </c:forEach>
                </thead>
            </table>
            <footer class="pagination clear">
                <div class="lf">
                    共${total}条记录，每页显示
                    <select id="pageSize">
                        <option value="10" <c:if test="${pageSize==10}">selected</c:if>>10</option>
                        <option value="20" <c:if test="${pageSize==20}">selected</c:if>>20</option>
                        <option value="50" <c:if test="${pageSize==50}">selected</c:if>>50</option>
                    </select>
                    条记录
                </div>
                <div class="rl fenY">
                    <script>
                        $(".fenY").createPage({
                            pageCount:${pages},
                            current:${pageNum},
                            backFn: function (p) {
                                load(p);
                            }
                        });
                        $('#pageSize').change(function () {
                            load(1);
                        })
                        function load(p) {
                            var name = encodeURI(encodeURI('${name}'));
                            var areaName = encodeURI(encodeURI('${areaName}'));
                            var target = '${ctx}/position/realtime/personalDetail?pageSize=' + $('#pageSize').val()+'&studentId=${studentId}&xh=${xh}&name='+name+'&pageNum=' + p + '&areaName=' + areaName;
                            window.location.href = target;
                        }
                    </script>
                </div>
            </footer>
        </section>
    </main>
</div>
</body>
</html>