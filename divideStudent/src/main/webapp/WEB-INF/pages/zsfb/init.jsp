<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>分班管理</title>
</head>
<body class="thisBody">
<sys:message content="${message}"/>
<div style="height:78px;width:1200px;margin:0px auto">
	<div style="z-index:9999;position:fixed;margin:0px 0px">
		<div class="app_store_headerMenu fenBan-header">
			<div class="appStoreLeft fenBanLeft">
				<img src="${ctxStatic }/fb/image/fenban/fenban.png" />&nbsp;<a style="line-height: 49px; font-size: 24px; color: #1AB394;">分班系统</a>
			</div>
		</div>
	</div>
</div>
<div class="mainContain fenbanMain">
	<div class="forBeauty" style="background:#ffffff;">
		<div class="mainTable">
			<!--无数据界面-->
			<c:if test="${empty taskList }">
				<div>
					<div class="chuangjian">
						<a style="font-size: 18px;color: #565656; ">您还没有分过班,请先创建一个分班任务</a><br><br>
						<input onclick="openDialog('创建任务', '${ctx}/zsfb/zsTask/form','415px', '270px')" type="button" class="buttonCss kuanyidian" value="创建任务" />
					</div>
					<div class="notice shorter">
						<br><br>
						<p>分班须知：</p>
						<p>1 分班步骤：创建任务­­­­--导入表格--一键分班--分班完成</p>
						<p>2.用户可以选择分班的依据条件即分班准则，实现科学分班。</p>
					</div>
				</div>
			</c:if>
			<!--有数据界面-->
			<c:if test="${not empty taskList }">
				<div >
					<div class="tableHeader">
						<!-- <input onclick="openDialog('创建任务', '${ctx}/zsfb/zsTask/form','415px', '270px')" class="buttonCss dataCss" type="button" value="创建任务" onclick="showBg()" /> -->
					</div>
					<table>
						<tr class="tableTr">
							<td width="20%"><strong>任务名称</strong></td>
							<td width="15%"><strong>创建时间</strong></td>
							<td width="17%"><strong>入学年份</strong></td>
							<td width="6%"><strong>创建人</strong></td>
							<td width="17%"><strong>状态</strong></td>
							<td width="25%"><strong>操作</strong></td>


						</tr>
						<c:forEach items="${taskList}" var="task">
							<tr>
								<td>${task.rwmc}</td>
								<td><fmt:formatDate value="${task.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${task.rxnf}</td>
								<td>${fns:getUserById(task.createBy).name}</td>
								<td>
									<c:choose>
										<c:when test="${task.fbcg == '1'}">
											<label class="using">正在使用</label>
										</c:when>
										<c:otherwise>
											<label class="unDone">未完成</label>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:if test="${task.fbcg == '1'}">
										<label class="modify"><img src="${ctxStatic}/fb/image/fenban/modify.png"/> <a href="${ctx}/zsfb/zsStudent/devideResult?taskId=${task.id }"> 分班结果</a></label>
									</c:if>
									<c:if test="${task.fbcg == '0'}">
										<label class="start"><img src="${ctxStatic}/fb/image/fenban/reset.png" /> <a href="${ctx}/zsfb/zsTask/first?id=${task.id}"> 开始分班</a></label>
									</c:if>
									<label class="delete"><img src="${ctxStatic}/fb/image/fenban/delete.png" /><a href="${ctx}/zsfb/zsTask/delete?id=${task.id }" onclick="return confirmx('确认要删除该任务吗？', this.href)"> 删除</a></label>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>