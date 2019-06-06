<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>司机位置管理</title>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=${amapKey}&plugin=AMap.Autocomplete,AMap.Geocoder"></script>
<meta name="decorator" content="default" />
<style type="text/css">
body, html, #container {
	height: 100%;
	margin: 0px;
}
</style>
</head>
<body>

	<div id="container" tabindex="0"></div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">以下为该车辆实时数据</h4>
				</div>
				<div class="modal-body">
					<div class="control-group"></div>
					<div class="control-group">
                        <label id="licensePlateNumber"></label>
                    </div>
                    <div class="control-group">
                        <label id="driver"></label>
                    </div>
                    <div class="control-group">
                        <label id="stage"></label>
                    </div>
                    <div class="control-group">
                        <label id="oil"></label>
                    </div>
					<!-- 
					<div class="control-group">
						<label id="licensePlateNumber"></label>
					</div>
					<div class="control-group">
						<label id="driver"></label>
					</div>
					<div class="control-group">
						<label id="orderNo"></label>
					</div>
					<div class="control-group">
						<label id="oil"></label>
					</div>
					<div class="control-group">
						<label id="speed"></label>
					</div>
					<div class="control-group">
						<label id="at"></label>
					</div>
					<div class="control-group">
						<label id="temperature"></label>
					</div>
					 -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script type="text/javascript">
		var markers = [];
		var map = new AMap.Map('container', {
			resizeEnable : true
		});
		function addMarker(lnglatXY) {
			var marker = new AMap.Marker({ //加点
				map : map,
				position : lnglatXY,
				icon : new AMap.Icon({
					size : new AMap.Size(40, 20), //图标大小
					image : "/officialCarRental/static/car.png",
					imageOffset : new AMap.Pixel(0, 0)
				})
			});
			AMap.event.addListener(marker, 'click', _onClick);
			markers.push(marker);

		}
		$(document).ready(
				//标注车辆位置，并调整地图视角为合适位置
				function() {
					var locationListstr = '${locationList}';
					var locationList = JSON.parse(locationListstr);
					for (var i = 0; i < locationList.length; i++) {
						var location = [ locationList[i].driverLng,
								locationList[i].driverLat ];
						addMarker(location);
					}
					map.setFitView();
				});
		//查询，重新标注车辆位置
		function myrefresh() {
			$
					.ajax({
						url : "${ctx}/driverlocation/ocrDriverLocation/getDriverLocationList",
						success : function(data) {
							map.remove(markers);
							markers = [];
							var list = data;
							for (var i = 0; i < list.length; i++) {
								var location = [ list[i].driverLng,
										list[i].driverLat ];
								addMarker(location);
							}
						}
					});
		}
		//循环任务
		setInterval('myrefresh()', 3000);
		var _onClick = function(e) {
			//alert('以下为该车辆历史平均数据:油耗：' + randomNum(6, 10) + '/100KM 转数：'
			//	+ randomNum(800, 1600) + 'r/min 时速：' + randomNum(30, 60)
			//+ 'KM/H 水温：' + randomNum(80, 90) + '度');
			var driverName = '赵';
			var oil = '总油耗：' + '2021.6' + 'L';
			var stage = '车辆状态：' + '停车';
		//	var at = '时速：' + randomNum(30, 60) + 'KM/H';
		//	var temperature = '水温：' + randomNum(80, 90) + '℃';
			var licensePlateNumber = '车牌号：陕A' + randomNum(10000, 99999);
			//var nameIndex = randomNum(0, 7);
			var driver = '驾驶员：' + driverName +'师傅';
		//	var orderNo = '订单号：' + randomNum(1000000000, 9999999999);
			$("#oil").html(oil);
			$("#stage").html(stage);
			//$("#at").html(at);
			//$("#temperature").html(temperature);
	        $("#licensePlateNumber").html(licensePlateNumber);
	        $("#driver").html(driver);
	       // $("#orderNo").html(orderNo);
			$('#myModal').modal('show');

		}
		function randomNum(minNum, maxNum) {
			switch (arguments.length) {
			case 1:
				return parseInt(Math.random() * minNum + 1, 10);
				break;
			case 2:
				return parseInt(Math.random() * (maxNum - minNum + 1) + minNum,
						10);
				break;
			default:
				return 0;
				break;
			}
		}
	</script>
</body>
</html>