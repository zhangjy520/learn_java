<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../../common/header.jsp"%>
<%@ include file="../../../../login/login.jsp"%>
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
    .tech-detail-article img{
        margin: 0 0 30px 80px;
        width: 90%;
        border: 1px solid #ddd;
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
            <h3>企业怎么注册成为开发者</h3>
            <div>
                <p>1、登录网站，点击右上角的“开发者注册”按钮,如下图所示</p>
                <img src="${ctx}/static/images/personQUestion1.png">
            </div>
            <div>
                <p>2、用户注册时，在开发者类型中选择企业即可，按照系统提示的信息进行邮箱和密码的正确填写，确认无误后选择同意遵守教育云平台协议，点击下一步。</p>
                <img src="${ctx}/static/images/personalImg2.png">
            </div>
            <div>
                <p>3、系统自动向注册邮箱发送验证邮件，用户登录邮箱点击验证链接即可完成邮箱验证，完成后系统自动跳转到注册流程
                    的下一步。若信息填写错误可以点击重新填写，进行修改验证邮箱的操作，若未收到验证邮件，请先检查邮箱的垃圾邮
                    件中是否有验证邮件，若无可以点击重新发送，系统会自动重新发送验证邮件。</p>
                <img src="${ctx}/static/images/p3.png">
            </div>
            <div>
                <p>4、正确填写用户资料，确认无误后点击成为开发者即可，系统自动将用户信息提交到管理员处，由管理员对用户信息
                    进行审核。</p>
                <img src="${ctx}/static/images/p4.png">
            </div>
            <div>
                <p>5、由教育云平台管理员对注册信息进行审核，审核通过后用户即完成了注册流程，在审核过程中用户可以登录教育云平台进行审核状
                    态的查看。若审核未通过，用户需要对注册信息进行更改直至通过审核。</p>
            </div>
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