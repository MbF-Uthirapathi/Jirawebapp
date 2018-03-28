<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/views/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="New team" />
	<c:param name="body">
		<div class="container" role="main">
			<h4>Project Create</h4>
			<br>
			<form:form method="POST" commandName="user"
				action="${pageContext.request.contextPath}/project" class="form">

				<div class="form-group">
					<label for="projectName">ProjectName:</label>
					<form:input path="projectName" class="form-control"
						style="width: 25%" />
					<form:errors path="projectName" cssStyle="color: red;" />
				</div>
				<div class="form-group">
					<label for="url">JIRA-Url:</label>
					<form:input path="url" class="form-control" style="width: 25%" />
					<form:errors path="url" cssStyle="color: red;" />
				</div>

				<div class="form-group">
					<label for="email">Email-Id:</label>
					<form:input path="email" class="form-control" style="width: 25%" />
					<form:errors path="email" cssStyle="color: red;" />
				</div>
				<div class="form-group">
					<label for="password">Password:</label>
					<form:input type="password" path="password" class="form-control"
						style="width: 25%" />
					<form:errors path="password" cssStyle="color: red;" />
				</div>


				<div class="form-group">
					<label for="jiraProjectName">Jira ProjectName:</label>
					<form:input path="jiraProjectName" class="form-control"
						style="width: 25%" />
					<form:errors path="jiraProjectName" cssStyle="color: red;" />
				</div>
				<%-- <div>
					<label for="employee.userId">UserName:</label> <br> <select
						id="employee.userId" name="employee.userId">

						<c:forEach var="CustomerList" items="${userList}">

							<option value="${CustomerList.userId}">${CustomerList.firstname}</option>

						</c:forEach>

					</select>
				</div> --%>

				<br>


				<button type="submit" class="btn btn-default">Create</button>
			</form:form>
			<div id="demo_pag1">
							<h4>List of Projects</h4>

				<table id="example" class="table">
					<thead>
						<tr>
							<th>ProjectName</th>
							<th>JIRA-Url</th>
							<th>Email-Id</th>
							<th>JIRA ProjectName</th>
							<th>Action</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${userList}">
							<tr>
								<td>${user.projectName}</td>
								<td>${user.url}</td>
								<td>${user.email}</td>
								<td>${user.jiraProjectName}</td>
								<td><a
									href="${pageContext.request.contextPath}/projectEdit/${user.projectId}">Edit</a>
								</td>
								<td><a
									href="${pageContext.request.contextPath}/projectDelete/${user.projectId}">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:param>
</c:import>