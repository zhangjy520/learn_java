<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看详情</title>
    <link rel="stylesheet" href="${ctxStatic}/css/physicalHealth.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/daterangepicker.min.css">
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/moment.js"></script>
    <script src="${ctxStatic}/js/plugins/jquery.daterangepicker.min.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
                    <span class="lf current-position">
                        当前位置：
                    </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="physicalHealth.html">体质健康</a></li>
        <li class="child-nav"><a href="physicalHealth3.html">学生体质档案</a></li>
        <li class="child-nav active"><a href="physicalDetail.html">查看详情</a></li>
    </ul>
</header>
<main id="physicalDetail" class="fix-width">
    <ul class="sh-tabs clear">
        <li><a id="tap1" onclick="tapChange(this)" data="detail1">基础信息</a></li>
        <li><a id="tap2" onclick="tapChange(this)" data="detail2">日常健康</a></li>
        <li><a id="tap3" onclick="tapChange(this)" data="detail3">体育测试</a></li>
        <li><a id="tap4" onclick="tapChange(this)" data="detail4">体质健康</a></li>
    </ul>
    <section id="detail1" class="module-bg">
        <div>
            <span>姓名</span>
            <label>${stuView.xsxm}</label>
            <span>性别</span>
            <label>
                <c:if test="${stuView.xsxb == 1}">
                    男
                </c:if>
                <c:if test="${stuView.xsxb == 2}">
                    女
                </c:if>
            </label>
            <span>出生日期</span>
            <label>${stuView.csrq}</label>
        </div>
        <div>
            <span>年级</span>
            <label>${stuView.indexName}</label>
            <span>班级</span>
            <label>${stuView.className}</label>
            <span>学号</span>
            <label>${stuView.xh}</label>
        </div>
        <div>
            <span>备注</span>
            <label>${stuView.remarks}</label>
        </div>
    </section>
    <section id="detail2" class="module-bg-full" style="margin-bottom:80px;">
        <div class="items-selector clear detail2-1">
            <ul class="lf clear select-items" id="timeBetween">
                <li id="day">过去一天</li>
                <li id="week" class="active">过去一周</li>
                <li id="month">过去一月</li>
                <li id="input">
                    <input type="text" id="date-range">
                </li>
            </ul>
        </div>
        <div class="detail2-2">
            <p>运动量和睡眠状况</p>
            <ul class="clear" id="avgDaily">

            </ul>
        </div>
        <div class="detail2-3">
            <p>运动时长趋势</p>
            <span>分钟</span>
            <div id="chart1" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
        <div class="detail2-3">
            <p>入睡时间趋势</p>
            <span></span>
            <div id="chart2" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
        <div class="detail2-3">
            <p>睡眠时长趋势</p>
            <span>分钟</span>
            <div id="chart3" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
    </section>
    <section id="detail3" class="module-bg-full">
        <div>
            <p>最新测试分数</p>
            <span>分</span>
            <div id="chart4" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
        <footer>
            <table class="table">
                <thead>
                <tr>
                    <th>项目</th>
                    <th>测试成绩</th>
                    <th>成绩单位</th>
                    <th>分数</th>
                    <th>等级</th>
                </tr>
                </thead>
                <tbody id="sport_table">
                </tbody>
            </table>
        </footer>
        <div>
            <p class="clear">
                <style>#detail3 > div > p.clear:after {
                    top: 17px;
                    left: 153px;
                }</style>
                体育成绩变化趋势
                <select class="rl" id="sportItem">
                    <c:forEach items="${itemList}" var="item">
                        <option value="${item.itemId}">${item.itemName}</option>
                    </c:forEach>
                </select>
            </p>
            <span class="itemUnitC">单位</span>
            <div id="chart5" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
    </section>
    <section id="detail4" class="module-bg-full">
        <div>
            <p>最新体质健康测试成绩</p>
            <span>分</span>
            <div id="chart6" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>

        <footer>
            <table class="table">
                <thead>
                <tr>
                    <th>项目</th>
                    <th>测试成绩</th>
                    <th>成绩单位</th>
                    <th>分数</th>
                    <th>等级</th>
                </tr>
                </thead>
                <tbody id="physical_table">
                </tbody>
            </table>
        </footer>
        <div>
            <p class="clear">
                <style>#detail4 > div > p.clear:after {
                    left: 185px;
                }</style>
                体质健康成绩变化趋势
                <select class="rl" id="physicalItem">
                    <c:forEach items="${physicalItemList}" var="physicalItem">
                        <option value="${physicalItem.itemId}">${physicalItem.itemName}</option>
                    </c:forEach>
                </select>
            </p>
            <span class="itemUnitC">单位</span>
            <div id="chart7" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
    </section>
