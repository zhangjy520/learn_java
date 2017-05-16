<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/another-js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/js/pageDevide.js"></script>
</head>
<body>
<!--导航栏-->
<!--开放文档-->
<style>
    .col-xs-9 > h1 > input {
        margin-left: 10px;
        position: relative;
        top: 2px;
        margin-right: 5px;
    }

    .col-xs-9 > h1 > label {
        font-size: 14px;
        color: #333;
        font-weight: normal;
        cursor: pointer
    }
    .news-center{
        margin-top: 25px !important;
    }
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp" %>
        <section class="col-xs-9">

            <h1>消息中心 <input type="checkbox" id="check"/><label for="check" id="unread-label">仅显示未读消息</label></h1>
            <section class="news-center">
                <ul style="padding-bottom: 20px;">
                    <c:forEach items="${messageContent}" var="message">
                        <c:if test="${message.openMessage.isread == 1}">
                            <li class="read">
                                <c:if test="${message.openMessage.status == 2}">
                                    <c:if test="${message.openMessage.messageType == 0}">
                                        <a onclick="window.location.href ='${ctx}/manager/info/detail?messageId=${message.openMessage.id}'">您当前账号信息已通过审核</a>
                                    </c:if>
                                    <c:if test="${message.openMessage.messageType == 1}">
                                        <a onclick="window.location.href ='${ctx}/manager/info/detail?messageId=${message.openMessage.id}'">您当前应用信息已通过审核</a>
                                    </c:if>
                                </c:if>
                                <c:if test="${message.openMessage.status == 3}">
                                    <c:if test="${message.openMessage.messageType == 0}">
                                        <a onclick="window.location.href ='${ctx}/manager/info/detail?messageId=${message.openMessage.id}'">您当前账号信息未通过审核</a>
                                    </c:if>
                                    <c:if test="${message.openMessage.messageType == 1}">
                                        <a onclick="window.location.href ='${ctx}/manager/info/detail?messageId=${message.openMessage.id}'">您当前应用信息未通过审核</a>
                                    </c:if>
                                </c:if>
                                <aside>
                                    <i></i>
                                    <span>${message.dateFormat}</span>
                                </aside>
                            </li>
                        </c:if>
                        <c:if test="${message.openMessage.isread == 0}">
                            <li class="unread">
                                <c:if test="${message.openMessage.status==2}">
                                    <c:if test="${message.openMessage.messageType == 0}">
                                        <a onclick="window.location.href ='${ctx}/manager/info/detail?messageId=${message.openMessage.id}'">您当前账号信息已通过审核</a>
                                    </c:if>
                                    <c:if test="${message.openMessage.messageType == 1}">
                                        <a onclick="window.location.href ='${ctx}/manager/info/detail?messageId=${message.openMessage.id}'">您当前应用信息已通过审核</a>
                                    </c:if>
                                    <aside>
                                        <i></i>
                                        <span>${message.dateFormat}</span>
                                    </aside>
                                </c:if>
                                <c:if test="${message.openMessage.status==3}">
                                    <c:if test="${message.openMessage.messageType == 0}">
                                        <a onclick="window.location.href ='${ctx}/manager/info/detail?messageId=${message.openMessage.id}'">您当前账号信息未通过审核</a>
                                    </c:if>
                                    <c:if test="${message.openMessage.messageType == 1}">
                                        <a onclick="window.location.href ='${ctx}/manager/info/detail?messageId=${message.openMessage.id}'">您当前应用信息未通过审核</a>
                                    </c:if>
                                    <aside>
                                        <i></i>
                                        <span>${message.dateFormat}</span>
                                    </aside>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>

            </section>
            <footer class="clear">
                <p style="color: #999;font-size: 12px;text-indent: 0em;float: left;">
                    每条显示${messagePageInfo.pageSize}条，共${messagePageInfo.pages}页，共${messagePageInfo.total}条记录</p>
                <div style="float:right;" class="fenY"></div>
            </footer>
            <script>
                $(".fenY").createPage({
                    pageCount:${messagePageInfo.pages},
                    current:${messagePageInfo.pageNum},
                    backFn: function (p) {
                        window.location.href = "${ctx}/manager/info/index?pageSize=10&pageNum=" + p;
                    }
                });
                if ("${basic}" == "basic") {
                    $("#appManager").removeClass('active');
                    $("#dynamic").addClass('active');
                }
                $("#check").on("click", function () {
                    if (this.checked == true) {
                        $(".read").hide();
                    } else {
                        $(".read").show();
                    }
                });
                $(".gotoPage").click(function () {
                    var pageNum = $(".go").val();
                    if (pageNum <= 0 || pageNum >${messagePageInfo.pages}) {
                        layer.msg("请输入正确的页码")
                    } else {
                        window.location.href = "${ctx}/manager/info/index?pageNum=" + $(".go").val() + "&pageSize=10";
                    }
                });
            </script>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>
</body>
</html>