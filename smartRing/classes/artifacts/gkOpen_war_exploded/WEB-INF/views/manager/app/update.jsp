<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/header.jsp" %>
<%--<%@ include file="../../login/login.jsp" %>--%>
<%@ include file="/base.jsp" %>
<%--<%
   /* response.setHeader("Access-Control-Allow-Origin", "*");*/
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css">
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <%--<script src="${ctx}/static/another-js/tinymce.min.js"></script>--%>
    <script src="${ctx}/static/another-js/Validform_v5.3_min.js"></script>
    <%--<script src="${ctx}/static/another-js/jquery.min.js"></script>--%>
    <%--<script src="${ctx}/static/another-js/layer.js"></script>--%>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/open/uploadfile.js"></script>

    <%--<!--[if lt IE 9]>--%>
    <%--<script src="${ctx}/static/js/html5shiv.min.js"></script>--%>
    <%--<script src="${ctx}/static/js/respond.min.js"></script>--%>
    <%--<![endif]-->--%>

    <link href="${ctx}/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${ctx}/qiniu/bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/static/css/easyui.css">
    <link rel="stylesheet" href="${ctx}/qiniu/styles/main.css">
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
    <script type="text/javascript" src="${ctx}/static/js/drag.js"></script>
    <script src="${ctx}/static/another-js/tinymce.min.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
</head>
<body>
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

    /*
     图片显示的轮播图的样式
   */
    * {
        margin: 0;
        padding: 0;

    }

    a {
        text-decoration: none;
    }

    body {
        padding: 20px;
    }

    #container {
        width: 521px; /*这里600x400是图片的宽高*/
        height: 406px;
        border: 3px solid #333;
        overflow: hidden; /*隐藏溢出的图片，因为图片左浮动，总宽度为4200*/
        position: relative;
    }

    #list {
        width: 3605px; /*这里设置7张图片总宽度*/
        height: 400px;
        position: absolute; /*基于父容器container进行定位*/
        z-index: 1;
    }

    #list span {
        float: left;
    }

    #list img {
        float: left;
        width: 100%;
        height: 100%;
    }

    #buttons {
        position: absolute;
        height: 10px;
        width: 100px;
        z-index: 2; /*按钮在图片的上面*/
        bottom: 20px;
        text-align: center;
        left: 207px;
    }

    #buttons span {
        cursor: pointer;
        /*float: left;*/
        border: 1px solid #fff;
        width: 10px;
        height: 10px;
        border-radius: 50%;
        background: #333;
        margin-right: 5px;
    }

    #buttons .on {
        background: orangered; /*选中的按钮样式*/

    }

    .arrow {
        cursor: pointer;
        display: none; /*左右切换按钮默认先隐藏*/
        line-height: 39px;
        text-align: center;
        font-size: 36px;
        font-weight: bold;
        width: 40px;
        height: 40px;
        position: absolute;
        z-index: 2;
        top: 180px;
        background-color: RGBA(0, 0, 0, .3);
        color: #fff;
    }

    .arrow:hover {
        background-color: RGBA(0, 0, 0, .7);
    }

    #container:hover .arrow {
        display: block; /*当鼠标放上去容器上面就显示左右切换按钮*/
    }

    #prev {
        left: 20px;
    }

    #next {
        right: 20px;
    }


