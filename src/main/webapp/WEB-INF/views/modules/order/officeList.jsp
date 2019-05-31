<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单位列表</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, rootId = "${not empty office.id ? office.id : '0'}";
			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 5});
			//var str_text = document.getElementById("name");
			//if (str_text.value==""){
  				//str_text.value="请输入单位名称";
 			//}
			$("#btnReset").click(function(){
				$("#name").val("");
			});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('sys_office_type'))}, row.type)
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			//var str_text = document.getElementById("name");
			//if (str_text.value=="请输入单位名称"){
  			//	str_text.value="";
 			//}
			$("#searchForm").attr("action","${ctx}/order/ocrOrder/officeList");
			$("#searchForm").submit();
	    	return false;
	    }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/ocrOrder/officeList">单位列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="office" action="${ctx}/order/ocrOrder/officeList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>单位名称：</label><form:input id="name" path="name" value="${office.name}" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入单位名称" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="button" value="重置"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>单位名称</th><!-- <th>状态</th> -->
		<th>操作</th>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td>{{row.name}}</td>
			<!--<td>{{row.useable}}</td>-->
			<td>
				<a href="${ctx}/order/ocrOrder/list?companyId={{row.id}}&companyName={{row.name}}">查看</a>
				<!-- <a href="${ctx}/sys/office/delete?id={{row.id}}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a>
				<a href="${ctx}/sys/office/form?parent.id={{row.id}}">添加下级</a> -->
			</td>
		</tr>
	</script>
</body>
</html>