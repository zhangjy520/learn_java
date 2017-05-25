<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>
<html>
<head>
    <title>分班评估结果</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            return true;
        }
    </script>
</head>
<body class="thisBody">
<div class="mainContain fenbanMain fenbanStep">
    <div class="forBeauty" style="background:#ffffff;">
        <div class="fbPgResult">
            <div class="pgResultTable">
                <table>
                    <c:if test="${fns:getUser().school.xxlx == '1'}">
                        <tr class="pgResultTableHeader">
                            <td width="2%" rowspan="2">班级</td>
                            <td class="sonTd" width="4%" colspan="2">性别</td>
                            <td width="2%" rowspan="2">总数</td>
                            <td width="3%" rowspan="2">随迁子女</td>
                            <td width="3%" rowspan="2">随班就读</td>
                            <td width="3%" rowspan="2">双(多)胞胎</td>
                            <td width="3%" rowspan="2">双(多)胞胎人数</td>
                            <td width="3%" rowspan="2">军人子女</td>
                            <td width="3%" rowspan="2">教师子女</td>
                            <td width="3%" rowspan="2">外籍子女</td>
                        </tr>
                        <tr class="pgResultTableHeader">
                            <td class="sonTd">男</td>
                            <td class="sonTd">女</td>
                        </tr>
                        <c:forEach items="${analysis }" var="r">
                            <tr class="pgNormalTr">
                                <td>${fns:toLetter(r.bj) }班</td>
                                <td>${r.male }人</td>
                                <td>${r.female }人</td>
                                <td>${r.sumPeople }人</td>
                                <td>${not empty r.sqzn ? r.sqzn : '0'}人</td>
                                <td>${not empty r.sbjd ? r.sbjd : '0'}人</td>
                                <td>${not empty r.sbt ? r.sbt - 1 : '0'}对</td>
                                <td>${not empty r.sbtCount ? r.sbtCount : '0'}人</td>
                                <td>${not empty r.js ? r.js : '0'}人</td>
                                <td>${not empty r.jszn ? r.jszn : '0'}人</td>
                                <td>${not empty r.wjzn ? r.wjzn : '0'}人</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${fns:getUser().school.xxlx == '2'}">
                        <tr class="pgResultTableHeader">
                            <td width="6%" rowspan="2">班级</td>
                            <td class="sonTd" width="12%" colspan="2">性别</td>
                            <td width="6%" rowspan="2">总数</td>
                            <td width="6%" rowspan="2">随班就读</td>
                            <td width="6%" rowspan="2">双(多)胞胎</td>
                            <td width="6%" rowspan="2">双(多)胞胎人数</td>
                            <td width="6%" rowspan="2">军人子女</td>
                            <td width="6%" rowspan="2">教师子女</td>
                            <td class="sonTd" width="18%" colspan="3">综合素质</td>
                            <td class="sonTd" width="${fn:length(schoolList) * 6}%" colspan="${fn:length(schoolList)+1}">学校比例</td>
                        </tr>
                        <tr class="pgResultTableHeader">
                            <td class="sonTd">男</td>
                            <td class="sonTd">女</td>
                            <td class="sonTd">A</td>
                            <td class="sonTd">B</td>
                            <td class="sonTd">C</td>
                            <c:forEach items="${schoolList}" var="sch">
                                <td class="sonTd">${sch.yxx}</td>
                            </c:forEach>
                            <td class="sonTd">非对口学校</td>
                        </tr>
                        <c:forEach items="${analysis }" var="r">
                            <tr class="pgNormalTr">
                                <td>${fns:toLetter(r.bj) }班</td>
                                <td>${r.male }人</td>
                                <td>${r.female }人</td>
                                <td>${r.sumPeople }人</td>
                                <td>${not empty r.sbjd ? r.sbjd : '0'}人</td>
                                <td>${not empty r.sbt ? r.sbt - 1 : '0'}对</td>
                                <td>${not empty r.sbtCount ? r.sbtCount : '0'}人</td>
                                <td>${not empty r.js ? r.js : '0'}人</td>
                                <td>${not empty r.jszn ? r.jszn : '0'}人</td>
                                <td>${not empty r.a ? r.a : '0'}人</td>
                                <td>${not empty r.b ? r.b : '0'}人</td>
                                <td>${not empty r.c ? r.c : '0'}人</td>
                                <c:forEach items="${schoolList}" var="n">
                                    <td>${schoolCount[r.bj][n.yxx]}人</td>
                                </c:forEach>
                                <td>${not empty r.fdkxx ? r.fdkxx : '0'}人</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>

        </div>
    </div>
</div>
<div class="grayHeader">

</div>
</body>
</html>