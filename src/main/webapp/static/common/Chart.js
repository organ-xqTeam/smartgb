// 静态 加载数据格式
//var linecontent = [ '石家庄市1', '张家口市', '承德市', '秦皇岛市', '唐山市', '廊坊市', '保定市', '沧州市', '衡水市', '邢台市', '邯郸市', '定州市', '辛集市' ];
//var linecontent1 = [ 2800, 1700, 1200, 1000, 900, 600, 400, 500, 550, 480, 320, 230, 380 ];
//var linecontentcolor = [
//	'#ff2600',
//	'#ffc000',
//	'#00ad4e',
//	'#0073c2',
//	'#165868',
//	'#e76f00',
//	'#316194',
//	'#723761',
//	'#00b2f1',
//	'#4d6022',
//	'#4b83bf',
//	'#f9c813',
//	'#0176c0'
//];
//var totalnum = '20.000.00';


//动态加载  注释掉上面

var linecontent,linecontent1,linecontentcolor,totalnum;
linecontent=[];
linecontent1=[];
linecontentcolor=[];
totalnum=[];
//加总数
function addTotalNum(){
	console.log(6666)
	var $div=$(`
		<div style="background-color:rgb(243, 245, 246);">
			<div style="color:green;text-align:center;">总油耗</div>
			<div style="font-size:20px;">${totalnum}</div>
		</div>
	`)
	$div.css({
	    "position": "absolute",
	    "white-space": "nowrap",
	    "z-index": "9999999",
	    "border-radius": "4px",
	    "padding": "5px",
	    "left": "50%",
	    "top": "50%",
	    "transform":"translate(-50%,-50%)"
	})
	$("#main1").append($div)
}
$(function() {
//	$.ajax({
//		type : "get",
//		async : true, // 异步请求
//		url : "listData" ,
//		data : {
//			"startDate" : "2018-09-01",
//			"endDate" : "2018-09-06"
//		},
//		dataType : "json",
//		success : function(result) {
//			console.log(result)
//			if (result.status) {
////				alert("请求成功!");
//				linecontent = result.linecontent;
//				linecontent1 = result.linecontent1;
//				linecontentcolor = result.linecontentcolor;
//				totalnum = result.totalnum;
//
//				lineA_(linecontent, linecontent1, linecontentcolor, totalnum); // 饼状图
//				lineB_(linecontent, linecontent1, linecontentcolor); // 柱状图
//				
//				addTotalNum()
//			}
//
//		},
//		error : function(errorMsg) {
//			// 请求失败时执行该函数
////			alert("图表请求数据失败!");
////			myChart.hideLoading();
//		}
//	});
	//查询点击
	$("#btnSubmit").click(function(){
		event.preventDefault(); 
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
//		console.log(startDate);
//		console.log(endDate);
		$.ajax({
			type : "get",
			async : true, // 异步请求
			url : "listData" ,
			data : {
				"startDate" : startDate,
				"endDate" : endDate
			},
			dataType : "json",
			success : function(result) {
				console.log(result)
				if (result.status) {
//					alert("请求成功!");
					linecontent = result.linecontent;
					linecontent1 = result.linecontent1;
					linecontentcolor = result.linecontentcolor;
					totalnum = result.totalnum;

					lineA_(linecontent, linecontent1, linecontentcolor, totalnum); // 饼状图
					lineB_(linecontent, linecontent1, linecontentcolor); // 柱状图
					
					addTotalNum()
				}

			},
			error : function(errorMsg) {
				// 请求失败时执行该函数
//				alert("图表请求数据失败!");
//				myChart.hideLoading();
			}
		});
	})

})

