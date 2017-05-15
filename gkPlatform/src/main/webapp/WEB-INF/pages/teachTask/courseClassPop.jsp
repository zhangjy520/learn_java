<%--
  Created by IntelliJ IDEA.
  User: LL
  Date: 2017/4/13
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<style>

</style>
<body>
<div>
    <form action="${ctx}/teach/task/course/class/add?courseId=${courseId}" method="post" class="courseClass">
        <c:forEach items="${sectionViewList}" var="sectionView">
            <input type="checkbox" name="Jszzdm"/>${sectionView.name}
            <c:forEach items='${sectionView.njview}' var='njview'>
                <input type="checkbox" name="Jszzdm"/>${njview.njname}
                <c:forEach items='${njview.banjiview}' var='banJiView'>
                    <input type="checkbox" name="classId" value="${banJiView.id}"
                           <c:if test="${banJiView.id==banJiView.selectedId}">checked</c:if>
                    />${banJiView.name}
                </c:forEach>
            </c:forEach>
        </c:forEach>
    </form>
</div>
</body>
<script>
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function doSubmit() {
        var classIds = "";
        $("input[name=classId]").each(function () {
            if ($(this).attr("checked")) {
                classIds += "," + $(this).val();
            }
        });
        $.post(postPath + "/teach/task/course/class/add", {
            courseId: '${courseId}',
            classIds: classIds
        }, function (data) {

        });
        return true;
    }
</script>
</html>
