<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>日常健康</title>
    <link rel="stylesheet" href="${ctxStatic}/css/dailyHealth.css"/>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/laydate.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav active"><a href="dailyHealth.html">日常健康</a></li>
    </ul>
</header>
<main id="dailyHealth" class="fix-width">
    <section id="i-1-line" class="clear">
        <div class="lf module-bg">
            <p>运动时间</p>
            <h3>${statistics.avgSport}min</h3>
            <span class="duration-up">46s</span>
        </div>
        <div class="lf module-bg" style="margin:0 25px;">
            <p>入睡时间</p>
            <h3>${asleep}</h3>
            <span class="duration-down">13min</span>
        </div>
        <div class="lf module-bg">
            <p>睡眠时长</p>
            <%--<h3>8.8h</h3>--%>
            <h3>${statistics.avgSleep}min</h3>
            <span class="duration-m"></span>
        </div>
    </section>
    <section id="i-2-line" class="clear">
        <div class="lf module-bg">
            <p>运动时长</p>
            <div id="exercise-duration" style="width:230px;height:230px;"></div>
            <footer class="clear">
                <span class="lf">低于${sportStandard}</span>
                <span class="rl">高于${sportStandard}</span>
            </footer>
        </div>
        <div class="lf module-bg" style="margin:0 25px;">
            <p>入睡时间</p>
            <div id="asleep-time" style="width:230px;height:230px;"></div>
            <footer class="clear">
                <span class="lf">早于${asleepStandard}</span>
                <span class="rl">晚于${asleepStandard}</span>
            </footer>
        </div>
        <div class="lf module-bg">
            <p>睡眠时长</p>
            <div id="sleep-duration" style="width:230px;height:230px;"></div>
            <footer class="clear">
                <span class="lf">低于${sleepStandard}</span>
                <span class="rl">高于${sleepStandard}</span>
            </footer>
        </div>
    </section>
</main>

<script>
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
            headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
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
                name: '低于${sportStandard} ',
                y: ${100-statistics.moreThanSportTime},
                color:'#D4D2D2'
            }, {
                name: '高于${sportStandard} ',
                y: ${statistics.moreThanSportTime},
                color:'#FFAF25',
                //selected: false
            }]
        }],
        credits:{enabled:false}
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
            headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
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
                name: '晚于${asleepStandard}',
                y: ${100-statistics.moreThanAsleepTime},
                color:'#D4D2D2'
            }, {
                name: '早于${asleepStandard}',
                y: ${statistics.moreThanAsleepTime},
                color:'#19BE9D',
                //selected: false
            }]
        }],
        credits:{enabled:false}
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
            headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
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
        series: [{
            name: '睡眠时长',
            colorByPoint: true,
            data: [{
                name: '低于${sleepStandard} ',
                y: ${100-statistics.moreThanSleepTime},
                color:'#D4D2D2',
            }, {
                name: '高于${sleepStandard} ',
                y: ${statistics.moreThanSleepTime},
                color:'#D571F2'
                //selected: false
            }]
        }],
        credits:{enabled:false},

    });
</script>
</body>
</html>