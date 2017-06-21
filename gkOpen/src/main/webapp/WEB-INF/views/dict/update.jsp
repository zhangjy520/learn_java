<%--
  Created by IntelliJ IDEA.
  User: jon
  Date: 17-6-8
  Time: 上午10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <br>
    <input type="hidden" id="id" value="${dict.id}">
    <label>类别</label><input id="description" value="${dict.description}"><br>
    <label>类别标识</label><input id="mark" value="${dict.mark}"><br>
    <label>名称</label><input id="label" value="${dict.label}"><br>
    <label>值</label><input id="value" value="${dict.value}"><br>
    <label>排序</label><input id="sort" value="${dict.sort}"><br>
    <input id="btn_submit" type="button" value="提交">
</form>
</body>
</html>

<script>

    $('#btn_submit').on('click', function () {
        var dict = {};
        dict.id = $('#id').val();
        dict.description = $('#description').val();
        dict.mark = $('#mark').val();
        dict.label = $('#label').val();
        dict.value = $('#value').val();
        dict.sort = $('#sort').val();

        var url = '${ctx}/dict/do/update';
        var data = dict;
        $.ajax({
            type: 'post',
            url: url,
            data: data,
            dataType: 'json',
            traditional: true,
            success: function(result){
                alert(result.msg);
                window.location.href = '${ctx}/dict/index';
            }
        });

    })
</script>
