<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script src="${ctxStatic}/upload/h5upload.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>
    <style>
        .container {
            width: 800px;
            margin: 0 auto;
            /*padding-top: 30px;*/
            font: 12px '微软雅黑';
        }

        .container > h3 {
            font-size: 16px;
            font-weight: normal;
            color: #54AB37;
            margin: 20px 0;
            padding: 0px 0 0px 8px;
            border-left: 3px solid #54AB37;
        }

        .container > h3 button {
            float: right;
            color: #fff;
            border-radius: 2px;
            background: #54AB37;
            border: 1px solid #54AB37;
            width: 80px;
            height: 35px;
            font-weight: bold
        }

        .stuMsg {
            overflow: hidden;
        }

        ul {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .left {
            float: left;
            width: 50%;
        }

        .right {
            float: right;
            width: 50%;
        }

        .stuMsg span {
            display: inline-block;
            width: 36%;
            text-align: right;
        }

        .stuMsg input[type=text] {
            width: 190px;
            height: 28px;
            padding:0;
            padding-left:5px;
            border-radius:3px;
            border:1px solid #a9a9a9;
        }

        .stuMsg input[type=radio] {
            margin: 0 20px;
        }

        .stuMsg input[class=laydate-icon] {
            width: 190px;
            border: 1px solid #a9a9a9;
        }

        .stuMsg label {
            margin-right: 61px;
        }

        ul li {
            position: relative;
            margin: 15px 0;
        }

        b {
            font-size: 20px;
            color: #E51C23;
            position: absolute;
            top: 4px;
            right: 36px;
        }

        .radio b {
            top: -3px;
        }

        .stuMsg select {
            font-size: 14px;
            color: #999;
            width: 197px;
            height: 28px;
            padding-left: 5px;
            border: 1px solid #a9a9a9;
            border-radius: 4px;
        }

        .uploading {
            display: inline-block;
            vertical-align: -600%;
            width: 60%;
            text-align: center;
        }

        .uploading p {
            width: 90px;
            height: 86px;
            background: url('${ctxStatic}/image/image.png');
            margin: 0;
            margin-left: 104px;
        }

        .uploading button {
            margin-top: 10px;
            padding: 5px 20px;
            color: #fff;
            background: #54AB37;
            border: 1px solid #54AB37;
            font-weight: bold;
        }

        .parentMsg P {
            FONT-SIZE: 14PX;
            color: #666;
            padding-left:11px;
        }

        .parentMsg ul {
            overflow: hidden;
            box-sizing: border-box;
        }

        .parentMsg ul li {
            float: left;
            width: 50%;
            margin: 15px 0
        }

        .parentMsg ul li span {

            display: inline-block;
            width: 36%;
            text-align: right;
        }

        .parentMsg input[type=text] {
            height: 28px;
            padding:0;
            padding-left:5px;
            width:190px;
            border:1px solid #a9a9a9;
            border-radius: 3px;
            outline: none;
        }

        .parentMsg select {
            width: 190px;
            height: 28px;
            border-radius: 4px;
            outline: none;
        }

        .container table {
            border-collapse: collapse
        }

        .container table th, .container table td {
            border: 1px solid #ddd;
            padding: 10px 0;
        }

        .container table th {
            background: #eee;
        }

        .addContent {
            font-size: 16px;
            color: #999;
            padding-left: 15px;
        }
    </style>
    <script>
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。

            var macId = $("input[name='macId']").val();
            var equipmentName= $("input[name='equipmentName']").val();
            var classroom= $("input[name='classroom']").val();

            if (macId == '') {
                layer.msg("终端MAC地址不能为空！");
                return false;
            }

            if (equipmentName == '') {
                layer.msg("设备名称不能为空！");
                return false;
            }

            if (classroom == '') {
                layer.msg("班牌显示名称不能为空！");
                return false;
            }

            var teachClassRoomId=$('#roomNum').val();
            var classId=$('#teach_class').val();
            if(teachClassRoomId==0 ||teachClassRoomId==''){
                layer.msg("房间号不能为空！");
                return false;
            }
            if(classId==0 ||classId==''){
                layer.msg("班级不能为空！");
                return false;
            }

            $.post($("form").attr('action'), {
                macId:macId,
                equipmentName:equipmentName,
                classroom:classroom,
                teachClassRoomId:teachClassRoomId,
                classId:classId,
                id:'${classCard.id}',
                schoolId:'${schoolId}'

            }, function (retJson) {
                if (retJson.code == '0') {
                    <%--window.location.replace("${ctx}/student/index");--%>
                    window.parent.location.reload(true);
                } else {
                    layer.msg(retJson.msg);
                }
            });
            return true;
        }
    </script>
