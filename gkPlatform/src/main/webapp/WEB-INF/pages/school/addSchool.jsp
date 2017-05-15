<%@ include file="../common/common.jsp"%>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
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
        .input{
            margin-left: 20px;
            height: 25px;
            width: 182px;
            padding: 0 8px;
        }
        .laydate-icon{
            width:110px ;
        }
        form{
            width: 400px;
        }
        #s_county{
            width: 120px;
        }
        #s_city{
            width: 80px;
        }
        #s_province{
            width: 80px;
        }
    </style>
    <link rel="stylesheet" href="${ctxStatic}/upload/h5upload.css"/>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStatic}/js/laydate.js"></script>
    <script src="${ctxStatic}/js/area.js"></script>
    <!-- h5upload -->
    <script src="${ctxStatic}/upload/h5upload.js"></script>
    <script>
        function rmPic(obj) {
            if (confirm("确定要删除这张图片吗?")) {
                $(obj).parent("li").remove();
            }
        }
        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $("#inputForm").submit();
                return true;
             }

             //上传，显示
        $(document).ready(function () {
            var iconChoose = document.getElementById("file-input");
            $("#iconUpload").on("click", function () {
                iconChoose.click();
            }).on("touchstart", function () {
                $(this).addClass("touch")
            }).on("touchend", function () {
                $(this).removeClass("touch")
            });
            iconChoose.onchange = function () {
                if (!this.files.length) return;
                var files = Array.prototype.slice.call(this.files);
                if (files.length > 9) {
                    alert("最多同时只可上传9张图片");
                    return;
                }
                files.forEach(function (file, i) {
                    if (!/\/(?:jpeg|png|gif)/i.test(file.type)) return;
                    var reader = new FileReader();
                    var li = document.createElement("li");
                    //          获取图片大小
                    var size = file.size / 1024 > 1024 ? (~~(10 * file.size / 1024 / 1024)) / 10 + "MB" : ~~(file.size / 1024) + "KB";
                    li.innerHTML = '<div class="removeBtn" onclick="rmPic(this)">—</div><div class="progress"><span></span></div><div class="size">' + size + '</div>';
                    $(".img-list-icon").children().remove();
                    $(".img-list-icon").append($(li));
                    reader.onload = function () {
                        var result = this.result;
                        var img = document.createElement("img");
                        $(img).attr("width", "100%");
                        $(img).attr("height", "100%");
                        $(img).attr("data-url", "");
                        $(img).attr("class", "pictures");
                        img.src = result;
                        $(li).append(img);
                        //如果图片大小小于100kb，则直接上传
                        if (result.length <= maxsize) {
                            img = null;
                            uploads(result, file.type, $(li), file.name);
                            return;
                        }
                        //      图片加载完毕之后进行压缩，然后上传
                        if (img.complete) {
                            callback();
                        } else {
                            img.onload = callback;
                        }
                        function callback() {
                            var data = compress(img);
                            uploads(data, file.type, $(li), file.name);
                            img = null;
                        }
                    };
                    reader.readAsDataURL(file);
                })
            };

            function uploads(basestr, type, $li, name) {
                var text = window.atob(basestr.split(",")[1]);
                var buffer = new Uint8Array(text.length);
                var pecent = 0, loop = null;
                for (var i = 0; i < text.length; i++) {
                    buffer[i] = text.charCodeAt(i);
                }
                var blob = getBlob([buffer], type);
                var xhr = new XMLHttpRequest();
                var formdata = getFormData();
                formdata.append('file', blob);
                formdata.append("imgName", name);
                formdata.append("imgPath", '<%=FileUtils.SCHOOL_LOGO_PATH%>');
                xhr.open('post', '${ctx}/file/upload');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        var imageData = JSON.parse(xhr.responseText);
                        var text = imageData.data ? '上传成功' : '上传失败';
                        clearInterval(loop);
                        //当收到该消息时上传完毕
                        $li.find(".progress span").animate({'width': "100%"}, pecent < 95 ? 200 : 0, function () {
                            $(this).html(text);
                        });
                        $("#headUrl").val(imageData.data);
                        $("#head_url").attr("data-url", imageData.data);
                    }
                };
                //数据发送进度，前50%展示该进度
                xhr.upload.addEventListener('progress', function (e) {
                    if (loop) return;
                    mockProgress();
                }, false);
                //数据后50%用模拟进度
                function mockProgress() {
                    if (loop) return;
                    loop = setInterval(function () {
                        pecent++;
                        $li.find(".progress span").css('width', pecent + "%");
                        if (pecent == 100) {
                            clearInterval(loop);
                        }
                    }, 50)
                }

                xhr.send(formdata);
            }
        });
            //end 上传，显示
    </script>

