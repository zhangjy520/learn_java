<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>平台动态</title>
    <link rel="stylesheet" href="${ctx}/static/css/tech-support.css">
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
            width: 70%;
            margin: 0 auto;
            font-size: 24px;
            text-align: center;
            line-height: 35px;
        }

        .detail p {
            font-family: "Microsoft YaHei" !important;
            line-height: 38px;
            font-size: 14px;
            color: #000;
            color: #666 !important;
        }

        .detail {
            margin-top: 20px;
        }

        .detail div p {
            text-align: right;
        }
    </style>
</head>
<body>
<script src="${ctx}/static/js/action.js"></script>
<div class="container-box">
    <div class="tech-detail-back" >
        <a onclick="window.history.back()">返回</a>
    </div>
    <h2>${dynamic.title}</h2>
    <div class="detail">
        ${dynamic.content}
        <div>
            <p>教育云开放平台</p>
            <p>${gukeer:millsToyyyyMMdd(dynamic.releaseTime)}</p>
        </div>
    </div>
</div>
</body>
</html>