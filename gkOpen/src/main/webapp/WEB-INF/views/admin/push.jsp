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
    <script src="${ctx}/static/another-js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
</head>
<style>
    #main-tab table{
        margin-top: 30px !important;
    }
</style>
<body>
<%@ include file="../common/admin/menu.jsp" %>
<main id="main-tab">
    <%--<ul class="clear tab-menu">--%>
        <%--&lt;%&ndash;<li data="check1" <c:if test="${optStatus==0}"> class="active"</c:if>&ndash;%&gt;--%>
            <%--&lt;%&ndash;onclick="location.href='${ctx}/admin/push/index?pushStatus=UNPUSH'"&ndash;%&gt;--%>
            <%--&lt;%&ndash;id="unPush">平台推送信息&ndash;%&gt;--%>
        <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
    <%--</ul>--%>
    <div id="check1" class="app-manage">
        <table>
            <thead>
            <tr>
                <th width="5%">序号</th>
                <th width="13%">应用名称</th>
                <th width="8%">应用分类</th>
                <th width="18%">开发商</th>
                <th width="8%">负责人</th>
                <th width="15%">联系电话</th>
                <th width="18%">操作</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${pageInfo.list}" var="appBaseInfoView" varStatus="status">
                <tr>
                        <%--<td><input type="checkbox" name="pushSingleCheck"/></td>--%>
                    <td>${status.count}</td>
                    <td>${appBaseInfoView.name}</td>
                    <c:if test="${appBaseInfoView.category == 0}">
                        <td>教学教务</td>
                    </c:if>
                    <c:if test="${appBaseInfoView.category == 1}">
                        <td>互动空间</td>
                    </c:if>
                            <!--<td>正在使用</td>-->
                    <c:if test="${appBaseInfoView.userType == 1}">
                        <td>${appBaseInfoView.cCompany}</td>
                        <td>${appBaseInfoView.cManage}</td>
                        <td>${appBaseInfoView.cPhone}</td>
                    </c:if>
                    <c:if test="${appBaseInfoView.userType == 0}">
                        <td>${appBaseInfoView.pCompany}</td>
                        <td>${appBaseInfoView.pManage}</td>
                        <td>${appBaseInfoView.pPhone}</td>
                    </c:if>
                    <td>
                        <span class="singlePush  c3" value="${appBaseInfoView.id}" id="singlePush">管理</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <footer class="clear">
            <p>每条显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</p>
            <div class="fenY" id="fenYDiv"></div>
        </footer>
    </div>
    <%--<div id="check2">--%>
    <%--<p style="">--%>
    <%--<button class="pushed" style="border:1px solid #19BE9D;background:#19BE9D;color:#fff;border-radius:3px;--%>
    <%--font-size:14px;height:28px;padding:0 15px;">取消推送--%>
    <%--</button>--%>
    <%--</p>--%>
    <!--<p>-->
    <!--<input type="radio" name="check2" id="c11" checked/>-->
    <!--<label for="c11">全部应用</label>-->
    <!--<input type="radio" name="check2" id="c22"/>-->
    <!--<label for="c22">待审核</label>-->
    <!--<input type="radio" name="check2" id="c33"/>-->
    <!--<label for="c33">已通过</label>-->
    <!--<input type="radio" name="check2" id="c44"/>-->
    <!--<label for="c44">未通过</label>-->
    <!--<input type="radio" name="check2" id="c55"/>-->
    <!--<label for="c55">禁用</label>-->
    <!--</p>-->
    <%--<table>--%>
    <%--<thead>--%>
    <%--<tr>--%>
    <%--<th width="3%"><input type="checkbox" class="allCheck" name="pushAllCheck"></th>--%>
    <%--<th width="3%">序号</th>--%>
    <%--<th width="13%">应用名称</th>--%>
    <%--<th width="8%">应用分类</th>--%>
    <%--<!--<th width="8%">应用状态</th>-->--%>
    <%--<th width="18%">开发商</th>--%>
    <%--<th width="8%">负责人</th>--%>
    <%--<th width="15%">联系电话</th>--%>
    <%--<th width="18%">操作</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>

    <%--<tbody>--%>
    <%--<c:forEach items="${pageInfo.list}" var="app" varStatus="status">--%>
    <%--&lt;%&ndash;<input type="hidden" class="singleInputId" value="${app.id}">&ndash;%&gt;--%>
    <%--<tr>--%>
    <%--<td><input type="checkbox" name="singleCheck"/></td>--%>
    <%--&lt;%&ndash;<td>${status.index+1+(pageInfo.pageNum-1)*10}</td>&ndash;%&gt;--%>
    <%--<td>${app.name}</td>--%>
    <%--<c:if test="${app.category == 0}">--%>
    <%--<td>教学教务</td>--%>
    <%--</c:if>--%>
    <%--<c:if test="${app.category == 1}">--%>
    <%--<td>互动空间</td>--%>
    <%--</c:if>--%>
    <%--<!--<td>正在使用</td>-->--%>
    <%--<c:if test="${app.userType == 0}">--%>
    <%--<td>${app.pCompany}</td>--%>
    <%--<td>${app.pManage}</td>--%>
    <%--<td>${app.pPhone}</td>--%>
    <%--</c:if>--%>
    <%--<c:if test="${app.userType == 1}">--%>
    <%--<td>${app.cCompany}</td>--%>
    <%--<td>${app.cManage}</td>--%>
    <%--<td>${app.cPhone}</td>--%>
    <%--</c:if>--%>
    <%--<td>--%>
    <%--<span class="app6 singleCancel">取消推送</span>--%>
    <%--<span class="detail">查看/修改平台信息</span>--%>
    <%--<!--<span class="app3">分配角色</span>-->--%>
    <%--<!--<span class="app4">禁用</span>-->--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--</tbody>--%>
    <%--</table>--%>
    <%--<footer class="clear">--%>
    <%--<p>每条显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</p>--%>
    <%--<div class="fenY1" id="fenY1Div"></div>--%>
    <%--</footer>--%>
    <%--</div>--%>
