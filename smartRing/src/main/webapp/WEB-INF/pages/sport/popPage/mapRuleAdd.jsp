<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/26
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/css/pop.css">
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/global.js"></script>
    <style>
        .sh-type label {
            padding-left: 20px;
            cursor: pointer;
            position: relative;
            margin-right: 18px;
        }

        .sh-type label:before {
            content: '';
            position: absolute;
            width: 13px;
            height: 13px;
            left: 0;
            top: 2px;
            background: url(${ctxStatic}/images/icon.png) no-repeat -15px -34px;
        }

        .sh-type .checked:before {
            background-position: 0 -34px;
        }

        .container > p {
            margin-bottom: 27px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 20px;
        }

        .container > p span {
            width: 30%;
            text-align: center;
        }

        div {
            margin-bottom: 27px;
            padding: 0 10px;
        }

        div > span {
            color: #666;
            width: 55px;
            margin-right: 15px;
        }

        div > input[type=text] {
            width: 257px;
        }
    </style>
</head>
<body>
<form action="${ctx}/sport/rule/add" id="inputForm" method="post">
    <main class="container">
        <p>
            <span id="itemName">${itemName}</span>
            <span id="gradeName">${gradeName}</span>

            <c:if test="${gender == 1}">
                <span id="gender">男生</span>
            </c:if>
            <c:if test="${gender == 2}">
                <span id="gender">女生</span>
            </c:if>
        </p>
       <%-- <input type="hidden" name="xd" value="${xdAndNj.xd}">
        <input type="hidden" name="nj" value="${xdAndNj.nj}">--%>
        <div>
            <span>测试成绩</span>
            <input name="mark" type="text" placeholder="测试项目的成绩，不用写单位">
        </div>
        <div>
            <span>分数</span>
            <input type="text" name="score" placeholder="对应的分数，例如:90">
        </div>
        <div class="sh-type">
            <span>等级</span>
            <label for="sh-1" class="checked">
                <input type="radio" id="sh-1" hidden="hidden" checked/>
                <span>优秀</span>
            </label>
            <label for="sh-2">
                <input type="radio" id="sh-2" hidden="hidden"/>
                <span>良好</span>
            </label>
            <label for="sh-3">
                <input type="radio" id="sh-3" hidden="hidden"/>
                <span>及格</span>
            </label>
            <label for="sh-4">
                <input type="radio" id="sh-4" hidden="hidden"/>
                <span>不及格</span>
            </label>
        </div>
    </main>
</form>
<script>
    function doSubmit() {
        $.post($("form").attr('action'), {
            itemName: "${itemName}",
            grade: "${gradeName}",
            nj: "${xdAndNj.nj}",
            xd: "${xdAndNj.xd}",
            gender: "${gender}",
            mark: $("input[name='mark']").val(),
            score: $("input[name='score']").val(),
            level: $('.checked').children('span').html()
        }, function (retJson) {

        });
        return true;
    }
</script>
</body>
</html>