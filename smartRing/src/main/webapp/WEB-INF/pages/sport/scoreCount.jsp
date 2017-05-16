<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>成绩统计</title>
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
        <li class="root-nav"><a>体育测试</a></li>
        <li class="child-nav  active"><a>成绩统计</a></li>
    </ul>
</header>
<section class="items-selector module-bg fix-width">
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
                    <c:if test="${bjChoose eq 0}">class="active " </c:if> id="0">全部
            </li>
            <c:forEach items="${bjList}" var="bj">
                <li
                        <c:if test="${bjChoose == bj.classId}">class="active" </c:if>
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
    <div class="clear">
        <span class="lf">项目</span>
        <ul id="itemSelect" class="lf clear select-items" style="margin-bottom:0;">
            <c:forEach items="${itemList}" var="item">
                <li
                        <c:if test="${itemIdChoose eq item.itemId}">class="active" </c:if>
                        id="${item.itemId}">${item.itemName}</li>
            </c:forEach>
        </ul>
        <div class="header-select-more">更多</div>
    </div>
</section>
<div class="haveData latest-grade module-bg clear fix-width">
    <p>最新测试成绩</p>
    <aside id="latest-grade" class="lf" style="width:600px;height:300px;"></aside>
    <main class="rl" style="width:400px;padding-top:10px;">
        <div class="clear">
            <span>
            <c:choose>
                <c:when test="${personAvg.itemUnit.indexOf('分')>=0}">
                    ${gukeer:unitTranslate(personAvg.minMark)}
                </c:when>
                <c:otherwise>
                    ${personAvg.minMark}
                </c:otherwise>
            </c:choose>
                ${personAvg.itemUnit}
            </span>
            <aside class="lf">
                <span>最低成绩</span>
            </aside>
            <aside class="rl">
                <span>${personAvg.minScore}分</span>
            </aside>
        </div>
        <div class="clear">
            <span>
             <c:choose>
                 <c:when test="${personAvg.itemUnit.indexOf('分')>=0}">
                     ${gukeer:unitTranslate(personAvg.maxMark)}
                 </c:when>
                 <c:otherwise>
                     ${personAvg.maxMark}
                 </c:otherwise>
             </c:choose>

                ${personAvg.itemUnit}</span>
            <aside class="lf">
                <span>最高成绩</span>
            </aside>
            <aside class="rl">
                <span>${personAvg.maxScore}分</span>
            </aside>
        </div>
        <div class="clear">
            <span>
             <c:choose>
                 <c:when test="${personAvg.itemUnit.indexOf('分')>=0}">
                     ${gukeer:unitTranslate(personAvg.mark)}
                 </c:when>
                 <c:otherwise>
                     ${personAvg.mark}
                 </c:otherwise>
             </c:choose>
            ${personAvg.itemUnit}
            </span>

            <aside class="lf">
                <span>平均成绩</span>
            </aside>
            <aside class="rl">
                <span>${personAvg.score}分</span>
            </aside>
        </div>
    </main>
</div>
<div class="haveData grade-tendency module-bg clear fix-width">
    <p>成绩变化趋势</p>
    <div class="clear cutline">
        <span class="lf">${personAvg.itemUnit}</span>
        <ul>
            <li>成绩</li>
            <li>优秀率</li>
            <li>及格率</li>
        </ul>
        <span class="rl">%</span>
    </div>
    <aside id="grade-tendency" class="lf" style="width:740px;height:300px;"></aside>
    <main class="rl fix-width">
        <div class="clear">
            <aside class="lf">
                <span>人均成绩</span>
            </aside>
            <aside class="rl">
                <span>
                <c:choose>
                    <c:when test="${personAvg.itemUnit.indexOf('分')>=0}">
                        ${gukeer:unitTranslate(personAvg.mark)}
                    </c:when>
                    <c:otherwise>
                        ${personAvg.mark}
                    </c:otherwise>
                </c:choose>
               ${personAvg.itemUnit}
                </span>
            </aside>
        </div>
        <div class="clear">
            <aside class="lf">
                <span>与上次相比</span>
            </aside>
            <aside class="rl">
                 <span style="padding-right:19px;position:relative;">
                  <c:choose>
                      <c:when test="${personAvg.itemUnit.indexOf('分')>=0}">
                          ${gukeer:unitTranslate(delete)}秒
                      </c:when>
                      <c:otherwise>
                          ${delete}${personAvg.itemUnit}
                      </c:otherwise>
                  </c:choose>
                   <c:choose>
                       <c:when test="${delete>0}">
                           <i class="pull-u"></i>
                       </c:when>
                       <c:when test="${delete<0}">
                           <i class="pull-d"></i>
                       </c:when>
                       <c:otherwise>

                       </c:otherwise>
                   </c:choose>
                </span>
            </aside>
        </div>
    </main>
</div>
<div class="noData module-bg clear fix-width">
    暂无相关数据，请在 体育测试--成绩管理 模块，导入相关数据
    <a href="${ctx}/sport/scoremange/index">去导入数据</a>
</div>

