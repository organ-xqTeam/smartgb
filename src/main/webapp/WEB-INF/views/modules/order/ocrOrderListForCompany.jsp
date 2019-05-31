<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
        function resetBtn() {
	        $("#status").val("all").trigger("change");
			$("input[name='beginDepartureDate']").val('');
			$("input[name='endDepartureDate']").val('');
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:;">订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrOrderVO" action="${ctx}/order/ocrOrder/listForCompany" method="post" class="breadcrumb form-search">
		<input id="companyName" name="companyName" type="hidden" value="${companyName}"/>
		<input id="companyId" name="companyId" type="hidden" value="${ocrOrderVO.companyId}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>状态：</label>
				<form:select path="status" placeholder="请选择订单状态" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>出发时间：</label>
				<input name="beginDepartureDate" placeholder="请选择开始时间" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ocrOrderVO.beginDepartureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endDepartureDate" placeholder="请选择截止时间" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ocrOrderVO.endDepartureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="" class="btn btn-primary" value="重置" type="button" onclick="javascript:resetBtn();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<h3>${companyName}</h3>
		<c:forEach items="${page.list}" var="ocrOrder">
		<label class="control-label">行程单号：${ocrOrder.id}</label>&nbsp;&nbsp;
		<label class="control-label">创建时间：<fmt:formatDate value="${ocrOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></label><br>
		<label class="control-label">出发地：${ocrOrder.departurePlace}</label>&nbsp;|&nbsp;
		<label class="control-label">目的地：${ocrOrder.destination}</label>&nbsp;|&nbsp;
		<label class="control-label">出发时间：<fmt:formatDate value="${ocrOrder.departureDate}" pattern="yyyy-MM-dd HH:mm:ss"/></label>&nbsp;|&nbsp;
		<label class="control-label">预计到达时间：<fmt:formatDate value="${ocrOrder.planArrivalDate}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>公司ID</th> -->
				<th>车辆类型</th>
				<th>支付方式</th>
				<th>费用</th>
				<th>申请人</th>
				<th>电话</th>
				<th>工号</th>
				<th>同行人数</th>
				<th>出行目的</th>
				<th>状态</th>
			</tr>
		</thead>
				<tbody>
		
			<tr>
				<!-- <td><a href="${ctx}/order/ocrOrder/form?id=${ocrOrder.id}">
					${ocrOrder.companyId}
				</a></td>
				 -->
				<td>
					${fns:getDictLabel(ocrOrder.vehicleType, 'vehicle_type', '')}
				</td>
			
				<td>
					${fns:getDictLabel(ocrOrder.paymentMode, 'payment_mode', '')}
				</td>
				<td>
					${ocrOrder.cost}
				</td>
				<td>
					${ocrOrder.user.realName}
				</td>
					<td>
					${ocrOrder.phone}
				</td>
					<td>
					${ocrOrder.user.jobNumber}
				</td>
				<td>
					${ocrOrder.peopleNumber}
				</td>
				<td>
					${ocrOrder.appearPurpose}
				</td>
				<td>
				    <c:if test="${ocrOrder.status==1||ocrOrder.status==2}">
                                                已通过
                    </c:if>
                     <c:if test="${ocrOrder.status==3&&ocrOrder.taskStatus==1}">
                                                已通过
                    </c:if>
                     <c:if test="${ocrOrder.status==3&&ocrOrder.taskStatus==2}">
                                           待出发
                    </c:if>
                      <c:if test="${ocrOrder.status==9}">
                                           已取消
                    </c:if>
                     <c:if test="${ocrOrder.status!=1&&ocrOrder.status!=2&&ocrOrder.status!=3&&ocrOrder.status!=9}">
					${fns:getDictLabel(ocrOrder.status, 'order_status', '')}
                    </c:if>
				</td>
			
			</tr>
		</tbody>
	</table>
		<table id="contentTable2" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
				<th>司机姓名</th>
				<th>工号</th>
				<th>电话</th>
				<th>车辆品牌</th>
				<th>车牌号</th>
				<th>行驶数据</th>
			</tr>
		</thead>
				<tbody>
		
			<tr>
			
				<td>
				 <c:if test="${ocrOrder.status!=3}">
					${ocrOrder.driver.realName}
				</c:if>
				 <c:if test="${ocrOrder.status==3&&ocrOrder.taskStatus==2}">
                    ${ocrOrder.driver.realName}
                 </c:if>
				</td>
				<td>
				     <c:if test="${ocrOrder.status!=3}">
                    ${ocrOrder.driver.jobNumber}
                </c:if>
                 <c:if test="${ocrOrder.status==3&&ocrOrder.taskStatus==2}">
                    ${ocrOrder.driver.jobNumber}
                 </c:if>
				</td>
				<td>
				     <c:if test="${ocrOrder.status!=3}">
                    ${ocrOrder.driver.phone}
                </c:if>
                 <c:if test="${ocrOrder.status==3&&ocrOrder.taskStatus==2}">
                    ${ocrOrder.driver.phone}
                 </c:if>
				</td>
				<td>
				     <c:if test="${ocrOrder.status!=3}">
                    ${ocrOrder.vehicle.brandModels}
                </c:if>
                 <c:if test="${ocrOrder.status==3&&ocrOrder.taskStatus==2}">
                    ${ocrOrder.vehicle.brandModels}
                 </c:if>
				</td>
				<td>
				     <c:if test="${ocrOrder.status!=3}">
                    ${ocrOrder.vehicle.licensePlateNumber}
                </c:if>
                 <c:if test="${ocrOrder.status==3&&ocrOrder.taskStatus==2}">
                    ${ocrOrder.vehicle.licensePlateNumber}
                 </c:if>
				</td>
				<td>
					<c:choose>
					<c:when test="${ocrOrder.status==4||ocrOrder.status==5||ocrOrder.status==6}">
					    <a href="${ctx}/order/ocrOrder/orderDetailMap?id=${ocrOrder.id}">查看</a>
					</c:when>
					<c:otherwise>
						<a onclick="javascript:messageNoData();"> 查看 </a>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
		
		</table>
	
	<hr>
		</c:forEach>
	<div class="pagination">${page}</div>
</body>
</html>