<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
	<title>修改密码</title>
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
			$(document).ready(function() {
				$("#oldPassword").focus();
			});
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
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/modifyPwd"  method="post" class="form-horizontal form-group">
		<form:hidden path="id"/>
		<sys:message hideType="1" content="${message}"/>
		<div class="control-group">
		</div>
		
		<div class="control-group">
			<label class="col-sm-3 control-label"><font color="red">*</font>旧密码:</label>
			<div class="controls">
				<input id="oldPassword" name="oldPassword" type="password" value="" maxlength="50" minlength="3"  class="form-control  max-width-250 required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="col-sm-3 control-label"><font color="red">*</font>新密码:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="form-control  max-width-250 required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>确认新密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" class="form-control  max-width-250 required" equalTo="#newPassword"></input>
			</div>
		</div>
	</form:form>
</body>
</html>