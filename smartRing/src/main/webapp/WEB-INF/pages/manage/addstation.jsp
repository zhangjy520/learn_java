<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        #addBasicStation{
            font-size:13px;
            color:#525252;
            padding:0 30px;
            width:396px;
            height:360px;
            /*border:1px solid #ddd;*/
        }
        #addBasicStation>p{color:#fc2f5b;margin:25px 0 15px 0;}
        #addBasicStation .row{margin-bottom:15px;}
        #addBasicStation .row p{display:inline;}
        #addBasicStation .row span{display:inline-block;width:70px;}
        #addBasicStation .row input,#addBasicStation .row select,#addBasicStation .row textarea{
            border:1px solid #ddd;
            width:300px;
            height:28px;
            padding-left:8px;
            outline: none;
        }
        #addBasicStation .row select{width:310px;height:32px;}
        #addBasicStation .row textarea{height:120px; resize: none;padding:8px;}
        .relevance{display:none;}

        #addBasicStation .row p{display:inline;}
    </style>
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js"></script>
</head>
<body>
<div id="addBasicStation">
    <input type="hidden" id="stationId" value="${station.id}"/>
    <c:if test="${empty station.id}">
        <p>网关MAC和种类一旦确定，不可修改，请谨慎操作！</p>
    </c:if>
    <div class="row">
        <span>网关MAC</span>
        <c:if test="${empty station.stationMac}">
            <input type="text" placeholder="请输入MAC地址" id="macid" value="${station.stationMac}">
        </c:if>
        <c:if test="${not empty station.stationMac}">
            <input type="hidden" id="macid" value="${station.stationMac}">
            <p>${station.stationMac}</p>
        </c:if>

    </div>
    <div class="row">
        <span>种类</span>
        <c:if test="${empty station.category}">
            <select name="" id="basicStation" value="${station.category}">
                <option value="1">校门口网关</option>
                <option value="2">公共网关</option>
                <option value="3">班级网关</option>
            </select>
        </c:if>
        <c:if test="${station.category==1}"><p>校门口网关</p></c:if>
        <c:if test="${station.category==2}"><p>公共网关</p></c:if>
        <c:if test="${station.category==3}"><p>班级网关</p></c:if>
        <c:if test="${not empty station.category}"><input type="hidden" id="basicStation" value="${station.category}"></c:if>
    </div>
    <div class="row relevance" style="display: none">
        <span>关联班级</span>
        <%--<select id="gradeclass" name="">--%>
            <%--<c:forEach items="${classList}" var="cla">--%>
                <%--<option value="${cla.name}">${cla.remarks}</option>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
        <select class="range-select" id="njSelect">
            <option value="">请选择年级</option>
        </select>
        <select class="range-select" id="bjSelect" style="margin-top:15px;margin-left:74px;">
            <option value="">请选择班级</option>
        </select>
    </div>
    <div class="row">
        <span style="vertical-align: top;">详细位置</span>
        <textarea id="remark" name="" placeholder="描述网关具体部署在哪里">${station.remarks}</textarea>
    </div>
    <div class="row">
        <span>所在区域</span>
        <select id="area" name="">
            <option value="">暂不选择</option>
            <c:forEach items="${areaList}" var="area">
                <option value="${area.id}">${area.areaName}</option>
            </c:forEach>
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
    //初始化
    $(function () {
        var category = $("#basicStation").val();
        if ( category == 3) {
            var relevance=document.getElementsByClassName('relevance')[0];
            relevance.style.display='block';
        }

        <c:if test="${empty station.category}">
        var station=document.getElementById('basicStation');
        station.onchange=function(){
            var relevance=document.getElementsByClassName('relevance')[0];
            if(this.value==3){
                relevance.style.display='block';
            } else{
                relevance.style.display='none';
            }
        }
        </c:if>

        $('#njSelect').change(function () {
            initBj();
        });

        for (var i = 0; i < classList.length; i++) {
            var node = classList[i];
            if (node.pid == '-10') {
                html = "<option value='" + node.id + "'  >" + node.name + "</option>"
                $("#njSelect").append(html);
            }
        }

        if (${not empty station.classId}) {
            var xd = '${gradeClass.xd}'
            var nj = '${gradeClass.nj}';
            var xdnj = xd+'_'+nj;
//            $("#njSelect").attr("value",xdnj);
            $("#njSelect").val(xdnj);
            initBj();
            $("#bjSelect").val('${station.classId}');
        }
        if (${not empty station.areaId}) {
            $("#area").val('${station.areaId}');
        }
    })

    function initBj() {
        $('#bjSelect').empty();
        $('#bjSelect').append("<option value=''>请选择班级</option>");
        for (var i = 0; i < classList.length; i++) {
            var node = classList[i];
            if (node.pid == $("#njSelect").val()) {
                html = "<option value='" + node.id + "'  >" + node.name + "</option>"
                $("#bjSelect").append(html);
            }
        }
    }

    function doSubmit() {
        var id = $("#stationId").val();
        var mac = $("#macid").val();
        var category = $("#basicStation").val();
//        var grade = $("#njSelect").val();
//        var i = grade.indexOf('_');
//        var xd = grade.substr(0, i);
//        var nj = grade.substr(i + 1);
//        var classname = $("#bjSelect").val();
        var gradeclass = $("#bjSelect").val();

        var remark = $("#remark").val();
        var area = $("#area").val();

        $.post('${ctx}/manage/station/save', {
            id:id,
            stationMac:mac,
            category:category,
            gradeclass:gradeclass,
            remark:remark,
            areaId:area
        }, function (res) {
            if (res.code == '-1') {
                layer.msg(res.msg,{time:1500});
            } else {
                layer.msg(res.msg,{time:1500},function () {
                    parent.location.reload();
                });
            }
        })
    }



</script>
</body>
</html>