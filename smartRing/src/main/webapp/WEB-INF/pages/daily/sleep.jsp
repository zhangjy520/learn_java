<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>睡眠统计</title>
    <link rel="stylesheet" href="${ctxStatic}/css/dailyHealth.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/daterangepicker.min.css">
    <script src="${ctxStatic}/js/plugins/moment.js"></script>
    <script src="${ctxStatic}/js/plugins/jquery.daterangepicker.min.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <!--<script src="js/plugins/laydate.js"></script>-->
    <!--<script src="js/plugins/jquery.daterangepicker.js"></script>-->
</head>
<body>
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a>日常健康</a></li>
        <li class="child-nav  active"><a>睡眠统计</a></li>
    </ul>
</header>
<div id="bodyContainer">
    <section class="items-selector module-bg fix-width" style="margin-bottom:25px;">
        <div class="clear">
            <span class="lf">年级</span>
            <ul id="njSelect" class="lf clear select-items">
                <li class="active" data-1="0">全部</li>
                <c:forEach items="${cascadingViews}" var="view">
                    <c:if test="${view.pid eq '-10'}">
                        <li data-1="${view.id}">${view.name}</li>
                    </c:if>
                </c:forEach>
            </ul>
            <div class="header-select-more">更多</div>
        </div>
        <div class="clear">
            <span class="lf">班级</span>
            <ul class="lf clear select-items" id="bjSelect">
                <li class="active" data-1="0">全部</li>
            </ul>
            <div class="header-select-more">更多</div>
        </div>
        <div class="clear">
            <span class="lf">时间</span>
            <ul id='timeSelect' class="lf clear select-items" style="margin-bottom:0;">
                <li data-1="0" name="time" class="active">过去一天</li>
                <li data-1="1" name="time">过去一周</li>
                <li data-1="2" name="time">过去一月</li>
                <li>
                    <input type="text" id="date-range">
                </li>
            </ul>
        </div>
    </section>
    <main id="sleep-statistic" class="fix-width">
        <section class="lf module-bg yundongliang">
            <p>睡眠情况</p>
            <div id="shuimianliang" style="width:465px;height:350px;"></div>
            <table>
                <tr>
                    <td>
                        <ul>
                            <li><span>入睡时间</span></li>
                            <li><span id="asleep">${asleep}</span></li>
                            <li><span id="asleepTimeDifference">${diffStatistics.avgAsleepTime}min</span></li>
                            <li id="caloriesStatus">
                                <c:if test="${diffStatistics.avgAsleepTime > 0}"><i class="pull-u"></i></c:if>
                                <c:if test="${diffStatistics.avgAsleepTime < 0}"><i class="pull-d"></i></c:if>
                                <c:if test="${diffStatistics.avgAsleepTime == 0}"><i class="pull-m"></i></c:if>
                            </li>

                        </ul>
                    </td>
                    <td>
                        <ul>
                            <li><span>深睡眠时长</span></li>
                            <li><span id="avgDeepSleep">${gukeer:getF(statistics.avgDeepSleep)}min</span></li>
                            <li><span id="deepSleepDifference">${gukeer:getF(diffStatistics.avgDeepSleep)}min</span>
                            </li>
                            <li id="distanceStatus">
                                <c:if test="${diffStatistics.avgDeepSleep > 0}"><i class="pull-u"></i></c:if>
                                <c:if test="${diffStatistics.avgDeepSleep < 0}"><i class="pull-d"></i></c:if>
                                <c:if test="${diffStatistics.avgDeepSleep == 0}"><i class="pull-m"></i></c:if>
                            </li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>
                        <ul>
                            <li><span>睡眠时长</span></li>
                            <li><span id="avgSleep">${gukeer:getF(statistics.avgSleep)}min</span></li>
                            <li><span id="sleepDifference">${gukeer:getF(diffStatistics.avgSleep)}min</span>
                            </li>
                            <li id="sportstatus">
                                <c:if test="${diffStatistics.avgSleep > 0}"><i class="pull-u"></i></c:if>
                                <c:if test="${diffStatistics.avgSleep < 0}"><i class="pull-d"></i></c:if>
                                <c:if test="${diffStatistics.avgSleep == 0}"><i class="pull-m"></i></c:if>
                            </li>
                        </ul>
                    </td>
                    <td>
                        <ul>
                            <li><span>睡眠质量</span></li>
                            <li><span id="avgSleepQuality">0.0</span></li>
                            <li><span id="sleepQualityDifference">0.0</span></li>
                            <li id="walkStatus">
                                <i class="pull-m"></i>
                            </li>
                        </ul>
                    </td>
                </tr>
            </table>
        </section>
        <section class="rl module-bg shuimiansj">
            <p>睡眠时长与入睡时间</p>
            <aside class="lf">
                <div id="rushuisj" style="width:200px;height:240px;margin:0 auto;"></div>
                <footer class="clear">
                    <span class="lf" id="later">晚于${asleepStandard}</span>
                    <span class="rl" id="earlier">早于${asleepStandard}</span>
                </footer>
            </aside>
            <aside class="rl">
                <div id="shuimiansc" style="width:200px;height:240px;margin:0 auto;"></div>
                <footer class="clear">
                    <span class="lf" id="higher">高于${sleepStandard}min</span>
                    <span class="rl" id="lower">低于${sleepStandard}min</span>
                </footer>
            </aside>
        </section>
        <section class="module-bg lf tendency-asleep">
            <p>
                入睡时间趋势
                <button class="rl" onclick="asleepExport()">导出</button>
            </p>
            <!--<span>分钟</span>-->
            <div id="tendency-asleep"></div>
        </section>
        <section class="module-bg lf tendency-sleep">
            <p>
                睡眠时长趋势
                <button class="rl" onclick="sleepExport()">导出</button>
            </p>
            <span>分钟</span>
            <div id="tendency-sleep"></div>
        </section>
        <section class="module-bg-full lf lack-exercise">
            <p style="border-bottom:1px solid #ddd;">
                睡眠不足学生
                <button class="rl" onclick="sleepLessView()">导出</button>
            </p>
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>入睡时间</th>
                        <th>睡眠时长（min）</th>
                        <th>睡眠质量</th>
                        <th>目标入睡时间</th>
                        <th>目标睡眠时长（min）</th>
                    </tr>
                    </thead>
                    <tbody id="sleeptable">
                    <c:forEach items="${pageInfo.list}" var="page">
                        <tr>
                            <td>${page.studentName}</td>
                            <td>${gukeer:getAsleepTime(page.asleepTime)}</td>
                            <td>${page.deepSleep+page.shallowSleep+page.consciousSleep}</td>
                            <td>0</td>
                            <td>${gukeer:getAsleepTime(asleepStandard)}</td>
                            <td>${sleepStandard}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <footer class="pagination clear">
                <div class="lf">
                    共<a id="total">${pageInfo.total}</a>条记录，每页显示
                    <select id="pageSelect" onclick="submit(1)">
                        <option value="10" <c:if test="${pageInfo.pageSize==10}">selected</c:if>>10</option>
                        <option value="20" <c:if test="${pageInfo.pageSize==20}">selected</c:if>>20</option>
                        <option value="50" <c:if test="${pageInfo.pageSize==50}">selected</c:if>>50</option>
                    </select>
                    条记录
                </div>
                <div class="rl fenY">
                    <script>
                        $(".fenY").createPage({
                            pageCount:${pageInfo.pages},
                            current:${pageInfo.pageNum},
                            backFn: function (p) {
                                submit(p);
                            }
                        });
                    </script>
                </div>
            </footer>
        </section>
    </main>
