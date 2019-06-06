<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤记录</title>
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
				$("#name").val('');
				$("#phone").val('');
				$("#idNum").val('');
				$("#driveNum").val('');
				$("#workDateStar").val('');
				$("#workDateEnd").val('');
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/drive/work/">考勤列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrDriverWork" action="${ctx}/drive/work/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" placeholder="请输入姓名" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" placeholder="请输入联系电话" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="idNum" placeholder="请输入身份证号" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>驾驶员编号：</label>
				<form:input path="driveNum" placeholder="请输入编号" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li>
				<label>日期范围：</label>
				<input id="workDateStar"  name="workDateStar"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${ocrDriverWork.workDateStar}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				
				<input id="workDateEnd" name="workDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${ocrDriverWork.workDateEnd}" pattern="yyyy-MM-dd"/>"
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
				<th>姓名</th>
				<th>性别</th>
				<th>联系电话</th>
				<th>身份证号</th>
				<th>驾驶员编号</th>
				<th>司机所在地</th>
				<th>上班时间</th>
				<th>下班时间</th>
				<shiro:hasPermission name="drive:work:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="workInfo">
			<tr>
				<td>${workInfo.name}</td>
				<td>
					${fns:getDictLabel(workInfo.sex, 'sex', '')}
				</td>
				<td>${workInfo.phone}</td>
				<td>${workInfo.idNum}</td>
				<td>${workInfo.driveNum}</td>
				<td>${workInfo.position}</td>
				<td>
					<fmt:formatDate value="${workInfo.onWork}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${workInfo.offWork}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="drive:work:edit">
				<td>
					<a href="${ctx}/drive/work/delete?id=${workInfo.id}" onclick="return confirmx('确认要删除该记录吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>