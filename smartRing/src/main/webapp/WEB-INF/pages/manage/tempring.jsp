<%@ page import="cc.gukeer.common.utils.ConstantUtil" %>
<%@ include file="../common/newHeader.jsp" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>手环管理</title>
    <link rel="stylesheet" href="${ctxStatic}/css/basicManage.css"/>
    <!--<link rel="stylesheet" href="css/daterangepicker.css"/>-->
    <script src="${ctxStatic}/js/plugins/laydate.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <style>
        body {
            padding-bottom: 80px;
        }
    </style>
    <!--<script src="js/plugins/jquery.daterangepicker.js"></script>-->
</head>
<body>
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">基础管理</a></li>
        <li class="child-nav  active"><a href="#">临时手环</a></li>
    </ul>
</header>
<main id="temporary-sh" class="module-bg-full fix-width">
    <p>临时手环
        <button class="rl download" onclick="window.location.href='${ctx}/manage/temptemplate'">下载模板</button>
        <button class="rl import"
                style="margin-left:15px;margin-right:15px;" <%--onclick="openDialog('导入手环','${ctx}/manage/importring','380px','240px');"--%>>
            导入
        </button>
        <button class="rl addNew" onclick="openDialog('新增临时手环','${ctx}/manage/tempring/add','380px', '240px')">新增
        </button>
    </p>
    <div class="sh-state">
        共有临时手环<span style="color:#333;">${pageInfo.total}</span>个，
        已丢失 <span class="sh-lose">${missingRing}</span>个，
        空闲临时手环现有<span class="sh-leisure">${freeRing}</span>个
    </div>
    <div class="sh-use-state">
        <table class="table">
            <thead>
            <tr>
                <th>手环编号</th>
                <th>状态</th>
                <th>配对学生</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="page">
                <tr>
                    <td>${page.mac}</td>
                    <td>
                        <c:choose>
                            <c:when test="${page.status == 0}">
                                <span>空闲</span>
                            </c:when>
                            <c:when test="${page.status == 1}">
                                <span class="sh-lack">使用中</span>
                            </c:when>
                            <c:when test="${page.status == 2}">
                                <span class="sh-lose">挂失中</span>
                            </c:when>
                            <c:when test="${page.status == 3}">
                                <span class="sh-leisure">已丢失</span>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>${page.stName}</td>
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
                    <c:if test="${pageInfo.pages != 0}">
                    $(".fenY").createPage({
                        pageCount: ${pageInfo.pages},
                        current: ${pageInfo.pageNum},
                        backFn: function (p) {
                            window.location.href = "${ctx}/manage/tempring/index?pageNum=" + p + "&pageSize=" + $('#pgSize').val();
                        }
                    });
                    $("#pgSize").change(function () {
                        window.location.href = "${ctx}/manage/tempring/index?pageNum=" + 1 + "&pageSize=" + $('#pgSize').val();
                    });
                    </c:if>
                </script>
            </div>
        </c:if>
    </footer>
</main>


<script>
    //    $('.addNew').click(function(){
    <%--layer.open({--%>
    <%--type: 2,--%>
    <%--title: '新增临时手环',--%>
    <%--shadeClose: true,--%>
    <%--shade: 0.8,--%>
    <%--btn:['确认','取消'],--%>
    <%--area: ['380px', '240px'],--%>
    <%--content: "${ctx}/manage/tempring/add",--%>
    <%--move:false,--%>
    <%--yes: function(index, layero){--%>
    <%--//do something--%>
    <%--layer.close(index); //如果设定了yes回调，需进行手工关闭--%>
    <%--}--%>
    <%--});--%>
    //    })
    $('.import').click(function () {
        var k = layer.open({
            type: 2,
            title: '批量添加临时手环',
            shadeClose: true,
            shade: 0.8,
            btn: ['确认', '取消'],
            resize:false,
            move: false,
            area: ['380px', '240px'],
            content: '${ctx}/manage/importuser',
            btn1: function () {
//                var index = layer.msg('导入中', {icon: 16, shade: 0.5, time: 1000000000});//当生成完成这个对话框才被关掉
                var index = layer.load();
                $.ajax({
                    url: '${ctx}/manage/import/tempring',
                    type: 'POST',
                    cache: false,
                    data: new FormData(layer.getChildFrame('#form')[0]),
                    processData: false,
                    contentType: false
                }).done(function (res) {
                    layer.close(index);
                    layer.close(k);
                    if (res.code == 0) {
                        layer.msg(res.msg, {
                            time: 1500 //2秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            parent.location.reload();
                        });
                    } else {
                        var tem = res.data;
                        var datamsg = '';
                        for (var i = 0; i < tem.length; i++) {
                            datamsg += '<tr>' +
                                    '<td>' + tem[i].mac + '</td>' +
                                    '<td>' + '已存在该手环' + '</td>' +
                                    '</tr>';
                        }
                        var html = ' <main style="padding:15px; font-size: 13px;">' +
                                ' <p style="margin-bottom:15px;">' +
                                ' 成功<span style="color: #19be9d;">' + res.msg + '</span>条，失败<span style="color: #fc2f5b;">' + res.data.length + '</span>条' +
                                '         </p>' +
                                '         <table class="table">' +
                                '         <thead>' +
                                '         <tr>' +
                                '         <th style="width:50%;">手环编号</th>' +
                                '         <th style="width:50%;">失败原因</th>' +
                                '         </tr>' +
                                '         </thead>' +
                                '         <tbody>' +
                                datamsg +
                                ' </tbody>' +
                                ' </table>' +
                                ' </main>';
                        layer.open({
                            type: 1,
                            title: '批量绑定手环',
                            shadeClose: true,
                            shade: 0,
                            btn: ['确认', '下载失败列表'],
                            area: ['480px', '460px'],
                            resize:false,
                            move: false,
                            content: html,
                            btn1: function () {
                                parent.location.reload();
                            },
                            btn2: function () {
                                var form=$("<form>");//定义一个form表单
                                form.attr("style","display:none");
                                form.attr("target","");
                                form.attr("method","post");
                                form.attr("action","${ctx}/manage/ring/errotemp");
                                var input1=$("<input>");
                                input1.attr("type","hidden");
                                input1.attr("name","msg");
                                input1.attr("value",JSON.stringify(res.data));
                                $("body").append(form);//将表单放置在web中
                                form.append(input1);
                                form.submit();//表单提交
                                return false;
                            }
                        })
                    }
                }).fail(function (res) {
                    layer.msg("模板异常，导入失败！");
                    setTimeout(function () {
                        layer.closeAll();
                    }, 500)
                });


//                    layer.open({
//                        type: 1,
//                        title: '批量添加临时手环',
//                        shadeClose: true,
//                        shade: 0,
//                        btn: ['确认', '下载失败列表'],
//                        area: ['530px', '480px'],
//                        content: 'pop-basicManage2-3.html',
//                        move: false,
//                        btn1: function () {
//                            layer.closeAll();
//                        }
//                    });
                }
        });
    })
</script>
</body>
</html>