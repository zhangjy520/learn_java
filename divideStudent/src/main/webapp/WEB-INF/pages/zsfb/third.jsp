<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
	<title>分班步骤</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
	$(document).ready(function(){
		var classNum=$("#currentBj").val();
		var leftSize= 0-(classNum-classNum%16)/16*1063;

		$(".step3Ul").css({"left": leftSize});
	});
	function queryDevide(classNum){
		$("#currentBj").val(classNum);
		$("#searchForm").submit();
	}
	</script>
	</head>
	<body class="thisBody">
		<sys:message content="${message}"/>
		<div style="height:78px;width:1200px;margin:0px auto">
			<div style="z-index:9999;position:fixed;margin:0px 0px">
				<div class="app_store_headerMenu fenBan-header">
					<div class="appStoreLeft fenBanLeft">
						<img src="${ctxStatic }/fb/image/fenban/fenban.png" />&nbsp;<a style="line-height: 49px;font-size:24px;color: #1AB394;">分班系统</a>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
		<div class="col-sm-12">
		<form:form id="searchForm" modelAttribute="zsStudent" action="${ctx}/zsfb/zsStudent/devideResult" method="post" class="form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="taskId" name="taskId" type="hidden" value="${zsTask.id}"/>
			<input id="bjCount" name="bjCount" type="hidden" value="${bjCount}"/>
			<input id="currentBj" name="currentBj" type="hidden" value="${currentBj}"/>
			<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		</form:form>
		<br/>
		</div>
		</div>
		<div class="mainContain fenbanMain fenbanStep">
			<div class="forBeauty" style="background:#ffffff;">
				<div class="mainTable">
					<div class="noData">
						<div class="taskInfo">
							<label>任务名称：${zsTask.rwmc }</label>
							<label>创建时间：<fmt:formatDate
									value="${zsTask.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
							<label>创建人：${fns:getUser().name }</label>
						</div>
						<div class="chuangjian taskStep">

							<div class="jinDuWithLabel active">
								<div class="specialJinDu">
									1
								</div>
								<p class="jinDuLabel">导入数据</p>
							</div><div id="yiJian" class="jinDuWithLabel active1">
								<div class="specialJinDu">
									2
								</div>
								<p class="jinDuLabel">一键分班</p>
							</div><div id="fbResult" class="jinDuWithLabel active2">
								<div class="specialJinDu">
									3
								</div>
								<p class="jinDuLabel">分班结果</p>
							</div>
						</div>
						<!--第三步 分班结果-->
						<div  class="notice taskNotice step3">
							<div class="step3Search">
								<div class="step3SearchLeft">
									<div>
										<button class="btn" style="background: #1AB394;color: #ffffff" onclick="queryDevide('${currentBj+1 > bjCount ? bjCount : currentBj+1 }')" data-toggle="tooltip" data-placement="top">${fns:toLetter(currentBj+1 > bjCount ? bjCount : currentBj+1) }班</button>
									</div>
								</div>
								<div class="step3SearchRight">
									<div>
									<button class="btn" style="background: #1AB394;color: #ffffff" onclick="openDialog('评估结果','${ctx}/zsfb/zsStudent/analysis?taskId=${zsTask.id }','1200px','800px')" data-toggle="tooltip" data-placement="top">评估结果</button>
									<%-- <button class="btn" style="background: #1AB394;color: #ffffff" onclick="window.location.href='${ctx}/zsfb/zsTask/second?id=${zsTask.id }'" data-toggle="tooltip" data-placement="top">打印</button> --%>
									<button class="btn" style="background: #1AB394;color: #ffffff" onclick="window.open('${ctx}/zsfb/zsStudent/printResult?taskId=${zsTask.id }&currentBj=${currentBj}')" data-toggle="tooltip" data-placement="top">打印</button>
									<shiro:hasPermission name="zsfb:zsStudent:export">
							       		<btn:exportExcel url="${ctx}/zsfb/zsStudent/export"></btn:exportExcel><!-- 导出按钮 -->
							       	</shiro:hasPermission>
									</div>
								</div>
							</div>

							<div class="step3Table">
								<div class="step3Header">
									<ul class="step3Ul">
										<li class="current-li-devide">${fns:toLetter(currentBj) }班</li>
									</ul>
								</div>
								<div id="printTable">
								<table class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
									<thead>
										<tr>
											<th >姓名</th>
											<th >性别</th>
											<th >家长姓名</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${male.list }" var="male">
										<tr>
											<td>${male.xsxm }</td>
											<td>${male.xsxb == '1' ? '男' : '女' }</td>
											<td>${male.sfcm > 0 ? male.jzxm : '-'}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								<table class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
									<thead>
									<tr>
										<th >姓名</th>
										<th >性别</th>
										<th >家长姓名</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${female.list }" var="female">
										<tr>
											<td>${female.xsxm }</td>
											<td>${female.xsxb == '1' ? '男' : '女' }</td>
                                            <td>${female.sfcm > 0 ? female.jzxm : '-'}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								</div>
								<table:page page="${male}"></table:page>
							</div>
						</div>
						<div class="fenbanS">
							<input class="canCz" type="button" onclick="window.location.href='${ctx}/zsfb/zsTask/list'" value="分班完成"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>