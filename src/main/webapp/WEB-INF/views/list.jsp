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
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>LastName</th>
						<th>Designation</th>
						<th>Team</th>
						<th>Action</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}">
						<tr>
							<td>${user.firstname}</td>
							<td>${user.lastname}</td>
							<td>${user.designation}</td>
							<td>${user.teamDetails.teamName}</td>
							<td><a
								href="${pageContext.request.contextPath}/edit/${user.userId}">Edit</a>
							</td>
							<td><a
								href="${pageContext.request.contextPath}/delete/${user.userId}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- /container -->
	</c:param>
</c:import>