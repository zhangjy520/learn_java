<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="${ctxStatic}/jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jquery/jquery-migrate-1.1.1.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jquery-validation/1.14.0/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jquery-validation/1.14.0/localization/messages_zh.min.js" type="text/javascript"></script>
    <link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- jeeplus -->
    <link href="${ctxStatic}/common/jeeplus.css" type="text/css" rel="stylesheet"/>
    <script src="${ctxStatic}/common/jeeplus.js" type="text/javascript"></script>
    <link rel="shortcut icon" href="images/favicon.png" type="image/png">
    <!-- text fonts -->
    <link rel="stylesheet" href="${ctxStatic }/common/login/ace-fonts.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctxStatic }/common/login/ace.css"/>

    <!-- 引入layer插件 -->
    <script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
    <script src="${ctxStatic}/layer-v2.0/layer/laydate/laydate.js"></script>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../assets/css/ace-part2.css"/>
    <![endif]-->
    <link rel="stylesheet" href="${ctxStatic }/common/login/ace-rtl.css"/>
    <title>登录首页</title>
    <style type="text/css">
        input[type="checkbox"] {
            -webkit-appearance: none;
            background: #fff url('${ctxStatic }/common/login/images/checkBox.png');
            background-position: -96px 0 !important;
            height: 22px;
            vertical-align: middle;
            width: 22px;
        }

        input[type="checkbox"]:checked {
            background-position: -48px 0 !important;
        }

        input[type="checkbox"][disabled]:checked {
            background-position: -96px 0 !important;
        }

        body {
            width: 100%;
            height: 100%;
            background: url('${ctxStatic }/common/login/images/loginBack.png') 100% 0% / cover no-repeat;
            overflow-x: hidden;
        }

        .loginForm {
            height: 421px;
            width: 399px;
            margin: 0 auto;
            background-color: rgba(255, 255, 255, 0.5);
            margin-top: 200px;
        }

        .headInfo {
            position: absolute;
            height: 120px;
            width: 100%;
            font-size: 44px;
            color: #ffffff;
            left: 18%;
            top: 5%
        }

        #title-a {
            color: #ffffff !important;
        }

        .devideLine {
            position: absolute;
            display: inline-block;
            border-left: 2px #FFFFFF solid;
            height: 55px;
            font-size: 24px;
            color: #ffffff;
            line-height: 50px;
            padding-left: 25px;
            margin-left: 25px;
            margin-top: 5px;
        }

        .loginForm > form {
            width: 100%;
            height: 100%;
        }

        * {
            font-family: "微软雅黑";
        }

        input {
            outline: none;
            border: none;
        }

        #username {
            background: url('${ctxStatic }/common/login/images/usernameInput.png') 97% 10px no-repeat;
        }

        #password {
            background: url('${ctxStatic }/common/login/images/passwordInput.png') 97% 10px no-repeat;
        }

        #username, #password {
            padding-left: 10px;
            width: 83%;
            background-color: #FFFFFF;
            height: 45px;
            margin-left: 33px;
            margin-bottom: 25px;
            margin-top: 20px;
        }

        .checks {
            font-size: 12px;
            color: #666666;
            width: 83%;
            margin: 0 auto;
            line-height: 30px;
        }

        #loginButton {
            width: 83%;
            height: 45px;
            margin-left: 34px;
            margin-top: 20px;
            background: #1AB394;
            color: #fff;
            font-size: 18px;
        }
        .errorMsg{
            width:83%;
            height: 40px;
            margin: 0 auto;
        }
    </style>
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginForm").validate({
                rules: {
                    validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
                },
                messages: {
                    username: {required: "请填写用户名."}, password: {required: "请填写密码."},
                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
                },
                errorLabelContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        });
        // 如果在框架或在对话框中，则弹出提示并跳转到首页
        if (self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0) {
            alert('未登录或登录超时。请重新登录，谢谢！');
            top.location = "${ctx}";
        }
    </script>
</head>
<body>
<div class="headInfo">
    <img align="absmiddle" src="${ctxStatic }/common/login/images/yangguangFb.png"/>
    <a id="title-a" style="text-decoration:none;">分班系统</a>
    <div class="devideLine">
        均衡搭配 智能分班
    </div>
</div>
<div class="loginForm">
    <form action="${ctx}/login" id="loginForm" method="post">
        <div style="text-align: center;font-size: 20px;color: #1AB394;line-height: 66px;font-weight: bold;">
            用户登录
        </div>
        <fieldset>
            <div style="height: 80%;">
                <input type="text" id="username" name="username" value="${username}" placeholder="用户名"/>
                <input type="password" id="password" name="password" placeholder="密码"/>
                <div class="checks">
                    <input class="rsCheck" style="float:left;margin-right:5px;" type="checkbox" id="rememberMe"
                           name="rememberMe" ${rememberMe ? 'checked' : ''}/>
                    <label>&nbsp;记住我</label>
                    <sys:message content="${message}"/>
                </div>
                <div class="space"></div>
                <input type="submit" id="loginButton" value="登录"/>
            </div>
        </fieldset>
    </form>
</div>
<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='../assets/js/jquery.js'>" + "<" + "/script>");
</script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='../assets/js/jquery1x.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='../assets/js/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<style>
    /* Validation */
    label.error {
        color: #cc5965;
        display: inline-block;
        margin-left: 5px;
    }

    .form-control.error {
        border: 1px dotted #cc5965;
    }
</style>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    $(document).ready(function () {
        $(document).on('click', '.form-options a[data-target]', function (e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });
    });
</script>
</body>
</html>
