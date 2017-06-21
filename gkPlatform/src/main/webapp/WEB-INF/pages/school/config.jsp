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
    <title>配置管理</title>

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
                <a style="line-height: 49px;font-size:24px;color: #1AB394;">机构管理</a>
            </div>
            <div class="rsMenuRight">
                <ul>
                    <li onclick="window.location.href='${ctx}/school/config?pageSize=10'" class="active">配置管理</li>
                    <li onclick="window.location.href='${ctx}/school/log?pageSize=10'">日志管理</li>
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
        <div class="rsTableHeader">
            <div class="">

                <input onclick="openDialog('新增配置','${ctx}/school/config/add','450px','400px');" class="rsButton"
                       type="button" value=" 新增 "/>

            </div>
        </div>
        <div class="rsTable">
            <table>
                <tr class="headerTh">
                    <th width="5%">序号</th>
                    <th width="15%">类别</th>
                    <th width="10%">键</th>
                    <th width="10%">值</th>
                    <th width="20%">操作</th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="config" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${config.paramType}</td>
                        <td>${config.paramKey}</td>
                        <td>${config.paramValue}</td>

                        <td class="caozuoTd">
                            <span style="color: red"
                                  onclick="alertTips(400,222,'删除配置','确定要删除当前配置吗？','configDelete(${config.id})')">删除</span>
                            <span style="color: #1ab394"
                                  onclick="openDialog('修改配置','${ctx}/school/config/add?id=${config.id}','450px','400px');">编辑</span>
                        </td>
                    </tr>
                </c:forEach>


            </table>
            <!--分页按钮组  -->
            <div class="fenY" id="fenY">
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                window.location.href = "${ctx}/school/index?pageNum=" + p + "&pageSize=10";
            }
        });

    });

    function configDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/school/config/delete", {
            id: id,
        },  "json");
        window.location.reload();
    }


</script>
</body>

</html>
