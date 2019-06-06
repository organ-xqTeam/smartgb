<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计费规则管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{ruleCoefficient: {number: true}},
				messages: {
					ruleCoefficient: {number: "只能输入数字"},
				},
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
		<li class="active"><a href="${ctx}/billing/ocrBilling/form?id=${ocrBilling.id}">计费规则<shiro:hasPermission name="billing:ocrBilling:edit">${not empty ocrBilling.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="billing:ocrBilling:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ocrBilling" action="${ctx}/billing/ocrBilling/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">计费系数：</label>
			<div class="controls">
				<form:input path="ruleCoefficient" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline">车费算法：公里数*费用系数，系数为一个包含每公里车费及其它成本或收益的正数。</span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="billing:ocrBilling:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>