<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>
<html>
<head>
    <title>预览</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $("#inputForm").submit();
            return true;
        }
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-title">
            <h5>学生列表 </h5>
        </div>
        <div class="ibox-content">
            <sys:message content="${message}"/>
            <div class="row">
                <div class="col-sm-12">
                    <form:form id="searchForm" modelAttribute="zsStudent"
                               action="${ctx}/zsfb/zsStudent/list?taskId=${zsTask.id}" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}"
                                          callback="sortOrRefresh();"/>
                        <div class="form-group">
                        </div>
                    </form:form>
                    <form:form id="inputForm" modelAttribute="zsTask"
                               action="${ctx}/zsfb/zsTask/savePreview?id=${zsTask.id}" method="post"
                               class="form-inline">
                        <input id="scsjqr" name="scsjqr" type="hidden" value="1"/>
                        <div class="form-group">
                        </div>
                    </form:form>
                </div>
            </div>
            <!-- 表格 -->
            <table id="contentTable"
                   class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <c:if test="${fns:getUser().school.xxlx == '1'}">
                    <thead>
                    <tr>
                        <th>学生姓名</th>
                        <th class="sort-column xsxb">性别</th>
                        <th class="sort-column jtdz">家庭地址</th>
                        <th class="sort-column xq">小区</th>
                        <th class="sort-column jzxm">家长姓名</th>
                        <th class="sort-column sfsqzn">随迁子女</th>
                        <th class="sort-column sfsbjd">随班就读</th>
                        <th class="sort-column sfsbt">双(多)胞胎</th>
                        <th class="sort-column sfjs">军人子女</th>
                        <th class="sort-column sfjszn">教师子女</th>
                        <th class="sort-column sfwjzn">外籍子女</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="zsStudent">
                        <tr>
                            <td>${zsStudent.xsxm}</td>
                            <td>${zsStudent.xsxb == '1' ? '男' : '女'}</td>
                            <td>${zsStudent.jtdz}</td>
                            <td>${zsStudent.xq}</td>
                            <td>${zsStudent.jzxm}</td>
                            <td>${zsStudent.sfsqzn == '1' ? '是' : '否'}</td>
                            <td>${zsStudent.sfsbjd == '1' ? '是' : '否'}</td>
                            <td>${zsStudent.sfsbt == '1' ? '是' : '否'}</td>
                            <td>${zsStudent.sfjs == '1' ? '是' : '否'}</td>
                            <td>${zsStudent.sfjszn == '1' ? '是' : '否'}</td>
                            <td>${zsStudent.sfwjzn == '1' ? '是' : '否'}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:if>
                <c:if test="${fns:getUser().school.xxlx == '2'}">
                    <thead>
                    <tr>
                        <th>学生姓名</th>
                        <th class="sort-column xsxb">性别</th>
                        <th class="sort-column xjh">学籍号</th>
                        <th class="sort-column yxx">原学校</th>
                        <th class="sort-column jzxm">家长姓名</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="zsStudent">
                        <tr>
                            <td>${zsStudent.xsxm}</td>
                            <td>${zsStudent.xsxb == '1' ? '男' : '女'}</td>
                            <td>${zsStudent.xjh}</td>
                            <td>${zsStudent.yxx}</td>
                            <td>${zsStudent.jzxm}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:if>
            </table>
            <!-- 分页代码 -->
            <table:page page="${page}"></table:page>
            <br/>
            <br/>
        </div>
    </div>
</div>
</body>
</html>