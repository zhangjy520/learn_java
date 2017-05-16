<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/css/pop.css">
    <style>
        /*.container{width:480px;}*/
        .container > p {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<main class="container">
    <p>
        成功<span class="green">121</span>条，失败<span class="red">${users.size}</span>条
    </p>
    <table class="table">
        <thead>
        <tr>
            <th width="16%">教工号</th>
            <th width="12%">角色</th>
            <th width="12%">学段</th>
            <th width="12%">年级</th>
            <th width="12%">班级</th>
            <th width="16%">失败原因</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.no}</td>
                <td>${user.roleName}</td>
                <td>${user.xd}</td>
                <td>${user.nj}</td>
                <td>${user.classId}</td>
                <td>${user.msg}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>