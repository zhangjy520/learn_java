<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>成绩管理</title>
    <link rel="stylesheet" href="${ctxStatic}/css/physicalHealth.css"/>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
            <span class="lf current-position">
                当前位置：
            </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">体质健康</a></li>
        <li class="child-nav  active"><a href="#">成绩管理</a></li>
    </ul>
</header>
<main class="physicalHealth2 fix-width module-bg">
    <header class="clear">
        <aside class="lf">
            <span>项目</span>
            <select id="itemName">
                <c:forEach items="${itemList}" var="sportItem">
                    <option <c:if test="${chooseItem==sportItem.itemId}"> selected</c:if>
                            value="${sportItem.itemId}">${sportItem.itemName}</option>
                </c:forEach>
            </select>
            <span>时间</span>
            <select id="time">
                <c:forEach items="${timeList}" var="time">
                    <option <c:if test="${chooseTime==time}"> selected</c:if>>${time}</option>
                </c:forEach>
            </select>

            <span>年级</span>
            <select id="nj">
                <option value="">全部</option>
                <c:forEach items="${njList}" var="nj">
                    <option <c:if test="${nj eq chooseNj}">selected</c:if>>${nj}</option>
                </c:forEach>
            </select>

            <c:if test="${gukeer:notEmptyString(chooseNj)}">
                <span>班级</span>
                <select id="bj">
                    <option value="">全部</option>
                    <c:forEach items="${bjList}" var="bj">
                        <option
                                <c:if test="${bj.classId eq chooseBj}">selected</c:if>
                                value="${bj.classId}">${bj.className}</option>
                    </c:forEach>
                </select>
            </c:if>

            <span>性别</span>
            <select id="gender">
                <option <c:if test="${chooseGender ==0 }"> selected</c:if> value="0">全部</option>
                <option <c:if test="${chooseGender ==1 }"> selected</c:if> value="1">男</option>
                <option <c:if test="${chooseGender ==2 }"> selected</c:if> value="2">女</option>
            </select>
        </aside>
        <section class="rl clear">
            <button style="margin-left:0;border-radius:0 3px 3px 0;" class="search" onclick="pageReload(1)"></button>
            <input style="border-right:none;margin-top:0;" type="text" name="stuNameOrNum" class="search" placeholder="学生姓名/学号"
                   value="${chooseName}"/>
            <%--<i class="search-item" style="right:7px ;top:7px;"></i>--%>
        </section>
    </header>
    <header class="clear" style="margin-bottom:0;padding-bottom:12px;border-bottom:1px solid #ddd;">
        <aside class="lf">
            <p>体育健康成绩</p>
        </aside>
        <section class="rl">
            <button onclick="window.location.href='${ctx}/physical/scoreMb/download'">下载模板</button>
            <button onclick="openDialog('导入体育测试成绩','${ctx}/physical/score/import/index','380px','240px');">导入</button>
            <button onclick="exportScore()">导出</button>
            <button onclick="openDialog('添加体质健康成绩','${ctx}/physical/score/add/index','430px','420px');">新增</button>
        </section>
    </header>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th width="8%">姓名</th>
                <th width="12%">学号</th>
                <th width="12%">项目</th>
                <th width="10%">时间</th>
                <th width="8%">测试成绩</th>
                <th width="8%">成绩单位</th>
                <th width="9%">分数</th>
                <th width="9%">等级</th>
                <th width="13%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${scoreList}" var="score">
                <tr>
                    <td>${score.xsxm}</td>
                    <td>${score.stuNum}</td>
                    <td>${score.itemName}</td>
                    <td>${score.testTime}</td>
                    <td>
                        <c:choose>
                            <c:when test="${score.itemUnit.indexOf('分')>=0}">
                                ${gukeer:unitTranslate(score.mark)}
                            </c:when>
                            <c:otherwise>
                                ${score.mark}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${score.itemUnit}</td>
                    <td>${score.stuScore}</td>
                    <td>${score.stuLevel}</td>
                    <td>
                        <span class="change"
                              onclick="openDialog('修改体质健康测试成绩','${ctx}/physical/score/edit/index?testSeq=${score.prim}','430px','420px');">修改</span>
                        <span class="delete" onclick="deleteScore('${score.prim}')">删除</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <footer class="pagination clear">
        <div class="lf">
            共${pageInfo.total}条记录，每页显示
            <select id="pageSize">
                <option <c:if test="${choosePageSize ==10 }"> selected</c:if> value="">10</option>
                <option <c:if test="${choosePageSize ==20 }"> selected</c:if> value="">20</option>
                <option <c:if test="${choosePageSize ==50 }"> selected</c:if> value="">50</option>
            </select>
            条记录
        </div>
        <div class="rl fenY">
            <script>
                $("#physicalHealth").addClass("active");
                $("#physicalHealth ul li:nth-child(2)>a").addClass("active");

                $(".fenY").createPage({
                    pageCount:${pageInfo.pages},//总页数
                    current:${pageInfo.pageNum},//当前页面
                    backFn: function (p) {
                        pageReload(p);
                    }
                });

                $("select").change(function () {
                    var p = $(".current").html();
                    if (typeof (p) == "undefined") {
                        p = 1;
                    }
                    if ($(this).attr("id") != "pageSize") {
                        $("input[name='stuNameOrNum']").val("");
                    }
                    pageReload(p);
                });

                function pageReload(p) {
                    var itemId = $("#itemName option:selected").val();
                    var time = $("#time option:selected").val();
                    var gender = $("#gender option:selected").val();
                    var stuNameOrNum = $("input[name='stuNameOrNum']").val();
                    var pageSize = $("#pageSize option:selected").text();
                    var pageNum = p;
                    var nj = $("#nj option:selected").text();
                    var bj = $("#bj option:selected").val();

                    window.location.href = "${ctx}/physical/score/index?itemId=" + itemId +
                            "&time=" + encodeURI(encodeURI(time)) +
                            "&gender=" + gender +
                            "&nj=" + encodeURI(encodeURI(nj)) +
                            "&bj=" + bj +
                            "&stuNameOrNum=" + encodeURI(encodeURI(stuNameOrNum)) +
                            "&pageSize=" + pageSize +
                            "&pageNum=" + p;
                }


                function exportScore() {
                    var itemId = $("#itemName option:selected").val();
                    var time = $("#time option:selected").val();
                    var gender = $("#gender option:selected").val();
                    var stuNameOrNum = $("input[name='stuNameOrNum']").val();
                    var nj = $("#nj option:selected").text();
                    var bj = $("#bj option:selected").val();

                    window.location.href = "${ctx}/physical/stuScore/export?itemId=" + itemId +
                            "&time=" + encodeURI(encodeURI(time)) +
                            "&nj=" + encodeURI(encodeURI(nj)) +
                            "&bj=" + bj +
                            "&gender=" + gender +
                            "&stuNameOrNum=" + encodeURI(encodeURI(stuNameOrNum));
                }

                function deleteScore(str) {
                    $.post("${ctx}/physical/score/delete", {
                        testSeq: str
                    }, function (retJson) {
                        layer.msg("删除成功", {
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            window.location.reload(true);
                        });
                    });
                }

            </script>
        </div>
    </footer>
</main>
</body>
</html>