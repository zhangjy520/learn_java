<%@ page import="cc.gukeer.smartRing.common.RStatusType" %>
<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/css/pop.css">
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/global.js"></script>
    <style>
        .container{width:350px;}
        .container>p{font-size:14px;color:#fc2f5b;margin-bottom:20px;}
        .container>div span{width:32%;color:#676767;}
        ul{background:#F7F9FB;margin-top:15px;}
        ul>li{line-height: 40px;color:#676767;}
        label{cursor: pointer}
        .sh-type>span{width:145px;padding-left:8px;}
        .sh-type label { padding-left: 20px;cursor: pointer;position: relative;margin-right: 30px;
        }
        .sh-type label:before{content:'';position:absolute;width:13px;height:13px;
            left:0;top:2px;background:url(${ctxStatic}/images/icon.png) no-repeat -15px -34px;}
        .sh-type .checked:before{background-position: 0 -34px;}
    </style>
</head>
<body>
<main class="container">
    <p>请确认该学生归还所有临时手环</p>
    <div>
        <span>${student.xsxm}</span>
        <span>${student.xh}</span>
    </div>
    <ul>
        <c:forEach items="${deviceRing}" var="ring" varStatus="i">
            <li class="sh-type">
                <span class="ringmac" name="${ring.id}">${ring.mac}</span>
                <label for="sh1-${i.index}" class="checked">
                    <input type="radio" id="sh1-${i.index}" hidden="hidden" value="<%=RStatusType.STATUS_UNUSED%>" checked/>
                    <span>归还</span>
                </label>
                <label for="sh2-${i.index}">
                    <input type="radio" id="sh2-${i.index}" hidden="hidden"  value="<%=RStatusType.STATUS_LOST%>"/>
                    <span>丢失</span>
                </label>
            </li>
        </c:forEach>
    </ul>
</main>
<script>

</script>
</body>
</html>