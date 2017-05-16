<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>网关信息</title>
    <link rel="stylesheet" href="${ctxStatic}/css/basicManage.css"/>
    <script src="${ctxStatic}/js/openDialog.js"></script>
    <script src="${ctxStatic}/js/plugins/laydate.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <!--<link rel="stylesheet" href="css/daterangepicker.css"/>-->
</head>
<body style="padding-bottom:80px;">
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">基础管理</a></li>
        <%--<li class="child-nav active"><a href="#">网关信息</a></li>--%>
        <li class="child-nav active"><a href="#">网关管理</a></li>
    </ul>
</header>
<main id="base-station" class="module-bg-full fix-width">
    <p>
        <%--网关状态--%>
        网关管理
        <%--<button class="rl download" style="margin-left:15px;" onclick="window.location.href='${ctx}/manage/stationtemplate'">下载模板</button>--%>
        <%--<button class="rl import" style="margin-left:15px;">导入</button>--%>
        <button class="rl add" onclick="openDialog('添加网关','${ctx}/manage/station/add','485px', '500px')">新增</button>
    </p>
    <div class="sh-state">
        <c:choose>
            <c:when test="${brokenStation==0}">
                <%--所有网关正常工作--%>
                所有网关正常工作
            </c:when>
            <c:otherwise>
                当前有<span class="sh-lose">${brokenStation}</span>个网关出现故障，
                故障维修电话：<span class="sh-leisure">444-588-077</span>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="sh-use-state">
        <table class="table">
            <thead>
            <tr>
                <%--<th width="15%">网关 MAC</th>--%>
                <th width="15%">网关mac</th>
                <th width="10%">状态</th>
                <th width="10%">种类</th>
                <th width="10%">关联班级</th>
                <th width="15%">部署区域</th>
                <th width="20%">详细位置</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="station" varStatus="status">
                <tr>
                    <c:if test="${station.status == 0}">
                        <td>${station.stationMac}</td>
                        <td>正常</td>
                        <td>
                            <c:if test="${station.category==1}">校门口网关</c:if>
                            <c:if test="${station.category==2}">公共网关</c:if>
                            <c:if test="${station.category==3}">班级网关</c:if>
                        </td>
                        <td>${station.className}</td>
                        <td>${station.areaName}</td>
                        <td>${station.remarks}</td>
                        <td>
                            <span class="change" onclick="openDialog('修改网关','${ctx}/manage/station/edit/${station.stationMac}','485px', '500px')">修改</span>
                            <span class="delete" onclick="delStation('${station.stationMac}')">删除</span>
                        </td>
                    </c:if>
                    <c:if test="${station.status == 1}">
                        <td class="sh-lose">${station.stationMac}</td>
                        <td class="sh-lose">异常</td>
                        <td>
                            <c:if test="${station.category==1}">校门口网关</c:if>
                            <c:if test="${station.category==2}">公共网关</c:if>
                            <c:if test="${station.category==3}">班级网关</c:if>
                        </td>
                        <td>初一1班</td>
                        <td class="sh-lose">${station.areaName}</td>
                        <td class="sh-lose">${station.remarks}</td>
                        <td>
                            <span class="change" onclick="openDialog('修改网关','${ctx}/manage/station/edit/${station.stationMac}','485px', '500px')">修改</span>
                            <span class="delete" onclick="delStation(${station.stationMac})">删除</span>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <footer class="pagination clear">
        <div class="lf">
            共${pageInfo.total}条记录，每页显示
            <select id="stationSize">
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
                        window.location.href = "${ctx}/manage/station/index?pageNum=" + p + "&pageSize=" + $('#stationSize').val();
                    }
                });
            </script>
        </div>
    </footer>
