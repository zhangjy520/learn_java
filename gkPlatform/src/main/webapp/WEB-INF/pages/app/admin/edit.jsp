<%@ include file="../../common/common.jsp" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="login">
    <meta name="author" content="lexi">

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${ctxStatic}/css/bootstrap.min.css"/>
    <!-- WebUploader CSS -->
    <link rel="stylesheet" href="${ctxStatic}/upload/h5upload.css"/>
    <!-- jQuery -->
    <script src="${ctxStatic}/js/jquery.js"></script>
    <!-- h5upload -->
    <script src="${ctxStatic}/upload/h5upload.js"></script>
    <!-- tinymce -->
    <script src="${ctxStatic}/tinymce/tinymce.min.js"></script>
    <title>编辑应用</title>
    <script>
    function rmPic(obj) {
        if (confirm("确定要删除这张图片吗?")) {
            $(obj).parent("li").remove();
        }
    }
     
        function doSubmit(){
                $("#iconUrl").val($(".icons").attr("data-url"));
                $(".pictures").each(function () {
                    var val = $(this).attr("data-url");
                    var temp = $("#picUrl").val();
                    if (temp.length > 0) temp = val + "," + temp;
                    else temp = val;
                    $("#picUrl").val(temp);
                });
                var temp=null;
                $("input[name = 'target']:checked").each(function(){
                    var val = $(this).val();
                    if(temp!=null) temp = val+","+temp;
                    else temp = val;
                    $("#targetUser").val(temp);
                });

                $("#editForm").submit();
                return true;
            };
            tinymce.init({
                selector: 'textarea',
                height: 120,
                language: "zh_CN"
            });
        $(function () {
            <%--<c:forEach items="${app.targetUsers}" var="targetUser">--%>
            <c:forEach items="${targetUsers}" var="targetUser">
                $("input:checkbox").each(function () {
                    if($(this).val()==${targetUser}) $(this).attr("checked",true);
                })
            </c:forEach>

        })
    </script>
</head>
<body>

<form method="post" action="${ctx}/app/save" id="editForm" >
    <input type="hidden" id="iconUrl" name="appEntity.iconUrl">
    <input type="hidden" id="picUrl" name="appEntity.picUrl">
    <input type="hidden" id="targetUser" name="appEntity.targetUser">
    <input type="hidden" name="appEntity.id" value="${app.id}"> <br/>
    应用名称:<input type="text" name="appEntity.name" value="${app.name}"> <br/>
    应用LOGO:
    <input type="file" id="icon" name="icon" value="" style="display: none" accept="image/*">
    <input type="hidden" id="iconNew" name="iconNew" value="">
    <ul class="img-list-icon">
        <li>
            <div class="removeBtn" onclick="rmPic(this)">—</div>
            <c:if test="${app.sfczxmz==0}"><img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" data-url="${app.iconUrl}" width="100%" height="100%" class="icons" /></c:if>
            <c:if test="${app.sfczxmz==1}"><img src="${ctxStatic}/image/appDetails/${app.iconUrl}" data-url="${app.iconUrl}" width="100%" height="100%" class="icons" /></c:if>
            <%--<img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" data-url="${app.iconUrl}" width="100%" height="100%" class="icons">--%>
        </li>
    </ul>
    <a id="iconUpload" class="uploadBtn-a">上传图片</a>
    <br>
    应用状态:
    <select name="appEntity.appStatus" class="form-control">
        <option value="0"
                <c:if test="${app.appStatus == 0}">selected</c:if> >待审核
        </option>
        <option value="1"
                <c:if test="${app.appStatus == 1}">selected</c:if> >已上线
        </option>
        <option value="2"
                <c:if test="${app.appStatus == 2}">selected</c:if> >已下线
        </option>
        <option value="3"
                <c:if test="${app.appStatus == 3}">selected</c:if> >其他问题
        </option>
    </select>
    是否为默认应用：
    <select name="appEntity.isDefault" class="form-control">
        <option value="0" <c:if test="${app.isDefault==0}">selected</c:if>>默认</option>
        <option value="1" <c:if test="${app.isDefault==1}">selected</c:if>>购买</option>
    </select>
    应用级别：
    <select name="appEntity.appPermission" class="form-control">
        <option value="1" <c:if test="${app.appPermission==1}">selected</c:if>>区级应用</option>
        <option value="2" <c:if test="${app.appPermission==2}">selected</c:if>>校级应用</option>
    </select>

    目标用户：
    <%--<c:forEach items="${app.targetUsers}" var="targetUser">--%>
        <%--<label><input name="target" type="checkbox" value="${targetUser}"/>${targetUser}</label>--%>
    <%--</c:forEach>--%>
    <label><input name="target" type="checkbox" value="1" />教师</label>
    <label><input name="target" type="checkbox" value="2" />学生</label>
    <label><input name="target" type="checkbox" value="3" />家长</label>
    <label><input name="target" type="checkbox" value="4" />其他</label></br>
    应用类别:
    <select name="appEntity.category" class="form-control">
        <option value="1"
                <c:if test="${app.category == 1}">selected</c:if> >教学教务
        </option>
        <option value="2"
                <c:if test="${app.category == 2}">selected</c:if> >互动空间
        </option>
    </select>
    开发商：<input type="text" name="appEntity.developers" value="${app.developers}"> <br/>
    网址：<input type="text" name="appEntity.webUrl" value="${app.webUrl}"> <br/>
    描述：<textarea name="appEntity.remarks" rows="3">${app.remarks}</textarea> <br/>
    介绍图片:
    <input type="file" id="pic" name="pic"style="display: none" accept="image/*" multiple>
    <input type="hidden" id="picNew" name="picNew" value="${app.picUrl}">
    <ul class="img-list">
        <%--<c:forEach items="${app.picUrls}" var="picURI">--%>
        <c:forEach items="${picUrls}" var="picURI">
            <li>
                <div class="removeBtn" onclick="rmPic(this)">—</div>
                <%--<img src="${ctx}/file/pic/show?picPath=${picURI}" data-url="${picURI}" width="100%" height="100%" class="pictures">--%>
                <c:if test="${app.sfczxmz==0}"><img src="${ctx}/file/pic/show?picPath=${picURI}" data-url="${picURI}" width="100%" height="100%" class="pictures" /></c:if>
                <c:if test="${app.sfczxmz==1}"><img src="${ctxStatic}/image/appDetails/${picURI}" data-url="${picURI}" width="100%" height="100%" class="pictures" /></c:if>
            </li>
        </c:forEach>
    </ul>
    <a id="picUpload" class="uploadBtn-a">上传图片</a><br>
