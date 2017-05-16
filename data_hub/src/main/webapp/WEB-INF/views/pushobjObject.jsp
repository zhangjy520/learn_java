<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lx
  Date: 2017/4/12
  Time: 18:25
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

</head>
<style>
    table{
        width: 100%;
        font-size: 14px !important;
        color: #525252;
    }
    th{
        border-bottom: none;
        text-align: center;
        line-height: 50px !important;
    }
    td{
        line-height: 35px;
        text-align: center;
    }
    .modify, .dele{
        width: 50px;
        height: 20px;
        line-height: 20px;
        text-align: center;
        font-size: 12px;
        border:none;
        color: #fff;
        border-radius: 5px;
    }
    .modify{
        background: rgba(0, 189, 239, .5);
    }
    .dele{
        background: rgba(172, 41, 37, .55);
    }
</style>
<body>
<%@ include file="common/layout/menu.jsp" %>
<div class="container">
    <main id="main-tab">
       <table>
             <form  method="POST" action="${ctx}/admin/detail/getTable?id=" style="display: inline-block" >
                  <input type="submit" value="新增">
              </form>
             <table id="tableDate" cellpadding="0" cellspacing="0" border='1' borderColor="#e5e5e5">
                 <tr>
                     <th width="250px">表名</th>
                     <th  width="150px">对象名</th>
                     <th  width="150px">操作</th>
                 </tr>
                 <c:forEach items="${pushObjTableList}" var="pushObjTable" varStatus="status">
                 <tr>
                     <td  width="20px">${pushObjTable.tableName}</td>
                     <td  width="20px">${pushObjTable.ObjectName}</td>
                     <td  width="20px">
                         <form id="my" method="POST" action="${ctx}/admin/detail/getTable?id=${pushObjTable.id}" style="display: inline-block">
                            <input class="modify" type="submit" value="修改"/>
                         </form>
                         <button class="dele" onclick="deleteObject( '${pushObjTable.id}')">删除</button>
                     </td>
                 </tr>
                 </c:forEach>
             </table>
       </table>
    </main>
</div>
<footer></footer>
<script>
    $("#menu_5").addClass("active");
    /*
    $('select').change(function(){
        getDate();
//        $('#personTable').css('display','none');
    })
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function  getDate() {
        var belong = $("#tableName ").val();

        $.get("${ctx}/admin/detail/get",{
            belong:belong
        },function (data) {
            var html="";
            $("#tableDate").append(html);
            $("#tableDate tr").empty();
            html+=' <tr>'+
                    '                                 <th width="150px">字段</th>'+
                    '                                 <th  width="150px">表名</th>'+
                    '                                 <th  width="150px">操作</th>'+
                    '                             </tr>';
            $("#tableDate").append(html);
           var obj= eval('('+data+')');
            for (var key in obj) { //第一层循环取到各个list
                var List = obj[key];
                $("#tableDate").append(
                        '                    <tr>' +
                        '                        <td>' + List.name + '</td>' +
                        '                        <td>' + List.belong + '</td>' +
                        "<td >" +
                        '    <form id="my" method="POST" action="'+postPath+'/admin/detail/getDate?id='+List.id+'" style="display: inline-block">'+
                        '                                            <input type="submit" value="修改"/>'+
                        '                                         </form>'+
                        "<button onclick=\"deleteziduan("+"List.id"+")\">删除</button></td>"+
                        '                    </tr>'
                );
            }
        })
    }
    */
    function deleteObject(idd) {
        var id = idd;
        $.get("${ctx}/admin/push/objectDelete",{
            id:idd
        })
        window.location.reload(true);
        alert("删除成功")
    }


</script>

</body>

</html>
