<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>司机管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/drive/ocrDriver/checkLoginName"}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					//confirmNewPassword: {equalTo: "输入与上面相同的密码"}
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
		<li><a href="${ctx}/drive/ocrDriver/">司机列表</a></li>
		<li class="active"><a href="${ctx}/drive/ocrDriver/form?id=${ocrDriver.id}">司机<shiro:hasPermission name="drive:ocrDriver:edit">${not empty ocrDriver.id?'编辑':'添加'}</shiro:hasPermission><shiro:lacksPermission name="drive:ocrDriver:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ocrDriver" action="${ctx}/drive/ocrDriver/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">注册手机号：</label>
			<div class="controls">
				<form:input path="loginName" placeholder="请输入登录名" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码：</label>
			<div class="controls">
				 <input id="newPassword" name="newPassword" placeholder="请输入密码" type="password" value="" maxlength="50" minlength="3" class="input-xlarge ${empty ocrDriver.id?'required':''}"/>
				 <c:if test="${empty ocrDriver.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty ocrDriver.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="realName" placeholder="请输入姓名" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:select path="sex" placeholder="请选择性别" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">归属区域:</label>
				<div class="controls">
					<sys:treeselect id="area" name="area.id" value="${ocrDriver.area.id}" labelName="area.name" labelValue="${ocrDriver.area.name}"
						title="区域" url="/sys/area/treeDataForDirverAndVehicle" cssClass="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="phone" placeholder="请输入联系电话" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label">职务(预留字段)：</label>
			<div class="controls">
				<form:input path="duty" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		 -->
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="cardNumber" placeholder="请输入身份证号" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">驾驶证编号：</label>
            <div class="controls">
                <form:input path="drivingLicenceNumber" placeholder="请输入驾驶证编号" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
		<c:if test="${not empty ocrDriver.id}">
		<div class="control-group">
			<label class="control-label">驾驶员编号：</label>
			<div class="controls">
				<label>${ocrDriver.jobNumber}</label>
			</div>
		</div>
		</c:if>
		
		<!--
		
		 
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ocr_driver_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重置码：</label>
			<div class="controls">
				<form:input path="resetCode" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		-->
		<div class="form-actions">
			<shiro:hasPermission name="drive:ocrDriver:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
			<sys:message content="${message}"/>		
	
</body>
</html>