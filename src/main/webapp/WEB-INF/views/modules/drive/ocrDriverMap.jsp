<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>行驶轨迹</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.4.2&key=96de376f610face350d9e4288b051ede&plugin=AMap.Autocomplete,AMap.Geocoder"></script>
<script type="text/javascript">
		//车辆相关
		var map;
		var lineArr = [];
		var lngX;
		var latY; 
		var polyline;
		var marker;
		var lnglat
		
		var icon = new AMap.Icon({
			image : "/static/car.png",
			imageOffset : new AMap.Pixel(0, 10)
		});
		
		var toHeight = $(window).height()-78;
		var toWidth = $(window).width()-5;
		
		$(document).ready(
			function() {
				$("#container").css({float:"left",margin:"5px",width:toWidth,height:toHeight});
				$("#contentTable").css({width:toWidth});
				map = new AMap.Map('container');
				map.setZoom(10);
				//行程结束，显示车的出发位置
				map.setCenter(["${startPosition.longitude}","${startPosition.latitude}"]);
				marker = new AMap.Marker({ //加点
					map : map,
					position : ["${startPosition.longitude}","${startPosition.latitude}"],
					icon : icon,
					autoRotation: true,
					zoom : 13
				});
				map.add(marker);
				var allPositionStr = '${allPosition}';
				var allPosition = JSON.parse(allPositionStr);
				for (var i = 0; i < allPosition.length; i++) {
					lngX= allPosition[i].longitude;
					latY= allPosition[i].latitude;
					lnglat = new AMap.LngLat(lngX,latY)
					lineArr.push(lnglat);
				}
				AMap.convertFrom(lineArr,"gps",function(status,result){
					lineArr = result.locations
					// 绘制轨迹
					var polyline = new AMap.Polyline({
						map: map,
						path: lineArr,
						strokeColor: "#00A", //线颜色
						strokeWeight: 3, //线宽
					});
					var passedPolyline = new AMap.Polyline({
						map: map,
						strokeColor: "#F00", //线颜色
						strokeWeight: 3, //线宽
					});
					marker.on('moving',function(e){
						passedPolyline.setPath(e.passedPath);
					})
					map.setFitView();
				});
				AMap.event.addDomListener(document.getElementById('start'), 'click', function() {
					if(document.getElementById('start').value=="行驶回放"){
						marker.moveAlong(lineArr, 500);
						document.getElementById("start").value="回放中";
					}else if(document.getElementById('start').value=="回放中"){
						marker.stopMove();
						document.getElementById("start").value="行驶回放";
					}
				}, false);
		})
		/* $(document).ready(
			function() {
				map = new AMap.Map('container');
				map.setZoom(10);
				//行程结束，显示车的出发位置
				map.setCenter(["${startPosition.longitude}","${startPosition.latitude}"]);
				alert("${startPosition.longitude},${startPosition.latitude}");
				marker = new AMap.Marker({ //加点
					map : map,
					position : ["${startPosition.longitude}","${startPosition.latitude}"],
					icon : icon,
					autoRotation: true
				});
				//行驶回放
				var allPositionStr = '${allPosition}';
				var allPosition = JSON.parse(allPositionStr);
				for (var i = 0; i < allPosition.length; i++) {
					lngX= allPosition[i].longitude;
					latY= allPosition[i].latitude;
					// lineArr.push([lngX, latY]);
					lnglat = new AMap.LngLat(lngX,latY)
					lineArr.push(lnglat);
				}
				AMap.convertFrom(lineArr, "gps",function(status,result){
					lineArr = result.locations
					// 绘制轨迹
					var polyline = new AMap.Polyline({
						map: map,
						path: lineArr,
						strokeColor: "#00A", //线颜色
						strokeWeight: 3, //线宽
					});
					var passedPolyline = new AMap.Polyline({
						map: map,
						strokeColor: "#F00",  //线颜色
						strokeWeight: 3,      //线宽
					});
					marker.on('moving',function(e){
						passedPolyline.setPath(e.passedPath);
					})
					map.setFitView();
				})
				AMap.event.addDomListener(document.getElementById('start'), 'click', function() {
					if(document.getElementById('start').value=="行驶回放"){
						marker.moveAlong(lineArr, 500);
						document.getElementById("start").value="回放中";
					}else if(document.getElementById('start').value=="回放中"){
						marker.stopMove();
						document.getElementById("start").value="行驶回放";
					}
				}, false);
			}
		) ; */
		
	</script>
</head>
<body>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-bottom:0px;margin-left:5px;">
		<thead>
			<tr>
				<th>车牌号</th>
				<th>出发时间</th>
				<th>结束时间</th>
				<th>车辆</th>
				<th>油耗</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${driverRecord.plateNum}</td>
				<td><fmt:formatDate value="${driverRecord.goDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${driverRecord.arriveDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${driverRecord.carBand}</td>
				<td>${driverRecord.oilNum}</td>
				<td><input type="button" class="button" class = "stop" value="行驶回放" id="start"/></td>
			</tr>
		</tbody>
	</table>
	<div id="container"></div>
</body>
</html>
