<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户反馈管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ocruser/ocrFeedback/">用户反馈列表</a></li>
		<li class="active"><a href="${ctx}/ocruser/ocrFeedback/form?id=${ocrFeedback.id}">用户反馈</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ocrFeedback" action="${ctx}/ocruser/ocrFeedback/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
			<label>${ocrFeedback.realName}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
			<label>${ocrFeedback.phone}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<textarea  readonly="readonly" rows="15" maxlength="2000" class="input-xxlarge " >${ocrFeedback.content}</textarea>
			</div>
		</div>
	 <!-- 	<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
			<label>${fns:getDictLabel(ocrFeedback.status, 'feedback_status', '')}</label>
			</div>
		</div> --> 

		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>