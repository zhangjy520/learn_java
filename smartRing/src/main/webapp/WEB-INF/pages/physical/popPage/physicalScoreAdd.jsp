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
        .container > div{margin-bottom:27px;padding:0 15px;}
        .container > div span{width:55px;color:#676767;margin-right:27px;}
        .container > div input[type=text],div select{width:230px;}
    </style>
    <script src="${ctxStatic}/js/jquery.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
</head>
<body>
<form action="${ctx}/physical/score/add" id="inputForm" method="post">
<main class="container">
    <input type="hidden" name="testSeq" value="${testSeq}">
    <div>
        <span>学号</span>
        <input type="text" name="stuNo" placeholder="请输入学生学号">
    </div>
    <div>
        <span>项目</span>
        <select name="itemId" id="itemId">
            <c:forEach items="${sportItemList}" var="sportItem">
                <option <c:if test="${itemName==sportItem.itemName}"> selected</c:if>
                        name="${sportItem.itemUnit}" value="${sportItem.itemId}">${sportItem.itemName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <span>时间</span>
        <input name="testTime" type="text" placeholder="请输入测试时间，例如:2016年春">
    </div>
    <div>
        <span>测试成绩</span>
        <input name="testMark" type="text" placeholder="请输入测试成绩，例如:5">
    </div>
</main>
</form>
<script>
    function doSubmit() {
        var stuNo = $("input[name='stuNo']").val();
        var itemId = $("#itemId option:selected").val();
        var testTime = $("input[name='testTime']").val();
        var testMark = $("input[name='testMark']").val();

        if (stuNo == '' || stuNo.trim().length == 0 && itemId == '' || itemId.trim().length == 0 &&
                testTime == '' || testTime.trim().length == 0 && testMark == '' || testMark.trim().length == 0) {
            layer.msg("请填写对应参数");
        } else{
            $.post($("form").attr('action'),{
                stuNo: stuNo,
                itemId: itemId,
                itemUnit: $("#itemId option:selected").attr("name"),
                testTime: testTime,
                testMark: testMark
            }, function (retJson) {
                if (retJson.code == 1) {
                    layer.msg(retJson.msg);
                } else {
                    layer.msg(retJson.msg);
                    window.parent.location.reload(true);
                }
            });
        }
    }
</script>
</body>
</html>
