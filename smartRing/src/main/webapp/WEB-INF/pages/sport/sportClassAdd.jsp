<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <c:set var="ctxStatic" value="${pageContext.request.contextPath}/assets"/>
    <link rel="stylesheet" href="${ctxStatic}/css/sportstest.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/jquery.autocomplete.css"/>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
    <script src="${ctxStatic}/js/jquery.chained.min.js"></script>
    <script src="${ctxStatic}/js/jquery.autocomplete.js"></script>


        <style>
        .main{
            margin-top: 100px;

        }
        .main>div{
            width: 1140px;
            margin: 0 auto;
            background: #fff;
            padding: 25px 65px;
            border-top: 1px solid #e7eaec;
        }

        #test1 table tr td:first-child{
            width: 85px;
            text-align: left;
        }
        #test1 table{
            font-size: 14px;
            color: #676767;
        }
        #test1 table tr:last-child{
            border-top: 1px solid #ddd;
        }
        #test1 table input[type='text'],#mapItemName{
            width: 200px;
            height: 30px;
            outline: none;
            color: #676767;
            border: 1px solid #e5e6e7;
            font-size: 13px;
            border-radius: 2px;
        }
        #test1 table tr td{
            padding: 12px 0;
            border: none;
        }
        table .chec span{
            display: inline-block;
            margin: 8px 40px 8px 0px;
            vertical-align: middle;
        }
        #test1 table .chec span input{
            vertical-align: middle;
            margin-right: 5px;
            -webkit-appearance: none;
            background: url(${ctxStatic}/images/chec_o.png) no-repeat;
            height: 12px;
            width: 12px;
        }
        #test1 table .chec span input[type="checkbox"]:checked{
            background: url('${ctxStatic}/images/chec_t.png') no-repeat;
        }

        #test1 table .add{
            width: 64px;
            height: 30px;
            line-height: 30px;
            text-align: center;
            color: #fff;
            background: #19be9d;
            font-size: 13px;
            border: none;
            outline: none;
            border-radius: 2px;
            cursor: pointer;
        }
        #test1 table button{
            outline: none;
            border: none;
            border-radius: 2px;
        }

        #test1 table .sub button{
            width: 96px;
            height: 40px;
            line-height: 40px;
            taxt-align: center;
            color: #fff;
            font-size: 13px;
        }
        #test1 table .yes{
            background: #19be9d;
            margin-right: 46px;
        }
        #test1 table .no{
            background: #fc2f5b;
        }
        #test1 table .no a{
            color: #fff;
            display: inline-block;
            width: 100%;
        }
    </style>


    <header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
        <ul class="lf breadcrumb clear">
            <%-- <li class="root-nav"><a href="${ctx}/sport/test/index">体育测试</a></li>--%>
            <li class="root-nav"><a href="#">基础管理</a></li>
            <li class="root-nav"><a href="${ctx}/sport/class/index">班级管理</a></li>
            <li class="child-nav  active"><a href="#">添加体育班级</a></li>
        </ul>
    </header>
</head>
<body>


