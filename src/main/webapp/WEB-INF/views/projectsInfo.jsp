<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.sql.*"%>
<%
	ResultSet resultset = null;
%>
<%@page
	import="com.springmvc.controller.ProjectController.*, java.util.*"%>

<c:import url="/WEB-INF/views/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="New User" />
	<c:param name="body">
		<div class="container" role="main">

			<form:form method="GET"
				action="${pageContext.request.contextPath}/projectsInfo"
				class="form">
				<div>
					<input id="date" type="date" name="date" style="width: 15%">
				</div>
				<br>
				<input id="submit" type="submit" name="Submit">
				<input id="reset" type="reset" name="Reset">
			</form:form>
			<br><br>
			<c:if test="${not empty projectInfoList}">
				<div id="info_page">
					<h4>Projects Info</h4>
					<style>
						table, th, td{
    						border: 1px solid black;
						}
					</style>
					<table id="info" class="example table">
						<thead>
							<tr>
								<th>ProjectName</th>
								<th>ProjectKey</th>
								<th>Sprint-Id</th>
								<th>SprintName</th>
								<th>ToDo</th>
								<th>New</th>
								<th>Approve</th>
								<th>InProgress</th>
								<th>ReOpened</th>
								<th>Done</th>
								<th>Closed</th>
								<th>Week</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="project" items="${projectInfoList}">
								<tr>
									<td>${project.projectName}</td>
									<td>${project.projectKey}</td>
									<td>${project.sprintId}</td>
									<td>${project.sprintName}</td>
									<td>${project.toDoCount}</td>
									<td>${project.newCount}</td>
									<td>${project.approveCount}</td>
									<td>${project.inProgressCount}</td>
									<td>${project.reOpenCount}</td>
									<td>${project.doneCount}</td>
									<td>${project.closedCount}</td>
									<td>${project.weekOfSprint}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</c:param>
</c:import>