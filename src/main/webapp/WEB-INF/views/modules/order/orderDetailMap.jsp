<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>订单管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.4.2&key=96de376f610face350d9e4288b051ede&plugin=AMap.Autocomplete,AMap.Geocoder"></script>
<script type="text/javascript">
		 //显示实时时间
/* 		  function gettime(){
			  var d = new Date().toLocaleString();
			 document.getElementById("t").innerHTML =d;
			 window.setTimeout("gettime()",1000);
			 } 
			  window.onload = gettime;  */
			  
			 
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
			  
 		function addMarker(data) { 
						var lineArr = [];
						var lnla = new AMap.LngLat(data.longitude,data.latitude);
						
    					lineArr.push(marker.CarXY);
        				lineArr.push(lnla);
        				
        						   	AMap.convertFrom(lineArr, "gps",
							function(status,result){
							lineArr = result.locations
											// 绘制轨迹
								var polyline = new AMap.Polyline({
									map : map,
								    path: lineArr,          //设置线覆盖物路径
							        strokeColor: "#00A", //线颜色
							        strokeWeight: 3,        //线宽
								});
	  					marker.moveAlong(lineArr, 200);
	  					marker.CarXY = lnla;  
								  
							})
					
	
			} 
		//查询，重新标注车辆位置
		 function myrefresh() {
			$.ajax({
						url : "${ctx}/odb/messageGps/getNewLatLngByCarId",
					    data:  {id:"${ocrOrder.vehicle.id}"},  
						success : function(data) {
						addMarker(data);
							}
						});
			}
			

		 
		 $(document).ready(
					function() {
							map = new AMap.Map('container');
							map.setZoom(10);
							//行程中，显示当前车的位置
							if(${ocrOrder.status}==4){
								map.setCenter(["${nowPosition.longitude}","${nowPosition.latitude}"]); 
								 marker = new AMap.Marker({ //加点
									map : map,
									position : ["${nowPosition.longitude}","${nowPosition.latitude}"], 
									icon : icon,
									resizeEnable: true
									});
								//画折线
								var allPositionStr = '${allPosition}';
							    var allPosition = JSON.parse(allPositionStr);
								for (var i = 0; i < allPosition.length; i++) {
									   lngX= allPosition[i].longitude;
									   latY= allPosition[i].latitude;
									   lnglat = new AMap.LngLat(lngX,latY)
									   lineArr.push(lnglat);
								   }
								   	AMap.convertFrom(lineArr, "gps",
							function(status,result){
							lineArr = result.locations
								    polyline = new AMap.Polyline({
							        path: lineArr,          //设置线覆盖物路径
							        strokeColor: "#00A", //线颜色
							        strokeWeight: 3,        //线宽
							    });
							    polyline.setMap(map);
							    marker.CarXY =  new AMap.LngLat("${nowPosition.longitude}","${nowPosition.latitude}");
							    			//定时任务刷新
							setInterval('myrefresh()', 10000);
							})
						
							}else{
								//行程结束，显示车的出发位置
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
								    
							}
					) ;
		 
	</script>
</head>
<body>
	<label class="control-label">行程单号：${ocrOrder.id} &nbsp;&nbsp; <span
		id="t">${ocrOrder.createDate}</span></label>
	<br />
	<label class="control-label">出发地：${ocrOrder.departurePlace} |
		目的地：${ocrOrder.destination} | 出发时间：<fmt:formatDate
			value="${ocrOrder.realDepartureDate}" pattern="yyyy-MM-dd HH:mm:ss" />
		| <c:if test="${ocrOrder.status==4}">
	预计送达时间:<fmt:formatDate value="${ocrOrder.planArrivalDate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</c:if> <c:if test="${ocrOrder.status!=4}">
	送达时间:<fmt:formatDate value="${ocrOrder.realArrivalDate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</c:if>
	</label>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>公司ID</th> -->
				<th>司机姓名</th>
				<th>工号</th>
				<th>电话</th>
				<th>车辆品牌</th>
				<th>车牌号</th>
				<th>本次任务油耗</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${ocrOrder.driver.realName}</td>
				<td>${ocrOrder.driver.jobNumber}</td>
				<td>${ocrOrder.driver.phone}</td>
				<td>${ocrOrder.vehicle.brandModels}</td>
				<td>${ocrOrder.vehicle.licensePlateNumber}</td>
				<td><c:if test="${ocrOrder.status==4}">
				统计中
				</c:if> <c:if test="${ocrOrder.status!=4}">
				<c:if test="${ocrOrder.orderOil lt 0}">
				0
				</c:if>
				<c:if test="${ocrOrder.orderOil gt 0}">
				${ocrOrder.orderOil}
				</c:if>
				</c:if></td>
			</tr>
		</tbody>
	</table>
	<div><font style="font-weight:bold;" >任务路径</font></div>
	<div id="container" style="margin:10px;width:1500px; height:745px;float:left">
	</div>
	<c:if test="${ocrOrder.status!=4}">
		<div style="margin:50px;height:7px;float:right">
		<input type="button" class="button" class = "stop" value="行驶回放" id="start"/>
	</c:if>
	
</body>
</html>