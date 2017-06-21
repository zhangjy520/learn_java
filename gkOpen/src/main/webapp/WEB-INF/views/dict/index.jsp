<%--
  Created by IntelliJ IDEA.
  User: jon
  Date: 17-6-8
  Time: 上午9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="button" id="btn_add" value="新增">
    <table>
        <tr>
            <td>id</td>
            <td>类别</td>
            <td>名称</td>
            <td>值</td>
            <td>类别标识</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${pageInfo.list}" var="dl">
        <tr>
            <td>${dl.id}</td>
            <td>${dl.description}</td>
            <td>${dl.label}</td>
            <td>${dl.value}</td>
            <td>${dl.mark}</td>
            <td>
                <input type="button" class="btn_update" value="修改">
                <input type="hidden" value="${dl.id}">
                <input type="hidden" value="${dl.mark}">
                <input type="button" class="btn_del" value="删除">
            </td>
        </tr>
        </c:forEach>
    </table>
</body>

<script>
    $('#btn_add').on('click', function () {
        window.location.href = '${ctx}/dict/to/add';
    })

    $('.btn_update').on('click', function () {
        var id = $(this).next().val();
        window.location.href = '${ctx}/dict/to/update?id='+id;
    })

    $('.btn_del').on('click', function () {
        var url = '${ctx}/dict/do/del';
        var dict = {};
        dict.id = $(this).prev().prev().val();
        dict.mark = $(this).prev().val();
        $.post(url, dict ,function (result) {
            alert(result.msg);
            window.location.href = '${ctx}/dict/index';
        })
    })

</script>
</html>
