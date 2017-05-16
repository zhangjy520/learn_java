<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ include file="../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<html>
<head>
    <title>开发者注册</title>
    <script src="${pageContext.request.contextPath}/static/js/action.js"></script>
</head>
<body>
<style>
    .tech-detail-article>div>img{
        width: 100%;
        margin: 20px 0;
        border: 1px solid #ddd;
    }
    p{
        margin-left: auto !important;
    }
    h4{
        font-weight: bold;
    }
</style>
<main class="container">
    <div id="document-content">
        <%@include file="../../common/docLeftMenu.jsp"%>
        <section class="col-xs-9">
            <h1>开发者注册</h1>
            <div class="tech-detail-article detial">
                <h4>一、网站登录</h4>
                <div>
                    <p>点击右上角的“开发者注册”按钮,如下图所示</p>
                    <img src="${ctx}/static/images/personQUestion1.png">
                </div>
                <h4>二、填写基本信息</h4>
                <div>
                    <p>用户需要在开发者类型中进行选择，教育云平台支持个人和企业两种类型的开发者，按照系统提示的信息进行邮箱和密码的正确填写，确认无误后选择同意遵守教育云平台协议，点击下一步。</p>
                    <img src="${ctx}/static/images/personalImg2.png">
                </div>
                <h4>三、邮箱激活</h4>
                <div>
                    <p>系统自动向注册邮箱发送验证邮件，用户登录邮箱点击验证链接即可完成邮箱验证，完成后系统自动跳转到注册流程
                        的下一步。若信息填写错误可以点击重新填写，进行修改验证邮箱的操作，若未收到验证邮件，请先检查邮箱的垃圾邮
                        件中是否有验证邮件，若无可以点击重新发送，系统会自动重新发送验证邮件。</p>
                    <img src="${ctx}/static/images/p3.png">
                </div>
                <h4>四、完善开发者资料</h4>
                <div>
                    <p>正确填写用户资料，确认无误后点击成为开发者即可，系统自动将用户信息提交到管理员处，由管理员对用户信息进行审核。图1为开发者类型为个人时所需填写的信息详情。
                        图2为开发者类型为企业时所需填写的信息详情。</p>
                    <img src="${ctx}/static/images/register1.png"><p style="text-align: center;font-weight: bold;margin-bottom: 10px;">图1</p>
                    <img src="${ctx}/static/images/register2.png"><p style="text-align: center;font-weight: bold;">图2</p>
                </div>
                <h4>五、用户审核</h4>
                <div>
                    <p>由平台管理员对注册信息进行审核，审核通过后用户即完成了注册流程，在审核过程中用户可以登录教育云平台进行审核状态的查看。若审核未通过，用户需要对注册信息进行更改直至通过审核。</p>
                </div>
            </div>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>

</body>
</html>
