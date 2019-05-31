<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>推送信息管理</title>
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
		<li class="active"><a href="${ctx}/message/ocrMessage/">推送信息列表</a></li>
		<shiro:hasPermission name="message:ocrMessage:edit"><li><a href="${ctx}/message/ocrMessage/form">推送信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrMessage" action="${ctx}/message/ocrMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('message_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>内容</th>
				<th>接收端</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>发送时间</th>
				<shiro:hasPermission name="message:ocrMessage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrMessage">
			<tr>
				<td>
					${ocrMessage.title}
				</td>
				<td>
					<div style="width: 150px;white-space: nowrap; text-overflow: ellipsis;overflow: hidden; ">
					${ocrMessage.content}
					</div>
					
				</td>
				<td>
					${fns:getDictLabel(ocrMessage.receiveSide, 'receive_side', '')}
				</td>
				<td>
					${fns:getDictLabel(ocrMessage.status, 'message_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${ocrMessage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:if test="${ocrMessage.status==0}">
				尚未发送
				</c:if>
				<c:if test="${ocrMessage.status==1}">
					<fmt:formatDate value="${ocrMessage.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</c:if>
				</td>
				<shiro:hasPermission name="message:ocrMessage:edit"><td>
				<c:if test="${ocrMessage.status==0}">
    				<a href="${ctx}/message/ocrMessage/form?id=${ocrMessage.id}">编辑</a>
					<a href="${ctx}/message/ocrMessage/send?id=${ocrMessage.id}" onclick="return confirmx('确认要推送该信息吗？', this.href)">发送</a>
				</c:if>
				<c:if test="${ocrMessage.status==1}">
    				<a href="${ctx}/message/ocrMessage/check?id=${ocrMessage.id}">查看</a>
				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>