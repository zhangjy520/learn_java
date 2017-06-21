<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<html>
<head>
    <title>表管理</title>
    <%@ include file="common/resouces.jsp" %>
</head>
<style>
    td input[type='text']{
        height: 30px;
        padding-left: 10px;
        border: 1px solid #e5e5e5;;
    }
</style>
<body>
<%@ include file="common/layout/menu.jsp" %>
<div class="container">
    <main id="main-tab">
        <table id="table" class="table" style="margin-top: 20px;">
            <tbody>
            <form id="mytable"  method="post" action="${ctx}/admin/update/table?id=${pushObj.id}">
                <tr>
                    <td>表名：<input type="text" value="${pushObj.objTableName}" id="tableName" name="tableName"></td>
                    <td>别名：<input type="text" value="${pushObj.objName}"id="name" name="name"></td>
                    <td>简介：<input type="text" value="${pushObj.objAbstract}" id="remark" name="remark"></td>
                </tr>
                    <input type="submit" value="保存" />
                </form>
            </tbody>
        </table>
    </main>
</div>
<script>
    $("#menu_4").addClass("active");
    function pushobj(a) {
        var id = a.name;
        var id_text = '.' + id;
        $(id_text).submit();
    }
    function save(id){
        $.post("${ctx}/admin/update/table", {
            id:id,
            tableName:$("#tableName").val(),
            name:$("#name").val(),
            remark:$("#remark").val()
        });
        alert("修改成功");
        window.location.href("${ctx}/admin/pushObj");

    }



</script>

</body>
</html>