<div class="main">
    <div id="test1">

        <table class="table">
            <tr><input id="getStudentId" style="display: none"></tr>
            <tr><input id="getStudentName" style="display: none"></tr>
                <tr>
                    <input value="${sportClassId}" name="sportClassId" style="display: none">
                    <td>班级名称</td>
                    <td>
                        <c:if test="${empty sportClassById}">
                            <input type="text" id="sportClassName" name="sportClassName" placeholder="填写班级名称"
                                    value="${sportClassById.sport_class_name}">
                        </c:if>
                        <c:if test="${not empty sportClassById}">
                            <input type="text" id="sportClassName" name="sportClassName" placeholder="填写班级名称"
                                    value="${sportClassById.sport_class_name}">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>所属年级</td>
                    <td class="chec">
                        <c:if test="${empty sportClassById}">
                            <c:if test="${gukeer:notEmptyString(mapList)}">
                                <c:forEach items="${mapList}" var="map">
                                   <span> <input type="checkbox" name="box" value="${map.xd},${map.njInt}">${map.name}${map.nj}</span>
                                </c:forEach>
                            </c:if>
                        </c:if>
                        <c:if test="${not empty sportClassById}">
                            <c:if test="${gukeer:notEmptyString(mapList)}">
                                <c:forEach items="${mapList}" var="map">
                                    <span><input type="checkbox" name="box" value="${map.xd},${map.njInt}">${map.name}${map.nj}</span>
                                </c:forEach>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>项目</td>
                    <td>
                        <c:if test="${empty sportClassById}">
                            <select id="mapItemName" >
                                <c:forEach items="${sportItemList}" var="sportItem">
                                    <option value="${sportItem.itemId}">${sportItem.itemName}</option>
                                </c:forEach>
                            </select>
                        </c:if>

                        <c:if test="${not empty sportClassById}">
                            <select id="mapItemName" >
                                <c:forEach items="${sportItemList}" var="sportItem">
                                    <option <c:if test="${sportItem.itemId==sportClassById.sport_item_id}"> selected</c:if>
                                            value="${sportItem.itemId}">${sportItem.itemName}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>授课教师</td>
                    <td>
                        <c:if test="${empty sportClassById}">
                            <input type="text"  id="autoComplete" name="teacherName" placeholder="填写教师名"
                                   value="${sportClassById.teacherName}">
                        </c:if>
                        <c:if test="${not empty sportClassById}">
                            <input type="text"  id="autoComplete" name="teacherName" placeholder="填写教师名"
                                   value="${sportClassById.teacherName}">
                        </c:if>
                    </td>
                    <input type="text" style="display: none" name="teacher">
                </tr>
                <tr>
                    <td class="stus">上课学生</td>
                    <td id="test">

                    </td>
                </tr>
                <%--<hr>--%>
                <tr>
                    <td colspan="2" class="sub">
                        <button class="yes" onclick="doSubmit()">确定</button>
                        <button class="no"><a href="${ctx}/sport/class/index">取消</a></button>
                    </td>
                </tr>
            </table>

    </div>
</div>
</div>
</main>
<script>
    var data =${teacherList};
    $("#autoComplete").autocomplete(data, {
        minChars: 1,// 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
        max: 100,//下拉显示项目的个数
        autoFill: false,//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
        mustMatch: true,//如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
        matchContains: true,
        formatItem: function (row, i, max) {
            return row.name + ":" + row.no;
        },
        formatMatch: function (row, i, max) {
            return row.name + "-" + row.no;
        },
        formatResult: function (row) {
            return row.name + "-" + row.no;//+row.account.replace(/[^0-9]/ig,"");//取得账号里面的数字...
        }
    })
    $("#autoComplete").bind("input propertychange", function () {
        if ($(this).val().trim() == "") {
            $(".completeTips").show();
        } else {
            $(".completeTips").hide();
        }
    });
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function next() {
        //选择年级
        var inputs = $("input[name='box']:checked");
        //选择项目名称
        var item = $("#mapItemName").val();
        //选择运动名称
        var sportClassName = $("input[name='sportClassName']").val();
        //选择老师名称
        var teacherName = $("input[name='teacherName']").val();
        //课程id
        var sportClassId = $("input[name='sportClassId']").val();
        var sendId = $("#getStudentId").val();
        var studentName = $("#getStudentName").val();
        //选择学生
        if (sportClassId == null || sportClassId == "") {
            if (inputs != null && item != null && sportClassName != null && teacherName != null && teacherName != "" && sportClassName != "") {
                openDialog('添加学生', postPath + '/sport/view/intostudent?sportClassId=${sportClassId}' + '&sendId=' + sendId + '&studentName=' + encodeURI(encodeURI(studentName)), '820px', '620px');
            } else {
                layer.msg("请输入完整信息");
            }
        } else {
            openDialog('添加学生', postPath + '/sport/view/intostudent?sportClassId=${sportClassId}' + '&sendId=' + sendId + '&studentName=' + encodeURI(encodeURI(studentName)),'820px','620px');
        }
    }
    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var inputs = $("input[name='box']:checked");
        var item = $("#mapItemName").val();
        var sportClassName = $("input[name='sportClassName']").val();
        var teacherName = $("input[name='teacherName']").val();
        var sportClassId = $("input[name='sportClassId']").val();
