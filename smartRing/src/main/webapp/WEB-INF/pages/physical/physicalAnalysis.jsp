<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>体质分析</title>
    <link rel="stylesheet" href="${ctxStatic}/css/physicalHealth.css"/>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
            <span class="lf current-position">
                当前位置：
            </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a>体质健康</a></li>
        <li class="child-nav  active"><a>体质分析</a></li>
    </ul>
</header>
<main class="fix-width">
    <section class="items-selector module-bg">
        <div class="clear">
            <span class="lf">年级</span>
            <ul id="njSelect" class="lf clear select-items">
                <c:forEach items="${njList}" var="nj">
                    <li
                            <c:if test="${njChoose eq nj}">class="active" </c:if> >${nj}</li>
                </c:forEach>
            </ul>
            <div class="header-select-more">更多</div>
        </div>
        <div class="clear">
            <span class="lf">班级</span>
            <ul id="bjSelect" class="lf clear select-items">
                <li
                        <c:if test="${bjChoose eq 0}">class="active" </c:if> id="0">全部
                </li>
                <c:forEach items="${bjList}" var="bj">
                    <li
                            <c:if test="${bjChoose eq bj.classId}">class="active" </c:if>
                            id="${bj.classId}">${bj.className}</li>
                </c:forEach>
            </ul>
            <div class="header-select-more">更多</div>
        </div>
        <div class="clear">
            <span class="lf">性别</span>
            <ul id="genderSelect" class="lf clear select-items">
                <li
                        <c:if test="${genderChoose == 0}">class="active" </c:if> id="0">全部
                </li>
                <li
                        <c:if test="${genderChoose == 1}">class="active" </c:if> id="1">男
                </li>
                <li
                        <c:if test="${genderChoose == 2}">class="active" </c:if> id="2">女
                </li>
            </ul>
        </div>
    </section>
    <main id="ph-index" class="fix-width module-bg-full">
        <p>最新测试成绩</p>
        <section>
            <table class="table">
                <thead>
                <tr>
                    <th>项目</th>
                    <th>时间</th>
                    <th>测试成绩</th>
                    <th>成绩单位</th>
                    <th>分数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${scoreRes}" var="score">
                    <tr>
                        <td>${score.itemName}</td>
                        <td>${score.testTime}</td>
                        <td>${score.mark}</td>
                        <td>${score.itemUnit}</td>
                        <td>${score.score}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <div class="column-chart">
            <span>分</span>
            <div id="chart1" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
        <div class="pie-chart">
            <ul class="clear">
                <c:forEach items="${scoreRes}" var="score" varStatus="status">
                    <li>
                        <h3>${score.itemName}</h3>
                        <div id="pie${status.index}" style="width:360px;height:240px;margin:0 auto;"></div>
                    </li>
                    <script>
                        $('#pie${status.index}').highcharts({
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
                                    },
                                    showInLegend: true
                                }
                            },
                            legend: {
                                itemStyle: {
                                    color: '#676767',
                                    fontSize: '12px',
                                    fontWeight: 'normal',
                                    fontFamily: 'microsoft Yahei'
                                }
                            },
                            series: [{
                                name: '${score.itemName}',
                                colorByPoint: true,
                                data: [{
                                    <c:if test="${score.itemName.indexOf('BMI') >= 0}">
                                    name: '正常',
                                    </c:if>
                                    <c:if test="${score.itemName.indexOf('BMI') < 0}">
                                    name: '优秀',
                                    </c:if>
                                    y: ${score.percent1},
                                    color: '#FF7F50'
                                }, {
                                    <c:if test="${score.itemName.indexOf('BMI') >= 0}">
                                    name: '超重',
                                    </c:if>
                                    <c:if test="${score.itemName.indexOf('BMI') < 0}">
                                    name: '良好',
                                    </c:if>
                                    y: ${score.percent2},
                                    color: '#87CEFA',
                                    //selected: false
                                }, {
                                    <c:if test="${score.itemName.indexOf('BMI') >= 0}">
                                    name: '肥胖',
                                    </c:if>
                                    <c:if test="${score.itemName.indexOf('BMI') < 0}">
                                    name: '及格',
                                    </c:if>
                                    y: ${score.percent3},
                                    color: '#DA70D6',
                                    //selected: false
                                }, {
                                    <c:if test="${score.itemName.indexOf('BMI') >= 0}">
                                    name: '低体重',
                                    </c:if>
                                    <c:if test="${score.itemName.indexOf('BMI') < 0}">
                                    name: '不及格',
                                    </c:if>
                                    y: ${score.percent4},
                                    color: '#32CD32',
                                    //selected: false
                                }]
                            }],
                            credits: {enabled: false},
                        });
                    </script>
                </c:forEach>
            </ul>
        </div>
    </main>
    <section class="phy-grade-tendency module-bg-full">
        <p class="clear">
            成绩变化趋势
            <select name="" id="itemSelect" class="rl">
                <c:forEach items="${itemList}" var="item">
                    <option <c:if test="${itemIdChoose ==item.itemId }"> selected</c:if>
                            value="${item.itemId}">${item.itemName}</option>
                </c:forEach>
            </select>
        </p>
        <div class="phy-tendency">
            <span id="itemUnit">单位</span>
            <div id="tendency-chart1" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
        <div class="intro-table">
            <table class="table">
                <thead>
                <tr>
                    <th>时间</th>
                    <th>测试成绩</th>
                    <th>成绩单位</th>
                    <th>分数</th>
                    <%-- <th>等级</th>--%>
                </tr>
                </thead>
                <tbody id="scoreChangeT1">
                <%--append--%>
                </tbody>
            </table>
        </div>
        <div class="phy-tendency">
            <span>%</span>
            <div id="tendency-chart2" style="width:1100px;height:300px;margin:0 auto;"></div>
        </div>
        <div class="intro-table">
            <table class="table">
                <thead>
                <tr>
                    <th width="20%">时间</th>
                    <th width="20%">优秀</th>
                    <th width="20%">良好</th>
                    <th width="20%">及格</th>
                    <th width="20%">不及格</th>
                </tr>
                </thead>
                <tbody id="scoreChangeT2">
                <%--append--%>
                </tbody>
            </table>
        </div>
    </section>

    <%-- <div class="noData module-bg clear fix-width">
         暂无相关数据，请在 体质健康--成绩管理 模块，导入相关数据
         <a href="${ctx}/physical/score/index">去导入数据</a>
     </div>--%>
