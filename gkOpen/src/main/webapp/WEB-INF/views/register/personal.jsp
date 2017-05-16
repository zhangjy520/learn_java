<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ include file="../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<%
    response.setHeader("Access-Control-Allow-Origin","*");
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <script src="${ctx}/static/js/h5upload.js"></script>
    <script src="${ctx}/static/open/uploadfile.js"></script>
    <script src="${ctx}/static/another-js/Validform_v5.3_min.js"></script>
    <link href="${ctx}/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${ctx}/qiniu/bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/qiniu/styles/main.css">
    <link rel="stylesheet" href="${ctx}/qiniu/styles/highlight.css">
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
    <script src="${ctx}/static/js/action.js"></script>
</head>
<body>
<style>
    body{background:#fff  !important;}
    #register-process>div span:nth-child(2):before{
        background-position: -46px 0;
    }
    #register-process>div span:nth-child(3):before{
        background-position: -92px 0;
    }
    #register-process>div{margin:0;padding:0 100px;border:1px solid #ddd;border-top:none;}
    #register-process>h3{margin-bottom:0;}
    #getCode{    margin-left: 13px;
        width: 145px;
        height: 50px;
        border: 2px solid #54ab37;
        outline: none;
        color: #54ab37;
        background: #fff;
        font-size: 16px;
        border-radius: 3px;
        position: relative;}
    .tooltip{opacity:1 !important;}
    #register-process{margin-bottom: 0;}
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
            <section id="complete-data" style="border-top:none;">
                <div class="register-msg">
                    <input type="hidden" id="domain" value="http://file3.ckmooc.com/">
                    <input type="hidden" id="uptoken_url" value="<%=basepath+"/file/getuptoken" %>">
                    <p>
                        <span>开发者类型：</span>
                        <span class="userType" value="0">个人</span>
                    </p>
                    <p>
                        <span>登录邮箱：</span>
                        <span id="usernameEmail">${username}</span>
                    </p>
                </div>
                <form id="save-detail" action="${ctx}/register/save" method="post">
                    <div class="company-msg validform">
                        <h3 style="color:#333;">个人信息</h3>
                        <div class="developer-name">
                            <span>姓名：</span>${sessionScope.name}
                            <input type="text" name="personal.name" datatype="*" value=" ${sessionScope.personal.name}" id="name" />
                        </div>
                        <p class="validTip" style="left:277px;top:130px;"></p>
                        <div class="company-phone" style="margin-bottom:54px;">
                            <span>身份证号码：</span>${sessionScope.identityCard}
                            <input type="text" name="personal.identityCard" datatype="sfz" errormsg="请输入正确的身份证号" value="${sessionScope.personal.identityCard }"  id="identityCard"/>
                        </div>
                        <p class="validTip" style="left:277px;top:246px;"></p>
                        <div>
                                <div class="license" style="margin-bottom:42px;">
                                    <span style="vertical-align: top;">身份证照片或扫描件：</span>
                                    <div style="display:inline-block;border:1px solid #ddd;">
                                        <input type="hidden" name="accessories.identityPhoto" id="tosql3"/>
                                        <img alt="" id="wm3" width="144px" height="90px" />
                                    </div>
                                    <div class="col-md-12">
                                        <div id="container3" style="margin: 23px 0px 23px 193px;">
                                            <a class="btn btn-default btn-lg " id="pickfiles3" href="#" >
                                                <i class="glyphicon glyphicon-plus"></i>
                                                <span style="width:44px;">选择文件</span>
                                            </a>
                                        </div>
                                    </div>
                                    <div style="margin: 23px 0px 23px 208px;color:#aaa;">
                                        <p>请上传原件照片或扫描件，或者复印件后的扫描件</p>
                                        <p>支持.jpg .jepg .bmp .gif .png格式照片，大小不超过2M</p>
                                    </div>
                                </div>
                        </div>
                        <div>
                            <span>手机号码：</span>
                            <input type="text" name="personal.contactsPhone" datatype="m" errormsg="请输入正确的手机号码" value="${sessionScope.personal.contactsPhone}" id="phone"/>
                        </div>
                        <p class="validTip" style="left:277px;top:633px;"></p>
                        <div class="security-code" style="margin-bottom:64px;">
                            <span>短信验证码</span>
                            <input type="text" placeholder="验证码为6位数字" name="codeInput" id="codeInput" value="" datatype="n6-6" errormsg="验证码错误"/>
                            <input type="button" onclick="sendCode(this)" value="免费获取验证码" id="getCode" width="180px" height="50px">
                            <%--<button  id="getCode" onclick="sendCode(this)"></button>--%>
                            <input type="hidden" name="usernameEmail" value="${username}">
                            <input type="hidden" name="loginUserId" value="${loginUserId}">
                        </div>
                       <%-- <p class="validTip" style="left:277px;top:749px;"></p>--%>

                        <div>
                            <span>联系地址：</span>
                            <select id="cmbProvince" name="cmbProvince"></select>
                            <select id="cmbCity" name="cmbCity"></select>
                            <select id="cmbArea" name="cmbArea"></select>
                            <script type="text/javascript">
                                addressInit('cmbProvince', 'cmbCity', 'cmbArea');
                            </script>
                            <p>
                                <span></span>
                                <input type="text" name="cmbAddress" datatype="*" value="${sessionScope.personal.address}" id="address"/>
                            </p>
                            <p class="validTip" style="left:198px;bottom:-34px;"></p>
                        </div>


                        <div>
                            <span>单位名称：</span>
                            <input type="text" name="personal.companyName" datatype="*" value="${sessionScope.personal.companyName}" id="companyName"/>
                        </div>
                        <p class="validTip" style="left:277px;bottom:722px;"></p>
                        <div>
                            <div class="license" style="margin-bottom:42px;">
                                <span id="multifySpan" style="vertical-align: top;">作品展示：</span>
                              <%--<input type="hidden" name="accessories.worksScan" id="tosql4" class="multifyInput"/>--%>
                                <div style="display:inline-block;border:1px solid #ddd;">
                                    <img alt="" id="wm4" width="144px" height="90px" />
                                    <input type="hidden" name="accessories.worksScan" value="" id="tosql4"/>
                                </div>
                                <div class="col-md-12">
                                    <div id="container4" style="margin: 23px 0px 23px 194px;" >
                                        <a class="btn btn-default btn-lg " id="pickfiles4" href="#" >
                                            <i class="glyphicon glyphicon-plus"></i>

                                            <span style="width:44px;">选择文件</span>
                                        </a>
                                    </div>
                                </div>
                                <div style="margin: 23px 0px 23px 208px;color:#aaa;">
                                    <p style="margin:0;">请上传作品清晰截图</p>
                                    <p style="margin:0;"> 支持.jpg .jpeg .bmp .gif .png格式照片，大小不超过2M。</p>
                                </div>
                            </div>
                        </div>
                        <div>
                                <div class="license" style="margin-bottom:60px;">
                                    <span style="vertical-align: top;">开发者资质证书：</span>
                                    <div style="display:inline-block;border:1px solid #ddd;">
                                        <input type="hidden" name="accessories.qualification" id="tosql5"/>
                                        <img alt="" id="wm5" width="144px" height="90px" />
                                    </div>
                                    <div class="col-md-12">
                                        <div id="container5"  style="margin: 23px 0px 23px 194px;">
                                            <a class="btn btn-default btn-lg " id="pickfiles5" href="#" >
                                                <i class="glyphicon glyphicon-plus"></i>
                                                <span style="width:44px;">选择文件</span>
                                            </a>
                                        </div>
                                    </div>
                                    <div  style="margin: 23px 0px 23px 208px;color:#aaa;">
                                        <p style="margin:0;">请上传证书原件照片或扫描件</p>
                                        <p style="margin:0;">支持.jpg .jpeg .bmp .gif .png格式照片，大小不超过2M。</p>
                                    </div>
                                </div>
                        </div>
                        <div class="submit">
                            <button type="button" id="tijiao" onclick="saveinfo()">成为开发者</button>
                        </div>
                    </div>

                </form>
            </section>
</main>

<script>
    $('.validform').Validform({
        tiptype:2,
        datatype:{"sfz":/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/}
    });
</script>
<!--网页信息-->
<%@ include file="../common/footer.jsp"%>
</body>
</html>
