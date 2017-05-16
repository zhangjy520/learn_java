<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>区域信息</title>
    <link rel="stylesheet" href="${ctxStatic}/css/position.css"/>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
    <script src="${ctxStatic}/js/plugins/laydate.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
                    <span class="lf current-position">
                        当前位置：
                    </span>
    <ul class="lf breadcrumb clear">
        <c:if test="${type == 0}">
            <li class="root-nav"><a href="#">基础管理</a></li>
            <li class="child-nav active"><a href="#">区域管理</a></li>
        </c:if>
        <c:if test="${type == 1}">
            <li class="root-nav"><a href="#">位置监控</a></li>
            <li class="child-nav active"><a href="#">区域信息</a></li>
        </c:if>

    </ul>
</header>
<div id="bodyContainer">
    <main class="fix-width module-bg-full position3" style='display:block;'>
        <p>
            区域信息
            <c:if test="${type == 0}">
                <button class="rl" onclick="openDialog('新增区域','${ctx}/manage/area/add','660px', '80%')">新增</button>
            </c:if>

        </p>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th width="25%">区域名称</th>
                    <th width="60%">说明</th>
                    <th width="15%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="page">
                    <tr>
                        <td>${page.areaName}</td>
                        <td>${page.remarks}</td>
                        <td>
                            <c:if test="${type == 0}">
                                <span class="change" style="margin-right:10px;" onclick="openDialog('修改区域','${ctx}/manage/area/edit/${page.id}','660px', '710px')">修改</span>
                                <span class="delete" onclick="delArea('${page.id}')">删除</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <footer class="pagination clear">
            <c:if test="${pageInfo.pages!=0}">
                <div class="lf">
                    共${pageInfo.total}条记录，每页显示
                    <select id="pgSize">
                        <option value="10" <c:if test="${pageInfo.pageSize==10}">selected</c:if>>10</option>
                        <option value="20" <c:if test="${pageInfo.pageSize==20}">selected</c:if>>20</option>
                        <option value="50" <c:if test="${pageInfo.pageSize==50}">selected</c:if>>50</option>
                    </select>
                    条记录
                </div>
                <div class="rl fenY">
                    <script>
                        $(".fenY").createPage({
                            pageCount: ${pageInfo.pages},
                            current: ${pageInfo.pageNum},
                            backFn: function (p) {
                                window.location.href = "${ctx}/manage/area/${type}?pageNum=" + p + "&pageSize=" + $('#pgSize').val();
                            }
                        });
                        $("#pgSize").change(function () {
                            window.location.href = "${ctx}/manage/area/index/${type}?pageNum=" + 1 + "&pageSize=" + $('#pgSize').val();
                        });
                    </script>
                </div>
            </c:if>
        </footer>
    </main>

</div>

<script>

    <c:if test="${type == 0}">
    $("#basicManage").addClass("active");
    $("#basicManage ul li:nth-child(5)>a").addClass("active");
    </c:if>

    <c:if test="${type == 1}">
    $("#positionControl").addClass("active");
    $("#positionControl ul li:nth-child(3)>a").addClass("active");
    </c:if>



    function delArea(id) {
        layer.open({
            type:1,
            title:'确认删除',
            btn:['确认','取消'],
            area:['380px','240px'],
            content:'<div style="padding:25px">' +
            '<p style="font-size:13px;">' +
            ' <span style="color:#F6182B;font-size:18px;">确认删除该区域？</span><br><br> '+
            '删除区域后，该区域里的网关不属于任何区域，您可以重新分配这些网关到其他的区域' +
            '</p>' +
            '</div>',
            btn1:function(){
                $.post("${ctx}/manage/area/del/"+id, function (res) {
                    layer.msg(res.msg);
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                })
            }
        })
        /*$.post("${ctx}/manage/area/del/"+id, function (res) {
            layer.msg(res.msg);
            setTimeout(function () {
                window.location.reload();
            }, 1000);
        })*/
    }
</script>
</body>
</html>