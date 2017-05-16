<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            overflow-y: hidden;
        }

        select::-ms-expand {
            display: none;
        }

        .wrap {
            width: 100%;
            background: rgba(178, 178, 178, .5);
            position: relative;
            color: #676a6c;
            font-size: 12px;
        }

        .wrap .sel-stu-wrap {
            width: 820px;
            background: #fff;
        }

        .sel-stu-top {
            padding: 18px 0 18px 20px;
            background: #f0f4f7;
            font-size: 13px;
            color: #333;
            border-bottom: 1px solid #eee;
        }

        .sel-stu-main div {
            display: inline-block;
        }

        .sel-stu-main-left {
            width: 450px;
            height: 500px;
            padding: 20px;
        }

        .sel-stu-main-right {
            width: 315px;
            overflow-y:scroll;

        }

        .sel-stu-main-left p {
            overflow: hidden;
            margin-bottom: 20px;
        }

        .sel-stu-main-left p input {
            width: 367px;
            height: 30px;
            line-height: 30px;
            color: #676767;
            border: 1px solid #e5e6e7;
            border-right: none;
            font-size: 13px;
            border-radius: 2px;
            float: left;
            outline: none;
        }

        .sel-no, .sel-serach {
            display: inline-block;
            width: 32px;
            height: 30px;
            line-height: 30px;
            float: left;

        }

        .sel-no {
            background: url("${ctxStatic}/images/close_o.png") no-repeat center;
            border-top: 1px solid #e5e6e7;
            border-bottom: 1px solid #e5e6e7;
            cursor: pointer;
        }

        .sel-no:hover {
            background: url("${ctxStatic}/images/close_t.png") no-repeat center;
        }

        .sel-serach {
            height: 32px;
            background: url("${ctxStatic}/images/sel_search.jpg") no-repeat center;
            border: none;
            outline: none;
        }

        .sel-stu-main-left select {
            border: 1px solid #e5e6e7;
            appearance: none;
            -moz-appearance: none;
            -webkit-appearance: none;
            outline: none;

            width: 95px;
            height: 30px;
            line-height: 30px;
            font-size: 12px;
            color: #676767;
            margin: 0 15px 0 6px;
            background: url("${ctxStatic}/images/down.png") no-repeat;
            background-position: 80px center;
            padding-left: 5px;
        }

        .tab-box {
            width: 100%;
            height: 380px;
            padding-bottom: 10px;
            padding-right: 5px;
            overflow-y: scroll;
        }

        .tab-box table {
            width: 100%;
            border: 1px solid #e7eaec;
            padding-top: 32px;
        }

        .tab-box table td, th {
            padding-left: 10px;
            border-bottom: 1px solid #e7eaec;
        }

        .tab-box table tr:last-child td {
            border: none;
        }

        .tab-box table tr {
            height: 32px;
            line-height: 32px;
        }

        .tab-box table thead {
            background: #e7eaec;
            text-align: left;
        }

        .tab-box table input[type='checkbox'] {
            -webkit-appearance: none;
            background: url(${ctxStatic}/images/chec_o.png) no-repeat;
            height: 12px;
            width: 12px;
            vertical-align: middle;
            margin: -3px 5px 0 0;
            cursor: pointer;
        }

        .tab-box table input[type="checkbox"]:checked {
            border: none;
            background: url('${ctxStatic}/images/chec_t.png') no-repeat;

        }

        .sel-stu-main-right {
            width: 310px;
            height: 500px;
            padding: 20px 20px 20px 0;
        }

        .sel-stu-bottom {
            height: 63px;
            background: #f0f4f7;
            text-align: right;
            border-top: 1px solid #c7c7c7;
        }

        .sel-stu-bottom button {
            width: 65px;
            height: 30px;
            outline: none;
            border: none;
            border-radius: 2px;
            margin: 16px;
            font-size: 13px;
        }

        .sel-stu-bottom .ok {
            background: #19be9d;
            color: #fff;
        }

        .sel-stu-bottom .quit {
            background: #fff;
            color: #747678;
            border: 1px solid #c0c4cd;
        }

        .sel-stu-main-right {
            color: #525252;
        }

        .sel-stu-main-right p {
            padding-bottom: 8px;
            border-bottom: 1px solid #c0c4cd;
        }

        .sel-stu-main-right p span {
            font-size: 13px;
            display: inline-block;
            overflow: hidden;
        }

        .sel-stu-main-right .del-all {
            float: right;
            padding-left: 25px;
            color: #fa2250;
            background: url("${ctxStatic}/images/del.png") no-repeat left;
            cursor: pointer;
        }

        .sel-stu-main-righ ul {
            overflow: hidden;

        }
        #test{
            overflow: hidden;
            margin-bottom: 100px;
        }
        .sel-stu-main-righ li {

        }
    </style>

    <header id="breadcrumb-nav" class="clear">

        </ul>
    </header>
