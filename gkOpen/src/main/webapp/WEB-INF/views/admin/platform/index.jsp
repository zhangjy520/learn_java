<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/header.jsp" %>
<%@ include file="../../login/login.jsp" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css">
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/another-js/layer.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/alert.css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
</head>
<body>
<!--导航栏-->
<%@ include file="../../common/admin/menu.jsp" %>
<!--开放文档-->
<style>
    .col-xs-9 > h1 > input {
        margin-left: 10px;
        position: relative;
        top: 2px;
        margin-right: 5px;
    }
    #document-content{
        border: none;
    }
    .container{
        padding-top: 0 !important;
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

    #document-content section.col-xs-9 > div table tr:first-child {
        text-align: left;
        background: #F6F6F6 !important;
    }
    #app1{
        padding-right: 0 !important;
    }
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../../common/admin/platformMenu.jsp" %>
        <section class="col-xs-9" id="app1">
            <div id="check1">
                <style>
                    #document-content section.col-xs-9 > div > div {
                        margin-bottom: 0;
                        font-size: 13px;
                        color: #ff1b26;
                        margin-bottom: -14px;
                        display: none;
                    }
                </style>
                <table style="margin: auto;margin-top: 25px;">
                    <tr>
                        <th width="3%"><input type="checkbox"></th>
                        <th width="6%">序号</th>
                        <th width="18%">平台名称</th>
                        <th width="18%">状态</th>
                        <th width="28%">操作</th>
                    </tr>
                    <c:forEach items="${pageInfo.list}" var="platform" varStatus="status">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                            <td>${platform.name}</td>
                            <c:if test="${platform.initStatus == 0}">
                                <td>未初始化</td>
                            </c:if>
                            <c:if test="${platform.initStatus == 1}">
                                <td>已经初始化</td>
                            </c:if>
                            <td>
                                <span class="app1"
                                      onclick="window.location.href='${ctx}/platform/detail?id='+'${platform.id}'">修改/查看信息</span>
                                <c:if test="${platform.delFlag==0}">
                                    <span class="app3" data-url="${ctx}/platform/del?id=${platform.id}">删除</span>
                                </c:if>
                                <c:if test="${platform.initStatus==0}">
                                    <span class="app2" onclick="window.location.href='${ctx}/platform/init?id='+'${platform.id}'">初始化</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <footer class="clear" style="margin-top:23px;">
                    <p style="color:#999;font-size:13px;float:left; margin: auto;">
                        每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</p>
                    <div style="float:right;margin: auto;" class="fenY"></div>
                </footer>
            </div>
        </section>
    </div>
</main>
<!--网页信息-->
<%@ include file="../../common/footer.jsp" %>
<!--<script src="js/jquery.min.js"></script>-->
<script>
    $(".fenY").createPage({
        pageCount: '${pageInfo.pages}',
        current: '${pageInfo.pageNum}',
        backFn: function (p) {
            window.location.href = "${ctx}/platform/index?pageNum=" + p + "&pageSize=10";
        }
    });
    $('.app3').click(function () {
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

    <%--$(".gotoPage").click(function (){--%>
    <%--var pageNum = $(".go").val();--%>
    <%--if (pageNum <= 0 || pageNum >${appPageInfo.pages}){--%>
    <%--layer.msg("请输入正确的页码")--%>
    <%--} else {--%>
    <%--window.location.href = "${ctx}/manager/index?pageNum="+$(".go").val()+"&pageSize=10";--%>
    <%--}--%>
    <%--});--%>

    $(function () {
        var table = $('table');
        for (var i = 0; i < table.length; i++) {
            var td = $(table[i]).find('tbody tr');
            if (td.length == 0) {
                $(table[i]).siblings('footer').before('<span style="font-size:13px;color:#666;">暂无数据</span>')
//                $("#pages").hide();
            }
        }
    });
</script>
</body>
</html>