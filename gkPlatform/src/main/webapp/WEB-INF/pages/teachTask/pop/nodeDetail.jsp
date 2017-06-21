<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>课节设置</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    .searchh button:after {
        content: '';
        display: inline-block;
        width: 16px;
        height: 16px;
        position: absolute;
        top: 8px;
        left: 10px;
        background: url(../../../../assetsNew/images/icon-2.png) no-repeat -1px -65px
    }

    .searchh button {
        float: right;
        height: 30px;
        background: #54ab37;
        width: 35px;
        position: relative;
        border: 1px solid #54ab37;
        border-top-right-radius: 2px;
        border-bottom-right-radius: 2px
    }

    .searchh input {
        float: right;
        height: 31px;
        border: 1px solid #dadada;
        border-right: 0;
        width: 152px;
        padding-left: 5px;
        outline: 0
    }
    table.normal thead {
        background: #f8f8f8;
    }
    .row {
        margin: 0;
    }

    .but {
        padding-left: 15px;
    }

    #zh-manage .stu-num-manage-menu {
        margin-top: 0;
    }

    /*tab页*/
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }
    .normal{
        width: 80% !important;
        margin: 0 auto;

    }
    td{
        height: 26px !important;
    }
    th{
        height: 35px !important;
    }
    main.container{
        border-top: none;
    }
    body {
        font: 12px/1.5 Tahoma;
    }

    #outer {
        width: 450px;
        margin: 150px auto;
    }

    #tab {
        overflow: hidden;
        zoom: 1;
        background: #000;
        border: 1px solid #000;
    }

    #tab li {
        float: left;
        color: #fff;
        height: 30px;
        cursor: pointer;
        line-height: 30px;
        padding: 0 20px;
    }

    #tab li.current {
        color: #000;
        background: #ccc;
    }

    #content {
        border: 1px solid #000;
        border-top-width: 0;
    }

    #content ul {
        line-height: 25px;
        display: none;
        margin: 0 30px;
        padding: 10px 0;
    }

    .anjDiv ul li a.active {
        color: #54AB37;
        border: 1px solid #ddd;
        border-bottom: 0;
        background: #fff;
    }

    .anjDiv {
        display: inline-block;
        /*width: 600px;*/
        height: 45px;
        line-height: 44px;
        font-size: 15px;
        color: #777;
        text-align: center;
        text-decoration: none;
    }

    .njUl li {
        float: left
    }

    .njUl a {
        padding: 10px 15px;
        cursor: pointer;
        color: #525252;
        border-bottom: 1px solid #ddd;
    }

    .njUl a:hover {
        text-decoration: none;
        color: #54AB37;
    }

    #zh-manage #generated > div {
        padding: 0 !important;
    }
</style>
<body>
<main class="container" id="zh-manage">
    <section id="generated" class="row">
        <div class="row">
            <table class="normal" border="1" cellpadding="0" cellspacing="0" borderColor="#ddd">
                <thead>
                <th>序号</th>
                <th>节次名称</th>
                <th>开始时间</th>
                <th>结束时间</th>
                </thead>
                <tbody>
                <c:forEach items="${courseNodeViewList}" var="courseNodeView" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                            <td>${courseNodeView.courseNode.nodeName}</td>
                            <%--<td>${courseNodeView.courseNode.cycleYear}</td>--%>
                            <%--<td>${courseNodeView.courseNode.cycleSemester}</td>--%>
                            <%--<td>${courseNodeView.courseNode.morningAfternoon}</td>--%>

                            <td>${courseNodeView.startTime}</td>
                            <td>${courseNodeView.endTime}</td>
                            <%--<td>${courseNodeInit.morningStart}</td>--%>
                            <%--<td>${courseNodeInit.afternoonStart}</td>--%>
                            <%--<td>${courseNodeInit.nightStart}</td>--%>
                        <%--<td>${courseNodeInit.monthStartEnd}</td>--%>
                        <%--<td>${courseNodeInit.monthStartEndName}</td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>

</body>
</html>