<%@ include file="../common/header.jsp"%>
<%@ include file="../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${ctx}/static/css/alert.css"/>
    <script src="${ctx}/static/js/action.js"></script>
    <script src="${ctx}/static/open/register.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
    <script src="${ctx}/static/another-js/Validform_v5.3_min.js"></script>
    <title>注册</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
</head>
<body>
<style>
    body{background:#fff  !important;}
</style>
<!--导航栏-->
<!--注册流程-->
<main class="container" id="register">
    <header id="register-process">
        <h3>开发者注册</h3>
        <div>
            <span class="col-xs-4">填写基本信息</span>
            <span class="col-xs-4">邮箱激活</span>
            <span class="col-xs-4">完善开发者资料</span>
        </div>
    </header>
    <section id="register-form" class="validform">
        <div class="register-type">
            <span>开发者类型：</span>
            <label for="company" class="gkradio radio-checked">
                <input hidden="hidden" type="radio" name="loginUser.userType" checked="checked" value="1" id="company"/>
                <span>企业</span>
            </label>
            <label for="personal"class="gkradio">
                <input hidden="hidden"  type="radio" name="loginUser.userType"   value="0" id="personal"/>
                <span>个人</span>
            </label>
        </div>
        <div class="register-email">
            <span>邮箱：</span>
            <input type="text" placeholder="请填写真实有效邮箱以便接收教育云平台下发消息" datatype="e" errormsg="邮箱格式错误"
                   name="username" id="regist_email" onload="javascript:document.username.reset()"/>
            <%--<input type="text" name="username" value="" placeholder="用户名" id="username"/>--%>
            <%--<i>请填写真实有效邮箱以便接收教育云平台下发消息</i>--%>
        </div>
        <p class="validTip" style="left:360px;top:182px;"></p>
        <div class="register-pwd">
            <span>密码：</span>
            <%--<input type="password" name="password" value="" placeholder="密码" id="password"/>--%>
            <input type="password" placeholder="请输入6-16位字符"  datatype="*6-16"
                   errormsg="请输入6-16位字符" name="password" value=""
                   id="regist_password" onload="javascript:document.password.reset()"/>
            <%--<i>字母和数字组成，最短6位，区分大小写</i>--%>
        </div>
        <p class='validTip' style="left:360px;top:297px;"></p>
        <div class="register-agreement">
            <input type="checkbox" id="agreement" checked/>
            <label for="agreement">
                我同意并遵守<a href="${ctx}/document/treaty">《教育云开放平台开发者协议》</a>
            </label>
        </div>
        <div class="register-next-step" >
            <button  class="next-step"  onclick="toEmailCheck()" id="nextStop">下一步</button>
        </div>
    </section>


</main>

<!--网页信息-->
<%@ include file="../common/footer.jsp"%>
<script charset="utf-8" src="${ctx}/static/js/action.js"></script>
<script>
    $('.gkradio').click(function(){
        $(this).siblings('label').removeClass('radio-checked')
        $(this).addClass('radio-checked')
    })
    $('.validform').Validform({
        tiptype:2
    });
</script>
</body>
</html>