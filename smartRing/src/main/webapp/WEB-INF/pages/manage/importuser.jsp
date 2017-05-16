<%@ include file="../common/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
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

        input[type=text] {
            width: 205px;
            height: 27px;
            border: 1px solid #ddd;
            padding: 0;
            padding-left: 5px;
            border-radius: 3px;
        }

        button {
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

        .h-upload {
            position: relative;
        }

        input[type="file"] {
            opacity: 0;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            /*  min-height:100%; */
            cursor: pointer;
        }
    </style>
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#photo").change(function(){
                var path=$(this).val();
                var path1 = path.lastIndexOf("\\");
                var name = path.substring(path1+1);
                $("#text").val(name);
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div>
        <form class="h-upload" method="post" id ="form" enctype="multipart/form-data">
            <input type="file" name="file" id="photo" <%--onchange="document.getElementById('text').value=this.value"--%>>
            <input type="text" id="text">
            <button type="button" class="h-upload-tit" id="pathText">上传文件</button>
        </form>
        <p>注:请上传后缀为xlsx或xls的文件</p>
    </div>
</div>
</body>
</html>