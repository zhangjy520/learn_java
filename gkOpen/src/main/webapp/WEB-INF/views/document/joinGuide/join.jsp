<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>开发者注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/document.css"/>
    <link rel="icon" href="${pageContext.request.contextPath}/static/images/logo-icon.png"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/static/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/respond.min.js"></script>
    <![endif]-->
</head>
<style>
    h4{
        font-weight: bold !important;
    }
</style>

<body>
<!--导航栏-->
<%@ include file="../../common/header.jsp"%>

<!--开放文档-->
<style>
    #document-content section.col-xs-9>div>p{text-indent:0;}
</style>
<main class="container">
    <div id="document-content">
        <%@include file="../../common/docLeftMenu.jsp"%>
        <section class="col-xs-9">
            <h1>应用接入规范</h1>
            <div>
                <h4>一、应用接入流程</h4>
                <div>
                    <img src="${ctx}/static/images/join1.png" style="display: block;">
                </div>
                <h4>二、应用接入规范</h4>
                <div>
                    <table>
                        <tr style="background: #fff;">
                            <td style="width:12%;">应用名称</td>
                            <td>应用名称，将显示在商店和用户桌面图标下</td>
                        </tr>
                        <tr>
                            <td>应用简介</td>
                            <td>应用的描述、介绍，显示在应用详情页面。</td>
                        </tr>
                        <tr>
                            <td>应用图标</td>
                            <td>图标，显示在商店和用户桌面。图标标准规范：<br>1、图标背景色为单色系，8px圆角;<br>2、图标主体图形为纯色图形，40px*40px，#FFFFFF;<br>3、24位色透明底，68px*68px;<br>4、图标图形设计含义必须符合应用名称。</td>
                        </tr>
                        <tr><td>目标用户</td><td>应用针对的使用人群，范围教师、学生、家长，不定项选择 </td></tr>
                        <tr><td>版本号</td><td>应用版本号</td></tr>
                        <tr><td>应用分类</td><td>根据应用内容性质分类</td></tr>
                        <tr><td>演示url</td><td>应用demo，管理员在审核应用时会访问该地址以确认应用是否合格</td></tr>
                        <tr><td>演示账号</td><td>演示账号，管理员审核应用时应该可以通过该账号登陆演示教育云平台，评测应用。</td></tr>
                        <tr><td>是否免费</td><td>付费方式，免费或者付费</td></tr>
                        <tr><td>应用截图</td><td>应用新建后可以添加应用截图，截图会显示在应用商店，图片标准规范：支持jgp、jpeg、png格式的图片，宽100px*高100px，大小不超过500k</td></tr>
                    </table>
                </div>
            </div>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>
<script src="${pageContext.request.contextPath}/static/js/less.js"></script>
<script src="${pageContext.request.contextPath}/static/js/action.js"></script>
</body>
</html>