</form>
</body>
<script>
    var iconChoose = document.getElementById("icon");
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
                $(img).attr("class", "icons");
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
        formdata.append("imgPath", '<%=FileUtils.APP_DETAIL_PATH%>');
        xhr.open('post', '${ctx}/file/upload');
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var imagedata =JSON.parse(xhr.responseText);
                var text = imagedata.data ? '上传成功' : '上传失败';
                clearInterval(loop);
                //当收到该消息时上传完毕
                $li.find(".progress span").animate({'width': "100%"}, pecent < 95 ? 200 : 0, function () {
                    $(this).html(text);
                });
                $("#iconNew").val(imagedata.data);
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
</script>
<script>
    var fileChoose = document.getElementById("pic");
    $("#picUpload").on("click", function () {
        fileChoose.click();
    })
            .on("touchstart", function () {
                $(this).addClass("touch")
            })
            .on("touchend", function () {
                $(this).removeClass("touch")
            });
    fileChoose.onchange = function () {
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
            $(".img-list").append($(li));
            reader.onload = function () {
                var result = this.result;
                var img = document.createElement("img");
                $(img).attr("width", "100%");
                $(img).attr("height", "100%");
                $(img).attr("class","pictures");
                img.src = result;
                $(li).append(img);
                //如果图片大小小于100kb，则直接上传
                if (result.length <= maxsize) {
                    img = null;
                    upload(result, file.type, $(li), file.name);
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
                    upload(data, file.type, $(li), file.name);
                    img = null;
                }
            };
            reader.readAsDataURL(file);
        })
    };

    //    图片上传，将base64的图片转成二进制对象，塞进formdata上传
    function upload(basestr, type, $li, name) {
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
        formdata.append("imgPath", '<%=FileUtils.APP_DETAIL_PATH%>');
        xhr.open('post', '${ctx}/file/upload');
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var imagedata = JSON.parse(xhr.responseText);
                var text = imagedata.data ? '上传成功' : '上传失败';
                clearInterval(loop);
                //当收到该消息时上传完毕
                $li.find(".progress span").animate({'width': "100%"}, pecent < 95 ? 200 : 0, function () {
                    $(this).html(text);
                });
                if (!imagedata.data) return;
//                $(".pic-list").append('<a href="' + imagedata.data + '">' + imagedata.data + '（' + imagedata.size + '）<img src="' + imagedata.data + '" /></a>');
                //var detailUrl = $("#pic").val();
                //if (detailUrl.length > 0) detailUrl += "," + imagedata.data;
                //else detailUrl = imagedata.data
                var detailUrl=imagedata.data;
                $li.find("img").attr('data-url',detailUrl);

//                if($("#picNew").val().length>0){
//                    $("#picNew").val($("#picNew").val()+","+detailUrl);
//                }else $("#picNew").val(detailUrl);

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
</script>
</html>
