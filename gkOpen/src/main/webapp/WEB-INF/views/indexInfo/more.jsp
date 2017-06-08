<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看更多动态</title>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/tech-support.css">
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
    <style>
        .container-box {
            height: 700px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background: #fff;
            width: 1170px;
            margin: 0 auto;
            margin-top: 30px;
            margin-bottom: 50px;
            padding: 43px;
            font-size: 14px;
        }

        .title {
            margin-bottom: 25px;
        }

        .title a {
            color: #54AB37;
        }

        .list-box ul {
            max-height: 500px;
        }

        .list-box ul li {
            line-height: 50px;
            border-bottom: 1px solid #ddd;
        }

        .list-box ul li a:hover{
           cursor: pointer;
            text-decoration: none;
        }

        .list-box ul li span {
            font-size: 12px;
            color: #999;
        }

        footer {
            margin-top: 23px;
        }

        footer p {
            float: left;
            color: #999;
            font-size: 12px;
            margin: 0;
        }

        .fenY1 {
            float: right;
        }
        .tech-detail-back a:hover{
            cursor: pointer;
        }
    </style>
</head>
<body>
<%--<div class="container-fluid">--%>
<%--<nav class="container">--%>
<%--<a href="home.html"><h3><img src="images/logo-logo.png" alt=""/></h3></a>--%>
<%--</nav>--%>
<%--</div>--%>
<div class="container-box">
    <div class="title">
        <p>
        <div class="tech-detail-back">
            <a onclick="window.history.go(-1)"
               onmouseout="this.style.cursor='normal'">返回</a>
        </div>
        </p>
    </div>

    <div class="list-box">
        <ul>
            <c:forEach items="${pageInfo.list}" var="dynamic">
                <li class="clearfix">
                    <a onclick="window.location.href='${ctx}/dynamic/detail?id=${dynamic.id}'" class="lf"
                       style="color: #666"  onmouseover="this.style.cursor='pointer'; style='color:#54ab37;text-underline:none'"
                       onmouseout="this.style.cursor='normal'; style='color:#666'">${dynamic.title}</a>
                    <span class="rl">${gukeer:millsToyyyyMMdd(dynamic.releaseTime)}</span>
                </li>
            </c:forEach>
        </ul>
    </div>
    <footer class="clearfix">
        <p>每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</p>
        <div class="fenY1"></div>
    </footer>
    <script>
        $(".fenY1").createPage({
            pageCount: '${pageInfo.pages}',
            current: '${pageInfo.pageNum}',
            backFn: function (p) {

                window.location.href = "${ctx}/dynamic/more?pageNum=" + p + "&pageSize=10";
            }
        });

        $(".gotoPage").click(function () {
            var pageNum = $(".go").val();
            if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/dynamic/more?pageNum=" + pageNum + "&pageSize=10";
            }
        });
    </script>
</div>
</body>
</html>