</head>
<body>
<form action="${ctx}/classCard/save">

    <div class="container">
        <h3>班牌信息</h3>
        <div class="stuMsg">
            <ul class="left">

                <li>
                    <span>终端MAC地址：</span>
                    <input ${disabled} type="text" name="macId" value="${classCard.macId}"/>
                </li>

                <li>
                    <span>校区：</span>
                    <select ${disabled} id="xq" name="xq">
                        <option value="0">请选择校区</option>
                        <c:forEach items="${schoolTypes}" var="xq">
                            <option value="${xq.id}"
                                    <c:if test="${classRoom.schoolType eq xq.id}">selected</c:if>  > ${xq.name}</option>
                        </c:forEach>
                    </select><b>*</b>
                </li>

                <li>
                    <span>楼名称：</span>
                    <select ${disabled} id="teachBuilding" name="teachBuilding">
                        <option value="0">请选择楼名称</option>
                    </select><b>*</b>
                </li>
                <li>
                    <span>楼层：</span>
                    <select ${disabled} id="floor" name="floor">
                            <option value="0">请选择楼层</option>
                    </select><b>*</b>
                </li>
                <li>
                    <span>房间号：</span>
                    <select ${disabled} id="roomNum" name="roomNum">
                            <option value="0">请选择房间号</option>
                    </select><b>*</b>
                </li>
            </ul>


            <ul class="right">
                <li>
                    <span>设备名称：</span>
                    <input ${disabled} type="text" name="equipmentName" value="${classCard.equipmentName}"/>
                </li>
                <li>
                    <span>班牌显示名称：</span>
                    <input ${disabled} type="text" name="classroom" value="${classCard.classroom}"/>
                </li>
                <li>
                    <span>学段：</span>
                    <select ${disabled} id="xd">
                            <option value="0">请选择学段</option>
                            <c:forEach items="${xds}" var="xd">
                                <option value="${xd.xdId}"
                                    <c:if test="${gradeClassExtention.xd eq xd.xdId}">selected</c:if>  > ${xd.xd}</option>
                            </c:forEach>

                    </select><b>*</b>
                </li>
                <li>
                    <span>年级：</span>
                    <select ${disabled} id="teach_grade">

                           <option value="0">请选择年级</option>
                    </select><b>*</b>
                </li>
                <li>
                    <span>班级：</span>
                    <select ${disabled} id="teach_class">

                            <option value="0">请选择班级</option>
                    </select><b>*</b>
                </li>

            </ul>
        </div>

    </div>
