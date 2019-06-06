<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆油耗信息</title>
	
	<script type="text/javascript"  src="${ctxStatic}/common/Chart.js"></script> 
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function resetBtn() {
				$("#startDate").val('');
				$("#endDate").val('');
		}
	</script>
	
</head>
<body>
	<form:form id="searchForm" action="${ctx}/drive/oil/list" method="get" class="breadcrumb form-search">
		<ul class="ul-form">
			<li>
				<label>日期范围：</label>
				<input id="startDate"  name="startDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${ocrDriverRecord.recordDateStar}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				
				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${ocrDriverRecord.recordDateEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="" class="btn btn-primary" value="重置" type="button" onclick="javascript:resetBtn();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id="main" style="width: 900px;height:700px;"></div>
	<div id="main1" style="width: 900px;height: 500px;position:relative;margin-top:30px;"></div> 
	
	<sys:message content="${message}"/>
	<%-- <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车辆</th>
				<th>车牌号</th>
				<th>出发时间</th>
				<th>结束时间</th>
				<th>油耗</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${carOilList}" var="carOil">
			<tr>
				<td>
					${carOil.carBand}
				</td>
				<td>
					${carOil.plateNum}
				</td>
				<td>
					<fmt:formatDate value="${carOil.goDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${carOil.arriveDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${carOil.oilNum}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table> --%>
</body>
</html>