</main>
<script>
    $("#basicManage").addClass("active");
    $("#basicManage ul li:nth-child(2)>a").addClass("active");

    $("#stationSize").change(function () {
        window.location.href = "${ctx}/manage/station/index?pageNum=" + 1 + "&pageSize=" + $('#stationSize').val();
    });
    $('.import').click(function(){
        layer.open({
            type: 2,
            title: '批量添加网关信息',
            shadeClose: true,
            shade: 0.8,
            btn: ['确认', '取消'],
            area: ['380px', '240px'],
            content: '${ctx}/manage/importstation',
            resize:false,
            move: false,
            btn1: function () {
//                layer.msg('导入中', {icon: 16, shade: 0.5,time:1000000000});//当生成完成这个对话框才被关掉
                var index = layer.load();
                $.ajax({
                    url: '${ctx}/manage/import/station',
                    type: 'POST',
                    cache: false,
                    data: new FormData(layer.getChildFrame('#form1')[0]),
                    processData: false,
                    contentType: false
                }).done(function (res) {
                    if (res.code == '0') {
                        layer.alert(res.msg, function () {
                            parent.location.reload();
                        });
                    } else if (res.code == '-2') {
                        layer.msg(res.msg, {
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            layer.closeAll();
                        });
                    } else if (res.code == '-1') {
                        layer.close(index);
//                        var index = layer.index; //获取当前弹层的索引号
//                        layer.close(index); //关闭当前弹层
                        var tem = res.data;

                        var dataHtml = "";
                        for (var i = 0; i < tem.length; i++) {
                            dataHtml += "<tr>" +
                                    "<td>" + tem[i].mac + "</td>" +
                                    "<td>" + tem[i].xxwz + "</td>" +
                                    "<td>" + tem[i].szqy + "</td>" +
                                    "<td>" + tem[i].category + "</td>" +
                                    "<td>" + tem[i].classId + "</td>" +
                                    "<td>" + tem[i].msg + "</td>" +
                                    "</tr>";
                        }

                        var html = " <main style='padding:30px 20px;'>" +
                                "<p style='font-size: 13px;margin-bottom: 15px;'>" +
                                "成功<span style='color: #19be9d;' class='green'>" + res.msg + "</span>条，失败<span style='color: #fc2f5b;' class='red'>" + res.data.length + "</span>条" +
                                "</p>" +
                                "<table class='table'>" +
                                "<thead>" +
                                "<tr>" +
                                "<th width='16%'>网关MAC</th>" +
                                "<th width='12%'>详细位置</th>" +
                                "<th width='12%'>所在区域</th>" +
                                "<th width='12%'>网关种类</th>" +
                                "<th width='12%'>关联班级</th>" +
                                "<th width='16%'>失败原因</th>" +
                                "</tr>" +
                                "</thead>" +
                                "<tbody>" +
                                dataHtml +
                                "</tbody>" +
                                "</table>" +
                                "</main>";
                        layer.open({
                            type: 1,
                            title: '批量添加网关失败列表',
                            shadeClose: true,
                            shade: 0,
                            btn: ['确认', '下载失败列表'],
                            area: ['620px', '500px'],
                            content: html,
                            move: false,
                            btn1: function () {
                                parent.location.reload();
                            },
                            btn2: function () {
                                var form=$("<form>");//定义一个form表单
                                form.attr("style","display:none");
                                form.attr("target","");
                                form.attr("method","post");
                                form.attr("action","${ctx}/manage/errorimportstation");
                                var input1=$("<input>");
                                input1.attr("type","hidden");
                                input1.attr("name","msg");
                                input1.attr("value",JSON.stringify(res.data));
                                $("body").append(form);//将表单放置在web中
                                form.append(input1);
                                form.submit();//表单提交
                                return false;
                            }
                        });
                    }
                }).fail(function (res) {
                    layer.msg("模板异常，导入失败！");
                    setTimeout(function () {
                        layer.closeAll();
                    }, 500)
                });


            }
        })
    })
    function delStation(mac) {
        layer.open({
            type:1,
            title:'确认删除',
            btn:['确认','取消'],
            area:['380px','240px'],
            content:'<div style="padding:25px">' +
            '<p style="font-size:13px;">' +
            ' <span style="color:#F6182B;font-size:18px;">确认删除该网关？</span><br><br> '+
            /*'删除角色后，该角色的教师用户必须重新分配角色才可以正常使用系统功能' +*/
            '</p>' +
            '</div>',
            btn1:function(){
                $.post("${ctx}/manage/station/del/"+mac, function (res) {
                    layer.msg(res.msg);
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                })
            }
        })

    }
</script>
</body>
</html>