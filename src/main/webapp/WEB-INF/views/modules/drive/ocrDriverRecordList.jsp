<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行驶记录</title>
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
				$("#workDateStar").val('');
				$("#workDateEnd").val('');
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/drive/record/">行驶列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrDriverRecord" action="${ctx}/drive/record/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<li>
				<label>日期范围：</label>
				<input id="recordDateStar"  name="recordDateStar"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${ocrDriverRecord.recordDateStar}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				
				<input id="recordDateEnd" name="workDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${ocrDriverRecord.recordDateEnd}" pattern="yyyy-MM-dd"/>"
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
				<th>车牌号</th>
				<th>出发时间</th>
				<th>结束时间</th>
				<th>车辆</th>
				<th>油耗</th>
				<th>行驶数据</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recordInfo">
			<tr>
				<td>${recordInfo.plateNum}</td>
				<td>
					<fmt:formatDate value="${recordInfo.goDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${recordInfo.arriveDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${recordInfo.carBand}</td>
				<td>${recordInfo.oilNum}L</td>
				<td>
					<a href="${ctx}/drive/record/travelMap?equipmentImei=${recordInfo.carId}&goDate=<fmt:formatDate value="${recordInfo.goDate}" pattern="yyyy-MM-dd"/>">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>