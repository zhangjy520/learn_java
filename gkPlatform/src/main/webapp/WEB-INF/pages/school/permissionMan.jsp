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
    <title>机构管理</title>

    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pageDivide.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/fenBan.css" />
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/pageDevide.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <style>
        .caozuoTd>span{
            padding-left: 10px;
        }
        .layui-layer{
            top:100px !important;
        }
    </style>

</head>

<body class="thisBody">

<div style="height:78px;width:1200px;margin:0px auto">
    <div style="z-index:99;position:fixed;margin:0px 0px">




        <div class="app_store_headerMenu fenBan-header">

            <div class="appStoreLeft fenBanLeft">
                <img src="${ctxStatic}/image/fenban/fenban.png" />&nbsp;
                <a style="line-height: 49px;font-size:24px;color: #1AB394;">角色管理</a>
            </div>
            <div class="rsMenuRight">
                <ul>
                    <li onclick="window.location.href='${ctx}/school/config?pageSize=10'">配置管理</li>
                    <li onclick="window.location.href='${ctx}/school/log?pageSize=10'">日志管理</li>
                    <li onclick="window.location.href='${ctx}/menu/index?pageSize=10'">菜单管理</li>
                    <li onclick="window.location.href='${ctx}/app/index'">应用管理</li>
                    <li onclick="window.location.href='${ctx}/school/permissionMan'"  class="active">角色管理</li>
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


                <input onclick="openDialog('新增角色','${ctx}/school/role/edit','500px','600px');" class="rsButton" type="button"  value=" 新增 " />

                <div style="color: #999999;font-size: 16px;float: right;margin-right: 30px;">
                    <input class="searchInput" type="text" placeholder=" 搜索角色名称" value=""  name="roleName"/> <!--高级搜索-->
                    <input style="opacity: 0;position: relative;margin-left: -40px;height:30px;" class="summitButton" type="button" name="" id="search" value="搜索"/>
                   <%-- <input class="rsButton" type="button"  value=" 查询 " />--%>
                </div>

            </div>
        </div>
        <div class="rsTable">
            <table>
                <tr class="headerTh">
                   <%-- <th width="10%">角色编号</th>--%>
                    <th width="20%">角色名称</th>
                    <th width="20%">角色标识hasRole</th>
                    <th width="20%">角色描述</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach items="${roleList}" var="role">
                    <tr>
                       <%-- <td>${role.id}</td>--%>
                        <td>${role.name}</td>
                        <td>${role.roleIdentify}</td>
                        <td>${role.remarks}</td>
                        <td class="caozuoTd">
                            <span style="color: red" onclick="alertTips(400,222,'删除角色','确定要删除当前角色吗，删除后，相关的用户的权限也会被删除,确定吗？','permissionDelete(\'${role.id}\')')">删除</span>
                            <span style="color: #1ab394"  onclick="openDialog('编辑角色','${ctx}/school/role/edit?id=${role.id}','500px','600px');">编辑</span>
                           <%-- <span style="color: #1ab394" onclick="roleDetails(${role.id})">查看</span>--%>
                            <span style="color: #1ab394" onclick="openDialogView('角色查看','${ctx}/school/role/edit?id=${role.id}','500px','600px');">查看</span>
                            <span style="color: #1ab394" onclick="openDialog('权限设置','${ctx}/school/permission/view?roleId=${role.id}','500px','600px');">分配权限</span>
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
                window.location.href = "${ctx}/school/permissionMan?pageNum="+p;
            }
        });

        /*查询搜索*/
        $(".summitButton").click(function () {
            var roleName=$("input[name='roleName']").val();
            window.location.href="${ctx}/school/permissionMan?roleName="+encodeURI(encodeURI(roleName));


        });

    })


function  permissionDelete(id) {
    closeAlertDiv();
    $.post("${ctx}/school/role/delete",{
        roleId:id,
    },function(retJson){
       //alert(retJson);
    },"json");
    setTimeout(function(){window.location.reload();}, 100);//删除的数据越多，延时要越长。否则：刷新页面的时候，数据还没删完..
}

function roleDetails(id) {
    $.ajax({
        type: "POST",
        url: "${ctx}/school/role/view",
        data: {roleId:id},
        dataType: "json",
        success: function (result) {
            alert(JSON.stringify(result));
        },
        error:function(response){
            alert("error");
        }

    });

}

</script>
</body>

</html>
