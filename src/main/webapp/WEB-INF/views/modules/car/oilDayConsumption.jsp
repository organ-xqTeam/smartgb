<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>油耗详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	   var myChart;
		$(document).ready(function() {
		
			myChart = echarts.init(document.getElementById('statistics'));
			myChart.setOption({
				title : {
					text : ''
				},
				tooltip : {},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				legend : {
					data : [ '油耗(L)' ],
					right : 0,
					bottom : '84%'
				},
				gird : {
				},
				xAxis : {
					data : ${xAxis},
					name : '日期'
				},
				yAxis : {
					name : '油耗(L)',
				},
				series : [ {
					name : '油耗(L)',
					type : 'bar',
					data : ${yAxis},
					label : {
						normal : {
							show : true,
							position : 'top'
						}
					}
				} ]
			});

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
		<li class="active"><a href="${ctx}/car/ocrVehicle/oilView">油耗详情</a></li>
	<%-- 	<shiro:hasPermission name="car:ocrVehicle:edit"><li><a href="${ctx}/car/ocrVehicle/form">车辆添加</a></li></shiro:hasPermission>
	</ul> --%>
	</ul>
	<form:form id="oilView" modelAttribute="ocrVehicle" action="${ctx}/car/ocrVehicle/oilView" method="post" class="breadcrumb form-search">
	<label class="control-label" style="font-size:15px;">车牌号：${ocrVehicle.licensePlateNumber} &nbsp;&nbsp;</label>|
	<label class="control-label" style="font-size:15px;">品牌车型：${ocrVehicle.brandModels}&nbsp;&nbsp;</label> |
	<label class="control-label" style="font-size:15px;">车辆类型：${fns:getDictLabel(ocrVehicle.vehicleType, 'vehicle_type', '')}&nbsp;&nbsp;</label> |
	<label class="control-label" style="font-size:15px;">注册时间：<fmt:formatDate value="${ocrVehicle.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></label> |
	<label class="control-label" style="font-size:15px;">查询时间：${queryDate}&nbsp;&nbsp;</label> |
	<div id="statistics" style="width: 100%;height:500px;"></div>
	</form:form>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期</th>
				<th>当日行驶里程（KM）</th>
                <th>当日油耗（L）</th>
                <th>当日形式轨迹</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${dayList}" var="OcrOilConsumption">
			<tr>
				<td>
					${OcrOilConsumption.dateForQuery }
				</td>
				<td>
					${OcrOilConsumption.mileage}
				</td>
				<td>
					${OcrOilConsumption.oilConsumptionDay}
				</td>
				<td>
				    <a href="${ctx}/car/ocrVehicle/dayTrajectoryMap?equipmentImei=${ocrVehicle.equipmentImei}&carDate=${OcrOilConsumption.dateForQuery}">查看</a>

				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>