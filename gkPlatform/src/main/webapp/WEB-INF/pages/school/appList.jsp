<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/z-tree-bootStrap.css" />
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/z-treeAll.js"></script>
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
        ul{
            list-style:none;
            width:450px;
        }
        li{
            float:left;
            height:100px;
            padding-left:30px;
            padding-top:49px
        }
        li>div{
            font-size:14px;color: #565656 ;padding-bottom: 10px;
        }

    </style>
</head>
<body>
<div id="container">
    <form action="${ctx}/school/app/authorization/save" id="inputForm" method="post">
        <input type="hidden" value="${schoolId}" name="schoolId">
        <ul>
        <c:forEach items="${appList}" var="app">
            <li>
                <div>
                    ${app.name}
                    <c:if test="${app.isDefault!=0}">
                        <input class="onPicCheckBox" type="checkbox" name="apps" <c:if test="${appIds.contains(app.id.toString())}">checked</c:if> value="${app.id}"/>
                    </c:if>
                </div>
                <c:if test="${app.sfczxmz==0}"><img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" data-url="${app.iconUrl}" width="100px" height="100px" id="head_url"/></c:if>
                <c:if test="${app.sfczxmz==1}"><img src="${ctxStatic}/image/appDetails/${app.iconUrl}" data-url="${app.iconUrl}" width="100px" height="100px" id="head_url"/></c:if>
                <%--<img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" data-url="${app.iconUrl}" width="100px" height="100px" id="head_url">--%>
            </li>
        </c:forEach>
        </ul>
    </form>
</div>
<script type="text/javascript">
    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var spCodesTemp = "";
        $("input[name='apps']:checked").each(function(i){
            if(0==i){
                spCodesTemp = $(this).attr("value");
            }else{
                spCodesTemp += (","+$(this).attr("value"));
            }
        });

        $.post("${ctx}/school/app/authorization/save",{
            apps:spCodesTemp,
            schoolId:$("input[name='schoolId']").val(),
        },function(retJson){
            alert(retJson);
        },"json");
        return true;
    }
</script>

</body>
</html>