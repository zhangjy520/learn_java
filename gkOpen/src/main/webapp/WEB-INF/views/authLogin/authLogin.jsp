<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="login">
    <meta name="author" content="lexi">


    <!-- jQuery -->
    <script src="${ctx}/static/another-js/jquery-1.9.1.min.js"></script>

    <title>login</title>
</head>
<body>

<div class="loginForm">
    <form method="post" action="">

        <div class="" style="height: 80%;">
            <input class="form-control noAuto" placeholder="请输入帐号" id="username" name="username" type="text">
            <input class="form-control noAuto" placeholder="请输入密码" id="password" name="password" type="password"
                   value="">
<br>
            是否确定使用如上账号访问${client.name}？
            <br>
        <input type="submit" id="loginButton" value="授权并登录"/>
        </div>

    </form>

</div>
</body>
</html>