<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctx}/static/css/tech-support.css">
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css">
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
</head>
<style>
    .document-grade-1>li{
        margin: 0 !important;
    }
</style>
<body>
<main class="container">
    <section class="clearfix">
        <aside class="col-xs-3">
            <ul class="document-grade-1">
                <li>
                    <a href="#">支持中心</a>
                    <ul class="normal-question document-grade-2" style="display:block;">
                        <li><a href="#" data="normal-q1" id="commonQuestion" class="active">常见问题</a></li>
                        <li><a href="#" data="normal-q2" id="loginQuestion">登录注册</a></li>
                        <li><a href="#" data="normal-q3" id="userQuestion">用户审核</a></li>
                        <li><a href="#" data="normal-q4" id="appQuestion">应用审核</a></li>
                        <li><a href="#" data="normal-q5" id="otherQuestion">其他</a></li>
                    </ul>
                </li>
            </ul>
        </aside>
        <div class="col-xs-9" id="normal-q1">
            <h1>常见问题</h1>
            <div class="question">
                <ul>
                    <li>
                        <a href="${ctx}/tech/question" class="clearfix">
                            <span class="lf">教育云开放平台注册</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=person" class="clearfix">
                            <span class="lf">个人怎么注册成为开发者</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=company" class="clearfix">
                            <span class="lf">企业怎么注册成为开发者</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-xs-9" id="normal-q2">
            <h1>登录注册</h1>
            <div class="question">
                <ul>
                    <li>
                        <a href="${ctx}/tech/question?status=person" class="clearfix">
                            <span class="lf">个人怎么注册成为开发者</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=company" class="clearfix">
                            <span class="lf">企业怎么注册成为开发者</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=login5" class="clearfix">
                            <span class="lf">用户如何登陆系统</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=login4" class="clearfix">
                            <span class="lf">忘记密码怎么办？</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <%--<li>--%>
                        <%--<a href="${ctx}/tech/question?status=login1" class="clearfix">--%>
                            <%--<span class="lf">修改开发者资料审核时间是多久？</span>--%>
                            <%--<span class="rl">2016-11-20 16:31:23</span>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="${ctx}/tech/question?status=login2" class="clearfix">--%>
                            <%--<span class="lf">注册审核时间是多久？</span>--%>
                            <%--<span class="rl">2016-11-20 16:31:23</span>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="${ctx}/tech/question?status=login3" class="clearfix">--%>
                            <%--<span class="lf">账号丢失，如何找回</span>--%>
                            <%--<span class="rl">2016-11-20 16:31:23</span>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
        <div class="col-xs-9" id="normal-q4">
            <h1>应用审核</h1>
            <div class="question">
                <ul>
                    <%--<li>--%>
                        <%--<a href="${ctx}/tech/question?status=process" class="clearfix">--%>
                            <%--<span class="lf">审核流程</span>--%>
                            <%--<span class="rl">2016-11-20 16:31:23</span>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <li>
                        <a href="${ctx}/tech/question?status=submit" class="clearfix">
                            <span class="lf">开发者如何提交应用？</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=time1" class="clearfix">
                            <span class="lf">应用审核时间是多久？</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=time2" class="clearfix">
                            <span class="lf">应用信息修改审核时间是多久？</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-xs-9" id="normal-q3">
            <h1>用户审核</h1>
            <div class="question">
                <ul>
                    <li>
                        <a href="${ctx}/tech/question?status=user1" class="clearfix">
                            <span class="lf">个人开发者审核流程</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=user1" class="clearfix">
                            <span class="lf">企业开发者审核流程</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=user2" class="clearfix">
                            <span class="lf">怎么提交用户审核?</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=user3" class="clearfix">
                            <span class="lf">用户审核时间是多久？</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/tech/question?status=user4" class="clearfix">
                            <span class="lf">用户修改资料审核时间是多久?</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="col-xs-9" id="normal-q5">
            <h1>其他</h1>
            <div class="question">
                <ul>
                    <li>
                        <a href="${ctx}/tech/question?status=other" class="clearfix">
                            <span class="lf">其他</span>
                            <span class="rl">2016-11-20 16:31:23</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </section>

    </section>
</main>
<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>
<script>

    $('.normal-question a').click(function () {
        localStorage.index_a =  $('.normal-question a').index($(this));
        $(this).addClass('active');
        $(this).parent().siblings().children().removeClass('active');
        toggleData(this);
    });


    //    问题内容切换
    function toggleData(data) {
        var div = $('[id*=normal-q]');
        for (var i = 0; i < div.length; i++) {
            if (div[i].id == $(data).attr('data')) {
                $(div[i]).show();
                $(div[i]).siblings('div').hide();
                localStorage.nnn = i;
            }
        }
    }

    if(localStorage.index_a != 'undefind' && localStorage.index_b != 'undefind'){
        $('.normal-question a').eq(localStorage.index_a).addClass('active');
        $('.normal-question a').eq(localStorage.index_a).parent().siblings().children().removeClass('active');
        var div = $('[id*=normal-q]');
        $(div[localStorage.nnn]).show();
        $(div[localStorage.nnn]).siblings('div').hide();
    }

</script>
</body>
</html>