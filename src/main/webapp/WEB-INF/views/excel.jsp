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
	import="com.springmvc.controller.JiraUserController.*, java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.3.js"></script>



  	<script
		src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css"
		href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"></link>
<style>
html, body {
	overflow-x: hidden;
}

.counterCell:before {
	content: counter(tableCount);
	counter-increment: tableCount;
}

body {
	padding-top: 100px;
}

footer {
	padding: 30px 0;
}

@media screen and (max-width: 767px) {
	.row-offcanvas {
		position: relative;
		-webkit-transition: all .25s ease-out;
		-o-transition: all .25s ease-out;
		transition: all .25s ease-out;
	}
	.row-offcanvas-right {
		right: 0;
	}
	.row-offcanvas-left {
		left: 0;
	}
	.row-offcanvas-right
		  .sidebar-offcanvas {
		right: -50%; /* 6 columns */
	}
	.row-offcanvas-left
		  .sidebar-offcanvas {
		left: -50%; /* 6 columns */
	}
	.row-offcanvas-right.active {
		right: 50%; /* 6 columns */
	}
	.row-offcanvas-left.active {
		left: 50%; /* 6 columns */
	}
	.sidebar-offcanvas {
		position: absolute;
		top: 0;
		width: 50%; /* 6 columns */
	}
}


</style>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">

	<div class="container">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

		</div>
		<div id="navbar" class="navbar-collapse collapse">

			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/project">Home</a></li>
								<li><a href="${pageContext.request.contextPath}/issueJsp">Issue</a></li>
				
								<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				
			</ul>
		</div>
	</div>
	</nav>
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
				<%
				    JsonParser parser = new JsonParser();
				    JsonElement tradeElement = parser.parse(request.getAttribute("jsonList").toString());
				    JsonObject fields = null;
				    JsonObject assignee = null;
				    JsonObject timetracking = null;
				    JsonObject worklog = null;
				    JsonObject status = null;
				    String emailAddress = null;
				    String displayName = null;
				    String remainingEstimate = null;
				    String originalEstimate = null;
				    String timeSpent = null;
				    String statusName = null;
				    String projectName = null;
				    String issueTypeName = null;
				    String key=null;
				%>

				<%
				    JsonArray array = tradeElement.getAsJsonArray();

				    for (int k = 0; k < array.size(); k++) {
				        if (array.size() != 0) {
				            JsonObject jsonObj = (JsonObject) array.get(k);

				            JsonArray city = (JsonArray) jsonObj.get("issues");

				            if (city.size() != 0) {

				                for (int i = 0; i < city.size(); i++) {

				                    JsonObject object2 = (JsonObject) city.get(i);
				                    key = (String) object2.get("key").getAsString();
				                    fields = (JsonObject) object2.get("fields");
				                    if (!(fields.get("assignee").isJsonNull())) {
				                        JsonObject object = (JsonObject) fields.get("assignee");
				                        displayName = (String) object.get("name").getAsString();
				                        emailAddress = (String) object.get("emailAddress").getAsString();

				                    } else {
				                        displayName = "null";
				                        emailAddress = "null";

				                    }
				                    if (!fields.get("project").isJsonNull()) {
				                        JsonObject object = (JsonObject) fields.get("project");
				                        projectName = (String) object.get("name").getAsString();

				                    } else {
				                        projectName = "null";

				                    }
				                    if (!fields.get("issuetype").isJsonNull()) {
				                        JsonObject object = (JsonObject) fields.get("issuetype");
				                        issueTypeName = (String) object.get("name").getAsString();

				                    } else {
				                        issueTypeName = "null";

				                    }
				                    if (!(fields.get("timetracking").isJsonNull())) {
				                        timetracking = (JsonObject) fields.get("timetracking");
				                        if (timetracking.has("remainingEstimate")) {
				                            if (remainingEstimate != null) {

				                                remainingEstimate = (String) timetracking.get("remainingEstimate")
				                                        .getAsString();
				                            } else {
				                                remainingEstimate = "null";
				                            }
				                        }
				                        if (timetracking.has("originalEstimate")) {
				                            if (originalEstimate != null) {
				                                originalEstimate = (String) timetracking.get("originalEstimate").getAsString();

				                            } else {
				                                originalEstimate = "null";
				                            }
				                        } else {
				                            originalEstimate = "null";
				                        }
				                        if (timetracking.has("timeSpent")) {
				                            if (timeSpent != null) {
				                                timeSpent = (String) timetracking.get("timeSpent").getAsString();

				                            } else {
				                                timeSpent = "null";
				                            }
				                        }
				                    } else {
				                        remainingEstimate = "null";
				                        originalEstimate = "null";
				                        timeSpent = "null";

				                    }

				                    worklog = (JsonObject) fields.get("worklog");
				                    if (fields.has("status")) {
				                        status = (JsonObject) fields.get("status");
				                        statusName = (String) status.get("name").getAsString();

				                    } else {
				                        break;

				                    }
				%>
					<tr>
							<td align="center"><%=projectName%></td>
							<td align="center"><%=issueTypeName%></td>

							<td align="center"><%=key%></td>
							<td align="center"><%=fields.get("summary").getAsString()%></td>
							<td align="center"><%=displayName%></td>
							<td align="center"><%=emailAddress%></td>
							<td align="center"><%=originalEstimate%></td>
							<td align="center"><%=remainingEstimate%></td>
							<td align="center"><%=timeSpent%></td>
							<td align="center"><%=statusName%></td>



						</tr>
				<%
				    }
				            } else {

				                continue;

				            }

				        } else {
				            break;
				        }
				    }
				%><tbody>
						


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
/* 			"pagingType" : "full_numbers"
 */
 "lengthMenu": [[10, 25, 50,100,500, -1], [10, 25, 50,100,500, "All"]]
 	});
	});
</script>
</html>