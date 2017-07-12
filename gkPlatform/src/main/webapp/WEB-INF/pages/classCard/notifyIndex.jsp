<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>班牌管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>
</head>
<body>

<%@ include file="../common/sonHead/classCardHead.jsp" %>

<main class="container">
    <!--班牌管理-->
    <div class="row" id="stu-manage">
        <%--<aside class="col-xs-3">
            <div class="tree1">
                <ul id="tree1" class="ztree"></ul>
            </div>
        </aside>--%>
        <main class="col-xs-9">
            <div class="search-box">
                <div class="roll-operation">
                    <select class="notifyType">
                        <option value="-1" <c:if test="${type==-1}">selected</c:if>>所有通知</option>
                        <option value="0" <c:if test="${type==0}">selected</c:if>>校园通知</option>
                        <option value="1" <c:if test="${type==1}">selected</c:if>>班级通知</option>
                    </select>
                    <button class="roll-add"
                            onclick="openDialog('新增','${ctx}/classCard/notify/edit','900px','700px');">新增
                    </button>
                    <button class="roll-delete" onclick="ifChoose()">删除</button>
                </div>
                <div class="roll-research">
                    <button class="summitButton"></button>
                    <input type="text" placeholder="请输入标题" name="studentname" value="${title}" class="searchInput"/>
                </div>
            </div>
            <div>
                <table>
                    <style>table th:nth-child(2), table td:nth-child(2), table th:nth-child(1), table td:nth-child(1) {
                        text-align: center;
                    }</style>
                    <thead>
                    <tr>
                        <th width="4%"><input type="checkbox" id="choseAll"/></th>
                        <th width="6%">序号</th>
                        <th width="10%">标题</th>
                        <th width="20%">发布时间</th>
                        <th width="6%">发布人</th>
                        <th width="18%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="notifyView" varStatus="status">
                        <tr>
                            <td>
                                <input name="chose" class="chose" type="checkbox" value="${notifyView.id}"/>
                            </td>
                            <td>
                                    ${status.index + 1 +(pageInfo.pageNum-1)*10}
                            </td>
                            <td>
                                    ${notifyView.title}
                            </td>
                            <td>
                                    ${notifyView.publishTime}
                            </td>
                            <td>
                                    ${notifyView.creatorName}
                            </td>
                            <td>
                    <span
                            onclick="openDialogView('查看','${ctx}/classCard/notify/edit?disabled=disabled&id=${notifyView.id}','900px','650px');">查看</span>
                                <span
                                        onclick="alertTips('400px','200px','删除通知','确定要删除标题为”${notifyView.title}“的通知吗？','notifyDelete(\'${notifyView.id}\')')"> 删除 </span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="fenye">
                <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                    <div class="fenyDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                </c:if>
                <div class="fenY" id="fenY">
                    <%--<input type="text"/>--%>
                </div>
            </div>
        </main>
    </div>

</main>


<form id="submit-form" method="post" action="${ctx}/classCard/notify/index">
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
    <input type="hidden" name="title" value="${title}">
    <input type="hidden" name="type" value="${type}">
</form>

<script>

/*
    function toggle(tag) {
        if (tag == $('.list li.active')) return;
        var list = $('.list li'), index = zNodes0
        for (var i = 0; i < list.length; i++) {
            if (tag == list[i]) {
                index = i;
            } else {
                $(list[i]).removeClass('active')
            }
        }
        $(list[index]).addClass('active');
        $.fn.zTree.init($("#tree1"), setting, eval('zNodes' + index));

    }*/

    $(".summitButton").click(function () {
        var title = $(".searchInput").val();
        var type= $('.notifyType').val();
        $("input[name='title']").val(title);
        $("input[name='type']").val(type);

        $("form").submit();
    });

    <c:if test="${pageInfo.pages != 0}">
    $(".fenY").createPage({
        pageCount:${pageInfo.pages},//总页数
        current:${pageInfo.pageNum},//当前页面
        backFn: function (p) {
            $("input[name='pageNum']").val(p);
            $("form").submit();
        }
    });
    $('.gotoPage').click(function () {
        var p = $('.go').val();
        if (p <= 0 || p >${pageInfo.pages}) {
            layer.msg("请输入正确的页码")
        } else {
            $("input[name='pageNum']").val(p);
            $("form").submit();
        }

    })

    </c:if>


    $(".notifyType").change(function () {
        $("input[name='type']").val($(".notifyType").val());
        var title = $(".searchInput").val();
        $("input[name='title']").val(title);
        $("form").submit();
    });

    function notifyDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/classCard/notify/multiDelete", {
            notifyId: id+","
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg(retJson.msg);
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除

    }

    function ifChoose() {
        if ($('input:checkbox[name=chose]:checked').length > 0) {
            alertTips('400px', '200px', '删除通知', '确定要删除选中的' + $('input:checkbox[name=chose]:checked').length + '个通知吗？', 'choose()')
        }
        else {
            layer.msg("请选择通知")
        }
    }

    function choose() {
        closeAlertDiv();
        var spCodesTemp = "";
        $('input:checkbox[name=chose]:checked').each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).val();
            } else {
                spCodesTemp += ("," + $(this).val());
            }
        });
        $.post("${ctx}/classCard/notify/multiDelete", {
            notifyId: spCodesTemp
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg(retJson.msg);
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除
    }


    var allBtn = $('#choseAll');
    var normalBtn = $('.chose');
    //    console.log(allBtn.checked);
    $(allBtn).click(function () {
        if (this.checked == true) {
            for (var i = 0; i < normalBtn.length; i++) {
                normalBtn[i].checked = true;
            }
        } else {
            for (var i = 0; i < normalBtn.length; i++) {
                normalBtn[i].checked = false;
            }
        }
    })

</script>
</body>
</html>