</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp" %>
        <section class="col-xs-9" style="position:static;">
            <%--<form action="${ctx}/save/app" method="post" class="form-save-submit">--%>
            <section id="app2" class="validform" style="position:static;">
                <p>提示：当前信息都为必填，请注意填写</p>
                <h3>应用信息</h3>
                <ul>
                    <li>
                        <span style="margin-top:9px;">应用名称:</span>
                        <input type="text" name="app.name" id="appName" value="${app.name}">
                        <i></i>
                    </li>
                    <li><span style="margin-top:9px;">应用版本:</span>
                        <input type="text" name="app.demoAccount" id="version" value=${app.version} onfocus=this.blur()>
                        <i>不可修改</i>
                    </li>
                    <%--<li>--%>
                    <%--<span style="margin-top:9px;">应用简称:</span>--%>
                    <%--&lt;%&ndash;<p style="display:inline-block;width:410px;margin:9px 0 0 0;">${app.appAbbreviation}</p>&ndash;%&gt;--%>
                    <%--<input type="text" name="app.appAbbreviation" id="abbreviation" value="${app.appAbbreviation}">--%>
                    <%--<i>发布后不可修改，用于大部分的对外显示，不超过六个汉字</i>--%>
                    <%--</li>--%>
                    <li>
                        <span>应用简介:</span>
                        <textarea cols="53" rows="10" name="app.appAbstruct" style="resize: none;border:1px solid #ddd;"
                                  id="appAbstruct" value="${app.appAbstruct}">${app.appAbstruct}</textarea>
                        <i>请填写应用介绍，审核通过后将在应用商店该应用详情中体现，便于师生了解该服务概况，不超过500个字</i>
                    </li>

                    <li>
                        <span id="multifySpan13">应用图标:</span>
                        <input type="hidden" id="domain" value="http://file3.ckmooc.com/">
                        <%-- <input type="hidden" id="uptoken_url" value="<%=basepath+"/file/getuptoken" %>">--%>
                        <input type="hidden" id="uptoken_url" value="${ctx}/file/getuptoken">
                        <div>
                            <input type="hidden" name="app.logo" id="tosql3"/>
                            <img style="border: 1px solid #ddd;" alt="" id="wm13" width="100px" height="100px" src="${app.logo}"/>
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
                        <select id="myselect" name="app.category" value="${app.category}">
                            <option value="0" class="categoryOption1">教学教务</option>
                            <option value="1" class="categoryOption2">互动空间</option>
                        </select>
                        <i style="margin-top:9px;">请选择应用分类</i>
                    </li>
                    <li>
                        <span>目标用户:</span>
                        <div class="targetUserDiv">
                            ${app.targetUser}
                            <%--<label for="u1"><input type="checkbox" name="app" id="u1" value="教师">教师</label>--%>
                            <%--<label for="u2" style="margin:0 116px;"><input type="checkbox" value="学生" name="app" id="u2">学生</label>--%>
                            <%--<label for="u3"><input type="checkbox" name="app" value="家长" id="u3">家长</label>--%>
                        </div>
                        <script>
                            $(function () {
                                var targetUser = '${app.targetUser}';
                                var str = targetUser.split(",");
                                if (str.length == 1) {
                                    $("#u1").attr("checked", true);
                                }
                                if (str.length == 2) {
                                    $("#u1").attr("checked", true);
                                    $("#u2").attr("checked", true);
                                }
                                if (str.length == 3) {
                                    $("#u1").attr("checked", true);
                                    $("#u2").attr("checked", true);
                                    $("#u3").attr("checked", true);
                                }
                                if ('${app.isFree}' == 1) {
                                    $("#c2").attr("checked", true);
                                }
                                if ('${app.isFree}' == 0) {
                                    $("#c1").attr("checked", true);
                                }
                                if ('${app.appRank}' == 0) {
                                    $("#rank1").attr("checked", true);
                                }
                                if ('${app.appRank}' == 1) {
                                    $("#rank2").attr("checked", true);
                                }
                                if ('${app.category}' == 0) {
                                    $(".categoryOption1").attr("selected", "true");

                                }
                                if ('${app.category}' == 1) {
                                    $(".categoryOption2").attr("selected", "true");
                                }
                            })
                        </script>
                        <i>请选择应用分类</i>
                    </li>
                    <li>
                        <span>是否免费:</span>
                        <div>
                            <label for="c1"><input type="radio" name="app.isFree" value="1" id="c1">是</label>
                            <label for="c2" style="margin-left:125px;"><input type="radio" value="0" name="app.isFree"
                                                                              id="c2">否</label>
                            <%--<label for="u3"><input type="radio" name="t-user">教师</label>--%>
                        </div>
                        <i>请选择是否需要付费试用该应用</i>
                    </li>
                    <li>
                        <span>应用级别:</span>
                        <div>
                            <label for="c1"><input type="radio" name="app.appRank" value="0" id="rank1">区级</label>
                            <label for="c2" style="margin-left:111px;"><input type="radio" value="1" name="app.appRank"
                                                                              id="rank2">校级</label>
                            <%--<label for="u3"><input type="radio" name="t-user">教师</label>--%>
                        </div>
                        <i>请选择是否需要付费试用该应用</i>
                    </li>
                    <li>
                        <span class="multifySpan2">应用截图:</span>
                        <ul class="gallery" style="overflow: hidden;width: 350px;">
                            <c:forEach items="${appScreenShotList}" var="screenshot">
                                <li style="float: left;margin: 0 12px 12px 0;" class="updateScreenShotShowLi"><a onclick="window.open('${screenshot}')"><img style="height: 100px; width: 100px;border: 1px solid #ddd;" src="${screenshot}" alt="应用截图"></a></li>
                            </c:forEach>
                        </ul>
                        <ul class="multifyUl" style="float: left;width: 500px;">
                        </ul>
                        <div class="no-img" style="float: left !important; margin-left: 103px;">
                            <input type="hidden" name="app.logo" id="tosql4"/>
                            <div class="col-md-12" style="position:static;">
                                <div id="container14" style="margin: 23px 0px 23px -15px;">
                                    <a class="btn btn-default btn-lg " id="pickfiles14">
                                        <i class="glyphicon glyphicon-plus"></i>
                                        <span>选择文件</span>
                                    </a>
                                </div>
                            </div>
                            <input type="hidden" class="urls">
                            <i>请上传图标，支持jgp，jpeg，png格式的图片，宽100px*高100px，大小不超过500k</i>
                        </div>
                    </li>
                    <li>
                        <span style="margin-top:9px;">演示URL:</span>
                        <input type="text" id="demoUrl" datatype="url" errormsg="URL地址错误" name="app.appUrl"
                               value="${app.appUrl}"/>
                        <i style="margin-top:9px;">演示地址在管理员审核时试用</i>
                    </li>
                    <li class="validTip" style="left:71px;bottom:150px;"></li>
                    <li>
                        <span style="margin-top:9px;">演示账号:</span>
                        <input type="text" id="demoAccount" name="app.demoAccount" value="${app.demoAccount}"/>
                        <i>请根据使用群体填写，如教师：用户名/密码、家长：用户名/密码</i>
                        <input type="hidden" id="id" name="app.demoAccount" value="${app.id}"/>
                    </li>
                </ul>
                <footer>
                    <button class="save" onclick="update()">修改</button>
                </footer>
            </section>
        </section>

    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>
