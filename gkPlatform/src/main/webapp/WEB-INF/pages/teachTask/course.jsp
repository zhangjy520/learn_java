<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>人员管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>

<main class="container" id="zh-manage">
    <div class="search-box">
        <div class="roll-research">
            学年：
            <select name="cycleYear">
                <c:forEach items="${yearList}" var="year">
                    <option name="cycleYear"
                            <c:if test="${cycleYear ==year}">selected</c:if>  >${year}</option>
                </c:forEach>
            </select>
            学期：
            <select name="cycleSemester" class="cycleSemester">
                <c:forEach items="${semesterList}" var="cycle">
                    <option name="cycleSemester" value="${cycle.cycleSemester}"
                            <c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if>>${cycle.cycleSemester}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="roll-operation">
            <button class="roll-add"
                    onclick="openDialog('新增','${ctx}/teach/task/course/pop?type=add','500px','400px');">课程新增
            </button>
        </div>
        <div class="roll-teatypemanage">
            <button onclick="openCourseType('课程类型管理','${ctx}/teach/task/course/type/pop','500px','352px');">课程类型管理
            </button>
        </div>
    </div>
    <div class="stu-num-manage-menu">
    </div>
    <section id="generated" class="row">
        <div class="row">
            <table class="normal check">
                <style>
                    table th:first-child, table td:first-child {
                        text-align: center;
                    }
                </style>
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>课程名称</th>
                    <th>课程类型</th>
                    <th>教室类型</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${coursePageInfo.list}" var="course" varStatus="status">
                    <tr>
                        <td>${status.index+1+(coursePageInfo.pageNum-1)*10}</td>
                        <td>${course.name}</td>
                        <td>${course.courseType}</td>
                        <td>${course.roomType}</td>
                        <td><span onclick="openDialog('编辑课程',
                                '${ctx}/teach/task/course/pop?id=${course.id}&&type=edit','500px','360px');">编辑</span>
                            <span onclick="openDialog('授课班级',
                                    '${ctx}/teach/task/course/class/pop?id=${course.id}','500px','352px');">授课班级</span>

                            <span value="${course.id}"
                                  onclick="alertTips('400px','200px','删除课程','确定要删除${course.name}课程吗？','deleteSure(\'${course.id}\')')"> 删除</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:98.5%;padding-left:15px;">
            <div class="fenYDetail">共${coursePageInfo.total}条记录，本页${coursePageInfo.size}条</div>
            <div class="fenY2" id="fenY2"></div>
        </div>
    </section>
</main>
<script>

    $(function () {
        $("select").change(function () {
            var cycleSemester = $("select[name='cycleSemester']").val();
            var cycleYear = $("select[name='cycleYear']").val();
            window.location.href = "${ctx}/teach/task/course/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });

        $(".fenY").createPage({
            pageCount:${coursePageInfo.pages},//总页数
            current:${coursePageInfo.pageNum},//当前页面
            backFn: function (p) {
                var semester = $("select[name='semester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/course/index?pageNum=" + p + "&year=" + cycleYear + "&semester=" + semester;
            }
        });


        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });

        $(".gotoPage").click(function () {
            var pageNum = $(".fenY2go").val();
            if (pageNum <= 0 || pageNum >${coursePageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                var semester = $("select[name='semester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/course/index?pageNum=" + $(".fenY2go").val() + "&year=" + cycleYear + "&semester=" + semester;
            }
        });

    });
    function reSetPass(id) {
        $.post("${ctx}/renshi/account/password/update", {
            id: id,
            password: '${password}',
        }, function (retJson) {

        }, "json");
        closeAlertDiv();
        layer.msg('重置成功', {
            time: 2000 //2秒关闭（如果不配置，默认是3秒）
        }, function () {
            parent.location.reload();
        });
    }

    function createSure() {
        closeAlertDiv();
        layer.msg('正在生成，请稍侯', {icon: 16, shade: 0.5, time: 100000000});//当生成完成这个对话框才被关掉
        $.ajax({
            type: "post",
            url: "${ctx}/renshi/account/add",
            data: {
                nameRule: $("#nameRule").val(),
                passRule: $("#passRule").val(),
                password: '${password}',
            },
            dataType: "json",
            success: function (data) {
                //alert(data);
            },
            error: function (e) {
                layer.msg('生成完毕', {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function () {
                    parent.location.reload();
                });
            }
        });
    }

    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    //删除用户
    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/teach/task/course/update", {
            id: id,
            type: "delete"
        }, function (retJson) {
        }, "json");
        setTimeout(function () {
            layer.msg('删除成功', {
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                window.location.reload();
            });
        }, 300)
    }


    function openCourseType(title, url, width, height, target) {
        layer.open({
            type: 2,
            area: [width, height],
            title: title,
            maxmin: false, //开启最大化最小化按钮
            content: url,
            scrollbar: false,
            btn: ['确定', '关闭'],
            yes: function (index, layero) {
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                var inputForm = body.find('#inputForm');
                var top_iframe;
                /*if(target){
                 top_iframe = target;//如果指定了iframe，则在改frame中跳转
                 }
                 inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示*/
                debugger;
                var add = body.find('.add').val();
                console.log(add);
                if (add.length > 0) {
                    $.get(postPath + "/teach/task/course/type/add", {
                        name: add
                    }, function (data) {
//                        alert("ssssss");
                    })
                } else {
                    if (iframeWin.contentWindow.doSubmit()) {
                        //debugger;
                        setTimeout(function () {
                            parent.location.reload();
                        }, 400);
                        /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                        setTimeout(function () {
                            top.layer.close(index)
                        }, 300);//延时0.1秒，对应360 7.1版本bug
                    }
                }
                setTimeout(function () {
                    parent.location.reload();
                }, 400);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close(index)
                }, 300);//延时0.1秒，对应360 7.1版本bug
            },
            cancel: function (index) {
                top.layer.close(index);
            }
        });
    }
</script>
</body>
</html>