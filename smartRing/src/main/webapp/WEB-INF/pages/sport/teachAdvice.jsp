<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>教学建议</title>
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
        <li class="root-nav"><a href="sportsTest.html">体育测试</a></li>
        <li class="child-nav  active"><a href="sportsTest2.html">教学建议</a></li>
    </ul>
</header>
<section class="items-selector module-bg fix-width">
    <div class="clear">
        <span class="lf">年级</span>
        <ul id="njSelect" class="lf clear select-items">
            <%--<li <c:if test="${njChoose eq '全部'}">class="active" </c:if> id="-1">全部</li>--%>
            <c:forEach items="${njList}" var="nj">
                <li <c:if test="${njChoose eq nj}">class="active" </c:if> >${nj}</li>
            </c:forEach>
        </ul>
        <div class="header-select-more">更多</div>
    </div>
    <c:if test="${gukeer:notEmptyString(bjList)}">
    <div class="clear">
        <span class="lf">班级</span>
        <ul id="bjSelect" class="lf clear select-items">
            <li <c:if test="${bjChoose eq -1}">class="active" </c:if> id="-1">全部
            </li>
            <c:forEach items="${bjList}" var="bj">
                <li <c:if test="${bjChoose == bj.classId}">class="active" </c:if> id="${bj.classId}">${bj.className}</li>
            </c:forEach>
        </ul>
        <div class="header-select-more">更多</div>
    </div>
    </c:if>
    <div class="clear">
        <span class="lf">性别</span>
        <ul id="genderSelect" class="lf clear select-items" style="margin-bottom:0;">
            <li <c:if test="${genderChoose == -1 }">class="active" </c:if> id="-1">全部</li>
            <li <c:if test="${genderChoose == 1}">class="active" </c:if> id="1">男</li>
            <li <c:if test="${genderChoose == 2}">class="active" </c:if> id="2">女</li>
        </ul>
    </div>
</section>
<main class="haveData fix-width module-bg clear test-grade">
    <p>最新测试分数</p>
    <aside id="test-grade" class="lf" style="width:640px;height:300px;"></aside>
    <div>
        <table class="table rl">
            <tr>
                <td>项目</td>
                <td>平均成绩</td>
                <td>成绩单位</td>
                <td>平均分</td>
            </tr>
            <c:forEach items="${scoreRes}" var="scoreAvg">
                <tr>
                    <td>${scoreAvg.itemName}</td>
                    <td>
                            <c:choose>
                                <c:when test="${scoreAvg.itemUnit.indexOf('分')>=0}">
                                    ${gukeer:unitTranslate(scoreAvg.mark)}
                                </c:when>
                                <c:otherwise>
                                    ${scoreAvg.mark}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    <td>${scoreAvg.itemUnit}</td>
                    <td>${scoreAvg.score}分</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</main>
<div class="haveData module-bg-full lacking-grade fix-width">
    <p>
        成绩不足学生名单
        <select name="" id="itemListR" class="rl">
            <option value="-1">全部</option>
            <c:forEach items="${itemList}" var="item">
                <option <c:if test="${itemIdChoose ==item.itemId }"> selected</c:if>
                        value="${item.itemId}">${item.itemName}</option>
            </c:forEach>
        </select>
    </p>
    <div>
        <table class="table">
            <thead>
            <tr>
                <td>姓名</td>
                <td>学号</td>
                <td>项目</td>
                <td>测试成绩</td>
                <td>测试单位</td>
                <td>分数</td>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="failStu" items="${failStuList}">
                    <tr>
                        <td>${failStu.xsxm}</td>
                        <td>${failStu.stuNum}</td>
                        <td>${failStu.itemName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${failStu.itemUnit.indexOf('分')>=0}">
                                ${gukeer:unitTranslate(failStu.mark)}
                                </c:when>
                                <c:otherwise>
                                ${failStu.mark}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${failStu.itemUnit}</td>
                        <td>${failStu.stuScore}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>
<div class="noData module-bg fix-width">
    暂无相关数据，请在 体育测试--成绩管理 模块，导入相关数据
    <a href="${ctx}/sport/scoremange/index">去导入数据</a>
</div>

<script>
    $("#peTest").addClass("active");
    $("#peTest ul li:nth-child(2)>a").addClass("active");
    //年级班级更多
    function isHide(grade){
        if(grade.length == 0)
            return;
        var length=0,pLength=grade.parent()[0].clientWidth;
        grade.map(function(i,key){
            length+=key.clientWidth;
            if(length>=pLength){
                $(grade).parent().css('height','25px');
                $(grade).parent().css('overflow','hidden');
                var selectMore=$(grade).parent().next();
                selectMore.show();
                selectMore.click(function(){
                    if($(grade).parent().css('height')=='25px'){
                        $(grade).parent().css('height','auto');
                    }else{
                        $(grade).parent().css('height','25px');
                    }
                })
            }
        })
    }
    isHide($('#njSelect li'))
    isHide($('#bjSelect li'))

    <c:if test="${gukeer:isNullOrEmpty(scoreRes)}">
    $(".haveData").hide();
    $(".noData").show();
    </c:if>
    <c:if test="${!gukeer:isNullOrEmpty(scoreRes)}">
    $(".haveData").show();
    </c:if>

    $('#test-grade').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: ' '
        },
        subtitle: {
            text: ' '
        },
        legend:{enabled:false},
        xAxis: {
            categories: [
              //  '50米跳','立定跳远','引体向上','一分钟仰卧起坐'
                <c:forEach items="${scoreRes}" var="scoreAvg">
                '${scoreAvg.itemName}',
                </c:forEach>
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:13px">{point.key}</span><br>',
            pointFormat:
                    '<td style="padding:0;"><b>{point.y:.1f}</b></td>',
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
            //data: [78,85,97,77],
            data: [
                <c:forEach items="${scoreRes}" var="scoreAvg">
                ${scoreAvg.score},
                </c:forEach>
            ],
            color:'#19BE9D',
            max:100
        }],
        credits:{enabled:false}
    });

    $('body').on("click", ".select-items li", function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');

        pageReload();

    })

    $("select").change(function () {
        pageReload();
    });

    function pageReload() {
        var nj = $("#njSelect >.active").text();
        var bj = $("#bjSelect >.active").attr("id");
        var gender = $("#genderSelect >.active").attr("id");
        var itemId = $("#itemListR option:selected").val();

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

        window.location.href = '${ctx}/sport/teach/index?nj=' + encodeURI(encodeURI(nj)) + "&bj=" + bj + "&gender=" + gender + "&itemId=" + itemId;
    }



</script>
</body>
</html>