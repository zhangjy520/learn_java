<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>班级管理</title>
    <link rel="stylesheet" href="${ctxStatic}/css/sportstest.css"/>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
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
        <li class="child-nav  active"><a href="${ctx}/sport/class/index">班级管理</a></li>
    </ul>
</header>

<div id="test4" class="fix-width module-bg" >
    <header class="clear">
        <aside class="lf">
            <p>班级列表</p>
        </aside>
        <section class="rl">
            <div class=" hiddenDelete" style="display:none; float: left;" id="hiddenDelete" name="hiddenDelete"><span class="delete" id="deletebatchItem" type="hidden" style="padding: 0 8px;line-height: 28px;font-size: 12px;margin-left: 5px;">删除</span></div>
           <span class="changeadd" onclick="window.location.href='${ctx}/sport/view/into?sportClassId=${sportClass.sport_class_id}'">添加班级</span>
        </section>
    </header>
    <div>
        <table class="table">
            <thead>
            <tr>
                <td><input type="checkbox" id="selectAll"></td>
                <th width="20%">班级</th>
                <th width="20%">所属年级</th>
                <th width="20%">项目</th>
                <th width="15%">授课教师</th>
                <th width="10%">学生人数</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${gukeer:notEmptyString(sportClassList)}">
                <c:forEach items="${sportClassList}" var="sportClass">
                    <tr>
                        <td><input type="checkbox" id="${sportClass.sport_class_id}" name="box" value="${sportClass.sport_class_id}" onclick="countTotal()"></td>
                        <input name="sportClassId" value="${sportClass.sport_class_id}" type="hidden">
                        <td>${sportClass.sport_class_name}</td>
                        <td>${sportClass.njxd}</td>
                        <td>${sportClass.item_name}</td>
                        <td>${sportClass.teacherName}</td>
                        <td>${sportClass.classCount}</td>
                        <td>
                            <span class="change" onclick="window.location.href='${ctx}/sport/view/into?sportClassId=${sportClass.sport_class_id}'">修改</span>
                            <span class="delete" onclick="deleteSportClass('${sportClass.sport_class_id}')">删除</span>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    <footer class="pagination clear">
        <div class="lf">
            共${pageInfo.total}条记录，每页显示
            <select id="pageSize">
                <option <c:if test="${pageSize ==10 }"> selected</c:if> value="10">10</option>
                <option <c:if test="${pageSize ==20 }"> selected</c:if> value="20">20</option>
                <option <c:if test="${pageSize ==50 }"> selected</c:if> value="50">50</option>
            </select>
            条记录
        </div>
        <div class="rl fenY">
            <script>
                <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                $(".fenY").createPage({
                    pageCount:${pageInfo.pages},//总页数
                    current:${pageInfo.pageNum},//当前页面
                    backFn: function (p) {
                        pageTap(p);
                    }
                });
                </c:if>
            </script>
        </div>
    </footer>

</div>
</main>
<script>

    $("input[name='box']").click(function() {
        var $subs = $("input[name='box']");
        $("#selectAll").prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
    });


    $("select").change(function () {
        if ($(this).attr("id") != "itemUnitAdd") {
            if ($(this).attr("id") != "pageSize") {
                $("#stuNameOrNo").val("");
            }
            pageTap(1);
        }
    });
    function pageTap(p) {
        var pageSize = $("#pageSize option:selected").text();
        window.location.href = "${ctx}/sport/class/index?pageSize=" + pageSize + "&pageNum=" + p ;
    }

    function deleteSportClass(data) {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该班级？</p>',
            move: false,
            btn1: function () {
                $.get("${ctx}/sport/class/delete", {
                    sportClassId: data
                },function (retJson) {
                    layer.msg("删除成功", {
                        time: 1000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        window.location.reload(true);
                    });
                });
            }
        })
    }
    $(function() {
        $("#selectAll").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
                hiddenDelete.style.display="block";
            } else {
                $("input[type='checkbox']").prop("checked", false);
                hiddenDelete.style.display="none";
            }

        });

    })

    function countTotal(){
        var count = 0;

        $.each($("input[name='box']:checked"), function() {
            count = count + 1;
        });
        if(count>1){
            hiddenDelete.style.display="block";
        }else {
            hiddenDelete.style.display="none";
        }

    }

    $('#deletebatchItem').click(function () {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该班级？</p>',
            move: false,
            btn1: function () {
                var inputs = $("input[name='box']:checked");
                var tempIds = 0;
                inputs.each(function () { // 遍历选中的checkbox
                    var singleId = $(this).val();
                    tempIds += "," + singleId;
                });
                $.get(postPath + "/sport/class/delete", {
                    sportClassId: tempIds
                }, function (retJson) {
                    layer.msg("ok", {
                        time: 1000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        window.location.reload(true);
                    });
                })
            }
        })
    })
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

</script>
</body>
</html>