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
				rules:{
				seatCount: {number: true},
				equipmentStatus:{
				max:'1',
				min:'1'
				}
				},
				messages: {
					seatCount: {number: "只能输入数字"},
					equipmentStatus:{
					max:"请先绑定设备IMEI",
					min:"请先绑定设备IMEI"
					
					}
					
				},
				submitHandler: function(form){
				if($("#equipmentStatus").val()!=1){
					alert("设备未绑定");
				return;
				}
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
			$("#equipmentImei").keyup(function(){
			    $("#equipmentStatus").val("0").trigger("change");
			});
		$("#equipmentStatus").on("change",function(e){
		 var esdata = $("#equipmentStatus").select2("data");
		 $("#equipmentStatusLaber").val(esdata.text);
		 if(esdata.id == '0'){
		  $("#equipmentStatusLaber").css("color","red");
		 } 
		 if(esdata.id == '1'){
		  $("#equipmentStatusLaber").css("color","green");
		 }
		 });
		  var esdata = $("#equipmentStatus").select2("data");
		 $("#equipmentStatusLaber").val(esdata.text);
		});
		 function btnTestIMEIBtn(){
		 if($("#equipmentImei").val()==''||$("#equipmentImei").val()==null||$("#equipmentImei").val()==undefined){
		 return;
		 }
		 	$.ajax({
						url : "${ctx}/obd/heartbeat/messageHeartbeat/findMessageHeartbeat",
						data:{
						equipmentImei:$("#equipmentImei").val(),
						id:$("#id").val()
						},
						success : function(data) {
						var successData = data;
						if(successData.status == '0'){
							$("#equipmentStatus").val("1").trigger("change");
						}else if(successData.status == '1'){
							$("#equipmentStatus").val("0").trigger("change");
						alert("该imei号已绑定其他车辆");
						}else{
							$("#equipmentStatus").val("0").trigger("change");
						alert("绑定失败");	
						}
						}
					});
		 
		 
		 };
	
	
		 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/car/ocrVehicle/">车辆列表</a></li>
		<li class="active"><a href="${ctx}/car/ocrVehicle/form?id=${ocrVehicle.id}">车辆<shiro:hasPermission name="car:ocrVehicle:edit">${not empty ocrVehicle.id?'编辑':'添加'}</shiro:hasPermission><shiro:lacksPermission name="car:ocrVehicle:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ocrVehicle" action="${ctx}/car/ocrVehicle/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">车辆厂牌：</label>
			<div class="controls">
				<form:select path="vehicleBrand" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${brands}" itemLabel="vehicleBrand" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">品牌车型：</label>
			<div class="controls">
				<form:input path="brandModels" placeholder="请输入品牌车型" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆类型：</label>
			<div class="controls">
				<form:select path="vehicleType" placeholder="请选择车辆类型" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vehicle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" >
            <label class="control-label">归属区域:</label>
            <div class="controls">
                <sys:treeselect id="area" name="area.id" value="${ocrVehicle.area.id}" labelName="area.name" labelValue="${ocrVehicle.area.name}"
                    title="区域" url="/sys/area/treeDataForDirverAndVehicle" cssClass="required"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">车牌号：</label>
			<div class="controls">
				<form:input path="licensePlateNumber" placeholder="请输入车牌号" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行驶证编号：</label>
			<div class="controls">
				<form:input path="drivingLicenseNumber" placeholder="请输入行驶证编号" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发动机编号：</label>
			<div class="controls">
				<form:input path="engineNumber" placeholder="请输入发动机编号" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车架号：</label>
			<div class="controls">
				<form:input path="vehicleIdentificationNumber" placeholder="请输入车架号" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">座位数量：</label>
			<div class="controls">
				<form:input path="seatCount" placeholder="请输入座位数量" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
				<div class="control-group">
			<label class="control-label">油箱容积(L)：</label>
			<div class="controls">
				<form:input path="tankVolume" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备IMEI：</label>
			<div class="controls">
				<form:input path="equipmentImei" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
							<input id="btnTestIMEI" class="btn" type="button" value="测试" onclick="btnTestIMEIBtn()"/>
			</div>
		</div>
		<div class="controls" hidden="hidden">
			<form:select path="equipmentStatus" class="input-xlarge " >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('equipment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	 
		</div>
			<div class="control-group">
			<label class="control-label">设备绑定状态：</label>
			<div class="controls" >
			<input id = "equipmentStatusLaber" class="input-xlarge " type="text" disabled="disabled">
			</div>
		</div>
		<div class="control-group" id="indateDiv">
			<label class="control-label">保险日期：</label>
			<div class="controls">
				<input name="insuranceDate" id="insuranceDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ocrVehicle.insuranceDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group" id="indateDiv">
			<label class="control-label">年检日期：</label>
			<div class="controls">
				<input name="annualInspectionDate" id="annualInspectionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ocrVehicle.annualInspectionDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
			<shiro:hasPermission name="car:ocrVehicle:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>