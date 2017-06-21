<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ include file="../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/js/less.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
</head>
<body>
<main class="container">
    <div id="document-content">
        <%@include file="../../common/docLeftMenu.jsp" %>
        <section class="col-xs-9">
            <h1>公共返回码说明</h1>
            <div>
                <table style="margin-top:50px;">
                    <tr>
                        <td width="23%">返回码</td>
                        <td width="77%">描述</td>
                    </tr>
                    <tr>
                        <td>000000</td>
                        <td>成功</td>
                    </tr>
                    <tr>
                        <td>100000</td>
                        <td>未知错误</td>
                    </tr>
                    <tr>
                        <td>100001</td>
                        <td>系统忙</td>
                    </tr>
                    <tr>
                        <td>100002</td>
                        <td>操作超时</td>
                    </tr>
                    <tr>
                        <td>000000</td>
                        <td>成功</td>
                    </tr>
                    <tr>
                        <td>100003</td>
                        <td>网络异常</td>
                    </tr>
                    <tr>
                        <td>100004</td>
                        <td>数据库操作异常</td>
                    </tr>
                </table>
            </div>
        </section>
    </div>
</main>
<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>
</body>

</html>