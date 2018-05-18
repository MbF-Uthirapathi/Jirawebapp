<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.sql.*"%>

<c:import url="/WEB-INF/views/loginlayout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="New User" />
	<c:param name="body">
		<div class="container" role="main">
			
			<form:form method="POST" commandName="team"
				action="${pageContext.request.contextPath}/team" class="form">

				<div class="form-group">
					<label for="teamName">Team Name:</label>
					<form:input path="teamName" class="form-control" style="width: 25%" />
					<form:errors path="teamName" cssStyle="color: red;" />
				</div>
				<div class="form-group">
					<label for="teamLoginName">Team LoginName:</label>
					<form:input path="teamLoginName" class="form-control" style="width: 25%" />
					<form:errors path="teamLoginName" cssStyle="color: red;" />
				</div>
				<div class="form-group">
					<label for="teamLoginPwd">Password:</label>
					<form:input type="password" path="teamLoginPwd" class="form-control" style="width: 25%" />
					<form:errors path="teamLoginPwd" cssStyle="color: red;" />
				</div>
			<button id="create" type="submit" class="btn btn-default">Create</button>
			</form:form>
		</div>
	</c:param>
</c:import>