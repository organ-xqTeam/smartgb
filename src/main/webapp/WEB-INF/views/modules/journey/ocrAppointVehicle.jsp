<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务指派</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/journey.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function resetBtn() {
			$("#status").val("30").trigger("change");
		}
		function vehicleAppointBtn(vehicleId) {
			window.location.href="${ctx}/journey/ocrJourney/reList?id=${ocrJourney.id}&driverId=${ocrDriver.id}&driverLoginName=${ocrDriver.loginName}&vehicleId="+vehicleId;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/journey/ocrJourney/">行程列表</a></li>
		<li class="active"><a href="${ctx}/journey/ocrJourney/designate?id=${ocrJourney.id}">任务指派</a></li>
		<li><a href="${ctx}/journey/ocrJourney/canceList">取消通知</a>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrJourney" action="${ctx}/journey/ocrJourney/appoint?id=${ocrJourney.id}&driverId=${ocrDriver.id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务间隔：</label>
				<form:select path="taskInterval" placeholder="请选择任务间隔" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('task_interval')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<span class="input-medium">&nbsp&nbsp 当前城市: ${ocrJourney.areaName}</span>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" value="重置" type="button" onclick="javascript:resetBtn();"/></li>
			<li class="btns"><input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<section class="header">
		<h3>当前任务</h3>
		<ul>
			<li>出发地：<span>${ocrJourney.departurePlace}</span></li>
			<li>目的地：<span>${ocrJourney.destination}</span></li>
			<li>出发时间：<span><fmt:formatDate value="${ocrJourney.departureDate}" pattern="yyyy-MM-dd HH:mm"/></span></li>
			<li>预计送达时间：<span><fmt:formatDate value="${ocrJourney.planArrivalDate}" pattern="yyyy-MM-dd HH:mm"/></span></li>
			<li>车辆类型：<span>${fns:getDictLabel(ocrJourney.vehicleType, 'vehicle_type', '')}</span></li>
			<li>人数：<span>${ocrJourney.peopleNumber}</span></li>
		</ul>
	</section>
	<section class="container">
		<h3>已选定司机</h3>
		<p><span>${ocrDriver.realName}</span>工号：<span>${ocrDriver.jobNumber}</span></p>
		<div class="tBox">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<th>上一任务</th>
				</tr>
				<c:choose>
					<c:when test="${ocrDriver.ocrTask1!=null}">
						<tr>
							<td>出发时间：</td>
							<td><fmt:formatDate value="${ocrDriver.ocrTask1.departureDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>出发地：</td>
							<td>${ocrDriver.ocrTask1.departurePlace}</td>
							<td>目的地：</td>
							<td>${ocrDriver.ocrTask1.destination}</td>
							<td>预计送达时间：</td>
							<td><fmt:formatDate value="${ocrDriver.ocrTask1.planArrivalDate}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td>车辆类型：</td>
							<td>${fns:getDictLabel(ocrDriver.ocrTask1.vehicleType, 'vehicle_type', '')}</td>
							<td>车辆品牌：</td>
							<td>${ocrDriver.ocrTask1.brandModels}</td>
							<td>车牌号：</td>
							<td>${ocrDriver.ocrTask1.licensePlateNumber}</td>
							<td>可乘人数：</td>
							<td>${ocrDriver.ocrTask1.seatCount}</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
				      		<td>无</td>
				      	</tr>
			   		</c:otherwise>
				</c:choose>
			</table>
			<c:choose>
				<c:when test="${ocrDriver.ocrTask1!=null&&fns:getDictLabel(ocrDriver.ocrTask1.vehicleType, 'vehicle_type', '')==fns:getDictLabel(ocrJourney.vehicleType, 'vehicle_type', '')}">
					<div class="btn-box">
						<a class="button" href="javascript:vehicleAppointBtn('${ocrDriver.ocrTask1.vehicleId}');">继续使用该车</a>
					</div>
				</c:when>
			</c:choose>
		</div>
		<div class="tBox">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<th>下一任务</th>
				</tr>
				<c:choose>
					<c:when test="${ocrDriver.ocrTask2!=null}">
						<tr>
							<td>出发时间：</td>
							<td><fmt:formatDate value="${ocrDriver.ocrTask2.departureDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>出发地：</td>
							<td>${ocrDriver.ocrTask2.departurePlace}</td>
							<td>目的地：</td>
							<td>${ocrDriver.ocrTask2.destination}</td>
							<td>预计送达时间：</td>
							<td><fmt:formatDate value="${ocrDriver.ocrTask2.planArrivalDate}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td>车辆类型：</td>
							<td>${fns:getDictLabel(ocrDriver.ocrTask2.vehicleType, 'vehicle_type', '')}</td>
							<td>车辆品牌：</td>
							<td>${ocrDriver.ocrTask2.brandModels}</td>
							<td>车牌号：</td>
							<td>${ocrDriver.ocrTask2.licensePlateNumber}</td>
							<td>可乘人数：</td>
							<td>${ocrDriver.ocrTask2.seatCount}</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
				      		<td>无</td>
				      	</tr>
			   		</c:otherwise>
				</c:choose>
			</table>
			<c:choose>
				<c:when test="${ocrDriver.ocrTask2!=null&&fns:getDictLabel(ocrDriver.ocrTask2.vehicleType, 'vehicle_type', '')==fns:getDictLabel(ocrJourney.vehicleType, 'vehicle_type', '')}">
					<div class="btn-box">
						<a class="button" href="javascript:vehicleAppointBtn('${ocrDriver.ocrTask2.vehicleId}');">继续使用该车</a>
					</div>
				</c:when>
			</c:choose>
		</div>
	</section>
	<section class="container bn">
		<h3>可选车辆</h3>
		<c:forEach items="${page.list}" var="ocrVehicle">
			<div class="tBox tBox2">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<th>${ocrVehicle.licensePlateNumber}</th>
						<th>类型：${fns:getDictLabel(ocrVehicle.vehicleType, 'vehicle_type', '')}</th>
						<th>${ocrVehicle.brandModels}</th>
						<th>可乘人数：${ocrVehicle.seatCount}</th>
					</tr>
					<tr>
						<th>上一任务：</th>
						<c:choose>
							<c:when test="${ocrVehicle.ocrTask1!=null}">
								<td>出发时间：</td>
								<td><fmt:formatDate value="${ocrVehicle.ocrTask1.departureDate}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>出发地：</td>
								<td>${ocrVehicle.ocrTask1.departurePlace}</td>
								<td>目的地：</td>
								<td>${ocrVehicle.ocrTask1.destination}</td>
								<td>预计送达时间：</td>
								<td><fmt:formatDate value="${ocrVehicle.ocrTask1.planArrivalDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							</c:when>
							<c:otherwise>
					      		<td>无</td>
					   		</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th>下一任务：</th>
						<c:choose>
							<c:when test="${ocrVehicle.ocrTask2!=null}">
								<td>出发时间：</td>
								<td><fmt:formatDate value="${ocrVehicle.ocrTask2.departureDate}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>出发地：</td>
								<td>${ocrVehicle.ocrTask2.departurePlace}</td>
								<td>目的地：</td>
								<td>${ocrVehicle.ocrTask2.destination}</td>
								<td>预计送达时间：</td>
								<td><fmt:formatDate value="${ocrVehicle.ocrTask2.planArrivalDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							</c:when>
							<c:otherwise>
					      		<td>无</td>
					   		</c:otherwise>
						</c:choose>
					</tr>
				</table>
				<a class="button sbutton" href="javascript:vehicleAppointBtn('${ocrVehicle.id}');">使用该车</a>
			</div>
		</c:forEach>
	</section>
	<div class="pagination">${page}</div>
</body>
</html>