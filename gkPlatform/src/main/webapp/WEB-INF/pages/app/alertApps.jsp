<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>应用管理</title>
    <link href="${ctxStatic}/css/pageDivide.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script  src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <style type="text/css">
        #container {
            padding: 0;
            margin: 0 auto;
            width: 480px;
            height: 250px;
        }
        .onPicCheckBox{
            position: absolute;
            margin: 2px 5px;
        }
        #container>table{
            margin: 0 auto;
            display: inline-table;width: 100%;
            text-align: center;
        }
        #container tr{
            height: 130px;
        }


    </style>

</head>

<body>
<div id="container">
    <form method="post" action="" id="editForm">
        <%--${ctx}/saveMyApp--%>
        <table cellspacing="38">
            <c:if test="${empty otherAppList}"><p>无更多应用</p></c:if>
            <c:forEach items="${otherAppList}" var="otherApp" varStatus="status">
                    <c:if test="${(status.index)%4==0}"><tr></c:if>
                    <td>
                        <img src="${ctx}/file/pic/show?picPath=${otherApp.iconUrl}" width="60px" height="65px"/>
                        <div style="font-size:14px;color: #565656 ;padding-top: 10px;">
                                ${otherApp.name}<input class="onPicCheckBox" type="checkbox" name="cbox" id="" value="${otherApp.id}" style="margin-left: 10px;"/>
                        </div>
                    </td>
                    <c:if test="${(status.index)%4==3 || status.index==(size-1)}"></tr></c:if>
                </c:forEach>
        </table>
        <%--<input id="submit-btn" type="button" value="提交">--%>
    </form>
</div>

</body>
<script>

    function doSubmit(){
        var idVals = "";
        $("input:checkbox[name='cbox']:checked").each(function(i){
            if(0==i){
                idVals = $(this).val();
            }else{
                idVals += ","+$(this).val();
            }
        });
        if(idVals.length>0){
            $.ajax({
                type: 'POST',
                data: {
                    ids:idVals
                },
                dataType: "json",
                url:'${ctx}/app/savemyapp',
                success: function (data) {
                    if (!data) {
                        //alertTips('300px', '150px', '提示', '应用失败,请刷新后重试!');
                        alert("应用失败,请刷新后重试!");
                        return;
                    }
                },
//                error: function () {
//                    alert("请求失败");
//                }
            });
        }else{
            alert("请至少选择一条数据!");
        }

        $("#editForm").submit();
        return true;
    };



</script>
</html>