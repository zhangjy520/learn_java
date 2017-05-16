<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>
<%--<%@ include file="/base.jsp" %>--%>
<%--<%@ include file="../common/admin/header.jsp" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<meta charset="UTF-8">--%>
    <%--<title>用户反馈界面</title>--%>
    <%--<link rel="stylesheet" href="${ctx}/static/css/common.css"/>--%>
    <%--<link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>--%>
    <%--<link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>--%>
    <%--<link rel="stylesheet" href="${ctx}/static/css/alert.css"/>--%>
    <%--<script src="${ctx}/static/js/jquery.min.js"></script>--%>
    <%--<script src="${ctx}/static/js/pageDevide.js"></script>--%>
    <%--<script src="${ctx}/static/js/alertPopShow.js"></script>--%>
    <%--<script src="${ctx}/static/another-js/layer.js"></script>--%>
    <%--<style>--%>
        <%--/*  [id*=check] table span {--%>
              <%--display: none;--%>
          <%--}*/--%>

        <%--[id*=check] table {--%>
            <%--display: none;--%>
        <%--}--%>
    <%--</style>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash;<%@ include file="../common/admin/menu.jsp" %>&ndash;%&gt;--%>
<%--<main>--%>
    <%--<div>--%>

        <%--<table border="1px">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th width="3%">应用名</th>--%>
                <%--<th width="5%">标题</th>--%>
                <%--<th width="10%">内容</th>--%>
                <%--<th width="5%">用户</th>--%>
                <%--<th width="5%">操作系统</th>--%>
                <%--<th width="5%">版本</th>--%>
                <%--<th width="5%">软件版本</th>--%>
                <%--<th width="5%">设备型号</th>--%>
                <%--<th width="5%">联系方式</th>--%>
                <%--&lt;%&ndash;<th width="5%">邮箱</th>&ndash;%&gt;--%>
                <%--<th width="5%">反馈时间</th>--%>
                <%--<th width="5%">状态</th>--%>
                <%--<th width="5%">附件</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody>--%>
            <%--<c:forEach items="${selectFeedBack}" var="feedback" varStatus="status">--%>
               <%--<tr>--%>
                   <%--<td>${feedback.applyName}</td>--%>
                   <%--<td>${feedback.title}</td>--%>
                   <%--<td>${feedback.context}</td>--%>
                   <%--&lt;%&ndash;<td>${feedback.userId}</td>&ndash;%&gt;--%>
                   <%--<td>${feedback.name}</td>--%>
                   <%--<td>${feedback.systemOperate}</td>--%>
                   <%--<td>${feedback.vsersion}</td>--%>
                   <%--<td>${feedback.softwareVersion}</td>--%>
                   <%--<td>${feedback.unitType}</td>--%>
                   <%--<td>${feedback.phone}</td>--%>
                   <%--&lt;%&ndash;<td>${feedback.email}</td>&ndash;%&gt;--%>
                   <%--<td>${feedback.feedbackTime}</td>--%>
                   <%--<c:if test="${feedback.status==0}">--%>
                       <%--<td>解决</td>--%>
                   <%--</c:if>--%>
                   <%--<c:if test="${feedback.status==1}">--%>
                       <%--<td>待解决</td>--%>
                   <%--</c:if>--%>
                   <%--<c:if test="${feedback.status==2}">--%>
                       <%--<td>未解决</td>--%>
                   <%--</c:if>--%>
                   <%--<td></td>--%>
               <%--</tr>--%>
            <%--</c:forEach>--%>
            <%--</tbody>--%>
        <%--</table>--%>
        <%--<footer class="clear">--%>
            <%--<p>每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</p>--%>
            <%--<div class="fenY"></div>--%>
        <%--</footer>--%>
    <%--</div>--%>
    <%--<button onclick="xx()">cscs</button>--%>
<%--</main>--%>

<%--<script>--%>
    <%--$(".fenY").createPage({--%>
        <%--pageCount: "${pageInfo.pages}",--%>
        <%--current:" ${pageInfo.pageNum}",--%>
        <%--backFn: function (p) {--%>
            <%--window.location.href = "${ctx}/feedback/select/data?appPageNum=" + p ;--%>
        <%--}--%>
    <%--});--%>
    <%--$(".gotoPage").click(function (){--%>
        <%--var pageNum = $(".go").val();--%>
        <%--if (pageNum <= 0 || pageNum >"${pageInfo.pages}"){--%>
            <%--layer.msg("请输入正确的页码")--%>
        <%--} else{--%>
            <%--window.location.href = "${ctx}/feedback/select/data?appPageNum=" + pageNum ;--%>
        <%--}--%>
    <%--});--%>
    <%--function xx() {--%>
        <%--alert("xsss");--%>
        <%--$.post("${ctx}/feedback/post",{--%>
            <%--appName:"haha哈哈"--%>
        <%--})--%>

    <%--}--%>
<%--</script>--%>
<%--</body>--%>

<%--</html>--%>