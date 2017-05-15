<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>人员管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="stu-num-manage-menu">
        <select  name="teachYear">
            <c:forEach items="${teachCyclePageInfo.list}" var="teachCycle" varStatus="status">
                <option value="普通课程" name="subjectType">${teachCycle.teach_year}</option>
            </c:forEach>
        </select>
        <select  name="semester">
            <c:forEach items="${teachCyclePageInfo.list}" var="teachCycle" varStatus="status">
                <option value="普通课程" name="subjectType">${teachCycle.semester}</option>
            </c:forEach>
        </select>
        <ul>
            <c:forEach items="${sectionList}" var="section">
                <c:if test="${section.name=='小学'}">
                    <li><a href="#" data="generated" class="active" >一年级</a></li>
                    <li><a href="#" data="generated" class="active" >二年级</a></li>
                    <%--<li><a href="#" data="generated" class="active" >三年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >四年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >五年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >六年级</a></li>--%>
                </c:if>
                <c:if test="${section.name=='初中'}">
                    <li><a href="#" data="generated" class="active" >初中一年级</a></li>
                    <li><a href="#" data="generated" class="active" >初中二年级</a></li>
                    <li><a href="#" data="generated" class="active" >初中三年级</a></li>
                    <%--<li><a href="#" data="generated" class="active" >四年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >五年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >六年级</a></li>--%>
                </c:if>
                <c:if test="${section.name=='高中'}">
                    <li><a href="#" data="generated" class="active" >高中一年级</a></li>
                    <li><a href="#" data="generated" class="active" >高中二年级</a></li>
                    <li><a href="#" data="generated" class="active" >高中三年级</a></li>
                    <%--<li><a href="#" data="generated" class="active" >四年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >五年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >六年级</a></li>--%>
                </c:if>
                <%--<c:if test="${section.name=='一贯制'}">--%>
                    <%--<li><a href="#" data="generated" class="active" >一年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >二年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >三年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >四年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >五年级</a></li>--%>
                    <%--<li><a href="#" data="generated" class="active" >六年级</a></li>--%>
                <%--</c:if>--%>
            </c:forEach>
        </ul>
    </div>
    <section id="generated" class="row">
        <%--<div class="row">--%>
        <%--<input type="hidden" id="searchHidden" value="${teacherName}"/>--%>
        <%--<button class="summitButton"></button>--%>
        <%--<input class="searchInput" type="text" name="zhiGong" placeholder="请输入职工姓名"/>--%>
        <%--</div>--%>
        <div class="row">
            <table class="normal">
                <tbody>

                <tr>
                    <td rowspan="2" style="background: #FBFBFB;">节次</td>
                    <td colspan="2" style="background: #FBFBFB;">周一</td>
                    <td colspan="2" style="background: #FBFBFB;">周二</td>
                    <td colspan="2" style="background: #FBFBFB;">周三</td>
                    <td colspan="2" style="background: #FBFBFB;">周四</td>
                    <td colspan="2" style="background: #FBFBFB;">周五</td>
                </tr>
                <tr>
                    <td width="10%">开始时间</td>
                    <td width="10%">结束时间</td>

                    <td width="10%">开始时间</td>
                    <td width="10%">结束时间</td>

                    <td width="10%">开始时间</td>
                    <td width="10%">结束时间</td>

                    <td width="10%">开始时间</td>
                    <td width="10%">结束时间</td>

                    <td width="10%">开始时间</td>
                    <td width="10%">结束时间</td>

                </tr>
                <tr>
                    <td>早自习</td>
                    <c:if test="">
                        <td>8：00</td>
                        <td>9：00</td>
                    </c:if>

                    <td>8：00</td>
                    <td>9：00</td>
                    <td>8：00</td>
                    <td>9：00</td>
                    <td>8：00</td>
                    <td>9：00</td>
                    <td>8：00</td>
                    <td>9：00</td>
                </tr>
                <tr>
                    <td>第一节</td>
                    <td>9：00</td>
                    <td>10：00</td>
                    <td>9：00</td>
                    <td>10：00</td>
                    <td>9：00</td>
                    <td>10：00</td>
                    <td>9：00</td>
                    <td>10：00</td>
                    <td>9：00</td>
                    <td>10：00</td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:98.5%;padding-left:15px;">
            <c:if test="${gukeer:notEmptyString(pageInfoHave.pages)}">
                <div class="fenYDetail">共${pageInfoHave.total}条记录，本页${pageInfoHave.size}条</div>
            </c:if>
            <div class="fenY2" id="fenY2">
            </div>
        </div>
    </section>
</main>
<script>
    var name = $("#searchHidden").val();
    <%--$(function () {--%>
    <%--<c:if test="${pageInfoHave.pages != 0}">--%>
    $(".fenY2").createPage({
        pageCount:${pageInfo.pages},//总页数
        current:${pageInfo.pageNum},//当前页面
        backFn: function (p) {
            window.location.href = "${ctx}/area/node/index?pageNumHave=" + p + "&pageSize=10";
        }
    });
    <%--</c:if>--%>
    <%--<c:if test="${pageInfoNo.pages != 0}">--%>
    <%--$(".fenY1").createPage({--%>
    <%--pageCount:${pageInfoNo.pages},//总页数--%>
    <%--current:${pageInfoNo.pageNum},//当前页面--%>
    <%--backFn: function (p) {--%>
    <%--window.location.href = "${ctx}/area/account/index?pageNumNo=" + p + "&pageSizeNo=10&whichPage=1";--%>
    <%--}--%>
    <%--});--%>
    <%--</c:if>--%>
    <%--<c:if test="${gukeer:notEmptyString(whichPage)}">--%>
    <%--$('.stu-num-manage-menu a').removeClass('active');--%>
    <%--$("#noGener").addClass('active');--%>
    <%--var data = $("#noGener").attr('data');--%>
    <%--var sections = $('#' + data);--%>
    <%--$('#' + sections[0].id).show();--%>
    <%--$('#' + sections[0].id).siblings('section').hide();--%>
    <%--</c:if>--%>


    <%--/*查询搜索*/--%>
    <%--$(".summitButton").click(function () {--%>
    <%--var name = $("input[name='zhiGong']").val();--%>
    <%--window.location.href = "${ctx}/area/account/index?teacherName=" + encodeURI(encodeURI(name));--%>
    <%--});--%>
    <%--//搜索框关键字回显--%>
    <%--$("input[name='zhiGong']").val($("#searchHidden").val());--%>

    $('#fenY2goto').click(function () {
        var p = $('#fenY2go').val();
        if (p <= 0 || p >${pageInfo.pages}) {
            layer.msg("请输入正确的页码")
        } else {
            window.location.href = "${ctx}/area/node/index?pageNumHave=" + p + "&pageSize=10";
        }
    })

    <%--$('#fenY1goto').click(function () {--%>
    <%--var p = $('#fenY1go').val();--%>
    <%--if (p <= 0 || p >${pageInfoNo.pages}) {--%>
    <%--layer.msg("请输入正确的页码")--%>
    <%--} else {--%>
    <%--window.location.href = "${ctx}/area/account/index?pageNumNo=" + p + "&pageSizeNo=10&whichPage=1";--%>
    <%--}--%>
    <%--})--%>

    <%--})--%>

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
</script>
</body>
</html>