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
			$("#taskInterval").val("30").trigger("change");
		}
		function vehicleAppointBtn(driverId) {
			window.location.href="${ctx}/journey/ocrJourney/appoint?id=${ocrJourney.id}&driverId="+driverId;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/journey/ocrJourney/">行程列表</a></li>
		<li class="active"><a href="${ctx}/journey/ocrJourney/designate?id=${ocrJourney.id}">任务指派</a></li>
		<li><a href="${ctx}/journey/ocrJourney/canceList">取消通知</a>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrJourney" action="${ctx}/journey/ocrJourney/designate?id=${ocrJourney.id}" method="post" class="breadcrumb form-search">
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
		<h3>可选司机</h3>
		<c:forEach items="${page.list}" var="ocrDriver">
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
			</div>
			<div class="btn-box">
				<a class="button" href="javascript:vehicleAppointBtn('${ocrDriver.id}');">选择该司机并指定车辆</a>
			</div>
			<hr style="height:1px;border:none;border-top:1px dashed #eee;margin:10px 0;"/>
		</c:forEach>
	</section>
	<div class="pagination">${page}</div>
</body>
</html>