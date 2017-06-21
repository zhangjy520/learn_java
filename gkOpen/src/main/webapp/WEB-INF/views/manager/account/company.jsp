<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<%
    response.setHeader("Access-Control-Allow-Origin", "*");
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <%--七牛图片上传--%>
    <script src="${ctx}/static/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/js/respond.min.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
    <script src="${ctx}/static/open/uploadfile.js"></script>
    <link href="${ctx}/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${ctx}/qiniu/bower_components/bootstrap/dist/css/bootstrap.css">
    <%--<link rel="stylesheet" href="${ctx}/qiniu/styles/main.css">--%>
    <link rel="stylesheet" href="${ctx}/qiniu/styles/highlight.css">
    <%--<script src="${ctx}/qiniu/respond.min.js"></script>--%>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/moxie.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/plupload.dev.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/scripts/ui.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/src/qiniu.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/scripts/highlight.js"></script>
    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>
    <script type="text/javascript" src="${ctx}/static/open/jsAddress.js"></script>
    <script type="application/javascript" src="${ctx}/static/open/register.js"></script>

</head>
<body>


<!--开放文档-->
<style>
    .col-xs-9 > h1 {
        font-weight: 500;
    }

    .col-xs-9 > h1 a {
        font-size: 12px;
        color: #54ab37;
        text-decoration: none;
        line-height: 37px;
    }

    .col-xs-9 div {
        border-bottom: 1px solid #ddd;
        padding-top: 24px;
        margin: 0 !important;
    }

    .col-xs-9 div > p {
        font-size: 14px !important;
        text-indent: 0em !important;
        margin-bottom: 20px;
    }

    .col-xs-9 div > p>span:nth-child(2){
        display: inline-block;
        width: 70%;
        word-wrap: break-word;
        word-break: break-all;
        vertical-align: top;
    }
    .short-sp{
        width: auto !important;
    }
    .col-xs-9 div > p label {
        font-weight: normal;
        color: #333;
        margin-left: 5px;
    }

    .col-xs-9 div > p span:last-child {
        color: #333;
    }

    .col-xs-9 div > p span:first-child {
        color: #888;
        text-align: right;
        width: 18%;
        margin-right: 20px;
    }

    .col-xs-9 div > p span.img {
        width: 80px;
        height: 80px;
        vertical-align: top;
        background: #fafafa;
        border: 1px solid #ddd;
    }

    .col-xs-9 div > p input[type=text] {
        border-radius: 3px;
        width: 48%;
        height: 37px;
        border: 2px solid #ddd;
        padding-left: 5px;
        outline: none;
    }

    .col-xs-9 div > p select {
        width: 100px;
        height: 44px;
        border: 2px solid #ddd;
        border-radius: 3px;
        outline: none;
    }

    #getCode {
        width: 130px;
        height: 100%;
        text-align: center;
        padding: 0;
        cursor: pointer;
        border-color: #54AB37;
        color: #54AB37;
        margin-left: 5px;
    }

    button {
        width: 110px;
        height: 40px;
        border: 1px solid #54ab37;
        background: #54ab37;
        font-size: 16px;
        color: #fff;
        border-radius: 3px;
        outline: none;
        border: 1px solid ;
    }
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp" %>
        <section class="col-xs-9">
            <c:if test="${openUser.status == 0}">
                <h1>开发者资料 <a href="#" class="rl">未提交审核状态</a></h1>
            </c:if>

            <c:if test="${openUser.status == 1}">
                <h1>开发者资料 <a href="#" class="rl">请耐心等待审核</a></h1>
            </c:if>

            <c:if test="${openUser.status == 2}">
                <h1>开发者资料 <a href="#" class="rl">审核成功</a></h1>
            </c:if>

            <c:if test="${openUser.status == 3}">
                <h1>开发者资料 <a href="#" class="rl">审核失败</a></h1>
            </c:if>

            <%--<c:if test="${openUser.status == 4}">
                <h1>开发者资料 <a href="#" class="rl">请完善资料</a></h1>
            </c:if>--%>
            <c:if test="${openUser.status == 5}">
                <h1>开发者资料 <a href="#" class="rl">已提交修改资料，请耐心等待审核</a></h1>
            </c:if>
            <div style="padding-bottom: 5px;">
                <p style="margin-left: auto;">
                    <span>开发者类型:</span>
                    <span>企业</span>
                </p>
                <p style="margin-left: auto;">
                    <span>登录邮箱:</span>
                    <span id="usernameEmail">${sessionScope.openUser.username }</span>
                </p>
                <p></p>
            </div>
            <form action="${ctx}/manager/updateInfo" method="post" id="submitForm" data-ajax='false'>
                <div>
                    <p><label style="font-size:16px">企业信息</label></p>
                </div>
                <div>
                    <%--<p><span>企业信息</span></p>--%>
                    <input type="hidden" id="domain" value="http://file3.ckmooc.com/">
                    <input type="hidden" id="uptoken_url" value="<%=basepath+"/file/getuptoken" %>">
                    <p>
                        <span>企业名称:</span>
                        <span <%--class="change"--%>>${company.businessName}</span>
                    </p>
                    <p id="foreAdress">
                        <span>企业地址:</span>
                        <span class="change">${company.address}</span>
                        <input type="hidden" value="${company.address}" class="forUpdate required" id="hiddenAdress"
                               name="company.address"/>
                    </p>
                    <%--<p style="display: none" class="forUpdatePersonalP">--%>
                        <%--<span>联系地址:</span>--%>
                        <%--<select id="cmbProvince" name="cmbProvince"></select>--%>
                        <%--<select id="cmbCity" name="cmbCity"></select>--%>
                        <%--<select id="cmbArea" name="cmbArea"></select>--%>
                        <%--<script type="text/javascript">--%>
                            <%--addressInit('cmbProvince', 'cmbCity', 'cmbArea');--%>
                        <%--</script>--%>
                    <p style="display: none" class="forUpdatePersonalP">
                        <span></span>
                        <input type="text" name="cmbAddress" class="easyui-validatebox textbox"
                               data-options="required:true" value="${sessionScope.personal.address}" id="address"
                               style="height: 42px;"/>
                    </p>
                    </p>
                    <p>
                        <span>企业电话:</span>
                        <span class="change">${company.companyPhone}</span>
                        <input type="hidden" value="${company.companyPhone}" class="forUpdate required"
                               id="companyPhone" name="company.companyPhone" style="height: 42px;"/>
                        <%--<span>--%>
                        <%--<input type="button" style="display: none" class="forUpdate required"--%>
                        <%--onclick="sendCode(this)" value="免费获取验证码" id="getCode" width="180px" height="50px">--%>
                        <%--</span>--%>
                        <%--&lt;%&ndash;<span>短信验证码</span>&ndash;%&gt;--%>
                        <%--<input type="hidden" placeholder="验证码为6位数字" id="codeInput"--%>
                        <%--value="" datatype="n6-6" errormsg="验证码错误" class="forUpdate required"/>--%>


                    </p>
                    <p>
                        <span class="spanForUpdate">营业执照注册号:</span>
                        <span class="change">${company.licenceNum}</span>
                        <input type="hidden" value="${company.licenceNum}" class="forUpdate required"
                               name="company.licenceNum" style="height: 42px;"/>
                    </p>
                    <p>
                        <span class="spanForUpdate">营业执照所在地:</span>
                        <span class="change">${company.licenceSite}</span>
                        <input type="hidden" value="${company.licenceSite}" class="forUpdate required"
                               name="company.licenceSite" style="height: 42px;"/>
                    </p>
                    <p>
                        <span>营业执照副本:</span>
                        <img alt="" id="wm6" width="100px" height="100px" src="${accessories.licenceScan}"
                             data-url="${accessories.identityPhoto}"
                             style="vertical-align: top;border: 1px solid #ddd;"/>
                    </p>
                    <p>
                        <span class="spanForUpdate">注册资本:</span>
                        <span <%--class="change"--%> class="short-sp">${company.capital}</span>
                        <span>万元</span>
                    </p>
                </div>
                <div>
                    <p><label style="font-size:16px">法人信息</label></p>
                </div>
                <div>
                    <p>
                        <span>法人姓名:</span>
                        <span class="change">${company.corporateName}</span>
                        <input type="hidden" value="${company.corporateName}" class="forUpdate required"
                               name="company.corporateName" style="height: 42px;"/>
                    </p>
                    <p>
                        <span>身份证号码:</span>
                        <span class="change">${company.corporateIdentity}</span>
                        <input type="hidden" value="${company.corporateIdentity}" class="forUpdate required"
                               name="company.corporateIdentity" style="height: 42px;"/>
                    </p>
                    <p>
                        <span>身份证照片或扫描件:</span>
                        <img alt="" id="wm7" width="100px" height="100px" src="${accessories.identityPhoto}"
                             data-url="${accessories.identityPhoto}"
                             style="vertical-align: top;border: 1px solid #ddd;"/>
                    </p>
                </div>
                <div>
                    <p><label style="font-size:16px;">个人信息</label></p>
                </div>
                <div>
                    <p>
                        <span>开发者姓名:</span>
                        <span class="change">${company.developerName}</span>
                        <input type="hidden" value="${company.developerName}" class="forUpdate required"
                               name="company.developerName" style="height: 42px;"/>
                    </p>
                    <p>
                        <span>手机号码:</span>
                        <span class="change">${company.developerPhone}</span>
                        <input type="hidden" value="${company.developerPhone}" class="forUpdate required"
                               id="phone" name="company.developerPhone" style="height: 42px;"/>
                        <span>
                            <input type="button" style="display: none;width:180px;height: 42px;"
                                   class="forUpdate required" datatype="m" errormsg="请输入正确的手机号码"
                                   onclick="sendCode(this)" value="免费获取验证码" id="getCode">
                        </span>
                    </p>
                    <p>
                        <span class="forUpdate required"></span>
                        <input type="hidden" placeholder="验证码为6位数字" id="codeInput"
                               value="" datatype="n6-6" errormsg="验证码错误" class="forUpdate required"
                               style="height: 42px;"/>
                    </p>
                </div>
            </form>
            <p style="text-align: center; padding: 30px 0 15px 0;">
                <c:if test="${openUser.status == 3||openUser.status==2}">
                    <button onclick="spanHideAndInputShow()" id="btnForUpdate">修&nbsp;改</button>
                </c:if>
                <button id="btnForSave" style="display: none;margin-right: 10px;" onclick="saveForUpdateInfo()"
                        type="submit">提&nbsp;交
                </button>
                <button style="display: none;margin-left: 10px;" id="btnForCancel"
                        onclick="window.location.href='${ctx}/manager/basic'">取消修改
                </button>
            </p>
            <script>

                $(function () {
                    var openUserStatus = '${openUser.status}';
                    if (openUserStatus == 0 || openUserStatus == 5) {
                        $("#btnForUpdate").hide();
                    }
                })
                function spanHideAndInputShow() {
                    $(".change").hide();
                    $(".forUpdate").attr("type", "text");
                    $("#btnForUpdate").hide();
                    $("#btnForSave").show();
                    $(".imgInputForUpdate").show();
                    $('.col-md-12').show();
//                    $('.forUpdatePersonalP').css('display', 'block');
//                    $('#foreAdress').hide();
                    $('#getCode').css('display', 'block');
                    $('#codeInput').show();
                    $("#btnForCancel").show();
//                    $('#getCode1').css('display', 'block');
//                    $('#codeInput1').show();
                }

                function saveForUpdateInfo() {
//                    var address = $("#cmbProvince").val() + $("#cmbCity").val()
//                            + $("#cmbArea").val() + $("#address").val();
//                    $("#hiddenAdress").val(address);
                    var username = document.getElementById("usernameEmail").innerHTML;
                    var code = $("#codeInput").val();
                    $.post(postPath + "/register/judgeCode", {
                                code: code,
                                username: username
                            }, function (data) {
                                if (data.code == 0) {
                                    /*  webToast("提交成功请耐心等待审核","top",2300);*/
                                    setTimeout(function () {
                                                $("#submitForm").submit();
                                            },
                                            2300)
                                } else {
                                    webToast(data.msg, "top", 2300);
                                }
                            }
                    );

                }
            </script>
        </section>

    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>

</body>
</html>



