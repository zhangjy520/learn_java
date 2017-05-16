<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../login/login.jsp" %>
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
    <title>完善资料</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctx}/static/css/h5upload.css"/>
    <script type="text/javascript" src="${ctx}/static/open/jsAddress.js"></script>
    <script src="${ctx}/static/open/register.js"></script>
    <script src="${ctx}/static/js/h5upload.js"></script>
    <script src="${ctx}/static/open/uploadfile.js"></script>

    <script src="${ctx}/static/another-js/Validform_v5.3_min.js"></script>

    <link href="${ctx}/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${ctx}/qiniu/bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/qiniu/styles/main.css">
    <link rel="stylesheet" href="${ctx}/qiniu/styles/highlight.css">
    <link rel="stylesheet" href="${ctx}/static/css/alert.css"/>
    <%--<script src="${ctx}/qiniu/respond.min.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/qiniu/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>--%>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/moxie.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/plupload.dev.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/scripts/ui.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/src/qiniu.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/scripts/highlight.js"></script>
    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>
    <script src="${ctx}/static/js/radialIndicator.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
</head>
<body>
<style>
    body {
        background: #fff !important;
    }

    #register-process > div span:nth-child(2):before {
        background-position: -46px 0;
    }

    #register-process > div span:nth-child(3):before {
        background-position: -92px 0;
    }

    #register-process > div {
        margin: 0;
        padding: 0 100px;
        border: 1px solid #ddd;
        border-top: none;
    }

    #register-process > h3 {
        margin-bottom: 0;
    }

    #register-process {
        margin-bottom: 0;
    }

    #getCode {
        margin-left: 13px;
        width: 145px;
        height: 50px;
        border: 2px solid #54ab37;
        outline: none;
        color: #54ab37;
        background: #fff;
        font-size: 16px;
        border-radius: 3px;
        position: relative;
    }

    .tooltip {
        opacity: 1 !important;
    }
    .validTip span.Validform_wrong{color:red !important;font-size:13px !important;}
    .validTip span.Validform_right{color:#71b83d !important;font-size:13px !important;}
</style>
<!--导航栏-->
<!--注册流程-->
<main class="container" id="register">
    <header id="register-process">
        <h3>开发者注册</h3>
        <div>
            <span class="col-xs-4">填写基本信息</span>
            <span class="col-xs-4">邮箱激活</span>
            <span class="col-xs-4">完善开发者资料</span>
        </div>
    </header>
    <section id="complete-data">
        <div class="register-msg">
            <p>
                <span>开发者类型：</span>
                <span class="userType" value="1">企业</span>
            </p>
            <p>
                <span>登录邮箱：</span>
                <span id="usernameEmail">${username}</span>
            </p>
        </div>
        <form id="save-detail" action="${ctx}/register/save" method="post" class="validform">
            <div class="company-msg">
                <h3 style="color:#333;">企业信息</h3>
                <div>
                    <span>企业名称：</span>
                    <input type="text" name="company.businessName" id="company_name" datatype="*"/>
                </div>
                <section class="validTip" style="left:277px;top:118px;"></section>

                <div>
                    <span>企业地址：</span>
                    <select id="cmbProvince" name="cmbProvince"></select>
                    <select id="cmbCity" name="cmbCity"></select>
                    <select id="cmbArea" name="cmbArea"></select>
                    <script type="text/javascript">
                        addressInit('cmbProvince', 'cmbCity', 'cmbArea');
                    </script>
                    <p>
                        <span></span>
                        <input type="text" datatype="*" name="cmbAddress" value="${sessionScope.personal.address}" id="address"/>
                    </p>
                    <section class="validTip" style="left:200px;top:118px;"></section>

                </div>

                <div class="company-phone">
                    <span>企业电话：</span>
                    <input type="text" name="company.companyPhone" id="company_phoneNumber" datatype="*"/>
                </div>
                <p class="validTip" style="left:277px;top:424px;"></p>
                <div>
                    <span>营业执照注册号：</span>
                    <input type="text" name="company.licenceNum" datatype="n15-15" errormsg="请输入正确的营业执照注册号码"
                           id="licence_num"/>
                </div>
                <p class="validTip" style="left:277px;top:521px;"></p>
                <div>
                    <span>营业执照所在地：</span>
                    <input type="text" name="company.licenceSite" id="licence_site" datatype="*"/>
                </div>
                <p class="validTip" style="left:277px;top:637px;"></p>
                <div>

                    <div class="license">
                        <input type="hidden" name="accessories.licenceScan" id="tosql1"/>
                        <span style="vertical-align: top;">营业执照副本：</span>

                        <input type="hidden" id="domain" value="http://file3.ckmooc.com/">
                        <input type="hidden" id="uptoken_url" value="<%=basepath+"/file/getuptoken" %>">

                        <img alt="" id="wm1" width="144px" height="90px" src=""/>
                        <div class="col-md-12">
                            <div id="container1" style="margin: 23px 0px 12px 192px;">
                                <a class="btn btn-default btn-lg " id="pickfiles1" href="#">
                                    <i class="glyphicon glyphicon-plus"></i>
                                    <span style="width:44px;">选择文件</span>
                                </a>
                            </div>
                        </div>

                        <%--<div class="col-md-12 ">--%>
                            <%--<table class="table table-striped table-hover text-left" id="successed"--%>
                                   <%--style="margin-top:40px;display:none">--%>
                                <%--<thead>--%>
                                <%--<tr>--%>
                                <%--</tr>--%>
                                <%--</thead>--%>
                                <%--<tbody id="fsUploadProgress">--%>
                                <%--</tbody>--%>
                            <%--</table>--%>
                        <%--</div>--%>
                        <div style="margin: 23px 0px 23px 207px;color:#999;">
                            <p style="margin:0;">请上传营业执照清晰彩色原件照片或扫描件</p>
                            <p style="margin:0;">在有效期内切年检章齐全（当年成立的可无年检章）</p>
                            <p style="margin:0;">由中国大陆工商局或市场监督管理局颁发</p>
                            <p style="margin:0;">支持.jpg .jepg .bmp .gif .png格式照片，大小不超过2M</p>
                        </div>
                    </div>
                </div>
            </div>

            </div>
            <div class="legal-person-msg">
                <h3>法人信息</h3>
                <div class="legal-person-name">
                    <span>法人姓名：</span>
                    <input type="text" name="company.corporateName" class="textbox" datatype="*"
                            id="corporate_name"/>
                </div>
                <p class="validTip" style="left:277px;bottom:963px;z-index:999"></p>
                <div class="ID-card">
                    <span>身份证号码：</span>
                    <input type="text"  name="company.corporateIdentity"
                           datatype="sfz" errormsg="请输入正确的身份证号" id="corporate_identity"/>
                </div>
                <p class="validTip" style="left:277px;bottom:827px;z-index:999"></p>

                <div>
                    <%--<form  id="corporate_photo_form" encType="multipart/form-data"   target="hidden_frame">--%>
                    <div class="license" style="margin-bottom:60px;margin-top:80px;">
                        <span style="vertical-align: top;">身份证照片或扫描件：</span>

                        <input type="hidden" name="accessories.identityPhoto" id="tosql2"/>
                        <img id="wm2" width="144px" height="90px"/>
                        <div class="col-md-12">
                            <div id="container2" style="margin: 23px 0px 12px 192px;">
                                <a class="btn btn-default btn-lg " id="pickfiles2" href="#">
                                    <i class="glyphicon glyphicon-plus"></i>
                                    <span style="width:44px;">选择文件</span>
                                </a>
                            </div>
                        </div>
                        <div style="margin: 23px 0px 23px 207px;color:#999;">
                            <p style="margin:0;">请上传原件照片或扫描件，或者复印件加盖企业公章后的扫描件</p>
                            <p style="margin:0;"> 支持.jpg .jpeg .bmp .gif .png格式照片，大小不超过2M。</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="personal-msg">
                <h3>个人信息</h3>
                <div class="developer-name">
                    <span>联系人姓名：</span>
                    <input type="text" name="company.developerName" id="name" datatype="*"/>
                </div>
                <p class="validTip" style="left:277px;bottom:337px;"></p>
                <div>
                    <span>手机号码：</span>
                    <input type="text" name="company.developerPhone" datatype="m" errormsg="请输入正确的手机号码" id="phone"/>
                </div>
                <p class="validTip" style="left:277px;bottom:238px;"></p>
                <div class="security-code">
                    <span>短信验证码：</span>
                    <input type="text" placeholder="验证码为6位数字" datatype="n6-6" errormsg="验证码错误" name="codeInput"
                           id="codeInput"/>
                    <input type="button" onclick="sendCode(this)" value="免费获取验证码" id="getCode">
                    <%--<button  id="getCode" onclick="sendCode(this)" value="免费获取验证码"></button>--%>
                    <input type="hidden" name="usernameEmail" value="${username}">
                    <input type="hidden" name="loginUserId" value="${loginUserId}">
                </div>
               <%-- <p class="validTip" style="left:277px;bottom:123px;"></p>--%>
            </div>
            <div class="submit">
                <button type="button" id="tijiao" onclick="saveinfo()">成为开发者</button>
            </div>
        </form>
    </section>
</main>
<script>
    $('.validform').Validform({
        tiptype: 2,
        datatype: {"sfz": /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/}
    });
</script>

<!--网页信息-->
<%@ include file="../common/footer.jsp" %>
</body>
</html>