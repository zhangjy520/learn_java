<%--
  Created by IntelliJ IDEA.
  User: LL
  Date: 2017/5/9
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<%@ include file="../common/headerXf.jsp" %>
<html>
<head>
    <title>教务管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    .row {
        margin: auto;
    }

    .search-box select {
        height: 30px;
    }

    .searchh button:after {
        content: '';
        display: inline-block;
        width: 16px;
        height: 16px;
        position: absolute;
        top: 8px;
        left: 10px;
        background: url(../../../assetsNew/images/icon-2.png) no-repeat -1px -65px
    }

    .searchh button {
        float: right;
        height: 30px;
        background: #54ab37;
        width: 35px;
        position: relative;
        border: 1px solid #54ab37;
        border-top-right-radius: 2px;
        border-bottom-right-radius: 2px
    }

    .searchh input {
        float: right;
        height: 31px;
        border: 1px solid #dadada;
        border-right: 0;
        width: 152px;
        padding-left: 5px;
        outline: 0
    }

    #generated {
        padding-top: 20px;
    }

    .but {
        padding-left: 15px;
    }

    .course ul li a {
        display: inline-block;
        width: 135px;
        height: 45px;
        line-height: 44px;
        font-size: 15px;
        color: #777;
        text-align: center;
        text-decoration: none;
    }

    .course ul li a.active {
        color: #54ab37;
        border: 1px solid #ddd;
        border-bottom: 0;
        background: #fff;
    }
    .course ul li a:hover{
        color: #54ab37;
    }
    .course {
        margin-top: 30px
    }

    .course ul {
        border-bottom: 1px solid #ddd;
        height: 45px
    }

    .course ul li {
        float: left
    }

    #zh-manage .search-box{
        margin-top: 0 !important;
    }
    .course ul{
        border: none;
    }
    .course ul li a{
        border-bottom: 1px solid #ddd;
    }
    .stu-num-manage-menu{
        display: inline-block;
    }
    .course ul{
        margin-bottom: 0 !important;
    }
    .roll-operation{
        vertical-align: middle !important;
    }
    .btn-containt input{
        border: 1px solid #54ab37;
        border-radius: 2px;
        background: #54AB37;
        color: #fff;
        width: 70px;
        height: 30px;
        line-height: 26px;
        margin: 20px 0 0 0;
    }
    .cla-hour-containt{
        margin-top: 40px;
    }
    .cla-hour-containt span{
        margin-bottom: 20px;
    }
    .cla-hour-containt span input{
        height: 30px;
        line-height: 30px;
        margin-left: 10px;
        padding-left: 10px;
    }
</style>

<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="search-box">
        <div class="stu-num-manage-menu" style="display: inline-block">
            学年：
            <select name="cycleYear" class="cycleYear">
                <c:forEach items="${yearList}" var="year">
                    <option name="cycleYear"
                            <c:if test="${cycleYear ==year}">selected</c:if> value="${year}">${year}</option>
                </c:forEach>
            </select>
            学期：
            <select name="cycleSemester" class="cycleSemester">
                <c:forEach items="${semesterList}" var="cycle">
                    <option name="cycleSemester" value="${cycle.cycleSemester}"
                            <c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if>>${cycle.cycleSemester}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="course">
        <ul id="aul">
            <c:forEach items="${courseList}" var="course" varStatus="status">
                <li><a href="#"
                       <c:if test="${course.id==courseId}">class="active"</c:if> va="${course.id}"
                       class="acourse">${course.name}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="cla-hour-containt">
        <c:forEach items="${courseClassViewList}" varStatus="status" var="courseClassView">
            <c:if test="${courseClassView.classSection!=null}">
            <span class="njBanji" value="${courseClassView.classSectionId}"> <span  valu="${courseClassView.nj}" class="tdForNj" style="display: none"></span>
            ${courseClassView.classSection}${courseClassView.nj}年级:<input type="text" value="${courseClassView.courseHour}"></span><%--<span  value="${status.count+1}" class="tdForNj" style="display: none"></span>--%></span>
               <br>
            </c:if>
        </c:forEach>
    </div>
    <div class="btn-containt">
        <input type="button" onclick="save()" class="save" value="保存">
    </div>
</main>
</body>
<script>
    $(function () {
        $("select").change(function () {
            var cycleSemester = $("select[name='cycleSemester']").val();
            var cycleYear = $("select[name='cycleYear']").val();
            var courseId = $('.active').val();
            window.location.href = "${ctx}/teach/task/course/hour?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });
    });

    $(".acourse").click(function () {
        $(this).addClass('active');
        $(this).removeClass("acourse");
        $(".acourse").removeClass("active");
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        var courseId = $(this).attr("va");
        window.location.href = "${ctx}/teach/task/course/hour?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&courseId=" + courseId;
    });


    function save() {
        debugger;
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        var courseId = $('#aul').find('.active').attr('va');
        var sectionIdSpan = $('.njBanji');
        console.log(sectionIdSpan.length);
        var sectionIdAndCourseHour = "";
        sectionIdSpan.each(function (i) {
            console.log($(this).siblings("input").val());
            sectionIdAndCourseHour += "," + $(this).attr("value") + ":" + $(this).children("span").attr("valu") + ":" + $(this).children("input").val();
        });
        $.post("${ctx}/teach/task/course/hour/edit", {
            sectionIdAndCourseHour: sectionIdAndCourseHour,
            courseId: courseId,
            cycleYear:cycleYear,
            cycleSemester:cycleSemester
        }, function (data) {
            setTimeout(function () {
                window.location.reload()
            }, 2000);
        })
    }


</script>
</html>
