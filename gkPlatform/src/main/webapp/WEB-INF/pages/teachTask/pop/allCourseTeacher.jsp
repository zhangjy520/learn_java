<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<body>
<button onclick="courseTeacherExport()">导出</button>
<div>
    <tbody>
    <c:forEach items="${coureClassViewList}" var="courseClassView" varStatus="status">

        <tr>
            <input type="hidden" value="${courseClassView.courseId}">
            <input type="hidden" value="${courseClassView.classId}">
            <input type="hidden" value="${courseClassView.teacherId}">

                <%--<input type="hidden" name="xdName" value="${courseClassView.classSection}">--%>
                <%--<input type="hidden" name="nj" value="${courseClassView.nj}">--%>
                <%--<input type="hidden" name="bj"  value="${courseClassView.className}">--%>
                <%--<input type="hidden" name="course" value="${courseClassView.courseName}">--%>
                <%--<input type="hidden" name="courseTeacher" value="${courseClassView.teacherName}">--%>


            <td>${status.count}</td>
            <td>${courseClassView.classSection}${courseClassView.nj}年级${courseClassView.className}</td>
            <td>${courseClassView.courseName}</td>
            <td>${courseClassView.teacherName}</td>
        </tr>


    </c:forEach>
    <form action="${ctx}/teach/task/course/teacher/export" method="post" id="export">
        <input type="hidden" name="courseClassViews" class="courseClassViews" value=${json}>
    </form>
    </tbody>
</div>
</body>
<script>
    function courseTeacherExport() {
        debugger;
        var json = $(".courseClassViews").val();
        $("#export").submit();
    }
</script>
</html>
