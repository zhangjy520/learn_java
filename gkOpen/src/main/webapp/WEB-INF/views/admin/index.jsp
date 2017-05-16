<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<%@ include file="../common/admin/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/alert.css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
    <style>
        /*  [id*=check] table span {
              display: none;
          }*/

        [id*=check] table {
            display: none;
        }
        #check1>P{
            margin-top: 20px!important;
        }


    </style>
</head>
<body>
<%@ include file="../common/admin/menu.jsp" %>
<main id="main-tab">
    <ul class="clear tab-menu">
        <li id="app" data="check1" style="padding: 0 20px;">应用审核</li>
        <li id="user" data="check2" style="padding: 0 20px;">用户审核</li>
    </ul>

    <div id="check1">
        <p>
            <label onclick="window.location.href ='${ctx}/admin/index'" for="c1">
                <input type="radio" name="check1" id="c1"/>全部应用</label>

            <label onclick="window.location.href ='${ctx}/admin/index?appStatus=AUDITING'" for="c2">
                <input type="radio" name="check1" id="c2"/>待审核</label>

            <label onclick="window.location.href ='${ctx}/admin/index?appStatus=AUDIT_SUCCESS'" for="c3">
                <input type="radio" name="check1" id="c3"/>已通过</label>

            <label onclick="window.location.href ='${ctx}/admin/index?appStatus=AUDIT_FAIL'" for="c4">
                <input type="radio" name="check1" id="c4"/>未通过</label>

            <label onclick="window.location.href ='${ctx}/admin/index?appStatus=FORBIDDEN'" for="c5">
                <input type="radio" name="check1" id="c5"/>禁用</label>
        </p>
        <table>
            <thead>
            <tr>
                <th width="5%">序号</th>
                <th width="8%">应用名称</th>
                <th width="8%">应用分类</th>
                <th width="8%">应用状态</th>
                <th width="9%">开发商</th>
                <th width="8%">负责人</th>
                <th width="15%">联系电话</th>
                <th width="9%">提交时间</th>
                <th width="18%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${appBaseInfoList}" var="appinfo" varStatus="status">
                <tr>
                    <c:if test="${appinfo.userType==0}">
                        <td>${status.count}</td>
                        <td>${appinfo.name}</td>
                        <td>${appinfo.category}</td>

                        <c:if test="${appinfo.checkStatus==1}">
                            <td>未审核</td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==5}">
                            <td>未审核</td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==2}">
                            <td>审核成功</td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==3}">
                            <td>审核失败</td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==4}">
                            <td>禁用</td>
                        </c:if>





                        <td>${appinfo.pCompany}</td>
                        <td>${appinfo.pManage}</td>
                        <td>${appinfo.pPhone}</td>
                        <td>${appinfo.time}</td>

                        <c:if test="${appinfo.checkStatus==1}">
                            <td>
                            <span class="c1 app-c1"
                                  onclick="window.open('${ctx}/check/details/app?id='+'${appinfo.id}')">审核</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                            </td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==5}">
                            <td>
                            <span class="c1 app-c1"
                                  onclick="window.open('${ctx}/check/details/app?id='+'${appinfo.id}')">审核</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                            </td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==2}">
                            <td>
                                <span class="c3 app-c3"
                                      onclick="location.href='${ctx}/check/details/app?id='+'${appinfo.id}'+'&&mark=1&&type=1'">详情</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                                <span data-url ="${ctx}/app/do/disable?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c5 app-c5">禁用</span>
                            </td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==3}">
                            <td>
                                <span class="c3 app-c3"
                                      onclick="location.href='${ctx}/check/details/app?id='+'${appinfo.id}'+'&&mark=1&&type=1'">详情</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                            </td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==4}">
                            <td>
                                <span onclick="location.href='${ctx}/check/details/app?id='+'${appinfo.id}'+'&&mark=1&&type=1'"
                                      class="c3 app-c3">详情</span>
                                <span  data-url="${ctx}/app/do/able?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c2 app-c2">启用</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id} &&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                            </td>
                        </c:if>
                    </c:if>



                    <c:if test="${appinfo.userType==1}">
                        <td>${status.count}</td>
                        <td>${appinfo.name}</td>
                        <td>${appinfo.category}</td>
                        <c:if test="${appinfo.checkStatus==1}">
                            <td>未审核</td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==5}">
                            <td>未审核</td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==2}">
                            <td>审核成功</td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==3}">
                            <td>审核失败</td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==4}">
                            <td>禁用</td>
                        </c:if>
                        <td>${appinfo.cCompany}</td>
                        <td>${appinfo.cManage}</td>
                        <td>${appinfo.cPhone}</td>
                        <td>${appinfo.time}</td>
                        <c:if test="${appinfo.checkStatus==1}">
                            <td>
                            <span class="c1 app-c1"
                                  onclick="window.open('${ctx}/check/details/app?id='+'${appinfo.id}')">审核</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                            </td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==5}">
                            <td>
                            <span class="c1 app-c1"
                                  onclick="window.open('${ctx}/check/details/app?id='+'${appinfo.id}')">审核</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                            </td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==2}">
                            <td>
                                <span class="c3 app-c3"
                                      onclick="location.href='${ctx}/check/details/app?id='+'${appinfo.id}'+'&&mark=1&&type=1&&type=1'">详情</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                                <span data-url ="${ctx}/app/do/disable?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c5 app-c5">禁用</span>
                            </td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==3}">
                            <td>
                                <span class="c3 app-c3"
                                      onclick="location.href='${ctx}/check/details/app?id='+'${appinfo.id}'+'&&mark=1&&type=1'">详情</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                            </td>
                        </c:if>
                        <c:if test="${appinfo.checkStatus==4}">
                            <td>
                                <span onclick="location.href='${ctx}/check/details/app?id='+'${appinfo.id}'+'&&mark=1&&type=1'"
                                      class="c3 app-c3">详情</span>
                                <span data-url="${ctx}/app/do/able?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c2 app-c2">启用</span>
                                <span data-url ="${ctx}/app/do/delete?id=${appinfo.id}&&appPageNum=${appBasePageInfo.pageNum}&&appStatus=${appStatus}" class="c4 app-c4">删除</span>
                            </td>
                        </c:if>

                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <footer class="clear">
            <p>每条显示${appBasePageInfo.pageSize}条，共${appBasePageInfo.pages}页，共${appBasePageInfo.total}条记录</p>
            <div class="fenY"></div>
        </footer>
    </div>
    <div id="check2">
        <p>
            <label onclick="window.location.href ='${ctx}/admin/index?show=user'" for="c11">
                <input type="radio" name="check2" id="c11"/> 全部用户</label>
            <label onclick="window.location.href ='${ctx}/admin/index?userStatus=AUDITING&&show=user'"
                   for="c22"> <input type="radio" name="check2" id="c22"/> 待审核</label>

            <label onclick="window.location.href ='${ctx}/admin/index?userStatus=AUDIT_SUCCESS&&show=user'" for="c33">
                <input type="radio" name="check2" id="c33"/> 已通过</label>

            <label onclick="window.location.href ='${ctx}/admin/index?userStatus=AUDIT_FAIL&&show=user'"
                   for="c44"><input type="radio" name="check2" id="c44"/>未通过</label>

            <label onclick="window.location.href ='${ctx}/admin/index?userStatus=FORBIDDEN&&show=user'"
                   for="c55"> <input type="radio" name="check2" id="c55"/> 禁用</label>
        </p>
        <table>
            <thead>
            <tr>
                <th width="3%">序号</th>
                <th width="8%">开发者姓名</th>
                <th width="8%">开发者类型</th>
                <th width="8%">联系电话</th>
                <th width="9%">注册邮箱</th>
                <th width="9%">提交时间</th>
                <th width="8%">审核状态</th>
                <th width="18%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userBaseInfoList}" var="userinfo" varStatus="status">
                <tr>
                    <c:if test="${userinfo.userType==0}">
                        <td>${status.count}</td>
                        <td>${userinfo.name}</td>
                        <td>个人</td>
                        <td>${userinfo.personalPhone}</td>
                        <td>${userinfo.username}</td>
                        <td>${userinfo.time}</td>
                        <c:if test="${userinfo.checkType==1}">
                            <td>未审核</td>
                            <td>
                            <span onclick="window.open('${ctx}/check/details/user?id='+'${userinfo.id}')"
                                  class="c1 user-c1">审核</span>
                                <span data-url ="${ctx}/user/edit/delete?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c4 user-c4">删除</span>
                            </td>
                        </c:if>

                        <c:if test="${userinfo.checkType==5}">
                            <td>未审核</td>
                            <td>
                            <span onclick="window.open('${ctx}/check/details/user?id='+'${userinfo.id}')"
                                  class="c1 user-c1">审核</span>
                                <span data-url ="${ctx}/user/edit/delete?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c4 user-c4">删除</span>
                            </td>
                        </c:if>

                        <c:if test="${userinfo.checkType==2}">
                            <td>审核成功</td>
                            <td>
                                <span onclick="location.href='${ctx}/check/details/user?id='+'${userinfo.id}'+'&&mark=1&&type=0'"
                                      class="c3 user-c3">详情</span>
                                <span data-url="${ctx}/user/edit/disable?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                       class="c5 user-c5">禁用</span>
                                <span data-url="${ctx}/user/edit/delete?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c4 user-c4">删除</span>
                            </td>
                        </c:if>

                        <c:if test="${userinfo.checkType==3}">
                            <td>审核失败</td>
                            <td>
                                <span onclick="location.href='${ctx}/check/details/user?id='+'${userinfo.id}'+'&&mark=1&&type=0'"
                                      class="c3 user-c3">详情</span>
                                <span data-url ="${ctx}/user/edit/delete?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c4 user-c4">删除</span>
                            </td>
                        </c:if>

                        <c:if test="${userinfo.checkType==4}">
                            <td>禁用</td>
                            <td>
                                <span onclick="location.href='${ctx}/check/details/user?id='+'${userinfo.id}'+'&&mark=1&&type=0'"
                                      class="c3 user-c3">详情</span>

                                <span data-url="${ctx}/user/edit/able?id=+${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c2 user-c2">启用</span>
                            </td>
                        </c:if>
                    </c:if>
                    <c:if test="${userinfo.userType==1}">
                        <td>${status.count}</td>
                        <td>${userinfo.companyName}</td>
                        <td>公司</td>
                        <td>${userinfo.companyPhone}</td>
                        <td>${userinfo.username}</td>
                        <td>${userinfo.time}</td>
                        <c:if test="${userinfo.checkType==1}">
                            <td>未审核</td>
                            <td>
                            <span onclick="window.open('${ctx}/check/details/user?id='+'${userinfo.id}')"
                                  class="c1 user-c1">审核</span>
                                <span data-url ="${ctx}/user/edit/delete?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c4 user-c4">删除</span>
                            </td>
                        </c:if>

                        <c:if test="${userinfo.checkType==5}">
                            <td>未审核</td>
                            <td>
                            <span onclick="window.open('${ctx}/check/details/user?id='+'${userinfo.id}')"
                                  class="c1 user-c1">审核</span>
                                <span data-url ="${ctx}/user/edit/delete?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c4 user-c4">删除</span>
                            </td>
                        </c:if>

                        <c:if test="${userinfo.checkType==2}">
                            <td>审核成功</td>
                            <td>
                                <span onclick="location.href='${ctx}/check/details/user?id='+'${userinfo.id}'+'&&mark=1&&type=0'"
                                      class="c3 user-c3">详情</span>
                                <span data-url ="${ctx}/user/edit/disable?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c5 user-c5">禁用</span>
                                <span data-url ="${ctx}/user/edit/delete?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c4 user-c4">删除</span>
                            </td>
                        </c:if>

                        <c:if test="${userinfo.checkType==3}">
                            <td>审核失败</td>
                            <td>
                                <span onclick="location.href='${ctx}/check/details/user?id='+'${userinfo.id}'+'&&mark=1&&type=0'"
                                      class="c3 user-c3">详情</span>
                                <span data-url ="${ctx}/user/edit/delete?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c4 user-c4">删除</span>
                            </td>
                        </c:if>

                        <c:if test="${userinfo.checkType==4}">
                            <td>禁用</td>
                            <td>
                                <span onclick="location.href='${ctx}/check/details/user?id='+'${userinfo.id}'+'&&mark=1&&type=0'"
                                      class="c3 user-c3">详情</span>
                                <span data-url="${ctx}/user/edit/able?id=${userinfo.id}&&userPageNum=${userBasePageInfo.pageNum}&&show=user&&userStatus=${userStatus}"
                                      class="c2 user-c2">启用</span>
                            </td>
                        </c:if>

                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <footer class="clear">
            <p>每条显示${userBasePageInfo.pageSize}条，共${userBasePageInfo.pages}页，共${userBasePageInfo.total}条记录</p>
            <div class="fenY1"></div>
        </footer>
    </div>
