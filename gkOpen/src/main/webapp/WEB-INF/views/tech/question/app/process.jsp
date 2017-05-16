<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/header.jsp"%>
<%@ include file="../../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="${ctx}/static/css/tech-support.css">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <!--[if lt IE 9]>

    <![endif]-->
</head>
<style>
    h5{
        margin-top: 24px
    }
</style>
<body>
<!--导航栏-->
<main class="container">
    <section id="tech-detail">
        <div class="tech-detail-back">
            <a onclick="window.history.go(-1)" onmouseover="this.style.cursor='hand'"
               onmouseout="this.style.cursor='normal'">返回</a>
        </div>
        <div class="tech-detail-article">
            <h3>开发者(个人)审核流程、开发者（企业）审核流程</h3>
            <h5>步骤一：注册成为开发者</h5>
            <p>用户首先要在教育云开放平台中注册成为开发者（个人、企业）。</p>
            <h5>步骤二：正确填写个人信息</h5>
            <p>1.开发者（个人、企业）资料中所有信息填写完全且正确。</p>
            <p>2.正确填写身份认证信息。</p>
            <p> 3.正确提交证件扫描件。</p>
            <h5>审核流程</h5>
            <p>1.开发者（个人、企业）线上提交审核申请。</p>
            <p>2.审核人员核实开发者（个人、企业）信息和证件的真实性、合法性等。</p>
            <p>3.发送审核结果通知。</p>
            <h5>备注</h5>
            <p>审核期间会有工作人员进行电话核实，请保持电话畅通。</p>
        </div>
    </section>
</main>
</body>
</html>