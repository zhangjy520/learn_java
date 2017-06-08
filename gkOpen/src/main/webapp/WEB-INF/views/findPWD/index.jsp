<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ include file="../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/another-js/Validform_v5.3_min.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
</head>
<body>
<style>
    body{background:#f6f6f6  !important;}
</style>
<!--导航栏-->

<!--注册流程-->
<div class="container find-pwd" id="find-pwd-1">
    <h3>忘记密码</h3>
    <div class="find-box">
        <h3>请输入您注册时填写的邮箱以找回密码</h3>
        <div style="margin-top:50px;" class="validform">
            <span>邮箱：</span>
            <input  type="text" id="email" datatype="e"  errormsg="邮箱格式错误">
        </div>
        <p class="validTip" style="left:356px;top:210px;"></p>
        <div class="find-box-btn">
            <button onclick="logEmail()">确&nbsp;定</button>
            <script>
                $('.validform').Validform({
                    tiptype:2
                });


                function logEmail() {
                    var email = $("#email").val();

                    if(email == '') return;

                    $.post( "${ctx}/pwd/check/username",{
                        email:email
                    },function (data) {
                        if (data.code == 0) {
                            window.location.replace("${ctx}/pwd/mail/send?username="+email);
                        } else {
                            $("#nextStop").attr("disabled", false);
                            webToast(data.msg,"top",2300);
                        }
                    });
                }
            </script>
        </div>
    </div>
</div>
<!--网页信息-->
<%@ include file="../common/footer.jsp"%>
</body>
</html>