</main>

<script>
    $("#physicalHealth").addClass("active");
    $("#physicalHealth ul li:nth-child(1)>a").addClass("active");

    //班级年级更多
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
                    } else {
                        $(grade).parent().css('height', '25px');
                    }
                })
            }
        })
    }
    isHide($('#njSelect li'))
    isHide($('#bjSelect li'))

    $('.select-items li').click(function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        pageReload();
    })

    line();//页面加载默认查询第一个select option
    $("select").change(function () {
        // pageReload();
        line();
    });

    function pageReload() {
        var nj = $("#njSelect >.active").text();
        var bj = $("#bjSelect >.active").attr("id");
        var gender = $("#genderSelect >.active").attr("id");
        var itemId = $("#itemSelect option:selected").val();

        window.location.href = '${ctx}/physical/analysis/index?nj=' + encodeURI(encodeURI(nj)) + "&bj=" + bj + "&gender=" + gender + "&itemId=" + itemId;

    }

    function line() {
        $("#itemUnit").html("单位");
        var nj = $("#njSelect >.active").text();
        var bj = $("#bjSelect >.active").attr("id");
        var gender = $("#genderSelect >.active").attr("id");
        var itemId = $("#itemSelect option:selected").val();

        $.post("${ctx}/physical/analysis/score/line", {
            xdId: "${xdId}",
            njId: "${njId}",
            gender: gender,
            itemId: itemId,
        }, function (retJson) {
            $("#scoreChangeT1").html("");
            $("#scoreChangeT2").html("");
            var itemArray = [];
            var markArray = [];
            var percent1Array = [];
            var percent2Array = [];
            var percent3Array = [];
            var percent4Array = [];
            for (var i = 0; i < retJson.length; i++) {

                $("#itemUnit").html(retJson[0].itemUnit);

                itemArray[itemArray.length] = retJson[i].testTime;
                markArray[markArray.length] = retJson[i].mark;
                percent1Array[percent1Array.length] = retJson[i].percent1;
                percent2Array[percent2Array.length] = retJson[i].percent2;
                percent3Array[percent3Array.length] = retJson[i].percent3;
                percent4Array[percent4Array.length] = retJson[i].percent4;
                $("#scoreChangeT1").append("<tr><td>" + retJson[i].testTime + "</td><td>" + retJson[i].mark + "</td><td>" + retJson[i].itemUnit + "</td><td>" + retJson[i].score + "</td></tr>");
                $("#scoreChangeT2").append("<tr><td>" + retJson[i].testTime + "</td><td>" + retJson[i].percent1 + "</td><td>" + retJson[i].percent2 + "</td> <td>" + retJson[i].percent3 + "</td> <td>" + retJson[i].percent4 + "</td></tr>");

            }
            $('#tendency-chart1').highcharts({
                title: {
                    text: ' '
                },
                subtitle: {
                    text: ' '
                },
                xAxis: {
                    categories: itemArray
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
                    name: '运动时长',
                    data: markArray,
                    color: '#19BE9D'
                }]
            });

            $('#tendency-chart2').highcharts({
                chart: {
                    type: 'column'
                },
                title: '',
                xAxis: {
                    categories: itemArray
                },
                yAxis: {
                    min: 0,
                    max: 100,
                    title: {
                        enabled: false,
                        text: 'Total fruit consumption'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:11px">{point.key}</span><table><br>',
                    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
                    shared: true
                },
                plotOptions: {
                    column: {
                        stacking: 'percent'
                    }
                },
                series: [{
                    name: '优秀',
                    data: percent1Array,
                    color: '#2FC48E'
                }, {
                    name: '良好',
                    data: percent2Array,
                    color: '#65E2C9'
                }, {
                    name: '及格',
                    data: percent3Array,
                    color: '#EFCA5A'
                }, {
                    name: '不及格',
                    data: percent4Array,
                    color: '#FC946B'
                }],
                credits: {enabled: false},
                legend: {
                    enabled: true,
                    itemStyle: {color: '#676767', fontSize: '12px', fontWeight: 'normal', fontFamily: 'microsoft Yahei'}
                }
            });
        });


    }

    $('#chart1').highcharts({
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
            categories: [
                <c:forEach items="${scoreRes}" var="score">
                '${score.itemName}',
                </c:forEach>
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            },
            min: 0,
            max: 100
        },
        tooltip: {
            headerFormat: '<span style="font-size:13px;margin:0;">{point.key}：</span><b>{point.y:.1f}</b>',
            pointFormat: '',
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
            data: [
                <c:forEach items="${scoreRes}" var="score">
                ${score.score},
                </c:forEach>
            ],
            color: '#19BE9D',
            max: 100
        }],
        credits: {enabled: false},

    });


</script>
</body>
</html>