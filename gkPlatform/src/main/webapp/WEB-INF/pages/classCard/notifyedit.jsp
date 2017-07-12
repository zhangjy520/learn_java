<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script src="${ctxStatic}/upload/h5upload.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>
    <style>
        .container {
            width: 800px;
            margin: 0 auto;
            /*padding-top: 30px;*/
            font: 12px '微软雅黑';
        }

        .container > h3 {
            font-size: 16px;
            font-weight: normal;
            color: #54AB37;
            margin: 20px 0;
            padding: 0px 0 0px 8px;
            border-left: 3px solid #54AB37;
        }

        .container > h3 button {
            float: right;
            color: #fff;
            border-radius: 2px;
            background: #54AB37;
            border: 1px solid #54AB37;
            width: 80px;
            height: 35px;
            font-weight: bold
        }

        .stuMsg {
            overflow: hidden;
        }

        ul {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .left {
            float: left;
            width: 50%;
        }

        .right {
            float: right;
            width: 50%;
        }

        .stuMsg span {
            display: inline-block;
            width: 36%;
            text-align: right;
        }

        .stuMsg input[type=text] {
            width: 190px;
            height: 28px;
            padding:0;
            padding-left:5px;
            border-radius:3px;
            border:1px solid #a9a9a9;
        }

        .stuMsg input[type=radio] {
            margin: 0 20px;
        }

        .stuMsg input[class=laydate-icon] {
            width: 190px;
            border: 1px solid #a9a9a9;
        }

        .stuMsg label {
            margin-right: 61px;
        }

        ul li {
            position: relative;
            margin: 15px 0;
        }

        b {
            font-size: 20px;
            color: #E51C23;
            position: absolute;
            top: 4px;
            right: 36px;
        }

        .radio b {
            top: -3px;
        }

        .stuMsg select {
            font-size: 14px;
            color: #999;
            width: 197px;
            height: 28px;
            padding-left: 5px;
            border: 1px solid #a9a9a9;
            border-radius: 4px;
        }

        .uploading {
            display: inline-block;
            vertical-align: -600%;
            width: 60%;
            text-align: center;
        }

        .uploading p {
            width: 90px;
            height: 86px;
            background: url('${ctxStatic}/image/image.png');
            margin: 0;
            margin-left: 104px;
        }

        .uploading button {
            margin-top: 10px;
            padding: 5px 20px;
            color: #fff;
            background: #54AB37;
            border: 1px solid #54AB37;
            font-weight: bold;
        }

        .parentMsg P {
            FONT-SIZE: 14PX;
            color: #666;
            padding-left:11px;
        }

        .parentMsg ul {
            overflow: hidden;
            box-sizing: border-box;
        }

        .parentMsg ul li {
            float: left;
            width: 50%;
            margin: 15px 0
        }

        .parentMsg ul li span {

            display: inline-block;
            width: 36%;
            text-align: right;
        }

        .parentMsg input[type=text] {
            height: 28px;
            padding:0;
            padding-left:5px;
            width:190px;
            border:1px solid #a9a9a9;
            border-radius: 3px;
            outline: none;
        }

        .parentMsg select {
            width: 190px;
            height: 28px;
            border-radius: 4px;
            outline: none;
        }

        .container table {
            border-collapse: collapse
        }

        .container table th, .container table td {
            border: 1px solid #ddd;
            padding: 10px 0;
        }

        .container table th {
            background: #eee;
        }

        .addContent {
            font-size: 16px;
            color: #999;
            padding-left: 15px;
        }
    </style>
    <script>
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            var title=$('#title').val();
            var type=$('#notifyType').val();
            var content =$('#content').val();

            if (title == '') {
                layer.msg("标题不能为空！");
                return false;
            }

            if (content == '') {
                layer.msg("内容不能为空！");
                return false;
            }

            var url="${ctx}/classCard/notify/save"
            $.post(url, {
                title:title,
                type:type,
                content:content,
                checkedIds:$('#checkedIds').val(),
                unCheckedIds:$('#unCheckedIds').val()
            }, function (retJson) {
                if (retJson.code == '0') {
                    window.parent.location.reload(true);
                } else {
                    layer.msg(retJson.msg);
                }
            });
            return true;
        }
    </script>
</head>
<body>
<form action="${ctx}/classCard/save">
</form>

    <div class="container">
        <h3>通知信息</h3>
        <div class="stuMsg">
            <ul class="left">

                <li>
                    <span>标题：</span>
                    <input ${disabled} type="text" id="title" name="title" placeholder="请输入标题" value="${classCardNotify.title}"/>
                </li>

                <li >
                    <c:if test="${disabled!='disabled'}">
                        <span>选择设备：</span>
                    </c:if>
                    <c:if test="${disabled=='disabled'}">
                        <span>查看设备：</span>
                    </c:if>
                    <button id="selectButton" class="roll-add" style="height: 30px;padding: 0 15px;margin-right: 4px;border:
                    1px solid #54ab37; background: #54ab37; color: #fff;border-radius: 2px;"
                            <c:if test="${disabled=='disabled'}">
                                  onclick="openDialogView('查看','${ctx}/classCard/chooseClassCard?disabled=disabled&checkedIds=${checkedIds}','800px','500px');">查看
                            </c:if>
                            <c:if test="${disabled!='disabled'}">
                                onclick="openDialog('选择','${ctx}/classCard/chooseClassCard','800px','500px');" >选择
                            </c:if>
                    </button>
                    <span id="checkedEquipment"></span>
                    <input type="hidden" value="" id="checkedIds">
                    <input type="hidden" value="" id="unCheckedIds">
                </li>
            </ul>
            <ul class="right">
                <li>
                    <span>通知类型：</span>
                    <select ${disabled} id="notifyType">
                        <option value="0"  <c:if test="${classCardNotify.type==0}"> selected </c:if>>校园通知</option>
                        <option value="1"  <c:if test="${classCardNotify.type==1}"> selected </c:if>>班级通知</option>
                    </select>
                </li>
            </ul>
        </div>
        <div>
            <ul>
                <li>
                    <span>内容：</span>
                    <textarea ${disabled} rows="10" cols="100" placeholder="请输入内容" id="content" name="content" value="${classCardNotify.content}">${classCardNotify.content}</textarea>
                </li>
            </ul>
        </div>
    </div>
<script>
    function chooseResult(checkedIds, unCheckedIds,checkedName) {
        $("#checkedEquipment").empty();
        var names = "";
        var checkedName = checkedName.split(",");
        for (var i = 0; i < checkedName.length; i++) {
            if (i<5&&checkedName[i].trim() != "") {
               // names += "<span class=''>" + checkedName[i] + "</span>";
                names+=checkedName[i]+"  --   ";
            }
            if(i==6){
                names+="...";
            }
        }
        $('#checkedIds').val(checkedIds);
        $('#unCheckedIds').val(unCheckedIds);
        $("#checkedEquipment").append(names);
        $("#selectButton").attr("onclick","openDialog('选择','${ctx}/classCard/chooseClassCard?checkedIds="+checkedIds+"','800px','500px')");
    }

</script>
</body>
</html>