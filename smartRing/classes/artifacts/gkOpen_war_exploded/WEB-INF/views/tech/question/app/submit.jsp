<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/header.jsp"%>
<%@ include file="../../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="${ctx}/static/css/tech-support.css">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <!--[if lt IE 9]>

    <![endif]-->
</head>
<style>
    .tech-detail-article{

    }
    .tech-detail-article img{
        margin: 0 0 30px 70px;
        width: 90%;
    }
    p{
        text-indent: 0 !important;
    }
</style>
<body>
<!--导航栏-->
<main class="container">
    <section id="tech-detail">
        <div class="tech-detail-back">
            <a onclick="window.history.go(-1)" onmouseover="this.style.cursor='hand'"
               onmouseout="this.style.cursor='normal'">返回</a>
        </div>
        <div class="tech-detail-article">
            <h3>开发者如何提交应用</h3>
            <p>1.开发者在教育云开放平台登录后，点击创建应用，如下图所示：</p>
            <img src="${ctx}/static/images/app1.png">
            <p>2.按要求将信息填写完整，点击提交即可</p>
        </div>
    </section>
</main>
<!--网页信息-->
<!--<footer id="footer">-->
<!--<div class="footer-box">-->
<!--<aside>-->
<!--<p>Copyright© 2016 gukeer.com. All rights reserved 京ICP备13001992号</p>-->
<!--<p>技术运营支持：乐希软件科技有限公司</p>-->
<!--<div>-->
<!--<span>客户服务热线：4001801818</span>-->
<!--<span>-->
<!--<a href="#" class="icon1"></a>-->
<!--<a href="#" class="icon2"></a>-->
<!--<a href="#" class="icon3"></a>-->
<!--</span>-->

<!--</div>-->
<!--</aside>-->
<!--<section>-->
<!--<ul>-->
<!--<li>-->
<!--<ul>-->
<!--<li>关于乐希</li>-->
<!--<li><a href="#">公司简介</a></li>-->
<!--<li><a href="#">业务定位</a></li>-->
<!--<li><a href="#">公司简介</a></li>-->
<!--</ul>-->
<!--</li>-->
<!--<li>-->
<!--<ul>-->
<!--<li>开放平台</li>-->
<!--<li><a href="#">开发者首页</a></li>-->
<!--<li><a href="#">开发者注册</a></li>-->
<!--<li><a href="#">开发者文档</a></li>-->
<!--<li><a href="#">开发者协议</a></li>-->
<!--</ul>-->
<!--</li>-->
<!--<li>-->
<!--<ul>-->
<!--<li>帮助反馈</li>-->
<!--<li><a href="#">在线客服</a></li>-->
<!--<li><a href="#">问题反馈</a></li>-->
<!--<li><a href="#">帮助中心</a></li>-->
<!--</ul>-->
<!--</li>-->
<!--<li>-->
<!--<ul>-->
<!--<li>服务支持</li>-->
<!--<li><a href="#">服务条款</a></li>-->
<!--<li><a href="#">运营支持</a></li>-->
<!--<li><a href="#">应用监控</a></li>-->
<!--<li><a href="#">后台管理</a></li>-->
<!--</ul>-->
<!--</li>-->
<!--<li>-->
<!--<ul>-->
<!--<li>联系我们</li>-->
<!--<li><a href="#">服务网点</a></li>-->
<!--<li><a href="#">商务合作</a></li>-->
<!--</ul>-->
<!--</li>-->
<!--</ul>-->
<!--</section>-->
<!--</div>-->
<!--</footer>-->

<!--登录界面-->
</body>
</html>