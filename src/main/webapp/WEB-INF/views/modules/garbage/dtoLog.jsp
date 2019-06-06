<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>垃圾桶管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		<li class="active"><a href="${ctx}/garbagebin/dtolog/">垃圾桶请求列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="garbageBinLog" action="${ctx}/garbagebin/dtolog/" method="post" type="hidden">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>时间</th>
				<th>请求数据</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbLog" varStatus="status">
			<tr tid="${gbLog.id}">
				<td>${status.index+1}</td>
				<td>
					<fmt:formatDate value="${gbLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gbLog.msg}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>