</main>

<script>

    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

    $(".allCheck").on("click", function () {
        if (this.checked == true) {
            $("input[type='checkbox']").prop("checked", true);
        } else {
            $("input[type='checkbox']").prop("checked", false);
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

    $(".fenY").createPage({
        pageCount: ${pageInfo.pages},
        current: ${pageInfo.pageNum},
        backFn: function (p) {
            window.location.href = "${ctx}/app/push?pushStatus=UNPUSH&&pageNum=" + p + "&pageSize=10";
        }
    });
    $(".fenY1").createPage({
        pageCount: ${pageInfo.pages},
        current: ${pageInfo.pageNum},
        backFn: function (p) {
            window.location.href = "${ctx}/app/push?pushStatus=PUSHED&&pageNum=" + p + "&pageSize=10";
        }
    });


    $('.push').click(function () {
        var appTempIds = 0;
        var i = 0;
        $("input[name='pushSingleCheck']:checked").each(function () {
            i++;
//            var singleId = $(this).parents('tr').siblings('input').val();
//            appTempIds += ',' + singleId;
            var singleId = $(".singleInputId" + i).val();
            appTempIds += ',' + singleId;
        });
        var platformIds = null;
        layer.open({
            type: 2,
            title: '选择需要推送的平台',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['450px', '360px'],
            content: '${ctx}/platform/pop?appId=' + appTempIds,
            move: false,
            success: function (layero, index) {
            },
            btn1: function () {
                var platformIds = 0;
                var body = layer.getChildFrame('body');
                appTempIds = body.find('#appIds').val();
                var platformCheckbox = body.find('input[name=platformCheckBox]:checked');
                var url = body.find('.push').attr('data-url');
                var r = 0;
                platformCheckbox.each(function () {
                    r++;
                    var platformId = $(this).siblings('input').val();
//                    platformIds += ","+platformId;
                })
                $.get(postPath + "/app/init", {
                    appId: appTempIds,
                    platformId: platformId
                }, function (data) {
                    if (data.coe == 0) {
                        alert("应用推送成功");
                    }
                })
            }
        })
    })


    $('.singlePush').click(function () {
        var tepmId = 0;
        var singleId = $(this).attr('value');
        layer.open({
            type: 2,
            title: '选择需要推送的平台',
            shadeClose: true,
            shade: 0.6,
//            btn: ['', '取消'],
            area: ['800px', '360px'],
            content: '${ctx}/app/pop?appId=' + singleId,
            success: function (/*layero, index*/) {
                var body = layer.getChildFrame('body');
                /*var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();*/
                /*console.log(body.html()) //得到iframe页的body内容*/
                /*var values = body.find('input').val();*/
                tepmIds = 0 + "," + body.find('#appIds').val();
                var platformCheckbox = body.find('input[name=platformCheckBox]');
                var platformWillPush = [];
                platformCheckbox.each(function (index, key) {
//                    console.log(arguments[0]);
                })
            },
            btn1: function () {
                var platformIds = 0;
                var body = layer.getChildFrame('body');
                body.find('.push').click(function () {
                    body.find('.push').attr('data-url');
                })
                var platformCheckbox = body.find('input[name=platformCheckBox]:checked');
                var r = 0;
                platformCheckbox.each(function () {
                    r++;
                    var platformId = platformCheckbox.val();
                    platformIds += "," + platformId;

                })
                console.log(platformCheckbox);
                $.get(postPath + "/app/init", {
                    appId: tepmId,
                    platformId: platformIds
                }, function (data) {
                    if (data.coe == 0) {
                        alert("应用推送成功");
                    }
                })
            }
        })
    })

    $('.detail').click(function () {
        layer.open({
            type: 2,
            title: '选择需要推送的平台',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['450px', '360px'],
            content: '${ctx}/platform/pop',
            move: false,
        })
    })
    $('.singleCancel').click(function () {
        var tepmIds = 0;
        var singleId = $(this).attr('value');
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">确定取消推送？</p>',
            move: false,
            success: function (/*layero, index*/) {
                body = layer.getChildFrame('body');
                /*var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();*/
                /*console.log(body.html()) //得到iframe页的body内容*/
                /*var values = body.find('input').val();*/
                tepmIds = 0 + "," + body.find('#appIds').val();
                var platformCheckbox = body.find('input[name=platformCheckBox]');
                var platformWillPush = [];
                platformCheckbox.each(function (index, key) {

//                    console.log(arguments[0]);

                })
            },
            btn1: function () {
                var platformIds = 0;
                var body = layer.getChildFrame('body');
                var platformCheckbox = body.find('input[name=platformCheckBox]:checked');
                var r = 0;
                platformCheckbox.each(function () {
                    r++;
                    var platformId = platformCheckbox.val();
                    platformIds += "," + platformId;

                })
                console.log(platformCheckbox);
                $.get(postPath + "/app/init", {
                    appId: tepmIds,
                    platformId: platformIds
                }, function (data) {
                    if (data.coe == 0) {
                        alert("应用推送成功");
                    }
                })
            }
        })
    })


    $('.pushed').click(function () {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">确定取消推送？</p>',
            move: false,
        })
    })

    $(function () {
        var table = $('table');
        for (var i = 0; i < table.length; i++) {
            var td = $(table[i]).find('tbody tr');
            if (td.length == 0) {
                $(table[i]).siblings('footer').before('<span style="font-size:13px;color:#666;">暂无数据</span>')
            }
        }

        if ("${sendStatus}" == 1) {
//            $("#unPush").removeClass('active');
//            $("#pushed").addClass('active');
            $("#check2").show();
            $("#check1").hide();
            $("#fenY1Div").show();
        }
    })


    $(".gotoPage").click(function () {
        var pageNum = $(".go").val();
        if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
            layer.msg("请输入正确的页码")
        } else {
            if ("${sendStatus}" == 0) {
                window.location.href = "${ctx}/admin/send?sendStatus=UNPUSH&&pageNum=" + pageNum + "&pageSize=10";
            }
            if ("${sendStatus}" == 1) {
                window.location.href = "${ctx}/admin/send?sendStatus=PUSHED&&pageNum=" + pageNum + "&pageSize=10";
            }
        }
    });


</script>
</body>
</html>