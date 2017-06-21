<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/zoom.css">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/another-js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/another-js/zoom.min.js"></script>
</head>
<body>
<!--导航栏-->
<!--开放文档-->
<style>
    .col-xs-9 > h1 > span {
        margin-left: 10px;
        font-size: 15px;
        font-weight: 500;
    }

    .col-xs-9 > div {
        margin: 30px 0 20px 0 !important;
    }

    .col-xs-9 > div > span {
        width: 100px;
        margin: 0 20px 0 30px !important;
        font-size: 13px !important;
        color: #888 !important;
    }

    .col-xs-9 > div > input {
        width: 45%;
        height: 30px;
        border-radius: 2px;
        border: 1px solid #ddd;
        outline: none;
        padding-left: 5px;
    }

    button {
        width: 110px;
        margin: 30px 0 30px 154px;
        height: 40px;
        border: 1px solid #54ab37;
        background: #54ab37;
        font-size: 16px;
        color: #fff;
        border-radius: 3px;
        outline: none;
    }

    /*
      图片显示的轮播图的样式
    */
    * {
        margin: 0;
        padding: 0;

    }

    a {
        text-decoration: none;
    }

    body {
        padding: 20px;
    }

    #container {
        width: 350px;
        overflow: hidden;
    }

    #list {

    }

    #buttons {
        width: 200px;
        text-align: center;
        height: 10px;
        z-index: 2; /*按钮在图片的上面*/
        position: absolute;
        left: 50%;
        bottom: 12px;
        margin-left: -100px;
    }

    #buttons span {
        cursor: pointer;
        border: 1px solid #ddd;
        width: 12px;
        height: 12px;
        border-radius: 50%;
        background: #fff;
        margin-right: 5px;
    }

    #buttons .on {
        background: #54ab37; /*选中的按钮样式*/
    }

    .arrow {
        cursor: pointer;
        display: none; /*左右切换按钮默认先隐藏*/
        line-height: 39px;
        text-align: center;
        font-size: 36px;
        font-weight: bold;
        width: 40px;
        height: 40px;
        position: absolute;
        z-index: 2;
        top: 180px;
        background-color: RGBA(0, 0, 0, .3);
        color: #fff;
    }

    .arrow:hover {
        background-color: RGBA(0, 0, 0, .7);
    }

    #container:hover .arrow {
        display: block; /*当鼠标放上去容器上面就显示左右切换按钮*/
    }

    #prev {
        left: 20px;
    }

    #next {
        right: 20px;
    }

    #list {
        width: 350px;
    }

    #check-main > p {
        font-size: 15px;
        color: #FF2C36;
        margin-top: 20px;
    }
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp" %>
        <section class="col-xs-9">
            <h1><a href="${ctx}/manager/index"
                   style="color:#54ab37;text-decoration: none;font-size: 20px;">应用管理</a><span
                    style="font-size:18px;">>&nbsp;详情</span></h1>
            <main id="check-main">
                <c:if test="${app.checkStatus==3}">
                    <p><span>应用审核不通过原因 :&nbsp;</span><span
                            style="width: 515px;vertical-align: top;"> ${clauseNotPass}</span></p>
                </c:if>
                <h3>应用信息</h3>
                <section>
                    <ul class="massages">

                        <li>
                            <aside><span>应用名称:</span></aside>
                            <div><span>${app.name}</span></div>
                        </li>
                        <li>
                            <aside><span>版本号:</span></aside>
                            <div><span>${app.version}</span></div>
                        </li>
                        <li>
                            <aside><span>应用介绍:</span></aside>
                            <div>${app.appAbstruct}</div>
                        </li>
                        <li>
                            <aside><span>应用图标:</span></aside>
                            <div><img src="${app.logo}" alt=""
                                      style="border: 1px solid #ddd;width: 100px;height: 100px;"></div>
                        </li>
                        <li>
                            <aside><span>应用类别:</span></aside>
                            <c:if test="${app.category==0}">
                                <div><span>教学教务</span></div>
                            </c:if>
                            <c:if test="${app.category==1}">
                                <div><span>教学教务</span></div>
                            </c:if>
                        </li>
                        <li>
                            <aside><span>目标用户:</span></aside>
                            <div><span>${app.targetUser}</span></div>
                        </li>
                        <li>
                            <aside><span>是否免费:</span></aside>
                            <div>
                                <c:if test="${app.isFree == 0}"><span>是</span></c:if>
                                <c:if test="${app.isFree == 1}"><span>否</span></c:if>
                            </div>
                        </li>
                        <li>
                            <aside><span>应用级别:</span></aside>
                            <div>
                                <c:if test="${app.appRank == 0}"><span>区级</span></c:if>
                                <c:if test="${app.appRank == 1}"><span>校级</span></c:if>
                            </div>
                        </li>
                        <li>
                            <aside><span>应用截图:</span></aside>
                            <div id="container">
                                <div id="list">
                                    <c:forEach items="${appScreenShotList}" var="appScreenShot" varStatus="status">
                                        <img src="${appScreenShot}" alt="应用截图"
                                             style="border: 1px solid #ddd;margin: 0 12px 12px 0;width: 100px;height: 100px;"/>
                                    </c:forEach>
                                </div>
                            </div>
                        </li>
                        <li>
                            <aside><span>演示URL:</span></aside>
                            <div><a href="">${app.appUrl}</a></div>
                        </li>
                        <li>
                            <aside><span>演示账号:</span></aside>
                            <div><span>${app.demoAccount}</span></div>
                        </li>
                    </ul>
                </section>
                <h3>应用概况</h3>
                <section>
                    <ul class="massages">
                        <li>
                            <aside><span>clientId:</span></aside>
                            <div><span>${app.clientId}</span></div>
                        </li>
                        <li>
                            <aside><span>clientSecret:</span></aside>
                            <div><span>${app.clientSecret}</span></div>
                        </li>
                    </ul>
                </section>

            </main>
        </section>
    </div>
</main>
<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>
</body>
<script>
</script>
</html>