<script>
    $(function () {
        <c:if test="${gukeer:isNullOrEmpty(lines)}">
        $(".haveData").hide();
        $(".noData").show();
        </c:if>
        <c:if test="${!gukeer:isNullOrEmpty(lines)}">
        $(".haveData").show();
        </c:if>

        $("#peTest").addClass("active");
        $("#peTest ul li:nth-child(1)>a").addClass("active");

        $('#grade-tendency').highcharts({
            chart: {
                zoomType: 'xy'
            },
            title: false,
            xAxis: [{
                categories: [
                    <c:forEach items="${lines}" var="line">
                    ${line.testCount},
                    </c:forEach>
                ]
            }],
            yAxis: [{ // 左Y轴
                title: false,
                labels: {
                    style: {
                        color: '#525252'
                    },
                    <c:if test="${personAvg.itemUnit.indexOf('分')>=0}">
                    formatter: function () {
                        var hh = Math.floor(this.value / 3600);
                        var mm = Math.floor(this.value % 3600 / 60);
                        var ss = Math.floor(this.value % 60);
                        if (hh != 0) {
                            return hh + '时' + mm + '分' + ss + '秒';
                        }
                        else {
                            return mm + '分' + ss + '秒';
                        }
                    }
                    </c:if>
                },

            }, { // 右Y轴
                labels: {
                    //format: '{value}°C',
                    style: {
                        color: '#525252',
                        fontSize: '12px'
                    }
                },
                opposite: true,
                title: false,
                max: 100
            }],
            tooltip: {
                shared: true
            },
            legend: {
                enabled: false
            },
            series: [{
                name: '成绩',
                color: '#19BE9D',
                type: 'spline',
                data: [
                    <c:forEach items="${lines}" var="line">
                    ${line.mark},
                    </c:forEach>
                ],
                tooltip: {
                    <c:if test="${personAvg.itemUnit.indexOf('分')>=0}">
                    headerFormat: '<span style="font-size: 10px">{point.key}</span><br/>',
                    pointFormatter: function () {
                        var hh = Math.floor(this.y / 3600)-1;
                        var mm = Math.floor(this.y % 3600 / 60);
                        var ss = Math.floor(this.y % 60);
                        var res;
                        if (hh == 0) {
                            res = mm + '分' + ss + '秒';
                        } else {
                            res = hh + '时' + mm + '分' + ss + '秒';
                        }
                        return '<span style="color:' + this.series.color + '">● </span>' + this.series.name + ':<b>' + res + '</b><br/>'
                    }
                    </c:if>
                    <c:if test="${personAvg.itemUnit.indexOf('分') < 0}">
                        valueSuffix: '${personAvg.itemUnit}'
                    </c:if>

                }

            }, {
                name: '优秀率',
                color: '#FFAF25',
                type: 'spline',
                yAxis: 1,
                data: [
                    <c:forEach items="${lines}" var="line">
                    ${line.yxl},
                    </c:forEach>
                ],
                tooltip: {
                    //valueSuffix: '°C'
                },
            }, {
                name: '及格率',
                color: '#5093E7',
                type: 'spline',
                yAxis: 1,
                data: [
                    <c:forEach items="${lines}" var="line">
                    ${line.jgl},
                    </c:forEach>
                ],
                tooltip: {
                    //valueSuffix: '°C'
                },
            }],
            credits: {enabled: false}

        });
        $('#latest-grade').highcharts({
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
                        format: '<b></b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'purple'
                        }
                    },
                    showInLegend: true,
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
                name: ' ',
                colorByPoint: true,
                data: [{
                    name: '及格',
                    y: ${gukeer:formatStr(personAvg.jg)},
                    color: '#FF8050'
                }, {
                    name: '良好',
                    y: ${gukeer:formatStr(personAvg.lhl)},
                    color: '#87CFFA',
                    //selected: false
                }, {
                    name: '不及格',
                    y: ${gukeer:formatStr(personAvg.bjgl)},
                    color: '#DA70D5',
                    //selected: false
                }, {
                    name: '优秀',
                    y:${gukeer:formatStr(personAvg.yxl)},
                    color: '#30CE32',
                    //selected: false
                }]
            }],
            credits: {enabled: false}
        });
    });
    //班级年级更多。。
    var njLis = $('#njSelect li');
    var bjLis = $('#bjSelect li');
    var xmLis = $('#itemSelect li');
    isHide(njLis)
    isHide(bjLis)
    isHide(xmLis)
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


    $('.select-items li').click(function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        pageReload();
    })

    function pageReload() {
        var nj = $("#njSelect >.active").text();
        var bj = $("#bjSelect >.active").attr("id");
        var itemId = $("#itemSelect >.active").attr("id");
        var gender = $("#genderSelect >.active").attr("id");

        var temp = '${njChoose}';
        if (nj != temp) {
            bj = "";
        }
        if (typeof(bj) == "undefined") {
            bj = "";
        }
        if (typeof(gender) == "undefined") {
            gender = "";
        }
        window.location.href = '${ctx}/sport/score/index?nj=' + encodeURI(encodeURI(nj)) + "&bj=" + bj + "&gender=" + gender + "&itemId=" + itemId;
    }

</script>

</body>
</html>