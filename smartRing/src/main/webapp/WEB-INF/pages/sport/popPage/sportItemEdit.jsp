<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/26
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/css/pop.css">
    <style>
        .container > div {
            padding: 0 10px;
            margin-bottom: 27px;
        }

        .container > div span {
            width: 100px;
            color: #999;
            margin-right: 28px;
        }

        .container > div label {
            color: #333;
        }

        input {
            width: 150px;
            margin-right: 5px;
        }
    </style>
    <script src="${ctxStatic}/js/jquery.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
</head>
<body>
<form action="${ctx}/sport/item/update/date" id="inputForm" method="post">
    <main class="container">
       <input  type="hidden" name="itemId"  value="${sportItem.itemId}">
        <div>
            <span>项目名称：</span>
            <label><input name="itemName" type="text" value="${sportItem.itemName}"></label>
        </div>
        <div>
            <span>项目成绩单位</span>
            <label>
                <select id="itemUnitAdd">
                    <option <c:if test="${mapItemName==sportItem.itemUnit}"> selected</c:if>
                             >${sportItem.itemUnit}</option>
                    <option>米</option>
                    <option>厘米</option>
                    <option>次</option>
                    <option>分′秒″</option>
                    <option>秒</option>
                    <option>毫升</option>
                    <option>Kg/M^2</option>
                </select>
            </label>
        </div>
    </main>
</form>
<script>
    function doSubmit() {
        $.post($("form").attr('action'),{
            itemId: $("input[name='itemId']").val(),
            itemName: $("input[name='itemName']").val(),
            //itemUnit:$("option:#itemUnitAdd").html(),
            itemUnit:$("#itemUnitAdd  option:selected").html(),
        }, function (retJson) {
            if (retJson.code == 1) {
                layer.msg(retJson.msg);
            } else {
                layer.msg(retJson.msg);
                window.parent.location.reload(true);
            }
        });
    }
</script>
</body>
</html>