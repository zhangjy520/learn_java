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
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css">
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/another-js/Validform_v5.3_min.js"></script>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/open/uploadfile.js"></script>
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/js/respond.min.js"></script>
    <![endif]-->
    <link href="${ctx}/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${ctx}/qiniu/bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/static/css/easyui.css">
    <link rel="stylesheet" href="${ctx}/qiniu/styles/highlight.css">
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/moxie.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/plupload.dev.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/bower_components/plupload/js/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/scripts/ui.js"></script>
    <script type="text/javascript" src="${ctx}/qiniu/src/qiniu.js"></script>

    <script type="text/javascript" src="${ctx}/qiniu/scripts/highlight.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/alertPopShow.js"></script>


    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/drag.js"></script>
    <script src="${ctx}/static/another-js/tinymce.min.js"></script>
</head>
<body style="position: relative;">
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

    #pickfiles13 :hover {
        background: red;
    }

    input[type="checkbox"] {
        margin: 0 5px 2px 0;
        vertical-align: middle;
    }

    .removeBtn {
        display: block;
        width: 15px;
        height: 15px;
        line-height: 15px;
        border-radius: 50%;
        background: red;
        text-align: center;
        position: absolute;
        right: -5px;
        top: -5px;
        z-index: 999;
        cursor: pointer;
        color: white;
        font-size: 12px;
    }
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp" %>
        <section class="col-xs-9" style="position: static">
            <section id="app2" class="validform" style="position: static"><p>提示：当前信息都为必填，请注意填写</p>
                <h3>应用信息</h3>
                <ul>
                    <li><span style="margin-top:9px;">应用名称:</span>
                        <input type="text" name="app.name" id="appName">
                        <i>名称不能为空且不能大于6个字</i>
                    </li>
                    <li><span style="margin-top:9px;">应用版本:</span>
                        <input type="text" id="version" name="app.demoAccount"/>
                        <i>按此格式填写，如V1.0.0</i>
                    </li>
                    <li><span>应用简介:</span>
                        <textarea cols="53" rows="10" name="app.appAbstruct" style="resize: none;"
                                  id="appAbstruct"></textarea>
                        <i>请填写应用介绍，审核通过后将在应用商店该应用详情中体现，便于师生了解该服务概况，不超过500个字</i>
                    </li>
                    <li><span id="multifySpan13">应用图标:</span>
                        <input type="hidden" id="domain" value="http://file3.ckmooc.com/">
                        <input type="hidden" id="uptoken_url" value="<%=basepath+"/file/getuptoken" %>">
                        <div>
                            <input type="hidden" name="app.logo" id="tosql3"/>
                            <img alt="" id="wm13" width="100px" height="100px" src=""/>
                            <div class="col-md-12">
                                <div id="container13" style="margin: 23px 0px 23px -15px;">
                                    <a class="btn btn-default btn-lg " id="pickfiles13" href="#">
                                        <i class="glyphicon glyphicon-plus"></i>
                                        <span id="pickfile">选择文件</span>
                                    </a>
                                </div>
                            </div>
                            <i>请上传图标，支持jgp，jpeg，png格式的图片，宽100px*高100px，大小不超过500k</i>
                        </div>
                    </li>
                    <li>
                        <span style="margin-top:9px;">应用类别:</span>
                        <select id="myselect" name="app.category" value="">
                            <option value="0">教学教务</option>
                            <option value="1">互动空间</option>
                        </select>
                        <i style="margin-top:9px;">请选择应用分类</i></li>
                    <li>
                        <span>目标用户:</span>
                        <div>
                            <label for="u1"><input type="checkbox" name="app" id="u1" value="教师" checked>教师</label>
                            <label for="u2" style="margin:0 116px;"><input type="checkbox" value="学生" name="app"
                                                                           id="u2">学生</label>
                            <label for="u3"><input type="checkbox" name="app" value="家长" id="u3">家长</label>
                        </div>
                        <i>请选择目标用户</i>
                    </li>
                    <li>
                        <span>是否免费:</span>
                        <div>
                            <label for="c1"><input type="radio" name="app.isFree" value="1" id="c1" checked>是</label>
                            <label for="c2" style="margin-left:131px;">
                                <input type="radio" value="0" name="app.isFree" id="c2">否
                            </label>
                        </div>
                        <i>请选择是否需要付费</i></li>
                    <li>
                        <span>应用级别:</span>
                        <div>
                            <label for="c1"><input type="radio" name="app.rank" value="0" id="rank1" checked>区级</label>
                            <label for="c2" style="margin-left:117px;">
                                <input type="radio" value="1" name="app.rank" id="rank2">校级
                            </label>
                        </div>
                        <i>请选择应用级别</i>
                    </li>
                    <li>
                        <span class="multifySpan2">应用截图:</span>

                        <ul class="multifyUl" style="float: left;width: 500px;">
                        </ul>

                        <div class="no-img" style="float: left !important;">
                            <input type="hidden" name="app.logo" id="tosql4"/>
                            <img alt="" id="wm00" width="100px" height="100px" src=""/>
                            <div class="col-md-12" style="position:static;">
                                <div id="container14" style="margin: 23px 0px 0px -15px;">
                                    <a class="btn btn-default btn-lg " id="pickfiles14">
                                        <i class="glyphicon glyphicon-plus"></i>
                                        <span>选择文件</span>
                                    </a>
                                </div>
                            </div>
                            <input type="hidden" class="urls">
                        </div>
                    </li>
                    <li>
                        <span style="margin-top:9px;">演示URL:</span>
                        <input type="text" id="demoUrl" datatype="url" errormsg="URL地址错误" name="app.appUrl"/>
                        <i style="margin-top:9px;">演示地址在管理员审核时使用</i>
                    </li>
                    <li class="validTip" style="left:71px;bottom:150px;"></li>
                    <li><span style="margin-top:9px;">演示账号:</span>
                        <input type="text" id="demoAccount" name="app.demoAccount"/>
                        <i>请根据使用群体填写，如教师：用户名/密码、家长：用户名/密码</i>
                    </li>

                </ul>
                <footer>
                    <button class="submit" onclick="saveOrSubmit()">提交审核</button>
                </footer>
            </section>
        </section>
    </div>