</head>
<body>
<div class="wrap">
    <div id="test2">
        <div class="sel-stu-wrap">
            <div class="sel-stu-top">
                选择上课学生
            </div>
            <div class="sel-stu-main" style="overflow: hidden">
                <div class="sel-stu-main-left" style="position: relative;float: left;">
                    <div STYLE="width: 20px;height: 100%; z-index:3;position: absolute; top: 0; right: 15px; background: #fff;border-right: 1px solid #e7eaec;"></div>
                    <p>
                        <input type="text" id="name" placeholder="请输入学生姓名">
                        <span class="sel-no" onclick="blank()"></span>
                        <button onclick="getData()" class="sel-serach" id="search"></button>
                    </p>
                    <p>
                        年级
                        <select id="nj">
                            <option value="">全部</option>
                            <c:forEach items="${onlyNj}" var="nj">
                                <option value="${nj.xd},${nj.nj}">${nj.indexName}</option>
                            </c:forEach>
                        </select>
                        班级
                        <select id="bj">
                            <option value="">全部</option>
                            <c:forEach items="${allList}" var="bj">

                                <option value="${bj.classId}" class="${bj.xd},${bj.nj}">${bj.className}</option>
                            </c:forEach>
                        </select>
                        性别
                        <select id="sex">
                            <option value="">全部</option>
                            <option value="2">女</option>
                            <option value="1">男</option>
                        </select>
                    </p>
                    <div class="tab-box" >
                        <table cellpadding="0" cellspacing="0" id="personTable">

                        </table>
                    </div>
                </div>

                <input type="hidden" value="${sendId}" class="sendId">


                <div class="sel-stu-main-right" style="float: left">
                    <p>
                        <span>已选人员</span>
                        <span class="del-all" onclick="clearAll()">清空</span>
                    </p>
                    <tbody id="chooseTable">
                    <ul id="test" style="list-style: none;">
                        <li class="chooseHeader" style="display: none">
                        </li>
                        <c:if test="${not empty studentClass}">
                            <c:forEach items="${studentClass}" var="student" varStatus="status">
                                <li name="${student.xh}" style='height:32px;line-height:32px;width:95px;float:left;'>
                                    <input type="text" name="studentXh" id="studentXh" value="${student.xh}"
                                           style="display: none"/>
                                    <span name="xsxm">${student.xsxm}</span>
                                    <span class="deleteChoose" onclick="deleteStudent('${student.xh}')"
                                          style="display: inline-block;height: 14px;width: 14px;vertical-align: middle;margin-left: 10px;background: url('${ctxStatic}/images/close_o.png') no-repeat center;cursor: pointer;"></span>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                    </tbody>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script>

    layer.getChildFrame()
    $('body').on("change", "#personTable input[type=checkbox]", function () {
        var $subs = $("input[name='boxx']");
        $(".allCheck").prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
        moveToRight(this)
    })

    $('body').on("click", ".deleteChoose", function () {
        clearSelf(this);
    })
    function moveToRight(obj) {
        var tag = $(obj).attr("class");
        var id = $(obj).attr("id");
        var text = $(obj).parent().nextAll();
        if (tag != 'allCheck') {
            if (obj.checked == true) {
                var already = $("li[name=" + id + "]").attr("name");
                if (typeof(already) != "undefined") {
                    return;
                }
                var str = "<li style='height:32px;line-height:32px;width:95px;float:left;' name=" + id + ">";
//                for (var i = 0; i < text.length; i++) {
                str += "<span name='xsxm'>" + $(text[0]).html() + "</span>";
//                }
                str += "<span class=\"deleteChoose\" style=\"display: inline-block;height: 14px;width: 14px;vertical-align: middle;margin-left: 10px;background: url(\'${ctxStatic}/images/close_o.png\') no-repeat center;cursor: pointer;\"></span>";
                ;
                $("#test").append(str);
            } else {
                clearSelf($("li[name=" + id + "]").children("span"));
            }
        };
    }
    $('body').on("click", "table th input", function () {
        var tds = $('table td input[type=checkbox]');
        var me = this;
        $(tds).each(function (i, e) {
            if (me.checked == true) {
                e.checked = true;
            } else {
                e.checked = false;
            }
            moveToRight(e);
        })
    });

    function clearAll() {
        //删除所有已经选择的人员
        var choose = $(".chooseHeader").nextAll();
        $("#personTable input[type=checkbox]").attr("checked", false);
        choose.remove();
    }
    function clearSelf(obj) {
        //删除选中的已选人员
        var id = $(obj).parent().attr("name");
        $("#" + id).attr("checked", false);
        $(obj).parent().remove();
    }

    $("select").change(function () {
                $("#name").val("");
            }
    );
    function getData() {


        var bj = $("#bj option:selected").val();
        var nj = $("#nj option:selected").text();
        var gender = $("#sex option:selected").val();
        var name = $("#name").val();
        var chooses = $(".chooseHeader").nextAll();

        if (name!=""){
           bj=""
           nj="全部"
           gender=""

        }

        var ids = "";
        for (var i = 0; i < chooses.length; i++)
            ids += "," + $(chooses[i]).attr("name");
        var con = "";
        $("#personTable").html(con);

        $("#personTable").append(
                '   <thead class="tab-th">' +
                '   <tr>' +
                '                    <th><input class="allCheck" type="checkbox" name="allCheckbox"/></th>' +
                '                    <th style="width: 86px;">姓名</th>' +
                '                    <th style="width: 118px;">年级</th>' +
                '                    <th style="width: 50px;">班级</th>' +
                '                    <th>学号</th>' +
                '                </tr>' +
                '   </thead>'
        );
        //放开没有第一行

        function tab_th_position() {
            var tab_th_top = $('.tab-box').offset().top;
            var tab_th_left = $('.tab-box').offset().left;
            var tab_th_width = $('.tab-box table').width() + 2;
            $('.tab-th').css({
                position: "fixed",
                top: tab_th_top,
                left: tab_th_left,
                width: tab_th_width
            });
        };
        tab_th_position();
        //当窗口大小改变时
        window.onresize = function () {
            tab_th_position();
        };

        $.post("${ctx}/sport/student/check", {
            nj: nj,
            bj: bj,
            gender: gender,
            sportClassId: $("input[name='sportClassId']").val(),
            name: name
        }, function (data) {
            var dataP = JSON.parse(data)
            var obj = dataP.studentList;

            for (var key in obj) { //第一层循环取到各个list
                var List = obj[key];
                var check = ""
                if (ids.indexOf(List.xh) > -1) {
                    check = "checked";
                }

                $("#personTable").append(
                        '                    <tbody>' +
                        '                    <tr>' +
                        '                        <td><input id=' + List.xh + ' type="checkbox" name="boxx" ' + check + ' value="' + List.xh + '"/></td>' +
                        '                        <td>' + List.xsxm + '</td>' +
                        '                        <td>' + List.indexName + '</td>' +
                        '                        <td>' + List.className + '</td>' +
                        '                        <td>' + List.xh + '</td>' +
                        '                    </tr>' +
                        '                   </tbody>'
                );

                var $subs = $("input[name='boxx']");
                $(".allCheck").prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
            }
        })
    }
    function doSubmit(ids) { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var chooses = $(".chooseHeader").nextAll();
        var xsxm = $("span[name='xsxm']");
        var studentxh = window.parent.document.getElementById("getStudentId");
        var studentName = window.parent.document.getElementById("getStudentName");
        ids = "";
        for (var i = 0; i < chooses.length; i++)
            ids += "," + $(chooses[i]).attr("name")
        var xm = "";
        for (var i = 0; i < xsxm.length; i++)
            xm += " ," + $(xsxm[i]).text();
        var tempIds = 0 + ids;
        var xmStudent = 0 + xm;
        if (tempIds != "0") {
            studentName.setAttribute("value", xmStudent);
            studentxh.setAttribute("value", tempIds);
            window.parent.getStudent(xm, tempIds);
//            window.parent.getStudentsId(tempIds);
            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
            parent.layer.close(index);
        } else {
            layer.msg("请添加至少一名学生");
        }
        return false;
    }

    function getChecked() {
    }

    $("select").change(function () {

        getData();
    })


    window.onload = function () {
        $("#bj").chained("#nj");
    }
    function deleteStudent(data) {
    }
    //进入界面和上一次未添加数据的内容相匹配
    $(function () {
        var data = '${classNameAndId}';
        if (data != '[]') {
//            var con = "";
//            $("#test").html(con);
            $("#test").empty();

            $("#test").append('<li class="chooseHeader" style="display: none">' +
                    '                        </li>');
            data = eval(${classNameAndId});
            for (var key in data) { //第一层循环取到各个list
                var List = data[key];
                var str = "<li style='height:32px;line-height:32px;width:95px;float:left;' name=" + List.xh + ">";
                str += "<span name='xsxm'>" + List.name + "</span>";
                str += "<span class=\"deleteChoose\" style=\"display: inline-block;height: 14px;width: 14px;vertical-align: middle;margin-left: 10px;background: url(\'${ctxStatic}/images/close_o.png\') no-repeat center;cursor: pointer;\"></span>" + "</li>";
                $("#test").append(str);
            }
        }
        getData();
    });


    function blank() {
        document.getElementById("name").value = "";
    }


</script>