<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/js/action.js"></script>
</head>
<style>
    h4, h5{
        font-weight: bold;
    }
    p{
        margin:20px 0 !important;
    }
    p a{
        margin-bottom: -15px !important;
    }
    li{
        line-height: 35px !important;
    }
</style>
<body>
<main class="container">
    <div id="document-content">
        <%@include file="../../common/docLeftMenu.jsp" %>
        <section class="col-xs-9">
            <h1>API</h1>
            <div id="oauth">
                <h4>一、Oauth2.0授权登录接口</h4>
                <h5>1.功能说明:</h5>
                <p>OAuth（开放授权）是一个开放标准，允许用户授权在教育云平台注册的第三方应用，访问他们存储在另外的服务提供者上的信息，而不需要将用户名和密码提供给第三方应用或分享他们数据的所有内容。</p>
                <h5>2.文档下载:</h5>
                <p><a href="${ctx}/manager/index" style="color: #54ab37">Oauth2.0授权登录说明文档下载需要登陆后,点击首页右上角的【管理中心】-->【文档下载中心】进行下载</a></p>
                <p>注:若没有可点击的链接，请耐心等待注册信息审核，审核通过后才可以进入下载也下载，是否通过审核的状态可以通过登陆后，进入管理中心的【基本资料】进行查看，审核状态会显示在基本资料的右上角</p>
                <h5>3.步骤说明:</h5>
                <ul>
                    <li>step1:获取授权码code</li>
                    <li>step2:通过code获取acess_token和refresh_token</li>
                    <li>step3:通过step2获得的access_token获取用户信息</li>
                    <li>step4:通过step2获取的access_token和refresh_token</li>
                    <li>step5:通过step2获取的access_token判断登录状态，判断token有效性,若有效授权登陆，无效则重新获取access_token</li>
                </ul>
            </div>
            <div id="dataSyncronism">
                <h4>二、dataSyncronism数据实时同步接口</h4>
                <h5>1.功能说明:</h5>
                <p>由于项目系统之间有些数据是共有的，例如人员、组织，在使用其它项目系统时，人员、组织也需要，这就需要将人员、组织的数据同步，人员、组织的数据项很多，而其它系统需要的很少，可能只需要人员和组织的名称及其标识列，并且数据量不大，不会一次性发送上百个人员或者组织的信息，基于这个考虑，通过将人员、组织信息的数据放在消息内放到消息中件上，各个系统通过订阅的方式获取消息中的数据。</p>
                <h5>2.文档下载:</h5>
                <p><a href="${ctx}/manager/index" style="color: #54ab37">数据实时同步说明文档下载需要登陆后,点击首页右上角的【管理中心】-->【文档下载中心】进行下载</a></p>
                <p>注:若没有可点击的链接，请耐心等待注册信息审核，审核通过后才可以进入下载也下载，是否通过审核的状态可以通过登陆后，进入管理中心的【基本资料】进行查看，审核状态会显示在基本资料的右上角</p>
                <h5>3.文档目录结构说明:</h5>
                <ul>
                    <li>基础数据标准:包括推送数据的字端、类型、作用</li>
                    <li>数据事件格式说明:推送来的数据的结构及类型</li>
                    <li>工具及事件数据类:工具类和事件类</li>
                    <li>秘钥认证说明:关于认证的说明</li>
                    <li>数据入库说明:关于入库的相关介绍</li>
                    <li>数据接收(实例):接收数据实例</li>
                </ul>
            </div>
        </section>
</main>
<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>

<!-- <script src="js/pageDevide.js"></script> -->
<script>
    //常见问题菜单切换
    $('.normal-question a').click(function () {
        $(this).addClass('active');
        $(this).parent().siblings().children().removeClass('active');
        var a = $(this).attr('data').substring(8, 9);
        localStorage.ff = a;
    });
</script>
</body>
</html>