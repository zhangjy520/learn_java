<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>平台动态</title>
    <style>
        body {
            padding-bottom: 80px;
        }

        .container-box {
            min-height: 700px;
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

        .container-box h2 {
            font-size: 24px;
            font-weight: 500;
            text-align: center;
            line-height: 50px;
        }

        .detail p {
            line-height: 38px;
            font-size: 14px;
        }

        .detail {
            margin-top: 20px;
        }

        .detail div p {
            text-align: right;
        }
        .tech-detail-back a{
            font-size: 14px;
            color: #54ab37;
            display: inline-block;
            padding-left: 16px;
            background:url(../../../../static/images/back.png) no-repeat left center;
        }
    </style>
</head>
<body>
<script src="${ctx}/static/js/action.js"></script>
<div class="container-box">
    <div class="tech-detail-back" >
        <a href="${ctx}/" style="color: #54ab37;" onmouseover="this.style.cursor='hand' ;style='color: #54ab37'"
           onmouseout="this.style.cursor='normal'">返回</a>
    </div>
    <h2>${extentionDynamic.dynamic.title}</h2>
    <div class="detail">
        ${extentionDynamic.dynamic.content}
        <div>
            <p>教育云开放平台</p>
            <p>${extentionDynamic.releaseTimeExt}</p>
        </div>
    </div>
</div>
</body>
</html>