<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计费规则管理</title>
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
		<li class="active"><a href="${ctx}/billing/ocrBilling/">计费规则列表</a></li>
		<shiro:hasPermission name="billing:ocrBilling:edit"><li><a href="${ctx}/billing/ocrBilling/form">计费规则添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrBilling" action="${ctx}/billing/ocrBilling/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>规则系数：</label>
				<form:input path="ruleCoefficient" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>规则系数</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="billing:ocrBilling:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrBilling">
			<tr>
				<td><a href="${ctx}/billing/ocrBilling/form?id=${ocrBilling.id}">
					${ocrBilling.ruleCoefficient}
				</a></td>
				<td>
					<fmt:formatDate value="${ocrBilling.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ocrBilling.remarks}
				</td>
				<shiro:hasPermission name="billing:ocrBilling:edit"><td>
    				<a href="${ctx}/billing/ocrBilling/form?id=${ocrBilling.id}">修改</a>
					<a href="${ctx}/billing/ocrBilling/delete?id=${ocrBilling.id}" onclick="return confirmx('确认要删除该计费规则吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>