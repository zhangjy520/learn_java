<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link rel="stylesheet" href="${ctxStatic}/css/personal.css"/>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">个人中心</a></li>
        <li class="child-nav  active"><a href="#">账户信息</a></li>
    </ul>
</header>
<main id="account-msg" class="module-bg-full fix-width">
    <p>账户基础信息</p>
    <section>
        <div>
            <span>账户名</span>
            <span>${userInfo.account}</span>
        </div>
        <div>
            <span>姓名</span>
            <span>${userInfo.xm}</span>
        </div>
        <div>
            <span>角色</span>
            <span>${userInfo.roleName}</span>
        </div>
        <div>
            <span>操作模块</span>
            <span>
                <c:forEach items="${permissionList}" var="permission">
                    ${permission.name},
                </c:forEach>
            </span>
        </div>
        <div>
            <span>数据范围</span>
            <span>
                <c:if test="${userInfo.permission == 0}">
                    全校
                </c:if>
                <c:if test="${userInfo.permission == 1}">
                    本年级
                </c:if>
                <c:if test="${userInfo.permission == 2}">
                    本班
                </c:if>
            </span>
        </div>
        <%-- <div>
             <span>密码</span>
             <span><button class="button">修改密码</button></span>
         </div>--%>
    </section>
</main>

<%--<script>
    $('.button').click(function(){
        layer.open({
            type: 2,
            title: '修改账户密码',
            shadeClose: true,
            shade: 0.8,
            btn:['确认','取消'],
            area: ['380px', '320px'],
            content: 'pop-changePwd.html',
            move:false
        });
    })
</script>--%>
</body>
</html>