<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../login/login.jsp"%>
<%@ include file="/base.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>开发者注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/document.css"/>
    <link rel="icon" href="${pageContext.request.contextPath}/static/images/logo-icon.png"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/static/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/respond.min.js"></script>
    <![endif]-->
    <script src="${ctx}/static/js/action.js"></script>
</head>
<body>
<!--导航栏-->
<%@ include file="../../common/header.jsp"%>

<!--开放文档-->
<style>
    #document-content section.col-xs-9>div>p{text-indent:0;}
</style>
<main class="container">
    <div id="document-content">
        <%@include file="../../common/docLeftMenu.jsp"%>
        <section class="col-xs-9">
            <h1>应用运营管理</h1>
            <div>
                <h4>一、开发者管理</h4>
                <p>开发者账号管理：开发者账号管理员可以对开发者的账号进行申请审核、查看、禁用、启用和重置密码等单独操作和批量操作。启用和重置密码等单独操作和批量操作</p>
                <p>API接口申请审核管理：API接口管理员对开发者申请的API接口查看申请原因，决定是否对该开发者是否开发API接口管理：开发者账号管理员可以对开发者的账号进行申请审核、查看。是否对该开发者是否开发API接</p>
                <p>开发者账号管理：开发者账号管理员可以对开发者的账号进行申请审核、查看、禁用、启用和重置密码等单独操作和批量操作。启用和重置密码等单独操作和批量操作</p>
            </div>
            <div>
                <h4>二、应用管理</h4>
                <p>应用接入审核：应用管理员审核开发者提交的应用接入申请，是否可以接入到教育云平台上。</p>
                <p>应用测试审核：开发者向平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应用进行上线操作。测试通过后，便可对应用进行上线操作</p>
            </div>
            <div>
                <h4>三、应用管理</h4>
                <p>应用接入审核：应用管理员审核开发者提交的应用接入申请，是否可以接入到教育云平台上。</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应用进行上线操作应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用。</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应操作。</p>
            </div>
            <div>
                <h4>四、应用管理</h4>
                <p>应用接入审核：应用管理员审核开发者提交的应用接入申请，是否可以接入到教育云平台上。</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应用进行上线操作试审核：开发者向教育云平台申请应用上线，应用管理员首先对申。</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应用进行上线操作。应用进行上线操作试审核：开发者向教育云平台申请</p>
            </div>
            <div>
                <h4>五、应用管理</h4>
                <p>应用接入审核：应用管理员审核开发者提交的应用接入申请，是否可以接入到教育云平台上。</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应用进。向教育云平台申请应用上线，应用管理员首先对申</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应用进行上线操作。行上线操作</p>
            </div>
            <div>
                <h4>六、应用管理</h4>
                <p>应用接入审核：应用管理员审核开发者提交的应用接入申请，是否可上。</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测应用进行上线操作。</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应用进行上线操作试，测试通过后，便可对。以接入到教育云平台</p>
                <p>应用测试审核：开发者向教育云平台申请应用上线，应用管理员首先对申请上线的应用进行测试，测试通过后，便可对应用进行上线操作试，测试通过后，便可对。以接入到教育云平台</p>
            </div>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>


<script src="${pageContext.request.contextPath}/static/js/less.js"></script>
</body>
</html>