</form>
<script>

    //回显
    $(function () {
        var option ='${option}';
        if(option=="edit"){
            //-----编辑班牌时回显位置---------
            var xqId ='${classRoom.schoolType}';
            var teachBuilding = '${classRoom.teachBuilding}';
            var floor = '${classRoom.floor}';
            var paras = {};
            paras.xqId = xqId;
            paras.flag = 'xq';
            paras.teachBuilding = '';
            paras.floor = '';
            cascade_location(paras);

            paras.teachBuilding = teachBuilding;
            paras.flag = 'teachBuilding';
            cascade_location(paras);

            paras.flag = 'floor';
            paras.floor = floor;
            cascade_location(paras);


            //----编辑班牌时回显班级---------
            var class_params={};
            class_params.xdId='${gradeClassExtention.xd}';
            class_params.flag='xd'
            class_params.nj='';
            cascade_class(class_params);

            class_params.nj='${gradeClassExtention.nj}';
            class_params.flag='nj';
            cascade_class(class_params);


        }
    });

    //位置级联参数
   var _param={};
   function determineLocationParam() {
       _param["xqId"]=$('#xq').val();
       _param["teachBuilding"]=$('#teachBuilding').val();
       _param["floor"]=$('#floor').val();
   }
    //位置级联方法
   function cascade_location(param) {
       $.ajax({
           url:"${ctx}/classCard/cascadeClassRoom",
           type:"post",
           data:{
               'mydata':JSON.stringify(param)
           },
           success:function(data){
            if(data.code=1){
                var flg =data.data.flag;
                var _classRomms=data.data.classRomms;
                if(flg == 'xq'){
                    for(var i=0; i<_classRomms.length;i++){
                        var classRoomTeachBuilding='${classRoom.teachBuilding}';
                        var tmp=_classRomms[i].teachBuilding;
                        var html="<option value='"+_classRomms[i].teachBuilding+"'"
                        if(classRoomTeachBuilding==tmp){
                            html+="selected";
                        }
                        html+=">"+_classRomms[i].teachBuilding +"</option>"
                        $('#teachBuilding').append(html);

                    }
                }else if(flg=='teachBuilding'){
                    for(var i=0; i<_classRomms.length;i++){
                        var classRoomFloor='${classRoom.floor}';
                        var tmp=_classRomms[i].floor;
                        var html="<option value='"+_classRomms[i].floor+"'";
                        if(classRoomFloor==tmp){
                            html+="selected";
                        }
                        html+= ">"+_classRomms[i].floor +"</option>"
                        $('#floor').append(html);
                    }
                }else if (flg=='floor'){
                    for(var i=0; i<_classRomms.length;i++){
                        var classRoomRoomNum='${classRoom.roomNum}';
                        var tmp=_classRomms[i].roomNum;
                        var html="<option value='"+_classRomms[i].id+"'";
                        if(classRoomRoomNum==tmp){
                            html+="selected";
                        }
                        html+=">"+_classRomms[i].roomNum +"</option>";
                        $('#roomNum').append(html);

                    }
                }
            }
           },
           error: function (e) {
               layer.msg("暂无相关数据");
           }
       })
   }

    $("#xq").change(function () {
        $("#teachBuilding").empty();
        $("#floor").empty();
        $("#roomNum").empty();
        $("#teachBuilding").append("<option value=''>请选择楼名称</option>");
        $("#floor").append("<option value= ''>请选择楼层</option>");
        $("#roomNum").append("<option value=''>请选择房间号</option>");
        _param["flag"]="xq";
        determineLocationParam();
        cascade_location(_param)
    });
    $("#teachBuilding").change(function () {
        $("#floor").empty();
        $("#roomNum").empty();
        $("#floor").append("<option value=''>请选择楼层</option>");
        $("#roomNum").append("<option value=''>请选择房间号</option>");
        _param["flag"]="teachBuilding";
        // alert(_param.flag+'------');
        determineLocationParam();
        cascade_location(_param)
    });
    $("#floor").change(function () {
        $("#roomNum").empty();
        $("#roomNum").append("<option value=''>请选择房间号</option>");
        _param["flag"]="floor";
        // alert(_param.flag+'------');
        determineLocationParam();
        cascade_location(_param)
    });


    //班级级联参数
   var _param_class={};
   function determineClassParam() {
       _param_class["xdId"]=$('#xd').val();
       _param_class["nj"]=$('#teach_grade').val();
   }
    //班级级联方法
   function cascade_class(param) {
       //console.log(JSON.stringify(param));
       $.ajax({
           url:"${ctx}/classCard/cascadeClass",
           type:"post",
           data:{
               'mydata':JSON.stringify(param)
           },
           success:function(data){
               console.log(data);
               if(data.code=1){
                   var flg =data.data.flag;
                   var _gradeClasses=data.data.gradeClasses;
                   //console.log(flg);
                   if(flg == 'xd'){
                       for(var i=0; i<_gradeClasses.length;i++){
                           var html="<option value='"+_gradeClasses[i].nj+"'";
                           if(_gradeClasses[i].nj =='${gradeClassExtention.nj}'){
                               html+="selected";
                           }
                           html+=">"+_gradeClasses[i].nj +"</option>";
                           $('#teach_grade').append(html);
                       }
                   }else if(flg=='nj'){
                       for(var i=0; i<_gradeClasses.length;i++){
                           var html ="<option value='"+_gradeClasses[i].id+"'";
                           var tmp='${gradeClassExtention.id}'

                           if(_gradeClasses[i].id =='${gradeClassExtention.id}'){
                               html+="selected";
                           }
                           html+=">"+_gradeClasses[i].name +"</option>";
                           $('#teach_class').append(html);
//                           "<option value='"+_gradeClasses[i].id+"'>"+_gradeClasses[i].name +"</option>"
                       }
                   }
               }
           },
           error: function (e) {
               layer.msg("暂无相关数据");
           }
       })
   }

   $("#xd").change(function () {
       $("#teach_grade").empty();
       $("#teach_class").empty();
       $("#teach_grade").append("<option value=''>请选择年级</option>");
       $("#teach_class").append("<option value=''>请选择班级</option>");
       _param_class["flag"]='xd';
       determineClassParam();
       cascade_class(_param_class);
   });
   $("#teach_grade").change(function () {
       $("#teach_class").empty();
       $("#teach_class").append("<option value=''>请选择班级</option>");
       _param_class["flag"]='nj';
       determineClassParam();
       cascade_class(_param_class);
   });

   //判断房间号是否被占用
    $('#roomNum').change(function () {
        $.ajax({
            url:'${ctx}/classCard/isLocaltationToken',
            type:'post',
            data:{
                roomNum:this.value
            },
            success:function (data) {
                alert(data.code);
            },
            error:function () {
                layer.msg("暂无相关数据");
            }
        })


    })

    //判断班级是否被占用
    $('#teach_class').change(function () {
        $.ajax({
            url:'${ctx}/classCard/isClassToken',
            type:'post',
            data:{
                classId:this.value
            },
            success:function (data) {
                alert(data.code);
            },
            error:function () {
                layer.msg("暂无相关数据");
            }
        })


    })




</script>
</body>
</html>