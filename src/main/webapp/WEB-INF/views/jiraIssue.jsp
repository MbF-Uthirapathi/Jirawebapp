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
	import="com.springmvc.controller.JiraUserController.*, java.util.*"%>

<c:import url="/WEB-INF/views/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="New User" />
	<c:param name="body">
		<div class="container" role="main">
			
			<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/issue" class="form">
			<div>
				<label>FromDate:</label><br>
				 <input id="formDate" type="date" name="fromDate" style="width: 15%" > 
				 <br><br>
				 <label>ToDate:</label><br>
				 <input id="toDate" type="date" name="toDate" style="width: 15%" > <br><br>
				  <label>AssignName:</label><br>
				  <input id="assignName" type="text" name="assignName" style="width: 15%" >
				 </div>
				<br>
				 <input id="submit" type="submit" name="Submit">
				 <input id="reset" type="reset" name="Reset">
			</form:form>
		</div>
	</c:param>
</c:import>