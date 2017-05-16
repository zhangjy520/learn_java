<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <link rel="stylesheet" href="${ctxStatic}/css/basicManage.css"/>
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
        <li class="child-nav active"><a href="#">用户管理</a></li>
    </ul>
</header>
<main id="user-manage1" class="module-bg-full fix-width">
    <p style="border-bottom:1px solid #ddd;">教师用户信息
        <button class="rl download" onclick="window.location.href='${ctx}/manage/usertemplate'">下载模板</button>
        <button class="rl import" <%--onclick="openDialog('导入用户','${ctx}/manage/importuser','380px', '240px')"--%>>导入
        </button>
        <button class="rl addNew" onclick="openDialog('添加教师','${ctx}/manage/user/add','380px', '360px')">新增</button>
        <%--<span class="rl">--%>
            <%--<input type="text" class="search" placeholder="教师姓名/教职工号">--%>
            <%--<i class="search-item" style="right:5px;top:5px;" onclick="searchTeacher()"></i>--%>
        <%--</span>--%>
        <button class="research rl" ></button>
        <input type="text" class="rl" placeholder="教师姓名/教职工号" id="search"/>
    </p>
    <div class="sh-use-state">
        <table class="table">
            <thead>
            <tr>
                <th>姓名</th>
                <th>教工号</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="teacher">
                <tr>
                    <td>${teacher.name}</td>
                    <td>${teacher.no}</td>
                    <td>${teacher.roleName}</td>
                    <td>
                        <span class="sh-operation1" onclick="edit(${teacher.id},${teacher.rid},'${teacher.no}')">修改</span>
                        <span class="sh-operation2" onclick="deleteRef(${teacher.userId},${teacher.rid})">删除</span>
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
                    <c:if test="${pageInfo.pages != 0}">
                    $(".fenY").createPage({
                        pageCount: ${pageInfo.pages},
                        current: ${pageInfo.pageNum},
                        backFn: function (p) {
                            window.location.href = "${ctx}/manage/user/index?pageNum=" + p + "&pageSize=" + $('#pgSize').val() + "&search=" + $('.search').val();
                        }
                    });
                    $("#pgSize").change(function () {
                        window.location.href = "${ctx}/manage/user/index?pageNum=" + 1 + "&pageSize=" + $('#pgSize').val() + "&search=" + $('.search').val();
                    });
                    </c:if>
                </script>
            </div>
        </c:if>
    </footer>
</main>
<script>
    $("#basicManage").addClass("active");
    $("#basicManage ul li:nth-child(4)>a").addClass("active");

    function edit(teacherId, roleid, teacherNo) {
        var path = '${ctx}/manage/user/add?teacherId=' + teacherId + '&roleId=' + roleid + '&teacherNo=' + teacherNo;
        openDialog('添加教师', path, '380px', '360px')
    }
    function deleteRef(userid, roleid) {
        layer.open({
            type:1,
            title:'确认删除',
            btn:['确认','取消'],
            area:['380px','240px'],
            content:'<div style="padding:25px">' +
            '<p style="font-size:13px;">' +
            ' <span style="color:#F6182B;font-size:18px;">确认删除该用户？</span><br><br> '+
            /*'删除角色后，该角色的教师用户必须重新分配角色才可以正常使用系统功能' +*/
            '</p>' +
            '</div>',
            btn1:function(){
                $.post("${ctx}/manage/user/delete", {
                    userId: userid,
                    roleId: roleid
                }, function (res) {
                    layer.msg(res.msg);
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                })
            }
        })

    }
    function searchTeacher() {
        window.location.href = "${ctx}/manage/user/index?pageNum=" + 1 + "&pageSize=" + $('#pgSize').val() + "&search=" + $('.search').val();
    }

    $('.import').click(function () {
        layer.open({
            type: 2,
            title: '批量添加教师用户',
            shadeClose: true,
            shade: 0.8,
            btn: ['确认', '取消'],
            area: ['380px', '240px'],
            content: '${ctx}/manage/importuser',
            resize:false,
            move: false,
            btn1: function () {
//                layer.msg('导入中', {icon: 16, shade: 0.5,time:1000000000});//当生成完成这个对话框才被关掉
                var index = layer.load();
                $.ajax({
                    url: '${ctx}/manage/import/station',
                    type: 'POST',
                    cache: false,
                    data: new FormData(layer.getChildFrame('#form')[0]),
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
                                    "<td>" + tem[i].no + "</td>" +
                                    "<td>" + tem[i].roleName + "</td>" +
                                    "<td>" + tem[i].xd + "</td>" +
                                    "<td>" + tem[i].nj + "</td>" +
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
                                "<th width='16%'>教工号</th>" +
                                "<th width='12%'>角色</th>" +
                                "<th width='12%'>学段</th>" +
                                "<th width='12%'>年级</th>" +
                                "<th width='12%'>班级</th>" +
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
                            title: '批量添加教师用户',
                            shadeClose: true,
                            shade: 0,
                            btn: ['确认', '下载失败列表'],
                            area: ['620px', '500px'],
                            content: html,
                            resize:false,
                            move: false,
                            btn1: function () {
                                parent.location.reload();
                            },
                            btn2: function () {
                                var form=$("<form>");//定义一个form表单
                                form.attr("style","display:none");
                                form.attr("target","");
                                form.attr("method","post");
                                form.attr("action","${ctx}/manage/ring/errouser");
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
        });
    })
</script>
</body>
</html>