<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
	<title>分班任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }

		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

		});
	</script>
</head>
<body style="background:#fff !important;height:150px">
		<form:form id="inputForm" modelAttribute="zsTask" action="${ctx}/zsfb/zsTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		</br>
		<verTap></verTap>
		<table class="modifyTable table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">任务名称：</label></td>
					<td class="width-35" colspan="2">
						<form:input path="rwmc" htmlEscape="false" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">入学年份：</label></td>
					<td class="width-35" colspan="2">
						<form:input path="rxnf" htmlEscape="false" class="form-control required"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>