<%@ include file="../common/header.jsp"%>
<%@ include file="../login/login.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String id = request.getParameter("id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>邮箱验证</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/js/action.js"></script>
</head>
<body>
    <style>
        body{background:#fff  !important;}
        #register-process>div span:nth-child(2):before{
            background-position: -46px 0;
        }
    </style>
    <!--导航栏部分提取公共代码-->

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
        <section id="email-checking">
            <h3>感谢注册，确认邮件已发送至您的注册邮箱：</h3>
            <h4 id="email_activate">${username}</h4>
            <p>请进入邮箱查看邮件，并激活教育云开放平台账号</p>
            <%--<form method="post" action="loginEmail" id="form1">--%>
                <div><button  onclick="window.open('${url}')" class="loginEmail">登录邮箱</button></div>
                <input type="hidden" id="loginUserId" value="<%= id %>"/>
            <%--</form>--%>
                <script>
                    var strPath = window.document.location.pathname;
                    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
                    /*2208995974@qq.com*/

                    function resend() {
                        var loginUserId = $('#loginUserId').val();
                        $.post(postPath + "/register/mail/resend",{
                            id:loginUserId
                        },function (data) {
                            if(data.code == 0){
                                webToast(data.msg,"top",2300);
                                /*setTimeout(function(){
                                    window.open(postPath +data.data);
                                },2300);*/
                            } else {
                                alert(data.msg);
                            }
                        });
                    }
                </script>
            <footer style="margin-bottom: 100px">
                <p>没有收到邮件？</p>
                <p>1、请检查邮箱地址是否正确，您可以返回 <a href="${ctx}/register/index" onmouseover="this.style.cursor='hand'"
                                          onmouseout="this.style.cursor='normal'">重新填写</a></p>
                <p>2、检查您的邮件垃圾箱</p>
                <p>3、若仍未收到确认，请尝试<a onclick="resend()" onmouseover="this.style.cursor='hand'"
                                   onmouseout="this.style.cursor='normal'">重新发送</a></p>
            </footer>
        </section>
    </main>

    <!--网页信息-->
    <%@ include file="../common/footer.jsp"%>
</body>
</html>