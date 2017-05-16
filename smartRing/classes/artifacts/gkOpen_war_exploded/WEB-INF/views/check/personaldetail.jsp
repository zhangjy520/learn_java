<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../login/login.jsp" %>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>审核管理</title>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
    <script src="${ctx}/static/js/action.js"></script>

    <style>
        aside{width:130px}
        #check-main{margin-bottom:80px;}
        #check-detail-content .massages li aside{width:165px;}
        #check-detail-content .massages{overflow:hidden;}
        #check-detail-content .massages li:nth-child(2n-1){float:left;width:50%;}
        #check-detail-content .massages li:nth-child(2n){float:right;width:50%;}
        .check-btn1 {
            border: 1px solid #a0adaa;
            background: #a0adaa;
        }
        .check-btn2 {
            border: 1px solid #fd4b4d;
            background: #fd4b4d;
        }
        .check-btn3 {
            border: 1px solid #19be9d;
            background: #19be9d;
        }
    </style>
</head>
<body>
<main id="check-detail"  style="overflow:hidden;height:800px;">

    <div id="aside">
        <ul>
            <%--<li data="content1" class="active">企业信息</li>--%>
            <%--<li data="content2">法人信息</li>--%>
            <li data="content1" class="active">个人信息</li>
        </ul>
    </div>
    <div id="check-detail-content">
        <section id="content1">
            <ul class="massages">
                <li>
                    <aside>
                        <span>开发者类型:</span>
                    </aside>
                    <div>
                        <span>个人</span>
                    </div>
                </li>
                <li>
                    <aside><span>登录邮箱:</span></aside>
                    <div>
                        <span>${username}</span>
                        <span id="userId" hidden>${userId}</span>
                    </div>
                </li>
                <li>
                    <aside><span>姓名:</span></aside>
                    <div>
                        <span>${personalInfo.personal.name}</span>
                    </div>
                </li>
                <li>
                    <aside><span>身份证号码:</span></aside>
                    <div><span>${personalInfo.personal.identityCard}</span></div>
                </li>
                <li>
                    <aside><span>身份证照片或扫描件:</span></aside>
                    <div>
                        <ul>
                            <li  style="width:100px;height:100px;border:1px solid #ddd;"><a onclick="window.open('${personalInfo.accessories.identityPhoto}')"><img style="width:100%;height:100%;" src="${personalInfo.accessories.identityPhoto}" alt=""></a></li>
                        </ul>
                    </div>
                </li>

                <li>
                    <aside><span>手机号码:</span></aside>
                    <div>
                        <span>${personalInfo.personal.contactsPhone}</span>
                    </div>
                </li>
                <li>
                    <aside><span>联系地址:</span></aside>
                    <div>
                        <span>${personalInfo.personal.address}</span>
                    </div>
                </li>
                <li>
                    <aside><span>单位名称:</span></aside>
                    <div>
                        <span>${personalInfo.personal.companyName}</span>
                    </div>
                </li>
                <%-- <li>
                     <aside><span>作品展示地址:</span></aside>
                     <div>
                         <span>${personalInfo.personal.worksHref}</span>
                     </div>
                 </li>--%>
                <li>
                    <aside><span>作品截图:</span></aside>
                    <div>
                        <ul>
                            <li style="width:100px;height:100px;border:1px solid #ddd;"><a onclick="window.open('${personalInfo.accessories.worksScan}')"><img style="width:100%;height:100px;" src="${personalInfo.accessories.worksScan}" alt=""></a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <aside><span>开发者资质证书:</span></aside>
                    <div>
                        <ul>
                            <li style="width:100px;height:100px;border:1px solid #ddd;" ><a onclick="window.open('${personalInfo.accessories.qualification}')"><img style="width:100%;height:100px;" src="${personalInfo.accessories.qualification}" alt=""></a></li>
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
        <button id="notPass" class="check-btn2">不通过</button>
        <button id="Pass" class="check-btn3">通过</button>
    </footer>
</c:if>
<c:if test="${mark==1}">
    <footer>
        <c:if test="${!empty openMessage.text}">
            <h3>审核不通过原因:</h3>
            <div>
                <span>${openMessage.text}</span>
            </div>
        </c:if>
        <button class="check-btn1" onclick="window.location.href='${ctx}/admin/index?show=user'">返回</button>
    </footer>
</c:if>
</body>
</html>
<script>
    $('.check-btn3').click(function(){
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn:['确认','取消'],
            area: ['380px','200px'],
            content: '<p id="textRemark" style="color:#525252;font-size:14px;">您确定通过该用户/企业的审核？</p>',
            move:false,
            btn1:function(){
                var userId = $("#userId").text();
                var textarea = $("#textRemark").val();
                $.post("${ctx}/check/user/Pass",{
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
    $('.check-btn2').click(function(){
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn:['确认','取消'],
            area: ['380px','310px'],
            content: '<textarea style="border-radius:5px;padding:5px;outline: none;" cols="45" rows="11" placeholder="请输入不通过的原因"></textarea>',
            move:false,
            btn1:function(){
                var userId = $("#userId").text();
                $.post("${ctx}/check/user/noPass",{
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
</script>