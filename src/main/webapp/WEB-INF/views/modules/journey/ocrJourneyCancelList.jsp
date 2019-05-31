<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行程管理</title>
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
			$("#status").val(null).trigger("change");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/journey/ocrJourney/">行程列表</a></li>
		<li><a style=cursor:default>任务指派</a></li>
		<li class="active"><a href="${ctx}/journey/ocrJourney/canceList">取消通知</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrJourney" action="${ctx}/journey/ocrJourney/canceList" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	</form:form>
	<sys:message content="${message}"/>
	<c:forEach items="${page.list}" var="ocrJourney">
		<section class="header">
			<ul>
				<li>行程单号：<span>${ocrJourney.id}</span></li>
				<li><span>${ocrJourney.companyName}</span></li>
				<li><span><fmt:formatDate value="${ocrJourney.createDate}" pattern="yyyy-MM-dd HH:mm"/></li>
			</ul>
			<ul>
				<li>出发地：<span>${ocrJourney.departurePlace}</span></li>
				<li>目的地：<span>${ocrJourney.destination}</span></li>
				<li>出发时间：<span><fmt:formatDate value="${ocrJourney.departureDate}" pattern="yyyy-MM-dd HH:mm"/></span></li>
				<li>预计送达时间：<span><fmt:formatDate value="${ocrJourney.planArrivalDate}" pattern="yyyy-MM-dd HH:mm"/></span></li>
			</ul>
		</section>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>车辆类型</th>
					<th>支付方式</th>
					<th>费用</th>
					<th>申请人</th>
					<th>电话</th>
					<th>工号</th>
					<th>同行人数</th>
					<th>出现目的</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td>
					${fns:getDictLabel(ocrJourney.vehicleType, 'vehicle_type', '')}
				</td>
				<td>
					${fns:getDictLabel(ocrJourney.paymentMode, 'payment_mode', '')}
				</td>
				<td>
					${ocrJourney.cost}
				</td>
				<td>
					${ocrJourney.userRealName}
				</td>
				<td>
					${ocrJourney.phone}
				</td>
				<td>
					${ocrJourney.userJobNumber}
				</td>
				<td>
					${ocrJourney.peopleNumber}
				</td>
				<td>
					${ocrJourney.appearPurpose}
				</td>
				<td>
				${ocrJourney.status}
					${fns:getDictLabel(ocrJourney.status, 'journey_status', '')}
				</td>
			</tr>
			</tbody>
		</table>
		<section class="header">
			<ul>
				<li>发车单号：<span>${ocrJourney.taskId}</span></li>
			</ul>
		</section>
	    <table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>司机姓名</th>
					<th>工号</th>
					<th>电话</th>
					<th>车辆品牌</th>
					<th>车牌号</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td>
					${ocrJourney.driverRealName}
				</td>
				<td>
					${ocrJourney.driverJobNumber}
				</td>
				<td>
					${ocrJourney.driverPhone}
				</td>
				<td>
					${ocrJourney.brandModels}
				</td>
				<td>
					${ocrJourney.licensePlateNumber}
				</td>
				<td>
					${fns:getDictLabel(ocrJourney.taskStatus, 'task_status', '')}
				</td>
			</tr>
			</tbody>
		</table>
		<hr style="height:1px;border:none;border-top:1px solid #808080;" />
	</c:forEach>
	<div class="pagination">${page}</div>
</body>
</html>