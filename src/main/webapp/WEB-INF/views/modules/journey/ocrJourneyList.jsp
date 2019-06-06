<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行程管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/journey.css" type="text/css" rel="stylesheet" />
	<style>.header{border-bottom:none!important;}</style>
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
			$("#status").val("2").trigger("change");
			$('#areaId').val('').trigger("change");
		}
		function refresh() {
			document.getElementById("btnSubmit").click();
		}
		setTimeout(function() {
			refresh();
		}, 10000); //指定60秒刷新一次
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/journey/ocrJourney/">行程列表</a></li>
		<li><a style=cursor:default>任务指派</a></li>
		<li><a href="${ctx}/journey/ocrJourney/canceList">取消通知</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrJourney" action="${ctx}/journey/ocrJourney/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>状态：</label>
				<form:select path="status" placeholder="请选择状态" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('journey_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>地区：</label>
				<form:select path="areaId" placeholder="请选择地区" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${areas}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" value="重置" type="button" onclick="javascript:resetBtn();"/></li>
			<li class="clearfix"></li>
		</ul>
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
		<div class="table-content">
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
					<c:choose>
						<c:when test="${ocrJourney.status==1||ocrJourney.status==2}">
							<shiro:hasPermission name="journey:ocrJourney:edit"><th>操作</th></shiro:hasPermission>
						</c:when>
					</c:choose>
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
					${fns:getDictLabel(ocrJourney.status, 'journey_status', '')}
				</td>
				<c:choose>
					<c:when test="${ocrJourney.status==1}">
						<shiro:hasPermission name="journey:ocrJourney:edit"><td>
	    					<a href="${ctx}/journey/ocrJourney/designate?id=${ocrJourney.id}">重新指派</a>
						</td></shiro:hasPermission>
					</c:when>
					<c:when test="${ocrJourney.status==2}">
						<shiro:hasPermission name="journey:ocrJourney:edit"><td>
	    					<a href="${ctx}/journey/ocrJourney/designate?id=${ocrJourney.id}">指派任务</a>
						</td></shiro:hasPermission>
					</c:when>
				</c:choose>
			</tr>
			</tbody>
		</table>
		</div>
		<c:if test="${ocrJourney.status==3||ocrJourney.status==4||ocrJourney.status==5||ocrJourney.status==6||ocrJourney.status==7}">
			<section class="header">
				<ul>
					<li>发车单号：<span>${ocrJourney.taskId}</span></li>
				</ul>
			</section>
			<div class="table-content">
		    <table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>司机姓名</th>
						<th>工号</th>
						<th>电话</th>
						<th>车辆品牌</th>
						<th>车牌号</th>
						<th>状态</th>
						<c:if test="${ocrJourney.taskStatus==2}">
							<shiro:hasPermission name="journey:ocrJourney:edit"><th>操作</th></shiro:hasPermission>
						</c:if>
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
					<c:if test="${ocrJourney.taskStatus==2}">
					<shiro:hasPermission name="journey:ocrJourney:edit"><td>
	    				<a href="${ctx}/journey/ocrJourney/designate?id=${ocrJourney.id}">重新指派</a>
					</td></shiro:hasPermission>
				</c:if>
				</tr>
				</tbody>
			</table>
			</div>
		</c:if>
		<hr style="height:1px;border:none;border-top:1px solid #ccc;" />
	</c:forEach>
	<div class="pagination">${page}</div>
</body>
</html>