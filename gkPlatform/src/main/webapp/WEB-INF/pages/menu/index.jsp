<%@ include file="../common/common.jsp"%>
<%@ include file="../common/headerMenu.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Language" content="en" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>权限管理</title>

    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pageDivide.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/fenBan.css" />
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/buttons.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/pageDevide.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <style>
        .caozuoTd>span{
            padding-left: 10px;
        }
        .layui-layer{
            top:250px !important;
        }
    </style>

</head>

<body class="thisBody">

<div style="height:78px;width:1200px;margin:0px auto">
    <div style="z-index:99;position:fixed;margin:0px 0px">




        <div class="app_store_headerMenu fenBan-header">

            <div class="appStoreLeft fenBanLeft">
                <img src="${ctxStatic}/image/fenban/fenban.png" />&nbsp;
                <a style="line-height: 49px;font-size:24px;color: #1AB394;">菜单管理</a>
            </div>
            <div class="rsMenuRight">
                <ul>
                    <li onclick="window.location.href='${ctx}/school/config?pageSize=10'">配置管理</li>
                    <li onclick="window.location.href='${ctx}/school/log?pageSize=10'">日志管理</li>
                    <li onclick="window.location.href='${ctx}/menu/index?pageSize=10'"  class="active">菜单管理</li>
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

                <input class="rsButton" type="button"  value=" 新增 " onclick="openDialog('菜单新增','${ctx}/menu/add','550px','600px');" />

                <div style="color: #999999;font-size: 16px;float: right;margin-right: 30px;">
                    <input class="searchInput" type="text" placeholder=" 搜索菜单名称" value=""  name="menuName"/> <!--高级搜索-->
                    <input style="opacity: 0;position: relative;margin-left: -40px;height:30px;" class="summitButton" type="button" name="" id="search" value="搜索"/>
                </div>

            </div>
        </div>
        <div class="rsTable">
            <table>
                <tr class="headerTh">
                    <td>ID</td>
                    <td>标题</td>
                    <td>路径</td>
                    <td>权限标识</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${menuList}" var="menu">
                    <tr>
                        <td>${menu.id}</td>
                        <td>${menu.name}</td>
                        <td>${menu.href}</td>
                        <td>${menu.permission}</td>
                        <td>
                            <a  onclick="openDialog('菜单新增','${ctx}/menu/edit?id=${menu.id}','550px','600px');">编辑</a> |
                            <a  onclick="openDialog('添加子菜单','${ctx}/menu/addSub?parentId=${menu.id}','550px','600px');">添加子菜单</a>
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
    $(function() {
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/menu/index?pageNum="+p+"&pageSize=10";
            }
        });

        /*查询搜索*/
        $(".summitButton").click(function () {
            var menuName=$("input[name='menuName']").val();
            window.location.href="${ctx}/menu/index?menuName="+encodeURI(encodeURI(menuName))+"&pageSize=10";

        });
    })
</script>
</body>

</html>