</div>


<script>
    $("#dailyHealth").addClass("active");
    $("#dailyHealth ul li:nth-child(2)>a").addClass("active");

    var shuimianliang = new Highcharts.Chart({
        chart: {
            polar: true,
            type: 'line',
            spacingLeft: 55,
            renderTo: 'shuimianliang',
        },
        title: {
            text: '',
            x: -80
        },
        pane: {
            size: '80%'
        },
        xAxis: {
            categories: ["入睡时间", "深睡眠时长<br>(小时)", "睡眠时长(小时)", "睡眠质量"],
            tickmarkPlacement: 'on',
            lineWidth: 0
        },
        yAxis: {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0,
            labels: {enabled: false}
            //ceiling:1000
        },
        tooltip: {
            enabled: false,
            shared: true,
            headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
            pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>'
            , style: {fontSize: '13px'}
        },
        legend: {
            enabled: false
        },
        series: [
            {
                color: '#FFAF25',
                name: '人均每天睡眠数据',
                data: [changeToThousand(${statistics.avgAsleepTime}), changeToThousand(${statistics.avgDeepSleep}), changeToThousand(${statistics.avgSleep}), changeToThousand("0")],
                pointPlacement: 'off',
                marker: {
                    radius: 3,
                    enabled: false
                }
            }],
        credits: {enabled: false}
    });
    var rushuisj = new Highcharts.Chart({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie',
            renderTo: 'rushuisj',
        }, title: {
            text: ''
        },
        tooltip: {
            headerFormat: '<span style="font-size:12px">{point.key}</span><br>',
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
                name: '晚于${asleepStandard} ',
                <%--y: ${statistics.moreThanAsleepTime},--%>
                <%--y: ${statistics.moreThanAsleepTime},--%>
                y:${messageMap.asleepPercentage},
                color: '#D4D2D2'
            }, {
                name: '早于${asleepStandard} ',
                <%--y: ${100-statistics.moreThanAsleepTime},--%>
                y:${100-messageMap.asleepPercentage},
                color: '#19BE9D',
                //selected: false
            }]
        }],
        credits: {enabled: false}
    });
    var shuimiansc = new Highcharts.Chart({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie',
            renderTo: 'shuimiansc',
        },
        title: {
            text: ''
        },
        tooltip: {
            headerFormat: '<span style="font-size:12px">{point.key}</span><br>',
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
            name: '睡眠时长',
            colorByPoint: true,
            data: [{
                name: '高于${sleepStandard}min ',
                <%--y: ${statistics.moreThanSleepTime}, 原来的--%>
                y:${messageMap.sleepPercentage},
                color: '#D4D2D2'
            }, {
                name: '低于${sleepStandard}min ',
                <%--y: ${100-statistics.moreThanSleepTime},原来的--%>
                y:${100-messageMap.sleepPercentage},
                color: '#CF6EEB',
                //selected: false
            }]
        }],
        credits: {enabled: false}
    });

    var chart1 = new Highcharts.Chart({
        chart: {
            renderTo: 'tendency-asleep',
            type: 'line'
        },
        title: {
            text: ' '

        },
        subtitle: {
            text: ' '

        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: {
                day: '%e of %b'
            }
        },
        yAxis: {
            type: 'datetime',
            title: {
                text: ' '
            },
            dateTimeLabelFormats: {
                day: '%H:%M',
            },
        },
        credits: {enabled: false},
        tooltip: {
//            headerFormat: '<span style="font-size:12px">{point.key}</span><table><br>',
//            pointFormat: '<span style="color:{point.color}"></span> {series.name}: <b>{point.y}</b><br/>'
            formatter: function () {
                return '<b>' + this.point.extendRemark + '</b>'
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            'name': '入睡时间',
            data: [
                <c:forEach items="${asleepList}" var="data">
                {y:${data*60*1000}, extendRemark: ${data}},
                </c:forEach>
            ],
            color: '#19BE9D',
            pointStart: Date.UTC(getYear(), getMonth(), getDate()),
            pointInterval: 24 * 3600 * 1000 // one day
        }],
    })

    var chart2 = new Highcharts.Chart({
        chart: {
            renderTo: 'tendency-sleep',
            type: 'line'
        }, title: {
            text: ' '

        },
        subtitle: {
            text: ' '

        },
        xAxis: {
//            categories: ["8-17","8-18","8-19","8-20","8-21","8-22","8-23"]
            type: 'datetime',
            dateTimeLabelFormats: {
                day: '%e of %b'
            }
        },
        yAxis: {
            title: {
                text: ' '
            }

        },
        credits: {enabled: false},
        tooltip: {
            headerFormat: '<span style="font-size:12px">{point.key}</span><br>',
            //valueSuffix: 'h'
        },
        legend: {
            enabled: false
        },
        series: [{
            name: '睡眠时长',
            data: [<c:forEach items="${dateList}" var="data">
                ${data},
                </c:forEach>],
            color: '#D571F2',
            pointStart: Date.UTC(getYear(), getMonth(), getDate()),
            pointInterval: 24 * 3600 * 1000 // one day
        }]
    })

    $('#date-range').dateRangePicker({
        endDate: getYear() + "-" + getMonth() + 1 + "-" + getDate()
    });

    var classList = [
        <c:forEach items="${cascadingViews}" var="view">
        {
            id: "${view.id}",
            pid: "${view.pid}",
            name: "${view.name}",
        },
        </c:forEach>
    ];

    //年级班级更多

    function isHide(grade) {
        if(grade.length == 0)
            return;
        var length = 0, pLength = grade.parent()[0].clientWidth;
        grade.map(function (i, key) {
            length += key.clientWidth;
            if (length >= pLength) {
                $(grade).parent().css('height', '25px');
                $(grade).parent().css('overflow', 'hidden');
                var selectMore = $(grade).parent().next();
                selectMore.show();
                selectMore.click(function () {
                    if ($(grade).parent().css('height') == '25px') {
                        $(grade).parent().css('height', 'auto');
                        $(grade).parent().siblings().html('收起');
                        $(grade).parent().siblings().addClass("slide-down");
                    } else {
                        $(grade).parent().css('height', '25px');
                        $(grade).parent().siblings().html('更多');
                        $(grade).parent().siblings().removeClass("slide-down");
                    }
                })
            }
        })
    }
    isHide($('#njSelect li'))

    $('#njSelect li').on("click", function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        $('#bjSelect').empty();
        $('#bjSelect').append("<li data-1='0' class='active'>全部</li>");
        var html = '';
        for (var i = 0; i < classList.length; i++) {
            var node = classList[i];
            if (node.pid == $(this).attr('data-1')) {
                html += "<li data-1='" + node.id + "'  >" + node.name + "</li>"
            }
        }
        $("#bjSelect").append(html);
        isHide($('#bjSelect li'))
        $('#bjSelect li').click(function () {
            $(this).addClass('active');
            $(this).siblings().removeClass('active');
            submit();
        })
        submit();
    })
    $('li[name=time]').click(function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        submit();
    });
    $('.apply-btn').click(function () {
        $('#timeSelect li').removeClass('active');
        submit();
    });

    function sleepExport() {
        var _xd = $('#njSelect li[class=active]').attr('data-1');
        var i = _xd.indexOf('_');
        var nj = 0;
        var xd = 0;
        if (i > 0) {
            var nj = _xd.substring(i + 1);
            var xd = _xd.substring(0, i);
        }
        var bj = $('#bjSelect li[class=active]').attr('data-1');
        var timeSelect = $('#timeSelect li[class=active]').attr('data-1');
        var fromDate, toDate;

        var yesterday = new Date();
        yesterday.setDate(yesterday.getDate() - 1);
        if (timeSelect == 0) {
            fromDate = getNowFormatDate(yesterday); // 1~20     12-19   4-11
            toDate = getNowFormatDate(yesterday);
        } else if (timeSelect == 1) {
            toDate = getNowFormatDate(yesterday);
            var aWeekAgo = new Date();
            aWeekAgo.setDate(aWeekAgo.getDate() - 8);
            fromDate = getNowFormatDate(aWeekAgo);
        } else if (timeSelect == 2) {
            toDate = getNowFormatDate(yesterday);
            var aMonthAgo = new Date();
            aMonthAgo = new Date(aMonthAgo.setMonth((new Date().getMonth() - 1)));
            fromDate = getNowFormatDate(aMonthAgo);
        } else if (timeSelect == undefined) {
            fromDate = $('.start-day').html().replace(/-/g, "");
            toDate = $('.end-day').html().replace(/-/g, "");
            if (fromDate == '...' || toDate == '...') {
                fromDate = getNowFormatDate(yesterday);
                toDate = getNowFormatDate(yesterday);
            }
        }
        window.location.href = "${ctx}/dailyhealth/sleepLine/export?nj=" + nj +
                "&xd=" + xd +
                "&bj=" + bj +
                "&fromDate=" + fromDate +
                "&toDate=" + toDate ;
    }

    function asleepExport() {
        var _xd = $('#njSelect li[class=active]').attr('data-1');
        var i = _xd.indexOf('_');
        var nj = 0;
        var xd = 0;
        if (i > 0) {
            var nj = _xd.substring(i + 1);
            var xd = _xd.substring(0, i);
        }
        var bj = $('#bjSelect li[class=active]').attr('data-1');
        var timeSelect = $('#timeSelect li[class=active]').attr('data-1');
        var fromDate, toDate;

        var yesterday = new Date();
        yesterday.setDate(yesterday.getDate() - 1);
        if (timeSelect == 0) {
            fromDate = getNowFormatDate(yesterday); // 1~20     12-19   4-11
            toDate = getNowFormatDate(yesterday);
        } else if (timeSelect == 1) {
            toDate = getNowFormatDate(yesterday);
            var aWeekAgo = new Date();
            aWeekAgo.setDate(aWeekAgo.getDate() - 8);
            fromDate = getNowFormatDate(aWeekAgo);
        } else if (timeSelect == 2) {
            toDate = getNowFormatDate(yesterday);
            var aMonthAgo = new Date();
            aMonthAgo = new Date(aMonthAgo.setMonth((new Date().getMonth() - 1)));
            fromDate = getNowFormatDate(aMonthAgo);
        } else if (timeSelect == undefined) {
            fromDate = $('.start-day').html().replace(/-/g, "");
            toDate = $('.end-day').html().replace(/-/g, "");
            if (fromDate == '...' || toDate == '...') {
                fromDate = getNowFormatDate(yesterday);
                toDate = getNowFormatDate(yesterday);
            }
        }
        window.location.href = "${ctx}/dailyhealth/asleepLine/export?nj=" + nj +
                "&xd=" + xd +
                "&bj=" + bj +
                "&fromDate=" + fromDate +
                "&toDate=" + toDate ;
    }

    function sleepLessView() {
        var _xd = $('#njSelect li[class=active]').attr('data-1');
        var i = _xd.indexOf('_');
        var nj = 0;
        var xd = 0;
        if (i > 0) {
            var nj = _xd.substring(i + 1);
            var xd = _xd.substring(0, i);
        }
        var bj = $('#bjSelect li[class=active]').attr('data-1');
        var timeSelect = $('#timeSelect li[class=active]').attr('data-1');
        var fromDate, toDate;

        var yesterday = new Date();
        yesterday.setDate(yesterday.getDate() - 1);
        if (timeSelect == 0) {
            fromDate = getNowFormatDate(yesterday); // 1~20     12-19   4-11
            toDate = getNowFormatDate(yesterday);
        } else if (timeSelect == 1) {
            toDate = getNowFormatDate(yesterday);
            var aWeekAgo = new Date();
            aWeekAgo.setDate(aWeekAgo.getDate() - 8);
            fromDate = getNowFormatDate(aWeekAgo);
        } else if (timeSelect == 2) {
            toDate = getNowFormatDate(yesterday);
            var aMonthAgo = new Date();
            aMonthAgo = new Date(aMonthAgo.setMonth((new Date().getMonth() - 1)));
            fromDate = getNowFormatDate(aMonthAgo);
        } else if (timeSelect == undefined) {
            fromDate = $('.start-day').html().replace(/-/g, "");
            toDate = $('.end-day').html().replace(/-/g, "");
            if (fromDate == '...' || toDate == '...') {
                fromDate = getNowFormatDate(yesterday);
                toDate = getNowFormatDate(yesterday);
            }
        }
        window.location.href = "${ctx}/dailyhealth/sleep/export?nj=" + nj +
                "&xd=" + xd +
                "&bj=" + bj +
                "&fromDate=" + fromDate +
                "&toDate=" + toDate ;
    }

    function submit(p) {
        var _xd = $('#njSelect li[class=active]').attr('data-1');
        var i = _xd.indexOf('_');
        var nj = 0;
        var xd = 0;
        if (i > 0) {
            var nj = _xd.substring(i + 1);
            var xd = _xd.substring(0, i);
        }
        var bj = $('#bjSelect li[class=active]').attr('data-1');
        var timeSelect = $('#timeSelect li[class=active]').attr('data-1');
        var fromDate, toDate;

        var yesterday = new Date();
        yesterday.setDate(yesterday.getDate() - 1);
        if (timeSelect == 0) {
            fromDate = getNowFormatDate(yesterday); // 1~20     12-19   4-11
            toDate = getNowFormatDate(yesterday);
        } else if (timeSelect == 1) {
            toDate = getNowFormatDate(yesterday);
            var aWeekAgo = new Date();
            aWeekAgo.setDate(aWeekAgo.getDate() - 8);
            fromDate = getNowFormatDate(aWeekAgo);
        } else if (timeSelect == 2) {
            toDate = getNowFormatDate(yesterday);
            var aMonthAgo = new Date();
            aMonthAgo = new Date(aMonthAgo.setMonth((new Date().getMonth() - 1)));
            fromDate = getNowFormatDate(aMonthAgo);
        } else if (timeSelect == undefined) {
            fromDate = $('.start-day').html().replace(/-/g, "");
            toDate = $('.end-day').html().replace(/-/g, "");
            if (fromDate == '...' || toDate == '...') {
                fromDate = getNowFormatDate(yesterday);
                toDate = getNowFormatDate(yesterday);
            }
        }
        var pageSize = $('#pageSelect').val();
        var onlyTable = true;
        var sportTable = false;
        if (p == undefined) {
            p = 1;
            onlyTable = false;
        }
        $.post("${ctx}/dailyhealth/sport/getdata", {
            xd: xd,
            nj: nj,
            classId: bj,
            fromDate: fromDate,
            toDate: toDate,
            pageNum: p,
            pageSize: pageSize,
            onlyTable: onlyTable,
            sportTable: sportTable
        }, function (res) {
            var data = JSON.parse(res);
            if (onlyTable == false) {
                //修改
                var shuju = JSON.parse(data.shuju);


                var statistics = JSON.parse(data.statistics);
                var diffStatistics = JSON.parse(data.diffStatistics);
                $('#asleep').html(data.asleep);
                $('#avgDeepSleep').html(round(statistics.avgDeepSleep,1) + 'min');
                $('#avgSleep').html(round(statistics.avgSleep,1) + 'min');
                $('#avgSleepQuality').html(round(statistics.avgSleepQuality,1));
                $('span[id=higher]').html('高于' + data.sleepStandard + 'min');
                $('span[id=lower]').html('低于' + data.sleepStandard + 'min');
                $('span[id=earlier]').html('早于' + data.asleepStandard);
                $('span[id=later]').html('晚于' + data.asleepStandard);
                var sportDifference = round(diffStatistics.avgSleep,1);
                var _sportDifference = sportDifference + "min";
                var sportStatus = "<i class=pull-u></i>"
                if (sportDifference < 0) {
                    sportDifference = -sportDifference;
                    _sportDifference = sportDifference + "min";
                    sportStatus = "<i class=pull-d></i>";
                }
                if (sportDifference == 0) {
                    _sportDifference = sportDifference + "min";
                    sportStatus = "<i class=pull-m></i>";
                }

                var caloriesDifference = diffStatistics.avgAsleepTime;
                var _caloriesDifference = caloriesDifference + "min";
                var caloriesStatus = "<i class=pull-u></i>";
                if (caloriesDifference < 0) {
                    caloriesDifference = -caloriesDifference;
                    _caloriesDifference = caloriesDifference + "min";
                    caloriesStatus = +"<i class=pull-d></i>";
                }
                if (caloriesDifference == 0) {
                    _caloriesDifference = caloriesDifference + "min";
                    caloriesStatus = "<i class=pull-m></i>";
                }

                var walkDayDifference = round(diffStatistics.avgSleepQuality,1);
                var walkStatus = '<i class=pull-u></i>';
                var _walkDayDifference = walkDayDifference;
                if (walkDayDifference < 0) {
                    walkDayDifference = -walkDayDifference;
                    _walkDayDifference = walkDayDifference;
                    walkStatus = '<i class=pull-d></i>';
                }
                if (walkDayDifference == 0) {
                    _walkDayDifference = walkDayDifference;
                    walkStatus = "<i class=pull-m></i>";
                }

                var distanceDifference = round(diffStatistics.avgDeepSleep,1);
                var _distanceDifference = distanceDifference + "min";
                var distanceStatus = "<i class=pull-u></i>";
                if (distanceDifference < 0) {
                    distanceDifference = -distanceDifference;
                    _distanceDifference = distanceDifference + "min";
                    distanceStatus = "<i class=pull-d></i>";
                }
                if (distanceDifference == 0) {
                    _distanceDifference = distanceDifference + "min";
                    distanceStatus = "<i class=pull-m></i>";
                }
                $('#sleepDifference').html(_sportDifference);
                $('#sportstatus').html(sportStatus);
                $('#asleepTimeDifference').html(_caloriesDifference);
                $('#caloriesStatus').html(caloriesStatus);
                $('#sleepQualityDifference').html(_walkDayDifference);
                $('#walkStatus').html(walkStatus);
                $('#deepSleepDifference').html(_distanceDifference);
                $('#distanceStatus').html(distanceStatus);
                var sleepList = eval('(' + data.sleepList + ')');
                var asleepList = eval('(' + data.asleepList + ')');
                var asleepValueList = eval('(' + data.asleepValueList + ')');
                var test = [];
                asleepValueList.map(function (el) {
                    var msg = new Object();
                    msg.y = el * 60 * 1000;
                    msg.extendRemark = translateTime(el);
                    test.push(msg);
                });
                var b = [];
                b.push(changeToThousand(statistics.avgAsleepTime));
                b.push(changeToThousand(statistics.avgDeepSleep));
                b.push(changeToThousand(statistics.avgSleep));
                b.push(changeToThousand("0"));
                shuimianliang.series[0].update({
                    data: b
                });

                chart1.series[0].update({
                    data: test,
                    pointStart: Date.UTC(fromDate.substring(0, 4), fromDate.substring(4, 6) - 1, fromDate.substring(6))
                });
                chart2.series[0].update({
                    data: sleepList.map(function (el) {
                        return parseInt(el);
                    }),
                    pointStart: Date.UTC(fromDate.substring(0, 4), fromDate.substring(4, 6) - 1, fromDate.substring(6))
                });
                rushuisj.series[0].update({
                    data: [{
                        name: '晚于' + data.asleepStandard,
//                        y: statistics.moreThanAsleepTime,原来的
                        y:shuju.asleepPercentage,
                        color: '#D4D2D2'
                    }, {
                        name: '早于' + data.asleepStandard,
//                        y: 100 - statistics.moreThanAsleepTime,原来的
                        y:100-shuju.asleepPercentage,
                        color: '#19BE9D'
                    }]
                });
                shuimiansc.series[0].update({
                    data: [{
                        name: '高于' + data.sleepStandard + 'min ',
//                        y: statistics.moreThanSleepTime,原来的
                        y:shuju.sleepPercentage,
                        color: '#D4D2D2'
                    }, {
                        name: '低于' + data.sleepStandard + 'min ',
//                        y: 100 - statistics.moreThanSleepTime,原来的
                        y:100-shuju.sleepPercentage,
                        color: '#CF6EEB'
                    }]
                });
            }
            console.log(data.pageInfo);
            console.log(dataArray);
            var dataArray = JSON.parse(data.pageInfo);

//            var dataArray = eval("("+data.pageInfo+")");

            $('#sleeptable').html('');
            for (var j = 0; j < dataArray.length; j++) {
                var eachName = dataArray[j].studentName;
                var eachasleepTime = dataArray[j].avgAsleepTime;
                var eachsleepTime = dataArray[j].deepSleep + dataArray[j].shallowSleep + dataArray[j].consciousSleep;//不是fz注释的
                var eachsleepTime = dataArray[j].avgSleepTime.toFixed(1);
                var eachsleepQuality = dataArray[j].avgSleepQuality.toFixed(1);
                var eachsleepStandard = dataArray[j].sleepStandard;
                var eachasleepStandard = dataArray[j].asleepStandard;

                var html = "<tr><td>" + eachName + "</td> <td>" + translateTime(eachasleepTime) + "</td> <td>" + eachsleepTime + "</td><td>" + eachsleepQuality + "</td><td>" + translateTime(eachasleepStandard) + "</td><td>" + eachsleepStandard + "</td></tr>";
                $('#sleeptable').append(html);
            }
            $('#total').html(data.total);
            $(".fenY").createPage({
                pageCount: data.pages,
                current: data.current,
            });
        });
    }


    function translateTime(t) {
        var temp = t / 60;
        var temp = temp + "";
        var flag = temp.indexOf(".");
        if (flag >= 0) {
            var hour = temp.substring(0, flag);
            if (hour > 24) hour = hour - 24;
            var min = temp.substring(flag);
            var m = "0" + min;
            m = Math.round(m * 60);
            m = m + "";
            if (m.length == 1) m = "0" + m;
            return hour + ":" + m;
        } else {
            return temp + ":" + "00";
        }
    }
    function getNowFormatDate(date) {
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year.toString() + month.toString() + strDate.toString();
        return currentdate;
    }


    function changeToThousand(str) {
        var str = str + "";
        if (str.indexOf(".") < 0) {
            var c = str.length - 4;
            if (c == 0) {
                return Number(str);
            } else {
                if (c < 0) {
                    for (var i = 0; i < -c; i++) {
                        str = str + "0";
                    }
                    return Number(str);
                } else {
                    var before = str.substring(0, 3);
                    var after = str.substring(3);
                    str = before + "." + after;
                    return Number(str);
                }
            }
        } else {
            var str2 = str.substring(0, str.indexOf("."));
            var c = str2.length - 4;
            if (c == 0) {
                return Number(str);
            } else {
                if (c < 0) {
                    for (var i = 0; i < -c; i++) {
                        Number(str);
                        str = str * 10;
                    }
                    return str;
                } else {
                    for (var i = 0; i < -c; i++) {
                        Number(str);
                        str = str / 10;
                    }
                    return str;
                }
            }
        }
    }
    function getYear() {
        var time = new Date();
        return time.getYear() + 1900;
    }
    function getMonth() {
        var time = new Date();
        return time.getMonth();
    }
    function getDate() {
        var time = new Date();
        return time.getDate();
    }
    function round(v,e){
        var t=1;
        for(;e>0;t*=10,e--);
        for(;e<0;t/=10,e++);
        return Math.round(v*t)/t;
    }
</script>
</body>
</html>
