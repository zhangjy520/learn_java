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
    <style>
        div {
            padding: 0 10px;
            margin-bottom: 27px;
        }

        div span {
            width: 55px;
            color: #999;
            margin-right: 28px;
        }

        div label {
            color: #333;
        }

        input {
            width: 150px;
            margin-right: 5px;
        }
    </style>
    <script src="${ctxStatic}/js/jquery.js"></script>
</head>
<body>
<form action="${ctx}/sport/rule/add" id="inputForm" method="post">
    <main class="container">
        <input type="hidden" name="ruleId" value="${mapRule.ruleId}">
        <div>
            <span>项目</span>
            <label>${mapRule.itemName}</label>
        </div>
        <div>
            <span>年级</span>
            <label>${mapRule.gradeName}</label>
        </div>
        <div>
            <span>性别</span>
            <label>
                <c:if test="${mapRule.gender ==1}">男</c:if>
                <c:if test="${mapRule.gender ==2}">女</c:if>
            </label>
        </div>
        <div>
            <span>成绩单位</span>
            <label>${itemUnit}</label>
        </div>
        <div>
            <span>分数</span>
            <label><input name="score" type="text" value="${mapRule.score}"></label>
        </div>
        <div>
            <span>等级</span>
            <label><input name="level" type="text" value="${mapRule.level}"></label>
        </div>
        <div>
            <span>测试成绩</span>
            <label><input name="mark" type="text" value="${mapRule.mark}"></label>
            <label>（成绩单位:${itemUnit}）</label>
        </div>
    </main>
</form>
<script>
    function doSubmit() {
        $("form").submit();
        return true;
    }
</script>
</body>
</html>