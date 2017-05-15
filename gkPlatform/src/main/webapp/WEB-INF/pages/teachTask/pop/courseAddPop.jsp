<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<style>
    span{display:inline-block;}
    .popup-main{
        background: #fff;
        padding: 35px 0px 10px 25px;
        font-size: 13px !important;
        color: #525252 !important;
    }
    table td{
        font-size: 13px;
        text-align: right;
        padding: 10px 0;
    }
    table td span{
        width: 88px;
        text-align: right;
    }
    table td input[type="text"],table td select{
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
    table td span:before{
        content: '*';
        color: red;
        vertical-align: middle;
        margin-right: 3px;
    }
</style>
<body>
<form action="${ctx}/teach/task/course/update?type=insert" id="courseAdd" method="post" target="hidden_frame">
    <div>
        <table>
            <tabody>
                <tr>
                    <td><span>课程名称:</span><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>
                        <span>教室类型:</span>
                        <select form="courseAdd" name="roomType">
                            <c:forEach items="${roomTypeList}" var="roomType" varStatus="status">
                            <option>${roomType.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>课程类型:</span>
                        <select form="courseAdd" name="courseType">
                            <c:forEach items="${courseTypePageInfo.list}" var="courseType" varStatus="status">
                            <option name="subjectTypeName"> ${courseType.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span style="margin-top:9px;">学年:</span>
                        <select name="cycleYear">
                            <c:forEach items="${teachCyclePageInfo.list}" var="teachCycle" varStatus="status">
                            <option name="teachYear">${teachCycle.cycleYear}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span style="margin-top:9px;">学期:</span>
                        <select name="semester">
                            <option name="semester" value="1">第一学期</option>
                            <option name="semester" value="1">第二学期</option>
                        </select>
                    </td>
                </tr>
            </tabody>
        </table>
    </div>
</form>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
<script>

    $('#hidden_frame').load(function(){
        var text=$(this).contents().find("body").text();
        // 根据后台返回值处理结果
        var j=$.parseJSON(text);
        if(j.status!=0) {
            alert(j.msg);
        } else {
            alert('导入成功');
            //location.href='BookResourceList.jsp'
        }
    });
    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        if (true) {
            debugger;
            $("#courseAdd").submit();
            return true;
        } else {
            layer.msg("输入有误！");
            return false;
        }
    }

    function doSubmitReturn() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var name = $(".name").val();
        var roomType =$(".roomType").find("option:selected").text();
        var courseType =$(".courseType").find("option:selected").text();
        var cycleSemester =$(".cycleSemester").find("option:selected").text();
        var cycleYear =$(".cycleYear").find("option:selected").text();
        if (cycleYear == "" || cycleSemester == "" || name == "" || roomType == "" || courseType == "") {
            webToast("所填项均为必填", "top", 2300);
        }

        $.post("${ctx}/teach/task/course/update", {
            cycleYear: cycleYear,
            cycleSemester: cycleSemester,
            name: name,
            roomType: roomType,
            courseType: courseType,
            type: "insert"
        }, function (data) {
            if (data.code == 0) {
                webToast(data.msg, "top", 5000);
                setTimeout(function () {
                    parent.location.reload();
                }, 5000);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close(index)
                }, 5000);
                return true;
            } else {
                webToast(data.msg, "top", 5000);
                setTimeout(function () {
                    parent.location.reload();
                }, 5000);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 5000);
                return false;
            }
        });
    }
    //    将checkbox的值存到域中 通过form提交
    $('input[type=checkbox]').change(function () {
        $('#Jszzdm').val($('input[type=checkbox]:checked').map(function () {
            return this.value
        }).get().join(','))
    })
</script>
</html>
