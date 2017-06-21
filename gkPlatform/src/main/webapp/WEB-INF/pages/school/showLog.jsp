<%@ include file="../common/common.jsp" %>
<%@ include file="../common/headerMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>日志管理</title>

    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pageDivide.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/fenBan.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/buttons.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/pageDevide.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <style>
        .caozuoTd > span {
            padding-left: 10px;
        }

        .layui-layer {
            top: 250px !important;
        }
    </style>

</head>

<body class="thisBody">

<div style="height:78px;width:1200px;margin:0px auto">
    <div style="z-index:99;position:fixed;margin:0px 0px">


        <div class="app_store_headerMenu fenBan-header">

            <div class="appStoreLeft fenBanLeft">
                <img src="${ctxStatic}/image/fenban/fenban.png"/>&nbsp;
                <a style="line-height: 49px;font-size:24px;color: #1AB394;">日志管理</a>
            </div>
            <div class="rsMenuRight">
                <ul>
                    <li onclick="window.location.href='${ctx}/school/config?pageSize=10'">配置管理</li>
                    <li onclick="window.location.href='${ctx}/school/log?pageSize=10'" class="active">日志管理</li>
                    <li onclick="window.location.href='${ctx}/menu/index?pageSize=10'">菜单管理</li>
                    <li onclick="window.location.href='${ctx}/app/index'">应用管理</li>
                    <li onclick="window.location.href='${ctx}/school/permissionMan'">角色管理</li>
                    <li onclick="window.location.href='${ctx}/school/index'">机构管理</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="mainContain fenbanMain fenbanStep">
    <div class="forBeauty" style="background:#ffffff;">
        <div class="rsTable">
            <table>
                <tr>
                    <td width="5%">标题</td>
                    <td width="5%">日志类型</td>
                    <td width="5%">创建者</td>
                    <td width="9%">创建时间</td>
                    <td width="9%">操作IP地址</td>
                    <td width="5%">用户代理</td>
                    <td width="5%">请求URI</td>
                    <td width="5%">方式</td>
                    <td width="5%">参数</td>
                    <td width="5%">异常</td>
                </tr>
                <c:forEach items="${pageInfo.list}" var="log">
                    <tr>
                        <td>${log.title}</td>
                        <td>
                            <c:if test="${log.type == '1'}">接入日志</c:if>
                            <c:if test="${log.type == '2'}">异常日志</c:if>
                        </td>
                        <td>${log.createBy}</td>
                        <td>${gukeer:millsToyyyyMMddHHmmss(log.createDate)}</td>
                        <td>${log.remoteAddr}</td>
                        <td>${log.userAgent}</td>
                        <td>${log.requestUri}</td>
                        <td>${log.method}</td>
                        <td>
                            <c:if test="${gukeer:notEmptyString(log.params)}">
                                <a  onclick="window.open('${ctx}/school/log/detail?id=${log.id}&type=params')" style="color: #1AB394">详情</a>
                                <%--<a onclick="logParams('${log.params}')" style="color: #1AB394">详情</a>--%>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${gukeer:notEmptyString(log.exception)}">
                                <a onclick="window.open('${ctx}/school/log/detail?id=${log.id}&type=exception')" style="color: red">详情</a>
                                <%--<a onclick="logParams('${log.exception}')" style="color: red">详情</a>--%>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table><!--分页按钮组  -->
            <div class="fenY" id="fenY">
            </div>
            <br/><br/>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                window.location.href = "${ctx}/school/log?pageNum=" + p + "&pageSize=10";
            }
        });
    });


    function logParams(msg){
        layer.open({
            title: '参数详情',
            content: msg,
        });
    };
</script>
</body>

</html>
