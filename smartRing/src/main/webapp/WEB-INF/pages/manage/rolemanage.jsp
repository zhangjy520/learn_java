<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>

    <link rel="stylesheet" href="${ctxStatic}/css/basicManage.css"/>
    <!--<link rel="stylesheet" href="css/daterangepicker.css"/>-->
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">基础管理</a></li>
        <li class="child-nav active"><a href="#">角色管理</a></li>
    </ul>
</header>
<div id="bodyContainer">
    <main id="roll-manage" class="module-bg-full fix-width">
        <p>角色管理
            <button class="rl" onclick="openDialog('新增', '${ctx}/manage/role/add', '630px','680px');">新增</button>
        </p>
        <div class="sh-use-state">
            <table class="table">
                <thead>
                <tr>
                    <th>角色</th>
                    <th>操作模块</th>
                    <th>数据范围</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${roleList.list}" var="role">
                    <tr>
                        <td>${role.name}</td>
                        <td>${role.menuName}</td>
                        <td>
                            <c:if test="${role.permissionRange == 0}">全校学生</c:if>
                            <c:if test="${role.permissionRange == 1}">本年级学生</c:if>
                            <c:if test="${role.permissionRange == 2}">本班学生</c:if>
                        </td>
                        <td>
                            <span class="sh-operation1" onclick="openDialog('修改', '${ctx}/manage/role/add?id='+'${role.id}', '630px','680px');">修改</span>
                            <span class="sh-operation2" onclick="deleteRole('${role.id}')">删除</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <footer class="pagination clear">
            <div class="lf">
                共${roleList.total}条记录，每页显示
                <select id="pgSize">
                    <option value="10" <c:if test="${roleList.pageSize==10}">selected</c:if>>10</option>
                    <option value="20" <c:if test="${roleList.pageSize==20}">selected</c:if>>20</option>
                    <option value="50" <c:if test="${roleList.pageSize==50}">selected</c:if>>50</option>
                </select>
                条记录
            </div>
            <div class="rl fenY">
                <script>
                    <c:if test="${roleList.pages != 0}">
                    $(".fenY").createPage({
                        pageCount: ${roleList.pages},
                        current: ${roleList.pageNum},
                        backFn: function (p) {
                            window.location.href = "${ctx}/manage/role/index?pageNum=" + p + "&pageSize=" + $('#pgSize').val();
                        }
                    });
                    $("#pgSize").change(function () {
                        window.location.href = "${ctx}/manage/role/index?pageNum=" + 1 + "&pageSize=" + $('#pgSize').val();
                    });
                    </c:if>
                </script>
            </div>
        </footer>
    </main>
</div>
<script>
    $("#basicManage").addClass("active");
    $("#basicManage ul li:nth-child(3)>a").addClass("active");

    function deleteRole(id){
        layer.open({
            type:1,
            title:'确认删除',
            btn:['确认','取消'],
            area:['380px','240px'],
            content:'<div style="padding:25px">' +
            '<p style="font-size:13px;">' +
            ' <span style="color:#F6182B;font-size:18px;">确认删除该角色？</span><br><br> '+
            '删除角色后，该角色的教师用户必须重新分配角色才可以正常使用系统功能' +
            '</p>' +
            '</div>',
            btn1:function(){
                $.post("${ctx}/manage/role/delete",{
                    id:id,
                },function (res) {
                    layer.msg(res.msg);
                    setTimeout(function () {
                        window.location.reload();
                    },1000);
                })
            }
        })

    }
</script>
</body>
</html>