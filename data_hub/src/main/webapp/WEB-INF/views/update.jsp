<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 马立立
  Date: 2017/5/2
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="common/resouces.jsp" %>
    <script src="${ctxStatic}/js/bootstrap-table.js"></script>
    <script src="${ctxStatic}/js/bootstrap-table-zh-CN.js"></script>

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
            <%--<tr>--%>
                <%--&lt;%&ndash;<th width="5%">序号</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th width="10%">是否有异常</th>&ndash;%&gt;--%>
                <%--<th>表名</th>--%>
                <%--<th>别名</th>--%>
                <%--<th>简介</th>--%>
            <%--</tr>--%>
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
