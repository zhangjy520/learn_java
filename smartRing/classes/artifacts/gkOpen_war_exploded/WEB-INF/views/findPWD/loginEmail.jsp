<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ include file="../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <link rel="stylesheet" href="${ctx}/static/css/easyui.css">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/another-js/jquery.easyui.min.js"></script>
</head>
<body>
<style>
    body{background:#f6f6f6  !important;}
</style>

<!--注册流程-->
<div class="container find-pwd" id="find-pwd-1">
    <h3>忘记密码</h3>
    <div class="find-box" id="f-email-check">
        <h3 style="padding-top:40px;">密码重置邮箱已发送至您的注册邮箱：</h3>
        <h4>${username}</h4>
        <p>请访问邮件中给出的网页链接，根据页面提示完成密码重置</p>
        <div class="find-box-btn"><button onclick="window.open('${url}')">登录邮箱</button></div>
        <footer>
            <p>没有收到邮件？</p>
            <p>1、请检查邮箱地址是否正确，您可以返回 <a href=${ctx}/pwd/index>重新填写</a></p>
            <p>2、检查您的邮件垃圾箱</p>
            <p>3、若仍未收到确认，请尝试<a href="#" onclick="resend()">重新发送</a></p>
        </footer>
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
            <div>
                <span>邮箱</span>:
                <span>Gukeerkeji@163.com</span>
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
            <div>
                <span>工作时间</span>:
                <span>周一至周五9:00-18:00</span>
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
    function resend() {
        $.post(postPath + "/pwd/mail/send",{
            username:'${username}'
        },function (data) {
            if(data != null){
                webToast("重新发送成功","top",2300);
            }
        });
    }
</script>
</body>
</html>