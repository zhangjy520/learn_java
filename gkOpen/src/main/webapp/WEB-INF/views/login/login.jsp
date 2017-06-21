<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/document.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/login.css"/>
    <link rel="icon" href="${ctx}/images/logo-icon.png"/>
    <link rel="stylesheet" href="${ctx}/static/css/alert.css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/open/register.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
</head>
<body style="margin:0;">
<div class="mask-bg">
    <div class="mask-dialog">
        <div class="mask-content">
            <h3>
                登录
                <i class="rl closeWindow"></i>
            </h3>
            <section>
                <div>
                <p id="alert" style="color: red;position:absolute"></p>
                </div>
                <div class="login-input">
                    <span><img src="${ctx}/static/images/logo_ico1.png" alt=""></span>
                    <input type="text" placeholder="用户名" name="username" id="username"  class="denglu" autocomplete="off"/>
                </div>
                <div class="login-input">
                    <span><img src="${ctx}/static/images/logo_ico2.png" alt=""></span>
                    <input type="password" placeholder="密码" name="password" id="password" class="denglupwd" autocomplete="off"/>
                </div>
                <div class="clearfix">
                    <label for="rememberme" class="checkbox lf">
                        <input type="checkbox" id="rememberme" hidden="hidden"/>
                        记住我
                    </label>
                    <a href="${ctx}/pwd/index" class="rl">忘记密码？</a>
                </div>

                <div class="clearfix">
                    <button class="lf" onclick="login()">登录</button>
                    <button class="rl" onclick="window.location.replace('${ctx}/register/index')" type="submit" >注册</button>
                </div>
            </section>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function(){
        document.onkeydown = function (event){
            if (event.keyCode==13) //回车键的键值为13
                login();
        };
    });

    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

    function login() {
        var remember  = 0;
            if (document.getElementById("rememberme").checked) {
                remember = 1;
            } else {
                remember = 0;
            }

        var username = $("#username").val();
        var password = $("#password").val();
        $.get(postPath + "/doLogin", {
            username: username,
            password: password,
            remember:remember
        }, function (data) {
            $("#loginButton").attr("disabled", false);
            if (data.code == 0) {
                window.location.replace(data.data);
            } else {
                $("#alert").text(data.msg);
            }
        });
    }
</script>
</html>