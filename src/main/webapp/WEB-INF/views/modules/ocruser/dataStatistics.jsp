<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据统计</title>
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
		<li class="active"><a href="${ctx}/ocruser/ocrUser/dataStatistics">数据统计</a></li>	
	</ul>

	<sys:message content="${message}"/>
	<label>	
	截止：<fmt:formatDate value="${statisticsDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	</label>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="20%">累计用户</th>
				<th width="20%">累计合作单位</th>
				<th width="20%">累计行程单量</th>
				<th width="20%">总应收款</th>
				<th width="20%">总已收款</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					${map.userCount}
				</td>
				<td>
					${map.officeCount}
				</td>
				<td>
					${map.orderCount}
				</td>
				<td>
					${map.cost}
				</td>
				<td>
					${map.paid}
				</td>
			</tr>
		</tbody>
	</table>
	<hr>
	<table id="contentTable2" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="20%">司机数量</th>
				<th width="20%">车辆数量</th>
				<th width="20%">舒适型</th>
				<th width="20%">商务型</th>
				<th width="20%">豪华型</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					${map.driverCount}
				</td>
				<td>
					${map.vehicleCount}
				</td>
				<td>
					${map.vehicleS0Count}
				</td>
				<td>
					${map.vehicleS1Count}
				</td>
				<td>
					${map.vehicleS2Count}
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>