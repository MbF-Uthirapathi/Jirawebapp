<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.sql.*"%>

<c:import url="/WEB-INF/views/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="Edit User" />
	<c:param name="body">
		<div class="container" role="main">
			<h1>Edit User</h1>
			<form:form method="POST" commandName="user"
				action="${pageContext.request.contextPath}/jiraEdit/${user.jiraId}">

				<div class="form-group">
					<label for="projectName">ProjectName:</label>
					<form:input path="projectName" class="form-control"
						style="width: 25%" />
					<form:errors path="projectName" cssStyle="color: red;" />
				</div>
				<%-- <div class="form-group">
					<label for="url">JIRA-Url:</label>
					<form:input path="url" class="form-control" style="width: 25%" />
					<form:errors path="url" cssStyle="color: red;" />
				</div> --%>
				
				<div class="form-group">
					<label for="email">Email-Id:</label>
					<form:input path="email" class="form-control" style="width: 25%" />
					<form:errors path="email" cssStyle="color: red;" />
				</div>
				<%-- <div class="form-group">
					<label for="password">Password:</label>
					<form:input type="password" path="password" class="form-control"
						style="width: 25%" />
					<form:errors path="password" cssStyle="color: red;" />
				</div> --%>
				<div class="form-group">
					<label for="assigneeName">AssigneeName:</label>
					<form:input path="assigneeName" class="form-control"
						style="width: 25%" />
					<form:errors path="assigneeName" cssStyle="color: red;" />
				</div>

			<%-- 	<div class="form-group">
					<label for="jiraProjectName">Jira ProjectName:</label>
					<form:input path="jiraProjectName" class="form-control"
						style="width: 25%" />
					<form:errors path="jiraProjectName" cssStyle="color: red;" />
				</div> --%>
				<%-- <div>
					<label for="employee.userId">UserName:</label> <br> <select
						id="employee.userId" name="employee.userId">

						<c:forEach var="CustomerList" items="${userList}">

							<option value="${CustomerList.userId}">${CustomerList.firstname}</option>

						</c:forEach>

					</select>
				</div> --%>

				<br>
				<br>
				<button type="submit" class="btn btn-default">Update</button>
				<button type="reset" class="btn btn-default">Reset</button>
				<button type="submit" class="btn btn-default">
					<a href="/spring-mvc-jpa-example/project">Cancel</a>
			</form:form>
		</div>
	</c:param>
</c:import>