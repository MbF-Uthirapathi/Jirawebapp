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
				action="${pageContext.request.contextPath}/edit/${user.userId}">
				
				<div class="form-group">
					<label for="firstname">First name:</label>
					<form:input path="firstname" class="form-control" />
					<form:errors path="firstname" cssStyle="color: red;" />
				</div>
				<div class="form-group">
					<label for="lastname">Last name:</label>
					<form:input path="lastname" class="form-control" />
					<form:errors path="lastname" cssStyle="color: red;" />
				</div>
				<div class="form-group">
					<label for="designation">Designation:</label>
					<form:input path="designation" class="form-control" />
					<form:errors path="designation" cssStyle="color: red;" />
				</div>
				<div>
				<label for="teamDetails.teamId">TeamName:</label> 
				<br>
				<select id="teamDetails.teamId" name="teamDetails.teamId">

            	<c:forEach var="CustomerList" items="${userList}">

                <option value="${CustomerList.teamId}">${CustomerList.teamName}</option>

            </c:forEach>

        </select>
        </div>
				<button type="submit" class="btn btn-default">Update</button>
			</form:form>
		</div>
	</c:param>
</c:import>