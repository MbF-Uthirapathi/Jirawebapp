<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/layoutProject.jsp" charEncoding="UTF-8">
	<c:param name="title" value="User List" />
	<c:param name="body">
		<div class="container" role="main">
					<h4>List of Projects</h4>
			
			<p>
			</p>
			<table class="table">
				<thead>
					<tr>
						<th>ProjectName</th>
						<th>JIRA-Url</th>
						<th>Email-Id</th>
<!-- 						<th>Password</th>
 -->						<th>JIRA ProjectName</th>
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
<%-- 							<td>${user.password}</td>
 --%>							<td>${user.jiraProjectName}</td>
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
		<!-- /container -->
	</c:param>
</c:import>