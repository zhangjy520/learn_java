<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>位置统计</title>
    <link rel="stylesheet" href="${ctxStatic}/css/common.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/position.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/daterangepicker.min.css">
    <script src="${ctxStatic}/js/plugins/moment.js"></script>
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/plugins/jquery.daterangepicker.min.js"></script>
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
        <li class="root-nav"><a href="#">体质健康</a></li>
        <li class="child-nav active"><a href="#">位置统计</a></li>
    </ul>
</header>
<div id="bodyContainer">
    <main class="fix-width module-bg-full position1">
        <header class="items-selector ">
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
        </header>
        <section class="clear">
            <%--加了一句话--%>
            <div style="display: none" id="pieTable">该段时间没有数据</div>
            <aside class="lf" id="chart1" style="width:30%;height:340px;">
            </aside>
            <%--加了areaData类--%>
            <div class="rl  areaData" style="margin: 50px 36px 50px 120px;height: 256px;width: 300px;overflow-y: scroll;">
                <table class="table">
                    <thead>
                        <tr>
                            <th>区域名称</th>
                            <th>逗留时间(h)</th>
                            <th>占比</th>
                        </tr>
                        </thead>
                        <tbody id="tb">
                        <c:forEach items="${avgTime}" var="avg">
                            <tr>
                                <td>${avg.areaName}</td>
                                <td>${avg.avgTime}</td>
                                <td>${avg.percent}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </main>

</div>

<script>
    //加了判断
    $(function () {
        if(${avgTime.size()==0}){
            $('.areaData').css("display","none");
            $('#pieTable').css("display","block");
        }else{
            $('.areaData').css("display","block");
            $('#pieTable').css("display","none");
        }
    });


    $("#positionControl").addClass("active");
    $("#positionControl ul li:nth-child(2)>a").addClass("active");
    console.log(${point.key});
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
    var chart = new Highcharts.Chart({
//    $('#chart1').highcharts({
        chart: {
            polar: true,
            type: 'line',
            spacingLeft: 55,
            renderTo: 'chart1',
        },
        title: {
            text: '',
            x: -80
        },
        pane: {
            size: '80%'
        },
        xAxis: {
            categories: [<c:forEach items="${avgTime}" var="avg">
                '${avg.areaName}',
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
            , style: {fontSize: '13px'}
        },
        legend: {
            enabled: false
        },
        series: [
            {
                color: '#FFAF25',
                name: '逗留时间（h）',
                data: [<c:forEach items="${avgTime}" var="avg">
                    ${avg.avgTime},
                    </c:forEach>],
                pointPlacement: 'off',
                marker: {
                    radius: 3,
                    enabled: false
                }
            }],
        credits: {enabled: false}
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
        var today = new Date();
        yesterday.setDate(yesterday.getDate() - 1);
        if (timeSelect == 0) {
            fromDate = getNowFormatDate(yesterday);
            toDate = getNowFormatDate(today);
        } else if (timeSelect == 1) {
            toDate = getNowFormatDate(today);
            var aWeekAgo = new Date();
            aWeekAgo.setDate(aWeekAgo.getDate() - 8);
            fromDate = getNowFormatDate(aWeekAgo);
        } else if (timeSelect == 2) {
            toDate = getNowFormatDate(today);
            var aMonthAgo = new Date();
            aMonthAgo.setDate(aMonthAgo.getDate() - 31);
            fromDate = getNowFormatDate(aMonthAgo);
        } else if (timeSelect == undefined) {
            fromDate = $('.start-day').html().replace(/-/g, "") + "000000";
            toDate = $('.end-day').html().replace(/-/g, "") + "000000";
            if (fromDate == '...' || toDate == '...') {
                fromDate = getNowFormatDate(yesterday);
                toDate = getNowFormatDate(today);
            }
        }
        $.post("${ctx}/position/statistics/getdata", {
            xd: xd,
            nj: nj,
            classId: bj,
            fromDate: fromDate,
            toDate: toDate,
        }, function (res) {
            var data = JSON.parse(res);
            var sportData = eval('(' + data.avgTime + ')');
            var a = [];
            var html = "";
            var html2 = "";
            //加了判断并且加了两个显示
            if (sportData !='') {
                $('.areaData').css('display','block');
                $('#pieTable').css("display","none");
                sportData.map(function (el) {
                    a.push(parseFloat(el.avgTime))
                    html += "<tr><td>" + el.areaName + "</td><td>" + el.avgTime + "</td><td>" + el.percent + "</td></tr>";
                    $('#tb').empty();
                    $('#tb').append(html);
                })
            }else{
                $('.areaData').css('display','none');
                $('#pieTable').css("display","block");
            }
//            a.push(0);
            chart.series[0].update({
                data: a
            });
//            $('#tb').html("");
//            做了移动
//            $('#tb').empty();
//            $('#tb').append(html);

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
        var currentdate = year.toString() + month.toString() + strDate.toString() + "000000";
        return currentdate;
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