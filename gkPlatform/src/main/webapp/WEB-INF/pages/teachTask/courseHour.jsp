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
    <title>科目课时安排</title>
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

    .course ul li a.active {
        color: #525252;
        border: 3px solid #ddd;
        border-bottom: 0;
        background: #fff;
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
        color: #525252;
        border: 1px solid #ddd;
        border-bottom: 0;
        background: #fff;
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
        <ul id="ullll">
            <c:forEach items="${courseList}" var="course" varStatus="status">
                <li><a href="#"
                       <c:if test="${course.id==courseId}">class="active"</c:if> va="${course.id}"
                       class="acourse">${course.name}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div>
        <c:forEach items="${courseClassViewList}" varStatus="status" var="courseClassView">
            <span class="njBanji"
                  value="${courseClassView.classSectionId}"> <span  value="${status.count+1}" class="tdForNj" style="display: none"></span></span>
                  ${courseClassView.classSection}${courseClassView.nj}年级:<span class="spanForEdit" >${courseClassView.courseHour}</span>
            <input type="hidden" value="${courseClassView.courseHour}" class="inputForEdit">

        </c:forEach>
    </div>
    <div>
        <c:if test="${inputLength=='>0'}">
            <input type="button" onclick="edit(this)" class="edit" value="编辑">
            <input type="hidden" onclick="save(this)" class="save" value="保存">
        </c:if>
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
    })

    $(".acourse").click(function () {
        $(this).addClass('active');
        $(this).removeClass("acourse");
        $(".acourse").removeClass("active");
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        var courseId = $(this).attr("va");
        window.location.href = "${ctx}/teach/task/course/hour?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&courseId=" + courseId;
    })

    function edit(thisInput) {
        $(".edit").hide();
        $(".save").attr("type", "hidden");
        $(".save").attr("type", "button");
//        $(".saveOrEdit").val("保存");
        $(".save").show();
        $(".spanForEdit").hide();
        $(".inputForEdit").attr("type", "text");
    }

    function save() {
        var courseId = $('#ullll').find('.active').attr('va');
        var sectionIdSpan = $('.njBanji');
        var sectionIdAndCourseHour = "";
        sectionIdSpan.each(function (i) {
            console.log($(this).children("span").attr("value"));
            sectionIdAndCourseHour += "," + $(this).attr("value")+":"+$(this).children("span").attr("value")+ ":" + $(this).siblings("input").val();
        })
        $.post("${ctx}/teach/task/course/hour/edit", {
            sectionIdAndCourseHour: sectionIdAndCourseHour,
            courseId:courseId
        }, function (data) {

        })
    }


</script>
</html>
