<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>体育测试</title>
    <link rel="stylesheet" href="${ctxStatic}/css/sportstest.css"/>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav  active"><a href="sportsTest.html">体育测试</a></li>
    </ul>
</header>
<main class="clear fix-width" id="sportstest">
    <section class="module-bg lf">
        <p>50米跑</p>
        <main class="clear">
            <div id="fifty" class="lf" style="width:280px;height:280px;"></div>
            <ul class="rl">
                <li>及格</li>
                <li>良好</li>
                <li>不及格</li>
                <li>优秀</li>
            </ul>
        </main>
        <div>
            <table class="table">
                <tr>
                    <td>最低成绩</td>
                    <td>10.6秒</td>
                    <td>43分</td>
                </tr>
                <tr>
                    <td>最高成绩</td>
                    <td>10.6秒</td>
                    <td>43分</td>
                </tr>
                <tr>
                    <td>平均成绩</td>
                    <td>10.6秒</td>
                    <td>43分</td>
                </tr>
            </table>
        </div>

    </section>
    <section class="module-bg rl">
        <p>50米跑</p>
        <main class="clear">
            <div id="pull-up" class="lf" style="width:280px;height:280px;"></div>
            <ul class="rl">
                <li>及格</li>
                <li>良好</li>
                <li>不及格</li>
                <li>优秀</li>
            </ul>
        </main>
        <div>
            <table class="table">
                <tr>
                    <td>最低成绩</td>
                    <td>3个</td>
                    <td>43分</td>
                </tr>
                <tr>
                    <td>最高成绩</td>
                    <td>24个</td>
                    <td>43分</td>
                </tr>
                <tr>
                    <td>平均成绩</td>
                    <td>13个</td>
                    <td>43分</td>
                </tr>
            </table>
        </div>

    </section>
    <section class="module-bg lf">
        <p>50米跑</p>
        <main class="clear">
            <div id="jump" class="lf" style="width:280px;height:280px;"></div>
            <ul class="rl">
                <li>及格</li>
                <li>良好</li>
                <li>不及格</li>
                <li>优秀</li>
            </ul>
        </main>
        <div>
            <table class="table">
                <tr>
                    <td>最低成绩</td>
                    <td>1.21米</td>
                    <td>43分</td>
                </tr>
                <tr>
                    <td>最高成绩</td>
                    <td>1.82米</td>
                    <td>43分</td>
                </tr>
                <tr>
                    <td>平均成绩</td>
                    <td>1.48米</td>
                    <td>43分</td>
                </tr>
            </table>
        </div>

    </section>
    <section class="module-bg rl">
        <p>50米跑</p>
        <main class="clear">
            <div id="sitUp" class="lf" style="width:280px;height:280px;"></div>
            <ul class="rl">
                <li>及格</li>
                <li>良好</li>
                <li>不及格</li>
                <li>优秀</li>
            </ul>
        </main>
        <div>
            <table class="table">
                <tr>
                    <td>最低成绩</td>
                    <td>12个</td>
                    <td>43分</td>
                </tr>
                <tr>
                    <td>最高成绩</td>
                    <td>43个</td>
                    <td>43分</td>
                </tr>
                <tr>
                    <td>平均成绩</td>
                    <td>23个</td>
                    <td>43分</td>
                </tr>
            </table>
        </div>

    </section>
</main>

<script>
    $('#fifty').highcharts({
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
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect:true,
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
            name: '50米跑',
            colorByPoint: true,
            data: [{
                name: '及格',
                y: 40,
                color:'#FF7F50'
            }, {
                name: '良好',
                y: 24,
                color:'#87CEFA',
                //selected: false
            },{
                name: '不及格',
                y: 13.5,
                color:'#DA70D6',
                //selected: false
            },{
                name: '优秀',
                y:22.5,
                color:'#32CD32',
                //selected: false
            }]
        }],
        credits:{enabled:false}
    });

    $('#pull-up').highcharts({
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
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect:true,
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
            name: '50米跑',
            colorByPoint: true,
            data: [{
                name: '及格',
                y: 40,
                color:'#FF7F50'
            }, {
                name: '良好',
                y: 24,
                color:'#87CEFA',
                //selected: false
            },{
                name: '不及格',
                y: 13.5,
                color:'#DA70D6',
                //selected: false
            },{
                name: '优秀',
                y:22.5,
                color:'#32CD32',
                //selected: false
            }]
        }],
        credits:{enabled:false}
    });
    $('#jump').highcharts({
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
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect:true,
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
            name: '50米跑',
            colorByPoint: true,
            data: [{
                name: '及格',
                y: 40,
                color:'#FF7F50'
            }, {
                name: '良好',
                y: 24,
                color:'#87CEFA',
                //selected: false
            },{
                name: '不及格',
                y: 13.5,
                color:'#DA70D6',
                //selected: false
            },{
                name: '优秀',
                y:22.5,
                color:'#32CD32',
                //selected: false
            }]
        }],
        credits:{enabled:false}
    });
    $('#sitUp').highcharts({
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
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect:true,
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
            name: '50米跑',
            colorByPoint: true,
            data: [{
                name: '及格',
                y: 40,
                color:'#FF7F50'
            }, {
                name: '良好',
                y: 24,
                color:'#87CEFA',
                //selected: false
            },{
                name: '不及格',
                y: 13.5,
                color:'#DA70D6',
                //selected: false
            },{
                name: '优秀',
                y:22.5,
                color:'#32CD32',
                //selected: false
            }]
        }],
        credits:{enabled:false}
    });
</script>
</body>
</html>