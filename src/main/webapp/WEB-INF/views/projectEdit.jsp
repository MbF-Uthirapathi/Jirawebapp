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
			<h4>Project Edit</h4>
			<form:form method="POST" commandName="user"
				action="${pageContext.request.contextPath}/projectEdit/${user.projectId}">

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
				<%-- <div class="form-group">
					<label for="password">Password:</label>
					<form:input type="password" path="password" class="form-control"
						style="width: 25%" />
					<form:errors path="password" cssStyle="color: red;" />
				</div>
				 --%>

				<div class="form-group">
					<label for="jiraProjectName">Jira ProjectName:</label>
					<form:input path="jiraProjectName" class="form-control"
						style="width: 25%" />
					<form:errors path="jiraProjectName" cssStyle="color: red;" />
				</div>
				
				<div class="form-group">
					<label for="projectStatus">Project Status:</label>
					<form:select path="projectStatus" class="form-control" style="width: 25%" >
						<form:option value="ACTIVE" label="ACTIVE" />
				  <form:option value="INACTIVE" label="INACTIVE" />
					</form:select>
					<form:errors path="jiraProjectName" cssStyle="color: red;" />
				</div>
				
				<button type="submit" class="btn btn-default">Update</button>
				<button type="reset" class="btn btn-default">Reset</button>
				<button type="submit" class="btn btn-default">
					<a href="/spring-mvc-jpa-example/project">Cancel</a>
				</button>

			</form:form>

			<br> <br>
			<button
				onclick="document.getElementById('id01').style.display='block'"
				style="width: auto;">Create New User</button>

			<div id="id01" class="modal">
				<span onclick="document.getElementById('id01').style.display='none'"
					class="close" title="Close Modal">×</span>
				<form class="modal-content animate" method="POST"
					action="/spring-mvc-jpa-example/jiraCreate">
					<div class="container">
						<h4>Create New User</h1>
						<label style="text-align: center;"><b>ProjectName</b></label> 
						<br>
						<input type="text" 
							name="projectName" required><br> <br> 
						<label><b>Email-Id:</b></label>
						<br>
						<input type="text" name="email" required><br> <br>
						<label><b>AssigneeName:</b></label> 
						<br><input type="text"
							name="assigneeName" required><br> <br>
						<div class="clearfix">
							<button type="button"
								onclick="document.getElementById('id01').style.display='none'"
								class="cancelbtn">Cancel</button>
							<button type="reset">Reset</button>
							<button type="submit" onclick="document.getElementById('id01').style.display='none'" class="signupbtn">SAVE</button>
						</div>
					</div>
				</form>
				<!--   <a href="https://www.w3schools.com">Visit W3Schools.com!</a>
 -->
			</div>
			<br> <br>
			<div>
				<button type="submit">
					<a href="/spring-mvc-jpa-example/jiraList">List Of User</a>
				</button>

			</div>
		</div>

	</c:param>
</c:import>