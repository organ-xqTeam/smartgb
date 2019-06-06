<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆信息管理</title>
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
		<li class="active"><a href="${ctx}/drive/ocrDriver/">车辆信息列表</a></li>
		<shiro:hasPermission name="drive:carInfo:edit"><li><a href="${ctx}/drive/carInfo/form">车辆添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrCarInfo" action="${ctx}/drive/carInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<li><label>车牌号：</label>
				<form:input path="plateNum" placeholder="请输入车牌号" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>车辆品牌：</label>
				<form:input path="carBand" placeholder="请输入车辆品牌" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>车载手机号：</label>
				<form:input path="terminalPhone" placeholder="请输入车载手机号" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>车辆品牌</th>
				<th>车牌号</th>
				<th>车载手机号</th>
				<th>创建时间</th>
				<shiro:hasPermission name="drive:carInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrCarInfo">
			<tr>
				<td>
					${ocrCarInfo.carBand}
				</td>
				<td>
					${ocrCarInfo.plateNum}
				</td>
				<td>
					${ocrCarInfo.terminalPhone}
				</td>
				<td>
					<fmt:formatDate value="${ocrCarInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="drive:carInfo:edit"><td>
					<a href="${ctx}/drive/carInfo/delete?id=${ocrCarInfo.id}" onclick="return confirmx('确认要删除该车辆吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>