<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>日常管理</title>
    <link rel="stylesheet" href="${ctxStatic}/css/dailyHealth.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/sportstest.css"/>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a>日常健康</a></li>
        <li class="child-nav  active"><a>日常管理</a></li>
    </ul>
</header>
<div id="bodyContainer">
    <main class="module-bg-full" id="daily-manage" style="display:block;">
        <p>日常健康设置</p>
        <section style="border-bottom:none;">
            <c:forEach items="${standardViews}" var="standard" varStatus="i">
                <div class="clear">
                    <aside class="lf">
                        <span>年级</span>
                        <input id="class-${i.index}" value="${standard.id}" hidden>
                        <span class="class">${standard.xdName} ${gukeer:getGradeNj(standard.nj)}</span>
                    </aside>
                    <section class="rl">
                        <span>运动时长（min）</span>
                        <input type="number" min="0" value="${standard.sportStandard}" id="sport-${i.index}"/>
                    </section>
                </div>
                <div class="clear">
                    <aside class="lf">
                        <span>入睡时间</span>
                        <input type="number" min="0" max="23" value="${gukeer:getHour(standard.asleepStandard)}" id="hour-${i.index}"/>时
                        <input type="number" min="0" max="59" value="${gukeer:getMin(standard.asleepStandard)}" id="min-${i.index}"/>分
                    </aside>
                    <section class="rl">
                        <span>睡眠时长（min）</span>
                        <input type="number" step="0.1" min="5" max="12" value="${standard.sleepStandard}" id="sleep-${i.index}"/>
                    </section>
                </div>
            </c:forEach>
            <div style="text-align: center;margin-bottom:0;">
                <button onclick="save()" style="border:1px solid #19BE9D;background:#1abe9d;font-size:14px;color:#fff;height:28px;padding:0 15px;border-radius:3px;">保存</button>
            </div>
        </section>


        <script>
            $("#dailyHealth").addClass("active");
            $("#dailyHealth ul li:nth-child(3)>a").addClass("active");

            var len = ${standardViews.size()};
            function save() {
                if(len>0){
                    var data = [];
                    for(var i = 0 ; i < len ; i++){
                        var obj = new Object();
                        obj.id = $('#class-'+i).val();
                        obj.sport = $('#sport-'+i).val();
                        obj.hour = $('#hour-'+i).val();
                        obj.min = $('#min-'+i).val();
                        obj.sleep = $('#sleep-'+i).val();
                        data.push(obj);
                    }
                    $.post('${ctx}/dailyhealth/manage/save',{
                        data:JSON.stringify(data),
                    },function (res) {
                        layer.msg(res.msg,{time:1500},function () {
                            window.location.reload();
                        })
                    })
                }
            }
        </script>
    </main>
</div>

</body>
</html>
