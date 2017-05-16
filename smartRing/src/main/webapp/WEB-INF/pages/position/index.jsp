<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>实时监控</title>
    <link rel="stylesheet" href="${ctxStatic}/css/common.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/position.css"/>
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/pageDevide.js"></script>
    <script src="${ctxStatic}/js/global.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
                    <span class="lf current-position">
                        当前位置：
                    </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">位置监控</a></li>
        <li class="child-nav active"><a href="#">实时监控</a></li>
    </ul>
</header>
<div id="bodyContainer">
    <main class="fix-width module-bg-full clear position" style="display: block;margin-bottom: 0;">
        <p>区域人数分布</p>
        <aside class="lf" id="chart1" style="width:50%;height:340px;">
        </aside>
        <section class="rl">
            <table class="table">
                <thead>
                <tr>
                    <th>区域名称</th>
                    <th>人数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${stuNum}" var="num">
                    <tr>
                        <td>${num.areaName}</td>
                        <td>${num.num}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>

    </main>
    <main class="fix-width module-bg-full position" style="display: block">
        <style>.position>p.clear:after{left:112px;top:1px;}</style>
        <p class="clear">
            学生当前位置
            <button class="button search rl" id="searchName"></button>
            <input type="text" class="rl search" id="stuName" style="margin-top: -3px;" value="${stuName}">
        </span>
        </p>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th width="12.5%">姓名</th>
                    <%--<th width="12.5%">学段</th>--%>
                    <th width="12.5%">年级</th>
                    <th width="12.5%">班级</th>
                    <th width="12.5%">学号</th>
                    <th width="12.5%">当前所在区域</th>
                    <th width="12.5%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${stuMsgInfo.list}" var="stuMsg">
                    <tr>
                        <td>${stuMsg.studentName}</td>
                        <%--<td>${stuMsg.xdName}</td>--%>
                        <td>${stuMsg.xdName}${gukeer:getGradeNj(stuMsg.nj)}</td>
                        <td>${stuMsg.className}</td>
                        <td>${stuMsg.xh}</td>
                        <td>${stuMsg.areaName}</td>
                        <td>
                            <span class="change" onclick="studetail('${stuMsg.studentName}','${stuMsg.xh}','${stuMsg.studentId}','${stuMsg.areaName}')"<%--onclick="window.open('${ctx}/position/realtime/personalDetail?studentId=${stuMsg.studentId}&xh=${stuMsg.xh}&name=${stuMsg.areaName}')"--%>>查看详情</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
        <footer class="pagination clear">
            <div class="lf">
                共${stuMsgInfo.total}条记录，每页显示
                <select id="pageSize">
                    <option value="10" <c:if test="${stuMsgInfo.pageSize==10}">selected</c:if>>10</option>
                    <option value="20" <c:if test="${stuMsgInfo.pageSize==20}">selected</c:if>>20</option>
                    <option value="50" <c:if test="${stuMsgInfo.pageSize==50}">selected</c:if>>50</option>
                </select>
                条记录
            </div>
            <div class="rl fenY">
                <script>
                    $(".fenY").createPage({
                        pageCount: ${stuMsgInfo.pages},
                        current: ${stuMsgInfo.pageNum},
                        backFn: function (p) {
                            var name = encodeURI(encodeURI($('#stuName').val()));
                            window.location.href = '${ctx}/position/realtime/index?pageSize=' + $('#pageSize').val() + '&pageNum=' + p +"&name="+name;
                        }
                    });
                    $('#pageSize').change(function () {
                        var name = encodeURI(encodeURI($('#stuName').val()));
                        window.location.href = '${ctx}/position/realtime/index?pageSize=' + $('#pageSize').val() + '&pageNum=' + 1 +"&name="+name;
                    })
                    $('#searchName').click(function () {
                        var name = encodeURI(encodeURI($('#stuName').val()));
                        window.location.href = '${ctx}/position/realtime/index?pageSize=' + $('#pageSize').val() + '&pageNum=' + 1 +"&name="+name;
                    })
                </script>
            </div>
        </footer>
    </main>
</div>


<script>
    $("#positionControl").addClass("active");
    $("#positionControl ul li:nth-child(1)>a").addClass("active");

    $('#chart1').highcharts({
        chart: {
            polar: true,
            type: 'line',
            spacingLeft:55
        },
        title: {
            text: '',
            x: -80
        },
        pane: {
            size: '80%'
        },
        xAxis: {
            categories: [ <c:forEach items="${stuNum}" var="num">
                '${num.areaName}',
                </c:forEach>],
            tickmarkPlacement: 'on',
            lineWidth: 0,
        },
        yAxis: {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0,
//            labels:{enabled:false}
            //ceiling:1000
        },
        tooltip: {
//            enabled:false,
            shared: true,
            headerFormat: '<span style="font-size:12px">{point.key}</span><table><br>',
            pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>'
            ,style:{fontSize:'13px'}
        },
        legend: {
            enabled:false
        },
        series: [
            {
                color: '#FFAF25',
                name:'人数',
                data: [<c:forEach items="${stuNum}" var="num">
                    ${num.num},
                    </c:forEach>],
                pointPlacement: 'off',
                marker: {radius: 3,
                    enabled: false
                }
            }],
        credits:{enabled:false}
    });

    function studetail(name,xh,id,areaName) {
        var name = encodeURI(encodeURI(name));
        var areaName = encodeURI(encodeURI(areaName));
        var target = '${ctx}/position/realtime/personalDetail?studentId='+id+'&xh='+xh+'&name='+name + '&areaName=' + areaName;
        window.open(target);
    }
</script>
</body>
</html>