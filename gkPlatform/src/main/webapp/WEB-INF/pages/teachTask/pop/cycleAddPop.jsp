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
</style>
<body>
<%--<form action="${ctx}/teach/task/cycle/do" class="cycleAdd" method="post">--%>
<div>
    <%--<ul>--%>
    <%--<li><span>学年:</span><input type="text" name="teachCycle.cycleYear"></li>--%>
    <%--<li><span>学期:</span><input type="text" name="teachCycle.cycleSemester"></li>--%>
    <%--<li><span>开始:</span><input type="text" name="teachCycle.beginDate"></li>--%>
    <%--<li><span>结束:</span><input type="text" name="teachCycle.endDate"></li>--%>
    <%--<li><span>总周次:</span><input type="text" name="teachCycle.weekCount"></li>--%>
    <%--</ul>--%>
    <table>
        <tr>
            <td><span>学年:</span><input type="text" name="cycleYear" class="cycleYear"></td>
        </tr>
        <tr>
            <td><span>学期:</span><input type="text" name="teachCycle.cycleSemester" class="cycleSemester"></td>
        </tr>
        <tr>
            <td><span>开始:</span>
                <input class="hello laydate-icon start" name="beginDate" onclick="laydate()">
                <%--<span class="laydate-icon" onclick="laydate({elem: '#hello1'});"></span>--%>
            </td>
        </tr>
        <tr>
            <td><span>结束:</span><input class="hello laydate-icon end" name="beginDate" onclick="laydate()"></td>
        </tr>
        <tr>
            <td><span>总周次:</span><input type="text" name="weekCount" class="weekCount"></td>
        </tr>
    </table>
</div>
<div>
    <input type="hidden" value="${cycleId}" class="cycleId">
    <input type="checkbox" class="room" value="教室管理"><span>教室管理</span>
    <input type="checkbox"  class="course" value="课节设置"><span>课节设置</span>
    <input type="checkbox"  class="course" value="课程安排"><span>课程安排</span>
    <input type="checkbox" class="master" value="班主任安排"><span>班主任安排</span>
    <input type="checkbox" class="courseTeacher" value="任课教师安排"><span>任课教师安排</span>
    <input type="checkbox" class="room" value="班级教室安排"><span>班级教室安排</span>
    <input type="checkbox" class="room" value="科目课时安排"><span>科目课时安排</span>
</div>
<%--</form>--%>
</body>
<script>
    function doSubmit() {
        var synInfo = "";
        var inputs =  $('input[type=checkbox]:checked');
        console.log(inputs.length);
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
                setTimeout(function(){top.layer.close(index)}, 5000);
                return true;
            } else {
                webToast(data.msg, "top", 5000);
                setTimeout(function(){parent.location.reload();}, 5000);/*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function(){top.layer.close()}, 5000);
                return false;
            }
        });
    }
    $('input[type=checkbox]').change(function () {
        $('#Jszzdm').val($('input[type=checkbox]:checked').map(function () {
            return this.value
        }).get().join(','))
    });

    laydate({
        elem: '.hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    });
    //    将checkbox的值存到域中 通过form提交


    var start = {
        elem: '.start',
        format: 'YYYY/MM/DD hh:mm:ss',
        min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '.end',
        format: 'YYYY/MM/DD hh:mm:ss',
        min: laydate.now(),
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
</script>
</html>
