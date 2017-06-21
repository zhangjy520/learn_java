<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教务管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="stu-num-manage-menu">
        学年：
        <select name="cycleYear" class="cycleYear">
            <c:forEach items="${yearList}" var="year">
                <option name="cycleYear"
                        <c:if test="${cycleYear ==year}">selected</c:if>  >${year}</option>
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
    <section id="generated" class="row">
        <div class="row">
            <input type="hidden" id="searchHidden" value="${teacherName}"/>
            <button class="summitButton" onclick="searchTeacher()"></button>
            <input class="searchInput" type="text" name="zhiGong" placeholder="请输入老师姓名"/>
        </div>
        <div class="row">
            <table class="normal">
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>姓名</th>
                    <th>教职工编号</th>
                    <th>所属科目</th>
                    <th>所在学段</th>
                    <th>所在年级</th>
                    <th>所在班级</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${courseTeacherList}" var="BZR" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${BZR.teacherName}</td>
                        <td>${BZR.teacherNo}</td>

                        <td>${BZR.courseName}</td>
                        <td>${BZR.xdName}</td>
                        <td>${BZR.nj}</td>
                        <td>${BZR.className}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </section>
</main>
<script>
    $(function () {
        $("select").change(function () {
            var cycleYear = $(".cycleYear").find("option:selected").text();
            var cycleSemester = $(".cycleSemester").find("option:selected").val();
            window.location.href = "${ctx}/teach/task/course/teacher/search?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });

        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });

    });
    function reSetPass(id) {
        $.post("${ctx}/renshi/account/password/update", {
            id: id,
            password: '${password}',
        }, function (retJson) {

        }, "json");
        closeAlertDiv();
        layer.msg('重置成功', {
            time: 2000 //2秒关闭（如果不配置，默认是3秒）
        }, function () {
            parent.location.reload();
        });
    }

    function createSure() {
        closeAlertDiv();
        layer.msg('正在生成，请稍侯', {icon: 16, shade: 0.5, time: 100000000});//当生成完成这个对话框才被关掉

        $.ajax({
            type: "post",
            url: "${ctx}/renshi/account/add",
            data: {
                nameRule: $("#nameRule").val(),
                passRule: $("#passRule").val(),
                password: '${password}',
            },
            dataType: "json",
            success: function (data) {
                //alert(data);
            },
            error: function (e) {
                layer.msg('生成完毕', {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function () {
                    parent.location.reload();
                });
            }
        });
    }

    function searchTeacher() {
        var cycleYear = $(".cycleYear").find("option:selected").text();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        var name = $(".searchInput").val();
        window.location.href = "${ctx}/teach/task/course/teacher/search?name=" + encodeURI(encodeURI(name)) + "&&cycleSemester=" + cycleSemester + "&&cycleYear=" + cycleYear;
    }
</script>
</body>
</html>