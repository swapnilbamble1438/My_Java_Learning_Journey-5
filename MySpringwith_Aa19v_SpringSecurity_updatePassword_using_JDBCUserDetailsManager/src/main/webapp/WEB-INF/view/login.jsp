
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
 
<%@ page isELIgnored= "false" %>

    <!-- jstl uri -->
   <%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


 <!--responsive purpose  -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<br>
<div style="text-align: center">
<h1> Login</h1>


<c:if test="${msg != null }">
		<span style="color:green">${msg}</span><br><br>
	</c:if>
	
	<c:if test="${param.error != null }">
		<span style="color:red">Please enter correct Username or password</span><br><br>
	</c:if>
	
	
	<c:if test="${param.logout != null}">
		<span style="color:red">You are successfully logout...</span><br><br>
	</c:if>
                
<form:form action="doLogin" method="POST">
	Username: <input type="text" name="username" required>
	<br><br>
	Password: <input type="password" name="password" required>
	<br><br>
	<input type ="submit" value="Login">
</form:form>

<br><br>
		<a href="signup"><button>Signup</button></a>
<br><br>
		<a href="${pageContext.request.contextPath}/"><button>Back To Home</button></a>		
		
	  

</div>
</div>

</body>
</html>