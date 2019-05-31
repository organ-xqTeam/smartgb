<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户反馈管理</title>
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
		 function resetBtn() {
				$("#status").val(null).trigger("change");
				
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ocruser/ocrFeedback/">用户反馈列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrFeedback" action="${ctx}/ocruser/ocrFeedback/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('feedback_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="" class="btn btn-primary" value="重置" type="button" onclick="javascript:resetBtn();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>真实姓名</th>
				<th>手机号</th>
				<th>内容</th>
				<th>状态</th>
				<th>创建时间</th>
				<shiro:hasPermission name="ocruser:ocrFeedback:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrFeedback">
			<tr>
				<td>
					${ocrFeedback.realName}
				</td>
				<td>
					${ocrFeedback.phone}
				</td>
				<td>
					<div style="width: 150px;white-space: nowrap; text-overflow: ellipsis;overflow: hidden; ">
					${ocrFeedback.content}
					</div>
				</td>
				<td>
					${fns:getDictLabel(ocrFeedback.status, 'feedback_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${ocrFeedback.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ocruser:ocrFeedback:view"><td>
    				<a href="${ctx}/ocruser/ocrFeedback/form?id=${ocrFeedback.id}">查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>