<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教务管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    .row {
        margin: auto;
    }
    #generated{
        padding-top: 0 !important;
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
    #zh-manage #generated > div:first-child{
        overflow: inherit;
    }
    .but {
        padding-left: 15px;
    }


    .active {
        color: #54AB37 !important;
        border: 1px solid #ddd !important;
        border-bottom: 0 !important;
    }
    .class-containt span{
        float: left;
        padding: 10px 15px;
        cursor: pointer;
        color: #525252;
        border-bottom: 1px solid #ddd;
    }
    .class-containt span:hover{
        color: #54AB37;
    }
</style>

<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="search-box">

            学年：
            <select name="cycleYear" class="cycleYear">
                <c:forEach items="${yearList}" var="year">
                    <option name="cycleYear"
                            <c:if test="${cycleYear ==year}">selected</c:if> value="${year}" >${year}</option>
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

        <div class="roll-operation" style="display: inline-block;">
            <button onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/teach/task/course/teacher/import','500px','350px')">
                导入
            </button>
            <button onclick="window.location.href='${ctx}/teach/task/course/teacher/moban/download'">下载模板</button>
            <button onclick="openDialog('查看所有任课教师安排','${ctx}/teach/task/course/teacher/all','800px','350px')">查看任课教师安排
            </button>
        </div>
        <div class="row searchh" style="display: inline-block;float: right;">
            <input type="hidden" id="searchHidden" value="${teacherName}"/>
            <button class="summitButton" onclick="searchTeacher()"></button>
            <input class="searchInput" type="text" name="zhiGong" placeholder="请输入老师姓名"/>
        </div>
    </div>
    </div>
    <section id="generated" class="row">
        <div style="padding:0;padding-top: 20px; overflow: hidden;" class="class-containt">
            <c:forEach items="${courseList}" var="course">
                        <span onclick="courseChange('${course.id}')" valCourseId="${course.id}" <c:if test="${course.id==courseId}" >class="active"</c:if>>
                            ${course.name}
                        </span>
            </c:forEach>
        </div>
        <div class="row" style="padding: 0;">
            <table class="normal">
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>年级</th>
                    <th>班级</th>
                    <th>任课教师</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="BZR" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${BZR.xdName}${BZR.nj}年级</td>
                        <td>${BZR.className}</td>
                        <td>${BZR.courseTeacher}</td>
                        <td><span
                                onclick="openDialog('编辑','${ctx}/teach/task/course/teacher/edit/pop?xd=${BZR.xdName}&&classId=${BZR.classId}&&teacherId=\
                                    ${BZR.teacherId}&&courseId=${BZR.courseId}&&nj=${BZR.nj}&&bj=${BZR.courseTeacher}&&courseName=${BZR.courseName}&&teacherName=${BZR.teacherName}&&className=${BZR.className}&&refId=${BZR.courseClassId}','500px','352px');"
                                style="background-color: #6b828e;float: left;margin-top: 10px;">编辑</span></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye">
            <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
            </c:if>
            <div class="fenY2" id="fenY2">
            </div>
        </div>
    </section>
</main>
<script>
    $(function () {
        $("select").change(function () {
            var cycleSemester = $("select[name='cycleSemester']").val();
            var cycleYear = $("select[name='cycleYear']").val();
            var valCourseId = $('.class-containt   .active').attr("valCourseId");
            window.location.href = "${ctx}/teach/task/course/teacher/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester+"&courseId="+valCourseId;
        });

        <c:if test="${pageInfo!=null&&pageInfo.pages != 0}">
        $(".fenY2").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                var valCourseId = $('.class-containt   .active').attr("valCourseId");
                window.location.href = "${ctx}/teach/task/course/teacher/index?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester+"&courseId="+valCourseId;
            }
        });
        </c:if>

        <c:if test="${pageInfo!=null&&pageInfo.pages != 0}">
        $(".gotoPage").click(function () {
            var pageNum = $("#fenY2go").val();
            if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                var valCourseId = $('.class-containt   .active').attr("valCourseId");
                window.location.href = "${ctx}/teach/task/course/teacher/index?pageNum=" + pageNum + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester+"&courseId="+valCourseId;
            }
        });
        </c:if>
    });

    function courseChange(id) {
        var cycleSemester = $("select[name='cycleSemester']").val();
        var cycleYear = $("select[name='cycleYear']").val();
        window.location.href = "${ctx}/teach/task/course/teacher/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&courseId=" + id;
    }
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
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        var name = $(".searchInput").val();
        window.location.href = "${ctx}/teach/task/course/teacher/search?name=" + encodeURI(encodeURI(name))+"&&cycleSemester="+cycleSemester+"&&cycleYear="+cycleYear;
    }

    function importCallBack(res) {
        debugger;
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表', '关闭'] //按钮
        }, function () {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "${ctx}/teach/task/course/teacher/error/export");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "msg");
            input1.attr("value", JSON.stringify(res.errorList));
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
            return false;
        }, function () {
            window.location.reload(true);
        });
    }
</script>
</body>
</html>