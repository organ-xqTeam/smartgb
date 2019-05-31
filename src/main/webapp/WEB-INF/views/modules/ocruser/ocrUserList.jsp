<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>租车用户管理</title>
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
		<li class="active"><a href="${ctx}/ocruser/ocrUser/">租车用户列表</a></li>
			
	</ul>
	<form:form id="searchForm" modelAttribute="ocrUser" action="${ctx}/ocruser/ocrUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>真实姓名：</label>
				<form:input path="realName" placeholder="请输入真实姓名"  htmlEscape="false" maxlength="100" class="input-medium realName"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="" class="btn btn-primary" value="重置" type="button" onclick="javascript:$('.realName').val('')"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登录名</th>
				<th>真实姓名</th>
				<th>单位</th>
				<th>员工编号</th>
				<th>注册时间</th>
				<shiro:hasPermission name="ocruser:ocrUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrUser">
			<tr>
				<td><!-- <a href="${ctx}/ocruser/ocrUser/form?id=${ocrUser.id}">
					${ocrUser.loginName}
				</a> -->
					${ocrUser.loginName}
				</td>
				<td>
					${ocrUser.realName}
				</td>
				<td>
					${ocrUser.office.name}
				</td>
				<td>
					${ocrUser.jobNumber}
				</td>
				<td>
					<fmt:formatDate value="${ocrUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ocruser:ocrUser:edit"><td>
    				<!-- <a href="${ctx}/ocruser/ocrUser/form?id=${ocrUser.id}">修改</a>
					 -->
					<a href="${ctx}/ocruser/ocrUser/delete?id=${ocrUser.id}" onclick="return confirmx('确认要删除该租车用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>