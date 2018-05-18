 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/views/loginlayout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="New User" />
    <c:param name="body">
    	<div class="container" role="main">
		<h4 style="color:red;">Username or Password is wrong!!</h4>
		</div>
	</c:param>
</c:import> 