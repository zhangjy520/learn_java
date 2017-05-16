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

    .check-btn1{
        outline: none;
        width: 120px;
        height: 44px;
        color: #fff;
        border-radius: 4px;
        margin: 50px 30px;
        font-size: 15px;
        border: 1px solid #54ab37;
        background: #54ab37;
    }
</style>
<main class="container">

    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp" %>

        <section class=" col-xs-9">
            <h1><a href="${ctx}/manager/info/index?type=2" style="color:#54AB37;text-decoration:none;font-size: large">消息中心></a>消息详情</h1>
            <section class="news-center-detail">
                <c:if test="${openMessage.messageType == 0}">
                    <c:if test="${openMessage.status == 2}">
                        <h2>账号信息已通过审核</h2>
                    </c:if>
                    <c:if test="${openMessage.status == 3}">
                        <h2>账号信息未通过审核</h2>
                    </c:if>
                    <ul>
                        <li>
                            <span>业务</span>:
                            <label>账号信息审核</label>
                        </li>
                        <li>
                            <span>开发者类型</span>:
                            <c:if test="${openUser.userType==0}">
                                <label>个人</label>
                            </c:if>
                            <c:if test="${openUser.userType==1}">
                                <label>企业</label>
                            </c:if>
                        </li>
                        <li>
                            <span>登录邮箱</span>:
                            <label>${openUser.username}</label>
                        </li>
                        <span>审核结果</span>:
                        <c:if test="${openMessage.status == 2}">
                            <label>审核成功</label>
                        </c:if>
                        <c:if test="${openMessage.status == 3}">
                            <label>审核失败</label>
                        </c:if>
                        <li>
                            <span>备注</span>:
                            <label>${openMessage.text}</label>
                        </li>
                    </ul>
                </c:if>

                <c:if test="${openMessage.messageType == 1}">
                    <c:if test="${openMessage.status == 2}">
                        <h2>应用信息已通过审核</h2>
                    </c:if>
                    <c:if test="${openMessage.status == 3}">
                        <h2>应用信息未通过审核</h2>
                    </c:if>
                    <ul>
                        <li>
                            <span>业务</span>:
                            <label>应用信息审核</label>
                        </li>
                        <li>
                            <span>应用简称</span>:
                            <label>${openMessage.appName}</label>
                        </li>
                        <li>
                            <span>登录邮箱</span>:
                            <label>${openUser.username}</label>
                        </li>
                        <li>
                            <span>审核结果</span>:
                            <c:if test="${openMessage.status == 2}">
                                <label>审核成功</label>
                            </c:if>
                            <c:if test="${openMessage.status == 3}">
                                <label>审核失败</label>
                            </c:if>
                        </li>
                        <li>
                            <span>备注</span>:
                            <label>${openMessage.text}</label>
                        </li>
                    </ul>
                </c:if>
            </section>

            </section>
        </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>
</body>
</html>