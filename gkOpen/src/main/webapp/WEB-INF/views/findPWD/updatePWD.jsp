<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/easyui.css">
    <link rel="icon" href="${ctx}/static/images/logo-icon.png"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/another-js/Validform_v5.3_min.js"></script>
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/js/respond.min.js"></script>
    <![endif]-->
    <script src="${ctx}/static/another-js/jquery.easyui.min.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
</head>
<body>
<style>
    body {
        background: #f6f6f6 !important;
    }

    .Validform_checktip {
        width: auto !important;
        font-size: 12px !important;
        position: relative;
        left: 376px;
        top: 2px;
    }

    /*.validTip span{width:120px !important;}*/
</style>
<!--注册流程-->
<div class="container find-pwd validform" id="find-pwd-1">
    <h3>忘记密码</h3>
    <div class="find-box" id="f-email-check" style="position:relative;">
        <div>
            <span>输入密码：</span>
            <input type="password" placeholder="请输入6-16位字符"
                   datatype="*6-16"
                   errormsg="请输入6-16位字符" value=""
                   onload="javascript:document.password.reset()"/>
            <%--<input type="password" name="newPassword" datatype="*6-20" errormsg='请输入字母、数字或英文符号，最短6位'>--%>
        </div>
        <p class="validTip" style=""></p>
        <div style="position:relative;">
            <span>确认密码：</span>
            <input type="password" placeholder="请输入6-16位字符"
                   datatype="*6-16"
                   errormsg='两次密码输入必须一致' value=""
                   id="confirmNewPassword"
                   onload="javascript:document.password.reset()"
            />
        </div>
        <p class="validTip" style=""></p>

        <input type="hidden" id="userEmail" value="${username}">
        <div class="find-box-btn">
            <button id="submit-PWD">确&nbsp;定</button>
        </div>
    </div>
</div>

<!--网页信息-->
<footer id="footer">
    <div class="footer-box">
        <aside>
            <p>商务合作</p>
            <div>
                <span>联系人</span>:
                <span>张经理</span>
            </div>
            <div>
                <span>电话</span>:
                <span>15311427437</span>
            </div>
            <%--<div>--%>
            <%--<span>邮箱</span>:--%>
            <%--<span>Gukeerkeji@163.com</span>--%>
            <%--</div>--%>
            <div>
                <span>工作时间</span>:
                <span>周一至周五&nbsp;&nbsp;&nbsp;&nbsp;9:00-18:00</span>
            </div>
        </aside>
        <section>
            <p>技术支持</p>
            <div>
                <span>技术运营支持</span>:
                <span>北京科技有限公司</span>
            </div>
            <div>
                <span>客服热线</span>:
                <span>010-82824069</span>
            </div>
        </section>
    </div>
    <div class="footer-footer">
        <p>
            <span>教育云平台简介</span>|
            <span>服务条款</span>|
            <span>技术支持</span>
        </p>
        <div>©2014-2016Beijing Search Champion Technology Co.,Ltd. 北京科技有限公司</div>
        <div>京ICP备16000182-3号|京公安网备11010502027075</div>
    </div>
</footer>

<script>
    //表单验证
    $('.validform').Validform({
        tiptype: 2,
//        datatyp: {"zh6-20": /^[\u4E00-\u9FA5\uf900-\ufa2d]{6,20}$/}
    });

    $("#submit-PWD").click(function () {
        var userEmail = $("#userEmail").val();
        var password = $("#confirmNewPassword").val();
        if(userEmail == '' || password == '') return;
        if(userEmail == password ){
            $('#confirmNewPassword').attr('errormsg','信息通过验证');
        }

//        if (userEmail == '' || password == '') return;

        $.post("${ctx}/pwd/update", {
            password: password,
            userEmail: userEmail
        }, function (data) {
            if (data.code == 0) {
                webToast(data.msg, "top", 300);
                window.open(data.data);
            } else if (data.code == -1) {
                webToast(data.msg, "top", 300);
                window.location.reload();
            }
        });
    })
</script>


</body>
</html>