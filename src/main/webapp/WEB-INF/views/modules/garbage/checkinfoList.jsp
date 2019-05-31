<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>垃圾桶管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			setInterval(function(){
				var dtuIds = new Array();
				$('#contentTable tbody tr').each(function(){
					dtuIds.push($(this).attr("tid"));
				});
				if(dtuIds.length>0){
					$.ajax({
						type: "POST",
						url: "${ctx}/garbagebin/checkinfo/listByIds",
						contentType: "application/json; charset=utf-8",
						data: JSON.stringify(dtuIds),
						dataType: "json",
						success: function (gbInfos) {
							if (gbInfos.length > 0) {
								$.each(gbInfos,function(index){
									$('#contentTable tbody tr').each(function(){
										if(gbInfos[index].dtuId == $(this).attr("tid")){
											$(this).find('td').eq(0).html(gbInfos[index].monitorState);
											$(this).find('td').eq(1).html(gbInfos[index].dtuId);
											$(this).find('td').eq(2).html(gbInfos[index].garbageMany+"%");
											$(this).find('td').eq(3).html(gbInfos[index].position);
											$(this).find('td').eq(4).html(gbInfos[index].connectState);
											$(this).find('td').eq(5).html(gbInfos[index].gpsState);
										}
									});
								});
							}
						},
						error: function (message) {
							$("#request-process-patent").html("提交数据失败！");
						}
					});
				}
			},10000);
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		function resetBtn() {
			$("#dtuId").val('');
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/garbagebin/checkinfo/">垃圾桶列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="garbageBinInfo" action="${ctx}/garbagebin/checkinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="dtuId" placeholder="请输入编号" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>监测状态</th>
				<th>编号</th>
				<th>垃圾量</th>
				<th>位置</th>
				<th>连接状态</th>
				<th>GPS状态</th>
				<%-- <shiro:hasPermission name="gb:checkinfo:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbInfo">
			<tr tid="${gbInfo.dtuId}">
				<td>
					${gbInfo.monitorState}
				</td>
				<td>
					${gbInfo.dtuId}
				</td>
				<td>
					${gbInfo.garbageMany}%
				</td>
				<td>
					${gbInfo.position}
				</td>
				<td>
					${gbInfo.connectState}
				</td>
				<td>
					${gbInfo.gpsState}
				</td>
				<%-- <shiro:hasPermission name="gb:checkinfo:edit"><td>
				<a href="${ctx}/garbagebin/checkinfo/form?id=${gbInfo.dtuId}">编辑</a>
					<a href="${ctx}/garbagebin/checkinfo/delete?id=${gbInfo.dtuId}" onclick="return confirmx('确认要删除该垃圾桶吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>