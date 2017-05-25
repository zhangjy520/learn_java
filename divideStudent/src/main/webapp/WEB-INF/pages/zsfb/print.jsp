<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>${fns:toLetter(currentBj)}班学生信息</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#printTableContent").jqprint();
        });
    </script>
</head>
<body class="thisBody">
<div style="height:78px;width:1200px;margin:0px auto">
    <div style="z-index:9999;position:fixed;margin:0px 0px">
        <div class="app_store_headerMenu fenBan-header">
            <div class="appStoreLeft fenBanLeft">
                <img src="${ctxStatic }/fb/image/fenban/fenban.png"/>&nbsp;<a
                    style="line-height: 49px; font-size: 24px; color: #1AB394;">分班系统</a>
            </div>
        </div>
    </div>
</div>
<div class="mainContain fenbanMain">
    <div class="forBeauty" style="background:#ffffff;">
        <div class="mainTable">
                <div id="printTableContent">
                    <table style="border-collapse: collapse !important;width: 45% !important;margin-left: 2% !important;margin-top: 1% !important;display: inline-table !important;"
                           class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                        <thead>
                        <tr>
                            <th colspan="6">${fns:getUser().school.xxmc}${fns:toLetter(currentBj)}班男生信息</th>
                        </tr>
                        <tr>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>家长姓名</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${male}" var="male">
                            <tr>
                                <td>${male.xsxm }</td>
                                <td>${male.xsxb == '1' ? '男' : '女' }</td>
                                <td>${male.sfcm > 0 ? male.jzxm : '-'}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table style="border-collapse: collapse !important;width: 45% !important;margin-left: 2% !important;margin-top: 1% !important;display: inline-table !important;" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                        <thead>
                        <tr>
                            <th colspan="6">${fns:getUser().school.xxmc}${fns:toLetter(currentBj)}班女生信息</th>
                        </tr>
                        <tr>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>家长姓名</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${female}" var="female">
                            <tr>
                                <td>${female.xsxm }</td>
                                <td>${female.xsxb == '1' ? '男' : '女' }</td>
                                <td>${female.sfcm > 0 ? female.jzxm : '-'}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
        </div>
    </div>
</div>
</body>
</html>