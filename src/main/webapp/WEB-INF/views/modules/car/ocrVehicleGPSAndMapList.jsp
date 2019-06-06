<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆管理</title>
	<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=96de376f610face350d9e4288b051ede&plugin=AMap.Autocomplete,AMap.Geocoder"></script>
	<meta name="decorator" content="default"/>
	<style type="text/css">
body, html,div, #container {
	height: 100%;
	margin: 0px;
}
</style>
</head>
<body>
<div  id="left">
	<form:form id="searchForm" modelAttribute="ocrVehicle" action="${ctx}/car/ocrVehicle/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<li><label>车牌号：</label>
				<form:input path="licensePlateNumber" placeholder="请输入车牌号"  htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<shiro:hasPermission name="car:ocrVehicle:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrVehicle">
			<tr>
				<td>
					${ocrVehicle.licensePlateNumber}
				</td>
				<shiro:hasPermission name="car:ocrVehicle:edit"><td>
				    <a href="javascript:;" onclick="ckClick('${ocrVehicle.id}')">查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	<div  style="width: 100%">
		<div id="container" tabindex="0"></div>
	</div>
	
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"  style="height:auto;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">以下为该车辆实时数据</h4>
				</div>
					<div class="control-group">
                        <label style="display:block;" id="MlicensePlateNumber">车牌号：</label>
                        <label style="display:block;" id="vehicleType">车型：</label>
                        <label style="display:block;" id="brandModels">品牌：</label>
                        <label style="display:block;" id="speed">时速：</label>
                        <label style="display:block;" id="direction">航向：</label>
                        <label style="display:block;" id="hourOil">小时油耗：</label>
                        <label style="display:block;" id="hundredKilometersOil">百公里油耗：</label>
                        <label style="display:block;" id="KilometreNumber">里程表数：</label>
                        <label style="display:block;" id="tankOil">剩余油量：</label>
                    </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
	</div>
	</div>
			<!-- /.modal-content -->
	<script type="text/javascript">
			var markers = [];
			var gpsArr = [];
			var carId;
			var lnla;
			var map = new AMap.Map('container', {
			resizeEnable : true
		});
		function addMarker(lnglatXY,id) {
			var marker = new AMap.Marker({ //加点
				map : map,
				position : lnglatXY,
				icon : new AMap.Icon({
					//size : new AMap.Size(40, 20), //图标大小
					image : "/officialCarRental/static/car.png",
					imageOffset : new AMap.Pixel(0, 10),
					
				}),
				resizeEnable: true
			});
			marker.CarId = id;
			marker.CarXY = lnglatXY;
			AMap.event.addListener(marker, 'click', _onClick);
			markers.push(marker);

		}
		//加载车辆标记
		$(document).ready(function() {
					var locationListstr = '${locationList}';
					var GPSjson = JSON.parse(locationListstr);
					for (var i = 0; i < GPSjson.length; i++) {
						var gps =new AMap.LngLat(GPSjson[i].longitude,GPSjson[i].latitude);
						//gpsArr = [];
						//gpsArr.push(gps);
							 //  	AMap.convertFrom(gpsArr, "gps",
						//	function(status,result){
					//		debugger;
							//var gpsGodArr = result.locations
							addMarker(gps,GPSjson[i].id);
								
						//	})
					}
					
					map.setFitView();
					
		});
		
		//刷新坐标点，做轨迹回放
				function myrefresh() {
			$.ajax({
				url : "${ctx}/driverlocation/ocrDriverLocation/ocrVehicleGPSList",
				success : function(data) {
					for (var j = 0; j < data.length; j++) {
					 lnla = new AMap.LngLat(data[j].longitude,data[j].latitude);
						for (var i = 0; i < markers.length; i++) {
						if(data[j].id == markers[i].CarId){
						var lineArr = [];
    					lineArr.push(markers[i].CarXY);
        				lineArr.push(lnla);
											// 绘制轨迹
								var polyline = new AMap.Polyline({
									map : map,
									path : lineArr,
									strokeColor : "#00A", //线颜色
									strokeOpacity: 0,     //线透明度
									strokeWeight : 3, //线宽
								});
	  					markers[i].moveAlong(lineArr, 200);
	  					markers[i].CarXY = lnla;
						}
					}
					}
				}
			});
			}
			//定时任务刷新
		setInterval('myrefresh()', 10000);

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function resetBtn() {
			$("#vehicleType").val(null).trigger("change");
			$("#licensePlateNumber").val('');
			$("#brandModels").val('');
			$("#status").val(null).trigger("change");
			
		}
		function sort(order){
			if($('#orderBy').val()==order)
			$('#orderBy').val(order+' DESC');
			else
			$('#orderBy').val(order);

			$('#searchForm').submit();
		}
		//点击地图车辆弹框
		var _onClick = function(e) {
					$.ajax({
				url : "${ctx}/car/ocrVehicle/findObdItemById",
				data : {id:e.target.CarId},
				success : function(data) {
						debugger;
				
					var licensePlateNumber = '车牌号：' + data.gps.licensePlateNumber;
					$("#MlicensePlateNumber").html(licensePlateNumber);
					
					var vehicleType = '车型：';
						if(data.gps.vehicleType=='0'){
						vehicleType = vehicleType +'舒适型';
					}
						if(data.gps.vehicleType=='1'){
						vehicleType = vehicleType +'商务型';
					}
						if(data.gps.vehicleType=='2'){
						vehicleType = vehicleType +'豪华型';
					}
					$("#vehicleType").html(vehicleType);
					
					var brandModels = '品牌：' + data.gps.brandModels;
					$("#brandModels").html(brandModels);
					
					var speed = '时速：' + data.gps.speed;
					$("#speed").html(speed);
					
					var direction = '航向：' + data.gps.direction;
					$("#direction").html(direction);
					
					var hourOil = '小时耗油：' + data.obd.hourOil;
					$("#hourOil").html(hourOil);
					
					var hundredKilometersOil = '百公里耗油：' + data.obd.hundredKilometersOil;
					$("#hundredKilometersOil").html(hundredKilometersOil);
					
					var KilometreNumber = '里程表数：' + data.obd.KilometreNumber;
					$("#KilometreNumber").html(KilometreNumber);
					

					var tankOil = '剩余油量：' + data.obd.tankOil;
					if(data.gps.tankType=='0'){
						tankOil = tankOil + 'L'
					}
					if(data.gps.tankType=='1'){
						tankOil = tankOil + '%'
					}
					
					$("#tankOil").html(tankOil);
					
					var point = [ data.gps.longitude,
								data.gps.latitude ]
					 map.setZoomAndCenter(15, point);
					
					$('#myModal').modal('show');
		
				}
			});
		
		

		}
	//点击列表弹框
		 function ckClick(id){
						$.ajax({
				url : "${ctx}/car/ocrVehicle/findObdItemById",
				data : {id:id},
				success : function(data) {
						debugger;
				
					var licensePlateNumber = '车牌号：' + data.gps.licensePlateNumber;
					$("#MlicensePlateNumber").html(licensePlateNumber);
					
					var vehicleType = '车型：';
						if(data.gps.vehicleType=='0'){
						vehicleType = vehicleType +'舒适型';
					}
						if(data.gps.vehicleType=='1'){
						vehicleType = vehicleType +'商务型';
					}
						if(data.gps.vehicleType=='2'){
						vehicleType = vehicleType +'豪华型';
					}
					$("#vehicleType").html(vehicleType);
					
					var brandModels = '品牌：' + data.gps.brandModels;
					$("#brandModels").html(brandModels);
					
					var speed = '时速：' + data.gps.speed;
					$("#speed").html(speed);
					
					var direction = '航向：' + data.gps.direction;
					$("#direction").html(direction);
					
					var hourOil = '小时耗油：' + data.obd.hourOil;
					$("#hourOil").html(hourOil);
					
					var hundredKilometersOil = '百公里耗油：' + data.obd.hundredKilometersOil;
					$("#hundredKilometersOil").html(hundredKilometersOil);
					
					var KilometreNumber = '里程表数：' + data.obd.KilometreNumber;
					$("#KilometreNumber").html(KilometreNumber);
					

					var tankOil = '剩余油量：' + data.obd.tankOil;
					if(data.gps.tankType=='0'){
						tankOil = tankOil + 'L'
					}
					if(data.gps.tankType=='1'){
						tankOil = tankOil + '%'
					}
					
					$("#tankOil").html(tankOil);
					
					var point = [ data.gps.longitude,
								data.gps.latitude ]
					 map.setZoomAndCenter(15, point);
					
					$('#myModal').modal('show');
		
				}
			});
		
		

		}	
	</script>
</body>
</html>