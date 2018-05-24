<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonParser"%>
<%@page
	import="com.springmvc.controller.IssueFilterController.*, java.util.*"%>

<%@include file="/WEB-INF/views/layout.jsp" %>
<body>
		<div id="dvData">

			<table id="example" border="1">
				<thead align="center">
					<tr>
						<th>Project Name</th>
						<th>Issue-Id</th>
						<th>Summary</th>
						<th>Issue Type</th>
						<th>Assignee Name</th>
						<th>Assignee emailAddress</th>
						<th>OriginalEstimate</th>
						<th>RemainingEstimate</th>
						<th>TimeSpent</th>
						<th>statusName</th>

					</tr>
				</thead>				
				<%
				    String exportToExcel = request.getParameter("exportToExcel");
				    if (exportToExcel != null && exportToExcel.toString().equalsIgnoreCase("YES")) {
				        response.setContentType("application/vnd.ms-excel");
				        response.setHeader("Content-Disposition", "inline; filename=" + "excel.xls");

				    }
				%>
				<c:if test="${not empty result}">
				<c:forEach items="${result}" var="result">
					<tr>
							<td align="center">${result.projectName}</td>
							<td align="center">${result.issueId}</td>
							<td align="center">${result.summary}</td>
							<td align="center">${result.issueType}</td>
							<td align="center">${result.assignee}</td>
                            <td align="center">${result.assigneeEmailAdd}</td>
                            <td align="center">${result.originalEstimate}</td>
                            <td align="center">${result.remainingEstimate}</td>
                            <td align="center">${result.timeSpent}</td>
							<td align="center">${result.statusName}</td>							
						</tr>						
				</c:forEach>
				</c:if>
				<tbody>
				</tbody>
	</table>
</div>
		<div>
			<input type="button" id="btnExport"
				value=" Export Table data into Excel " />
		</div>
</body>
<script>
	$("#btnExport").click(function(e) {
		var a = document.createElement('a');
		var data_type = 'data:application/vnd.ms-excel';
		var table_div = document.getElementById('dvData');
/* 		var table_html = table_div.outerHTML.replace(/ /g, '%20');
 		a.href = data_type + ', ' + table_html;*/
 		a.href = data_type +','+table_div.outerHTML.replace(/ /g, '%20');

		a.download = 'download.xlsx';
		a.click();
		e.preventDefault();
	});
	$(document).ready(function() {
		$('#example').DataTable({

			paging: false,
		    searching: false
			"lengthMenu": [[10, 25, 50,100,500, -1], [10, 25, 50,100,500, "All"]]
 	});
	});
</script>
</html>