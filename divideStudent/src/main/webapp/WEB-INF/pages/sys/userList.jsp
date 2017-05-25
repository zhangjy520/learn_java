<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-title">
            <h5>用户列表 </h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="close-link">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>

        <div class="ibox-content">
            <sys:message content="${message}"/>
            <!-- 查询条件 -->
            <div class="row">
                <div class="col-sm-12">
                    <form:form id="searchForm" action="${ctx}/sys/user/list" method="post"  class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
                        <div class="form-group">
                            <span>登录名：</span>
                            <input id="loginName" name="loginName" type="text" maxlength="50" class="form-control input-sm" value="${user.loginName}"/>
                            <span>姓&nbsp;名：</span>
                            <input id="name" name="name" type="text" maxlength="50" class="form-control input-sm" value="${user.name}"/>
                            <span>手机号码：</span>
                            <input id="mobile" name="mobile" type="text" maxlength="50"  class="form-control input-sm" value="${user.mobile}"/>
                        </div>
                        <div class="pull-right">
                            <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
                            <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
                        </div>
                    </form:form>
                    </br>
                </div>
            </div>
            <!-- 工具栏 -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">
                        <shiro:hasPermission name="sys:user:add">
                            <table:addRow url="${ctx}/sys/user/form" title="用户" width="800px" height="620px"></table:addRow><!-- 增加按钮 -->
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sys:user:del">
                            <table:delRow url="${ctx}/sys/user/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sys:user:import">
                            <table:importExcel url="${ctx}/sys/user/import"></table:importExcel><!-- 导入按钮 -->
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sys:user:export">
                            <table:exportExcel url="${ctx}/sys/user/export"></table:exportExcel><!-- 导出按钮 -->
                        </shiro:hasPermission>
                        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
                    </div>
                </div>
            </div>

            <table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <th><input type="checkbox" class="i-checks"></th>
                    <th class="sort-column login_name">登录名</th>
                    <th class="sort-column name">姓名</th>
                    <th class="sort-column phone">隶属学校</th>
                    <th class="sort-column mobile">手机</th>
                    <th class="sort-column login_flag">是否允许登录</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="user">
                    <tr>
                        <td><input type="checkbox" id="${user.id}" class="i-checks"></td>
                        <td>${user.loginName}</td>
                        <td>${user.name}</td>
                        <td>${user.school.xxmc}</td>
                        <td>${user.mobile}</td>
                        <td>${user.loginFlag == '1' ? '是' : '否'}</td>
                        <td>
                            <shiro:hasPermission name="sys:user:view">
                                <a href="#" onclick="openDialogView('查看用户', '${ctx}/sys/user/form?id=${user.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="sys:user:edit">
                                <a href="#" onclick="openDialog('修改用户', '${ctx}/sys/user/form?id=${user.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="sys:user:del">
                                <a href="${ctx}/sys/user/delete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
                            </shiro:hasPermission>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
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