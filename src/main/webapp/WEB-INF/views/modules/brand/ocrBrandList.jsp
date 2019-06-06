<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆厂牌管理</title>
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
		<li class="active"><a href="${ctx}/brand/ocrBrand/">车辆厂牌列表</a></li>
		<shiro:hasPermission name="brand:ocrBrand:edit"><li><a href="${ctx}/brand/ocrBrand/form">车辆厂牌添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrBrand" action="${ctx}/brand/ocrBrand/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<!-- 
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			 -->
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车辆厂牌</th>
				<th>油箱类型</th>
				<shiro:hasPermission name="brand:ocrBrand:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrBrand">
			<tr>
				<td><a href="${ctx}/brand/ocrBrand/form?id=${ocrBrand.id}">
					${ocrBrand.vehicleBrand}
				</a></td>
				<td>
					${fns:getDictLabel(ocrBrand.tankType, 'tank_type', '')}
				</td>
				<shiro:hasPermission name="brand:ocrBrand:edit"><td>
    				<a href="${ctx}/brand/ocrBrand/form?id=${ocrBrand.id}">修改</a>
					<a href="${ctx}/brand/ocrBrand/delete?id=${ocrBrand.id}" onclick="return confirmx('确认要删除该车辆厂牌吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>