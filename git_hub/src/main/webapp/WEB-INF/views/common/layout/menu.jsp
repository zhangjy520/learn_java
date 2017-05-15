<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" style="cursor: text;font-size: 20px;">lexisoft数据中心</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav nav-left">
                <%--<li><a href="#">首页 <span class="sr-only">(current)</span></a></li>--%>
                <%--<li><a href="#">统计</a></li>--%>
                <li id="menu_1"><a href="${ctx}/admin/queue">队列信息</a></li>
                <li id="menu_2"><a href="${ctx}/admin/bind">推送对象绑定</a></li>
                <%--<li><a href="#">日志信息</a></li>--%>
                <li id="menu_3"><a href="${ctx}/admin/sync">同步管理</a></li>
                <li id="menu_4"><a href="${ctx}/admin/pushObj">表管理</a></li>
                <li id="menu_5"><a href="${ctx}/admin/push/select">推送对象管理</a> </li>
                <%--<li class="dropdown">--%>
                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
                <%--<ul class="dropdown-menu">--%>
                <%--<li><a href="#">Action</a></li>--%>
                <%--<li><a href="#">Another action</a></li>--%>
                <%--<li><a href="#">Something else here</a></li>--%>
                <%--<li role="separator" class="divider"></li>--%>
                <%--<li><a href="#">Separated link</a></li>--%>
                <%--<li role="separator" class="divider"></li>--%>
                <%--<li><a href="#">One more separated link</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="${ctx}/doLogout">退出登录</a></li>
                <li><a>管理员</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