var lineA_ = function(linecontent, linecontent1, linecontentcolor,totalnum) {
	var myChart = echarts.init(document.getElementById('main1'));
	/**
	 * 图标所需数据
	 */
	
	var data = {
		id : 'echartPie',
		value : linecontent1,
		legend : linecontent,
		color : linecontentcolor,
//		tooltipShow : true, //设置悬浮提示显示              --默认显示true
//		hoverAnimation : true, //设置鼠标悬浮点击饼图动画效果  --默认开启动画true
		title : '饼图',
	}

	////////////////////////////////////////

	/**
	 * 数据处理
	 */
	var seriesData = []
	data.value.forEach(function(item, index) {
		seriesData.push({
			value : item,
			name : data.legend[index]
		})
	})
	////////////////////////////////////////

	var option = {
		backgroundColor : '#fff',
//		title : {    //标题
//			x : '2%',
//			y : '2%',
//			textStyle : {
//				fontWeight : 400,
//				fontSize : 16,
//				color : '#687284'
//			},
//			text : data.title || ''
//		},
//		tooltip: {
//	        show:true,
//	        trigger: 'item',
//	        triggerOn:"mousemove", 
//			formatter : function(params, ticket, callback) {
//				var num = totalnum;
//				var str = '<div style="text-align: center" id="toolTipBox"><p style="font-size:15px;margin:0px;color:green;">总油耗</p>'+
//					'<p style="font-size:20px;margin:0px">' + num + '</p></div>'
//				return str
//			},
//	         position: function (point, params, dom, rect, size) {
//	             var marginW = Math.ceil(size.contentSize[0]/2);
//	             var marginH = Math.ceil(size.contentSize[1]/2);
//	             dom.style.marginLeft='-' + marginW + 'px';
//	             dom.style.marginTop='-' + marginH + 'px';
//	      			 return ['50%', '50%'];
//	  			},
//	        alwaysShowContent:true,
//	        backgroundColor:'#f3f5f6',	//设置提示框的背景色
//	        textStyle:{
//	        	color:'#333'
//	        }
//	    },
		legend : {
			orient : 'horizontal',
			top : 16,
			icon : 'circle',
			selectedMode : false,
			itemWidth : 6,
			itemHeight : 6,
			itemGap : 6,
			borderRadius : 6,
			selectedMode:false,
			data : data.legend
		},
		series : [ 

			{
			type : 'pie',
			// clickable:false,
			// selectedMode: 'single',//单点击设置
			hoverAnimation : data.hoverAnimation === false ? false : true,
			radius : [ '40%', '67%' ],
			color : data.color,
			label : {
				normal : {
					position : 'inner',
					// formatter: '{d}%',
					formatter : function(param) {
						if (!param.percent) return ''
						var f = Math.round(param.percent * 10) / 10;
						var s = f.toString();
						var rs = s.indexOf('.');
						if (rs < 0) {
							rs = s.length;
							s += '.';
						}
						while (s.length <= rs + 1) {
							s += '0';
						}
						return s + '%';
					},
					textStyle : {
						color : '#fff',
						fontSize : 12
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : seriesData
		} ]
	};
	myChart.setOption(option, true);
}
var lineB_ = function(linecontent, linecontent1, linecontentcolor) {
	var myChart = echarts.init(document.getElementById('main'));
	var colorList = linecontentcolor;
	option = {
		color : colorList,
		legend : {
			orient : 'vertical',
			y : 'center',
			right : '2%',
			itemWidth : 12,
			itemHeight : 12,
			data : linecontent,
			textStyle : { //图例文字的样式
				fontSize : 14
			}
		},
		tooltip : {
			show : true,
			trigger : 'item',
			formatter : "{c}"
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
			}
		},
		grid : {
			left : '2%',
			right : '10%',
			bottom : '3%',
			height : '90%',
			width : '80%',
			containLabel : true
		},
		xAxis : [
			{
				type : 'category',
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#28316d', //左边线的颜色
						width : '1' //坐标线的宽度
					}
				},
				axisLabel : {
					interval : 0,
					rotate : 40,
					show : true,
					splitNumber : 15,
					textStyle : {
						//fontFamily: "微软雅黑",
						fontSize : 10,
					},
				},
				data : linecontent,
				axisTick : {
					alignWithLabel : true
				}
			}
		],
		yAxis : [
			{
				type : 'value',
				name : '',
				splitLine : { //分割线
					show : true,
					// color:"#fff",
					lineStyle : {
						color : '#28316d'
					}
				},
				axisLabel : {
					interval : 0,
					rotate : 0,
					show : true,
					splitNumber : 30,
					// color:"#fff",
					textStyle : {
						//fontFamily: "微软雅黑",
						fontSize : 12,
					}
				},
			},

		],
		series : [
			{
				name : '',
				type : 'bar',
				barWidth : 30, //柱图宽度
				data : linecontent1,
				itemStyle : {
					normal : {
						color : function(params) {
							// build a color map as your need.
							var colorList = linecontentcolor;
							return colorList[params.dataIndex]
						},
						label : {
							show : false,
							position : 'top',
							formatter : '{c}%'
						}
					}
				}
			}
		]
	};
	myChart.setOption(option, true);
}