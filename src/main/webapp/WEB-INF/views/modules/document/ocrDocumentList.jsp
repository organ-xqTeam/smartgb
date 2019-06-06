<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档管理</title>
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
		<li class="active"><a href="${ctx}/document/ocrDocument/">文档列表</a></li>
		<shiro:hasPermission name="document:ocrDocument:edit"><li><a href="${ctx}/document/ocrDocument/form">文档添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrDocument" action="${ctx}/document/ocrDocument/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类型</th>
				<th>内容</th>
				<th>备注信息</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="document:ocrDocument:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrDocument">
			<tr>
				<td><a href="${ctx}/document/ocrDocument/form?id=${ocrDocument.id}">
					${fns:getDictLabel(ocrDocument.type, 'document_type', '')}
				</a></td>
				<td>
				<c:set var="content" value="${ocrDocument.content}"/>
				<c:choose>  
				    <c:when test="${fn:length(content) > 30}">  
				        <c:out value="${fn:substring(content, 0, 30)}......"/>  
				    </c:when>  
				   <c:otherwise>  
				      <c:out value="${content}"/>  
				    </c:otherwise>  
				</c:choose>
				</td>
				<td>
					${ocrDocument.remarks}
				</td>
				<td>
					<fmt:formatDate value="${ocrDocument.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ocrDocument.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="document:ocrDocument:edit"><td>
    				<a href="${ctx}/document/ocrDocument/form?id=${ocrDocument.id}">修改</a>
					<a href="${ctx}/document/ocrDocument/delete?id=${ocrDocument.id}" onclick="return confirmx('确认要删除该文档吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>