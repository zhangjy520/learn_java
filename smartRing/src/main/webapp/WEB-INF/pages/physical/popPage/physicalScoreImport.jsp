<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/26
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../common/common.jsp" %>
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
            margin: 0 auto;
            padding: 30px 0;
            padding-left: 20px;
        }

        /*.container>div{margin-top:30px;}*/
        .container > div input {
            width: 205px;
            height: 27px;
            border: 1px solid #ddd;
            padding: 0;
            padding-left: 5px;
            border-radius: 3px;
        }

        .container > div button {
            cursor: pointer;
            outline: none;
            margin-left: 15px;
            font-size: 13px;
            color: #fff;
            height: 27px;
            padding: 0 18px;
            border: 1px solid #19be9d;
            background: #19be9d;
            border-radius: 3px;
        }

        .container > div p {
            font-size: 12px;
            color: #888;
            margin-top: 8px;
        }
        .layui-layer-dialog .layui-layer-padding {
            padding: 10px 5px 15px 55px;
            text-align: left;
        }
    </style>
    <script src="${ctxStatic}/js/jquery.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js"></script>
</head>
<body>
<form action="${ctx}/physical/score/import" id="inputForm" method="post" enctype="multipart/form-data" >
    <div class="container">
        <div>
            <input type="file" name="file" id="file"/>
            <button type="button">选择文件</button>
            <p>注:请上传后缀为xlsx或xls的文件</p>
        </div>
</div>
</form>
<script>
    var fileChoose = document.getElementById("file");
    $("button").on("click", function () {
        fileChoose.click();
    });

    function doSubmit() {
        window.parent.layer.msg('导入成绩并计算分数耗时可能较长，请您耐心等待。操作完成后系统会自动刷新', {icon: 16, shade: 0.5, time: 1000000000});//当生成完成这个对话框才被关掉
        var index = layer.load();
        $.ajax({
            url: $('form').attr('action'),
            type: 'POST',
            cache: false,
            data: new FormData($('#inputForm')[0]),
            processData: false,
            contentType: false
        }).done(function (res) {
            if (res.code == 1) {
                window.parent.layer.msg("共" + res.success + "条数据，全部导入成功,耗时"+res.timeUse+"秒", {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function () {
                    window.parent.location.reload(true);
                });
            }
            if (res.code == 0) {
                layer.close(index);
                var dataHtml = "", tem = res.failList;
                var dataList =[];
                for (var i = 0; i < tem.length; i++) {
                    dataHtml += "<tr>" +
                            "<td>" + tem[i].data.stuNo + "</td>" +
                            "<td>" + tem[i].data.itemName + "</td>" +
                            "<td>" + tem[i].data.testTime + "</td>" +
                            "<td>" + tem[i].data.stuMark + "</td>" +
                            "<td>" + tem[i].reason + "</td>" +
                            "</tr>";
                    dataList[dataList.length] = tem[i].data;
                }

                var html = " <main style='padding:30px 20px;'>" +
                        "<p style='font-size: 13px;margin-bottom: 15px;'>" +
                        "成功<span style='color: #19be9d;' class='green'>" + res.success + "</span>条，失败<span style='color: #fc2f5b;' class='red'>" + res.fail + "</span>条，共耗时"+res.timeUse+"秒" +
                        "</p>" +
                        "<table class='table'>" +
                        "<thead>" +
                        "<tr>" +
                        "<th width='20%'>学生学号</th>" +
                        "<th width='20%'>测试项目</th>" +
                        "<th width='20%'>测试时间</th>" +
                        "<th width='20%'>测试成绩</th>" +
                        "<th width='20%'>失败原因</th>" +
                        "</tr>" +
                        "</thead>" +
                        "<tbody>" +
                        dataHtml +
                        "</tbody>" +
                        "</table>" +
                        "</main>";
                window.parent.layer.open({
                    type: 1,
                    title: '成绩导入结果',
                    shade: 0.5,
                    closeBtn: 0,
                    btn: ['确认', '下载失败列表'],
                    area: ['780px', '600px'],
                    content: html,
                    resize:false,
                    move: false,
                    btn1: function () {
                        window.parent.location.reload(true);
                    },
                    btn2: function () {
                        var form=$("<form>");//定义一个form表单
                        form.attr("style","display:none");
                        form.attr("target","");
                        form.attr("method","post");
                        form.attr("action","${ctx}/physical/physicalScore/error/export");
                        var input1=$("<input>");
                        input1.attr("type","hidden");
                        input1.attr("name","msg");
                        input1.attr("value",JSON.stringify(dataList));
                        $("body").append(form);//将表单放置在web中
                        form.append(input1);
                        form.submit();//表单提交
                        return false;
                    },
                });
            }
        }).fail(function (res) {

        });
    }
</script>
</body>
</html>