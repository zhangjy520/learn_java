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
    .cla-type-containt{
        width: 380px;
        margin-left: 50px;
    }
    .cla-type-containt span{
        width: 102px;
        line-height: 30px;
        font-size: 12px;
    }
    .cla-type-containt input{
        margin-right: 5px;
        position: relative;
        top:2px;
    }
</style>
<body>
<%--<form action="${ctx}/teach/task/cycle/do" class="cycleAdd" method="post">--%>
<div>
    <table>
        <tr>
            <td><span>学年:</span><input type="text" name="cycleYear" class="cycleYear" required><i>请填写如下格式:2016-2017</i></td>
        </tr>
        <tr>
            <td><span>学期:</span><input type="text" name="teachCycle.cycleSemester" class="cycleSemester" required><i>填写数字1或2，1表示第一学期</i></td>
        </tr>
        <tr>
            <td><span>开始周:</span>
                <input class="start" type="text" name="beginDate"  style="margin-left: 8px;" required><i>填写数字,例如1表示开始周为第一周</i>
                <%--<span class="laydate-icon" onclick="laydate({elem: '#hello1'});"></span>--%>
            </td>
        </tr>
        <tr>
            <td><span>结束周:</span><input class="end" type="text" name="beginDate" required><i>填写数字,例如1表示结束周为第一周</i></td>
        </tr>
        <tr>
            <td><span>总周次:</span><input type="text" name="weekCount" class="weekCount" required><i>填写数字,例如10表示一共10周</i></td>
        </tr>
    </table>
</div>
<div  class="cla-type-containt">
    <input type="hidden" value="${cycleId}" class="cycleId">
    同步数据 <i>（若没有上一学期数据,请不要勾选）</i><br>
    <input type="checkbox" class="room" value="教室管理"><span>教室管理</span>
    <input type="checkbox"  class="course" value="课节设置"><span>课节设置</span>
    <input type="checkbox"  class="course" value="课程安排"><span>课程安排</span>
    <input type="checkbox" class="master" value="班主任安排"><span>班主任安排</span>
    <input type="checkbox" class="courseTeacher" value="任课教师安排"><span>任课教师安排</span>
    <input type="checkbox" class="room" value="班级教室安排"><span>班级教室安排</span>
    <input type="checkbox" class="room" value="科目课时安排"><span>科目课时安排</span>
</div>
</body>
<script>
    function doSubmit() {
        var synInfo = "";
        var inputs =  $('input[type=checkbox]:checked');
//        console.log(inputs.length);
        $('input[type=checkbox]:checked').each(function (i) {
            var oneText = $(this).val();
            synInfo +=","+oneText;
        })
        var cycleId = $(".cycleId").val();//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
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
            cycleYear: cycleYear,
            cycleSemester: cycleSemester,
            beginDate: start,
            endDate: end,
            weekCount: weekCount,
            synInfo:synInfo,
            cycleId:cycleId
        }, function (data) {
            if (data.code == 0) {
                webToast(data.msg, "top", 5000);
                setTimeout(function(){parent.location.reload();}, 5000);/*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function(){top.layer.close()}, 5000);
                return true;
            } else {
                webToast(data.msg, "top", 5000);
                setTimeout(function(){parent.location.reload();}, 5000);/*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function(){top.layer.close()}, 5000);
                return false;
            }
        });
    }
</script>
</html>
