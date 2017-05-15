<%@ include file="../../common/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>

    <style>
        body{
            font-family:"Roboto","Noto Sans CJK SC","Nato Sans CJK TC","Nato Sans CJK JP","Nato Sans CJK KR",-apple-system,".SFNSText-Regular","Helvetica Neue","PingFang SC","Hiragino Sans GB","Microsoft YaHei","WenQuanYi Zen Hei",Arial,sans-serif

        }
        div.container{

            font-size:14px;
            color:#101010;
            width:300px;
            margin:0 auto;
            margin-top:30px;
        }
        input{
            margin-left:20px;
            height:25px;
            width:182px;
            padding:0 8px;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <script src="${ctxStatic}/tinymce/tinymce.min.js"></script>
    <script type="text/javascript">

        function doSubmit(){
        	//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
          $.post($("form").attr('action'),{
                appid:$("input[name='appid']").val(),
                name:$("input[name='name']").val(),
                identify:$("select[name='identify']").val(),
                remarks:tinymce.get('remarks').getContent(),
            } ,function(retJson){
            }); 
            return true;
        }
        tinymce.init({
            selector: 'textarea',
            height: 120,
            language : "zh_CN"
        });
    </script>

</head>
<body><!-- onclick=" onclick="openDialog('11','addUser.html','500px','400px');""  -->
<div class="container" >

    <form method="post" id="inputForm" action="${ctx}/app/addroleaddtoapp">
        <input type="hidden" name="appid" value="${appid}">
        角色名称：<input type="text" name="name" > <br/><br/>

        角色标识：
        <select name="identify">
            <option value="teacher">管理员</option>
            <option value="teacher">教职工</option>
            <option value="patriarch">家长</option>
            <option value="student">学生</option>
        </select> <br/><br/>
    
        内容：<textarea name="remarks" rows="3"></textarea> <br/>
    </form>
</div>
</body>
</html>