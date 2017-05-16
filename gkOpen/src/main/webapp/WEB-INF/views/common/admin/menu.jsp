<%@ page contentType="text/html;charset=UTF-8" %>
<header class="clear">
    <h3>开放平台管理</h3>
    <ul>
        <li onclick="location.href='${ctx}/admin/index'"  id="checkManager">审核管理</li>
       <%-- <li onclick="location.href='${ctx}/admin/sync'" id="syncDate">数据同步</li>--%>
        <li onclick="location.href='${ctx}/admin/push/index?pushStatus=UNPUSH'" id="appSend">应用推送</li>
        <li onclick="location.href='${ctx}/dynamic/index'" id="adminDynamic">平台动态</li>
        <li onclick="location.href='${ctx}/platform/index'" id="platformManager">平台管理</li>
    </ul>
</header>
<script>
    $(function () {
        if ("${adminIndex}"=="adminIndex"){
            $("#checkManager").attr("class","active");
        }
        if ("${appPush}"=="appPush"){
            $("#appSend").attr("class","active");
        }
        if ("${adminDynamic}"=="adminDynamic"){
            $("#adminDynamic").attr("class","active");
        }
        if ("${platformManager}"=="platformManager"){
            $("#platformManager").attr("class","active");
        }
        if ("${sync}"=="sync"){
            $("#syncDate").attr("class","active");
        }
    })
</script>