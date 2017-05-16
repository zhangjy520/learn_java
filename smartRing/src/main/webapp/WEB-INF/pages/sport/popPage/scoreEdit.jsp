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
            width: 55px;
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
<form action="${ctx}/sport/stuScore/add" id="inputForm" method="post">
    <main class="container">
        <input type="hidden" name="testSeq" value="${scoreDetail.prim}">
        <div>
            <span>姓名</span>
            <label>${scoreDetail.xsxm}</label>
        </div>
        <div>
            <span>学号</span>
            <label>${scoreDetail.stuNum}</label>
        </div>
        <div>
            <span>项目</span>
            <label>${scoreDetail.itemName}</label>
        </div>
        <div>
            <span>测试次序</span>
            <label>${scoreDetail.testCount}</label>
        </div>
        <div>
            <span>时间</span>
            <label><input name="testDate" type="text" value="${scoreDetail.testDate}"></label>
        </div>
        <div>
            <span>测试成绩</span>
            <c:choose>
                <c:when test="${scoreDetail.itemUnit.indexOf('分')>=0}">
                    <label><input name="testMark" type="text" value="${gukeer:unitTranslate(scoreDetail.mark)}"></label>
                </c:when>
                <c:otherwise>
                    <label><input name="testMark" type="text" value="${scoreDetail.mark}"></label>
                </c:otherwise>
            </c:choose>
            <label>（成绩单位:${scoreDetail.itemUnit}）</label>
            <input name="itemUnit" type="hidden" value="${scoreDetail.itemUnit}">
        </div>
    </main>
</form>
<script>
    function doSubmit() {
        $.post($("form").attr('action'),{
            testSeq: $("input[name='testSeq']").val(),
            testDate: $("input[name='testDate']").val(),
            itemUnit: "${scoreDetail.itemUnit}",
            testMark: $("input[name='testMark']").val()
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