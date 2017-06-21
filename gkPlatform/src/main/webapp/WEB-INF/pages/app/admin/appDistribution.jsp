<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/headerMenu.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<META http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="en" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>应用管理</title>

<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/pageDivide.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/fenBan.css" />
<script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/pageDevide.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
<style>
.caozuoTd>span {
	padding-left: 10px;
}

.layui-layer {
	top: 100px !important;
}
</style>

</head>

<body class="thisBody">

	<div style="height: 78px; width: 1200px; margin: 0px auto">
		<div style="z-index: 99; position: fixed; margin: 0px 0px">




			<div class="app_store_headerMenu fenBan-header">

				<div class="appStoreLeft fenBanLeft">
					<img src="${ctxStatic}/image/fenban/fenban.png" />&nbsp; <a
						style="line-height: 49px; font-size: 24px; color: #1AB394;">应用管理</a>
				</div>
				<div class="rsMenuRight">
					<ul>
					    <li onclick="window.location.href='${ctx}/menu/index?pageSize=10'">菜单管理</li>
						<li onclick="window.location.href='${ctx}/app/index'">应用管理</li>
						<%--<shiro:hasPermission name="oa:oaNotify:add">--%>
						<li onclick="window.location.href='${ctx}/school/permissionMan'">角色管理</li>
						<li onclick="window.location.href='${ctx}/school/index'">机构管理</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="mainContain fenbanMain fenbanStep">
		<div class="forBeauty" style="background: #ffffff;">
			<div class="rsTableHeader">
				<div class="">
					<input
						onclick="openDialog('新增角色','${ctx}/app/role/add?appid=${appId}','500px','600px');"
						class="rsButton summitButton" type="button" value="为${appName}新增角色 " />
				</div>
			</div>
			<div class="rsTable">
				<table>
					<tr class="headerTh">
						<th width="20%">该应用已分配角色名称</th>
						<th width="10%">该应用已分配角色标识hasRole</th>
						<th width="20%">该应用已分配角色描述</th>
						<th width="30%">操作</th>
					</tr>
                         <c:forEach items="${appRole}" var="role">
                             <tr>
							 <td>${role.name}</td>
							 <td>${role.roleIdentify}</td>
							 <td>${role.remarks}</td>
							 <td class="caozuoTd">
							<span style="color: red" onclick="alertTips(400,222,'删除权限','确定要删除当前权限吗，删除后，相关的信息也会删除,确定吗？','app_and_roleDelete(\'${appId}\',\'${role.id}\')')"> 移除角色 </span>
							<span id="dis" style="color: #1ab394" onclick="openDialog('权限设置','${ctx}/app/getmenutree?roleId=${role.id}&&appId=${appId}','500px','600px');">分配权限</span>
						    </td>
					     	</tr>
                          </c:forEach>
				</table>
				<!--分页按钮组  -->
				<div class="fenY" id="fenY"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
    $(function() {
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "";
            }
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
    function  app_and_roleDelete(appid,roleid) {
        closeAlertDiv();
        $.post("${ctx}/app/del_role_and_app",{
            appId:appid,
            roleId:roleid,
        },function(retJson){
        	setTimeout(function(){parent.location.reload();}, 300)
        });

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
