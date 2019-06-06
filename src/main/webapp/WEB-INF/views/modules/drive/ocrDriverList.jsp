<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>司机管理</title>
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
				$("#realName").val('');
				$("#phone").val('');
				$("#cardNumber").val('');
				$("#jobNumber").val('');
		}
		function sort(order){
			if($('#orderBy').val()==order)
				$('#orderBy').val(order+' DESC');
			else
				$('#orderBy').val(order);

			$('#searchForm').submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/drive/ocrDriver/">司机列表</a></li>
		<shiro:hasPermission name="drive:ocrDriver:edit"><li><a href="${ctx}/drive/ocrDriver/form">司机添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrDriver" action="${ctx}/drive/ocrDriver/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="realName" placeholder="请输入姓名" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" placeholder="请输入联系电话" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="cardNumber" placeholder="请输入身份证号" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%-- <li><label>驾驶员编号：</label>
				<form:input path="jobNumber" placeholder="请输入驾驶员编号" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="" class="btn btn-primary" value="重置" type="button" onclick="javascript:resetBtn();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>联系电话</th>
				<th>身份证号</th>
				<th>驾驶员编号</th>
				<th>司机所在地</th>
				<th><a href="javascript:void(0)" onclick="sort('status')">任务状态</a></th>
				<th>注册时间</th>
				<!-- 
				<th>更新时间</th>
				<th>备注信息</th>
				 -->
				<shiro:hasPermission name="drive:ocrDriver:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrDriver">
			<tr>
				<td>
					${ocrDriver.realName}
				</td>
				<td>
					${fns:getDictLabel(ocrDriver.sex, 'sex', '')}
				</td>
				<td>
					${ocrDriver.phone}
				</td>
				<td>
					${ocrDriver.cardNumber}
				</td>
				<td>
					${ocrDriver.jobNumber}
				</td>
				<td>
					${ocrDriver.area.name}
				</td>
				<td>
					${fns:getDictLabel(ocrDriver.status, 'ocr_driver_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${ocrDriver.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 
				<td>
					<fmt:formatDate value="${ocrDriver.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ocrDriver.remarks}
				</td>
				 -->
				<shiro:hasPermission name="drive:ocrDriver:edit"><td>
    				<a href="${ctx}/drive/ocrDriver/form?id=${ocrDriver.id}">编辑</a>
					<a href="${ctx}/drive/ocrDriver/delete?id=${ocrDriver.id}" onclick="return confirmx('确认要删除该司机吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>