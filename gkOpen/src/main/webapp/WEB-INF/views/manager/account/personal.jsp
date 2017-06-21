<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ include file="../../login/login.jsp"%>
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

    <script src="${ctx}/static/open/uploadfile.js"></script>
    <link href="${ctx}/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${ctx}/qiniu/bower_components/bootstrap/dist/css/bootstrap.css">
    <%--<link rel="stylesheet" href="${ctx}/qiniu/styles/main.css">--%>
    <link rel="stylesheet" href="${ctx}/qiniu/styles/highlight.css">
    <script type="application/javascript" src="${ctx}/static/open/register.js"></script>
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

    <%--表单验证--%>
</head>
<body>
<!--导航栏-->
<!--开放文档-->
<style>

    .col-xs-9>h1{font-weight:500;}
    .col-xs-9>h1 a{font-size:12px;color:#54ab37;text-decoration: none;line-height: 37px;}
    .col-xs-9>div{padding-top:24px !important;margin:0 !important;border-bottom: 1px solid #ddd}
    .col-xs-9 div>p{font-size:14px !important;text-indent:0em !important;margin-bottom:20px;}
    .col-xs-9 div>p span:last-child{color:#333;}
    .col-xs-9 div>p span:first-child{color:#888;text-align: right;width:18%;margin-right:30px;}
    .col-xs-9 div>p img{vertical-align:top;}
    .col-xs-9 div>p>input[type=text]{padding-left:3px;border:2px solid #ddd;width:300px;height:44px;border-radius:3px;outline: none;}
    .col-xs-9 div>p>input[type=text]:focus{border-color: #54AB37}
    .col-xs-9 div>p>select{width:97px;height:44px;border:2px solid #ddd;border-radius:3px;}
    #getCode{width:130px;height:44px;border:2px solid #54AB37;cursor: pointer;text-align: center;color:#54AB37;border-radius:3px;}
    button{
        width: 110px;
        height: 40px;
        border: 1px solid #54ab37;
        background: #54ab37;
        font-size: 16px;
        color: #fff;
        border-radius: 3px;
        outline: none;
    }
    #document-content section.col-xs-9 > div p{margin:0;margin-bottom:20px;}
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp"%>
        <section class="col-xs-9">
            <c:if test="${openUser.status == 0}">
                <h1>开发者资料 <a href="#" class="rl">未提交审核</a></h1>
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
            <%--<c:if test="${openUser.status == 4}">--%>
                <%--<h1>开发者资料 <a href="#" class="rl">请完善资料</a></h1>--%>
            <%--</c:if>--%>
            <c:if test="${openUser.status == 5}">
                <h1>开发者资料 <a href="#" class="rl">修改待审核</a></h1>
            </c:if>
            <div>
                <p style="margin: auto;margin-bottom: 20px;">
                    <span>开发者类型:</span>
                    <span>个人</span>
                </p>
                <p>
                    <span>登录邮箱:</span>
                    <span id="usernameEmail">${sessionScope.openUser.username }</span>
                </p>
            </div>
            <div style="padding-top:0;">
                <p><span style="text-align: left; font-size: 16px; color: #333;">个人信息</span></p>
                <input type="hidden" id="domain" value="http://file3.ckmooc.com/">
                <input type="hidden" id="uptoken_url" value="<%=basepath+"/file/getuptoken" %>">
            </div>
            <div>
                <form action="${ctx}/manager/updateInfo" method="post" id="submitFormPersonal" data-ajax='false'>
                    <div>
                        <p>
                            <span>姓名:</span>
                            <span class="changePersonalPersonal">${personal.name}</span>
                        </p>
                        <p>
                            <span>身份证号:</span>
                            <span class="changePersonal">${personal.identityCard}</span>
                        </p>

                        <p style="overflow:hidden;">
                            <span style="float:left;">身份证照片或扫描件:</span>
                            <span style="float:left;">
                            <img style="width:100px;height:100px;border:1px solid #ddd;" alt="" id="wm8" width="100%" height="100%" src="${accessories.identityPhoto}" />
                        </span>
                        </p>
                    </div>
                    <div>
                        <p>
                            <span>手机号码:</span>
                            <span class="changePersonaladdA">${personal.contactsPhone}</span>
                            <input type="hidden" value="${personal.contactsPhone}" class="forUpdatePersonal" name="personal.contactsPhone" id="phone"/>
                            <span>
                            <span></span>
                            <input type="button" style="display: none" class="forUpdatePersonal"
                                   onclick="sendCode(this)" value="免费获取验证码" id="getCode" width="180px" height="50px">
                        </span>
                            <%--<span>短信验证码</span>--%>
                        </p>
                        <p>
                            <span class="changePersonal"></span>
                            <input type="hidden"  placeholder="验证码为6位数字" id="codeInput"
                                   value="" datatype="n6-6" errormsg="验证码错误" class="forUpdatePersonal" />
                        </p>
                    </div>
                    <div id="foreAdress">
                        <p>
                            <span>联系地址:</span>
                            <span class="changePersonaladdA">${personal.address}</span>
                            <input type="hidden" value="${personal.address}" class="forUpdatePersonal" name="personal.address" id="hiddenAdress"/>
                        </p>
                    </div>
                    <%--<div style="display: none" class="forUpdatePersonalDiv">--%>
                        <%--<p>--%>
                            <%--<span>联系地址：</span>--%>
                            <%--<select id="cmbProvince" name="cmbProvince"></select>--%>
                            <%--<select id="cmbCity" name="cmbCity"></select>--%>
                            <%--<select id="cmbArea" name="cmbArea"></select>--%>
                        <%--</p>--%>
                        <%--<script type="text/javascript">--%>
                            <%--addressInit('cmbProvince', 'cmbCity', 'cmbArea');--%>
                        <%--</script>--%>
                        <%--<p>--%>
                            <%--<span></span>--%>
                            <%--<input type="text" name="personal.address" class="easyui-validatebox textbox"  data-options="required:true"  value="${personal.address}" id="address"/>--%>
                        <%--</p>--%>
                    <%--</div>--%>

                    <div>
                        <p>
                            <span>单位名称:</span>
                            <span class="changePersonal">${personal.companyName}</span>
                        </p>
                    </div>
                    <div>
                        <p style="overflow:hidden;">
                            <span style="float:left;">作品截图:</span>
                            <%--<span class="img"></span>--%>
                            <span style="float:left;">
                            <img style="width:100px;height:100px;border:1px solid #ddd;" alt="" id="wm9" width="100%" height="100%" src="${accessories.worksScan}" />
                        </span>
                        </p>
                    </div>
                    <div>
                        <p style="overflow:hidden;">
                            <span style="float:left;">开发资质证书:</span>
                            <span style="float:left;">
                            <img style="width:100px;height:100px;border:1px solid #ddd;" alt="" id="wm10" width="100%" height="100%" src="${accessories.qualification}" />
                        </span>
                        </p>
                    </div>
                </form>
            </div>
            <p style="text-align: center; padding: 30px 0 15px 0;">
                <c:if test="${openUser.status == 3||openUser.status==2}">
                    <button onclick="spanHideAndInputShow()" id="btnForUpdate">修&nbsp;改</button>
                </c:if>
                <button id="btnForSave" style="display: none;margin-right: 10px;" onclick="saveforUpdatePersonalInfo()" type="submit">提&nbsp;交</button>
                <button  style="display: none;margin-left: 10px;" id="btnForCancel" onclick="window.location.href='${ctx}/manager/basic'">取消修改</button>
            </p>
            <script>
                $(function () {
                    var  openUserStatus ='${openUser.status}';
                    if (openUserStatus==0 || openUserStatus==5){
                        $("#btnforUpdatePersonal").hide();
                    }
                    var  personalAddress = $("#cmbProvince").val();
                    if("${personalAddress}".indexOf(personalAddress)){
                        $("#cmbProvince").attr("selected",true);
                    }
                })
                function spanHideAndInputShow() {
                    $(".changePersonaladdA").hide();
                    $(".forUpdatePersonal").attr("type","text");
                    $("#btnforUpdatePersonal").hide();
                    $("#btnForPersonalSave").show();
                    $('.col-md-12').show();
                    $('#getCode').css('display','block');
//                    $('.forUpdatePersonalDiv').css('display','block');
                    $('#codeInput').show();
//                    $('#foreAdress').hide();
                    $("#btnForCancel").show();
                    $("#btnForUpdate").hide();
                    $("#btnForSave").show();
                }

                function  saveforUpdatePersonalInfo() {
//                    var address = $("#cmbProvince").val()+$("#cmbCity").val()
//                                    +$("#cmbArea").val()+$("#address").val();
//                    $("#hiddenAdress").val(address);
                    var username = document.getElementById("usernameEmail").innerHTML;
                    var code = $("#codeInput").val();
                    $.post(postPath + "/register/judgeCode", {
                                code: code,
                                username:username
                            }, function (data) {
                                if (data.code == 0) {
                                   /* webToast("提交成功请耐心等待审核","top",2300);*/
                                    setTimeout(function () {
                                                $("#submitFormPersonal").submit();
                                            },
                                            2300)
                                } else {
                                    webToast(data.msg,"top",2300);
                                }
                            }
                    );
                }
                if('${flush}' == "flush") {
                    $("#basic").addClass('active');
                }
            </script>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>
</body>
</html>