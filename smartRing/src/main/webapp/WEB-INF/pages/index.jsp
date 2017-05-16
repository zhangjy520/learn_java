<%@ include file="common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="${ctxStatic}/css/index.css"/>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/excanvas.compiled.js"></script>
    <script src="${ctxStatic}/js/plugins/exporting.js"></script>
    <script src="${ctxStatic}/js/plugins/exporting.src.js"></script>
</head>
<body>
<!--面包屑导航 页面的位置-->
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav active"><a href="index.html">首页</a></li>
        <!--<li class="child-nav"><a href="#">首页</a></li>-->
    </ul>
</header>
<!--页面-->
<div id="bodyContainer">
    <main id="index-main">
        <section id="i-1-line" class="clear">
            <div class="lf module-bg">
                <p>运动时间</p>
                <h3>${gukeer:minConvertDayHourMin(healthLatest.sportTime)}</h3>
                <c:if test="${poorThen.sportPoor > 0}">
                    <span class="duration-up">${poorThen.sportPoor}分</span>
                </c:if>
                <c:if test="${poorThen.sportPoor < 0}">
                    <span class="duration-down">${poorThen.sportPoor}分</span>
                </c:if>
                <c:if test="${gukeer:emptyString(poorThen.sportPoor) or poorThen.sportPoor == 0}">
                    <span class="duration-m"></span>
                </c:if>
            </div>
            <div class="lf module-bg" style="margin:0 25px;">
                <p>入睡时间</p>
                <h3>${gukeer:asleepTime(healthLatest.asleepTime)}</h3>
                <c:if test="${poorThen.asleepPoor > 0}">
                    <span class="duration-up">${poorThen.asleepPoor}分</span>
                </c:if>
                <c:if test="${poorThen.asleepPoor < 0}">
                    <span class="duration-down">${poorThen.asleepPoor}分</span>
                </c:if>
                <c:if test="${gukeer:emptyString(poorThen.asleepPoor) or poorThen.asleepPoor == 0}">
                    <span class="duration-m"></span>
                </c:if>
            </div>
            <div class="lf module-bg">
                <p>睡眠时长</p>
                <h3>${gukeer:minConvertDayHourMin(healthLatest.sleepLong)}</h3>
                <c:if test="${poorThen.sleepLongPoor > 0}">
                    <span class="duration-up">${poorThen.sleepLongPoor}分</span>
                </c:if>
                <c:if test="${poorThen.sleepLongPoor < 0}">
                    <span class="duration-down">${poorThen.sleepLongPoor}分</span>
                </c:if>
                <c:if test="${gukeer:emptyString(poorThen.sleepLongPoor) or poorThen.sleepLongPoor == 0}">
                    <span class="duration-m"></span>
                </c:if>
            </div>
        </section>
        <section id="i-2-line" class="clear">
            <div class="lf module-bg">
                <p>运动时长</p>
                <div id="exercise-duration" style="width:230px;height:230px;"></div>
                <footer class="clear">
                    <c:if test="${fn:length(standard)>1}">
                        <span class="lf">不达标</span>
                        <span class="rl">达标</span>
                    </c:if>
                    <c:if test="${fn:length(standard) == 1}">
                        <span class="lf">低于${gukeer:minConvertDayHourMin(standardMap.sportStandard)}</span>
                        <span class="rl">高于${gukeer:minConvertDayHourMin(standardMap.sportStandard)}</span>
                    </c:if>
                </footer>
            </div>
            <div class="lf module-bg" style="margin:0 25px;">
                <p>入睡时间</p>
                <div id="asleep-time" style="width:230px;height:230px;"></div>
                <footer class="clear">
                    <c:if test="${fn:length(standard)>1}">
                        <span class="lf">不达标</span>
                        <span class="rl">达标</span>
                    </c:if>
                    <c:if test="${fn:length(standard) == 1}">
                        <span class="lf">晚于${gukeer:asleepTime(standardMap.asleepStandard)}</span>
                        <span class="rl">早于${gukeer:asleepTime(standardMap.asleepStandard)}</span>
                    </c:if>
                </footer>
            </div>
            <div class="lf module-bg">
                <p>睡眠时长</p>
                <div id="sleep-duration" style="width:230px;height:230px;"></div>
                <footer class="clear">
                    <c:if test="${fn:length(standard)>1}">
                        <span class="lf">不达标</span>
                        <span class="rl">达标</span>
                    </c:if>
                    <c:if test="${fn:length(standard) == 1}">
                        <span class="lf">低于${gukeer:minConvertDayHourMin(standardMap.sleepLongStandard)}</span>
                        <span class="rl">高于${gukeer:minConvertDayHourMin(standardMap.sleepLongStandard)}</span>
                    </c:if>
                </footer>
            </div>
        </section>
        <section id="i-3-line">
            <div class="module-bg-full clear">
                <p style="border-bottom:1px solid #ddd;">体育测试</p>
                <div style="padding:23px 25px 0;">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>项目</th>
                            <th>平均成绩</th>
                            <th>最低成绩</th>
                            <th>最高成绩</th>
                            <th>成绩单位</th>
                            <th>及格率</th>
                            <th>优秀率</th>
                            <th>年级</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sportList}" var="sport">
                            <tr>
                                <td>${sport.itemName}</td>
                                <c:choose>
                                    <c:when test="${sport.itemUnit.indexOf('分')>=0}">
                                        <td>${gukeer:unitTranslate(sport.avgMark)}</td>
                                        <td>${gukeer:unitTranslate(sport.minMark)}</td>
                                        <td>${gukeer:unitTranslate(sport.MaxMark)}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${sport.avgMark}</td>
                                        <td>${sport.minMark}</td>
                                        <td>${sport.MaxMark}</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${sport.itemUnit}</td>
                                <td>${sport.jgl}</td>
                                <td>${sport.yxl}</td>
                                <td>${gukeer:translate(sport.njName)}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <footer class="pagination clear">
                    <div class="lf">
                        共${pageInfo.total}条记录，每页显示10
                        <%--<select>
                            <option value="">10</option>
                            <option value="">20</option>
                            <option value="">50</option>
                        </select>--%>
                        条记录
                    </div>
                    <div class="rl fenY">
                        <script>
                            $(".fenY").createPage({
                                pageCount: ${pageInfo.pages},
                                current: ${pageInfo.pageNum},
                                backFn: function (p) {
                                    window.location.href = '${ctx}/index?pageNum=' + p;
                                }
                            });
                        </script>
                    </div>
                </footer>
            </div>
        </section>
    </main>
