<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>推送信息管理</title>
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
		<li><a href="${ctx}/message/ocrMessage/">推送信息列表</a></li>
		<li class="active"><a href="${ctx}/message/ocrMessage/form?id=${ocrMessage.id}">推送信息<shiro:hasPermission name="message:ocrMessage:edit">${not empty ocrMessage.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="message:ocrMessage:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ocrMessage" action="${ctx}/message/ocrMessage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="status"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
				<div class="controls">
			<label>${ocrMessage.title}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<textarea  readonly="readonly" rows="15" maxlength="2000" class="input-xxlarge " >${ocrMessage.content}</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接收端：</label>
			<div class="controls">
			<label>${fns:getDictLabel(ocrMessage.status, 'receive_side', '')}</label>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">发送时间：</label>
			<div class="controls">
			<label>
			<fmt:formatDate value="${ocrMessage.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</label>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="message:ocrMessage:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>