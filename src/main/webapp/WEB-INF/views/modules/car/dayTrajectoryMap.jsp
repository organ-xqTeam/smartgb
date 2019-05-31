<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>订单管理</title>
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
			//size : new AMap.Size(40, 20), //图标大小
			image : "/officialCarRental/static/car.png",
			imageOffset : new AMap.Pixel(0, 10)
			});
	$(document).ready(function() {
		//debugger;
 		map = new AMap.Map('containerMap');
		map.setZoom(10);
		map.setCenter(["${startPosition.longitude}","${startPosition.latitude}"]); 
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
				lngX = allPosition[i].longitude;
				latY = allPosition[i].latitude;
				// lineArr.push([lngX, latY]);
				lnglat = new AMap.LngLat(lngX, latY)
				lineArr.push(lnglat);
				if (i != 0 && i % 1000 == 0) {
					AMap.convertFrom(lineArr, "gps",
						function(status, result) {
							lineArr = result.locations
							// 绘制轨迹
							var polyline = new AMap.Polyline({
								map : map,
								path : lineArr,
								strokeColor : "#00A", //线颜色
								strokeWeight : 3, //线宽
							});
							var passedPolyline = new AMap.Polyline({
								map : map,
								strokeColor : "#F00", //线颜色
								strokeWeight : 3, //线宽
							});
							marker.on('moving', function(e) {
								passedPolyline.setPath(e.passedPath);
							})
							map.setFitView();
						})
					continue;
				}
				if (i % 1000 > 0 && i == allPosition.length - 1) {
					AMap.convertFrom(lineArr, "gps",
						function(status, result) {
							lineArr = result.locations
							// 绘制轨迹
							var polyline = new AMap.Polyline({
								map : map,
								path : lineArr,
								strokeColor : "#00A", //线颜色
								strokeWeight : 3, //线宽
							});
							var passedPolyline = new AMap.Polyline({
								map : map,
								strokeColor : "#F00", //线颜色
								strokeWeight : 3, //线宽
							});
							marker.on('moving', function(e) {
								passedPolyline.setPath(e.passedPath);
							})
							map.setFitView();
000000000000000000000000000						})
				}
			}
			AMap.event.addDomListener(document.getElementById('start'), 'click', function() {
				if (document.getElementById('start').value == "行驶回放") {
					marker.moveAlong(lineArr, 500);
					document.getElementById("start").value = "回放中";
				} else if (document.getElementById('start').value == "回放中") {
					marker.stopMove();
					document.getElementById("start").value = "行驶回放";
				}
	
			}, false);
		});
</script>
</head>
<body>
	<div><font style="font-weight:bold;" >当日轨迹</font></div>
	<div id="containerMap" style="margin:10px;width:1500px; height:745px;float:left">
	</div>
		<input type="button" class="button" class = "stop" value="行驶回放" id="start"/>
</body>

<script type="text/javascript">


/* 			  //车辆相关
			  var map;
			  var lineArr = [];
			  var lngX;
			  var latY; 
			  var polyline;
			  var marker;
			  var lnglat
			  var icon = new AMap.Icon({
					//size : new AMap.Size(40, 20), //图标大小
					image : "/officialCarRental/static/car.png",
					imageOffset : new AMap.Pixel(0, 10)
					});
					 
		 $(document).ready(
					function() {
					debugger;
							map = new AMap.Map('containerMap');
							map.setZoom(10);
							map.setCenter(["${startPosition.longitude}","${startPosition.latitude}"]); 
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
							AMap.convertFrom(lineArr, "gps",
							function(status,result){
							lineArr = result.locations
							// 绘制轨迹
								    var polyline = new AMap.Polyline({
								        map: map,
								        path: lineArr,
								        strokeColor: "#00A",  //线颜色
								        strokeWeight: 3,      //线宽
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
					); */
</script>
</html>