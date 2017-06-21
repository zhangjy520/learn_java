<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/base.jsp" %>
<html>
<head>
    <title>推送对象绑定</title>
    <%@ include file="common/resouces.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/css/zTreeStyle.css">
</head>
<style>

    td,th{
       padding: 0 !important;
    }
    td{
        vertical-align: middle !important;
        line-height: 35px !important;
    }
    table{
        font-size: 14px;
        color: #525252;
    }
    .table>thead>tr>th{
        line-height: 50px !important;
    }
    button{
        padding: 3px 10px !important;
        font-size: 12px;
    }
    .bbtn{
        width: 90px;
        text-align: center;
        font-size: 12px;
        border: none;
        color: #fff;
        border-radius: 5px;
        background: rgba(0, 189, 239, .5);
        outline: none;
    }
    .bbtn:hover{
        background: rgba(0, 189, 239, .5);
        border:none;
    }
</style>
<body>
<%@ include file="common/layout/menu.jsp" %>
<div class="container">
    <main id="main-tab">
        <table id="table" class="table" style="margin-top: 30px;" cellpadding="0" cellspacing="0" border='1' borderColor="#e5e5e5">
            <thead>
            <tr>
                <%--<th width="5%">序号</th>--%>
                <%--<th width="10%">是否有异常</th>--%>
                <th>应用名称</th>
                <th>平台名称</th>
                <th>队列名</th>
                <th>推送对象</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${queueObjViewList}" var="ObjView" varStatus="status">
                <tr>
                    <td>${ObjView.appName}</td>
                    <td>${ObjView.platName}</td>
                    <td>${ObjView.queueName}</td>
                    <td>
               <%-- <c:forEach items="${result}" var="result" varStatus="status">
                        &lt;%&ndash;${result.PlatAppId}&&${ObjView.id}&ndash;%&gt;
                    <c:if test="${result.PlatAppId eq ObjView.queueId}">

                    <div>
                    <span>${pushObj.objName}</span>
                    <span>------${pushObj.objAbstract}</span>
                    </div>
                    <span>${result.name}</span>
                    <span>------${result.ObjTableName}</span>
                    <div>
                        </c:if>
                </c:forEach>--%>
                    <button type="button" class="btn btn-info bbtn" data-toggle="modal" data-target="#${ObjView.id}" >绑定推送对象</button>
                        <div>
                            <div id="${ObjView.id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title" id="gridSystemModalLabel">推动对象列表</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form class="${ObjView.id}" action="${ctx}/admin/refobj">

                                            <%--<input type="hidden" name="queueId" value="${ObjView.queueId}">--%>
                                               <%--<c:forEach items="${pushObjList}" var="push" varStatus="status">--%>
                                                 <%--<p><input id="${push.id}" type="checkbox" checked name="objId" value="${push.id}" />${push.objName} -------<span>${push.objAbstract}</span></p>--%>
                                               <%--</c:forEach>--%>

                                                <ul class="ztree" id="treeDemo"></ul>

                                            <input type="hidden" name="queueId" value="${ObjView.queueId}">
                                                <c:forEach items="${queue}" var="result" varStatus="status">
                                                   <p> <input id="${result.id}" type="checkbox" <c:if test="${ObjView.detailObjIdList.contains(result.id)}">checked </c:if>name="objId" value="${result.id}"/>${result.name}--------------<span>${result.ObjTableName}</span></p>
                                                </c:forEach>
                                             </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                            <button type="button" onclick="pushobj(this)" name="${ObjView.id}" class="btn btn-primary">保存</button>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
</div>
<footer></footer>
<script>
 function pushobj (a) {
     var id = a.name;
     var id_text = '.'+id;
     $(id_text).submit();
 }

    $("#menu_2").addClass("active");
</script>
</body>
</html>
