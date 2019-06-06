<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>心跳管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/obd/heartbeat/messageHeartbeat/">心跳列表</a></li>
		<shiro:hasPermission name="obd:heartbeat:messageHeartbeat:edit"><li><a href="${ctx}/obd/heartbeat/messageHeartbeat/form">心跳添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="messageHeartbeat" action="${ctx}/obd/heartbeat/messageHeartbeat/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>car_id：</label>
				<form:input path="carId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>create_time：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${messageHeartbeat.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="obd:heartbeat:messageHeartbeat:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="messageHeartbeat">
			<tr>
				<shiro:hasPermission name="obd:heartbeat:messageHeartbeat:edit"><td>
    				<a href="${ctx}/obd/heartbeat/messageHeartbeat/form?id=${messageHeartbeat.id}">修改</a>
					<a href="${ctx}/obd/heartbeat/messageHeartbeat/delete?id=${messageHeartbeat.id}" onclick="return confirmx('确认要删除该心跳吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>