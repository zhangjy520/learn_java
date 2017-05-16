<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>运动统计</title>
    <link rel="stylesheet" href="${ctxStatic}/css/dailyHealth.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/daterangepicker.min.css">
    <script src="${ctxStatic}/js/plugins/moment.js"></script>
    <script src="${ctxStatic}/js/plugins/jquery.daterangepicker.min.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
</head>
<body>

<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a>日常健康</a></li>
        <li class="child-nav  active"><a>运动统计</a></li>
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
                <li class="active" data-1="0" name="time">过去一天</li>
                <li data-1="1" name="time">过去一周</li>
                <li data-1="2" name="time">过去一月</li>
                <li>
                    <input type="text" id="date-range">
                </li>
            </ul>
        </div>
    </section>
    <main id="exercise-statistic" class="fix-width">
        <section class="lf module-bg yundongliang">
            <p>运动情况</p>
            <div id="yundonglaing" style="width:500px;height:350px;">

            </div>
            <table>
                <tr>
                    <td>
                        <ul>
                            <li><span>运动时长</span></li>
                            <li><span id="avgSport">${gukeer:getF(statistics.avgSport)}min</span></li>
                            <li><span id="sportDifference">${gukeer:getF(diffStatistics.avgSport)}min</span></li>
                            <li id="sportStatus">
                                <c:if test="${diffStatistics.avgSport > 0}"><i class="pull-u"></i></c:if>
                                <c:if test="${diffStatistics.avgSport < 0}"><i class="pull-d"></i></c:if>
                                <c:if test="${diffStatistics.avgSport == 0}"><i class="pull-m"></i></c:if>
                            </li>
                        </ul>
                    </td>
                    <td>
                        <ul>
                            <li><span>运动耗能</span></li>
                            <li><span id="avgCalories">${gukeer:getF(statistics.avgCalories)}cal</span></li>
                            <li><span id="caloriesDifference">${gukeer:getF(diffStatistics.avgCalories)}cal</span>
                            </li>
                            <li id="caloriesStatus">
                                <c:if test="${diffStatistics.avgCalories > 0}"><i class="pull-u"></i></c:if>
                                <c:if test="${diffStatistics.avgCalories < 0}"><i class="pull-d"></i></c:if>
                                <c:if test="${diffStatistics.avgCalories == 0}"><i class="pull-m"></i></c:if>
                            </li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>
                        <ul>
                            <li><span>步行步数</span></li>
                            <li><span id="avgWalkDay">${gukeer:getF(statistics.avgWalkDay)}步</span></li>
                            <li><span id="walkDayDifference">${gukeer:getF(diffStatistics.avgWalkDay)}步</span></li>
                            <li id="walkStatus">
                                <c:if test="${diffStatistics.avgWalkDay > 0}"><i class="pull-u"></i></c:if>
                                <c:if test="${diffStatistics.avgWalkDay < 0}"><i class="pull-d"></i></c:if>
                                <c:if test="${diffStatistics.avgWalkDay == 0}"><i class="pull-m"></i></c:if>
                            </li>
                        </ul>
                    </td>
                    <td>
                        <ul>
                            <li><span>步行距离</span></li>
                            <li><span id="avgDistance">${gukeer:getF(statistics.avgDistance)}米</span></li>
                            <li><span id="distanceDifference">${gukeer:getF(diffStatistics.avgDistance)}米</span></li>
                            <li id="distanceStatus">
                                <c:if test="${diffStatistics.avgDistance > 0}"><i class="pull-u"></i></c:if>
                                <c:if test="${diffStatistics.avgDistance < 0}"><i class="pull-d"></i></c:if>
                                <c:if test="${diffStatistics.avgDistance == 0}"><i class="pull-m"></i></c:if>
                            </li>
                        </ul>
                    </td>
                </tr>
            </table>
        </section>
        <section class="rl module-bg yundongsj">
            <p>运动时长</p>
            <div id="yundongsj" style="width:300px;height:350px;"></div>
            <footer class="clear">
                <span class="lf" id="lower">低于${sportStandard}min</span>
                <span class="rl" id="higher">高于${sportStandard}min</span>
            </footer>
        </section>
        <section class="module-bg lf tendency-duration">
            <p>
                运动时长趋势
                <button class="rl" onclick="exportsportLine()">导出</button>
            </p>
            <span>分钟</span>
            <div id="tendency-duration"></div>
        </section>
        <section class="module-bg-full lf lack-exercise">
            <p style="border-bottom:1px solid #ddd;">
                运动不足学生
                <button class="rl" onclick="exportsportless()">导出</button>
            </p>
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>运动时长(min)</th>
                        <th>运动耗能(cal)</th>
                        <th>目标运动时长(min)</th>
                    </tr>
                    </thead>
                    <tbody id="sporttable">
                    <c:forEach items="${pageInfo.list}" var="page">
                        <tr>
                            <td>${page.studentName}</td>
                            <td>${page.sportTime}</td>
                            <td>${page.caloriesDay}</td>
                            <td>${sportStandard}</td>
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
    $("#dailyHealth ul li:nth-child(1)>a").addClass("active");

    var njLis = $('#njSelect li');
    isHide(njLis)
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

    function exportsportless() {
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
            fromDate = getNowFormatDate(yesterday);
            toDate = getNowFormatDate(yesterday);
        } else if (timeSelect == 1) {
            toDate = getNowFormatDate(yesterday);
            var aWeekAgo = new Date();
            aWeekAgo.setDate(aWeekAgo.getDate() - 7);
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
        window.location.href = "${ctx}/dailyhealth/sport/export?nj=" + nj +
                "&xd=" + xd +
                "&bj=" + bj +
                "&fromDate=" + fromDate +
                "&toDate=" + toDate ;
    }

    function exportsportLine() {
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
            fromDate = getNowFormatDate(yesterday);
            toDate = getNowFormatDate(yesterday);
        } else if (timeSelect == 1) {
            toDate = getNowFormatDate(yesterday);
            var aWeekAgo = new Date();
            aWeekAgo.setDate(aWeekAgo.getDate() - 7);
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
        window.location.href = "${ctx}/dailyhealth/sportLine/export?nj=" + nj +
                "&xd=" + xd +
                "&bj=" + bj +
                "&fromDate=" + fromDate +
                "&toDate=" + toDate ;
    }


    var yundonglaing = new Highcharts.Chart({
        chart: {
            polar: true,
            type: 'line',
            spacingLeft: 55,
            renderTo: 'yundonglaing',
        },
        title: {
            text: '',
            x: -80
        },
        pane: {
            size: '80%'
        },
        xAxis: {
            categories: ["时间(分钟)", "步数", "距离(米)", "耗能(卡路里)"],
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
//                data: [1100, 1000, 1170, 900],
                data: [changeToThousand(${statistics.avgSport}), changeToThousand(${statistics.avgWalkDay}), changeToThousand(${statistics.avgDistance}), changeToThousand(${statistics.avgCalories})],
                pointPlacement: 'off',
                marker: {
                    radius: 3,
                    enabled: false
                }
            }],
        credits: {enabled: false}
    });

    var yundongsj = new Highcharts.Chart({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie',
            renderTo: 'yundongsj',
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
                    format: '<br><b>{point.name}</b>: {point.percentage:.1f} %',
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
                name: '低于${sportStandard}min ',
                y: ${100-statistics.moreThanSportTime},
                color: '#D4D2D2'
            }, {
                name: '高于${sportStandard}min ',
                y: ${statistics.moreThanSportTime},
                color: '#FFAF25',
                //selected: false
            }]
        }],
        credits: {enabled: false}
    })

    var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'tendency-duration',
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
                month: '%时 ： %b'
            }
        },
        yAxis: {
            title: {
                text: ' '
            }

        },
        credits: {enabled: false},
        tooltip: {
            headerFormat: '<span style="font-size:12px">{point.key}</span><table><br>',
            //valueSuffix: 'h'
        },
        legend: {
            enabled: false
        },
        series: [{
            name: '运动时长(分钟)',
            data: [<c:forEach items="${dateList}" var="data">
                ${data},
                </c:forEach>],
            color: '#FFAF25',
            pointStart: Date.UTC(getYear(), getMonth(), getDate()),
            pointInterval: 24 * 3600 * 1000 // one day
        }]
    });

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


    $('#njSelect li').on("click", function () {
//        isHide(bjLis);
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
        var bjLis = $('#bjSelect li');
        isHide(bjLis);
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
    })


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
            fromDate = getNowFormatDate(yesterday);
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
        var sportTable = true;
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
                var statistics = JSON.parse(data.statistics);
