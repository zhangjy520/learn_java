<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
	<title>学校管理</title>
	<meta name="decorator" content="default"/>
	<!--引入CSS-->
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/webuploader-0.1.5/webuploader.css">
	<!--引入JS-->
	<script type="text/javascript" src="${ctxStatic}/webuploader-0.1.5/webuploader.js"></script>
	<script type="text/javascript" src="${ctxStatic}/webuploader-0.1.5/schoolUpload.js"></script>
	<script type="text/javascript" src="${ctxStatic}/school/school.js"></script>
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
<body>
		<form:form id="inputForm" modelAttribute="school" action="${ctx}/sys/school/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">学校名称：</label></td>
					<td class="width-35">
						<form:input path="xxmc" htmlEscape="false" maxlength="64" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">学校类型：</label></td>
					<td class="width-35">
						<form:select path="xxlx" htmlEscape="false" maxlength="1" class="form-control required">
							<form:option value="">---请选择---</form:option>
							<form:options items="${fns:getDictList('xuexiao_xueduan')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>