<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆保险年检</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/car/ocrVehicle/IAI">车辆列表</a></li>
		<!-- <shiro:hasPermission name="car:ocrVehicle:edit"><li><a href="${ctx}/car/ocrVehicle/form">车辆添加</a></li></shiro:hasPermission> -->
	</ul>
	<form:form id="searchForm" modelAttribute="ocrVehicle" action="${ctx}/car/ocrVehicle/IAI" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<!-- <li><label>车辆类型：</label>
				<form:select path="vehicleType" placeholder="请选择车辆类型" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vehicle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> -->
			<li><label>车牌号：</label>
				<form:input path="licensePlateNumber" placeholder="请输入车牌号"  htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<!-- <li><label>品牌车型：</label>
				<form:input path="brandModels" placeholder="请输入品牌车型" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" placeholder="请选择车辆状态" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ocr_vegicle_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> -->
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
                <th>品牌车型</th>
                <th>车辆所在地</th>
                <th>下次保险日期</th>
                <th>下次年检日期</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ocrVehicle">
			<tr>
				<td>
					${ocrVehicle.licensePlateNumber}
				</td>
				<td>
					${ocrVehicle.brandModels}
				</td>
				<td>
                    ${ocrVehicle.area.name}
                </td>
				<td>
                   <fmt:formatDate value="${ocrVehicle.insuranceDate}" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                    <fmt:formatDate value="${ocrVehicle.annualInspectionDate}" pattern="yyyy-MM-dd"/>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>