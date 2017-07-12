<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/laydate.js"></script>
    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>
</head>
<style>
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        font-family: "Microsoft YaHei", Arial, STXihei, '华文细黑', 'Microsoft YaHei', SimSun, '宋体', Heiti, '黑体', sans-serif;
    }

    span {
        display: inline-block;
    }

    .popup-main {
        background: #fff;
        padding: 35px 0px 10px 25px;
        font-size: 13px !important;
        color: #525252 !important;
    }

    table td {
        font-size: 13px;
        /*text-align: right;*/
        padding: 10px 0;
    }

    table td span:first-child {
        width: 88px;
        text-align: right;
    }

    table td span:last-child {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 12px;
        padding-left: 10px;
    }

    table td input[type="text"], table td select {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 12px;
        padding-left: 10px;
        outline: none;
        border: 1px solid #dadada;
        border-radius: 2px;
        color: #333;
    }
    i{
        font-style: normal;
        color: #999;
        font-size: 12px;
        margin-left: 10px;
    }

</style>
<body>
<%--<form action="${ctx}/teach/task/cycle/do?id=${teachCycle.id}&&type=update" class="cycleAdd" method="post">--%>
<div>
    <table>
        <tr>
            <td><span>学年:</span><input type="text" name="teachCycle.cycleYear" value="${teachCycle.cycleYear}"
                                       class="cycleYear"><i>请填写如下格式:2016-2017</i></td>
        </tr>
        <tr>
            <td><span>学期:</span><input type="text" name="teachCycle.cycleSemester" value="${teachCycle.cycleSemester}"
                                       class="cycleSemester"><i>填写数字1或2，1表示第一学期</i></td>
        </tr>
        <tr>
            <td><span>开始周:</span><input type="text" name="teachCycle.beginDate" value="${teachCycle.beginDate}"
                                        class="start"><i>填写数字,例如1表示开始周为第一周</i></td>
        </tr>
        <tr>
            <td><span>结束周:</span><input type="text" name="teachCycle.endDate" value="${teachCycle.endDate}" class="end"><i>填写数字,例如1表示结束周为第一周</i>
            </td>
        </tr>
        <tr>
            <td><span>总周次:</span><input type="text" name="teachCycle.weekCount" value="${teachCycle.weekCount}"
                                        class="weekCount"><i>填写数字,例如10表示一共10周</i></td>
        </tr>
    </table>
</div>
<%--</form>--%>
</body>
<script>
    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var cycleYear = $(".cycleYear").val();
        var cycleSemester = $(".cycleSemester").val();
        var start = $(".start").val();
        var end = $(".end").val();
        var weekCount = $(".weekCount").val();
        if (cycleYear == "" || cycleSemester == "" || start == "" || end == "" || weekCount == "") {
            webToast("所填项均为必填", "top", 2300);
            return;
        }
        $.post("${ctx}/teach/task/cycle/do", {
            id: '${teachCycle.id}',
            cycleYear: cycleYear,
            cycleSemester: cycleSemester,
            beginDate: start,
            endDate: end,
            weekCount: weekCount,
            type: "update"
        }, function (data) {
            if (data.code == 0) {
                layer.msg("修改成功");
                setTimeout(function () {
                    parent.location.reload();
                }, 2300);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 2300);
                return true;
            } else {
                layer.msg("操作失败");
                setTimeout(function () {
                    parent.location.reload();
                }, 2300);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 2300);
                return false;
            }
//
        });
    }
</script>
</html>
