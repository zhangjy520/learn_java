<%@ include file="../common/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        * {
            box-sizing: border-box
        }

        .container {
            width: 350px;
            padding: 30px 0;
            text-align: center;
            margin: 0 auto;
        }

        .container > div {
            margin-bottom: 24px;
            font-size: 12px;
        }

        .container > div span {
            font-size: 12px;
            color: #525252;
            margin-right: 10px;
            display: inline-block;
            width: 70px;
            text-align: left;
        }

        .container > div select, .container > div input {
            border: 1px solid #ddd;
            width: 205px;
            height: 27px;
            border-radius: 3px;
            outline: none;
        }

        .container > div input {
            padding: 0;
            padding-left: 5px;
        }

        .container > div .range-select {
            width: 100px;
        }
    </style>
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js"></script>
</head>
<body>
<div class="container">
    <div>
        <span>教工号</span>
        <input id="no" type="text" placeholder="请输入教工号" value="${teacherNo}"/>
    </div>
    <div>
        <span>角色</span>
        <select id="roleSelect">
            <option class="-1" value="-1">请选择角色</option>
            <c:forEach items="${roleList}" var="role">
                <option class="${role.permissionRange}" value="${role.id}"
                        <c:if test="${role.id == roleId}">selected</c:if>>${role.name}</option>
            </c:forEach>
        </select>
    </div>
    <div id="rangeSelect">
        <span>数据范围</span>
        <select class="range-select" id="njSelect">
            <option value="-1">请选择年级</option>
        </select>
        <select class="range-select" id="bjSelect">
            <option value="-1">请选择班级</option>
        </select>
    </div>
</div>
<script>

    var classList = [
        <c:forEach items="${cascadingViews}" var="view">
        {
            id: "${view.id}",
            pid: "${view.pid}",
            name: "${view.name}",
        },
        </c:forEach>
    ];

    var range;

    function init() {
        range = $('#roleSelect').children('option:selected').attr('class');
        $('#njSelect').html("");
        $('#bjSelect').html("");
        if (range == '0') {
            $('#njSelect').attr('disabled', true);
            $('#njSelect').append("<option value='-2' selected >所有年级</option>");
            $('#bjSelect').attr('disabled', true);
            $('#bjSelect').append("<option value='-2' selected >所有班级</option>");
        }
        else if (range == '1') {
            $('#njSelect').attr('disabled', false);
            $('#bjSelect').attr('disabled', true);
            initnj();
            $('#bjSelect').append("<option value='-2' selected >请选择年级</option>");
        }
        else if (range == '2') {
            $('#njSelect').attr('disabled', false);
            $('#bjSelect').attr('disabled', false);
            initnj();
            $('#bjSelect').empty();
            $('#bjSelect').append("<option value='-1' selected >请选择班级</option>");
        } else if (range == '-1') {
            $('#njSelect').attr('disabled', false);
            $('#njSelect').append("<option value='-1' selected >请选择年级</option>");
            $('#bjSelect').attr('disabled', false);
            $('#bjSelect').append("<option value='-1' selected >请选择班级</option>");
        }
    }

    $(function () {
        init();
        var nownj <c:if test="${gukeer:notEmptyString(nownj)}">= '${nownj}'</c:if>;
        var nowbj <c:if test="${gukeer:notEmptyString(nownj)}">= ${nowclass}</c:if>;
        if (range != 0) {
            if ($('#roleSelect option[selected]').className != "-1") {
                initnj();
                $('#njSelect option').each(function (i) {
                    if ($('#njSelect option')[i].value == nownj) {
                        $('#njSelect option')[i].selected = true;
                    }
                })
            }
        }
        if (range == 2) {
            initBj();
            $('#bjSelect option').each(function (i) {
                if ($('#bjSelect option')[i].value == nowbj) {
                    $('#bjSelect option')[i].selected = true;
                }
            })
        }
    })

    $('#roleSelect').change(function () {
        init();
    });

    function initnj() {
        $('#njSelect').empty();
        $('#njSelect').append("<option value='-1'>请选择年级</option>");
        for (var i = 0; i < classList.length; i++) {
            var node = classList[i];
            if (node.pid == '-10') {
                html = "<option value='" + node.id + "'  >" + node.name + "</option>"
                $("#njSelect").append(html);
            }
        }
    }

    $('#njSelect').change(function () {
        initBj();
    });

    function initBj() {
        $('#bjSelect').empty();
        $('#bjSelect').append("<option value='-1'>请选择班级</option>");
        if (range == '1' && $('#njSelect').children('option:selected').val() != '-1') {
            $('#bjSelect').append("<option value='-2' selected disabled>所有班级</option>");
            $('#bjSelect').attr('disabled', true);
            return;
        } else {
            $('#bjSelect').attr('disabled', false);
        }
        for (var i = 0; i < classList.length; i++) {
            var node = classList[i];
            if (node.pid == $("#njSelect").val()) {
                html = "<option value='" + node.id + "'  >" + node.name + "</option>"
                $("#bjSelect").append(html);
            }
        }
    }


    function doSubmit() {
        var roleId = $('#roleSelect').val();
        var nj = $('#njSelect').val();
        var bj = $('#bjSelect').val();
        if (roleId == '-1' || nj == '-1' || bj == '-1') {
            layer.msg('有未选项');
            return false;
        }
        var i = nj.indexOf('_');
        var xd = nj.substr(0, i);
        var nj = nj.substr(i + 1);
        $.post('${ctx}/manage/user/save', {
            no: $('#no').val(),
            xd: xd,
            nj: nj,
            classId: bj,
            roleId:roleId,
            range: range
        }, function (res) {
            layer.msg(res.msg,{time:1500},function () {
                parent.location.reload();
            });
        })
    }
</script>
</body>
</html>
