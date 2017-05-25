<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>
<html>
<head>
    <title>个人信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#userPassWordBtn").click(function () {
                top.layer.open({
                    type: 2,
                    area: ['600px', '350px'],
                    title: "修改密码",
                    content: "${ctx}/sys/user/modifyPwd",
                    btn: ['确定', '关闭'],
                    yes: function (index, layero) {
                        var body = top.layer.getChildFrame('body', index);
                        var inputForm = body.find('#inputForm');
                        var btn = body.find('#btnSubmit');
                        var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe
                        inputForm.attr("target", top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
                        inputForm.validate({
                            rules: {},
                            messages: {
                                confirmNewPassword: {equalTo: "输入与上面相同的密码"}
                            },
                            submitHandler: function (form) {
                                loading('正在提交，请稍等...');
                                form.submit();

                            },
                            errorContainer: "#messageBox",
                            errorPlacement: function (error, element) {
                                $("#messageBox").text("输入有误，请先更正。");
                                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                                    error.appendTo(element.parent().parent());
                                } else {
                                    error.insertAfter(element);
                                }
                            }
                        });
                        if (inputForm.valid()) {
                            loading("正在提交，请稍等...");
                            inputForm.submit();
                            top.layer.close(index);//关闭对话框。
                        } else {
                            return;
                        }


                    },
                    cancel: function (index) {
                    }
                });
            });

            $("#userInfoBtn").click(function () {
                top.layer.open({
                    type: 2,
                    area: ['600px', '500px'],
                    title: "个人信息编辑",
                    content: "${ctx}/sys/user/infoEdit",
                    btn: ['确定', '关闭'],
                    yes: function (index, layero) {
                        var body = top.layer.getChildFrame('body', index);
                        var inputForm = body.find('#inputForm');
                        var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe
                        inputForm.attr("target", top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
                        inputForm.validate();
                        if (inputForm.valid()) {
                            loading("正在提交，请稍等...");
                            inputForm.submit();
                        } else {
                            return;
                        }

                        top.layer.close(index);//关闭对话框。

                    },
                    cancel: function (index) {
                    }
                });
            });
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row animated fadeInRight">
        <sys:message hideType="1" content="${message}"/>
        <div class="col-sm-5">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>个人资料</h5>
                    <div class="ibox-tools">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            编辑资料/修改密码<i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a id="userInfoBtn" data-toggle="modal" data-target="#register">编辑资料</a>
                            </li>
                            <li><a id="userPassWordBtn" data-toggle="modal" data-target="#register">修改密码</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-8">
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <tbody>
                                    <tr>
                                        <td><strong>姓名</strong></td>
                                        <td>${user.name}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>邮箱</strong></td>
                                        <td>${user.email}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>手机</strong></td>
                                        <td>${user.mobile}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>电话</strong></td>
                                        <td>${user.phone}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>备注</strong></td>
                                        <td>${user.remarks}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <strong>上次登录</strong>
                    时间：<fmt:formatDate value="${user.oldLoginDate}" type="both" dateStyle="full"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>