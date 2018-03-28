<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<style>
form {
	border: 1px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 12%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 11px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 10%;
}


.container {
	padding: 100px;
  text-align: center;
  height: 100%;
}

h1{
text-align: center;
}


</style>
<body>

<h1>Login</h1>
	<div class="container">
	
		<!-- <form action="/loginUser"> -->
		<form:form method="POST" commandName="team"
				action="${pageContext.request.contextPath}/loginUser" class="form">
			
			<div>
				<label><b>TeamName</b></label>
			</div><div>
				<input id="teamName" type="text" placeholder="Enter teamName" name="teamName"
					required>
			</div>
			<div>
				<label><b>TeamLoginName</b></label>
			</div><div>
				<input id="teamLoginName" type="text" placeholder="Enter team loginName" name="teamLoginName"
					required>
			</div>
			<div>
				<label><b>Password</b></label>
			</div><div>
				<input id="password" type="password" placeholder="Enter Password" name="teamLoginPwd"
					required>
			</div>
			<button  id="login" type="submit">Login</button>
		</form:form>
	</div>


</body>
</html>