//                修改
                var shuju = JSON.parse(data.shuju);

                console.log(shuju);
                var diffStatistics = JSON.parse(data.diffStatistics);
                $('#avgSport').html(statistics.avgSport.toFixed(1) + "min");
                $('#avgCalories').html(statistics.avgCalories.toFixed(1) + "cal");
                $('#avgDistance').html(statistics.avgDistance.toFixed(1) + "米");
                $('#avgWalkDay').html(statistics.avgWalkDay.toFixed(1) + "步");
                $('span[id=higher]').html('高于' + data.sportStandard + 'min');
                $('span[id=lower]').html('低于' + data.sportStandard + 'min');
                var sportDifference = diffStatistics.avgSport.toFixed(1);
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

                var caloriesDifference = diffStatistics.avgCalories.toFixed(1);
                var _caloriesDifference = caloriesDifference + "cal";
                var caloriesStatus = "<i class=pull-u></i>";
                if (caloriesDifference < 0) {
                    caloriesDifference = -caloriesDifference;
                    _caloriesDifference = caloriesDifference + "cal";
                    caloriesStatus = +"<i class=pull-d></i>";
                }
                if (caloriesDifference == 0) {
                    _caloriesDifference = caloriesDifference + "cal";
                    caloriesStatus = "<i class=pull-m></i>";
                }

                var walkDayDifference = diffStatistics.avgWalkDay.toFixed(1);
                var _walkDayDifference = walkDayDifference + "步";
                var walkStatus = '<i class=pull-u></i>';
                if (walkDayDifference < 0) {
                    walkDayDifference = -walkDayDifference;
                    _walkDayDifference = walkDayDifference + "步";
                    walkStatus = '<i class=pull-d></i>';
                }
                if (walkDayDifference == 0) {
                    _walkDayDifference = walkDayDifference + "步";
                    walkStatus = "<i class=pull-m></i>";
                }

                var distanceDifference = diffStatistics.avgDistance.toFixed(1);
                var _distanceDifference = distanceDifference + "米";
                var distanceStatus = "<i class=pull-u></i>";
                if (distanceDifference < 0) {
                    distanceDifference = -distanceDifference;
                    _distanceDifference = distanceDifference + "米";
                    distanceStatus = "<i class=pull-d></i>";
                }
                if (distanceDifference == 0) {
                    _distanceDifference = distanceDifference + "米";
                    distanceStatus = "<i class=pull-m></i>";
                }
                $('#sportDifference').html(_sportDifference);
                $('#sportStatus').html(sportStatus);
                $('#caloriesDifference').html(_caloriesDifference);
                $('#caloriesStatus').html(caloriesStatus);
                $('#walkDayDifference').html(_walkDayDifference);
                $('#walkStatus').html(walkStatus);
                $('#distanceDifference').html(_distanceDifference);
                $('#distanceStatus').html(distanceStatus);

                var a = [];
                a.push(changeToThousand(statistics.avgSport));
                a.push(changeToThousand(statistics.avgWalkDay));
                a.push(changeToThousand(statistics.avgDistance));
                a.push(changeToThousand(statistics.avgCalories));
                yundonglaing.series[0].update({
                    data: a
                });

                var sportData = eval('(' + data.dateList + ')');
                chart.series[0].update({
                    data: sportData.map(function (el) {
                        return parseInt(el);
                    }),
                    pointStart: Date.UTC(fromDate.substring(0, 4), fromDate.substring(4, 6) - 1, fromDate.substring(6))
                });
                yundongsj.series[0].update({
                    data: [{
                        name: '高于' + data.sportStandard + 'min ',
//                        y: statistics.moreThanSportTime, 原来的
                        y:shuju.sportPercentage,
                        color: '#FFAF25'
                    }, {
                        name: '低于' + data.sportStandard + 'min ',
//                        y: 100 - statistics.moreThanSportTime, 原来的
                        y:100-shuju.sportPercentage,
                        color: '#D4D2D2'
                    }]
                });
            }
            var dataArray = JSON.parse(data.pageInfo);
            $('#sporttable').html('');
            for (var j = 0; j < dataArray.length; j++) {
                var eachName = dataArray[j].studentName;
                var eachsportTime = dataArray[j].avgTime.toFixed(1);
                var caloriesDay = dataArray[j].avgCal.toFixed(1);
                var sportStandard = dataArray[j].sportStandard;
                var html = "<tr><td>" + eachName + "</td> <td>" + eachsportTime + "</td> <td>" + caloriesDay + "</td><td>" + sportStandard  + "</td></tr>";
                $('#sporttable').append(html);
            }
            $('#total').html(data.total);
            $(".fenY").createPage({
                pageCount: data.pages,
                current: data.current,
            });
        });
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
</script>
</body>
</html>