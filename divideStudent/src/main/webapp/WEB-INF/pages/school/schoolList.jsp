<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
	<title>学校管理</title>
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
			<h5>学校列表 </h5>
			<div class="ibox-tools">
				<a class="collapse-link">
					<i class="fa fa-chevron-up"></i>
				</a>
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="fa fa-wrench"></i>
				</a>
				<a class="close-link">
					<i class="fa fa-times"></i>
				</a>
			</div>
		</div>

		<div class="ibox-content">
			<sys:message content="${message}"/>

			<!--查询条件-->
			<div class="row">
				<div class="col-sm-12">
					<form:form id="searchForm" modelAttribute="school" action="${ctx}/sys/school/" method="post" class="form-inline">
						<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
						<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
						<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
						<div class="form-group">
							<span>学校名 ：</span>
							<input name="xxmc" id="xxmc" maxlength="50" value="${school.xxmc }" class="form-control"/>
						</div>
						<div class="pull-right">
							<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
							<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
						</div>
					</form:form>
					<br/>
				</div>
			</div>

			<!-- 工具栏 -->
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
						<shiro:hasPermission name="sys:school:add">
							<table:addRow url="${ctx}/sys/school/form" title="学校"></table:addRow><!-- 增加按钮 -->
						</shiro:hasPermission>
						<shiro:hasPermission name="sys:school:del">
							<table:delRow url="${ctx}/sys/school/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
						</shiro:hasPermission>
						<shiro:hasPermission name="sys:school:import">
							<table:importExcel url="${ctx}/sys/school/import"></table:importExcel><!-- 导入按钮 -->
						</shiro:hasPermission>
						<shiro:hasPermission name="sys:school:export">
							<table:exportExcel url="${ctx}/sys/school/export"></table:exportExcel><!-- 导出按钮 -->
						</shiro:hasPermission>
						<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>

				</div>
			</div>

			<!-- 表格 -->
			<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
				<thead>
				<tr>
					<th><input type="checkbox" class="i-checks"></th>
					<th class="sort-column xxmc">学校名称</th>
					<th class="sort-column xxxd">学校类型</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="school" varStatus="status">
					<tr>
						<td> <input type="checkbox" id="${school.id}" class="i-checks"></td>
						<td >
							<a  href="#" onclick="openDialogView('查看学校', '${ctx}/sys/school/form?id=${school.id}','800px', '500px')">
									${school.xxmc}
							</a>
						</td>
						<td>
							<c:forEach items="${fns:getDictList('xuexiao_xueduan')}" var="stage">
								<c:if test="${school.xxlx==stage.value }">
									${stage.label }
								</c:if>
							</c:forEach>
						</td>
						<td>
							<shiro:hasPermission name="sys:school:view">
								<a href="#" onclick="openDialogView('查看学校', '${ctx}/sys/school/form?id=${school.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="sys:school:edit">
								<a href="#" onclick="openDialog('修改学校', '${ctx}/sys/school/form?id=${school.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="sys:school:del">
								<a href="${ctx}/sys/school/delete?id=${school.id}" onclick="return confirmx('确认要删除该学校吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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