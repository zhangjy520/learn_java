<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        #addArea{padding:25px 30px;font-size:13px;color:#525252;width:570px;height:515px;margin:0 auto;}
        div.row{margin-bottom:18px;overflow:hidden;}
        span{width:70px;display:inline-block}
        input[type=text]{width:435px;height:30px;padding:0 8px;}
        textarea{width:436px;height:60px;padding:8px;resize: none;}
        input[type=text],textarea{outline: none;border:1px solid #ddd;}
        table{width:100%;text-align: left;border-collapse: collapse}
        table th{font-weight:500;color:#000;}
        table th,table td{height:30px;border-bottom:1px solid #ddd;}
        input[type=checkbox]{display:inline-block;margin:0 10px;position:relative;top:2px;}
        .row p{border-bottom:1px solid #ddd;padding:10px 0;}
        .table{height:280px;overflow-y: auto}
    </style>
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js"></script>
</head>
<body>
<div id="addArea">
    <input type="hidden" id="areaId" value="${stationArea.id}">
    <div class="row">
        <span>区域名称</span>
        <input type="text" id="areaName" placeholder="请输入区域名称，区域名称不能重复" value="${stationArea.areaName}">
    </div>
    <div class="row">
        <span style="vertical-align: top;">说明</span>
        <textarea id="remarks" placeholder="描述这个区域由哪些物理空间组成" >${stationArea.remarks}</textarea>
    </div>
    <div class="row">
        <p>包含网关</p>
        <div class="table">
            <table>
                <thead>
                <tr>
                    <th width="33%"><input type="checkbox" class="checkbox">网关MAC</th>
                    <th width="30%">所在区域</th>
                    <th width="37%">详细设置</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${deviceStations}" var="station">
                    <tr>
                        <c:choose>
                            <c:when test="${gukeer:notEmptyString(station.areaId) && station.areaId!=stationArea.id}">
                                <td style="color: #999">
                                    <input type="checkbox"name="Station" value="${station.id}" disabled="disabled">
                                        ${station.stationMac}
                                </td>
                                <td style="color: #999">${station.areaName}</td>
                                <td style="color: #999">${station.remarks}</td>
                                <td></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <input type="checkbox"name="Station" value="${station.id}"
                                           <%--<c:if test="${gukeer:notEmptyString(station.areaId) && station.areaId!=stationArea.id}">disabled="disabled"</c:if>--%>
                                           <c:if test="${gukeer:notEmptyString(station.areaId) && station.areaId==stationArea.id}">checked</c:if>
                                    >
                                        ${station.stationMac}
                                </td>
                                <td>${station.areaName}</td>
                                <td>${station.remarks}</td>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    var checkAll=document.getElementsByClassName('checkbox')[0];
    checkAll.onclick=function(){
        var checks=document.querySelectorAll('tbody input[type=checkbox]');
//            console.log(checks);
        if(this.checked){
            for(var i=0;i<checks.length;i++){
                checks[i].checked=true;
                if (checks[i].disabled){
                    checks[i].checked=false;
                }
            }
        }else{
            for(var i=0;i<checks.length;i++){
                checks[i].checked=false;
            }
        }
    }

    function doSubmit() {
        var areaId = $("#areaId").val();
        var areaName = $("#areaName").val();
        var remarks = $("#remarks").val();

        var temp=null;
        $("input[name = 'Station']:checked").each(function(){
            var val = $(this).val();
            if(temp!=null) temp = val+","+temp;
            else temp = val;
        });

        $.post('${ctx}/manage/area/save', {
            areaId:areaId,
            areaName:areaName,
            remarks:remarks,
            stations:temp
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