</main>

<script>
    var tapIndex = "${whichTap}";
    tapChange($("#" + tapIndex));

    $("#physicalHealth").addClass("active");
    $("#physicalHealth ul li:nth-child(3)>a").addClass("active");

    $('#date-range').dateRangePicker({});

    $(".apply-btn").click(function () {
        dailyHealth(1);
    });

    $('.select-items li').click(function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        if ($(this).attr("id") != "input"){
            dailyHealth(0);
        }
    })

    function tapChange(obj) {
        $('.sh-tabs a').removeClass('active');
        $(obj).addClass('active');

        var tabItem = $('#physicalDetail section[id*=detail]');
        var data = $(obj).attr('data');
        for (var i = 0; i < tabItem.length; i++) {
            if (tabItem[i].id == data) {
                $(tabItem[i]).show();
                $(tabItem[i]).siblings('section').hide();
            }
        }
        if (data == "detail1") {
            //基础信息
        }
        if (data == "detail2") {
            dailyHealth(0);
        }
        if (data == "detail3") {
            sportPage(0, 0);
        }
        if (data == "detail4") {
            sportPage(0, 1);
        }
    }

    $("#sportItem").change(function () {
        sportPage(1, 0);
    });

    $("#physicalItem").change(function () {
        sportPage(1, 1);
    });

    function sportPage(type, scoreType) {
        var itemArray = [];
        var scoreArray = [];
        var dataX = [];
        var dataY = [];

        var stuNum = "${stuNum}";

        var itemId = "";
        if (scoreType == 0) {
            itemId = $("#sportItem option:selected").val();
        }
        if (scoreType == 1) {
            itemId = $("#physicalItem option:selected").val();
        }

        $.post("${ctx}/physical/archives/sport/" + stuNum, {
            itemId: itemId,
            scoreType: scoreType
        }, function (retJson) {
            //体育测试
            if (type == 0) {
                if (scoreType == 0) {
                    $("#sport_table").html("");
                    var score = retJson.latestScore;
                    for (var i = 0; i < score.length; i++) {
                        itemArray[itemArray.length] = score[i].itemName;
                        scoreArray[scoreArray.length] = Number(score[i].stuScore);
                        $("#sport_table").append("<tr><td>" + score[i].itemName + "</td><td>" + score[i].mark + "</td><td>" + score[i].itemUnit + "</td><td>" + score[i].stuScore + "</td><td>" + score[i].stuLevel + "</td></tr>");
                    }
                    $('#chart4').highcharts({
                        chart: {
                            type: 'column'
                        },
                        title: {
                            text: ' '
                        },
                        subtitle: {
                            text: ' '
                        },
                        legend: {enabled: false},
                        xAxis: {
                            categories: itemArray
                        },
                        yAxis: {
                            min: 0,
                            title: {
                                text: ' '
                            },
                            min: 0,
                            /* max: 100*/
                        },
                        tooltip: {
                            headerFormat: '<span style="font-size:13px">{point.key}</span><table>',
                            pointFormat: '<td style="padding:0;"><b>{point.y:.1f}</b></td></tr>',
                            //footerFormat: '</table>',
                            shared: true,
                            useHTML: true
                        },
                        plotOptions: {
                            column: {
                                pointPadding: 0.2,
                                borderWidth: 0
                            }
                        },
                        series: [{
                            name: ' ',
                            data: scoreArray,
                            color: '#19BE9D',
                            /*max: 100*/
                        }],
                        credits: {enabled: false}
                    });
                }
                if (scoreType == 1) {
                    $("#physical_table").html("");
                    var score = retJson.latestScore;
                    for (var i = 0; i < score.length; i++) {
                        itemArray[itemArray.length] = score[i].itemName;
                        scoreArray[scoreArray.length] = Number(score[i].stuScore);
                        $("#physical_table").append("<tr><td>" + score[i].itemName + "</td><td>" + score[i].mark + "</td><td>" + score[i].itemUnit + "</td><td>" + score[i].stuScore + "</td><td>" + score[i].stuLevel + "</td></tr>");
                    }
                    $('#chart6').highcharts({
                        chart: {
                            type: 'column'
                        },
                        title: {
                            text: ' '
                        },
                        subtitle: {
                            text: ' '
                        },
                        legend: {enabled: false},
                        xAxis: {
                            categories: itemArray
                        },
                        yAxis: {
                            min: 0,
                            title: {
                                text: ' '
                            },
                            min: 0,
                            /*max: 100*/
                        },
                        tooltip: {
                            headerFormat: '<span style="font-size:13px">{point.key}</span><table><br>',
                            pointFormat: '<td style="padding:0;"><b>{point.y:.1f}</b></td></tr>',
                            //footerFormat: '</table>',
                            shared: true,
                            useHTML: true
                        },
                        plotOptions: {
                            column: {
                                pointPadding: 0.2,
                                borderWidth: 0
                            }
                        },
                        series: [{
                            name: '最新体质健康测试成绩',
                            data: scoreArray,
                            color: '#19BE9D',
                            /*max: 100*/
                        }],
                        credits: {enabled: false}
                    });

                }

            }
            var line = retJson.itemScoreLog;
            var itemUnit = "";

            for (var i = 0; i < line.length; i++) {
                if (scoreType == 0) {
                    dataX[dataX.length] = line[i].testCount;
                }
                if (scoreType == 1) {
                    dataX[dataX.length] = line[i].testTime;
                }
                dataY[dataY.length] = Number(line[i].mark);
                itemUnit = line[0].itemUnit;
            }

            if (scoreType == 0) {
                sportBrokenLine(dataX, dataY);
            }
            if (scoreType == 1) {
                physicalBrokenLine(dataX, dataY);
            }
            $(".itemUnitC").html(itemUnit);
        });
    }

    function sportBrokenLine(dataX, dataY) {
        $('#chart5').highcharts({
            title: {
                text: ' '

            },
            subtitle: {
                text: ' '

            },
            xAxis: {
                categories: dataX
            },
            yAxis: {
                title: {
                    text: ' '
                },
            },
            credits: {enabled: false},
            tooltip: {
                headerFormat: '<span style="font-size:12px">{point.key}</span><table><br/>',
                //valueSuffix: 'h'
            },
            legend: {
                enabled: false
            },
            series: [{
                name: '体育成绩',
                data: dataY,
                color: '#19BE9D'
            }]
        });
    }

    function physicalBrokenLine(dataX, dataY) {
        $('#chart7').highcharts({
            title: {
                text: ' '

            },
            subtitle: {
                text: ' '

            },
            xAxis: {
                categories: dataX
            },
            yAxis: {
                title: {
                    text: ' '
                },


            },
            credits: {enabled: false},
            tooltip: {
                headerFormat: '<span style="font-size:12px">{point.key}</span><table><br/>',
                //valueSuffix: 'h'
            },
            legend: {
                enabled: false
            },
            series: [{
                name: '体质健康成绩',
                data: dataY,
                color: '#19BE9D'
            }]
        });
    }

    //参数为0为选择一个，1为范围调用
    function dailyHealth(type) {
        var beginDate, endDate, choose = $("#timeBetween .active").attr("id");
        if (type == 0) {
            endDate = getDaysBefore(1);//getTime("yyyyMMdd");
            if (choose == "day") {
                beginDate = getDaysBefore(1);
            } else if (choose == "week") {
                beginDate = getDaysBefore(7);
            } else if (choose == "month") {
                beginDate = getDaysBefore(0);
            }
        } else if (type == 1) {
            var time = $("#date-range").val().replace(/-/g, "").replace(/ /g, "");
            var arr = time.split("to");
            beginDate = arr[0];
            endDate = arr[1];
        }

        $("#avgDaily").html("");

        var dataX = [], dataY1 = [], dataY2 = [], dataY3 = [], stuNum = "${stuNum}";

        $.post("${ctx}/physical/archives/daily/" + stuNum, {
            beginDate: beginDate,
            endDate: endDate
        }, function (retJson) {
            if (retJson.avgData == null) {
                //layer.msg("暂无数据");
                $("#avgDaily").append("<li>运动时间:" + 0 + "</li>" +
                        "<li>步行步数:" + 0 + "步</li>" +
                        "<li>步行距离:" + 0 + "m</li>" +
                        "<li>运动耗能:" + 0 + "cal</li>" +
                        "<li>入睡时间:" + 0 + "</li>" +
                        "<li>睡眠时长:" + 0 + "</li>" +
                        "<li>睡眠质量:" + 0 + "</li>");
            }else {
                var sportTime = retJson.avgData.sportTime, walkAll = retJson.avgData.walkAll, distance = retJson.avgData.distance,
                        calories = retJson.avgData.calories,
                        asleepTime = retJson.avgData.asleepTime,
                        sleepLong = retJson.avgData.sleepLong,
                        sleepQuality = retJson.avgData.sleepQuality;

                $("#avgDaily").append("<li>运动时间:" + sportTime + "</li>" +
                        "<li>步行步数:" + walkAll + "步</li>" +
                        "<li>步行距离:" + distance + "m</li>" +
                        "<li>运动耗能:" + calories + "cal</li>" +
                        "<li>入睡时间:" + asleepTime + "</li>" +
                        "<li>睡眠时长:" + sleepLong + "</li>" +
                        "<li>睡眠质量:" + sleepQuality + "</li>");
            }
            var allData = retJson.allData;

            for (var i = 0; i < allData.length; i++) {
                dataX[dataX.length] = allData[i].info_date;
                dataY1[dataY1.length] = Number(allData[i].sportTime);
                // dataY2[dataY2.length] = allData[i].asleepTime * 1000 * 60;
                var map = {
                    "y": allData[i].asleepTime * 1000 * 60,
                    "extendRemark": allData[i].asleepTimeFormat,
                };
                dataY2[dataY2.length] = map;
                dataY3[dataY3.length] = Number(allData[i].sleepLong);
            }

            $('#chart1').highcharts({
                title: {
                    text: ' '

                },
                subtitle: {
                    text: ' '

                },
                xAxis: {
//                    categories: dataX
                     type :'datetime',
                    dateTimeLabelFormats: {
                        month: '%时 ： %b'
                    }
                },
                yAxis: {
                    title: {
                        text: ' '
                    },
                    min: 0,

                },
                credits: {enabled: false},
                tooltip: {
                    headerFormat: '<span style="font-size:12px">{point.key}</span><table><br/>',
                    //valueSuffix: 'h'
                },
                legend: {
                    enabled: false
                },
                series: [{
                    name: '运动时长趋势',
                    data: dataY1,
                    color: '#FFAF25',
                    pointStart: Date.UTC(beginDate.substring(0, 4), beginDate.substring(4, 6) - 1, beginDate.substring(6)),
                    pointInterval: 24 * 3600 * 1000 // one day
                }]
            });
            $('#chart2').highcharts({
                title: {
                    text: ' '
                },
                subtitle: {
                    text: ' '
                },
                xAxis: {
                  //  categories: dataX
                    type :'datetime',
                    dateTimeLabelFormats: {
                        month: '%时 ： %b'
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
                    // headerFormat: '<span style="font-size:12px">{point.key}</span><table><br/>',
                    formatter: function () {
                        return '<b>' + this.point.extendRemark + '</b>'
                    }
                },
                legend: {
                    enabled: false
                },
                series: [{
                    'name': '入睡时间',
                    data: dataY2,
                    color: '#19BE9D',
                    pointStart: Date.UTC(beginDate.substring(0, 4), beginDate.substring(4, 6) - 1, beginDate.substring(6)),
                    pointInterval: 24 * 3600 * 1000 // one day
                }],
            });
            $('#chart3').highcharts({
                title: {
                    text: ' '

                },
                subtitle: {
                    text: ' '

                },
                xAxis: {
                    //categories: dataX
                    type :'datetime',
                    dateTimeLabelFormats: {
                        month: '%时 ： %b'
                    }
                },
                yAxis: {
                    title: {
                        text: ' '
                    },
                    min: 0,

                },
                credits: {enabled: false},
                tooltip: {
                    headerFormat: '<span style="font-size:12px">{point.key}</span><table><br/>',
                    //valueSuffix: 'h'
                },
                legend: {
                    enabled: false
                },
                series: [{
                    name: '睡眠时长趋势',
                    data: dataY3,
                    color: '#D571F2',
                    pointStart: Date.UTC(beginDate.substring(0, 4), beginDate.substring(4, 6) - 1, beginDate.substring(6)),
                    pointInterval: 24 * 3600 * 1000 // one day
                }]
            });

        });
    }

    //yyyyMMddHHmmss 获取参数格式的当前日期
    function getTime(str) {
        var now = new Date();
        var year = now.getFullYear(); //getFullYear getYear
        var month = now.getMonth() + 1;
        var date = now.getDate();
        var hour = now.getHours();
        var minu = now.getMinutes();
        var sec = now.getSeconds();

        var formatStr = "";
        if (str.indexOf("yyyy") >= 0) {
            formatStr += year + "";
        }
        if (str.indexOf("MM") >= 0) {
            if (month < 10) {
                month = "0" + month;
            }
            formatStr += month + "";
        }
        if (str.indexOf("dd") >= 0) {
            if (date < 10) {
                date = "0" + date;
            }
            formatStr += date + "";
        }
        if (str.indexOf("HH") >= 0) {
            if (hour < 10) {
                hour = "0" + hour;
            }
            formatStr += hour + "";
        }
        if (str.indexOf("mm") >= 0) {
            if (minu < 10) {
                minu = "0" + minu;
            }
            formatStr += minu + "";
        }
        if (str.indexOf("ss") >= 0) {
            if (sec < 10) {
                sec = "0" + sec;
            }
            formatStr += sec + "";
        }
        return formatStr.replace(" ", "");
    }
    //获取当前日期的前n天的日期  0为一个月。其他数字，向前推n天
    function getDaysBefore(many) {
        var date = new Date(), timestamp, newDate, year, month;
        if (!(date instanceof Date)) {
            date = new Date(date.replace(/-/g, '/'));
        }
        timestamp = date.getTime();
        year = date.getFullYear();
        month = date.getMonth() + 1;
        var days = new Date(year, month, 0);
        days = days.getDate(); //获取当前日期中月的天数
        if (many == 0) {
            many = days;
        }
        newDate = new Date(timestamp - many * 24 * 3600 * 1000);
        //return [[newDate.getFullYear(), newDate.getMonth() + 1, newDate.getDate()].join('/'), [newDate.getHours(), newDate.getMinutes(), newDate.getSeconds()].join(':')].join(' ');
        var newMonth = newDate.getMonth() + 1,
                newDay = newDate.getDate();
        if (newDate.getMonth() < 10) {
            newMonth = "0" + newMonth;
        }
        if (newDate.getDate() < 10) {
            newDay = "0" + newDay;
        }
        return [newDate.getFullYear(), newMonth, newDay].join('');
    }
</script>
</body>
</html>