</head>
<body><!-- onclick=" onclick="openDialog('11','addUser.html','500px','400px');""  -->
<div class="container" >


    <form action="${ctx}/school/save"  method="post" id="inputForm">
        logo：<%--<input type="file" name="file" class="input" id="file" onchange="preImg(this.id,'imgPre');">
        <img id="imgPre" style="width: 100px;height:100px;display: block;float: right;position: absolute; margin: -51px 283px;" />
        <input type="button" value="上传" onclick="upload()" style="position:absolute;margin: 82px 53px;"><br><br>--%>
        <input type="file" id="file-input" style="display: none" accept="image/*">
        <input type="hidden" name="headUrl" id="headUrl" value="${school.logo}">
        <p style="display: inline-block">
            <a id="iconUpload" class="uploadBtn-a">上传图片</a>
        </p>
        <ul class="img-list-icon" style="float: right">
            <li>
                <div class="removeBtn" onclick="rmPic(this)">—</div>
                <img src="${ctx}/file/pic/show?picPath=${school.logo}" data-url="${school.logo}" width="100%" height="100%" id="head_url">
            </li>
        </ul><br><br>
        <input type="hidden" name="id" value="${school.id}">
        机构标识：<input type="text" name="shortFlag" class="input" value="${school.shortFlag}"><br><br>
        机构名称：<input type="text" name="name" value="${school.schoolName}"><br><br>
        申请使用类型：<select>
                    <option>正式使用</option>
                    <option>试用</option>
                    </select><br><br>
        <%--开通时间： <input type="text" id="startDate" class="laydate-icon"/>至<input type="text" id="endDate" class="laydate-icon"/><br><br>--%>
        类型：<select name="type">
                <option value="1" <c:if test="${school.type==1}">selected</c:if> >区平台</option>
                <option value="2" <c:if test="${school.type==2}">selected</c:if> >学校</option>
              </select><br><br>
        学校类型：
            <select name="grade">
                <option value="1" <c:if test="${school.grade==1}">selected</c:if> >小学</option>
                <option value="2" <c:if test="${school.grade==2}">selected</c:if> >初级中学</option>
                <option value="3" <c:if test="${school.grade==3}">selected</c:if> >高级中学</option>
                <option value="4" <c:if test="${school.grade==4}">selected</c:if> >一贯制学校</option>
            </select><br><br>
        所属地区：
                    <select id="s_province" name="s_province"></select>  
                    <select id="s_city" name="s_city" ></select>  
                    <select id="s_county" name="s_county"></select>
        <br><br>
        地址：<input type="text" name="address" class="input" value="${school.address}"><br><br>
        所属区平台：<select name="parentId">
                  <option value="0">无</option>
                   <c:forEach items="${schoolList}" var="sch">
                       <option value="${sch.id}" <c:if test="${sch.id eq school.parentId}">selected</c:if> >${sch.name}</option>
                   </c:forEach>
                    </select><br><br>
        域名：<input type="text" class="input" name="deployUrl" value="${school.deployUrl}"><br><br>
       <%-- 是否同步数据：<input type="radio" name="tongBu"/>是 <input type="radio" name="tongBu"/>否<br><br>--%>
        <%--登录后缀：<input type="text"  class="input" value="${school.shortFlag}"><br><br>--%>
        <%--网页名称：<input type="text" name="" class="input"><br><br>--%>
        邮箱：<input type="text" name="email"  value="${school.email}" class="input">
    </form>

</div>
<script>
    _init_area();
/*    !function(){
        laydate.skin('molv');
        laydate({elem:'#startDate'})
        laydate({elem:'#endDate'})
    }();*/
        
    function  upload() {
        $.post("${ctx}/file/upload",{
        },function(retJson){
          alert(retJson);
        },"json");
    }
    //图片上传和截取--------------------------------------begin------------------------------------
    /**
     * 从 file 域获取 本地图片 url
     */
    function getFileUrl(sourceId) {
        var url;
        if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
            url = document.getElementById(sourceId).value;
        }
        else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
        }
        else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
        }
        return url;
    }

    /**
     * 将本地图片 显示到浏览器上
     */
    function preImg(sourceId, targetId) {
        var url = getFileUrl(sourceId);
        document.getElementById(targetId).src=url;
    }
</script>
</body>
</html>