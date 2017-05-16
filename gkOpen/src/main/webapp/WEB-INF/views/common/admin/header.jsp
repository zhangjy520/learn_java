<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container-fluid">
    <nav class="container">
        <a href="${ctx}/"><h3><img src="${ctx}/static/images/logo-logo.png" alt=""/></h3></a>
        <ul>
            <li><a href="${ctx}/" >首页</a></li>
            <li><a href="${ctx}/document/index">开发文档</a></li>
            <li><a href="${ctx}/tech/index">技术支持</a></li>
        </ul>
        <c:if test="${sessionScope.openUser == null }">
            <div>
                <span class="login">登录</span>
                <span class="regist" onclick="location.href='${ctx}/register/index'">开发者注册</span>
            </div>
        </c:if>

        <c:if test="${sessionScope.openUser != null }">
            <div>
                <span style="width:auto;border:none;"
                      onclick="window.location.href = '${ctx}/admin/index'">${sessionScope.openUser.username}</span>
                <span id="logOut" onclick="window.location.href='${ctx}/doLogout'"> 退出登录</span>
                <span onclick="window.location.href='${ctx}/admin/index'">管理中心</span>
            </div>
        </c:if>
    </nav>
</div>