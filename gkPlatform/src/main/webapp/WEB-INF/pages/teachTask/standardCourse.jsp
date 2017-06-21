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
    table .selectCourseClass  {
        color: #54ab37;
        background: url(${ctxStaticNew}/images/modify.png) no-repeat left center;
    }
</style>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>

<main class="container" id="zh-manage">
    <div class="search-box">

        <div class="roll-operation">
            <%--<button class="roll-add"--%>
                    <%--onclick="openDialog('新增','${ctx}/teach/task/course/pop?type=add','500px','400px');">课程新增--%>
            <%--</button>--%>
            <button class="roll-add"
                    onclick="openDialog('标准课程新增','${ctx}/teach/task/course/standard/pop','500px','400px');">标准课程新增
            </button>
        </div>
        <%--<div class="roll-teatypemanage">--%>
            <%--<button onclick="openCourseType('课程类型管理','${ctx}/teach/task/course/type/pop','500px','352px');">课程类型管理--%>
            <%--</button>--%>
        <%--</div>--%>
    </div>
    <section id="generated" class="row">
        <div class="row">
            <table class="normal check">
                <style>
                    table th:first-child, table td:first-child {
                        text-align: center;
                    }
                </style>
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>课程名称</th>
                    <th>课程类型</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="standCourse" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${standCourse.name}</td>
                        <td>${standCourse.courseTypeName}</td>
                        <td><span onclick="openDialog('编辑课程',
                                '${ctx}/teach/task/course/standard/pop?id=${standCourse.id}&&type=edit','500px','360px');">编辑</span>
                            <%--<span onclick="openDialog('授课班级',--%>
                                    <%--'${ctx}/teach/task/course/class/pop?id=${course.id}','500px','352px');" class="selectCourseClass">授课班级</span>--%>

                            <span value="${course.id}"
                                  onclick="alertTips('400px','200px','删除课程','确定要删除${course.name}课程吗？','deleteSure(\'${standCourse.id}\')')"> 删除</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:98.5%;padding-left:15px;">
            <div class="fenYDetail">共${coursePageInfo.total}条记录，本页${coursePageInfo.size}条</div>
            <div class="fenY2" id="fenY2"></div>
        </div>
    </section>
</main>
<script>

    $(function () {
        <%--$("select").change(function () {--%>
            <%--var cycleSemester = $("select[name='cycleSemester']").val();--%>
            <%--var cycleYear = $("select[name='cycleYear']").val();--%>
            <%--window.location.href = "${ctx}/teach/task/course/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;--%>
        <%--});--%>

        $(".fenY2").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
//                var semester = $("select[name='semester']").val();
//                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/course/index?pageNum=" + p + "&year=" + cycleYear + "&semester=" + semester;
            }
        });


        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });

        $(".gotoPage").click(function () {
            var pageNum = $(".fenY2go").val();
            if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                var semester = $("select[name='semester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/course/index?pageNum=" + $(".fenY2go").val() + "&year=" + cycleYear + "&semester=" + semester;
            }
        });

    });


    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    //删除用户
    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/teach/task/course/standard/del", {
            id: id,
            type: "delete"
        }, function (retJson) {
        }, "json");
        setTimeout(function () {
            layer.msg('删除成功', {
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                window.location.reload();
            });
        }, 300)
    }

</script>
</body>
</html>