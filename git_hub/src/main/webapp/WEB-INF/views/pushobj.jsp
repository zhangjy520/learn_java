<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lx
  Date: 2017/4/12
  Time: 18:25
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
    table{
        color: #525252;
    }
    .modify, .dele{
        width: 50px;
        height: 20px;
        line-height: 20px;
        text-align: center;
        font-size: 12px;
        border:none;
        color: #fff;
        border-radius: 5px;
    }
    .modify{
        background: rgba(0, 189, 239, .5);
    }
    .dele{
        background: rgba(172, 41, 37, .55);
    }

</style>
<body>
<%@ include file="common/layout/menu.jsp" %>
<div class="container">
    <main id="main-tab">
        <table id="table" class="table" style="margin-top: 20px;" border="1" borderColor="#e5e5e5" cellspacing="0" cellpadding="0">
            <thead>
            <tr>
                <%--<th width="5%">序号</th>--%>
                <%--<th width="10%">是否有异常</th>--%>
                <th>表名</th>
                <th>别名</th>
                <th>简介</th>
                <th>编辑&nbsp;&nbsp;&nbsp;</th>
            </tr>
            </thead>
            <tbody>

            <form  method="post" action="${ctx}/admin/select/table?id=''" style="display: inline-block" >
                <input type="submit" value="新增">
            </form>


            <c:forEach items="${pushObjList}" var="ObjView" varStatus="status">
                <tr>

                    <td>${ObjView.objTableName}</td>
                    <td>${ObjView.objName}</td>
                    <td>${ObjView.objAbstract}</td>
                    <td>
                        <form id="mytable"  method="post" action="${ctx}/admin/select/table?id=${ObjView.id}" style="display: inline-block" >
                            <input class="modify" type="submit" value="修改"/>
                        </form>
                        <%--<input type="button" value="删除" onclick="deleteTable(${ObjView.id})">--%>
                        <button class="dele" onclick="deleteTable('${ObjView.id}')" value="删除">删除</button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <%--<footer class="clear">
            <p>每条显示${syncViews.pageSize}条，共${syncViews.pages}页，共${syncViews.total}条记录</p>
            <div class="fenY" id="fenYDiv"></div>
        </footer>--%>
    </main>
</div>
<script>

    $("#menu_4").addClass("active");

    function pushobj(a) {
        var id = a.name;
        var id_text = '.' + id;
        $(id_text).submit();
    }

    <%--function deleteTable(idd) {--%>
        <%--var id = idd;--%>
        <%--$.get("${ctx}/admin/delete/table", {--%>
            <%--id: id--%>
        <%--});--%>
        <%--window.location.reload(true);--%>
        <%--alert("删除成功");--%>
    <%--}--%>
    function  deleteTable(idd) {
        var id = idd;

        $.get("${ctx}/admin/delete/table",{
            id:id
        })
        window.location.reload(true);
//        alert("删除");
    }

/*    $(function () {
        $(".fenY").createPage({
            pageCount: ${syncViews.pages},
            current: ${syncViews.pageNum},
            backFn: function (p) {
                window.location.href = "${ctx}/admin/sync?pageNum="+p+"&pageSize=10";
            }
        });
    })*/

</script>

<footer></footer>
</body>

</html>
