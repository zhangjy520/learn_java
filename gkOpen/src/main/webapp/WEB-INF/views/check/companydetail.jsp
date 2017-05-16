<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../login/login.jsp" %>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>审核管理</title>
    <link rel="stylesheet" href="${ctx}/static/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <script src="${ctx}/static/another-js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
    <style>
        aside{width:130px;}
        main{padding-bottom:80px;}
        #check-detail-content .massages li aside{width:165px;}
    </style>
</head>
<body>
<%--<div class="container-fluid">
    <nav class="container">
        <a href="${ctx}/"><h3><img src="${ctx}/static/images/logo-logo.png" alt=""/></h3></a>
        <ul>
            <li><a href="${ctx}/">首页</a></li>
            <li><a href="${ctx}/document/index">开发文档</a></li>
            <li><a href="${ctx}/document/techsupport">技术支持</a></li>
        </ul>
        <c:if test="${sessionScope.openUser == null }">
            <div>
                <span class="login">登录</span>
                <span class="regist" onclick="window.location.replace('${ctx}/register/index')">开发者注册</span>
            </div>
        </c:if>

        <c:if test="${sessionScope.openUser != null }">
            <div>
                <span style="width:auto;border:none;"
                      onclick="window.location.href = '${ctx}/manager/index'">${sessionScope.openUser.username}</span>
                <span id="logOut" onclick="window.location.href='${ctx}/doLogout'"> 退出登录</span>
                <span onclick="window.location.href='${ctx}/admin/index'">管理中心</span>
            </div>
        </c:if>
    </nav>
</div>--%>
<%@ include file="../common/admin/menu.jsp" %>
<main id="check-detail" style="padding:0;">
    <div id="aside">
        <ul>
            <li data="content1" class="active">企业信息</li>
            <li data="content2">法人信息</li>
            <li data="content3">个人信息</li>
        </ul>
    </div>
    <div id="check-detail-content">
        <section id="content1">
            <ul class="massages massages1">
                <li>
                    <aside>
                        <span>企业名称:</span>
                    </aside>
                    <div>
                        <span>${companyInfo.company.businessName}</span>
                        <span id="userId" hidden>${userId}</span>
                    </div>
                </li>
                <li>
                    <aside><span>企业地址:</span></aside>
                    <div>
                        <span>${companyInfo.company.address}</span>
                    </div>
                </li>
                <li>
                    <aside><span>企业电话:</span></aside>
                    <div>
                        <span>${companyInfo.company.companyPhone}</span>
                    </div>
                </li>
                <li>
                    <aside><span>营业执照注册号:</span></aside>
                    <div>
                        <span>${companyInfo.company.licenceNum}</span>
                    </div>
                </li>
                <li>
                    <aside><span>营业执照所在地:</span></aside>
                    <div>
                        <span>${companyInfo.company.licenceSite}</span>
                    </div>
                </li>
                <li>
                    <aside><span>注册资本:</span></aside>
                    <div><span>${companyInfo.company.capital}万元</span></div>
                </li>
                <li>
                    <aside><span>营业执照副本:</span></aside>
                    <div>
                        <ul>
                            <li style="width:100px;height:100px;border:1px solid #ddd;"><a onclick="window.open('${companyInfo.accessories.licenceScan}')"><img style="width:100%;height:100%;" src="${companyInfo.accessories.licenceScan}" alt=""></a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </section>
        <!--<h3>开发者信息</h3>-->
        <section id="content3">
            <%--<c:if test="${empty appInfo.personal}">--%>
                <%--<ul class="massages">--%>
                    <%--<li>--%>
                        <%--<aside>--%>
                            <%--<span>开发者:</span>--%>
                        <%--</aside>--%>
                        <%--<div>--%>
                            <%--<span>${appInfo.company.businessName}</span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<aside><span>负责人:</span></aside>--%>
                        <%--<div>--%>
                            <%--<span>${appInfo.company.developerName}</span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<aside><span>联系电话:</span></aside>--%>
                        <%--<div><span>${appInfo.company.developerPhone}</span></div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</c:if>--%>
            <%--<c:if test="${empty appInfo.company}">--%>
                <ul class="massages">
                    <li>
                        <aside>
                            <span>开发者姓名:</span>
                        </aside>
                        <div>
                            <span>${companyInfo.company.developerName}</span>
                        </div>
                    </li>
                    <li>
                        <aside><span>手机号码:</span></aside>
                        <div>
                            <span>${companyInfo.company.developerPhone}</span>
                        </div>
                    </li>
                </ul>
            <%--</c:if>--%>
        </section>
        <section id="content2">
            <ul class="massages">
                <li>
                    <aside><span>法人姓名:</span></aside>
                    <div>
                        <span>${companyInfo.company.corporateName}</span>
                    </div>
                </li>
                <li>
                    <aside><span>身份证号码:</span></aside>
                    <div>
                        <span>${companyInfo.company.corporateIdentity}</span>
                    </div>
                </li>
                <li>
                    <aside><span>身份证照片或扫描件:</span></aside>
                    <div>
                        <ul>
                            <li style="width:100px;height:100px;border:1px solid #ddd;"><a onclick="window.open('${companyInfo.accessories.identityPhoto}')"><img src="${companyInfo.accessories.identityPhoto}" alt=""></a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </section>
    </div>
</main>
<c:if test="${mark!=1}">
    <footer>
        <button class="check-btn1" onclick="window.close()">返回</button>
        <button class="check-btn2">不通过</button>
        <button class="check-btn3">通过</button>
    </footer>
    </c:if>
    <c:if test="${mark==1}">
        <footer>
                <c:if test="${!empty message}">
                    <h3>审核不通过原因:</h3>
                    <div>
                        <span>${message}</span>
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                </c:if>
                <button class="check-btn1" onclick="window.location.href='${ctx}/admin/index?show=user'">返回</button>
        </footer>
    </c:if>
</main>

<script>
    $('.check-btn3').click(function(){
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn:['确认','取消'],
            area: ['380px','200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定通过该用户/企业的审核？</p>',
            move:false,
            btn1:function(){
                var userId = $("#userId").text();
                $.post("${ctx}/check/user/Pass",{
                    userId:userId
                }, function (data) {
                    webToast(data.data,"top",1300);
                    setTimeout(function () {
                                window.opener.location.reload();
                                window.close();
                            },
                            1300)
                });
            }
        })
    })
    $('.check-btn2').click(function(){
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn:['确认','取消'],
            area: ['380px','310px'],
            content: '<textarea id="textRemark" style="border-radius:5px;padding:5px;outline: none;" cols="45" rows="11" placeholder="请输入不通过的原因"></textarea>',
            move:false,
            btn1:function(){
                var userId = $("#userId").text();
                var textarea = $("#textRemark").val()
                $.post("${ctx}/check/user/noPass",{
                    userId:userId,
                    text:textarea
                }, function (data) {
                    webToast(data.data,"top",1300);
                    setTimeout(function () {
                                window.opener.location.reload();
                                window.close();
                            },
                            1300)
                });
            }
        })
    })
    $('#aside li').click(function () {
        var data = $(this).attr('data');
        var content = $('#check-detail-content section');
        content.map(function (index, key) {
            console.log(key)
            if (data == key.id) {
                $(key).show();
                $(key).siblings().hide();
            }
        })
        $(this).addClass('active').siblings().removeClass('active')
    })
</script>
</body>
</html>