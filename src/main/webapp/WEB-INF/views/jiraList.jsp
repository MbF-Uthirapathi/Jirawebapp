<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="User List" />
	<c:param name="body">
		<div class="container" role="main">
			<p>
				<em>${message}</em>
			</p>
			<table  id="example" class="table">
				<thead>
					<tr>
						<!-- <th>JIRA LoginName</th> -->
						<th>Email-Id</th>
						<!-- <th>JIRA-Url</th> -->
						<th>ProjectName</th>
						<!-- <th>UserName</th> -->
						<th>AssigneeName</th>
						<th>Action</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}">
						<tr>
							<%-- <td>${user.jiraUsername}</td> --%>
							<td>${user.email}</td>
<%-- 							<td>${user.url}</td>
 --%>							<td>${user.projectName}</td>
<%-- 							<td>${user.employee.firstname}</td> 
 --%>							<td>${user.assigneeName}</td>
							
							<td><a
								href="${pageContext.request.contextPath}/jiraEdit/${user.jiraId}">Edit</a>
							</td>
							<td><a
								href="${pageContext.request.contextPath}/jiraDelete/${user.jiraId}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- /container -->
	</c:param>
</c:import>