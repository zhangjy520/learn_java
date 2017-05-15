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

<main class="container" id="ry-manage">
    <div class="search-box">
        <div class="roll-research">
            学年：
            <select name="cycle_year">
                <c:forEach var="year" items="${yearList}">
                    <option value="${year}" <c:if test="${year eq chooseYear}">selected</c:if>>${year}</option>
                </c:forEach>
            </select>

            学期：
            <select name="cycle_semester">
                <c:forEach var="semester" items="${semesterList}">
                    <option value="${semester.cycleSemester}" <c:if test="${semester.cycleSemester eq chooseSemester}">selected</c:if>>${semester.cycleSemester}</option>
                </c:forEach>
            </select>
        </div>
        <div class="roll-operation">
            <button class="roll-add" onclick="openDialog('新增教室','${ctx}/teach/task/room/update/index','700px','550px')">
                新增
            </button>

            <button class="roll-delete" <%--onclick="alertTips(400,200,'删除','确定要删除选中项吗？','sure()')"--%>>删除</button>

            <button class="roll-import"
                    onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/teach/task/room/import','500px','350px')">
                导入
            </button>

            <button class="roll-import" onclick="window.location.href='${ctx}/teach/task/room/download'">下载模板</button>
        </div>
        <div class="roll-teatypemanage">
            <button>教师类型管理</button>
        </div>
    </div>
    <div>
        <table class="check">
            <style>
                table th:first-child, table td:first-child {
                    text-align: center;
                }
            </style>
            <thead>
            <tr>
                <th width="4%"><input class="rsCheck headerCheck" type="checkbox"/></th>
                <th width="4%" style="text-align: center">序号</th>
                <th width="6%">所在校区</th>
                <%--     <th width="6%">所在楼</th>--%>
                <th width="6%">楼层</th>
                <th width="6%">房间号</th>
                <th width="6%">教室类型</th>
                <th width="6%">容纳人数</th>
                <th width="6%">有效座位数</th>
                <th width="6%">考试座位数</th>
                <th width="6%">是否用于选课</th>
                <th width="6%">备注</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="room" varStatus="status">
                <tr>
                    <td><input class="rsCheck" name="lanmuCheck" id="${room.roomId}" type="checkbox"/></td>
                    <td style="text-align: center">${status.index+1+(pageInfo.pageNum-1)*10}</td>
                    <td>${room.schoolTypeName}</td>
                    <td>${room.floor}</td>
                    <td>${room.roomNum}</td>
                    <td>${room.roomTypeName}</td>
                    <td>${room.count}</td>
                    <td>${room.availableSeat}</td>
                    <td>${room.examSeat}</td>
                    <td>
                        <c:choose>
                            <c:when test="${room.courseSelect==1}">
                                是
                            </c:when>
                            <c:when test="${room.courseSelect==2}">
                                否
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${room.remarks}</td>
                    <td>
                        <span onclick="openDialogView('修改信息','${ctx}/teach/task/room/update/index?roomId=${room.roomId}','700px','600px')">详情</span>
                        <span <%--onclick="alertTips(400,202,'删除人员','确定要删除${teacher.name}吗?','deleteSure(\'${teacher.id}\')')"--%>>删除</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="fenye">
        <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
            <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
        </c:if>
        <div class="fenY" id="fenY">
        </div>
    </div>
</main>
<script>
    //activeMenu("quji",1);
    /* 初始化分页 */
    $(function () {

        $("select").change(function () {
            var cycle_semester = $("select[name='cycle_semester']").val();
            var cycle_year = $("select[name='cycle_year']").val();

            if ($(this).attr("name") == "cycle_year") {
                cycle_semester = "";
            }

            window.location.href = "${ctx}/teach/task/room/index?year=" + cycle_year + "&semester=" + cycle_semester;
        });


        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                var cycle_semester = $("select[name='cycle_semester']").val();
                var cycle_year = $("select[name='cycle_year']").val();
                window.location.href = "${ctx}/teach/task/room/index?pageNum=" + p + "&year=" + cycle_year + "&semester=" + cycle_semester;
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
            name = $("input[name='zhiGong']").val();
            var pageNum = $(".go").val();
            if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                var cycle_semester = $("select[name='cycle_semester']").val();
                var cycle_year = $("select[name='cycle_year']").val();
                window.location.href = "${ctx}/teach/task/room/index?pageNum=" + $(".go").val() + "&year=" + cycle_year + "&semester=" + cycle_semester;
            }
        });

    });

    //删除用户
    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/renshi/renyuan/delete", {
            id: id,
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

    function sure() {
        //closeAlertDiv();
        var howManyDelay = 0;
        var spCodesTemp = "";
        $("input[name='lanmuCheck']:checked").each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).attr("id");
            } else {
                spCodesTemp += ("," + $(this).attr("id"));
            }
            howManyDelay++;
        });
        deleteSure(spCodesTemp);
    }

    function importCallBack(res) {
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表', '关闭'] //按钮
        }, function () {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "${ctx}/teach/task/room/error/export");
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