<!--<script src="js/jquery.min.js"></script>-->
<script>
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function update() {
        var id = $("#id").val();
        var appName = $("#appName").val();
        var abbreviation = $("#abbreviation").val();
        var appAbstruct = tinyMCE.activeEditor.getContent();
        var logo = $("#wm13").attr("src");
//        var myselect = $("#myselect").value;
        var myselect = $('#myselect option:selected').val();
        var inputs = $("input[name='app']:checked");
        var arrindex = [];
        $(".item").each(function (i) {
            arrindex[i] = $($(".item")[i]).attr("index");
//            console.log(ind);
        });
        var arrsrc = [];
        $(".multifyImg2").each(function (i) {
            arrsrc[i] = $($(".multifyImg2")[i]).attr("src");
//            console.log(src);
        });
//        var multifyInput2 = $(".urls").val();
        var isFree = $("input[name='app.isFree']:checked").val();
        var rank = $("input[name='app.appRank']:checked").val();
        var demoUrl = $("#demoUrl").val();
        var demoAccount = $("#demoAccount").val();
        $.post(postPath + "/app/update", {
            id: id,
            status: status,
            appName: appName,
            abbreviation: abbreviation,
            appAbstruct: appAbstruct,
            logo: logo,
            myselect: myselect,
//            targetUser: targetUser,
            isFree: isFree,
            rank: rank,
            demoUrl: demoUrl,
            demoAccount: demoAccount,
            arrsrc: arrsrc.toString(),
            arrindex: arrindex.toString()
        }, function (data) {
            if (data.code == 0) {
                window.location.href = postPath + "/" + data.data;
            } else {
                webToast(data.msg, "top", 2300);
            }

        });
    }

    tinymce.init({
        selector: 'textarea',
        height: 120,
        width: 400,
        language: 'zh_CN'
    })
    //表单验证
    $('.validform').Validform({
        tiptype: 2
//        datatype:{"sfz":/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/}
    });
    //验证字符长度
    $('#abbreviation').blur(function () {
        if (this.value != '' && this.value.length < 6) {
            layer.msg('长度不能小于6个字符');
        }
    });



</script>

</body>
</html>