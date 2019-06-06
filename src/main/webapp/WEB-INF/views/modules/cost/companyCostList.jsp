<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账务管理</title>
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
				$("#status").val(null).trigger("change");
				$("input[name='beginDepartureDate']").val('');
				$("input[name='endDepartureDate']").val('');
				
			}
			function sort(order){
				if($('#orderBy').val()==order)
				$('#orderBy').val(order+' DESC');
				else
				$('#orderBy').val(order);

				$('#searchForm').submit();
			}
			function sortyearandmonth(){
				if($('#orderBy').val()=='year , month')
				$('#orderBy').val('year DESC ,month DESC');
				else
				$('#orderBy').val('year , month');

				$('#searchForm').submit();
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:;">账务列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ocrCostDetailedVO" action="${ctx}/cost/ocrCost/companyCostList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<li><label>清账标识：</label>
				<form:select path="status" placeholder="请选择订单状态" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('cost_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>时间：</label>
				<input name="beginDepartureDate" placeholder="请选择开始时间" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${ocrCostDetailedVO.beginDepartureDate}"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/> - 
				<input name="endDepartureDate" placeholder="请选择截止时间" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${ocrCostDetailedVO.endDepartureDate}"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="" class="btn btn-primary" value="重置" type="button" onclick="javascript:resetBtn();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<h3>${companyName}</h3>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><a href="javascript:void(0)" onclick="sortyearandmonth()">时间</a></th>
				<th><a href="javascript:void(0)" onclick="sort('orderCount')">订单数量</a></th>
				<th><a href="javascript:void(0)" onclick="sort('totalAmount')">订单总金额（元）</a></th>
                <th><a href="javascript:void(0)" onclick="sort('unpaid')">未结清金额（元）</a></th>
                <th><a href="javascript:void(0)" onclick="sort('paidOnline')">在线支付（元）</a></th>
                <th><a href="javascript:void(0)" onclick="sort('unpaidOnline')">在线未付款（元）</a></th>
                <th><a href="javascript:void(0)" onclick="sort('paidUnified')">统一结算额（元）</a></th>				<th>清账标识</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrCostDetailedVO">
			<tr>
				<td>
					${ocrCostDetailedVO.year}-${ocrCostDetailedVO.month}
				</td>
				<td>
					${ocrCostDetailedVO.orderCount}
				</td>
				<td>
					${ocrCostDetailedVO.totalAmount}
				</td>
                <td>
                    ${ocrCostDetailedVO.unpaid}
                </td>
                 <td>
                    ${ocrCostDetailedVO.paidOnline}
                </td>
                <td>
                    ${ocrCostDetailedVO.unpaidOnline}
                </td>
                <td>
                    ${ocrCostDetailedVO.paidUnified}
                </td>
			
				<td>
					<c:if test="${ocrCostDetailedVO.unpaid<=0}">已清账</c:if>
					<c:if test="${ocrCostDetailedVO.unpaid>0}">未清账</c:if>
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>