</main>
<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>
<script>
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

    function rmPic(obj) {
        $(obj).parent().parent("li").remove();
        changeP();
    }
    function changeP() {
        $(".item").each(function (a, b) {
            $(".item").eq(a).attr("index", a);
            var L = $(b).parent().offset().left;
            var T = $(b).parent().offset().top;
            $(".item").eq(a).css({
                "left": L,
                "top": T
            })
        })
    }
    function saveOrSubmit() {
        var appName = $("#appName").val();
        if (appName == null || appName == "" || appName.length > 6) {
            webToast("名称不能为空且不能大于6个字", "top", 3000);
        }
        var appAbstruct = tinyMCE.activeEditor.getContent();
        if (appAbstruct == null || appAbstruct == "" || appAbstruct.length > 500) {
            webToast("介绍不能为空且不能超过500字", "top", 3000);
        }
        var inputs = $("input[name='app']:checked");

        var targetUser="";
        inputs.each(function (i) {
            if(i=0){
                targetUser = $(this).val();
            }else {
                targetUser += ',' + $(this).val();
            }
        })
        var arrindex = [];
        $(".item").each(function (i) {
            arrindex[i] = $($(".item")[i]).attr("index");
        });
        var arrsrc = [];
        $(".multifyImg2").each(function (i) {
            arrsrc[i] = $($(".multifyImg2")[i]).attr("src");
        });
        if (arrsrc == null || arrsrc.length == 0) {
            webToast("请上传应用截图", "top", 3000);
        }
        var logo = $("#tosql3").val();
        if (logo == null || logo == "") {
            webToast("请上传logo图片", "top", 3000);
        }
        var version = $("#version").val();
        if (version == null || version == "") {
            webToast("版本号不能为空", "top", 3000);
        }
        var myselectCategory = $('#myselect option:selected').val();
        if (myselectCategory == null || myselectCategory == "") {
            webToast("类别不能为空", "top", 3000);
        }

        var isFree = $("input[name='app.isFree']:checked").val();
        if (isFree == null || isFree == "") {
            webToast("是否免费不能为空", "top", 3000);
        }


        var rank = $("input[name='app.rank']:checked").val();
        if (rank == null || rank == "") {
            webToast("级别不能为空", "top", 3000);
        }


        var multifyInput2 = $(".urls").val();

        var demoUrl = $("#demoUrl").val();
        var demoAccount = $("#demoAccount").val();
        $.post(postPath + "/app/save", {
            name: appName,
            appRank: rank,
            appAbstruct: appAbstruct,
            logo: logo,
            category: myselectCategory,
            targetUser: targetUser,
            isFree: isFree,
//            multifyInput2: multifyInput2,
            appUrl: demoUrl,
            demoAccount: demoAccount,
            version: version,
            arrsrc: arrsrc.toString(),
            arrindex: arrindex.toString()
        }, function (data) {
            if (data.code == 0) {
                webToast(data.msg, "top", 5000);
                window.location.href = postPath + "/manager/index";
            } else {
                webToast(data.msg, "top", 5000);
            }
        });
    }
    tinymce.init({
        selector: 'textarea',
        height: 120,
        width: 400,
        language: 'zh_CN'
    })
    //验证字符长度
    $('#abbreviation').blur(function () {
        if (this.value != '' && this.value.length < 6) {
            layer.msg('长度不能小于6个字符');
        }
    })
</script>
</body>
</html>