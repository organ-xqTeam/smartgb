<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆管理</title>
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
		<li><a href="${ctx}/car/ocrVehicle/">车辆列表</a></li>
		<li class="active"><a href="${ctx}/car/ocrVehicle/view?id=${ocrVehicle.id}"><shiro:hasPermission name="car:ocrVehicle:view">查看车辆</shiro:hasPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ocrVehicle" action="${ctx}/car/ocrVehicle/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">车辆厂牌：</label>
			<div class="controls">
				<form:select path="vehicleBrand" class="input-xlarge required" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${brands}" itemLabel="vehicleBrand" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">品牌车型：</label>
			<div class="controls">
				<form:input path="brandModels" htmlEscape="false" maxlength="100" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆类型：</label>
			<div class="controls">
				<form:select path="vehicleType" class="input-xlarge required" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vehicle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		      <div class="control-group">
            <label class="control-label">归属区域：</label>
            <div class="controls">
                <form:input path="area.name" htmlEscape="false" maxlength="100" class="input-xlarge required" disabled="true"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">车牌号：</label>
			<div class="controls">
				<form:input path="licensePlateNumber" htmlEscape="false" maxlength="100" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行驶证编号：</label>
			<div class="controls">
				<form:input path="drivingLicenseNumber" htmlEscape="false" maxlength="100" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发动机编号：</label>
			<div class="controls">
				<form:input path="engineNumber" htmlEscape="false" maxlength="11" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车架号：</label>
			<div class="controls">
				<form:input path="vehicleIdentificationNumber" htmlEscape="false" maxlength="100" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">座位数量：</label>
			<div class="controls">
				<form:input path="seatCount" htmlEscape="false" maxlength="11" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">座位数量：</label>
			<div class="controls">
				<form:input path="seatCount" placeholder="请输入座位数量" htmlEscape="false" maxlength="11" class="input-xlarge required" disabled="true"/>
			</div>
		</div> -->
				<div class="control-group">
			<label class="control-label">油箱容积(L)：</label>
			<div class="controls">
				<form:input path="tankVolume" htmlEscape="false" maxlength="11" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备IMEI：</label>
			<div class="controls">
				<form:input path="equipmentImei" htmlEscape="false" maxlength="20" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保险日期：</label>
			<div class="controls">
				<fmt:formatDate value="${ocrVehicle.insuranceDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年检日期：</label>
			<div class="controls">
				<fmt:formatDate value="${ocrVehicle.annualInspectionDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		 -->
		<div class="form-actions">
			<!--
			<shiro:hasPermission name="car:ocrVehicle:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			-->
			<input id="btnCancel" class="btn btn-primary" type="button" value="确定" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>