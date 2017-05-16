<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js"></script>
    <style>
        *{box-sizing: border-box}
        .container{width:350px;padding:30px;font-size:12px;text-align: center;}
        span{color:#676767;}
        input{color:#525252;padding-left:5px;width:205px;height:27px;border:1px solid #ddd;border-radius: 3px;margin-left:10px;outline: none;}
    </style>
</head>
<body>
<div class="container">
    <span>手环编号</span>
    <input type="text" id="mac"/>
</div>
</body>
<script>
    function doSubmit(){
        $.post("${ctx}/manage/tempring/save",{
            mac:$('#mac').val(),
        },function (res) {
            layer.msg(res.msg);
            setTimeout(function () {
                window.parent.location.reload(true);
            },1000);
        })
    }
</script>
</html>
