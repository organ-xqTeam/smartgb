<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/drive/carInfo/">车辆列表</a></li>
		<li class="active"><a href="${ctx}/drive/carInfo/form">车辆添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ocrCarInfo" action="${ctx}/drive/carInfo/save" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">车辆品牌：</label>
			<div class="controls">
				<form:input path="carBand" placeholder="请输入车辆品牌" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车牌号：</label>
			<div class="controls">
				<form:input path="plateNum" placeholder="请输入车牌号" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车载手机号：</label>
			<div class="controls">
				<form:input path="terminalPhone" placeholder="请输入车载手机号" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="drive:carInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
			<sys:message content="${message}"/>		
</body>
</html>