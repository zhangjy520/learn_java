<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 马立立
  Date: 2017/5/2
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="common/resouces.jsp" %>
    <script src="${ctxStatic}/js/bootstrap-table.js"></script>
    <script src="${ctxStatic}/js/bootstrap-table-zh-CN.js"></script>
    <script src="${ctxStatic}/js/jquery.min.js"></script>
</head>
<style>
    div{
        color: #525252;
    }
    select,input[type='text']{
        height: 30px;
        color: #525252;
        font-size: 14px;
    }

    input[type='text']{
        padding-left: 10px;
    }
    option{
        height: 30px !important;
        line-height: 30px !important;
    }
    #tableDate{
        margin-top: 10px;
    }
    input[type='checkbox']{
        position: relative;
        bottom: -2px;
    }
</style>
<body>
<%@ include file="common/layout/menu.jsp" %>
<div class="container">
    <main id="main-tab">
<<<<<<< HEAD
        <input type="button" value="保存" onclick="checkRight()" />
            <div style="margin-top: 20px;">
                <span>表名:</span>
                <select id="NameTable">
                    <c:forEach var="pushObjTable" items="${pushObjTableList}" varStatus="status">
                        <option <c:if test="${pushObjTable.id eq detailObj.pushObjId}">selected</c:if>
                                   value="${pushObjTable.id }">
                                ${pushObjTable.tableName}
                        </option>
                    </c:forEach>
                </select>
                <span style="margin-left: 20px;">对象名:</span>
                <input type="text"id="objectName" name="objectName" value="${detailObj.name}" >
                <input type="text" value="${pushObjTableList.get(0).id}" id="tableId" name="tableId"  style="display:none;">
                <input type="text" id="tableName" name="tableName" value="${pushObjTableList.get(0).tableName}" style="display: none"  >
                <input type="text" id="filed" name="filed" value="${columnList}" style="display: none">
                <input style="display: none" type="text" name="objectId" id="objectId" value="${detailObj.id}">
            </div>
            <div id="tableDate">
                <c:forEach items="${pushObjFieldLists}"  var="pushObjFieldList"  varStatus="status">
                    <div style="display: inline-block;width:200px;">
                        <input type="checkbox" name="box" onclick="hit()"
                               value="${pushObjFieldList.filed}">
                        <span> ${pushObjFieldList.filed}</span>
=======
        <table id="table" class="table" style="margin-top: 30px;">
            <tbody>

                    <div>
                        <label>表名:</label>
                        <select id="NameTable">
                            <c:forEach var="pushObjTable" items="${pushObjTableList}" varStatus="status">

                                <option    <c:if test="${pushObjTable.id eq detailObj.pushObjId}">selected</c:if>
                                           value="${pushObjTable.id }">
                                        ${pushObjTable.objTableName}
                                </option>
                            </c:forEach>
                        </select>
                        <label>对象名:</label>
                        <input type="text"id="objectName" name="objectName" value="${detailObj.name}" >
                        <input type="text" value="${pushObjTableList.get(0).id}" id="tableId" name="tableId"  style="display:none;">
                        <input type="text" id="tableName" name="tableName" value="${pushObjTableList.get(0).objTableName}" style="display: none"  >
                        <input type="text" id="filed" name="filed" value="${columnList}" style="display: none">
                        <input style="display: none" type="text" name="objectId" id="objectId" value="${detailObj.id}">
                    </div>
                    <div id="tableDate">
                        <c:forEach items="${pushObjFieldLists}"  var="pushObjFieldList"  varStatus="status">
                            <div style="display: inline-block;width:200px;">
                                <input type="checkbox" name="box" onclick="hit()"
                                       value="${pushObjFieldList.filed}">
                                   <font size="4px"> ${pushObjFieldList.filed}</font>
                            </div>
                        </c:forEach>
                    </div>
                    <div>
                        <input type="button" value="保存" onclick="checkRight()" />
>>>>>>> e99c16d9dc356b450a71ba4718bb32ec7cedc2ba
                    </div>
                </c:forEach>
            </div>
    </main>
</div>
<script>
    $("#menu_5").addClass("active");
    $(function() {
        var module_ids = eval(${columns});
        $.each(module_ids, function(i,item){
            $("input[name=box][value="+item+"]").attr("checked","checked");
        });
        getTableId();
        getTableName();
    });

    function hit() {
        var obj=document.getElementsByName('box');
        var s='';
        for(var i=0; i<obj.length; i++){
            if(obj[i].checked) s+=',' + obj[i].value; //如果选中，将value添加到变量s中
        }
        $("#filed").attr("value",s);
    }

    $('select').change(function(){
        getDate();
        getTableName();
        getTableId();
        $("#filed").attr("value","");
    })
    function getTableId() {
        var belong = $("#NameTable ").val();
        $("#tableId").attr("value",belong);
    }
    function getTableName() {
        var belong = $("#NameTable option:selected").text().trim();
        $("#tableName").attr("value",belong);
    }
    function  getDate() {
        var belong = $("#NameTable option:selected").text().trim();

        $.get("${ctx}/admin/detail/getDate",{
            belong:belong
        },function (data) {
            $("#tableDate ").empty();
            var obj= eval('('+data+')');
            for (var key in obj) { //第一层循环取到各个list
                var List = obj[key];
                $("#tableDate").append(
                        '     <div style="display: inline-block;width:200px;">'+
                        '                                <input type="checkbox" name="box" onclick="hit()" value="'+List.filed+'";>'+
                        '                                   <font size="4px"> '+List.filed+'</font>'+
                        '                            </div>'
                );
            }
        })
    }
    function checkRight() {

        var check = true;
        var ObjectName = $("#objectName").val();

        if(ObjectName==""||ObjectName==null){
            alert("对象名不能为空");
            check = false;
            return check;
        }
        var objectId = $("#objectId").val();
        var tableId = $("#tableId").val();

        var tableName=$("#tableName").val();
        var filed = $("#filed").val();
        $.post("${ctx}/admin/detail/objInsert?id=${detailObj.id}",{
            objectName:ObjectName,
            objectId:objectId,
            tableId:tableId,
            tableName:tableName,
            filed:filed
        }, function (data) {
            if(data=="false"){
                alert("已有该对象名");
            }else{
                window.location.href="${ctx}/admin/push/select";
            }
        })
    }
</script>

</body>
</html>
