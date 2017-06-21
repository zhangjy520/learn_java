<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <%@ include file="common/resouces.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/login.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal" action="${ctx}/index" method="post">
                <span class="heading">用户登录</span>
                <div class="form-group">
                    <input type="test" name="username" class="form-control" id="inputEmail3" placeholder="用户名或电子邮件">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="密　码">
                    <i class="fa fa-lock"></i>
                    <a href="#" class="fa fa-question-circle"></a>
                </div>
                <div class="form-group">
                    <div class="main-checkbox">
                        <input type="checkbox" value="None" id="checkbox1" name="check"/>
                        <label for="checkbox1"></label>
                    </div>
                    <span class="text">记住我</span>
                    <button type="submit" class="btn btn-default">登录</button>
                </div>
                <div id="myAlert"  class="alert alert-danger hidden">
                    <p>
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        用户名密码错误
                    </p>
                </div>
            </form>

        </div>
    </div>
</div>
<script>
    $(function () {
        if ("${status}"=="fail"){
            $("#myAlert").show();
        }
    })
</script>
</body>
</html>

