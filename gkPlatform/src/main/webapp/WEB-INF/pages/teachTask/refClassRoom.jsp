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
    #zh-manage .search-box{
        margin-top: 0 !important;
    }
    #zh-manage .search-box ul{
        border: none;
    }
    .stu-num-manage-menu{
        display: inline-block;
    }
    .stu-num-manage-menu ul{
        margin-bottom: 0 !important;
        height: auto !important;
    }
    .roll-operation{
        vertical-align: middle !important;
    }
</style>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="search-box">
        <div class="stu-num-manage-menu">
            <ul>
                学年:
                <select name="cycleYear">
                    <c:forEach items="${yearList}" var="teachCycle" varStatus="status">
                        <option
                                <c:if test="${cycleYear eq teachCycle}">selected</c:if>
                                value="${teachCycle}">${teachCycle}</option>
                    </c:forEach>
                </select>
                学期：
                学期：
                <select name="cycleSemester" class="cycleSemester">
                    <c:forEach items="${semesterList}" var="cycle">
                        <option name="cycleSemester" value="${cycle.cycleSemester}"
                                <c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if>
                                value="${cycle.cycleSemester}">${cycle.cycleSemester}
                        </option>
                    </c:forEach>
                </select>
            </ul>
        </div>
        <div class="roll-operation">
            <button onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/teach/task/ref/class/room/import','500px','350px')" style="float: left">
                导入
            </button>
            <button onclick="window.location.href='${ctx}/teach/task/ref/class/room/download'" style="float: left">下载模板</button>
        </div>
    </div>

    <section id="generated" class="row">

        <div class="row">
            <table class="normal">
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>学段</th>
                    <th>年级</th>
                    <th>班级</th>
                    <th>校区</th>
                    <th>教学楼</th>
                    <th>教室号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="refClassRoom" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${refClassRoom.sectionName}</td>
                        <td>${refClassRoom.nj}</td>
                        <td>${refClassRoom.banji}</td>
                        <td>${refClassRoom.schoolTypeName}</td>
                        <td>${refClassRoom.teachBuildingName}</td>
                        <td>${refClassRoom.roomNum}</td>
                        <td><span class="edit-td"
                                onclick="window.location.href='${ctx}/teach/task/ref/class/room/edit/pop?refId=${refClassRoom.refId}'"
                                <%--onclick="openDialog('编辑课程',--%>
                                        <%--'${ctx}/teach/task/ref/class/room/edit/pop?id=${refClassRoom.refId}&&type=edit','500px','360px');"--%>
                        >编辑</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:98.5%;padding-left:15px;">
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
        if ('${cycleSemester}' == "1") {
            $(".cycleSemester1").attr("selected", "selected");
        }
        if ('${cycleSemester}' == "2") {
            $(".cycleSemester2").attr("selected", "selected");
        }
    })
    $(function () {

        <c:if test="${pageInfo!=null&&pageInfo.pages != 0}">
        $(".fenY2").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/ref/class/room/index?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
            }
        });
        </c:if>

        $("select").change(function () {
            var cycleSemester = $("select[name='cycleSemester']").val();
            var cycleYear = $("select[name='cycleYear']").val();
            window.location.href = "${ctx}/teach/task/ref/class/room/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });


        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });

    });

    $(".gotoPage").click(function () {
        var pageNum = $(".fenY2goto").val();
        if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
            layer.msg("请输入正确的页码")
        } else {
            var cycleSemester = $("select[name='cycleSemester']").val();
            var cycleYear = $("select[name='cycleYear']").val();
            window.location.href = "${ctx}/teach/task/ref/class/room/index?pageNum=" + $(".fenY2goto").val() + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        }
    });

    function importCallBack(res) {
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表', '关闭'] //按钮
        }, function () {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "${ctx}/teach/task/ref/class/room/error/export");
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