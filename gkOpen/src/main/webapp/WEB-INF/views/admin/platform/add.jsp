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
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/another-js/layer.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/alert.css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
</head>
<body>
<%@ include file="../../common/admin/menu.jsp" %>
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

    #document-content section.col-xs-9 > div table tbody tr {
        background: #fff !important;
    }

    footer {
        text-align: center;
    }

    footer button {
        height: 40px;
        width: 112px;
        border: 1px solid #54AB37;
        border-radius: 3px;
        margin: 0 0 30px 0;
        outline: none;
        background: #54ab37;
        color: #fff;
    }
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/admin/platformMenu.jsp" %>
        <section class="col-xs-9">
            <section id="app2" class="validform">
                <p>提示：当前信息都为必填，请注意填写</p>
                <h3>应用信息</h3>
                <ul>
                    <input type="hidden"  value="${platform.id}" id="platformId">
                    <li>
                        <span style="margin-top:9px;">名称:</span>
                        <input type="text" name="platform.name" id="platformName" value="${platform.name}">
                        <i></i>
                    </li>
                    <li>
                        <span style="margin-top:9px;">接收应用地址:</span>
                        <input type="text" name="platform.urlApp" id="urlApp" value="${platform.urlApp}">
                        <i>发布后不可修改，用于大部分的对外显示，不超过六个汉字</i>
                    </li>
                    <c:if test="${status==1}">
                        <li style="width: 520px;">
                            <span style="margin-top:9px;">唯一标识:</span>
                            <span id="identitySpan"
                                  style="line-height: 36px;vertical-align: middle;width: auto;padding-left: 5px;">${platform.identity}</span>
                        </li>
                        <li style="width: 520px;">
                            <span style="margin-top:9px;">传输密钥:</span>
                            <span style="line-height: 36px;vertical-align: middle;width: auto;padding-left: 5px;">${platform.password}</span>
                        </li>
                    </c:if>
                    <li>
                        <span style="margin-top:9px;">平台地址:</span>
                        <input type="text" name="platform.urlVisit" id="urlVisit" value="${platform.urlVisit}">
                        <i>发布后不可修改，用于大部分的对外显示，不超过六个汉字</i>
                    </li>
                    <li>
                        <span>介绍:</span>
                        <textarea cols="53" rows="10" name="platform.introduce"
                                  style="resize: none;border:1px solid #ddd;" id="platformIntroduce">${platform.introduce}</textarea>
                        <i>请填写应用介绍，审核通过后将在应用商店该应用详情中体现，便于师生了解该服务概况，不超过500个字</i>
                    </li>
                </ul>
            </section>
        </section>
    </div>
    <footer>
        <button onclick="save()">保存</button>
    </footer>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>
<script>
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function save() {
        var platformName = $("#platformName").val();
        if (platformName == "") {
            webToast("平台名称不能为空", "top", 2300);
        }
        var urlVisit = $("#urlVisit").val();
        if (urlVisit == "") {
            webToast("平台访问地址不能为空", "top", 2300);
        }
        var urlApp = $("#urlApp").val();
        if (urlApp == "") {
            webToast("应用推送地址不能为空", "top", 2300);
        }
        var platformIntroduce = $("#platformIntroduce").val();
        if (platformIntroduce == "") {
            webToast("平台介绍不能为空", "top", 2300);
        }
        var url="";
        var id = $("#platformId").val();
        if (id !=null){
            url= "/platform/update";
        }else {
            url = "/platform/add";
        }
        $.post(postPath + url, {
            id:id,
            platformName: platformName,
            urlVisit: urlVisit,
            urlApp: urlApp,
            platformIntroduce: platformIntroduce
        }, function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.href = postPath + "/" + data.data;
            } else {
                alert(data.msg);
            }
        });
    }
</script>
</body>
</html>