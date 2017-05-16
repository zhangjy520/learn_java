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
    <script src="${ctx}/static/another-js/Validform_v5.3_min.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
</head>
<body>
<!--导航栏-->
<!--开放文档-->
<style>
    .col-xs-9>h1>span{margin-left:10px;font-size:14px;font-weight:500;}
    /*.col-xs-9>div{margin:40px 0 20px 0 !important;}*/
    .col-xs-9>div{margin:30px 0 20px 0 !important;}
    .col-xs-9>div>span{width:100px;margin:0 20px 0 30px !important;font-size:14px !important;color:#525252 !important;text-align:right;}
    .col-xs-9>div>input{width:45%;height:37px;border-radius: 3px;border:2px solid #ddd;outline: none;padding-left:5px;}
    button{
        width:110px;margin:30px 0 30px 155px;
        height:40px;
        border:1px solid #54ab37;
        background: #54ab37;
        font-size:16px;
        color:#fff;
        border-radius:3px;
        outline: none;
    }
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp"%>
        <section class="col-xs-9 validform">
            <h1>密码修改 <span>字母、数字或英文符号，最短6位，区分大小写</span></h1>
            <div>
                <span style="font-size: 14px;color: #525252;">当前密码:</span>
                <input type="password" <%--datatype="*6-20" errormsg='请输入字母、数字或英文符号，最短6位'--%> id="currentPassword"/>
            </div>
            <p class="validTip" style="left:173px;top:140px;"></p>
            <div>
                <span style="font-size: 14px;color: #525252;">新密码:</span>
                <input type="password" datatype="*6-20" errormsg='请输入字母、数字或英文符号，最短6位' id="newPassword" name="newPassword"  />
            </div>
            <p class="validTip" style="left:173px;top:207px;"></p>
            <div>
                <span style="font-size: 14px;color: #525252;">确认新密码:</span>
                <input type="password" recheck="newPassword" datatype="*" errormsg='您两次输入的密码不一致，请重试' id="confirmNewPassword" name="confirmNewPassword"/>
            </div>
            <p class="validTip" style="left:173px;top:274px;"></p>
            <button onclick="uploadPassword()">修&nbsp;改</button>

            <script>
                var strPath = window.document.location.pathname;
                var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
                function uploadPassword() {
                    var currentPassword = $("#currentPassword").val();
                    var newPassword = $("#newPassword").val();
                    var confirmNewPassword = $("#confirmNewPassword").val();
                    $.post(postPath + "/manager/password/update",{
                        currentPassword : currentPassword,
                        newPassword : newPassword,
                        confirmNewPassword : confirmNewPassword
                    },function (data) {
                        if (data.code == 0) {
                            window.location.href=postPath + "/"+data.data;
                        }else {
//                            alert();
                            webToast(data.msg, "top", 2300);
                        }
                    });
                }

                //表单验证
                $('.validform').Validform({
                    tiptype:2,
//                    datatyp:{"zh6-20":/^[\u4E00-\u9FA5\uf900-\ufa2d]{6,20}$/}
                });
            </script>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>
</body>
</html>