//       var name= $("input[name='teacher']").attr('value',teacherName);
//        console.log(name)
        //年级的box
        var tempId = 0;
        inputs.each(function () {
            var singleId = $(this).val();
            tempId += ";" + singleId;
        });
        var getId = 0;
        $(".all").each(function (i) {
            var singleId = this.id;
            getId += "," + singleId;
        })
        var tempIds = $("#getStudentId").val();
        var studentId = ${classStudent};
        if (getId != 0) {
            if (inputs != null && item != null && sportClassName != null && teacherName != null && teacherName != "" && sportClassName != "") {
                //人员选择完毕，点击确定提交绑定操作
                if (tempIds != 0 && tempIds != null && tempIds != "") {
                    $.post("${ctx}/sport/class/add", {
                        classId: sportClassId,
                        studentId: tempIds,
                        sportClassName: sportClassName,
                        grade: tempId,
                        item: item,
                        teacherName: teacherName
                    }, function (data) {
                        if(data=="false"){
                            layer.msg("已有该课程名")
                        } else if(data=="false2") {
                            layer.msg("没有该老师")
                        }else{
                            window.location.href = "${ctx}/sport/class/index";
                        }

                    })
                } else {
                    $.post("${ctx}/sport/class/add", {
                        classId: sportClassId,
                        studentId: getId,
                        sportClassName: sportClassName,
                        grade: tempId,
                        item: item,
                        teacherName: teacherName
                    }, function (data) {
                        if(data=="false"){
                            layer.msg("已有该课程名")
                        } else if(data=="false2") {
                            layer.msg("没有该老师")
                        }else{
                            window.location.href = "${ctx}/sport/class/index";
                        }
                    })
                }
            } else {
                layer.msg("请输入完整信息");
            }
        } else {
            layer.msg("请至少添加一个学生");
        }
    }
    $(function () {
        var inputs = $("input[name='box']").not("input:checked");
        var tempIds = "";
        inputs.each(function () { // 遍历选中的checkbox
            var singleId = $(this).val();
            tempIds += "," + "\"" + singleId + "\"";
        });
        var id = ${gradeId};
        for (var key in id) { //第一层循环取到各个list
            var List = id[key];
            var check = ""
            if (tempIds.indexOf(List) > -1) {
                check = "checked";
                var fxks = $("input[name='box']");
                for (var i = 0; i < fxks.length; i++) {
                    var f = fxks[i];
                    if (f.value == List) {
                        f.checked = true;
                    }
                }
            }
        }
    })
    function getStudent(str, id) {
        con = "";
        $("#test").html(con);
        var arr = str.split(',');
        var arr2 = id.split(',');
        $("#test").append('<ul style="overflow: hidden">');

        $.each(arr, function (index) {
            if (index != 0) {

                $("#test").append('<li class="all" id="' + arr2[index]+ '" style="float: left;display: inline-block;border: 1px solid #19be9d;padding: 0 8px;margin: 0 12px 10px 0;border-radius: 2px;">'  + arr[index] + '</li>')
            }
        });
        $("#test").append(" <input style='height: 36px;padding-left: 0;' type=\"button\" class=\"add\" id=\"next\" onclick=\"next()\" value=\"修改\"/>");
        $("#test").append('</ul>');
    }
    $(function () {
        $("#test").append('<ul id="getClassStudentId" style="overflow: hidden;">');
        var classStudent = eval(${classStudent});
        for (var key in classStudent) { //第一层循环取到各个list
            var List = classStudent[key];
            $("#test").append('<li class="all" id="' + List.xh + '" style="float: left;display: inline-block;border: 1px solid #19be9d;padding: 0 8px;margin: 0 12px 10px 0;border-radius: 2px;">' + List.xsxm + '</li>')
        }
        $("#test").append(" <input style='height: 36px;padding-left: 0;' type=\"button\" class=\"add\" id=\"next\" onclick=\"next()\" value=\"修改\"/>");
        $("#test").append('</ul>');
    });



    $(function () {
        var getId = 0;
        $(".all").each(function (i) {
            var singleId = this.id;
            getId += "," + singleId;
        })
        if (getId != 0) {
            document.getElementById("next").value = "修改";
        } else {
            document.getElementById("next").value = "添加";
        }
    })

</script>
</body>
</html>