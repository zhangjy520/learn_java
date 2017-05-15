<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
    <script>
        $(function () {
            var data = ${teacherList};
            debugger;
            $(".autoComplete").autocomplete(data, {
                minChars: 1,// 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
                max: 100,//下拉显示项目的个数
                autoFill: false,//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
                mustMatch: true,//如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
                matchContains: true,
                formatItem: function (row, i, max) {
                    return row.name;

                },
                formatMatch: function (row, i, max) {
                    return row.name;
                },
                formatResult: function (row) {
                    return row.name;//+row.account.replace(/[^0-9]/ig,"");//取得账号里面的数字...
                }
            });

            $(".autoComplete").bind("input propertychange", function () {
                if ($(this).val().trim() == "") {
                    $(".completeTips").show();
                } else {
                    $(".completeTips").hide();
                }
            });
        })
    </script>
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
<form action="${ctx}/teach/task/master/update?type=update&&id=${classView.classId}" id="courseEdit" method="post">
    <table>
        <tr>
            <td><span>年级班级:</span><span>${classView.xdName}${classView.nj}年级${classView.className}</span></td>
        </tr>
        <tr>
            <td><span>班主任:</span>
                <div class="row">
                    <input class="autoComplete" name="teacherName" value="${master}"/><span class="completeTips">请输入系统中存在的信息！</span>
                    <input type="hidden" name="teacherId" id="personId"/>
                    <input type="hidden" class="teacherIdFromHoutai" value="${teacherIdFromHouTai}"/>
                    <input type="hidden" class="classId" value="${classId}"/>
                    <input type="hidden" class="cycleId" value="${cycleId}"/>
                </div>
            </td>
        </tr>
        <tr>
            <td><span>副班主任:</span>
                <c:forEach items="${teacherList}" var="teacher" varStatus="status">
                    <input type="checkbox" value="${teacher.id}"><span>${teacher.name}</span>
                </c:forEach>
            </td>

        </tr>
    </table>
    <div class="suggest" id="search-suggest" style="display: none">
        <ul id="search-result">
            <li></li>
        </ul>
    </div>
</form>
</body>
<script>
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        if (true) {
            debugger;
            var classId = $(".classId").val();
            var tempTeacherId = "";
            var teacherIdFromHouTai =$(".teacherIdFromHoutai").val();
            var cycleId = $(".cycleId").val()
            $('input[type=checkbox]:checked').each(function (i) {
                var teacherId = $(this).val();
                tempTeacherId += "," + teacherId;
            })
            var teacherId = $("#personId").val();
            $.post("${ctx}/teach/task/master/edit", {
                tempTeacherId:tempTeacherId,
                teacherId:teacherId,
                teacherIdFromHouTai:teacherIdFromHouTai,
                classId:classId
            }, function (data) {

            })
//            $("#courseEdit").submit();
            return true;
        } else {
            layer.msg("输入有误！");
            return false;
        }
    }
    //    将checkbox的值存到域中 通过form提交
    $('input[type=checkbox]').change(function () {
        $('#Jszzdm').val($('input[type=checkbox]:checked').map(function () {
            return this.value
        }).get().join(','))
    })
</script>
</html>