</main>

<script>
    $(".fenY").createPage({
        pageCount: ${appBasePageInfo.pages},
        current: ${appBasePageInfo.pageNum},
        backFn: function (p) {
            window.location.href = "${ctx}/admin/index?appPageNum=" + p + "&&appStatus=${appStatus}";
        }
    });
    $(".fenY1").createPage({
        pageCount: ${userBasePageInfo.pages},
        current: ${userBasePageInfo.pageNum},
        backFn: function (p) {
            window.location.href = "${ctx}/admin/index?pageNum=" + p + "&&show=user&&userStatus=${userStatus}";
        }
    });

    $('.tab-menu li').click(function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        var data = $(this).attr('data');
        var div = $('div[id=' + data + ']');
        $(div).show();
        $(div).siblings('div').hide();
    })


    $(function () {
        var table = $('table');
        for (var i = 0; i < table.length; i++) {
            var td = $(table[i]).find('tbody tr');
            if (td.length == 0) {
                $(table[i]).siblings('footer').before('<span style="font-size:13px;color:#666; margin-top: 15px;">暂无数据</span>')
            }
        }
    })
    $('.app-c4').click(function () {
        var url = $(this).attr('data-url');
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该应用？</p>',
            move: false,
            btn1: function () {
                console.log(url);
                window.location.href = url;
            }
        })
    })

    $('.app-c2').click(function () {
        var url = $(this).attr('data-url');
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定启用该应用？</p>',
            move: false,
            btn1: function () {
                console.log(url);
                window.location.href = url;
            }
        })
    })
    $('.user-c2').click(function() {
        var url = $(this).attr('data-url');
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定启用该用户？</p>',
            move: false,
            btn1: function () {
                console.log(url);
                window.location.href = url;
            }
        })
    })

    $('.app-c5').click(function () {
        var url = $(this).attr('data-url');
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定禁止该应用？</p>',
            move: false,
            btn1: function () {
                console.log(url);
                window.location.href = url;
            }
        })
    })
    $('.user-c4').click(function () {
        var url = $(this).attr('data-url');
        console.log("adsfasdf"+url);
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该用户/企业？</p>',
            move: false,
            btn1: function () {
                console.log(url);
                window.location.href = url;
            }
        })
    })

    $('.user-c5').click(function () {
        var url = $(this).attr('data-url');
        console.log("adsfasdf"+url);
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定禁止该用户/企业？</p>',
            move: false,
            btn1: function () {
                window.location.href = url;
            }
        })
    })

    $(function () {

        if ("${show}" == "user") {

            $("#user").addClass('active');
            $("#user").siblings().removeClass('active');
            $('#check2 table').show();
            var data = $("#user").attr('data');
            var div = $('div[id=' + data + ']');
            $(div).show();
            $(div).siblings('div').hide();
            if ("${userStatus}" == '1') {
                $("#c22").attr("checked", true);
                $("#c1").attr("checked", true);
            } else if ("${userStatus}" == '2') {
                $("#c33").attr("checked", true);
                $("#c1").attr("checked", true);
                console.log(2)
            } else if ("${userStatus}" == '3') {
                $("#c44").attr("checked", true);
                $("#c1").attr("checked", true);
                console.log(3)
            } else if ("${userStatus}" == '4') {
                $("#c55").attr("checked", true);
                $("#c1").attr("checked", true);
            } else {
                $("#c11").attr("checked", true);
                $("#c1").attr("checked", true);
            }
        } else {
            $('#app').addClass('active');
            $('#check1 table').show()
            $("#user").siblings().addClass('active');
            $("#user").removeClass('active');

            if ("${appStatus}" == '1') {
                $("#c2").attr("checked", true);
                $("#c11").attr("checked", true);

            } else if ("${appStatus}" == '2') {
                $("#c3").attr("checked", true);
                $("#c11").attr("checked", true);
            } else if ("${appStatus}" == '3') {
                $("#c4").attr("checked", true);
                $("#c11").attr("checked", true);
            } else if ("${appStatus}" == '4') {
                $("#c5").attr("checked", true);
                $("#c11").attr("checked", true);
            } else {
                $("#c1").attr("checked", true);
                $("#c11").attr("checked", true);
            }
        }
        $('#user').click(function () {
            $('#check2 table').show();
            $(this).addClass('active');

        })

        $("#app").click(function () {
            $('#check1 table').show();
            $(this).addClass('active');
        })
//
    })
</script>
</body>

</html>