<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/headerXf.jsp" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级教室编辑</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jquery.autocomplete.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>

    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
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
    table{
        margin-top: 20px;
    }
    table td {
        font-size: 13px;
        /*text-align: right;*/
        padding: 10px 0;
    }

    table td span:first-child {
        width: 88px;
        /*text-align: right;*/
    }

    table td span:last-child {
        /*width: 150px;*/
        /*height: 28px;*/
        /*line-height: 28px;*/
        /*margin-left: 12px;*/
        /*padding-left: 10px;*/
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
    .cla-sp{
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 14px;
    }
    #zh-manage .search-box{
        margin-top: 0 !important;
    }
    #zh-manage .search-box ul{
        border: none;
    }
    .stu-num-manage-menu{
        display: inline-block;
    }
    .stu-num-manage-menu ul{
        margin-bottom: 0 !important;
        height: auto !important;
    }
    .roll-operation{
        vertical-align: middle !important;
    }
    .btn-containt button{
        border: 1px solid #54ab37;
        border-radius: 2px;
        background: #54AB37;
        color: #fff;
        width: 70px;
        height: 30px;
        line-height: 30px;
        margin-top: 20px;
    }
</style>
<div>
    <%@ include file="../../common/sonHead/teachTaskHead.jsp" %>
    <main class="container" id="zh-manage">
    <table>
        <input type="hidden" value="${refId}" class="refId">
        <tr>
            <td><span>年级班级:</span><span class="cla-sp">${refClassRoomView.sectionName}${refClassRoomView.nj}年级${refClassRoomView.banji}</span></td>
        </tr>
        <tr>
            <td><span>校区:</span>
                <select name="schoolType" class="schoolType">
                    <c:forEach items="${schoolTypeList}" var="schoolType" varStatus="status">
                        <option  <c:if test="${schoolTypeId==schoolType.id}">selected</c:if> value="${schoolType.id}">${schoolType.name}</option>
                    </c:forEach>
                </select></td>
        </tr>
        <tr>
            <td><span>教学楼:</span>
                <select name="teachBuilding" class="teachBuilding">
                    <c:forEach items="${buildingList}" var="building" varStatus="status">
                        <option  value="${building.teachBuilding}" <c:if test="${building==building.teachBuilding}">selected</c:if>>${building.teachBuilding}</option>
                    </c:forEach>
                </select></td>
        </tr>
        <tr>
            <td><span>教室号:</span>
                <select name="roomNum" class="roomNum">
                    <c:forEach items="${roomNumList}" var="classRoom" varStatus="status">
                        <option  <c:if test="${roomId==classRoom.id}">selected</c:if> value="${classRoom.id}">${classRoom.roomNum}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <div class="btn-containt">
        <button onclick="confirmSave()">确定</button>
    </div>
    </main>
</div>
</body>
<script>
    $(function () {
        $("select").change(function () {
            var refId = $(".refId").val();
            var schoolTypeId =$(".schoolType").find("option:selected").val();
            var teachBuilding =$(".teachBuilding").find("option:selected").val();
            var roomId =$(".roomNum").find("option:selected").val();

            window.location.href = "${ctx}/teach/task/ref/class/room/edit/pop?&&roomId="+roomId+"&&refId="+refId+"&&schoolTypeId="+schoolTypeId+"&teachBuilding="+teachBuilding+"&";
        });
    })
    function confirmSave() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var refId = $(".refId").val();
        var schoolTypeId =$(".schoolType").find("option:selected").val();
        var teachBuilding =$(".teachBuilding").find("option:selected").val();
        var roomId =$(".roomNum").find("option:selected").val();
        $.post("${ctx}/teach/task/ref/class/room/edit", {
            refId: refId,
            schoolTypeId:schoolTypeId,
            teachBuilding: teachBuilding,
            roomId: roomId,
            refId:refId
        }, function (data) {
            layer.msg("保存成功");
           location.reload();
//            if (data.code == 0) {
//                webToast(data.msg, "top", 5000);
//                setTimeout(function () {
//                    parent.location.reload();
//                }, 5000);
//                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
//                setTimeout(function () {
//                    top.layer.close(index)
//                }, 5000);
//                return true;
//            } else {
//            window.reloadPage();
//                return false;
//            }
        });
    }
</script>
</html>

