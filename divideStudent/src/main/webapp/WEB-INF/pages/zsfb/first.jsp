<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
<title>分班步骤</title>
<meta name="decorator" content="default" />
<c:if test="${zsTask.scsjqr == '1'}">
<script type="text/javascript">
$(document).ready(function() {
	//setCookie("reload","${reload}");
	
	$("#nextStepButton").removeClass("notCz");
	$("#preview").removeClass("noClick");
});
</script>
</c:if>
<c:if test="${importFlag == 'true'}">
<c:if test="${zsTask.scsjqr != '1'}">
<script type="text/javascript">
$(document).ready(function() {
	openDialog("导入学生预览", "${ctx}/zsfb/zsStudent/list?taskId=${zsTask.id}", "1200px", "800px");
});
</script>
</c:if>
</c:if>
<script type="text/javascript">
function next(){
	if('${zsTask.scsjqr}' == '1'){
		window.location.href='${ctx}/zsfb/zsTask/second?id=${zsTask.id }';
	}
}
function preview(){
	if('${zsTask.scsjqr}' == '1'){
		openDialog("导入学生预览", "${ctx}/zsfb/zsStudent/list?taskId=${zsTask.id}", "1200px", "800px");
	}
}
</script>
</head>
<body class="thisBody">
	<sys:message content="${message}" />
	<div style="height: 78px; width: 1200px; margin: 0px auto">
		<div style="z-index: 9999; position: fixed; margin: 0px 0px">
			<div class="app_store_headerMenu fenBan-header">
				<div class="appStoreLeft fenBanLeft">
					<img src="${ctxStatic }/fb/image/fenban/fenban.png" />&nbsp;
					<a style="line-height: 49px; font-size: 24px; color: #1AB394;">分班系统</a>
				</div>
			</div>
		</div>
	</div>

	<div class="mainContain fenbanMain fenbanStep">
		<div class="forBeauty" style="background: #ffffff;">
			<div class="mainTable">

				<div class="noData">
					<div class="taskInfo">
						<label>任务名称：${zsTask.rwmc }</label> 
						<label>创建时间：<fmt:formatDate value="${zsTask.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></label>
						<label>创建人：${fns:getUser().name }</label>
					</div>
					<div class="chuangjian taskStep">

						<div class="jinDuWithLabel active">
							<div class="specialJinDu">1</div>
							<p class="jinDuLabel ">导入数据</p>
						</div><div id="yiJian" class="jinDuWithLabel noActive">
							<div class="specialJinDu">2</div>
							<p class="jinDuLabel">一键分班</p>
						</div><div id="fbResult" class="jinDuWithLabel noActive">
							<div class="specialJinDu">3</div>
							<p class="jinDuLabel">分班结果</p>
						</div>
					</div>

					<!--第一步 导入数据-->
					<div class="notice taskNotice step1">
						<span class="step-1">
							<p>
								第一步：下载模板 &nbsp;&nbsp;&nbsp; 
								<input class="buttonCss download same-button back-green" type="button" value=" 下载模板 " onclick="window.location.href='${ctx}/zsfb/zsStudent/import/template'" />
								<br> <a>说明：下载模板，将学生数据录入到模板，再将模板上传即可</a>
							</p>
						</span> <span class="step-1">
							<p>
								第二步：上传数据 &nbsp;&nbsp;&nbsp;
								<!--目标样式的文件选择框-->
							<div class="file-box">
								<shiro:hasPermission name="zsfb:zsStudent:import">
									<!-- 导入按钮 -->
									<c:if test="${zsTask.scsjqr != '1'}">
									<btn:importExcel url="${ctx}/zsfb/zsStudent/import?taskId=${zsTask.id }"></btn:importExcel>
									</c:if>
									<input class="noClick download buttonCss same-button" onclick='javascript:preview()' type="button" value="预览" id="preview">
								</shiro:hasPermission>
							</div>
							<div style="width:915px;margin-left:191px"><a class="a">注意事项：</a> <br> <a class="a">1：上传文件格式只限于excel格式,只读取第一个sheet，不可以有合并的单元格</a>
							<br> <a class="a">2：按照模板表格中的注释填写。</a> 
							
							</div>
							</p>
						</span>
						<div class="czButtons">
							<input class="canCz" type="button" lang="${ctx}/zsfb/zsTask/list?id=${zsTask.id }" onclick="return confirmx('确认要放弃这次分班吗？', this.lang)"  value="放弃分班" /> 
							<input class="canCz notCz" onclick="javascript:next()" type="button" value="下一步" id="nextStepButton"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>