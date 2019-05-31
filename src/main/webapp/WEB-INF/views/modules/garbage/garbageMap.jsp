<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>垃圾桶地图</title>
<meta name="decorator" content="default" />
<style type="text/css">
#container{float:none;position:absolute;left:0;right:0;top:0;bottom:0;margin:0!important;}
.progressbar{display: inline-block;border-radius: 3px;transform:rotate(-90deg);-ms-transform:rotate(-90deg);-moz-transform:rotate(-90deg);-webkit-transform:rotate(-90deg);-o-transform:rotate(-90deg);}
.percentCount{position: absolute;margin-top: 4px;margin-left: -2px;transform:rotate(90deg);-ms-transform:rotate(90deg);-moz-transform:rotate(90deg);-webkit-transform:rotate(90deg);-o-transform:rotate(90deg);}
.garbageNum{position: relative;top: 6px;}
.amap-info-content{font-size:16px;}
</style>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.2&key=96de376f610face350d9e4288b051ede&plugin=AMap.Autocomplete,AMap.Geocoder"></script>
<script type="text/javascript">
		var map;
		var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(10,-15)});
		var markers = [];
		var iconWarn = new AMap.Icon({
			image : "/static/garbageWarn.png",
			size: new AMap.Size(40, 55),
			imageOffset : new AMap.Pixel(0,10)
		});
		var iconNormal = new AMap.Icon({
			image : "/static/garbageNormal.png",
			size: new AMap.Size(40, 55),
			imageOffset : new AMap.Pixel(0,10)
		});
		var toHeight = $(window).height()-10;
		var toWidth = $(window).width()-5;
		
		$(document).ready(
			function() {
				$("#container").css({float:"left",margin:"5px",width:toWidth,height:toHeight});
				map = new AMap.Map('container');
				map.setZoom(10);
				//map.setCenter(['104.132205','30.600601']);
				var allGbInfo = eval(${allGbInfo});
				var iconPath;
				for (var i = 0; i < allGbInfo.length; i++) {
					if(allGbInfo[i].monitorState == '预警'){
						iconPath = iconWarn;
					}else{
						iconPath = iconNormal;
					}
					var marker = {
						icon:iconPath,
						position:[allGbInfo[i].longitude,allGbInfo[i].latitude],
						dtuId:allGbInfo[i].dtuId,
						address:allGbInfo[i].position,
						gbMany:allGbInfo[i].garbageMany
					};
					markers.push(marker);
				};
				
				for(var i = 0; i < markers.length; i++){
					marker = new AMap.Marker({ //加点
						map : map,
						position : [markers[i].position[0], markers[i].position[1]],
						icon : markers[i].icon,
						autoRotation: true,
						zoom : 13
					});
					
					var info = [];
					info.push("<div><b>ID:</b>  "+markers[i].dtuId);
					info.push("<b>地址:</b>  "+markers[i].address);
					var Color = '';
					if(markers[i].gbMany>80){
						info.push("<b class='garbageNum'>垃圾量:</b>  <span id=\"progressbar\" style=\"color:red\"></span></div>");
						Color = 'red';
					}else{
						info.push("<b class='garbageNum'>垃圾量:</b>  <span id=\"progressbar\" style=\"color:blue\"></span></div>");
						 Color = 'green';
					} 
					
					var num = markers[i].gbMany;
					
					console.log($('#progressbar').length)
					map.add(marker);
			
					marker.content = info.join("<br/>");
					marker.on('click', function(e){
						markerClick(e,num,Color)
					});
				};
				
				map.setFitView();
				
				function markerClick(e,num,Color) {
					infoWindow.setContent(e.target.content);
					infoWindow.open(map, e.target.getPosition());
					console.log($('#progressbar').length)
					
					//生成小柱
					$('#progressbar').LineProgressbar({
						percentage: num	,
						fillBackgroundColor: Color,//颜色
						height: '4px',//高度
						width:'16px',//宽度
						radius: '2px'//圆角
					}); 
				};
				
			})
			'use strict';
				$.fn.LineProgressbar = function(options){
					var options = $.extend({
						percentage : null,
						ShowProgressCount: true,
						duration: 1000,
						// Styling Options
						fillBackgroundColor: null,
						backgroundColor: '#ddd',
						radius: '3px',
						height: '10px',
						width: '100%'
					},options);

					return this.each(function(index, el) {
						// Markup
						$(el).html('<div class="progressbar"><div class="proggress"></div><div class="percentCount"></div></div>');
						var progressFill = $(el).find('.proggress');
						var progressBar= $(el).find('.progressbar');
						progressFill.css({
							backgroundColor : options.fillBackgroundColor,
							height : options.height,
							borderRadius: options.radius
						});
						progressBar.css({
							width : options.width,
							backgroundColor : options.backgroundColor,
							borderRadius: options.radius
						});
						// Progressing
						progressFill.animate(
						{
							width: options.percentage + "%"
						},
						{	
							step: function(x) {
								if(options.ShowProgressCount){
									$(el).find(".percentCount").text(Math.round(x));
								}
							},
							duration: options.duration
						});
					});
				}
	</script>
	<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
	<div id="container"></div>
</body>
</html>