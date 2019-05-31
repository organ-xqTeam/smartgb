<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>清理记录</title>
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
				$("#dtuId").val('');
				$("#cleanDateStart").val('');
				$("#cleanDateEnd").val('');
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/garbagebin/cleaninfo/">清理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="garbageBinClean" action="${ctx}/garbagebin/cleaninfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="dtuId" placeholder="请输入编号" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li>
				<label>创建时间：</label>
				<input id="cleanDateStart"  name="cleanDateStart"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${garbageBinClean.cleanDateStart}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				
				<input id="cleanDateEnd" name="cleanDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${garbageBinClean.cleanDateEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
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
				<th>清理时间</th>
				<th>编号</th>
				<!-- <th>垃圾量</th> -->
				<th>位置</th>
				<shiro:hasPermission name="gb:cleaninfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbInfo">
			<tr>
				<td>
					<fmt:formatDate value="${gbInfo.cleanDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gbInfo.dtuId}
				</td>
				<%-- <td>
					${gbInfo.garbageMany}%
				</td> --%>
				<td>
					${gbInfo.position}
				</td>
				<shiro:hasPermission name="gb:checkinfo:edit">
				<td>
					<a href="${ctx}/garbagebin/cleaninfo/delete?id=${gbInfo.id}" onclick="return confirmx('确认要删除该记录吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>