</div>

<script>
    $("#indexPage").addClass("active");
    $("#indexPage ul li>a").addClass("active");

    $('#exercise-duration').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>',
            headerFormat: '<span style="font-size:12px">{point.key}</span><table><br>',
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'purple'
                    }
                }
            }
        },
        series: [{
            name: '运动时长',
            colorByPoint: true,
            data: [{
                <c:if test="${fn:length(standard)>1}">
                name: '不达标',
                </c:if>
                <c:if test="${fn:length(standard) == 1}">
                name: '低于${gukeer:minConvertDayHourMin(standardMap.sportStandard)}',
                </c:if>
                y: ${100-healthLatest.ydsc},
                color: '#D4D2D2'
            }, {
                <c:if test="${fn:length(standard)>1}">
                name: '达标',
                </c:if>
                <c:if test="${fn:length(standard) == 1}">
                name: '高于${gukeer:minConvertDayHourMin(standardMap.sportStandard)}',
                </c:if>
                y: ${healthLatest.ydsc},
                color: '#FFAF25',
                //selected: false
            }]
        }],
//        下载图片
        exporting:{
            filename:'chart',
            url:'${ctx}/file//export/birt'
        },
        credits: {enabled: false}
    });
    $('#asleep-time').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            headerFormat: '<span style="font-size:12px">{point.key}</span><table><br>',
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'purple'
                    }
                }
            }
        },
        series: [{
            name: '入睡时间',
            colorByPoint: true,
            data: [{
                <c:if test="${fn:length(standard)>1}">
                name: '不达标',
                </c:if>
                <c:if test="${fn:length(standard) == 1}">
                name: '晚于${gukeer:asleepTime(standardMap.asleepStandard)}',
                </c:if>
                y: ${100-healthLatest.rssj},
                color: '#D4D2D2'
            }, {
                <c:if test="${fn:length(standard)>1}">
                name: '达标',
                </c:if>
                <c:if test="${fn:length(standard) == 1}">
                name: '早于${gukeer:asleepTime(standardMap.asleepStandard)}',
                </c:if>
                y: ${healthLatest.rssj},
                color: '#19BE9D',
                //selected: false
            }]
        }],
        credits: {enabled: false}
    });
    $('#sleep-duration').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            headerFormat: '<span style="font-size:12px">{point.key}</span><table><br>',
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: '#999'
                    }
                },
                showInLegend: false,
            }
        },
        //legend:{itemStyle:{color:'#676767',fontSize:'12px',fontWeight:'normal',fontFamily:'microsoft Yahei'},}
        series: [{
            name: '睡眠时长',
            colorByPoint: true,
            data: [{
                <c:if test="${fn:length(standard)>1}">
                name: '不达标',
                </c:if>
                <c:if test="${fn:length(standard) == 1}">
                name: '低于${gukeer:minConvertDayHourMin(standardMap.sleepLongStandard)}',
                </c:if>
                y: ${100-healthLatest.smsc},
                color: '#D4D2D2',
            }, {
                <c:if test="${fn:length(standard)>1}">
                name: '达标',
                </c:if>
                <c:if test="${fn:length(standard) == 1}">
                name: '高于${gukeer:minConvertDayHourMin(standardMap.sleepLongStandard)}',
                </c:if>
                y: ${healthLatest.smsc},
                color: '#D571F2'
                //selected: false
            }]
        }],
        credits: {enabled: false},

    });
</script>
</body>
</html>