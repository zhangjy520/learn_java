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
                <a style="line-height: 49px;font-size:24px;color: #1AB394;">机构管理</a>
            </div>
            <div class="rsMenuRight">
                <ul>
                    <li onclick="window.location.href='${ctx}/school/config?pageSize=10'">配置管理</li>

                    <li onclick="window.location.href='${ctx}/school/log?pageSize=10'">日志管理</li>
                    <li onclick="window.location.href='${ctx}/menu/index?pageSize=10'">菜单管理</li>
                    <li onclick="window.location.href='${ctx}/app/index'">应用管理</li>
                    <li onclick="window.location.href='${ctx}/school/permissionMan'">角色管理</li>
                    <li onclick="window.location.href='${ctx}/school/index'" class="active" >机构管理</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="mainContain fenbanMain fenbanStep">
    <div class="forBeauty" style="background:#ffffff;">
        <div class="rsTableHeader">
            <div class="">

                <input onclick="openDialog('机构新增','${ctx}/school/edit','550px','600px');" class="rsButton" type="button"  value=" 新增 " />

                <div style="color: #999999;font-size: 16px;float: right;margin-right: 30px;">
                    <input class="searchInput" type="text" placeholder=" 搜索机构名称" value=""  name="name"/> <!--高级搜索-->
                    <input style="opacity: 0;position: relative;margin-left: -40px;height:30px;" class="summitButton" type="button" name="" id="search" value="搜索"/>
                </div>

            </div>
        </div>
        <div class="rsTable">
            <table>
                <tr class="headerTh">
                   <%-- <th width="5%">序号</th>--%>
                    <th width="15%">机构名称</th>
                    <th width="10%">机构标识</th>
                    <th width="10%">所属平台</th>
                    <th width="20%">url</th>
                    <th width="10%">管理员</th>
                    <th width="40%">操作</th>
                </tr>
                <c:forEach items="${schoolList}" var="school">
                    <tr >
                       <%-- <td>${school.id}</td>--%>
                        <td>${school.schoolName}</td>
                        <td>${school.shortFlag}</td>
                        <td>${school.parentId}</td>
                        <td>${school.deployUrl}</td>
                        <td>
                         ${school.managerName}
                        </td>
                        <td class="caozuoTd">
                            <span style="color: red" onclick="alertTips(400,222,'删除机构','确定要删除当前机构吗，删除后，相关的信息也会删除,确定吗？','schoolDelete(\'${school.id}\')')">删除</span>
                            <span style="color: #1ab394"  onclick="openDialog('机构修改','${ctx}/school/edit?id=${school.id}','550px','600px');">编辑</span>
                            <span style="color: #1ab394" onclick="alertTips(400,222,'重置密码','确定要重置管理员密码吗，重置密码默认为6个0！','resetPassword(\'${school.id}\')')">重置密码</span>
                            <span style="color: #1ab394" onclick="openDialogView('机构详情','${ctx}/school/edit?id=${school.id}','550px','600px');">详情</span>
                            <span style="color: #1ab394" onclick="openDialog('授权应用','${ctx}/school/app/authorization?schoolId=${school.id}','500px','600px');">授权应用</span>

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
                window.location.href = "${ctx}/school/index?pageNum="+p+"&pageSize=10";
            }
        });

        /*查询搜索*/
        $(".summitButton").click(function () {
            var name=$("input[name='name']").val();
            window.location.href="${ctx}/school/index?name="+encodeURI(encodeURI(name));


        });
    });
//重置密码
function  resetPassword(id) {
    closeAlertDiv();
    $.post("${ctx}/school/admin/update",{
        id:id,
    },function(retJson){
        window.location.href="${ctx}/school/index";
    },"json");
}
    
function  schoolDelete(id) {
    closeAlertDiv();
    $.post("${ctx}/school/delete",{
        id:id,
    },function(retJson){
        window.location.href="${ctx}/school/index";
    },"json");

}

function schoolDetils(obj,id) {
    $.ajax({
        type: "POST",
        url: "${ctx}/school/details",
        data: {id:id},
        dataType: "json",
        success: function (result) {
            layer.tips(JSON.stringify(result), obj, {
                tips: [3, '#1ab394']
            });
        },
        error:function(response){
            alert("error");
        }

    });

}

</script>
</body>

</html>
